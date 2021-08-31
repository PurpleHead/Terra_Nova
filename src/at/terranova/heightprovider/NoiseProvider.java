package at.terranova.heightprovider;

public interface NoiseProvider {

    public double getHeight(int x, int z, int chunkX, int chunkZ, int chunkSize, double frequency, double amplitude);

}
