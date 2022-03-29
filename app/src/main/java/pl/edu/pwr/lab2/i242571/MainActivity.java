package pl.edu.pwr.lab2.i242571;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter viewAdapter;
    private RecyclerView.LayoutManager viewManager;

    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHandler(this);
        db.openDatabase();

        viewManager = new LinearLayoutManager(this);
        //viewAdapter = new MainActivityAdapter();

        //recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply(setHas)
    }
}