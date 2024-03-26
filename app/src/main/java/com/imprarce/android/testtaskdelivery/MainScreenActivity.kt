package com.imprarce.android.testtaskdelivery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Spinner

class MainScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen_main)
        val isFragmentContainerEmpty = savedInstanceState == null
        if (isFragmentContainerEmpty){
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, MainFragment.newInstance())
                .commit()
        }
    }
}