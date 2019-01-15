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

def resize(image):
    # reducir a la mitad del tamaño
    SCALE_VAL = 0.5
    height, width, depth = image.shape
    new_x, new_y = image.shape[1] * SCALE_VAL, image.shape[0] * SCALE_VAL
    return cv2.resize(image,(int(new_x), int(new_y)))

def blur(image):
    # suavizando/ opacando la imagen
    kernel = (30, 30)
    return cv2.blur(image,kernel);

def threshold_HSV(image):
    # aplicamos un filtro basado en hue, saturation, value
    lower_orange = (0.0, 64.20863, 201.798561)
    upper_orange = (174.114124, 255, 255)

    hsv = cv2.cvtColor(image, cv2.COLOR_BGR2HSV)
    return cv2.inRange(hsv, lower_orange, upper_orange)

def blobs(image):
    pass 

def simple_image(url):
    # capturando la imagen que viene del servidor de la camara
    cap = cv2.VideoCapture(url)

    # capturando cada cuadro
    while True:
        ret, frame = cap.read()

        # detectando los cargos color naranja
        # que se pasen por enfrente del robot
        resized_img = resize(frame)
        blur_img = blur(resized_img)
        cargo = threshold_HSV(blur_img)
        
        cv2.imshow('FRANKIE CAMERA', frame)
        cv2.imshow('BLUR IMAGE', blur_img)
        cv2.imshow('CARGO DETECTOR ALGORITHM', cargo)
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