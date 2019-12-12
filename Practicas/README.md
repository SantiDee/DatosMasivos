**INSTITUTO TECNOLOGICO DE TIJUANA.**

**SUBDIRECCIÓN ACADÉMICA.**

**DEPARTAMENTO DE SISTEMAS COMPUTACIONALES.**

**SEMESTRE AGOSTO-DICIEMBRE 2019.**

**Carrera: Ing. Tics**

**Materia: Datos Masivos**

**Serie: BDD-1704 TI9A.**

**Título: Practicas**

**Unidad: 2**

**Nombre del alumno: Luis Armando Santiago Teófilo.**

**Número de control: 15210344**

**Nombre del maestro: Jose Christian Romero Hernandez.**


## Introducción
Las prácticas a continuación mostradas se llevaron a cabo en la materia de datos masivos, 
parte de la exposición 1 que abarca la unidad 2.

## Algoritmos

###### Correlation de P
El coeficiente de correlación de Pearson es una prueba que mide la relación estadística entre dos variables continuas. 
Si la asociación entre los elementos no es lineal, entonces el coeficiente no se encuentra representado adecuadamente.
El coeficiente de correlación de Pearson puede tomar un rango de valores de +1 a -1. Un valor de 0 indica que no hay
asociación entre las dos variables. Un valor mayor que 0 indica una asociación positiva. Es decir, a medida que aumenta
el valor de una variable, también lo hace el valor de la otra. Un valor menor que 0 indica una asociación negativa; 
es decir, a medida que aumenta el valor de una variable, el valor de la otra disminuye.

```scala
código en scala correlación de p.
// Se manda a llamar a las librerías del algoritmo
import org.apache.spark.ml.linalg.{Matrix, Vectors}
import org.apache.spark.ml.stat.Correlation
import org.apache.spark.sql.Row
 
// Se declara la variable "df"
val data = Seq(
Vectors.sparse(4, Seq((0, 1.0), (3, -2.0))),
Vectors.dense(4.0, 5.0, 0.0, 3.0),
Vectors.dense(6.0, 7.0, 0.0, 8.0),
Vectors.sparse(4, Seq((0, 9.0), (3, 1.0)))
)
// Se transforma la variable "df" a data frame
val df = data.map(Tuple1.apply).toDF("features")
 
// Se declara la funcion de correlacion
val Row(coeff1: Matrix) = Correlation.corr(df, "features").head
println(s"Pearson correlation matrix:\n $coeff1")
 
// Se manda a imprimir la correlación
val Row(coeff2: Matrix) = Correlation.corr(df, "features", "spearman").head
println(s"Spearman correlation matrix:\n $coeff2")

Hypothesis testing
 
// Se mandan a llamar las librerias del algoritmo
import org.apache.spark.ml.linalg.{Vector, Vectors}
import org.apache.spark.ml.stat.ChiSquareTest
 
// Se declaran en la variable "data"
 
val data = Seq(
(0.0, Vectors.dense(0.5, 10.0)),
(0.0, Vectors.dense(1.5, 20.0)),
(1.0, Vectors.dense(1.5, 30.0)),
(0.0, Vectors.dense(3.5, 30.0)),
(0.0, Vectors.dense(3.5, 40.0)),
(1.0, Vectors.dense(3.5, 40.0))
)
 
// Se transforma la variable "data" a data frame "df"
val df = data.toDF("label", "features")
 
// Se declara el modelo y se agregan los parámetros
val chi = ChiSquareTest.test(df, "features", "label").head
println(s"pValues = ${chi.getAs[Vector](0)}")
println(s"degreesOfFreedom ${chi.getSeq[Int](1).mkString("[", ",", "]")}")
println(s"statistics ${chi.getAs[Vector](2)}")

Summarizer
// Se mandan a llamar las librerías del algoritmo
import org.apache.spark.ml.linalg.{Vector, Vectors}
import org.apache.spark.ml.stat.Summarizer
 
// Se crear una variable llamada "data"
val data = Seq(
  (Vectors.dense(2.0, 3.0, 5.0), 1.0),
  (Vectors.dense(4.0, 6.0, 7.0), 2.0)
)
// Se transforma la variable "data" en Data Frame
val df = data.toDF("features", "weight")
df.show()
 
//Creamos una sumatoria a partir del DataFrame
val (meanVal, varianceVal) = df.select(Summarizer.metrics("mean", "variance").summary($"features", $"weight").as("summary")).select("summary.mean", "summary.variance").as[(Vector, Vector)].first()
 
// Se imprime el resultado
println(s"with weight: mean = ${meanVal}, variance = ${varianceVal}")
 
//Creamos una sumatoria
val (meanVal2, varianceVal2) = df.select(Summarizer.mean($"features"),Summarizer.variance($"features")).as[(Vector, Vector)].first()
 
// Se imprime el resultado
println(s"without weight: mean = ${meanVal2}, sum = ${varianceVal2}")

```
