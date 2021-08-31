package at.terranova.generation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;

import java.util.Random;

public class CustomChunkGenerator extends ChunkGenerator {

    private static final int CHUNK_SIZE = 16;
    private static final int SEA_LEVEL = 63;

    @Override
    public void generateNoise(WorldInfo worldInfo, Random random, int x, int z, ChunkData chunkData) {
        Bukkit.getLogger().info(ChatColor.YELLOW + "[Generating Chunk]");
        for (int i = 0; i < CHUNK_SIZE; i++) {
            for (int j = 0; j < CHUNK_SIZE; j++) {
                chunkData.setBlock(i, SEA_LEVEL, j, Material.STONE);
            }
        }
    }
}
