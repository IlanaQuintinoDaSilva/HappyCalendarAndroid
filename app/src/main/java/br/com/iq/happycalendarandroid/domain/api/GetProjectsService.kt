package br.com.iq.happycalendarandroid.domain.api

import br.com.iq.happycalendarandroid.domain.vo.ProjectVO
import com.google.gson.JsonObject

object GetProjectsService {
    fun getProjects():ArrayList<ProjectVO>{
        val url = "http://localhost:49792/api/happycalendar/GetProjects"

        val obj = JsonObject()
        val request = JsonObject()
        val jsonRequest = request.toJson()
        val httpHelper = getHttpHelper()
    }
}