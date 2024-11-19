package com.redgogh.test;

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

import com.redgogh.common.io.File;
import org.junit.Test;

import static com.redgogh.common.base.BasicConverter.atos;

@SuppressWarnings("ALL")
public class FileTest {

    /**
     * 随机读写访问测试
     */
    @Test
    public void randomAccessTest() {
        File file = new File(".dat");

        // write float
        file.open();
        {
            float writeValue = 114.514f;
            file.writeFloat(writeValue);
            System.out.printf("-------------------------------------------------------------------\n");
            System.out.printf("File random access write float example, write value: %f\n", writeValue);
        }
        file.close();

            System.out.printf("-------------------------------------------------------------------\n");

        // read float
        file.open();
        {
            float readValue = 0.0f;
            readValue = file.readFloat();
            System.out.printf("File random access read float example, read value: %f\n", readValue);
            System.out.printf("-------------------------------------------------------------------\n");
        }
        file.close();

        file.forceDelete();
    }

    /**
     * 随机读写访问测试, 写入两个 Int
     */
    @Test
    public void randomAccessWriteTest() {
        File file = new File(".dat");

        file.open();

        file.writeInt(1001);
        file.writeInt(1002);
        file.writeInt(1003);

        file.seek(0);

        System.out.printf("----------------------------------------------------\n");
        System.out.printf("random access write example read(0): %s\n", file.readInt());
        System.out.printf("random access write example read(1): %s\n", file.readInt());
        System.out.printf("random access write example read(2): %s\n", file.readInt());
        System.out.printf("----------------------------------------------------\n");

        file.forceDelete();
    }

    @Test
    public void readBytesTest() {
        System.out.println(atos(new File("Desktop://log.txt").readBytes()));
    }

    @Test
    public void listFileTest() {
        for (File file : new File("D:/aaaa").listFiles()) {
            System.out.println(file.getPath());
        }
    }

}
