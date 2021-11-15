package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static sample.HomeController.getData;

public class ManagementPositionController implements Initializable {
    @FXML
    private ObservableList<Config> PositionList;
    @FXML
    private TextField idPosition;
    @FXML
    private TextField idCeo_Salary;
    @FXML
    private TableColumn<Config,String> positionColumn;
    @FXML
    private TableColumn<Config,Double> ceo_salaryColumn;
    @FXML
    private TableView<Config> table;
    @FXML
    private ObservableList<BenhNhan> nhanvienListAll;
    @FXML
    private ObservableList<BenhNhan> nhanvienListCopy;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PositionList = FXCollections.observableArrayList();
        nhanvienListAll = FXCollections.observableArrayList();
        if(readData()!=null){
            for(BenhNhan nhanvien : readData()){
                nhanvienListAll.add(nhanvien);
            }
        }
        if(readDataPos()!=null){
            for(Config chucvu : readDataPos()){
                PositionList.add(chucvu);
            }
        }
        positionColumn.setCellValueFactory(new PropertyValueFactory<Config, String>("Position"));
        ceo_salaryColumn.setCellValueFactory(new PropertyValueFactory<Config, Double>("Coe_Salary"));
        table.setItems(PositionList);
    }

    public void setNull(){
        idPosition.setText("");
        idCeo_Salary.setText("");
    }

    @FXML
    private void handleClickTableView(MouseEvent click) {
        Config chucvu = table.getSelectionModel().getSelectedItem();
        if (chucvu!= null) {
            idPosition.setText(chucvu.getPosition());
            idCeo_Salary.setText(String.valueOf(chucvu.getCoe_Salary()));

        }
    }
    @FXML
    public void add (ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác Nhận");
        alert.setHeaderText("THÊM");
        alert.setContentText("Bạn có chắc chắn muốn thêm tình trạng này này ?");
        ButtonType buttonTypeYes = new ButtonType("Yes",ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("No",ButtonBar.ButtonData.NO);
        ButtonType buttonTypeCancel = new ButtonType("Cancel",ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeYes,buttonTypeNo,buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get()==buttonTypeYes) {
            Config newChucvu = new Config();
            newChucvu.setPosition(idPosition.getText());
            newChucvu.setCoe_Salary(Double.parseDouble(idCeo_Salary.getText()));
            PositionList.add(newChucvu);
            setNull();
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Information");
            alert1.setHeaderText("Bạn đã thêm thành công");
            alert1.show();
        }
        saveDataPos();

    }
    public void edit(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác Nhận");
        alert.setHeaderText("SỬA");
        alert.setContentText("Bạn có chắc chắn muốn sửa tình trạng này ?");
        ButtonType buttonTypeYes = new ButtonType("Yes",ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("No",ButtonBar.ButtonData.NO);
        ButtonType buttonTypeCancel = new ButtonType("Cancel",ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeYes,buttonTypeNo,buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get()==buttonTypeYes) {
            Config selected = table.getSelectionModel().getSelectedItem();
            Config newchucvu = new Config();
            for(Config chucvu : PositionList){
                if(chucvu==selected){
                    newchucvu.setCoe_Salary(Double.parseDouble(idCeo_Salary.getText()));
                    newchucvu.setPosition(idPosition.getText());
                    PositionList.set(PositionList.indexOf(chucvu),newchucvu);
                    for(BenhNhan nhanvien : nhanvienListAll){
                        if(nhanvien.getPosition().contains(chucvu.getPosition())){
                            BenhNhan newnhanvien = new BenhNhan();
                            newnhanvien.setBranch(nhanvien.getBranch());
                            newnhanvien.setID(nhanvien.getID());
                            newnhanvien.setName(nhanvien.getName());
                            newnhanvien.setAge(nhanvien.getAge());
                            newnhanvien.setSex(nhanvien.getSex());
                            newnhanvien.setAddress(nhanvien.getAddress());
                            newnhanvien.setPhoneNumber(nhanvien.getPhoneNumber());
                            newnhanvien.setGmail(nhanvien.getGmail());
                            newnhanvien.setPosition(nhanvien.getPosition());
                            newnhanvien.setCoe_Salary(Double.parseDouble(idCeo_Salary.getText()));
                            nhanvienListAll.set(nhanvienListAll.indexOf(nhanvien),newnhanvien);
                        }
                    }
                }
            }
            setNull();
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Information");
            alert1.setHeaderText("Bạn đã sửa thành công");
            alert1.show();
        }
        saveData();
        saveDataPos();

    }
    public void delete(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác Nhận");
        alert.setHeaderText("XÓA");
        alert.setContentText("Bạn có chắc chắn muốn xóa tình trạng này ?");
        ButtonType buttonTypeYes = new ButtonType("Yes",ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("No",ButtonBar.ButtonData.NO);
        ButtonType buttonTypeCancel = new ButtonType("Cancel",ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeYes,buttonTypeNo,buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get()==buttonTypeYes) {
            Config selected = table.getSelectionModel().getSelectedItem();
            for(BenhNhan nhanvien : nhanvienListAll){
                if(nhanvien.getPosition().contains(selected.getPosition())){
                    BenhNhan newnhanvien = new BenhNhan();
                    newnhanvien.setBranch(nhanvien.getBranch());
                    newnhanvien.setID(nhanvien.getID());
                    newnhanvien.setName(nhanvien.getName());
                    newnhanvien.setAge(nhanvien.getAge());
                    newnhanvien.setSex(nhanvien.getSex());
                    newnhanvien.setAddress(nhanvien.getAddress());
                    newnhanvien.setPhoneNumber(nhanvien.getPhoneNumber());
                    newnhanvien.setGmail(nhanvien.getGmail());
                    newnhanvien.setPosition("null");
                    newnhanvien.setCoe_Salary(0.0);
                    nhanvienListAll.set(nhanvienListAll.indexOf(nhanvien),newnhanvien);
                }
            }
            PositionList.remove(selected);
            setNull();
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Information");
            alert1.setHeaderText("Bạn đã xóa thành công");
            alert1.show();

        }
        saveData();
        saveDataPos();

    }
    public void backHome(ActionEvent e)throws Exception{
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Home.fxml"));
        Parent LoginParent = loader.load();
        stage.setTitle("Home");
        Scene scene = new Scene(LoginParent);
        scene.getStylesheets().add(getClass().getResource("./Style/style.css").toExternalForm());
        stage.setScene(scene);
        saveDataPos();
    }
    public void saveDataPos(){
        /*try {
            FileOutputStream f = new FileOutputStream("data.txt");
            ObjectOutputStream oStream = new ObjectOutputStream(f);

            oStream.writeObject(nhanvienList);
            oStream.close();
        } catch (IOException e) {
            System.out.println("Erro");
        }*/
        try {
            writeToTextFilePos("Config.txt", PositionList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<Config> readDataPos() {

        List<Config> inputPositions = null;
        try {
            inputPositions = readPositions("Config.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputPositions;


        /*try {
            FileInputStream g = new FileInputStream("data.txt");
            ObjectInputStream inStream = new ObjectInputStream(g);
            nhanvienList1 = (ObservableList<NhanVien>) inStream.readObject();
            inStream.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        } catch (IOException e) {
            System.out.println("Error IO file");
        }*/
    }
    private static void writeToTextFilePos(String filename, ObservableList<Config> positions)
            throws IOException {

        FileWriter writer = new FileWriter(filename);
        for (Config position : positions) {
            writer.write(position.getPosition()+ "," +position.getCoe_Salary() +"\n");
        }
        writer.close();
    }
    private static List<Config> readPositions(String filename)
            throws IOException {
        List<Config> Positions = new ArrayList<>();
        /*Paths.get(filename)*/
        //BufferedReader reader = Files.newBufferedReader(new InputStreamReader());
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("Config.txt")));
        while ((line = reader.readLine()) != null) {
            String[] names = line.split(",");
            Positions.add(new Config(names[0], Double.parseDouble(names[1])));
        }
        return Positions;
    }
    public void saveData(){
        /*try {
            FileOutputStream f = new FileOutputStream("data.txt");
            ObjectOutputStream oStream = new ObjectOutputStream(f);

            oStream.writeObject(nhanvienList);
            oStream.close();
        } catch (IOException e) {
            System.out.println("Erro");
        }*/
        try {
            writeToTextFile("data.txt", nhanvienListAll);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<BenhNhan> readData() {

        List<BenhNhan> inputNhanviens = null;
        try {
            inputNhanviens = readNhanviens("data.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputNhanviens;


        /*try {
            FileInputStream g = new FileInputStream("data.txt");
            ObjectInputStream inStream = new ObjectInputStream(g);
            nhanvienList1 = (ObservableList<NhanVien>) inStream.readObject();
            inStream.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        } catch (IOException e) {
            System.out.println("Error IO file");
        }*/
    }
    private static void writeToTextFile(String filename, ObservableList<BenhNhan> nhanViens)
            throws IOException {

        FileWriter writer = new FileWriter(filename);
        for (BenhNhan nhanvien : nhanViens) {
            writer.write(nhanvien.getBranch()+ "," +nhanvien.getID() + "," + nhanvien.getName() + "," + nhanvien.getAge()+","+nhanvien.getSex() + "," + nhanvien.getPhoneNumber() + "," + nhanvien.getGmail()+","+nhanvien.getAddress() + "," + nhanvien.getPosition()+","+nhanvien.getCoe_Salary() + "\n");
        }
        writer.close();
    }
    private static List<BenhNhan> readNhanviens(String filename)
            throws IOException {
        List<BenhNhan> nhanViens = new ArrayList<>();
        /*Paths.get(filename)*/
        //BufferedReader reader = Files.newBufferedReader(new InputStreamReader());
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("data.txt")));
        while ((line = reader.readLine()) != null) {
            String[] names = line.split(",");
            nhanViens.add(new BenhNhan(names[0], names[1],names[2],names[3], names[4],names[5],names[6], names[7], names[8],Double.parseDouble(names[9])));
            //System.out.println("******"+" "+names[0]+" "+ names[1]+" "+names[2]+" "+names[3]+" "+ names[4]+" "+names[5]+" "+names[6]+" "+ names[7]+" "+names[8]);
        }
        return nhanViens;
    }
}
