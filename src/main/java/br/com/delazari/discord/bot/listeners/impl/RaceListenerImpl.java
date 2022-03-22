package br.com.delazari.discord.bot.listeners.impl;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.delazari.discord.bot.listeners.RaceListener;
import br.com.delazari.discord.bot.service.MessagingService;

@Component
public class RaceListenerImpl implements RaceListener {

    private static boolean active = false;

    @Autowired
    private MessagingService messagingService;

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if (event.getMessageContent().equalsIgnoreCase("!race")) {
            if (!active) {
                active = true;
                messagingService.sendMessage(event.getMessageAuthor(), "Começa a corrida!!",
                        "Seja o primeiro a **reajir** para ganhar", null,
                        "https://acegif.com/wp-content/gifs/race-flag-10.gif", event.getChannel())
                        .thenAccept(message -> {
                            message.addReactionAddListener(listener -> {
                                if (active) {
                                    message.edit(new EmbedBuilder().setTitle("Temos um vencedor!!")
                                            .setDescription("Parabéns!!! **"
                                                    + event.getServer().get().getMembers()
                                                    + "** venceu!!!\n A corrida acabou!!")
                                            .setFooter("Correr novamente?"));
                                    active = false;
                                }
                            });
                        });
            }
        }
    }
}