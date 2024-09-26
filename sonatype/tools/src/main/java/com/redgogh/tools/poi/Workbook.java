package com.redgogh.tools.poi;

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

import com.redgogh.tools.BasicConverter;
import com.redgogh.tools.Optional;
import com.redgogh.tools.collection.Lists;
import com.redgogh.tools.io.File;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.OutputStream;

import static com.redgogh.tools.Assert.throwIfError;
import static com.redgogh.tools.BasicConverter.atos;
import static com.redgogh.tools.StringUtils.strnempty;

/**
 * 类 {@link Workbook} 用于创建和操作 Excel 工作簿。
 *
 * <p>该类使用 Apache POI 库来实现工作簿的创建和行的添加。
 * 默认情况下，会创建一个名为 "Sheet1" 的工作表。
 *
 * <p>可以通过 {@link #addRow(Object...)} 方法添加行数据，
 * 通过 {@link #getRow(int)} 方法获取指定行的数据。
 *
 * <p>调用 {@link #write(OutputStream)} 方法可以将工作簿写入指定的输出流。
 *
 * <p>注意：在使用此类时，请确保已添加 Apache POI 依赖。
 *
 * @author RedGogh
 */
public class Workbook {

    /**
     * Apache POI 工作簿实例，用于创建和操作 Excel 文件。
     */
    private final org.apache.poi.ss.usermodel.Workbook wb = new XSSFWorkbook();

    /**
     * 当前工作簿中的工作表。
     */
    private Sheet sheet;

    /**
     * 构造函数，创建一个新的工作簿但不初始化任何工作表。如果
     * 需要创建工作表，使用 {@link #checkout} 函数创建。
     */
    public Workbook() {
        this(null);
    }

    /**
     * 构造函数，创建一个新的工作簿并初始化工作表。
     *
     * <p>使用此构造函数可以指定初始工作表名称。
     */
    public Workbook(String name) {
        if (strnempty(name))
            sheet = wb.createSheet(name);
    }

    /**
     * 检查并获取指定名称的工作表。如果不存在，则创建一个新的工作表。
     *
     * <p>此方法用于确保在操作之前工作表存在，如果指定名称的工作表不存在，将自动创建。
     *
     * <p>示例用法：
     * <pre>
     *      workbook.checkout("Sheet2");
     * </pre>
     *
     * @param name 工作表的名称。
     * @throws IllegalArgumentException 如果工作表名称为 {@code null} 或空字符串。
     */
    public void checkout(String name) {
        sheet = Optional.ifNull(wb.getSheet(name), wb.createSheet(name));
    }

    /**
     * 向工作表添加一行数据。
     *
     * <p>此方法将接受任意数量的参数，并将它们作为单元格的内容添加到当前工作表中。
     *
     * <p>注意：如果参数为 null，单元格将设置为一个空字符串。
     *
     * <p>示例用法：
     * <pre>
     *      Workbook workbook = new Workbook();
     *      workbook.addRow("数据1", "数据2", "数据3");
     * </pre>
     *
     * @param values 行数据，类型为 Object 变长参数。
     *
     */
    public void addRow(Object... values) {
        addRow(new Row(Lists.map(values, BasicConverter::atos)));
    }

    /**
     * 向工作表添加一行 {@link Row} 对象。
     *
     * <p>此方法将使用给定的 {@link Row} 对象将数据添加到当前工作表。
     *
     * <p>注意：确保 {@code rowUnit} 不是 null。
     *
     * <p>示例用法：
     * <pre>
     *      Row row = new Row();
     *      row.add("数据1");
     *      workbook.addRow(row);
     * </pre>
     *
     * @param rowUnit 行数据的 {@link Row} 对象。
     * @throws NullPointerException 如果 {@code rowUnit} 为 null。
     *
     */
    public void addRow(Row rowUnit) {
        int rowNum = sheet.getLastRowNum();
        org.apache.poi.ss.usermodel.Row row = sheet.createRow(rowNum + 1);
        for (int i = 0; i < rowUnit.size(); i++) {
            row.createCell(i).setCellValue(atos(rowUnit.get(i)));
        }
    }

    /**
     * 获取指定索引的行数据。
     *
     * <p>此方法返回指定索引的行的数据，作为一个 {@link Row} 对象。
     *
     * <p>注意：索引必须在有效范围内，否则会抛出异常。
     *
     * <p>示例用法：
     * <pre>
     *      Row row = workbook.getRow(0);
     * </pre>
     *
     * @param index 行的索引。
     * @return {@link Row} 对象，包含该行的所有单元格数据。
     * @throws IndexOutOfBoundsException 如果索引超出范围。
     *
     */
    public Row getRow(int index) {
        org.apache.poi.ss.usermodel.Row row = sheet.getRow(index);
        Row retval = new Row();

        short lastCellNum = row.getLastCellNum();
        for (short i = 0; i < lastCellNum; i++)
            retval.add(row.getCell(i).getStringCellValue());

        return retval;
    }

    /**
     * 将工作簿写入指定的输出流。
     *
     * <p>使用此方法可以将当前工作簿的数据输出到文件或其他输出流中。
     *
     * <p>注意：在写入流之前，请确保流已经打开并可以写入。
     *
     * <p>示例用法：
     * <pre>
     *      try (OutputStream stream = new FileOutputStream("output.xlsx")) {
     *          workbook.write(stream);
     *      }
     * </pre>
     *
     * @param stream 输出流，用于写入工作簿数据。
     *
     */
    public void write(OutputStream stream) {
        throwIfError(() -> wb.write(stream));
    }

}
