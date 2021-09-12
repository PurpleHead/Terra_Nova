/*
 * author: [USER_NOT_FOUND]
 * https://github.com/PurpleHead
 */
package at.terranova.generation.biomes.decoration;

import org.bukkit.Material;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.BlockData;
import org.bukkit.generator.LimitedRegion;
import org.bukkit.generator.WorldInfo;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GrassDecoration extends CustomDecoration {

    @Override
    public List<Material> getMaterials() {
        return Arrays.asList(Material.TALL_GRASS, Material.GRASS);
    }

    @Override
    public float getProbability() {
        return 0.8f;
    }

    @Override
    public void generate(WorldInfo worldInfo, Random random, int x, int y, int z, LimitedRegion limitedRegion) {
        Material m = getMaterials().get(random.nextInt(getMaterials().size()));

        if(m == Material.TALL_GRASS && random.nextBoolean()) {
            limitedRegion.setType(x, y, z, m);
            limitedRegion.setType(x, y + 1, z, m);

            BlockData data = limitedRegion.getBlockData(x, y+1, z);
            if (data instanceof Bisected) {
                ((Bisected) data).setHalf(Bisected.Half.TOP);
                limitedRegion.setBlockData(x, y+1, z, data);
            }
        } else {
            limitedRegion.setType(x, y, z, m);
        }
    }

    @Override
    public boolean useCustomGenerateMethod() {
        return true;
    }

    @Override
    public boolean useCustomAmount() {
        return false;
    }

    @Override
    public int getAmount(Random random) {
        return 0;
    }

}
