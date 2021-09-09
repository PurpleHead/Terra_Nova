/*
 * author: [USER_NOT_FOUND]
 * https://github.com/PurpleHead
 */
package at.terranova.generation.populators;

import at.terranova.heightprovider.NoiseProvider;
import org.bukkit.*;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

public class TreePopulator extends BlockPopulator {

    private final NoiseProvider provider;

    public TreePopulator(NoiseProvider provider) {
        this.provider = provider;
    }

    @Override
    public void populate(World world, Random random, Chunk source) {
        boolean populate = random.nextBoolean();
        if(populate) {
            int amount = 1 + random.nextInt(5);
            for (int i = 0; i < amount; i++) {
                int blockX = random.nextInt(15);
                int blockZ = random.nextInt(15);
                int blockY = (int) provider.getHeight(blockX, blockZ, source.getX(), source.getZ());

                try {
                    world.generateTree(source.getBlock(blockX, blockY + 1, blockZ).getLocation(), TreeType.TREE);
                } catch (IllegalStateException e) {
                }
            }
        }
    }
}
