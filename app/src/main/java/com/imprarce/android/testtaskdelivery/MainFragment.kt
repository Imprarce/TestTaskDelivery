package com.imprarce.android.testtaskdelivery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.imprarce.android.testtaskdelivery.model.MealItem

class MainFragment : Fragment() {
    private lateinit var citySpinner: Spinner
    private lateinit var productRecyclerView: RecyclerView
    private lateinit var qrCodeImage: ImageView
    private lateinit var mainFragmentViewModel: MainFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainFragmentViewModel = ViewModelProviders.of(this).get(MainFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        citySpinner = view.findViewById(R.id.city_spinner) as Spinner
        productRecyclerView = view.findViewById(R.id.product_recycler_view)
        productRecyclerView.layoutManager = LinearLayoutManager(context)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainFragmentViewModel.mealItemLiveData.observe(
            viewLifecycleOwner,
            Observer { productItems -> productRecyclerView.adapter = PhotoAdapter(productItems) }
        )

        if(citySpinner != null){
            val adapter = ArrayAdapter(view.context, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.Cities))
            citySpinner.adapter = adapter
        }


    }

    private class ProductHolder(itemTextView: TextView) : RecyclerView.ViewHolder(itemTextView){
        val bindTitle: (CharSequence) -> Unit = itemTextView::setText
    }

    private class PhotoAdapter(private val mealItem: List<MealItem>) : RecyclerView.Adapter<ProductHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
            val textView = TextView(parent.context)
            return ProductHolder(textView)
        }

        override fun getItemCount(): Int = mealItem.size

        override fun onBindViewHolder(holder: ProductHolder, position: Int) {
            val productItem = mealItem[position]
            holder.bindTitle(productItem.title)
        }

    }

    companion object{
        fun newInstance() = MainFragment()
    }
}