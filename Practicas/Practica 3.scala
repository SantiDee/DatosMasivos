// Primer Algoritmo
// En este primer algortimo se agrego una funcion que despues de haber efectuado
// las operaciones correspondientes la funcion nos dara un resultado (return)
// este debe de ser un valor entero (Int)
def funcion (n: Int): Int = 
{   
// Si el numero ingresado en la funcion es menor a 2, el numero ingresado sera regresado
    if (n<2)
    {
        return n
    }
// En todo caso, la funcion hara una serie de sumas, y regresa el resultado
    else
    {
        return funcion(n-1) + funcion(n-2)
    }
}

funcion(9)

// Segundo Algoritmo
// En este segundo algortimo se agrego una funcion que despues de haber efectuado
// las operaciones correspondientes la funcion nos dara un resultado (return)
// este debe de ser un valor entero con puntos decimales (Double)
def funcion1(n: Double): Double =
{
//Si el numero ingresado en la funcion es menor a 2, el numero ingresado sera regresado
    if (n<2)
    {
        return n
    }
// En todo caso, se hara lo siguiente
    else
    {
// La formula matematica es mas extensa, pero yo decidi hacerla en partes paqueñas
// para luego unirla en la variable (j) 
        var p = ((1+(Math.sqrt(5)))/2)
        var a = Math.pow(p,n)
        var b = Math.pow((1-p),n)
        var c = Math.sqrt(5)
        var j = ((a-(b)))/(c)
// EL resultado de (j) sera el resultado al regresar (return)
        return(j)
    }

}
funcion1(9)

// Tercer Algoritmo

// En este tercer algortimo se agrego una funcion que despues de haber efectuado
// las operaciones correspondientes la funcion nos dara un resultado (return)
// este debe de ser un valor entero (Int)
def funcion2(n: Int): Int =
{
var a = 0
var b = 1
var c = 0
// Se inicia un ciclo (for) donde k = 1, empezara a ciclar hasta llegar a ser (n)
// (n) representa el valor que se ingresara en la funcion 
    for (k <- 1 to n)
    {
// En funcion al ciclo (for) las variables (c,a,b) empezaran a cambiar su resultado
// hasta haber terminado el ciclo (for)
        c = b + a
        a = b
        b = c
    }
// El resultado sera retornado con (return)
    return(a)
}
funcion2(9)

// Cuarto Algoritmo
// En este cuarto algortimo se agrego una funcion que despues de haber efectuado
// las operaciones correspondientes la funcion nos dara un resultado (return)
// este debe de ser un valor entero (Int)
def funcion3(n: Int): Int =
{
    var a = 0
    var b = 1
// Se inicia un ciclo (for) donde k = 1, empezara a ciclar hasta llegar a ser (n)
// (n) representa el valor que se ingresara en la funcion 
    for(k <- 1 to n)
        {

            b = b + a
            a = b - a
// En funcion al ciclo (for) las variables (b,a) empezaran a cambiar su resultado
// hasta haber terminado el ciclo (for)
        }
// El resultado sera retornado con (return)
        return(a)
}
funcion3(9)

// Quinto Algoritmo
// En este quinto algoritmo se realiza una funcion que pide un valor entero (Int)
// para luego retornar un valor entero con decimales(Double) 
def funcion4(n: Int): Double =
{
// Se crea un arreglo que empieza de 0 hasta (n+1)
    val vector = Array.range(0,n+1)
// Si la variable (n) es menor a 2, se regresa esa misma variable como resultado
    if (n < 2)
    {
        return (n)
    }
// En caso contrario el vector con espacio (0) tendra un valor de cero(0)
// y el vector con espacio (1) tendra valor de uno(1)
    else
    {
        vector(0) = 0
        vector(1) = 1
// Se empieza a ciclar con un for el vector
        for (k <- 2 to n)
        {
            vector(k) = vector(k-1) + vector(k-2)
        } 
// El resultado sera la variable (n) en funcion al vector establecido
        return vector(n)
    }
}
funcion4(9)

// Sexto Algoritmo
// En este sexto algortimo se agrego una funcion que despues de haber efectuado
// las operaciones correspondientes la funcion nos dara un resultado (return)
// este debe de ser un valor entero con puntos decimales (Double)
def funcion5 (n: Double): Double = 
{
// Si el valor ingresado es menor o igual a 0, entonces ese valor se vera regresado
    if (n<=0)
    {
        return (n)
    }
// En caso contrario se tendra que hacer una serie se operaciones
    else
    {
        var i: Double = n - 1
        var auxOne: Double = 0
        var auxTwo: Double  = 1 
        var a: Double  = auxTwo
        var b: Double = auxOne
        var c: Double  = auxOne
        var d: Double  = auxTwo
// Empezara un ciclo (while) donde las variables empezaran a cambiar de valor 
// en funcion a la iteracion del ciclo
        while (i > 0)
        {
// Si la variable (i) es impar, se haran operaciones diferentes 
            if (i % 2 == 1)
            {
                auxOne = (d*b) + (c*a)
                auxTwo = ((d+(b*a)) + (c*b))
                a = auxOne
                b = auxTwo
            }
// Si la variable (i) es par, se haran operaciones diferentes 
            else
            {
                var pow1 = Math.pow(c,2)
                var pow2 = Math.pow(d,2)
                auxOne = pow1 + pow2
                auxTwo = (d*((2*(c)) + d))
                c = auxOne
                d = auxTwo
            }
// La variable (i) empezara a cambiar de valor cada vez que se itere el ciclo
// hasta que salga del ciclo, y se regrese la suma de (a+b)
            i = (i / 2)   
        }
        return(a+b)
    }
}
funcion5(9)