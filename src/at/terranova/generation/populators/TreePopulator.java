package at.terranova.generation.populators;

import at.terranova.generation.CustomChunkGenerator;
import at.terranova.heightprovider.NoiseProvider;
import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.LimitedRegion;
import org.bukkit.generator.WorldInfo;

import java.util.Random;

public class TreePopulator extends BlockPopulator {

    private final NoiseProvider provider;

    public TreePopulator(NoiseProvider provider) {
        this.provider = provider;
    }

    @Override
    public void populate(WorldInfo worldInfo, Random random, int chunkX, int chunkZ, LimitedRegion limitedRegion) {
        boolean populate = random.nextBoolean();
        if(populate) {
            int amount = 1 + random.nextInt(10);
            for (int i = 0; i < amount; i++) {
                int blockX = random.nextInt(CustomChunkGenerator.CHUNK_SIZE) + (chunkX * CustomChunkGenerator.CHUNK_SIZE);
                int blockZ = random.nextInt(CustomChunkGenerator.CHUNK_SIZE) + (chunkZ * CustomChunkGenerator.CHUNK_SIZE);
                int blockY = (int) provider.getHeight(blockX, blockZ);
                Location location = new Location(null, blockX, blockY + 1, blockZ);

                if (!limitedRegion.isInRegion(location)) {
                    return;
                }

                try {
                    TreeType type = getTreeType(limitedRegion.getBiome(blockX, blockY, blockZ), random);
                    if (type != null) {
                        limitedRegion.generateTree(location, random, type);
                    }
                } catch (IllegalStateException e) {
                }
            }
        }
    }

    private TreeType getTreeType (Biome b, Random random) {
        TreeType type = null;

        switch(b) {
            case SAVANNA:
                type = TreeType.ACACIA;
                break;
            case FOREST:
                type = random.nextBoolean() ? TreeType.TREE : TreeType.BIG_TREE;
                break;
            case TAIGA:
                type = random.nextBoolean() ? TreeType.MEGA_REDWOOD : TreeType.REDWOOD;
            default:
                break;
        }

        return type;
    }
}
