package pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.navigators

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.R
import pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.mvvm.views.fragments.CalculatorFragment
import pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.mvvm.views.fragments.HistoricFragment

abstract class NavigationManager {

    companion object {

        private  fun placeFragment(
            fm: FragmentManager,
            fragment: Fragment
        ) {

            val transition = fm.beginTransaction()
            transition.replace(
                R.id.frame,
                fragment
            )
            transition.addToBackStack(null)
            transition.commit()

        }

        fun goToCalculatorFragment(fm: FragmentManager) {
            placeFragment(
                fm,
                CalculatorFragment()
            )
        }

        fun goToHistoricFragment(fm: FragmentManager) {
            placeFragment(
                fm,
                HistoricFragment()
            )
        }

    }

}