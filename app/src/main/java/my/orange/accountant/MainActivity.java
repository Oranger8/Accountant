package my.orange.accountant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TableRow;

public class MainActivity extends AppCompatActivity {

    private TableRow inTable, midTable, outTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inTable = findViewById(R.id.in_table);
        outTable = findViewById(R.id.out_table);
        midTable = findViewById(R.id.mid_table);
        SQLHandler handler = new SQLHandler(this);
        handler.createDatabase();
    }
}
