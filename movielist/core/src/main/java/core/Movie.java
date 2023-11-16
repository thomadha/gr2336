package core;

/**
 * Class Movie, out base class.
 */
public class Movie {

  private String name;
  private String genre;
  private double score;

  private int count = 1;

  /**
   * toString method for showing objects of the Movie Class.
   *
   * @return a string with name, genre, score and count.
   */
  @Override
  public String toString() {
    return name + " \n - Genre: " + genre + " - Score: " + score + " - Views: " + count;
  }

  /**
   * Creates a string for showing scores.
   *
   * @return a string with name of movie and score.
   */
  public String scoreString() {
    return name + "\n - Score: " + score + "\n\n";
  }

  public String viewsString() {
    return name + "\n - Views: " + count + "\n\n"; 
  }

  /**
   * Constructor for movie class without movieCount.
   *
   * @param name is name of movie.
   * @param score is score of movie.
   * @param genre genre of movie.
   */
  public Movie(final String name, final double score, final String genre) {
    if (!validScore(score)) {
      throw new IllegalArgumentException("Score must be between 0 and 10");
    }
    if (!validName(name)) {
      throw new IllegalArgumentException("Must have a name");
    }
    if (!validgenre(genre)) {
      throw new IllegalArgumentException("Not specified");
    }
    this.name = name;
    this.score = score; 
    this.genre = genre;
  }

  /**
   * Constructor with four inputs for Movie class.
   *
   * @param name of movie
   * @param score of movie
   * @param genre of movie
   * @param count of movie
   */
  public Movie(String name, double score, String genre, int count) {
    if (!validScore(score)) {
      throw new IllegalArgumentException("Score must be between 0 and 10");
    }
    if (!validName(name)) {
      throw new IllegalArgumentException("Must have a name");
    }
    if (!validgenre(genre)) {
      this.genre = "Must specify genre";
    } else {
      this.genre = genre;
      this.name = name;
      this.score = score; 
      this.count = count;
    }
  }
  
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
   * @param nameInput of movie.
   */
  public void setName(final String nameInput) {
    this.name = nameInput;
  }

  /**
   * Setter for score.
   *
   * @param scoreInput of the movie.
   */
  public void setScore(final double scoreInput) {
    this.score = scoreInput;
  }

  /**
   * Setter for genre.
   *
   * @param genreInput of movie.
   */
  public void setGenre(final String genreInput) {
    this.genre = genreInput;
  }

  /**
   * Checks if score is valid.
   * Valid: over or equal to 0.0 and below or equal to 10.0.
   *
   * @param scoreInput of movie.
   * @return a boolean, true if valid and false if not.
   */
  private boolean validScore(final double scoreInput) {
    return scoreInput <= 10 && scoreInput >= 0;
  }

  /**
   * Checks if name is valid. Meaning not an empty string.
   *
   * @param nameInput of movie.
   * @return a boolean, true if valid and false if not.
   */
  private boolean validName(final String nameInput) {
    return !nameInput.isEmpty();
  }

  /**
   * Checks if genre is valid.
   *
   * @param genreInput of movie.
   * @return a boolean, true if valid and false if not.
   */
  private boolean validgenre(final String genreInput) {
    return !genreInput.equals("Choose genre");
  }

  public void addDuplicate() {
    this.count++;
  }

  public int getCount() {
    return this.count;
  }
}
