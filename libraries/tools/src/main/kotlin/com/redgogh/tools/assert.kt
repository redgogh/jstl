package com.redgogh.tools

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

import com.redgogh.tools.StringUtils.strnemp
import com.redgogh.tools.StringUtils.strwfmt
import com.redgogh.tools.exception.AssertException
import com.redgogh.tools.logging.LoggerFactory
import java.util.*
import kotlin.jvm.Throws

fun interface VoidFunction {
    @Throws(Throwable::class)
    fun apply()
}

fun interface TypeFunction<T> {
    @Throws(Throwable::class)
    fun apply(): T
}

/**
 * 无返回值，无参默认匿名函数声明接口。适用于Lambda函数或
 * 闭包函数。
 *
 * @see Assert.throwIfError
 */
fun interface NormalFunction {
    @Throws(Throwable::class)
    fun apply()
}

/**
 * 执行泛型类型作为返回值，无参默认匿名函数声明接口。适用于Lambda函数或
 * 闭包函数。
 *
 * @see Assert.throwIfError
 */
fun interface GenericFunction<T> {
    @Throws(Throwable::class)
    fun apply(): T
}

/**
 * 运行时断言工具类，这个类断言失败后并不会直接停止整个程序，而是会将所有失败的断言
 * 信息作为 Runtime 异常抛出。
 */
object Assert {

    /**
     * 运行时断言：如果结果是 false，就抛出异常。
     */
    @JvmStatic
    fun xassert(value: Boolean) = xassert(value, "xassert failed!")

    /**
     * 运行时断言：如果结果是 false，就抛出异常。
     */
    @JvmStatic
    fun xassert(value: Boolean, vfmt: String, vararg args: Any) {
        if (!value)
            throw AssertException(vfmt, args)
    }

    /**
     * 判断一个对象是否为空，如果对象为空则抛出运行时异常。如果对象不为空则
     * 返回该对象本身。
     *
     * @param obj 检查该对象引用是否为空。
     * @param T 引用对象泛型类型
     * @return 如果 [obj] 引用不为空，则返回该对象。
     * @throws [NullPointerException] 如果 [obj] 对象引用为空。
     *
     */
    @JvmStatic
    fun <T> throwIfNull(obj: T?): T =
            throwIfNull(obj, "The object cannot be null.")

    /**
     * 判断一个对象是否为空，如果对象为空则抛出运行时异常。如果对象不为空则
     * 返回该对象本身。
     *
     * @param obj   检查该对象引用是否为空。
     * @param vfmt  格式化字符串模板
     * @param args  字符串模板参数替换列表
     * @param T 引用对象泛型类型
     * @return 如果 [obj] 引用不为空，则返回该对象。
     * @throws [NullPointerException] 如果 [obj] 对象引用为空。
     *
     */
    @JvmStatic
    fun <T> throwIfNull(obj: T?, vfmt: String, vararg args: Any): T =
            Objects.requireNonNull<T>(obj, strwfmt(vfmt, args))

    /**
     * 判断一个集合容器类的对象是否为空对象或 `null`，如果满足其中任何一个
     * 则抛出运行时断言异常。
     *
     * @param collection 集合容器对象
     * @throws [AssertException]
     */
    @JvmStatic
    fun throwIfEmpty(collection: Collection<*>?) = throwIfEmpty(collection, "collection object cannot be null or empty!")

    /**
     * 判断一个集合容器类的对象是否为空对象或 `null`，如果满足其中任何一个
     * 则抛出运行时断言异常。
     *
     * @param collection 集合容器对象
     * @param vfmt       格式化字符串模板
     * @param args       字符串模板参数替换列表
     * @throws [AssertException]
     */
    @JvmStatic
    fun throwIfEmpty(collection: Collection<*>?, vfmt: String, vararg args: Any) =
            xassert(collection != null && !collection.isEmpty(), strwfmt(vfmt, args))

    /**
     * 闭包函数接口，安静地捕获一个异常并忽略。
     *
     * @param function 无返回值闭包函数接口
     *
     */
    @JvmStatic
    fun ignore(function: VoidFunction) {
        try {
            function.apply()
        } catch (e: Throwable) {
            /* ignore... */
        }
    }

