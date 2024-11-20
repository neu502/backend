package com.neu.demo.controller;

import com.neu.demo.biz.ProjectBiz;
import com.neu.demo.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
            System.out.println("在输出了");
            result.put("isOk", true);
            result.put("msg", "查询项目所有信息成功");
            result.put("project", project);
        } else {
            result.put("isOk", false);
            result.put("msg", "未找到对应的项目");
        }
        return result;
    }
    //根据项目id得到审核状态
    @GetMapping("/getStatus")
    public Map<String, Object> findStatusById(@RequestParam int projectId) {
        Map<String, Object> result = new HashMap<>();
        System.out.println("请求的项目ID是：" + projectId);  // 日志打印
        String status = biz.selectStatusById(projectId);

        if (status != null) {
            System.out.println("审核状态：" + status);  // 日志打印
            result.put("isOk", true);
            result.put("msg", "查询审核状态成功");
            result.put("status", status);
        } else {
            result.put("isOk", false);
            result.put("msg", "未找到对应的审核状态");
        }
        return result;
    }
    //根据项目id得到审核意见
    @GetMapping("/getSuggest")
    public Map<String, Object> findSuggestById(@RequestParam int projectId) {
        Map<String, Object> result = new HashMap<>();
        String suggest = biz.selectSuggestById(projectId);
        if (suggest != null) {
            result.put("isOk", true);
            result.put("msg", "查询审核意见成功");
            result.put("suggest", suggest);
        } else {
            result.put("isOk", false);
            result.put("msg", "未找到对应的审核意见");
        }
        return result;
    }

    @RequestMapping("/findProject")
    public Map findProject(String projectName){
        List<Project> project = this.biz.findProject(projectName);
        Map res = new HashMap();
        res.put("isOk",true);
        res.put("msg","信息查询成功");
        res.put("project",project);
        return res;
    }

    // 提交文档 URL，暂时 projectId 写死为 1
    @PostMapping("/submitDocument")
    public ResponseEntity<String> submitDocument(@RequestBody Map<String, String> requestBody) {
        String url = requestBody.get("fileUrl");

        // 检查 URL 是否有效
        if (url == null || url.isEmpty()) {
            return ResponseEntity.badRequest().body("File URL is required.");
        }

        try {
            // 这里暂时写死为 1，调用 updateUrlByProjectId 更新 URL
            int projectId = 1;  // 假设你只针对 projectId = 1 进行处理

            // 更新数据库中的 URL
            int rowsAffected = biz.updateUrlByProjectId(projectId, url);

            if (rowsAffected > 0) {
                // 如果 URL 更新成功
                return ResponseEntity.ok("Document URL updated successfully.");
            } else {
                // 如果没有找到对应的项目，或没有更新任何记录
                return ResponseEntity.status(404).body("Project not found or no update performed.");
            }

        } catch (IllegalArgumentException e) {
            // 处理 URL 不合法的情况
            return ResponseEntity.badRequest().body("Invalid URL: " + e.getMessage());
        } catch (Exception e) {
            // 处理其他异常情况
            return ResponseEntity.status(500).body("Internal server error.");
        }
    }


    // 获取项目的审计信息
    @GetMapping("/{projectId}/audit")
    public ResponseEntity<Map<String, Object>> getAuditInfo(@PathVariable int projectId) {
        try {
            // 调用服务层的查询方法
            Map<String, Object> auditInfo = biz.getAuditInfoByProjectId(projectId);
            if (auditInfo != null && !auditInfo.isEmpty()) {
                return ResponseEntity.ok(auditInfo);
            } else {
                return ResponseEntity.status(404).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
