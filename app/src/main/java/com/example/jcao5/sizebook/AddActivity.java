package com.example.jcao5.sizebook;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//add the record
public class AddActivity extends AppCompatActivity {

    private EditText et1,et2,et3,et4,et5,et6,et7,et8,et9;
    private Button btn;
    private Data data ;
    private MyDbOpenHelper helper;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
    }


    private void initView() {
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        et3 = (EditText) findViewById(R.id.et3);
        et4 = (EditText) findViewById(R.id.et4);
        et5 = (EditText) findViewById(R.id.et5);
        et6 = (EditText) findViewById(R.id.et6);
        et7 = (EditText) findViewById(R.id.et7);
        et8 = (EditText) findViewById(R.id.et8);
        et9 = (EditText) findViewById(R.id.et9);

        helper = new MyDbOpenHelper(this);
        db = helper.getWritableDatabase();

        //save the record,
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click the aave to save the record into database, and finish the current activity
                String name = et1.getText().toString();
                Log.e("name",name);
                if(TextUtils.isEmpty(name)){
                    Toast.makeText(AddActivity.this,"The name cannot be null",Toast.LENGTH_LONG).show();
                }else{//If not equal to null, add the information to the database.
                    ContentValues cv = new ContentValues();
                    cv.put("name",name);
                    cv.put("date",et2.getText().toString());
                    cv.put("neck",et3.getText().toString());
                    cv.put("bust",et4.getText().toString());
                    cv.put("chest",et5.getText().toString());
                    cv.put("waist",et6.getText().toString());
                    cv.put("hip",et7.getText().toString());
                    cv.put("inseam",et8.getText().toString());
                    cv.put("comment",et9.getText().toString());
                    db.insert("SizeBook",null,cv);
                    finish();
                }
            }
        });
    }
}
