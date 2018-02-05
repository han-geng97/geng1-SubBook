package com.example.geng1.geng1_subbook;

import android.support.v7.app.AppCompatActivity;

import java.util.Date;
import java.util.Locale;

/**
 * Created by geng1 on 2/5/18.
 */

public class Subscription extends AppCompatActivity {

    private String usage;
    private String date;
    private String charge;
    private String comment;

    public Subscription(String usage, String date, String charge, String comment){
        this.usage = usage;
        this.date = date;
        this.charge = charge;
        this.comment = comment;
    }

    public Subscription(String usage, String date, String charge){
        this.usage = usage;
        this.date = date;
        this.charge = charge;
        this.comment = "";
    }

    /** getters and setters**/
    public String getUsage() {
        return usage;
    }
    public void setUsage(String Usage){
        this.usage = Usage;
    }

    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date = date;
    }

    public String getCharge(){
        return charge;
    }
    public void setCharge(String charge){
        this.charge = charge;
    }

    public String getComment(){
        return comment;
    }
    public void setComment(String comment){
        this.comment = comment;
    }

    public String toString(){
        return (usage + "\n"+ date + "\t$" + charge + "\n" + comment);
    }

}
