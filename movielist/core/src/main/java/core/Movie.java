package core;

public class Movie {
  private String name; 
  private String genre; 
  private double score;
  private int movieCount = 1;

  /**
   * toString method for showing objects of the Movie Class.
   * 
   * @return a string with name, genre, score and moviecount.
   */
  @Override
  public String toString() {
    return name + " \n - Genre: " + genre + " - Score: " + score + " - Views: " + movieCount;
  }

  /**
   * Creates a string for showing scores.
   * 
   * @return a string with name of movie and score.
   */
  public String scoreString(){
    return name + "\n - Score: " + score + "\n\n";
  }

  /**
   * Creates a string for showing scores.
   * 
   * @return a string with name of movie and score.
   */
  public String viewsString(){
    return name + "\n - Views: " + movieCount + "\n\n";
  }

  /**
   * Constructor for movie class without movieCount.
   * 
   * @param name of movie.
   * @param score of movie.
   * @param genre of movie.
   */
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

  /**
   * Constructor for movie class with movieCount.
   * 
   * @param name of movie.
   * @param score of movie.
   * @param genre of movie.
   * @param movieCount which is amount of times a movie has been seen.
   */
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
  
  /**
   * Getter for moviename.
   * 
   * @return String of moviename.
   */
  public String getName() {
    return name;
  }
  /**
   * Getter for genre.
   * 
   * @return String of genre.
   */
  public String getGenre() {
    return genre;
  }
  /**
   * Getter for score.
   * 
   * @return double of moviename.
   */
  public double getScore() {
    return score;
  }
  /**
   * Setter for name.
   * 
   * @param string of moviename.
   */
  public void setName(String name) {
    this.name = name;
  }
  /**
   * Setter for score.
   * 
   * @param double of score.
   */
  public void setScore(double score) {
    this.score = score;
  }
  /**
   * Setter for genre.
   * 
   * @param string of genre.
   */
  public void setGenre(String genre) {
    this.genre = genre;
  }

  /**
   * Checks if score is valid, i.e over or equal to 0.0 and below or equal to 10.0.
   * 
   * @param score of movie.
   * @return a boolean, true if valid and false if not.
   */
  private boolean validScore(double score){
    return score<=10 && score>=0;
  }
  /**
   * Checks if name is valid. Meaning not an empty string.
   * 
   * @param name of movie.
   * @return a boolean, true if valid and false if not.
   */
  private boolean validName(String name){
    return !name.isEmpty();
  }

  /**
   * Checks if genre is valid.
   * 
   * @param genre of movie.
   * @return a boolean, true if valid and false if not.
   */
  private boolean validgenre(String genre){
    return !genre.equals("Choose genre");
  }

  /**
   * Method to increase moviecount if two movies have the same name.
   */
  public void addDuplicate(){
    this.movieCount ++;
  }

  /**
   * Getter for moviecount.
   * @return movieCount of object.
   */
  public int getCount(){
    return this.movieCount;
  }
}
