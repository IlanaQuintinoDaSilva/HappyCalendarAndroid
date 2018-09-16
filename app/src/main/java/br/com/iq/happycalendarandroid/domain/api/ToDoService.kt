package br.com.iq.happycalendarandroid.domain.api

import android.content.Context
import br.com.iq.happycalendarandroid.R
import br.com.iq.happycalendarandroid.domain.Category
import br.com.iq.happycalendarandroid.domain.ToDo



object ToDoService{
    fun getToDos(context: Context, category: Category): List<ToDo>{
        val categoryString = context.getString(category.string)
        val toDos = mutableListOf<ToDo>()
        var t = ToDo()
        t.description = "Ligar assistência técnica Samsung. Conserto secadora."
        t.category = Category.Equilibrio.string.toString()
        toDos.add(t)
        t.description = "Finalizar cursos .NET Udemy."
        t.category = Category.Equilibrio.string.toString()
        toDos.add(t)
        return toDos
    }
}
