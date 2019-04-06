package br.com.iq.happycalendarandroid.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import br.com.iq.happycalendarandroid.HappyCalendarApplication
import br.com.iq.happycalendarandroid.R
import br.com.iq.happycalendarandroid.R.id.*
import br.com.iq.happycalendarandroid.domain.ToDo
import br.com.iq.happycalendarandroid.domain.api.CategoryService
import br.com.iq.happycalendarandroid.domain.api.ToDoService
import br.com.iq.happycalendarandroid.utils.DateUtil
import kotlinx.android.synthetic.main.adapter_todo.view.*


class ToDoAdapter(
        val toDos: List<ToDo>,
        val onClick: (ToDo) -> Unit) : RecyclerView.Adapter<ToDoAdapter.ToDosViewHolder>(){
    private var service = ToDoService()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_todo, parent, false)
        return ToDosViewHolder(view)
    }

    class ToDosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onBindViewHolder(holder: ToDosViewHolder, position: Int) {
        var toDo = toDos[position]
        val itemView = holder.itemView

        with(itemView){
            tTodoId.text = toDo.id.toString()
            tBacklog.text = toDo.backlog.toString()
            tDescription.text = toDo.description
            tCategory.text = toDo.category
            //tDateTemp.text = DateUtil.formatDateToString(toDo.sprint.startDate, "dd/MM/yyyy")


            if(HappyCalendarApplication.toDosScreen){
                ckTodo.visibility = View.VISIBLE
                ckTodo.isChecked = false
                ckTodo.setOnClickListener {
                    if(ckTodo.isChecked){
                        //toDo.done = 1
                        service.updateDone(toDo.id)
                    }
                }
            }else{
                ckBacklog.visibility = View.VISIBLE
                ckBacklog.isChecked = false
                ckBacklog.setOnClickListener {
                    if(ckBacklog.isChecked){
                        //toDo.backlog = 0
                        service.updateBacklog(toDo.id)
                    }
                }
            }

        }
        holder.itemView.setOnClickListener{onClick(toDo)}
    }

    override fun getItemCount() = this.toDos.size
}