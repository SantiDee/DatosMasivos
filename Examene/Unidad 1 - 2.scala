// punto 1
import org.apache.spark.sql.SparkSession 

val spark = SparkSession.builder().getOrCreate()

// punto 2
val df = spark.read.option("header", "true").option("inferSchema","true")csv("Netflix_2011_2016.csv") 

// punto 3
df.show() 

// punto 4
df.printSchema() 

// punto 5
df.head(5) 

 // punto 6
df.describe().show()

// punto 7
val df2 = df.withColumn("HV Ratio", df("High")*df("Volume"))

// punto 9
// la columna Close significa como cerro ese dia la bolsa

// punto 10
df.select(max("Volume")).show()

df.select(min("Volume")).show()

//punto 11
//A
df.filter($"Close" < 600).count()

//B
val res = df.select($"High").filter($"High">500).count()
val por = (res * 1.0 )/100.0

//C
var total = df.select(corr($"high",$"Volume")).show()

//D
df.groupBy(year(df("Date"))).max().show()

//E
df.groupBy(month(df("Date"))).avg().show()

