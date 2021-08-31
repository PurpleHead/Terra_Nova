package at.terranova;

import at.terranova.generation.CustomChunkGenerator;
import org.bukkit.Bukkit;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public class TerraNova extends JavaPlugin {

    public static final String PLUGIN_NAME = "Terra_Nova";

    @Override
    public void onEnable() {
        super.onEnable();
        Bukkit.getLogger().info(String.format("[%s LOADED]", PLUGIN_NAME));
    }

    @Override
    public void onDisable() {
        super.onDisable();
        Bukkit.getLogger().info(String.format("[%s DISABLED]", PLUGIN_NAME));
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new CustomChunkGenerator();
    }
}
