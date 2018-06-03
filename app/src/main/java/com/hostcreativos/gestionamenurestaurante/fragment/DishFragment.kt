package com.hostcreativos.gestionamenurestaurante.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.view.*
import com.hostcreativos.gestionamenurestaurante.model.Dish
import com.hostcreativos.gestionamenurestaurante.R
import com.hostcreativos.gestionamenurestaurante.activity.DishDetailActivity
import com.hostcreativos.gestionamenurestaurante.adapter.DishRecyclerViewAdapter
import com.hostcreativos.gestionamenurestaurante.model.Tables
import com.hostcreativos.gestionamenurestaurante.model.Table

import kotlinx.android.synthetic.main.fragment_dish.*

class DishFragment: Fragment() {
    private var onButtonClickedListener: OnButtonClickedListener? = null

    companion object {

        val ARG_TABLE = "ARG_TABLE"

        fun newInstance(table: Table): Fragment {
            // Nos creamos el fragment
            val fragment = DishFragment()

            // Nos creamos los argumentos del fragment
            val arguments = Bundle()
            arguments.putSerializable(ARG_TABLE, table)

            // Asignamos los argumentos al fragment
            fragment.arguments = arguments

            // Devolvemos el fragment
            return fragment
        }

    }
    private enum class VIEW_INDEX(val index:Int){
        LOADIND(0), DISH(1)
    }
    val REQUEST_SETTINGS = 1
    var table: Table? = null

    var dishes: List<Dish>? = null
        set(value) {
            field = value
            if(value != null){
                val adapter = DishRecyclerViewAdapter(value)

                dish_list.adapter = adapter
                setRecyclerViewClickListener()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_dish, container, false)!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Configuramos las animaciones para el ViewSwitcher
        view_switcher.setInAnimation (activity, android.R.anim.fade_in)
        view_switcher.setOutAnimation(activity, android.R.anim.fade_out)

        //Le decimos al ViewSwitcher que muestre la primera vista
        view_switcher.displayedChild = VIEW_INDEX.LOADIND.index

        view.postDelayed({
            // Aqui simulamos que ya nos hemos bajado la información del menu
            // Configuramos el RecyclerView. Primero decimos cómo se visualizan sus elementos

            dish_list.layoutManager = GridLayoutManager(activity, resources.getInteger(R.integer.dish_columns))

            // Le decimos quién es el que anima al RecyclerView
            dish_list.itemAnimator = DefaultItemAnimator()

            // Por último tenemos que decirle los datos que van a rellenar el RecyclerView. Esto es
            // tarea del setter de forecast


            table = arguments?.getSerializable(ARG_TABLE) as Table
            dishes = table?.dishes

            view_switcher?.displayedChild= VIEW_INDEX.DISH.index
        },  1000.toLong())

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.activity_table_details, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_calc_bill -> {
                // Calculamos la factura
                val billAmount = table?.getBillAmount()
                Snackbar.make(view!!, getString(R.string.bill_amount, billAmount), Snackbar.LENGTH_LONG)
                        .show()

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {

        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if(isVisibleToUser && dishes != null){
            updateView()
        }
    }

    fun setRecyclerViewClickListener(){

        val adapter = dish_list?.adapter as? DishRecyclerViewAdapter
        //Si alguien pulsa un elemento del RecyclerView, nos queremos enterar aqui
        adapter ?.onClickListener = View.OnClickListener {
            //Alguien ha pulsado un elemento del RecyclerView
            //Obtenemos la posición pulsada
            val dishIndex = dish_list.getChildAdapterPosition(it)
            val table = arguments?.getSerializable(ARG_TABLE) as Table
            val tableIndex = Tables.getIndex(table)

            //Opciones especiales para navegar con vistas comunes
            val animationOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity!!,
                    it,
                    getString(R.string.transition_to_dish_detail)
            )
            startActivity(DishDetailActivity.intent(activity!!, tableIndex, dishIndex), animationOptions.toBundle())
        }

        add_button.setOnClickListener{
            onButtonClickedListener?.onButtonClicked(table)
        }
    }

    // Aquí actualizaremos la interfaz
    fun updateView() {
        dish_list.adapter = DishRecyclerViewAdapter(dishes!!)
        setRecyclerViewClickListener()
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        commonAttach(context as? Activity)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        commonAttach(activity)
    }

    fun commonAttach(activity: Activity?){
        if(activity is OnButtonClickedListener){
            onButtonClickedListener = activity
        }
        else{
            onButtonClickedListener = null
        }
    }

    override fun onDetach() {
        super.onDetach()
        onButtonClickedListener = null
    }

    interface OnButtonClickedListener {
        fun onButtonClicked(table: Table?)
    }
}
