package dev.twelveoclock.worldeditblockparticles.commands;

import dev.twelveoclock.worldeditblockparticles.WorldEditBlockParticlesPlugin;
import dev.twelveoclock.worldeditblockparticles.module.BlockParticlesModule;
import dev.twelveoclock.worldeditblockparticles.particle.ParticleRunner;
import dev.twelveoclock.worldeditblockparticles.proto.BlockLocation;
import dev.twelveoclock.worldeditblockparticles.proto.BlockParticle;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.util.RayTraceResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public final class BlockParticlesCommand implements CommandExecutor, TabExecutor {

    private final BlockParticlesModule blockParticlesModule;


    public BlockParticlesCommand(final WorldEditBlockParticlesPlugin plugin) {
        this.blockParticlesModule = plugin.getBlockParticlesModule();
    }


    @Override
    public boolean onCommand(
            @NotNull final CommandSender sender,
            @NotNull final Command command,
            @NotNull final String label,
            @NotNull final String[] args
    ) {

        if (!(sender instanceof final Player player)) {
            sender.sendMessage("This command can only be ran as a Player");
            return true;
        }

        if (args.length == 0) {
            player.sendMessage("Usage: /blockparticles <add|rem|list>");
            return true;
        }

        switch (args[0].toLowerCase()) {

            case "create":
                // /blockparticles create <name> <tickrate>
                // Create a new block particle
                createBlockParticle(player, args);
                break;

            case "add":
                // /blockparticles add <name> <particle> (tickrate)
                // Add block in front of player
                addBlockParticle(player, args);
                break;

            case "rem":
                // /blockparticles rem <name>
                // Remove block in front of player
                remBlockParticle(player, args);
                break;

            case "list":
                // /blockparticles list
                // List all blocks in the world with their location, particle and tickrate
                listBlockParticles(player, args);
                break;

            case "settings":
                // /blockparticles settings
                // List all settings
                // /blockparticles settings <name> <setting> <value>

                // TODO: A setting for random tickrate per block, value can be a tick range
                // TODO: Maybe a GUI for this?
                break;

            case "fill":
                // /blockparticles fill <name> <particle> <count>
                // Flood fill blocks with block particles
                floodFillBlockParticles(player, args);
                break;

            default:
                player.sendMessage("Unknown subcommand: " + args[0].toLowerCase());
                break;

        }

        // Nautilus
        // Dolphin
        // dripping obsidian tear
        // Glow squid ink
        /*
        Thread.startVirtualThread(() -> {
            for (final Particle value : Particle.values()) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                player.sendMessage(value.name());

                try {
                    Objects.requireNonNull(world).spawnParticle(value, location.clone().add(location.getDirection().multiply(2)), 100);
                }
                catch (final Exception e) {
                    player.sendMessage("Failed to spawn particle: " + value.name());
                }
            }
        });*/

        return true;
    }

    private void floodFillBlockParticles(Player player, String[] args) {
        // /blockparticles fill <name> <particle> <count>
        // Flood fill blocks with block particles


        // Add block in front of player
        if (args.length < 4) {
            sendAddUsage(player);
            return;
        }

        final RayTraceResult found = player.rayTraceBlocks(10);
        if (found == null) {
            player.sendMessage("No block found in front of you");
            return;
        }

        final Block foundBlock = Objects.requireNonNull(found.getHitBlock());

        final String name = args[1];

        final ParticleRunner particleRunner = blockParticlesModule.getParticleRunners().get(name);
        if (particleRunner == null) {
            player.sendMessage("No block particle found with name: " + name);
            return;
        }

        final World particleWorld = Bukkit.getWorld(particleRunner.getWorldUUID());
        if (particleWorld == null) {
            player.sendMessage("Block particle is not in a loaded world, expected world: " + particleRunner.getWorldUUID());
            return;
        }

        if (!particleRunner.getWorldUUID().equals(player.getWorld().getUID())) {
            player.sendMessage("Block particle is not in the same world as you, expected world: " + particleWorld.getName());
            return;
        }

        final Particle bukkitParticle;
        try {
            bukkitParticle = Particle.valueOf(args[2].toUpperCase());
        } catch (final IllegalArgumentException e) {
            player.sendMessage("Invalid particle: " + args[2]);
            return;
        }

        final int count;
        try {
            count = Integer.parseInt(args[3]);
        } catch (final NumberFormatException e) {
            player.sendMessage("Invalid count: " + args[3]);
            return;
        }


        // Flood fill
        var visited = new HashSet<Block>();
        var blockStack = new ArrayDeque<Block>();

        blockStack.add(foundBlock);

        while (!blockStack.isEmpty()) {

            final var block = blockStack.pop();

            final BlockParticle particle = BlockParticle.newBuilder()
                    .setBlockLocation(BlockLocation.newBuilder().setX(block.getX()).setY(block.getY()).setZ(block.getZ()))
                    .setParticle(particleToProto(bukkitParticle))
                    .setCount(count)
                    .build();

            particleRunner.getBlockParticles().add(particle);

            visited.add(block);

            if (visited.size() > 10000) {
                player.sendMessage("Flood fill limit reached, stopping");
                break;
            }
            // Add all surrounding blocks
            for (final var face : BlockFace.values()) {
                final var relative = block.getRelative(face);
                if (relative.getType() == foundBlock.getType() && !visited.contains(relative)) {
                    blockStack.add(relative);
                }
            }
        }

        blockParticlesModule.save(name, particleRunner);
        player.sendMessage("Flood filled block particle: " + name + " " + bukkitParticle.name() + " " + particleRunner.getTickRate() + " ticks");

    }

    private void createBlockParticle(final Player player, final String[] args) {

        if (args.length < 3) {
            sendCreateUsage(player);
            return;
        }

        final String name = args[1];

        final int tickRate;
        try {
            tickRate = Integer.parseInt(args[2]);
        } catch (final NumberFormatException e) {
            player.sendMessage("Invalid tickrate: " + args[3]);
            return;
        }

        blockParticlesModule.getParticleRunners().put(name, new ParticleRunner(tickRate, player.getWorld().getUID()));
        player.sendMessage("Added block particle: " + name + " " + tickRate + " ticks");
    }

    private void addBlockParticle(final Player player, final String[] args) {

        // Add block in front of player
        if (args.length < 4) {
            sendAddUsage(player);
            return;
        }

        final RayTraceResult found = player.rayTraceBlocks(10);
        if (found == null) {
            player.sendMessage("No block found in front of you");
            return;
        }

        final Block foundBlock = Objects.requireNonNull(found.getHitBlock());


        final String name = args[1];

        final ParticleRunner particleRunner = blockParticlesModule.getParticleRunners().get(name);
        if (particleRunner == null) {
            player.sendMessage("No block particle found with name: " + name);
            return;
        }

        final World particleWorld = Bukkit.getWorld(particleRunner.getWorldUUID());
        if (particleWorld == null) {
            player.sendMessage("Block particle is not in a loaded world, expected world: " + particleRunner.getWorldUUID());
            return;
        }

        if (!particleRunner.getWorldUUID().equals(player.getWorld().getUID())) {
            player.sendMessage("Block particle is not in the same world as you, expected world: " + particleWorld.getName());
            return;
        }

        final Particle bukkitParticle;
        try {
            bukkitParticle = Particle.valueOf(args[2].toUpperCase());
        } catch (final IllegalArgumentException e) {
            player.sendMessage("Invalid particle: " + args[2]);
            return;
        }

        final int count;
        try {
            count = Integer.parseInt(args[3]);
        } catch (final NumberFormatException e) {
            player.sendMessage("Invalid count: " + args[3]);
            return;
        }

        final BlockParticle particle = BlockParticle.newBuilder()
                .setBlockLocation(BlockLocation.newBuilder().setX(foundBlock.getX()).setY(foundBlock.getY()).setZ(foundBlock.getZ()))
                .setParticle(particleToProto(bukkitParticle))
                .setCount(count)
                .build();

        particleRunner.getBlockParticles().add(particle);
        blockParticlesModule.save(name, particleRunner);

        player.sendMessage("Added block particle: " + name + " " + bukkitParticle.name() + " " + particleRunner.getTickRate() + " ticks " + count + " count");
    }

    private void remBlockParticle(final Player player, final String[] args) {

        // TODO: Add a way to list all block particles in a runner, with numbers
        // TODO: /blockparticles rem <name> <number>

        final String name = args[1];
        if (blockParticlesModule.rem(name) != null) {
            player.sendMessage("Removed block particle: " + name);
        }
        else {
            player.sendMessage("No block particle found with name: " + name);
        }
    }

    private void listBlockParticles(final Player player, final String[] args) {

        final String[] messages = blockParticlesModule.getParticleRunners().entrySet().stream().map(entry -> {
            final String name = entry.getKey();
            final ParticleRunner particleRunner = entry.getValue();
            return name + " " + particleRunner.getTickRate() + " ticks";
        }).toArray(String[]::new);

        player.sendMessage(messages);
        // List all blocks in the world with their location, particle and tickrate
    }


    private dev.twelveoclock.worldeditblockparticles.proto.Particle particleToProto(final Particle particle) {
        return dev.twelveoclock.worldeditblockparticles.proto.Particle.valueOf("PARTICLE_"+ particle.name());
    }


    private void sendCreateUsage(final Player player) {
        player.sendMessage("Usage: /blockparticles create <name> <tickrate>");
    }

    private void sendAddUsage(final Player player) {
        player.sendMessage("Usage: /blockparticles add <name> <particle> <count>");
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        return switch (args.length) {
            case 1 -> List.of("create", "add", "rem", "list", "settings", "fill");
            case 2 -> {
                if (equalsAny(args[0], "rem", "add", "settings", "fill")) {
                    yield blockParticlesModule.getParticleRunners().keySet().stream()
                            .filter(name -> name.toLowerCase().startsWith(args[1].toLowerCase()))
                            .toList();
                }
                yield null;
            }
            case 3 -> {
                if (equalsAny(args[0], "add", "fill")) {
                    yield Arrays.stream(Particle.values()).map(Enum::name)
                            .filter(name -> name.toLowerCase().startsWith(args[2].toLowerCase()))
                            .toList();
                }
                yield null;
            }
            default -> null;
        };
    }


    private boolean equalsAny(final String string, final String... strings) {
        return Arrays.stream(strings).anyMatch(string::equalsIgnoreCase);
    }

}
