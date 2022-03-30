package com.example.jetpackui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.recyclerview.widget.RecyclerView
import androidx.coordinatorlayout.widget.CoordinatorLayout

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navigationView = findViewById<BottomNavigationView>(R.id.navigationView)

        replaceFragment(Fragment_init())

        navigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home ->{
                    replaceFragment(Fragment_init())
                }
                R.id.reservation ->{
                    replaceFragment(Fragment_init())
                }else ->{
                    replaceFragment(Fragment_init())
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment : Fragment_init){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment, fragment)
        fragmentTransaction.commit()
    }
}

