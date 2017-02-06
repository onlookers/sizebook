package com.example.jcao5.sizebook;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//SAVE THE DETAILS
public class DetailActivity extends AppCompatActivity {

    private Button btnBack;

    private EditText tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9;
    private Data data;
    private TextView tvUp,tvDel;//edit and delete


    private MyDbOpenHelper helper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);
        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        data = (Data) intent.getSerializableExtra("data");
        tv1.setText(data.getName());
        tv2.setText(data.getDate());
        tv3.setText(data.getNeck());
        tv4.setText(data.getBust());
        tv5.setText(data.getChest());
        tv6.setText(data.getWaist());
        tv7.setText(data.getHip());
        tv8.setText(data.getInseam());
        tv9.setText(data.getComment());



    }

    private void initView() {
        helper = new MyDbOpenHelper(this);
        db = helper.getWritableDatabase();
        //return
        btnBack = (Button) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv1 = (EditText) findViewById(R.id.tv1);
        tv2 = (EditText) findViewById(R.id.tv2);
        tv3 = (EditText) findViewById(R.id.tv3);
        tv4 = (EditText) findViewById(R.id.tv4);
        tv5 = (EditText) findViewById(R.id.tv5);
        tv6 = (EditText) findViewById(R.id.tv6);
        tv7 = (EditText) findViewById(R.id.tv7);
        tv8 = (EditText) findViewById(R.id.tv8);
        tv9 = (EditText) findViewById(R.id.tv9);

        //edit and delete
        tvUp = (TextView) findViewById(R.id.tv_up);
        tvDel = (TextView) findViewById(R.id.tv_del);
        //click the edit and save the data after edit
        tvUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = tv1.getText().toString();
                if(TextUtils.isEmpty(name)){
                    Toast.makeText(DetailActivity.this,"The name cannot be null",Toast.LENGTH_LONG).show();
                }else{//If not equal to null, add the information to the database.
                    ContentValues cv = new ContentValues();
                    cv.put("name",name);
                    cv.put("date",tv2.getText().toString());
                    cv.put("neck",tv3.getText().toString());
                    cv.put("bust",tv4.getText().toString());
                    cv.put("chest",tv5.getText().toString());
                    cv.put("waist",tv6.getText().toString());
                    cv.put("hip",tv7.getText().toString());
                    cv.put("inseam",tv8.getText().toString());
                    cv.put("comment",tv9.getText().toString());
                    db.update("SizeBook",cv,"_id="+data.getId(),null);
                    Toast.makeText(DetailActivity.this,"edit successfully",Toast.LENGTH_LONG).show();
                }

            }
        });

        //Click delete,
        tvDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //dialog appear
                AlertDialog dialog = new AlertDialog.Builder(DetailActivity.this).
                        setTitle("").setMessage("Do you want to delete this size record?").setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int id = data.getId();
                                Log.e("XXid",id+"");
                                // db.delete("SizeBook","name="+data.getName(),null);
                                db.delete("SizeBook","_id="+data.getId(),null);
                                finish();
                            }
                        }).setNegativeButton("No", null).create();
                dialog.show();


            }
        });


    }
}
