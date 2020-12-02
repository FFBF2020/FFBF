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

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.Holder> {
   ArrayList<RestAndStrFood> list;
    Holder.PlacesInterface listener;

    public ListAdapter(ArrayList<RestAndStrFood> list, Holder. PlacesInterface _listener) {
        this.list = list;
        listener = _listener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.places, parent, false);
        Holder holder = new Holder(v, listener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.tv.setText(list.get(position).getName());
        Picasso.get().load(list.get(position).getUrl()).fit().into(holder.iv);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Holder extends RecyclerView.ViewHolder implements View.OnClickListener  {

       private ImageView iv;
        private TextView tv;
        PlacesInterface listener;

     public Holder (@NonNull  View itemView, PlacesInterface _listener) {

         super(itemView);
         iv = itemView.findViewById(R.id.imageView2);
         tv = itemView.findViewById(R.id.textView);
         listener = _listener;
         itemView.setOnClickListener(this);
     }
   public void onClick(View view) {
         listener.onPlaceClick(getAdapterPosition());

   }



        public interface PlacesInterface {

            void onPlaceClick(int i) ;



        }
    }


}
