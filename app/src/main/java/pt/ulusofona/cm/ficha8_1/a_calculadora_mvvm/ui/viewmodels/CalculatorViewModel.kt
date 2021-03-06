package pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.ui.viewmodels

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.ui.utils.NavigationManager
import pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.ui.adapters.HistoricAdapter
import pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.ui.listeners.OnDisplayChanged
import pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.ui.models.CalculatorLogic
import pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.data.local.entities.Operation
import pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.data.local.room.CalculatorDatabase
import java.util.*

class CalculatorViewModel(application: Application): AndroidViewModel(application) {

    private val storage = CalculatorDatabase.getInstance(application as Context).operationDao()
    private val calculatorLogic = CalculatorLogic(storage)

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

    fun backToCalculatorFragment(activity: FragmentActivity?) {
        NavigationManager.goToCalculatorFragment(
            activity?.supportFragmentManager!!
        )
    }

    fun setAdapter(context: Context, widget: Int): HistoricAdapter{
        return HistoricAdapter(
            context,
            widget,
            calculatorLogic.getHistoric().toMutableList()
        )
    }

    fun makeToast(context: Context, text: String) {
        Toast.makeText(context, "$text at: " +
                "${Calendar.getInstance().get(Calendar.HOUR_OF_DAY)}:" +
                "${Calendar.getInstance().get(Calendar.MINUTE)}:" +
                "${Calendar.getInstance().get(Calendar.SECOND)}",
            Toast.LENGTH_SHORT).show()
    }

}