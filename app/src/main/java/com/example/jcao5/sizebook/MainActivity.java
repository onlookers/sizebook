package com.example.jcao5.sizebook;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView tvCount;//record the number of messages of size
    private Button btn;//the button of add message
    private ListView lv ;//the list of record

    private SizeAdapter adapter;
    private ArrayList<Data> list;

    private MyDbOpenHelper helper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {

        helper = new MyDbOpenHelper(this);
        db = helper.getWritableDatabase();

        list = new ArrayList<>();//init list;
        //add data to List ，search and output from database
        adapter = new SizeAdapter(MainActivity.this,list);
        list.addAll(select());
        Log.e("XXXXXXXXXXXX",list+"");
        if(list!=null){//make sure if the list is empty, record the count of number of size message
            tvCount.setText("count:"+list.size());
        }else{
            tvCount.setText("count:0");
        }


        lv.setAdapter(adapter);//set adapter
    }
    //read the list information from the database
    private ArrayList<Data> select(){
        String sql = "select * from SizeBook";
        Cursor cursor = db.rawQuery(sql,null);
        ArrayList<Data> dataList = new ArrayList<Data>();
        //keep reading data from database
        while(cursor.moveToNext()){
            Data data = new Data(cursor.getString(1),cursor.getString(2),
                    cursor.getString(3),cursor.getString(4),
                    cursor.getString(5),cursor.getString(6),
                    cursor.getString(7),cursor.getString(8),cursor.getString(9));
            data.setId(cursor.getInt(0));
            dataList.add(data);//add to datalist
            adapter.notifyDataSetChanged();//reflush
        }
        cursor.close();//close the cursor
        return dataList;
    }

    private void initView() {
        tvCount = (TextView) findViewById(R.id.tv_count);
        btn = (Button) findViewById(R.id.main_btn);
        lv = (ListView) findViewById(R.id.main_lv);

        //add new record, jump to the add activity intent
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });


        //click each data recorded to see the details ，jump to details page,and can be edit
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent2 = new Intent();
                intent2.setClass(MainActivity.this,DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data",list.get(position));
                intent2.putExtras(bundle);
                startActivity(intent2);
            }
        });
    }


}
