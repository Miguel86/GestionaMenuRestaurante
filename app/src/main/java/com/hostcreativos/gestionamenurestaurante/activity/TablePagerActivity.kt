package com.hostcreativos.gestionamenurestaurante.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import com.hostcreativos.gestionamenurestaurante.R
import com.hostcreativos.gestionamenurestaurante.fragment.TablePagerFragment
import com.hostcreativos.gestionamenurestaurante.fragment.DishFragment
import com.hostcreativos.gestionamenurestaurante.model.Table
import com.hostcreativos.gestionamenurestaurante.model.Tables
import kotlinx.android.synthetic.main.activity_table_pager.*


class TablePagerActivity : AppCompatActivity(), DishFragment.OnButtonClickedListener{


    companion object {
        val EXTRA_CITY = "EXTRA_CITY"

        fun intent(context: Context, cityIndex: Int): Intent {
            val intent = Intent(context, TablePagerActivity::class.java)
            intent.putExtra(EXTRA_CITY, cityIndex)

            return intent
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_pager)

        setSupportActionBar(toolbar) //Para mostrar la toolbar, como actionbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val initialCityIndex = intent.getIntExtra(EXTRA_CITY, 0)

        //Creo, si hace falta, el TablePagerFragment pasándole la mesa inicial
        if(supportFragmentManager.findFragmentById(R.id.view_pager_fragment) == null){
            //Metemos el fragment

            val fragment = TablePagerFragment.newInstance(initialCityIndex)
            supportFragmentManager.beginTransaction()
                    .add(R.id.view_pager_fragment, fragment)
                    .commit()
        }
    }



    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        android.R.id.home -> { //Nos han llamado a la flecha de back
            finish()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }


    override fun onButtonClicked(table: Table?) {
        //Estamos en una interfaz donde sólo hay la lista. Lanzamos la actividad TablePagerActivity
        val intent = DishesActivity.intent(this, Tables.getIndex(table))
        startActivity(intent)

    }
}

