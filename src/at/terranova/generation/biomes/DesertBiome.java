/*
 * author: [USER_NOT_FOUND]
 * https://github.com/PurpleHead
 */
package at.terranova.generation.biomes;

import at.terranova.generation.biomes.decoration.CustomDecoration;
import at.terranova.generation.biomes.decoration.DesertDecoration;
import at.terranova.heightprovider.NoiseProvider;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class DesertBiome implements CustomBiome {

    @Override
    public void generate(NoiseProvider provider, WorldInfo worldInfo, Random random, int x, int height, int z, ChunkGenerator.ChunkData chunkData) {
        int sandDepth = 7 + random.nextInt(5);
        for(int i = height - 1; i > height - sandDepth; i--) {
            if(i < (height - (sandDepth / 2))) {
                chunkData.setBlock(x, i, z, Material.SANDSTONE);
            } else {
                chunkData.setBlock(x, i, z, Material.SAND);
            }

        }
        for (int i = height - sandDepth; i > 0; i--) {
            chunkData.setBlock(x, i, z, Material.STONE);
        }

        chunkData.setBlock(x, height, z, Material.SAND);
    }

    @Override
    public List<TreeType> getTreeTypes() {
        return new LinkedList<>();
    }

    @Override
    public List<CustomDecoration> getDecorations() {
        return Arrays.asList(new DesertDecoration()); //Arrays.asList(Material.CACTUS, Material.DEAD_BUSH);
    }

    @Override
    public boolean shouldGenerateBeach() {
        return false;
    }

}
