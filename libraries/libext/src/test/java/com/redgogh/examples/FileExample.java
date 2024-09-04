package com.redgogh.examples;

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

import com.redgogh.libext.io.File;
import org.junit.Test;

import static com.redgogh.libext.AnyObjects.printf;

@SuppressWarnings("ALL")
public class FileExample {

    /**
     * 随机读写访问测试
     */
    @Test
    public void randomAccessExample() {
        File file = new File(".dat");

        // write float
        file.open();
        {
            float writeValue = 114.514f;
            file.writeFloat(writeValue);
            printf("-------------------------------------------------------------------\n");
            printf("File random access write float example, write value: %f\n", writeValue);
        }
        file.close();

            printf("-------------------------------------------------------------------\n");

        // read float
        file.open();
        {
            float readValue = 0.0f;
            readValue = file.readFloat();
            printf("File random access read float example, read value: %f\n", readValue);
            printf("-------------------------------------------------------------------\n");
        }
        file.close();

        file.forceDelete();
    }

    /**
     * 随机读写访问测试, 写入两个 Int
     */
    @Test
    public void randomAccessWriteExample() {
        File file = new File(".dat");

        file.open();

        file.writeInt(1001);
        file.writeInt(1002);
        file.writeInt(1003);

        file.seek(0);

        printf("----------------------------------------------------\n");
        printf("random access write example read(0): %s\n", file.readInt());
        printf("random access write example read(1): %s\n", file.readInt());
        printf("random access write example read(2): %s\n", file.readInt());
        printf("----------------------------------------------------\n");

        file.forceDelete();
    }

}
