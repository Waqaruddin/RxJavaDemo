package com.example.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        Observable<String> nameObservable = getNameObservable();

        Observer<String> nameObserver = getNameObserver();

        nameObservable
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(nameObserver);

    }

    private Observable<String> getNameObservable(){
        return Observable.just("Mark", "Stacy", "Paul", "Watson");
    }

    private Observer<String> getNameObserver(){
        return new Observer<String>(){

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d("abc", "onSubscribe");
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.d("abc", "Name: " + s);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("abc", "Error " + e.getMessage());

            }

            @Override
            public void onComplete() {

            }
        };
    }
}