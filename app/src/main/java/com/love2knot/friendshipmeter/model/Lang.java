package com.love2knot.friendshipmeter.model;

public class Lang {
    String lanName;
    String lang;

    public Lang(String lanName, String lang) {
        this.lanName = lanName;
        this.lang = lang;
    }

    public String getLanName() {
        return lanName;
    }

    public void setLanName(String lanName) {
        this.lanName = lanName;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
