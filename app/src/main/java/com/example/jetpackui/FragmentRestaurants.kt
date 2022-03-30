package com.example.jetpackui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay

class FragmentRestaurants(val list: List<Restaurants3>, context: Context?) : Fragment() {

    val intent = Intent(context, RestaurantProfile::class.java)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                startList()
            }
        }
    }

    @Composable
    fun showRestaurantList2(items : Restaurants3){
        val pun = items.punctuation.toString()

        Card(elevation = 4.dp, modifier = Modifier
            .padding(18.dp, 0.dp, 18.dp, 10.dp)
            .clickable(onClick = { startActivity(intent) }), shape = RoundedCornerShape(16.dp)
        ){
            Column {
                Row{
                    Box(contentAlignment = Alignment.TopCenter, modifier = Modifier.fillMaxSize()) {
                        Image(
                            painterResource(id = items.image),
                            contentDescription = "Restaurant image",
                            modifier = Modifier
                                .fillMaxSize(),
                            alignment = Alignment.Center,
                            contentScale = ContentScale.FillWidth)
                    }
                }
                Row{
                    Box(contentAlignment = Alignment.TopCenter, modifier = Modifier
                        .padding(10.dp, 10.dp, 10.dp, 5.dp)
                        .fillMaxSize()) {
                        Text(
                            text = items.name,
                            style = MaterialTheme.typography.h5,
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                        )
                        Text(
                            text = pun,
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                        )
                    }
                }
                Row {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp, 5.dp, 10.dp, 10.dp)) {
                        Text(
                            text = "Average price: ${items.averagePrice}€",
                            style = MaterialTheme.typography.subtitle1,
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                        )
                        Text(
                            text = "${items.distance}km",
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun startList(){
        var refreshing by remember { mutableStateOf(false) }
        LaunchedEffect(refreshing) {
            if (refreshing) {
                delay(2000)
                refreshing = false
            }
        }

        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = refreshing),
            onRefresh = { refreshing = true }
        ){
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