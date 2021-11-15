package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
public class Controller {
    @FXML
    private TextField Username;
    @FXML
    private PasswordField Password;
    @FXML
    private Label Wrong;
    @FXML
    private String usernameCons = "admin";
    @FXML
    private Button btnLogin;
    @FXML
    private String passwordCons = "admin";

    @FXML
    public void ChangeSceneHome(ActionEvent e) throws Exception{
        Stage stage =(Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Home.fxml"));
        Parent QuanLyNhanSuView = loader.load();
        Scene scene = new Scene(QuanLyNhanSuView);
        stage.setTitle("Home");
        scene.getStylesheets().add(getClass().getResource("./Style/style.css").toExternalForm());
        stage.setScene(scene);
    }
    public void Login(ActionEvent e) throws Exception {

        String username = Username.getText();
        String password = Password.getText();
        if(username.equals(usernameCons)==true && password.equals(passwordCons)==true) {
            ChangeSceneHome(e);
        }
        else{
            Wrong.setText("*Sai username hoặc password");
            Wrong.setTextFill(Color.rgb(21, 117, 84));
        }
    }
}
