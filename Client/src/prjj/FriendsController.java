/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prjj;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;



/**
 * FXML Controller class
 *
 * @author kanos
 */

public class FriendsController implements Initializable {
    
    

    @FXML
    private StackPane contentPane;
    @FXML
    private TextField addFrndTxt;
    @FXML
    private Button addFrndBtn;
    @FXML
    private ListView<String> frndReqList;
    @FXML
    private ListView<String> frndList;
    @FXML
    private TextField frndSreachTxt;
    @FXML
    private Button frndSearchBtn;
    @FXML
    private ListView<String> frndWL;

    private void addFriend() {
        // Implement logic to add a friend based on the input in addFrndTxt
        String newFriend = addFrndTxt.getText();
        // Perform add friend logic and update frndList accordingly
        frndList.getItems().add(newFriend);

        // Clear the input field after adding a friend
        addFrndTxt.clear();
    }

    private void searchFriends() {
        // Implement logic to search for friends based on the input in frndSearchTxt
        String searchTerm = frndSreachTxt.getText();
        // Perform search logic and update frndList accordingly
        // For simplicity, we'll just filter the existing list in this example
        frndList.getItems().clear(); // Clear previous items
        frndList.getItems().addAll("Friend1", "Friend2", "Friend3"); // Replace with your logic
    }

    // Add more methods for handling other actions as needed
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
