/*
 * author: [USER_NOT_FOUND]
 * https://github.com/PurpleHead
 */
package at.terranova.heightprovider;

import at.terranova.Pair;
import at.terranova.generation.CustomChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import java.util.List;

public class SimplexNoiseProvider implements NoiseProvider {

    private SimplexOctaveGenerator generator;

    private List<Pair<Double, Double>> freqAmp;

    public SimplexNoiseProvider (long seed, int octaves, List<Pair<Double, Double>> freqAmp) {
        generator = new SimplexOctaveGenerator(seed, octaves);
        generator.setScale(0.005);
        this.freqAmp = freqAmp;
    }

    @Override
    public double getHeight(int x, int z, int chunkX, int chunkZ) {
        double noise = 0;
        for (Pair<Double, Double> p : this.freqAmp) {
            double frequency = p.getT();
            double amplitude = p.getE();

            noise += generator.noise(chunkX* CustomChunkGenerator.CHUNK_SIZE +x, chunkZ*CustomChunkGenerator.CHUNK_SIZE+z, frequency, amplitude) * 15 + 80;
        }
        return Math.abs(noise * 0.4);
    }

    @Override
    public double getHeight(int x, int z) {
        int i = x % CustomChunkGenerator.CHUNK_SIZE;
        int j = z % CustomChunkGenerator.CHUNK_SIZE;
        return getHeight(i, j, (x - i) / CustomChunkGenerator.CHUNK_SIZE, (z - j) / CustomChunkGenerator.CHUNK_SIZE);
    }
}
