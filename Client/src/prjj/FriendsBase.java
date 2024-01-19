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

public class FriendsBase extends StackPane {

    Socket mySocket;
    DataInputStream dis;
    PrintStream ps;

    class FriendListCell extends ListCell<String> {

        private HBox hbox = new HBox();
        private HBox buttonBox = new HBox(); // HBox for buttons
        private Text friendName = new Text();
        private Button deleteButton = new Button("Delete");
        private Button visitProfileButton = new Button("Visit Profile");

        public FriendListCell() {
            buttonBox.getChildren().addAll(visitProfileButton, deleteButton);
            hbox.getChildren().addAll(buttonBox, friendName);
            HBox.setHgrow(friendName, Priority.ALWAYS);
            hbox.setAlignment(Pos.CENTER_LEFT);

            // Set spacing between friendName and buttons
            hbox.setSpacing(50);

            // Handle delete action for the corresponding friend
            deleteButton.setOnAction(event -> {
                String friend = getItem();
                ps.println("deleteFriend|" + init.currentUserEmail + "|" + friend);
                System.out.println("Delete friend: " + friend);
                frndList.getItems().remove(friend);
                updateFriendList();
                // Call your method to delete the friend, e.g., dataAccessLayer.deleteFriend(friend)
            });

            // Handle visit profile action for the corresponding friend
            visitProfileButton.setOnAction(event -> {
                ps.println("visit");
                String friend = getItem();
                FriendProfileBase.setFriend(friend);
                //FriendProfileBase.label.setText(friend);
                System.out.println("Visit profile of friend: " + friend);
                frameBase.setContent(new FriendProfileBase());
                // Call your method to visit the friend's profile
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

    protected final VBox vBox;
    protected final HBox hBox;
   // protected final VBox vBox0;
    protected final VBox vBox1;
    protected final ListView frndList;
    protected ListView<String> friendListView;
    // String currentUserEmail ;

    public void updateFriendList() {
        try {
            ps.println("friendShow|" + init.currentUserEmail);
            // Assuming the server responds with a list of friends separated by a delimiter
            String response = dis.readLine();
            System.out.println(response);
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

    public FriendsBase() {
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
        updateFriendList();
        vBox = new VBox();
        hBox = new HBox();
      //  vBox0 = new VBox();

        vBox1 = new VBox();
        frndList = new ListView();
        frndList.setCellFactory(param -> new FriendListCell()); // Set custom cell factory

        setPrefHeight(564.0);
        setPrefWidth(610.0);

        vBox.setPrefHeight(500.0);
        vBox.setPrefWidth(600.0);
        frndList.setPrefHeight(500.0);
        frndList.setPrefWidth(600.0);
       // HBox.setHgrow(vBox0, javafx.scene.layout.Priority.ALWAYS);

        HBox.setHgrow(vBox1, javafx.scene.layout.Priority.ALWAYS);

        // vBox0.getChildren().add(addFrndTxt);
        // vBox0.getChildren().add(addFrndBtn);
       // hBox.getChildren().add(vBox0);
        vBox1.getChildren().add(frndList);
        /* vBox1.getChildren().add(frndSreachTxt);
        vBox1.getChildren().add(frndSearchBtn);*/
        hBox.getChildren().add(vBox1);
        vBox.getChildren().add(hBox);
        getChildren().add(vBox);

    }

}
