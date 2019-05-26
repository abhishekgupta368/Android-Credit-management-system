package com.example.credittransfer;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class CreaditList extends AppCompatActivity {
    Button transfer;
    Button insert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_creadit_list);
        ArrayList<user> uCredits = new ArrayList<user>();
        DatabaseHelper mydb = new DatabaseHelper(CreaditList.this);
        Cursor cur = mydb.getAllData();

        while (cur.moveToNext()){
            uCredits.add(new user(cur.getString(1).toString(),Integer.valueOf(cur.getString(2).toString())));
        }

//        uCredits.add(new user("Abhishek Gupta",5000));
//        uCredits.add(new user("Neeru Gupta", 5000));
//        uCredits.add(new user("Somesh Gupta", 5000));

        MyAdapter credits = new MyAdapter(CreaditList.this, uCredits);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) findViewById(R.id.cred_list);
        listView.setAdapter(credits);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),""+position,Toast.LENGTH_SHORT).show();
            }
        });
        transfer =(Button)findViewById(R.id.transfer);
        insert = (Button)findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(CreaditList.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreaditList.this,TransferCredits.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
