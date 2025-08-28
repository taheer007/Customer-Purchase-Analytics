package com.example.sparkapp;

import org.apache.spark.sql.*;
import org.apache.spark.sql.functions.*;

public class App {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder()
                .appName("Customer Purchase Analytics")
                .master("local[*]") // run locally
                .getOrCreate();

        // Load data from MySQL
        Dataset<Row> purchases = spark.read()
                .format("jdbc")
                .option("url", "jdbc:mysql://localhost:3306/customerdb")
                .option("dbtable", "purchases")
                .option("user", "root")
                .option("password", "password")
                .load();

        purchases.show();

        // 1. Top selling products
        Dataset<Row> topProducts = purchases.groupBy("product")
                .agg(functions.sum("amount").alias("total_sales"))
                .orderBy(functions.desc("total_sales"));

        topProducts.show();

        // 2. Customer spending trends
        Dataset<Row> customerSpending = purchases.groupBy("customerId")
                .agg(functions.sum("amount").alias("total_spent"))
                .orderBy(functions.desc("total_spent"));

        customerSpending.show();

        // Save results back to MySQL
        topProducts.write()
                .mode(SaveMode.Overwrite)
                .format("jdbc")
                .option("url", "jdbc:mysql://localhost:3306/customerdb")
                .option("dbtable", "top_products")
                .option("user", "root")
                .option("password", "password")
                .save();

        customerSpending.write()
                .mode(SaveMode.Overwrite)
                .format("jdbc")
                .option("url", "jdbc:mysql://localhost:3306/customerdb")
                .option("dbtable", "customer_spending")
                .option("user", "root")
                .option("password", "password")
                .save();

        spark.stop();
    }
}
