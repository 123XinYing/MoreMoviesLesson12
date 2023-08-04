package sg.edu.rp.c346.id22011117.mymoviesinsertmovie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etMovieTitle, etGenre, etYear;
    TextView tvMovieTitle, tvGenre, tvYear, tvRating;
    Spinner spinMovies;
    Button btnInsert, btnShowList;
    ArrayList<Movie> alMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.buttonInsert);
        btnShowList = findViewById(R.id.buttonShowList);
        etGenre = findViewById(R.id.editTextGenre);
        etYear = findViewById(R.id.editTextYear);
        etMovieTitle = findViewById(R.id.editTextMovieTitle);
        tvGenre = findViewById(R.id.textViewGenre);
        tvRating = findViewById(R.id.textViewRating);
        tvYear = findViewById(R.id.textViewYear);
        tvMovieTitle = findViewById(R.id.textViewMovieTitle);
        spinMovies = findViewById(R.id.spinnerMovies);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                db.insertMovie(etMovieTitle.getText().toString(), etGenre.getText().toString(), Integer.parseInt(etYear.getText().toString());
                Toast.makeText(MainActivity.this, "Successfully added",
                        Toast.LENGTH_SHORT).show();
                etMovieTitle.setText("");
                etGenre.setText("");
                etYear.setText("");
                db.close();
            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);

                ArrayList<Movie> movieList = db.getAllMovies();

                Intent intent = new Intent(MainActivity.this, secondActivity.class);
                intent.putExtra("movieList", movieList);
                startActivity(intent);

                db.close();
            }});
    }
}