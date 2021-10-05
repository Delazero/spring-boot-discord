package br.com.delazari.discord.impl.entity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class GuildConfiguration {

    @Id
    private String id;

    @Indexed(unique = true)
    private String guildId;

    private LocalDate firstDay;

    private String prefix;

    private List<String> fixedChannels;
    

    public GuildConfiguration() {
        this.firstDay = LocalDate.now();
        this.prefix = ".";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGuildId() {
        return guildId;
    }

    public void setGuildId(String guildId) {
        this.guildId = guildId;
    }

    public LocalDate getFirstDay() {
        return firstDay;
    }

    public void setFirstDay(LocalDate firstDay) {
        this.firstDay = firstDay;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public List<String> getFixedChannels() {
        return fixedChannels;
    }

    public void setFixedChannels(List<String> fixedChannels) {
        this.fixedChannels = fixedChannels;
    }

}
