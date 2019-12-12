//Se agregan las librerias del algortimo
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator

// Se cargan los datos en la variable "data" en un formato "libsvm"
val data = spark.read.format("libsvm").load("data/mllib/sample_multiclass_classification_data.txt")

// Se declara un variable llamda "splits" donde se hacen los cortes de forma aleatoria de los datos de la variable "data"
val splits = data.randomSplit(Array(0.6, 0.4), seed = 1234L)
// Se declara la variable "train" donde con ayuda de "splits" tendra el primer parametro que es el 60% de los datos cortados
val train = splits(0)
// Se declara la variable "test" donde con ayuda de "splits" tendra el primer parametro que es el 40% de los datos cortados
val test = splits(1)

// Se especifican las capas de la red neuronal
// Capa de entrada de tamaño 4 (características), dos intermedios de tamaño 5 y 4 y salida de tamaño 3 (clases)
val layers = Array[Int](4, 5, 4, 3)

// Se declara el modelo y se agregan los parametros necesarios para su funcionamiento
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)

// Se entrena el modelo con los datos de entrenamiento
val model = trainer.fit(train)

// Se evalua y despliega resultados
val result = model.transform(test)
val predictionAndLabels = result.select("prediction", "label")
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")

// Se imprime el error de la precision 
println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")