package br.com.iq.happycalendarandroid.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.iq.happycalendarandroid.HappyCalendarApplication
import br.com.iq.happycalendarandroid.R
import br.com.iq.happycalendarandroid.adapter.ToDoAdapter
import br.com.iq.happycalendarandroid.domain.Category
import br.com.iq.happycalendarandroid.domain.ToDo
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.TemporalAdjusters
import java.util.*


class ToDoListFragment : BaseFragment() {
    private var toDos: List<ToDo> = ArrayList()
    private var selectedSprint: Date = Date()
    var rvToDo: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getToDos(selectedSprint)
        ///category = arguments?.getSerializable("category") as Category
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_todo_list, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvToDo = view?.findViewById(R.id.rvToDo)
        rvToDo?.layoutManager = LinearLayoutManager(activity)
        rvToDo?.itemAnimator = DefaultItemAnimator()
        rvToDo?.setHasFixedSize(true)
    }


    override fun onResume(){
        super.onResume()
        setupAdapter(toDos)
    }

    private fun setupAdapter(list: List<ToDo>){
        rvToDo?.adapter = ToDoAdapter(toDos) { onClickItem(it) }
    }

    private fun onClickItem(toDo: ToDo) {

    }

    private fun getToDos(selectedSprint: Date){
        var dtLastSunday1 = getSundayDate(selectedSprint)
        val selectedSprintToDos = mutableListOf<ToDo>()

        for(todo in HappyCalendarApplication.toDos){
            var dtLastSunday2 = getSundayDate(todo.sprint.startDate)
            if(dtLastSunday1 == dtLastSunday2){
                selectedSprintToDos.add(todo)
            }
        }
        toDos = selectedSprintToDos
    }

    @SuppressLint("NewApi")
    private fun getSundayDate(date: Date): Date{
        val localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        val y = localDate.year
        val m = localDate.monthValue
        val d = localDate.dayOfMonth

        var lastSunday = LocalDate.of(y, m, d).with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))
        return Date.from(lastSunday.atStartOfDay(ZoneId.systemDefault()).toInstant())
    }

}