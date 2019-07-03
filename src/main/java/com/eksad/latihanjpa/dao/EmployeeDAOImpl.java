package com.eksad.latihanjpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.eksad.latihanjpa.model.Employee;

// sbg bahwa class ini disimpan dalam memori. jd ga perlu inisialisasi lg employee employee= new dll gitu
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

// sbg menjembatani database dg objek yg dibuat
	@PersistenceContext
	EntityManager entityManager; // otomatis akan mapping object dalam class
	
	@Override
	public List<Employee> getAll() {
		return entityManager.createQuery("select e from Employee e", Employee.class).getResultList();
	} //  fungsi select e from Employee e : memanggil Employee yg e dari dalam class. 
	//e nya itu alias atau variable. lalu ditampung di Employee.Class
	// diubah menjadi array list yg getResultList
	
	@Override
	public Employee getById(int id) {
		// TODO Auto-generated method stub
		return entityManager.find(Employee.class, id);
	}

	@Transactional  //ketika aplikasi akan melakukan transaksi utk menyimpan atau update ke dalam database. 
	@Override
	public void save(Employee employee) {
		// TODO Auto-generated method stub
		entityManager.persist(employee); 
		
	}

	@Transactional
	@Override
	public void update(Employee employee) {
		// TODO Auto-generated method stub
		entityManager.merge(employee);
		
	}

	@Transactional
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		Employee employee = getById(id);	
		entityManager.remove(employee);
	}

	@Override
	public List<Employee> getByName(String name) {
		// TODO Auto-generated method stub
		return entityManager.createNativeQuery("SELECT * FROM employee WHERE name LIKE ?0 ", Employee.class) // fungi ? utk memasukkan parameter yg diinginkan
		// return entityManager.createQuery("select e from Employee e where e.name like ?0 ", Employee.class) // Employee dibuat menjadi tipe data. e.name = memangggil employee.name. jd mengquery suat objek class
		.setParameter(0, "%" + name + "%")
		.getResultList();
	
	}

}
