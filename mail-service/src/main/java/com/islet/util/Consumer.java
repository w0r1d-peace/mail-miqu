package com.islet.util;

import java.util.Objects;

/**
 * 支持两个入参 Consumer
 *
 * @author liqigui
 * @date 2020-10-27 10:00
 */
@FunctionalInterface
public interface Consumer<S, T> {


    /**
     * accept
     *
     * @param s 入参1
     * @param t 入参2
     */
    void accept(S s, T t);

    /**
     * Returns a composed {@code Consumer} that performs, in sequence, this operation followed by the {@code after}
     * operation. If performing either operation throws an exception, it is relayed to the caller of the composed
     * operation.  If performing this operation throws an exception, the {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code Consumer} that performs in sequence this operation followed by the {@code after}
     * operation
     * @throws NullPointerException if {@code after} is null
     */
    default Consumer<S, T> andThen(Consumer<S, T> after) {
        Objects.requireNonNull(after);
        return (S s, T t) -> {
            accept(s, t);
            after.accept(s, t);
        };
    }
}
