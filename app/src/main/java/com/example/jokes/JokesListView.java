package com.example.jokes;

import com.example.jokes.Pojo.Example;
import com.example.jokes.Pojo.Value;

import java.util.ArrayList;
import java.util.List;

public interface JokesListView {
    void showData(List<Value>value);
    void showError();
}
