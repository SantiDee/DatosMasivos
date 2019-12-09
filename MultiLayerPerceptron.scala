//importamos Librerias A Utilizar Del Algortimo De Multilayer Perceptron
import org.apache.spark.mllib.classification.{SVMModel, SVMWithSGD}
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.sql.types.DateType
import org.apache.spark.sql.{SparkSession, SQLContext}
import org.apache.spark.ml.classification.LinearSVC
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator

//Librerias para limpiar y transformar el dataset
import org.apache.spark.ml.feature.VectorIndexer
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.Transformer



//Elimina varios avisos de warnings/errores inecesarios
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

//Creamos una sesion de spark
import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().getOrCreate()

//Cargamos El Dataset En Un Dataframe
val df = spark.read.option("header","true").option("inferSchema","true").option("delimiter",";").format("csv").load("bank-full.csv")

//Imprimimo El Esquema Del Dataset Y Como Esta Conformado
df.printSchema()
df.show(1)

//Transformamos La Columna A Datos Binarios
val change1 = df.withColumn("y",when(col("y").equalTo("yes"),1).otherwise(col("y")))
val change2 = change1.withColumn("y",when(col("y").equalTo("no"),2).otherwise(col("y")))
val newcolumn = change2.withColumn("y",'y.cast("Int"))

//Imprimimos La Nueva Columna Binaria
newcolumn.show(1)

//Creamos El Features
val assembler = new VectorAssembler().setInputCols(Array("balance","day","duration","pdays","previous")).setOutputCol("features")
val fea = assembler.transform(newcolumn)

//Imprimimos El Features
fea.show(1)

//Modificamos La Columna Y A Label
val cambio = fea.withColumnRenamed("y", "label")
val feat = cambio.select("label","features")
feat.show(1)

//Dividimos Los Datos En Un Arreglo En Partes De 70% y 30%
val split = feat.randomSplit(Array(0.6, 0.4), seed = 1234L)
val train = split(0)
val test = split(1)

// Indicamos Las Capas De La Red Neuronal Que Deseas Implementar en Este Dataset
//5 Capas De Entrada Por El Tamaño Del Features, 2 capas Ocultas De 3 Neuronas y 4 De Salida
val layers = Array[Int](5, 3, 3, 4)

// Entrenamos El Modelo 
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)
val model = trainer.fit(train)

//Imprimimos El Modelo De Exactitud Del Algoritmo Multilayer Perceptron
val result = model.transform(test)
val predictionAndLabels = result.select("prediction", "label")
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")
println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")