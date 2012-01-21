package spark.routes;

import static spark.template.TemplateHandler.outputModelToTemplate;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spark.Request;
import spark.Response;
import spark.Route;

public abstract class ViewRoute extends Route {

	private final static Logger logger = LoggerFactory.getLogger(ViewRoute.class);

	private String viewTemplate;

	public ViewRoute(String path, String initTemplate) {
		super(path);
		this.setViewTemplate(initTemplate);
	}

	@Override
	public Object handle(Request request, Response response) {
		Object returnValue = "";
		try {
			OutputStream outputStream = response.raw().getOutputStream();
			Map<String, Object> outputModel = new HashMap<String, Object>();
			returnValue = handle(request, response, outputModel);
			outputModelToTemplate(outputStream, outputModel, viewTemplate);
		} catch (Exception e) {
			logger.warn(e.getMessage());
			halt();
		}
		return (returnValue != null) ? returnValue : "";
	}

	protected abstract Object handle(Request request, Response response, Map<String, Object> model) throws Exception;

	public String getViewTemplate() {
		return viewTemplate;
	}

	public void setViewTemplate(String viewTemplate) {
		this.viewTemplate = viewTemplate;
	}

}
