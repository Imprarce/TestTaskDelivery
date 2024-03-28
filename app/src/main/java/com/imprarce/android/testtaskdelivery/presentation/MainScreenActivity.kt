package com.imprarce.android.testtaskdelivery.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.imprarce.android.testtaskdelivery.R
import com.imprarce.android.testtaskdelivery.presentation.view.fragment.MainFragment

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