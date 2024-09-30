package com.redgogh.examples;

/* -------------------------------------------------------------------------------- *\
|*                                                                                  *|
|*    Copyright (C) 2019-2024 RedGogh All rights reserved.                          *|
|*                                                                                  *|
|*    Licensed under the Apache License, Version 2.0 (the "License");               *|
|*    you may not use this file except in compliance with the License.              *|
|*    You may obtain a copy of the License at                                       *|
|*                                                                                  *|
|*        http://www.apache.org/licenses/LICENSE-2.0                                *|
|*                                                                                  *|
|*    Unless required by applicable law or agreed to in writing, software           *|
|*    distributed under the License is distributed on an "AS IS" BASIS,             *|
|*    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.      *|
|*    See the License for the specific language governing permissions and           *|
|*    limitations under the License.                                                *|
|*                                                                                  *|
\* -------------------------------------------------------------------------------- */

import com.redgogh.tools.io.File;
import com.redgogh.tools.poi.Row;
import com.redgogh.tools.poi.Workbook;
import org.junit.Test;

@SuppressWarnings("ALL")
public class WorkbookExample {

    @Test
    public void generateWorkbookExample() {
            Workbook wb = new Workbook();

            wb.checkout("一班学生");
            wb.addRow("序号", "姓名", "年龄", "性别", "班级", "学号", "身份证号码", "联系方式");
            wb.addRow(1, "张三", 20, "男", "一班", "2023001", "123456789012345678", "13800000001");
            wb.addRow(2, "李四", 21, "女", "一班", "2023002", "123456789012345679", "13800000002");
            wb.addRow(3, "王五", 19, "男", "一班", "2023003", "123456789012345680", "13800000003");
            wb.addRow(4, "赵六", 22, "女", "一班", "2023004", "123456789012345681", "13800000004");

            wb.checkout("六班学生");
            wb.addRow("序号", "姓名", "年龄", "性别", "班级", "学号", "身份证号码", "联系方式");
            wb.addRow(1, "吴十三", 19, "男", "六班", "2023011", "123456789012345688", "13800000011");
            wb.addRow(2, "郑十四", 22, "女", "六班", "2023012", "123456789012345689", "13800000012");

            wb.checkout("七班学生");
            wb.addRow("序号", "姓名", "年龄", "性别", "班级", "学号", "身份证号码", "联系方式");
            wb.addRow(1, "冯十五", 20, "男", "七班", "2023013", "123456789012345690", "13800000013");
            wb.addRow(2, "陈十六", 21, "女", "七班", "2023014", "123456789012345691", "13800000014");

            wb.write(new File("Desktop://b.xlsx").openByteWriter());
    }

    @Test
    public void loadWorkbookExample() {
        Workbook wb = Workbook.load("Desktop://test.xlsx");
        for (int i = 0; i < wb.rowCount(); i++)
            System.out.println(wb.getRow(i));
    }

    @Test
    public void workbookIteratorExample() {
        Workbook wb = Workbook.load("Desktop://test.xlsx");
        // for (Row row : wb) {
        //     System.out.println(row);
        // }
        wb.forEach(System.out::println);
    }

}
