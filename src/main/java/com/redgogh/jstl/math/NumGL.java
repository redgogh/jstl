package com.redgogh.jstl.math;

/* -------------------------------------------------------------------------------- *\
|*                                                                                  *|
|*    Copyright (C) 2019-2024 RedGogh All rights reserved.                          *|
|*                                                                                  *|
|*    Licensed under the Apache License, Version 2.0 (the "License");               *|
|*    you may not use this file except in compliance with the License.              *|
|*    You may obtain a copy of the License at                                       *|
|*                                                                                  *|
|*        http://www.apache.org/licenses/LICENSE-2.0                                *|
|*                                                                                  *|
|*    Unless required by applicable law or agreed to in writing, software           *|
|*    distributed under the License is distributed on an "AS IS" BASIS,             *|
|*    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.      *|
|*    See the License for the specific language governing permissions and           *|
|*    limitations under the License.                                                *|
|*                                                                                  *|
\* -------------------------------------------------------------------------------- */

/**
 * 图形相关的数学库
 *
 * @author Red Gogh
 */
public class NumGL {

    public static int round(float x) {
        return (int) (x + 0.5f);
    }

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
        a = (float) (a % (2.0f * Math.PI));

        float q = a;
        float t = a;
        int sign = 1;
        int limit = a < 1.0f ? 15 : 31;

        for (int i = 3; i <= limit; i += 2) {
            t *= (a * a) / (i * (i - 1));
            q += ((sign *= -1) * t);
        }

        return q;
    }

    public static float cos(float a) {
        a = (float) (a % (2.0f * Math.PI));

        float q = 1.0f;
        float t = 1.0f;
        int sign = 1;
        int limit = a < 1.0f ? 16 : 32;

        for (int i = 2; i <= limit; i += 2) {
            t *= (a * a) / (i * (i - 1));
            q += (sign *= -1) * t;
        }

        return q;
    }

    public static int degrees(float angrad) {
        return round(angrad * (180.0f / (float) Math.PI));
    }

    public static float radians(float angdeg) {
        return angdeg * ((float) Math.PI / 180.0f);
    }

    public static float err(float a, float b) {
        return abs(round((abs(a - b) / a * 100.0f) * 100.0f) / 100.0f);
    }

    public static float sqrt(float a) {
        if (a == 0)
            return a;

        int maxIter = 1000;

        float x = a;
        for (int i = 0; i < maxIter; i++) {
            float n = 0.5f * (x + a / x);

            if (abs(n - x) < (float) 1e-10)
                return n;

            x = n;
        }

        return x;
    }

    public static void main(String[] args) {
        float a = radians(261);
        float v1 = 0.0f;
        float v2 = 0.0f;

        float sqrt_v = 32;

        System.out.printf("sqrt(%s) = %s\n", sqrt_v, sqrt(sqrt_v));
        System.out.println("radians = " + a);
        System.out.println("degrees = " + degrees(a));

        v1 = (float) Math.sin(a);
        v2 = NumGL.sin(a);
        System.out.printf("sin:\n    v1 = %s\n    v2 = %s\n    err = %s\n", v1, v2, err(v2, v1));

        v1 = (float) Math.cos(a);
        v2 = NumGL.cos(a);
        System.out.printf("cos:\n    v1 = %s\n    v2 = %s\n    err = %s\n", v1, v2, err(v2, v1));
    }

}
