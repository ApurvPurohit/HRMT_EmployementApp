package com.example.android.hrm;

import androidx.appcompat.app.AppCompatActivity;

public class Employer  {

    String empname;
    String empnumber;
    String empaddress;
    public void Employer(){

    }

    public String getEmpname() {
        return empname;
    }

    public String getEmpnumber() {
        return empnumber;
    }

    public String getEmpaddress() {
        return empaddress;
    }

    public Employer(String empname, String empnumber, String empaddress) {
        this.empname = empname;
        this.empnumber = empnumber;
        this.empaddress = empaddress;
    }



}
