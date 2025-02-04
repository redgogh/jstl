# v3.0.1-beta.2+build

---------------------

## ⭐ 新增新能

- 新增 `Streams#anyMatch`匹配函数。
- 新增 `Streams#noneMatch`匹配函数。
- 新增 `StringUtils#strcap`首字母大写函数。
- 新增 `UClass#filterFields`方法用于过滤指定成员对象。
- 新增 `Optional#ifBlank`方法用于检查空字符串。
- 新增 `ByteBuffer#readByte` 方法用于读取单个字节。

## 👻 优化功能

- `anyclude -> anycount`。
- `BeanUtils#copyProperties` 方法支持使用 `set/get` 拷贝。
- `Optional#ifNull -> Optional#ifNullable`。
- 明确 `ByteBuffer#write` 函数写入类型。
- `Crypto#uuid` 方法默认返回大写的 UUID。

## 🐞 BUG 修复

- 修复 `DateFormatter#parse` 方法参数传递不正确问题。