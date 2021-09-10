/*
 * author: [USER_NOT_FOUND]
 * https://github.com/PurpleHead
 */
package at.terranova.generation.biomes;

import at.terranova.heightprovider.NoiseProvider;
import org.bukkit.TreeType;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ForestBiome implements CustomBiome {
    @Override
    public void generate(NoiseProvider provider, WorldInfo worldInfo, Random random, int x, double height, int z, ChunkGenerator.ChunkData chunkData) {

    }

    @Override
    public List<TreeType> getTreeTypes() {
        return Arrays.asList();
    }
}
