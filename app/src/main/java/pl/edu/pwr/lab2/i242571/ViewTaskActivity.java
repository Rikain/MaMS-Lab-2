package pl.edu.pwr.lab2.i242571;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);

        TextView titleTv = findViewById(R.id.title_tv2);
        TextView dateTv = findViewById(R.id.dueDate_tv2);
        TextView descTv = findViewById(R.id.desc_tv2);
        TextView typeTv = findViewById(R.id.type_tv2);
        ImageView imageIv = findViewById(R.id.imageView);
        TextView statusTv = findViewById(R.id.status_tv2);
        ImageView imageIvStatus = findViewById(R.id.status_iv);

        Intent intent = getIntent();
        String title = intent.getStringExtra(MainActivity.TileMessage);
        int type = intent.getIntExtra(MainActivity.TypeMessage, TaskModel.TaskType.todo);
        String date = intent.getStringExtra(MainActivity.DateMessage);
        String desc = intent.getStringExtra(MainActivity.DescMessage);
        int status = intent.getIntExtra(MainActivity.StatusMessage, TaskModel.Status.pending);

        titleTv.setText(title);
        dateTv.setText(date);
        descTv.setText(desc);
        int image = TaskModel.TaskType.typeToImage(type);
        imageIv.setImageResource(image);
        String typeString = typeToString(type);
        typeTv.setText(typeString);
        String statusString = statusToText(status);
        statusTv.setText(statusString);
        int imageStatus = TaskModel.Status.statusToImage(status);
        imageIvStatus.setImageResource(imageStatus);
    }

    public String typeToString(int type){
        switch (type){
            case TaskModel.TaskType.email:
                return getResources().getString(R.string.email);
            case TaskModel.TaskType.phone:
                return getResources().getString(R.string.phone);
            case TaskModel.TaskType.meeting:
                return getResources().getString(R.string.meeting);
            case TaskModel.TaskType.todo:
            default:
                return getResources().getString(R.string.todo);
        }
    }

    public String statusToText(int status){
        if(status == TaskModel.Status.done){
            return getResources().getString(R.string.status_done);
        }else{
            return getResources().getString(R.string.status_pending);
        }
    }
}