    /**
     * 闭包函数接口，安静地捕获一个异常并抛出。避免 try/catch 代码占用太多
     * 地方。从而导致代码不简洁难以维护。
     *
     * @param function 无返回值闭包函数接口
     * @throws AssertException
     *
     */
    @JvmStatic
    fun throwIfError(function: VoidFunction) = throwIfError(function, null)

    /**
     * 闭包函数接口，安静地捕获一个异常并抛出。避免 try/catch 代码占用太多
     * 地方。从而导致代码不简洁难以维护。
     *
     * @param function 无返回值闭包函数接口
     * @param vfmt     格式化字符串模板
     * @param args     格式化参数
     * @throws AssertException
     *
     */
    @JvmStatic
    fun throwIfError(function: VoidFunction, vfmt: String?, vararg args: Any) {
        try {
            function.apply()
        } catch (e: Throwable) {
            throw AssertException(vfmt?.let { strwfmt(it, *args) } ?: e.message ?: "Unknown Error!")
        }
    }

    /**
     * 闭包函数接口，安静地捕获一个异常并抛出。避免 try/catch 代码占用太多
     * 地方。从而导致代码不简洁难以维护。
     *
     * @param function 有返回值闭包函数接口
     * @return 如果闭包函数执行正常，则返回闭包函数中的返回值。
     * @throws AssertException
     *
     */
    @JvmStatic
    fun <T> throwIfError(function: TypeFunction<T>): T = throwIfError(function, null)

    /**
     * 闭包函数接口，安静地捕获一个异常并抛出。避免 try/catch 代码占用太多
     * 地方。从而导致代码不简洁难以维护。
     *
     * @param function 有返回值闭包函数接口
     * @param vfmt     格式化字符串模板
     * @param args     格式化参数
     * @return 如果闭包函数执行正常，则返回闭包函数中的返回值。
     * @throws AssertException
     *
     */
    @JvmStatic
    fun <T> throwIfError(function: TypeFunction<T>, vfmt: String?, vararg args: Any): T {
        try {
            return function.apply()
        } catch (e: Throwable) {
            throw AssertException(vfmt?.let { strwfmt(it, *args) } ?: e.message ?: "Unknown Error!")
        }
    }

}

/**
 * Optional 对象，通常用于返回值的处理。
 */
object Optional {

    private val log = LoggerFactory.getLogger(Optional::class.java)

    /**
     * #brief：如果[obj]对象为`null`则返回[orElse]
     *
     * 如果[obj]为`null`则返回[orElse]，[obj]是一个任意类型的对象，
     *
     * @param obj
     *        任意一个对象
     *
     * @param orElse
     *        如果d对象引用为`null`，那么则返回`orElse`
     *
     */
    @JvmStatic
    fun <T> optionalIfNull(obj: T, orElse: T): T =
            optionalIfNull(obj, orElse, "")

    /**
     * #brief：如果[obj]对象为`null`则返回[orElse]
     *
     * 如果[obj]为`null`则返回[orElse]，[obj]是一个任意类型的对象，
     *
     * @param obj
     *        任意一个对象
     *
     * @param orElse
     *        如果d对象引用为`null`，那么则返回`orElse`
     *
     * @param message
     *        自定义异常信息
     *
     * @param args
     *        格式化参数
     */
    @JvmStatic
    fun <T> optionalIfNull(obj: T, orElse: T, message: String, vararg args: Any): T {
        if (obj == null) {
            if (strnemp(message))
                log.error("%s - 异常信息：%s", message, *args)
            return orElse
        }
        return obj
    }

    /**
     * #brief：如果[function]执行结果为`null`则返回[orElse]
     *
     * 如果[function]执行结果为`null`则返回[orElse]，[function]是一个闭包函数，这个
     * 函数必须带有返回值。如果[function]执行结果为`null`则会返回[orElse]
     *
     * @param function
     *        闭包函数用于执行方法体，捕获异常
     *
     * @param orElse
     *        如果闭包函数执行结果为`null`，那么则返回`orElse`
     *
     */
    @JvmStatic
    fun <T> optionalIfNull(function: GenericFunction<T>, orElse: T): T =
            optionalIfNull(function, orElse, "")

