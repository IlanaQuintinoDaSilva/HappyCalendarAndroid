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
        toDos.add(feedToDoList(
                "Bordado",
                "Needle Painting",
                "Ana",
                "Wasen"))
        toDos.add(feedToDoList(
                "Modelagem",
                "Tricotin",
                "Isis",
                "Kranz"))
        return toDos
    }

    private fun feedToDoList(projectName: String,
                             category: String,
                             firstName: String,
                             lastName: String): ToDo{
        var toDo = ToDo()
        toDo.setProject(projectName, category)
        toDo.setAssignee(firstName, lastName)
        return toDo
    }
}
