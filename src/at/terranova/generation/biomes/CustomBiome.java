/*
 * author: [USER_NOT_FOUND]
 * https://github.com/PurpleHead
 */
package at.terranova.generation.biomes;

import at.terranova.heightprovider.NoiseProvider;
import org.bukkit.TreeType;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;

import java.util.List;
import java.util.Random;

public interface CustomBiome {

    void generate(NoiseProvider provider, WorldInfo worldInfo, Random random, int x, int height, int z, ChunkGenerator.ChunkData chunkData);
    List<TreeType> getTreeTypes();

}
