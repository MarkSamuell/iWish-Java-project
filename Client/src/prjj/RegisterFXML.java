package prjj;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

public  class RegisterFXML extends AnchorPane {

    protected final Label lFirstNameR;
    protected final Label lBalanceR;
    protected final Label lLastNameR;
    protected final Label lEmailR;
    protected final Label lPhoneR;
    protected final Button btnRegisterR;
    protected final TextField tFirstNameR;
    protected final TextField tLastNameR;
    protected final TextField tEmailR;
    protected final TextField tBalanceR;
    protected final TextField tPhoneR;
    Socket mySocket;
    DataInputStream dis;
    PrintStream ps;
    public RegisterFXML() { 
      try {
            mySocket=new Socket("10.145.11.234",5005);
            dis =new DataInputStream(mySocket.getInputStream());
            ps=new PrintStream(mySocket.getOutputStream());           
        } catch (SocketException e) {
            
            JOptionPane.showMessageDialog(null,"Faild to connect with the server" +"\n"+"Error Message: "+e.getMessage());

              
            }
        catch (UnknownHostException e) {
                JOptionPane.showMessageDialog(null,"UnknownHost" +"\n"+"Error Message: "+e.getMessage());

            }
        catch (IOException ex) {
                JOptionPane.showMessageDialog(null,"Faild to connect with the server" +"\n"+"Error Message: "+ex.getMessage());
        }
        lFirstNameR = new Label();
        lLastNameR = new Label();
        lBalanceR= new Label();
        lEmailR = new Label();
        lPhoneR = new Label();
        btnRegisterR = new Button();
        tFirstNameR = new TextField();
        tLastNameR = new TextField();
        tEmailR = new TextField();
        tBalanceR = new TextField();
        tPhoneR = new TextField();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        lFirstNameR.setLayoutX(80.0);
        lFirstNameR.setLayoutY(57.0);
        lFirstNameR.setPrefHeight(31.0);
        lFirstNameR.setPrefWidth(71.0);
        lFirstNameR.setText("First Name");
        lFirstNameR.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        lLastNameR.setLayoutX(80.0);
        lLastNameR.setLayoutY(110.0);
        lLastNameR.setPrefHeight(31.0);
        lLastNameR.setPrefWidth(62.0);
        lLastNameR.setText("Last Name");

        lEmailR.setLayoutX(85.0);
        lEmailR.setLayoutY(169.0);
        lEmailR.setPrefHeight(31.0);
        lEmailR.setPrefWidth(62.0);
        lEmailR.setText("Email");

        lPhoneR.setLayoutX(84.0);
        lPhoneR.setLayoutY(228.0);
        lPhoneR.setPrefHeight(31.0);
        lPhoneR.setPrefWidth(62.0);
        lPhoneR.setText("Phone");

        lBalanceR.setLayoutX(83.0);
        lBalanceR.setLayoutY(248.0);
        lBalanceR.setPrefHeight(31.0);
        lBalanceR.setPrefWidth(62.0);
        lBalanceR.setText("Balance");

        btnRegisterR.setLayoutX(260.0);
        btnRegisterR.setLayoutY(309.0);
        btnRegisterR.setMnemonicParsing(false);
        btnRegisterR.setText("Register");

        tFirstNameR.setLayoutX(232.0);
        tFirstNameR.setLayoutY(60.0);

        tLastNameR.setLayoutX(232.0);
        tLastNameR.setLayoutY(113.0);
        
        tBalanceR.setLayoutX(232.0);
        tBalanceR.setLayoutY(300.0);
        
        tEmailR.setLayoutX(232.0);
        tEmailR.setLayoutY(169.0);

        tPhoneR.setLayoutX(232.0);
        tPhoneR.setLayoutY(231.0);

        getChildren().add(lFirstNameR);
        getChildren().add(lLastNameR);
        getChildren().add(lEmailR);
        getChildren().add(lPhoneR);
        getChildren().add(btnRegisterR);
        getChildren().add(tFirstNameR);
        getChildren().add(tLastNameR);
        getChildren().add(tEmailR);
        getChildren().add(tPhoneR);
        getChildren().add(tBalanceR);
        getChildren().add(lBalanceR);

         btnRegisterR.setOnAction(e -> {
            // Retrieve the registration data
            String firstName = tFirstNameR.getText();
            String lastName = tLastNameR.getText();
            String email = tEmailR.getText();
            String phone = tPhoneR.getText();
            int Balance =Integer.parseInt(tBalanceR.getText());
            // the message to be sent to the server
            String registrationMessage = "REGISTER|" + firstName + "|" + lastName + "|" + email + "|" + phone+'|'+Balance;
            // Send the registration message to the server
            ps.println(registrationMessage);
        });

    }
}
