package br.com.delazari.discord.impl.entity;

import java.awt.image.BufferedImage;
import java.util.concurrent.CompletableFuture;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @author delazari
 */

@Document
public class User {

    @Id
    private String id;

    private String tag;

    private String username;

    @Indexed(unique = true)
    private String discordId;

    private CompletableFuture<BufferedImage> userIcon;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDiscordId() {
        return discordId;
    }

    public void setDiscordId(String discordId) {
        this.discordId = discordId;
    }

    public CompletableFuture<BufferedImage> getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(CompletableFuture<BufferedImage> completableFuture) {
        this.userIcon = completableFuture;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((discordId == null) ? 0 : discordId.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (discordId == null) {
            if (other.discordId != null)
                return false;
        } else if (!discordId.equals(other.discordId))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

    public static User NewUserFromDiscord(org.javacord.api.entity.user.User user) {
        User userToSave = new User();
        userToSave.setDiscordId(user.getIdAsString());
        userToSave.setUsername(user.getName());
        userToSave.setTag(user.getDiscriminatedName());
        userToSave.setUserIcon(user.getAvatar().asBufferedImage());
        return userToSave;
    }

}
