package vttp.batch5.paf.movies.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import vttp.batch5.paf.movies.models.DirectorDetail;
import vttp.batch5.paf.movies.services.MovieService;

@RestController
@RequestMapping
public class MainController {

  @Autowired
  private MovieService movieSvc;

  // TODO: Task 3 - get top n directors with most directed movies
  @GetMapping(path="/directors", produces="application/json")
  public ResponseEntity<String> getTopDirectors(
    @RequestParam Integer num
  ) {

    try {

      // get top directors from mongodb
    List<DirectorDetail> directors = movieSvc.getProlificDirectors(num);

    JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

    for (DirectorDetail dd : directors) {

      JsonObject jObject = Json.createObjectBuilder()
        .add("director_name", dd.getDirector())
        .add("movies_count", dd.getMovieCount())
        .add("total_revenue", dd.getRevenue())
        .add("total_budget", dd.getBudget())
        .build();

      arrBuilder.add(jObject);

    }

    JsonArray jArray = arrBuilder.build();

    return ResponseEntity.ok()
      .contentType(MediaType.APPLICATION_JSON)
      .body(jArray.toString());
 
    } catch (Exception e) {

      return ResponseEntity.badRequest()
        .contentType(MediaType.APPLICATION_JSON)
        .body("Exception occurred: %s".formatted(e.getMessage()));

    }

  }

  
  // TODO: Task 4


}
