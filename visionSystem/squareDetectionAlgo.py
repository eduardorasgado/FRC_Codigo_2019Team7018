import cv2
import numpy as np

JUST_ONCE = 0
def resizingImage(img, f_height, f_width):
    #cambiando el tamano de la imagen a la mitad
    ## dividiendo enteros
    f_height = f_height//2
    f_width = f_width//2

    global JUST_ONCE
    #imprimiendo las nuevas medidas
    if JUST_ONCE == 0:
        print("Altura: {}, Ancho: {}".format(f_height, f_width))
        JUST_ONCE = 1
    
    img = cv2.resize(img, (f_height, f_width))
    return img, f_height, f_width

def gettingROI(img, width, height):

    print("[WIDTH]: {} [HEIGHT]: {}".format(width, height))
    # poligono o rectangulo de abajo de el poligono superior
    #vertices = np.array([[0, height//2],[width,height//2],[width,height],[0,height]], np.int32)

    # poligono que permanecera al final con la imagen del procesamiento inicial
    vertices = np.array([[0, 0],[width, 0],[width, height*0.75],[0, height*0.75]], np.int32)
    vertices = [vertices]
    # creando un filtro de puros ceros
    mask = np.zeros_like(img)
    # trazar el poligino en la mascara de zeros y llenar lo que no nos interesa
    # con negro
    cv2.fillPoly(mask, vertices, 255)
    #fusionar con una compuerta logica and
    masked = cv2.bitwise_and(img, mask)
    return masked

def processingImage(frame, width, height):
    gray = cv2.cvtColor(frame, cv2.COLOR_RGB2GRAY)

    # consiguiendo las areas de interes
    masked = gettingROI(gray, width, height)

    # aplicando un filtro threshold que produce una imagen binaria con los rangos propuestos
    #img, filtro bajo, filtro alto, algoritmo de filtro
    ret, threshImg = cv2.threshold(masked, 180, 255, cv2.THRESH_BINARY)

    # eliminando falsos positivos
    kernel = np.ones((4,4), np.uint8)
    threshImg = cv2.morphologyEx(threshImg,  cv2.MORPH_OPEN, kernel)

    
    return gray, masked, threshImg

def mainLoop():
    cap = cv2.VideoCapture("GoPro.mp4")

    # manejando el error
    if cap.isOpened() == False:
        print("Error al procesar el video")

    # capturando tamano de la imagen
    frame_width = int(cap.get(3))
    frame_height = int(cap.get(4))
    print("Altura: {}, Ancho: {}".format(frame_width, frame_height))

    # loop principal
    while(cap.isOpened()):
        ret, frame = cap.read()

        # procesando la imagen
        #cambiando tamano de la imagen
        frame, f_height, f_width = resizingImage(frame, frame_width, frame_height)

        grayScale, masked, threshImg = processingImage(frame, f_height, f_width)

        # comprobando que exista un retorno de pantalla
        if ret == True:
             cv2.imshow("Cuadro inicial", frame)
             cv2.imshow("Escala de grises", grayScale)
             cv2.imshow("Region de interes", masked)
             cv2.imshow("Imagen binaria", threshImg)

             if cv2.waitKey(25) & 0xFF == ord('q'):
                break

    # terminando la aplicacion
    cap.release()
    cv2.destroyAllWindows()

if __name__=="__main__":
    mainLoop()