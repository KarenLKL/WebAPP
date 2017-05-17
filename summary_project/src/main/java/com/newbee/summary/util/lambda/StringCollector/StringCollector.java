package com.newbee.summary.util.lambda.StringCollector;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * 字符串收集器
 * Created by kl09 on 2017/4/27.
 */
public class StringCollector implements Collector<String, StringCombiner, String> {
    private final String prefix;
    private final String suffix;
    private final String delim;

    public StringCollector(String prefix, String suffix, String delim) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.delim = delim;
    }

    /**
     * 创建容器的工厂
     *
     * @return
     */
    @Override
    public Supplier<StringCombiner> supplier() {
        return () -> new StringCombiner(delim, prefix, suffix);
    }

    /**
     * 结合之前操作的结果和当前值，生成并返回新的值
     *
     * @return
     */
    @Override
    public BiConsumer<StringCombiner, String> accumulator() {
        return StringCombiner::add;
    }

    /**
     * 如果有两个容器，我们需要将其合并
     *
     * @return
     */
    @Override
    public BinaryOperator<StringCombiner> combiner() {
        return StringCombiner::merge;
    }

    /**
     * 最终结果字符串
     *
     * @return
     */
    @Override
    public Function<StringCombiner, String> finisher() {
        return StringCombiner::toString;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return null;
    }
}
