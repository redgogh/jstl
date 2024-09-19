import cv2
import pathlib


def cv2_haar_data(name):
    return cv2.data.haarcascades + name


def detect_faces(image_path):
    print(cv2.data.haarcascades)
    face_cascade = cv2.CascadeClassifier(cv2_haar_data('haarcascade_frontalface_default.xml'))
    image = cv2.imread(image_path)
    gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
    faces = face_cascade.detectMultiScale(gray, scaleFactor=1.1, minNeighbors=6, minSize=(10, 10))
    return image, faces

'''
main
'''
if __name__ == '__main__':
    (image, faces) = detect_faces('a.jpg')

    length = len(faces)
    print(f'face count: {length}')

    for (x, y, w, h) in faces:
        cv2.rectangle(image, (x, y), (x + w, y + h), (0, 255, 0), 2)

    cv2.imwrite('o.png', image)
