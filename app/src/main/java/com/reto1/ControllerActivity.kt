package com.reto1

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.reto1.databinding.ActivityControllerBinding
import com.reto1.model.*

class ControllerActivity : AppCompatActivity() {

    private lateinit var homeFragment: HomeFragment
    private lateinit var profileFragment: ProfileFragment
    private lateinit var publishFragment: PublishFragment

    private lateinit var binding: ActivityControllerBinding

    private lateinit var userController: UserController
    private lateinit var publicationController: PublicationController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityControllerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        desSerialized()

        val bundle = intent.extras
        val actualUser = bundle?.getInt("actualUser")
        if (actualUser != null) {
            userController.setUpActualUser(actualUser)
        }

        homeFragment = HomeFragment.newInstance()
        homeFragment.setControllers(publicationController, userController)
        profileFragment = ProfileFragment.newInstance()
        profileFragment.setControllers(publicationController, userController)
        publishFragment = PublishFragment.newInstance()
        publishFragment.setControllers(publicationController, userController)

        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> showFragment(homeFragment)
                R.id.profile -> showFragment(profileFragment)
                R.id.publish -> showFragment(publishFragment)
            }
            true
        }
    }

    private fun showFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()

        transaction.replace(R.id.fragmentControl, fragment)

        transaction.commit()
    }

    override fun onPause() {
        super.onPause()
        val serializationClass = SerializationClass(publicationController, userController)

        val json = Gson().toJson(serializationClass)
        Log.e(">>>", json.toString())
        val sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        sharedPreferences.edit()
            .putString("serialization" , json)
            .apply()
    }

    override fun onResume() {
        super.onResume()
    }

    private fun desSerialized() {
        val sharedPreferences = getPreferences(Context.MODE_PRIVATE)

        val json = sharedPreferences.getString("serialization", "No_Data")
        Log.e("!!!", json.toString())
        if (json != "No_Data") {
            val ser = Gson().fromJson(json, SerializationClass::class.java)
            userController = ser.userController
            publicationController = ser.publicationController
        } else {
            userController = UserController()
            publicationController = PublicationController()
        }
    }
}