package com.newbee.summary.util.lambda.StringCollector;

/**
 * Created by kl09 on 2017/4/27.
 */
public class StringCombiner {
    private final String prefix;
    private final String suffix;
    private final String delim;
    private final StringBuilder builder;

    public StringCombiner(String delim, String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.delim = delim;
        this.builder = new StringBuilder();
    }


    /**
     * 返回链接元素后的结果
     *
     * @param element
     * @return
     */
    public StringCombiner add(String element) {
        if (!areAtStart()) {
            builder.append(delim);
        }
        builder.append(element);
        return this;
    }

    /**
     * 链接两个StringCombiner
     *
     * @param other
     * @return
     */
    public StringCombiner merge(StringCombiner other) {
        builder.append(other.builder);
        return this;
    }


    private boolean areAtStart() {
        return builder.length() == 0;
    }

    @Override
    public String toString() {
        return this.prefix + builder.toString() + this.suffix;
    }
}
