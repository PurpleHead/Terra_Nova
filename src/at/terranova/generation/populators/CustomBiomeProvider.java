/*
 * author: [USER_NOT_FOUND]
 * https://github.com/PurpleHead
 */
package at.terranova.generation.populators;

import at.terranova.generation.CustomChunkGenerator;
import at.terranova.heightprovider.NoiseProvider;

import at.terranova.heightprovider.SimplexNoiseProvider;
import at.terranova.util.Pair;
import org.bukkit.block.Biome;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.WorldInfo;

import java.util.*;

public class CustomBiomeProvider extends BiomeProvider {

    private final List<Biome> biomes = new LinkedList<>();
    private List<Pair<Biome, NoiseProvider>> biomeNoiseProviders = new LinkedList<>();
    private final int OCTAVES;
    private final List FREQ_AMP;
    private final NoiseProvider PROVIDER;

    public CustomBiomeProvider (int octaves, NoiseProvider provider, List freqAmp) {
        this.biomes.addAll(Arrays.asList(Biome.FOREST, Biome.DESERT, Biome.SAVANNA, Biome.SNOWY_TAIGA, Biome.SNOWY_TUNDRA));
        this.OCTAVES = octaves;
        this.PROVIDER = provider;
        this.FREQ_AMP = freqAmp;
        for(Biome b : this.biomes) {
            this.biomeNoiseProviders.add(new Pair<>(b, new SimplexNoiseProvider(CustomChunkGenerator.getSeeds(b.hashCode()), OCTAVES, FREQ_AMP)));
        }
    }

    @Override
    public Biome getBiome(WorldInfo worldInfo, int x, int y, int z) {
        Biome biome = Biome.FOREST;
        double height = 0;
        if (PROVIDER.getHeight(x, z) < CustomChunkGenerator.SEA_MAX_LEVEL) {
            return Biome.OCEAN;
        } else {
            for(Pair p : biomeNoiseProviders) {
                NoiseProvider provider = (NoiseProvider) p.getB();
                if (provider.getHeight(x, z) > height) {
                    biome = (Biome) p.getA();
                    height = provider.getHeight(x, z);
                }
            }
        }
        return biome;
    }

    @Override
    public List<Biome> getBiomes(WorldInfo worldInfo) {
        return biomes;
    }

}
