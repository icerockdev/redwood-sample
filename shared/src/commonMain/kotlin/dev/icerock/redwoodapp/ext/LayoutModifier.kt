package dev.icerock.redwoodapp.ext

import app.cash.redwood.LayoutModifier

fun LayoutModifier.weight(value: Float): LayoutModifier =
    then(Weight(value))

public class Weight(
    public val weight: Float = 1f
) : LayoutModifier.Element