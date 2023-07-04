package sg.edu.rp.c346.id22013080.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnGetTasks;
    TextView tvResults;
    ListView lv;

    EditText taskInsert, dateInsert;

    ArrayList<Task> task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        lv = findViewById(R.id.lv);
        taskInsert = findViewById(R.id.eTTask);
        dateInsert = findViewById(R.id.etDate);



        task = new ArrayList<Task>();
        ArrayAdapter item = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, task);
        lv.setAdapter(item);

        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);
                String newTask = taskInsert.getText().toString();
                String taskDate = dateInsert.getText().toString();
                // Insert a task
                db.insertTask(newTask, taskDate);

                Toast.makeText(MainActivity.this, "Task inserted", Toast.LENGTH_SHORT).show();

            }
        });
        btnGetTasks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<String> data = db.getTaskContent();
                db.close();

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i +". "+data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }
                tvResults.setText(txt);

                task.clear();
                task.addAll(db.getTasks());
                item.notifyDataSetChanged();

            }
        });

    }
}