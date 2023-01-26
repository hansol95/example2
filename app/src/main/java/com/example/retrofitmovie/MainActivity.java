package com.example.retrofitmovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MovieAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    Button btn;

    private MovieInterface apiInterface;
    List<Movie> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        recyclerView = findViewById(R.id.reyclerView);
        list = new ArrayList<>(); //빈껍데기
        adapter = new MovieAdapter(list); //여기서 데이터를 차곡 차곡 쌓아줌

        //linearLayout에 뿌림
        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        //리사이클러뷰에 어뎁터에 붙이기 위해서 setLayoutManger() 사용
        recyclerView.setLayoutManager(linearLayoutManager);
        //어뎁터에 연결하기 위해 setAdapter() 사용
        recyclerView.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //retrofit을 사용하여 네트워크에 연결해서 데이터를 가져옴
                apiInterface = MovieClient.getClient().create(MovieInterface.class);
                Call<List<Movie>> call = apiInterface.goGetMovie();

                call.enqueue(new Callback<List<Movie>>() {
                    @Override
                    public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                        Log.d("TAG", response.code() +"");
                        Log.d("TAG", response.toString() +"");
                        String displayResponse = "";

                        //body에 데이터가 들어감(body의 사이즈 = 개수)
                        List<Movie> resource = response.body();
                        Log.d("TAG", resource.size()+"");
                        Log.d("adapter.getItemCount()", adapter.getItemCount()+"");
                        //adapter.add(resource);
                        //recyclerView.setAdapter(adapter);
                        for(Movie zip: resource){
                            list.add(zip);
                        }
                        Toast.makeText(getApplicationContext(),adapter.getItemCount()+"",Toast.LENGTH_SHORT).show();
                        //바뀐 거를 화면에 보여줄 때
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<Movie>> call, Throwable t) {

                    }
                });

            }
        });




    }
}