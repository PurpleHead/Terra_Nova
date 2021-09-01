package at.terranova.generation.populators;

import at.terranova.generation.CustomChunkGenerator;
import at.terranova.heightprovider.NoiseProvider;
import org.bukkit.block.Biome;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.WorldInfo;

import java.util.Arrays;
import java.util.List;

public class CustomBiomeProvider extends BiomeProvider {

    private NoiseProvider provider;

    public CustomBiomeProvider (NoiseProvider provider) {
        this.provider = provider;
    }

    @Override
    public Biome getBiome(WorldInfo worldInfo, int x, int y, int z) {
        if (provider.getHeight(x, z) <= CustomChunkGenerator.SEA_MAX_LEVEL && provider.getHeight(x, z) >= CustomChunkGenerator.SEA_MIN_LEVEL) {
            return Biome.OCEAN;
        }
        return Biome.TAIGA;
    }

    @Override
    public List<Biome> getBiomes(WorldInfo worldInfo) {
        return Arrays.asList(Biome.OCEAN, Biome.TAIGA);
    }

}
