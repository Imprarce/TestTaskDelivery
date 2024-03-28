package com.imprarce.android.testtaskdelivery.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.imprarce.android.testtaskdelivery.data.local.db.DatabaseManager
import com.imprarce.android.testtaskdelivery.data.repository.MainRepositoryImpl

class MainViewModelFactory(
    private val repository: MainRepositoryImpl,
    private val dbManager: DatabaseManager
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository, dbManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}