package com.reto1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.reto1.databinding.ActivityControllerBinding

class ControllerActivity : AppCompatActivity() {

    private lateinit var homeFragment: HomeFragment
    private lateinit var profileFragment: ProfileFragment
    private lateinit var publishFragment: PublishFragment

    private lateinit var binding: ActivityControllerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityControllerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        homeFragment = HomeFragment.newInstance()
        profileFragment = ProfileFragment.newInstance()
        publishFragment = PublishFragment.newInstance()

        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->

            if (menuItem.itemId == R.id.home) {
                showFragment(homeFragment)
            } else if (menuItem.itemId == R.id.profile) {
                showFragment(profileFragment)
            } else if (menuItem.itemId == R.id.publish) {
                showFragment(publishFragment)
            }

            true
        }
    }

    fun showFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()

        transaction.replace(R.id.fragmentControl, fragment)

        transaction.commit()
    }
}