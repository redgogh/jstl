package com.redgogh.libext

import kotlin.annotation.AnnotationRetention.SOURCE
import kotlin.annotation.AnnotationTarget.*

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

/**
 * 标记功能支持从 JDK X 开始提供，低于 [version] 版本后就不可
 * 使用。
 *
 * @param version 表示功能从哪个版本开始提供
 * @author RedGogh
 * @since 1.0
 */
@Target(CLASS, FIELD, TYPE, FUNCTION)
@Retention(SOURCE)
@MustBeDocumented
annotation class Since(val version: String)

/**
 * 标记函数或类可以升级到最新的 JDK 提供的功能，如果以后 Jdk 升级后可以
 * 使用新的语法或功能代替。
 *
 * @param features 新版本功能描述
 * @author RedGogh
 * @since 1.0
 */
@Target(CLASS, FIELD, TYPE, FUNCTION)
@Retention(SOURCE)
@MustBeDocumented
annotation class Upgrade(val features: String)