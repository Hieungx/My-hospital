package sample;
import com.sun.javafx.collections.MappingChange;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static sample.HomeController.getData;

public class QuanLyNhanSuController implements Initializable {
    @FXML
    private TableView<NhanVien> table;
    @FXML
    private TableColumn<NhanVien,String> idColumn;
    @FXML
    private TableColumn<NhanVien,String> nameColumn;
    @FXML
    private TableColumn<NhanVien,String> ageColumn;
    @FXML
    private TableColumn<NhanVien,String> sexColumn;
    @FXML
    private TableColumn<NhanVien,String> addressColumn;
    @FXML
    private TableColumn<NhanVien,String> phoneNumberColumn;
    @FXML
    private TableColumn<NhanVien,String> emailColumn;
    @FXML
    private TableColumn<NhanVien,String> positionColumn;
    @FXML
    private TableColumn<NhanVien,String> ceo_salaryColumn;
    @FXML
    private TextField idClick;
    @FXML
    private TextField nameClick;
    @FXML
    private TextField ageClick;
    @FXML
    private TextField sexClick;
    @FXML
    private TextField addressClick;
    @FXML
    private TextField phoneClick;
    @FXML
    private TextField emailClick;
    @FXML
    private ComboBox<String> positionClick;
    @FXML
    private TextField ceo_salaryClick;
    @FXML
    private TextField searchText;
    @FXML
    private TextField filterText;
    @FXML
    private ObservableList<NhanVien> nhanvienList;
    @FXML
    private ObservableList<ChucVu> PositionList;
    @FXML
    private ObservableList<NhanVien> nhanvienListAll;
    @FXML
    private ObservableMap<String,Double> Salary;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> pos = FXCollections.observableArrayList();
        Salary = FXCollections.observableHashMap();
        PositionList = FXCollections.observableArrayList();
        for(ChucVu chucvu : readDataPos()){
            PositionList.add(chucvu);
            pos.add(chucvu.getPosition());
            Salary.put(chucvu.getPosition(), chucvu.getCoe_Salary());
        }
        positionClick.setItems(pos);
        nhanvienList = FXCollections.observableArrayList();
        nhanvienListAll = FXCollections.observableArrayList();
        if(readData()!=null){
            for(NhanVien nhanvien : readData()){
                nhanvienListAll.add(nhanvien);
                if(nhanvien.getBranch().contains(getData())){
                    nhanvienList.add(nhanvien);
                }
            }
        }
        idColumn.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("ID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("Name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("Age"));
        sexColumn.setCellValueFactory(new PropertyValueFactory<NhanVien,String>("Sex"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("Address"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("PhoneNumber"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("Gmail"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("Position"));
        ceo_salaryColumn.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("Coe_Salary"));
        table.setItems(nhanvienList);

    }

    public void setTextSalary(){
        ceo_salaryClick.setText(String.valueOf(Salary.get(positionClick.getValue())));
    }

    public void setNull(){
        idClick.setText("");
        nameClick.setText("");
        ageClick.setText("");
        sexClick.setText("");
        addressClick.setText("");
        phoneClick.setText("");
        emailClick.setText("");
        positionClick.setValue("");
        ceo_salaryClick.setText("");
    }

    @FXML
    private void handleClickTableView(MouseEvent click) {
        NhanVien nhanvien = table.getSelectionModel().getSelectedItem();
        if (nhanvien!= null) {
            idClick.setText(nhanvien.getID());
            nameClick.setText(nhanvien.getName());
            ageClick.setText(nhanvien.getAge());
            sexClick.setText(nhanvien.getSex());
            addressClick.setText(nhanvien.getAddress());
            phoneClick.setText(nhanvien.getPhoneNumber());
            emailClick.setText(nhanvien.getGmail());
            //positionClick.setText(nhanvien.getPosition());
            positionClick.setValue(String.valueOf(nhanvien.getPosition()));
            ceo_salaryClick.setText(String.valueOf(nhanvien.getCoe_Salary()));

        }
    }
    @FXML
    private void handleClickSearch(MouseEvent click) {
        Search();
    }
    @FXML
    private void handleClickFilter(MouseEvent click) {
        Filter();
    }
    @FXML
    public void add (ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác Nhận");
        alert.setHeaderText("THÊM");
        alert.setContentText("Bạn có chắc chắn muốn thêm nhân viên này ?");
        ButtonType buttonTypeYes = new ButtonType("Yes",ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("No",ButtonBar.ButtonData.NO);
        ButtonType buttonTypeCancel = new ButtonType("Cancel",ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeYes,buttonTypeNo,buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get()==buttonTypeYes) {
            NhanVien newNhanvien = new NhanVien();
            newNhanvien.setBranch(getData());
            newNhanvien.setID(idClick.getText());
            newNhanvien.setName(nameClick.getText());
            newNhanvien.setAge(ageClick.getText());
            newNhanvien.setSex(sexClick.getText());
            newNhanvien.setAddress(addressClick.getText());
            newNhanvien.setPhoneNumber(phoneClick.getText());
            newNhanvien.setGmail(emailClick.getText());
            newNhanvien.setPosition(String.valueOf(positionClick.getValue()));
            newNhanvien.setCoe_Salary(Double.parseDouble(ceo_salaryClick.getText()));
            nhanvienList.add(newNhanvien);
            nhanvienListAll.add(newNhanvien);
            setNull();
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Information");
            alert1.setHeaderText("Bạn đã thêm thành công");
            alert1.show();
        }
        saveData();

    }
    public void edit(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác Nhận");
        alert.setHeaderText("SỬA");
        alert.setContentText("Bạn có chắc chắn muốn sửa nhân viên này ?");
        ButtonType buttonTypeYes = new ButtonType("Yes",ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("No",ButtonBar.ButtonData.NO);
        ButtonType buttonTypeCancel = new ButtonType("Cancel",ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeYes,buttonTypeNo,buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get()==buttonTypeYes) {
            NhanVien selected = table.getSelectionModel().getSelectedItem();
            NhanVien newnhanvien = new NhanVien();
            for(NhanVien nhanvien : nhanvienList){
                if(nhanvien==selected){
                    newnhanvien.setBranch(getData());
                    newnhanvien.setID(idClick.getText());
                    newnhanvien.setName(nameClick.getText());
                    newnhanvien.setAge(ageClick.getText());
                    newnhanvien.setSex(sexClick.getText());
                    newnhanvien.setAddress(addressClick.getText());
                    newnhanvien.setPhoneNumber(phoneClick.getText());
                    newnhanvien.setGmail(emailClick.getText());
                    newnhanvien.setPosition(String.valueOf(positionClick.getValue()));
                    newnhanvien.setCoe_Salary(Double.parseDouble(ceo_salaryClick.getText()));
                    nhanvienList.set(nhanvienList.indexOf(nhanvien),newnhanvien);
                    for(NhanVien nhanvien1 : nhanvienListAll){
                        if(nhanvien1==selected){
                            nhanvienListAll.set(nhanvienListAll.indexOf(nhanvien1),newnhanvien);
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

    }
    public void delete(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác Nhận");
        alert.setHeaderText("XÓA");
        alert.setContentText("Bạn có chắc chắn muốn xóa nhân viên này ?");
        ButtonType buttonTypeYes = new ButtonType("Yes",ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("No",ButtonBar.ButtonData.NO);
        ButtonType buttonTypeCancel = new ButtonType("Cancel",ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeYes,buttonTypeNo,buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get()==buttonTypeYes) {
            NhanVien selected = table.getSelectionModel().getSelectedItem();
            nhanvienList.remove(selected);
            nhanvienListAll.remove(selected);
            setNull();
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Information");
            alert1.setHeaderText("Bạn đã xóa thành công");
            alert1.show();
        }
        saveData();

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
        saveData();
    }
    public void Search(){
        FilteredList<NhanVien> filteredData = new FilteredList<>(nhanvienList, p -> true);
        searchText.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(NhanVien -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(NhanVien.getID()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                else if(String.valueOf(NhanVien.getName()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                else if(String.valueOf(NhanVien.getAddress()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                return false;
            });
        });
        SortedList<NhanVien> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
    }
    public void Filter(){
        FilteredList<NhanVien> filteredData1 = new FilteredList<>(nhanvienList, p -> true);
        filterText.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData1.setPredicate(NhanVien -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                if (NhanVien.getCoe_Salary()>=Double.parseDouble(filterText.getText())) {
                    return true;
                }
                return false;
            });
        });
        SortedList<NhanVien> sortedData1 = new SortedList<>(filteredData1);
        sortedData1.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData1);
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
    public List<NhanVien> readData() {

        List<NhanVien> inputNhanviens = null;
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
    private static void writeToTextFile(String filename, ObservableList<NhanVien> nhanViens)
            throws IOException {

        FileWriter writer = new FileWriter(filename);
        for (NhanVien nhanvien : nhanViens) {
            writer.write(nhanvien.getBranch()+ "," +nhanvien.getID() + "," + nhanvien.getName() + "," + nhanvien.getAge()+","+nhanvien.getSex() + "," + nhanvien.getPhoneNumber() + "," + nhanvien.getGmail()+","+nhanvien.getAddress() + "," + nhanvien.getPosition()+","+nhanvien.getCoe_Salary() + "\n");
        }
        writer.close();
    }
    private static List<NhanVien> readNhanviens(String filename)
            throws IOException {
        List<NhanVien> nhanViens = new ArrayList<>();
        /*Paths.get(filename)*/
        //BufferedReader reader = Files.newBufferedReader(new InputStreamReader());
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("data.txt")));
        while ((line = reader.readLine()) != null) {
            String[] names = line.split(",");
            nhanViens.add(new NhanVien(names[0], names[1],names[2],names[3], names[4],names[5],names[6], names[7], names[8],Double.parseDouble(names[9])));
            //System.out.println("******"+" "+names[0]+" "+ names[1]+" "+names[2]+" "+names[3]+" "+ names[4]+" "+names[5]+" "+names[6]+" "+ names[7]+" "+names[8]);
        }
        return nhanViens;
    }
    public List<ChucVu> readDataPos() {

        List<ChucVu> inputPositions = null;
        try {
            inputPositions = readPositions("Position.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputPositions;
    }
    private static List<ChucVu> readPositions(String filename)
            throws IOException {
        List<ChucVu> Positions = new ArrayList<>();
        /*Paths.get(filename)*/
        //BufferedReader reader = Files.newBufferedReader(new InputStreamReader());
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("Position.txt")));
        while ((line = reader.readLine()) != null) {
            String[] names = line.split(",");
            Positions.add(new ChucVu(names[0], Double.parseDouble(names[1])));
        }
        return Positions;
    }
}
