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

    fun getToDosSampleData(): List<ToDo>{
        val toDos = mutableListOf<ToDo>()
        var dtToday: Date = Date()
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
        toDos.add(feedToDoListHC(
                "Happy Calendar",
                "Família",
                "Preparar jantar de Natal",
                DateUtil.StringToDate("23/12/2018", "dd/MM/yyyy")
        ))
        toDos.add(feedToDoListHC(
                "Happy Calendar",
                "Amigos",
                "Planejamento Férias",
                DateUtil.StringToDate("23/12/2018", "dd/MM/yyyy")
        ))
        toDos.add(feedToDoListHC(
                "Happy Calendar",
                "Carreira",
                "Treinamento RH",
                dtToday
        ))
        toDos.add(feedToDoListHC(
                "Happy Calendar",
                "Casa",
                "Comprar rack TV",
                dtToday
        ))
        toDos.add(feedToDoListHC(
                "Happy Calendar",
                "Casa",
                "Conserto lavadora",
                dtToday
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

    private fun feedToDoListHC(projectName: String, category: String, description: String, startDate: Date): ToDo{
        var toDo = ToDo()
        toDo.setDescription(projectName, category, description)
        toDo.setSprint(startDate)
        return toDo
    }
}
