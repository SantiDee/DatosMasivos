INSTITUTO TECNOLOGICO DE TIJUANA.

SUBDIRECCIÓN ACADÉMICA.

DEPARTAMENTO DE SISTEMAS COMPUTACIONALES.

SEMESTRE AGOSTO-DICIEMBRE 2019.

Carrera: Ing. Tics

Materia: Datos Masivos

Serie: BDD-1704 TI9A.

Título: Practica 1

Unidad: 3

Nombre del alumno: Luis Armando Santiago Teófilo.

Número de control: 15210344

Nombre del maestro:Jose Christian Romero Hernandez.

Introducción

La siguiente practica se realizo gracias a la explicación del algoritmo de regresión logística visto en la clase

de datos masivos, realizado en el lenguaje scala con spark, se deja el código en scala y el código en github.

Código

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
 
//Iniciamos sesion en spark

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

val logregdataall = timedata.select(data("Clicked on Ad").as("label"),$"Daily Time Spent on Site",$"Age",$"Area 

Income",$"Daily Internet Usage",$"Hour",$"Male")

val feature_data = data.select($"Daily Time Spent on Site",$"Age",$"Area Income",$"Daily Internet
Usage",$"Timestamp",$"Male")

val logregdataal = (data.withColumn("Hour",hour(data("Timestamp")))

val logregdataal = logregdataall.na.drop()
 
//Generamos nuestro vector de ensamble en un arrengo donde tomamos nuestros features

val assembler = new VectorAssembler().setInputCols(Array("Daily Time Spent on Site","Age","Area Income","Daily Internet
Usage","Hour","Male")).setOutputCol("features")
 
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


Introducción
La siguiente practica se realizo gracias a la explicación del algoritmo de K-means visto en la clase de datos masivos,
realizado en el lenguaje scala con spark, se deja el código en scala y el código en github.

K-means
K-medias es un método de agrupamiento, que tiene como objetivo la partición de un conjunto de n observaciones en k grupos
en el que cada observación pertenece al grupo cuyo valor medio es más cercano. Es un método utilizado en minería de datos.
La agrupación del conjunto de datos puede ilustrarse en una partición del espacio de datos en celdas de Voronoi.
El problema es computacionalmente difícil (NP-hard). Sin embargo, hay eficientes heurísticas que se emplean comúnmente y 
convergen rápidamente a un óptimo local. Estos suelen ser similares a los algoritmos expectation-maximization de mezclas 
de distribuciones gaussianas por medio de un enfoque de refinamiento iterativo empleado por ambos algoritmos. Además,
los dos algoritmos usan los centros que los grupos utilizan para modelar los datos, sin embargo k-medias tiende a encontrar
grupos de extensión espacial comparable, mientras que el mecanismo expectation-maximization permite que los grupos tengan
formas diferentes.

Código
// Se crea un sesion en Spark
import org.apache.spark.sql.SparkSession
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)
val spark = SparkSession.builder().getOrCreate()
import org.apache.spark.ml.clustering.KMeans
 
// Se cargan los datos en la variable "dataset"
val dataset = spark.read.format("libsvm").load("sample_kmeans_data.txt")
 
// Se entra el modelo de K means y a la vez se especifica el valor de K
val kmeans = new KMeans().setK(2).setSeed(1L)
// Se entrena el modelo con los datos de la variable "dataset"
val model = kmeans.fit(dataset)
 
// Se evalua el cluster calculando en los errores cuadraticos
val WSSE = model.computeCost(dataset)
println(s"Within set sum of Squared Errors = $WSSE")
 
// Se muestran el movimiento de los centroides
println("Cluster Centers: ")
model.clusterCenters.foreach(println)
