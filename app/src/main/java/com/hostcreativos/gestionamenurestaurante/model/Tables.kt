package com.hostcreativos.gestionamenurestaurante.model

import com.hostcreativos.gestionamenurestaurante.R

object Tables {
    private val tables: List<Table> = listOf(
            Table(1, "Mesa 1", mutableListOf(
                    Dish(1, "Lentejas", "Lentejas al estilo casero con chorizo, patata y verdura.", 12.5F, R.drawable.lentejas, "Las quiere caldosas.", listOf(
                            Allergen(1, R.drawable.allergen1),
                            Allergen(2, R.drawable.allergen2)
                    )),
                    Dish(2, "Tortilla española", "Rica tortilla de pata con cebolla.", 8F, R.drawable.tortilla, "Tortilla.", listOf(
                            Allergen(1, R.drawable.allergen1)
                    )),
                    Dish(3, "Albóndigas", "Albóndigas de carne caseras con patatas cocidas.", 9.25F, R.drawable.albondigas, "Albóndigas.", listOf(
                    ))
            )),
            Table(2, "Mesa 2", mutableListOf(
                    Dish(1, "Lentejas", "Lentejas al estilo casero con chorizo, patata y verdura.", 12.5F, R.drawable.lentejas, "Las quiere caldosas.", listOf(

                    ))
            )),
            Table(3, "Mesa gupos", mutableListOf()),
            Table(4, "Barra 1", mutableListOf()),
            Table(5, "Barra 2", mutableListOf()),
            Table(6, "Barra 3", mutableListOf()),
            Table(7, "Terraza 1", mutableListOf()),
            Table(8, "Terraza 2", mutableListOf()),
            Table(9, "Terraza 3", mutableListOf())
    )

    val count
        get() = tables.size

    fun getTable(index: Int) = tables[index]

    operator fun get(index: Int) = tables[index]

    fun toArray() = tables.toTypedArray()

    fun getIndex(table:Table?) = tables.indexOf(table)
}