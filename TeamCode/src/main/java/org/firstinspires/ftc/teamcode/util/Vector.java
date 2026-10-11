package org.firstinspires.ftc.teamcode.util;

public class Vector {
   public double x , y , z;
    public Vector(double x, double y , double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getZ() {
        return z;
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }
}
