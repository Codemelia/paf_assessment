package vttp.batch5.paf.movies.repositories;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import vttp.batch5.paf.movies.models.Movie;

@Repository
public class MongoMovieRepository {

    @Autowired
    private MongoTemplate template;

    // TODO: Task 2.1
    /*
      db.imdb.countDocuments({});
    */
    public Long checkCount() {
        Query query = new Query();
        return template.count(query, Long.class, "imdb");
    }

    // TODO: Task 2.3
    // You can add any number of parameters and return any type from the method
    // You can throw any checked exceptions from the method
    // Write the native Mongo query you implement in the method in the comments
    /* 
        db.imdb.insertMany([
            {
                _id: <imdbId>,
                title: <title>,
                director: <director>,
                overview: <overview>,
                tagline: <tagline>,
                genres: <genres>,
                imdb_rating: <imdbRating>,
                imdb_votes: <imdbVotes>
            },
            ...
        ]);
    */
    public Integer batchInsertMovies(List<Movie> movieList) {

        // new list to hold docs
        List<Document> docs = new LinkedList<>();
        
        for (Movie movie : movieList) {
            
            // new map constaining movie variables
            Map<String, Object> movieMap = new HashMap<>();
            movieMap.put("imdb_id", movie.getImdbId());
            movieMap.put("title", movie.getTitle());
            movieMap.put("director", movie.getDirector());
            movieMap.put("overview", movie.getOverview());
            movieMap.put("tagline", movie.getTagline());
            movieMap.put("genres", movie.getGenres());
            movieMap.put("imdb_rating", movie.getImdbRating());
            movieMap.put("imdb_votes", movie.getImdbVotes());

            // new document with map variables
            Document doc = new Document(movieMap);

            // add doc to list
            docs.add(doc);
            
        }

        // insert into mongo
        List<Document> result = (List<Document>) template.insert(docs, "imdb");

        return result.size(); // return count of docs

    }

    // drop mongo collection
    /*
        db.imdb.drop();
    */
    public void dropCollection() {
        template.dropCollection("imdb");
    }

    // TODO: Task 2.4
    // You can add any number of parameters and return any type from the method
    // You can throw any checked exceptions from the method
    // Write the native Mongo query you implement in the method in the comments
    //
    //    native MongoDB query here
    //
    public void logError() {
        
    }

    // TODO: Task 3
    // Write the native Mongo query you implement in the method in the comments
    //
    //    native MongoDB query here
    //


}
