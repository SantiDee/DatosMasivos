// pratica 1
//Desarrollar un algoritmo en scala que calcule el area de un circulo
//Obetenr circuferencia de un circulo en scala
Double Radio;// variable que guardara el valor del radio
Double Pi =3.1416;
Double Area=0;

//Mensaje para pedir el radio
println("Ingrese el radio del circulo que desea obtener su area")
Radio=Double = scala.io.StdIn.readline.toDouble// variable que guardara el valor del radio

//Area Del circulo

println ("El Area Del Circulo Es:" )
Area=Double=Pi*(Radio * Radio)


println(Area)

//2. Desarrollar un algoritmo que diga si un numero es primo o no
def Esprimo(i :Int) : Boolean = {
    if (i <= 1)
    false
    else if (i==2)
    true
    else
    !(2 to (i-1)).exists(x=> i % x==0)
}
//Se piede que ingrese el valor donde iniciara el limite inferor
println("Ingrese Un Numero:")
val numero1: Int = scala.io.StdIn.readline.toInt
//Se pide que ingrese el segundo valor que sera el limite superior
println("Ingrese el segundo valor:")
val numero2: Int = scala.io.StdIn.readline.toInt

(numero1 to numero2).foreach(i=> if(Esprimo2(i))println("%d Es Primo.".format(i)))


//3. Dada la variable bird = "tweet", utiliza interpolacion de string para imprimir "Estoy escribiendo un tweet"
var bird = "tweet"
string conexion "Estoy Escribiendo Un Tweet"
println("Estoy Escibiendo Un %s", bird)

//4. Dada la variable mensaje = "hola luke yo soy tu padre!" utiliza slice para extraer la secuencia "luke"
var variable = "Hola Luke Soy Tu Padre!"
variable.slice(5,9)

//5. Cual es la diferencia en value y una variable en scala?
//Respuesta :value(val) se le asigna un valor definido y no puede ser cambiado, en una variable

//6. Dada la tupla (2,4,5,1,2,3,3.1416,23) regresa el numero 3.1416
var x = (2,4,5,1,2,3,3.1416,23)
println(x,7)