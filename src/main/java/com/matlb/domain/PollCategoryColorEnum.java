package com.matlb.domain;

/**
 * Created by prassingh on 1/27/17.
 */
public enum PollCategoryColorEnum {

    GENERAL("GENERAL", "#FF9800"),
    POLITICS("POLITICS", "#FF9800"),
    SPORTS("SPORTS", "#FF9800"),
    INDIA("INDIA", "#FF9800") ,
    CRICKET("CRICKET","#FF9800") ,
    STOCK("STOCK", "#FF9800") ,
    FINANCE("FINANCE", "#FF9800") ,
    CAREER("CAREER","#FF9800") ,
    MOVIES("MOVIES", "#FF9800") ,
    SONGS("SONGS", "#FF9800") ,
    APPAREL("APPAREL" , "#FF9800") ,
    FASHION("FASHION" , "#FF9800") ,
    BOLLYWOOD("BOLLYWOOD" , "#FF9800") ,
    HOLLYWOOD("HOLLYWOOD", "#FF9800") ,
    TV_SERIES("TV_SERIES", "#FF9800") ,
    WEATHER("WEATHER", "#FF9800") ,
    DELHI("DELHI", "#FF9800") ,
    FOOD("FOOD" , "#FF9800");

    private String fullName;
    private String colorCode;

    PollCategoryColorEnum(String name, String code) {
        this.fullName = name;
        this.colorCode = code;
    }

    public String getFullName() {
        return fullName;
    }

    public String getColorCode() {
        return colorCode;
    }
}
