# v3.0.1-beta.2+build

---------------------

## ⭐ 新增新能

- `Streams`新增`anyMatch`匹配函数。
- `Streams`新增`noneMatch`匹配函数。
- `StringUtils`新增`strcap`首字母大写函数。
- `UClass`新增`filterFields`方法用于过滤指定成员对象。
- `Optional`新增`ifBlank`方法用于检查空字符串。

## 👻 优化功能

- `anyclude`更新为`anycount`。
- `BeanUtils#copyProperties`支持`set/get`方法拷贝。
- `Optional#ifNull`命名更新为`Optional#ifNullable`。

## 🐞 BUG 修复

- 修复 `DateFormatter#parse` 方法参数传递不正确问题。