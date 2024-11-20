package com.neu.demo.biz;

import com.neu.demo.entity.Project;
import com.neu.demo.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProjectBiz {
    @Autowired
    private ProjectMapper mapper;

    public Project selectProjectById(int projectId) {
        return mapper.selectProjectById(projectId);
    }

<<<<<<< HEAD
    public Project findProjectByName(String projectName) {
        return this.mapper.selectProjectByProjectName(projectName);
    }

    public void setMapper(ProjectMapper mapper) {
        this.mapper = mapper;
=======
    //获取所有项目
    public List<Project> selectProjects() {
        return this.mapper.getProjects();
    }

    public String selectStatusById(int projectId) {
        String status = mapper.selectStatusById(projectId);
        return status;
    }
    public String selectSuggestById(int projectId) {
        String suggest = mapper.selectSuggestById(projectId);
        return suggest;
    }
    public List<Project> findProject(String projectName) {
        return this.mapper.selectProjectByProjectName(projectName);
    }

    public int updateUrlByProjectId(int projectId, String url) {
        // 先进行一些校验，确保 URL 合法（如果需要的话）
        if (url == null || url.trim().isEmpty()) {
            throw new IllegalArgumentException("URL cannot be empty.");
        }
        return mapper.updateUrlByProjectId(projectId, url);
    }
    public Map<String, Object> getAuditInfoByProjectId(int projectId) {
        return mapper.selectAuditInfoByProjectId(projectId);
>>>>>>> 4141cb604101d3c7e96a66696b9c9e10931d0c48
    }
}