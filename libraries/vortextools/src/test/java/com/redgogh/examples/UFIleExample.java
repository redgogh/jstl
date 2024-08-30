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

import com.redgogh.vortextools.io.UFile;
import org.junit.Test;

import static com.redgogh.vortextools.RedGogh.printf;

@SuppressWarnings("ALL")
public class UFIleExample {

    /**
     * 随机读写访问测试
     */
    @Test
    public void randomAccessExample() {
        UFile uFile = new UFile(".dat");

        // write float
        uFile.open();
        {
            float writeValue = 114.514f;
            uFile.writeFloat(writeValue);
            printf("-------------------------------------------------------------------\n");
            printf("UFile random access write float example, write value: %f\n", writeValue);
        }
        uFile.close();

            printf("-------------------------------------------------------------------\n");

        // read float
        uFile.open();
        {
            float readValue = 0.0f;
            readValue = uFile.readFloat();
            printf("UFile random access read float example, read value: %f\n", readValue);
            printf("-------------------------------------------------------------------\n");
        }
        uFile.close();

        uFile.forceDelete();
    }

    /**
     * 随机读写访问测试, 写入两个 Int
     */
    @Test
    public void randomAccessWriteExample() {
        UFile uFile = new UFile(".dat");

        uFile.open();

        uFile.writeInt(1001);
        uFile.writeInt(1002);
        uFile.writeInt(1003);

        uFile.seek(0);

        printf("----------------------------------------------------\n");
        printf("random access write example read(0): %s\n", uFile.readInt());
        printf("random access write example read(1): %s\n", uFile.readInt());
        printf("random access write example read(2): %s\n", uFile.readInt());
        printf("----------------------------------------------------\n");

        uFile.forceDelete();
    }

}
