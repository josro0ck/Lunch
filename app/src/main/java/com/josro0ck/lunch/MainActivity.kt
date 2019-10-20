package com.josro0ck.lunch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.josro0ck.lunch.extensions.inTransaction

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.inTransaction {
            add(R.id.fragmentContainer, AddIngredientFragment.newInstance())
        }
    }
}
