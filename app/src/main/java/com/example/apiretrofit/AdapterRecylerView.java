package com.example.apiretrofit;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterRecylerView extends RecyclerView.Adapter<AdapterRecylerView.UserViewHolder> {

  List<User> list;
  ItemClick click;

    public AdapterRecylerView(ItemClick click, List<User> list) {
        this.click = click;
        this.list = list;
    }


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_items,parent,false);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = list.get(position);
        holder.id.setText(user.getId());
        holder.name.setText(user.getName());
        holder.email.setText(user.getEmail());

        // for update item
        holder.itemView.setOnClickListener(v -> {
            click.updateItemClick(user);
        });


        //for delete item
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (user.getId()== null) {
                    Log.e("AdapterRecyclerView", "ID null hai ji: " );

                }
                RetrofitInstance.getApi().deleteUser(user.getId()).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            list.remove(position);
                            notifyItemRemoved(position);
                            Toast.makeText(v.getContext(), "User deleted", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e("AdapterRecyclerView", "Delete failed  to delete: " + response.code());
                            Toast.makeText(v.getContext(), "Failed to delete user", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.e("AdapterRecyclerView", "Delete failed with code: " + t.getMessage());
                        Toast.makeText(v.getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                return true;
            }



        });

    }
    @Override
    public int getItemCount() {
        return list.size();

    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView id,name,email;



        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.textId);
            name=itemView.findViewById(R.id.textName);
            email=itemView.findViewById(R.id.textEmail);
        }
    }
}
