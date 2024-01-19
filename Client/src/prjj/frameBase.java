package prjj;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class frameBase extends BorderPane {

    protected final Pane pane;
    protected final Label label;
    protected final VBox vBox;
    protected final ImageView imageView;
    protected final VBox vBox0;
    protected final Button homeBtn;
    protected final ImageView imageView0;
    protected final Button profileBtn;
    protected final ImageView imageView1;
    protected final Button frndBtn;
    protected final ImageView imageView2;
    protected final Button frndReqBtn;
    protected final ImageView imageView3;
    protected final Button notifBtn;
    protected final ImageView imageView4;
    protected final Button exitBtn;
    protected final ImageView imageView5;
    protected static StackPane contentPane;

    protected static void setContent(Node newContent) {
        contentPane.getChildren().clear();
        contentPane.getChildren().add(newContent);
    }

    private void restartApplication() {
        // Get the current stage
        Stage stage = (Stage) getScene().getWindow();

        // Create a new instance of the application and start it
        Platform.runLater(() -> {
            try {
                new prjj().start(new Stage());
                stage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public frameBase(Curr_User curr_user) throws IOException {

        pane = new Pane();
        label = new Label();
        vBox = new VBox();
        imageView = new ImageView();
        vBox0 = new VBox();
        homeBtn = new Button();
        imageView0 = new ImageView();
        profileBtn = new Button();
        imageView1 = new ImageView();
        frndBtn = new Button();
        imageView2 = new ImageView();
        frndReqBtn = new Button();
        imageView3 = new ImageView();
        notifBtn = new Button();
        imageView4 = new ImageView();
        exitBtn = new Button();
        imageView5 = new ImageView();
        contentPane = new StackPane();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(600.0);
        setPrefWidth(800.0);

        BorderPane.setAlignment(pane, javafx.geometry.Pos.CENTER);
        pane.setPrefHeight(37.0);
        pane.setPrefWidth(600.0);
        pane.setStyle("-fx-background-color: #eb567d;");

        label.setLayoutX(369.0);
        label.setLayoutY(-7.0);
        label.setText("Iwish");
        label.setFont(new Font("Viner Hand ITC", 25.0));
        setTop(pane);

        BorderPane.setAlignment(vBox, javafx.geometry.Pos.CENTER);

        imageView.setFitHeight(135.0);
        imageView.setFitWidth(136.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(getClass().getResource("img-removebg-preview.png").toExternalForm()));
        setLeft(vBox);

        BorderPane.setAlignment(vBox0, javafx.geometry.Pos.CENTER);

        homeBtn.setMnemonicParsing(false);
        homeBtn.setStyle("-fx-background-color: transparent;");

        imageView0.setFitHeight(35.0);
        imageView0.setFitWidth(35.0);
        imageView0.setPickOnBounds(true);
        imageView0.setPreserveRatio(true);
        imageView0.setImage(new Image(getClass().getResource("home.png").toExternalForm()));
        homeBtn.setGraphic(imageView0);

        profileBtn.setMnemonicParsing(false);
        profileBtn.setStyle("-fx-background-color: transparent;");

        imageView1.setFitHeight(35.0);
        imageView1.setFitWidth(35.0);
        imageView1.setPickOnBounds(true);
        imageView1.setPreserveRatio(true);
        imageView1.setImage(new Image(getClass().getResource("profile.png").toExternalForm()));
        profileBtn.setGraphic(imageView1);

        frndBtn.setMnemonicParsing(false);
        frndBtn.setStyle("-fx-background-color: transparent;");

        imageView2.setFitHeight(35.0);
        imageView2.setFitWidth(35.0);
        imageView2.setPickOnBounds(true);
        imageView2.setPreserveRatio(true);
        imageView2.setImage(new Image(getClass().getResource("friends.png").toExternalForm()));
        frndBtn.setGraphic(imageView2);

        frndReqBtn.setMnemonicParsing(false);
        frndReqBtn.setStyle("-fx-background-color: transparent;");

        imageView3.setFitHeight(35.0);
        imageView3.setFitWidth(35.0);
        imageView3.setPickOnBounds(true);
        imageView3.setPreserveRatio(true);
        imageView3.setImage(new Image(getClass().getResource("frindReqIcon.png").toExternalForm()));
        frndReqBtn.setGraphic(imageView3);

        notifBtn.setMnemonicParsing(false);
        notifBtn.setStyle("-fx-background-color: transparent;");

        imageView4.setFitHeight(35.0);
        imageView4.setFitWidth(35.0);
        imageView4.setPickOnBounds(true);
        imageView4.setPreserveRatio(true);
        imageView4.setImage(new Image(getClass().getResource("notification.png").toExternalForm()));
        notifBtn.setGraphic(imageView4);

        exitBtn.setMnemonicParsing(false);
        exitBtn.setStyle("-fx-background-color: transparent;");

        imageView5.setFitHeight(35.0);
        imageView5.setFitWidth(35.0);
        imageView5.setPickOnBounds(true);
        imageView5.setPreserveRatio(true);
        imageView5.setImage(new Image(getClass().getResource("exist.png").toExternalForm()));
        exitBtn.setGraphic(imageView5);
        setRight(vBox0);

        BorderPane.setAlignment(contentPane, javafx.geometry.Pos.CENTER);
        contentPane.setPrefHeight(150.0);
        contentPane.setPrefWidth(200.0);
        setCenter(contentPane);

        pane.getChildren().add(label);
        vBox.getChildren().add(imageView);
        vBox0.getChildren().add(homeBtn);
        vBox0.getChildren().add(profileBtn);
        vBox0.getChildren().add(frndBtn);
        vBox0.getChildren().add(frndReqBtn);
        vBox0.getChildren().add(notifBtn);
        vBox0.getChildren().add(exitBtn);

        setContent(new ProfileView(curr_user));

        homeBtn.setOnAction(e -> {
            Platform.runLater(() -> {
                try {
                    setContent(new homeBase(curr_user));
                    Stage stage = (Stage) getScene().getWindow();
                    stage.setMaximized(true);
                } catch (IOException ex) {
                    Logger.getLogger(frameBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        });

        profileBtn.setOnAction(e -> setContent(new ProfileView(curr_user)));

        frndBtn.setOnAction(e -> setContent(new FriendsBase()));

        frndReqBtn.setOnAction(e -> setContent(new FriendsReqBase(curr_user)));

        notifBtn.setOnAction(e -> setContent(new NotiBase(curr_user)));

        exitBtn.setOnAction(e -> restartApplication());

    }
}
