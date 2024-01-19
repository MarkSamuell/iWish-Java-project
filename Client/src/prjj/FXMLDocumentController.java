/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prjj;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Salma Ahmed
 */
public class FXMLDocumentController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void openRegisterScene(ActionEvent event) throws IOException{
         Parent root = FXMLLoader.load(getClass().getResource("/prjj/Register.fxml"));
         stage=(Stage)((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
         stage.show();

    }
    public void openHomeScene(ActionEvent event) throws IOException{
         Parent root = FXMLLoader.load(getClass().getResource("/prjj/home.fxml"));
         stage=(Stage)((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
         stage.show();

    }
    public void openInitScene(ActionEvent event) throws IOException{
         Parent root = FXMLLoader.load(getClass().getResource("/prjj/init.fxml"));
         stage=(Stage)((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
         stage.show();

    }
     public void logout(ActionEvent event) throws IOException{
         Parent root = FXMLLoader.load(getClass().getResource("/prjj/init.fxml"));
         stage=(Stage)((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
         stage.show();

    }
  
}
