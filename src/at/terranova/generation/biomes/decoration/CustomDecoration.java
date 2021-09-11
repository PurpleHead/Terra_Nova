/*
 * author: [USER_NOT_FOUND]
 * https://github.com/PurpleHead
 */
package at.terranova.generation.biomes.decoration;

import org.bukkit.Material;
import org.bukkit.generator.LimitedRegion;
import org.bukkit.generator.WorldInfo;

import java.util.List;
import java.util.Random;

public abstract class CustomDecoration {

    public abstract List<Material> getMaterials();
    public abstract float getProbability();
    public abstract void generate (WorldInfo worldInfo, Random random, int x, int y, int z, LimitedRegion limitedRegion);
    public abstract boolean useCustomGenerateMethod();

}
