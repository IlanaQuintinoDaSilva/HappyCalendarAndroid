package br.com.iq.happycalendarandroid.activity

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import br.com.iq.happycalendarandroid.R
import br.com.iq.happycalendarandroid.data.DatabaseHelper
import br.com.iq.happycalendarandroid.domain.api.CategoryService
import br.com.iq.happycalendarandroid.domain.api.ToDoService
import kotlinx.android.synthetic.main.activity_add_backlog.*
import kotlinx.android.synthetic.main.activity_backlog.*

class AddBacklogActivity : BaseActivity() {
    private var category: String = ""
    private var backlog: String = ""
    private var toDoService = ToDoService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_backlog)
        btAdd.setOnClickListener{
            category = etBacklogCategory.text.toString()
            backlog = etBacklog.text.toString()
            addBacklog(backlog,category)
            val intent = Intent(context, BacklogActivity::class.java)
            startActivity(intent)
        }

        val spinner: Spinner = findViewById(R.id.planets_spinner)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
                this,
                R.array.planets_array,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
    }

    private fun addBacklog(backlog: String, category: String){
        val helper = DatabaseHelper(this)
        toDoService.addToDo(backlog, category, helper)
    }
}

class SpinnerActivity : Activity(), AdapterView.OnItemSelectedListener {

    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        /*val spinner: Spinner = findViewById(R.id.spinner)
        spinner.onItemSelectedListener = this*/
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }
}
