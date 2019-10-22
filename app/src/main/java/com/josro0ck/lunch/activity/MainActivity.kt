package com.josro0ck.lunch.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.IdlingResource
import com.josro0ck.lunch.adapter.IngredientListAdapter
import com.josro0ck.lunch.model.Ingredient
import com.josro0ck.lunch.viewModel.IngredientViewModel
import kotlinx.android.synthetic.main.activity_main.*
import com.josro0ck.lunch.R
import com.josro0ck.lunch.espresso.RecyclerIdlingResource
import com.josro0ck.lunch.recycler.NotifyingLinearLayoutManager




private const val NEW_INGREDIENT = 1
private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private val mIdlingRes = RecyclerIdlingResource()

    private lateinit var ingredientViewModel: IngredientViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //RecyclerViewInit
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = IngredientListAdapter(this)
        recyclerView.adapter = adapter
        mIdlingRes.setIdleState(false)
        val layoutManager = NotifyingLinearLayoutManager(this)
        layoutManager.mCallback = object : NotifyingLinearLayoutManager.OnLayoutCompleteCallback {
            override fun onLayoutComplete() {
                // here we know that the view has been updated.
                // now you can execute your code here
                mIdlingRes.setIdleState(true)
            }
        }
        recyclerView.layoutManager = layoutManager
        //ViewModelInit
        ingredientViewModel = ViewModelProvider(this).get(IngredientViewModel::class.java)
        ingredientViewModel.allIngredients.observe(this, Observer {
            Log.d(TAG, "ingredientViewModel onUpdate")
            it?.let { adapter.setIngredients(it) }
        })
        //FloatingActionButto init
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddIngredientActivity::class.java)
            startActivityForResult(intent, NEW_INGREDIENT)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_INGREDIENT && resultCode == Activity.RESULT_OK) {
            data?.let {
                val ingredient = it.getParcelableExtra<Ingredient>(AddIngredientActivity.EXTRA_NEW_INGREDIENT)
                ingredientViewModel.insert(ingredient)
            }
        } else {
            Toast.makeText(
                this@MainActivity,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }


    fun getIdlingRes(): IdlingResource {
        return mIdlingRes
    }
}
