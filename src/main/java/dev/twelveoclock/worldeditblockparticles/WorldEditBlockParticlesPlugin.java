package dev.twelveoclock.worldeditblockparticles;

import dev.twelveoclock.worldeditblockparticles.commands.BlockParticlesCommand;
import dev.twelveoclock.worldeditblockparticles.module.BlockParticlesModule;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.io.File;
import java.util.Objects;

/**
 * A template plugin that incorporates the basics for every plugin I make
 *
 * Features:
 *  - Modules
 *  - Basic utilities
 *  - Latest Java features
 *  - Toml based config loading
 */

// TODO: Rename to BlockParticlesPlugin, no worldedit needed here
public final class WorldEditBlockParticlesPlugin extends JavaPlugin {

    private BlockParticlesModule blockParticlesModule;

    /**
     * Constructor for MockBukkit
     */
    public WorldEditBlockParticlesPlugin() {
        super();
    }

    /**
     * Constructor for MockBukkit
     */
    public WorldEditBlockParticlesPlugin(final JavaPluginLoader loader, final PluginDescriptionFile description, final File dataFolder, final File file) {
        super(loader, description, dataFolder, file);
    }


    @Override
    public void onLoad() {
        blockParticlesModule = new BlockParticlesModule(this);
    }

    @Override
    public void onEnable() {

        blockParticlesModule.enable();

        final PluginCommand pluginCommand = Objects.requireNonNull(this.getCommand("blockparticles"));
        final BlockParticlesCommand blockParticlesCommand = new BlockParticlesCommand(this);

        pluginCommand.setExecutor(blockParticlesCommand);
        pluginCommand.setTabCompleter(blockParticlesCommand);
    }

    @Override
    public void onDisable() {
        blockParticlesModule.disable();
    }

    public BlockParticlesModule getBlockParticlesModule() {
        return blockParticlesModule;
    }

}
