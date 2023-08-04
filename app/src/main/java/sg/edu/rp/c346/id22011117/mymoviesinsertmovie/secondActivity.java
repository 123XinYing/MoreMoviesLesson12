package sg.edu.rp.c346.id22011117.mymoviesinsertmovie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashSet;

public class secondActivity extends AppCompatActivity {

    ListView lv;
    Button btn;
    CustomAdapter adapter;
    ArrayList<Movie> alMovie;
    ArrayList<String> arraySpinner;
    ArrayAdapter<String> spnAdapter;
    Movie movie;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        DBHelper dbh = new DBHelper(secondActivity.this);
        lv = findViewById(R.id.lv);
        btn = findViewById(R.id.button);
        spinner = findViewById(R.id.spinner);
        arraySpinner = new ArrayList<String>();
        spnAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        spnAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spnAdapter);

        alMovie = new ArrayList<Movie>();
        adapter = new CustomAdapter(this, R.layout.row,alMovie);
        lv.setAdapter(adapter);

        Intent i = getIntent();
        movie = (Movie) i.getSerializableExtra("data");
        ArrayList<Movie> data = dbh.getAllMovies();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Movie data = alMovie.get(position);
                Intent i = new Intent(secondActivity.this,
                        thirdActivity.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String spinner = spinner.getSelectedItem().toString();
                alMovie.clear();
                alMovie.addAll(dbh.getRating(spinner));
                adapter.notifyDataSetChanged();
                dbh.close();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        updateMovieListAndSpinner();
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateMovieListAndSpinner();
    }

    private void updateMovieListAndSpinner() {
        DBHelper dbh = new DBHelper(secondActivity.this);

        // Update the movie list
        alMovie.clear();
        alMovie.addAll(dbh.getAllMovies());
        adapter.notifyDataSetChanged();

        // Update the spinner data
        arraySpinner.clear();
        ArrayList<Movie> data = dbh.getAllMovies();
        HashSet<String> uniqueRatings = new HashSet<>();
        for (int i = 0; i < data.size(); i++) {
            uniqueRatings.add(data.get(i).getRating());
        }
        arraySpinner.addAll(uniqueRatings);

        spnAdapter.notifyDataSetChanged();

        dbh.close();
    }

}