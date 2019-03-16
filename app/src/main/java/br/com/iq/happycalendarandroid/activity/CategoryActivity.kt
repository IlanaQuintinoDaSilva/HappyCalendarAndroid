package br.com.iq.happycalendarandroid.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.iq.happycalendarandroid.HappyCalendarApplication
import br.com.iq.happycalendarandroid.R
import br.com.iq.happycalendarandroid.data.DatabaseHelper
import br.com.iq.happycalendarandroid.domain.api.CategoryService
import br.com.iq.happycalendarandroid.extensions.addFragment
import br.com.iq.happycalendarandroid.fragment.CategoryListFragment

class CategoryActivity : BaseActivity() {

    private var categoryService = CategoryService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        getCategories()
        addFragment(R.id.category_container, CategoryListFragment())
    }

    private fun getCategories(){
        val helper = DatabaseHelper(this)
        HappyCalendarApplication.categories =  categoryService.getCategories(helper)
    }


}
