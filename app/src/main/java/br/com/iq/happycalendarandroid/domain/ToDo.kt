package br.com.iq.happycalendarandroid.domain

class ToDo{
    var id: Long = 0
    var description: String = ""
    var project: Project = Project()
    var category: String = ""

    fun setProject(name: String, category: String){
        project.name = name
        project.category = category
    }


    override fun toString(): String{
        return "ToDo(description='$description')"
    }
}