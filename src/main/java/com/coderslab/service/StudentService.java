/**
 * 
 */
package com.coderslab.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.coderslab.model.Address;
import com.coderslab.model.Student;

/**
 * @author cyclingbd007
 *
 */
@Service
public class StudentService {

	public List<Student> getStudents() {
		List<Student> students = new ArrayList<>();
		for (int i = 1; i <= 50; i++) {
			Student student = new Student();
			student.setId(i);
			student.setName("name" + i);
			student.setDob(new Date());
			student.setAddresses(getAddress());
		}
		return students;
	}

	public List<Address> getAddress() {
		List<Address> addresses = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			Address address = new Address();
			address.setAddressId(i);
			address.setCity("dhaka");
			address.setDist("comilla");
			address.setHouseNo("615");
			address.setPo("Dashpara");
			address.setPs("Daudkandi");
			address.setRoadNo("1236");
			addresses.add(address);
		}
		return addresses;
	}
}
