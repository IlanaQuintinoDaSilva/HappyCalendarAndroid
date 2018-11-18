package br.com.iq.happycalendarandroid.domain

class Project{
    var name: String = ""
    var description: String = ""
    var category: CategoryHC = CategoryHC()

    fun setCategory(categoryName: String){
        this.category.name = categoryName
    }
}