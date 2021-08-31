package br.com.delazari.discord;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import br.com.delazari.discord.config.Configuration;

@SpringBootApplication
public class DiscordApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscordApplication.class, args);
	}

	@Bean
	@ConfigurationProperties(value = "discord-api")
	public DiscordApi discordApi() {
		DiscordApi api = new DiscordApiBuilder().setToken(Configuration.TOKEN.getDescricao())
				.setAllNonPrivilegedIntents().login().join();


		api.addMessageCreateListener(event -> {
			if(event.getMessageContent().equals(".ping")){
				event.getChannel().sendMessage("Pong!");
			}
		});
		return api;
	}

}
