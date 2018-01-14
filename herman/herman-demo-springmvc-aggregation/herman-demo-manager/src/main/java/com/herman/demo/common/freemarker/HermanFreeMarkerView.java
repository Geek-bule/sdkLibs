package com.herman.demo.common.freemarker;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by herman on 2017/11/27.
 */
public class HermanFreeMarkerView extends FreeMarkerView {
	private static final String BASE_PATH = "basePath";

	@Override
	protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request)
			throws Exception {
		String webRootPath = request.getContextPath();
		String port = "";
		if(80 == request.getServerPort()||443==request.getServerPort()){
			port = "";
		}else{
			port =  ":"+Integer.toString(request.getServerPort());
		}
		String basePath = request.getScheme()+"://"+request.getServerName() + port + webRootPath;
		model.put(BASE_PATH, basePath);
		super.exposeHelpers(model, request);
	}

}
