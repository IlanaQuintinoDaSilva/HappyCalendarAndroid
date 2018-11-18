package br.com.iq.happycalendarandroid.domain.api

import android.content.ClipDescription
import android.content.Context
import br.com.iq.happycalendarandroid.R
import br.com.iq.happycalendarandroid.domain.Category
import br.com.iq.happycalendarandroid.domain.ToDo
import java.util.ArrayList


class ToDoService{
    private var toDos: List<ToDo> = ArrayList()

    fun getToDosSampleData(): List<ToDo>{
        val toDos = mutableListOf<ToDo>()
        toDos.add(feedToDoListHC(
                "Happy Calendar",
                "Finanças",
                "Pagar conta de luz"))
        toDos.add(feedToDoListHC(
                "Happy Calendar",
                "Carreira",
                "Desenvolvimento app TCC"))
        toDos.add(feedToDoListHC(
                "Happy Calendar",
                "Família",
                "Acompanhamento tarefas da escola filhos"))
        toDos.add(feedToDoListHC(
                "Happy Calendar",
                "Finanças",
                "Pagar conta celular"))
        toDos.add(feedToDoListHC(
                "Happy Calendar",
                "Finanças",
                "Monitoramento investimentos"))
        toDos.add(feedToDoListHC(
                "Happy Calendar",
                "Amigos",
                "Festa de aniversário Fulano"))
        toDos.add(feedToDoListHC(
                "Happy Calendar",
                "Saúde",
                "Exercícios academia"))
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

    private fun feedToDoListHC(
            projectName: String,
            category: String,
            description: String): ToDo{
        var toDo = ToDo()
        toDo.setDescription(projectName, category, description)
        return toDo
    }
}
