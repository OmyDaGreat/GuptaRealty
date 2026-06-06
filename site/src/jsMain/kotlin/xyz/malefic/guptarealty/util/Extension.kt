package xyz.malefic.guptarealty.util

import org.kimplify.kurrency.CurrencyFormatter
import org.kimplify.kurrency.Kurrency

val formatter = CurrencyFormatter()

val Number.price: String
    get() = formatter.formatCurrencyStyle(this.toString(), Kurrency.USD)
