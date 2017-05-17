package com.newbee.summary.util.lambda;


import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

/**
 * Created by kl09 on 2017/4/24.
 */
public class Demo1 {
    public final static ThreadLocal<DateFormatter> formatter = ThreadLocal.withInitial(() -> new DateFormatter(new SimpleDateFormat("dd-MMM-yyyy")));

    public static void main(String[] args) {
        Predicate<Integer> atLeast5 = x -> x > 5;
        boolean test = atLeast5.test(4);
        System.out.println(test);
        System.out.println("------------------------------");
        System.out.println(binaryTest(2L, 8L));
        threadLocalTest();


    }

    /**
     * 两个数的乘积
     *
     * @param valueA
     * @param valueB
     * @return
     */
    private static Long binaryTest(Long valueA, Long valueB) {
        BinaryOperator<Long> add = (x, y) -> x * y;
        return add.apply(valueA, valueB);
    }

    private static void threadLocalTest() {
        String format = formatter.get().getFormat().format(new Date());
        System.out.println(format);
    }
}
