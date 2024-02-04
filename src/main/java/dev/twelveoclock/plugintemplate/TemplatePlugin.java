package dev.twelveoclock.plugintemplate;

import com.fasterxml.jackson.dataformat.toml.TomlMapper;
import dev.twelveoclock.plugintemplate.config.PluginConfig;
import dev.twelveoclock.plugintemplate.module.CatModule;
import dev.twelveoclock.plugintemplate.module.PlayerModule;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * A template plugin that incorporates the basics for every plugin I make
 *
 * Features:
 *  - Modules
 *  - Basic utilities
 *  - Latest Java features
 *  - Toml based config loading
 */
public final class TemplatePlugin extends JavaPlugin {

    private CatModule catModule;

    private PluginConfig pluginConfig;

    private final PlayerModule playerModule = new PlayerModule(this);

    /**
     * Constructor for MockBukkit
     */
    public TemplatePlugin() {
        super();
    }

    /**
     * Constructor for MockBukkit
     */
    public TemplatePlugin(final JavaPluginLoader loader, final PluginDescriptionFile description, final File dataFolder, final File file) {
        super(loader, description, dataFolder, file);
    }


    @Override
    public void onLoad() {
        loadConfig();
        catModule = new CatModule(this, pluginConfig);
    }

    @Override
    public void onEnable() {
        catModule.enable();
        playerModule.enable();
    }

    @Override
    public void onDisable() {
        catModule.disable();
        playerModule.disable();
    }


    /**
     * Loads the current config or copies the default
     */
    private void loadConfig() {

        final Path configPath = Path.of(getDataFolder().getAbsolutePath(), "config.toml");

        // Create the default config if no file exists
        if (Files.notExists(configPath)) {
            try (final InputStream inputStream = getClass().getResource("/config.toml").openStream()) {
                Files.createDirectories(configPath.getParent());
                Files.copy(inputStream, configPath);
            }
            catch (final IOException ex) {
                ex.printStackTrace();
            }
        }

        // Read config
        try (final InputStream inputStream = Files.newInputStream(configPath)) {
            pluginConfig = new TomlMapper().readValue(inputStream, PluginConfig.class);
        }
        catch (final IOException ex) {
            ex.printStackTrace();
        }
    }

}
