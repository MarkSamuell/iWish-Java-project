package prjj;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public  class ProfileView extends VBox {

    protected final HBox hBox;
    protected final Label label;
    protected final HBox hBox0;
    protected final Label label0;
    protected final TextField firstNameTFP;
    protected final HBox hBox1;
    protected final Label label1;
    protected final TextField lastNameTFP;
    protected final HBox hBox2;
    protected final Label label2;
    protected final TextField emailTFP;
    protected final HBox hBox3;
    protected final Label label3;
    protected final TextField passwordTFP;
    protected final HBox hBox4;
    protected final Label label4;
    protected final TextField creditTFP;
    protected final HBox hBox5;
    protected final Label label5;
    protected final TextField balanceTFP;
    protected final HBox hBox6;
    protected final Label label6;
    protected final TextField addToWalledTFP;
    protected final Button addToWalledBtn;
    protected final FontAwesomeIcon fontAwesomeIcon;
    protected final HBox hBox7;
    protected final Label label7;
    protected final Button updateBtn;

   Socket mySocket;
    DataInputStream dis;
    PrintStream ps;
    
     public void startinfo(){
      try {
            String response=dis.readLine();
            System.out.println(response);
            System.out.println("Response from server: " + response);

            
            String[] parts = response.split("\\|");
            if (parts.length >= 6) {
                
                String firstName = parts[0];
                String lastName = parts[1];
                String email = parts[2];
                String password = parts[3];
                String credit = parts[4];
                String balance = parts[5];
         
            
            firstNameTFP.setText(firstName);
            lastNameTFP.setText(lastName);
            emailTFP.setText(email);
            passwordTFP.setText(password);
            creditTFP.setText(credit);
            balanceTFP.setText(balance);}
             else {
                    // Handle the case where the response does not have enough elements.
                    System.out.println("Invalid response format");
                    System.out.println("Number of elements in response: " + parts.length);
                  //  System.out.println( parts[0]+parts[1]+parts[2]+parts[3]+parts[4]+parts[5]);

    

                }
            
        } catch (IOException ex) {
            Logger.getLogger(ProfileView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ProfileView(Curr_User s) {
        
         try {
            mySocket=new Socket("10.145.11.234",5005);
             dis =new DataInputStream(mySocket.getInputStream());
            ps=new PrintStream(mySocket.getOutputStream());  
            String x=s.getUserName();
            ps.println("showinfo|"+x);
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(ProfileView.class.getName()).log(Level.SEVERE, null, ex);
        }

        hBox = new HBox();
        label = new Label();
        hBox0 = new HBox();
        label0 = new Label();
        firstNameTFP = new TextField();
        hBox1 = new HBox();
        label1 = new Label();
        lastNameTFP = new TextField();
        hBox2 = new HBox();
        label2 = new Label();
        emailTFP = new TextField();
        hBox3 = new HBox();
        label3 = new Label();
        passwordTFP = new TextField();
        hBox4 = new HBox();
        label4 = new Label();
        creditTFP = new TextField();
        hBox5 = new HBox();
        label5 = new Label();
        balanceTFP = new TextField();
        hBox6 = new HBox();
        label6 = new Label();
        addToWalledTFP = new TextField();
        addToWalledBtn = new Button();
        fontAwesomeIcon = new FontAwesomeIcon();
        fontAwesomeIcon.setGlyphName("PLUS");
        hBox7 = new HBox();
        label7 = new Label();
        updateBtn = new Button();

        setPrefHeight(564.0);
        setPrefWidth(610.0);

        hBox.setPrefHeight(100.0);
        hBox.setPrefWidth(200.0);

        label.setPrefHeight(53.0);
        label.setPrefWidth(376.0);
        label.setText(" Personal Info");
        label.setUnderline(true);
        label.setFont(new Font("Berlin Sans FB", 27.0));

        hBox0.setPrefHeight(100.0);
        hBox0.setPrefWidth(200.0);

        label0.setPrefWidth(100.0);
        label0.setText("First Name");

        hBox1.setPrefHeight(100.0);
        hBox1.setPrefWidth(200.0);

        label1.setPrefWidth(100.0);
        label1.setText("Last Name");

        hBox2.setPrefHeight(100.0);
        hBox2.setPrefWidth(200.0);

        label2.setPrefWidth(100.0);
        label2.setText("Email");

        hBox3.setPrefHeight(100.0);
        hBox3.setPrefWidth(200.0);

        label3.setPrefWidth(100.0);
        label3.setText("Password");

        hBox4.setPrefHeight(100.0);
        hBox4.setPrefWidth(200.0);

        label4.setPrefWidth(100.0);
        label4.setText("Credit");

        hBox5.setPrefHeight(100.0);
        hBox5.setPrefWidth(200.0);

        label5.setPrefWidth(100.0);
        label5.setText("Balance");

        balanceTFP.setEditable(false);

        hBox6.setPrefHeight(100.0);
        hBox6.setPrefWidth(200.0);

        label6.setPrefWidth(100.0);
        label6.setText("Add To Wallet");

        addToWalledBtn.setMnemonicParsing(false);
        addToWalledBtn.setPrefHeight(31.0);
        addToWalledBtn.setPrefWidth(41.0);
        addToWalledBtn.setStyle("-fx-background-color: transparent;");

        addToWalledBtn.setGraphic(fontAwesomeIcon);

        hBox7.setPrefHeight(87.0);
        hBox7.setPrefWidth(361.0);

        label7.setPrefHeight(21.0);
        label7.setPrefWidth(137.0);

        updateBtn.setAlignment(javafx.geometry.Pos.CENTER);
        updateBtn.setMnemonicParsing(false);
        updateBtn.setPrefHeight(31.0);
        updateBtn.setPrefWidth(112.0);
        updateBtn.setStyle("-fx-background-color: transparent;");
        updateBtn.setText("Update");
        updateBtn.setUnderline(true);
        updateBtn.setFont(new Font("Elephant Italic", 15.0));

        hBox.getChildren().add(label);
        getChildren().add(hBox);
        hBox0.getChildren().add(label0);
        hBox0.getChildren().add(firstNameTFP);
        getChildren().add(hBox0);
        hBox1.getChildren().add(label1);
        hBox1.getChildren().add(lastNameTFP);
        getChildren().add(hBox1);
        hBox2.getChildren().add(label2);
        hBox2.getChildren().add(emailTFP);
        getChildren().add(hBox2);
        hBox3.getChildren().add(label3);
        hBox3.getChildren().add(passwordTFP);
        getChildren().add(hBox3);
        hBox4.getChildren().add(label4);
        hBox4.getChildren().add(creditTFP);
        getChildren().add(hBox4);
        hBox5.getChildren().add(label5);
        hBox5.getChildren().add(balanceTFP);
        getChildren().add(hBox5);
        hBox6.getChildren().add(label6);
        hBox6.getChildren().add(addToWalledTFP);
        hBox6.getChildren().add(addToWalledBtn);
        getChildren().add(hBox6);
        hBox7.getChildren().add(label7);
        hBox7.getChildren().add(updateBtn);
        getChildren().add(hBox7);
        
         startinfo();
        
        
        
       
        
        
        updateBtn.setOnAction(e -> {
                // Retrieve the registration data
                String firstName = firstNameTFP.getText();
                String lastName = lastNameTFP.getText();
                String email = emailTFP.getText();
                String password = passwordTFP.getText();
                int credit =Integer.parseInt(creditTFP.getText());
                int balance = (int) Double.parseDouble(balanceTFP.getText());

//int balance =Integer.parseInt(balanceTFP.getText());
                // the message to be sent to the server
                String updateinfoMessage = "updateinfo|" + firstName + "|" + lastName + "|" + email+'|'+password+'|'+credit+"|"+balance;
                // Send the registration message to the server
                ps.println(updateinfoMessage);
                startinfo();
                
            });
        
         addToWalledBtn.setOnAction(e -> {
                // Retrieve the registration data
                String balance = addToWalledTFP.getText();
                String x=s.getUserName();
                
                String newBalance = "addbalance|" + balance+"|"+x ;
                // Send the registration message to the server
                ps.println(newBalance);
               
                
                 String balanceText = balanceTFP.getText();
                 String addToWalletText = addToWalledTFP.getText();

                double balance1 = Double.parseDouble(balanceText);
                double addToWallet = Double.parseDouble(addToWalletText);

                        // Perform your operations with balance and addToWallet
                double result = balance1 + addToWallet;
                init.balance=result;
                        // Set the result back to the UI
                balanceTFP.setText(String.valueOf(result));
                addToWalledTFP.clear();
                
           
                
            });

    }
}