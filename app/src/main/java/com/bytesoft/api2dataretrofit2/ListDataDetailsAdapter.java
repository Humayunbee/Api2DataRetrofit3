package com.bytesoft.api2dataretrofit2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.bytesoft.api2dataretrofit2.Network.Methods;
import com.bytesoft.api2dataretrofit2.Network.Retrofitclient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListDataDetailsAdapter extends AppCompatActivity {
    ProgressDialog progressDialog;
    TextView userId, Id,title,body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data_details);

        userId = findViewById(R.id.userId);
        Id =  findViewById(R.id.tvid);
        title= findViewById(R.id.title);
        body = findViewById(R.id.body);
        progressDialog = new ProgressDialog(ListDataDetailsAdapter.this);


        progressDialog.setMessage("Please wait.....");


        int id=getIntent().getIntExtra("id", 0);

        Methods methods = Retrofitclient.getRetrofitInstance().create(Methods.class);
        progressDialog.show();
        Call<List<Model>> call = methods.getDetailsData(id);
        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                progressDialog.dismiss();
                if(response.isSuccessful()){

                    Log.d("tesst", response.body().get(0).getTitle());

                    Id.setText(String.valueOf(response.body().get(0).getId()));
                    userId.setText(String.valueOf(response.body().get(0).getUserId()));
                    title.setText(String.valueOf(response.body().get(0).getTitle()));
                    body.setText(String.valueOf(response.body().get(0).getBody()));


                }else{
                    Toast.makeText(ListDataDetailsAdapter.this, "Data not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {

            }


        });
    }
}