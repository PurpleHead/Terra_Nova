/*
 * author: [USER_NOT_FOUND]
 * https://github.com/PurpleHead
 */
package at.terranova.generation.biomes;

import at.terranova.heightprovider.NoiseProvider;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;

import java.util.List;
import java.util.Random;

public class TaigaBiome implements CustomBiome {

    @Override
    public void generate(NoiseProvider provider, WorldInfo worldInfo, Random random, int x, int height, int z, ChunkGenerator.ChunkData chunkData) {
        int dirtDepth = 2 + random.nextInt(7);

        for(int i = height - 1; i > height - dirtDepth; i--) {
            chunkData.setBlock(x, i, z, Material.DIRT);
        }
        for(int i = height - dirtDepth; i > 0; i--) {
            chunkData.setBlock(x, i, z, Material.STONE);
        }
        chunkData.setBlock(x, height, z, Material.GRASS_BLOCK);
        chunkData.setBlock(x, height + 1, z, Material.SNOW);
    }

    @Override
    public List<TreeType> getTreeTypes() {
        return null;
    }

}
