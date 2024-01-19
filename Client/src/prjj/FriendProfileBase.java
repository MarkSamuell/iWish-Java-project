package prjj;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import static dto.ListItem.getItem;
import dto.wish_list;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;

public class FriendProfileBase extends AnchorPane {
    Socket mySocket;
    DataInputStream dis;
    PrintStream ps;
    String response;
    protected final FontAwesomeIcon fontAwesomeIcon;
    protected final Label label;
    protected final VBox vBox;
    public static double contributionAmount;
    protected final ListView<wish_list> frndList;
    protected final Label label0;
    protected final VBox vBox0;
    protected final ListView<String> frndList1;
    protected final TextField frndSreachTxt;
    protected final Button frndSearchBtn;
    protected final Label label1;
    private ListView<wish_list> wishListListView;
    public static String friend;
     public static void setFriend(String friendEmail) {
        friend = friendEmail;
        // Perform any additional actions if needed
    }
        public  ProgressBar progressBar;

    private  class WishListCell extends ListCell<wish_list> {
        private final Label itemName;
        private final Label itemPrice;
        private final Label currentContri;
        private final Button contributeButton;
        private final VBox wishListBox;
        private final ProgressBar progressBar; // Declare the progressBar here

        public WishListCell(ProgressBar progressBar) {
            itemName = new Label();
            this.progressBar = new ProgressBar(); // Initialize the progressBar here
            itemPrice = new Label();
            currentContri = new Label();
            contributeButton = new Button("Contribute");

            contributeButton.setOnAction(event -> contributeToItem(getItem()));

            wishListBox = new VBox(5,
                    itemName,
                    itemPrice,
                    currentContri,
                    this.progressBar, // Use the instance variable
                    contributeButton);

            setGraphic(wishListBox);
        }

