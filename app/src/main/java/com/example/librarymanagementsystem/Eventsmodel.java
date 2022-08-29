package com.example.librarymanagementsystem;

public class Eventsmodel {


        private String EventName, Description, EventDate;


        public Eventsmodel(){

        }


        public Eventsmodel(String EventDate,String EventName, String Description) {
            this.EventDate = EventDate;
            this.EventName = EventName;
            this.Description = Description;


        }

    public String getEventName() {
        return EventName;
    }

    public String getDescription() {
        return Description;
    }

    public String getEventDate() {
        return EventDate;
    }
}
