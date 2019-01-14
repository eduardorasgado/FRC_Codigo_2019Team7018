# https://pythonprogramming.net/haar-cascade-object-detection-python-opencv-tutorial/

import urllib.request
import os
import cv2
import numpy as np

def store_raw_images():
    negative_link = 'http://image-net.org/api/text/imagenet.synset.geturls?wnid=n04098169'
    negative_img_urls = urllib.request.urlopen(negative_link).read().decode()

    if not os.path.exists('neg'):
        os.makedirs('negatives')

    pic_num = 1
    for i in negative_img_urls.split('\n'):
        try:
            print(i)
            filename_and_path = 'neg/'+str(pic_num)+'.jpg'
            urrlib.request.urlretrieve(i, filename_and_path)
            img = cv2.imread(filename_and_path, cv2.IMREAD_GRAYSCALE)
            resized_image = cv2.resize(img, (100, 100))
            cv2.imwrite(filename_and_path, resized_iamge)
            pic_num +=1
            
        except Exception as e:
            print(str(e))


if __name__ == "__main__":
    store_raw_images()
