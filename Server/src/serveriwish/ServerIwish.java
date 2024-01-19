package serveriwish;

import com.google.gson.Gson;
import db.dataAccessLayer;
import static db.dataAccessLayer.getAllOwned;
import static db.dataAccessLayer.getAllWishes;
import static db.dataAccessLayer.getItem;
import static db.dataAccessLayer.rmvWish;
import static db.dataAccessLayer.setWish;
import dto.Info;
import dto.wish_list;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class ServerIwish extends Application {

    private ServerSocket myServerSocket;

    public static void main(String[] args) {
        new ServerIwish();
    }

    public ServerIwish() {
        try {
            myServerSocket = new ServerSocket(5005);
            while (true) {
                Socket clientSocket = myServerSocket.accept();
                new MainHandler(clientSocket);
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerIwish.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void start(Stage stage) throws Exception {

    }

    class MainHandler extends Thread {

        private DataInputStream dis;
        private PrintStream ps;
        private Vector<MainHandler> clientsVector = new Vector<>();

        public MainHandler(Socket clientSocket) {
            try {
                dis = new DataInputStream(clientSocket.getInputStream());
                ps = new PrintStream(clientSocket.getOutputStream());
                clientsVector.add(this);
                start();
            } catch (IOException ex) {
                Logger.getLogger(MainHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public void run() {
            while (true) {
                try {
                    String message = dis.readLine();

                    if (message.startsWith("register|")) {
                        handleRegistration(message);
                    }else if (message.startsWith("friendShow")) {
                        handleFriendShow(message);
                    } else if (message.startsWith("FriendRequestShow|")) {
                        handleFriendRequestShow(message);
                    } else if (message.startsWith("login|")) {
                        handleLogin(message);
                    } else if (message.startsWith("FriendRequest|")) {
                        handleFriendRequest(message);
                    } else if (message.startsWith("acceptFriend|") || message.startsWith("rejectFriend|")) {
                        handleFriendRequestResponse(message);
                    } else if (message.startsWith("deleteFriend|")) {
                        handleFriendDeletion(message);
                    } else if (message.startsWith("friendProfile|")) {
                        friendProfile(message);
                    } else if (message.startsWith("update_Contribution_Amount")) {
                        update_Contribution_Amount(message);
                    } else if (message.startsWith("Item Already Bought")) {
                        Item_Already_Bought(message);
                    } else if (message.startsWith("updateBlance")) {
                        updateBlance(message);
                    } else if (message.startsWith("updateinfo|")) {
                        handleUpdateInfo(message);
                    } else if (message.startsWith("showinfo")) {
                        handleUpdateL(message);
                    } else if (message.startsWith("addbalance")) {
                        handlebalance(message);
                    } else if (message.startsWith("shownoti")) {
                        handleNoti(message);
                    } else if (message.startsWith("ITEMREQ|")){
                         ItemHandler(message);
                    } else if(message.startsWith("ADDWISH|")){
                         WishesHandler(message);
                    } else if(message.startsWith("RMVWISH|")){
                         WishesRemover (message);
                    } else if(message.startsWith("ALLWISHES|")){
                         GetAllWishes(message);
                    } else if(message.startsWith("ALLOWNED|")){
                         GetAllOwned(message);
                    }
                    else if (message.startsWith("comment|")){
                         handleComment(message);                      
                    }else if (message.startsWith("showcomments")){
                         handleCommentL(message);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(MainHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        public void handleNoti(String s) {
            try {

                String[] parts = s.split("\\|");
                String curr_user = parts[1];

                ResultSet resultSet = dataAccessLayer.retNoti(curr_user);

                List<String> notiList = new ArrayList<>();
                while (resultSet.next()) {
                    notiList.add(resultSet.getString("noti"));
                }

                Gson gson = new Gson();
                String jsonResult = gson.toJson(notiList);

                ps.println(jsonResult);

            } catch (SQLException ex) {
                Logger.getLogger(MainHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public void handlebalance(String s) {

            String[] parts = s.split("\\|");
            String balance = parts[1];
            String curr_user = parts[2];
            double x = Double.parseDouble(balance);
            double c = dataAccessLayer.addbalance(x, curr_user);
        }

        private void handleFriendDeletion(String deletionMessage) {
            String[] parts = deletionMessage.split("\\|");
            String currentUser = parts[1];
            String friendEmail = parts[2];

            dataAccessLayer.deleteFriend(currentUser, friendEmail);
            dataAccessLayer.deleteFriend(friendEmail, currentUser);
        }

        double balance;

        private void handleLogin(String loginMessage) {
            String[] parts = loginMessage.split("\\|");
            String firstName = parts[1];
            String pass = parts[2];
            balance = dataAccessLayer.Login(firstName, pass);
            if (balance != -1) {
                // Send success message to the client
                ps.println("Login successful|" + firstName + "|" + balance);

            } else {
                // Send failure message to the client
                ps.println("Login failed");
            }
        }

        private void handleUpdateInfo(String updateinfomessage) {

            String[] parts = updateinfomessage.split("\\|");
            String firstName = parts[1];
            String lastName = parts[2];
            String email = parts[3];
            String password = parts[4];
            String credit = parts[5];
            String balance = parts[6];

            try {
                dataAccessLayer.updateinfo(firstName, lastName, email, password, Integer.parseInt(credit), Integer.parseInt(balance));
            } catch (SQLException ex) {
                Logger.getLogger(MainHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

            ps.println(firstName + "|" + lastName + "|" + email + "|" + password + "|" + credit);

        }

        private void handleRegistration(String registrationMessage) {
            String[] parts = registrationMessage.split("\\|");
            String fname = parts[1];
            String lname = parts[2];
            String email = parts[3];
            String password = parts[4];
            String credit = parts[5];
            if (password.length() < 8) {
                // Send failure message to the client
                ps.println("PasswordTooShort");
                return;
            } else if (!isValidEmail(email)) {
                // Send failure message to the client
                ps.println("InvalidEmail");
                return;
            } else {
                boolean registrationSuccessful = dataAccessLayer.registerUser(fname, lname, email, password, credit);
                if (registrationSuccessful) {
                    // Send success message to the client
                    ps.println("RegistrationSuccessful");
                } else {
                    // Send failure message to the client
                    ps.println("UserExists");
                }
            }
        }

        private boolean isValidEmail(String email) {
            // Simple email validation using a regular expression
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
            return email.matches(emailRegex);
        }

        private void handleFriendRequestResponse(String responseMessage) {
            String[] parts = responseMessage.split("\\|");
            String responseType = parts[0];
            String recieverEmail = parts[1];
            String senderEmail = parts[2];

            Platform.runLater(() -> {
                if ("acceptFriend".equals(responseType)) {
                    try {
                        // Call DataAccessLayer method to handle friend request acceptance
                        dataAccessLayer.acceptFriendRequest(senderEmail, recieverEmail);
                        handleFriendRequestShow(responseMessage); // Update the friend request list
                    } catch (SQLException ex) {
                        Logger.getLogger(ServerIwish.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else if ("rejectFriend".equals(responseType)) {
                    try {
                        // Call DataAccessLayer method to handle friend request rejection

                        dataAccessLayer.rejectFriendRequest(senderEmail, recieverEmail);
                        handleFriendRequestShow(responseMessage); // Update the friend request list
                    } catch (SQLException ex) {
                        Logger.getLogger(ServerIwish.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });
        }

        private void handleFriendRequest(String friendRequestMessage) {
            String[] parts = friendRequestMessage.split("\\|");
            String senderEmail = parts[1];
            String recieverEmail = parts[2];

            // Assuming currentUser is set appropriately in your code
            // If not, you need to set it when the user logs in or registers
            Platform.runLater(() -> {
                try {
                    // Check if the friend request already exists
                    boolean requestExists = dataAccessLayer.doesFriendRequestExist(senderEmail, recieverEmail);

                    if (requestExists) {
                        // Notify the client that the friend request already exists
                        ps.println("FriendRequestAlreadyExists|" + senderEmail + "|" + recieverEmail);
                    } else {
                        // Check if the friend (sender) is already a friend
                        boolean areAlreadyFriends = dataAccessLayer.areAlreadyFriends(senderEmail, recieverEmail);

                        if (areAlreadyFriends) {
                            // Notify the client that they are already friends
                            ps.println("AlreadyFriends|" + senderEmail + "|" + recieverEmail);
                        } else {
                            // Check if the friend exists in the database
                            boolean friendExists = dataAccessLayer.doesUserExist(recieverEmail);

                            if (!friendExists) {
                                // Notify the client that the friend does not exist
                                ps.println("FriendNotFound|" + senderEmail + "|" + recieverEmail);
                            } else {
                                // If all checks passed without errors, notify the client
                                dataAccessLayer.addFriendRequestToDatabase(senderEmail, recieverEmail);
                                ps.println("FriendRequestProcessed|" + senderEmail + "|" + recieverEmail);
                            }
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MainHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
        public void handleComment(String s){
        String[] parts = s.split("\\|");
        String email=parts[1];
        String comment = parts[2];
        String uemail=parts[3];
            try {
                dataAccessLayer.addcomment(email,comment,uemail);
            } catch (SQLException ex) {
                Logger.getLogger(ServerIwish.class.getName()).log(Level.SEVERE, null, ex);
            }
        ps.println(comment);

 
            
    }
    public void handleCommentL(String s) {
    try {
        String[] parts = s.split("\\|");
        String curr_user = parts[1];

        ResultSet resultSet = dataAccessLayer.retcomment(curr_user);

        List<String> commentsList = new ArrayList<>();
        while (resultSet.next()) {
            commentsList.add(resultSet.getString("comment"));
        }

        Gson gson = new Gson();
        String jsonResult = gson.toJson(commentsList);

        ps.println(jsonResult);
    } catch (SQLException ex) {
        Logger.getLogger(MainHandler.class.getName()).log(Level.SEVERE, null, ex);
    }
}

        public void handleUpdateL(String s) {
            try {
                String[] parts = s.split("\\|");
                String curr_user = parts[1];

                Info info = dataAccessLayer.retinfo(curr_user);
                String c = info.getFname() + "|" + info.getLname() + "|" + info.getEmail() + "|" + info.getPassword() + "|" + String.valueOf(info.getCredit()) + "|" + String.valueOf(info.getBalance());
                ps.println(c);
            } catch (SQLException ex) {
                Logger.getLogger(MainHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        private void handleFriendRequestShow(String message) {
            try {
                String[] parts = message.split("\\|");
                String recieverEmail = parts[1];

                String friendReq = dataAccessLayer.retrieveFriendRequests(recieverEmail);
                ps.println(friendReq);

            } catch (SQLException ex) {
                Logger.getLogger(MainHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        private void handleFriendListShow(String message) {
            String[] parts = message.split("\\|");
            String emails = parts[1];
            String friends = dataAccessLayer.retfriend(emails);
            ps.println(friends);
        }

        private void friendProfile(String message) {
            try {
                String[] parts = message.split("\\|");
                String emails = parts[1];
                ArrayList<wish_list> wishlists = new ArrayList<wish_list>();
                wishlists = dataAccessLayer.retfriendProfile(emails);
                Gson gson = new Gson();
                String jsonResult = gson.toJson(wishlists);
                System.out.print(jsonResult);
                ps.println(jsonResult);

            } catch (SQLException ex) {
                Logger.getLogger(MainHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        private void update_Contribution_Amount(String message) {
            String[] parts = message.split("\\|");
            String emails = parts[1];
            int item_id = Integer.parseInt(parts[2]);
            double amount = Double.parseDouble(parts[3]);
            double currentContri = Double.parseDouble(parts[4]);
            String fromemails = parts[5];
            String item_name = parts[6];
            int check;
            check = dataAccessLayer.update_ContributionAmout(emails, item_id, amount);
            int s = dataAccessLayer.update_Contribution(fromemails, emails, item_id, currentContri);
            int x = dataAccessLayer.update_notification(fromemails, emails, item_name, currentContri);
        }

        private void Item_Already_Bought(String message) {
            String[] parts = message.split("\\|");
            String emails = parts[1];
            int item_id = Integer.parseInt(parts[2]);
            int check;
            check = dataAccessLayer.Already_Bought(emails, item_id);

        }
        ////////////////////////////////////////////////////////////////////////////////////////
        private void ItemHandler(String message) {
            try {
                String[] parts = message.split("\\|");
                int item_id = Integer.parseInt(parts[1]);
                String itemJson = getItem(item_id);
                ps.println(itemJson);
                
            } catch (SQLException ex) {
                Logger.getLogger(MainHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        private void WishesHandler(String message) {
                try {
                    String[] parts = message.split("\\|");
                    int item_id = Integer.parseInt(parts[1]);
                    String email = parts[2];
                    String itemJson = setWish(item_id, email);
                    ps.println(itemJson);
                    ps.flush();
                    } catch (SQLException ex) {
            Logger.getLogger(MainHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
         private void handleFriendShow(String message) {
             String[] parts = message.split("\\|");
             String emails = parts[1];
             String friends = dataAccessLayer.retfriend(emails);
             System.out.print(friends);
             ps.println(friends);
        }
         private void WishesRemover(String message){
            try {
                    String[] parts = message.split("\\|");
                    int item_id = Integer.parseInt(parts[1]);
                    String email = parts[2];
                    rmvWish(item_id, email);
                } catch (SQLException ex) {
            Logger.getLogger(MainHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
         
        private void GetAllWishes(String message) {
            String[] parts = message.split("\\|");
            String email = parts[1];
            String allWishes = dataAccessLayer.getAllWishes(email);
            ps.println(allWishes);
            ps.flush();
        }
        
        public void GetAllOwned(String message) {
            String[] parts = message.split("\\|");
            String email = parts[1];
            String allOwned = dataAccessLayer.getAllOwned(email);
            ps.println(allOwned);
            ps.flush();
        }
        
    }

    private void updateBlance(String message) {
        String[] parts = message.split("\\|");
        double contributionAmount = Double.parseDouble(parts[1]);
        String email = parts[2];
        int check;
        check = dataAccessLayer.updateBalance(contributionAmount, email);

    }
      
}
        
