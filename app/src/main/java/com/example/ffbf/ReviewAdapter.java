package com.example.ffbf;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ReviewAdapter  extends  RecyclerView.Adapter<ReviewAdapter.Holder> {

    ArrayList<Review> list;
    ReviewAdapter.Holder.ReviewInterface listener;

    public ReviewAdapter(ArrayList<Review> list, ReviewAdapter.Holder. ReviewInterface  _listener) {
        this.list = list;
        listener = _listener;
    }

    @NonNull
    @Override
    public ReviewAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.userlist, parent, false);
        ReviewAdapter.Holder holder = new ReviewAdapter.Holder(v, listener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.Holder holder, int position) {
        holder.userMail.setText(list.get(position).getUserMail());
        holder.review.setText(list.get(position).getReview());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Holder extends RecyclerView.ViewHolder implements View.OnClickListener  {


        TextView userMail, review;
        ReviewAdapter.Holder.ReviewInterface listener;

        public Holder (@NonNull  View itemView, ReviewAdapter.Holder.ReviewInterface _listener) {

            super(itemView);

            userMail = itemView.findViewById(R.id.tv_userEmail);
            review = itemView.findViewById(R.id.etml_userFullName);
            listener = _listener;
            itemView.setOnClickListener(this);
        }
        public void onClick(View view) {
            listener.onPlaceClick(getAdapterPosition());

        }



        public interface ReviewInterface {

            void onPlaceClick(int i) ;



        }
    }
}
