package pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.mvvm.views.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.fragment_historic.*
import pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.R
import pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.adapters.HistoricAdapter
import pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.mvvm.viewmodels.CalculatorViewModel

/**
 * A simple [Fragment] subclass.
 */
class HistoricFragment : Fragment() {

    /*override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        historic = savedInstanceState?.getParcelableArrayList<Operation>(HISTORY_KEY)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        updateHistoric()

        val view = inflater.inflate(
            R.layout.fragment_historic,
            container,
            false
        )

        historic = arguments?.getParcelableArrayList<Operation>(
            HISTORY_KEY
        )!!

//        savedInstanceState?.getParcelableArrayList<Operation>(HISTORY_KEY)

//        val bundle:Bundle = Intent().getBundleExtra(HISTORY_KEY)!!
//        historic = bundle.getParcelableArrayList<Operation>(HISTORY_KEY)!!
//
//        historic = savedInstanceState?.getParcelableArrayList<Operation>(HISTORY_KEY) as ArrayList<Operation>

        ButterKnife.bind(
            this,
            view
        )
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val operations = activity?.intent!!.getParcelableArrayListExtra<Operation>(EXTRA_HISTORIC_LIST)

//        historic_page.layoutManager = LinearLayoutManager(activity as Context)

//        historic = savedInstanceState?.getParcelableArrayList<Operation>(HISTORY_KEY) as ArrayList<Operation>

        historic_page.adapter =
            HistoricAdapter(
                activity as Context,
                R.layout.historic_page,
//            operations
//            mutableListOf(Operation("5+2", 20.0))
                historic
            )
    }

    @OnClick(
        R.id.button_back
    )
    fun onClickSendBack(view: View) {
        makeToast("back ")
        NavigationManager.goToCalculatorFragment(
            activity?.supportFragmentManager!!
        )
    }

    private fun makeToast(text: String) {
        Toast.makeText(activity as Context, text +
                "${Calendar.getInstance().get(Calendar.HOUR_OF_DAY)}:" +
                "${Calendar.getInstance().get(Calendar.MINUTE)}:" +
                "${Calendar.getInstance().get(Calendar.SECOND)}",
            Toast.LENGTH_SHORT).show()
    }*/

    private lateinit var viewModel: CalculatorViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_historic, container, false)
        viewModel = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)
        ButterKnife.bind(this, view)
        historic_page.adapter = HistoricAdapter(
            activity as Context,
            R.layout.historic_page,
            viewModel.getHistoric().toMutableList()
        )
        return view
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        historic_page.layoutManager = LinearLayoutManager(this.context)
//        historic_page.adapter = HistoricAdapter(
//            activity as Context,
//            R.layout.historic_page,
//            viewModel.getHistoric().toMutableList()
//        )
//    }

}
