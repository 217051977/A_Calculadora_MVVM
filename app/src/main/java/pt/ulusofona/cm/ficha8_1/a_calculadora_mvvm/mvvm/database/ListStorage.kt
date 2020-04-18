package pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.mvvm.database

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.objects.Operation

class ListStorage private constructor() {

    private val storage = mutableListOf<Operation>()

    companion object {

        private var instance: ListStorage? = null

        fun getInstance(): ListStorage {
            synchronized(this) {
                instance = ListStorage()
            }
            return instance as ListStorage
        }

    }

    suspend fun insert(operation: Operation) {
        withContext(Dispatchers.IO) {
            Thread.sleep(30000)
            storage.add(operation)
            Log.e(ListStorage::class.java.simpleName, "Data: $storage")
        }
    }

    fun getAll(): List<Operation> {
        Log.e(ListStorage::class.java.simpleName, "Data: $storage")
        return  storage.toList()
    }

}