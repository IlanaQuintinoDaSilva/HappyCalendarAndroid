package br.com.iq.happycalendarandroid.domain

class Project{
    var name: String = ""
    var description: String = ""
    var category: CategoryMC = CategoryMC()

    fun setCategory(categoryName: String){
        this.category.name = categoryName
    }
}