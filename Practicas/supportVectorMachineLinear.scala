// importamos la libreria BAM!!! 
import org.apache.spark.ml.classification.LinearSVC

// Carga los datos de entrenamiento de la base de datos de prueba
val training = spark.read.format("libsvm").load("data/mllib/sample_libsvm_data.txt")

//establede el maximo de interaciones y establecemos una region de parametros de 0.1
val lsvc = new LinearSVC()
  .setMaxIter(10)
  .setRegParam(0.1)

// Ajusta el modelo  a los datos de entrenamiento
val lsvcModel = lsvc.fit(training)

//  Imprime los coeficientes e interceta los valores para Linear SVM, maximixa la distancia entre los valores.
println(s"Coefficients: ${lsvcModel.coefficients} Intercept: ${lsvcModel.intercept}")

println("")
// Imprime un Super BAM!!!
println("SUPER BAM!!!")