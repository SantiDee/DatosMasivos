//Libreria spark
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.clustering.KMeans
import org.apache.spark.ml.feature.{VectorAssembler,StringIndexer,VectorIndexer,OneHotEncoder}
import org.apache.spark.ml.linalg.Vectors
import org.apache.log4j._

//Cara que no marque errores 
Logger.getLogger("org").setLevel(Level.ERROR)

// Cargamos el csv 
val spark = SparkSession.builder().getOrCreate()
val dataset = spark.read.option("header","true").option("inferSchema","true").csv("Wholesale customers data.csv")

//Selecionamos las columnas que vamos a entrenar
val feature_data = dataset.select($"Fresh", $"Milk", $"Grocery", $"Frozen", $"Detergents_Paper", $"Delicassen")
val assembler = new VectorAssembler().setInputCols(Array("Fresh", "Milk", "Grocery", "Frozen", "Detergents_Paper", "Delicassen")).setOutputCol("features")

//Entrenamos la data que colocamos en el vectorassembler
val traning = assembler.transform(feature_data)
//La data entrenada la pasamos al modelo 
val kmeans = new KMeans().setK(3).setSeed(1L)
val model = kmeans.fit(traning)
// Evaluamos el cluster calculando en los errores cuadraticos
val WSSSE = model.computeCost(traning)
println(s"Within Set Sum of Squared Errors = $WSSSE")
//Resultado
println("Cluster Centers: ")
model.clusterCenters.foreach(println)