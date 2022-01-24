package unipi.it.umdb.data;

import unipi.it.umdb.MongoDriver;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class RegisteredUser {
    private String userID;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String country;
    private SimpleDateFormat dob;
    private String[] uFollowers;
    private Boolean isAdmin;
    private ArrayList<Document> watchlist;

    public RegisteredUser (String userID, String email, String password, String name, String surname, String country, SimpleDateFormat dob) {
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.name = name;
        this. surname = surname;
        this.country = country;
        this.dob = dob;
        this.uFollowers = new String[]{};
        this.isAdmin = false;
        this.watchlist = new ArrayList<Document>();
    }

    public RegisteredUser(String userID) {

    }

    public String getUserID(){
        return userID;
    }

    public void setUserID(String userId){
        this.userID = userId;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword (String password){
        this.password = password;
    }

    public String getName(){
        return name;
    }

    public void setName (String name){
        this.name = name;
    }

    public String getSurname(){
        return surname;
    }

    public void setSurname (String surname){
        this.surname = name;
    }

    public String getCountry(){
        return country;
    }

    public void setCountry (String country){
        this.country = country;
    }

    public SimpleDateFormat getDob(){
        return dob;
    }

    public void setDob (SimpleDateFormat dob){
        this.dob = dob;
    }

    public ArrayList<Movie> getWatchlist() {
        ArrayList<Movie> list = new ArrayList<Movie>();
        for(Document movie : watchlist) {
            String name = movie.getString("name");
            list.add(Movie.findMovie(name));
        }
        return list;
    }

    public static boolean checkUserID(String uID) {
        MongoDriver driver = null;
        MongoCollection<Document> collection = null;

        try {
            driver = MongoDriver.getInstance();
            collection = driver.getCollection("users");
            MongoCursor<Document> cursor = collection.find(eq("userID", uID)).iterator();
            return(cursor.hasNext());

        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkPassword(String pwd) {
        MongoDriver driver = null;
        MongoCollection<Document> collection = null;

        try {
            driver = MongoDriver.getInstance();
            collection = driver.getCollection("users");
            MongoCursor<Document> cursor = collection.find(eq("password", pwd)).iterator();
            return(cursor.hasNext());

        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkEmail(String email) {
        try {
            MongoDriver md = MongoDriver.getInstance();
            MongoCollection<Document> collection = md.getCollection("users");
            Document user = collection.find(eq("email", email)).first();
            if (user == null)
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isAdmin(String uID) {
        MongoDriver driver = null;
        MongoCollection<Document> collection = null;

        try {
            driver = MongoDriver.getInstance();
            collection = driver.getCollection("users");
            MongoCursor<Document> cursor = collection.find(and(eq("userID", uID),eq("isAdmin", 1))).iterator();
            return(cursor.hasNext());

        }catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void registerUser(String newUserId, String newEmail, String newPwd, String newName, String newSurname, String newCountry, SimpleDateFormat newDob) {
        try {
            MongoDriver md;
            MongoCollection<Document> collection;
            Document user;

            user = new Document("userID", newUserId)
                    .append("email", newEmail)
                    .append("password", newPwd)
                    .append("name", newName)
                    .append("surname", newSurname)
                    .append("country", newCountry)
                    .append("dob", newDob)
                    .append("isAdmin", 0);

            md = MongoDriver.getInstance();
            collection = md.getCollection("users");
            collection.insertOne(user);

//            createUserNode(newUserId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<RegisteredUser> searchUsers(String search) {
        ArrayList<RegisteredUser> listRegUsers = new ArrayList<RegisteredUser>();

        try {
            MongoDriver md = MongoDriver.getInstance();
            MongoCollection<Document> collection = md.getCollection("users");

            BasicDBObject query = new BasicDBObject();
            query.put("nickname", Pattern.compile(search, Pattern.CASE_INSENSITIVE));

            MongoCursor<Document> cursor = collection.find(query).limit(10).iterator();

            try {
                while (cursor.hasNext()) {
                    Document document = cursor.next();
                    if(!isAdmin(document.getString("userID"))) {
                        listRegUsers.add(new RegisteredUser(document.getString("userID")));
                    }
                }
            } finally {
                cursor.close();
            }

            return listRegUsers;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listRegUsers;
    }



}
