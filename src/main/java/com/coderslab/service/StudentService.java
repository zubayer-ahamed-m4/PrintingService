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

			List<Address> list = new ArrayList<>();
			for (int j = 1; j <= 3; j++) {
				Address address = new Address();
				address.setAddressId(j);
				address.setHouseNo("dhaka");
				list.add(address);
			}
			student.setAddresses(list);
			students.add(student);
		}
		return students;
	}
}
