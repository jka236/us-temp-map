import org.apache.spark.sql.{ SparkSession}
import org.apache.spark.sql.functions.{call_udf, input_file_name, mean, round}
import org.apache.spark.sql.types.{DoubleType, FloatType, IntegerType, StringType, StructType}

object USTempDf {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder.appName("weather").config("spark.master", "bblocal").getOrCreate()
    val weather_schema = new StructType()
      .add("STN", StringType)
      .add("WBAN", StringType)
      .add("Month", IntegerType)
      .add("Day", IntegerType)
      .add("Temperature", FloatType)

    val temperature_file_loc = "./src/main/scala/resources/temp/*"
    val location_file_loca = "./src/main/scala/resources/station/*"
    spark.udf.register("get_file_name", (path: String) => path.split("/").last.split("\\.").head)

    val weather_data = spark.read.option("header", "false")
      .schema(weather_schema).csv(temperature_file_loc)
      .withColumn("year", call_udf("get_file_name", input_file_name()))
      .na.drop("any")

    val avg_weather_data = weather_data.groupBy("STN", "WBAN", "year")
      .agg(round(mean("Temperature"), 2).alias("avg_temp"))

    val location_schema = new StructType()
      .add("STN", StringType)
      .add("WBAN", StringType)
      .add("lat", DoubleType)
      .add("long", DoubleType)

    val location_data = spark.read.option("header", "false")
      .schema(location_schema).csv(location_file_loca)
      .na.drop("any")

    val joined_data = avg_weather_data.join(location_data, Seq("STN", "WBAN"), "inner")
    val select_data = joined_data.select("year", "avg_temp", "lat", "long")

    select_data.write.option("header", "true").csv("./weather_spark")
  }
}
