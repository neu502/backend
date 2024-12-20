package com.neu.demo.biz;

import com.neu.demo.mapper.StructureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StructureBiz {
    @Autowired
    private StructureMapper mapper;

    public List<Map<String, Object>> getModulesByProjectId(int projectId) {
        return this.mapper.getModulesByProjectId(projectId);
    }

    public void setMapper(StructureMapper mapper) {
        this.mapper = mapper;
    }
}
