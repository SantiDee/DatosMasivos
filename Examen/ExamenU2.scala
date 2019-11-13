//Agregamos las lbrerias necesarias para trabajar con el algortimo Multilayer Perceptron.
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier 
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator 

//Del data set Iris.cvs, elaborar la limpieza de datos necesaria por medio de un scrip en scala spark, impportamos las librerias necesarias para la limpieza.
import org.apache.spark.sql.types._ 
import org.apache.spark.ml.Pipeline 
import org.apache.spark.ml.PipelineStage
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.feature.StringIndexer


val estructuraIris = StructType(StructField("Datos0", DoubleType, true) ::StructField("Datos1", DoubleType, true) ::StructField("Datos2", DoubleType, true) ::StructField("Datos3",DoubleType, true) ::StructField("Datos4", StringType, true) :: Nil)

//Cargamos el dataset "iris"
val datosIris = spark.read.option("header", "true").schema(estructuraIris)csv("iris.csv")

//vemos el esquema para comprobar que todos los valores estan calsificados correctamente en el datset
datosIris.show()  

val etiqueta = new StringIndexer().setInputCol("Datos4").setOutputCol("label")
val ensamblador = new VectorAssembler().setInputCols(Array("Datos0", "Datos1", "Datos2", "Datos3")).setOutputCol("features")

// Se declara la variable "splits" donde se dividen de forma aleatoria los datos de la variable "data"
val splits = datosIris.randomSplit(Array(0.6, 0.4), seed = 1234L)

// Se declara la variable "train" con ayuda de "splits" tendra el primer parametro que es el 60% de los datos cortados
val entrenar = splits(0)

// Se declara la variable "test" con ayuda de "splits" tendra el primer parametro que es el 40% de los datos cortados
val prueba = splits(1)

// Se especifican las capas de la red neuronal
// Capa de entrada de tamaño 3 (características), dos intermedios de tamaño 5 y 4 y salida de tamaño 3 (clases
val capasN = Array[Int](4, 5, 4, 3)

// Se declara el modelo y se agregan los parametros necesarios para su funcionamiento
val entrenador = new MultilayerPerceptronClassifier().setLayers(capasN).setBlockSize(128).setSeed(1234L).setMaxIter(100)
val pipeline = new Pipeline().setStages(Array(etiqueta,ensamblador,entrenador))

// Se entrena el modelo con los datos de entrenamiento
val modelo = pipeline.fit(entrenador)

// Se evalua y despliega resultados
val resultados = modelo.transform(prueba)
val predictionAndLabels = resultados.select("prediction", "label")
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")

// Se imprime el error de la precision 
println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")