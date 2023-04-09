import random
import string

datos = []

for i in range(1000):
    entero = random.randint(0, 100)
    cadena = "".join(random.choices(string.ascii_letters, k=10))
    decimal = random.uniform(0.0, 100.0)
    otro_entero = random.randint(0, 100)
    datos.append((entero, cadena, decimal, otro_entero))

# Escribir cada tupla en una nueva línea en un archivo con "new Item" antes de cada tupla
with open("datos.txt", "w") as archivo:
    for tupla in datos:
        tupla_str = str(tupla).replace("'", '"').replace(")", "),")
        archivo.write('new Item ' + tupla_str + '\n')