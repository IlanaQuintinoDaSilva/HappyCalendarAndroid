package br.com.iq.happycalendarandroid.domain.vo

data class GetProjectsResultVO (
        var data: ArrayList<ProjectVO>? = null,
        var success: Boolean = false,
        var description: String = "",
        var errorCode: Int = 0
)