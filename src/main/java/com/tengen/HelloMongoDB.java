package com.tengen;

import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import java.net.UnknownHostException;

/**
 * Created by paladii on 23.01.2015.
 */
public class HelloMongoDB {
    public static void main(String[] args) {
        try {
            MongoClient client = new MongoClient("localhost:27017");
             DB db = client.getDB("test");
            DBCursor cursor  = db.getCollection("things").find();

            for (DBObject curs:cursor)
                System.out.println(curs);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
