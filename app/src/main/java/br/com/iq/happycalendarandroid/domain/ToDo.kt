package br.com.iq.happycalendarandroid.domain

import java.util.*

class ToDo{
    var id: Long = 0
    var description: String = ""
    var project: Project = Project()
    var sprint: Sprint = Sprint()
    var assignee: Assignee = Assignee()

    fun setProject(name: String, category: String){
        project.name = name
        project.setCategory(category)
    }

    fun setDescription(name: String, category: String, description: String, startDate: Date){
        project.name = name
        project.setCategory(category)
        this.description = description
        sprint.startDate = startDate
    }

    fun setAssignee(firstName: String, lastName: String){
        assignee.firstName = firstName
        assignee.lastName = lastName
    }

    override fun toString(): String{
        return "ToDo(description='$description')"
    }
}