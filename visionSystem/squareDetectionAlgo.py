import cv2
import numpy as np

def mainLoop():
    cap = cv2.VideoCapture("GoPro.mp4")

    # manejando el error
    if cap.isOpened() == False:
        print("Error al procesar el video")

    # cambiando el size para manipular mejor la imagen
    frame_width = int(cap.get(3))
    frame_height = int(cap.get(4))
    print("Altura: {}, Ancho: {}".format(frame_width, frame_height))
    
    # loop principal
    while(cap.isOpened()):
        ret, frame = cap.read()

        # procesando la imagen
        
        
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