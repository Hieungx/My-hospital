package sample;

import java.io.Serializable;

public class NhanVien  extends ChucVu implements Serializable {
    private String Branch;
    private String ID;
    private String Name;
    private String Age;
    private String Sex;
    private String Address;
    private String PhoneNumber;
    private String Gmail;
    public NhanVien(){

    };
    public NhanVien(String branch,String id, String name, String age,String sex, String phoneNumber,String gmail,String address, String position,Double ceo_Salary) {
        super(position,ceo_Salary);
        Branch = branch;
        ID = id;
        Name = name;
        Age = age;
        Sex = sex;
        Address = address;
        PhoneNumber = phoneNumber;
        Gmail = gmail;
    }

    public String getBranch() {
        return Branch;
    }

    public void setBranch(String branch) {
        Branch = branch;
    }

    public String getSex() { return Sex; }

    public void setSex(String sex) { Sex = sex; }

    public String getGmail() {
        return Gmail;
    }

    public void setGmail(String gmail) {
        Gmail = gmail;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

}
