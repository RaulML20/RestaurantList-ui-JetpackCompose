package com.example.jetpackui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.TextView

class RestaurantProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_profile)

        val description = findViewById<TextView>(R.id.description)
        description.movementMethod = ScrollingMovementMethod()
    }
}