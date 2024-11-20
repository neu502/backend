package com.neu.demo.mapper;

import com.neu.demo.entity.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProjectMapper {
    @Select("SELECT * FROM t_project WHERE project_id=#{projectId}")
    Project selectProjectById(int projectId);

    @Select("select * from t_project")
    List<Project> selectProjectByProjectName(String projectName);

    // 根据 projectId 查询审核状态
    @Select("SELECT auditStatus FROM t_project WHERE project_id=#{projectId}")
    String selectStatusById(int projectId);
    // 根据 projectId 查询审核状态
    @Select("SELECT auditSuggest FROM t_project WHERE project_id=#{projectId}")
    String selectSuggestById(int projectId);
    // 根据 projectId 查询 auditStatus 和 auditSuggest
    @Select("SELECT audit_status, audit_suggest FROM t_project WHERE project_id=#{projectId}")
    Map<String, Object> selectAuditInfoByProjectId(int projectId);
    // 根据 projectId 更新 URL
    @Update("UPDATE t_project SET url=#{url} WHERE project_id=#{projectId}")
    int updateUrlByProjectId(@Param("projectId") int projectId, @Param("url") String url);
}
