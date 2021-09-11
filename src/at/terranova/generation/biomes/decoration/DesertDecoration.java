/*
 * author: [USER_NOT_FOUND]
 * https://github.com/PurpleHead
 */
package at.terranova.generation.biomes.decoration;

import org.bukkit.Material;
import org.bukkit.generator.LimitedRegion;
import org.bukkit.generator.WorldInfo;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DesertDecoration extends CustomDecoration {
    @Override
    public List<Material> getMaterials() {
        return Arrays.asList(Material.DEAD_BUSH, Material.CACTUS);
    }

    @Override
    public float getProbability() {
        return 0.2f;
    }

    @Override
    public void generate(WorldInfo worldInfo, Random random, int x, int y, int z, LimitedRegion limitedRegion) {
        Material m = getMaterials().get(random.nextInt(getMaterials().size()));

        if(m == Material.CACTUS && random.nextBoolean()) {
            limitedRegion.setType(x, y, z, m);
            limitedRegion.setType(x, y + 1, z, m);
        } else {
            limitedRegion.setType(x, y, z, m);
        }
    }

    @Override
    public boolean useCustomGenerateMethod() {
        return true;
    }
}
