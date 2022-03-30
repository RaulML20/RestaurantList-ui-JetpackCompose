package com.example.jetpackui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView

data class Restaurants3(val name : String, val image : Int, val averagePrice : Int, val punctuation : Double, val distance : Double)
var listRestaurants3 = mutableListOf<Restaurants3>()
var listResult3 = mutableListOf<Restaurants3>()

class Fragment_init : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_init, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        replaceFragment2(Fragment2())

        val searchView = requireView().findViewById<SearchView>(R.id.search)
        val r1 = Restaurants3("Gaiteira", R.drawable.gaiteira, 20, 3.2, 0.2)
        val r2 = Restaurants3("Meson don nacho", R.drawable.meson_don_nacho, 30, 4.5, 1.1)
        val r3 = Restaurants3("Tele sushi", R.drawable.tele_sushi, 40, 4.0, 3.2)
        listRestaurants3.add(r1)
        listRestaurants3.add(r2)
        listRestaurants3.add(r3)

        replaceFragment(FragmentRestaurants(listRestaurants3, context))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                listResult3.clear()
                listRestaurants3.forEach {
                    if(it.name.contains(p0.toString())){
                        listResult3.add(it)
                    }
                }

                replaceFragment(FragmentRestaurants(listResult3, context))
                return false
            }
        })
    }

    private fun replaceFragment(fragment : FragmentRestaurants){
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment2, fragment)
        fragmentTransaction.commit()
    }

    private fun replaceFragment2(fragment : Fragment2){
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.topList, fragment)
        fragmentTransaction.commit()
    }
}