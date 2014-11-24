package com.innverse.elearn.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="permission")
public class Permission {

	@Id
	@GeneratedValue
	private long id;
	
	private boolean createData;
	
	private boolean viewData;
	
	private boolean editData;
	
	private boolean deleteData;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isCreateData() {
		return createData;
	}

	public void setCreateData(boolean createData) {
		this.createData = createData;
	}

	public boolean isViewData() {
		return viewData;
	}

	public void setViewData(boolean viewData) {
		this.viewData = viewData;
	}

	public boolean isEditData() {
		return editData;
	}

	public void setEditData(boolean editData) {
		this.editData = editData;
	}

	public boolean isDeleteData() {
		return deleteData;
	}

	public void setDeleteData(boolean deleteData) {
		this.deleteData = deleteData;
	}
	
}
