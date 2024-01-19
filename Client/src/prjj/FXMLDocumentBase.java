package prjj;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class FXMLDocumentBase extends Pane {

    protected final Button btnSignIn;
    protected final Button btnSignUp;
    protected final Label lUserName;
    protected final Label lPassword;
    protected final TextField tUserName;
    protected final TextField tPassword;
    protected final ImageView img;

    public FXMLDocumentBase() {

        btnSignIn = new Button();
        btnSignUp = new Button();
        lUserName = new Label();
        lPassword = new Label();
        tUserName = new TextField();
        tPassword = new TextField();
        img = new ImageView();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        btnSignIn.setLayoutX(272.0);
        btnSignIn.setLayoutY(175.0);
        btnSignIn.setMnemonicParsing(false);
        btnSignIn.setPrefHeight(25.0);
        btnSignIn.setPrefWidth(57.0);
        btnSignIn.setText("Sign In");

        btnSignUp.setLayoutX(272.0);
        btnSignUp.setLayoutY(220.0);
        btnSignUp.setMnemonicParsing(false);
        btnSignUp.setOnAction(this::openSecondScene);
        btnSignUp.setText("Sign Up");

        lUserName.setLayoutX(113.0);
        lUserName.setLayoutY(62.0);
        lUserName.setPrefHeight(30.0);
        lUserName.setPrefWidth(67.0);
        lUserName.setText("User Name");

        lPassword.setLayoutX(113.0);
        lPassword.setLayoutY(116.0);
        lPassword.setPrefHeight(30.0);
        lPassword.setPrefWidth(67.0);
        lPassword.setText("Password");

        tUserName.setLayoutX(226.0);
        tUserName.setLayoutY(65.0);

        tPassword.setLayoutX(226.0);
        tPassword.setLayoutY(118.0);

        img.setFitHeight(55.0);
        img.setFitWidth(62.0);
        img.setLayoutX(14.0);
        img.setLayoutY(14.0);
        img.setPickOnBounds(true);
        img.setPreserveRatio(true);

        getChildren().add(btnSignIn);
        getChildren().add(btnSignUp);
        getChildren().add(lUserName);
        getChildren().add(lPassword);
        getChildren().add(tUserName);
        getChildren().add(tPassword);
        getChildren().add(img);

    }

   protected abstract void openSecondScene(javafx.event.ActionEvent actionEvent);

}
