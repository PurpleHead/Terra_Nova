/*
 * author: [USER_NOT_FOUND]
 * https://github.com/PurpleHead
 */
package at.terranova.generation.biomes;

import at.terranova.generation.biomes.decoration.CustomDecoration;
import at.terranova.heightprovider.NoiseProvider;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.block.Biome;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;

import java.util.*;

public class CustomBiomeHandler {

    private static CustomBiomeHandler INSTANCE;
    private static final CustomBiome DEFAULT = new VoidBiome();

    private Map<Biome, CustomBiome> biomes = new HashMap<>();

    private CustomBiomeHandler() {

    }

    public static CustomBiomeHandler getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new CustomBiomeHandler();
        }
        return INSTANCE;
    }

    public CustomBiome getCustomBiome(Biome b) {
        return biomes.getOrDefault(b, DEFAULT);
    }

    public void registerCustomBiome(Biome biome, CustomBiome customBiome) {
        biomes.put(biome, customBiome);
    }

}

class VoidBiome implements CustomBiome {

    @Override
    public void generate(NoiseProvider provider, WorldInfo worldInfo, Random random, int x, int height, int z, ChunkGenerator.ChunkData chunkData) {
        for (int i = height; i > 0; i--) {
            chunkData.setBlock(x, i, z, Material.RED_TERRACOTTA);
        }
    }

    @Override
    public List<TreeType> getTreeTypes() {
        return null;
    }

    @Override
    public List<CustomDecoration> getDecorations() {
        return null;
    }

}
