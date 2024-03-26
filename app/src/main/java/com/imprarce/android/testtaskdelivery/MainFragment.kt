package com.imprarce.android.testtaskdelivery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import androidx.recyclerview.widget.RecyclerView

class MainFragment : Fragment() {
    private lateinit var citySpinner: Spinner
    private lateinit var recyclerView: RecyclerView
    private lateinit var qrCodeImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

//        citySpinner = view.findViewById(R.id.city_spinner) as Spinner

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        if(citySpinner != null){
//            val adapter = ArrayAdapter(view.context, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.Cities))
//            citySpinner.adapter = adapter
//        }


    }

    companion object{
        fun newInstance() = MainFragment()
    }
}