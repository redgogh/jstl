package org.karatsuba.test;

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

import org.karatsuba.io.PhysicalFile;
import org.junit.Test;

import static org.karatsuba.utils.TypeCvt.atos;

@SuppressWarnings("ALL")
public class PhysicalFileTest {

    /**
     * 随机读写访问测试
     */
    @Test
    public void randomAccessTest() {
        PhysicalFile physicalFile = new PhysicalFile(".dat");

        // write float
        physicalFile.open();
        {
            float writeValue = 114.514f;
            physicalFile.writeFloat(writeValue);
            System.out.printf("-------------------------------------------------------------------\n");
            System.out.printf("File random access write float example, write value: %f\n", writeValue);
        }
        physicalFile.close();

            System.out.printf("-------------------------------------------------------------------\n");

        // read float
        physicalFile.open();
        {
            float readValue = 0.0f;
            readValue = physicalFile.readFloat();
            System.out.printf("File random access read float example, read value: %f\n", readValue);
            System.out.printf("-------------------------------------------------------------------\n");
        }
        physicalFile.close();

        physicalFile.forceDelete();
    }

    /**
     * 随机读写访问测试, 写入两个 Int
     */
    @Test
    public void randomAccessWriteTest() {
        PhysicalFile physicalFile = new PhysicalFile(".dat");

        physicalFile.open();

        physicalFile.writeInt(1001);
        physicalFile.writeInt(1002);
        physicalFile.writeInt(1003);

        physicalFile.seek(0);

        System.out.printf("----------------------------------------------------\n");
        System.out.printf("random access write example read(0): %s\n", physicalFile.readInt());
        System.out.printf("random access write example read(1): %s\n", physicalFile.readInt());
        System.out.printf("random access write example read(2): %s\n", physicalFile.readInt());
        System.out.printf("----------------------------------------------------\n");

        physicalFile.forceDelete();
    }

    @Test
    public void readBytesTest() {
        System.out.println(atos(new PhysicalFile("Desktop://log.txt").readAllBytes()));
    }

    @Test
    public void listFileTest() {
        for (PhysicalFile physicalFile : new PhysicalFile("D:/aaaa").listFiles()) {
            System.out.println(physicalFile.getPath());
        }
    }

}
