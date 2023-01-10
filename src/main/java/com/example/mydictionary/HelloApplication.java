package com.example.mydictionary;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    Stage MyStage;
    Scene scene1,scene2;
    AnchorPane bodyPane;
    int width=400,height=500;
    Dictionary dictionary_obj=new Dictionary();
    Label meaningLabel;
    TextField searchTextField;
    VBox lowerBox;
    ListView<String> listView;
    Button searchButton;

    public HelloApplication() throws Exception {
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene1=createScene1();
        scene2=createScene2();
        MyStage=stage;
        MyStage.setTitle("Welcome");
        MyStage.setScene(scene1);
        MyStage.setWidth(width);
        MyStage.setHeight(height);
        MyStage.setResizable(false);
        MyStage.show();
    }

    public static void main(String[] args) {

        launch();
    }
    public Scene createScene1(){
        Pane pane=new Pane();
     VBox topBox=new VBox();
        Label textLabel1=new Label();
        textLabel1.setText("Welcome to \n My Dictionary");
        Label textLabel2=new Label();
        textLabel2.setText("click the button below to proceed");
        Button button1=new Button("continue");
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                switchScene(scene2);
                MyStage.setTitle("My Dictionary");
            }
        });

        VBox lowerBar=new VBox();
        lowerBar.setPrefHeight(100);
        lowerBar.setPrefWidth(width);
        lowerBar.getChildren().addAll(textLabel2,button1);
        lowerBar.setStyle("-fx-background-color:yellow");
        lowerBar.setTranslateY(400);
        lowerBar.setAlignment(Pos.TOP_CENTER);
        lowerBar.setPadding(new Insets(10,0,0,0));



        topBox.getChildren().addAll(textLabel1);
        topBox.setPrefHeight(height-100);
        topBox.setPrefWidth(width);
        topBox.setAlignment(Pos.CENTER);
        topBox.setStyle("-fx-background-color:green");
         pane.getChildren().addAll(topBox,lowerBar);

        Scene scene=new Scene(pane);
        return scene;
    }
    public Scene createScene2(){
        bodyPane =new AnchorPane();
        HBox box1=new HBox();
        searchTextField =new TextField();
        searchTextField.setPromptText("Enter Your Word here");
        searchTextField.setFocusTraversable(false);
        searchTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                try{
                lowerBox.getChildren().add(listView);
                }
                catch (Exception e){
                    System.out.println("total name not yet added until search button is clicked");
                }
            }
        });
        searchTextField.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                searchButton.setDisable(false);
                lowerBox.getChildren().clear();
            }
        });
         searchButton=new Button("search");
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lowerBox.getChildren().clear();
                lowerBox.getChildren().add(meaningLabel);

                String textFieldText= searchTextField.getText();
                if(textFieldText.isBlank()||textFieldText.isEmpty()){
                    meaningLabel.setText("Please Enter a Word To Search");
                }
                else{
                    String toBeShowed=dictionary_obj.getMeaning(textFieldText);
                    if(toBeShowed==null)
                        meaningLabel.setText("Word Not found");
                    else
                        meaningLabel.setText(toBeShowed);
                }
                searchTextField.setText("");
                searchButton.setDisable(true);


            }
        });
        box1.getChildren().addAll(searchTextField,searchButton);
        box1.setAlignment(Pos.BOTTOM_CENTER);
        box1.setPrefWidth(width);
        box1.setPrefHeight(100);
        box1.setStyle("-fx-background-color:skyblue");

        lowerBox=new VBox();
        meaningLabel=new Label("Your Meaning Will Be Showed Here");
        meaningLabel.setPrefWidth(350);
        meaningLabel.setPrefHeight(150);
        meaningLabel.setTranslateX(18);
        meaningLabel.setAlignment(Pos.TOP_LEFT);
        meaningLabel.setWrapText(true);
        meaningLabel.setStyle("-fx-background-color:white");
         listView=new ListView<>();
        ObservableList<String> list= FXCollections.observableArrayList();
        for(String ele:dictionary_obj.dictionary.keySet()){
            list.add(ele);
        }
        listView.setItems(list);
        listView.setMaxSize(300,150);

        listView.setTranslateX(50);
        listView.setTranslateY(0);
//        listView.setStyle("-fx-background-color:yellow");
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String word=listView.getSelectionModel().getSelectedItem();
                lowerBox.getChildren().clear();
                lowerBox.getChildren().add(meaningLabel);
                String toBeShowed=dictionary_obj.getMeaning(word);
                if(toBeShowed==null)
                    meaningLabel.setText("Word Not Found");
                else
                    meaningLabel.setText(toBeShowed);
                searchTextField.setText("");
            }
        });




//        lowerBox.setStyle("-fx-background-color:green");
        lowerBox.setTranslateY(100);
        lowerBox.setPrefWidth(width);
        lowerBox.setPrefHeight(400);
        lowerBox.setPadding(new Insets(4,0,0,0));



         bodyPane.getChildren().addAll(box1,lowerBox);
         Scene scene=new Scene(bodyPane);
         return scene;
    }
    public void switchScene(Scene scene){
        MyStage.setScene(scene);
    }
}