/*
 * author: [USER_NOT_FOUND]
 * https://github.com/PurpleHead
 */
package at.terranova.heightprovider;

public interface NoiseProvider {

    double getHeight(int x, int z, int chunkX, int chunkZ);

    double getHeight(int x, int z);

}
