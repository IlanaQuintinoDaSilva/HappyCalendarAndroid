package br.com.iq.happycalendarandroid.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.iq.happycalendarandroid.R
import br.com.iq.happycalendarandroid.domain.Category
import kotlinx.android.synthetic.main.adapter_todo.view.*

class CategoryAdapter(
        val categories: List<Category>,
        val onClick: (Category) -> Unit) : RecyclerView.Adapter<CategoryAdapter.CategoriesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_category, parent, false)
        return CategoriesViewHolder(view)
    }

    class CategoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onBindViewHolder(holder: CategoryAdapter.CategoriesViewHolder, position: Int) {
        var category = categories[position]
        val itemView = holder.itemView

        with(itemView){
            tCategory.text = category.name
        }
        holder.itemView.setOnClickListener{onClick(category)}
    }

    override fun getItemCount() = this.categories.size
}