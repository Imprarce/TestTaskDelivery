package com.imprarce.android.testtaskdelivery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.imprarce.android.testtaskdelivery.model.BannerItem
import com.imprarce.android.testtaskdelivery.model.MealItem
import com.squareup.picasso.Picasso

class MainFragment : Fragment() {
    private lateinit var citySpinner: Spinner
    private lateinit var productRecyclerView: RecyclerView
    private lateinit var bannerRecyclerView: RecyclerView
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
        productRecyclerView = view.findViewById(R.id.product_recycler_view) as RecyclerView
        productRecyclerView.layoutManager = LinearLayoutManager(context)

        bannerRecyclerView = view.findViewById(R.id.recycler_view_banner) as RecyclerView
        bannerRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainFragmentViewModel.mealItemLiveData.observe(
            viewLifecycleOwner,
            Observer { productItems -> productRecyclerView.adapter = ProductAdapter(productItems) }
        )

        mainFragmentViewModel.bannerItemLiveData.observe(
            viewLifecycleOwner,
            Observer { bannerItems -> bannerRecyclerView.adapter = BannerAdapter(bannerItems) }
        )

        mainFragmentViewModel.categoryItemLiveData.observe(viewLifecycleOwner, Observer { categoryList ->
            val linearLayout: LinearLayout = view.findViewById(R.id.linear_layout_buttons)
            linearLayout.removeAllViews()

            for (categoryItem in categoryList) {
                val button = Button(context)
                button.text = categoryItem.title
                button.setOnClickListener {
                    Toast.makeText(context, "Выбрана категория: ${categoryItem.title}", Toast.LENGTH_SHORT).show()
                }
                linearLayout.addView(button)
            }
        })

        if(citySpinner != null){
            val adapter = ArrayAdapter(view.context, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.Cities))
            citySpinner.adapter = adapter
        }


    }

    private class ProductHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleTextView: TextView = itemView.findViewById(R.id.pizza_name)
        val descriptionTextView: TextView = itemView.findViewById(R.id.pizza_text)
        val mealImageView: ImageView = itemView.findViewById(R.id.meal_image)
    }

    private class ProductAdapter(private val mealItem: List<MealItem>) : RecyclerView.Adapter<ProductHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_meals_recycler_view, parent, false)
            return ProductHolder(itemView)
        }

        override fun getItemCount(): Int = mealItem.size

        override fun onBindViewHolder(holder: ProductHolder, position: Int) {
            val currentMeal = mealItem[position]
            holder.titleTextView.text = currentMeal.title
            holder.descriptionTextView.text = currentMeal.ingredients
            Picasso.get().load(currentMeal.url).into(holder.mealImageView)
        }

    }

    class BannerAdapter(private val bannerList: List<BannerItem>) : RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {

        inner class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageView: ImageView = itemView.findViewById(R.id.banner_image)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_banner_recycler_view, parent, false)
            return BannerViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
            val currentItem = bannerList[position]
            Picasso.get().load(currentItem.url).into(holder.imageView)
        }

        override fun getItemCount() = bannerList.size
    }

    companion object{
        fun newInstance() = MainFragment()
    }
}