package prjj;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.ListItem;
import static dto.ListItem.getItem;
import dto.WishItem;
import static dto.WishItem.addToWishList;
import static dto.WishItem.removeWish;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import static prjj.FriendProfileBase.friend;

public class homeBase extends StackPane {

    protected final BorderPane borderPane;
    protected final AnchorPane anchorPane;
    protected final AnchorPane anchorPane0;
    protected final AnchorPane anchorPane1;
    protected final VBox vBox;
    protected final VBox vBox0;
    protected final ScrollPane scrollPane;
    protected final VBox vBox1;
    protected final TitledPane titledPane;
    protected final BorderPane borderPane0;
    protected final TextArea ownedText;
    protected final VBox vBox2;
    protected final ScrollPane scrollPane0;
    protected final HBox hBox;
    protected final BorderPane borderPane1;
    protected final VBox vBox3;
    protected final TitledPane item_1_text;
    protected final TextField item_1_price;
    protected final ImageView imageView;
    protected final ImageView imageView0;
    protected final ImageView imageView1;
    protected final Button button;
    protected final Reflection reflection;
    protected final VBox vBox4;
    protected final TitledPane item_2_text;
    protected final AnchorPane anchorPane2;
    protected final TextField item_2_price;
    protected final ImageView imageView2;
    protected final ImageView imageView3;
    protected final Button button0;
    protected final Reflection reflection0;
    protected final VBox vBox5;
    protected final TitledPane item_3_text;
    protected final AnchorPane anchorPane3;
    protected final TextField item_3_price;
    protected final ImageView imageView4;
    protected final ImageView imageView5;
    protected final Button button1;
    protected final Reflection reflection1;
    protected final VBox vBox6;
    protected final TitledPane item_4_text;
    protected final AnchorPane anchorPane4;
    protected final TextField item_4_price;
    protected final ImageView imageView6;
    protected final ImageView imageView7;
    protected final Button button2;
    protected final Reflection reflection2;
    protected final VBox vBox7;
    protected final TitledPane item_5_text;
    protected final AnchorPane anchorPane5;
    protected final TextField item_5_price;
    protected final ImageView imageView8;
    protected final ImageView imageView9;
    protected final Button button3;
    protected final Reflection reflection3;
    protected final VBox vBox8;
    protected final TitledPane item_6_text;
    protected final TextField item_6_price;
    protected final ImageView imageView10;
    protected final ImageView imageView11;
    protected final Button button4;
    protected final Reflection reflection4;
    protected final VBox vBox9;
    protected final TitledPane item_7_text;
    protected final TextField item_7_price;
    protected final ImageView imageView12;
    protected final ImageView imageView13;
    protected final Button button5;
    protected final Reflection reflection5;
    protected final VBox vBox10;
    protected final TitledPane item_8_text;
    protected final AnchorPane anchorPane6;
    protected final TextField item_8_price;
    protected final ImageView imageView14;
    protected final ImageView imageView15;
    protected final Button button6;
    protected final Reflection reflection6;
    protected final VBox vBox11;
    protected final TitledPane item_9_text;
    protected final TextField item_9_price;
    protected final ImageView imageView16;
    protected final ImageView imageView17;
    protected final Button button7;
    protected final Reflection reflection7;
    protected final VBox vBox12;
    protected final TitledPane item_10_text;
    protected final TextField item_10_price;
    protected final ImageView imageView18;
    protected final ImageView imageView19;
    protected final Button button8;
    protected final Reflection reflection8;
    protected final BorderPane borderPane2;
    protected final TextField emailText;
    protected final ImageView imageView110;
    protected final ImageView imageView111;
    protected final BorderPane borderPane3;
    protected final TitledPane titledPane0;
    protected final HBox hBox0;
    protected final ScrollPane scrollPane1;
    protected final AnchorPane anchorPane7;
    protected final VBox vBox13;
    protected final HBox hBox1;
    protected final Text wish_1_text;
    protected final ProgressBar progress1;
    protected final TextField current_contribution1;
    protected final Separator separator;
    protected final HBox hBox2;
    protected final Text wish_2_text;
    protected final ProgressBar progress2;
    protected final TextField current_contribution2;
    protected final Separator separator0;
    protected final HBox hBox3;
    protected final Text wish_3_text;
    protected final ProgressBar progress3;
    protected final TextField current_contribution3;
    protected final Separator separator1;
    protected final HBox hBox4;
    protected final Text wish_4_text;
    protected final ProgressBar progress4;
    protected final TextField current_contribution4;
    protected final Separator separator2;
    protected final HBox hBox5;
    protected final Text wish_5_text;
    protected final ProgressBar progress5;
    protected final TextField current_contribution5;
    protected final AnchorPane anchorPane8;
    protected final Button removeBtn;
    protected final TitledPane titledPane1;
    protected final ListView listView;
    protected final HBox hBox6;
    protected final TextField addCommentTextField;
    protected final Button addCommentBtn;
    Socket mySocket;
    DataInputStream dis;
    PrintStream ps;
    String response;

    public static void setFriend(String friendEmail) {
        friend = friendEmail;
        // Perform any additional actions if needed
    }

    public static String curr_email;
    public static ArrayList<String> wishes = new ArrayList<>();
    public static ArrayList<ListItem> wishItems = new ArrayList<>();
    public static ArrayList<String> ownedItemsList = new ArrayList<>();

