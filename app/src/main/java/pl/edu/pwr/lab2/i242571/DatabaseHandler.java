package pl.edu.pwr.lab2.i242571;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String NAME = "taskDatabase";
    private static final String TODO_TABLE = "todo";
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String STATUS = "status";
    private static final String TYPE = "type";
    private static final String DATE = "dueDate";
    private static final String CREATE_TODO_TABLE = "CREATE TABLE " + TODO_TABLE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TITLE + " TEXT, "
            + DESCRIPTION + " TEXT, " + DATE + " TEXT, " + TYPE + " INTEGER, " + STATUS + " INTEGER)";

    private SQLiteDatabase db;

    public DatabaseHandler(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TODO_TABLE);
        onCreate(db);
    }

    public void openDatabase() {
        db = this.getWritableDatabase();
    }

    public long insertTask(TaskModel task) {
        ContentValues cv = new ContentValues();
        cv.put(TITLE, task.getTitle());
        cv.put(DESCRIPTION, task.getDescription());
        cv.put(TYPE, task.getType());
        cv.put(STATUS, task.getStatus());
        cv.put(DATE, task.getDueDate());
        return db.insert(TODO_TABLE, null, cv);
    }

    public List<TaskModel> getAllTasks() {
        List<TaskModel> taskList = new ArrayList<>();
        Cursor cur = null;
        db.beginTransaction();
        try {
            cur = db.query(TODO_TABLE, null, null, null, null, null, null, null);
            if (cur != null) {
                if (cur.moveToFirst()) {
                    do {
                        TaskModel task = new TaskModel();
                        task.setId(cur.getInt(cur.getColumnIndex(ID)));
                        task.setTitle(cur.getString(cur.getColumnIndex(TITLE)));
                        task.setType(cur.getInt(cur.getColumnIndex(TYPE)));
                        task.setDescription(cur.getString(cur.getColumnIndex(DESCRIPTION)));
                        task.setDueDate(cur.getString(cur.getColumnIndex(DATE)));
                        task.setStatus(cur.getInt(cur.getColumnIndex(STATUS)));
                        taskList.add(task);
                    }
                    while (cur.moveToNext());
                }
            }
        } finally {
            db.endTransaction();
            assert cur != null;
            cur.close();
        }
        return taskList;
    }

    public void updateStatus(long id, int status) {
        ContentValues cv = new ContentValues();
        cv.put(STATUS, status);
        db.update(TODO_TABLE, cv, ID + "= ?", new String[]{String.valueOf(id)});
    }

    public void deleteTask(long id) {
        db.delete(TODO_TABLE, ID + "= ?", new String[]{String.valueOf(id)});
    }
}