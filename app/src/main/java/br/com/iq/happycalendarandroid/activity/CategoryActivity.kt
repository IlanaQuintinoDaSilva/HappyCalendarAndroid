package br.com.iq.happycalendarandroid.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import br.com.iq.happycalendarandroid.HappyCalendarApplication
import br.com.iq.happycalendarandroid.R
import br.com.iq.happycalendarandroid.data.DatabaseHelper
import br.com.iq.happycalendarandroid.domain.api.CategoryService
import br.com.iq.happycalendarandroid.extensions.addFragment
import br.com.iq.happycalendarandroid.extensions.setupToolbar
import br.com.iq.happycalendarandroid.fragment.BacklogFragment
import br.com.iq.happycalendarandroid.fragment.CategoryListFragment
import br.com.iq.happycalendarandroid.fragment.ToDoListFragment
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : BaseActivity() {

    private lateinit var mDrawerLayout: DrawerLayout
    private var categoryService = CategoryService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        getCategories()
        setToolBarTitle(getString(R.string.categories))

        mDrawerLayout = findViewById(R.id.drawer_layout_ct)

        val navigationView: NavigationView = findViewById(R.id.nav_view_ct)
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
                        val intent = Intent(context, BacklogActivity::class.java)
                        startActivity(intent)
                    }
                }

                R.id.nav_category ->{
                    setToolBarTitle(getString(R.string.categories))
                    addFragment(R.id.container, CategoryListFragment())
                }
            }

            // close drawer when item is tapped
            mDrawerLayout.closeDrawers()

            // Add code here to update the UI based on the item selected
            // For example, swap UI fragments here

            true
        }

        addFragment(R.id.category_container, CategoryListFragment())
        fbCategory.setOnClickListener {
            val intent = Intent(context, AddCategoryActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getCategories(){
        val helper = DatabaseHelper(this)
        HappyCalendarApplication.categories =  categoryService.getCategories(helper)
    }

    private fun setToolBarTitle(title:String){
        setupToolbar(R.id.toolbar, title, false)
    }


}
