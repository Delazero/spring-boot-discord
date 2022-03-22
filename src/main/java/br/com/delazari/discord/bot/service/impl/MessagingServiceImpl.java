package br.com.delazari.discord.bot.service.impl;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import javax.swing.plaf.ColorUIResource;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.delazari.discord.bot.listeners.DeleteReactionListener;
import br.com.delazari.discord.bot.service.MessagingService;

@Component
public class MessagingServiceImpl implements MessagingService {

    @Autowired
    private DeleteReactionListener deleteReactionListener;

    @Override
    public CompletableFuture<Message> sendMessage(MessageAuthor autor, String titulo, String descricao, String rodape,
            String thumbnail,
            TextChannel canal) {

        int red = (int) Math.floor(Math.random() * 255);
        int green = (int) Math.floor(Math.random() * 255);
        int blue = (int) Math.floor(Math.random() * 255);

        return new MessageBuilder().setEmbed(new EmbedBuilder()
                .setAuthor(autor)
                .setTitle(titulo)
                .setDescription(descricao)
                .setFooter(rodape)
                .setThumbnail(thumbnail)
                .setColor(new ColorUIResource(red, green, blue))).send(canal);
    }

    @Override
    public void sendMessage(MessageAuthor autor, String titulo, String descricao, String rodape, String thumbnail,
            TextChannel canal, boolean delete) {
        this.sendMessage(autor, titulo, descricao, rodape, thumbnail, canal)
                .thenAccept(message -> message.addReactionAddListener(deleteReactionListener)
                .removeAfter(30, TimeUnit.SECONDS));

    }
}