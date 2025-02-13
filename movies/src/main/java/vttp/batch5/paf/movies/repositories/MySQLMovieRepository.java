package vttp.batch5.paf.movies.repositories;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp.batch5.paf.movies.models.Movie;

@Repository
public class MySQLMovieRepository {

  @Autowired
  private JdbcTemplate template;

  // TODO: Task 2.1
  // check count in mysql
  public Long checkCount() {
    String sqlString = "SELECT COUNT(*) FROM imdb;";
    return template.queryForObject(sqlString, Long.class);
  }

  // TODO: Task 2.3
  // You can add any number of parameters and return any type from the method
  public int[] batchInsertMovies(List<Movie> movieList) {

    // gen insert string
    String sqlString = """
      INSERT INTO imdb (imdb_id, vote_average, vote_count, release_date, revenue, budget, runtime) 
          VALUES (?, ?, ?, ?, ?, ?, ?);
    """;

    List<Object[]> batchArgs = new LinkedList<>();

    for (Movie movie : movieList) {
      batchArgs.add(new Object[] {
        movie.getImdbId(),
        movie.getVoteAverage(),
        movie.getVoteCount(),
        movie.getReleaseDate(),
        movie.getRevenue(),
        movie.getBudget(),
        movie.getRuntime()
      });
    }

    // batch update
    return template.batchUpdate(sqlString, batchArgs);
   
  }
  
  // TODO: Task 3


}
