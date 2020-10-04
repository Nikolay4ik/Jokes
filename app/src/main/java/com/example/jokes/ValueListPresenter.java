package com.example.jokes;

import android.util.Log;

import com.example.jokes.Pojo.Example;
import com.example.jokes.Pojo.Value;
import com.example.jokes.api.ApiFactory;
import com.example.jokes.api.ApiService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import io.reactivex.schedulers.Schedulers;


public class ValueListPresenter {
   private CompositeDisposable compositeDisposable;
   private JokesListView jokesListView;
   private String firstName;
   private String lastName;
   private String jok;


    public String getJok() {
        return jok;
    }

    public void setJok(String jok) {
        this.jok = jok;
    }

    public ValueListPresenter(JokesListView jokesListView) {
        this.jokesListView = jokesListView;
        firstName="Chuck";
        lastName="Norris";


    }

    public void loadData() {
        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.getApiService();
        compositeDisposable = new CompositeDisposable();
        Disposable disposable = apiService.getValue(jok,firstName,lastName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Example>() {
                               @Override
                               public void accept(Example example) throws Exception {
                                   for (Value s : example.getValue()){
                                   Log.i("MyResult1",s.getJoke());}
                                   jokesListView.showData(example.getValue());

                               }
                           },new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   Log.i("MyResult",throwable.getMessage());
                                   jokesListView.showError();
                               }
                           }

                );

        compositeDisposable.add(disposable);

    }

    public void loadWeb(){


    }

    public void disposeDisposable() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }
}
