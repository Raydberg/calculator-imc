package com.calculator_imc.view

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.calculator_imc.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import org.w3c.dom.Text

class ImcCalculatorActivity : AppCompatActivity() {
    private var isMaleSelected: Boolean = true;
    private var isFemaleSelected: Boolean = false;
    private var currentWeight: Int = 60;
    private var currentAge: Int = 10;
    private var currentHeight: Int = 70;
    private var currentResult: Double = 0.0;

    // lateinit -> Se inicia cuando yo le diga , ya que esta afuera
    private lateinit var viewMale: CardView;
    private lateinit var viewFemale: CardView;
    private lateinit var tvHeight: TextView;
    private lateinit var rsHeight: RangeSlider;
    private lateinit var btnSubtractWeight: FloatingActionButton;
    private lateinit var btnPlusWeight: FloatingActionButton;
    private lateinit var tvWeight: TextView
    private lateinit var btnSubtractAge: FloatingActionButton
    private lateinit var btnPlusAge: FloatingActionButton;
    private lateinit var tvAge: TextView;
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc_calculator)
        initComponent()
        initListeners()
        initUi()

    }

    private fun initUi() {
        setWeight()
        setAge()
        setResult()
        setGenderColor()
    }

    private fun initComponent() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        btnPlusWeight = findViewById(R.id.btnPlusWeight)
        btnSubtractWeight = findViewById(R.id.btnSubtractWeight)
        tvWeight = findViewById(R.id.tvWeight)
        btnSubtractAge = findViewById(R.id.btnSubtractAge)
        btnPlusAge = findViewById(R.id.btnPlusAge)
        tvAge = findViewById(R.id.tvAge)
        btnCalculate = findViewById(R.id.btnCalculate)
        tvResult = findViewById(R.id.tvResult)
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
            currentWeight = df.format(value).toInt()
            tvHeight.text = "$currentWeight cm";
        }
        btnSubtractWeight.setOnClickListener {
            currentWeight -= 1
            setWeight()

        }
        btnPlusWeight.setOnClickListener {
            currentWeight += 1;
            setWeight()

        }

        btnPlusAge.setOnClickListener {
            currentAge += 1
            setAge()
        }
        btnSubtractAge.setOnClickListener {
            currentAge -= 1;
            setAge();
        }
        btnCalculate.setOnClickListener {
            calculateIMC()
        }

    }

    private fun calculateIMC() {
        val df = DecimalFormat("#.##")
        currentResult =
            df.format(currentWeight / (currentHeight.toDouble() / 100 * currentHeight.toDouble() / 100))
                .toDouble()

        setResult()
    }

    private fun setResult() {
        tvResult.text = currentResult.toString()
    }

    private fun setWeight() {
        tvWeight.text = currentWeight.toString()
    }

    private fun setAge() {
        tvAge.text = currentAge.toString();
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