package com.example.apiretrofit;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ItemClick {

    private EditText editTextName, editTextEmail;
    private RecyclerView recylerView;
    List<User> usersList;
    AdapterRecylerView adapterRecylerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        Button buttonCreateUser = findViewById(R.id.buttonCreateUser);
        recylerView = findViewById(R.id.textViewResult);
        usersList = new ArrayList<>();

        //setting adapter
         adapterRecylerView = new AdapterRecylerView(this,usersList);
        recylerView.setLayoutManager(new LinearLayoutManager(this));
        recylerView.setAdapter(adapterRecylerView);

      // create or post user
        buttonCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String email = editTextEmail.getText().toString();

                // Create a new user object
                User newUser = new User(name, email);

                // API call to create a new user
                Call<User> call = RetrofitInstance.getApi().createUser(newUser);


                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "send data sucessfully", Toast.LENGTH_SHORT).show();
                            getUser();
                        } else {
                            Toast.makeText(MainActivity.this, "Data not send", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "data falied", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

      // get All users
        getUser();

    }

    private void getUser() {
        // Make the API call for get
        Call<List<User>> call = RetrofitInstance.getApi().getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "data is not comming", Toast.LENGTH_SHORT).show();
                    return;
                }
                usersList.clear();
                usersList.addAll(response.body());
                adapterRecylerView.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
                Log.e("MainActivity", "API call failed", t);
            }
        });
    }


    @Override
    public void updateItemClick(User user) {
        editTextName.setText(user.getName());
        editTextEmail.setText(user.getEmail());

        Button buttonCreateUser = findViewById(R.id.buttonCreateUser);

        buttonCreateUser.setOnClickListener(v -> {
            String name = editTextName.getText().toString().trim();
            String email = editTextEmail.getText().toString().trim();

            User users = new User(name,email);

            Call<User> call = RetrofitInstance.getApi().updateUser(user.getId(),users);

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    Toast.makeText(MainActivity.this, "User Update"+user.getId(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "User Not Update", Toast.LENGTH_SHORT).show();
                }
            });
        });

       
    }
}
