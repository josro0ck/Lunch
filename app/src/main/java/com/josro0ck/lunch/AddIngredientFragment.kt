package com.josro0ck.lunch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import com.josro0ck.lunch.enum.RatioScale
import com.josro0ck.lunch.repository.Catalog
import kotlinx.android.synthetic.main.add_ingredient_layout.*
import com.josro0ck.lunch.ui.EditTextDatePicker
import java.text.SimpleDateFormat
import java.util.*


class AddIngredientFragment : Fragment() {

    companion object {
        fun newInstance(): AddIngredientFragment {
            return AddIngredientFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_ingredient_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapters()
        initViews()
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        purchaesTodayCheckBox.setOnClickListener {
            purchasedTodayEditText.isEnabled = !purchaesTodayCheckBox.isChecked
            if(purchaesTodayCheckBox.isChecked)
                setTodayDateOfPurchase()
        }
    }

    private fun initViews() {
        purchaesTodayCheckBox.isChecked = true
        purchasedTodayEditText.isEnabled = false
        setTodayDateOfPurchase()
    }

    private fun setTodayDateOfPurchase() {
        val date_n = SimpleDateFormat("MM/dd/YYYY", Locale.getDefault()).format(Date())
        purchasedTodayEditText.setText(date_n)
    }

    private fun setAdapters() {
        ingredientName.setAdapter(
            ArrayAdapter<String>(
                activity,
                android.R.layout.simple_dropdown_item_1line,
                Catalog.myCatalog
            )
        )
        rationaleSpinner.setAdapter(
            ArrayAdapter<RatioScale>(
                activity,
                android.R.layout.simple_spinner_item,
                RatioScale.values()
            )
        )
        EditTextDatePicker(activity, purchasedTodayEditText)
    }
}