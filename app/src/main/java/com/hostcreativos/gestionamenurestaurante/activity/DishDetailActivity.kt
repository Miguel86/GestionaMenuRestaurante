package com.hostcreativos.gestionamenurestaurante.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hostcreativos.gestionamenurestaurante.R
import com.hostcreativos.gestionamenurestaurante.model.Tables
import kotlinx.android.synthetic.main.content_dish.*

class DishDetailActivity : AppCompatActivity() {

    companion object {
        val EXTRA_TABLE_INDEX = "EXTRA_TABLE_INDEX"
        val EXTRA_DISH_INDEX = "EXTRA_DISH_INDEX"

        fun intent(context: Context, cityIndex: Int, forecastIndex: Int) : Intent {
            val intent = Intent(context, DishDetailActivity::class.java)
            intent.putExtra(EXTRA_TABLE_INDEX, cityIndex)
            intent.putExtra(EXTRA_DISH_INDEX, forecastIndex)

            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dish_detail)

        val table = Tables[intent.getIntExtra(EXTRA_TABLE_INDEX, 0)]
        val dish = table.dishes[intent.getIntExtra(EXTRA_DISH_INDEX, 0)]

        //Actualizamos la interfaz
        dish_name.text = dish.name
        dish_image.setImageResource(dish.image)
        dish_description?.text = dish.description
    }
}