    public homeBase(Curr_User curr_user) throws IOException {
        mySocket = new Socket("10.145.11.234", 5005);
        dis = new DataInputStream(mySocket.getInputStream());
        ps = new PrintStream(mySocket.getOutputStream());
        this.curr_email = curr_user.getUserName();
        
        borderPane = new BorderPane();
        anchorPane = new AnchorPane();
        anchorPane0 = new AnchorPane();
        anchorPane1 = new AnchorPane();
        vBox = new VBox();
        vBox0 = new VBox();
        scrollPane = new ScrollPane();
        vBox1 = new VBox();
        titledPane = new TitledPane();
        borderPane0 = new BorderPane();
        ownedText = new TextArea();
        vBox2 = new VBox();
        scrollPane0 = new ScrollPane();
        hBox = new HBox();
        borderPane1 = new BorderPane();
        vBox3 = new VBox();
        item_1_text = new TitledPane();
        item_1_price = new TextField();
        imageView = new ImageView();
        imageView0 = new ImageView();
        imageView1 = new ImageView();
        button = new Button();
        reflection = new Reflection();
        vBox4 = new VBox();
        item_2_text = new TitledPane();
        anchorPane2 = new AnchorPane();
        item_2_price = new TextField();
        imageView2 = new ImageView();
        imageView3 = new ImageView();
        button0 = new Button();
        reflection0 = new Reflection();
        vBox5 = new VBox();
        item_3_text = new TitledPane();
        anchorPane3 = new AnchorPane();
        item_3_price = new TextField();
        imageView4 = new ImageView();
        imageView5 = new ImageView();
        button1 = new Button();
        reflection1 = new Reflection();
        vBox6 = new VBox();
        item_4_text = new TitledPane();
        anchorPane4 = new AnchorPane();
        item_4_price = new TextField();
        imageView6 = new ImageView();
        imageView7 = new ImageView();
        button2 = new Button();
        reflection2 = new Reflection();
        vBox7 = new VBox();
        item_5_text = new TitledPane();
        anchorPane5 = new AnchorPane();
        item_5_price = new TextField();
        imageView8 = new ImageView();
        imageView9 = new ImageView();
        button3 = new Button();
        reflection3 = new Reflection();
        vBox8 = new VBox();
        item_6_text = new TitledPane();
        item_6_price = new TextField();
        imageView10 = new ImageView();
        imageView11 = new ImageView();
        button4 = new Button();
        reflection4 = new Reflection();
        vBox9 = new VBox();
        item_7_text = new TitledPane();
        item_7_price = new TextField();
        imageView12 = new ImageView();
        imageView13 = new ImageView();
        button5 = new Button();
        reflection5 = new Reflection();
        vBox10 = new VBox();
        item_8_text = new TitledPane();
        anchorPane6 = new AnchorPane();
        item_8_price = new TextField();
        imageView14 = new ImageView();
        imageView15 = new ImageView();
        button6 = new Button();
        reflection6 = new Reflection();
        vBox11 = new VBox();
        item_9_text = new TitledPane();
        item_9_price = new TextField();
        imageView16 = new ImageView();
        imageView17 = new ImageView();
        button7 = new Button();
        reflection7 = new Reflection();
        vBox12 = new VBox();
        item_10_text = new TitledPane();
        item_10_price = new TextField();
        imageView18 = new ImageView();
        imageView19 = new ImageView();
        button8 = new Button();
        reflection8 = new Reflection();
        borderPane2 = new BorderPane();
        emailText = new TextField();
        imageView110 = new ImageView();
        imageView111 = new ImageView();
        borderPane3 = new BorderPane();
        titledPane0 = new TitledPane();
        hBox0 = new HBox();
        scrollPane1 = new ScrollPane();
        anchorPane7 = new AnchorPane();
        vBox13 = new VBox();
        hBox1 = new HBox();
        wish_1_text = new Text();
        progress1 = new ProgressBar();
        current_contribution1 = new TextField();
        separator = new Separator();
        hBox2 = new HBox();
        wish_2_text = new Text();
        progress2 = new ProgressBar();
        current_contribution2 = new TextField();
        separator0 = new Separator();
        hBox3 = new HBox();
        wish_3_text = new Text();
        progress3 = new ProgressBar();
        current_contribution3 = new TextField();
        separator1 = new Separator();
        hBox4 = new HBox();
        wish_4_text = new Text();
        progress4 = new ProgressBar();
        current_contribution4 = new TextField();
        separator2 = new Separator();
        hBox5 = new HBox();
        wish_5_text = new Text();
        progress5 = new ProgressBar();
        current_contribution5 = new TextField();
        anchorPane8 = new AnchorPane();
        removeBtn = new Button();
        titledPane1 = new TitledPane();
        listView = new ListView();
        hBox6 = new HBox();
        addCommentTextField = new TextField();
        addCommentBtn = new Button();

        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(1000.0);
        setPrefWidth(1500.0);

        BorderPane.setAlignment(anchorPane, javafx.geometry.Pos.CENTER);
        anchorPane.setPrefHeight(24.0);
        anchorPane.setPrefWidth(600.0);
        borderPane.setTop(anchorPane);

        BorderPane.setAlignment(anchorPane0, javafx.geometry.Pos.CENTER);
        anchorPane0.setPrefHeight(376.0);
        anchorPane0.setPrefWidth(30.0);
        borderPane.setRight(anchorPane0);

        BorderPane.setAlignment(anchorPane1, javafx.geometry.Pos.CENTER_LEFT);
        anchorPane1.setPrefHeight(961.0);
        anchorPane1.setPrefWidth(262.0);
        BorderPane.setMargin(anchorPane1, new Insets(0.0));

        vBox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        vBox.setMaxWidth(1000.0);
        vBox.setMinWidth(USE_PREF_SIZE);
        vBox.setNodeOrientation(javafx.geometry.NodeOrientation.LEFT_TO_RIGHT);
        vBox.setPrefHeight(376.0);
        vBox.setPrefWidth(160.0);

        VBox.setVgrow(vBox0, javafx.scene.layout.Priority.ALWAYS);
        vBox0.setMaxHeight(Double.MAX_VALUE);
        vBox0.setMaxWidth(USE_PREF_SIZE);
        vBox0.setPrefHeight(2000.0);
        vBox0.setPrefWidth(160.0);

        VBox.setVgrow(scrollPane, javafx.scene.layout.Priority.NEVER);
        scrollPane.setFitToWidth(true);
        scrollPane.setMaxWidth(USE_PREF_SIZE);
        scrollPane.setMinHeight(USE_PREF_SIZE);
        scrollPane.setMinWidth(USE_PREF_SIZE);
        scrollPane.setPannable(true);
        scrollPane.setPrefHeight(960.0);
        scrollPane.setPrefWidth(262.0);
        scrollPane.setVbarPolicy(javafx.scene.control.ScrollPane.ScrollBarPolicy.ALWAYS);

        titledPane.setAnimated(false);
        titledPane.setCollapsible(false);
        titledPane.setText("owned items");
        titledPane.setFont(new Font("Lucida Sans Typewriter Bold", 20.0));

        borderPane0.setPrefHeight(327.0);
        borderPane0.setPrefWidth(148.0);

        BorderPane.setAlignment(ownedText, javafx.geometry.Pos.CENTER);
        ownedText.setEditable(false);
        ownedText.setMinHeight(USE_PREF_SIZE);
        ownedText.setPrefHeight(930.0);
        ownedText.setPrefWidth(238.0);
        ownedText.setStyle("-fx-background-color: Green;");
        ownedText.setFont(new Font("Lucida Sans Typewriter Regular", 19.0));
        borderPane0.setRight(ownedText);
        scrollPane.setContent(vBox1);
        borderPane.setLeft(anchorPane1);

        BorderPane.setAlignment(vBox2, javafx.geometry.Pos.CENTER);
        vBox2.setDepthTest(javafx.scene.DepthTest.ENABLE);
        vBox2.setPrefHeight(173.0);
        vBox2.setPrefWidth(410.0);

        scrollPane0.setFitToWidth(true);
        scrollPane0.setHbarPolicy(javafx.scene.control.ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane0.setNodeOrientation(javafx.geometry.NodeOrientation.LEFT_TO_RIGHT);
        scrollPane0.setPrefHeight(500.0);
        scrollPane0.setPrefWidth(410.0);
        scrollPane0.setVbarPolicy(javafx.scene.control.ScrollPane.ScrollBarPolicy.NEVER);

        hBox.setMaxWidth(Double.MAX_VALUE);
        hBox.setPrefHeight(375.0);
        hBox.setPrefWidth(500.0);

        borderPane1.setPrefHeight(375.0);
        borderPane1.setPrefWidth(0.0);
        HBox.setMargin(borderPane1, new Insets(0.0));

        vBox3.setMaxWidth(200.0);
        vBox3.setMinWidth(USE_PREF_SIZE);
        vBox3.setPrefHeight(200.0);
        vBox3.setPrefWidth(100.0);

        item_1_text.setAnimated(false);
        item_1_text.setCollapsible(false);
        item_1_text.setExpanded(false);
        item_1_text.setPrefHeight(0.0);
        item_1_text.setPrefWidth(100.0);
        item_1_text.setText("item_1");
        item_1_text.setFont(new Font("Lucida Sans Regular", 18.0));

        item_1_price.setEditable(false);
        item_1_price.setPrefHeight(19.0);
        item_1_price.setPrefWidth(100.0);

        imageView.setFitHeight(23.0);
        imageView.setFitWidth(1005.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);

        imageView0.setFitHeight(196.0);
        imageView0.setFitWidth(1005.0);
        imageView0.setPickOnBounds(true);
        imageView0.setPreserveRatio(true);
        imageView0.setImage(new Image(getClass().getResource("86126-1440x2560-pure-white-background-photo-phone-hd.jpg").toExternalForm()));

        VBox.setVgrow(imageView1, javafx.scene.layout.Priority.NEVER);
        imageView1.setFitHeight(140.0);
        imageView1.setFitWidth(99.0);
        imageView1.setPickOnBounds(true);
        imageView1.setPreserveRatio(true);
        imageView1.setImage(new Image(getClass().getResource("ahlyjersy.jpg").toExternalForm()));

        VBox.setVgrow(button, javafx.scene.layout.Priority.ALWAYS);
        button.setMaxHeight(456.0);
        button.setMaxWidth(200.0);
        button.setMinHeight(USE_PREF_SIZE);
        button.setMinWidth(USE_PREF_SIZE);
        button.setMnemonicParsing(false);
        button.setPrefHeight(30.0);
        button.setPrefWidth(100.0);
        button.setText("+");
        button.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        button.setTextFill(javafx.scene.paint.Color.valueOf("#222121"));
        VBox.setMargin(button, new Insets(0.0));

        button.setEffect(reflection);
        button.setFont(new Font("Symbol", 26.0));

        vBox4.setMaxWidth(200.0);
        vBox4.setMinWidth(USE_PREF_SIZE);
        vBox4.setPrefHeight(200.0);
        vBox4.setPrefWidth(100.0);

        item_2_text.setAnimated(false);
        item_2_text.setCollapsible(false);
        item_2_text.setExpanded(false);
        item_2_text.setPrefHeight(0.0);
        item_2_text.setPrefWidth(100.0);
        item_2_text.setText("item_2");

        anchorPane2.setMinHeight(0.0);
        anchorPane2.setMinWidth(0.0);
        anchorPane2.setPrefHeight(180.0);
        anchorPane2.setPrefWidth(200.0);
        item_2_text.setContent(anchorPane2);
        item_2_text.setFont(new Font("Lucida Sans Regular", 18.0));

        item_2_price.setEditable(false);

        imageView2.setFitHeight(145.0);
        imageView2.setFitWidth(104.0);
        imageView2.setPickOnBounds(true);
        imageView2.setPreserveRatio(true);

        VBox.setVgrow(imageView3, javafx.scene.layout.Priority.NEVER);
        imageView3.setFitHeight(183.0);
        imageView3.setFitWidth(101.0);
        imageView3.setPickOnBounds(true);
        imageView3.setPreserveRatio(true);
        imageView3.setImage(new Image(getClass().getResource("airpods.jpg").toExternalForm()));

        VBox.setVgrow(button0, javafx.scene.layout.Priority.ALWAYS);
        button0.setMaxHeight(251.0);
        button0.setMaxWidth(200.0);
        button0.setMinHeight(USE_PREF_SIZE);
        button0.setMinWidth(USE_PREF_SIZE);
        button0.setMnemonicParsing(false);
        button0.setPrefHeight(30.0);
        button0.setPrefWidth(100.0);
        button0.setText("+");
        button0.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        button0.setTextFill(javafx.scene.paint.Color.valueOf("#222121"));
        VBox.setMargin(button0, new Insets(0.0));

        button0.setEffect(reflection0);
        button0.setFont(new Font("Symbol", 26.0));

        vBox5.setMaxWidth(200.0);
        vBox5.setMinWidth(USE_PREF_SIZE);
        vBox5.setPrefHeight(200.0);
        vBox5.setPrefWidth(100.0);

        item_3_text.setAnimated(false);
        item_3_text.setCollapsible(false);
        item_3_text.setExpanded(false);
        item_3_text.setPrefHeight(0.0);
        item_3_text.setPrefWidth(100.0);
        item_3_text.setText("item_3");

        anchorPane3.setMinHeight(0.0);
        anchorPane3.setMinWidth(0.0);
        anchorPane3.setPrefHeight(180.0);
        anchorPane3.setPrefWidth(200.0);
        item_3_text.setContent(anchorPane3);
        item_3_text.setFont(new Font("Lucida Sans Regular", 18.0));

        item_3_price.setEditable(false);

        imageView4.setFitHeight(179.0);
        imageView4.setFitWidth(99.0);
        imageView4.setPickOnBounds(true);
        imageView4.setPreserveRatio(true);

        VBox.setVgrow(imageView5, javafx.scene.layout.Priority.NEVER);
        imageView5.setFitHeight(255.0);
        imageView5.setFitWidth(100.0);
        imageView5.setPickOnBounds(true);
        imageView5.setPreserveRatio(true);
        imageView5.setImage(new Image(getClass().getResource("HPbook.jpg").toExternalForm()));

        VBox.setVgrow(button1, javafx.scene.layout.Priority.ALWAYS);
        button1.setMaxHeight(204.0);
        button1.setMaxWidth(100.0);
        button1.setMinHeight(USE_PREF_SIZE);
        button1.setMinWidth(USE_PREF_SIZE);
        button1.setMnemonicParsing(false);
        button1.setPrefHeight(30.0);
        button1.setPrefWidth(100.0);
        button1.setText("+");
        button1.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        button1.setTextFill(javafx.scene.paint.Color.valueOf("#222121"));
        VBox.setMargin(button1, new Insets(0.0));

        button1.setEffect(reflection1);
        button1.setFont(new Font("Symbol", 26.0));

        vBox6.setMaxWidth(200.0);
        vBox6.setMinWidth(USE_PREF_SIZE);
        vBox6.setPrefHeight(200.0);
        vBox6.setPrefWidth(100.0);

        item_4_text.setAnimated(false);
        item_4_text.setCollapsible(false);
        item_4_text.setExpanded(false);
        item_4_text.setPrefHeight(0.0);
        item_4_text.setPrefWidth(100.0);
        item_4_text.setText("item4");

        anchorPane4.setMinHeight(0.0);
        anchorPane4.setMinWidth(0.0);
        anchorPane4.setPrefHeight(180.0);
        anchorPane4.setPrefWidth(200.0);
        item_4_text.setContent(anchorPane4);
        item_4_text.setFont(new Font("Lucida Sans Regular", 18.0));

        item_4_price.setEditable(false);

        imageView6.setFitHeight(175.0);
        imageView6.setFitWidth(103.0);
        imageView6.setPickOnBounds(true);
        imageView6.setPreserveRatio(true);

        VBox.setVgrow(imageView7, javafx.scene.layout.Priority.NEVER);
        imageView7.setFitHeight(223.0);
        imageView7.setFitWidth(104.0);
        imageView7.setPickOnBounds(true);
        imageView7.setPreserveRatio(true);
        imageView7.setImage(new Image(getClass().getResource("HPwond.jpg").toExternalForm()));

        VBox.setVgrow(button2, javafx.scene.layout.Priority.ALWAYS);
        button2.setMaxHeight(175.0);
        button2.setMaxWidth(100.0);
        button2.setMinHeight(USE_PREF_SIZE);
        button2.setMinWidth(USE_PREF_SIZE);
        button2.setMnemonicParsing(false);
        button2.setPrefHeight(30.0);
        button2.setPrefWidth(100.0);
        button2.setText("+");
        button2.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        button2.setTextFill(javafx.scene.paint.Color.valueOf("#222121"));
        VBox.setMargin(button2, new Insets(0.0));

        button2.setEffect(reflection2);
        button2.setFont(new Font("Symbol", 26.0));

        vBox7.setMaxWidth(200.0);
        vBox7.setMinWidth(USE_PREF_SIZE);
        vBox7.setPrefHeight(256.0);
        vBox7.setPrefWidth(100.0);

        item_5_text.setAnimated(false);
        item_5_text.setCollapsible(false);
        item_5_text.setExpanded(false);
        item_5_text.setPrefHeight(0.0);
        item_5_text.setPrefWidth(100.0);
        item_5_text.setText("item_5");

        anchorPane5.setMinHeight(0.0);
        anchorPane5.setMinWidth(0.0);
        anchorPane5.setPrefHeight(180.0);
        anchorPane5.setPrefWidth(200.0);
        item_5_text.setContent(anchorPane5);
        item_5_text.setFont(new Font("Lucida Sans Regular", 18.0));

        item_5_price.setEditable(false);
        item_5_price.setPrefHeight(0.0);
        item_5_price.setPrefWidth(100.0);

        imageView8.setFitHeight(179.0);
        imageView8.setFitWidth(101.0);
        imageView8.setPickOnBounds(true);
        imageView8.setPreserveRatio(true);

        VBox.setVgrow(imageView9, javafx.scene.layout.Priority.NEVER);
        imageView9.setFitHeight(255.0);
        imageView9.setFitWidth(101.0);
        imageView9.setPickOnBounds(true);
        imageView9.setPreserveRatio(true);
        imageView9.setImage(new Image(getClass().getResource("ilap.jpg").toExternalForm()));

        VBox.setVgrow(button3, javafx.scene.layout.Priority.ALWAYS);
        button3.setMaxHeight(184.0);
        button3.setMaxWidth(100.0);
        button3.setMinHeight(USE_PREF_SIZE);
        button3.setMinWidth(USE_PREF_SIZE);
        button3.setMnemonicParsing(false);
        button3.setPrefHeight(30.0);
        button3.setPrefWidth(100.0);
        button3.setText("+");
        button3.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        button3.setTextFill(javafx.scene.paint.Color.valueOf("#222121"));
        VBox.setMargin(button3, new Insets(0.0));

        button3.setEffect(reflection3);
        button3.setFont(new Font("Symbol", 26.0));

        vBox8.setMaxWidth(200.0);
        vBox8.setMinWidth(USE_PREF_SIZE);
        vBox8.setPrefHeight(200.0);
        vBox8.setPrefWidth(100.0);

        item_6_text.setAnimated(false);
        item_6_text.setCollapsible(false);
        item_6_text.setExpanded(false);
        item_6_text.setPrefHeight(0.0);
        item_6_text.setPrefWidth(100.0);
        item_6_text.setText("item_1");
        item_6_text.setFont(new Font("Lucida Sans Regular", 18.0));

        item_6_price.setEditable(false);
        item_6_price.setPrefHeight(19.0);
        item_6_price.setPrefWidth(100.0);

        imageView10.setFitHeight(180.0);
        imageView10.setFitWidth(104.0);
        imageView10.setPickOnBounds(true);
        imageView10.setPreserveRatio(true);

        VBox.setVgrow(imageView11, javafx.scene.layout.Priority.NEVER);
        imageView11.setFitHeight(255.0);
        imageView11.setFitWidth(99.0);
        imageView11.setPickOnBounds(true);
        imageView11.setPreserveRatio(true);
        imageView11.setImage(new Image(getClass().getResource("ipad.jpg").toExternalForm()));

        VBox.setVgrow(button4, javafx.scene.layout.Priority.ALWAYS);
        button4.setMaxHeight(112.0);
        button4.setMaxWidth(200.0);
        button4.setMinHeight(USE_PREF_SIZE);
        button4.setMinWidth(USE_PREF_SIZE);
        button4.setMnemonicParsing(false);
        button4.setPrefHeight(30.0);
        button4.setPrefWidth(100.0);
        button4.setText("+");
        button4.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        button4.setTextFill(javafx.scene.paint.Color.valueOf("#222121"));
        VBox.setMargin(button4, new Insets(0.0));

        button4.setEffect(reflection4);
        button4.setFont(new Font("Symbol", 26.0));

        vBox9.setMaxWidth(200.0);
        vBox9.setMinWidth(USE_PREF_SIZE);
        vBox9.setPrefHeight(200.0);
        vBox9.setPrefWidth(100.0);

        item_7_text.setAnimated(false);
        item_7_text.setCollapsible(false);
        item_7_text.setExpanded(false);
        item_7_text.setPrefHeight(0.0);
        item_7_text.setPrefWidth(100.0);
        item_7_text.setText("item_1");
        item_7_text.setFont(new Font("Lucida Sans Regular", 18.0));

        item_7_price.setEditable(false);
        item_7_price.setPrefHeight(19.0);
        item_7_price.setPrefWidth(100.0);

        imageView12.setFitHeight(185.0);
        imageView12.setFitWidth(100.0);
        imageView12.setPickOnBounds(true);
        imageView12.setPreserveRatio(true);

        VBox.setVgrow(imageView13, javafx.scene.layout.Priority.NEVER);
        imageView13.setFitHeight(255.0);
        imageView13.setFitWidth(99.0);
        imageView13.setPickOnBounds(true);
        imageView13.setPreserveRatio(true);
        imageView13.setImage(new Image(getClass().getResource("iphone.jpg").toExternalForm()));

        VBox.setVgrow(button5, javafx.scene.layout.Priority.ALWAYS);
        button5.setMaxHeight(112.0);
        button5.setMaxWidth(200.0);
        button5.setMinHeight(USE_PREF_SIZE);
        button5.setMinWidth(USE_PREF_SIZE);
        button5.setMnemonicParsing(false);
        button5.setPrefHeight(30.0);
        button5.setPrefWidth(100.0);
        button5.setText("+");
        button5.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        button5.setTextFill(javafx.scene.paint.Color.valueOf("#222121"));
        VBox.setMargin(button5, new Insets(0.0));

        button5.setEffect(reflection5);
        button5.setFont(new Font("Symbol", 26.0));

        vBox10.setMaxWidth(200.0);
        vBox10.setMinWidth(USE_PREF_SIZE);
        vBox10.setPrefHeight(256.0);
        vBox10.setPrefWidth(100.0);

        item_8_text.setAnimated(false);
        item_8_text.setCollapsible(false);
        item_8_text.setExpanded(false);
        item_8_text.setPrefHeight(0.0);
        item_8_text.setPrefWidth(100.0);
        item_8_text.setText("item_5");

        anchorPane6.setMinHeight(0.0);
        anchorPane6.setMinWidth(0.0);
        anchorPane6.setPrefHeight(180.0);
        anchorPane6.setPrefWidth(200.0);
        item_8_text.setContent(anchorPane6);
        item_8_text.setFont(new Font("Lucida Sans Regular", 18.0));

        item_8_price.setEditable(false);
        item_8_price.setPrefHeight(0.0);
        item_8_price.setPrefWidth(100.0);

        imageView14.setFitHeight(125.0);
        imageView14.setFitWidth(99.0);
        imageView14.setPickOnBounds(true);
        imageView14.setPreserveRatio(true);

        VBox.setVgrow(imageView15, javafx.scene.layout.Priority.NEVER);
        imageView15.setFitHeight(218.0);
        imageView15.setFitWidth(101.0);
        imageView15.setPickOnBounds(true);
        imageView15.setPreserveRatio(true);
        imageView15.setImage(new Image(getClass().getResource("jacket.jpg").toExternalForm()));

        VBox.setVgrow(button6, javafx.scene.layout.Priority.ALWAYS);
        button6.setMaxHeight(184.0);
        button6.setMaxWidth(100.0);
        button6.setMinHeight(USE_PREF_SIZE);
        button6.setMinWidth(USE_PREF_SIZE);
        button6.setMnemonicParsing(false);
        button6.setPrefHeight(50.0);
        button6.setPrefWidth(100.0);
        button6.setText("+");
        button6.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        button6.setTextFill(javafx.scene.paint.Color.valueOf("#222121"));
        VBox.setMargin(button6, new Insets(0.0));

        button6.setEffect(reflection6);
        button6.setFont(new Font("Symbol", 26.0));

        vBox11.setMaxWidth(200.0);
        vBox11.setMinWidth(USE_PREF_SIZE);
        vBox11.setPrefHeight(200.0);
        vBox11.setPrefWidth(100.0);

        item_9_text.setAnimated(false);
        item_9_text.setCollapsible(false);
        item_9_text.setExpanded(false);
        item_9_text.setPrefHeight(0.0);
        item_9_text.setPrefWidth(100.0);
        item_9_text.setText("item_1");
        item_9_text.setFont(new Font("Lucida Sans Regular", 18.0));

        item_9_price.setEditable(false);
        item_9_price.setPrefHeight(19.0);
        item_9_price.setPrefWidth(100.0);

        imageView16.setFitHeight(143.0);
        imageView16.setFitWidth(103.0);
        imageView16.setPickOnBounds(true);
        imageView16.setPreserveRatio(true);

        VBox.setVgrow(imageView17, javafx.scene.layout.Priority.NEVER);
        imageView17.setFitHeight(255.0);
        imageView17.setFitWidth(99.0);
        imageView17.setPickOnBounds(true);
        imageView17.setPreserveRatio(true);
        imageView17.setImage(new Image(getClass().getResource("mug.jpg").toExternalForm()));

        VBox.setVgrow(button7, javafx.scene.layout.Priority.ALWAYS);
        button7.setMaxHeight(112.0);
        button7.setMaxWidth(200.0);
        button7.setMinHeight(USE_PREF_SIZE);
        button7.setMinWidth(USE_PREF_SIZE);
        button7.setMnemonicParsing(false);
        button7.setPrefHeight(30.0);
        button7.setPrefWidth(100.0);
        button7.setText("+");
        button7.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        button7.setTextFill(javafx.scene.paint.Color.valueOf("#222121"));
        VBox.setMargin(button7, new Insets(0.0));

        button7.setEffect(reflection7);
        button7.setFont(new Font("Symbol", 26.0));

        vBox12.setMaxWidth(200.0);
        vBox12.setMinWidth(USE_PREF_SIZE);
        vBox12.setPrefHeight(200.0);
        vBox12.setPrefWidth(100.0);

        item_10_text.setAnimated(false);
        item_10_text.setCollapsible(false);
        item_10_text.setExpanded(false);
        item_10_text.setPrefHeight(0.0);
        item_10_text.setPrefWidth(100.0);
        item_10_text.setText("item_1");
        item_10_text.setFont(new Font("Lucida Sans Regular", 18.0));

        item_10_price.setEditable(false);
        item_10_price.setPrefHeight(19.0);
        item_10_price.setPrefWidth(100.0);

        imageView18.setFitHeight(143.0);
        imageView18.setFitWidth(100.0);
        imageView18.setPickOnBounds(true);
        imageView18.setPreserveRatio(true);

        VBox.setVgrow(imageView19, javafx.scene.layout.Priority.NEVER);
        imageView19.setFitHeight(255.0);
        imageView19.setFitWidth(99.0);
        imageView19.setPickOnBounds(true);
        imageView19.setPreserveRatio(true);
        imageView19.setImage(new Image(getClass().getResource("shoes.jpg").toExternalForm()));

        VBox.setVgrow(button8, javafx.scene.layout.Priority.ALWAYS);
        button8.setMaxHeight(112.0);
        button8.setMaxWidth(200.0);
        button8.setMinHeight(USE_PREF_SIZE);
        button8.setMinWidth(USE_PREF_SIZE);
        button8.setMnemonicParsing(false);
        button8.setPrefHeight(30.0);
        button8.setPrefWidth(100.0);
        button8.setText("+");
        button8.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        button8.setTextFill(javafx.scene.paint.Color.valueOf("#222121"));
        VBox.setMargin(button8, new Insets(0.0));

        button8.setEffect(reflection8);
        button8.setFont(new Font("Symbol", 26.0));

        BorderPane.setAlignment(emailText, javafx.geometry.Pos.CENTER);
        emailText.setEditable(false);
        emailText.setPrefHeight(60.0);
        emailText.setPrefWidth(200.0);
        emailText.setPromptText("email");
        emailText.setFont(new Font("Lucida Sans Regular", 15.0));
        borderPane2.setCenter(emailText);

        BorderPane.setAlignment(imageView110, javafx.geometry.Pos.CENTER);
        imageView110.setFitHeight(150.0);
        imageView110.setFitWidth(233.0);
        imageView110.setPickOnBounds(true);
        imageView110.setPreserveRatio(true);
        imageView110.setImage(new Image(getClass().getResource("profile.jpg").toExternalForm()));
        borderPane2.setTop(imageView110);

        BorderPane.setAlignment(imageView111, javafx.geometry.Pos.CENTER);
        imageView111.setFitHeight(150.0);
        imageView111.setFitWidth(200.0);
        imageView111.setPickOnBounds(true);
        imageView111.setPreserveRatio(true);
        borderPane2.setBottom(imageView111);
        scrollPane0.setContent(hBox);

        borderPane3.setPrefHeight(228.0);
        borderPane3.setPrefWidth(410.0);

        BorderPane.setAlignment(titledPane0, javafx.geometry.Pos.CENTER);
        titledPane0.setAnimated(false);
        titledPane0.setCollapsible(false);
        titledPane0.setExpanded(false);
        titledPane0.setPrefHeight(24.0);
        titledPane0.setPrefWidth(410.0);
        titledPane0.setText("wish_list_items");
        titledPane0.setFont(new Font("Lucida Sans Regular", 18.0));
        borderPane3.setTop(titledPane0);

        BorderPane.setAlignment(hBox0, javafx.geometry.Pos.CENTER);
        hBox0.setMaxHeight(USE_PREF_SIZE);
        hBox0.setPrefHeight(500.0);
        hBox0.setPrefWidth(410.0);

        HBox.setHgrow(scrollPane1, javafx.scene.layout.Priority.ALWAYS);
        scrollPane1.setHbarPolicy(javafx.scene.control.ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane1.setPrefHeight(14.0);
        scrollPane1.setPrefWidth(410.0);
        scrollPane1.setVbarPolicy(javafx.scene.control.ScrollPane.ScrollBarPolicy.ALWAYS);

        AnchorPane.setRightAnchor(vBox13, 0.0);
        vBox13.setMaxWidth(Double.MAX_VALUE);
        vBox13.setPrefHeight(200.0);
        vBox13.setPrefWidth(3000.0);

        hBox1.setPrefHeight(45.0);
        hBox1.setPrefWidth(3000.0);

        HBox.setHgrow(wish_1_text, javafx.scene.layout.Priority.ALWAYS);
        wish_1_text.setAccessibleRole(javafx.scene.AccessibleRole.BUTTON);
        wish_1_text.setFill(javafx.scene.paint.Color.valueOf("#eb7272"));
        wish_1_text.setFontSmoothingType(javafx.scene.text.FontSmoothingType.LCD);
        wish_1_text.setSmooth(false);
        wish_1_text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        wish_1_text.setStrokeWidth(0.0);
        wish_1_text.setStyle("-fx-font-family: Sans Serif;");
        wish_1_text.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        wish_1_text.setUnderline(true);
        wish_1_text.setWrappingWidth(82.98307228088379);

        progress1.setMinWidth(USE_PREF_SIZE);
        progress1.setPrefHeight(38.0);
        progress1.setPrefWidth(869.0);
        progress1.setProgress(0.0);

        current_contribution1.setEditable(false);
        current_contribution1.setPrefHeight(38.0);
        current_contribution1.setPrefWidth(201.0);
        current_contribution1.setStyle("-fx-background-color: pink;");

        separator.setPrefWidth(200.0);

        hBox2.setPrefHeight(44.0);
        hBox2.setPrefWidth(3002.0);

        HBox.setHgrow(wish_2_text, javafx.scene.layout.Priority.ALWAYS);
        wish_2_text.setAccessibleRole(javafx.scene.AccessibleRole.BUTTON);
        wish_2_text.setFill(javafx.scene.paint.Color.valueOf("#eb7272"));
        wish_2_text.setFontSmoothingType(javafx.scene.text.FontSmoothingType.LCD);
        wish_2_text.setSmooth(false);
        wish_2_text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        wish_2_text.setStrokeWidth(0.0);
        wish_2_text.setStyle("-fx-font-family: Sans Serif;");
        wish_2_text.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        wish_2_text.setUnderline(true);
        wish_2_text.setWrappingWidth(82.9765625);

        progress2.setMinWidth(USE_PREF_SIZE);
        progress2.setPrefHeight(37.0);
        progress2.setPrefWidth(870.0);
        progress2.setProgress(0.0);

        current_contribution2.setEditable(false);
        current_contribution2.setPrefHeight(37.0);
        current_contribution2.setPrefWidth(200.0);
        current_contribution2.setStyle("-fx-background-color: pink;");

        separator0.setPrefWidth(200.0);

        hBox3.setPrefHeight(44.0);
        hBox3.setPrefWidth(3002.0);

        HBox.setHgrow(wish_3_text, javafx.scene.layout.Priority.ALWAYS);
        wish_3_text.setAccessibleRole(javafx.scene.AccessibleRole.BUTTON);
        wish_3_text.setFill(javafx.scene.paint.Color.valueOf("#eb7272"));
        wish_3_text.setFontSmoothingType(javafx.scene.text.FontSmoothingType.LCD);
        wish_3_text.setSmooth(false);
        wish_3_text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        wish_3_text.setStrokeWidth(0.0);
        wish_3_text.setStyle("-fx-font-family: Sans Serif;");
        wish_3_text.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        wish_3_text.setUnderline(true);
        wish_3_text.setWrappingWidth(84.98307228088379);

        progress3.setMinWidth(USE_PREF_SIZE);
        progress3.setPrefHeight(37.0);
        progress3.setPrefWidth(868.0);
        progress3.setProgress(0.0);

        current_contribution3.setEditable(false);
        current_contribution3.setPrefHeight(37.0);
        current_contribution3.setPrefWidth(202.0);
        current_contribution3.setStyle("-fx-background-color: pink;");

        separator1.setPrefWidth(200.0);

        hBox4.setPrefHeight(45.0);
        hBox4.setPrefWidth(3000.0);

        HBox.setHgrow(wish_4_text, javafx.scene.layout.Priority.ALWAYS);
        wish_4_text.setAccessibleRole(javafx.scene.AccessibleRole.BUTTON);
        wish_4_text.setFill(javafx.scene.paint.Color.valueOf("#eb7272"));
        wish_4_text.setFontSmoothingType(javafx.scene.text.FontSmoothingType.LCD);
        wish_4_text.setSmooth(false);
        wish_4_text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        wish_4_text.setStrokeWidth(0.0);
        wish_4_text.setStyle("-fx-font-family: Sans Serif;");
        wish_4_text.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        wish_4_text.setUnderline(true);
        wish_4_text.setWrappingWidth(83.9765625);

        progress4.setMinWidth(USE_PREF_SIZE);
        progress4.setPrefHeight(38.0);
        progress4.setPrefWidth(869.0);
        progress4.setProgress(0.0);

        current_contribution4.setEditable(false);
        current_contribution4.setPrefHeight(38.0);
        current_contribution4.setPrefWidth(202.0);
        current_contribution4.setStyle("-fx-background-color: pink;");

        separator2.setPrefWidth(200.0);

        hBox5.setPrefHeight(45.0);
        hBox5.setPrefWidth(3000.0);

        HBox.setHgrow(wish_5_text, javafx.scene.layout.Priority.ALWAYS);
        wish_5_text.setAccessibleRole(javafx.scene.AccessibleRole.BUTTON);
        wish_5_text.setFill(javafx.scene.paint.Color.valueOf("#eb7272"));
        wish_5_text.setFontSmoothingType(javafx.scene.text.FontSmoothingType.LCD);
        wish_5_text.setSmooth(false);
        wish_5_text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        wish_5_text.setStrokeWidth(0.0);
        wish_5_text.setStyle("-fx-font-family: Sans Serif;");
        wish_5_text.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        wish_5_text.setUnderline(true);
        wish_5_text.setWrappingWidth(82.98307228088379);

        progress5.setMinWidth(USE_PREF_SIZE);
        progress5.setPrefHeight(38.0);
        progress5.setPrefWidth(870.0);
        progress5.setProgress(0.0);

        current_contribution5.setEditable(false);
        current_contribution5.setPrefHeight(69.0);
        current_contribution5.setPrefWidth(201.0);
        current_contribution5.setStyle("-fx-background-color: pink;");
        scrollPane1.setContent(anchorPane7);
        borderPane3.setCenter(hBox0);

        BorderPane.setAlignment(anchorPane8, javafx.geometry.Pos.CENTER);
        anchorPane8.setPrefHeight(335.0);
        anchorPane8.setPrefWidth(35.0);
        borderPane3.setLeft(anchorPane8);

        removeBtn.setAlignment(javafx.geometry.Pos.CENTER);
        removeBtn.setMnemonicParsing(false);
        removeBtn.setNodeOrientation(javafx.geometry.NodeOrientation.RIGHT_TO_LEFT);
        removeBtn.setPrefHeight(32.0);
        removeBtn.setPrefWidth(1192.0);
        removeBtn.setText("Remove");
        removeBtn.setFont(new Font("Lucida Sans Regular", 18.0));

        titledPane1.setPrefHeight(200.0);
        titledPane1.setPrefWidth(200.0);
        titledPane1.setText("Comments");

        listView.setPrefHeight(200.0);
        listView.setPrefWidth(200.0);
        titledPane1.setContent(listView);

        hBox6.setPrefHeight(59.0);
        hBox6.setPrefWidth(1208.0);

        addCommentTextField.setPrefHeight(48.0);
        addCommentTextField.setPrefWidth(986.0);

        addCommentBtn.setMnemonicParsing(false);
        addCommentBtn.setPrefHeight(51.0);
        addCommentBtn.setPrefWidth(288.0);
        addCommentBtn.setText("Add Comment");
        borderPane.setCenter(vBox2);

        vBox1.getChildren().add(titledPane);
        vBox1.getChildren().add(borderPane0);
        vBox0.getChildren().add(scrollPane);
        vBox.getChildren().add(vBox0);
        anchorPane1.getChildren().add(vBox);
        hBox.getChildren().add(borderPane1);
        vBox3.getChildren().add(item_1_text);
        vBox3.getChildren().add(item_1_price);
        vBox3.getChildren().add(imageView);
        vBox3.getChildren().add(imageView0);
        vBox3.getChildren().add(imageView1);
        vBox3.getChildren().add(button);
        hBox.getChildren().add(vBox3);
        vBox4.getChildren().add(item_2_text);
        vBox4.getChildren().add(item_2_price);
        vBox4.getChildren().add(imageView2);
        vBox4.getChildren().add(imageView3);
        vBox4.getChildren().add(button0);
        hBox.getChildren().add(vBox4);
        vBox5.getChildren().add(item_3_text);
        vBox5.getChildren().add(item_3_price);
        vBox5.getChildren().add(imageView4);
        vBox5.getChildren().add(imageView5);
        vBox5.getChildren().add(button1);
        hBox.getChildren().add(vBox5);
        vBox6.getChildren().add(item_4_text);
        vBox6.getChildren().add(item_4_price);
        vBox6.getChildren().add(imageView6);
        vBox6.getChildren().add(imageView7);
        vBox6.getChildren().add(button2);
        hBox.getChildren().add(vBox6);
        vBox7.getChildren().add(item_5_text);
        vBox7.getChildren().add(item_5_price);
        vBox7.getChildren().add(imageView8);
        vBox7.getChildren().add(imageView9);
        vBox7.getChildren().add(button3);
        hBox.getChildren().add(vBox7);
        vBox8.getChildren().add(item_6_text);
        vBox8.getChildren().add(item_6_price);
        vBox8.getChildren().add(imageView10);
        vBox8.getChildren().add(imageView11);
        vBox8.getChildren().add(button4);
        hBox.getChildren().add(vBox8);
        vBox9.getChildren().add(item_7_text);
        vBox9.getChildren().add(item_7_price);
        vBox9.getChildren().add(imageView12);
        vBox9.getChildren().add(imageView13);
        vBox9.getChildren().add(button5);
        hBox.getChildren().add(vBox9);
        vBox10.getChildren().add(item_8_text);
        vBox10.getChildren().add(item_8_price);
        vBox10.getChildren().add(imageView14);
        vBox10.getChildren().add(imageView15);
        vBox10.getChildren().add(button6);
        hBox.getChildren().add(vBox10);
        vBox11.getChildren().add(item_9_text);
        vBox11.getChildren().add(item_9_price);
        vBox11.getChildren().add(imageView16);
        vBox11.getChildren().add(imageView17);
        vBox11.getChildren().add(button7);
        hBox.getChildren().add(vBox11);
        vBox12.getChildren().add(item_10_text);
        vBox12.getChildren().add(item_10_price);
        vBox12.getChildren().add(imageView18);
        vBox12.getChildren().add(imageView19);
        vBox12.getChildren().add(button8);
        hBox.getChildren().add(vBox12);
        hBox.getChildren().add(borderPane2);
        vBox2.getChildren().add(scrollPane0);
        hBox1.getChildren().add(wish_1_text);
        hBox1.getChildren().add(progress1);
        hBox1.getChildren().add(current_contribution1);
        vBox13.getChildren().add(hBox1);
        vBox13.getChildren().add(separator);
        hBox2.getChildren().add(wish_2_text);
        hBox2.getChildren().add(progress2);
        hBox2.getChildren().add(current_contribution2);
        vBox13.getChildren().add(hBox2);
        vBox13.getChildren().add(separator0);
        hBox3.getChildren().add(wish_3_text);
        hBox3.getChildren().add(progress3);
        hBox3.getChildren().add(current_contribution3);
        vBox13.getChildren().add(hBox3);
        vBox13.getChildren().add(separator1);
        hBox4.getChildren().add(wish_4_text);
        hBox4.getChildren().add(progress4);
        hBox4.getChildren().add(current_contribution4);
        vBox13.getChildren().add(hBox4);
        vBox13.getChildren().add(separator2);
        hBox5.getChildren().add(wish_5_text);
        hBox5.getChildren().add(progress5);
        hBox5.getChildren().add(current_contribution5);
        vBox13.getChildren().add(hBox5);
        anchorPane7.getChildren().add(vBox13);
        hBox0.getChildren().add(scrollPane1);
        vBox2.getChildren().add(borderPane3);
        vBox2.getChildren().add(removeBtn);
        vBox2.getChildren().add(titledPane1);
        hBox6.getChildren().add(addCommentTextField);
        hBox6.getChildren().add(addCommentBtn);
        vBox2.getChildren().add(hBox6);
        getChildren().add(borderPane);

        addCommentBtn.setOnAction(e -> {
            String comment = addCommentTextField.getText();
            String commentsMessage = "comment|" + init.currentUserEmail + "|" + comment+"|"+init.currentUserEmail;

            // Send the comment to the server
            ps.println(commentsMessage);

            // Assuming you want to display comments in frndList1
            ObservableList<String> observableComments = listView.getItems();
            observableComments.add(comment);

            // Clear the comment text field
            addCommentTextField.clear();
        });

        String allOwned = WishItem.getAllOwned(curr_email);
        if (allOwned.length() > 0) {
            String[] allOwnedArray = allOwned.split(",");
            ArrayList<Integer> itemsIds = new ArrayList<>();
            String ownedItems = "";
            for (String i : allOwnedArray) {
                itemsIds.add(Integer.parseInt(i));
            }
            for (Integer i : itemsIds) {
                ownedItems += "\n" + getItem(i).getItem_name() + "\n" + "\n";
                ownedItemsList.add(getItem(i).getItem_name());
            }

            ownedText.setText(ownedItems);
        }

        ArrayList<Integer> ids = new ArrayList<>();
        ArrayList<Integer> progress = new ArrayList<>();
        String allWishes = null;
        try {
            allWishes = WishItem.getAllWishes(curr_email);
        } catch (IOException ex) {
            Logger.getLogger(homeBase.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (allWishes.length() > 0) {
            String[] allWishesArray = allWishes.split(",");
            for (String i : allWishesArray) {
                String[] oneWish = i.split(":");
                ids.add(Integer.parseInt(oneWish[0]));
                progress.add(Integer.parseInt(oneWish[1]));
            }


            int idsSize = ids.size();
            double progrs;

            switch (idsSize) {
                case 1:
                    wish_1_text.setText(ListItem.getItem(ids.get(0)).getItem_name());
                    progrs = 1.0 * progress.get(0) / ListItem.getItem(ids.get(0)).getItem_price();
                    progress1.setProgress(progrs);
                    wishes.add(ListItem.getItem(ids.get(0)).getItem_name());
                    wishItems.add(ListItem.getItem(ids.get(0)));

                    current_contribution1.setText("you have :  " + (progress.get(0)) + "/" + (ListItem.getItem(ids.get(0)).getItem_price()));
                    break;
                case 2:
                    wish_1_text.setText(ListItem.getItem(ids.get(0)).getItem_name());
                    progrs = 1.0 * progress.get(0) / ListItem.getItem(ids.get(0)).getItem_price();
                    progress1.setProgress(progrs);
                    wish_2_text.setText(ListItem.getItem(ids.get(1)).getItem_name());
                    progrs = 1.0 * progress.get(1) / ListItem.getItem(ids.get(1)).getItem_price();
                    progress2.setProgress(progrs);
                    wishes.add(ListItem.getItem(ids.get(0)).getItem_name());
                    wishItems.add(ListItem.getItem(ids.get(0)));
                    wishes.add(ListItem.getItem(ids.get(1)).getItem_name());
                    wishItems.add(ListItem.getItem(ids.get(1)));

                    current_contribution1.setText("you have :  " + (progress.get(0)) + "/" + (ListItem.getItem(ids.get(0)).getItem_price()));

                    current_contribution2.setText("you have :  " + (progress.get(1)) + "/" + (ListItem.getItem(ids.get(1)).getItem_price()));

                    break;
                case 3:
                    wish_1_text.setText(ListItem.getItem(ids.get(0)).getItem_name());
                    progrs = 1.0 * progress.get(0) / ListItem.getItem(ids.get(0)).getItem_price();
                    progress1.setProgress(progrs);
                    wish_2_text.setText(ListItem.getItem(ids.get(1)).getItem_name());
                    progrs = 1.0 * progress.get(1) / ListItem.getItem(ids.get(1)).getItem_price();
                    progress2.setProgress(progrs);
                    wish_3_text.setText(ListItem.getItem(ids.get(2)).getItem_name());
                    progrs = 1.0 * progress.get(2) / ListItem.getItem(ids.get(2)).getItem_price();
                    progress3.setProgress(progrs);
                    wishes.add(ListItem.getItem(ids.get(0)).getItem_name());
                    wishItems.add(ListItem.getItem(ids.get(0)));
                    wishes.add(ListItem.getItem(ids.get(1)).getItem_name());
                    wishItems.add(ListItem.getItem(ids.get(1)));
                    wishes.add(ListItem.getItem(ids.get(2)).getItem_name());
                    wishItems.add(ListItem.getItem(ids.get(2)));

                    current_contribution1.setText("you have :  " + (progress.get(0)) + "/" + (ListItem.getItem(ids.get(0)).getItem_price()));

                    current_contribution2.setText("you have :  " + (progress.get(1)) + "/" + (ListItem.getItem(ids.get(1)).getItem_price()));

                    current_contribution3.setText("you have :  " + (progress.get(2)) + "/" + (ListItem.getItem(ids.get(2)).getItem_price()));
                    break;
                case 4:
                    wish_1_text.setText(ListItem.getItem(ids.get(0)).getItem_name());
                    progrs = 1.0 * progress.get(0) / ListItem.getItem(ids.get(0)).getItem_price();
                    progress1.setProgress(progrs);
                    wish_2_text.setText(ListItem.getItem(ids.get(1)).getItem_name());
                    progrs = 1.0 * progress.get(1) / ListItem.getItem(ids.get(1)).getItem_price();
                    progress2.setProgress(progrs);
                    wish_3_text.setText(ListItem.getItem(ids.get(2)).getItem_name());
                    progrs = 1.0 * progress.get(2) / ListItem.getItem(ids.get(2)).getItem_price();
                    progress3.setProgress(progrs);
                    wish_4_text.setText(ListItem.getItem(ids.get(3)).getItem_name());
                    progrs = 1.0 * progress.get(3) / ListItem.getItem(ids.get(3)).getItem_price();
                    progress4.setProgress(progrs);
                    wishes.add(ListItem.getItem(ids.get(0)).getItem_name());
                    wishItems.add(ListItem.getItem(ids.get(0)));
                    wishes.add(ListItem.getItem(ids.get(1)).getItem_name());
                    wishItems.add(ListItem.getItem(ids.get(1)));
                    wishes.add(ListItem.getItem(ids.get(2)).getItem_name());
                    wishItems.add(ListItem.getItem(ids.get(2)));
                    wishes.add(ListItem.getItem(ids.get(3)).getItem_name());
                    wishItems.add(ListItem.getItem(ids.get(3)));

                    current_contribution1.setText("you have :  " + (progress.get(0)) + "/" + (ListItem.getItem(ids.get(0)).getItem_price()));

                    current_contribution2.setText("you have :  " + (progress.get(1)) + "/" + (ListItem.getItem(ids.get(1)).getItem_price()));

                    current_contribution3.setText("you have :  " + (progress.get(2)) + "/" + (ListItem.getItem(ids.get(2)).getItem_price()));

                    current_contribution4.setText("you have :  " + (progress.get(3)) + "/" + (ListItem.getItem(ids.get(3)).getItem_price()));
                    break;
                case 5:
                    wish_1_text.setText(ListItem.getItem(ids.get(0)).getItem_name());
                    progrs = 1.0 * progress.get(0) / ListItem.getItem(ids.get(0)).getItem_price();
                    progress1.setProgress(progrs);
                    wish_2_text.setText(ListItem.getItem(ids.get(1)).getItem_name());
                    progrs = 1.0 * progress.get(1) / ListItem.getItem(ids.get(1)).getItem_price();
                    progress2.setProgress(progrs);
                    wish_3_text.setText(ListItem.getItem(ids.get(2)).getItem_name());
                    progrs = 1.0 * progress.get(2) / ListItem.getItem(ids.get(2)).getItem_price();
                    progress3.setProgress(progrs);
                    wish_4_text.setText(ListItem.getItem(ids.get(3)).getItem_name());
                    progrs = 1.0 * progress.get(3) / ListItem.getItem(ids.get(3)).getItem_price();
                    progress4.setProgress(progrs);
                    wish_5_text.setText(ListItem.getItem(ids.get(4)).getItem_name());
                    progrs = 1.0 * progress.get(4) / ListItem.getItem(ids.get(4)).getItem_price();
                    progress5.setProgress(progrs);
                    wishes.add(ListItem.getItem(ids.get(0)).getItem_name());
                    wishItems.add(ListItem.getItem(ids.get(0)));
                    wishes.add(ListItem.getItem(ids.get(1)).getItem_name());
                    wishItems.add(ListItem.getItem(ids.get(1)));
                    wishes.add(ListItem.getItem(ids.get(2)).getItem_name());
                    wishItems.add(ListItem.getItem(ids.get(2)));
                    wishes.add(ListItem.getItem(ids.get(3)).getItem_name());
                    wishItems.add(ListItem.getItem(ids.get(3)));
                    wishes.add(ListItem.getItem(ids.get(4)).getItem_name());
                    wishItems.add(ListItem.getItem(ids.get(4)));

                    current_contribution1.setText("you have :  " + (progress.get(0)) + "/" + (ListItem.getItem(ids.get(0)).getItem_price()));

                    current_contribution2.setText("you have :  " + (progress.get(1)) + "/" + (ListItem.getItem(ids.get(1)).getItem_price()));

                    current_contribution3.setText("you have :  " + (progress.get(2)) + "/" + (ListItem.getItem(ids.get(2)).getItem_price()));

                    current_contribution4.setText("you have :  " + (progress.get(3)) + "/" + (ListItem.getItem(ids.get(3)).getItem_price()));

                    current_contribution5.setText("you have :  " + (progress.get(4)) + "/" + (ListItem.getItem(ids.get(4)).getItem_price()));

                    break;
                default:
                    break;
            }
        }

        emailText.setText("User: " + curr_email);

        try {
            ListItem item1 = ListItem.getItem(1);
            ListItem item2 = ListItem.getItem(2);
            ListItem item3 = ListItem.getItem(3);
            ListItem item4 = ListItem.getItem(4);
            ListItem item5 = ListItem.getItem(5);
            ListItem item6 = ListItem.getItem(6);
            ListItem item7 = ListItem.getItem(7);
            ListItem item8 = ListItem.getItem(8);
            ListItem item9 = ListItem.getItem(9);
            ListItem item10 = ListItem.getItem(10);

            item_1_text.setText(item1.getItem_name());
            item_2_text.setText(item2.getItem_name());
            item_3_text.setText(item3.getItem_name());
            item_4_text.setText(item4.getItem_name());
            item_5_text.setText(item5.getItem_name());
            item_6_text.setText(item6.getItem_name());
            item_7_text.setText(item7.getItem_name());
            item_8_text.setText(item8.getItem_name());
            item_9_text.setText(item9.getItem_name());
            item_10_text.setText(item10.getItem_name());

            item_1_price.setText(item1.getItem_price() + "$");
            item_2_price.setText(item2.getItem_price() + "$");
            item_3_price.setText(item3.getItem_price() + "$");
            item_4_price.setText(item4.getItem_price() + "$");
            item_5_price.setText(item5.getItem_price() + "$");
            item_6_price.setText(item6.getItem_price() + "$");
            item_7_price.setText(item7.getItem_price() + "$");
            item_8_price.setText(item8.getItem_price() + "$");
            item_9_price.setText(item9.getItem_price() + "$");
            item_10_price.setText(item10.getItem_price() + "$");

        } catch (IOException ex) {
            Logger.getLogger(homeBase.class.getName()).log(Level.SEVERE, null, ex);
        }

        button.setOnAction(e -> {
            try {
                ListItem wish1 = addToWishList(1);
                if (wishes.contains(wish1.getItem_name())) {
                    removeWish(1, curr_email);
                    Alert alert = new Alert(AlertType.INFORMATION, "You already added this item");
                    alert.show();
                } else if (ownedItemsList.contains(wish1.getItem_name())) {
                    removeWish(1, curr_email);
                    Alert alert2 = new Alert(AlertType.INFORMATION, "You already owne this item");
                    alert2.show();
                } else {
                    if (wish_1_text.getText() == "") {
                        wish_1_text.setText(wish1.getItem_name());
                        progress1.setProgress(0.01);
                        wishes.add(wish1.getItem_name());
                        wishItems.add(wish1);

                    } else if (wish_2_text.getText() == "") {
                        wish_2_text.setText(wish1.getItem_name());
                        progress2.setProgress(0.01);
                        wishes.add(wish1.getItem_name());
                        wishItems.add(wish1);
                        current_contribution2.setText("");
                    } else if (wish_3_text.getText() == "") {
                        wish_3_text.setText(wish1.getItem_name());
                        progress3.setProgress(0.01);
                        wishes.add(wish1.getItem_name());
                        wishItems.add(wish1);
                        current_contribution3.setText("");
                    } else if (wish_4_text.getText() == "") {
                        wish_4_text.setText(wish1.getItem_name());
                        progress4.setProgress(0.01);
                        wishes.add(wish1.getItem_name());
                        wishItems.add(wish1);
                        current_contribution4.setText("");
                    } else if (wish_5_text.getText() == "") {
                        wish_5_text.setText(wish1.getItem_name());
                        progress5.setProgress(0.01);
                        wishes.add(wish1.getItem_name());
                        wishItems.add(wish1);
                        current_contribution5.setText("");
                    } else {
                        removeWish(1, curr_email);
                        Alert alert = new Alert(AlertType.INFORMATION, "Your Wish List is full");
                        alert.show();

                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(homeBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        button0.setOnAction(e -> {
            try {
                ListItem wish2 = addToWishList(2);
                if (wishes.contains(wish2.getItem_name())) {
                    removeWish(2, curr_email);
                    Alert alert = new Alert(AlertType.INFORMATION, "You already added this item");
                    alert.show();
                } else if (ownedItemsList.contains(wish2.getItem_name())) {
                    removeWish(2, curr_email);
                    Alert alert2 = new Alert(AlertType.INFORMATION, "You already owne this item");
                    alert2.show();
                } else {
                    if (wish_1_text.getText() == "") {
                        wish_1_text.setText(wish2.getItem_name());
                        progress1.setProgress(0.01);
                        wishes.add(wish2.getItem_name());
                        wishItems.add(wish2);
                        current_contribution1.setText("");
                    } else if (wish_2_text.getText() == "") {
                        wish_2_text.setText(wish2.getItem_name());
                        progress2.setProgress(0.01);
                        wishes.add(wish2.getItem_name());
                        wishItems.add(wish2);
                        current_contribution2.setText("");
                    } else if (wish_3_text.getText() == "") {
                        wish_3_text.setText(wish2.getItem_name());
                        progress3.setProgress(0.01);
                        wishes.add(wish2.getItem_name());
                        wishItems.add(wish2);
                        current_contribution3.setText("");
                    } else if (wish_4_text.getText() == "") {
                        wish_4_text.setText(wish2.getItem_name());
                        progress4.setProgress(0.01);
                        wishes.add(wish2.getItem_name());
                        wishItems.add(wish2);
                        current_contribution4.setText("");
                    } else if (wish_5_text.getText() == "") {
                        wish_5_text.setText(wish2.getItem_name());
                        progress5.setProgress(0.01);
                        wishes.add(wish2.getItem_name());
                        wishItems.add(wish2);
                        current_contribution5.setText("");
                    } else {
                        removeWish(2, curr_email);
                        Alert alert = new Alert(AlertType.INFORMATION, "Your Wish List is full");
                        alert.show();
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(homeBase.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        button1.setOnAction(e -> {
            try {
                ListItem wish3 = addToWishList(3);
                if (wishes.contains(wish3.getItem_name())) {
                    removeWish(3, curr_email);
                    Alert alert = new Alert(AlertType.INFORMATION, "You already added this item");
                    alert.show();
                } else if (ownedItemsList.contains(wish3.getItem_name())) {
                    removeWish(3, curr_email);
                    Alert alert2 = new Alert(AlertType.INFORMATION, "You already owne this item");
                    alert2.show();
                } else {
                    if (wish_1_text.getText() == "") {
                        wish_1_text.setText(wish3.getItem_name());
                        progress1.setProgress(0.01);
                        wishes.add(wish3.getItem_name());
                        wishItems.add(wish3);
                        current_contribution1.setText("");
                    } else if (wish_2_text.getText() == "") {
                        wish_2_text.setText(wish3.getItem_name());
                        progress2.setProgress(0.01);
                        wishes.add(wish3.getItem_name());
                        wishItems.add(wish3);
                        current_contribution2.setText("");
                    } else if (wish_3_text.getText() == "") {
                        wish_3_text.setText(wish3.getItem_name());
                        progress3.setProgress(0.01);
                        wishes.add(wish3.getItem_name());
                        wishItems.add(wish3);
                        current_contribution3.setText("");
                    } else if (wish_4_text.getText() == "") {
                        wish_4_text.setText(wish3.getItem_name());
                        progress4.setProgress(0.01);
                        wishes.add(wish3.getItem_name());
                        wishItems.add(wish3);
                        current_contribution4.setText("");
                    } else if (wish_5_text.getText() == "") {
                        wish_5_text.setText(wish3.getItem_name());
                        progress5.setProgress(0.01);
                        wishes.add(wish3.getItem_name());
                        wishItems.add(wish3);
                        current_contribution5.setText("");
                    } else {
                        removeWish(3, curr_email);
                        Alert alert = new Alert(AlertType.INFORMATION, "Your Wish List is full");
                        alert.show();
                    }
                }

            } catch (IOException ex) {
                Logger.getLogger(homeBase.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        button2.setOnAction(e -> {
            try {
                ListItem wish4 = addToWishList(4);
                if (wishes.contains(wish4.getItem_name())) {
                    removeWish(4, curr_email);
                    Alert alert = new Alert(AlertType.INFORMATION, "You already added this item");
                    alert.show();
                } else if (ownedItemsList.contains(wish4.getItem_name())) {
                    removeWish(4, curr_email);
                    Alert alert2 = new Alert(AlertType.INFORMATION, "You already owne this item");
                    alert2.show();
                } else {
                    if (wish_1_text.getText() == "") {
                        wish_1_text.setText(wish4.getItem_name());
                        progress1.setProgress(0.01);
                        wishes.add(wish4.getItem_name());
                        wishItems.add(wish4);
                        current_contribution1.setText("");
                    } else if (wish_2_text.getText() == "") {
                        wish_2_text.setText(wish4.getItem_name());
                        progress2.setProgress(0.01);
                        wishes.add(wish4.getItem_name());
                        wishItems.add(wish4);
                        current_contribution2.setText("");
                    } else if (wish_3_text.getText() == "") {
                        wish_3_text.setText(wish4.getItem_name());
                        progress3.setProgress(0.01);
                        wishes.add(wish4.getItem_name());
                        wishItems.add(wish4);
                        current_contribution3.setText("");
                    } else if (wish_4_text.getText() == "") {
                        wish_4_text.setText(wish4.getItem_name());
                        progress4.setProgress(0.01);
                        wishes.add(wish4.getItem_name());
                        wishItems.add(wish4);
                        current_contribution4.setText("");
                    } else if (wish_5_text.getText() == "") {
                        wish_5_text.setText(wish4.getItem_name());
                        progress5.setProgress(0.01);
                        wishes.add(wish4.getItem_name());
                        wishItems.add(wish4);
                        current_contribution5.setText("");
                    } else {
                        removeWish(4, curr_email);
                        Alert alert = new Alert(AlertType.INFORMATION, "Your Wish List is full");
                        alert.show();
                    }
                }

            } catch (IOException ex) {
                Logger.getLogger(homeBase.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        button3.setOnAction(e -> {
            try {
                ListItem wish5 = addToWishList(5);
                if (wishes.contains(wish5.getItem_name())) {
                    removeWish(5, curr_email);
                    Alert alert = new Alert(AlertType.INFORMATION, "You already added this item");
                    alert.show();
                } else if (ownedItemsList.contains(wish5.getItem_name())) {
                    removeWish(5, curr_email);
                    Alert alert2 = new Alert(AlertType.INFORMATION, "You already owne this item");
                    alert2.show();
                } else {
                    if (wish_1_text.getText() == "") {
                        wish_1_text.setText(wish5.getItem_name());
                        progress1.setProgress(0.01);
                        wishes.add(wish5.getItem_name());
                        wishItems.add(wish5);
                        current_contribution1.setText("");
                    } else if (wish_2_text.getText() == "") {
                        wish_2_text.setText(wish5.getItem_name());
                        progress2.setProgress(0.01);
                        wishes.add(wish5.getItem_name());
                        wishItems.add(wish5);
                        current_contribution2.setText("");
                    } else if (wish_3_text.getText() == "") {
                        wish_3_text.setText(wish5.getItem_name());
                        progress3.setProgress(0.01);
                        wishes.add(wish5.getItem_name());
                        wishItems.add(wish5);
                        current_contribution3.setText("");
                    } else if (wish_4_text.getText() == "") {
                        wish_4_text.setText(wish5.getItem_name());
                        progress4.setProgress(0.01);
                        wishes.add(wish5.getItem_name());
                        wishItems.add(wish5);
                        current_contribution4.setText("");
                    } else if (wish_5_text.getText() == "") {
                        wish_5_text.setText(wish5.getItem_name());
                        progress5.setProgress(0.01);
                        wishes.add(wish5.getItem_name());
                        wishItems.add(wish5);
                        current_contribution5.setText("");
                    } else {
                        removeWish(5, curr_email);
                        Alert alert = new Alert(AlertType.INFORMATION, "Your Wish List is full");
                        alert.show();
                    }
                }

            } catch (IOException ex) {
                Logger.getLogger(homeBase.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        button4.setOnAction(e -> {
            try {
                ListItem wish6 = addToWishList(6);
                if (wishes.contains(wish6.getItem_name())) {
                    removeWish(6, curr_email);
                    Alert alert = new Alert(AlertType.INFORMATION, "You already added this item");
                    alert.show();
                } else if (ownedItemsList.contains(wish6.getItem_name())) {
                    removeWish(6, curr_email);
                    Alert alert2 = new Alert(AlertType.INFORMATION, "You already owne this item");
                    alert2.show();
                } else {
                    if (wish_1_text.getText() == "") {
                        wish_1_text.setText(wish6.getItem_name());
                        progress1.setProgress(0.01);
                        wishes.add(wish6.getItem_name());
                        wishItems.add(wish6);
                        current_contribution1.setText("");
                    } else if (wish_2_text.getText() == "") {
                        wish_2_text.setText(wish6.getItem_name());
                        progress2.setProgress(0.01);
                        wishes.add(wish6.getItem_name());
                        wishItems.add(wish6);
                        current_contribution2.setText("");
                    } else if (wish_3_text.getText() == "") {
                        wish_3_text.setText(wish6.getItem_name());
                        progress3.setProgress(0.01);
                        wishes.add(wish6.getItem_name());
                        wishItems.add(wish6);
                        current_contribution3.setText("");
                    } else if (wish_4_text.getText() == "") {
                        wish_4_text.setText(wish6.getItem_name());
                        progress4.setProgress(0.01);
                        wishes.add(wish6.getItem_name());
                        wishItems.add(wish6);
                        current_contribution4.setText("");
                    } else if (wish_5_text.getText() == "") {
                        wish_5_text.setText(wish6.getItem_name());
                        progress5.setProgress(0.01);
                        wishes.add(wish6.getItem_name());
                        wishItems.add(wish6);
                        current_contribution5.setText("");
                    } else {
                        removeWish(6, curr_email);
                        Alert alert = new Alert(AlertType.INFORMATION, "Your Wish List is full");
                        alert.show();
                    }
                }

            } catch (IOException ex) {
                Logger.getLogger(homeBase.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        button5.setOnAction(e -> {
            try {
                ListItem wish7 = addToWishList(7);
                if (wishes.contains(wish7.getItem_name())) {
                    removeWish(7, curr_email);
                    Alert alert = new Alert(AlertType.INFORMATION, "You already added this item");
                    alert.show();
                } else if (ownedItemsList.contains(wish7.getItem_name())) {
                    removeWish(7, curr_email);
                    Alert alert2 = new Alert(AlertType.INFORMATION, "You already owne this item");
                    alert2.show();
                } else if (ownedItemsList.contains(wish7.getItem_name())) {
                    removeWish(7, curr_email);
                    Alert alert2 = new Alert(AlertType.INFORMATION, "You already owne this item");
                    alert2.show();
                } else {
                    if (wish_1_text.getText() == "") {
                        wish_1_text.setText(wish7.getItem_name());
                        progress1.setProgress(0.01);
                        wishes.add(wish7.getItem_name());
                        wishItems.add(wish7);
                        current_contribution1.setText("");
                    } else if (wish_2_text.getText() == "") {
                        wish_2_text.setText(wish7.getItem_name());
                        progress2.setProgress(0.01);
                        wishes.add(wish7.getItem_name());
                        wishItems.add(wish7);
                        current_contribution2.setText("");
                    } else if (wish_3_text.getText() == "") {
                        wish_3_text.setText(wish7.getItem_name());
                        progress3.setProgress(0.01);
                        wishes.add(wish7.getItem_name());
                        wishItems.add(wish7);
                        current_contribution3.setText("");
                    } else if (wish_4_text.getText() == "") {
                        wish_4_text.setText(wish7.getItem_name());
                        progress4.setProgress(0.01);
                        wishes.add(wish7.getItem_name());
                        wishItems.add(wish7);
                        current_contribution4.setText("");
                    } else if (wish_5_text.getText() == "") {
                        wish_5_text.setText(wish7.getItem_name());
                        progress5.setProgress(0.01);
                        wishes.add(wish7.getItem_name());
                        wishItems.add(wish7);
                        current_contribution5.setText("");
                    } else {
                        removeWish(7, curr_email);
                        Alert alert = new Alert(AlertType.INFORMATION, "Your Wish List is full");
                        alert.show();
                    }
                }

            } catch (IOException ex) {
                Logger.getLogger(homeBase.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        button6.setOnAction(e -> {
            try {
                ListItem wish8 = addToWishList(8);
                if (wishes.contains(wish8.getItem_name())) {
                    removeWish(8, curr_email);
                    Alert alert = new Alert(AlertType.INFORMATION, "You already added this item");
                    alert.show();
                } else if (ownedItemsList.contains(wish8.getItem_name())) {
                    removeWish(8, curr_email);
                    Alert alert2 = new Alert(AlertType.INFORMATION, "You already owne this item");
                    alert2.show();
                } else {
                    if (wish_1_text.getText() == "") {
                        wish_1_text.setText(wish8.getItem_name());
                        progress1.setProgress(0.01);
                        wishes.add(wish8.getItem_name());
                        wishItems.add(wish8);
                        current_contribution1.setText("");
                    } else if (wish_2_text.getText() == "") {
                        wish_2_text.setText(wish8.getItem_name());
                        progress2.setProgress(0.01);
                        wishes.add(wish8.getItem_name());
                        wishItems.add(wish8);
                        current_contribution2.setText("");
                    } else if (wish_3_text.getText() == "") {
                        wish_3_text.setText(wish8.getItem_name());
                        progress3.setProgress(0.01);
                        wishes.add(wish8.getItem_name());
                        wishItems.add(wish8);
                        current_contribution3.setText("");
                    } else if (wish_4_text.getText() == "") {
                        wish_4_text.setText(wish8.getItem_name());
                        progress4.setProgress(0.01);
                        wishes.add(wish8.getItem_name());
                        wishItems.add(wish8);
                        current_contribution4.setText("");
                    } else if (wish_5_text.getText() == "") {
                        wish_5_text.setText(wish8.getItem_name());
                        progress5.setProgress(0.01);
                        wishes.add(wish8.getItem_name());
                        wishItems.add(wish8);
                        current_contribution5.setText("");
                    } else {
                        removeWish(8, curr_email);
                        Alert alert = new Alert(AlertType.INFORMATION, "Your Wish List is full");
                        alert.show();
                    }
                }

            } catch (IOException ex) {
                Logger.getLogger(homeBase.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        button7.setOnAction(e -> {
            try {
                ListItem wish9 = addToWishList(9);
                if (wishes.contains(wish9.getItem_name())) {
                    removeWish(9, curr_email);
                    Alert alert = new Alert(AlertType.INFORMATION, "You already added this item");
                    alert.show();
                } else if (ownedItemsList.contains(wish9.getItem_name())) {
                    removeWish(9, curr_email);
                    Alert alert2 = new Alert(AlertType.INFORMATION, "You already owne this item");
                    alert2.show();
                } else {
                    if (wish_1_text.getText() == "") {
                        wish_1_text.setText(wish9.getItem_name());
                        progress1.setProgress(0.01);
                        wishes.add(wish9.getItem_name());
                        wishItems.add(wish9);
                        current_contribution1.setText("");
                    } else if (wish_2_text.getText() == "") {
                        wish_2_text.setText(wish9.getItem_name());
                        progress2.setProgress(0.01);
                        wishes.add(wish9.getItem_name());
                        wishItems.add(wish9);
                        current_contribution2.setText("");
                    } else if (wish_3_text.getText() == "") {
                        wish_3_text.setText(wish9.getItem_name());
                        progress3.setProgress(0.01);
                        wishes.add(wish9.getItem_name());
                        wishItems.add(wish9);
                        current_contribution3.setText("");
                    } else if (wish_4_text.getText() == "") {
                        wish_4_text.setText(wish9.getItem_name());
                        progress4.setProgress(0.01);
                        wishes.add(wish9.getItem_name());
                        wishItems.add(wish9);
                        current_contribution4.setText("");
                    } else if (wish_5_text.getText() == "") {
                        wish_5_text.setText(wish9.getItem_name());
                        progress5.setProgress(0.01);
                        wishes.add(wish9.getItem_name());
                        wishItems.add(wish9);
                        current_contribution5.setText("");
                    } else {
                        removeWish(9, curr_email);
                        Alert alert = new Alert(AlertType.INFORMATION, "Your Wish List is full");
                        alert.show();
                    }
                }

            } catch (IOException ex) {
                Logger.getLogger(homeBase.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        button8.setOnAction(e -> {
            try {
                ListItem wish10 = addToWishList(10);
                if (wishes.contains(wish10.getItem_name())) {
                    removeWish(10, curr_email);
                    Alert alert = new Alert(AlertType.INFORMATION, "You already added this item");
                    alert.show();
                } else if (ownedItemsList.contains(wish10.getItem_name())) {
                    removeWish(10, curr_email);
                    Alert alert2 = new Alert(AlertType.INFORMATION, "You already owne this item");
                    alert2.show();
                } else {
                    if (wish_1_text.getText() == "") {
                        wish_1_text.setText(wish10.getItem_name());
                        progress1.setProgress(0.01);
                        wishes.add(wish10.getItem_name());
                        wishItems.add(wish10);
                        current_contribution1.setText("");
                    } else if (wish_2_text.getText() == "") {
                        wish_2_text.setText(wish10.getItem_name());
                        progress2.setProgress(0.01);
                        wishes.add(wish10.getItem_name());
                        wishItems.add(wish10);
                        current_contribution2.setText("");
                    } else if (wish_3_text.getText() == "") {
                        wish_3_text.setText(wish10.getItem_name());
                        progress3.setProgress(0.01);
                        wishes.add(wish10.getItem_name());
                        wishItems.add(wish10);
                        current_contribution3.setText("");
                    } else if (wish_4_text.getText() == "") {
                        wish_4_text.setText(wish10.getItem_name());
                        progress4.setProgress(0.01);
                        wishes.add(wish10.getItem_name());
                        wishItems.add(wish10);
                        current_contribution4.setText("");
                    } else if (wish_5_text.getText() == "") {
                        wish_5_text.setText(wish10.getItem_name());
                        progress5.setProgress(0.01);
                        wishes.add(wish10.getItem_name());
                        wishItems.add(wish10);
                        current_contribution5.setText("");
                    } else {
                        removeWish(10, curr_email);
                        Alert alert = new Alert(AlertType.INFORMATION, "Your Wish List is full");
                        alert.show();
                    }
                }

            } catch (IOException ex) {
                Logger.getLogger(homeBase.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        removeBtn.setOnAction(e -> {
            int size = wishItems.size() - 1;
            if (wishItems.size() - 1 >= 0) {
                try {
                    removeWish(wishItems.get(size).getItem_id(), curr_email);
                    wishes.remove(wishItems.get(wishItems.size() - 1).getItem_name());
                    wishItems.remove(wishItems.get(wishItems.size() - 1));
                    switch (size) {
                        case 0:
                            wish_1_text.setText(null);
                            progress1.setProgress(0.0);
                            current_contribution1.setText("");
                            break;
                        case 1:
                            wish_2_text.setText(null);
                            progress2.setProgress(0.0);
                            current_contribution2.setText("");
                            break;
                        case 2:
                            wish_3_text.setText(null);
                            progress3.setProgress(0.0);
                            current_contribution3.setText("");
                            break;
                        case 3:
                            wish_4_text.setText(null);
                            progress4.setProgress(0.0);
                            current_contribution4.setText("");
                            break;
                        case 4:
                            wish_5_text.setText(null);
                            progress5.setProgress(0.0);
                            current_contribution5.setText("");
                            break;
                        default:
                            break;
                    }
                } catch (IOException ex) {
                    Logger.getLogger(homeBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                Alert alert = new Alert(AlertType.INFORMATION, "nothing to remove!!");
                alert.show();
            }
        });

        ps.println("showcomments|" + init.currentUserEmail);

        // Receive and handle the comments response
        String commentsResponse = dis.readLine();
        handleCommentsResponse(commentsResponse);

    }

    private void handleCommentsResponse(String response) {
        Gson gson = new Gson();
        List<String> commentsList = gson.fromJson(response, new TypeToken<List<String>>() {
        }.getType());
        listView.getItems().addAll(commentsList);
        for (String comment : commentsList) {
        }
    }
}
