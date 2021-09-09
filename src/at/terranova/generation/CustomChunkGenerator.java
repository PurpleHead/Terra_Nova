/*
 * author: [USER_NOT_FOUND]
 * https://github.com/PurpleHead
 */
package at.terranova.generation;

import at.terranova.Pair;
import at.terranova.generation.biomes.CustomBiome;
import at.terranova.generation.biomes.CustomBiomeHandler;
import at.terranova.generation.populators.CustomBiomeProvider;
import at.terranova.generation.populators.TreePopulator;
import at.terranova.heightprovider.NoiseProvider;
import at.terranova.heightprovider.SimplexNoiseProvider;

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

    private static final int SEED_AMOUNT = 2;
    private static final int SEED_DIFF = 100;
    private static final int OCTAVES = 10;
    private static final List FREQ_AMP = Arrays.asList(new Pair<>(0.7, 1.25), new Pair<>(0.6, 1.25), new Pair<>(0.75, 1.25));

    private CustomBiomeHandler customBiomeHandler = CustomBiomeHandler.getInstance();

    public CustomChunkGenerator() {
    }

    @Override
    public void generateNoise(WorldInfo worldInfo, Random random, int chunkX, int chunkZ, ChunkData chunkData) {
        NoiseProvider provider = new SimplexNoiseProvider(getSeeds(worldInfo.getSeed()), OCTAVES, FREQ_AMP);
        for (int x = 0; x < CHUNK_SIZE; x++) {
            for (int z = 0; z < CHUNK_SIZE; z++) {
                double height = provider.getHeight(x, z, chunkX, chunkZ);
                CustomBiome customBiome = customBiomeHandler.getCustomBiome(chunkData.getBiome(x, (int) height, z));
                customBiome.generate(provider, worldInfo, random, x, height, z, chunkData);

                /*// Generate solid floor
                int dirtDepth = 2 + random.nextInt(10);
                for(int i = (int) height - 1; i > height - dirtDepth; i--) {
                    chunkData.setBlock(x, i, z, Material.DIRT);
                }
                for (int i = (int) height - dirtDepth; i > 0; i--) {
                    chunkData.setBlock(x, i, z, Material.STONE);
                }
                // TODO generate sand next to ocean
                if (height < SEA_MAX_LEVEL) {
                    for (int i = (int) height; i > (int) height - 2; i--) {
                        chunkData.setBlock(x, i, z, Material.SAND);
                    }
                } else {
                    chunkData.setBlock(x, (int) height, z, Material.GRASS_BLOCK);
                }*/

            }
        }
    }

    private long[] getSeeds (long seed) {
        long[] seeds = new long[SEED_AMOUNT];
        for (int i = 0; i < SEED_AMOUNT; i++) {
            if (i == 0)
                seeds[i] = seed - SEED_DIFF;
            else
                seeds[i] = seeds[i - 1] - SEED_DIFF;
        }
        return seeds;
    }

    @Override
    public BiomeProvider getDefaultBiomeProvider(WorldInfo worldInfo) {
        return new CustomBiomeProvider(new SimplexNoiseProvider(getSeeds(worldInfo.getSeed()), OCTAVES, FREQ_AMP));
    }

    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
        return Arrays.asList(new TreePopulator(new SimplexNoiseProvider(getSeeds(world.getSeed()), OCTAVES, FREQ_AMP)));
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
