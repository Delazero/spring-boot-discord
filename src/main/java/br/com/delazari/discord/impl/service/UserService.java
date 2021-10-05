package br.com.delazari.discord.impl.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.delazari.discord.impl.entity.User;
import br.com.delazari.discord.impl.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;

    public User salvar(org.javacord.api.entity.user.User user) {
        Optional<User> find = repository.findByDiscordId(user.getIdAsString());
        if(!find.isPresent()) {
            User result = repository.save(User.NewUserFromDiscord(user));
            return result.getId() != null ? result : null;
        }
        return null;
    }
}
