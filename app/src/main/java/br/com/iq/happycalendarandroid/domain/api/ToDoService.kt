package br.com.iq.happycalendarandroid.domain.api

import android.content.ClipDescription
import android.content.Context
import android.text.format.DateUtils
import br.com.iq.happycalendarandroid.R
import br.com.iq.happycalendarandroid.domain.Category
import br.com.iq.happycalendarandroid.domain.ToDo
import br.com.iq.happycalendarandroid.utils.DateUtil
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class ToDoService{
    //private var toDos: List<ToDo> = ArrayList()

    fun getToDosSampleData(): List<ToDo>{
        val toDos = mutableListOf<ToDo>()
        toDos.add(feedToDoListHC(
                "Happy Calendar",
                "Finanças",
                "Pagar conta de luz",
                DateUtil.StringToDate("07/01/2018", "dd/MM/yyyy")
                ))
        toDos.add(feedToDoListHC(
                "Happy Calendar",
                "Carreira",
                "Desenvolvimento app TCC",
                DateUtil.StringToDate("07/01/2018", "dd/MM/yyyy")
        ))
        toDos.add(feedToDoListHC(
                "Happy Calendar",
                "Família",
                "Acompanhamento tarefas da escola filhos",
                DateUtil.StringToDate("07/01/2018", "dd/MM/yyyy")
        ))
        toDos.add(feedToDoListHC(
                "Happy Calendar",
                "Finanças",
                "Pagar conta celular",
                DateUtil.StringToDate("07/01/2018", "dd/MM/yyyy")
        ))
        toDos.add(feedToDoListHC(
                "Happy Calendar",
                "Finanças",
                "Monitoramento investimentos",
                DateUtil.StringToDate("07/01/2018", "dd/MM/yyyy")
        ))
        toDos.add(feedToDoListHC(
                "Happy Calendar",
                "Amigos",
                "Festa de aniversário Fulano",
                DateUtil.StringToDate("07/01/2018", "dd/MM/yyyy")
        ))
        toDos.add(feedToDoListHC(
                "Happy Calendar",
                "Saúde",
                "Exercícios academia",
                DateUtil.StringToDate("07/01/2018", "dd/MM/yyyy")
        ))
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
            description: String,
            startDate: Date): ToDo{
        var toDo = ToDo()
        toDo.setDescription(projectName, category, description, startDate)
        return toDo
    }

}
