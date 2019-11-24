package com.example.starwarswiki.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.starwarswiki.R
import com.example.starwarswiki.databinding.PersonListFragmentBinding
import com.example.starwarswiki.viewmodel.PersonListViewModel

class PersonListFragment : Fragment() {

    private val viewModel: PersonListViewModel by lazy {
        ViewModelProviders.of(this).get(PersonListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = PersonListFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }
}
