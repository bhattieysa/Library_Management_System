package com.example.librarymanagementsystem;

public class BookReserveModel {

    private String Fullname,Bookname, Bookid, Email, Subject;


    public BookReserveModel(){

    }

    public String getFullname() {
        return Fullname;
    }

    public String getBookid() {
        return Bookid;
    }

    public String getBookname() {
        return Bookname;
    }

    public String getEmail() {
        return Email;
    }

    public String getSubject() {
        return Email;
    }

    public BookReserveModel(String Fullname,String Email, String Bookname, String Bookid, String Subject) {
        this.Fullname = Fullname;
        this.Email = Email;
        this.Bookname = Bookname;
        this.Bookid = Bookid;
        this.Subject = Subject;

    }


}
