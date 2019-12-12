//Agregamos las lbrerias necesarias para trabajar con el algortimo Multilayer Perceptron.
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator

//Del data set Iris.cvs, elaborar la limpieza de datos necesaria por medio de un scrip en scala spark, impportamos las librerias necesarias para la limpieza.
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.feature.{VectorAssembler, StringIndexer}
import org.apache.spark.ml.linalg.Vectors

// Se cargan los datos del dataser iris.csv en la variable "data"
val data  = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("iris.csv")

//vemos el esquema para comprobar que todos los valores estan calsificados correctamente en el datset
data.printSchema()

// Se eliminan los campos null 
val dataClean = data.na.drop()

// Se declara un vector que se transforma los datos a la variable "features"
val vectorFeatures = (new VectorAssembler().setInputCols(Array("sepal_length","sepal_width", "petal_length","petal_width")).setOutputCol("features"))

// Se transforman los features usando el dataframe
val features = vectorFeatures.transform(dataClean)

// Se declara un "StringIndexer" que transformada los datos en "species" en datos numericos 
val speciesIndexer = new StringIndexer().setInputCol("species").setOutputCol("label")

// Ajustamos las especies indexadas con el vector features
val dataIndexed = speciesIndexer.fit(features).transform(features)

// Con la variable "splits" hacemos un corte de forma aleatoria
val splits = dataIndexed.randomSplit(Array(0.6, 0.4), seed = 1234L)
// Se declara la variable "train" la cual tendra el 60% de los datos
val train = splits(0)
// Se declara la variable "test" la cual tendra el 40% de los datos
val test = splits(1)

// Se establece la configuracion de las capas para el modelo de redes neuronales artificiales
val layers = Array[Int](4, 2, 2, 3)

// Se configura el entrenador del algoritmo Multilayer con sus respectivos parametros
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)

// Se entrena el modelo con los datos de entrenamiento
val model = trainer.fit(train)

// Se prueban ya entrenado el modelo
val result = model.transform(test)

// Se selecciona la prediccion y la etiqueta que seran guardado en la variable 
val predictionAndLabels = result.select("prediction", "label")
// Se muestran algunos datos 
predictionAndLabels.show()

// Se ejecuta la estimacion de la precision del modelo
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("label").setPredictionCol("prediction").setMetricName("accuracy")
val accuracy = evaluator.evaluate(predictionAndLabels)

// Se imprime el error del modelo
println(s"Test Error = ${(1.0 - accuracy)}")