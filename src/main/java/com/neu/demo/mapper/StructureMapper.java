package com.neu.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface StructureMapper {
    @Select("SELECT module_name, dfp FROM t_structure WHERE project_id = #{projectId}")
    List<Map<String, Object>> getModulesByProjectId(int projectId);
}

