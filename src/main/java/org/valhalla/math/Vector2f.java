package org.valhalla.math;

/**
 * 2维向量
 *
 * @author Red Gogh
 */
public class Vector2f {

    public float x;

    public float y;

    public Vector2f() {
        this(0.0f, 0.0f);
    }

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

}
