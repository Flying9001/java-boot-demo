package com.ljq.demo.base.common.i18n;

import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

/**
 * @Description: 语言类型枚举类
 * @Author: junqiang.lu
 * @Date: 2020/9/3
 */
@Getter
@ToString
public enum LanguageEnum {

    /**
     * 美式英文
     */
    EN_US("en_us"),
    /**
     * 简体中文
     */
    ZH_CN("zh_cn");

    /**
     * 语言类型
     */
    private String language;

    private LanguageEnum (String language) {
        this.language = language;
    }

    /**
     * 获取语言类型(如果没有对应的语言类型,则返回中文)
     *
     * @param language 语言类型
     * @return
     */
    public static LanguageEnum getLanguage(String language) {
        if (Objects.isNull(language) || language.trim().isEmpty()) {
            return LanguageEnum.ZH_CN;
        }
        for (LanguageEnum languageEnum : LanguageEnum.values()) {
            if (languageEnum.language.equalsIgnoreCase(language)) {
                return languageEnum;
            }
        }
        return LanguageEnum.ZH_CN;
    }

}
