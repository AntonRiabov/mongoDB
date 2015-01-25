package com.tengen;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by paladii on 26.01.2015.
 */
public class FindModifyTest {
    public static void main(String[] args) throws UnknownHostException {
        DBCollection collection = getDbCollection();
        Random random = new Random();

        List<String> names = Arrays.asList("abby", "bobby", "cathy", "david", "ethan");
        for(String name : names){
            collection.insert(new BasicDBObject("_id", name));
        }

        collection.update(new BasicDBObject("_id", "frank"), new BasicDBObject("$set", new BasicDBObject("gender", "F").append("age", 12) ), true, false);
        collection.update(new BasicDBObject(), new BasicDBObject("$set", new BasicDBObject("gender", "M").append("work", "hard") ), true, true);

        int range = 0;

        getRange(collection, range, "abby");
//        collection.remove(new BasicDBObject("_id", "abby"));
//        QueryBuilder builder = QueryBuilder.start("start.x").greaterThan( 70);
//        QueryBuilder builder = QueryBuilder.start("x").is(0).and("y").greaterThan(10).lessThan(80);
//        BasicDBObject query = new BasicDBObject("x", 0)
//                .append("y", new BasicDBObject("$gt", 20).append("$lt", 90));
//        System.out.println(query);
//        System.out.println(builder.get());

//        System.out.println("Find one:");
//        DBObject one = collection.findOne();
//        System.out.println(one);

        System.out.println("Find all:");
//        BasicDBObject append = new BasicDBObject("y", 1).append("_id", 0);
//        System.out.println(append);
//        DBCursor cursor = collection.find(builder.get(), new BasicDBObject("start.y", true).append("_id", false));
        DBCursor cursor = collection.find();
//                .sort(new BasicDBObject("start.x", 1).append("start.y", -1)).skip(2).limit(3);
        try {
            for (DBObject obj : cursor) {
                System.out.println(obj);
            }
        } finally {
            cursor.close();
        }
        System.out.println("Count: ");
//        System.out.println(collection.count(builder.get()));
        System.out.println(collection.count());

int first = getRange(collection, 2, "abby");
        System.out.println("Range" + first + "-" + 2);


         first = getRange(collection, 3, "abby");
        System.out.println("Range" + first + "-" + 3);
        first = getRange(collection, 4, "abby");
        System.out.println("Range  " + first + "-" + 4);
    }

    private static int getRange(DBCollection collection, int range, String id) {
        DBObject doc = collection.findAndModify(new BasicDBObject("_id", id), null, null, false,
                new BasicDBObject("$inc", new BasicDBObject("counter", range)),
                true, true);
        return (Integer)doc.get("counter") - range;
    }

    private static DBCollection getDbCollection() throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB db = client.getDB("test");
        DBCollection collection = db.getCollection("findTest");
        collection.drop();
        return collection;
    }

}
