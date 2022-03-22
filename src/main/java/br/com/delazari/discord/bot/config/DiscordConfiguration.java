package br.com.delazari.discord.bot.config;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.delazari.discord.bot.listeners.RaceListener;
import br.com.delazari.discord.bot.listeners.RateListener;
import br.com.delazari.discord.bot.listeners.TestListener;

@Configuration
public class DiscordConfiguration {

    @Value("${discord.token}")
    private String token;

    @Autowired
    private TestListener testListener;

    @Autowired
    private RateListener rateListener;

    @Autowired
    private RaceListener raceListener;

    @Bean
    @ConfigurationProperties(value = "discord-api")
    public DiscordApi discordApi() {
        DiscordApi api = new DiscordApiBuilder().setToken(token)
                .setAllNonPrivilegedIntents()
                .login()
                .join();

        api.addMessageCreateListener(testListener);
        api.addMessageCreateListener(rateListener);
        api.addMessageCreateListener(raceListener);

        return api;
    }

}
