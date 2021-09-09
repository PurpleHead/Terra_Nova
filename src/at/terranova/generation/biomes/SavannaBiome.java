/*
 * author: [USER_NOT_FOUND]
 * https://github.com/PurpleHead
 */
package at.terranova.generation.biomes;

import at.terranova.heightprovider.NoiseProvider;
import org.bukkit.Material;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;

import java.util.Random;

public class SavannaBiome implements CustomBiome {
    @Override
    public void generate(NoiseProvider provider, WorldInfo worldInfo, Random random, int x, double height, int z, ChunkGenerator.ChunkData chunkData) {
        int dirtDepth = 8 + random.nextInt(3);
        for(int i = (int) height - 1; i > height - dirtDepth; i--) {
            chunkData.setBlock(x, i, z, Material.DIRT);
        }
        for (int i = (int) height - dirtDepth; i > 0; i--) {
            chunkData.setBlock(x, i, z, Material.STONE);
        }

        chunkData.setBlock(x, (int) height, z, Material.GRASS_BLOCK);
    }
}
