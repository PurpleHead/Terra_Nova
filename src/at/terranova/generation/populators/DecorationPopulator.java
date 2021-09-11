/*
 * author: [USER_NOT_FOUND]
 * https://github.com/PurpleHead
 */
package at.terranova.generation.populators;

import at.terranova.generation.CustomChunkGenerator;
import at.terranova.generation.biomes.CustomBiome;
import at.terranova.generation.biomes.CustomBiomeHandler;
import at.terranova.generation.biomes.decoration.CustomDecoration;
import at.terranova.heightprovider.NoiseProvider;

import org.bukkit.Material;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.LimitedRegion;
import org.bukkit.generator.WorldInfo;

import java.util.List;
import java.util.Random;

public class DecorationPopulator extends BlockPopulator {

    private final NoiseProvider provider;

    public DecorationPopulator(NoiseProvider provider) {
        this.provider = provider;
    }

    @Override
    public void populate(WorldInfo worldInfo, Random random, int chunkX, int chunkZ, LimitedRegion limitedRegion) {
        int amount = 2 + random.nextInt(10);

        for(int i = 0; i < amount; i++) {
            int x = random.nextInt(CustomChunkGenerator.CHUNK_SIZE) + (chunkX * CustomChunkGenerator.CHUNK_SIZE);
            int z = random.nextInt(CustomChunkGenerator.CHUNK_SIZE) + (chunkZ * CustomChunkGenerator.CHUNK_SIZE);
            int y = (int) provider.getHeight(x, z);
            CustomBiomeHandler customBiomeHandler = CustomBiomeHandler.getInstance();
            CustomBiome customBiome = customBiomeHandler.getCustomBiome(limitedRegion.getBiome(x, y, z));

            if(limitedRegion.isInRegion(x, y, z) && limitedRegion.getType(x, y + 1, z) == Material.AIR) {
                List<CustomDecoration> decorations = customBiome.getDecorations();

                if(decorations != null && !decorations.isEmpty()) {
                    for (CustomDecoration d : decorations) {
                        int n = random.nextInt(100);
                        if(n < (d.getProbability() * 100) - 1) {
                            if (d.useCustomGenerateMethod()) {
                                d.generate(worldInfo, random, x, y + 1, z, limitedRegion);
                            } else {
                                Material m = d.getMaterials().get(random.nextInt(d.getMaterials().size()));
                                limitedRegion.setType(x, y + 1, z, m);
                            }
                            break;
                        }
                    }
                }
            }

        }
    }
}
