package pl.edu.pwr.lab2.i242571;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;

public class AddTaskActivity extends AppCompatActivity {
    private int mYear,mMonth,mDay;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        final TextView dateTv = (TextView) findViewById(R.id.show_date_tv);
        final Button submit = (Button) findViewById(R.id.add_button);
        final Button pickDate = (Button) findViewById(R.id.date_button);
        final Calendar myCalendar = Calendar.getInstance();
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = TaskModel.dateFormat;
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);
                dateTv.setText(sdf.format(myCalendar.getTime()));
            }


        };

        pickDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(AddTaskActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                if (year < mYear)
                                    view.updateDate(mYear,mMonth,mDay);

                                if (monthOfYear < mMonth && year == mYear)
                                    view.updateDate(mYear,mMonth,mDay);

                                if (dayOfMonth < mDay && year == mYear && monthOfYear == mMonth)
                                    view.updateDate(mYear,mMonth,mDay);

                                String text = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                                dateTv.setText(text);

                            }
                        }, mYear, mMonth, mDay);
                dpd.getDatePicker().setMinDate(System.currentTimeMillis());
                dpd.show();

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = (RadioButton)findViewById(selectedId);
                String type;
                if (selectedRadioButton != null){
                    type = selectedRadioButton.getText().toString();
                }else{
                    type = getResources().getString(R.string.todo);
                }
                String date = mDay + "/" + (mMonth + 1) + "/" + mYear;
                EditText titleEt  = (EditText) findViewById(R.id.title_et);
                EditText descEt  = (EditText) findViewById(R.id.desc_et);
                String title = titleEt.getText().toString();
                String desc = descEt.getText().toString();
                Intent resultIntent = new Intent();
                resultIntent.putExtra(MainActivity.DateMessage, date);
                resultIntent.putExtra(MainActivity.TileMessage, title);
                resultIntent.putExtra(MainActivity.TypeMessage, stringToType(type));
                resultIntent.putExtra(MainActivity.DescMessage, desc);
                setResult(AddTaskActivity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    public int stringToType(String type){
        if (type.equals(getResources().getString(R.string.email))) {
            return TaskModel.TaskType.email;
        }
        else if (type.equals(getResources().getString(R.string.phone))) {
            return TaskModel.TaskType.phone;
        }
        else if (type.equals(getResources().getString(R.string.meeting))) {
            return TaskModel.TaskType.meeting;
        }
        else{
            return TaskModel.TaskType.todo;
        }
    }
}