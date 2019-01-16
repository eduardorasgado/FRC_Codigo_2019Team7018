import os
import sys
import urllib.request as urllib
import cv2

def download_images(url):
    images_url = urllib.urlopen(url)
    print("URL: {}".format(url))

    # conseguir el directorio de trabajo actual
    path = os.getcwd()
    # crear el directorio donde se van a guardar las imagenes
    file_path = path+"/images"
    file_path_resized = path+"/resized"
    
    #directory = os.path.dirname(file_path)

    # si el directorio no existe
    if not os.path.exists(file_path):
        print("NO HAY DIRECTORIO. VA A SER CREADO:")
        # creamos la carpeta
        os.mkdir("images")

    if not os.path.exists(file_path_resized):
        #creamos la carpeta donde va la imagen hecha peque
        os.mkdir("resized")
        
    #descargamos cada imagen en el link
    link_counter = 0
    all_links = images_url.read().decode()
    
    for link in all_links.split("\n"):
        nombre = "bolos"+str(link_counter)+".jpg"
        
        nombre_path = "images/"+ nombre
        nombre_small_path = "resized/"+nombre
        
        print("link: {}".format(link))

        # oponerle un nombre al elemento dentro del link
        # guardando la imagen original
        try:
            # en caso de que el link este disponible
            # se descarga la imagen
            urllib.urlretrieve(link, nombre_path)
             # con opencfv
            img = cv2.imread(nombre_path)
            #hacer mas pequeña la imagen
            resized_imagen = cv2.resize(img, (400, 400))
            # guardando la imagen mas peque
            cv2.imwrite(nombre_small_path, resized_imagen)        
            link_counter += 1
        except Exception as e:
            """En caso de que el link no exista o
            no halla una imagen en su contenido"""
            print(str(e))
            print("[LINK NO FUNCIONA]... SKIPPED...")

    print("EL total de imagenes es de: {}".format(link_counter))

if __name__ == "__main__":
    if len(sys.argv) != 2:
        print("No hay URL")
        exit(0)

    url = sys.argv[1]

    download_images(url)
    