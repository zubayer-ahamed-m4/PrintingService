/**
 * 
 */
package com.coderslab.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * @author cyclingbd007
 *
 */
@Data
public class Student {

	private Integer id;
	private String name;
	private Date dob;
	private List<Address> addresses;
}
