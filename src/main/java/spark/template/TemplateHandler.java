package spark.template;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateDateModel;
import freemarker.template.TemplateException;

public class TemplateHandler {

	private final static Logger logger = LoggerFactory.getLogger(TemplateHandler.class);

	private static Configuration templateConfiguration = new Configuration();

	static {
		try {
			templateConfiguration.setDirectoryForTemplateLoading(new File("target/classes/template"));
			DefaultObjectWrapper defaultWrapper = new DefaultObjectWrapper();
			defaultWrapper.setDefaultDateType(TemplateDateModel.DATETIME);
			templateConfiguration.setObjectWrapper(defaultWrapper);
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
	}

	public static void outputModelToTemplate(OutputStream outputStream, Map<String, Object> outputModel,
			String templateName) throws IOException, TemplateException {
		Template outputTemplate = templateConfiguration.getTemplate(templateName);
		Writer out = new OutputStreamWriter(outputStream);
		outputTemplate.process(outputModel, out);
		out.flush();
	}

}
