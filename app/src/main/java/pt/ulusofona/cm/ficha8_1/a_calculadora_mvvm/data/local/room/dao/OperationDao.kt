package pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.data.local.entities.Operation

@Dao
interface OperationDao {

    @Insert
    suspend fun insert(operation: Operation)

    @Query("select * from operation")
    suspend fun getAll(): List<Operation>

}