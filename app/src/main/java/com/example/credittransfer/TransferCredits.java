package com.example.credittransfer;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TransferCredits extends AppCompatActivity {

    private Button insert,go_back;
    private EditText user_from,user_credit,user_to;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_credits);
        insert = (Button)findViewById(R.id.insert);
        go_back =(Button)findViewById(R.id.go_back);

        user_from = (EditText)findViewById(R.id.from);
        user_credit = (EditText)findViewById(R.id.credit);
        user_to = (EditText)findViewById(R.id.to);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper mydb = new DatabaseHelper(TransferCredits.this);
                String from = user_from.getText().toString();
                Integer cred =  Integer.valueOf(user_credit.getText().toString());
                String to = user_to.getText().toString();
                SQLiteDatabase db = mydb.getWritableDatabase();
                boolean res = mydb.update_credit(from,cred,to);
                if(res == true){
                    Toast.makeText(TransferCredits.this,"Money Transfered",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TransferCredits.this,CreaditList.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(TransferCredits.this,"Money Not Transfered",Toast.LENGTH_SHORT).show();
                }


            }
        });

        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TransferCredits.this,CreaditList.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
