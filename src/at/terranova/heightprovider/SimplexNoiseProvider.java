/*
 * author: [USER_NOT_FOUND]
 * https://github.com/PurpleHead
 */
package at.terranova.heightprovider;

import at.terranova.Pair;
import at.terranova.generation.CustomChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import java.util.LinkedList;
import java.util.List;

public class SimplexNoiseProvider implements NoiseProvider {

    private List<SimplexOctaveGenerator> generators = new LinkedList<>();
    private List<Pair<Double, Double>> freqAmp;

    private final int SEED_AMOUNT;

    public SimplexNoiseProvider (long[] seeds, int octaves, List<Pair<Double, Double>> freqAmp) {
        this.SEED_AMOUNT = seeds.length;
        for (long s : seeds) {
            SimplexOctaveGenerator g = new SimplexOctaveGenerator(s, octaves);
            g.setScale(0.005);
            generators.add(g);
        }
        this.freqAmp = freqAmp;
    }

    @Override
    public double getHeight(int x, int z, int chunkX, int chunkZ) {
        double noise = 0;
        for (Pair<Double, Double> p : this.freqAmp) {
            double frequency = p.getT();
            double amplitude = p.getE();

            for(SimplexOctaveGenerator g : generators) {
                noise += g.noise(chunkX* CustomChunkGenerator.CHUNK_SIZE +x, chunkZ*CustomChunkGenerator.CHUNK_SIZE+z, frequency, amplitude) * 15;
            }
        }
        return Math.abs((noise + (50)) * 0.3);
    }

    @Override
    public double getHeight(int x, int z) {
        int i = x % CustomChunkGenerator.CHUNK_SIZE;
        int j = z % CustomChunkGenerator.CHUNK_SIZE;
        return getHeight(i, j, (x - i) / CustomChunkGenerator.CHUNK_SIZE, (z - j) / CustomChunkGenerator.CHUNK_SIZE);
    }

    public int getSeedAmount() {
        return SEED_AMOUNT;
    }
}
