package com.codebreakers.tictactoe;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter{

    private Context context;

    private List<CardItems> displayList;

    public ItemAdapter(Context context, List<CardItems> displayList) {

        this.context = context;
        this.displayList = displayList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_view, null);
        return new ItemViewHolder(view);
    }

   public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        onBindViewHolder1((ItemViewHolder)viewHolder,i);
   }

    public void onBindViewHolder1(@NonNull ItemViewHolder viewHolder, int i) {

        final int position = i;
        CardItems cardItem = displayList.get(i);

        viewHolder.textViewTitle.setText(cardItem.getTitle());
        viewHolder.imageView.setImageDrawable(context.getResources().getDrawable(cardItem.getImage()));

        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position==0){
                    context.startActivity(new Intent(context,single_player.class));
                }
                else if(position==1) {
                    context.startActivity(new Intent(context,dual_player.class));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return displayList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle;
        ImageView imageView;

        public ItemViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.card_title_id);
            imageView = itemView.findViewById(R.id.img_view_id);


        }
    }
}
