package br.com.iq.happycalendarandroid.activity

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.util.Log
import android.view.View
import android.widget.CheckBox
import br.com.iq.happycalendarandroid.HappyCalendarApplication
import br.com.iq.happycalendarandroid.R
import br.com.iq.happycalendarandroid.data.DatabaseHelper
import br.com.iq.happycalendarandroid.data.TodosContract
import br.com.iq.happycalendarandroid.domain.api.CategoryService
import br.com.iq.happycalendarandroid.domain.api.ToDoService
import br.com.iq.happycalendarandroid.extensions.addFragment
import br.com.iq.happycalendarandroid.extensions.setupToolbar
import br.com.iq.happycalendarandroid.fragment.BacklogFragment
import br.com.iq.happycalendarandroid.fragment.CategoryListFragment
import br.com.iq.happycalendarandroid.fragment.StatiticsFragment
import br.com.iq.happycalendarandroid.fragment.ToDoListFragment
import br.com.iq.happycalendarandroid.google_drive.CloudBackup

class ToDoListActivity : BaseActivity() {

    private lateinit var mDrawerLayout: DrawerLayout
    private var service = ToDoService()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        feedInitialToDosData()

        setContentView(R.layout.activity_todo_list)
        HappyCalendarApplication.toDosScreen = true
        setToolBarTitle(getString(R.string.actual_sprint))

        mDrawerLayout = findViewById(R.id.drawer_layout)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // set item as selected to persist highlight
            menuItem.isChecked = true

            when (menuItem.itemId) {
                R.id.nav_actual_sprint ->{
                    if(savedInstanceState == null){
                        setToolBarTitle(getString(R.string.actual_sprint))
                        addFragment(R.id.container, ToDoListFragment())
                    }
                }
                R.id.nav_backlog ->{
                    if(savedInstanceState == null){
                        val intent = Intent(context, BacklogActivity::class.java)
                        startActivity(intent)
                    }
                }
                R.id.nav_category ->{
                    val intent = Intent(context, CategoryActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_google_drive ->{
                    val intent = Intent(context, CloudBackup::class.java)
                    startActivity(intent)
                }
            }

            // close drawer when item is tapped
            mDrawerLayout.closeDrawers()

            // Add code here to update the UI based on the item selected
            // For example, swap UI fragments here

            true
        }


        if(savedInstanceState == null){
            addFragment(R.id.container, ToDoListFragment())
        }
        //CreateTodo()
        //readData()
    }

    private fun setToolBarTitle(title:String){
        setupToolbar(R.id.toolbar, title, false)
    }


    private fun feedInitialToDosData(){
        val helper = DatabaseHelper(this)
        HappyCalendarApplication.dbHelper = helper
        HappyCalendarApplication.toDos = service.getToDos(helper, "0")
        //HappyCalendarApplication.backlog = service.getToDos(helper, "1")
        //service.addToDo("Fazer almoço", "Casa", helper)

    }

    /*private fun readData() {
        val helper = DatabaseHelper(this)
        val db = helper.readableDatabase
        val projection = arrayOf(TodosContract.TodosEntry.COLUMN_TEXT, TodosContract.TodosEntry.COLUMN_SPRINT, TodosContract.TodosEntry.COLUMN_DONE, TodosContract.TodosEntry.COLUMN_CATEGORY)
        val selection = TodosContract.TodosEntry.COLUMN_BACKLOG + " = ?"
        val selectionArgs = arrayOf("1")
        val c = db.query(TodosContract.TodosEntry.TABLE_NAME,
                projection, selection, selectionArgs, null, null, null)
        var i = c.count
        Log.d("Record Count", i.toString())

        var rowContent = ""
        while (c.moveToNext()) {
            i = 0
            while (i < 4) {
                rowContent += c.getString(i) + " - "
                i++
            }
            Log.i("Todo Row " + c.position.toString(), rowContent)
            rowContent = ""
        }
        c.close()
    }*/

    private fun CreateTodo() {
        val helper = DatabaseHelper(this)
        val db = helper.writableDatabase

        val values = ContentValues()
        values.put(TodosContract.TodosEntry.COLUMN_TEXT, "Limpar a casa")
        values.put(TodosContract.TodosEntry.COLUMN_CATEGORY, "Casa")
        values.put(TodosContract.TodosEntry.COLUMN_SPRINT, "2016-01-02")
        values.put(TodosContract.TodosEntry.COLUMN_DONE, 0)
        values.put(TodosContract.TodosEntry.COLUMN_BACKLOG, 1)
        var todo_id = db.insert(TodosContract.TodosEntry.TABLE_NAME, null, values)

        todo_id = db.insert(TodosContract.TodosEntry.TABLE_NAME, null, values)
    }


}
