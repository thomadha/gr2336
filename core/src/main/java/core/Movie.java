package core;

public class Movie {
  String name; 
  double score;

  public Movie(String name, double score){
    if(!validScore(score)){
      throw new IllegalArgumentException("Score must be between 0 and 10");
    }
    this.name = name;
    this.score = score; 
  }
  
  public String getName() {
    return name;
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

  private boolean validScore(double score){
    return score<=10 && score>=0;
  }

  
}
