package com.jojo.design.module_core.widgets.cardview


import androidx.cardview.widget.CardView

interface ICardAdapter {

    fun getMaxElevationFactor(): Int

    fun getBaseElevation(): Float

    fun getCardViewAt(position: Int): CardView

    fun getCount(): Int
}
