package pl.edu.pwr.lab2.i242571;

import android.icu.text.SimpleDateFormat;
import android.os.Build;

import java.text.DateFormat;
import java.util.Date;

public class TaskModel {
    TaskModel(int id, String title, int type, String description, String dueDate, int status){
        this.id = id;
        this.title = title;
        this.type = type;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
    }
    TaskModel(){}


    public static class TaskType{
        public static final int todo = 0;
        public static final int email = 1;
        public static final int phone = 2;
        public static final int meeting = 3;
    }

    public static class Status{
        public static final int pending = 0;
        public static final int done = 1;
    }

    private int status;
    private int id;
    private String title, description;
    private int type;
    private String dueDate;

    public static String getCurrentDate(){
        String date = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            date = new SimpleDateFormat("dd/mm/yyyy").format(new Date());
        }
        return date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
