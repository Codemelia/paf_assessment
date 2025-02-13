package vttp.batch5.paf.movies.services;

import java.io.StringReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import jakarta.json.JsonValue.ValueType;
import net.sf.jasperreports.json.data.JsonDataSource;
import vttp.batch5.paf.movies.models.DirectorDetail;
import vttp.batch5.paf.movies.models.Movie;
import vttp.batch5.paf.movies.repositories.MongoMovieRepository;
import vttp.batch5.paf.movies.repositories.MySQLMovieRepository;

@Service
public class MovieService {

  @Autowired
  private MongoMovieRepository mongoRepo;

  @Autowired
  private MySQLMovieRepository sqlRepo;

  // TODO: Task 2.1
  // check databases for upload
  public Boolean checkDatabases() {
    
    // check count in mysql
    Long mySqlInserted = sqlRepo.checkCount();

    // check count in mongodb
    Long mongoInserted = mongoRepo.checkCount();

    if (mySqlInserted > 0 && mongoInserted > 0) {
      return true;
    } else {
      return false;
    }

  }

  // TODO: Task 2.2
  // read json from zip file
  public List<JsonObject> readZipAsJson(List<String> strList) {

    // new list to hold json objects
    List<JsonObject> jsonList = new LinkedList<>();
    
    // iterate list of strings, convert each to jsonobject, add to list
    for (String jsonString : strList) {

      JsonReader jReader = Json.createReader(new StringReader(jsonString));
      JsonArray jArray = jReader.readArray();

      for (JsonValue j : jArray) {

        JsonObject jObject = j.asJsonObject();
        jsonList.add(jObject);

      }

    } 

    return jsonList;

  }
  

  // filter json from zip file
  public List<JsonObject> filterZipJson(List<JsonObject> jsonList) {

    // new list to hold filtered json
    List<JsonObject> newList = new LinkedList<>();

    // iterate through list of json
    for (JsonObject movie : jsonList) {

      // obtain release_date attr and parse as localdate
      LocalDate releaseDate = LocalDate.parse(movie.getString("release_date"));

      // get year as integer
      Integer releaseYear = releaseDate.getYear();

      System.out.println(releaseYear);

      // if year is >= 2018, iterate through each jsonobj as map
      if (releaseYear >= 2018) {

        // iterate json object
        for (Map.Entry<String, JsonValue> entry : movie.entrySet()) {

          // get entry key
          String key = entry.getKey();

          // get entry value
          JsonValue value = entry.getValue();

          // check for null values

          if (value == null) {

            // for strings - replace with empty strings
            if (key.equals("title") || key.equals("status") ||
            key.equals("release_date") || key.equals("imdb_id") ||
            key.equals("original_language") || key.equals("overview") ||
            key.equals("tagline") || key.equals("genres") ||
            key.equals("spoken_languages") || key.equals("casts") ||
            key.equals("director") || key.equals("poster_path")) {

              movie.put(key, Json.createValue(""));

            } else if (key.equals("vote_average") || key.equals("vote_count") ||// for numbers - replace with 0
              key.equals("revenue") || key.equals("runtime") ||
              key.equals("budget") || key.equals("popularity") ||
              key.equals("imdb_rating") || key.equals("imdb_votes")) {

              movie.put(key, Json.createValue(0));

            } 

            // no boolean values in documents

          }
          
        }

        // add all updated movies aft 2018 to new list
        newList.add(movie);

      }

    }
    
    return newList;

  }

  // TODO: Task 2.3
  // conv json objects into movies
  public List<Movie> convJsonToMovies(List<JsonObject> newList) {

    // new list to hold movie objects
    List<Movie> movieList = new LinkedList<>();

    // iterate through list of json objects, convert to movies, add to new list
    for (JsonObject movieJson : newList) {

      Movie movie = new Movie(
        movieJson.getString("title"), 
        movieJson.getString("status"), 
        movieJson.getString("release_date"), 
        movieJson.getString("imdb_id"), 
        movieJson.getString("original_language"), 
        movieJson.getString("overview"), 
        movieJson.getString("tagline"), 
        movieJson.getString("genres"), 
        movieJson.getString("spoken_languages"), 
        movieJson.getString("casts"), 
        movieJson.getString("director"), 
        movieJson.getString("poster_path"), 
        movieJson.getInt("vote_average"), 
        movieJson.getInt("vote_count"), 
        movieJson.getJsonNumber("revenue").bigDecimalValue(), 
        movieJson.getInt("runtime"), 
        movieJson.getJsonNumber("budget").bigDecimalValue(), 
        movieJson.getInt("popularity"), 
        movieJson.getInt("imdb_rating"), 
        movieJson.getInt("imdb_votes"));

      movieList.add(movie);

    }

    return movieList; // return updated movie list

  }

  @Transactional(rollbackFor = Exception.class) // rollback for any exceptions that occur during update
  public void insertIntoDatabases(List<Movie> movieList) throws Exception {

    try {

      // insert by batches of 25 (0-24 in list)
      for (int i = 0; i < movieList.size(); i += 24) {

        // sublist of movielist
        List<Movie> subList = movieList.subList(i, i + 24); // sublist 0 - 25 docs

        // try insert into MySql
        int[] sqlInserted = sqlRepo.batchInsertMovies(subList);

        // if inserted rows not equal to movie sublist size
        if (sqlInserted.length != subList.size()) {
          throw new Exception("SQL batch update failed"); // throw exception
        }

        // try insert into mongodb
        Integer mongoInserted = mongoRepo.batchInsertMovies(subList); // sublist 0 - 25 docs

        // if inserted docs size not equal to movie sublist size
        if (mongoInserted != subList.size()) {
          mongoRepo.dropCollection(); // drop collection
          throw new Exception("MongoDB batch update failed"); // throw exception
        }

      }

    } catch (Exception e) {
      throw e; // throw for roll back
    }

  }

  // TODO: Task 2.4


  // TODO: Task 3
  // You may change the signature of this method by passing any number of parameters
  // and returning any type
  public List<DirectorDetail> getProlificDirectors(Integer num) {

    // get dir/count/ids from mongodb
    List<Document> mongoDir = mongoRepo.getDirectors(num);

    // new list to hold director details
    List<DirectorDetail> dirList = new ArrayList<>();

    for (Document d : mongoDir) {

      // new detail
      DirectorDetail dd = new DirectorDetail();

      // set director name and count
      dd.setDirector(d.getString("director"));
      dd.setMovieCount(d.getInteger("movies_count"));

    }

    return null;

  }


  // TODO: Task 4
  // You may change the signature of this method by passing any number of parameters
  // and returning any type
  public void generatePDFReport() {

    // TODO
    /*
      // create overall report JSON datasource
      JsonDataSource reportDS = new JsonDataSource();

      // create director table Json data source = json array
      JsonDataSource directorDS = new JsonDataSource();

      // create report's params
      Map<String, Object> params = new HashMap<>();
      params.put("DIRECTOR_TABLE_DATASET", directorsDS);

      // load report
      JasperReport report = ...;

      // populate report with JSON data sources
      JasperPrint print = JasperFillManager.fillReport(report, params, reportDS);

      // generate report as PDF
    */
    

  }

}
