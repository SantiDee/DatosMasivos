//importamos Librerias A Utilizar
import org.apache.spark.ml.feature.{VectorAssembler, StringIndexer, VectorIndexer, OneHotEncoder}
import org.apache.spark.mllib.evaluation.MulticlassMetrics
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.sql.types.DateType
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.Pipeline
import org.apache.log4j._

//Elimina varios avisos de warnings/errores inecesarios
Logger.getLogger("org").setLevel(Level.ERROR)

val spark = SparkSession.builder().getOrCreate()
//Creacion del dataframe para cargar el archivo csv
val data  = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("advertising.csv")
//Imprimimos el esquema del dataframe para visualizarlo
data.printSchema()
//Imprime la primera linea de datos del csv
data.head(1)
data.select("Clicked on Ad").show()
val timedata = data.withColumn("Hour",hour(data("Timestamp")))
//Tomamos nuestros datos mas relevantes a una variables y tomamos clicked on ad como nuestra label
val logregdataall = timedata.select(data("Clicked on Ad").as("label"),$"Daily Time Spent on Site",$"Age",$"Area Income",$"Daily Internet Usage",$"Hour",$"Male")
val feature_data = data.select($"Daily Time Spent on Site",$"Age",$"Area Income",$"Daily Internet Usage",$"Timestamp",$"Male")
val logregdataal = (data.withColumn("Hour",hour(data("Timestamp")))
val logregdataal = logregdataall.na.drop()

//Generamos nuestro vector de ensamble en un arrengo donde tomamos nuestros features
val assembler = new VectorAssembler().setInputCols(Array("Daily Time Spent on Site","Age","Area Income","Daily Internet Usage","Hour","Male")).setOutputCol("features")
//Utilizamos la regresion lineal en nuestros datos con un 70% y 30% de datos.
val Array(training, test) = logregdataall.randomSplit(Array(0.7, 0.3), seed = 12345)

val lr = new LogisticRegression()

val pipeline = new Pipeline().setStages(Array(assembler,lr))
//Creacion del modelo
val model = pipeline.fit(training)
//resultados de las pruebas con nuestro modelo
val results = model.transform(test)

val predictionAndLabels = results.select($"prediction",$"label").as[(Double, Double)].rdd
val metrics = new MulticlassMetrics(predictionAndLabels)

//Imprimimos nuestras metricas y la accuaricy de los calculos
println("Confusion matrix:")
println(metrics.confusionMatrix)
metrics.accuracy