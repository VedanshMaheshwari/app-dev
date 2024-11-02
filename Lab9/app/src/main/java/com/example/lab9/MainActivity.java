package com.example.lab9;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lab9.DBHelper;

public class MainActivity extends AppCompatActivity {
    EditText name, id1;
    Button insert, update, delete, view;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        id1= findViewById(R.id.id);
        insert = findViewById(R.id.button1);
        update = findViewById(R.id.button4);
        delete = findViewById(R.id.button2);
        view = findViewById(R.id.button3);
        DB = new DBHelper(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = name.getText().toString();
                Integer idInteger= Integer.valueOf(id1.getText().toString());

                Boolean checkinsertdata = DB.insertuserdata(idInteger,nameTXT);
                if(checkinsertdata==true) Toast.makeText(MainActivity.this, "New Entry Inserted",
                        Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry Not Inserted",

                            Toast.LENGTH_SHORT).show();
            } });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = DB.viewAllData() ;
                if (cursor.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "No Records Found",

                            Toast.LENGTH_SHORT).show();

                    return;
                }
                StringBuilder builder = new StringBuilder();
                while (cursor.moveToNext()) {
                    builder.append("ID: ").append(cursor.getInt(0)).append(", ");
                    builder.append("Name: ").append(cursor.getString(1)).append("\n");
                }
                Toast.makeText(MainActivity.this, builder.toString(), Toast.LENGTH_LONG).show();
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                builder1.setCancelable(true); builder1.setTitle("User Entries");
                builder1.setMessage(builder.toString()); builder1.show();
                cursor.close();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = name.getText().toString();
                Boolean checkudeletedata = DB.deleteData(nameTXT);
                if(checkudeletedata==true)

                    Toast.makeText(MainActivity.this, "Entry Deleted",

                            Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Entry Not Deleted",
                            Toast.LENGTH_SHORT).show(); } });
        update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String nameTXT = name.getText().toString();
                Integer idInteger = Integer.valueOf(id1.getText().toString());
                Boolean checkupdatedata = DB.updateuserdata(nameTXT,

                        idInteger);

                if (checkupdatedata == true)
                    Toast.makeText(MainActivity.this, "Entry Updated",
                            Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry Not Updated",

                            Toast.LENGTH_SHORT).show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}