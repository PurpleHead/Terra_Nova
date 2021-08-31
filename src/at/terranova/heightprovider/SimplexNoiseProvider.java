package at.terranova.heightprovider;

import org.bukkit.util.noise.SimplexOctaveGenerator;

public class SimplexNoiseProvider implements NoiseProvider {

    private SimplexOctaveGenerator generator;

    public SimplexNoiseProvider (long seed, int octaves) {
        generator = new SimplexOctaveGenerator(seed, octaves);
        generator.setScale(0.005);
    }

    @Override
    public double getHeight(int x, int z, int chunkX, int chunkZ, int chunkSize, double frequency, double amplitude) {
        return generator.noise(chunkX*chunkSize+x, chunkZ*chunkSize+z, 0.9, 1.2) * 20 + 80;
    }
}
