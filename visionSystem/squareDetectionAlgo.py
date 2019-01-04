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
    return img

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
        frame = resizingImage(frame, frame_width, frame_height)
        
        # comprobando que exista un retorno de pantalla
        if ret == True:
             cv2.imshow("Cuadro inicial", frame)

             if cv2.waitKey(25) & 0xFF == ord('q'):
                break

    # terminando la aplicacion
    cap.release()
    cv2.destroyAllWindows()

if __name__=="__main__":
    mainLoop()