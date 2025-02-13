package vttp.batch5.paf.movies.models;

import java.io.Serializable;
import java.math.BigDecimal;

public class Movie implements Serializable {

    private String title;
    private String status;
    private String releaseDate;
    private String imdbId;
    private String originalLanguage;
    private String overview;
    private String tagline;
    private String genres;
    private String spokenLanguages;
    private String casts;
    private String director;
    private String posterPath;
    
    private float voteAverage;
    private Integer voteCount;
    private BigDecimal revenue;
    private Integer runtime;
    private BigDecimal budget;
    private Integer popularity;
    private Integer imdbRating;
    private float imdbVotes;
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
    public String getImdbId() {
        return imdbId;
    }
    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }
    public String getOriginalLanguage() {
        return originalLanguage;
    }
    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }
    public String getOverview() {
        return overview;
    }
    public void setOverview(String overview) {
        this.overview = overview;
    }
    public String getTagline() {
        return tagline;
    }
    public void setTagline(String tagline) {
        this.tagline = tagline;
    }
    public String getGenres() {
        return genres;
    }
    public void setGenres(String genres) {
        this.genres = genres;
    }
    public String getSpokenLanguages() {
        return spokenLanguages;
    }
    public void setSpokenLanguages(String spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }
    public String getCasts() {
        return casts;
    }
    public void setCasts(String casts) {
        this.casts = casts;
    }
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public String getPosterPath() {
        return posterPath;
    }
    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
    public float getVoteAverage() {
        return voteAverage;
    }
    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }
    public Integer getVoteCount() {
        return voteCount;
    }
    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }
    public BigDecimal getRevenue() {
        return revenue;
    }
    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }
    public Integer getRuntime() {
        return runtime;
    }
    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }
    public BigDecimal getBudget() {
        return budget;
    }
    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }
    public Integer getPopularity() {
        return popularity;
    }
    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }
    public Integer getImdbRating() {
        return imdbRating;
    }
    public void setImdbRating(Integer imdbRating) {
        this.imdbRating = imdbRating;
    }
    public float getImdbVotes() {
        return imdbVotes;
    }
    public void setImdbVotes(float imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public Movie() {
    }
    
    public Movie(String title, String status, String releaseDate, String imdbId, String originalLanguage,
            String overview, String tagline, String genres, String spokenLanguages, String casts, String director,
            String posterPath, float voteAverage, Integer voteCount, BigDecimal revenue, Integer runtime,
            BigDecimal budget, Integer popularity, Integer imdbRating, float imdbVotes) {
        this.title = title;
        this.status = status;
        this.releaseDate = releaseDate;
        this.imdbId = imdbId;
        this.originalLanguage = originalLanguage;
        this.overview = overview;
        this.tagline = tagline;
        this.genres = genres;
        this.spokenLanguages = spokenLanguages;
        this.casts = casts;
        this.director = director;
        this.posterPath = posterPath;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.revenue = revenue;
        this.runtime = runtime;
        this.budget = budget;
        this.popularity = popularity;
        this.imdbRating = imdbRating;
        this.imdbVotes = imdbVotes;
    }

    @Override
    public String toString() {
        return "Movie [title=" + title + ", status=" + status + ", releaseDate=" + releaseDate + ", imdbId=" + imdbId
                + ", originalLanguage=" + originalLanguage + ", overview=" + overview + ", tagline=" + tagline
                + ", genres=" + genres + ", spokenLanguages=" + spokenLanguages + ", casts=" + casts + ", director="
                + director + ", posterPath=" + posterPath + ", voteAverage=" + voteAverage + ", voteCount=" + voteCount
                + ", revenue=" + revenue + ", runtime=" + runtime + ", budget=" + budget + ", popularity=" + popularity
                + ", imdbRating=" + imdbRating + ", imdbVotes=" + imdbVotes + "]";
    }
    
}
