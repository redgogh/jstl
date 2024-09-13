package com.redgogh.tools;

import static com.redgogh.tools.StringUtils.*;

/**
 * 操作系统工具类
 *
 * <p>该类提供了一些静态方法，用于检查当前操作系统的类型和获取用户主目录路径。
 *
 * @author RedGogh
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
     * #brief: 当前操作系统的名称
     *
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
     * 操作系统枚举类
     */
    private static byte OS_FLAG = UNKNOWN;

    static {
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

}
