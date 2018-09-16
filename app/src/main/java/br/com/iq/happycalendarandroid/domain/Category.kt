package br.com.iq.happycalendarandroid.domain

import br.com.iq.happycalendarandroid.R

/*class Category{
    var id: Long = 0
    var categoryDesc: String = ""
}*/

enum class Category(val string: Int) {
    Equilibrio(R.string.equilibrio),
    Trabalho(R.string.trabalho),
    Lazer(R.string.lazer),
    Casa(R.string.casa),
    Carreira(R.string.carreira)
}