package com.greatLearning.collegeFest.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import lombok.Data;

@Entity
@Table(name="users")
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	int id;
	@Column(name="user_name")
	String username;
	@Column(name="password")
	String password;
	@Column (name="email")
	String email;
	
	@JoinTable( name = "users_roles",
			joinColumns = @JoinColumn(name="user_id"),
			inverseJoinColumns =  @JoinColumn(name="role_id"))
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	List<Role> roles = new ArrayList<Role>();
}