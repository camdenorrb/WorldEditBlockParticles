package dev.twelveoclock.worldeditblockparticles.module;

import dev.twelveoclock.worldeditblockparticles.WorldEditBlockParticlesPlugin;
import dev.twelveoclock.worldeditblockparticles.module.base.PluginModule;
import dev.twelveoclock.worldeditblockparticles.particle.ParticleRunner;
import dev.twelveoclock.worldeditblockparticles.proto.BlockParticle;
import dev.twelveoclock.worldeditblockparticles.proto.BlockParticles;
import org.bukkit.scheduler.BukkitTask;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;

public final class BlockParticlesModule extends PluginModule {

    private final WorldEditBlockParticlesPlugin plugin;

    private final Map<String, ParticleRunner> particleRunners = new HashMap<>();

    private Path particlesPath;

    private BukkitTask tickerTask;


    public BlockParticlesModule(WorldEditBlockParticlesPlugin plugin) {
        super(plugin);
        this.plugin = plugin;
    }

    @Override
    protected void onEnable() {

        particlesPath = plugin.getDataFolder().toPath().resolve("particles");

        load();

        tickerTask = plugin.getServer().getScheduler().runTaskTimer(plugin, this::tick, 0, 1);
    }

    @Override
    protected void onDisable() {
        tickerTask.cancel();
        save();
    }

    private void tick() {
        for (final var particleRunner : particleRunners.values()) {
            particleRunner.tick(plugin);
        }
    }


    private void save() {

        try {
            Files.createDirectories(particlesPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        particleRunners.forEach(this::save);
    }

    public void save(final String name, final ParticleRunner runner) {

        final UUID worldUUID = runner.getWorldUUID();

        final dev.twelveoclock.worldeditblockparticles.proto.UUID worldUUIDProto = dev.twelveoclock.worldeditblockparticles.proto.UUID.newBuilder()
                .setLeastSigBits(worldUUID.getLeastSignificantBits())
                .setMostSigBits(worldUUID.getMostSignificantBits())
                .build();

        final BlockParticles particles = BlockParticles.newBuilder()
                .addAllBlockParticles(runner.getBlockParticles())
                .setTickRate(runner.getTickRate())
                .setWorldUUID(worldUUIDProto)
                .build();

        try {
            particles.writeTo(Files.newOutputStream(particlesPath.resolve(name + ".particle")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void load() {

        if (!Files.exists(particlesPath)) {
            return;
        }

        try(final var walkStream = Files.walk(particlesPath)) {
            walkStream.filter(path -> path.toString().endsWith(".particle")).forEach(path -> {
                try {

                    final BlockParticles particles = BlockParticles.parseFrom(Files.newInputStream(path));
                    final var worldUUID = particles.getWorldUUID();
                    final ParticleRunner particleRunner = new ParticleRunner(particles.getTickRate(), new UUID(worldUUID.getMostSigBits(), worldUUID.getLeastSigBits()));

                    particleRunner.getBlockParticles().addAll(particles.getBlockParticlesList());

                    var name = path.getFileName().toString();
                    name = name.substring(0, name.length() - ".particle".length());

                    particleRunners.put(name, particleRunner);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }


    public ParticleRunner set(final String name, final BlockParticle particle, final int tickRate, final UUID worldUUID) {
        final var particleRunner = new ParticleRunner(new HashSet<>(){{add(particle);}}, tickRate, worldUUID);
        return particleRunners.put(name, particleRunner);
    }

    public ParticleRunner rem(final String name) {

        final ParticleRunner runner = particleRunners.remove(name);
        if (runner != null) {
            try {
                Files.delete(particlesPath.resolve(name + ".particle"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return runner;
    }


    public Map<String, ParticleRunner> getParticleRunners() {
        return particleRunners;
    }



}
