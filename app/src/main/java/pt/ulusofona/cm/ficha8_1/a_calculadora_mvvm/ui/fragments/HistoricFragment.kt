package pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import butterknife.OnClick
import kotlinx.android.synthetic.main.fragment_historic.*
import pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.R
import pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.ui.viewmodels.CalculatorViewModel

/**
 * A simple [Fragment] subclass.
 */
class HistoricFragment : Fragment() {

    private lateinit var viewModel: CalculatorViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_historic, container, false)
        viewModel = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        historic_page.layoutManager = LinearLayoutManager(this.context)
        historic_page.adapter = viewModel.setAdapter(
            activity as Context,
            R.layout.fragment_historic
        )
    }

    @OnClick(R.id.button_back)
    fun backToCalculatorFragment(view: View) {
        viewModel.backToCalculatorFragment(activity)
        viewModel.makeToast(activity as Context, "Back")
    }

}
