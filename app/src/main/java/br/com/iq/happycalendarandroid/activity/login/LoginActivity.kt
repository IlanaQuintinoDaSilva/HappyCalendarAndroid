package br.com.iq.happycalendarandroid.activity.login

import android.content.Intent
import android.os.Bundle
import br.com.iq.happycalendarandroid.HappyCalendarApplication
import br.com.iq.happycalendarandroid.R
import br.com.iq.happycalendarandroid.activity.BaseActivity
import br.com.iq.happycalendarandroid.activity.ToDoListActivity
import br.com.iq.happycalendarandroid.domain.Category
import br.com.iq.happycalendarandroid.domain.api.ToDoService
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {
    private var service = ToDoService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if(!HappyCalendarApplication.launched){
            feedInitialToDosData()
        }

        btEntrar.setOnClickListener{
            val intent = Intent(context, ToDoListActivity::class.java)
            intent.putExtra("category", Category.Equilibrio)
            startActivity(intent)
        }
    }

    private fun feedInitialToDosData(){
        HappyCalendarApplication.toDos = service.getToDosSampleData()
        HappyCalendarApplication.launched = true
    }
}
