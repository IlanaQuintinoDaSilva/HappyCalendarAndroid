package br.com.iq.happycalendarandroid.fragment

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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ToDosListFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ToDosListFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ToDosListFragment : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var categoryDesc = Category.Equilibrio
    private var toDos = listOf<ToDo>()
    var rvToDo: RecyclerView? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryDesc = arguments?.getSerializable("category") as Category
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

    override fun onResume(){
        super.onResume()
        taskToDos()
    }

    fun taskToDos(){
        this.toDos = ToDoService.getToDos(requireContext(), categoryDesc)

        rvToDo?.adapter = ToDoAdapter(toDos,
                {
                    toDo: ToDo ->
                    System.out.println("Oi!")
                }
                )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                ToDosListFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
