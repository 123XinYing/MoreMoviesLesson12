package sg.edu.rp.c346.id22011117.mymoviesinsertmovie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Movie> movieList;
    ImageView iv;

    public CustomAdapter(Context context, int resource, ArrayList<Movie> objects){
        super(context, resource, objects);
        parent_context = context;
        layout_id = resource;
        movieList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(layout_id, parent, false);

        TextView tvTitle = rowView.findViewById(R.id.textViewTitle);
        TextView tvGenre = rowView.findViewById(R.id.textViewGenre);
        TextView tvYear = rowView.findViewById(R.id.textViewYear);
        ImageView iv = rowView.findViewById(R.id.imageViewRating);

        Movie currentItem = movieList.get(position);
        tvTitle.setText(currentItem.getTitle());
        tvGenre.setText("+" + currentItem.getGenre());
        tvYear.setText(currentItem.getYear() + "");

        String imageUrlG = "https://images.immediate.co.uk/production/volatile/sites/28/2019/02/16277-28797ce.jpg?quality=90&webp=true&fit=584,471";
        String imageUrlPG = "https://images.immediate.co.uk/production/volatile/sites/28/2019/02/16278-28797ce.jpg?quality=90&webp=true&fit=584,471";
        String imageUrlPG13 = "https://images.immediate.co.uk/production/volatile/sites/28/2019/02/16279-8d5bdb7.jpg?quality=90&webp=true&fit=490,490";
        String imageUrlNC16 = "https://images.immediate.co.uk/production/volatile/sites/28/2019/02/16281-8d5bdb7.jpg?quality=90&webp=true&fit=490,490";
        String imageUrlM18 = "https://images.immediate.co.uk/production/volatile/sites/28/2019/02/16282-05127b2.jpg?quality=90&webp=true&fit=300,300";
        String imageUrlR21 = "https://images.immediate.co.uk/production/volatile/sites/28/2019/02/16283-05127b2.jpg?quality=90&webp=true&fit=515,424";

        iv.setImageResource(R.drawable.rating_g);
        if (currentItem.getRating().equals("G")) {
            Picasso.with(this.getContext()).load(imageUrlG).into(iv);
        } else if (currentItem.getRating().equals("PG")) {
            Picasso.with(this.getContext()).load(imageUrlPG).into(iv);
        } else if (currentItem.getRating().equals("PG13")) {
            Picasso.with(this.getContext()).load(imageUrlPG13).into(iv);
        } else if (currentItem.getRating().equals("NC16")) {
            Picasso.with(this.getContext()).load(imageUrlNC16).into(iv);
        } else if (currentItem.getRating().equals("M18")) {
            Picasso.with(this.getContext()).load(imageUrlM18).into(iv);
        } else if (currentItem.getRating().equals("R21")) {
            Picasso.with(this.getContext()).load(imageUrlR21).into(iv);
        }
        return rowView;
    }
}

