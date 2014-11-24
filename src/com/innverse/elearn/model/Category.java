package com.innverse.elearn.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.innverse.elearn.eenum.CategoryType;


/**
 * @author Sameer
 *
 */
@Entity
@Table(name = "category")
public class Category {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name="parent_id")
	private long parentId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "parent_category_id")
	private Category parentCategory;
	
	private String categoryName;
	
	private String description;
	
	private CategoryType catagoryType;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Organization createBy;
	
	private boolean active;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public CategoryType getCatagoryType() {
		return catagoryType;
	}

	public void setCatagoryType(CategoryType catagoryType) {
		this.catagoryType = catagoryType;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Organization getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Organization createBy) {
		this.createBy = createBy;
	}

	public Category getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}
	
}
