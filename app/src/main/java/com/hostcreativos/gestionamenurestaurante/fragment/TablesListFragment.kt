package com.hostcreativos.gestionamenurestaurante.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView

import com.hostcreativos.gestionamenurestaurante.R
import com.hostcreativos.gestionamenurestaurante.model.Table
import com.hostcreativos.gestionamenurestaurante.model.Tables
import kotlinx.android.synthetic.main.fragment_table_list.*

class TablesListFragment : Fragment() {
    companion object {
        @JvmStatic
        fun newInstance() = TablesListFragment()
    }

    private var onTableSelectedListener: OnTableSelectedListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_table_list, container, false)
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ArrayAdapter<Table>(activity, android.R.layout.simple_list_item_1, Tables.toArray())

        tables_list.adapter = adapter

        tables_list.setOnItemClickListener{ _, _, index, _ ->
            //Avisamos al listener que una mesa ha sido seleccionada

            onTableSelectedListener?. onTableSelected(Tables[index], index)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        commonAttach(context as? Activity)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        commonAttach(activity)
    }

    private fun commonAttach(activity: Activity?){
        if(activity is OnTableSelectedListener){
            onTableSelectedListener = activity
        }
        else{
            onTableSelectedListener = null
        }
    }

    override fun onDetach() {
        super.onDetach()
        onTableSelectedListener = null
    }

    interface OnTableSelectedListener {
        fun onTableSelected(table: Table, position: Int)
    }
}
