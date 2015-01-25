package com.tengen;

import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.IOException;
import java.io.StringWriter;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by paladii on 24.01.2015.
 */
public class HelloMogoSparkMarker {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient("localhost:27017");
        final DB db = client.getDB("test");

        final Configuration conf = new Configuration();
        conf.setClassForTemplateLoading(HelloFreeMarker.class, "/");

        Spark.get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {

                final StringWriter writer = new StringWriter();
                Template hello = null;
                try {
                    hello = conf.getTemplate("ololo.ftl");
                    DBObject data = db.getCollection("things").findOne();
                    hello.process(data, writer);
                } catch (TemplateException e) {
                    halt(500);
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(writer);
                return writer.toString();
            }
        });

        Spark.get(new Route("/test") {
            @Override
            public Object handle(Request request, Response response) {
                StringBuilder str = new StringBuilder();
                for (String ff : (String[])request.queryParams().toArray()) str.append(ff).append("\n");
return str.toString();
            }
        });
    }
}
