package com.example.librarymanagementsystem;

class Recyclermodel {
    String bookname,bookSubject,bookid;
    String bookImage;

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getBookSubject() {
        return bookSubject;
    }

    public void setBookSubject(String bookSubject) {
        this.bookSubject = bookSubject;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getBookImage() {
        return bookImage;
    }

    public void setBookImage(String bookImage) {
        this.bookImage = bookImage;
    }

    public Recyclermodel(String bookname, String bookSubject, String bookid, String bookImage) {
        this.bookname = bookSubject;
        this.bookSubject = bookid;
        this.bookid = bookname;
        this.bookImage = bookImage;
    }

    public Recyclermodel()
    {
    }
}
