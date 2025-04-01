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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

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
        btn = findViewById(R.id.button);
        rc = findViewById(R.id.rv);

        rc.setLayoutManager(new LinearLayoutManager(this));
        List<Notes> noteList = noteDao.getAllNotes();
        ContactAdapter adapter = new ContactAdapter(this);
        rc.setAdapter(adapter);

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
                    adapter.updateData(noteDao.getAllNotes());et1.setText("");
                    et2.setText("");
                    et3.setText("");
                    et4.setText("");

                    Toast.makeText(MainActivity.this, "Contact added!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}