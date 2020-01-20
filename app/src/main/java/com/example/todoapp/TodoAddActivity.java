package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TodoAddActivity extends AppCompatActivity {

    private EditText todoNameEdit;
    private EditText todoDateEdit;
    private EditText todoTimeEdit;

    private String todoName;
    private String todoDate;
    private String todoTime;

    private Button cancelButton;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_add);

        todoNameEdit = findViewById(R.id.itemEdit);
        todoDateEdit = findViewById(R.id.dateEdit);
        todoTimeEdit = findViewById(R.id.timeEdit);

        cancelButton = findViewById(R.id.cancelButton);
        addButton = findViewById(R.id.addFinalButton);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoMain = new Intent();
                setResult(Activity.RESULT_CANCELED, gotoMain);
                finish();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todoName = todoNameEdit.getText().toString();
                todoDate = todoDateEdit.getText().toString();
                todoTime = todoTimeEdit.getText().toString();

                Intent addAndGotoMain = new Intent();
                addAndGotoMain.putExtra("todoName", todoName);
                addAndGotoMain.putExtra("todoDate", todoDate);
                addAndGotoMain.putExtra("todoTime", todoTime);
                setResult(Activity.RESULT_OK, addAndGotoMain);
                finish();
            }
        });
    }
}
