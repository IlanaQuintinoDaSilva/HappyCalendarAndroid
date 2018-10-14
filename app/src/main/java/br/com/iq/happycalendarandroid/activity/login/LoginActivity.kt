package br.com.iq.happycalendarandroid.activity.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.iq.happycalendarandroid.R
import br.com.iq.happycalendarandroid.activity.BaseActivity
import br.com.iq.happycalendarandroid.activity.ToDoListActivity
import br.com.iq.happycalendarandroid.domain.Category
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btEntrar.setOnClickListener{
            val intent = Intent(context, ToDoListActivity::class.java)
            intent.putExtra("category", Category.Equilibrio)
            startActivity(intent)
        }
    }
}
