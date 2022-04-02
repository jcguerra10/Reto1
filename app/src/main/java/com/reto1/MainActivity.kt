package com.reto1

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.reto1.databinding.ActivityMainBinding
import com.reto1.model.SerializationClass
import com.reto1.model.UserAdapter
import com.reto1.model.UserController

class MainActivity : AppCompatActivity() {

    private lateinit var userController: UserController

    private lateinit var binding: ActivityMainBinding

    private lateinit var controllerActivity: ControllerActivity

    @SuppressLint("WrongConstant")
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
        controllerActivity = ControllerActivity()


        var message = ""
        var bool = false

        binding.keepSignedSwt?.setOnCheckedChangeListener { _, isChecked ->
            message = if (isChecked) "Seguir Conectado:ON" else "No Seguir Conectado:OFF"
            Toast.makeText(
                this@MainActivity, message,
                Toast.LENGTH_SHORT
            ).show()

            bool = isChecked
        }


        binding.logInBtn.setOnClickListener {
            var mail = binding.editTextTextPersonName.text.toString()
            var pass = binding.editTextTextPassword.text.toString()

            val intUser = userController.verifyUser(mail, pass)
            if (intUser != -1) {
                val intent = Intent(this, ControllerActivity::class.java)
                intent.putExtra("actualUser", intUser)
                intent.putExtra("keepSigned", bool)
                startActivity(intent)

            } else {
                val toast = Toast.makeText(applicationContext, "Contraseña y/o Usuario no Coinciden", Toast.LENGTH_LONG)
                toast.show()
            }
        }
    }
}