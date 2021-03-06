/*
 * author: [USER_NOT_FOUND]
 * https://github.com/PurpleHead
 */
package at.terranova;

import at.terranova.generation.CustomChunkGenerator;
import at.terranova.generation.biomes.*;
import org.bukkit.Bukkit;
import org.bukkit.block.Biome;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public class TerraNova extends JavaPlugin {

    public static final String PLUGIN_NAME = "Terra_Nova";

    @Override
    public void onEnable() {
        super.onEnable();
        Bukkit.getLogger().info(String.format("[%s LOADED]", PLUGIN_NAME));

        CustomBiomeHandler handler = CustomBiomeHandler.getInstance();
        handler.registerCustomBiome(Biome.DESERT, new DesertBiome());
        handler.registerCustomBiome(Biome.SAVANNA, new SavannaBiome());
        handler.registerCustomBiome(Biome.OCEAN, new OceanBiome());
        handler.registerCustomBiome(Biome.FOREST, new ForestBiome());
        handler.registerCustomBiome(Biome.SNOWY_TAIGA, new SnowyTaigaBiome());
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
