# 3.0.3

---------------------

## ⭐ 新增新能

- 新增 `Chrono#weekOfYear` 函数获取今天是今年内的第几周。
- 新增 `VirtualMachineFile` 对象用于代替 `MutableFile`。
- 新增 `VirtualMachineFile#openRandomAccess` 随机读写访问函数。
- 新增 `IOUtils#copy` 拷贝文件或文件夹函数。
- 新增 `IOUtils#move` 移动文件或文件夹函数。
- 新增 `IOUtils#serialize` 序列化对象函数。
- 新增 `IOUtils#deserialize` 反序列化对象函数。
- 新增 `Chrono#toZoned` 函数用于转换时区。

## 👻 优化功能

- 优化 `VirtualMachineFile` 新增 `Home://` 目录。
- 优化 `Chrono#getToday` 函数命名更新 `Chrono#today`。
- 优化 `Chrono#getWeekOfMonth` 函数命名更新 `Chrono#weekOfMonth`。
- 移除 `Chrono#moments` 函数。
- 优化 `Transformer` 命名更新为 `TypeCvt`。
- 优化 `any*、checkin` 等函数移动到 `Comparators` 类中。
- 优化 `iface` 包下的函数式接口命名。
- 移除 `MutableFile` 对象。
- 移除 `VirtualMachineFile` 对象中所有随机读写访问接口。
- 优化 `Pair` 内存布局。
- 优化 `VirtualMachineFile#from` 函数避免重复创建对象

## 🐞 BUG 修复

## 🔨 构建工具