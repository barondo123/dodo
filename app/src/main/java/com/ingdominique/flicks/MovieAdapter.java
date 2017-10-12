package com.ingdominique.flicks;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ingdominique.flicks.models.Config;
import com.ingdominique.flicks.models.Movie;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by INGDOMINIQUE on 10/10/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    //  liste des video
    ArrayList<Movie> movies;
    Config config;
    Context context;

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public MovieAdapter(ArrayList<Movie> movies) {
        this.movies = movies;

    }
// creation de nouveau viseur
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context= parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View movieView= inflater.inflate(R.layout.item_movie,parent,false);
        return new ViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
         Movie movie=movies.get(position);
        holder.tvTitle.setText(movie.getTitle());
        holder.tvOverview.setText(movie.getOverview());
        boolean isPotrait= context.getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT;
        String imageUrl=null;
        if (isPotrait){
            imageUrl=config.getImageUrl(config.getPosterSize(), movie.getPosterPath());
        }else{
            imageUrl=config.getImageUrl(config.getPosterSize(),movie.getPosterPath());
        }
        int placeholderId=isPotrait? R.drawable.flicks_movie_placeholder : R.drawable.flicks_backdrop_placeholder;
        ImageView imageview = isPotrait ? holder.ivPosterImage: holder.ivBackdropimage;


        //String imageUrl=config.getImageUrl(config.getPosterSize(), movie.getPosterPath());
        Glide.with(context)
                .load(imageUrl)
                 .bitmapTransform(new RoundedCornersTransformation(context,25,0))
                .placeholder(placeholderId)
                .error(placeholderId)
                .into(imageview);
    }
// retourner la liste total
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        //viseur d'image
        ImageView ivPosterImage;
        ImageView ivBackdropimage;
        TextView tvTitle;
        TextView tvOverview;


        public ViewHolder(View itemView) {
            super(itemView);
            ivPosterImage=(ImageView) itemView.findViewById(R.id.ivPosterImage);
            ivBackdropimage=(ImageView)itemView.findViewById(R.id.ivBackdropimage);
            tvTitle=(TextView) itemView.findViewById(R.id.tvTitle);
            tvOverview= (TextView) itemView.findViewById(R.id.tvOverview);
        }
    }
}
