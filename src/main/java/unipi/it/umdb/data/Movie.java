package unipi.it.umdb.data;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import unipi.it.umdb.MongoDriver;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;

public class Movie {
    private int movieID;
    private String title;
    private String genres;
    private String imdbId;
    private double avgRating;
    private int numVotes;
    private int inWatchlists;

    public Movie(int movieID, String title, String genres, String imdbId, double avgRating, int numVotes){
        this.movieID = movieID;
        this.title = title;
        this.genres = genres;
        this.imdbId = imdbId;
        this.avgRating = avgRating;
        this.numVotes = numVotes;
        this.inWatchlists = 0;
    }

    public Movie(Document movie) {
    }

    public int getMovieID (){
        return this.movieID;
    }

    public String getTitle (){
        return this.title;
    }

    public void setTile (String title){
        this.title = title;
    }

    public String getGenres (){
        return this.genres;
    }

    public void setGenres(String genres){
        this.genres = this.genres.concat(genres);
    }

    public String getImdbId (){
        return this.imdbId;
    }

    public void setImdbId (String imdbId){
        this.imdbId = imdbId;
    }

    public double getAvgRating(){
        return this.avgRating;
    }

    public void setAvgRating (double avgRating){
        this.avgRating = avgRating;
    }

    public int getNumVotes (){
        return this.numVotes;
    }

    public void setImdbId (int numVotes){
        this.numVotes = numVotes;
    }

    public static Movie findMovie(String title) {
        try {
            MongoDriver md = MongoDriver.getInstance();
            MongoCollection<Document> collection = md.getCollection("movies");
            Document movie = collection.find(eq("title", title)).first();

            return new Movie(movie);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<Movie> browseMoviesWithGenre(String value, int searchAll) {
        return searchMovies("genres", value, searchAll);
    }

    public static ArrayList<Movie> browseMoviesWithRating(String value, int searchAll) {
        return searchMovies("avgRating", value, searchAll);
    }

    public static ArrayList<Movie> searchMovies(String search, int searchAll) {
        return searchMovies("title", search, searchAll);
    }


    public static ArrayList<Movie> searchMovies(String key, String search, int searchAll) {
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        MongoDriver driver = null;
        MongoCollection<Document> collection = null;
        MongoCursor<Document> cursor;
        try {
            driver = MongoDriver.getInstance();
            collection = driver.getCollection("movies");
            if (searchAll > 0) {
                cursor = collection.find(regex(key, search, "i")).limit(10*(searchAll+1)).iterator();
            } else {
                cursor = collection.find(regex(key, search, "i")).limit(10).batchSize(10).iterator();
            }

            try {
                while (cursor.hasNext()) {
                    Document document = cursor.next();
                    movieList.add(new Movie(document));
                }
            } finally {
                cursor.close();
            }

            return movieList;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void deleteMovie(String gameName) {

        try {
            MongoDriver md;
            MongoCollection<Document> collection;

            md = MongoDriver.getInstance();
            collection = md.getCollection("movies");
            collection.deleteOne(eq("movieID", movieID));

//            deleteMovieNode(movieID);	//delete from graph
//            deleteFromWatchlists(movieID);	//delete from users lists
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteFromWatchlists(String movieID){
        try {
            Document movie = new Document();
            movie.append("movieID", movieID);

            MongoDriver driver;
            MongoCollection<Document> collection;
            Bson filter;
            Bson delete;
            Bson delete1;

            driver = MongoDriver.getInstance();
            collection = driver.getCollection("users");

            //NEED TO LOOP AROUND ALL WATCHLISTS
            filter = Filters.eq("watchlist.name.movieID", movieID);

//            delete = Updates.pull("wishlist", game);
//            delete1 = Updates.pull("mygames", game);
//
//            collection.updateMany(filter, delete);
//            collection.updateMany(filter1, delete1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

