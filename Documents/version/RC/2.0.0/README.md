# 2.0.0

---------------------

## ⭐ 新增新能

- 新增 `UClass#ireadFieldValue` 方法，忽略异常读取。
- 移除所有 Kotlin 代码，全部切换到 Java 增强兼容性。
- 新增 `Response#valueEquals` 方法，比较响应值对象某个属性是否相等。
- 新增 `HttpClient#newOctetStreamCall` 文件下载功能，支持异步下载。

## 👻 优化功能

- 优化 `UClass#newInstance` 方法，增加对 `private` 构造器的支持。
- 优化 `AnyObjects` 命名，新命名为 `BasicConverter`，更准确地反映其基本转换功能。
- 优化 `StreamMapping` 命名，新命名为 `StreamMapper`，提升命名清晰度和可读性。

## 🐞 BUG 修复

- 修复 `OSEnvironment#OS_FLAG` 检测不正确问题。

## 🔨 依赖升级

- `com.squareup.okhttp3:okhttp` 依赖从 `4.10.0` 版本升级到 `4.12.0` 版本。