package com.example.calculator1

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import org.mozilla.javascript.Context

class MainActivity : AppCompatActivity(), View.OnClickListener {
	private var resultTv: TextView? = null
	private var solutionTv: TextView? = null

	private var buttonC: MaterialButton? = null
	private var buttonBracketOpen: MaterialButton? = null
	private var buttonBracketClose: MaterialButton? = null

	private var buttonDivide: MaterialButton? = null
	private var buttonMultiply: MaterialButton? = null
	private var buttonPlus: MaterialButton? = null
	private var buttonMinus: MaterialButton? = null
	private var buttonEquals: MaterialButton? = null

	private var button0: MaterialButton? = null
	private var button1: MaterialButton? = null
	private var button2: MaterialButton? = null
	private var button3: MaterialButton? = null
	private var button4: MaterialButton? = null
	private var button5: MaterialButton? = null
	private var button6: MaterialButton? = null
	private var button7: MaterialButton? = null
	private var button8: MaterialButton? = null
	private var button9: MaterialButton? = null

	private var buttonAC: MaterialButton? = null
	private var buttonDot: MaterialButton? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		resultTv = findViewById(R.id.result_tv)
		solutionTv = findViewById(R.id.solution_tv)

		buttonC = findViewById(R.id.button_c)
		buttonBracketOpen = findViewById(R.id.button_open_bracket)
		buttonBracketClose = findViewById(R.id.button_close_bracket)

		buttonDivide = findViewById(R.id.button_divide)
		buttonMultiply = findViewById(R.id.button_multiplier)
		buttonPlus = findViewById(R.id.button_plus)
		buttonMinus = findViewById(R.id.button_minus)
		buttonEquals = findViewById(R.id.button_equals)

		button0 = findViewById(R.id.button_0)
		button1 = findViewById(R.id.button_1)
		button2 = findViewById(R.id.button_2)
		button3 = findViewById(R.id.button_3)
		button4 = findViewById(R.id.button_4)
		button5 = findViewById(R.id.button_5)
		button6 = findViewById(R.id.button_6)
		button7 = findViewById(R.id.button_7)
		button8 = findViewById(R.id.button_8)
		button9 = findViewById(R.id.button_9)

		buttonAC = findViewById(R.id.button_AC)
		buttonDot = findViewById(R.id.button_dot)

		assignId(buttonC!!, R.id.button_c)
		assignId(buttonBracketOpen!!, R.id.button_open_bracket)
		assignId(buttonBracketClose!!, R.id.button_close_bracket)

		assignId(buttonDivide!!, R.id.button_divide)
		assignId(buttonMultiply!!, R.id.button_multiplier)
		assignId(buttonPlus!!, R.id.button_plus)
		assignId(buttonMinus!!, R.id.button_minus)
		assignId(buttonEquals!!, R.id.button_equals)

		assignId(button0!!, R.id.button_0)
		assignId(button1!!, R.id.button_1)
		assignId(button2!!, R.id.button_2)
		assignId(button3!!, R.id.button_3)
		assignId(button4!!, R.id.button_4)
		assignId(button5!!, R.id.button_5)
		assignId(button6!!, R.id.button_6)
		assignId(button7!!, R.id.button_7)
		assignId(button8!!, R.id.button_8)
		assignId(button9!!, R.id.button_9)

		assignId(buttonAC!!, R.id.button_AC)
		assignId(buttonDot!!, R.id.button_dot)
	}

	private fun assignId(btn: MaterialButton, id: Int) {
		btn.id = id
		btn.setOnClickListener(this)
	}

	override fun onClick(v: View?) {
		if (v is MaterialButton) {
			val buttonText = v.text.toString()
			var dataToCalculate = solutionTv?.text.toString() ?: ""

			if (buttonText == "AC") {
				solutionTv?.text = ""
				resultTv?.text = ""
				return
			}
			if (buttonText == "=") {
				val finalResult = getResult(dataToCalculate)
				resultTv?.text = finalResult
				return
			}
			if (buttonText == "C") {
				dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length - 1)
			} else {
				dataToCalculate += buttonText
			}
			solutionTv?.text = dataToCalculate
		}
	}

	private fun getResult(data: String): String {
		return try {
			val context = Context.enter()
			context.optimizationLevel = -1
			val scriptable = context.initSafeStandardObjects()
			val finalResult = context.evaluateString(scriptable, data, "JavaScript", 1, null).toString()
			if(finalResult.endsWith(".0")){
				finalResult.replace(".0","")
			}else{
				finalResult
			}
			return finalResult
		} catch (e: Exception) {
			e.printStackTrace()
			"Err"
		}
	}
}
