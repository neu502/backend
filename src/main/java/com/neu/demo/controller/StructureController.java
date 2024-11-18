package com.neu.demo.controller;

import com.neu.demo.biz.ProjectBiz;
import com.neu.demo.biz.StructureBiz;
import com.neu.demo.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/structure")
public class StructureController {
    @Autowired
    private StructureBiz biz;

    //根据项目id得到项目所有信息
    @GetMapping("/getModule")
    public Map<String, Object> findProjectById(@RequestParam int projectId) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> module = biz.getModulesByProjectId(projectId);
        if (module != null) {
            result.put("isOk", true);
            result.put("msg", "查询该项目下所有模块成功");
            result.put("project", module);
        } else {
            result.put("isOk", false);
            result.put("msg", "未找到对应项目下的模块信息");
        }
        return result;
    }
}
