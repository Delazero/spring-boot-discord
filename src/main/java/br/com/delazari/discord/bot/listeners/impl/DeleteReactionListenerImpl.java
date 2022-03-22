package br.com.delazari.discord.bot.listeners.impl;

import org.javacord.api.event.message.reaction.ReactionAddEvent;
import org.springframework.stereotype.Component;

import br.com.delazari.discord.bot.listeners.DeleteReactionListener;

@Component
public class DeleteReactionListenerImpl implements DeleteReactionListener {

    @Override
    public void onReactionAdd(ReactionAddEvent event) {
        if (event.getEmoji().equalsEmoji("\uD83D\uDC4E")) {
            event.deleteMessage();
        }
    }

}