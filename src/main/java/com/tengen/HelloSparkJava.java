package com.tengen;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Created by paladii on 23.01.2015.
 */
public class HelloSparkJava {
    public static void main(String[] args) {
        Spark.get( new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                return "Hello";
            }
        });
    }
}
