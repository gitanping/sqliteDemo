package com.example.sqlitedemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sqlitedemo.dao.PersonDao;
import com.example.sqlitedemo.model.Person;

import java.util.ArrayList;
import java.util.List;

;

/**
 * Created by XU on 2015/7/11.
 */
public class ShowActivity extends Activity{
    private static final  String TAG = "ShowActivity";
    private PersonDao dao=null;
    private List<Person> personList = new ArrayList<Person>();
    private ListView lvList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        lvList = (ListView)findViewById(R.id.lv_list);
        Log.i(TAG,"进入第二个activity");
        show();
    }
    public void show(){
        dao = new PersonDao(this);
        personList = dao.queryAll();
        for(Person person : personList){
            Log.i(TAG,person.toString());
        }
        MyAdapter adapter = new MyAdapter(this);
        lvList.setAdapter(adapter);
    }
    class MyAdapter extends BaseAdapter{
        private LayoutInflater mInflater = null;

        public MyAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        /**
         * 获取列表大小
         * @return
         */
        @Override
        public int getCount() {
            return personList.size();
        }

        @Override
        public Object getItem(int position) {
            return personList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        /**
         * 这个是adapter种最重要的
         * @param position
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = mInflater.inflate(R.layout.person_item,null);
                TextView tvItemName = (TextView)convertView.findViewById(R.id.tv_item_name);
                TextView tvItemPhone = (TextView)convertView.findViewById(R.id.tv_item_phone);
                tvItemName.setText(personList.get(position).getName());
                tvItemPhone.setText(personList.get(position).getPhone());
            }else{
                TextView tvItemName = (TextView)convertView.findViewById(R.id.tv_item_name);
                TextView tvItemPhone = (TextView)convertView.findViewById(R.id.tv_item_phone);
                tvItemName.setText(personList.get(position).getName());
                tvItemPhone.setText(personList.get(position).getPhone());
            }
            return convertView;
        }
    }
}
