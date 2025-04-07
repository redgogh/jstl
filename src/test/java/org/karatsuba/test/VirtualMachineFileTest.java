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

import org.karatsuba.io.VirtualMachineFile;
import org.junit.Test;

import static org.karatsuba.utils.TypeCvt.atos;

@SuppressWarnings("ALL")
public class VirtualMachineFileTest {

    @Test
    public void readBytesTest() {
        System.out.println(atos(new VirtualMachineFile("Desktop://log.txt").readAllBytes()));
    }

    @Test
    public void listFileTest() {
        for (VirtualMachineFile virtualMachineFile : new VirtualMachineFile("D:/aaaa").listFiles()) {
            System.out.println(virtualMachineFile.getPath());
        }
    }

}
