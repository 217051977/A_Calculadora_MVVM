package pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.ui.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_expression.view.*
import pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.data.local.entities.Operation

/*
class HistoricAdapter (
    context: Context,
    private val layout: Int,
    items: MutableList<Operation>
) : ArrayAdapter<Operation> (context, layout, items){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(layout, parent, false)
        view.text_expression.text = getItem(position)?.expression
        view.text_result.text = getItem(position)?.result.toString()
        return view
    }

}*/

private val TAG = HistoricAdapter::class.java.simpleName

class HistoricAdapter(
    private val context: Context,
    private val layout: Int,
    private val items: MutableList<Operation>
) : RecyclerView.Adapter<HistoricAdapter.HistoryViewHolder>() {

    class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val expression: TextView = view.text_expression

        val resoult: TextView = view.text_result
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        Log.e(TAG, "Creating view holder")
        return HistoryViewHolder(
            LayoutInflater.from(context).inflate(
                layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        Log.e(TAG, "Binding view holder")
        holder.expression.text = items[position].expression
        holder.resoult.text = items[position].result.toString()
    }

    override fun getItemCount() = items.size

    fun getList() : MutableList<Operation> = items

}
