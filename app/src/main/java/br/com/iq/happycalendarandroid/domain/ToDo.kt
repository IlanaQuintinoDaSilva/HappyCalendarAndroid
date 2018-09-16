package br.com.iq.happycalendarandroid.domain

class ToDo{
    var id: Long = 0
    var description: String = ""
    var category: String = ""

    override fun toString(): String{
        return "ToDo(description='$description')"
    }
}