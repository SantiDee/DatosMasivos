**INSTITUTO TECNOLOGICO DE TIJUANA.**

**SUBDIRECCIÓN ACADÉMICA.**

**DEPARTAMENTO DE SISTEMAS COMPUTACIONALES.**

**SEMESTRE AGOSTO-DICIEMBRE 2019.**

**Carrera: Ing. Tics**

**Materia: Datos Masivos**

**Serie: BDD-1704 TI9A.**

**Título: Examen**

**Unidad: 2**

**Nombre del alumno: Luis Armando Santiago Teófilo.**

**Número de control: 15210344**

**Nombre del maestro:Jose Christian Romero Hernandez.**



## Introducción
se resolvio el siguiente examen utilizando el algoritmo Multilayer Percentron de Machine Learning, en el lenguaje scala 
en su versión 2.4.4 y una versión de java 8 (1.8), para la correcta utilización de las librerías necesarias para el algoritmo.

## Explicación
En el examen resuelto a continuación se presentó la problemática de utilizar el algoritmo de Machine Learning llamado
Multilayer Perceptron para clasificar un dataset en el lenguaje de programación scala, el cual es iris.csv, donde se pide 
primero limpiar el dataset por que scala spark clasifica la última columna como string y no como variable numérica, 
para poder utilizarlo con el algoritmo, en el código siguiente se presenta la solución al examen presentado con un valor
de agrupación correctamente del 92%. 

```scala
Algoritmos Multilayer Perceptron Documentado.
//Agregamos las librerías necesarias para trabajar con el algoritmo Multilayer Perceptron.
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
 
//Del data set Iris.cvs, elaborar la limpieza de datos necesaria por medio de un script en scala spark, importamos las librerias necesarias para la limpieza.
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.feature.{VectorAssembler, StringIndexer}
import org.apache.spark.ml.linalg.Vectors
 
// Se cargan los datos del dataset iris.csv en la variable "data"
val data  = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("iris.csv")
 
//vemos el esquema para comprobar que todos los valores están clasificados correctamente en el dataset
data.printSchema()
 
// Se eliminan los campos null
val dataClean = data.na.drop()
 
// Se declara un vector que se transforma los datos a la variable "features"
val vectorFeatures = (new VectorAssembler().setInputCols(Array("sepal_length","sepal_width", "petal_length","petal_width")).setOutputCol("features"))
 
// Se transforman los features usando el dataframe
val features = vectorFeatures.transform(dataClean)
 
// Se declara un "StringIndexer" que transformada los datos en "species" en datos numéricos
val speciesIndexer = new StringIndexer().setInputCol("species").setOutputCol("label")
 
// Ajustamos las especies indexadas con el vector features
val dataIndexed = speciesIndexer.fit(features).transform(features)
 
// Con la variable "splits" hacemos un corte de forma aleatoria
val splits = dataIndexed.randomSplit(Array(0.6, 0.4), seed = 1234L)
// Se declara la variable "train" la cual tendrá el 60% de los datos
val train = splits(0)
// Se declara la variable "test" la cual tendrá el 40% de los datos
val test = splits(1)
 
// Se establece la configuración de las capas para el modelo de redes neuronales artificiales
val layers = Array[Int](4, 2, 2, 3)
 
// Se configura el entrenador del algoritmo Multilayer con sus respectivos parámetros
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)
 
// Se entrena el modelo con los datos de entrenamiento
val model = trainer.fit(train)
 
// Se prueban ya entrenado el modelo
val result = model.transform(test)
 
// Se selecciona la predicción y la etiqueta que serán guardado en la variable
val predictionAndLabels = result.select("prediction", "label")
// Se muestran algunos datos
predictionAndLabels.show()
 
// Se ejecuta la estimación de la precisión del modelo
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("label").setPredictionCol("prediction").setMetricName("accuracy")
val accuracy = evaluator.evaluate(predictionAndLabels)
 
// Se imprime el error del modelo
println(s"Test Error = ${(1.0 - accuracy)}")
```
