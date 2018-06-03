package com.hostcreativos.gestionamenurestaurante.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.hostcreativos.gestionamenurestaurante.R
import com.hostcreativos.gestionamenurestaurante.adapter.DishRecyclerViewAdapter
import com.hostcreativos.gestionamenurestaurante.model.*
import kotlinx.android.synthetic.main.activity_dishes.*

class DishesActivity : AppCompatActivity(){


    companion object {
        val EXTRA_TABLE = "EXTRA_TABLE"
        val DISH_ADDED = 0

        fun intent(context: Context, tableIndex: Int): Intent {
            val intent = Intent(context, DishesActivity::class.java)
            intent.putExtra(EXTRA_TABLE, tableIndex)

            return intent
        }

    }

    var dishes: List<Dish>? = null
        set(value) {
            field = value
            if(value != null){
                val adapter = DishRecyclerViewAdapter(value)

                full_dish_list.adapter = adapter
                setRecyclerViewClickListener()
            }
        }
    var table: Table? = null
    var dish: Dish? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dishes)

        full_dish_list.layoutManager = GridLayoutManager(this, resources.getInteger(R.integer.dish_columns))

        // Le decimos quién es el que anima al RecyclerView
        full_dish_list.itemAnimator = DefaultItemAnimator()

        val tableIndex = intent.getIntExtra(EXTRA_TABLE, 0)
        table = Tables.get(tableIndex)

        dishes = listOf(
                Dish(1, "Lentejas", "Lentejas al estilo casero con chorizo, patata y verdura.", 12.5F, R.drawable.lentejas, "Las quiere caldosas.", listOf(
                        Allergen(1, R.drawable.allergen1),
                        Allergen(2, R.drawable.allergen2)
                )),
                Dish(2, "Tortilla española", "Rica tortilla de pata con cebolla.", 8F, R.drawable.tortilla, "Tortilla.", listOf(
                        Allergen(1, R.drawable.allergen1)
                )),
                Dish(3, "Langostinos al ajillo", "Cazuela de langostinos al ajillo.", 11.75F, R.drawable.langostinos, "Langostinos al ajillo.", listOf(

                )),
                Dish(4, "Albóndigas caseras", "Albóndigas de carne caseras con patatas cocidas.", 9.25F, R.drawable.albondigas, "Albóndigas.", listOf(

                ))
        )
    }

    fun setRecyclerViewClickListener(){

        val adapter = full_dish_list?.adapter as? DishRecyclerViewAdapter
        //Si alguien pulsa un elemento del RecyclerView, nos queremos enterar aqui
        adapter ?.onClickListener = View.OnClickListener {
            //Alguien ha pulsado un elemento del RecyclerView
            //Obtenemos la posición pulsada
            val dishIndex = full_dish_list.getChildAdapterPosition(it)
            dish = dishes?.get(dishIndex)

            val intent = ConfigureDishActivity.intent(this, dishIndex)
            startActivityForResult(intent, DISH_ADDED)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //Vemos si al final se añade el plato o no desde la otra vista
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK)
        {
            finish()
            table?.addDish(dish!!)
        }
        else{
            finish()
        }
    }
}