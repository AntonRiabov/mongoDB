package com.tengen;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Random;

/**
 * Created by paladii on 26.01.2015.
 */
public class FindCriteriaTest {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB db = client.getDB("test");
        DBCollection collection = db.getCollection("findTest");
        collection.drop();

        for (int i = 0; i < 10; i++) {
            collection.insert(new BasicDBObject("x", new Random().nextInt(2)).append("y", new Random().nextInt(100)));
        }
QueryBuilder builder = QueryBuilder.start("x").is(0).and("y").greaterThan(10).lessThan(80);
//        BasicDBObject query = new BasicDBObject("x", 0)
//                .append("y", new BasicDBObject("$gt", 20).append("$lt", 90));
//        System.out.println(query);
        System.out.println(builder.get());

        System.out.println("Find one:");
        DBObject one = collection.findOne();
        System.out.println(one);

        System.out.println("Find all:");
        DBCursor cursor = collection.find(builder.get());
//        DBCursor cursor = collection.find(query);
        try {
            for (DBObject obj : cursor) {
                System.out.println(obj);
            }
        } finally {
            cursor.close();
        }
        System.out.println("Count: ");
        System.out.println(collection.count(builder.get()));
//        System.out.println(collection.count(query));
    }

}
