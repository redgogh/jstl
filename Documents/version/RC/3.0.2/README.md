# 3.0.2

---------------------

## ⭐ 新增新能

- 新增 `Streams#anyMatch`匹配函数。
- 新增 `Streams#noneMatch`匹配函数。
- 新增 `StringUtils#strcap`首字母大写函数。
- 新增 `UClass#filterFields`方法用于过滤指定成员对象。
- 新增 `Optional#ifBlank`方法用于检查空字符串。
- 新增 `ByteBuffer#readByte` 方法用于读取单个字节。
- 新增 `ByteBuffer#readableBytes` 方法判断缓冲区剩余可读字节数。
- 新增 `ByteBuffer#readShort` 以及 `writeShort` 方法用于写入 short 类型数据。
- 新增 `MutableFile#loadProperties` 方法读取 `properties` 文件。
- 新增 `StreamReader` 用于统一操作输入流对象。
- 完善 `ByteBuffer` API。
- 新增 `BeanUtils#directCopy` 直接属性拷贝方法。
- 新增 `DateFormatter#autoParse` 支持自动根据常用格式解析。
- 新增 `IOUtils#strread` 支持根据文件路径读取数据。
- 新增 `Pair<A, B>` 支持两个数据的元组对象。
- 新增 `Tuple<A, B, C>` 支持三个数据的元组对象。
- 新增 `RSACipher` 加解密工具类。
- 新增 `Codec` 编码解码工具类。
- 新增 `Lists#partitionBySize` 支持集合按块拆分。

## 👻 优化功能

- `anyclude -> anycount`。
- `BeanUtils#copyProperties` 方法支持使用 `set/get` 拷贝。
- `Optional#ifNull -> Optional#ifNullable`。
- 明确 `ByteBuffer#write` 函数写入类型。
- `Crypto#uuid` 方法默认返回大写的 UUID。
- 增强 `HttpClient` API 提供通用配置项配置。
- 优化 `Lists` 工具类方法命名。

## 🐞 BUG 修复

- 修复 `DateFormatter#parse` 方法参数传递不正确问题。
- 修复 `BeanUtils`拷贝空对象以及基本数据类型不能拷贝问题。