//Practice 2
// 1. Crea una lista llamada "lista" con los elementos "rojo", "blanco", "negro"
// Importamos la biblieria que nos permite hacer listas mutables
// ya que por defecto las listas son inmutables, esto quiere decir
// que no se puede modificar
import scala.collection.mutable.ListBuffer
var lista = collection.mutable.ListBuffer("rojo","blanco","negro")

// 2. Añadir 5 elementos mas a "lista" "verde" ,"amarillo", "azul", "naranja", "perla"
// Ya con las lista mutable se puedes agregar uno por uno los datos que queremos tener
lista += "verde"
lista += "amarillo"
lista += "azul"
lista += "naranja"
lista += "perla"

// 3. Traer los elementos de "lista" "verde", "amarillo", "azul"
// La funcion (slice) nos permite tomar los datos con el uso de sus coordenadas
// que se encuentran en la lista
lista slice (3,6)

// 4. Crea un arreglo de numero en rango del 1-1000 en pasos de 5 en 5
// La funcion (range) nos permite darle el rango que queremos que tenga nuestro arreglo
Array.range(1, 1000, 5)

// 5. Cuales son los elementos unicos de la lista Lista(1,3,3,4,6,7,3,7) utilice conversion a conjuntos
// La funcion (toSet) nos muestra datos no repetidos o duplicados
lista.toSet

// 6. Crea una mapa mutable llamado nombres que contenga los siguiente "Jose", 20, "Luis", 24, "Ana", 23, "Susana", "27"
var mutmap = collection.mutable.Map(("Jose", 20), ("Luis", 24), ("Susana", 27))

// 6 a . Imprime todas la llaves del mapa
println(mutmap)

// 7 b . Agrega el siguiente valor al mapa("Miguel", 23)
mutmap += ("Miguel" -> 23)