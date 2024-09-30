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
import com.redgogh.tools.refection.ObjectSerializer;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.junit.Test;

import java.io.Serializable;

@SuppressWarnings("ALL")
public class ObjectSerializerExample {

    @AllArgsConstructor
    @ToString
    static class User implements Serializable {
        /* test field */
        private String name = "Crazy";
    }

    @Test
    public void serializeExample() {
        ObjectSerializer.serialize(new User("Judy"), new File("Desktop://judy.ser"));
    }

    @Test
    public void deserializeExample() {
        File file = new File("Desktop://judy.ser");
        User user = (User) ObjectSerializer.deserialize(file);
        System.out.println(user);
        file.forceDelete();
    }


}
