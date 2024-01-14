package com.zhong.repository;

import com.zhong.node.Area;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AreaRepository extends GraphRepository<Area>{
	Area findByName(String name);
}
