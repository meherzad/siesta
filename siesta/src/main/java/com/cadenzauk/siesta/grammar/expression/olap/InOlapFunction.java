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

package com.cadenzauk.siesta.grammar.expression.olap;

import com.cadenzauk.core.sql.RowMapper;
import com.cadenzauk.siesta.Scope;
import com.cadenzauk.siesta.grammar.expression.Precedence;
import com.cadenzauk.siesta.grammar.expression.TypedExpression;
import com.google.common.reflect.TypeToken;

import java.util.Optional;
import java.util.stream.Stream;

public abstract class InOlapFunction<T> implements TypedExpression<T> {
    protected final OlapFunction<T> function;

    InOlapFunction(OlapFunction<T> function) {
        this.function = function;
    }

    @Override
    public String label(Scope scope) {
        return function.label(scope);
    }

    @Override
    public RowMapper<T> rowMapper(Scope scope, Optional<String> label) {
        return function.rowMapper(scope, label.orElseGet(() -> label(scope)));
    }

    @Override
    public TypeToken<T> type() {
        return function.type();
    }

    @Override
    public String sql(Scope scope) {
        return function.sql(scope);
    }

    @Override
    public Stream<Object> args(Scope scope) {
        return function.args(scope);
    }

    @Override
    public Precedence precedence() {
        return function.precedence();
    }
}
