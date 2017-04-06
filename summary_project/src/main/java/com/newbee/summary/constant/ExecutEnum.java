package com.newbee.summary.constant;


import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 操作结果
 * Created by kl09 on 2017/4/5.
 */
public enum ExecutEnum {
    EXECUTIVE_OK("1", "操作成功"),
    EXECUTIVE_FAIL("0", "操作失败");

    private String flag;
    private String message;

    public static Map<String, ExecutEnum> executEnums;

    ExecutEnum(String flag, String message) {
        this.flag = flag;
        this.message = message;
    }

    static {
        executEnums = new HashMap<>();
        for (ExecutEnum executEnum : ExecutEnum.values()) {
            executEnums.put(executEnum.getFlag(), executEnum);
        }
    }

    /**
     * 根据结果码返回该enum
     *
     * @param code
     * @return
     */
    public static ExecutEnum getExecutEnumByCode(String code) {
        if (StringUtils.isNotBlank(code) && executEnums.containsKey(code)) {
            return executEnums.get(code);
        }
        return null;
    }

    /**
     * 根据结果码返回操作结果
     *
     * @param code
     * @return
     */
    public static String getMessageByCode(String code) {
        if (StringUtils.isNotBlank(code) && executEnums.containsKey(code)) {
            return executEnums.get(code).getMessage();
        }
        return null;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
