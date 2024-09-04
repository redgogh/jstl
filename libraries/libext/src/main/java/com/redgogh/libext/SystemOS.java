package com.redgogh.libext;

import static com.redgogh.libext.StringUtils.strihas;
import static com.redgogh.libext.StringUtils.strrepall;

public class SystemOS {

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
     * #brief: 检查当前操作系统是否为 Windows
     *
     * 该函数用于判断当前运行环境是否为 Windows 操作系统。
     *
     * @return 如果操作系统是 Windows，返回 {@code true}，否则返回 {@code false}
     */
    public static boolean isWindows() {
        return strihas(OS_NAME, "Windows");
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
        String home = strrepall(System.getProperty("user.home"), "\\\\", "/");
        if (sub != null)
            return home + "/" + sub;
        return home;
    }

}
