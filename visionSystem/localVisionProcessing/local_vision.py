import cv2
import numpy as np
import urllib.request as urllib
import sys

def test(url):
    response = urllib.urlopen(url)
    image = np.asarray(bytearray(response.read()), dtype=np.uint8)
    image = cv2.imdecode(image, cv2.IMREAD_COLOR)
    cv2.imshow("image", image)
    if(cv2.waitKey(25) & 0xFF == ord('q')):
        exit(0)

def url_to_image_mjpeg(url):
    url = 'http://frcvision.local:1181/stream.mjpg'
    stream = urllib.urlopen(url)
    byte = bytes()
    while True:
        byte += stream.read(1024)
        a = byte.find(b'\xff\xd8')
        b = byte.find(b'\xff\xd9')

        if a != -1 and b != -1:
            jpg = byte[a:b+2]
            byte = byte[b+2:]
            image = cv2.imdecode(np.fromstring(jpg,
                dtype=np.uint8), cv2.IMREAD_COLOR)
            cv2.imshow("decoded image", image)
            if cv2.waitKey(25) & 0xFF == ord('q'):
                break

    cv2.destroyAllWindows()

def simple_image(url):
    # capturando la imagen que viene del servidor de la camara
    cap = cv2.VideoCapture(url)

    # capturando cada cuadro
    while True:
        ret, frame = cap.read()
        cv2.imshow('FRANKIE CAMERA', frame)
        # si presionamos esc
        if cv2.waitKey(1) == 27:
            break
    cv2.destroyAllWindows()
        

if __name__ == "__main__":
    url = 'http://frcvision.local:1181/stream.mjpg'
    #url_to_image(url)
    #test(url)
    #url_to_image_mjpeg(url)
    simple_image(url)