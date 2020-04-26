package pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.data.local.room

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.data.local.entities.Operation
import pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.data.local.room.dao.OperationDao

@Database(entities = arrayOf(Operation::class), version = 1)
abstract class CalculatorDatabase: RoomDatabase() {

    abstract fun operationDao(): OperationDao

    companion object {

        private var instance: CalculatorDatabase? = null

        fun getInstance(applicationContext: Context): CalculatorDatabase {
            Log.e(this::class.java.simpleName, "Creating a Calculator Database instance")
            synchronized(this) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        applicationContext,
                        CalculatorDatabase::class.java,
                        "calculator_db"
                    ).build()
                }
                return instance as CalculatorDatabase
            }
        }

    }

}