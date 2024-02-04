package dev.twelveoclock.worldeditblockparticles.config;

import dev.twelveoclock.worldeditblockparticles.utils.ChatUtils;

/**
 * Represents the `config.toml` file's data
 */
public record PluginConfig(String catName) {

    public PluginConfig(final String catName) {
        this.catName = ChatUtils.colorize(catName);
    }

}