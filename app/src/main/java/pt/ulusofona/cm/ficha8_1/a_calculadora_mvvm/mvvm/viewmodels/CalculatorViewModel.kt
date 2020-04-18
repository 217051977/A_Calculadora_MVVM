package pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.mvvm.viewmodels

import androidx.lifecycle.ViewModel
import pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.interfaces.OnDisplayChanged
import pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.mvvm.models.CalculatorLogic
import pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.objects.Operation

class CalculatorViewModel: ViewModel() {

    private val calculatorLogic = CalculatorLogic()
    private var listener: OnDisplayChanged? = null
    var display = ""

    private fun notifyOnDisplayChanged() {
        listener?.onDisplayChanged(display)
    }

    fun registerListener(listener: OnDisplayChanged) {
        this.listener = listener
        listener?.onDisplayChanged(display)
    }

    fun unregisterListener() {
        listener = null
    }

    fun onClickNumber(number: String) : String {
        display = calculatorLogic.insertNumber(display, number)
        notifyOnDisplayChanged()
        return display
    }

    fun onClickDot() : String {
        display = calculatorLogic.insertDot(display)
        notifyOnDisplayChanged()
        return display
    }

    fun onClickClear() : String {
        display = calculatorLogic.insertClear()
        notifyOnDisplayChanged()
        return display
    }

    fun onClickDelete() : String {
        display = calculatorLogic.insertDelete(display)
        notifyOnDisplayChanged()
        return display
    }

    fun onClickSymbol(symbol: String) : String {
        display = calculatorLogic.insertSymbol(display, symbol)
        notifyOnDisplayChanged()
        return display
    }

    fun onClickEquals() : String {
        display = calculatorLogic.preformOperation(display).toString()
        notifyOnDisplayChanged()
        return display
    }

    fun getHistoric(): List<Operation> {
        val historic = calculatorLogic.getHistoric()
        notifyOnDisplayChanged()
        return historic
    }

}