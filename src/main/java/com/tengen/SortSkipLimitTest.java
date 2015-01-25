package com.tengen;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Random;

/**
 * Created by paladii on 26.01.2015.
 */
public class SortSkipLimitTest {
    public static void main(String[] args) throws UnknownHostException {
        DBCollection collection = getDbCollection();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            collection.insert(new BasicDBObject("_id", i)
                            .append("start",
                                    new BasicDBObject("x", random.nextInt(90) + 10)
                                            .append("y", random.nextInt(90) +10)
                            )
                            .append("end",new BasicDBObject("x", random.nextInt(90) + 10)
                                            .append("y", random.nextInt(90) +10)
                            )
            );
        }
        QueryBuilder builder = QueryBuilder.start("start.x").greaterThan( 70);
//        QueryBuilder builder = QueryBuilder.start("x").is(0).and("y").greaterThan(10).lessThan(80);
//        BasicDBObject query = new BasicDBObject("x", 0)
//                .append("y", new BasicDBObject("$gt", 20).append("$lt", 90));
//        System.out.println(query);
        System.out.println(builder.get());

        System.out.println("Find one:");
        DBObject one = collection.findOne();
        System.out.println(one);

        System.out.println("Find all:");
//        BasicDBObject append = new BasicDBObject("y", 1).append("_id", 0);
//        System.out.println(append);
//        DBCursor cursor = collection.find(builder.get(), new BasicDBObject("start.y", true).append("_id", false));
        DBCursor cursor = collection.find().sort(new BasicDBObject("start.x", 1).append("start.y", -1)).skip(2).limit(3);
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

    private static DBCollection getDbCollection() throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB db = client.getDB("test");
        DBCollection collection = db.getCollection("findTest");
        collection.drop();
        return collection;
    }

}
