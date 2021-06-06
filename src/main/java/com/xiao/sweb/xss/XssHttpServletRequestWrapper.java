package com.xiao.sweb.xss;

import org.owasp.esapi.ESAPI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.regex.Pattern;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    public String[] getParameterValues(String parameter){
        String[] values = super.getParameterValues(parameter);
        if (values == null){
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i=0; i<count; i++){
            encodedValues[i] = cleanXSS(values[i]);
        }
        return encodedValues;
    }

    public String getParameter(String parameter){
        String value = super.getParameter(parameter);
        if (value == null){
            return null;
        }
        return cleanXSS(value);
    }

    public String getHeader(String name){
        String value = super.getHeader(name);
        if (value == null){
            return null;
        }
        return cleanXSS(value);
    }

    private String cleanXSS(String value) {
        if (value != null){
            // 推荐使用ESAPI库来避免脚本攻击
            value = ESAPI.encoder().canonicalize(value);

            // 避免空字符串
            value = value.replaceAll("", "");

            // 避免script标签
            Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // 避免src表达式
            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // 删除单个的</script>标签
            scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // 删除单个<script ...>标签
            scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // 避免 eval(...) 表达式
            scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // 避免 exception(..) 表达式
            scriptPattern = Pattern.compile("exception\\((.*?)\\)", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // 避免 javascript: 表达式
            scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // 避免 vbscript: 表达式
            scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // 避免 onload= 表达式
            scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // 避免 onXX= 表达式
            scriptPattern = Pattern.compile("onerro(.*?)=:", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

        }
        return value;
    }
}
