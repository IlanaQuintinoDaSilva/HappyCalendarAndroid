package br.com.iq.happycalendarandroid.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import br.com.iq.happycalendarandroid.HappyCalendarApplication
import br.com.iq.happycalendarandroid.R
import br.com.iq.happycalendarandroid.data.DatabaseHelper
import br.com.iq.happycalendarandroid.domain.api.CategoryService
import br.com.iq.happycalendarandroid.extensions.addFragment
import br.com.iq.happycalendarandroid.fragment.CategoryListFragment
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.android.synthetic.main.activity_float_button.*

class CategoryActivity : BaseActivity() {

    private var categoryService = CategoryService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        getCategories()
        addFragment(R.id.category_container, CategoryListFragment())
        fbCategory.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    private fun getCategories(){
        val helper = DatabaseHelper(this)
        HappyCalendarApplication.categories =  categoryService.getCategories(helper)
    }


}
