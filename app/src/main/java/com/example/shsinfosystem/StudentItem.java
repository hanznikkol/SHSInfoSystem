package com.example.shsinfosystem;

public class StudentItem {
    public String firstName, middleInitial, lastName, strand, gender, address, mobileNum;
    public int yearLevel,  age, accountNumber;

    public StudentItem(){}
    public StudentItem(String firstName, String middleInitial, String lastName, String strand, String gender, String address, int yearLevel, String mobileNum, int age, int accountNumber){
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.strand = strand;
        this.gender = gender;
        this.address = address;
        this.yearLevel = yearLevel;
        this.mobileNum = mobileNum;
        this.age = age;
        this.accountNumber = accountNumber;
    }
}
