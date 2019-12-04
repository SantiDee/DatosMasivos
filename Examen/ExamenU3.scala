//1. Importar una simple sesión Spark.
import org.apache.spark.sql.SparkSession 

//2. Utilice las lineas de código para minimizar errores.
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

//3. Cree una instancia de la sesión Spark.
val spark = SparkSession.builder().getOrCreate()

//4. Importar la librería de Kmeans para el algoritmo de agrupamiento.
import org.apache.spark.ml.clustering.KMeans

//5. Carga el dataset de Wholesale Customers Data.
val dataset = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("Wholesale customers data.csv")

//6. Seleccione las siguientes columnas: Fres, Milk, Grocery, Frozen, Detergents_Paper,Delicassen y llamar a este conjunto feature_data.
val feature_data = dataset.select($"Fresh", $"Milk", $"Grocery", $"Frozen", $"Detergents_Paper", $"Delicassen")

//7. Importar Vector Assembler y Vector.
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.feature.{VectorAssembler, StringIndexer}
import org.apache.spark.ml.linalg.Vectors

//9. Utilice el objeto assembler para transformar feature_data.
val assembler = new VectorAssembler().setInputCols(Array("Fresh", "Milk", "Grocery", "Frozen", "Detergents_Paper", "Delicassen")).setOutputCol("features")

//10.Crear un modelo Kmeans con K=3.
val kmeans = new KMeans().setK(3).setSeed(1L)
val model = kmeans.fit(traning)

//11.Evalúe los grupos utilizando.
val WSSSE = model.computeCost(traning)
println(s"Within Set Sum of Squared Errors = $WSSSE")

//12. ¿Cuáles son los nombres de las columnas?.
println("Cluster Centers: ")
model.clusterCenters.foreach(println)