/**
 * 
 */
package com.coderslab.service;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author cyclingbd007
 *
 */
@Service
public class PrintingService {

	private static final Logger logger = LoggerFactory.getLogger(PrintingService.class);

	public ByteArrayOutputStream transfromToPDFBytes(org.w3c.dom.Document doc, String xslTemplate)
			throws TransformerException {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			Source xsltSrc = new StreamSource(this.getClass().getClassLoader().getResourceAsStream(xslTemplate));

			Transformer transformer = TransformerFactory.newInstance().newTransformer(xsltSrc);
			Fop fop = FopFactory.newInstance().newFop(MimeConstants.MIME_PDF, out);
			// Make sure the XSL transformation's result is piped through to FOP
			Result res = new SAXResult(fop.getDefaultHandler());
			// Start the transformation and rendering process
			transformer.transform(new DOMSource(doc), res);
			return out;
		} catch (TransformerException ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		} catch (FOPException ex) {
			logger.error(ex.getMessage(), ex);
			throw new TransformerException(ex.getMessage(), ex);
		}

	}

	public ByteArrayOutputStream transfromToThermalLabel(org.w3c.dom.Document doc, String xslTemplate)
			throws TransformerException {
		try {
			// Setup a buffer to obtain the content length
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			Source xsltSrc = new StreamSource(this.getClass().getClassLoader().getResourceAsStream(xslTemplate));
			Transformer transformer = TransformerFactory.newInstance().newTransformer(xsltSrc);
			// Start the transformation and rendering process
			transformer.transform(new DOMSource(doc), new StreamResult(out));
			return out;
		} catch (TransformerException ex) {
			logger.error("Transformation exeption. Actual error message is :" + ex.getMessage());
			throw ex;
		}
	}
}
