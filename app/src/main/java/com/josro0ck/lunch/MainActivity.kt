package com.josro0ck.lunch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.josro0ck.lunch.repository.Catalog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, Catalog.myCatalog)
        ingredientName.setAdapter(adapter)


    }
}
