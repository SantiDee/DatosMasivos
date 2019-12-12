// Se manda a llarmar a las librerias del algoritmo
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
// Se tranforma la variable "df" a dataframe 
val df = data.map(Tuple1.apply).toDF("features")

// Se declara la funcion de correlacion
val Row(coeff1: Matrix) = Correlation.corr(df, "features").head
println(s"Pearson correlation matrix:\n $coeff1")

// Se manda a imprimir la correlacion
val Row(coeff2: Matrix) = Correlation.corr(df, "features", "spearman").head
println(s"Spearman correlation matrix:\n $coeff2")