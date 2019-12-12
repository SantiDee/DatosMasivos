// Se importan todas la librerias necesarias
import org.apache.spark.ml.classification.{LogisticRegression, OneVsRest}
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator

//Se agregan las librerias del algortimo
val inputData = spark.read.format("libsvm").load("data/mllib/sample_multiclass_classification_data.txt")

// Se declararan 2 arreglos, uno tendra los datos de entrenamiento y el otro tendra
// los datos de prueba, respectivamente fueron declarados como arreglos y tendran el 80 y 20 porciento de los datos totales
val Array(train, test) = inputData.randomSplit(Array(0.8, 0.2))

// Se declara la variable "classifier" que hara la regresion
val classifier = new LogisticRegression().setMaxIter(10).setTol(1E-6).setFitIntercept(true)

// Se declara el modelo "OneVsRest"
val ovr = new OneVsRest().setClassifier(classifier)

// Se entrena el modelo con los datos de entrenamiento
val ovrModel = ovr.fit(train)

// Se hacen las predicciones con los datos de prueba
val predictions = ovrModel.transform(test)

// Se declara el evaluador que tomara la precision del modelo y lo guardara en una variable metrica llamada "accuracy"
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")

// Se calcula el error del modelo con una simple resta
val accuracy = evaluator.evaluate(predictions)
println(s"Test Error = ${1 - accuracy}")