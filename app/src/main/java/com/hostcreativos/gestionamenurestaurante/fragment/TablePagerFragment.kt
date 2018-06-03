package com.hostcreativos.gestionamenurestaurante.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.*
import com.hostcreativos.gestionamenurestaurante.R
import com.hostcreativos.gestionamenurestaurante.model.Tables
import kotlinx.android.synthetic.main.fragment_table_pager.*

class TablePagerFragment: Fragment() {
    companion object {
        val ARG_TABLE = "ARG_TABLE"

        fun newInstance(tableIndex: Int): TablePagerFragment {
            val arguments = Bundle()
            arguments.putInt(ARG_TABLE, tableIndex)

            val fragment = TablePagerFragment()
            fragment.arguments = arguments

            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_table_pager, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = object : FragmentPagerAdapter(fragmentManager) {
            override fun getItem(position: Int): Fragment {
                return DishFragment.newInstance(Tables.getTable(position))
            }

            override fun getCount() = Tables.count

            override fun getPageTitle(position: Int): CharSequence {
                return Tables.getTable(position).name
            }

        }

        view_pager.adapter = adapter

        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                updateTableInfo(position)
            }
        })


        val initialTableIndex = arguments?.getInt(ARG_TABLE, 0)
        if (initialTableIndex != null) {
            moveToTable(initialTableIndex)
            updateTableInfo(initialTableIndex)
        }
    }

    fun moveToTable(initialTableIndex: Int) {
        view_pager.currentItem = initialTableIndex
    }

    private fun updateTableInfo(position: Int) {
        if (activity is AppCompatActivity) {
            val supportActionBar = (activity as? AppCompatActivity)?.supportActionBar
            supportActionBar?.title = Tables[position].name
        }
    }
}