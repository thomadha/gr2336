package core;

/**
 * Class Movie, out base class.
 */
public class Movie {

  /**
   * Name of movie.
   */
  private String name;
  /**
   * Genre of movie.
   */
  private String genre;
  /**
   * Score of movie.
   */
  private double score;
  /**
   * Amount of times a movie has been watched.
   * Starts at 1.
   */
  private int movieCount = 1;
  /**
   * Maximum value of score.
   */
  public static final int MAXIMUM_SCORE = 10;


  /**
   * toString method for showing objects of the Movie Class.
   *
   * @return a string with name, genre, score and moviecount.
   */
  @Override
  public String toString() {
    String nameString = this.name + " \n - Genre: ";
    String genreString = this.genre + " - Score: ";
    String scoreString = this.score + " - Views: ";
    return nameString + genreString + scoreString + movieCount;
  }

  /**
   * Creates a string for showing scores.
   *
   * @return a string with name of movie and score.
   */
  public String scoreString() {
    return name + "\n - Score: " + score + "\n\n";
  }

  /**
   * Creates a string for showing scores.
   *
   * @return a string with name of movie and score.
   */
  public String viewsString() {
    return name + "\n - Views: " + movieCount + "\n\n";
  }

  /**
   * Constructor for movie class without movieCount.
   *
   * @param n is name of movie.
   * @param s is score of movie.
   * @param g genre of movie.
   */
  public Movie(final String n, final double s, final String g) {
    if (!validScore(s)) {
      throw new IllegalArgumentException("Score must be between 0 and 10");
    }
    if (!validName(n)) {
      throw new IllegalArgumentException("Must have a name");
    }
    if (!validgenre(g)) {
      throw new IllegalArgumentException("Not specified");
    }
    this.name = n;
    this.score = s;
    this.genre = g;
  }

  /**
   * Constructor for movie class with movieCount.
   *
   * @param n is name of movie.
   * @param s is score of movie.
   * @param g genre of movie.
   * @param mC which is amount of times a movie has been seen.
   */
  public Movie(final String n, final double s, final String g, final int mC) {
    if (!validScore(s)) {
      throw new IllegalArgumentException("Score must be between 0 and 10");
    }
    if (!validName(n)) {
      throw new IllegalArgumentException("Must have a name");
    }
    if (!validgenre(g)) {
      this.genre = "Not specified";
    } else {
      this.genre = g;
    }
    this.name = n;
    this.score = s;
    this.movieCount = mC;
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
    return scoreInput <= MAXIMUM_SCORE && scoreInput >= 0;
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

  /**
   * Method to increase moviecount if two movies have the same name.
   */
  public void addDuplicate() {
    this.movieCount++;
  }

  /**
   * Getter for moviecount.
   *
   * @return movieCount of object.
   */
  public int getCount() {
    return this.movieCount;
  }
}
