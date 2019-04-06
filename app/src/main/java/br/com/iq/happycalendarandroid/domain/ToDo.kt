package br.com.iq.happycalendarandroid.domain

import java.util.*

class ToDo{
    var id: Long = 0
    var description: String = ""
    var category: String = ""
    var project: Project = Project()
    var sprint: Sprint = Sprint()
    var assignee: Assignee = Assignee()
    var done: Int = 0
    var backlog: Long = 0

    fun setProject(name: String, category: String){
        project.name = name
        project.setCategory(category)
    }

    fun setDescription(name: String, category: String, description: String){
        project.name = name
        project.setCategory(category)
        this.description = description
    }

    fun setSprint(startDate: Date){
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