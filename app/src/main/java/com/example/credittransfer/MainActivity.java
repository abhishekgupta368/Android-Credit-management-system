package com.example.credittransfer;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mydb;
    EditText name,cred;
    Button submit,show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DatabaseHelper(this);
        name =(EditText)findViewById(R.id.name);
        cred = (EditText)findViewById(R.id.creadit);
        submit = (Button)findViewById(R.id.insert);
        show  = (Button)findViewById(R.id.go_back);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean query = mydb.insetData(name.getText().toString(),cred.getText().toString());
                if(query){
                    Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this,CreaditList.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(MainActivity.this,"Data Not inserted",Toast.LENGTH_LONG).show();
                }
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CreaditList.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
