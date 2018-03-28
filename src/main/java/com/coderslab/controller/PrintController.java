/**
 * 
 */
package com.coderslab.controller;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.coderslab.model.Student;
import com.coderslab.service.PrintingService;
import com.coderslab.service.StudentService;

/**
 * @author cyclingbd007
 *
 */
@Controller
@RequestMapping("/print")
public class PrintController {

	private static final Logger logger = LoggerFactory.getLogger(PrintController.class);
	private static final String XSL_TEMPLATE = "";

	@Autowired
	private PrintingService printingService;
	@Autowired
	private StudentService studentService;

	@GetMapping("/print")
	public ResponseEntity<byte[]> doPrint() {
		String message;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("text", "html"));
		headers.add("X-Content-Type-Options", "nosniff");

		byte[] byt = null;
		ByteArrayOutputStream out = null;

		Student student = studentService.getStudents().stream().findFirst().orElse(null);
		try {
			String xml = parseXMLString(student);
			Document doc = getDomSourceForXML(xml);
			out = printingService.transfromToPDFBytes(doc, XSL_TEMPLATE);
		} catch (JAXBException | TransformerException e) {
			logger.error(e.getMessage(), e);
		}

		if (out == null) {
			message = "Can't generate PDF to print";
			byt = message.getBytes();
		} else {
			byt = out.toByteArray();
			headers.setContentType(new MediaType("application", "txt"));
		}

		return new ResponseEntity<>(byt, headers, HttpStatus.OK);
	}

	private String parseXMLString(Student student) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Student.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		// output pretty printed
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		StringWriter result = new StringWriter();
		jaxbMarshaller.marshal(student, result);
		return result.toString();
	}

	private Document getDomSourceForXML(String xml) {
		try {
			InputSource is = new InputSource(new StringReader(xml));
			return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

}
