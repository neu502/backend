package com.neu.demo.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.*;

import java.util.List;
import java.util.Map;

@Service
public interface StructureMapper {
    @Select("SELECT module_name, dfp FROM t_structure WHERE project_id = #{project_id}")
    List<Map<String, Object>> getModulesByProjectId(int project_id);
}

