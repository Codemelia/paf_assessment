package vttp.batch5.paf.movies.models;

public class DirectorDetail {
 
    private String director;
    private Integer movieCount;
    private float revenue;
    private float budget;
    private float profLoss;
    
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public Integer getMovieCount() {
        return movieCount;
    }
    public void setMovieCount(Integer movieCount) {
        this.movieCount = movieCount;
    }
    public float getRevenue() {
        return revenue;
    }
    public void setRevenue(float revenue) {
        this.revenue = revenue;
    }
    public float getBudget() {
        return budget;
    }
    public void setBudget(float budget) {
        this.budget = budget;
    }
    public float getProfLoss() {
        return profLoss;
    }
    public void setProfLoss(float profLoss) {
        this.profLoss = profLoss;
    }
    
    public DirectorDetail() {
    }

    public DirectorDetail(String director, Integer movieCount, float revenue, float budget, float profLoss) {
        this.director = director;
        this.movieCount = movieCount;
        this.revenue = revenue;
        this.budget = budget;
        this.profLoss = profLoss;
    }

}
