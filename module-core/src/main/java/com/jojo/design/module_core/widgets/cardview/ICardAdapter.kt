package com.jojo.design.module_core.widgets.cardview


import android.support.v7.widget.CardView

interface ICardAdapter {

    fun getMaxElevationFactor(): Int

    fun getBaseElevation(): Float

    fun getCardViewAt(position: Int): CardView

    fun getCount(): Int
}
