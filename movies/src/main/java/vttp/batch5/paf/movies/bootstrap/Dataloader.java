package vttp.batch5.paf.movies.bootstrap;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import jakarta.json.JsonObject;
import vttp.batch5.paf.movies.services.MovieService;

@Component
public class Dataloader implements CommandLineRunner {

  @Value("${zip.data.file}")
  private String load;

  @Autowired
  private MovieService movieSvc;

  //TODO: Task 2
  @Override
  public void run(String... args) throws Exception {

    System.out.println(">>>> Checking file contents in databases...\n\n");

    // TASK 2.1 - load zip file into db

      // if filename null, set default
      if (load == null || load.isEmpty()) {
        load = "data/movies_post_2010.zip";
      }

      // read zip file
      ZipInputStream zis = new ZipInputStream(new FileInputStream(load));

      List<String> strList = new ArrayList<>();
      byte[] buffer = new byte[1024];

      ZipEntry zipEntry;
      int read;

        while ((zipEntry = zis.getNextEntry()) != null) {
            while ((read = zis.read(buffer)) >= 0) {
              strList.add(new String(buffer,0,read));
            }
        }

        while (zipEntry != null){
          zipEntry = zis.getNextEntry();

        zis.closeEntry();
        zis.close();

        }

        // System.out.printf(">>>> Zip file contents read: %s\n\n", strList);

        // check if zip file has been loaded into db
        Boolean isLoaded = movieSvc.checkDatabases();
        System.out.printf(">>>> File loaded in DB?: %b\n\n", isLoaded);

        // TASK 2.2

        // if not loaded, load zip file into db
        if (!isLoaded) {
          // read json docs from zip file
          List<JsonObject> jsonList = movieSvc.readZipAsJson(strList);
          System.out.printf(">>>> File read to JSON list: %d\n\n", jsonList.size());

          // filter movies into new list of json objects - released aft 2018 and corrected movies
          /* List<JsonObject> newList = movieSvc.filterZipJson(jsonList);
          System.out.printf(">>>> JSON list filtered: %d\n\n", newList.size());

          // convert list of jsonobjects into list of movies
          List<Movie> movieList = movieSvc.convJsonToMovies(newList);
          System.out.printf(">>>> JSON list converted to movies: %d\n\n", movieList.size());

          // insert updated list of json doc into mysql
          movieSvc.insertIntoDatabases(movieList); */

        }
      
    }
    
} 
