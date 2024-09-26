package com.redgogh.tools.poi;

import com.redgogh.tools.collection.Lists;
import com.redgogh.tools.io.File;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.OutputStream;
import java.util.List;

import static com.redgogh.tools.Assert.throwIfError;
import static com.redgogh.tools.BasicConverter.atos;

public class Workbook {

    private final org.apache.poi.ss.usermodel.Workbook wb = new XSSFWorkbook();

    private Sheet sheet;

    public Workbook() {
        sheet = wb.createSheet("Sheet1");
    }

    public void addRow(Object... values) {
        int rowNum = sheet.getLastRowNum();
        Row row = sheet.createRow(rowNum + 1);
        for (int i = 0; i < values.length; i++) {
            row.createCell(i).setCellValue(atos(values[i]));
        }
    }

    public List<String> readRow(int index) {
        Row row = sheet.getRow(index);
        List<String> cellValues = Lists.of();

        short lastCellNum = row.getLastCellNum();
        for (short i = 0; i < lastCellNum; i++)
            cellValues.add(row.getCell(i).getStringCellValue());

        return cellValues;
    }

    public void write(OutputStream stream) {
        throwIfError(() -> wb.write(stream));
    }

    public static void main(String[] args) {
        Workbook workbook = new Workbook();

        workbook.addRow("序号", "姓名", "年龄", "性别", "班级", "学号", "身份证号码", "联系方式");
        workbook.addRow(1, "张三", 20, "男", "一班", "2023001", "123456789012345678", "13800000001");
        workbook.addRow(2, "李四", 21, "女", "一班", "2023002", "123456789012345679", "13800000002");
        workbook.addRow(3, "王五", 19, "男", "二班", "2023003", "123456789012345680", "13800000003");
        workbook.addRow(4, "赵六", 22, "女", "二班", "2023004", "123456789012345681", "13800000004");
        workbook.addRow(5, "刘七", 20, "男", "三班", "2023005", "123456789012345682", "13800000005");
        workbook.addRow(6, "陈八", 21, "女", "三班", "2023006", "123456789012345683", "13800000006");
        workbook.addRow(7, "杨九", 19, "男", "四班", "2023007", "123456789012345684", "13800000007");
        workbook.addRow(8, "黄十", 22, "女", "四班", "2023008", "123456789012345685", "13800000008");
        workbook.addRow(9, "孙十一", 20, "男", "五班", "2023009", "123456789012345686", "13800000009");
        workbook.addRow(10, "周十二", 21, "女", "五班", "2023010", "123456789012345687", "13800000010");
        workbook.addRow(11, "吴十三", 19, "男", "六班", "2023011", "123456789012345688", "13800000011");
        workbook.addRow(12, "郑十四", 22, "女", "六班", "2023012", "123456789012345689", "13800000012");
        workbook.addRow(13, "冯十五", 20, "男", "七班", "2023013", "123456789012345690", "13800000013");
        workbook.addRow(14, "陈十六", 21, "女", "七班", "2023014", "123456789012345691", "13800000014");
        workbook.addRow(15, "于十七", 19, "男", "八班", "2023015", "123456789012345692", "13800000015");
        workbook.addRow(16, "许十八", 22, "女", "八班", "2023016", "123456789012345693", "13800000016");
        workbook.addRow(17, "毛十九", 20, "男", "九班", "2023017", "123456789012345694", "13800000017");
        workbook.addRow(18, "赵二十", 21, "女", "九班", "2023018", "123456789012345695", "13800000018");
        workbook.addRow(19, "蔡二十一", 19, "男", "十班", "2023019", "123456789012345696", "13800000019");
        workbook.addRow(20, "沈二十二", 22, "女", "十班", "2023020", "123456789012345697", "13800000020");

        workbook.write(new File("Desktop://b.xlsx").openByteWriter());
    }

}
