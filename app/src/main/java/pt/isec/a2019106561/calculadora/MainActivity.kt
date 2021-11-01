package pt.isec.a2019106561.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import pt.isec.a2019106561.calculadora.databinding.ActivityMainBinding
import java.util.*
import kotlin.math.round
import kotlin.math.roundToLong
import kotlin.math.sign
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    var valueStr: String = ""
    var mathEq: String = "0p"
    var valueBuffer: Float = 0F
    val clickedColor : Long = 4290121151
    val originalColor : Long = 4284094618
    val buttonArray: ArrayList<Button> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        buttonArray.addAll(listOf(binding.btn0, binding.btn1, binding.btn2, binding.btn3, binding.btn4, binding.btn5, binding.btn6, binding.btn7, binding.btn8, binding.btn9, binding.btn0,
            binding.btnComma, binding.btnSum, binding.btnSub, binding.btnMul, binding.btnDiv, binding.btnPlusMinus, binding.btnAc, binding.btnErase, binding.btnEq))
    }

    fun disableAllButtonsExeptAC(){
        for (b in buttonArray)
            b.isEnabled = false
        binding.btnAc.isEnabled = true
    }

    fun enableAllButtons(){
        for (b in buttonArray)
            b.isEnabled = true
    }

    fun notClickableOpertators(){
        binding.btnSum.isClickable = false
        binding.btnSub.isClickable = false
        binding.btnMul.isClickable = false
        binding.btnDiv.isClickable = false
        binding.btnEq.isClickable = false
        binding.btnComma.isClickable = false
    }

    fun clickableOpertators(){
        binding.btnSum.isClickable = true
        binding.btnSub.isClickable = true
        binding.btnMul.isClickable = true
        binding.btnDiv.isClickable = true
        binding.btnEq.isClickable = true
        binding.btnComma.isClickable = true
        binding.btnSum.setBackgroundColor(originalColor.toInt())
        binding.btnSub.setBackgroundColor(originalColor.toInt())
        binding.btnMul.setBackgroundColor(originalColor.toInt())
        binding.btnDiv.setBackgroundColor(originalColor.toInt())
    }

    fun roundString(num: Float): String {
        var str : String = num.toString()
        if(str.last().toString() == "0")
            str = str.replace(".0", "")
        return str
    }

    fun solveEq(eq: String): Float {
        var solution: Float = 0F
        var bufferFloat: Float = 0F
        var bufferStr: String
        var firstNum: Boolean = true
        val st = StringTokenizer(eq.toString(), "psmd", true)
        while(st.hasMoreTokens()){
            if(firstNum){ //First Number
                solution = st.nextToken().toFloat()
                firstNum = false
            }
            bufferStr = st.nextToken()
            if(bufferStr == "p" || bufferStr == "s" || bufferStr == "m" || bufferStr == "d") {
                if(!st.hasMoreTokens())
                    return solution
                bufferFloat = st.nextToken().toFloat()
                when (bufferStr) {
                    "p" -> solution = solution + bufferFloat
                    "s" -> solution = solution - bufferFloat
                    "m" -> solution = solution * bufferFloat
                    "d" -> solution = solution / bufferFloat
                }
            }
        }
        return solution
    }

    fun onProcessButtons(view: android.view.View) {
        val btn = view as Button
        if(binding.textMsg.text.toString().equals("NaN") || binding.textMsg.text.toString().equals("Infinity"))
            disableAllButtonsExeptAC()
        if(binding.textMsg.text.toString().equals("0"))
            valueStr = ""
        when (btn) {
            binding.btn1 -> {
                valueStr += "1"
                binding.textMsg.text = valueStr
                clickableOpertators()
            }
            binding.btn2 -> {
                valueStr += "2"
                binding.textMsg.text = valueStr
                clickableOpertators()
            }
            binding.btn3 -> {
                valueStr += "3"
                binding.textMsg.text = valueStr
                clickableOpertators()
            }
            binding.btn4 -> {
                valueStr += "4"
                binding.textMsg.text = valueStr
                clickableOpertators()
            }
            binding.btn5 -> {
                valueStr += "5"
                binding.textMsg.text = valueStr
                clickableOpertators()
            }
            binding.btn6 -> {
                valueStr += "6"
                binding.textMsg.text = valueStr
                clickableOpertators()
            }
            binding.btn7 -> {
                valueStr += "7"
                binding.textMsg.text = valueStr
                clickableOpertators()
            }
            binding.btn8 -> {
                valueStr += "8"
                binding.textMsg.text = valueStr
                clickableOpertators()
            }
            binding.btn9 -> {
                valueStr += "9"
                binding.textMsg.text = valueStr
                clickableOpertators()
            }
            binding.btn0 -> {
                if(!binding.textMsg.text.toString().equals("0")) {
                    valueStr += "0"
                    binding.textMsg.text = valueStr
                }
                clickableOpertators()
            }
            binding.btnSum -> {
                mathEq = mathEq + binding.textMsg.text.toString() + "p"
                binding.textMsg.text = roundString(solveEq(mathEq))
                if(binding.textMsg.text.toString().equals("NaN") || binding.textMsg.text.toString().equals("Infinity"))
                    disableAllButtonsExeptAC()
                notClickableOpertators()
                binding.btnSum.setBackgroundColor(clickedColor.toInt())
                valueStr = ""
            }
            binding.btnSub -> {
                mathEq = mathEq + binding.textMsg.text.toString() + "s"
                binding.textMsg.text = roundString(solveEq(mathEq))
                if(binding.textMsg.text.toString().equals("NaN") || binding.textMsg.text.toString().equals("Infinity"))
                    disableAllButtonsExeptAC()
                notClickableOpertators()
                binding.btnSub.setBackgroundColor(clickedColor.toInt())
                valueStr = ""
            }
            binding.btnMul -> {
                mathEq = mathEq + binding.textMsg.text.toString() + "m"
                binding.textMsg.text = roundString(solveEq(mathEq))
                if(binding.textMsg.text.toString().equals("NaN") || binding.textMsg.text.toString().equals("Infinity"))
                    disableAllButtonsExeptAC()
                notClickableOpertators()
                binding.btnMul.setBackgroundColor(clickedColor.toInt())
                valueStr = ""
            }
            binding.btnDiv -> {
                mathEq = mathEq + binding.textMsg.text.toString() + "d"
                binding.textMsg.text = roundString(solveEq(mathEq))
                if(binding.textMsg.text.toString().equals("NaN") || binding.textMsg.text.toString().equals("Infinity"))
                    disableAllButtonsExeptAC()
                notClickableOpertators()
                binding.btnDiv.setBackgroundColor(clickedColor.toInt())
                valueStr = ""
            }
            binding.btnPlusMinus -> {
                valueStr = binding.textMsg.text.toString()
                if(!roundString(valueStr.toFloat()).equals("0")) {
                    valueBuffer = valueStr.toFloat()
                    valueBuffer = valueBuffer * -1
                    binding.textMsg.text = roundString(valueBuffer)
                }
                else{
                    binding.textMsg.text = "0"
                }
                valueStr = ""
            }
            binding.btnComma -> {
                if(binding.textMsg.text.equals("0") || binding.textMsg.text.equals("0.0")) {
                    valueStr = "0."
                    binding.textMsg.text = valueStr
                }
                else{
                    valueStr = binding.textMsg.text.toString()
                    if(!valueStr.contains(".")) {
                        valueStr += "."
                        binding.textMsg.text = valueStr
                    }
                }
            }
            binding.btnErase -> {
                valueStr = binding.textMsg.text.toString()
                if(valueStr.length > 1) {
                    if(valueStr.equals("NaN") || valueStr.equals("Infinity")) {
                        binding.textMsg.text = "0"
                        valueStr = ""
                    }
                    else {
                        if(valueStr.contains("E", false)){
                            binding.textMsg.text = valueStr
                        }
                        else {
                            valueStr = valueStr.substring(0, valueStr.length - 1);
                            binding.textMsg.text = valueStr
                        }
                    }
                }
                else {
                    binding.textMsg.text = "0"
                    valueStr = ""
                }
            }
            binding.btnAc -> {
                enableAllButtons()
                clickableOpertators()
                mathEq = ""
                valueStr = ""
                binding.textMsg.text = "0"
            }

            binding.btnEq -> {
                if(!mathEq.isEmpty()){
                    mathEq = mathEq + binding.textMsg.text.toString()
                    binding.textMsg.text = roundString(solveEq(mathEq))
                    if(binding.textMsg.text.toString().equals("NaN") || binding.textMsg.text.toString().equals("Infinity"))
                        disableAllButtonsExeptAC()
                    mathEq = "0p"
                }
                valueStr = ""
            }
        }
    }
}