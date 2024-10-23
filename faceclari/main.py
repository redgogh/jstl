import face_recognition
import os
import numpy
from PIL import Image, ImageDraw

input_dir = "input_faces/"
output_dir = "matched_faces/"

if not os.path.exists(output_dir):
    os.makedirs(output_dir)

image_file = face_recognition.load_image_file("target.jpg")
target_face_encoding = face_recognition.face_encodings(image_file)[0]

tolerance = 0.45


def face_compare(main_face_encoding, image_path):
    image = face_recognition.load_image_file(image_path)
    face_encodings = face_recognition.face_encodings(image)
    face_locations = face_recognition.face_locations(image)

    is_matched = False

    for face_encoding, face_location in zip(face_encodings, face_locations):
        face_encoding = numpy.array(face_encoding)

        distance = numpy.linalg.norm(face_encoding - main_face_encoding)
        is_matched = distance <= tolerance

        if is_matched:
            pil_image = Image.fromarray(image)
            draw = ImageDraw.Draw(pil_image)

            top, right, bottom, left = face_location
            draw.rectangle([left, top, right, bottom], outline="red", width=3)

            base_filename = os.path.basename(image_path)
            new_filename = f"matched_{base_filename}"

            pil_image.save(os.path.join(output_dir, new_filename))
            break

    print(f"Matching face image {image_path}, Result: {is_matched}")


for root, dirs, files in os.walk(input_dir):
    for filename in files:
        if filename.endswith(".jpg") or filename.endswith(".png"):
            image_path = os.path.join(root, filename)
            face_compare(target_face_encoding, image_path)