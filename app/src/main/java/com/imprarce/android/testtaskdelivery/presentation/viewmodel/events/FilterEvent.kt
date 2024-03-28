package com.imprarce.android.testtaskdelivery.presentation.viewmodel.events

sealed class FilterEvent {
    data class Selected(val categoryName: String) : FilterEvent()
    object Cleared : FilterEvent()
}
