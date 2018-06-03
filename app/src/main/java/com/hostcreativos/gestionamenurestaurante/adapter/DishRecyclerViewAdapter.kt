package com.hostcreativos.gestionamenurestaurante.adapter

import android.media.Image
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import com.hostcreativos.gestionamenurestaurante.model.Dish
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.hostcreativos.gestionamenurestaurante.R

class DishRecyclerViewAdapter(private val dish: List<Dish>) : RecyclerView.Adapter<DishRecyclerViewAdapter.DishViewHolder>(){

    var onClickListener: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DishViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.content_dish,parent, false)

        //le decimos a este view que cuando lo pulsen avise a nuestro onClickListener
        view.setOnClickListener(onClickListener)

        return DishViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dish.size
    }

    override fun onBindViewHolder(holder: DishViewHolder?, position: Int) {
        holder?.bindDish(dish[position])
    }

    inner class DishViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val dishName = itemView.findViewById<TextView?>(R.id.dish_name)
        val dishtImage = itemView.findViewById<ImageView?>(R.id.dish_image)
        val dishDescription = itemView.findViewById<TextView>(R.id.dish_description)
        val dishPersonalization = itemView.findViewById<TextView>(R.id.dish_personalization)
        val allergen1 = itemView.findViewById<ImageView>(R.id.list_allergen1)
        val allergen2 = itemView.findViewById<ImageView>(R.id.list_allergen2)
        val allergen3 = itemView.findViewById<ImageView>(R.id.list_allergen3)
        val allergen4 = itemView.findViewById<ImageView>(R.id.list_allergen4)

        val context = itemView.context

        fun bindDish(dish: Dish){
            // Actualizamos la vista con el modelo
            dishtImage?.setImageResource(dish.image)
            dishName?.text = dish.name
            dishDescription?.text = dish.description
            if(dish.personalization != null) {
                dishPersonalization?.text = itemView.context.getString(R.string.personalization_value,dish.personalization)
            }

            allergen1?.setImageResource(dish.getAllergen(0))
            allergen2?.setImageResource(dish.getAllergen(1))
            allergen3?.setImageResource(dish.getAllergen(2))
            allergen4?.setImageResource(dish.getAllergen(3))
        }
    }

}