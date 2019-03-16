package br.com.iq.happycalendarandroid.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.iq.happycalendarandroid.R
import br.com.iq.happycalendarandroid.data.DatabaseHelper
import br.com.iq.happycalendarandroid.domain.api.CategoryService
import kotlinx.android.synthetic.main.activity_add_category.*

class AddCategoryActivity : BaseActivity() {
    private var category: String = ""
    private var categoryService = CategoryService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_category)
        btAddCategory.setOnClickListener{
            category = etCategory.text.toString()
            addCategory(category)
            val intent = Intent(context, CategoryActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addCategory(category: String){
        val helper = DatabaseHelper(this)
        categoryService.addCategory(category, helper)
    }
}
