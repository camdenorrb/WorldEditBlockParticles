package dev.twelveoclock.worldeditblockparticles.particle;

import dev.twelveoclock.worldeditblockparticles.proto.BlockLocation;
import dev.twelveoclock.worldeditblockparticles.proto.BlockParticle;
import org.bukkit.Particle;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class ParticleRunner {

    private final Set<BlockParticle> blockParticles;

    private final int tickRate;

    // Setting -> value
    private final Map<Setting, String> settings = new HashMap<>();

    private final UUID worldUUID;

    private int tickCount;

    public ParticleRunner(final Set<BlockParticle> blockParticles, final int tickRate, final UUID worldUUID) {
        this.blockParticles = blockParticles;
        this.tickRate = tickRate;
        this.worldUUID = worldUUID;
    }

    public ParticleRunner(final int tickRate, final UUID worldUUID) {
        this.blockParticles = new HashSet<>();
        this.tickRate = tickRate;
        this.worldUUID = worldUUID;
    }

    public Set<BlockParticle> getBlockParticles() {
        return blockParticles;
    }

    public int getTickRate() {
        return tickRate;
    }


    public Map<Setting, String> getSettings() {
        return settings;
    }

    public UUID getWorldUUID() {
        return worldUUID;
    }

    public int getTickCount() {
        return tickCount;
    }

    public void setTickCount(final int tickCount) {
        this.tickCount = tickCount;
    }

    public void tick(final JavaPlugin plugin) {

        tickCount++;
        if (tickCount < tickRate) {
            return;
        }

        tickCount = 0;

        for (final var particle : blockParticles) {
            final BlockLocation location = particle.getBlockLocation();
            final var block = Objects.requireNonNull(plugin.getServer().getWorld(worldUUID)).getBlockAt(location.getX(), location.getY(), location.getZ());
            block.getWorld().spawnParticle(fromProto(particle.getParticle()), block.getLocation().add(0.5, 0, 0.5), particle.getCount());
        }
    }

    private Particle fromProto(final dev.twelveoclock.worldeditblockparticles.proto.Particle particle) {
        return Particle.valueOf(particle.name().substring("PARTICLE_".length()));
    }

    public enum Setting {
        RANDOM_TICKRATE, // Example "1-10"
    }

}
