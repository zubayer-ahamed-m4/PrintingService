/**
 * 
 */
package com.coderslab.model;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

/**
 * @author cyclingbd007
 *
 */
@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Student {

	private Integer id;
	private String name;
	private Date dob;

	@XmlElementWrapper(name = "addresses")
	@XmlElement(name = "address")
	private List<Address> addresses;
}
