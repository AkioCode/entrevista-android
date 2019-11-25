package com.example.starwarswiki.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import com.example.starwarswiki.R
import com.example.starwarswiki.database.DatabasePerson
import com.example.starwarswiki.database.PersonRoomDatabase
import com.example.starwarswiki.databinding.PersonListFragmentBinding
import com.example.starwarswiki.viewmodel.PersonListAdapter
import com.example.starwarswiki.viewmodel.PersonListViewModel
import com.example.starwarswiki.viewmodel.PersonListViewModelFactory

class PersonListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = PersonListFragmentBinding.inflate(inflater)
        val application = requireNotNull(this.activity).application
        val dataSource =  PersonRoomDatabase.getDatabase(application).personDao
        val viewModelFragment = PersonListViewModelFactory(dataSource, application)
        val viewModel = ViewModelProviders.of(this, viewModelFragment)
            .get(PersonListViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        val adapter = PersonListAdapter()
        binding.personList.adapter = adapter
        viewModel.personList.observe(this, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
        viewModel.eventNetworkError.observe(this, Observer {
            if(it==true){
                Toast.makeText(activity, "Network Error", Toast.LENGTH_SHORT)
                    .show()
                viewModel.onNetworkErrorShown()
            }
        })
        viewModel.showSnackbarEvent.observe(this, Observer {
            if(it==true){
                Toast.makeText(
                    context,
                    "Database cleared !",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        return binding.root
    }
}
