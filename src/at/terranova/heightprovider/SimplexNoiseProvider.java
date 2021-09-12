/*
 * author: [USER_NOT_FOUND]
 * https://github.com/PurpleHead
 */
package at.terranova.heightprovider;

import at.terranova.Pair;
import at.terranova.generation.CustomChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SimplexNoiseProvider implements NoiseProvider {

    private List<SimplexOctaveGenerator> generators = new LinkedList<>();
    private List<Pair<Double, Double>> freqAmp;

    private Map<Pair<Integer, Integer>, Double> cachedHeights = new HashMap<>();

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
        x = chunkX* CustomChunkGenerator.CHUNK_SIZE +x;
        z = chunkZ*CustomChunkGenerator.CHUNK_SIZE+z;

        if(cachedHeights.size() > 2000000) {
            cachedHeights.clear();
        }

        if(!cachedHeights.containsKey(new Pair<>(x, z))) {
            for (Pair<Double, Double> p : this.freqAmp) {
                double frequency = p.getA();
                double amplitude = p.getB();

                for(SimplexOctaveGenerator g : generators) {
                    noise += g.noise(x, z, frequency, amplitude) * 15;
                }
            }
            noise = Math.abs(noise * 0.15) + 40;
            cachedHeights.put(new Pair<>(x, z), noise);
        } else {
            noise = cachedHeights.get(new Pair<>(x, z));
        }

        return noise;
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
