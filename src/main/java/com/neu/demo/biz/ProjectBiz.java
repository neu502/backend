package com.neu.demo.biz;

import com.neu.demo.entity.Project;
import com.neu.demo.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProjectBiz {
    @Autowired
    private ProjectMapper mapper;

    public Project selectProjectById(int projectId) {
        return mapper.selectProjectById(projectId);


    }

    public List<Project> findProject(String projectName) {
        return this.mapper.selectProjectByProjectName(projectName);
    }

}