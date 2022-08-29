package com.example.librarymanagementsystem;

public class Issueandreturnmodel {
        private String Bookname, Bookid, ReturnDate, IssueDate;


        public Issueandreturnmodel(){

        }

    public String getBookname() {
        return Bookname;
    }

    public String getBookid() {
        return Bookid;
    }

    public String getReturnDate() {
        return ReturnDate;
    }

    public String getIssueDate() {
        return IssueDate;
    }

    public Issueandreturnmodel(String Bookname, String Bookid, String ReturnDate, String IssueDate) {
            this.Bookname = Bookname;
            this.Bookid = Bookid;
            this.ReturnDate = ReturnDate;
            this.IssueDate = IssueDate;

        }


    }
