package dev.twelveoclock.worldeditblockparticles;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import org.bukkit.Sound;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

final class TemplatePluginTest {

    private static ServerMock server;

    private static WorldEditBlockParticlesPlugin plugin;


    @BeforeAll
    static void setUp() {
        server = MockBukkit.mock();
        plugin = MockBukkit.load(WorldEditBlockParticlesPlugin.class);
    }


    @Test
    void join() {
    }

    // TODO: Add more tests, unfortunately MockBukkit seems pretty limited


    @AfterAll
    static void tearDown() {
        MockBukkit.unmock();
    }

}
