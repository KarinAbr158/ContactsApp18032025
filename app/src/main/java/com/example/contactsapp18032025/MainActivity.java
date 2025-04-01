package com.example.contactsapp18032025;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText et1, et2, et3, et4;
    ArrayList<String> arrList;
    ArrayAdapter<String> arrAdapt;
    ListView lv;
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
        btn = findViewById(R.id.button);
        lv = findViewById(R.id.listView);
        arrList = new ArrayList<>();
        List<Notes> list;
        list = noteDao.getAllNotes();
        for(Notes n: list){
            arrList.add(String.valueOf(n.getId()));
        }
        arrAdapt = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, arrList);
        lv.setAdapter(arrAdapt);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et1.getText().toString().trim();
                String phone = et2.getText().toString().trim();
                String email = et3.getText().toString().trim();
                String address = et4.getText().toString().trim();

                if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || address.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    Notes temp = new Notes(name, phone, email, address);
                    noteDao.insert(temp);
                    et1.setText("");
                    et2.setText("");
                    et3.setText("");
                    et4.setText("");

                    Toast.makeText(MainActivity.this, "Contact added!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}