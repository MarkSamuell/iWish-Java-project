/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import prjj.homeBase;
import com.google.gson.Gson;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mark
 */
public class WishItem {
    private int item_id;
    private double contribution;
    private String user_email;

    public WishItem(int item_id, double contribution, String user_email) {
        this.item_id = item_id;
        this.contribution = contribution;
        this.user_email = user_email;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public double getContribution() {
        return contribution;
    }

    public void setContribution(double contribution) {
        this.contribution = contribution;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }
    
    public WishItem(){
        
    }
    
    public static ListItem addToWishList(int item_id) throws IOException{
        Socket mySocket;
        PrintStream ps;
        DataInputStream dis = null;
        ListItem item = null;
        try {
                mySocket=new Socket("10.145.11.234",5005);
                ps=new PrintStream(mySocket.getOutputStream());
                String wishRequest = "ADDWISH|" + item_id + "|" + homeBase.curr_email;
                ps.println(wishRequest);
                dis =new DataInputStream(mySocket.getInputStream());
        
    }   catch (IOException ex) {
            Logger.getLogger(WishItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        String item_json;
            item_json = dis.readLine();
            Gson gson = new Gson();
            item =  gson.fromJson(item_json, ListItem.class);
        return item;
}
    public static void removeWish(int item_id, String email) throws IOException{
        Socket mySocket;
        PrintStream ps;
        try {
                mySocket=new Socket("10.145.11.234",5005);
                ps=new PrintStream(mySocket.getOutputStream());
                String wishRequest = "RMVWISH|" + item_id + "|" + email;
                ps.println(wishRequest);
                 }   catch (IOException ex) {
            Logger.getLogger(WishItem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String getAllWishes(String email) throws IOException{
        Socket mySocket;
        PrintStream ps;
        DataInputStream dis = null; 
        mySocket=new Socket("10.145.11.234",5005);
        ps=new PrintStream(mySocket.getOutputStream());
                String request = "ALLWISHES|" + email;
                ps.println(request);
                dis =new DataInputStream(mySocket.getInputStream());
                String allWishes;
                allWishes = dis.readLine();
                return allWishes;
    }
    
    public static String getAllOwned(String email) throws IOException{
        Socket mySocket;
        PrintStream ps;
        DataInputStream dis = null; 
        mySocket=new Socket("10.145.11.234",5005);
        ps=new PrintStream(mySocket.getOutputStream());
                String request = "ALLOWNED|" + email;
                ps.println(request);
                dis =new DataInputStream(mySocket.getInputStream());
                String allOwned;
                allOwned = dis.readLine();
                return allOwned;
    }
}



    




        