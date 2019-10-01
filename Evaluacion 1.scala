// maria juega laloncesto universitaro y quiere ser profesional.
// Cada temporada ella mantiene un registro de su juego.
// Tabula la cantidad de veces que rompe su record de temporada para la mayoria de los puntos
// y la cantidad menor cantidad de puntos en un juego. Los puntos anotados en el primer juego
//establece su record para la temporada, y ella comienza a contar desde alli
//Scores=[12,24,10,24]

//funcion BreakinRecords declaracion de variables a utilizar
def breakingRecords (Num:List[Int]) : Unit =
{
    var Minimo =Num(0)
    var Maximo =Num(0)
    var CMinimo = 0
    var CMaximo =0
    
    //Ciclo for decompracion para vereificar si rompio el records de temporada o no
    for (i <- Num)
    {
        if (i<Minimo)
        {
            Minimo = i
            CMinimo= CMinimo +1
        }
        if (i>Maximo)
        {
            Maximo = i
            CMaximo = CMaximo + 1
        }
    }
    println (CMaximo,CMinimo)
}

//lista con los valores de la temporada de maria "lista de Scores"
var lista = List(10,5,20,20,4, 5, 2, 25, 1)
var lista2 = List(3,4,21,36,10,28,35,5,24,42)
println("Puntaje Mas Alto")
breakingRecords(lista)
println("Puntaje Mas Bajo")
breakingRecords(lista2)

//Santiago Teofilo Luis Armando No. De Control: 15210344