package com.hostcreativos.gestionamenurestaurante.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import com.hostcreativos.gestionamenurestaurante.R
import com.hostcreativos.gestionamenurestaurante.fragment.DishFragment
import com.hostcreativos.gestionamenurestaurante.fragment.TablePagerFragment
import com.hostcreativos.gestionamenurestaurante.fragment.TablesListFragment
import com.hostcreativos.gestionamenurestaurante.fragment.TablesListFragment.OnTableSelectedListener
import com.hostcreativos.gestionamenurestaurante.model.Table
import com.hostcreativos.gestionamenurestaurante.model.Tables

class TablesActivity : AppCompatActivity(), OnTableSelectedListener, DishFragment.OnButtonClickedListener  {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tables)

        // Comprobamos primero que no tenemos ya añadido el fragment a nuestra jerarquía
        if(findViewById<ViewGroup>(R.id.tables_list_fragment) != null){
            //Hemos cargado una interfaz que tiene el hueco para el fragment TablesListFragment
            //Comprobamos primero que no tenemos ya añadido el fragmend a nuestra jerarquía

            if(supportFragmentManager.findFragmentById(R.id.tables_list_fragment) == null) {
                //Añadiremos el fragment de forma dinámica
                val fragment: TablesListFragment = TablesListFragment.newInstance()

                supportFragmentManager.beginTransaction()
                        .add(R.id.tables_list_fragment, fragment)
                        .commit()
            }
        }

        if(findViewById<ViewGroup>(R.id.view_pager_fragment) != null){
            //Hemos cargado una interfaz que tiene el hueco para el fragment TablePagerFragment
            if(supportFragmentManager.findFragmentById(R.id.view_pager_fragment) == null){
                supportFragmentManager.beginTransaction()
                        .add(R.id.view_pager_fragment, TablePagerFragment.newInstance(0))
                        .commit()
            }
        }

    }

    override fun onTableSelected(table: Table, position: Int) {
        val tablesPagerFragment = supportFragmentManager.findFragmentById(R.id.view_pager_fragment) as? TablePagerFragment
        if(tablesPagerFragment != null) {
            //Estamos en una interfaz donde existe el TablePagerFragment, le decimos que nos mueva a una mesa
            tablesPagerFragment.moveToTable(position)
        }
        else {
            //Estamos en una interfaz donde sólo hay la lista. Lanzamies la actividad TablePagerActivity
            val intent = TablePagerActivity.intent(this, position)
            startActivity(intent)
        }
    }

    override fun onButtonClicked(table: Table?) {
        val intent = DishesActivity.intent(this, Tables.getIndex(table))
        startActivity(intent)
    }
}
