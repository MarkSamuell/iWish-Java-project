package prjj;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import static de.jensd.fx.glyphs.fontawesome.FontAwesomeIcons.HOME;
import java.awt.Color;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.PINK;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class init extends StackPane {
    public static double balance;
    public static String currentUserEmail ;
    private static Curr_User curr_User;

    public static void setCurrentUser(Curr_User user) {
        curr_User = user;
    }

    public static Curr_User getCurrentUser() {
        return curr_User;
    }
    protected final BorderPane LoginPage;
    protected final AnchorPane anchorPane;
    protected final FontAwesomeIcon fontAwesomeIcon;
    protected final Label label;
    protected final TextField userNameLogin;
    protected final PasswordField passwordLogin;
    protected final FontAwesomeIcon fontAwesomeIcon0;
    protected final FontAwesomeIcon fontAwesomeIcon1;
    protected final Button btnLogin;
    protected final AnchorPane anchorPane0;
    protected final ImageView imageView;
    protected final Label label0;
    protected final Button btnCreate;
    protected final BorderPane RegisterPage;
    protected final AnchorPane anchorPane1;
    protected final Label label1;
    protected final TextField userNameRegister;
    protected final TextField userNamelRegister;
    protected final PasswordField passwordRegister;
    protected final FontAwesomeIcon fontAwesomeIcon2;
    protected final FontAwesomeIcon fontAwesomeIcon3;
    protected final Button btnSignUp;
    protected final FontAwesomeIcon fontAwesomeIcon4;
    protected final FontAwesomeIcon fontAwesomeIcon5;
    protected final FontAwesomeIcon fontAwesomeIcon6;
    protected final TextField EmailRegister;
    protected final TextField phoneRegister;
    protected final AnchorPane anchorPane2;
    protected final ImageView imageView0;
    protected final Label label2;
    protected final Button btnLogin0;
    Socket mySocket;
    DataInputStream dis;
    PrintStream ps;

    public void switchForm(ActionEvent event) {
        if (event.getSource() == btnCreate) {
            RegisterPage.setVisible(true);
            LoginPage.setVisible(false);
        }
        if (event.getSource() == btnLogin0) {
            RegisterPage.setVisible(false);
            LoginPage.setVisible(true);
        }
    }

    public init() {

        try {
            mySocket = new Socket("10.145.11.234", 5005);
            dis = new DataInputStream(mySocket.getInputStream());
            ps = new PrintStream(mySocket.getOutputStream());
        } catch (SocketException e) {

            JOptionPane.showMessageDialog(null, "Faild to connect with the server" + "\n" + "Error Message: " + e.getMessage());

        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(null, "UnknownHost" + "\n" + "Error Message: " + e.getMessage());

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Faild to connect with the server" + "\n" + "Error Message: " + ex.getMessage());
        }
        LoginPage = new BorderPane();
        anchorPane = new AnchorPane();
        fontAwesomeIcon = new FontAwesomeIcon();
        fontAwesomeIcon.setGlyphName("USER");
        fontAwesomeIcon.setSize("5em");
        fontAwesomeIcon.setFill(Paint.valueOf("#fc3468"));

        label = new Label();
        userNameLogin = new TextField();
        passwordLogin = new PasswordField();

        fontAwesomeIcon0 = new FontAwesomeIcon();
        fontAwesomeIcon0.setGlyphName("USER");
        fontAwesomeIcon0.setSize("2em");
        fontAwesomeIcon0.setFill(Paint.valueOf("#fc3468"));

        fontAwesomeIcon1 = new FontAwesomeIcon();
        fontAwesomeIcon1.setGlyphName("LOCK");
        fontAwesomeIcon1.setSize("2em");
        fontAwesomeIcon1.setFill(Paint.valueOf("#fc3468"));

        btnLogin = new Button();
        anchorPane0 = new AnchorPane();
        imageView = new ImageView();
        label0 = new Label();
        btnCreate = new Button();
        RegisterPage = new BorderPane();
        anchorPane1 = new AnchorPane();
        label1 = new Label();
        userNameRegister = new TextField();
        userNamelRegister = new TextField();
        passwordRegister = new PasswordField();
        fontAwesomeIcon2 = new FontAwesomeIcon();
        fontAwesomeIcon2.setGlyphName("USER");
        fontAwesomeIcon2.setSize("2.4em");
        fontAwesomeIcon2.setFill(Paint.valueOf("#fc3468"));

        fontAwesomeIcon3 = new FontAwesomeIcon();
        fontAwesomeIcon3.setGlyphName("ENVELOPE");
        fontAwesomeIcon3.setSize("2em");
        fontAwesomeIcon3.setFill(Paint.valueOf("#fc3468"));

        btnSignUp = new Button();
        fontAwesomeIcon4 = new FontAwesomeIcon();
        fontAwesomeIcon4.setGlyphName("USER_PLUS");
        fontAwesomeIcon4.setSize("5em");
        fontAwesomeIcon4.setFill(Paint.valueOf("#fc3468"));

        fontAwesomeIcon5 = new FontAwesomeIcon();
        fontAwesomeIcon5.setGlyphName("LOCK");
        fontAwesomeIcon5.setSize("2.4em");
        fontAwesomeIcon5.setFill(Paint.valueOf("#fc3468"));

        fontAwesomeIcon6 = new FontAwesomeIcon();
        fontAwesomeIcon6.setGlyphName("MONEY");
        fontAwesomeIcon6.setSize("2em");
        fontAwesomeIcon6.setFill(Paint.valueOf("#fc3468"));

        EmailRegister = new TextField();
        phoneRegister = new TextField();
        anchorPane2 = new AnchorPane();
        imageView0 = new ImageView();
        label2 = new Label();
        btnLogin0 = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(456.0);
        setPrefWidth(676.0);

        LoginPage.setPrefHeight(200.0);
        LoginPage.setPrefWidth(200.0);

        BorderPane.setAlignment(anchorPane, javafx.geometry.Pos.CENTER);
        anchorPane.setPrefHeight(456.0);
        anchorPane.setPrefWidth(346.0);
        anchorPane.getStyleClass().add("white-form");
        anchorPane.getStylesheets().add("/prjj/design.css");

        fontAwesomeIcon.setLayoutX(153.0);
        fontAwesomeIcon.setLayoutY(76.0);

        label.setLayoutX(104.0);
        label.setLayoutY(92.0);
        label.setText("Login User Form");
        label.setTextFill(javafx.scene.paint.Color.valueOf("#fc3468"));
        label.setFont(new Font("Arial Rounded MT Bold", 20.0));

        userNameLogin.setLayoutX(84.0);
        userNameLogin.setLayoutY(175.0);
        userNameLogin.setPrefHeight(35.0);
        userNameLogin.setPrefWidth(220.0);
        userNameLogin.setPromptText("User Name");

        passwordLogin.setLayoutX(85.0);
        passwordLogin.setLayoutY(221.0);
        passwordLogin.setPrefHeight(35.0);
        passwordLogin.setPrefWidth(220.0);
        passwordLogin.setPromptText("Password");

        fontAwesomeIcon0.setLayoutX(43.0);
        fontAwesomeIcon0.setLayoutY(197.0);

        fontAwesomeIcon1.setLayoutX(43.0);
        fontAwesomeIcon1.setLayoutY(242.0);

        btnLogin.setLayoutX(95.0);
        btnLogin.setLayoutY(294.0);
        btnLogin.setMnemonicParsing(false);
        // btnLogin.setOnAction(this::Login);
        btnLogin.setPrefHeight(40.0);
        btnLogin.setPrefWidth(180.0);
        btnLogin.getStyleClass().add("other-form");
        btnLogin.getStylesheets().add("/prjj/design.css");
        btnLogin.setText("Login");
        btnLogin.setTextFill(javafx.scene.paint.Color.valueOf("#fc3568"));
        btnLogin.setFont(new Font("System Bold", 18.0));
        LoginPage.setRight(anchorPane);

        BorderPane.setAlignment(anchorPane0, javafx.geometry.Pos.CENTER);
        anchorPane0.setPrefHeight(456.0);
        anchorPane0.setPrefWidth(354.0);
        anchorPane0.getStyleClass().add("other-form");
        anchorPane0.getStylesheets().add("/prjj/design.css");

        imageView.setFitHeight(256.0);
        imageView.setFitWidth(545.0);
        imageView.setLayoutX(40.0);
        imageView.setLayoutY(14.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(getClass().getResource("img-removebg-preview.png").toExternalForm()));

        label0.setLayoutX(136.0);
        label0.setLayoutY(319.0);
        label0.setText("Join US!");
        label0.setTextFill(javafx.scene.paint.Color.valueOf("#f5eded"));
        label0.setFont(new Font(18.0));

        btnCreate.setLayoutX(78.0);
        btnCreate.setLayoutY(353.0);
        btnCreate.setMnemonicParsing(false);
        //  btnCreate.setOnAction(this::openRegisterScene);
        btnCreate.setPrefHeight(40.0);
        btnCreate.setPrefWidth(180.0);
        btnCreate.getStyleClass().add("create-btn");
        btnCreate.getStylesheets().add("/prjj/design.css");
        btnCreate.setText("Create Account");
        LoginPage.setCenter(anchorPane0);

        RegisterPage.setLayoutX(10.0);
        RegisterPage.setLayoutY(10.0);
        RegisterPage.setPrefHeight(200.0);
        RegisterPage.setPrefWidth(200.0);
        RegisterPage.setVisible(false);

        BorderPane.setAlignment(anchorPane1, javafx.geometry.Pos.CENTER);
        anchorPane1.setPrefHeight(456.0);
        anchorPane1.setPrefWidth(340.0);
        anchorPane1.getStyleClass().add("white-form");
        anchorPane1.getStylesheets().add("/prjj/design.css");

        label1.setLayoutX(99.0);
        label1.setLayoutY(94.0);
        label1.setText("Register User Form");
        label1.setTextFill(javafx.scene.paint.Color.valueOf("#fc3468"));
        label1.setFont(new Font("Arial Rounded MT Bold", 20.0));

        userNameRegister.setLayoutX(85.0);
        userNameRegister.setLayoutY(147.0);
        userNameRegister.setPrefHeight(33.0);
        userNameRegister.setPrefWidth(107.0);
        userNameRegister.setPromptText("first Name");

        userNamelRegister.setLayoutX(202.0);
        userNamelRegister.setLayoutY(147.0);
        userNamelRegister.setPrefHeight(33.0);
        userNamelRegister.setPrefWidth(107.0);
        userNamelRegister.setPromptText("Last Name");
        EmailRegister.setLayoutX(85.0);
        EmailRegister.setLayoutY(190.0);
        EmailRegister.setPrefHeight(35.0);
        EmailRegister.setPrefWidth(220.0);
        EmailRegister.setPromptText("Email Address");

        passwordRegister.setLayoutX(85.0);
        passwordRegister.setLayoutY(232.0);
        passwordRegister.setPrefHeight(35.0);
        passwordRegister.setPrefWidth(220.0);
        passwordRegister.setPromptText("Password");

        fontAwesomeIcon2.setLayoutX(43.0);
        fontAwesomeIcon2.setLayoutY(171.0);

        fontAwesomeIcon3.setLayoutX(43.0);
        fontAwesomeIcon3.setLayoutY(217.0);

        btnSignUp.setLayoutX(91.0);
        btnSignUp.setLayoutY(364.0);
        btnSignUp.setMnemonicParsing(false);
        btnSignUp.setPrefHeight(40.0);
        btnSignUp.setPrefWidth(180.0);
        btnSignUp.getStyleClass().add("other-form");
        btnSignUp.getStylesheets().add("/prjj/design.css");
        btnSignUp.setText("SIGNUP");
        btnSignUp.setTextFill(javafx.scene.paint.Color.valueOf("#fc3568"));
        btnSignUp.setFont(new Font("System Bold", 18.0));

        fontAwesomeIcon4.setLayoutX(148.0);
        fontAwesomeIcon4.setLayoutY(74.0);
        fontAwesomeIcon4.glyphNameProperty();
        fontAwesomeIcon5.setLayoutX(43.0);
        fontAwesomeIcon5.setLayoutY(259.0);

        fontAwesomeIcon6.setLayoutX(43.0);
        fontAwesomeIcon6.setLayoutY(307.0);

        phoneRegister.setLayoutX(85.0);
        phoneRegister.setLayoutY(280.0);
        phoneRegister.setPrefHeight(35.0);
        phoneRegister.setPrefWidth(220.0);
        phoneRegister.setPromptText("Credit Number");

        anchorPane2.setLayoutX(354.0);
        anchorPane2.setLayoutY(-7.0);
        anchorPane2.setPrefHeight(467.0);
        anchorPane2.setPrefWidth(329.0);
        anchorPane2.getStyleClass().add("other-form");
        anchorPane2.getStylesheets().add("/prjj/design.css");

        imageView0.setFitHeight(256.0);
        imageView0.setFitWidth(545.0);
        imageView0.setLayoutX(40.0);
        imageView0.setLayoutY(7.0);
        imageView0.setPickOnBounds(true);
        imageView0.setPreserveRatio(true);
        imageView0.setImage(new Image(getClass().getResource("img-removebg-preview.png").toExternalForm()));

        label2.setLayoutX(102.0);
        label2.setLayoutY(316.0);
        label2.setText("Already has an Account");
        label2.setTextFill(javafx.scene.paint.Color.valueOf("#f5eded"));
        label2.setFont(new Font(14.0));

        btnLogin0.setLayoutX(78.0);
        btnLogin0.setLayoutY(353.0);
        btnLogin0.setMnemonicParsing(false);
        btnLogin0.setPrefHeight(40.0);
        btnLogin0.setPrefWidth(180.0);
        btnLogin0.getStyleClass().add("create-btn");
        btnLogin0.getStylesheets().add("/prjj/design.css");
        btnLogin0.setText("LOGIN");
        RegisterPage.setTop(anchorPane1);

        anchorPane.getChildren().add(fontAwesomeIcon);
        anchorPane.getChildren().add(label);
        anchorPane.getChildren().add(userNameLogin);
        anchorPane.getChildren().add(passwordLogin);
        anchorPane.getChildren().add(fontAwesomeIcon0);
        anchorPane.getChildren().add(fontAwesomeIcon1);
        anchorPane.getChildren().add(btnLogin);
        anchorPane0.getChildren().add(imageView);
        anchorPane0.getChildren().add(label0);
        anchorPane0.getChildren().add(btnCreate);
        getChildren().add(LoginPage);
        anchorPane1.getChildren().add(label1);
        anchorPane1.getChildren().add(userNameRegister);
        anchorPane1.getChildren().add(userNamelRegister);
        anchorPane1.getChildren().add(passwordRegister);
        anchorPane1.getChildren().add(fontAwesomeIcon2);
        anchorPane1.getChildren().add(fontAwesomeIcon3);
        anchorPane1.getChildren().add(btnSignUp);
        anchorPane1.getChildren().add(fontAwesomeIcon4);
        anchorPane1.getChildren().add(fontAwesomeIcon5);
        anchorPane1.getChildren().add(fontAwesomeIcon6);
        anchorPane1.getChildren().add(EmailRegister);
        anchorPane1.getChildren().add(phoneRegister);
        anchorPane2.getChildren().add(imageView0);
        anchorPane2.getChildren().add(label2);
        anchorPane2.getChildren().add(btnLogin0);
        anchorPane1.getChildren().add(anchorPane2);
        getChildren().add(RegisterPage);

        btnLogin.setOnAction(e -> {
            final Alert[] alert = {null};  // Declare alert as a final array
            Platform.runLater(() -> {
                if (userNameLogin.getText().isEmpty() || passwordLogin.getText().isEmpty()) {
                    alert[0] = new Alert(AlertType.ERROR);
                    alert[0].setTitle("ERROR MESSAGE");
                    alert[0].setHeaderText(null);
                    alert[0].setContentText("Please fill all blank Fields");
                    alert[0].showAndWait();
                } else {
                    // Retrieve the login data
                    String userName = userNameLogin.getText();
                    String password = passwordLogin.getText();

                    // the message to be sent to the server
                    String loginMessage = "login|" + userName + "|" + password;

                    // Send the login message to the server
                    ps.println(loginMessage);

                    try {
                        // Wait for a response from the server
                        String serverResponse = dis.readLine();

                        // Process the server response, you can show an alert or update the UI accordingly
                        if (serverResponse.startsWith("Login successful")) {
                            // Create a User instance with the retrieved data
                             String[] parts = serverResponse.split("\\|");
                             String firstName = parts[1];
                             balance=Double.parseDouble(parts[2]);
                             Curr_User loggedInUser = new Curr_User(userName);
                            currentUserEmail=firstName;
                            // Set the current user
                            init.setCurrentUser(loggedInUser);

                            // Show success message
                            Alert successAlert = new Alert(AlertType.INFORMATION);
                            successAlert.setTitle("Login Successful");
                            successAlert.setHeaderText(null);
                            successAlert.setContentText("Login successful!");
                            successAlert.showAndWait();
                            frameBase mainFrame = new frameBase(loggedInUser);

                            // Get the current stage from the scene
                            Stage currentStage = (Stage) getScene().getWindow();

                            // Set the mainFrame as the root scene
                            currentStage.setScene(new Scene(mainFrame));
                            // Handle the transition to the next scene or perform any other actions
                        } else {
                            // Show failure message
                            Alert failureAlert = new Alert(AlertType.ERROR);
                            failureAlert.setTitle("Login Failed");
                            failureAlert.setHeaderText(null);
                            failureAlert.setContentText("Login failed. Please check your Email and Password.");
                            failureAlert.showAndWait();

                            // Handle the case where login fails, you can show an error message or take appropriate action
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace(); // Handle the exception appropriately
                    }
                }

            });

        });
        btnSignUp.setOnAction(e -> {
            final Alert[] alert = {null};  // Declare alert as a final array
            Platform.runLater(() -> {
                if (userNameRegister.getText().isEmpty() || userNamelRegister.getText().isEmpty() || EmailRegister.getText().isEmpty() || passwordRegister.getText().isEmpty() || phoneRegister.getText().isEmpty()) {
                    alert[0] = new Alert(AlertType.ERROR);
                    alert[0].setTitle("ERROR MESSAGE");
                    alert[0].setHeaderText(null);
                    alert[0].setContentText("Please fill all fields");
                    alert[0].showAndWait();
                } else {
                    // Retrieve the registration data
                    String userName = userNameRegister.getText();
                    String userNamel = userNamelRegister.getText();
                    String password = passwordRegister.getText();
                    String email = EmailRegister.getText();
                    String phone = phoneRegister.getText();

                    // the message to be sent to the server
                    String registrationMessage = "register|" + userName + "|" + userNamel + "|" + email + "|" + password + "|" + phone;

                    // Send the registration message to the server
                    ps.println(registrationMessage);

                    try {
                        // Wait for a response from the server
                        String serverResponse = dis.readLine();

                        // Process the server response
                        switch (serverResponse) {
                            case "RegistrationSuccessful":
                                // Show success message
                                Alert successAlert = new Alert(AlertType.INFORMATION);
                                successAlert.setTitle("Registration Successful");
                                successAlert.setHeaderText(null);
                                successAlert.setContentText("Registration successful!");
                                successAlert.showAndWait();
                                userNameRegister.clear();
                                userNamelRegister.clear();
                                EmailRegister.clear();
                                passwordRegister.clear();
                                phoneRegister.clear();
                                // Handle the transition to the next scene or perform any other actions
                                break;

                            case "UserExists":
                                // Show failure message for existing user
                                Alert existingUserAlert = new Alert(AlertType.ERROR);
                                existingUserAlert.setTitle("Registration Failed");
                                existingUserAlert.setHeaderText(null);
                                existingUserAlert.setContentText("User already exists. Please choose a different Email.");
                                existingUserAlert.showAndWait();
                                break;
                            case "PasswordTooShort":
                                Alert PasswordTooShort = new Alert(AlertType.ERROR);
                                PasswordTooShort.setTitle("Registration Failed");
                                PasswordTooShort.setHeaderText(null);
                                PasswordTooShort.setContentText("Please choose a Strong Password.");
                                PasswordTooShort.showAndWait();
                                break;
                            case "InvalidEmail":
                                Alert InvalidEmail = new Alert(AlertType.ERROR);
                                InvalidEmail.setTitle("Registration Failed");
                                InvalidEmail.setHeaderText(null);
                                InvalidEmail.setContentText("Please enter a valid email.");
                                InvalidEmail.showAndWait();
                                break;

                            default:
                                // Show a generic failure message for other cases
                                Alert failureAlert = new Alert(AlertType.ERROR);
                                failureAlert.setTitle("Registration Failed");
                                failureAlert.setHeaderText(null);
                                failureAlert.setContentText("Registration failed. Please try again.");
                                failureAlert.showAndWait();
                                break;
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace(); // Handle the exception appropriately
                    }
                }
            });
        });
        btnCreate.setOnAction(e -> switchForm(e));
        btnLogin0.setOnAction(e -> switchForm(e));

    }

}

//    protected abstract void Login(javafx.event.ActionEvent actionEvent);
//  protected abstract void openRegisterScene(javafx.event.ActionEvent actionEvent);