        @Override
        protected void updateItem(wish_list item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                itemName.setText("Item Name: " + item.item_name());
                itemPrice.setText("Item Price: " + item.item_price());
                currentContri.setText("Current contribution: " + item.current_contribution());
                double newProgress = item.current_contribution() / item.item_price();
                progressBar.setProgress(newProgress);
                setGraphic(wishListBox);
            }
        }
    }

    public FriendProfileBase() {
        try {
            mySocket = new Socket("10.145.11.234", 5005);
            dis = new DataInputStream(mySocket.getInputStream());
            ps = new PrintStream(mySocket.getOutputStream());
        } catch (SocketException e) {
            showErrorAlert("Failed to connect with the server\nError Message: " + e.getMessage());
        } catch (UnknownHostException e) {
            showErrorAlert("Unknown Host\nError Message: " + e.getMessage());
        } catch (IOException ex) {
            showErrorAlert("Failed to connect with the server\nError Message: " + ex.getMessage());
        }

        ps.println("friendProfile|" + friend);

        fontAwesomeIcon = new FontAwesomeIcon();
        label = new Label();
        vBox = new VBox();
        frndList = new ListView<>();
        label0 = new Label();
        vBox0 = new VBox();
        frndList1 = new ListView<>();
        frndSreachTxt = new TextField();
        frndSearchBtn = new Button();
        label1 = new Label();

        setId("AnchorPane");
        setPrefHeight(590.0);
        setPrefWidth(678.0);

        fontAwesomeIcon.setLayoutX(83.0);
        fontAwesomeIcon.setLayoutY(58.0);
        fontAwesomeIcon.setGlyphName("USER");
        fontAwesomeIcon.setSize("4em");
        fontAwesomeIcon.setFill(Paint.valueOf("#fc3468"));
        label.setLayoutX(281.0);
        label.setLayoutY(52.0);
        label.setText("Wish list");
        label.setFont(new Font("System Bold", 18.0));

        vBox.setLayoutX(76.0);
        vBox.setLayoutY(86.0);
        vBox.setPrefHeight(369.0);
        vBox.setPrefWidth(526.0);

        frndList.setPrefHeight(362.0);
        frndList.setPrefWidth(560.0);

        label0.setPrefHeight(25.0);
        label0.setPrefWidth(61.0);
        label0.setText("Comment");

        vBox0.setLayoutX(76.0);
        vBox0.setLayoutY(459.0);
        vBox0.setPrefHeight(139.0);
        vBox0.setPrefWidth(526.0);

        frndList1.setPrefHeight(70.0);
        frndList1.setPrefWidth(560.0);

        frndSearchBtn.setMnemonicParsing(false);
        frndSearchBtn.setText("Comment");
        

        label1.setLayoutX(83.0);
        label1.setLayoutY(65.0);
        label1.setPrefHeight(25.0);
        label1.setPrefWidth(100.0);
        label1.setText(friend);

        getChildren().add(fontAwesomeIcon);
        getChildren().add(label);
        vBox.getChildren().add(frndList);
        vBox.getChildren().add(label0);
        getChildren().add(vBox);
        vBox0.getChildren().add(frndList1);
        vBox0.getChildren().add(frndSreachTxt);
        vBox0.getChildren().add(frndSearchBtn);
        getChildren().add(vBox0);
        getChildren().add(label1);

        try {
            response = dis.readLine();
            handleFriendProfileResponse(response);

            // Send a request to the server for comments
            ps.println("showcomments|" + friend);

            // Receive and handle the comments response
            String commentsResponse = dis.readLine();
            handleCommentsResponse(commentsResponse);

        } catch (IOException ex) {
            Logger.getLogger(FriendProfileBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        //frndList.setCellFactory(param -> new WishListCell(progressBar));
        frndSearchBtn.setOnAction(e -> {
            String comment = frndSreachTxt.getText();
            String commentsMessage = "comment|" +friend+ "|"+comment+"|"+init.currentUserEmail;

            // Send the comment to the server
            ps.println(commentsMessage);

            // Assuming you want to display comments in frndList1
            ObservableList<String> observableComments = frndList1.getItems();
            observableComments.add(comment);

            // Clear the comment text field
            frndSreachTxt.clear();
        });

        // Rest of your initialization code (unchanged)
        frndList.setCellFactory(param -> new WishListCell(progressBar));
    
    }
    

    private void contributeToItem(wish_list item) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Contribute to Item");
        dialog.setHeaderText("Enter Contribution Amount");
        dialog.setContentText("Amount:");

        if (item.current_contribution() >= item.item_price()) {
            ps.println("Item Already Bought|" + item.user_email() + "|" + item.item_id());
            showInvalidContributionAlert("This item has already been fully funded. You cannot contribute further.");
        } else {
            dialog.showAndWait().ifPresent(contribution -> {
                try {
                    double contributionAmount = Double.parseDouble(contribution);
                    if (item.item_price() > item.current_contribution() && init.balance >= contributionAmount  && contributionAmount>0) {
                        if (item.item_price() >= (item.current_contribution() + contributionAmount)) {
                            updateProgressBar(item, contributionAmount);
                            init.balance -= contributionAmount;
                            ps.println("updateBlance|" + init.balance + "|" + init.currentUserEmail);
                        } else {
                            showInvalidContributionAlert("Your contribution cannot exceed the remaining amount needed to fully fund this item.");
                        }
                    } else{
                        showInvalidContributionAlert("Invalid contribution amount or insufficient balance.");
                    }
                } catch (NumberFormatException e) {
                    showInvalidContributionAlert("Invalid input. Please enter a numeric value.");
                }
            });
        }
    }
    

    private void showInvalidContributionAlert(String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Invalid Contribution Amount");
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public void updateProgressBar(wish_list item, double contributionAmount) {
        ps.println("update_Contribution_Amount|" + item.user_email() + "|" + item.item_id() +
                "|" + (item.current_contribution() + contributionAmount) + "|" + contributionAmount + "|" +
                init.currentUserEmail + "|" + item.item_name());
        double newContribution =item.current_contribution() + contributionAmount;
        item.current_contribution(newContribution);

        //double newContribution =item.current_contribution() + contributionAmount;
        if (item.item_price() == newContribution) {
            ps.println("Item Already Bought|" + item.user_email() + "|" + item.item_id());
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Contribution Successful");
        alert.setHeaderText(null);
        alert.setContentText("Your contribution has been successfully added.");
        alert.showAndWait();

        frndList.refresh();
    }

    private void showErrorAlert(String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    private void handleFriendProfileResponse(String response) {
        Gson gson = new Gson();
        List<wish_list> wishList = gson.fromJson(response, new TypeToken<List<wish_list>>() {}.getType());
        frndList.getItems().addAll(wishList);
        for (wish_list w : wishList) {
            System.out.println(w.item_id() + " " + w.current_contribution());
        }
    }

    private void handleCommentsResponse(String response) {
        Gson gson = new Gson();
        List<String> commentsList = gson.fromJson(response, new TypeToken<List<String>>() {}.getType());
        frndList1.getItems().addAll(commentsList);
        for (String comment : commentsList) {
            System.out.println("Comment: " + comment);
        }
    }
}
