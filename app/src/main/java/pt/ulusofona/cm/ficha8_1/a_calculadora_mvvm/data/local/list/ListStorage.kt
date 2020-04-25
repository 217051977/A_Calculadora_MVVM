package pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.data.local.list

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.data.local.entities.Operation

class ListStorage private constructor() {

//    iniciliza a variavel que irá servir para guardar as operações feitas
    private val storage = mutableListOf<Operation>()

//    cria um objeto de forma a puder inicialiar esta class
    companion object {

//      cira uma variavel instancia
        private var instance: ListStorage? = null

//      função que devolverá a instancia
        fun getInstance(): ListStorage {
            synchronized(this) {
//                verifica se ainda nao foi criada uma instancia
                if (instance == null) {
//                    cria uma instancia
                    instance =
                        ListStorage()
                }
//                retorna a instancia
                return instance as ListStorage
            }
        }

    }

    suspend fun insert(operation: Operation) {
        withContext(Dispatchers.IO) {
//            espera 30s
            Thread.sleep(30000)
//            adiciona a operação a variavel storage
            storage.add(operation)
//            debug
            Log.e(ListStorage::class.java.simpleName, "Data: $storage")
        }
    }

    fun getAll(): List<Operation> {
//        debug
        Log.e(ListStorage::class.java.simpleName, "Data: $storage")
//        retorna o conteudo da variavel storage em formate lista
        return  storage.toList()
    }

}