package com.example.sqlitedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlitedemo.dao.PersonDao;
import com.example.sqlitedemo.model.Person;
import com.example.sqlitedemo.utils.DatabaseHelper;


public class MainActivity extends Activity {
    private EditText etName;
    private EditText etPhone;
    private Button btAdd;
    private Button btList;
    private DatabaseHelper dbHelper = null;
    private PersonDao dao = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /* 获得对应的View */
        etName = (EditText) findViewById(R.id.et_name);
        etPhone = (EditText)findViewById(R.id.et_phone);
        btAdd = (Button)findViewById(R.id.btn_add);
        btList = (Button)findViewById(R.id.btn_list);
        btAdd.setOnClickListener(new btAddListener());
        btList.setOnClickListener(new btListListener());
        dbHelper = new DatabaseHelper(this);
        dao = new PersonDao(this);
    }
    class btAddListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            String name = etName.getText().toString();
            String phone = etPhone.getText().toString();
            Person person = new Person(0,name,phone);
            dao.insert(person);
            Toast.makeText(MainActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
        }
    }
    class btListListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,ShowActivity.class);
            startActivity(intent);
        }
    }
}
