package sample;

import java.util.function.DoubleUnaryOperator;

public class ChucVu {
    private String Pos;
    private Double Coe_Salary;
    public ChucVu(){

    }
    public ChucVu(String position,Double coe_Salary) {
        Coe_Salary = coe_Salary;
        Pos = position;
    }

    public String getPosition() {
        return Pos;
    }

    public void setPosition(String position) {
        Pos = position;
    }
    public void setCoe_Salary(Double coe_Salary){
        Coe_Salary= coe_Salary;
    }
    public Double getCoe_Salary() {
        return Coe_Salary;
    }
}
