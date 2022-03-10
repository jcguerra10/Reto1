package com.reto1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.reto1.databinding.ActivityMainBinding
import com.reto1.model.UserAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var userAdapter: UserAdapter

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        userAdapter = UserAdapter()

        binding.logInBtn.setOnClickListener {
            var mail = binding.editTextTextPersonName.text.toString()
            var pass = binding.editTextTextPassword.text.toString()

            if (userAdapter.verifyUser(mail, pass)) {
                val inte = Intent(this, ControllerActivity::class.java)
                startActivity(inte)
            } else {
                val toast = Toast.makeText(applicationContext, "Contraseña y/o Usuario no Coinciden", Toast.LENGTH_LONG)
                toast.show()
            }
        }
    }
}