package br.com.iq.happycalendarandroid.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.iq.happycalendarandroid.R
import br.com.iq.happycalendarandroid.domain.Category
import br.com.iq.happycalendarandroid.extensions.addFragment
import br.com.iq.happycalendarandroid.fragment.ToDoListFragment

class ToDoListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_list)

        val category = intent.getSerializableExtra("category") as Category
        val title = getString(category.string)

        if(savedInstanceState == null){
            addFragment(R.id.container, ToDoListFragment())
        }
    }
}
