package br.arthur.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="groups")
public class Group {
	@Id
	@Column(name="group_id")
	@GeneratedValue
	private int id;
	
	@Column(name="name", nullable=false, unique=true)
	private String name;
	
	@ManyToMany(  
	        targetEntity=br.arthur.entities.Permission.class,  
	        cascade={CascadeType.PERSIST, CascadeType.MERGE}  
	    )  
	    @JoinTable(  
	        name="group_permission",  
	        joinColumns=@JoinColumn(name="GROUP_ID"),  
	        inverseJoinColumns=@JoinColumn(name="PERMISSION_ID")  
	    )    
	private Set<Permission> permissions = new HashSet<Permission>();

	public Group() {

	}

	public Group(String name) {
		super();
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((permissions == null) ? 0 : permissions.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Group other = (Group) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (permissions == null) {
			if (other.permissions != null)
				return false;
		} else if (!permissions.equals(other.permissions))
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	public void setId(byte id) {
		this.id = id;
	}

}
