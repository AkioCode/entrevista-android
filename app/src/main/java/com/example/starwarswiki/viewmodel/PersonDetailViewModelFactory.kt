package com.example.starwarswiki.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.starwarswiki.database.PersonDao
import timber.log.Timber
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class PersonDetailViewModelFactory(
    private val id: Int,
    private val dataSource: PersonDao
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PersonDetailViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            //Timber.d("Instance of PersonDetailViewModel created !")
            return PersonDetailViewModel(dataSource, id) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}