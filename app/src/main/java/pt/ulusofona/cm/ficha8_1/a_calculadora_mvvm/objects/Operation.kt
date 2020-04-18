package pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.objects

import java.util.*

class Operation(val expression: String, val result: Double) {

    var uuid: String = UUID.randomUUID().toString()

//    fun updateList(orientation: Int) {
//
//        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            list_historic.adapter = HistoricAdapter(
//                this,
//                R.layout.item_expression,
////                arrayListOf(
////                    "1+1=2",
////                    "2+3=5"
////                )
//                historic
//            )
//        }
//
//    }

}