"""

    Copyright (C) 2019-2024 RedGogh All rights reserved.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

"""
import cv2
import face_recognition
import os
from pathlib import Path
import configparser

# Initialize properties config file.
config = configparser.ConfigParser()

with open("faceclari.ini", "r", encoding="UTF-8") as file:
    config.read_file(file)

# Value of config.
known_faces_dir = config.get("dir", "known")
matched_faces_dir = config.get("dir", "matched")
scan_dir = config.get("dir", "scandir")
tolerance = config.getfloat("recognition", "tolerance")
drawrect = config.getboolean("recognition", "drawrect")

kfaces = []

def typecheck(pathname):
    """
    Check if the file is image or not.
    """
    return Path(pathname).suffix in [".jpg", ".png"]


class KFace:
    """
    Loaded all the known face encodings and locations in memory. Use this
    class to compare other faces.
    """
    def __init__(self, dirname, lenc, lloc):
        self.dirname = Path(dirname)
        self.lenc = lenc
        self.lloc = lloc
        self.name = self.dirname.stem


    def compare(self, image_path):
        """
        Compare the known face data with to unknown face data. if similarity
        is high return true otherwise return false.
        """
        if not typecheck(image_path):
            return False

        abs_image_path = Path(image_path).resolve()
        image_file = face_recognition.load_image_file(abs_image_path)
        lenc = face_recognition.face_encodings(image_file)
        lloc = face_recognition.face_locations(image_file)

        matchs = None
        find = False

        for cmpenc, cmploc in zip(self.lenc, self.lloc):
            matchs = face_recognition.compare_faces(lenc, cmpenc, tolerance=tolerance)
            find = True in matchs
            if find:
                break

        if find:
            origin = cv2.cvtColor(image_file, cv2.COLOR_RGB2BGR)

            # draw rectangle on matched face
            if drawrect:
                matched_index = matchs.index(True)
                top, right, bottom, left = lloc[matched_index]
                cv2.rectangle(origin, (left, top), (right, bottom), (255, 0, 0), 2)

            final_dir = f"{matched_faces_dir}/{self.name}"
            if not os.path.exists(final_dir):
                os.makedirs(final_dir)

            cv2.imwrite(f"{final_dir}/{os.path.basename(image_path)}", origin)

        return find


def load_known_faces():
    """
    loading all the known faces in known_faces directory. Each image in the
    known_faces directory must include one face.
    """
    kfaces = []

    for item in os.listdir(known_faces_dir):
        face_dir = os.path.join(known_faces_dir, item)
        if Path(face_dir).is_dir():
            # face encodings and locations.
            lenc = []
            lloc = []

            for filename in os.listdir(face_dir):
                if typecheck(filename):
                    abs_file_path = Path(f"{face_dir}/{filename}").resolve()
                    image_file = face_recognition.load_image_file(abs_file_path)
                    enc = face_recognition.face_encodings(image_file)
                    loc = face_recognition.face_locations(image_file)

                    lenc.append(enc[0])
                    lloc.append(loc[0])

                    print(f"Loading face data of {item}/{filename}")

            kfaces.append(KFace(face_dir, lenc, lloc))

    return kfaces


if __name__ == "__main__":
    # create matched_faces dir if not exists.
    if not os.path.exists(matched_faces_dir):
        os.makedirs(matched_faces_dir)

    # loading faces
    kfaces = load_known_faces()

    for dirpath, dirnames, filenames in os.walk(scan_dir):
        for filename in filenames:
            abs_file_name = os.path.join(dirpath, filename)
            print(f"SCAN INFO --- {abs_file_name}")
            for kface in kfaces:
                kface.compare(abs_file_name)