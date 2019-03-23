package br.com.iq.happycalendarandroid.domain.api

import android.content.ContentValues
import android.util.Log
import br.com.iq.happycalendarandroid.R.string.category
import br.com.iq.happycalendarandroid.data.DatabaseHelper
import br.com.iq.happycalendarandroid.data.TodosContract
import br.com.iq.happycalendarandroid.domain.Category
import br.com.iq.happycalendarandroid.domain.ToDo
import br.com.iq.happycalendarandroid.utils.DateUtil
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

    fun getBacklogSampleData(): List<ToDo>{
        val toDos = mutableListOf<ToDo>()
        var dtToday: Date = Date()
        toDos.add(feedToDoListHC(
                "Happy Calendar",
                "Finanças",
                "Backlog 1",
                DateUtil.StringToDate("07/01/2018", "dd/MM/yyyy")
        ))
        toDos.add(feedToDoListHC(
                "Happy Calendar",
                "Carreira",
                "Backlog 2",
                DateUtil.StringToDate("07/01/2018", "dd/MM/yyyy")
        ))
        toDos.add(feedToDoListHC(
                "Happy Calendar",
                "Família",
                "Backlog 3",
                DateUtil.StringToDate("07/01/2018", "dd/MM/yyyy")
        ))
        toDos.add(feedToDoListHC(
                "Happy Calendar",
                "Finanças",
                "Backlog 4",
                DateUtil.StringToDate("07/01/2018", "dd/MM/yyyy")
        ))
        toDos.add(feedToDoListHC(
                "Happy Calendar",
                "Finanças",
                "Backlog 5",
                DateUtil.StringToDate("07/01/2018", "dd/MM/yyyy")
        ))
        toDos.add(feedToDoListHC(
                "Happy Calendar",
                "Amigos",
                "Backlog 6",
                DateUtil.StringToDate("07/01/2018", "dd/MM/yyyy")
        ))
        toDos.add(feedToDoListHC(
                "Happy Calendar",
                "Saúde",
                "Backlog 7",
                DateUtil.StringToDate("07/01/2018", "dd/MM/yyyy")
        ))
        toDos.add(feedToDoListHC(
                "Happy Calendar",
                "Família",
                "Backlog 8",
                DateUtil.StringToDate("23/12/2018", "dd/MM/yyyy")
        ))
        toDos.add(feedToDoListHC(
                "Happy Calendar",
                "Amigos",
                "Backlog 9",
                DateUtil.StringToDate("23/12/2018", "dd/MM/yyyy")
        ))
        toDos.add(feedToDoListHC(
                "Happy Calendar",
                "Carreira",
                "Backlog 10",
                dtToday
        ))
        toDos.add(feedToDoListHC(
                "Happy Calendar",
                "Casa",
                "Backlog 11",
                dtToday
        ))
        toDos.add(feedToDoListHC(
                "Happy Calendar",
                "Casa",
                "Backlog '12",
                dtToday
        ))
        return toDos
    }

    fun getToDos(helper: DatabaseHelper, isBacklog: String):List<ToDo> {
        val todos = mutableListOf<ToDo>()
        val db = helper.readableDatabase
        val projection = arrayOf(TodosContract.TodosEntry.COLUMN_TEXT, TodosContract.TodosEntry.COLUMN_CATEGORY,
                TodosContract.TodosEntry._ID, TodosContract.TodosEntry.COLUMN_BACKLOG)
        val selection = TodosContract.TodosEntry.COLUMN_BACKLOG + " = ?"
        val selectionArgs = arrayOf(isBacklog)
        val c = db.query(TodosContract.TodosEntry.TABLE_NAME,
                projection, selection, selectionArgs, null, null, null)
        val i = c.count
        while (c.moveToNext()) {
            var td = ToDo()
            td.description = c.getString(0)
            td.category = c.getString(1)
            td.id = c.getLong(2)
            td.backlog = c.getLong(3)
            todos.add(td)
        }
        c.close()
        return todos
    }

    fun addToDo(description: String, category: String, helper: DatabaseHelper){
        var db = helper.writableDatabase
        var values = ContentValues().apply {
            put(TodosContract.TodosEntry.COLUMN_TEXT, description)
            put(TodosContract.TodosEntry.COLUMN_CATEGORY, category)
            put(TodosContract.TodosEntry.COLUMN_DONE, 0)
            put(TodosContract.TodosEntry.COLUMN_BACKLOG, 1)
        }

        val newRowId = db?.insert(TodosContract.TodosEntry.TABLE_NAME, null, values)

        db?.close()

    }

    private fun feedToDoListHC(projectName: String, category: String, description: String, startDate: Date): ToDo{
        var toDo = ToDo()
        toDo.setDescription(projectName, category, description)
        toDo.setSprint(startDate)
        return toDo
    }
}
