//package prjj;
//
//import javafx.collections.FXCollections;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.Node;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.ListView;
//import javafx.scene.control.TextField;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.Pane;
//import static javafx.scene.layout.Region.USE_PREF_SIZE;
//import javafx.scene.layout.StackPane;
//import javafx.scene.layout.VBox;
//import javafx.scene.text.Font;
//
//public class frameBase extends BorderPane {
//    // Add a Curr_User instance variable
//    private Curr_User currentUser;
//    protected final Pane pane;
//    protected final Label label;
//    protected final VBox vBox;
//    protected final ImageView imageView;
//    protected final VBox vBox0;
//    protected final Button homeBtn;
//    protected final ImageView imageView0;
//    protected final Button profileBtn;
//    protected final ImageView imageView1;
//    protected final Button frndBtn;
//    protected final ImageView imageView2;
//    protected final Button notifBtn;
//    protected final ImageView imageView3;
//    protected final Button exitBtn;
//    protected final ImageView imageView4;
//    protected final StackPane contentPane;
//    
//    protected void setContent(Node newContent) {
//        contentPane.getChildren().clear();
//        contentPane.getChildren().add(newContent);
//    }
//
//
//    public frameBase(Curr_User curr_user) {
//        this.currentUser = curr_user; // Store the current user
//        pane = new Pane();
//        label = new Label();
//        vBox = new VBox();
//        imageView = new ImageView();
//        vBox0 = new VBox();
//        homeBtn = new Button();
//        imageView0 = new ImageView();
//        profileBtn = new Button();
//        imageView1 = new ImageView();
//        frndBtn = new Button();
//        imageView2 = new ImageView();
//        notifBtn = new Button();
//        imageView3 = new ImageView();
//        exitBtn = new Button();
//        imageView4 = new ImageView();
//        contentPane = new StackPane();
//        
//        
//        setMaxHeight(USE_PREF_SIZE);
//        setMaxWidth(USE_PREF_SIZE);
//        setMinHeight(USE_PREF_SIZE);
//        setMinWidth(USE_PREF_SIZE);
//        setPrefHeight(600.0);
//        setPrefWidth(800.0);
//
//        BorderPane.setAlignment(pane, javafx.geometry.Pos.CENTER);
//        pane.setPrefHeight(37.0);
//        pane.setPrefWidth(600.0);
//        pane.setStyle("-fx-background-color: #eb567d;");
//
//        label.setLayoutX(369.0);
//        label.setLayoutY(-7.0);
//        label.setText("Iwish");
//        label.setFont(new Font("Viner Hand ITC", 25.0));
//        setTop(pane);
//
//        BorderPane.setAlignment(vBox, javafx.geometry.Pos.CENTER);
//
//        imageView.setFitHeight(135.0);
//        imageView.setFitWidth(136.0);
//        imageView.setPickOnBounds(true);
//        imageView.setPreserveRatio(true);
//        imageView.setImage(new Image(getClass().getResource("logo.png").toExternalForm()));
//        setLeft(vBox);
//
//        BorderPane.setAlignment(vBox0, javafx.geometry.Pos.CENTER);
//
//        homeBtn.setMnemonicParsing(false);
//        homeBtn.setStyle("-fx-background-color: transparent;");
//
//        imageView0.setFitHeight(35.0);
//        imageView0.setFitWidth(35.0);
//        imageView0.setPickOnBounds(true);
//        imageView0.setPreserveRatio(true);
//        imageView0.setImage(new Image(getClass().getResource("home.png").toExternalForm()));
//        homeBtn.setGraphic(imageView0);
//
//        profileBtn.setMnemonicParsing(false);
//        profileBtn.setStyle("-fx-background-color: transparent;");
//
//        imageView1.setFitHeight(35.0);
//        imageView1.setFitWidth(35.0);
//        imageView1.setPickOnBounds(true);
//        imageView1.setPreserveRatio(true);
//        imageView1.setImage(new Image(getClass().getResource("profile.png").toExternalForm()));
//        profileBtn.setGraphic(imageView1);
//
//        frndBtn.setMnemonicParsing(false);
//        frndBtn.setStyle("-fx-background-color: transparent;");
//
//        imageView2.setFitHeight(35.0);
//        imageView2.setFitWidth(35.0);
//        imageView2.setPickOnBounds(true);
//        imageView2.setPreserveRatio(true);
//        imageView2.setImage(new Image(getClass().getResource("friends.png").toExternalForm()));
//        frndBtn.setGraphic(imageView2);
//
//        notifBtn.setMnemonicParsing(false);
//        notifBtn.setStyle("-fx-background-color: transparent;");
//
//        imageView3.setFitHeight(35.0);
//        imageView3.setFitWidth(35.0);
//        imageView3.setPickOnBounds(true);
//        imageView3.setPreserveRatio(true);
//        imageView3.setImage(new Image(getClass().getResource("notification.png").toExternalForm()));
//        notifBtn.setGraphic(imageView3);
//
//        exitBtn.setMnemonicParsing(false);
//        exitBtn.setStyle("-fx-background-color: transparent;");
//
//        imageView4.setFitHeight(35.0);
//        imageView4.setFitWidth(35.0);
//        imageView4.setPickOnBounds(true);
//        imageView4.setPreserveRatio(true);
//        imageView4.setImage(new Image(getClass().getResource("exist.png").toExternalForm()));
//        exitBtn.setGraphic(imageView4);
//        setRight(vBox0);
//
//        BorderPane.setAlignment(contentPane, javafx.geometry.Pos.CENTER);
//        contentPane.setPrefHeight(150.0);
//        contentPane.setPrefWidth(200.0);
//        setCenter(contentPane);
//
//        pane.getChildren().add(label);
//        vBox.getChildren().add(imageView);
//        vBox0.getChildren().add(homeBtn);
//        vBox0.getChildren().add(profileBtn);
//        vBox0.getChildren().add(frndBtn);
//        vBox0.getChildren().add(notifBtn);
//        vBox0.getChildren().add(exitBtn);
//        setContent(new HomeView(curr_user));
//        homeBtn.setOnAction(e -> setContent(new HomeView(curr_user)));
//        
//        profileBtn.setOnAction(e -> setContent(new ProfileView(curr_user)));
//        
//        frndBtn.setOnAction(e -> setContent(new FriendsBase(curr_user)));
//        
//        notifBtn.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                // Add logic for the notification button
//            }
//        });
//        
//         exitBtn.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                // Add logic for the exit button
//            }
//        });
//         
//         
//    }
//    
//
//}
