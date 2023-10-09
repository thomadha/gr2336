package json;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import core.MovieList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MovieListHandler {

    //private static final String filePath = "MovieList.json";
    private Gson gson;
    private static final String folderPath = "ui/src/resources/persistence/"; 


    public MovieListHandler() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void writeMovieListToFile(MovieList movieList, String filename) { //FOR SAVING TO SPESIFIC USERNAME
        String filePath = folderPath + filename;
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(movieList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeMovieListToFile(MovieList movieList) { //FOR ADDING MOVIE IN GENERAL
        String filePath = "MovieList.json";
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(movieList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MovieList readMovieListFromFile(String filename) { //FOR OPENING SPECIFIC USERNAME
        String filePath = folderPath + filename;
        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, MovieList.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public MovieList readMovieListFromFile() { //FOR ADDING MOVIE IN GENERAL
        //String filePath = folderPath + filename;
        String filePath = "MovieList.json";
        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, MovieList.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
  
}
