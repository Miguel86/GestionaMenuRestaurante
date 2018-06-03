package com.hostcreativos.gestionamenurestaurante.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hostcreativos.gestionamenurestaurante.R
import com.hostcreativos.gestionamenurestaurante.model.Dishes

import kotlinx.android.synthetic.main.activity_configure_dish.*
import kotlinx.android.synthetic.main.allergens.*

class ConfigureDishActivity : AppCompatActivity() {

    companion object {
        val EXTRA_DISH = "EXTRA_DISH"

        fun intent(context: Context, dishIndex: Int): Intent {
            val intent = Intent(context, ConfigureDishActivity::class.java)
            intent.putExtra(EXTRA_DISH, dishIndex)

            return intent
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configure_dish)

        val addingDishIndex = intent.getIntExtra(EXTRA_DISH, 0)
        val dish = Dishes.getDish(addingDishIndex)

        configure_dish_name.text = dish.name
        configure_dish_description.text = dish.description
        configure_dish_image.setImageResource(dish.image)

        list_allergen1?.setImageResource(dish.getAllergen(0))
        list_allergen2?.setImageResource(dish.getAllergen(1))
        list_allergen3?.setImageResource(dish.getAllergen(2))
        list_allergen4?.setImageResource(dish.getAllergen(3))

        ok_btn.setOnClickListener { acceptSettings() }
        cancel_btn.setOnClickListener { cancelSettings() }
    }

    private fun cancelSettings() {
        setResult(Activity.RESULT_CANCELED)
        finish()
    }

    private fun acceptSettings() {
        // Sólo devolvemos que se pulso el botón OK
        val returnIntent = Intent()
        setResult(Activity.RESULT_OK, returnIntent)

        // Finalizamos la actividad para volver a la anterior
        finish()
    }
}
