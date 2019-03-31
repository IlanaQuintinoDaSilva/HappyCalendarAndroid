package br.com.iq.happycalendarandroid.activity

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import br.com.iq.happycalendarandroid.HappyCalendarApplication
import br.com.iq.happycalendarandroid.R
import br.com.iq.happycalendarandroid.R.string.category
import br.com.iq.happycalendarandroid.data.DatabaseHelper
import br.com.iq.happycalendarandroid.domain.Category
import br.com.iq.happycalendarandroid.domain.ToDo
import br.com.iq.happycalendarandroid.domain.api.CategoryService
import br.com.iq.happycalendarandroid.domain.api.ToDoService
import kotlinx.android.synthetic.main.activity_add_backlog.*
import kotlinx.android.synthetic.main.activity_backlog.*

class AddBacklogActivity : BaseActivity() {
    private var category: String = ""
    private var backlog: String = ""
    private var toDoService = ToDoService()
    private var categories: List<Category> = ArrayList()
    private var categoryService = CategoryService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_backlog)
        btAdd.setOnClickListener{
            backlog = etBacklog.text.toString()
            addBacklog(backlog,category)
            val intent = Intent(context, BacklogActivity::class.java)
            startActivity(intent)
        }


        getCategories()

        val spinner: Spinner = findViewById(R.id.planets_spinner)
        // Create an ArrayAdapter using the string array and a default spinner layout


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                category = parent.getItemAtPosition(pos).toString()


            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Code to perform some action when nothing is selected
            }
        }


        categories = HappyCalendarApplication.categories
        var arraySize =  categories.count()


        var myStrings = arrayOfNulls<String>(arraySize)
        categories = HappyCalendarApplication.categories


        var i = 0
        for (category in HappyCalendarApplication.categories){
            myStrings[i] = category.name
            i++
        }

        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, myStrings)

    }

    private fun addBacklog(backlog: String, category: String){
        val helper = DatabaseHelper(this)
        toDoService.addToDo(backlog, category, helper)
    }

    private fun getCategories(){
        val helper = DatabaseHelper(this)
        HappyCalendarApplication.categories =  categoryService.getCategories(helper)
    }
}

/*class SpinnerActivity : Activity(), AdapterView.OnItemSelectedListener {

    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {

        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        /*val spinner: Spinner = findViewById(R.id.spinner)
        spinner.onItemSelectedListener = this*/
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }
}*/
