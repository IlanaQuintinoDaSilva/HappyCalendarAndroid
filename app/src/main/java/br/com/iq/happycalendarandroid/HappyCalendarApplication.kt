package br.com.iq.happycalendarandroid

import br.com.iq.happycalendarandroid.domain.CategoryHC
import br.com.iq.happycalendarandroid.domain.ToDo
import java.util.*

object HappyCalendarApplication {
    var toDos: List<ToDo> = ArrayList()
    var categories: List<CategoryHC> = ArrayList()
    var backlog: List<ToDo> = ArrayList()
    var launched: Boolean = false
}