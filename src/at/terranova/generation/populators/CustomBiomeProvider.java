package at.terranova.generation.populators;

import at.terranova.generation.CustomChunkGenerator;
import at.terranova.heightprovider.NoiseProvider;
import org.bukkit.block.Biome;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.WorldInfo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CustomBiomeProvider extends BiomeProvider {

    private NoiseProvider provider;

    private final int BIOME_HEIGHT = 1000;
    private final List<Biome> biomes = new LinkedList<>();

    public CustomBiomeProvider (NoiseProvider provider) {
        this.provider = provider;
        this.biomes.addAll(Arrays.asList(Biome.DESERT, Biome.SAVANNA, Biome.FOREST, Biome.TAIGA, Biome.SNOWY_TUNDRA));
    }

    @Override
    public Biome getBiome(WorldInfo worldInfo, int x, int y, int z) {
        int divider = BIOME_HEIGHT / this.biomes.size();
        if (provider.getHeight(x, z) <= CustomChunkGenerator.SEA_MAX_LEVEL && provider.getHeight(x, z) >= CustomChunkGenerator.SEA_MIN_LEVEL) {
            return Biome.OCEAN;
        }
        int index = Math.abs(z) >= 1000 ? biomes.size() - 1 : Math.abs(z / divider);
        return biomes.get(index);
    }

    @Override
    public List<Biome> getBiomes(WorldInfo worldInfo) {
        return biomes;
    }

}
