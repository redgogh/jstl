package com.redgogh.tools.generators;

import java.util.Random;

/**
 * `RandomGenerator` 是一个用于生成随机数的工具类，提供了多种生成随机整数、浮点数和双精度数的方法。
 *
 * <p>该类包含静态方法，用于生成指定范围内的随机数。这些方法适用于需要生成随机数的场景，如
 * 随机化算法、模拟测试、数据生成等。
 *
 * <h2>主要功能</h2>
 * <ul>
 *     <li>生成指定范围内的随机整数。</li>
 *     <li>生成指定范围内的随机浮点数。</li>
 *     <li>生成指定范围内的随机双精度数。</li>
 * </ul>
 *
 * @author RedGogh
 * @since 1.0
 */
public class RandomGenerator {

    /**
     * #brief: 生成指定最大值的随机整数
     *
     * <p>该方法生成一个从 0 到指定最大值（包括最大值）的随机整数。
     *
     * @param max 随机整数的最大值（包括）
     * @return 生成的随机整数
     */
    public static int nextInt(int max) {
        return nextInt(0, max);
    }

    /**
     * #brief: 生成指定范围的随机整数
     *
     * <p>该方法生成一个从指定最小值到最大值（包括最大值）的随机整数。
     *
     * @param min 随机整数的最小值（包括）
     * @param max 随机整数的最大值（包括）
     * @return 生成的随机整数
     */
    public static int nextInt(int min, int max) {
        return (int) nextDouble(min, max);
    }

    /**
     * #brief: 生成指定最大值的随机浮点数
     *
     * <p>该方法生成一个从 0 到指定最大值（包括最大值）的随机浮点数。
     *
     * @param max 随机浮点数的最大值（包括）
     * @return 生成的随机浮点数
     */
    public static float nextFloat(float max) {
        return nextFloat(0, max);
    }

    /**
     * #brief: 生成指定范围的随机浮点数
     *
     * <p>该方法生成一个从指定最小值到最大值（包括最大值）的随机浮点数。
     *
     * @param min 随机浮点数的最小值（包括）
     * @param max 随机浮点数的最大值（包括）
     * @return 生成的随机浮点数
     */
    public static float nextFloat(float min, float max) {
        return (float) nextDouble(min, max);
    }

    /**
     * #brief: 生成指定最大值的随机双精度数
     *
     * <p>该方法生成一个从 0 到指定最大值（包括最大值）的随机双精度数。
     *
     * @param max 随机双精度数的最大值（包括）
     * @return 生成的随机双精度数
     */
    public static double nextDouble(double max) {
        return nextDouble(0, max);
    }

    /**
     * #brief: 生成指定范围的随机双精度数
     *
     * <p>该方法生成一个从指定最小值到最大值（包括最大值）的随机双精度数。
     *
     * @param min 随机双精度数的最小值（包括）
     * @param max 随机双精度数的最大值（包括）
     * @return 生成的随机双精度数
     */
    public static double nextDouble(double min, double max) {
        return (Math.random() * ((max - min) + 1.0f)) + min;
    }

    /**
     * #brief: 生成随机布尔值
     *
     * <p>该方法生成一个随机的布尔值，`true` 或 `false`，用于表示随机事件的结果。
     * 该方法利用 Java 标准库中的 `Random` 类生成布尔值。
     *
     * @return 随机生成的布尔值
     */
    public static boolean nextBoolean() {
        return new Random().nextBoolean();
    }

}

