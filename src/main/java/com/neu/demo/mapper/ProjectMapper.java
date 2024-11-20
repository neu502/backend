package com.neu.demo.mapper;

import com.neu.demo.entity.Project;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ProjectMapper {
    @Select("SELECT * FROM t_project WHERE project_id=#{projectId}")
    Project selectProjectById(int projectId);

    @Select("select * from t_project where project_name=#{projectName}")
    Project selectProjectByProjectName(String projectName);

}
