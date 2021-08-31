package at.terranova.generation.populators;

import org.bukkit.*;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

public class TreePopulator extends BlockPopulator {

    @Override
    public void populate(World world, Random random, Chunk source) {
        boolean populate = random.nextBoolean();
        if(populate) {
            int amount = 1 + random.nextInt(5);
            for (int i = 0; i < amount; i++) {
                int blockX = random.nextInt(15);
                int blockZ = random.nextInt(15);
                int blockY = world.getMaxHeight() - 1;
                for(; (source.getBlock(blockX, blockY, blockZ).getType() == Material.AIR) && blockY > 63; blockY--) {}
                try {
                    world.generateTree(source.getBlock(blockX, blockY + 1, blockZ).getLocation(), TreeType.TREE);
                } catch (IllegalStateException e) {
                    Bukkit.getLogger().info("Cannot generate tree outside its chunk!");
                }
                //}
            }
        }
    }
}
