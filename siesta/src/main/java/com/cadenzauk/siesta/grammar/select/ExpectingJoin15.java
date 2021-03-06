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

package com.cadenzauk.siesta.grammar.select;

import com.cadenzauk.core.tuple.Tuple15;
import com.cadenzauk.core.tuple.Tuple16;
import com.cadenzauk.siesta.Alias;
import com.cadenzauk.siesta.JoinType;
import com.cadenzauk.siesta.Projection;
import com.cadenzauk.siesta.RowMappers;
import com.google.common.reflect.TypeToken;

public class ExpectingJoin15<RT1, RT2, RT3, RT4, RT5, RT6, RT7, RT8, RT9, RT10, RT11, RT12, RT13, RT14, RT15> extends InJoinExpectingAnd<ExpectingJoin15<RT1,RT2,RT3,RT4,RT5,RT6,RT7,RT8,RT9,RT10,RT11,RT12,RT13,RT14,RT15>,Tuple15<RT1,RT2,RT3,RT4,RT5,RT6,RT7,RT8,RT9,RT10,RT11,RT12,RT13,RT14,RT15>> {
    public ExpectingJoin15(SelectStatement<Tuple15<RT1,RT2,RT3,RT4,RT5,RT6,RT7,RT8,RT9,RT10,RT11,RT12,RT13,RT14,RT15>> statement) {
        super(statement);
    }

    public <R16> InJoinExpectingOn<ExpectingJoin16<RT1,RT2,RT3,RT4,RT5,RT6,RT7,RT8,RT9,RT10,RT11,RT12,RT13,RT14,RT15,R16>, Tuple16<RT1,RT2,RT3,RT4,RT5,RT6,RT7,RT8,RT9,RT10,RT11,RT12,RT13,RT14,RT15,R16>> join(Alias<R16> alias) {
        return join(JoinType.INNER, alias);
    }

    public <R16> InJoinExpectingOn<ExpectingJoin16<RT1,RT2,RT3,RT4,RT5,RT6,RT7,RT8,RT9,RT10,RT11,RT12,RT13,RT14,RT15,R16>, Tuple16<RT1,RT2,RT3,RT4,RT5,RT6,RT7,RT8,RT9,RT10,RT11,RT12,RT13,RT14,RT15,R16>> join(Class<R16> rowClass, String alias) {
        return join(JoinType.INNER, scope().database().table(rowClass).as(alias));
    }

    public <R16> InJoinExpectingOn<ExpectingJoin16<RT1,RT2,RT3,RT4,RT5,RT6,RT7,RT8,RT9,RT10,RT11,RT12,RT13,RT14,RT15,R16>, Tuple16<RT1,RT2,RT3,RT4,RT5,RT6,RT7,RT8,RT9,RT10,RT11,RT12,RT13,RT14,RT15,R16>> leftJoin(Alias<R16> alias) {
        return join(JoinType.LEFT_OUTER, alias);
    }

    public <R16> InJoinExpectingOn<ExpectingJoin16<RT1,RT2,RT3,RT4,RT5,RT6,RT7,RT8,RT9,RT10,RT11,RT12,RT13,RT14,RT15,R16>, Tuple16<RT1,RT2,RT3,RT4,RT5,RT6,RT7,RT8,RT9,RT10,RT11,RT12,RT13,RT14,RT15,R16>> leftJoin(Class<R16> rowClass, String alias) {
        return join(JoinType.LEFT_OUTER, scope().database().table(rowClass).as(alias));
    }

    public <R16> InJoinExpectingOn<ExpectingJoin16<RT1,RT2,RT3,RT4,RT5,RT6,RT7,RT8,RT9,RT10,RT11,RT12,RT13,RT14,RT15,R16>, Tuple16<RT1,RT2,RT3,RT4,RT5,RT6,RT7,RT8,RT9,RT10,RT11,RT12,RT13,RT14,RT15,R16>> rightJoin(Alias<R16> alias) {
        return join(JoinType.RIGHT_OUTER, alias);
    }

    public <R16> InJoinExpectingOn<ExpectingJoin16<RT1,RT2,RT3,RT4,RT5,RT6,RT7,RT8,RT9,RT10,RT11,RT12,RT13,RT14,RT15,R16>, Tuple16<RT1,RT2,RT3,RT4,RT5,RT6,RT7,RT8,RT9,RT10,RT11,RT12,RT13,RT14,RT15,R16>> rightJoin(Class<R16> rowClass, String alias) {
        return join(JoinType.RIGHT_OUTER, scope().database().table(rowClass).as(alias));
    }

    public <R16> InJoinExpectingOn<ExpectingJoin16<RT1,RT2,RT3,RT4,RT5,RT6,RT7,RT8,RT9,RT10,RT11,RT12,RT13,RT14,RT15,R16>, Tuple16<RT1,RT2,RT3,RT4,RT5,RT6,RT7,RT8,RT9,RT10,RT11,RT12,RT13,RT14,RT15,R16>> fullOuterJoin(Alias<R16> alias) {
        return join(JoinType.FULL_OUTER, alias);
    }

    public <R16> InJoinExpectingOn<ExpectingJoin16<RT1,RT2,RT3,RT4,RT5,RT6,RT7,RT8,RT9,RT10,RT11,RT12,RT13,RT14,RT15,R16>, Tuple16<RT1,RT2,RT3,RT4,RT5,RT6,RT7,RT8,RT9,RT10,RT11,RT12,RT13,RT14,RT15,R16>> fullOuterJoin(Class<R16> rowClass, String alias) {
        return join(JoinType.FULL_OUTER, scope().database().table(rowClass).as(alias));
    }

    private <R16> InJoinExpectingOn<ExpectingJoin16<RT1,RT2,RT3,RT4,RT5,RT6,RT7,RT8,RT9,RT10,RT11,RT12,RT13,RT14,RT15,R16>, Tuple16<RT1,RT2,RT3,RT4,RT5,RT6,RT7,RT8,RT9,RT10,RT11,RT12,RT13,RT14,RT15,R16>> join(JoinType joinType, Alias<R16> alias) {
        SelectStatement<Tuple16<RT1,RT2,RT3,RT4,RT5,RT6,RT7,RT8,RT9,RT10,RT11,RT12,RT13,RT14,RT15,R16>> select16 = new SelectStatement<>(
            scope().plus(alias),
            new TypeToken<Tuple16<RT1,RT2,RT3,RT4,RT5,RT6,RT7,RT8,RT9,RT10,RT11,RT12,RT13,RT14,RT15,R16>>() {},
            statement.from().join(joinType, alias),
            RowMappers.add16th(statement.rowMapper(), alias.rowMapper()),
            Projection.of(statement.projection(), Projection.of(alias)));
        return new InJoinExpectingOn<>(select16, ExpectingJoin16::new);
    }
}
