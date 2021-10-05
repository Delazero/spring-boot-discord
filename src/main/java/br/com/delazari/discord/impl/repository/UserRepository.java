package br.com.delazari.discord.impl.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.delazari.discord.impl.entity.User;

/**
 * 
 * @author delazari
 */

@Repository
public interface UserRepository extends MongoRepository<User, String>{
    
    Optional<User> findByDiscordId(String discordId);
}
