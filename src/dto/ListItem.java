/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Mark
 */
import com.google.gson.Gson;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javax.swing.JOptionPane;


/**
 *
 * @author Mark
 */
public class ListItem {
    private int item_id;
    private String item_name;
    private int item_price;
    private byte[] img;

    public ListItem(int item_id, String item_name, int item_price)//, //byte[] img)
    {
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_price = item_price;
        //this.img = img;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getItem_price() {
        return item_price;
    }

    public void setItem_price(int item_price) {
        this.item_price = item_price;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public ListItem() {
        
    }
    
    public static ListItem getItem(int item_id) throws IOException{
            Socket mySocket;
            PrintStream ps;
            DataInputStream dis = null;
            ListItem item = null;
            try {
                mySocket=new Socket("10.145.11.234",5005);
                ps=new PrintStream(mySocket.getOutputStream());
                String imgRequest = "ITEMREQ|" + item_id;
                ps.println(imgRequest);
                dis =new DataInputStream(mySocket.getInputStream());
            } catch (SocketException e) {
                
                JOptionPane.showMessageDialog(null,"Faild to connect with the server" +"\n"+"Error Message: "+e.getMessage());
                
            }
            catch (UnknownHostException e) {
                JOptionPane.showMessageDialog(null,"UnknownHost" +"\n"+"Error Message: "+e.getMessage());

            }
            catch (IOException ex) {
                JOptionPane.showMessageDialog(null,"Faild to connect with the server" +"\n"+"Error Message: "+ex.getMessage());
            }
            
            String item_json;
            item_json = dis.readLine();
            Gson gson = new Gson();
            item =  gson.fromJson(item_json, ListItem.class);
        return item;
    }
    
    public static int getProgress(int item_id, String email) throws IOException{
        Socket mySocket;
            PrintStream ps;
            DataInputStream dis = null;
            mySocket=new Socket("10.145.11.234",5005);
            ps=new PrintStream(mySocket.getOutputStream());
            String request = "PROGRESS|" + item_id + email;
            ps.println(request);
            dis =new DataInputStream(mySocket.getInputStream());
            int progress = Integer.parseInt(dis.readLine());
            return progress;
    }
    
   
    public static Image getImage(int item_id) throws IOException, SQLException{
        Socket mySocket;
        PrintStream ps;
        InputStream fis = null;
        try {
            mySocket=new Socket("10.145.11.234",5005);
            ps=new PrintStream(mySocket.getOutputStream());  
            String imgRequest = "IMAGEREQ|" + item_id;
            ps.println(imgRequest);
            fis =mySocket.getInputStream();
            
        } catch (SocketException e) {
            
            JOptionPane.showMessageDialog(null,"Faild to connect with the server" +"\n"+"Error Message: "+e.getMessage());

            }
        catch (UnknownHostException e) {
                JOptionPane.showMessageDialog(null,"UnknownHost" +"\n"+"Error Message: "+e.getMessage());

            }
        catch (IOException ex) {
                JOptionPane.showMessageDialog(null,"Faild to connect with the server" +"\n"+"Error Message: "+ex.getMessage());
        }
        
        int size = fis.available();
        byte[] b = new byte[size];
            for (int i = 0; i< b.length -1; i++){
                b[i]=(byte) fis.read();
            }
            //String outputFilePath = "E:\\iwish\\iwish_1\\client_test\\client\\src\\client\\img2.jpg";
            //Blob blob = new SerialBlob(b);
            //InputStream inputStream = b.getBinaryStream();  
        InputStream inputStream = new ByteArrayInputStream(b);
        Image img = new Image(inputStream);
        fis.close();
        return img;
    }
    
    
    
    
}   
    /*public static void saveImage(InputStream inputStream, String outputPath) {
        try {
            // Create a Path for the output file
            Path outputFilePath = Paths.get(outputPath);

            // Copy the content of the InputStream to the output file
            Files.copy(inputStream, outputFilePath, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Image saved successfully to: " + outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception as appropriate for your use case
        }
    }*/


/* try {
            ListItem item1 = ListItem.getItem(1);
            item_1_text.setText(item1.getItem_name());
            System.out.println("item1_printed");
        } catch (IOException ex) {
            Logger.getLogger(homeBase.class.getName()).log(Level.SEVERE, null, ex);
        }*/


