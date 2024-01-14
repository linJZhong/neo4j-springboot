package com.zhong.node;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@NodeEntity
public class Area {

	@GraphId
	private Long id;

	/**
	 * Neo4j 并没有真正的双向关系，我们只有在查询的时候忽略关系的方向 可以参考下面这个链接对neo4j的关系作出正确的理解：
	 * https://dzone.com/articles/modelling-data-neo4j
	 */
	@Relationship(type = "城市", direction = Relationship.UNDIRECTED)
	public Set<Area> city;

	@Relationship(type = "省份")
	public Set<Area> province;

	/*
	 * 指定城市关系 --->
	 */
	public void belongWith(Area Area) {
		if (city == null) {
			city = new HashSet<>();
		}
		city.add(Area);
	}

	/*
	 * 指定省份关系 --->
	 */
	public void provinceBelong(Area Area) {
		if (province == null) {
			province = new HashSet<>();
		}
		province.add(Area);
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Area> getCity() {
		return city;
	}

	public void setCity(Set<Area> city) {
		this.city = city;
	}

	public Set<Area> getProvince() {
		return province;
	}

	public void setProvince(Set<Area> province) {
		this.province = province;
	}

	private String name;

	public Area(String name) {
		this.name = name;
	}

	public Area() {
	}

	/**
	 * 列出该节点（Area）的关系网
	 */
	public String toString() {

		/*
		 * java8新特新 Optional.ofNullable(arg) 参数可以是null
		 * 如果值不为null，orElse方法返回Optional实例（关系）的值 Collections.emptySet():防止空指针出现 |
		 * | V 当代码需要一个集合而这个集合可能不存在，此时尽量使用空集合而不是null
		 */
		return this.name + "  城市 => "
				+ Optional.ofNullable(this.city).orElse(Collections.emptySet()).stream()
				.map(Area -> Area.getCity()).collect(Collectors.toList())
				+ " 省份 => " + Optional.ofNullable(this.province).orElse(Collections.emptySet()).stream()
				.map(Area -> Area.getProvince()).collect(Collectors.toList());
	}
}