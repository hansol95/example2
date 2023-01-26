package com.example.retrofitmovie;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> movieList; //빈 껍데기를 가져옴
    public MovieAdapter(List<Movie> movieList){
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //빈껍데기가 create를 통해서 데이터가 차곡차곡 쌓여짐
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent,false);

        MovieViewHolder  viewHolder = new MovieViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.txId.setText(Integer.toString(movie.getId()));
        holder.txName.setText(movie.getName());
        holder.txAge.setText(Integer.toString(movie.getAge()));
        holder.txIntro.setText(movie.getIntro());

    }

    @Override
    public int getItemCount() {
        return (null != movieList ? movieList.size():0);
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder{
        protected TextView txId, txName, txAge, txIntro;
        public MovieViewHolder(@NonNull View itemView){
            super(itemView);
            this.txId = itemView.findViewById(R.id.txId);
            this.txName = itemView.findViewById(R.id.txName);
            this.txAge = itemView.findViewById(R.id.txAge);
            this.txIntro = itemView.findViewById(R.id.txIntro);
        }
    }

}
