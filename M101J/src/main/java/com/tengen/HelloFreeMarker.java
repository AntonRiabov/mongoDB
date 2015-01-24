package com.tengen;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by paladii on 24.01.2015.
 */
public class HelloFreeMarker {

    public static void main(String[] args) throws IOException, TemplateException {
Configuration conf = new Configuration();
        conf.setClassForTemplateLoading(HelloFreeMarker.class, "/");
        StringWriter writer = new StringWriter();
        Template hello = conf.getTemplate("ololo.ftl");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", new Object());
        try {
            hello.process(map, writer);
            System.out.println(writer);
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

