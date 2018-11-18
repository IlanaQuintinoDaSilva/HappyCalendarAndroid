package br.com.iq.happycalendarandroid.domain

class ToDo{
    var id: Long = 0
    var description: String = ""
    var project: Project = Project()
    var assignee: Assignee = Assignee()

    fun setProject(name: String, category: String){
        project.name = name
        project.setCategory(category)
    }

    fun setDescription(name: String, category: String, description: String){
        project.name = name
        project.setCategory(category)
        this.description = description
    }

    fun setAssignee(firstName: String, lastName: String){
        assignee.firstName = firstName
        assignee.lastName = lastName
    }

    override fun toString(): String{
        return "ToDo(description='$description')"
    }
}