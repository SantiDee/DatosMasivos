//Se agregan las librerias del algortimo
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.{GBTClassificationModel, GBTClassifier}
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}

// Se carga los datos en la variable "data" en formato "libsvm"
val data = spark.read.format("libsvm").load("data/mllib/sample_libsvm_data.txt")

// Se agrega una nueva columna "IndexLabel" que tendra todos los datos de la columna "label"
val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(data)

// Se agrega una nueva columna "indexedFeatures" que tendra todos los datos de la columna "features"
val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4).fit(data)

// Se declaran dos arreglos; "trainingData" y "testData" de los cuales tendran 70% y 30% de los datos que fueron declarados en la variable "data"
val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3))

// Se declara el modelo y se agregan como parametros "indexedLabel" y "indexedFeatures", que son las etiquetas de cada clase
// y las caracteristicas de esa clase
val gbt = new GBTClassifier().setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures").setMaxIter(10).setFeatureSubsetStrategy("auto")

// Se convierten las "indexedLabel" a las etiquetas originales
val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.labels)

// Se declara el objeto "pipeline" en donde nos ayudara a pasar el codigo por estados, estos mismos estan declarados despues de "Array"
val pipeline = new Pipeline().setStages(Array(labelIndexer, featureIndexer, gbt, labelConverter))

// Se entrena el modelo con los datos de entrenamiento
val model = pipeline.fit(trainingData)

// Se hacen las predicciones con el modelos ya entrenado y con los datos de prueba que representan el 30%
val predictions = model.transform(testData)

// Se mandan a imprimir o se seleccionan algunas columnas y se muestran solo las primerias 5
predictions.select("predictedLabel", "label", "features").show(5)

// Se evalua la precision y se agrega a una variable "accuracy"
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy")
val accuracy = evaluator.evaluate(predictions)
// Se manda a imprimir el error de precision del modelo
println(s"Test Error = ${1.0 - accuracy}")
// Se manda a imprimir el arbol por medio de condicionales "if and else"
val gbtModel = model.stages(2).asInstanceOf[GBTClassificationModel]
println(s"Learned classification GBT model:\n ${gbtModel.toDebugString}")