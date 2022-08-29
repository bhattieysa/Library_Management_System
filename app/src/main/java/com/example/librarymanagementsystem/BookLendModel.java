package com.example.librarymanagementsystem;

public class BookLendModel {

    private String Bookname, Bookid, Lendingfrom, Lendingdays;


    public BookLendModel(){

    }

    public String getFullname() {
        return Bookname;
    }

    public String getBookid() {
        return Bookid;
    }

    public String getLendingfro() {
        return Lendingfrom;
    }

    public String getLendingdays() {
        return Lendingdays;
    }

    public BookLendModel(String Fullname, String Bookid, String Lendingdays, String Lendingfrom) {
        this.Bookname = Fullname;
        this.Bookid = Bookid;
        this.Lendingfrom = Lendingfrom;
        this.Lendingdays = Lendingdays;

    }


}
