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
import java.util.HashMap;
import java.util.Map;

/**
 * Created by paladii on 24.01.2015.
 */
public class HelloSparkPlusFreemarker {
    public static void main(String[] args) throws IOException, TemplateException {
       final Configuration conf = new Configuration();
        conf.setClassForTemplateLoading(HelloFreeMarker.class, "/");
         final StringWriter writer = new StringWriter();


        Spark.get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {

                Template hello = null;
                try {
                    hello = conf.getTemplate("ololo.ftl");


                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("name", new Object());
                    hello.process(map, writer);
                } catch (TemplateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(writer);
                return writer.toString();
            }
        });

    }
}