package com.wxf.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.Arrays;

/**
 * spark连接
 *
 * @author WangXiaofan777
 * @since 2020-11-18 10:11:27
 */
public class SparkMain {
    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf().setAppName("SparkApp");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);
        JavaRDD<String> lines = sparkContext.textFile("");
        JavaRDD<String> javaRDD = lines.flatMap((FlatMapFunction<String, String>) x -> Arrays.asList(x.split("")).iterator());

        JavaPairRDD<String, Integer> counts = javaRDD.mapToPair((PairFunction<String, String, Integer>) s -> new Tuple2<>(s, 1))
                .reduceByKey((Function2<Integer, Integer, Integer>) Integer::sum);
        counts.saveAsTextFile("");

        sparkContext.stop();

    }
}