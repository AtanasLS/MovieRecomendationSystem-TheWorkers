package dk.easv.presentation.controller;

import dk.easv.entities.*;
import dk.easv.presentation.model.AppModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class AppController implements Initializable {
    //top three movies labels
    @FXML
    private Label topOneMovTitle, topTwoMovTitle, topThreeMovTitle;
    //top-rated movies labels
    @FXML private Label topOneMovTitle1, topOneMovTitle2, topOneMovTitle3, topOneMovTitle4, topOneMovTitle5;
    //categories movie titles
    @FXML private Label catMovTitle1, catMovTitle2, catMovTitle3, catMovTitle4, catMovTitle5;
    //footer's label
    @FXML
    private Label footer;



    private AppModel model;
    private long timerStartMillis = 0;
    private String timerMsg = "";

    private void startTimer(String message){
        timerStartMillis = System.currentTimeMillis();
        timerMsg = message;
    }

    private void stopTimer(){
        System.out.println(timerMsg + " took : " + (System.currentTimeMillis() - timerStartMillis) + "ms");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setModel(AppModel model) {
        model.loadData(model.getObsLoggedInUser());
       List<Movie>topThreeMovies= model.getObsTopMovieNotSeen();
       topThreeMovies.sort(Comparator.comparing(Movie::getAverageRating));

       topOneMovTitle.setText(topThreeMovies.get(0).getTitle().toString());
        topTwoMovTitle.setText(topThreeMovies.get(1).getTitle());
        topThreeMovTitle.setText(topThreeMovies.get(2).getTitle());


        topOneMovTitle1.setText(topThreeMovies.get((int)(Math.random()*topThreeMovies.size())+0).getTitle());
        topOneMovTitle2.setText(topThreeMovies.get((int)(Math.random()*topThreeMovies.size())+0).getTitle());
        topOneMovTitle3.setText(topThreeMovies.get((int)(Math.random()*topThreeMovies.size())+0).getTitle());
        topOneMovTitle4.setText(topThreeMovies.get((int)(Math.random()*topThreeMovies.size())+0).getTitle());
        topOneMovTitle5.setText(topThreeMovies.get((int)(Math.random()*topThreeMovies.size())+0).getTitle());

        catMovTitle1.setText(topThreeMovies.get((int)(Math.random()*topThreeMovies.size())+0).getTitle());
        catMovTitle2.setText(topThreeMovies.get((int)(Math.random()*topThreeMovies.size())+0).getTitle());
        catMovTitle3.setText(topThreeMovies.get((int)(Math.random()*topThreeMovies.size())+0).getTitle());
        catMovTitle4.setText(topThreeMovies.get((int)(Math.random()*topThreeMovies.size())+0).getTitle());
        catMovTitle5.setText(topThreeMovies.get((int)(Math.random()*topThreeMovies.size())+0).getTitle());




    }
}
