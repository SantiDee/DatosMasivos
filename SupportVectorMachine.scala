 
//importamos Librerias A Utilizar Del Algortimo Support Vector Machine
import org.apache.spark.mllib.classification.{SVMModel, SVMWithSGD}
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.sql.types.DateType
import org.apache.spark.sql.{SparkSession, SQLContext}
import org.apache.spark.ml.classification.LinearSVC

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

//Variables Para El Maximo De Interaciones Que Realizara El Algoritmo
//Algoritmo De Support Vector Machine
val c1 = feat.withColumn("label",when(col("label").equalTo("1"),0).otherwise(col("label")))
val c2 = c1.withColumn("label",when(col("label").equalTo("2"),1).otherwise(col("label")))
val c3 = c2.withColumn("label",'label.cast("Int"))
val linsvc = new LinearSVC().setMaxIter(20).setRegParam(0.1)

// Entrenamos El Modelo 
val linsvcModel = linsvc.fit(c3)

//Imprimimos El Coeficiente De Intercepcion
println(s"Coefficients: ${linsvcModel.coefficients} Intercept: ${linsvcModel.intercept}")