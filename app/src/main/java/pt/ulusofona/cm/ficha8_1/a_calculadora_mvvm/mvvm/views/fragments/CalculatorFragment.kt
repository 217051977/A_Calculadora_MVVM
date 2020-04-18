package pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.mvvm.views.fragments

import android.content.Context
import android.content.res.Configuration
import kotlinx.android.synthetic.main.fragment_calculator.*
import kotlinx.android.synthetic.main.fragment_calculator.view.text_visor

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Optional
import kotlinx.android.synthetic.main.fragment_historic.*
import pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.mvvm.viewmodels.CalculatorViewModel
import pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.R
import pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.adapters.HistoricAdapter
import pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.interfaces.OnDisplayChanged
import java.util.*

private val TAG = CalculatorFragment::class.java.simpleName
//private var lastCal = "0"
//private var aux = ""
//private var hasCalculated = true
//private var historicMode = false
//private var HISTORY_KEY = "history"
//private var historic: ArrayList<Operation> = arrayListOf(Operation("1+1", 2.0))
//const val EXTRA_HISTORIC_LIST = "com.example.acalculator.LIST"

class CalculatorFragment : Fragment(), OnDisplayChanged {

    private lateinit var viewModel: CalculatorViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_calculator, container, false)
        viewModel = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onStart() {
        viewModel.registerListener(this)
        super.onStart()
    }

    override fun onDisplayChanged(value: String?) {
        value.let {
            text_visor.text = it
        }
    }

    override fun onDestroy() {
        viewModel.unregisterListener()
        super.onDestroy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.w(TAG, "Historic: ${viewModel.getHistoric().toMutableList().toString()}")
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            list_historic.layoutManager = LinearLayoutManager(activity as Context)
            list_historic.adapter = HistoricAdapter(
                activity as Context,
                R.layout.historic_page,
                viewModel.getHistoric().toMutableList()
            )
        }
    }

    @Optional
    @OnClick(
        R.id.button_0,
        R.id.button_00,
        R.id.button_1,
        R.id.button_2,
        R.id.button_3,
        R.id.button_4,
        R.id.button_5,
        R.id.button_6,
        R.id.button_7,
        R.id.button_8,
        R.id.button_9
    )
    fun onClickNumber(view: View) {
        text_visor.text = viewModel.onClickNumber(view.tag.toString())
    }

    @OnClick(
        R.id.button_equals
    )
    fun onClickEquals(view: View) {
        text_visor.text = viewModel.onClickEquals()
    }

    /*fun onClickNumber(view: View) {
        val number = view.tag.toString()
        Log.i(TAG, "Click on button $number")
        if (text_visor.text.toString() == "0") {
            if (hasCalculated) {
                hasCalculated = false
            }
            text_visor.text = number
        } else {
            text_visor.append(number)
        }
        aux = text_visor.text.toString()
        makeToast("button_$number.setOnClickListener at ")
    }

    fun onClickEquals(view: View) {
        Log.i(TAG, "Click on button =")
        val textVisorValue = text_visor.text.toString()
        val expression =  ExpressionBuilder(textVisorValue).build().evaluate()
        historic.add(Operation(textVisorValue, expression))
        updateHistoric()
        text_visor.text = expression.toString()
        Log.i(TAG, "The result of the expression is ${text_visor.text}")
        hasCalculated = true
        aux = ""
        makeToast("button_equals.setOnClickListener at ")
    }*/

    @OnClick(
        R.id.button_addiction,
        R.id.button_subtraction,
        R.id.button_multiplier,
        R.id.button_divider
    )
    fun onClickSymbol(view: View) {
        text_visor.text = viewModel.onClickSymbol(view.tag.toString())
        /*val symbol = view.tag.toString()
        Log.i(TAG, "Click on button $symbol")
        text_visor.append(symbol)
        if (hasCalculated) {
            hasCalculated = false
        }
        aux = text_visor.text.toString()
        makeToast("button_addiction.setOnClickListener at ")*/
    }

    @OnClick(
        R.id.button_dot
    )
    fun onClickDot(view: View) {
        Log.i(TAG, "Click on button .")
        text_visor.text = viewModel.onClickDot()
        /*if (!text_visor.text.contains(".")) {
            text_visor.append(".")
            if (hasCalculated) {
                hasCalculated = false
            }
        }
        aux = text_visor.text.toString()*/
        makeToast("button_dot.setOnClickListener at ")
    }

    @OnClick(
        R.id.button_c
    )
    fun onClickClear(view: View) {
        Log.i(TAG, "Click on button C")
        text_visor.text = viewModel.onClickClear()
        makeToast("button_c.setOnClickListener at ")
    }

    @OnClick(
        R.id.button_delete
    )
    fun onClickDelete(view: View) {
        Log.i(TAG, "Click on button \u232B")
        text_visor.text = viewModel.onClickDelete()
        makeToast("button_delete.setOnClickListener at ")
    }

    private fun makeToast(text: String) {
        Toast.makeText(activity as Context, text +
                "${Calendar.getInstance().get(Calendar.HOUR_OF_DAY)}:" +
                "${Calendar.getInstance().get(Calendar.MINUTE)}:" +
                "${Calendar.getInstance().get(Calendar.SECOND)}",
            Toast.LENGTH_SHORT).show()
    }

}
