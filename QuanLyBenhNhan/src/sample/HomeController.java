package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;


public class HomeController{
    @FXML
    private Button btnBranchA;
    @FXML
    private Button btnBranchB;
    @FXML
    private Button btnBranchC;
    @FXML
    private Button btnBranchD;
    @FXML
    private Button btnBranchE;
    @FXML
    private Button btnBranchF;
    @FXML
    static String x;
    public void ChangeSceneManagement(ActionEvent e) throws Exception{
        Stage stage =(Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("QuanLyNhanSu.fxml"));
        Parent QuanLyNhanSuView = loader.load();
        /*QuanLyNhanSuController controller = loader.getController();
        controller.getBranchForm("A301");*/
        Scene scene = new Scene(QuanLyNhanSuView);
        stage.setTitle("ManagementEmployee");
        scene.getStylesheets().add(getClass().getResource("./Style/style.css").toExternalForm());
        stage.setScene(scene);
    }
    public void ChangeScenePosition(ActionEvent e) throws Exception{
        Stage stage =(Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("QuanLyChucVu.fxml"));
        Parent PositionView = loader.load();
        stage.setTitle("ManagementPosition");
        /*QuanLyNhanSuController controller = loader.getController();
        controller.getBranchForm("A301");*/
        Scene scene = new Scene(PositionView);
        scene.getStylesheets().add(getClass().getResource("./Style/style.css").toExternalForm());
        stage.setScene(scene);
    }
    public void backLogin(ActionEvent e)throws Exception{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác Nhận");
        alert.setHeaderText("ĐĂNG XUẤT");
        alert.setContentText("Bạn có chắc chắn muốn đăng xuất khỏi tài khoản này ?");
        ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("No",ButtonBar.ButtonData.NO);
        ButtonType buttonTypeCancel = new ButtonType("Cancel",ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeYes,buttonTypeNo,buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get()==buttonTypeYes){
            Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Login.fxml"));
            Parent LoginParent = loader.load();
            stage.setTitle("Login");
            Scene scene = new Scene(LoginParent);
            scene.getStylesheets().add(getClass().getResource("./Style/style.css").toExternalForm());
            stage.setScene(scene);
        }
    }
    static public String getData(){
        return x;
    }//Truyền dữ liệu sang scene QuanLyNhanSu
    public void BranchA(ActionEvent e) throws Exception {
        x="A";
        ChangeSceneManagement(e);
    }
    public void BranchB(ActionEvent e) throws Exception {
        x="B";
        ChangeSceneManagement(e);
    }
    public void BranchC(ActionEvent e) throws Exception {
        x="C";
        ChangeSceneManagement(e);
    }
    public void BranchD(ActionEvent e) throws Exception {
        x="D";
        ChangeSceneManagement(e);
    }
    public void BranchE(ActionEvent e) throws Exception {
        x="E";
        ChangeSceneManagement(e);
    }
    public void BranchF(ActionEvent e) throws Exception {
        x="A306";
        ChangeSceneManagement(e);
    }

}
