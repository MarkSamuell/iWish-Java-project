package db;

import com.google.gson.Gson;
import static com.mysql.cj.util.SaslPrep.prepare;
import static db.dataAccessLayer.connect;
import dto.Info;
import dto.ListItem;
import dto.user;
import dto.wish_list;
import java.sql.Connection;
import org.apache.derby.jdbc.ClientDriver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javax.swing.JOptionPane;

public class dataAccessLayer {

    static ResultSet rs;
    Connection conn;
    static String User_email;

    public static void connect() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(dataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/i_wish", "root", "root");
        PreparedStatement pst = con.prepareStatement("SELECT * FROM users", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rs = pst.executeQuery();
    }

    public static String retrieveFriendRequests(String recieverEmail) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/i_wish", "root", "root");
        PreparedStatement pst = con.prepareStatement("SELECT * FROM friend_request WHERE friend_request_email=?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        pst.setString(1, recieverEmail);
        ResultSet rs = pst.executeQuery();
        StringBuilder friendRequestsList = new StringBuilder();

        while (rs.next()) {
            String friendRequest = rs.getString("user_email");
            friendRequestsList.append(friendRequest);
            friendRequestsList.append("|");
        }

        // Close resources (important!)
        rs.close();
        pst.close();
        con.close();

        return friendRequestsList.toString();
    }

    public static int deleteFriend(String curr_user, String friendEmail) {
        int result = -1;
        Connection con = null;
        PreparedStatement pst = null;

        try {
            DriverManager.registerDriver(new ClientDriver());
            con = DriverManager.getConnection("jdbc:mysql://localhost/i_wish", "root", "root");
            pst = con.prepareStatement("DELETE FROM Friends WHERE user_email=? AND friend_email=?");
            pst.setString(1, curr_user);
            pst.setString(2, friendEmail);
            pst.executeUpdate();
            result = 1;  // Deletion successful
        } catch (SQLException ex) {
            Logger.getLogger(dataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Close resources in the finally block to ensure they're closed even if an exception occurs
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    Logger.getLogger(dataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(dataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return result;
    }

    public static String retfriend(String user_email) {
        StringBuilder friendsList = new StringBuilder();

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/i_wish", "root", "root");
                PreparedStatement pst = con.prepareStatement("SELECT * FROM Friends WHERE user_email=?",
                        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

            pst.setString(1, user_email);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String friend = rs.getString("friend_email");
                    friendsList.append(friend);
                    friendsList.append("|");
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(dataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return friendsList.toString();
    }

    static double loginSuccessful;

    public static double Login(String name, String pass) {
        loginSuccessful = -1;

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/i_wish", "root", "root");
                PreparedStatement prepare = con.prepareStatement("SELECT balance FROM Users WHERE user_email=? and user_password=?")) {

            prepare.setString(1, name);
            prepare.setString(2, pass);
            try (ResultSet rs = prepare.executeQuery()) {
                if (rs.next()) {
                    User_email = name;
                    loginSuccessful = rs.getDouble("balance");
                    return loginSuccessful;
                } 
            }

        } catch (SQLException ex) {
            Logger.getLogger(dataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return loginSuccessful;
    }

    static boolean registrationSuccessful;

    public static boolean registerUser(String firstName, String lastName, String email, String password, String credit) {
        registrationSuccessful = false;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/i_wish", "root", "root");
            PreparedStatement prepare = con.prepareStatement("SELECT user_fname FROM Users WHERE user_email=?");

            prepare.setString(1, email);
            ResultSet rs = prepare.executeQuery();

            if (!rs.next()) {
                // User does not exist, proceed with registration
                PreparedStatement pst = con.prepareStatement("insert into users values(?,?,?,?,?,?)");
                pst.setString(1, email);
                pst.setString(2, firstName);
                pst.setString(3, lastName);
                pst.setString(4, password);
                pst.setInt(5, Integer.parseInt(credit));
                pst.setInt(6, 50);
                pst.executeUpdate();

                registrationSuccessful = true;
            }

            con.close(); // Close the connection after use
        } catch (SQLException ex) {
            Logger.getLogger(dataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registrationSuccessful;
    }
    public static boolean areAlreadyFriends(String user1, String user2) throws SQLException {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/i_wish", "root", "root");
                PreparedStatement checkFriends1 = con.prepareStatement("SELECT * FROM Friends WHERE user_email=? AND friend_email=?");
                PreparedStatement checkFriends2 = con.prepareStatement("SELECT * FROM Friends WHERE user_email=? AND friend_email=?")) {

            checkFriends1.setString(1, user1);
            checkFriends1.setString(2, user2);
            ResultSet friendsResult1 = checkFriends1.executeQuery();

            checkFriends2.setString(1, user2);
            checkFriends2.setString(2, user1);
            ResultSet friendsResult2 = checkFriends2.executeQuery();

            // Return true if either direction indicates friendship
            return friendsResult1.next() || friendsResult2.next();
        }
    }

    public static boolean doesUserExist(String userEmail) throws SQLException {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/i_wish", "root", "root");
                PreparedStatement checkUser = con.prepareStatement("SELECT * FROM Users WHERE user_email=?")) {

            checkUser.setString(1, userEmail);
            try (ResultSet userResult = checkUser.executeQuery()) {
                return userResult.next();
            }
        }
    }

    public static boolean addFriendRequestToDatabase(String senderEmail, String receiverEmail) throws SQLException {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/i_wish", "root", "root")) {
            // Check if the friend request already exists
            boolean requestExists = doesFriendRequestExist(senderEmail, receiverEmail);

            if (!requestExists) {
                // Friend request doesn't exist, proceed to add it to the database
                try (PreparedStatement addRequest = con.prepareStatement("INSERT INTO friend_request (user_email, friend_request_email) VALUES (?, ?)");
                        PreparedStatement notification = con.prepareStatement("INSERT INTO notifications(user_email,from_email,noti) VALUES(?,?,?)")) {

                    // Set parameters for the first PreparedStatement
                    addRequest.setString(1, senderEmail);
                    addRequest.setString(2, receiverEmail);
                    addRequest.executeUpdate();

                    // Set parameters for the second PreparedStatement
                    String notify = senderEmail + " has sent you a friend request ";
                    notification.setString(1, receiverEmail);
                    notification.setString(2, senderEmail);
                    notification.setString(3, notify);
                    notification.executeUpdate();

                    return true;
                }
            } else {
                // Friend request already exists
                return false;
            }
        }
    }

    public static boolean doesFriendRequestExist(String senderEmail, String receiverEmail) throws SQLException {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/i_wish", "root", "root");
                PreparedStatement checkRequest = con.prepareStatement("SELECT * FROM friend_request WHERE user_email=? AND friend_request_email=?")) {

            checkRequest.setString(1, senderEmail);
            checkRequest.setString(2, receiverEmail);

            try (ResultSet existingRequest = checkRequest.executeQuery()) {
                return existingRequest.next();
            }
        }
    }

    public static void acceptFriendRequest(String senderEmail, String receiverEmail) throws SQLException {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/i_wish", "root", "root")) {
            // Accept the friend request
            try (PreparedStatement acceptRequest = con.prepareStatement("INSERT INTO friends (user_email, friend_email) VALUES (?, ?)")) {
                acceptRequest.setString(1, senderEmail);
                acceptRequest.setString(2, receiverEmail);
                acceptRequest.executeUpdate();
            }

            // Add bidirectional relationship (friendEmail, curr_user)
            try (PreparedStatement acceptRequestReverse = con.prepareStatement("INSERT INTO friends (user_email, friend_email) VALUES (?, ?)")) {
                acceptRequestReverse.setString(1, receiverEmail);
                acceptRequestReverse.setString(2, senderEmail);
                acceptRequestReverse.executeUpdate();
            }

            // Delete the request from friend_request table
            try (PreparedStatement deleteRequest = con.prepareStatement("DELETE FROM friend_request WHERE user_email=? AND friend_request_email=?")) {
                deleteRequest.setString(1, senderEmail);
                deleteRequest.setString(2, receiverEmail);
                deleteRequest.executeUpdate();
            }

            try (PreparedStatement notification = con.prepareStatement("INSERT INTO notifications(user_email,from_email,noti) VALUES(?,?,?)")) {
                String notify = receiverEmail + " has accepted to your friend request ";
                notification.setString(1, senderEmail);
                notification.setString(2, receiverEmail);
                notification.setString(3, notify);
                notification.executeUpdate();
            }
        }
    }

    public static int update_ContributionAmout(String emails, int item_id, double amount) {
        int result = -1;

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/i_wish", "root", "root");
                PreparedStatement pst = con.prepareStatement("UPDATE user_wish_list SET current_contribution=? WHERE user_email=? AND item_id=?")) {

            pst.setDouble(1, amount);
            pst.setString(2, emails);
            pst.setInt(3, item_id);
            pst.executeUpdate();
            result = 1;  // Update successful

        } catch (SQLException ex) {
            Logger.getLogger(dataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public static int update_Contribution(String fromemails, String toemail, int item_id, double amount) {
        int result = -1;

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/i_wish", "root", "root");
                PreparedStatement pst = con.prepareStatement("INSERT INTO contributions VALUES(?,?,?,?)")) {

            pst.setDouble(4, amount);
            pst.setString(1, fromemails);
            pst.setString(2, toemail);
            pst.setInt(3, item_id);
            pst.executeUpdate();
            result = 1;  // Insert successful

        } catch (SQLException ex) {
            Logger.getLogger(dataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public static int update_notification(String fromemails, String toemail, String item_name, double currentContri) {
        int result = -1;

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/i_wish", "root", "root");
                PreparedStatement pst = con.prepareStatement("INSERT INTO notifications(user_email,from_email,noti) VALUES(?,?,?)")) {

            String notify = fromemails + " has contributed to your item " + item_name + " by " + Double.toString(currentContri);
            pst.setString(2, fromemails);
            pst.setString(1, toemail);
            pst.setString(3, notify);
            pst.executeUpdate();
            result = 1;  // Insert successful

        } catch (SQLException ex) {
            Logger.getLogger(dataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public static int Already_Bought(String emails, int item_id) {
        int result = -1;

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/i_wish", "root", "root");
                PreparedStatement pst1 = con.prepareStatement("DELETE FROM user_wish_list WHERE user_email=? AND item_id=?");
                PreparedStatement pst = con.prepareStatement("INSERT INTO user_owned_list VALUES(?,?)")) {

            pst1.setString(1, emails);
            pst1.setInt(2, item_id);
            pst.setString(1, emails);
            pst.setInt(2, item_id);
            pst.executeUpdate();
            pst1.executeUpdate();
            result = 1;  // Operations successful

        } catch (SQLException ex) {
            Logger.getLogger(dataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public static int updateBalance(double contributionAmount, String email) {
        int result = -1;

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/i_wish", "root", "root");
                PreparedStatement pst = con.prepareStatement("UPDATE users SET balance=? WHERE user_email=?")) {

            pst.setDouble(1, contributionAmount);
            pst.setString(2, email);
            pst.executeUpdate();
            result = 1;  // Update successful

        } catch (SQLException ex) {
            Logger.getLogger(dataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public static ResultSet retNoti(String s) throws SQLException {

        ResultSet getComment;
        DriverManager.registerDriver(new ClientDriver());
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/i_wish", "root", "root");
        PreparedStatement pst1 = con.prepareStatement("select noti from notifications where user_email=?");
        pst1.setString(1, s);
        ResultSet resultSet = pst1.executeQuery();

        getComment = resultSet;

        return getComment;
    }

    public static int addbalance(double s, String y) {
        int rowsAffected = -1;

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/i_wish", "root", "root");
                PreparedStatement pst = con.prepareStatement("UPDATE users SET balance = balance + ? WHERE user_email = ?")) {

            pst.setDouble(1, s);
            pst.setString(2, y);

            rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
            } else {
                rowsAffected = -1;
            }

        } catch (SQLException ex) {
            Logger.getLogger(dataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowsAffected;
    }

    public static int updateinfo(String firstName, String lastName, String email, String password, int credit, double balance) throws SQLException {
        int result = -1;

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/i_wish", "root", "root");
                PreparedStatement pst = con.prepareStatement("UPDATE users SET user_fname=?, user_lname=?, user_password=?, user_credit=?, balance=? WHERE user_email=?")) {

            pst.setString(1, firstName);
            pst.setString(2, lastName);
            pst.setString(3, password);
            pst.setInt(4, credit);
            pst.setDouble(5, balance);
            pst.setString(6, email);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
            } else {
            }

        } catch (SQLException ex) {
            Logger.getLogger(dataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public static void addcomment(String email, String c,String e) throws SQLException {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/i_wish", "root", "root");
                PreparedStatement pst = con.prepareStatement("INSERT INTO comments VALUES(?,?)")) {
            
            String x= e+": "+ c;
            pst.setString(1, email);
            pst.setString(2, x);
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(dataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
            // Handle the exception or rethrow if needed
            throw ex;
        }
    }

    public static ResultSet retcomment(String s) throws SQLException {

        ResultSet getComment;
        DriverManager.registerDriver(new ClientDriver());
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/i_wish", "root", "root");
        PreparedStatement pst1 = con.prepareStatement("select comment from comments where user_email=?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        pst1.setString(1, s);
        ResultSet resultSet = pst1.executeQuery();

        getComment = resultSet;

        return getComment;
    }

    public static Info retinfo(String s) throws SQLException {
        DriverManager.registerDriver(new ClientDriver());

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/i_wish", "root", "root");
                PreparedStatement pst1 = con.prepareStatement("SELECT * FROM users WHERE user_email=?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

            pst1.setString(1, s);
            try (ResultSet resultSet = pst1.executeQuery()) {
                if (resultSet.next()) {
                    return new Info(
                            resultSet.getString("user_fname"),
                            resultSet.getString("user_lname"),
                            resultSet.getString("user_email"),
                            resultSet.getString("user_password"),
                            resultSet.getInt("user_credit"),
                            resultSet.getDouble("balance")
                    );
                } else {
                    return null;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(dataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
            // Handle the exception or rethrow if needed
            throw ex;
        }
    }

    public static ArrayList<wish_list> retfriendProfile(String user_email) throws SQLException {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/i_wish", "root", "root");
                PreparedStatement pst = con.prepareStatement("SELECT u.user_email, i.item_id, i.item_name, i.item_price, u.current_contribution FROM user_wish_list u JOIN items i ON u.item_id = i.item_id WHERE u.user_email=?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

            pst.setString(1, user_email);
            try (ResultSet rs = pst.executeQuery()) {
                ArrayList<wish_list> wishlists = new ArrayList<>();
                while (rs.next()) {
                    wish_list wishlist = new wish_list(
                            rs.getString("user_email"),
                            rs.getInt("item_id"),
                            rs.getString("item_name"),
                            rs.getInt("item_price"),
                            rs.getInt("current_contribution")
                    );
                    wishlists.add(wishlist);
                }
                // No need to close resources explicitly; try-with-resources will take care of it
                return wishlists;
            }

        } catch (SQLException ex) {
            Logger.getLogger(dataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
            // Handle the exception or rethrow if needed
            throw ex;
        }
    }

    public static void rejectFriendRequest(String senderEmail, String recieverEmail) throws SQLException {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/i_wish", "root", "root");
                PreparedStatement deleteRequest = con.prepareStatement("DELETE FROM friend_request WHERE user_email=? AND friend_request_email=?");
                PreparedStatement notification = con.prepareStatement("INSERT INTO notifications(user_email,from_email,noti) VALUES(?,?,?)")) {

            // Set parameters for deleteRequest
            deleteRequest.setString(1, senderEmail);
            deleteRequest.setString(2, recieverEmail);
            deleteRequest.executeUpdate();

            String notify = recieverEmail + " has rejected to your friend request ";
            notification.setString(1, senderEmail);
            notification.setString(2, recieverEmail);
            notification.setString(3, notify);
            notification.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(dataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
            // Handle the exception or rethrow if needed
            throw ex;
        }
    }

  public static String getItem(int item_id) throws SQLException {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/i_wish", "root", "root");
                PreparedStatement pst = con.prepareStatement("SELECT * FROM items WHERE item_id = ?")) {

            pst.setInt(1, item_id);

            try (ResultSet rs = pst.executeQuery()) {
                ListItem item = new ListItem();
                if (rs.next()) {
                    item.setItem_id(rs.getInt("item_id"));
                    item.setItem_name(rs.getString("item_name"));
                    item.setItem_price(rs.getInt("item_price"));
                }

                Gson gson = new Gson();
                String json = gson.toJson(item);
                return json;
            }

        } catch (SQLException ex) {
            Logger.getLogger(dataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
            // Handle the exception or rethrow if needed
            throw ex;
        }
    }

    public static String setWish(int item_id, String email) throws SQLException {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/i_wish", "root", "root");
                PreparedStatement pst = con.prepareStatement("SELECT * FROM items WHERE item_id = ?");
                PreparedStatement pst_setWish = con.prepareStatement("INSERT INTO user_wish_list (user_email, item_id, current_contribution) VALUES ((SELECT user_email FROM users WHERE user_email = ?), (SELECT item_id FROM items WHERE item_id = ?), 0.05) ON DUPLICATE KEY UPDATE user_email = user_email, item_id = item_id")) {

            pst.setInt(1, item_id);

            try (ResultSet rs = pst.executeQuery()) {
                ListItem item = new ListItem();
                if (rs.next()) {
                    item.setItem_id(rs.getInt("item_id"));
                    item.setItem_name(rs.getString("item_name"));
                    item.setItem_price(rs.getInt("item_price"));
                }

                Gson gson = new Gson();
                String json = gson.toJson(item);

                pst_setWish.setString(1, email);
                pst_setWish.setInt(2, item_id);
                int i = pst_setWish.executeUpdate();
                

                return json;
            }

        } catch (SQLException ex) {
            Logger.getLogger(dataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
            // Handle the exception or rethrow if needed
            throw ex;
        }
    }

    public static void rmvWish(int item_id, String email) throws SQLException {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/i_wish", "root", "root");
                PreparedStatement pst = con.prepareStatement("DELETE FROM user_wish_list WHERE user_email = ? AND item_id = ?")) {

            pst.setString(1, email);
            pst.setInt(2, item_id);

            int i = pst.executeUpdate();
            

        } catch (SQLException ex) {
            Logger.getLogger(dataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
            // Handle the exception or rethrow if needed
            throw ex;
        }
    }

    public static String getAllWishes(String email) {
        String allWishes = "";
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/i_wish", "root", "root");
                PreparedStatement pst = con.prepareStatement("SELECT * FROM user_wish_list WHERE user_email = ?", ResultSet.TYPE_SCROLL_SENSITIVE)) {

            pst.setString(1, email);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    allWishes += rs.getInt("item_id") + ":" + rs.getInt("current_contribution") + ",";
                }
            }

        } catch (SQLException ex) {
            allWishes = "";
        }
        return allWishes;
    }

    public static String getAllOwned(String email) {
        String allOwned = "";
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/i_wish", "root", "root");
                PreparedStatement pst = con.prepareStatement("SELECT * FROM user_owned_list WHERE user_email = ?", ResultSet.TYPE_SCROLL_SENSITIVE)) {

            pst.setString(1, email);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    allOwned += rs.getInt("item_id") + ",";
                }
            }

        } catch (SQLException ex) {
            allOwned = "";
        }
        return allOwned;
    }

}
