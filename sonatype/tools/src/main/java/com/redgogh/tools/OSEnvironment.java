package com.redgogh.tools;

/* -------------------------------------------------------------------------------- *\
|*                                                                                  *|
|*    Copyright (C) 2023 RedGogh                                                    *|
|*                                                                                  *|
|*    This program is free software: you can redistribute it and/or modify          *|
|*    it under the terms of the GNU General Public License as published by          *|
|*    the Free Software Foundation, either version 3 of the License, or             *|
|*    (at your option) any later version.                                           *|
|*                                                                                  *|
|*    This program is distributed in the hope that it will be useful,               *|
|*    but WITHOUT ANY WARRANTY; without even the implied warranty of                *|
|*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                 *|
|*    GNU General Public License for more details.                                  *|
|*                                                                                  *|
|*    You should have received a copy of the GNU General Public License             *|
|*    along with this program.  If not, see <https://www.gnu.org/licenses/>.        *|
|*                                                                                  *|
|*    This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.    *|
|*    This is free software, and you are welcome to redistribute it                 *|
|*    under certain conditions; type `show c' for details.                          *|
|*                                                                                  *|
\* -------------------------------------------------------------------------------- */

import java.util.Map;

import static com.redgogh.tools.StringUtils.*;

/**
 * `OSEnvironment` 是一个类，用于管理和操作操作系统环境变量。
 *
 * <p>该类提供了访问和检索操作系统环境变量的功能。主要功能包括获取指定名称的环境
 * 变量值等。可以通过静态方法访问环境变量，无需实例化该类。
 *
 * <p>该类的主要特点包括：
 * <ul>
 *     <li>提供简单的接口来获取操作系统环境变量。</li>
 *     <li>封装了环境变量的访问逻辑，易于管理和扩展。</li>
 * </ul>
 *
 * <h2>使用示例</h2>
 * <pre>
 *     // 获取指定名称的环境变量值
 *     String value = OSEnvironment.getEnvironment("PATH");
 * </pre>
 *
 * @author RedGogh
 * @see System
 * @since 1.0
 */
public class OSEnvironment {

    /**
     * 未知操作系统
     */
    public static final byte UNKNOWN = 0x0000;
    /**
     * Windows 操作系统
     */
    public static final byte WINDOWS = 0x0001;
    /**
     * Linux 操作系统
     */
    public static final byte LINUX   = 0x0002;
    /**
     * MacOS 操作系统
     */
    public static final byte MACOS   = 0x0004;

    /**
     * 该常量用于获取并保存当前运行环境的操作系统名称。
     * 通过调用 {@code System.getProperty("os.name")} 来初始化，
     * 返回的操作系统名称可能是 "Windows", "Linux", "Mac OS X" 等。
     *
     * 注意事项：
     * - 该值在 JVM 启动时被初始化，不会随操作系统的变化而更新。
     * - 使用此常量时，确保不会直接依赖于操作系统名称进行特定逻辑操作，
     *   应该使用操作系统类型判断。
     */
    private static final String OS_NAME = System.getProperty("os.name");

    /**
     * 操作系统环境变量
     */
    private static final Map<String, String> environments = System.getenv();

    /**
     * 操作系统枚举类
     */
    private static byte OS_FLAG = UNKNOWN;

    static {
        // initialize
        if (strihas(OS_NAME, "Windows"))
            OS_FLAG = WINDOWS;
        else if (strihas(OS_NAME, "Linux"))
            OS_FLAG = LINUX;
        else if (strihas(OS_NAME, "Mac"))
            OS_FLAG = MACOS;
    }

    /**
     * #brief: 检查当前操作系统是否为 Windows
     *
     * 该函数用于判断当前运行环境是否为 Windows 操作系统。
     *
     * @return 如果操作系统是 Windows，返回 {@code true}，否则返回 {@code false}
     */
    public static boolean isWindows() {
        return OS_FLAG == WINDOWS;
    }

    /**
     * #brief: 检查当前操作系统是否为 Linux
     *
     * 该函数用于判断当前运行环境是否为 Linux 操作系统。
     *
     * @return 如果操作系统是 Linux，返回 {@code true}，否则返回 {@code false}
     */
    public static boolean isLinux() {
        return OS_FLAG == LINUX;
    }

    /**
     * #brief: 检查当前操作系统是否为 MacOS
     *
     * 该函数用于判断当前运行环境是否为 MacOS 操作系统。
     *
     * @return 如果操作系统是 MacOS，返回 {@code true}，否则返回 {@code false}
     */
    public static boolean isMacOS() {
        return OS_FLAG == MACOS;
    }

    /**
     * #brief: 获取当前用户的主目录路径
     *
     * 该函数返回当前用户的主目录路径，路径中的所有反斜杠（\）会被替换为正斜杠（/）。
     *
     * @return 返回当前用户的主目录路径
     */
    public static String getUserHome() {
        return getUserHome(null);
    }

    /**
     * #brief: 获取当前用户的主目录路径，并可选择性地附加一个子路径
     *
     * 该函数返回当前用户的主目录路径，路径中的所有反斜杠（\）会被替换为正斜杠（/）。
     * 可以通过传入 {@code concat} 参数，在主目录路径后附加一个子路径。
     *
     * @param sub
     *        要附加在用户主目录路径后的子路径，如果为 {@code null}，则不附加任何内容
     *
     * @return 返回当前用户的主目录路径，如果 {@code concat} 不为空，则在路径后附加该子路径
     */
    public static String getUserHome(String sub) {
        String home = strrep(System.getProperty("user.home"), "\\\\", "/");
        if (sub != null)
            return home + "/" + sub;
        return home;
    }

    /**
     * #brief: 根据名称获取对应的环境变量值
     *
     * <p>从环境变量集合中查找指定名称的环境变量，并返回其对应的值。
     * 如果环境变量名称不存在，返回 `null`。
     *
     * @param name 环境变量的名称
     * @return 对应名称的环境变量值，如果名称未找到，则返回 `null`
     */
    public static String getEnvironment(String name) {
        return environments.get(name);
    }

}
