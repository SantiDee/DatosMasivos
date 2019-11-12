// Se mandan a llamar las librerias del algoritmo 
import org.apache.spark.ml.linalg.{Vector, Vectors}
import org.apache.spark.ml.stat.Summarizer

// Se crear una variable llamada "data" 
val data = Seq(
   (Vectors.dense(2.0, 3.0, 5.0), 1.0),
   (Vectors.dense(4.0, 6.0, 7.0), 2.0)
)
// Se transforma la variable "data" en DataFrame
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