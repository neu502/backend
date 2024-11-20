package com.neu.demo.controller;

import com.neu.demo.biz.ProjectBiz;
import com.neu.demo.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectBiz biz;

    //根据项目id得到项目所有信息
    @GetMapping("/getall")
    public Map<String, Object> findProjectById(@RequestParam int projectId) {
        Map<String, Object> result = new HashMap<>();
        Project project = biz.selectProjectById(projectId);
        if (project != null) {
            result.put("isOk", true);
            result.put("msg", "查询项目所有信息成功");
            result.put("project", project);
        } else {
            result.put("isOk", false);
            result.put("msg", "未找到对应的项目");
        }
        return result;
    }

    @RequestMapping("/findProject")
    public Map findProjectByName(String projectName){
        Project project = this.biz.findProjectByName(projectName);
        Map res = new HashMap();
        res.put("isOk",true);
        res.put("msg","信息查询成功");
        res.put("project",project);
        return res;
    }

}
