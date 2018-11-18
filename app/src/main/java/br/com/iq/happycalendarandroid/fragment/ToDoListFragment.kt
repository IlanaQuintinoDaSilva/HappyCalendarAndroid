package br.com.iq.happycalendarandroid.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.content.ContextCompat
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.iq.happycalendarandroid.R
import br.com.iq.happycalendarandroid.adapter.ToDoAdapter
import br.com.iq.happycalendarandroid.domain.Category
import br.com.iq.happycalendarandroid.domain.ToDo
import br.com.iq.happycalendarandroid.domain.api.ToDoService
import br.com.iq.happycalendarandroid.utils.DateUtil
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.TemporalAdjusters
import java.util.*
import java.time.ZoneId.systemDefault



class ToDoListFragment : BaseFragment() {
    private var category = Category.Equilibrio
    private var toDos: List<ToDo> = ArrayList()
    private var service = ToDoService()
    var rvToDo: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getToDos()
        category = arguments?.getSerializable("category") as Category
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_todo_list, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvToDo = view?.findViewById<RecyclerView>(R.id.rvToDo)
        rvToDo?.layoutManager = LinearLayoutManager(activity)
        rvToDo?.itemAnimator = DefaultItemAnimator()
        rvToDo?.setHasFixedSize(true)
    }

    private fun onClickItem(toDo: ToDo) {

    }

    /*    fun taskToDos(){
        this.toDos = ToDoService.getToDos(requireContext(), category)

        rvToDo?.adapter = ToDoAdapter(toDos,
                {
                    toDo: ToDo ->
                    System.out.println("Oi!")
                }
                )
    }*/

    override fun onResume(){
        super.onResume()
        setupAdapter(toDos)
    }

    private fun setupAdapter(list: List<ToDo>){
        rvToDo?.adapter = ToDoAdapter(toDos) { onClickItem(it) }
    }

    private fun getToDos(){

        val allToDos = service.getToDosSampleData()
        val tmp = mutableListOf<ToDo>()
        for(todo in allToDos){
            if(DateUtil.formatDateToString(todo.sprint.startDate, "dd/MM/yyyy") == "23/12/2018" ){
                getSundayDate(todo.sprint.startDate)
                tmp.add(todo)
            }
        }
        toDos = tmp
    }

    @SuppressLint("NewApi")
    private fun getSundayDate(date: Date){
        val localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        val y = localDate.year
        val m = localDate.monthValue
        val d = localDate.dayOfMonth

        var lastSunday = LocalDate.of(y, m, d).with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))
        /*val cal = Calendar.getInstance()
        cal.set(2018, 11, 10)
        cal.add(Calendar.DAY_OF_WEEK, -(cal.get(Calendar.DAY_OF_WEEK) - 1))*/
        //System.out.println("Último Domingo " + LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY)))

        System.out.println("Último Domingo " + lastSunday)
    }
}