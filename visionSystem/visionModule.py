import cv2

class VideoCapture:
    def __init__(self, cameraNumber):
        self.cap = cv2.VideoCapture(cameraNumber)
        self.frame = 0
        self.gray = 0
        
    def reading(self):
        _, self.frame = self.cap.read();
        
    def grayConversor(self):
        self.gray = cv2.cvtColor(self.frame, cv2.COLOR_RGB2GRAY)
        
    def showCamera(self, cameraNames):
        # cameraNames are send as an array of names
        for camera in cameraNames:
            if camera == "gray":
                if(self.gray.any()):
                    cv2.imshow("Gray camera", self.gray)
                else:
                    # on debug mode
                    print("This camera is not initialized.")
                    
    def stop(self):
        self.cap.release();
        cv2.destroyAllWindows()

## end VideoCapture class

def mainLoop():
    #initializing opencv2
    videocap = VideoCapture(0)
    
    # camera capture loop
    while(True):
        #capturing frame by frame
        videocap.reading()
        
        # converting to gray
        videocap.grayConversor()
        
        #display
        camerasAvailable = ["gray"]
        videocap.showCamera(camerasAvailable)
        
        #listening to close event
        if cv2.waitKey(1) & 0xFF == ord('q'):
            break;

    videocap.stop()

if __name__=="__main__":
    mainLoop()
    
    
    
