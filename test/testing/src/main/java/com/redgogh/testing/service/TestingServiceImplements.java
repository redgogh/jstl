package com.redgogh.testing.service;

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

/* Create on 2024/9/13 */

import com.redgogh.testing.enums.TikaFile;
import com.redgogh.tools.io.File;
import org.apache.tika.Tika;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.TimeUnit;

import static com.redgogh.tools.Assert.throwIfError;
import static com.redgogh.tools.StringUtils.strxip;

/**
 * @author RedGogh
 */
@Service
public class TestingServiceImplements implements TestingService {

    @Override
    public boolean uploadFile(MultipartFile multipartFile) {
        return new File(multipartFile.getOriginalFilename()).typeEquals(".pdf");
    }

    @Override
    public void asyncCall(int sleep) {
        throwIfError(() -> Thread.sleep(TimeUnit.SECONDS.toMillis(sleep)));
    }

    @Override
    public String tika(MultipartFile multipartFile, TikaFile tikaFile) {
        return throwIfError(() -> new Tika().parseToString(multipartFile.getInputStream()));
    }
}
