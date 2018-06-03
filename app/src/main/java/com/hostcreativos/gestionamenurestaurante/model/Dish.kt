package com.hostcreativos.gestionamenurestaurante.model

import com.hostcreativos.gestionamenurestaurante.R
import java.io.Serializable


data class Dish(var id: Int, var name: String, var description: String, var price: Float, var image: Int, var personalization: String, var allergen: List<Allergen>): Serializable {
    override fun toString(): String {
        return name
    }

    fun getAllergen(index: Int): Int{
        if(allergen.count()> index)
            return allergen[index].image
        else
            return R.drawable.allergen_white
    }
}