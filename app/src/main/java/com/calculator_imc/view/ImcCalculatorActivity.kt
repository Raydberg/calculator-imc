package com.calculator_imc.view

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.calculator_imc.R
import com.google.android.material.slider.RangeSlider

class ImcCalculatorActivity : AppCompatActivity() {
    private var isMaleSelected: Boolean = true;
    private var isFemaleSelected: Boolean = false;

    // lateinit -> Se inicia cuando yo le diga , ya que esta afuera
    private lateinit var viewMale: CardView;
    private lateinit var viewFemale: CardView;
    private lateinit var tvHeight: TextView;
    private lateinit var rsHeight: RangeSlider;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc_calculator)
        initComponent()
        initListeners()
        initUi()

    }

    private fun initUi() {
        setGenderColor()
    }

    private fun initComponent() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
    }

    private fun initListeners() {
        viewMale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        viewFemale.setOnClickListener {
            changeGender()
            setGenderColor()
        }

//        Para el slider
        rsHeight.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##")
            val result = df.format(value)
            tvHeight.text = "$result cm";
        }

    }

    private fun changeGender() {
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isMaleSelected
    }

    private fun setGenderColor() {
        viewFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
    }

    private fun getBackgroundColor(isSelectedComponent: Boolean): Int {
        val colorReference = if (isSelectedComponent) {
            R.color.background_component_select
        } else {
            R.color.background_component
        }
        return ContextCompat.getColor(this, colorReference)
    }

}