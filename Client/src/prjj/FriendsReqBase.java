package prjj;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class FriendsReqBase extends StackPane {

    Socket mySocket;
    DataInputStream dis;
    PrintStream ps;
    protected final VBox vBox;
    protected final HBox hBox;
    protected final VBox vBox0;
    protected final ListView frndList;
    protected final TextField frndAddTxt;
    protected final Button frndAddBtn;
    String currentUser;

    public FriendsReqBase(Curr_User curr_user) {

        try {
            mySocket = new Socket("10.145.11.234", 5005);
            dis = new DataInputStream(mySocket.getInputStream());
            ps = new PrintStream(mySocket.getOutputStream());
            currentUser = curr_user.getUserName();
        } catch (SocketException e) {
            JOptionPane.showMessageDialog(null, "Faild to connect with the server" + "\n" + "Error Message: " + e.getMessage());
        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(null, "UnknownHost" + "\n" + "Error Message: " + e.getMessage());

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Faild to connect with the server" + "\n" + "Error Message: " + ex.getMessage());
        }

        vBox = new VBox();
        hBox = new HBox();
        vBox0 = new VBox();
        frndList = new ListView();
        frndAddTxt = new TextField();
        frndAddBtn = new Button();
        frndList.setCellFactory(param -> new FriendListCell()); // Set custom cell factory

        setPrefHeight(564.0);
        setPrefWidth(610.0);

        vBox.setPrefHeight(200.0);
        vBox.setPrefWidth(100.0);

        HBox.setHgrow(vBox0, javafx.scene.layout.Priority.ALWAYS);

        frndAddBtn.setMnemonicParsing(false);
        frndAddBtn.setText("Add Friends");

        vBox0.getChildren().add(frndList);
        vBox0.getChildren().add(frndAddTxt);
        vBox0.getChildren().add(frndAddBtn);
        hBox.getChildren().add(vBox0);
        vBox.getChildren().add(hBox);
        getChildren().add(vBox);

        frndAddBtn.setOnAction(event -> {
            String friendEmail = frndAddTxt.getText().trim();

            // Check if the friendEmail is not empty and is different from the currentUser
            if (!friendEmail.isEmpty() && !friendEmail.equals(currentUser)) {
                try {
                    ps.println("FriendRequest|" + currentUser + "|" + friendEmail);
                    String serverResponse = dis.readLine();
                    handleFriendRequestResponse(serverResponse);
                } catch (IOException ex) {
                    Logger.getLogger(FriendsBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                // Show an alert or provide feedback to the user about the invalid input
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid friend email address.");
                alert.showAndWait();
            }
        });

        updateFriendList();

    }

    private void handleFriendRequestResponse(String responseMessage) {
        String[] parts = responseMessage.split("\\|");
        String responseType = parts[0];
        String senderEmail = parts[1];
        String recieverEmail = parts[2];

        switch (responseType) {
            case "FriendRequestAlreadyExists":
                showFriendErrorAlert("Friend Request Already Exists", "You already have a pending friend request to " + recieverEmail);
                break;
            case "AlreadyFriends":
                showFriendErrorAlert("Already Friends", "You are already friends with " + recieverEmail);
                break;
            case "FriendNotFound":
                showFriendErrorAlert("Friend Not Found", "No user found with email: " + recieverEmail);
                break;
            case "FriendRequestProcessed":
                showFriendSuccessAlert("Friend Request Processed", "Friend request sent to " + recieverEmail + " successfully.");
                frndAddTxt.clear();
                break;
        }
    }

    private void showFriendErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showFriendSuccessAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void updateFriendList() {
        try {
            ps.println("FriendRequestShow|" + currentUser);
            // Assuming the server responds with a list of friends separated by a delimiter
            String response = dis.readLine();

            // Use Platform.runLater to update UI components on the FX Application Thread
            Platform.runLater(() -> {
                try {
                    String[] friends = response.split("\\|"); // Change the delimiter based on your server response

                    // Clear the friend list in the UI
                    frndList.getItems().clear();

                    // Add friends to the friend list in the UI
                    frndList.getItems().addAll(friends);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            );
        } catch (IOException e) {
            // Handle the exception (e.g., show an error message)
            Platform.runLater(() -> {
                // Show an alert or log the error details
                e.printStackTrace();
            });
        }
    }

    class FriendListCell extends ListCell<String> {

        private HBox hbox = new HBox();
        private HBox buttonBox = new HBox(); // HBox for buttons
        private Text friendName = new Text();
        private Button rejectButton = new Button("Reject");
        private Button acceptButton = new Button("Accept");

        public FriendListCell() {
            buttonBox.getChildren().addAll(acceptButton, rejectButton);
            hbox.getChildren().addAll(buttonBox, friendName);
            HBox.setHgrow(friendName, Priority.ALWAYS);
            hbox.setAlignment(Pos.CENTER_LEFT);

            // Set spacing between friendName and buttons
            hbox.setSpacing(50);

            // Handle delete action for the corresponding friend
            rejectButton.setOnAction(event -> {
                String sender = getItem();
                ps.println("rejectFriend|" + currentUser + "|" + sender);
                frndList.getItems().remove(sender);
                updateFriendList();
            });

            // Handle visit profile action for the corresponding friend
            acceptButton.setOnAction(event -> {
                String sender = getItem();
                ps.println("acceptFriend|" + currentUser + "|" + sender);
                frndList.getItems().remove(sender);
                updateFriendList();
            });
        }

        @Override
        protected void updateItem(String friend, boolean empty) {
            super.updateItem(friend, empty);
            if (empty || friend == null || getItem().isEmpty()) {
                setGraphic(null);
            } else {
                friendName.setText(friend);
                // Check if the friend list is empty and conditionally display buttons
                if (frndList.getItems().isEmpty()) {
                    hbox.getChildren().clear(); // Clear existing content
                } else {
                    hbox.getChildren().setAll(buttonBox, friendName);
                }
                setGraphic(hbox);
            }
        }
    }

}
