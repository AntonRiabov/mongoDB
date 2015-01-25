package com.tengen;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Random;

/**
 * Created by paladii on 25.01.2015.
 */
public class FindTest {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB db = client.getDB("test");
        DBCollection collection = db.getCollection("findTest");
        collection.drop();

        for (int i = 0; i < 10; i++) {
            collection.insert(new BasicDBObject("x", new Random().nextInt(100)));
        }

        System.out.println("Find one:");
        DBObject one = collection.findOne();
        System.out.println(one);

        System.out.println("Find all:");
        DBCursor cursor = collection.find();
        try {
            for (DBObject obj : cursor) {
                System.out.println(obj);
            }
        } finally {
            cursor.close();
        }
        System.out.println("Count: ");
        System.out.println(collection.count());
    }
}
