/*
 * Copyright (c) 2017 Cadenza United Kingdom Limited
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.cadenzauk.siesta.grammar.expression;

import com.cadenzauk.core.MockitoTest;
import com.cadenzauk.core.sql.RowMapper;
import com.cadenzauk.siesta.Database;
import com.cadenzauk.siesta.Dialect;
import com.cadenzauk.siesta.Scope;
import com.cadenzauk.siesta.type.DefaultIntegerTypeAdapter;
import com.google.common.reflect.TypeToken;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ObjectArrayArguments;
import org.mockito.Mock;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

class CastExpressionTest extends MockitoTest {
    @Mock
    private TypedExpression<String> expression;

    @Mock
    private Scope scope;

    @Mock
    private Dialect dialect;

    @Mock
    private ResultSet resultSet;

    @Mock
    private Database database;

    private static <T> Arguments testCaseForSql(String type, Function<CastBuilder<String>,CastExpression<String,T>> castFunction, Function<Dialect,String> dialectType) {
        return ObjectArrayArguments.create(type, castFunction, dialectType);
    }

    @SuppressWarnings("unused")
    private static Stream<Arguments> parametersForSql() {
        return Stream.of(
            testCaseForSql("bigint", CastBuilder::asBigInteger, Dialect::bigintType),
            testCaseForSql("char(3)", c -> c.asChar(3), d -> d.charType(3)),
            testCaseForSql("date", CastBuilder::asDate, Dialect::dateType),
            testCaseForSql("double precision", CastBuilder::asDoublePrecision, Dialect::doubleType),
            testCaseForSql("integer", CastBuilder::asInteger, Dialect::integerType),
            testCaseForSql("real", CastBuilder::asReal, Dialect::realType),
            testCaseForSql("smallint", CastBuilder::asSmallInteger, Dialect::smallintType),
            testCaseForSql("timestamp", CastBuilder::asTimestamp, d -> d.timestampType(Optional.empty())),
            testCaseForSql("timestamp(6)", c -> c.asTimestamp(6), d -> d.timestampType(Optional.of(6))),
            testCaseForSql("tinyint", CastBuilder::asTinyInteger, Dialect::tinyintType),
            testCaseForSql("varchar(8)", c -> c.asVarchar(8), d -> d.varcharType(8))
        );
    }

    @ParameterizedTest
    @MethodSource(names = "parametersForSql")
    <T> void sql(String type, Function<CastBuilder<String>,CastExpression<String,T>> castFunction, Function<Dialect,String> dialectType) {
        when(expression.sql(scope)).thenReturn("input");
        when(scope.dialect()).thenReturn(dialect);
        when(dialectType.apply(dialect)).thenReturn(type);
        CastBuilder<String> builder = new CastBuilder<>(expression);
        CastExpression<String,T> sut = castFunction.apply(builder);

        String result = sut.sql(scope);

        assertThat(result, is("cast(input as " + type + ")"));
        verifyNoMoreInteractions(expression, scope, dialect, database, resultSet);
    }

    @Test
    void label() {
        CastBuilder<String> builder = new CastBuilder<>(expression);
        CastExpression<String,Integer> sut = builder.asInteger();
        when(scope.newLabel()).thenReturn(345L);

        String result = sut.label(scope);

        assertThat(result, is("cast_345"));
        verifyZeroInteractions(expression, scope, dialect, database, resultSet);
    }

    @Test
    void rowMapper() throws SQLException {
        when(resultSet.getInt("bob")).thenReturn(44);
        when(resultSet.wasNull()).thenReturn(false);
        when(scope.database()).thenReturn(database);
        when(database.dialect()).thenReturn(dialect);
        when(dialect.type(Integer.class)).thenReturn(new DefaultIntegerTypeAdapter());
        CastBuilder<String> builder = new CastBuilder<>(expression);
        CastExpression<String,Integer> sut = builder.asInteger();

        RowMapper<Integer> result = sut.rowMapper(scope, "bob");

        assertThat(result.mapRow(resultSet), is(44));
        verifyNoMoreInteractions(expression, scope, dialect, database, resultSet);
    }

    @Test
    void type() {
        CastBuilder<String> builder = new CastBuilder<>(expression);
        CastExpression<String,LocalDate> sut = builder.asDate();

        TypeToken<LocalDate> result = sut.type();

        assertThat(result, is(TypeToken.of(LocalDate.class)));
        verifyNoMoreInteractions(expression, scope, dialect, database, resultSet);
    }

    @Test
    void args() {
        when(expression.args(scope)).thenReturn(Stream.of(1, 2));
        CastBuilder<String> builder = new CastBuilder<>(expression);
        CastExpression<String,LocalDate> sut = builder.asDate();

        Object[] result = sut.args(scope).toArray();

        assertThat(result, arrayContaining(1, 2));
        verifyNoMoreInteractions(expression, scope, dialect, database, resultSet);
    }

    @Test
    void precedence() {
        CastBuilder<String> builder = new CastBuilder<>(expression);
        CastExpression<String,String> sut = builder.asVarchar(20);

        Precedence result = sut.precedence();

        assertThat(result, is(Precedence.UNARY));
        verifyNoMoreInteractions(expression, scope, dialect, database, resultSet);
    }

    @Test
    void toStringFunction() {
        when(expression.toString()).thenReturn("input");
        CastBuilder<String> builder = new CastBuilder<>(expression);
        CastExpression<String,String> sut = builder.asVarchar(20);

        String result = sut.toString();

        assertThat(result, is("cast(input as varchar(20))"));
        verifyNoMoreInteractions(expression, scope, dialect, database, resultSet);
    }
}