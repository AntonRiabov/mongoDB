package com.tengen;

import com.mongodb.*;
import org.bson.types.ObjectId;

import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * Created by paladii on 25.01.2015.
 */
public class InsertTest {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB db = client.getDB("test");

        DBCollection collection = db.getCollection("insertTest");

        DBObject doc = new BasicDBObject().append("x",1);
        System.out.println(doc);
        collection.insert(doc);
//        collection.insert(doc);
        System.out.println(doc);

    }
}
