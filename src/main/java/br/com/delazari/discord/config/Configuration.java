package br.com.delazari.discord.config;

public enum Configuration {

    TOKEN("ODgwNjQ1Mjg4NzQwNjU1MTE0.YShS1g.bPQQ26GNVBL7loG_DHme0lkLcfc");

    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private Configuration(String desc) {
        this.desc = desc;
    }

}
