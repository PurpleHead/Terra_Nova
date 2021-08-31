package at.terranova.generation;

import at.terranova.heightprovider.NoiseProvider;
import at.terranova.heightprovider.SimplexNoiseProvider;
import org.bukkit.Material;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;

import java.util.Random;

public class CustomChunkGenerator extends ChunkGenerator {

    private static final int CHUNK_SIZE = 16;
    private static final int SEA_LEVEL = 63;

    @Override
    public void generateNoise(WorldInfo worldInfo, Random random, int chunkX, int chunkZ, ChunkData chunkData) {
        NoiseProvider provider = new SimplexNoiseProvider(worldInfo.getSeed(), 8);

        for (int x = 0; x < CHUNK_SIZE; x++) {
            for (int z = 0; z < CHUNK_SIZE; z++) {
                double height = provider.getHeight(x, z, chunkX, chunkZ, CHUNK_SIZE, 0.9, 1.2);
                if (height < SEA_LEVEL) {
                    for (int i = (int) height + 1; i < SEA_LEVEL; i++) {
                        chunkData.setBlock(x, i, z, Material.WATER);
                    }
                    if (height < SEA_LEVEL)
                        chunkData.setBlock(x, (int) height, z, Material.SAND);
                } else {
                    chunkData.setBlock(x, (int) height, z, Material.GRASS_BLOCK);
                }
                int dirtDepth = 2 + random.nextInt(10);
                for(int i = (int) height - 1; i > height - dirtDepth; i--) {
                    chunkData.setBlock(x, i, z, Material.DIRT);
                }
                for (int i = (int) height - dirtDepth; i > 0; i--) {
                    chunkData.setBlock(x, i, z, Material.STONE);
                }
            }
        }
    }

    @Override
    public boolean shouldGenerateCaves() {
        return true;
    }

    @Override
    public boolean shouldGenerateBedrock() {
        return true;
    }
}
