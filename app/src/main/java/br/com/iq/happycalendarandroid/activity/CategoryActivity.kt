package br.com.iq.happycalendarandroid.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.iq.happycalendarandroid.R
import br.com.iq.happycalendarandroid.extensions.addFragment
import br.com.iq.happycalendarandroid.fragment.CategoryListFragment

class CategoryActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        addFragment(R.id.category_container, CategoryListFragment())
    }


}
