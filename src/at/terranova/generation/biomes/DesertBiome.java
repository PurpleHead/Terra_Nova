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

public class DesertBiome implements CustomBiome {

    @Override
    public void generate(NoiseProvider provider, WorldInfo worldInfo, Random random, int x, double height, int z, ChunkGenerator.ChunkData chunkData) {
        int sandDepth = 7 + random.nextInt(5);
        for(int i = (int) height - 1; i > height - sandDepth; i--) {
            if(i < (height - (sandDepth / 2))) {
                chunkData.setBlock(x, i, z, Material.SANDSTONE);
            } else {
                chunkData.setBlock(x, i, z, Material.SAND);
            }

        }
        for (int i = (int) height - sandDepth; i > 0; i--) {
            chunkData.setBlock(x, i, z, Material.STONE);
        }

        chunkData.setBlock(x, (int) height, z, Material.SAND);
    }

}
