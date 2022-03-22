package br.com.delazari.discord.bot.listeners.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.javacord.api.event.message.MessageCreateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.delazari.discord.bot.listeners.RateListener;
import br.com.delazari.discord.bot.service.MessagingService;

@Component
public class RateListenerImpl implements RateListener {

    private final static Pattern PATTERN = Pattern.compile("!rate (\\w+)");

    @Autowired
    private MessagingService messagingService;

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if (event.getMessageContent().startsWith("!rate")) {
            Matcher matcher = PATTERN.matcher(event.getMessageContent());
            if (matcher.matches()) {
                int rating = (int) Math.floor(Math.random() * 100) + 1;

                messagingService.sendMessage(event.getMessageAuthor(), "Probabilidade",
                        event.getMessageAuthor().getDisplayName() + " é " + rating + "% "
                                + matcher.group(1),
                        "Recalcular?", "https://i.pinimg.com/originals/94/cc/d5/94ccd56f2a24d1eb9486d86fcee0b3b1.gif",
                        event.getChannel());
            } else {
                messagingService.sendMessage(event.getMessageAuthor(), "Probabilidade",
                        "Isso deu 100% errado xD. Você está tentando utilizar o comando `!rate` ? Por favor utilize a sintaxe correta: `!rate <palavra>`",
                        "Recalcular?", null, event.getChannel(), true);
            }
        }
    }

}