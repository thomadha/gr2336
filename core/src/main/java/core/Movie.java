package core;

public class Movie {
  private String name; 
  private String genre; 
  private double score;
  private int movieCount = 1;

  public Movie(String name, double score){
    if(!validScore(score)){
      throw new IllegalArgumentException("Score must be between 0 and 10");
    }
    if(!validName(name)){
      throw new IllegalArgumentException("Must have a name");
    }
    this.name = name;
    this.score = score; 
  }

  public Movie(String name, double score, int movieCount){
    if(!validScore(score)){
      throw new IllegalArgumentException("Score must be between 0 and 10");
    }
    if(!validName(name)){
      throw new IllegalArgumentException("Must have a name");
    }
    this.name = name;
    this.score = score; 
    this.movieCount = movieCount;
  }

  
  public String getName() {
    return name;
  }
  public String getGenre() {
    return genre;
  }

  public double getScore() {
    return score;
  }
  public void setName(String name) {
    this.name = name;
  }
  public void setScore(double score) {
    this.score = score;
  } 

  public void setGenre(String genre) {
    this.genre = genre;
  }

  private boolean validScore(double score){
    return score<=10 && score>=0;
  }

  private boolean validName(String name){
    return !name.isEmpty();
  }

  public void addDuplicate(){
    this.movieCount ++;
  }

  public int getCount(){
    return this.movieCount;
  }

  
}
