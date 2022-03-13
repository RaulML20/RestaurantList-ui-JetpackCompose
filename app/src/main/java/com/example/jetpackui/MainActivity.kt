package com.example.jetpackui

import android.os.Bundle
import android.widget.SearchView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackui.ui.theme.JetpackUITheme

data class Restaurants(val name : String, val image : Int, val averagePrice : Int)
val listRestaurants = mutableListOf<Restaurants>()

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val r1 = Restaurants("Gaiteira", R.drawable.gaiteira, 20)
        val r2 = Restaurants("Meson don nacho", R.drawable.meson_don_nacho, 30)
        val r3 = Restaurants("Tele sushi", R.drawable.tele_sushi, 40)
        listRestaurants.add(r1)
        listRestaurants.add(r2)
        listRestaurants.add(r3)

        setContent {
            JetpackUITheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    showSearchBar()
                    showList(listRestaurants)
                }
            }
        }
    }
}



@Composable
fun showRestaurantList(items : Restaurants) {
    Row(modifier = Modifier.padding(3.dp)){
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()){
            Image(painterResource(id = items.image), contentDescription = "Restaurant image", modifier = Modifier
                .size(350.dp)
            )
            Text(
                text = items.name,
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(3.dp)
            )
        }
    }
    Row {
        Box (contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()){
            Text(
                text = "Average price: ${items.averagePrice}$",
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(1.dp)
            )
        }
    }
}

@Composable
fun showSearchBar(){
    Row{
        Box(contentAlignment = Alignment.TopCenter, modifier = Modifier.fillMaxWidth(1f).size(150.dp)){
            var text by remember { mutableStateOf("") }
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Search") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.clip(RoundedCornerShape(5.dp))
            )
        }
    }
}

@Composable
fun showList(list : List<Restaurants>){
    LazyColumn(modifier = Modifier.height(870.dp).padding(0.dp, 70.dp, 0.dp, 0.dp)){
        items(list) { items ->
            showRestaurantList(items)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackUITheme {
        showList(listRestaurants)
    }
}