package br.com.iq.happycalendarandroid.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.util.Log
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

class ToDoListActivity : BaseActivity() {

    private lateinit var mDrawerLayout: DrawerLayout
    private var service = ToDoService()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(!HappyCalendarApplication.launched){
            feedInitialToDosData()
        }
        setContentView(R.layout.activity_todo_list)
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
                        setToolBarTitle(getString(R.string.backlog))
                        addFragment(R.id.container, BacklogFragment())
                    }
                }

                R.id.nav_category ->{
                    val intent = Intent(context, CategoryActivity::class.java)
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

        readData()
    }

    private fun setToolBarTitle(title:String){
        setupToolbar(R.id.toolbar, title, false)
    }


    private fun feedInitialToDosData(){
        val helper = DatabaseHelper(this)
        HappyCalendarApplication.toDos = service.getToDos(helper)
        HappyCalendarApplication.backlog = service.getBacklogSampleData()
        HappyCalendarApplication.launched = true

    }

    private fun readData() {
        val helper = DatabaseHelper(this)
        val db = helper.readableDatabase
        val projection = arrayOf(TodosContract.TodosEntry.COLUMN_TEXT, TodosContract.TodosEntry.COLUMN_SPRINT, TodosContract.TodosEntry.COLUMN_DONE, TodosContract.TodosEntry.COLUMN_CATEGORY)
        //String selection = TodosContract.TodosEntry.COLUMN_CATEGORY + " = ?";
        //String[] selectionArgs = {"1"};
        val c = db.query(TodosContract.TodosEntry.TABLE_NAME,
                projection, null, null, null, null, null)
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
    }




}
