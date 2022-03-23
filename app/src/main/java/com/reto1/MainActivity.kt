package com.reto1

import android.Manifest
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.reto1.databinding.ActivityMainBinding
import com.reto1.model.UserAdapter
import com.reto1.model.UserController

class MainActivity : AppCompatActivity() {

    private lateinit var userController: UserController

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        requestPermissions(arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE), 1)

        userController = UserController()

        binding.logInBtn.setOnClickListener {
            var mail = binding.editTextTextPersonName.text.toString()
            var pass = binding.editTextTextPassword.text.toString()

            if (userController.verifyUser(mail, pass)) {
                val inte = Intent(this, ControllerActivity::class.java)
                startActivity(inte)
            } else {
                val toast = Toast.makeText(applicationContext, "Contraseña y/o Usuario no Coinciden", Toast.LENGTH_LONG)
                toast.show()
            }
        }
    }
}