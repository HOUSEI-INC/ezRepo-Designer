/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.jasperreports.eclipse.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.util.JRLoader;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 */
public class CastorHelper {

	public static Object read(InputStream is, String mappingFile) {
		Object object = null;

		InputStream mis = null;

		try {
			mis = JRLoader.getLocationInputStream(mappingFile);

			Mapping mapping = new Mapping();
			mapping.loadMapping(new InputSource(mis));

			object = read(is, mapping);
		} catch (JRException e) {
			throw new JRRuntimeException(e);
		} finally {
			FileUtils.closeStream(mis);
		}

		return object;
	}

	public static Object read(Node node, String mappingFile) {
		Object object = null;

		InputStream mis = null;

		try {
			mis = JRLoader.getLocationInputStream(mappingFile);

			Mapping mapping = new Mapping();
			mapping.loadMapping(new InputSource(mis));

			object = read(node, mapping);
		} catch (JRException e) {
			throw new JRRuntimeException(e);
		} finally {
			FileUtils.closeStream(mis);
		}

		return object;
	}

	public static Object read(InputStream is, Mapping mapping) {
		Object object = null;

		try {
			Unmarshaller unmarshaller = new Unmarshaller(mapping);// FIXME
																	// initialization
																	// is not
																	// thread
																	// safe
			unmarshaller.setWhitespacePreserve(true);
			object = unmarshaller.unmarshal(new InputSource(is));
		} catch (MappingException e) {
			throw new JRRuntimeException(e);
		} catch (MarshalException e) {
			throw new JRRuntimeException(e);
		} catch (ValidationException e) {
			throw new JRRuntimeException(e);
		}

		return object;
	}

	public static Object read(Node node, Mapping mapping) {
		Object object = null;

		try {
			Unmarshaller unmarshaller = new Unmarshaller(mapping);
			unmarshaller.setWhitespacePreserve(true);
			object = unmarshaller.unmarshal(node);
		} catch (MappingException e) {
			throw new JRRuntimeException(e);
		} catch (MarshalException e) {
			throw new JRRuntimeException(e);
		} catch (ValidationException e) {
			throw new JRRuntimeException(e);
		}

		return object;
	}

	public static Object read(InputStream is, Class<?> clazz) {
		return read(is, getMappingFileName(clazz));
	}

	public static Object read(Node node, Class<?> clazz) {
		return read(node, getMappingFileName(clazz));
	}

	public static void write(Object object, String mappingFile, Writer writer) {
		InputStream mis = null;

		try {
			mis = JRLoader.getLocationInputStream(mappingFile);

			Mapping mapping = new Mapping();
			mapping.loadMapping(new InputSource(mis));

			write(object, mapping, writer);
		} catch (JRException e) {
			throw new JRRuntimeException(e);
		} finally {
			FileUtils.closeStream(mis);
		}
	}

	public static void write(Object object, Mapping mapping, Writer writer) {
		try {
			Marshaller marshaller = new Marshaller(writer);

			marshaller.setMapping(mapping);
			marshaller.setMarshalAsDocument(false);

			marshaller.marshal(object);
		} catch (IOException e) {
			throw new JRRuntimeException(e);
		} catch (MappingException e) {
			throw new JRRuntimeException(e);
		} catch (MarshalException e) {
			throw new JRRuntimeException(e);
		} catch (ValidationException e) {
			throw new JRRuntimeException(e);
		}
	}

	public static void write(Object object, String mappingFile, File file) {
		Writer writer = null;

		try {
			writer = new FileWriter(file);
			write(object, mappingFile, writer);
		} catch (IOException e) {
			throw new JRRuntimeException(e);
		} finally {
			FileUtils.closeStream(writer);
		}
	}

	public static void write(Object object, Mapping mapping, File file) {
		Writer writer = null;

		try {
			writer = new FileWriter(file);
			write(object, mapping, writer);
		} catch (IOException e) {
			throw new JRRuntimeException(e);
		} finally {
			FileUtils.closeStream(writer);
		}
	}

	public static String write(Object object, String mappingFile) {
		StringWriter writer = new StringWriter();

		try {
			write(object, mappingFile, writer);
		} finally {
			FileUtils.closeStream(writer);
		}

		return writer.toString();
	}

	public static String write(Object object, Mapping mapping) {
		StringWriter writer = new StringWriter();

		try {
			write(object, mapping, writer);
		} finally {
			FileUtils.closeStream(writer);
		}
		String res = writer.toString();
		return res;
	}

	public static String write(Object object) {
		StringWriter writer = new StringWriter();

		try {
			write(object, getMappingFileName(object.getClass()), writer);
		} finally {
			FileUtils.closeStream(writer);
		}

		return writer.toString();
	}

	/**
	 *
	 */
	private static String getMappingFileName(Class<?> clazz) {
		return clazz.getName().replace(".", "/") + ".xml";
	}

}
