package com.tengen;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by paladii on 24.01.2015.
 */
public class FruitPeekerApp {
    public static void main(String[] args) {
        final Configuration conf = new Configuration();
        conf.setClassForTemplateLoading(FruitPeekerApp.class, "/fruit");
        Spark.get(new Route("/fruits") {
            @Override
            public Object handle(Request request, Response response) {
                StringBuilder str = new StringBuilder();
                Map<String, Object> fruitsMap = new HashMap<String, Object>();
                fruitsMap.put("fruits", Arrays.asList("apple", "orange", "banana", "peach"));

                try {
                    Template fruitTemplate = conf.getTemplate("fruitChooser.ftl");
                    StringWriter writer = new StringWriter();
                    fruitTemplate.process(fruitsMap, writer);
                    return writer;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TemplateException e) {
                    e.printStackTrace();
                }
                return "fail =(";
            }
        });
        Spark.post(new Route("/favorite_fruit") {
            @Override
            public Object handle(Request request, Response response) {
                final String fruit = request.queryParams("fruit");
                if(fruit == null){
                    return "Why you don't peek anyone?!!";
                } else {
                    return String.format("Your favorite fruit is: \n%s \n I guess so))", fruit);
                }
            }
        });
    }
}
