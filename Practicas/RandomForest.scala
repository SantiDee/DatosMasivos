//Se agregan las librerias del algortimo
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.{RandomForestClassificationModel, RandomForestClassifier}
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}

// Se carga y se convierte en un DataFrame.
val data = spark.read.format("libsvm").load("./sample_libsvm_data.txt")data.show()

// Se ajusta a todo el conjunto de datos para incluir todas las etiquetas en el índice.
val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(data)

// Se establecen el maxCategories para que las entidades con > 4 valores distintos se traten como continuas.
val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4).fit(data)

// Divide los datos en conjuntos de entrenamiento y prueba (30% para pruebas).
val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3))

// Entrena un modelo RandomForest.
val rf = new RandomForestClassifier().setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures").setNumTrees(10)

// Convierte las etiquetas indexadas de nuevo a etiquetas originales.
val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.labels)

// Indicadores de cadena y bosque en una tubería.
val pipeline = new Pipeline().setStages(Array(labelIndexer, featureIndexer, rf, labelConverter))

// Modelo de tren. Esto también ejecuta los indexadores.
val model = pipeline.fit(trainingData)

// Hacer predicciones.
val predictions = model.transform(testData)

// Seleccione filas de ejemplo para mostrar.
predictions.select("predictedLabel", "label", "features").show(5)

// Seleccione (predicción, etiqueta verdadera) y calcule el error de prueba.
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy")
val accuracy = evaluator.evaluate(predictions)
println(s"Test Error = ${(1.0 - accuracy)}")

val rfModel = model.stages(2).asInstanceOf[RandomForestClassificationModel]
println(s"Learned classification forest model:\n ${rfModel.toDebugString}")