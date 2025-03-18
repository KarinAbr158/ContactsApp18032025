package com.example.contactsapp18032025;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    EditText et1, et2, et3, et4;
    RecyclerView rc;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Database database = Database.getInstance(this);
        NotesDAO noteDao = database.noteDao();
        et1 = findViewById(R.id.et);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        et4 = findViewById(R.id.et4);
        rc = findViewById(R.id.rv);
        btn = findViewById(R.id.button);
//        Notes check = new Notes("Botam", "Yerger", "Buttfucknowhere Avenue", "696969420");


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Notes temp = new Notes(et1.getText().toString(), et2.getText().toString(), et3.getText().toString(), et4.getText().toString());
                noteDao.insert(temp);
            }
        });




    }
}