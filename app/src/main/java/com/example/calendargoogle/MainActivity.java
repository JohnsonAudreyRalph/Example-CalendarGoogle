package com.example.calendargoogle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText Title, Location, Description;
    Button Add_Event;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Title = findViewById(R.id.etTile);
        Location = findViewById(R.id.etLocation);
        Description = findViewById(R.id.etDescription);
        Add_Event = findViewById(R.id.btnAdd);

        Add_Event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Title.getText().toString().isEmpty() && !Location.getText().toString().isEmpty() && !Description.getText().toString().isEmpty()){
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE, Title.getText().toString()); // Tựa đề
                    intent.putExtra(CalendarContract.Events.EVENT_LOCATION, Location.getText().toString()); // Địa điểm xảy ra sự kiện
                    intent.putExtra(CalendarContract.Events.DESCRIPTION, Description.getText().toString()); // Mô tả sự kiện
                    // Đặt thời gian bắt đầu và kết thúc cho sự kiện
                    Calendar startCalendar = Calendar.getInstance();
                    startCalendar.set(2023, Calendar.AUGUST, 25, 0, 0, 0);
                    long startMillis = startCalendar.getTimeInMillis();
                    Calendar endCalendar = Calendar.getInstance();
                    endCalendar.set(2023, Calendar.AUGUST, 26, 0, 0, 0);
                    long endMillis = endCalendar.getTimeInMillis();
                    intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startMillis);
                    intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endMillis);
                    intent.putExtra(CalendarContract.Events.ALL_DAY, true);// Lấy cả ngày hôm đấy cho sự kiện, nếu là True thì lấy cả ngày, False thì cần setup thời gian nữa
                    intent.putExtra(Intent.EXTRA_EMAIL, "rowan@example.com,trevor@example.com"); // Thêm người tham gia sự kiện thông qua việc Add Email

                    if(intent.resolveActivity(getPackageManager()) != null){
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this, "Lỗi!! Hãy kiểm tra lại thiết bị để đảm bảo có ứng dụng lịch", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Hãy điền đầy đủ thông tin cần thiết", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}