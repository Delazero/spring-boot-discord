package br.com.delazari.discord.config;

import java.util.Collection;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import br.com.delazari.discord.impl.service.UserService;

public class DiscordConfiguration {

    @Autowired
    private UserService userService;

    @Bean
    @ConfigurationProperties(value = "discord-api")
    public DiscordApi discordApi() {
        DiscordApi api = new DiscordApiBuilder().setToken(Configuration.TOKEN.getDesc()).setAllNonPrivilegedIntents()
                .login().join();

        api.addServerJoinListener(event -> {
            Collection<User> users = event.getServer().getMembers();
            users.stream().forEach(user -> {
                userService.salvar(user);
            });
        });

        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().equals(".ping")) {
                event.getChannel().sendMessage("Pong!");
            }
        });

        return api;
    }

}
