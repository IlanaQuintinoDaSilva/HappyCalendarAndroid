package br.com.iq.happycalendarandroid.domain.api

import br.com.iq.happycalendarandroid.domain.vo.GetProjectsResultVO
import br.com.iq.happycalendarandroid.domain.vo.ProjectVO
import org.json.JSONObject

class GetProjectsParser {
    companion object {
        fun parserData(jsonResponse: String): ArrayList<ProjectVO> {
            val json = JSONObject(jsonResponse)
            val result = json.optJSONObject("")
            val arrGetProjectsJson = result.getJSONArray("Data")
            var arrProjects: ArrayList<ProjectVO> = ArrayList()

            if(arrGetProjectsJson != null) {
                if(arrGetProjectsJson.length() > 0) {
                    for(i in 0 until arrGetProjectsJson.length()) {
                        val projectObj = arrGetProjectsJson.getJSONObject(i)
                        val projectVO = ProjectVO()
                        projectVO.name = projectObj.getString("Name")
                        projectVO.description = projectObj.getString("Description")

                        arrProjects.add(projectVO)
                    }

                }
            }
            return arrProjects
        }
    }
}