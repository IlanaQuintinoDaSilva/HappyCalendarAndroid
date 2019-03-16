package br.com.iq.happycalendarandroid.domain

class Project{
    var name: String = ""
    var description: String = ""
    var category: Category = Category()

    fun setCategory(categoryName: String){
        this.category.name = categoryName
    }
}