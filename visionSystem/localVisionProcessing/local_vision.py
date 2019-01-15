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
    #height, width, depth = image.shape
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
    #detectando circulos para saber que existe un cargo al frente,
    # de igual manera saber donde se encuentra
    params = cv2.SimpleBlobDetector_Params()
    params.minThreshold = 0;
    params.maxThreshold = 250;

    params.filterByArea = True
    params.minArea = 22.0

    params.filterByCircularity = True
    params.minCircularity = 0.8
    params.maxCircularity = 1.0

    params.filterByConvexity = True
    params.minConvexity = 0.5

    params.filterByInertia = True
    params.minInertiaRatio = 0.5

    # creando un detector con los paramentros
    detector = cv2.SimpleBlobDetector_create(params)
    
    #detectando los blobs en la imagen
    # debemos hacerle un reverse a la matriz de la imagen
    reverse_image = 255-image
    keypoints = detector.detect(reverse_image)

    x, y = reverse_image.shape
    # creando una imagen completamente oscura
    image = np.zeros((x, y, 3), np.uint8)
    image[:] = (0, 0, 0)
    
    #dibujando los blobs
    return cv2.drawKeypoints(image, keypoints, np.array([]),
            (0,0,255), cv2.DRAW_MATCHES_FLAGS_DRAW_RICH_KEYPOINTS)

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
        t_hsv = threshold_HSV(blur_img)
        blobs_img = blobs(t_hsv)
        cv2.imshow('FRANKIE CAMERA', frame)
        cv2.imshow('BLUR IMAGE', blur_img)
        cv2.imshow('HSV ORANGE FILTER', t_hsv)
        cv2.imshow('CARGO DETECTOR ALGORITHM', blobs_img)
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