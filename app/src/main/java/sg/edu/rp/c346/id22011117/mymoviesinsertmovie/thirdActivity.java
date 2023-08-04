package sg.edu.rp.c346.id22011117.mymoviesinsertmovie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class thirdActivity extends AppCompatActivity {

    TextView tvMovieID, tvMovieTitle, tvGenre, tvYear, tvRating;
    EditText etMovieID, etMovieTitle, etGenre, etYear;
    Button btnUpdate, btnDelete, btnCancel;
    Spinner spinnerRating;

    Movie data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        etMovieID = findViewById(R.id.etMovieID);
        etMovieTitle = findViewById(R.id.etMovieTitle);
        etGenre = findViewById(R.id.etGenre);
        etYear = findViewById(R.id.etYear);
        tvGenre = findViewById(R.id.tvGenre);
        tvMovieID = findViewById(R.id.tvMovieID);
        tvMovieTitle = findViewById(R.id.tvMovieTitle);
        tvYear = findViewById(R.id.tvYear);
        tvRating = findViewById(R.id.tvRating);
        btnCancel = findViewById(R.id.btnCancel);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        spinnerRating = findViewById(R.id.spinnerRating);

        Intent i = getIntent();
        data = (Movie) i.getSerializableExtra("data");

        etMovieID.setText(String.valueOf(data.getId()));
        etMovieTitle.setText(String.valueOf(data.getTitle()));
        etGenre.setText(String.valueOf(data.getGenre()));
        etYear.setText(String.valueOf(data.getYear()));
    }
}

    btnUpdate.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DBHelper dbh = new DBHelper(thirdActivity.this);
            data.setTitle(etMovieTitle.getText().toString());
            data.setGenre(etGenre.getText().toString());
            data.setYear(Integer.valueOf(etYear.getText().toString()));
            data.setRating(spinnerRating);
            dbh.updateMovie(data);
            dbh.close();
            finish();
        }
    });

        btnDelete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DBHelper dbh = new DBHelper(ThirdActivity.this);
            dbh.deleteMovie(data.getId());
            finish();
        }
    });

        btnCancel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    });
