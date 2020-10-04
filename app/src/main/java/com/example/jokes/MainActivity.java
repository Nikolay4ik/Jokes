package com.example.jokes;

import android.content.Intent;
import android.os.PersistableBundle;
import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jokes.Adapter.AdapterJokes;
import com.example.jokes.Pojo.Example;
import com.example.jokes.Pojo.Value;
import com.example.jokes.api.ApiFactory;
import com.example.jokes.api.ApiService;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements JokesListView {
    private RecyclerView recyclerView;
    private AdapterJokes adapterJokes;
    private ValueListPresenter valueListPresenter;
    private EditText editTextNuber;
    private BottomNavigationView bottomNavigationView;
    private String jokNumber;
    private String jok;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        valueListPresenter = new ValueListPresenter(this);
        recyclerView = findViewById(R.id.recyclerView_jokes);
        adapterJokes = new AdapterJokes();
        adapterJokes.setValues(new ArrayList<Value>());
        if (savedInstanceState != null) {
            jok = savedInstanceState.getString("num");

        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterJokes);
        editTextNuber = findViewById(R.id.EditText_joksNumber);

        if (editTextNuber != null) {
            Intent intent = getIntent();
            jok = intent.getStringExtra("jockNumber");
            valueListPresenter.setJok(jok);
        }
        valueListPresenter.loadData();
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottom();


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("num", jokNumber);
    }


    @Override
    protected void onDestroy() {
        valueListPresenter.disposeDisposable();
        super.onDestroy();
    }

    @Override
    public void showData(List<Value> value) {
        adapterJokes.setValues(value);
    }

    @Override
    public void showError() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }

    public void clickSave(View view) {
        jokNumber = String.valueOf(editTextNuber.getText());
        Log.i("MyRes", jokNumber);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("jockNumber", jokNumber);
        startActivity(intent);
    }

    private void bottom() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                Intent intent1 = new Intent(MainActivity.this, ApiInfo.class);
                switch (item.getItemId()) {
                    case R.id.jokes_but:
                        startActivity(intent);
                        break;
                    case R.id.web_but:
                        startActivity(intent1);
                        break;
                }

                return false;
            }
        });
    }
}
