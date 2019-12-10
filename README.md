TECNOLÓGICO​ ​NACIONAL​ ​DE​ ​MÉXICO

INSTITUTO TECNOLÓGICO DE TIJUANA

SUBDIRECCIÓN ACADÉMICA

DEPARTAMENTO DE SISTEMAS Y COMPUTACIÓN

SEMESTRE: 

Agosto - Diciembre 2019

CARRERA: 

ING EN TECNOLOGÍAS DE LA INFORMACIÓN Y COMUNICACIONES

MATERIA:

Datos Masivos

Proyecto Final

Comparación De Rendimiento De Algoritmos De Machine Learning

NOMBRE Y NÚMERO DE CONTROL DEL ALUMNO:

Santiago Teofilo Luis Armando # 15210344

NOMBRE DEL MAESTRO (A):

Jose Christian Romero Hernandez

Índice
Introducción……………………………………………………………………………………………………….3
Marco Teórico……………………………………………………………………………………………..3.6
Implementación………………………………………………………………………………………………..7
Resultados……………………………………………………………………………………………………….8.9
Conclusión…………………………………………………………………………………………………………..9
Referencias……………………………………………………………………………………………………..10
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
Introducción.
En el siguiente proyecto se pretende analizar y ver la eficiencia de 3 algoritmos de machine learning, los algoritmos son Naive Bayes, Support Vector Machine y Logistic Regression, esto para obtener resultados de los 3 y optar por cuál de los 3 es más rápido, más preciso y cual de los algoritmos de machine learning clasifica mejor el dataset a trabajar que es bank marketing proporcionado por UCI Machine Learning Repository, esto como parte del proyecto final para la clase de datos masivos impartida por el Dr.José Christian Romero Hernandez docente del Instituto Tecnológico Nacional De Tijuana.
 
Marco Teórico.
Multilayer Perceptron.
En el perceptrón multicapa, puede haber más de una capa lineal (combinaciones de neuronas ).
En un sistema de clasificación supervisado, cada vector de entrada está asociado con una etiqueta, o verdad fundamental, que define su clase o etiqueta de clase con los datos. La salida de la red proporciona un puntaje de clase, o predicción, para cada entrada. Para medir el rendimiento del clasificador, se define la función de pérdida. La pérdida será alta si la clase predicha no corresponde a la clase verdadera, de lo contrario será baja. A veces, el problema de sobreajuste y falta de ajuste se produce en el momento de entrenar al modelo. En este caso, nuestro modelo funciona muy bien en los datos de entrenamiento pero no en los datos de prueba. Para entrenar la red, se requiere un procedimiento de optimización para esto necesitamos una función de pérdida y un optimizador. Este procedimiento encontrará los valores para el conjunto de pesos, W, que minimiza la función de pérdida.
Una estrategia popular es inicializar los pesos a valores aleatorios y refinarlos iterativamente para obtener una pérdida menor. Este refinamiento se logra moviéndose en la dirección definida por el gradiente de la función de pérdida. Y es importante establecer una tasa de aprendizaje que defina la cantidad en que se mueve el algoritmo en cada iteración.

Imagen 1: Exploring Multilayer Perceptron classifier.
 
Support Vector Machine.
Una máquina de vectores de soporte (SVM) es un clasificador discriminativo definido formalmente por un hiperplano de separación. En otras palabras, dados los datos de entrenamiento etiquetados ( aprendizaje supervisado ), el algoritmo genera un hiperplano óptimo que categoriza nuevos ejemplos. En dos espacios dimensionales, este hiperplano es una línea que divide un plano en dos partes donde en cada clase se encuentra a cada lado.Estos métodos están propiamente relacionados con problemas de clasificación y regresión. Dado un conjunto de ejemplos de entrenamiento (de muestras) podemos etiquetar las clases y entrenar una SVM para construir un modelo que prediga la clase de una nueva muestra. Intuitivamente, una SVM es un modelo que representa a los puntos de muestra en el espacio, separando las clases a 2 espacios lo más amplios posibles mediante un hiperplano de separación definido como el vector entre los 2 puntos, de las 2 clases, más cercanos al que se llama vector soporte. Cuando las nuevas muestras se ponen en correspondencia con dicho modelo, en función de los espacios a los que pertenezcan, pueden ser clasificadas a una o la otra clase.
 
Más formalmente, una SVM construye un hiperplano o conjunto de hiperplanos en un espacio de dimensionalidad muy alta (o incluso infinita) que puede ser utilizado en problemas de clasificación o regresión. Una buena separación entre las clases permitirá una clasificación correcta.(2. Patel, 2017, Articulo Web)

Imagen 2: Support Vector Machines for Classification
 
