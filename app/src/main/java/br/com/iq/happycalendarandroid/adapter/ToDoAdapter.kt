package br.com.iq.happycalendarandroid.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.iq.happycalendarandroid.R
import br.com.iq.happycalendarandroid.domain.ToDo
import br.com.iq.happycalendarandroid.utils.DateUtil
import kotlinx.android.synthetic.main.adapter_todo.view.*

class ToDoAdapter(
        val toDos: List<ToDo>,
        val onClick: (ToDo) -> Unit) : RecyclerView.Adapter<ToDoAdapter.ToDosViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_todo, parent, false)
        return ToDosViewHolder(view)
    }

    class ToDosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)/*{
        var tDescription: TextView
        var cardView: CardView
        init {
            tDescription = view.findViewById(R.id.tDescription)
            cardView = view.findViewById(R.id.cvToDo)
        }
    }*/

    override fun onBindViewHolder(holder: ToDosViewHolder, position: Int) {
        var toDo = toDos[position]
        val itemView = holder.itemView

        with(itemView){
            tDescription.text = toDo.description
            tCategory.text = toDo.project.category.name
            tDateTemp.text = DateUtil.formatDateToString(toDo.sprint.startDate, "dd/MM/yyyy")
            ckTodo.isChecked = toDo.done
            ckTodo.setOnClickListener {
                if(ckTodo.isChecked){
                    toDo
                }
            }
        }
        holder.itemView.setOnClickListener{onClick(toDo)}
    }

    override fun getItemCount() = this.toDos.size
}