package pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Operation(val expression: String, val result: Double) {

    @PrimaryKey
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