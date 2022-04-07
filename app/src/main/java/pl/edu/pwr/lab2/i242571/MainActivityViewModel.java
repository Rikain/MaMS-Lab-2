package pl.edu.pwr.lab2.i242571;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import java.util.List;


public class MainActivityViewModel extends ViewModel {
    List<TaskModel> values;
    DatabaseHandler db;

    public MainActivityViewModel(Context context){
        super();
        db = new DatabaseHandler(context);
        db.openDatabase();
        updateTasks();
    }

    public TaskModel get(int pos){
        return values.get(pos);
    }

    public void addTask(TaskModel task){
        long id = db.insertTask(task);
        task.setId(id);
        values.add(task);
    }

    public void removeTask(int pos){
        db.deleteTask(values.get(pos).getId());
        values.remove(pos);
    }

    public void markTaskAsDone(int pos){
        values.get(pos).setStatus(TaskModel.Status.done);
        db.updateStatus(values.get(pos).getId(), TaskModel.Status.done);
    }

    public void updateTasks(){
        values = db.getAllTasks();
    }
}

