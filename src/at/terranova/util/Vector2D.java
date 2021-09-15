/*
 * author: [USER_NOT_FOUND]
 * https://github.com/PurpleHead
 */
package at.terranova.util;

public class Vector2D {

    private double x;
    private double z;

    public Vector2D (double x, double z) {
        this.x = x;
        this.z = z;
    }

    public static Vector2D ofAngle(double radians, int length) {
        Vector2D vector2D = new Vector2D(Math.cos(radians), Math.sin(radians));
        vector2D.multiply(length);
        return vector2D;
    }

    public static Vector2D ofDirection (Direction d, int length) {
        switch (d) {
            case NORTH:
                return new Vector2D(0, -length);
            case EAST:
                return new Vector2D(length, 0);
            case SOUTH:
                return new Vector2D(0, length);
            case WEST:
                return new Vector2D(-length, 0);
            default:
                return null;
        }
    }

    public void add(Vector2D other) {
        this.x += other.x;
        this.z += other.z;
    }

    public void multiply (double m) {
        this.x *= m;
        this.z *= m;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", z=" + z +
                '}';
    }
}

enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST
}
