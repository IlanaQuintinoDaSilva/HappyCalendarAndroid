package br.com.iq.happycalendarandroid.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import br.com.iq.happycalendarandroid.HappyCalendarApplication
import br.com.iq.happycalendarandroid.R
import br.com.iq.happycalendarandroid.data.DatabaseHelper
import br.com.iq.happycalendarandroid.domain.api.ToDoService
import br.com.iq.happycalendarandroid.extensions.addFragment
import br.com.iq.happycalendarandroid.extensions.setupToolbar
import br.com.iq.happycalendarandroid.fragment.BacklogFragment
import br.com.iq.happycalendarandroid.fragment.ToDoListFragment
import kotlinx.android.synthetic.main.activity_backlog.*
import kotlinx.android.synthetic.main.fragment_backlog.*

class BacklogActivity : BaseActivity() {

    private lateinit var mDrawerLayout: DrawerLayout
    private var service = ToDoService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        feedBacklogData()

        setContentView(R.layout.activity_backlog)
        setToolBarTitle(getString(R.string.backlog))

        mDrawerLayout = findViewById(R.id.drawer_layout_bk)

        val navigationView: NavigationView = findViewById(R.id.nav_view_bk)
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
                        displayMainScreen()
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
            displayMainScreen()
        }

        btAddToDo.setOnClickListener {
            val intent = Intent(context, AddBacklogActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setToolBarTitle(title:String){
        setupToolbar(R.id.toolbar, title, false)
    }

    private fun feedBacklogData(){
        val helper = DatabaseHelper(this)
        HappyCalendarApplication.backlog = service.getToDos(helper, "1")

    }

    private fun displayMainScreen(){
        setToolBarTitle(getString(R.string.backlog))
        addFragment(R.id.container_bk, BacklogFragment())
    }
}
