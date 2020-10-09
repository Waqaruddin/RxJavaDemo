package com.example.rxjava

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameObservable: Observable<String>? = getNameObservable()

        val nameObserver = getNameObserver()

        nameObservable
            ?.observeOn(Schedulers.io())
            ?.subscribeOn(AndroidSchedulers.mainThread())
            ?.subscribe(nameObserver)

    }


    private fun getNameObservable(): Observable<String>? {
        return Observable.just("Mark", "Stacy", "Paul", "Watson")
    }

    private fun getNameObserver(): Observer<String?>? {
        return object : Observer<String?> {
            override fun onSubscribe(d: @NonNull Disposable?) {
                Log.d("abc", "onSubscribe")
            }

            override fun onNext(s: @NonNull String?) {
                Log.d("abc", "Name: $s")
            }

            override fun onError(e: @NonNull Throwable?) {
                Log.d("abc", "Error " + e!!.message)
            }

            override fun onComplete() {}
        }
    }


}