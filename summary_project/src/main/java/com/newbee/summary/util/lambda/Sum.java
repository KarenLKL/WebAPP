package com.newbee.summary.util.lambda;

import java.util.stream.Stream;

/**
 * Created by kl09 on 2017/4/24.
 */
public class Sum {

    private static Integer sum(Stream<Integer> t) {
        return t.reduce((acc, element) -> acc + element).get();
    }

    public static void main(String[] args) {
        Integer sum = sum(Stream.of(1, 2, 3, 4, 5, 6, 7));
        System.out.println(sum);
    }
}
