package com.bytesoft.api2dataretrofit2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.bytesoft.api2dataretrofit2.Network.Methods;
import com.bytesoft.api2dataretrofit2.Network.Retrofitclient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button getData;
    RecyclerView rclist;
    ProgressDialog progressDialog;
    Adapter adaptar;
    private ArrayList<Model> courseModalArrayList;
    // calling on create option menu
    // layout to inflate our menu file.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Please wait.....");
        setContentView(R.layout.activity_main);
        // getData=findViewById(R.id.getData);
        rclist = findViewById(R.id.rclist);
        Methods methods = Retrofitclient.getRetrofitInstance().create(Methods.class);


        progressDialog.show();
        Call<List<Model>> call = methods.getAllData();
        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {


                    rclist.setHasFixedSize(true);
                    rclist.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                            LinearLayoutManager.VERTICAL, false));
                    adaptar = new Adapter(response.body(), getApplicationContext());
                    rclist.setAdapter(adaptar);
                    Log.e(TAG, "onResponse: Code: " + response.code());
                    List<Model> data = response.body();

                    for (Model data1 : data) {
                        // Toast.makeText(MainActivity.this, "emails: "+data1.getEmail(), Toast.LENGTH_SHORT).show();

                        //  Log.e(TAG,"onResponse: emails: "+data1.getEmail() );


                    }
                } else {
                    Toast.makeText(MainActivity.this, "Data not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {

            }




        });
    }


  
    
}