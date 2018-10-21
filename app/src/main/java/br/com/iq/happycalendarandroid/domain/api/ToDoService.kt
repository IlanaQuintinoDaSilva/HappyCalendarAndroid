package br.com.iq.happycalendarandroid.domain.api

import android.content.Context
import br.com.iq.happycalendarandroid.R
import br.com.iq.happycalendarandroid.domain.Category
import br.com.iq.happycalendarandroid.domain.ToDo
import java.util.ArrayList


class ToDoService{
    private var toDos: List<ToDo> = ArrayList()

    fun getToDosSampleData(): List<ToDo>{
        val toDos = mutableListOf<ToDo>()
        toDos.add(feedToDoList("Bordado, Por Ana Wasen", "Needle Painting"))
        toDos.add(feedToDoList("Modelagem, Por Isis Kranz", "Tricotin"))
        return toDos
    }

    private fun feedToDoList(projectName: String, category: String): ToDo{
        var toDo = ToDo()
        toDo.setProject(projectName, category)
        return toDo
    }
}
