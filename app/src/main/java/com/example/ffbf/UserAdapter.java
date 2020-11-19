package com.example.ffbf;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

     ArrayList<User> list;
     UserHolder.OnUserClickListener listener;

    public UserAdapter(ArrayList<User> list, UserHolder.OnUserClickListener  _listener) {
        this.list = list;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.userlist, parent, false);

        return new UserHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {

        holder.tv.setText(list.get(position).getFn() + " " + list.get(position).getSn());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    ImageView iv;
    TextView tv;
    OnUserClickListener listener;




    public UserHolder(@NonNull View itemView, OnUserClickListener _listener) {
        super(itemView);

        iv = itemView.findViewById(R.id.iv_avatar);
        tv = itemView.findViewById(R.id.tv_userName);
        listener = _listener;
        itemView.setOnClickListener(this);
    }

        @Override
        public void onClick(View v) {
        listener.onUserClicked(getAdapterPosition());

        }
        public interface OnUserClickListener{

            public void onUserClicked(int position);
        }
    }





}
