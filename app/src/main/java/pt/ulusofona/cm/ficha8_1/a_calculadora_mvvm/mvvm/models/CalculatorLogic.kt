package pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.mvvm.models

import android.app.Activity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.objecthunter.exp4j.ExpressionBuilder
import pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.NavigationManager
import pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.mvvm.database.ListStorage
import pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.objects.Operation

class CalculatorLogic {

    private val storage = ListStorage.getInstance()

    fun insertNumber(display: String, number: String) : String {
        return if (display.isEmpty() || (display == number && number == "0")) {
            number
        } else if (display == "0") {
            number
        } else {
            display + number
        }
    }

    fun insertSymbol(display: String, symbol: String) : String {
        return if (display.isEmpty()){
            if (symbol == "-") {
                symbol
            } else {
                ""
            }
        } else if(
            display.substring(display.length - 1) != "+" &&
            display.substring(display.length - 1) != "-" &&
            display.substring(display.length - 1) != "*" &&
            display.substring(display.length - 1) != "/"
        ){
            display + symbol
        } else {
            display
        }
    }

    fun insertDot(display: String) : String {
        return if (display.isEmpty()) {
            "0."
        } else if (!display.contains(".")) {
            "$display."
        } else {
            display
        }
    }

    fun insertClear() : String {
        return ""
    }

    fun insertDelete(display: String) : String {
        val length = display.length
        return if (length < 2){
            ""
        } else {
            display.substring(0, length - 1)
        }
    }

    fun preformOperation(expression: String) : Double {
        val expressionBuilder = ExpressionBuilder(expression).build()
        val result = expressionBuilder.evaluate()
        CoroutineScope(Dispatchers.IO).launch {
            storage.insert(
                Operation(
                    expression,
                    result
                )
            )
        }
        return result
    }

    fun getHistoric(): List<Operation> {
        return storage.getAll()
    }

}