package br.com.iq.happycalendarandroid.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.iq.happycalendarandroid.R
import br.com.iq.happycalendarandroid.data.DatabaseHelper
import br.com.iq.happycalendarandroid.domain.api.CategoryService
import br.com.iq.happycalendarandroid.domain.api.ToDoService
import kotlinx.android.synthetic.main.activity_add_backlog.*
import kotlinx.android.synthetic.main.activity_backlog.*

class AddBacklogActivity : BaseActivity() {
    private var category: String = ""
    private var backlog: String = ""
    private var toDoService = ToDoService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_backlog)
        btAdd.setOnClickListener{
            category = etBacklogCategory.text.toString()
            backlog = etBacklog.text.toString()
            addBacklog(backlog,category)
            val intent = Intent(context, BacklogActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addBacklog(backlog: String, category: String){
        val helper = DatabaseHelper(this)
        toDoService.addToDo(backlog, category, helper)
    }
}
