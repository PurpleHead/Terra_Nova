package at.terranova.generation;

import at.terranova.Pair;
import at.terranova.generation.populators.CustomBiomeProvider;
import at.terranova.generation.populators.TreePopulator;
import at.terranova.heightprovider.NoiseProvider;
import at.terranova.heightprovider.SimplexNoiseProvider;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CustomChunkGenerator extends ChunkGenerator {

    public static final int CHUNK_SIZE = 16;
    public static final int SEA_MAX_LEVEL = 63;
    public static final int SEA_MIN_LEVEL = 25;

    private static final int octaves = 10;
    private static final List freqAmp = Arrays.asList(new Pair<>(0.9, 0.9), new Pair<>(1.2, 0.9));

    public CustomChunkGenerator() {
    }

    @Override
    public void generateNoise(WorldInfo worldInfo, Random random, int chunkX, int chunkZ, ChunkData chunkData) {
        NoiseProvider provider = new SimplexNoiseProvider(worldInfo.getSeed(), octaves, freqAmp);
        for (int x = 0; x < CHUNK_SIZE; x++) {
            for (int z = 0; z < CHUNK_SIZE; z++) {
                double height = provider.getHeight(x, z, chunkX, chunkZ);

                // Generate ocean if necessary
                if (height < SEA_MAX_LEVEL) {
                    chunkData = generateSea(height, x, z, chunkData);
                }

                // Generate solid floor
                int dirtDepth = 2 + random.nextInt(10);
                for(int i = (int) height - 1; i > height - dirtDepth; i--) {
                    chunkData.setBlock(x, i, z, Material.DIRT);
                }
                for (int i = (int) height - dirtDepth; i > 0; i--) {
                    chunkData.setBlock(x, i, z, Material.STONE);
                }
                if (height < SEA_MAX_LEVEL + 2) {
                    for (int i = (int) height; i > (int) height - 2; i--) {
                        chunkData.setBlock(x, i, z, Material.SAND);
                    }
                } else {
                    chunkData.setBlock(x, (int) height, z, Material.GRASS_BLOCK);
                }
            }
        }
    }

    private ChunkData generateSea (double height, int x, int z, ChunkData chunkData) {
        if (height < SEA_MIN_LEVEL) {
            height++;
            for (;height < SEA_MIN_LEVEL; height++) {
                chunkData.setBlock(x, (int) height, z, Material.SAND);
            }
        }
        for (int i = (int) height + 1; i < SEA_MAX_LEVEL; i++) {
            chunkData.setBlock(x, i, z, Material.WATER);
        }
        return chunkData;
    }

    @Override
    public BiomeProvider getDefaultBiomeProvider(WorldInfo worldInfo) {
        return new CustomBiomeProvider(new SimplexNoiseProvider(worldInfo.getSeed(), octaves, freqAmp));
    }

    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
        return Arrays.asList(/*new TreePopulator(new SimplexNoiseProvider(world.getSeed(), octaves, freqAmp))*/);
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
