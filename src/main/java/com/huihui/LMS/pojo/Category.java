package com.huihui.LMS.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@NamedQuery(name="Category.findByName",query="select c from Category c where c.name=:name")
@NamedQuery(name="Category.getAllCat",query="select new com.huihui.LMS.wrapper.CategoryWrapper(c.id,c.name) from Category c")


@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "c_id")
    @SequenceGenerator(name = "c_id", initialValue = 1, allocationSize = 1, sequenceName = "category_id")
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
