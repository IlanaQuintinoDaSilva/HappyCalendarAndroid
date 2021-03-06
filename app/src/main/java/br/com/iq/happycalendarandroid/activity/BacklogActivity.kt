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
import br.com.iq.happycalendarandroid.google_drive.CloudBackup
import kotlinx.android.synthetic.main.activity_backlog.*
import kotlinx.android.synthetic.main.fragment_backlog.*

class BacklogActivity : BaseActivity() {

    private lateinit var mDrawerLayout: DrawerLayout
    private var service = ToDoService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        feedBacklogData()

        setContentView(R.layout.activity_backlog)
        HappyCalendarApplication.toDosScreen = false
        setToolBarTitle(getString(R.string.backlog))

        mDrawerLayout = findViewById(R.id.drawer_layout_bk)

        val navigationView: NavigationView = findViewById(R.id.nav_view_bk)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // set item as selected to persist highlight
            menuItem.isChecked = true

            when (menuItem.itemId) {
                R.id.nav_actual_sprint ->{
                    if(savedInstanceState == null){
                        val intent = Intent(context, ToDoListActivity::class.java)
                        startActivity(intent)
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
            displayMainScreen()
        }

        btAddToDo.setOnClickListener {
            val intent = Intent(context, AddBacklogActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        feedBacklogData()
    }

    private fun setToolBarTitle(title:String){
        setupToolbar(R.id.toolbar, title, false)
    }

    private fun feedBacklogData(){
        val helper = DatabaseHelper(this)
        HappyCalendarApplication.dbHelper = helper
        HappyCalendarApplication.backlog = service.getToDos(helper, "1")

    }

    private fun displayMainScreen(){
        setToolBarTitle(getString(R.string.backlog))
        addFragment(R.id.container_bk, BacklogFragment())
    }
}
