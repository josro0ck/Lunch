package com.josro0ck.lunch.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.josro0ck.lunch.R
import com.josro0ck.lunch.enum.RatioScale
import com.josro0ck.lunch.extensions.US_DATE_TEMPLATE
import com.josro0ck.lunch.extensions.toDate
import com.josro0ck.lunch.model.Ingredient
import kotlinx.android.synthetic.main.add_ingredient_layout.*
import com.josro0ck.lunch.ui.EditTextDatePicker
import java.text.SimpleDateFormat
import java.util.*


class AddIngredientActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NEW_INGREDIENT = "newIngredientParcelable"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_ingredient_layout)
        setAdapters()
        initViews()
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        purchaesTodayCheckBox.setOnClickListener {
            purchasedDateEditText.isEnabled = !purchaesTodayCheckBox.isChecked
            if (purchaesTodayCheckBox.isChecked)
                setTodayDateOfPurchase()
        }
        addIngredientButton.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(ingredientName.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val newIngredient = Ingredient(
                    ingredientName.text.toString()
                    , purchasedDateEditText.text.toString().toDate(US_DATE_TEMPLATE)
                    , 1
                    , RatioScale.valueOf(rationaleSpinner.selectedItem.toString())
                )
                replyIntent.putExtra(EXTRA_NEW_INGREDIENT, newIngredient)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    private fun initViews() {
        purchaesTodayCheckBox.isChecked = true
        purchasedDateEditText.isEnabled = false
        setTodayDateOfPurchase()
    }

    private fun setTodayDateOfPurchase() {
        val date_n = SimpleDateFormat(US_DATE_TEMPLATE, Locale.getDefault()).format(Date())
        purchasedDateEditText.setText(date_n)
    }

    private fun setAdapters() {
        rationaleSpinner.setAdapter(
            ArrayAdapter<RatioScale>(
                this@AddIngredientActivity,
                android.R.layout.simple_spinner_item,
                RatioScale.values()
            )
        )
        EditTextDatePicker(this@AddIngredientActivity, purchasedDateEditText)
    }
}