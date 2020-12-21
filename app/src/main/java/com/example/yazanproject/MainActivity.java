package com.example.yazanproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name, doctors,marks;
    Button add ,viewData;

    DatabaseHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB=new DatabaseHelper(this);
       name =findViewById(R.id.name);
        doctors=findViewById(R.id.doctors);
        marks=findViewById(R.id.marks);
        add=findViewById(R.id.adddata);
        viewData=findViewById(R.id.showd);
        AddDAta();
        viewAll();


    }
    public  void viewAll()
    {
     viewData.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            Cursor res = myDB.getAllData();
            if(res.getCount()==0){
                showMessage("error","nothig found");
                return;
            }
            StringBuffer buffer=new StringBuffer();
            while (res.moveToNext()){
                buffer.append("Id  :"+res.getString(0)+"\n");
                buffer.append("name  :"+res.getString(1)+"\n");
                buffer.append("doctors  :"+res.getString(2)+"\n");
                buffer.append("marks  :"+res.getString(3)+"\n\n");




            }
               showMessage("data ",buffer.toString());
         }
     });
    }
    public  void showMessage(String title,String Message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }
    public void AddDAta()
    {
add.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
       boolean isInserted= myDB.InsertData(name.getText().toString(),doctors.getText()
        .toString(),marks.getText().toString());
       if(isInserted=true)
           Toast.makeText(MainActivity.this,"Data inserted ",Toast.LENGTH_SHORT).show();
       else
           Toast.makeText(MainActivity.this,"Data not inserted ",Toast.LENGTH_SHORT).show();
    }
});
    }
}