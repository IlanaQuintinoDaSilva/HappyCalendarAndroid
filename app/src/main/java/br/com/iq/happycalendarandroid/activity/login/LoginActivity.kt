package br.com.iq.happycalendarandroid.activity.login

import android.content.Intent
import android.os.Bundle
import br.com.iq.happycalendarandroid.R
import br.com.iq.happycalendarandroid.activity.BaseActivity
import br.com.iq.happycalendarandroid.activity.ToDoListActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        btEntrar.setOnClickListener{
            val intent = Intent(context, ToDoListActivity::class.java)
            startActivity(intent)
        }
    }

}
