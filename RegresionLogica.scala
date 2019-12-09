//importamos Librerias A Utilizar Del Algortimo De Regresion Logica
import org.apache.spark.mllib.classification.{SVMModel, SVMWithSGD}
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.ml.classification.LinearSVC
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.sql.types.DateType
import org.apache.spark.sql.{SparkSession, SQLContext}

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

//Variable Para El Maximo De Interaciones Que Realizara El Algoritmo
val logistic = new LogisticRegression().setMaxIter(20).setRegParam(0.3).setElasticNetParam(0.8)

// Entrenamos El Modelo 
val logisticModel = logistic.fit(feat)

//Imprimimos Los Coeficientes Y La Intercepcion
val logisticMult = new LogisticRegression().setMaxIter(10).setRegParam(0.3).setElasticNetParam(0.8).setFamily("multinomial")
val logisticMultModel = logisticMult.fit(feat)
println(s"Multinomial coefficients: ${logisticMultModel.coefficientMatrix}")
println(s"Multinomial intercepts: ${logisticMultModel.interceptVector}")