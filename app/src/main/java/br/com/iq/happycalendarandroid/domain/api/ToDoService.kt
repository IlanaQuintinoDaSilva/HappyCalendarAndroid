package br.com.iq.happycalendarandroid.domain.api

import android.content.Context
import br.com.iq.happycalendarandroid.R
import br.com.iq.happycalendarandroid.domain.Category
import br.com.iq.happycalendarandroid.domain.ToDo
import java.util.ArrayList


class ToDoService{
    private var toDos: List<ToDo> = ArrayList()

    fun getToDos(context: Context, category: Category): List<ToDo>{
        val categoryString = context.getString(category.string)
        val toDos = mutableListOf<ToDo>()
        var t = ToDo()
        t.description = "Needle Painting | Bordado, Por Ana Wasen"
        t.category = Category.Equilibrio.string.toString()
        toDos.add(t)
        t.description = "Tricotin | Modelagem, Por Isis Kranz"
        t.category = Category.Equilibrio.string.toString()
        toDos.add(t)
        return toDos
    }

    fun getToDosSampleData(): List<ToDo>{
        val toDos = mutableListOf<ToDo>()
        var t = ToDo()
        t.description = "Bordado, Por Ana Wasen"
        t.category = "Needle Painting"
        toDos.add(t)
        var t2 = ToDo()
        t2.description = "Modelagem, Por Isis Kranz"
        t2.category = "Tricotin"
        toDos.add(t2)
        return toDos
    }
}
