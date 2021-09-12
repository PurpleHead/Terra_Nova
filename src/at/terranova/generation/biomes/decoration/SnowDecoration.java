/*
 * author: [USER_NOT_FOUND]
 * https://github.com/PurpleHead
 */
package at.terranova.generation.biomes.decoration;

import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Snowable;
import org.bukkit.generator.LimitedRegion;
import org.bukkit.generator.WorldInfo;

import java.util.List;
import java.util.Random;

public class SnowDecoration extends CustomDecoration {

    @Override
    public List<Material> getMaterials() {
        return null;
    }

    @Override
    public float getProbability() {
        return 1;
    }

    @Override
    public void generate(WorldInfo worldInfo, Random random, int x, int y, int z, LimitedRegion limitedRegion) {
        BlockData data = limitedRegion.getBlockData(x, y-1, z);
        if(data instanceof Snowable) {
            ((Snowable) data).setSnowy(true);
            limitedRegion.setBlockData(x, y-1, z, data);
        }
        limitedRegion.setType(x, y, z, Material.SNOW);
    }

    @Override
    public boolean useCustomGenerateMethod() {
        return true;
    }

    @Override
    public boolean useCustomAmount() {
        return true;
    }

    @Override
    public int getAmount(Random random) {
        return 48 * 48;
    }

}
