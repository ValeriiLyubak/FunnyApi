package utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import dto.request.AuthRequest;
import dto.response.UserResponse;
import org.bson.Document;
import org.junit.jupiter.api.AfterAll;


public class DatabaseHelper {
    private static final MongoClient mongoClient = MongoClients.create("mongodb://javacode:bestEducationEver@80.66.64.141:27017/estim?authSource=admin");
    private static final MongoDatabase database = mongoClient.getDatabase("estim");


    public static Document getUserByUsername(String username) {
        return database.getCollection("users").find(new Document("username", username)).first();
    }
    public static Document getUserFromDatabase(UserResponse userResponse) {
        return database.getCollection("users").find(new Document("_id", userResponse.getData().get_id())).first();

    }

    @AfterAll
    public static void closeConnection() {
        mongoClient.close();
    }

    public static void listCollections() {
        for (String name : database.listCollectionNames()) {
            System.out.println(name);
        }
    }
}