Logistic Regression.
La regresión logística es un algoritmo de clasificación de Machine Learning que se utiliza para predecir la probabilidad de una variable dependiente categórica. En la regresión logística, la variable dependiente es una variable binaria que contiene datos codificados como 1 (sí, éxito, etc.) o 0 (no, falla, etc.). En otras palabras, el modelo de regresión logística predice P (Y = 1) en función de X.
La regresión logística es una de las formas más populares de ajustar modelos para datos categóricos, especialmente para datos de respuesta binaria en modelado de datos. Es el miembro más importante (y probablemente el más utilizado) de una clase de modelos llamados modelos lineales generalizados. A diferencia de la regresión lineal, la regresión logística puede predecir directamente las probabilidades (valores que están restringidos al intervalo (0,1);Los coeficientes del modelo también proporcionan una pista de la importancia relativa de cada variable de entrada.
La regresión logística se utiliza cuando la variable dependiente (objetivo) es categórica.(3. Brid 2018, Articulo Web).

Imagen 3: Regresión Lógica.
Implementación.
para la realización de las pruebas de los algoritmos de machine learning se utilizaron las herramientas; interpretador de lenguajes se utilizó el software de visual studio code, como lenguaje de programación se utilizó scala por ser un lenguaje no interpretado, donde se puede expresar patrones comunes de programación, como características principales de scala resalta que es un lenguaje de programación orientado a objetos y trabaja de forma binaria lo que hace que los algoritmos de machine learning trabajen en óptimas condiciones de rapidez y precisión, todo esto con la ayuda del servicio de spark de apache por ser uno de los frameworks que puede utilizar MapReduce y corre en java virtual machine.(4. Scala,2018. 5. Bigeek, 2017.)
 

Imagen 4: Apache spark + Scala.
 
Resultados.
A continuación se presentan los resultados obtenidos de los algoritmos de machine learning, en forma de tabla, se tomó en cuenta datos de trabajo (Dataset Bank-full.csv), tiempo de procesamiento de los datos, tiempo de respuesta del algoritmo y exactitud en cuanto a los resultados de los mismos.
 
Machine Learning 
Dataset
Tiempo De Procesamiento
Repeticiones
Resultados
Multilayer Perceptron
Bank-Full.csv
28 Segundos
20 
0.885148840524094
Support Vector Machine
Bank-Full.csv
30 Segundos
20 
1.0656723967997292
Logistic Regression
Bank-Full.csv
24 Segundos
20 
-7.827431230413901,2.903059294046486,4.924371936367415]
 
Algoritmos De Machine Learning
Multilayer Perceptron.

Support Vector Machine.

Logistic Regression.

Conclusión.
Como conclusión de este proyecto final para la materia de Datos Masivos impartida por el Dr.josé Christian Romero Hernandez docente del Instituto Tecnológico Nacional De Tijuana, de los tres algoritmos de Machine Learning presentados para la realización de las pruebas mencionadas en el desarrollo del documento se consideró que el algoritmo más eficiente es el de Multilayer Perceptron logrando un tiempo de 30 segundos de procesamiento con el dataset bank-full.csv y logrando clasificar con un 88.5% por ciento correctamente los datos de trabajo
 
Referencias.
Kurman, N. (2018, 21 noviembre). Comprensión del perceptrón multicapa. Recuperado 5 diciembre, 2019, de https://medium.com/@AI_with_Kain/understanding-of-multilayer-perceptron-mlp-8f179c4a135f
Brid, R. A. J. E. S. H. (2018, 17 octubre). Regresión logística. Simplificado.. Recuperado 5 diciembre, 2019, dehttps://medium.com/greyatom/logistic-regression-89e496433063
Patel, S. (2017, 3 abril). SVM (Máquina de vectores de soporte) - Teoría. Recuperado 5 diciembre, 2019, de https://medium.com/machine-learning-101/chapter-2-svm-support-vector-machine-theory-f0812effc72
Roman, V. (2019, 25 mayo). Algoritmos Naive Bayes: Fundamentos e Implementación. Recuperado 5 diciembre, 2019, de https://medium.com/datos-y-ciencia/algoritmos-naive-bayes-fudamentos-e-implementaci%C3%B3n-4bcb24b307f
Scala. (2018, 18 febrero). The Scala Programming Language. Recuperado 5 diciembre, 2019, de https://www.scala-lang.org/
Bigeek. (2017, 3 octubre). Apache Spark para principiantes. Recuperado 5 diciembre, 2019, de https://blog.bi-geek.com/apache-spark-introduccion/
Moro, S., Cortez, P., & Rita, A. (2014, 29 julio). Apache Spark para principiantes [Conjunto de datos]. Recuperado 5 diciembre, 2019, de https://archive.ics.uci.edu/ml/datasets/Bank+Marketing#
 
