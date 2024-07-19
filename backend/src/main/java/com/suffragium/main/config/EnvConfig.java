package com.suffragium.main.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import java.util.Objects;

@Configuration
public class EnvConfig {
    @PostConstruct
    public void loadEnv() {
        Dotenv dotenv = Dotenv.configure().load();
        System.setProperty("SPOTIFY_CLIENT_ID", Objects.requireNonNull(dotenv.get("SPOTIFY_CLIENT_ID")));
        System.setProperty("SPOTIFY_CLIENT_SECRET", Objects.requireNonNull(dotenv.get("SPOTIFY_CLIENT_SECRET")));
    }
}