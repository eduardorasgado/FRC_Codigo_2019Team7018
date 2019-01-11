import cv2
import numpy as np

def mainLoop():
    cap = cv2.VideoCapture(0)

    NARANJA_MINIMO = np.array([5, 60, 60]);
    NARANJA_MAXIMO = np.array([15, 255, 255])

    while(True):
        ret, frame = cap.read()

        hsv_img = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)

        # aplicando la deteccion de colores naranjas
        threshold = cv2.cv2.inRange(hsv_img, NARANJA_MINIMO, NARANJA_MAXIMO)

        canny_white = cv2.Canny(threshold, 280, 300)

        kernel = np.ones((5, 5), np.uint8)
        opening = cv2.morphologyEx(threshold, cv2.MORPH_OPEN, kernel)   

        cv2.imshow("Frame", frame);
        cv2.imshow("Cargo detection", opening)
        cv2.imshow("Cargo detection", canny_white);

        if cv2.waitKey(25) & 0xFF == ord('q'):
            break
    
    cap.release()
    cv2.destroyAllWindows()

if __name__=="__main__":
    mainLoop()