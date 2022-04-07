package pl.edu.pwr.lab2.i242571;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity { //implements OnItemClickListener
    private RecyclerView recyclerView;
    private RecyclerView.Adapter viewAdapter;
    private RecyclerView.LayoutManager viewManager;
    private Context mainContext;
    private final View.OnClickListener mOnClickListener = new MyOnClickListener();

    public static final String TileMessage = "pl.edu.pwr.lab1.i242571.TITLE_MESSAGE";
    public static final String TypeMessage = "pl.edu.pwr.lab1.i242571.TYPE_MESSAGE";
    public static final String DescMessage = "pl.edu.pwr.lab1.i242571.DESC_MESSAGE";
    public static final String DateMessage = "pl.edu.pwr.lab1.i242571.DATE_MESSAGE";

    public static final int AddTaskActivityRequestCode = 15;

    private MainActivityViewModel viewModel;

    class MyOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(final View view) {
            int itemPosition = recyclerView.getChildLayoutPosition(view);
            TaskModel task = viewModel.get(itemPosition);
            Intent intent = new Intent(mainContext, ViewTaskActivity.class);
            intent.putExtra(MainActivity.DateMessage, task.getDueDate());
            intent.putExtra(MainActivity.TileMessage, task.getTitle());
            intent.putExtra(MainActivity.TypeMessage, task.getType());
            intent.putExtra(MainActivity.DescMessage, task.getDescription());
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new MainActivityViewModel(this);

        viewManager = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.recycler_view);

        mainContext = this;

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        viewAdapter = new MainActivityAdapter(viewModel.values, mOnClickListener); //, this
        recyclerView.setAdapter(viewAdapter);
        recyclerView.setLayoutManager(viewManager);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT + ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction == ItemTouchHelper.RIGHT){
                    viewModel.removeTask(viewHolder.getAdapterPosition());
                    viewAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                }
                else {
                    viewModel.markTaskAsDone(viewHolder.getAdapterPosition());
                    viewAdapter.notifyItemChanged(viewHolder.getAdapterPosition());
                }
            }
        }).attachToRecyclerView(recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add) {
            Intent intent = new Intent(this, AddTaskActivity.class);
            startActivityForResult(intent, AddTaskActivityRequestCode);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (AddTaskActivityRequestCode) : {
                if (resultCode == AddTaskActivity.RESULT_OK) {
                    int type = data.getIntExtra(TypeMessage, TaskModel.TaskType.todo);
                    String date = data.getStringExtra(DateMessage);
                    String title = data.getStringExtra(TileMessage);
                    String desc = data.getStringExtra(DescMessage);

                    if(!title.isEmpty() && !date.isEmpty()){
                        TaskModel task = new TaskModel(title, type, desc, date);
                        viewModel.addTask(task);
                        viewAdapter.notifyDataSetChanged();
                    }
                }
                break;
            }
        }
    }

    //@Override
    //public View.OnClickListener onItemClicked(TaskModel task) {
    //    Toast.makeText(this,task.getTitle(),Toast.LENGTH_LONG).show();
        //Intent intent = new Intent(this, AddTaskActivity.class);
        //intent.putExtra(BMI_MESSAGE, bmi_tv.getText().toString());
        //startActivity(intent);
    //    return null;
    //}
}