package com.imprarce.android.testtaskdelivery.dependency

import com.imprarce.android.testtaskdelivery.data.local.db.DatabaseManager
import com.imprarce.android.testtaskdelivery.data.repository.MainRepositoryImpl

interface DependencyProvider {
    val mainRepository: MainRepositoryImpl
    val databaseManager: DatabaseManager
}