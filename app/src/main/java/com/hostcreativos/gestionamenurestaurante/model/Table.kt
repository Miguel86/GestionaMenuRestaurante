package com.hostcreativos.gestionamenurestaurante.model

import java.io.Serializable

data class Table(var id: Int, var name: String, var dishes: MutableList<Dish>) : Serializable{
    override fun toString(): String {
        return name
    }

    fun getBillAmount() : Float {
        var total = 0f

        for (dish in dishes){
            total += dish.price
        }

        return total
    }

    fun addDish(dish: Dish){
        dishes?.add(dishes.count(), dish)
    }
}