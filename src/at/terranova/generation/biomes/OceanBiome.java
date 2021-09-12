/*
 * author: [USER_NOT_FOUND]
 * https://github.com/PurpleHead
 */
package at.terranova.generation.biomes;

import at.terranova.generation.CustomChunkGenerator;
import at.terranova.generation.biomes.decoration.CustomDecoration;
import at.terranova.heightprovider.NoiseProvider;

import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;

import java.util.List;
import java.util.Random;

public class OceanBiome implements CustomBiome {

    @Override
    public void generate(NoiseProvider provider, WorldInfo worldInfo, Random random, int x, int height, int z, ChunkGenerator.ChunkData chunkData) {
        for (int i = 0; i < height; i++) {
            int sandDepth = 3 + random.nextInt(10);

            if(i > height - (sandDepth / 2)) {
                chunkData.setBlock(x, i, z, Material.SAND);
            } else if (i > height - sandDepth) {
                chunkData.setBlock(x, i, z, Material.SANDSTONE);
            } else {
                chunkData.setBlock(x, i, z, Material.STONE);
            }
        }
        for (int i = height; i <= CustomChunkGenerator.SEA_MAX_LEVEL; i++) {
            chunkData.setBlock(x, i, z, Material.WATER);
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

    @Override
    public boolean shouldGenerateBeach() {
        return true;
    }

}
