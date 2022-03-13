package com.example.jetpackui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.fragment.app.ListFragment

data class Restaurants2(val name : String, val image : Int, val averagePrice : Int, val punctuation : Double, val distance : Double)
var listRestaurants2 = mutableListOf<Restaurants2>()
var listResult = mutableListOf<Restaurants2>()

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchView = findViewById<SearchView>(R.id.search)
        val r1 = Restaurants2("Gaiteira", R.drawable.gaiteira, 20, 3.2, 0.2)
        val r2 = Restaurants2("Meson don nacho", R.drawable.meson_don_nacho, 30, 4.5, 1.1)
        val r3 = Restaurants2("Tele sushi", R.drawable.tele_sushi, 40, 4.0, 3.2)
        listRestaurants2.add(r1)
        listRestaurants2.add(r2)
        listRestaurants2.add(r3)

        replaceFragment(Fragment(listRestaurants2))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                listResult.clear()
                listRestaurants2.forEach {
                    if(it.name.contains(p0.toString())){
                        listResult.add(it)
                    }
                }

                replaceFragment(Fragment(listResult))
                return false
            }
        })
    }

    private fun replaceFragment(fragment : Fragment){
        val FragmentManager = supportFragmentManager
        val FragmentTransaction = FragmentManager.beginTransaction()
        FragmentTransaction.replace(R.id.fragment, fragment)
        FragmentTransaction.commit()
    }
}

