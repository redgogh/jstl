package com.redgogh.tools.generators;

/**
 * `ChanceMaker` 是一个用于生成随机概率的工具类，提供了基于概率的幸运检测功能。
 *
 * <p>该类包含静态方法，用于确定某个值是否满足一定的概率条件。这些方法适用于需要
 * 随机化决策或概率判断的场景，例如游戏中的概率事件、模拟实验等。
 *
 * @author RedGogh
 * @since 1.0
 */
public class ChanceMaker {

    /**
     * #brief: 判断是否幸运
     *
     * <p>该方法通过生成一个随机浮点数，并将其与指定的值进行比较，来确定是否满足幸运条件。
     * 如果随机数加上指定的值大于等于 100，则返回 `true`，表示满足幸运条件。否则，返回 `false`。
     *
     * @param value 用于判断幸运的浮点数值
     * @return 如果随机数加上 `value` 大于等于 100，则返回 `true`；否则返回 `false`
     */
    public static boolean isLuck(float value) {
        return (RandomGenerator.nextFloat(0.0f, 100.0f) + value) >= 100.0f;
    }

}
