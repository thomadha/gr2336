package json;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import core.MovieList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MovieListHandler {

    private static final String filePath = "MovieList.json";
    private Gson gson;

    public MovieListHandler() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void writeMovieListToFile(MovieList movieList) {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(movieList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MovieList readMovieListFromFile() {
        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, MovieList.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
  
}
