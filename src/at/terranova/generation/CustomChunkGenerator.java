/*
 * author: [USER_NOT_FOUND]
 * https://github.com/PurpleHead
 */
package at.terranova.generation;

import at.terranova.Pair;
import at.terranova.generation.biomes.CustomBiome;
import at.terranova.generation.biomes.CustomBiomeHandler;
import at.terranova.generation.populators.CustomBiomeProvider;
import at.terranova.generation.populators.DecorationPopulator;
import at.terranova.generation.populators.TreePopulator;
import at.terranova.heightprovider.NoiseProvider;
import at.terranova.heightprovider.SimplexNoiseProvider;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
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
                int y = (int) provider.getHeight(x, z, chunkX, chunkZ);
                CustomBiome customBiome = customBiomeHandler.getCustomBiome(chunkData.getBiome(x, y, z));

                if(y < SEA_MAX_LEVEL) {
                    customBiome = customBiomeHandler.getCustomBiome(Biome.OCEAN);
                }

                customBiome.generate(provider, worldInfo, random, x, y, z, chunkData);

                if((y == SEA_MAX_LEVEL || isInOceanRadius(x, z, chunkX, chunkZ, provider, 5, 1)) && customBiome.shouldGenerateBeach()) {
                    chunkData.setBlock(x, y, z, Material.SAND);
                }
            }
        }
    }

    public static boolean isInOceanRadius (int x, int z, int chunkX, int chunkZ, NoiseProvider provider, int radius, int steps) {
        x = chunkX * CHUNK_SIZE + x;
        z = chunkZ * CHUNK_SIZE + z;

        return isInOceanRadius(x, z, provider, radius, steps);
    }

    public static boolean isInOceanRadius (int x, int z, NoiseProvider provider, int radius, int steps) {
        if(steps > radius) {
            throw new IllegalArgumentException("Steps must be smaller than the radius");
        }
        boolean found = false;
        for (int i = -radius; i <= radius && !found; i += steps) {
            for (int t = -radius; t <= radius && !found; t += steps) {
                if (provider.getHeight(x + i, z + t) <= SEA_MAX_LEVEL) {
                    found = true;
                }
            }
        }
        return found;
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
        NoiseProvider provider = new SimplexNoiseProvider(getSeeds(world.getSeed()), OCTAVES, FREQ_AMP);
        return Arrays.asList(new TreePopulator(provider), new DecorationPopulator(provider));
    }

    @Override
    public boolean shouldGenerateCaves() {
        return false;
    }

    @Override
    public boolean shouldGenerateBedrock() {
        return true;
    }
}
