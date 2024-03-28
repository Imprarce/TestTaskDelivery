package com.imprarce.android.testtaskdelivery.presentation.view.fragment

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.imprarce.android.testtaskdelivery.MyApplication
import com.imprarce.android.testtaskdelivery.R
import com.imprarce.android.testtaskdelivery.data.model.BannerItem
import com.imprarce.android.testtaskdelivery.data.model.CategoryItem
import com.imprarce.android.testtaskdelivery.data.model.MealItem
import com.imprarce.android.testtaskdelivery.presentation.view.MainFragmentContract
import com.imprarce.android.testtaskdelivery.presentation.view.adapters.BannerAdapter
import com.imprarce.android.testtaskdelivery.presentation.view.adapters.MealAdapter
import com.imprarce.android.testtaskdelivery.presentation.viewmodel.MainViewModel
import com.imprarce.android.testtaskdelivery.presentation.viewmodel.MainViewModelFactory
import com.imprarce.android.testtaskdelivery.presentation.viewmodel.events.FilterEvent
import com.imprarce.android.testtaskdelivery.dependency.DependencyProvider

private const val TAG = "MainFragment"
private const val SAVED_INSTANCE_CHIP = ""


class MainFragment : Fragment(), MainFragmentContract {
    private lateinit var viewModel: MainViewModel
    private lateinit var citySpinner: Spinner
    private lateinit var mealRecyclerView: RecyclerView
    private lateinit var bannerRecyclerView: RecyclerView
    private lateinit var chipGroup: ChipGroup
    private lateinit var dependencyProvider: DependencyProvider

    private var nameChip : String = ""

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(SAVED_INSTANCE_CHIP, nameChip)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dependencyProvider = (requireActivity().applicationContext as MyApplication).dependencyProvider

        val mainRepository = dependencyProvider.mainRepository
        val db = dependencyProvider.databaseManager

        viewModel = ViewModelProvider(this, MainViewModelFactory(mainRepository, db)).get(MainViewModel::class.java)

        if(savedInstanceState != null){
            nameChip = savedInstanceState.getString(SAVED_INSTANCE_CHIP, "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        citySpinner = view.findViewById(R.id.city_spinner) as Spinner

        mealRecyclerView = view.findViewById(R.id.product_recycler_view) as RecyclerView
        mealRecyclerView.layoutManager = LinearLayoutManager(context)

        bannerRecyclerView = view.findViewById(R.id.recycler_view_banner) as RecyclerView
        bannerRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        chipGroup = view.findViewById(R.id.chip_group_categories)

        val adapter = ArrayAdapter(
            view.context, android.R.layout.simple_spinner_item, resources.getStringArray(
                R.array.Cities
            )
        )
        citySpinner.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.bannerItemsLiveData.observe(viewLifecycleOwner) { bannerItems ->
            showBannerItems(bannerItems)
        }

        viewModel.mealItemsLiveData.observe(viewLifecycleOwner) { mealItems ->
            showMealItems(mealItems)
        }

        viewModel.categoryItemsLiveData.observe(viewLifecycleOwner) { categoryItems ->
            showCategoryChips(categoryItems)
        }

        viewModel.filterEvent.observe(viewLifecycleOwner) { event ->
            when (event) {
                is FilterEvent.Selected -> {
                    viewModel.filterItemsByCategory(event.categoryName)
                    Log.d(TAG, "Selected: ${event.categoryName}")
                }
                is FilterEvent.Cleared -> {
                    viewModel.filterItemsByCategory(nameChip)
                    Log.d(TAG, "Cleared")
                }
            }
        }

    }

    override fun showBannerItems(bannerItems: List<BannerItem>) {
        bannerRecyclerView.adapter = BannerAdapter(bannerItems)
    }

    override fun showMealItems(mealItems: List<MealItem>) {
        mealRecyclerView.adapter = MealAdapter(mealItems)
    }

    override fun showCategoryChips(categories: List<CategoryItem>) {
        for (category in categories) {
            val chip = Chip(chipGroup.context)
            chip.text = category.title
            chip.isClickable = true
            chip.isCheckable = true
            chip.checkedIcon = null
            chip.setChipBackgroundColorResource(R.color.white)
            chip.setTextColor(ContextCompat.getColor(chipGroup.context, R.color.black))

            chip.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    nameChip = chip.text as String
                    chip.chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(chipGroup.context,
                        R.color.selected_category_background
                    ))
                    chip.setTextColor(ContextCompat.getColor(chipGroup.context,
                        R.color.selected_icon
                    ))
                } else {
                    nameChip = ""
                    chip.chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(chipGroup.context,
                        R.color.white
                    ))
                    chip.setTextColor(ContextCompat.getColor(chipGroup.context, R.color.black))
                }

                val event = if (isChecked) FilterEvent.Selected(chip.text as String) else FilterEvent.Cleared
                viewModel.setSelectedCategory(event)
            }

            if(chip.text == nameChip) chip.isChecked = true

            chipGroup.addView(chip)
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
