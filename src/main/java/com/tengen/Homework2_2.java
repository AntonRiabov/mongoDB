package com.tengen;

import com.mongodb.*;

import java.net.UnknownHostException;

/**
 * Created by paladii on 26.01.2015.
 */
public class Homework2_2 {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB db = client.getDB("students");
        DBCollection collection = db.getCollection("grades");
//        QueryBuilder builder = QueryBuilder.start("")

        DBCursor cursor = collection.find(new BasicDBObject("type", "homework"))
                .sort(new BasicDBObject("student_id", 1).append("score", 1));
        Integer  id = 31654157;

        for (DBObject obj: cursor) {
            if (!id.equals(obj.get("student_id"))){
                id = (Integer)obj.get("student_id");
                collection.remove(obj);
            }
        }
               cursor = collection.find(new BasicDBObject("type", "homework"))
                .sort(new BasicDBObject("student_id", 1).append("score", 1)).limit(10);

        for (DBObject obj: cursor){
            System.out.println(obj);
        }

//        System.out.println(collection.findOne());

    }
}
