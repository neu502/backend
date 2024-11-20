package com.neu.demo.mapper;

import com.neu.demo.entity.Project;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ProjectMapper {
    @Select("SELECT * FROM t_project WHERE project_id=#{projectId}")
    Project selectProjectById(String projectId);

    @Select("select * from t_project")
    List<Project> selectProjectByProjectName(String projectName);
    @Select("select * from user")
    List<Project> selectAllProject();
    @Update("update user set auditStatus=#{auditStatus} auditSuggest = #{auditSuggest} where project_id = #{project_id}")
    int auditProject(Project project);

}
