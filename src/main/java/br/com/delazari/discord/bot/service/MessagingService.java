package br.com.delazari.discord.bot.service;

import java.util.concurrent.CompletableFuture;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageAuthor;

public interface MessagingService {

    CompletableFuture<Message> sendMessage(MessageAuthor autor, String titulo, String descricao, String rodape,
            String thumbnail,
            TextChannel canal);

    void sendMessage(MessageAuthor autor, String titulo, String descricao, String rodape,
            String thumbnail,
            TextChannel canal, boolean delete);

}