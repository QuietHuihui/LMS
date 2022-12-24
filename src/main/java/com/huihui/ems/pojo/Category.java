package com.huihui.ems.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "log_id")
    @SequenceGenerator(name = "log_id", initialValue = 1, allocationSize = 1, sequenceName = "SYS_LOG_ID")
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;

	public Category(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Category() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
