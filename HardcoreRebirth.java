package com.hardcorerebirth;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class HardcoreRebirth extends JavaPlugin implements Listener {

    private final HashMap<UUID, String> playerNewWorld = new HashMap<>();
    private final Random random = new Random();

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().info("HardcoreRebirth activé ! Bonne chance... 💀");
    }

    @Override
    public void onDisable() {
        getLogger().info("HardcoreRebirth désactivé.");
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        // Génère un nom de monde unique avec seed aléatoire
        long seed = random.nextLong();
        String worldName = "rebirth_" + player.getName().toLowerCase() + "_" + System.currentTimeMillis();

        getLogger().info("Génération d'un nouveau monde pour " + player.getName() + " avec la seed: " + seed);

        // Crée le nouveau monde
        WorldCreator creator = new WorldCreator(worldName);
        creator.seed(seed);
        creator.type(WorldType.NORMAL);
        World newWorld = Bukkit.createWorld(creator);

        if (newWorld != null) {
            newWorld.setDifficulty(org.bukkit.Difficulty.HARD);
            playerNewWorld.put(player.getUniqueId(), worldName);
            getLogger().info("Monde créé avec succès : " + worldName);
        } else {
            getLogger().warning("Échec de la création du monde pour " + player.getName());
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        if (playerNewWorld.containsKey(uuid)) {
            String worldName = playerNewWorld.get(uuid);
            World newWorld = Bukkit.getWorld(worldName);

            if (newWorld != null) {
                event.setRespawnLocation(newWorld.getSpawnLocation());
                playerNewWorld.remove(uuid);

                // Remet le joueur en mode survie
                Bukkit.getScheduler().runTaskLater(this, () -> {
                    player.setGameMode(GameMode.SURVIVAL);
                    player.sendMessage("§6💀 Tu es mort... mais tu renais dans un nouveau monde ! Bonne chance !");
                }, 5L);
            }
        }
    }
}
