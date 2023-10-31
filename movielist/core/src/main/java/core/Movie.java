package core;

public class Movie {
  private String name; 
  private String genre; 
  private double score;
  private int movieCount = 1;

  @Override
  public String toString() {
    return name + " \n - Genre: " + genre + " - Score: " + score + " - Views: " + movieCount;
  }

  public Movie(String name, double score, String genre){
    if(!validScore(score)){
      throw new IllegalArgumentException("Score must be between 0 and 10");
    }
    if(!validName(name)){
      throw new IllegalArgumentException("Must have a name");
    }
    if(!validgenre(genre)){
      throw new IllegalArgumentException("Not specified");
    }
    this.name = name;
    this.score = score; 
    this.genre=genre;
  }

  public Movie(String name, double score, String genre, int movieCount){
    if(!validScore(score)){
      throw new IllegalArgumentException("Score must be between 0 and 10");
    }
    if(!validName(name)){
      throw new IllegalArgumentException("Must have a name");
    }
    if(!validgenre(genre)){
      this.genre = "Not specified";
    }else{
      this.genre = genre;
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

  private boolean validgenre(String genre){
    return !genre.equals("Choose genre");
  }

  public void addDuplicate(){
    this.movieCount ++;
  }

  public int getCount(){
    return this.movieCount;
  }

  
}
