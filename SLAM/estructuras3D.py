#!/usr/bin/env python3

"""
A cartesian frame implementation
"""
import pygame

class Nodo:
    def __init__(self, coordinates):
        # x, y, z space
        self.x = coordinates[0]
        self.y = coordinates[1]
        self.z = coordinates[2]


class Arista:
    def __init__(self, start, stop):
        self.start = start
        self.stop = stop


class Estructura:
    def __init__(self):
        self.nodos = []
        self.aristas = []

    def addNodos(self, nodelist):
        for node in nodelist:
            # adding a nodo object with its coordinates
            self.nodos.append(Nodo(node))

    def addAristas(self, edgeList):
        for (start, stop) in edgeList:
            self.aristas.append(Arista(self.nodos[start], self.nodos[stop]))

    # debugging tools
    def outputNodos(self):
        print("\n---Nodos---")
        for i, node in enumerate(self.nodos):
            print("{}, {}, {}".format(i, node.x, node.y, node.z))

    def outputAristas(self):
        print("\n---Aristas---")
        for i, edge in enumerate(self.aristas):
            print("{} from: {}, {}, {}"
                .format(i, edge.start.x, edge.start.y, edge.start.z))
            print("to {}, {}, {}".format(edge.stop.x, edge.stop.y, edge.stop.z))




def main():
    mi_estructura = Estructura()
    mi_estructura.addNodos([(0, 0, 0), (1, 2, 3), (3, 2, 1)] )
    mi_estructura.addAristas([(1, 2)])

    # 1 x 1 x 1 cube
    cube_nodes = [(x, y, z) for x in (0, 1) for y in (0, 1) for z in (0, 1)]

    cube = Estructura()
    cube.addNodos(cube_nodes)

    cube.addAristas([(n, n+1) for n in range(0, 8, 2)])
    cube.addAristas([(n, n+2) for n in (0, 1, 4, 5)])

    cube.outputNodos()
    cube.outputAristas()

if __name__=="__main__":
    main()


    

