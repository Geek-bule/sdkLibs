package com.herman.gameserver.common.exception;

import com.herman.common.bean.ResponseBean;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一异常捕获
 * Created by herman on 2018/1/17.
 */
public class UnifiedExceptionHandler extends SimpleMappingExceptionResolver {

    private static final Logger logger = Logger.getLogger(UnifiedExceptionHandler.class);

    /**
     * 异常截获及统一处理
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 输出异常详细到日志文件中
        logger.error(ex.getMessage());
        // 判断网络访问类型，页面或者JSON
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        ResponseBody body = handlerMethod.getMethodAnnotation(ResponseBody.class);

        // 如果是页面的场合迁移到错误页面
        if (body == null) {
            ModelAndView error = new ModelAndView("error");
            error.addObject("content", "系统错误，请与管理员联系");
            return error;
        }

        // JSON的场合，返回异常JSON
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");

        ResponseBean result = new ResponseBean();
        result.serverError();
        try {
            PrintWriter writer = response.getWriter();
            writer.write(JSONObject.fromObject(result).toString());
            writer.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return new ModelAndView();

    }

}
