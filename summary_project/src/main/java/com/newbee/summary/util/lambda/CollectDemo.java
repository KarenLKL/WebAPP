package com.newbee.summary.util.lambda;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by kl09 on 2017/4/24.
 */
public class CollectDemo {
    public static List<String> getCollect(){
        List<String> collect = Stream.of("a", "b", "c").collect(Collectors.toList());
        return collect;
    }
}
