package com.redgogh.jstl.math;

/**
 * @author Red Gogh
 */
public class Maths {

    public static final float PI = 3.141592653589793F;

    public static int abs(short x) {
        return x <= 0 ? -x : x;
    }

    public static int abs(int x) {
        return x <= 0 ? -x : x;
    }

    public static long abs(long x) {
        return x <= 0L ? -x : x;
    }

    public static float abs(float x) {
        return x <= 0.0f ? -x : x;
    }

    public static double abs(double x) {
        return x <= 0.0D ? -x : x;
    }

    public static float pow(float a, int b) {
        float q = 1.0f;
        boolean n = a < 0;
        while (b > 0) {
            if ((b & 1) == 1)
                q = q * a;
            a = a * a;
            b = b >> 1;
        }
        return n ? 1 / q : q;
    }

    public static int fac(int x) {
        int q = 1;
        if (x >= 3)
            x = x + 1;
        for (int i = 2; i < x; i++)
            q = q * i;
        return q;
    }

    public static float sin(float a) {
        float q = a;
        int limit = a < 1.0f ? 7 : 15;
        for (int i = 3; i <= limit; i += 2) {
            float t = pow(a, i) / fac(i);
            q = (i / 2) % 2 == 0 ? q + t : q - t;
        }
        return q;
    }

    public static float cos(float a) {
        float q = 1.0f;
        int limit = a < 1.0f ? 8 : 16;
        for (int i = 2; i <= limit; i += 2) {
            float t = pow(a, i) / fac(i);
            q = (i / 2) % 2 == 0 ? q + t : q - t;
        }
        return q;
    }

    public static float degrees(float angle) {
        return angle * (180.0f / PI);
    }

    public static float radians(float angle) {
        return angle * (PI / 180.0f);
    }

    public static float err(float a, float b) {
        return abs(Math.round((abs(a - b) / a * 100.0f) * 100.0f) / 100.0f);
    }

    public static void main(String[] args) {
        float a = radians(185);
        float v1 = (float) Math.cos(a);
        float v2 = cos(a);
        System.out.printf("v1 = %s\nv2 = %s\nerr = %s\n", v1, v2, err(v2, v1));
    }

}
