package pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.ui.models

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.objecthunter.exp4j.ExpressionBuilder
import pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.data.local.list.ListStorage
import pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.data.local.entities.Operation
import pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.data.local.room.dao.OperationDao
import java.util.ArrayList

class CalculatorLogic(private val storage: OperationDao) {

//    private val storage = ListStorage.getInstance()

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
        val expressionBuilder = if (expression == "") {
            ExpressionBuilder("0+0").build()
        } else {
            ExpressionBuilder(expression).build()
        }
        val result = expressionBuilder.evaluate()
        CoroutineScope(Dispatchers.IO).launch {
            Log.e(this::class.java.simpleName, "Inserting")
            storage.insert(
                Operation(
                    expression,
                    result
                )
            )
        }
        Log.e(this::class.java.simpleName, "Returning result")
        return result
    }

    fun getHistoric(): List<Operation> {
//        declara a variavel storaData
        var storageData: List<Operation> = arrayListOf()
        CoroutineScope(Dispatchers.IO).launch {
//            debug (esta como error para puder identificar melhor)
            Log.e(this::class.java.simpleName, "Inside the thread")
//            atualiza o valor da variavel com o a lista de operações existentes na base de dados
            storageData = storage.getAll()
//            outro debug
            Log.e(this::class.java.simpleName, "StorageData inside the thread: $storageData")
        }
//        outro debug
        Log.e(this::class.java.simpleName, "StorageData: $storageData")
//        retorna o valor da lista storageData como lista
        return storageData.toList()
    }

}