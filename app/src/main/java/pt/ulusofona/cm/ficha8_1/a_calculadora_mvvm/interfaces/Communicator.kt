package pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.interfaces

import pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.objects.Operation

interface Communicator {
    fun passDataComm(list: ArrayList<Operation>)
}