package com.example.todoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Button addButton;
    private TextView emptyTextView;

    private RecyclerView todoList;
    private TodoAdapter mAdapter;
    private LinearLayoutManager layoutManager;

    private static final int TODO_EVENT_REQUEST = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // RecyclerView
        todoList = (RecyclerView) findViewById(R.id.todoList);
        // LinearLayoutManager
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        todoList.setLayoutManager(layoutManager);
        // Set and adapter
        mAdapter = new TodoAdapter();
        todoList.setAdapter(mAdapter);

        // Adding swipe action using SwipeController and ItemTouchHelper
        final SwipeController swipeController = new SwipeController(new SwipeControllerActions() {
            @Override
            public void onDelClicked(int position) {
                mAdapter.getDataList().remove(position);
                mAdapter.notifyItemRemoved(position);
                mAdapter.notifyItemRangeChanged(position, mAdapter.getItemCount());

                if (mAdapter.getDataList().isEmpty()) {
                    todoList.setVisibility(View.GONE);
                    emptyTextView.setVisibility(View.VISIBLE);
                }
            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeController);
        itemTouchHelper.attachToRecyclerView(todoList);

        todoList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });

        // Check if TODOlist is empty
        emptyTextView = (TextView)findViewById(R.id.noTodoTextView);
        if (mAdapter.getDataList().isEmpty()) {
            todoList.setVisibility(View.GONE);
            emptyTextView.setVisibility(View.VISIBLE);
        } else {
            todoList.setVisibility(View.VISIBLE);
            emptyTextView.setVisibility(View.GONE);
        }

        // Button for adding TODOs
        addButton = (Button) findViewById(R.id.todoAddButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent gotoTodoAddAct = new Intent(MainActivity.this, TodoAddActivity.class);
                startActivityForResult(gotoTodoAddAct, TODO_EVENT_REQUEST);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TODO_EVENT_REQUEST){
            if (resultCode == Activity.RESULT_OK) {
                TodoItem newTodo = new TodoItem(data.getStringExtra("todoName"),
                        data.getStringExtra("todoDate"),
                        data.getStringExtra("todoTime"));
                mAdapter.upDateDataList(newTodo);
                mAdapter.notifyDataSetChanged();

                scheduleNotification(getNotification(data.getStringExtra("todoName"),data.getStringExtra("todoDate")),
                        data.getStringExtra("todoDate"));

                emptyTextView.setVisibility(View.GONE);
                todoList.setVisibility(View.VISIBLE);
            }
        }
    }

    private void scheduleNotification(Notification notification, String date) {
        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        if (date.equals("")) {
            long futureInMillis = SystemClock.elapsedRealtime();
            alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
        } else {
            String[] parsedDate = date.split("/");
            int mm = Integer.parseInt(parsedDate[0]);
            int dd = Integer.parseInt(parsedDate[1]);
            int yyyy = Integer.parseInt(parsedDate[2]);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
            String currentDateandTime = sdf.format(new Date());
            int curyyyy = Integer.parseInt(currentDateandTime.substring(0, 4));
            int curmm = Integer.parseInt(currentDateandTime.substring(4, 6));
            int curdd = Integer.parseInt(currentDateandTime.substring(6, 8));

            if ((curyyyy == yyyy) && (curmm == mm) && (curdd == dd)) {
                long futureInMillis = SystemClock.elapsedRealtime();
                alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
            } else {
                calendar.set(yyyy, mm, dd, 0,0);
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            }
        }
    }

    private Notification getNotification(String title, String description) {
        Notification.Builder builder;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            builder = new Notification.Builder(this, "my_channel_01");
        } else {
            builder = new Notification.Builder(this);
        }
        builder.setContentTitle(title);
        builder.setContentText(description);
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        return builder.build();
    }

}
