package com.hostcreativos.gestionamenurestaurante.model

import com.hostcreativos.gestionamenurestaurante.R

object Dishes {
    private val dishes: List<Dish> = listOf(
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

    val count
        get() = dishes.size

    fun getDish(index: Int) = dishes[index]

    operator fun get(index: Int) = dishes[index]

    fun toArray() = dishes.toTypedArray()

    fun getIndex(dish:Dish) = dishes.indexOf(dish)
}