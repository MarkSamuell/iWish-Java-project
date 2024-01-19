package prjj;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javax.swing.JOptionPane;

public  class NotiBase extends VBox {

    protected final Label label;
    protected final ListView listView;

 Socket mySocket;
    DataInputStream dis;
    PrintStream ps;
    
      public void startnoti() {
            try {
                String jsonResult = dis.readLine(); 

                Gson gson = new Gson();
                List<String> notiList = gson.fromJson(jsonResult, new TypeToken<List<String>>() {}.getType());

                Platform.runLater(() -> {
                    ObservableList<String> observableNotiList = FXCollections.observableArrayList(notiList);

                    
                    listView.getItems().setAll(observableNotiList);
                });
            } catch (IOException ex) {
                Logger.getLogger(NotiBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    public NotiBase(Curr_User s) {
        
         try {
            mySocket=new Socket("10.145.11.234",5005);
            dis =new DataInputStream(mySocket.getInputStream());
            ps=new PrintStream(mySocket.getOutputStream()); 
            
            String x=s.getUserName();
            
            
              ps.println("shownoti|"+x);
              
              startnoti();
        } catch (SocketException e) {
            
            JOptionPane.showMessageDialog(null,"Faild to connect with the server" +"\n"+"Error Message: "+e.getMessage());

              
            }
        catch (UnknownHostException e) {
                JOptionPane.showMessageDialog(null,"UnknownHost" +"\n"+"Error Message: "+e.getMessage());

            }
        catch (IOException ex) {
                JOptionPane.showMessageDialog(null,"Faild to connect with the server" +"\n"+"Error Message: "+ex.getMessage());
        }
        label = new Label();
        listView = new ListView();

        setPrefHeight(564.0);
        setPrefWidth(610.0);

        label.setPrefHeight(36.0);
        label.setPrefWidth(374.0);
        label.setText("Notifications");
        label.setFont(new Font("Arial Narrow", 18.0));

        listView.setPrefHeight(527.0);
        listView.setPrefWidth(610.0);

        getChildren().add(label);
        getChildren().add(listView);

    }
}