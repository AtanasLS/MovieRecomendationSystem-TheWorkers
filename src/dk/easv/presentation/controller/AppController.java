package dk.easv.presentation.controller;

import dk.easv.entities.*;
import dk.easv.presentation.model.AppModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;


import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AppController implements Initializable {
    @FXML
    public Label usernameLabel;
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
    //categories titles
    @FXML
    private Label cat1,cat2,cat3,cat4,cat5,cat6;
    //images under every category
    @FXML
    private ImageView catMov1,catMov2,catMov3,catMov4,catMov5;



    private List<Label> categories =new ArrayList<>();

    private List<Movie> topThreeMovies;
    private ArrayList<Image> randomImages = new ArrayList<>();




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
        usernameLabel.setText(model.getObsLoggedInUser().getName());

       topThreeMovies= model.getObsTopMovieNotSeen();
       topThreeMovies.sort(Comparator.comparing(Movie::getAverageRating));

       topOneMovTitle.setText(topThreeMovies.get(0).getTitle());
       topTwoMovTitle.setText(topThreeMovies.get(1).getTitle());
       topThreeMovTitle.setText(topThreeMovies.get(2).getTitle());


        topOneMovTitle1.setText(topThreeMovies.get((int)(Math.random()*topThreeMovies.size())+0).getTitle());
        topOneMovTitle2.setText(topThreeMovies.get((int)(Math.random()*topThreeMovies.size())+0).getTitle());
        topOneMovTitle3.setText(topThreeMovies.get((int)(Math.random()*topThreeMovies.size())+0).getTitle());
        topOneMovTitle4.setText(topThreeMovies.get((int)(Math.random()*topThreeMovies.size())+0).getTitle());
        topOneMovTitle5.setText(topThreeMovies.get((int)(Math.random()*topThreeMovies.size())+0).getTitle());


        this.setLabels();
        this.categories.add(cat1);
        this.categories.add(cat2);
        this.categories.add(cat3);
        this.categories.add(cat4);
        this.categories.add(cat5);
        this.categories.add(cat6);


    }

    @FXML
    private void setAction(){
        this.setColour(0);
        this.setLabels();
        setImages();

    }
    private void setRandomImages(){
        try {
            Stream<Path> stream = Files.list(Paths.get(getClass().getResource("/movie_covers/").toURI()));
            List<String> imgs = stream.filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString).collect(Collectors.toList());
            List<Image> imgsConv = imgs.stream()
                    .map(name -> new Image("/movie_covers/"+name)).collect(Collectors.toList());
            randomImages.addAll(imgsConv);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        Collections.shuffle(randomImages);

    }

    @FXML
    private void setDrama(){
        this.setColour(1);
        this.setLabels();
        setImages();

    }

    @FXML
    private void setComedy(){
        this.setColour(2);
        this.setLabels();
        setImages();

    }

    @FXML
    private void setHorror(){
        this.setColour(3);
        this.setLabels();
        setImages();

    }

    @FXML
    private void setAnimation(){
        this.setColour(4);
        this.setLabels();
        setImages();

    }

    @FXML
    private void setRomance(){
        this.setColour(5);
        this.setLabels();
        setImages();


    }




    private void setImages(){
       setRandomImages();
      catMov1.setImage(randomImages.get(1));
      catMov2.setImage(randomImages.get(2));
      catMov3.setImage(randomImages.get(3));
      catMov4.setImage(randomImages.get(4));
      catMov5.setImage(randomImages.get(5));
    }



    private void setColour(int i){
        for (int j = 0; j < 6; j++) {

            if (j==i){
                this.categories.get(j).setStyle("-fx-background-radius: 30px;" +
                        "    -fx-text-fill: #C69749;\n" +
                        "     -fx-background-fill: #282A3A;\n");






            }else {
                this.categories.get(j).setTextFill(Color.web("#fff"));
                this.categories.get(j).setBackground(Background.fill(Color.web("#735F32")));
                this.categories.get(j).setStyle("-fx-background-radius: 30;");
                this.categories.get(j).setStyle("-fx-border-radius: 30;");



            }
        }
    }

    private void setLabels(){

        catMovTitle1.setText(topThreeMovies.get((int)(Math.random()*topThreeMovies.size())+0).getTitle());
        catMovTitle2.setText(topThreeMovies.get((int)(Math.random()*topThreeMovies.size())+0).getTitle());
        catMovTitle3.setText(topThreeMovies.get((int)(Math.random()*topThreeMovies.size())+0).getTitle());
        catMovTitle4.setText(topThreeMovies.get((int)(Math.random()*topThreeMovies.size())+0).getTitle());
        catMovTitle5.setText(topThreeMovies.get((int)(Math.random()*topThreeMovies.size())+0).getTitle());

    }
}
