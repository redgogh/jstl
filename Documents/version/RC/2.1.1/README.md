# 2.1.0

---------------------

## ⭐ 新增新能

- 新增`Capturer#icall`函数用于忽略异常调用。
- 新增`Assert#isTrue`函数用于校验为`true`的值。
- 提供`Assert#notEquals`检测两个对象是否不相等。
- 重写`Row#toString`用于快速测试时直接打印每行的数据内容。
- 重写`Workbook#toString`用于快速测试时直接打印工作簿数据内容。

## 👻 优化功能

- 移除断言`Assert#ignore`忽略异常函数。取而代之使用`Capaturer#icall`。

## 🐞 BUG 修复

- 修复空`Workbook`创建时`Sheet`索引超出问题。

## 🔨 依赖变更