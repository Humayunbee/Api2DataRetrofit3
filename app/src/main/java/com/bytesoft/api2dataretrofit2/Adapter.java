package com.bytesoft.api2dataretrofit2;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

class Adapter extends RecyclerView.Adapter<Adapter.ViewHolders> {

        List<Model> data = new ArrayList<>();
         Context context;
        public Adapter(List<Model> data, Context context) {
            this.data = data;
            this.context=context;
            Log.e("coverragearea", "adaptar");
        }
    public void filterList(ArrayList<Model> filterllist) {
        // below line is to add our filtered
        // list in our course array list.
        data = filterllist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

        @Override
        public Adapter.ViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_sample_design, parent, false);

            return new ViewHolders(view);
        }

        @Override
        public void onBindViewHolder(Adapter.ViewHolders holder, int position) {
         //   holder.userId.setText(String.valueOf(data.get(i).getUserId()));
            holder.id.setText(String.valueOf(data.get(position).getId()));
            holder.title.setText(String.valueOf(data.get(position).getTitle()));
          //  holder.body.setText(String.valueOf(data.get(i).getBody()));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, ListDataDetailsAdapter.class);
                    intent.putExtra("id", data.get(position).getId());
                    holder.itemView.getContext().startActivity(intent);
                }
            });

            //Log.e("datata", String.valueOf(coverarealist.get(i).getCoverageArea()));

        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class ViewHolders extends RecyclerView.ViewHolder {

            TextView userId,id,title,body;

            public ViewHolders( View itemView) {
                super(itemView);

              //  userId = itemView.findViewById(R.id.userId);
                id = itemView.findViewById(R.id.id);
                title = itemView.findViewById(R.id.title);
              //  body = itemView.findViewById(R.id.body);



            }
        }
    }