    /**
     * #brief：如果[function]执行结果为`null`则返回[orElse]
     *
     * 如果[function]执行结果为`null`则返回[orElse]，[function]是一个闭包函数，这个
     * 函数必须带有返回值。如果[function]执行结果为`null`则会返回[orElse]
     *
     * @param function
     *        闭包函数用于执行方法体，捕获异常
     *
     * @param orElse
     *        如果闭包函数执行结果为`null`，那么则返回`orElse`
     *
     * @param message
     *        自定义异常信息
     *
     * @param args
     *        格式化参数
     */
    @JvmStatic
    fun <T> optionalIfNull(function: GenericFunction<T>, orElse: T, message: String, vararg args: Any): T {
        val ret = function.apply()
        if (ret == null) {
            if (strnemp(message))
                log.error("%s - 异常信息：%s", message, *args)
            return orElse
        }
        return ret
    }

    /**
     * #brief：如果闭包函数没有出现异常，返回闭包函数值。否则返回 [orError] 值
     *
     * 封装一个 Optional 函数，如果闭包函数没有出现异常，返回闭包函数值。如果出现异常
     * 返回 [orError] 值。
     *
     * 代码示例：
     *
     *      var ret = optionalIfError(() -> Double.parse("1.0d"), 0.0)
     *      println(ret) // ret = 1.0
     *
     * @param function
     *        闭包函数用于执行方法体，捕获异常
     *
     * @param orError
     *        执行结果出现异常返回
     */
    @JvmStatic
    fun <T> optionalIfError(function: GenericFunction<T>, orError: T): T =
            optionalIfError(function, orError, "")

    /**
     * #brief：如果闭包函数没有出现异常，返回闭包函数值。否则返回 [orError] 值
     *
     * 封装一个 Optional 函数，如果闭包函数没有出现异常，返回闭包函数值。如果出现异常
     * 返回 [orError] 值。
     *
     * 代码示例：
     *
     *      var ret = optionalIfError(() -> Double.parse("1.0d"), 0.0)
     *      println(ret) // ret = 1.0
     *
     * @param function
     *        闭包函数用于执行方法体，捕获异常
     *
     * @param orError
     *        执行结果出现异常返回
     *
     * @param message
     *        自定义异常信息
     *
     * @param args
     *        格式化参数
     */
    @JvmStatic
    fun <T> optionalIfError(function: GenericFunction<T>, orError: T, message: String, vararg args: Any): T {
        return try {
            function.apply()
        } catch (e: Throwable) {
            val out = if (strnemp(message)) strwfmt(message, *args) else e.message
            log.error("%s", out)
            return orError
        }
    }

    /**
     * #brief：optional 选项封装，如果出现异常返回 [orError] 否则返回 [orSuccess]
     *
     * optional 选项封装，如果出现异常返回 [orError] 否则返回 [orSuccess]，用于平判断一个
     * 函数执行是否出错，避免空指针可使用。
     *
     * 代码示例：
     *
     *      var ret = optionalIfError(() -> Double.parse("1.0a"), true, false)
     *      println(ret) // ret = false
     *
     * @param function
     *        闭包函数用于执行方法体，捕获异常
     *
     * @param orSuccess
     *        执行结果未出现异常返回 [true]
     *
     * @param orError
     *        执行结果出现异常返回 [false]
     *
     */
    @JvmStatic
    fun <T> optionalIfError(function: NormalFunction, orSuccess: T, orError: T): T =
            optionalIfError(function, orSuccess, orError, "")

    /**
     * #brief：optional 选项封装，如果出现异常返回 [orError] 否则返回 [orSuccess]
     *
     * optional 选项封装，如果出现异常返回 [orError] 否则返回 [orSuccess]，用于平判断一个
     * 函数执行是否出错，避免空指针可使用。
     *
     * 代码示例：
     *
     *      var ret = optionalIfError(() -> Double.parse("1.0a"), true, false)
     *      println(ret) // ret = false
     *
     * @param function
     *        闭包函数用于执行方法体，捕获异常
     *
     * @param message
     *        自定义异常信息
     *
     * @param args
     *        格式化参数
     *
     */
    @JvmStatic
    @Suppress("UNREACHABLE_CODE")
    fun <T> optionalIfError(function: NormalFunction, orSuccess: T, orError: T, message: String, vararg args: Any): T {
        return try {
            function.apply()
            return orSuccess
        } catch (e: Throwable) {
            if (strnemp(message)) {
                val tmp = strwfmt(message, *args)
                log.error("%s - 异常信息：%s", tmp, e.message)
            }
            return orError
        }
    }

}