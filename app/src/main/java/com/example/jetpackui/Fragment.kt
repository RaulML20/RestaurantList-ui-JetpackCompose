package com.example.jetpackui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


class Fragment(val list : List<Restaurants2>) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                LazyColumn(modifier = Modifier
                    .height(870.dp)
                    .padding(0.dp, 70.dp, 0.dp, 0.dp)){
                    items(list) { items ->
                        showRestaurantList2(items)
                    }
                }
            }
        }
    }

    @Composable
    fun showRestaurantList2(items : Restaurants2){
        val pun = items.punctuation.toString()

        Row(modifier = Modifier.padding(3.dp)) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Image(
                    painterResource(id = items.image),
                    contentDescription = "Restaurant image",
                    modifier = Modifier
                        .size(350.dp)
                )
                Text(
                    text = items.name,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(10.dp)
                )
                Text(
                    text = pun,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .clip(CircleShape)
                        .border(1.4.dp, MaterialTheme.colors.secondary, CircleShape)
                        .padding(12.dp)
                )
            }
        }
        Row {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "Average price: ${items.averagePrice}€",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(15.dp)
                )
                Text(
                    text = "${items.distance}km",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(15.dp)
                )
            }
        }
    }
}