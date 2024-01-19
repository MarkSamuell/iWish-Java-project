/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prjj;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class HomeView extends StackPane {
    private Curr_User curr_user;
    public HomeView(Curr_User curr_user) {
        this.curr_user = curr_user;
        getChildren().add(new Label("Welcome to Home Content"));
    }
}