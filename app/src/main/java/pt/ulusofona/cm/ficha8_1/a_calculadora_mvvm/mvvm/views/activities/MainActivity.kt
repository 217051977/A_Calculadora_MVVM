package pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.mvvm.views.activities

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.NavigationManager
import pt.ulusofona.cm.ficha8_1.a_calculadora_mvvm.R

private val TAG = MainActivity::class.java.simpleName
private var HISTORY_KEY = "history"

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener/*,
    Communicator*/ {

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_calculator -> NavigationManager.goToCalculatorFragment(
                supportFragmentManager
            )
            R.id.nav_history -> {
                NavigationManager.goToHistoricFragment(
                    supportFragmentManager
                )
            }
            R.id.nav_logout -> finish()
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

//    override fun passDataComm(list: ArrayList<Operation>) {
//        val bundle = Bundle()
//        bundle.putParcelableArrayList(HISTORY_KEY, list)
//
//        val transaction = this.supportFragmentManager.beginTransaction()
//        val frag2 =
//            HistoricFragment()
//        frag2.arguments = bundle
//
//        transaction.replace(R.id.frame, frag2)
//        transaction.addToBackStack(null)
//        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//        transaction.commit()
//    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        }
        super.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "o método \"onCreate\" foi invocado")
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setupDrawerMenu()
        NavigationManager.goToCalculatorFragment(
            supportFragmentManager
        )

    }

    private fun setupDrawerMenu() {

        val toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        )
        nav_drawer.setNavigationItemSelectedListener(this)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

    }

}