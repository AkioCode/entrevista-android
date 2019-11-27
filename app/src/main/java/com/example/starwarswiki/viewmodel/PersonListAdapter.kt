package com.example.starwarswiki.viewmodel

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.starwarswiki.domain.PersonModel
import com.example.starwarswiki.util.RecyclerViewHolder
import com.example.starwarswiki.util.getObjectId

class PersonListAdapter(val clickListener: PersonClickListener, val favoriteClickListener: FavoriteClickListener): ListAdapter<PersonModel, RecyclerViewHolder> (PersonListDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item!!, position, clickListener, favoriteClickListener)
    }
}

class PersonListDiffCallback: DiffUtil.ItemCallback<PersonModel>(){
    override fun areItemsTheSame(oldItem: PersonModel, newItem: PersonModel): Boolean {
        return (oldItem.url == newItem.url)
    }

    override fun areContentsTheSame(oldItem: PersonModel, newItem: PersonModel): Boolean {
        return (oldItem == newItem)
    }
}

class PersonClickListener(val clickListener: (url: String)->Unit){
    fun onClick(person: PersonModel) = clickListener(person.url)
}

class FavoriteClickListener(val clickListener: (id: Int) -> Unit){
    fun onClick(person: PersonModel){
        val id = getObjectId(person.url,"https://swapi.co/api/people/")
        return clickListener(id)
    }
}