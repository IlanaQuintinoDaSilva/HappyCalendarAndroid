package br.com.iq.happycalendarandroid.adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.iq.happycalendarandroid.R
import br.com.iq.happycalendarandroid.domain.ToDo
import kotlinx.android.synthetic.main.adapter_todo.view.*

class ToDoAdapter(
        val toDos: List<ToDo>,
        val onClick: (ToDo) -> Unit) : RecyclerView.Adapter<ToDoAdapter.ToDosViewHolder>(){
    class ToDosViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var tDescription: TextView
        var cardView: CardView
        init {
            tDescription = view.findViewById<TextView>(R.id.tDescription)
            cardView = view.findViewById<CardView>(R.id.cvToDo)
        }
    }

    override fun getItemCount() = this.toDos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_todo, parent, false)
        val holder = ToDosViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: ToDosViewHolder, position: Int) {
        /*//context constant will be used only when a picture is downloaded, using Picasso lib.
        val context = holder.itemView.context*/
        val toDo = toDos[position]
        holder.tDescription.text = toDo.description
        holder.itemView.setOnClickListener{onClick(toDo)}
    }
}
