package com.example.sqlitedemo.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sqlitedemo.model.Person;
import com.example.sqlitedemo.utils.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XU on 2015/7/11.
 */
public class PersonDao {
    private DatabaseHelper dbHelper = null;

    public PersonDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    /**
     * 向数据库添加一条数据
     * @param person
     */
    public void insert(Person person){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(db.isOpen()){
            db.execSQL("insert into t_persons(name,phone) values (?,?)", new Object[]{person.getName(),person.getPhone()});
            db.close();
        }
    }

    /**
     * 删除一条记录
     * @param id
     */
    public void delete(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(db.isOpen()){
            db.execSQL("delete from t_persons where id=?", new Object[]{id});
            db.close();
        }
    }

    /**
     * 更改一条记录
     * @param person
     */
    public void update(Person person){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db.isOpen()){
            db.execSQL("update t_persons set name=?,phone=? where id=?",new Object[]{person.getName(),person.getPhone(),person.getId()});
            db.close();
        }
    }

    /**
     * 查询单条记录
     * @param id
     * @return
     */
    public Person queryItem(int id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        if (db.isOpen()){
            Cursor cursor = db.rawQuery("select id,name,phone from t_persons where id=?", new String[]{id + ""});
            if(cursor != null && cursor.moveToFirst()){
                int _id = cursor.getInt(0);
                String name = cursor.getString(1);
                String phone = cursor.getString(2);
                db.close();
                return new Person(_id,name,phone);
            }
        }
        db.close();
        return null;
    }

    /**
     * 查询所有记录
     * @return
     */
    public List<Person> queryAll(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        if(db.isOpen()){
            Cursor cursor = db.rawQuery("select id,name,phone from t_persons",null);
            if(cursor != null && cursor.getCount()>0){
                List<Person> personList = new ArrayList<Person>();
                int id;
                String name;
                String phone;
                while(cursor.moveToNext()) {
                    id = cursor.getInt(0);
                    name = cursor.getString(1);
                    phone = cursor.getString(2);
                    personList.add(new Person(id, name, phone));
                }
                db.close();
                return personList;
            }
            db.close();
        }
        return null;
    }
}
