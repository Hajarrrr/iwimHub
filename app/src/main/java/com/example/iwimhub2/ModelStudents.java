package com.example.iwimhub2;

public class ModelStudents {
    String isStudent,UserName,PhoneNumber;
    public ModelStudents (){};

    public String getIsStudent() {
        return isStudent;
    }

    public void setIsStudent(String isStudent) {
        this.isStudent = isStudent;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public ModelStudents(String isStudent, String userName, String phoneNumber) {
        this.isStudent = isStudent;
        UserName = userName;
        PhoneNumber = phoneNumber;
    }
}


