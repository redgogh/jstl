# 3.0.1

---------------------

## ⭐ 新增新能

- 新增`StreamOperator`工具类，方便简化流式操作。
- 新增`Assert#isEmpty`支持 Map 类型判断。
- 新增`Assert#notEquals`支持自定义异常信息。
- 新增`Tuple`元组对象。
- 新增`Itertools`迭代器操作对象。

## 👻 优化功能

- `HttpClient#newStreamCall`简化字节流请求写法。
- `StringUtils#strclude`更新为`StringUtils#strcont`。
- `File`更新为`MutableFile`避免与JDK原生File起命名冲突。
- 移除`Lists#filter`函数，将该函数移动到`Stream#filter`中。

## 🐞 BUG 修复
