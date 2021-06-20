package com.xiao.sweb.filter;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Log4j2
public class LoggerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest
                && servletResponse instanceof HttpServletResponse){
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            String agent = request.getHeader("User-Agent");

            HttpServletRequest requestToCache = new ContentCachingRequestWrapper(request);
            HttpServletResponse responseToCache = new ContentCachingResponseWrapper(response);
            filterChain.doFilter(requestToCache, responseToCache);

            String requestData = getRequestData(requestToCache);
            String responseData = getResponseData(responseToCache);

            Map<String, Object> trace = getTrace(request);
            trace.put("reqBody", requestData);
            trace.put("resBody", responseData);
            String tk = request.getHeader("SID");
            String uid = request.getHeader("UID");
            if (tk == null){
                if (request.getCookies() != null && request.getCookies().length > 0){
                    tk = Stream.of(request.getCookies()).filter(x->"SID".equals(x.getName()))
                            .map(x->x.getValue()).collect(Collectors.joining());
                    uid = Stream.of(request.getCookies()).filter(x->"UID".equals(x.getName()))
                            .map(x->x.getValue()).collect(Collectors.joining());
                }
            }
            trace.put("SID", tk);
            trace.put("UID", uid);
            log.info("api revoked data ------------->{}",trace.toString());
        }else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    private String getRequestData(HttpServletRequest requestToCache) throws UnsupportedEncodingException {
        String payload = null;
        ContentCachingRequestWrapper requestWrapper = WebUtils
                .getNativeRequest(requestToCache, ContentCachingRequestWrapper.class);
        if (requestWrapper != null){
            byte[] buf = requestWrapper.getContentAsByteArray();
            if (buf.length > 0){
                payload = new String(buf, 0, buf.length, requestWrapper.getCharacterEncoding());
            }
        }
        return payload;
    }

    private String getResponseData(HttpServletResponse responseToCache) throws IOException {
        String payload = null;
        ContentCachingResponseWrapper responseWrapper = WebUtils
                .getNativeResponse(responseToCache, ContentCachingResponseWrapper.class);
        if (responseWrapper != null){
            byte[] buf = responseWrapper.getContentAsByteArray();
            if (buf.length > 0){
                payload = new String(buf, 0, buf.length, responseWrapper.getCharacterEncoding());
                responseWrapper.copyBodyToResponse();
            }
        }
        return payload;
    }

    private Map<String, Object> getTrace(HttpServletRequest request) {
        Throwable exception = (Throwable) request.getAttribute("javax.servlet.error.exception");
        Principal userPrincipal = request.getUserPrincipal();
        Map<String, Object> trace = new LinkedHashMap<>();
        Map<String, Object> headers = new LinkedHashMap<>();
        trace.put("method", request.getMethod());
        trace.put("path", request.getRequestURI());
        headers.put("request", getRequestHeaders(request));
        trace.put("headers", headers);
        trace.put("parameter", getParameterMapCopy(request));
        if (exception != null){
            trace.put("exception", exception.getStackTrace());
        }
        return trace;
    }

    private Object getRequestHeaders(HttpServletRequest request) {
        Map<String, Object> headers = new LinkedHashMap<>();
        Enumeration<String> names = request.getHeaderNames();
        while (names.hasMoreElements()){
            String name = names.nextElement();
            headers.put(name, getHeaderValue(request, name));
        }
        return headers;
    }

    private Object getHeaderValue(HttpServletRequest request, String name) {
        List<String> values = Collections.list(request.getHeaders(name));
        if (values.size() == 1){
            return values.get(0);
        }
        if (values.isEmpty()){
            return "";
        }
        return values;
    }

    private Object getParameterMapCopy(HttpServletRequest request) {
        return new LinkedHashMap<String, String[]>(request.getParameterMap());
    }

    @Override
    public void destroy() {

    }
}
