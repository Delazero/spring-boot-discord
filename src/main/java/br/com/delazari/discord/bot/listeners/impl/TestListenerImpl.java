package br.com.delazari.discord.bot.listeners.impl;

import org.javacord.api.event.message.MessageCreateEvent;
import org.springframework.stereotype.Component;

import br.com.delazari.discord.bot.listeners.TestListener;

@Component
public class TestListenerImpl implements TestListener {

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if (event.getMessage().getContent().equalsIgnoreCase("!test")) {
            event.getChannel().sendMessage("Testado!!!");
        }
    }

}
