//Se agregan las librerias del algortimo
import org.apache.spark.ml.classification.NaiveBayes
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator

// Cargar los datos almacenados en formato LIBSVM como un DataFrame.
val data = spark.read.format("libsvm").load("data/mllib/sample_libsvm_data.txt")

// Dividir los datos en conjuntos de entrenamiento y prueba (30% para pruebas)
val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3), seed = 1234L)

// Entrena un modelo NaiveBayes.
val model = new NaiveBayes().fit(trainingData)

// Seleccione filas de ejemplo para mostrar.
val predictions = model.transform(testData)
predictions.show()

// Seleccionar (predicción, etiqueta verdadera) y calcular error de prueba
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("label").setPredictionCol("prediction").setMetricName("accuracy")
val accuracy = evaluator.evaluate(predictions)
println(s"Test set accuracy = $accuracy")