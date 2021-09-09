/*
 * author: [USER_NOT_FOUND]
 * https://github.com/PurpleHead
 */
package at.terranova.generation.biomes;

import org.bukkit.block.Biome;

import java.util.HashMap;
import java.util.Map;

public class CustomBiomeHandler {

    private static CustomBiomeHandler INSTANCE;

    private Map<Biome, CustomBiome> biomes = new HashMap<>();

    private CustomBiomeHandler() {

    }

    public static CustomBiomeHandler getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new CustomBiomeHandler();
        }
        return INSTANCE;
    }

    public CustomBiome getCustomBiome(Biome b) {
        return biomes.get(b);
    }

}
