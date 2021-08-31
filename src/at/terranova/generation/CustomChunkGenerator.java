package at.terranova.generation;

import org.bukkit.Material;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import java.util.Random;

public class CustomChunkGenerator extends ChunkGenerator {

    private static final int CHUNK_SIZE = 16;
    private static final int SEA_LEVEL = 63;

    @Override
    public void generateNoise(WorldInfo worldInfo, Random random, int chunkX, int chunkZ, ChunkData chunkData) {
        SimplexOctaveGenerator generator = new SimplexOctaveGenerator(worldInfo.getSeed(), 8);

        generator.setScale(0.005);

        for (int x = 0; x < CHUNK_SIZE; x++) {
            for (int z = 0; z < CHUNK_SIZE; z++) {
                double height = generator.noise(chunkX*CHUNK_SIZE+x, chunkZ*CHUNK_SIZE+z, 0.9, 1.2) * 20 + 80;
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
