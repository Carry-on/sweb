package ${package.Controller};


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};

import lombok.extern.log4j.Log4j2;
import com.lianren.doc.common.enums.ResultEnum;
import com.lianren.doc.pay.exception.ServiceException;
import com.lianren.doc.pay.vo.res.RestfulResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;
import com.lianren.doc.pay.vo.req.BaseQueryRequest;
import com.lianren.doc.pay.utils.PageUtils;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment!} 支付服务控制器
 * </p>
 *
 * @author ${author}
 * @date ${date}
 */
<#if swagger2>
@Api(tags = "${table.comment!}")
</#if>
@Log4j2
<#if restControllerStyle>
@RestController
<#else>
@RestController
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

	@Autowired
	${table.serviceName} ${table.serviceName?uncap_first};

	@ApiOperation("新增")
	@PostMapping
	public RestfulResponse<${entity}> save(@RequestBody @ApiParam ${entity} ${entity?uncap_first}) {
		RestfulResponse restfulResponse = new RestfulResponse();
        try {
        	restfulResponse.setData(${table.serviceName?uncap_first}.save(${entity?uncap_first}));
        }catch (ServiceException se){
        	restfulResponse.setCode(se.getCode());
        	restfulResponse.setMessage(se.getMsg());

        }  catch (Exception e) {
      log.error(e);
        	restfulResponse.setCode(ResultEnum.SERVER_ERROR.getCode());
        	restfulResponse.setMessage(ResultEnum.SERVER_ERROR.getMsg());

        }
        return restfulResponse;
	}

	@ApiOperation("修改")
	@PutMapping
	public RestfulResponse<${entity}> update(@RequestBody @ApiParam ${entity} ${entity?uncap_first}) {
		RestfulResponse restfulResponse = new RestfulResponse();
        try {
        	restfulResponse.setData(${table.serviceName?uncap_first}.updateById(${entity?uncap_first}));
        }catch (ServiceException se){
        	restfulResponse.setCode(se.getCode());
        	restfulResponse.setMessage(se.getMsg());

        }  catch (Exception e) {
      log.error(e);
        	restfulResponse.setCode(ResultEnum.SERVER_ERROR.getCode());
        	restfulResponse.setMessage(ResultEnum.SERVER_ERROR.getMsg());

        }
        return restfulResponse;
	}
	
	@ApiOperation("分页")
	@PostMapping("page")
	public RestfulResponse<PageUtils<${entity}>> findAll(@RequestBody BaseQueryRequest queryRequest) {
        RestfulResponse restfulResponse = new RestfulResponse();
        try {
        	PageUtils data = ${table.serviceName?uncap_first}.queryPage(queryRequest);
        	restfulResponse.setData(data);
        }catch (ServiceException se){
        	restfulResponse.setCode(se.getCode());
        	restfulResponse.setMessage(se.getMsg());

        }  catch (Exception e) {
      log.error(e);
        	restfulResponse.setCode(ResultEnum.SERVER_ERROR.getCode());
        	restfulResponse.setMessage(ResultEnum.SERVER_ERROR.getMsg());

        }
        return restfulResponse;
	}
	
	@ApiOperation("查询所有数据")
    @GetMapping
    public RestfulResponse<List<${entity}>> list() {
        RestfulResponse restfulResponse = new RestfulResponse();
        try {
        	restfulResponse.setData(${table.serviceName?uncap_first}.list(Wrappers.emptyWrapper()));
        }catch (ServiceException se){
        	restfulResponse.setCode(se.getCode());
        	restfulResponse.setMessage(se.getMsg());

        }  catch (Exception e) {
      log.error(e);
        	restfulResponse.setCode(ResultEnum.SERVER_ERROR.getCode());
        	restfulResponse.setMessage(ResultEnum.SERVER_ERROR.getMsg());

        }
        return restfulResponse;
    }
	
	@ApiOperation("详情")
	@GetMapping("{id}")
	public RestfulResponse<${entity}> getById(@PathVariable Long id) {
		RestfulResponse restfulResponse = new RestfulResponse();
        try {
        	restfulResponse.setData(${table.serviceName?uncap_first}.getById(id));
        }catch (ServiceException se){
        	restfulResponse.setCode(se.getCode());
        	restfulResponse.setMessage(se.getMsg());

        }  catch (Exception e) {
      log.error(e);
        	restfulResponse.setCode(ResultEnum.SERVER_ERROR.getCode());
        	restfulResponse.setMessage(ResultEnum.SERVER_ERROR.getMsg());

        }
        return restfulResponse;
	}

	@ApiOperation("删除")
	@DeleteMapping("{id}")
	public RestfulResponse removeById(@PathVariable Long id) {
	    RestfulResponse restfulResponse = new RestfulResponse();
        try {
        	restfulResponse.setData(${table.serviceName?uncap_first}.removeById(id));
        }catch (ServiceException se){
        	restfulResponse.setCode(se.getCode());
        	restfulResponse.setMessage(se.getMsg());

        }  catch (Exception e) {
      log.error(e);
        	restfulResponse.setCode(ResultEnum.SERVER_ERROR.getCode());
        	restfulResponse.setMessage(ResultEnum.SERVER_ERROR.getMsg());

        }
        return restfulResponse;
	}
	
	@ApiOperation("批量删除")
	@PostMapping("removeByIds")
	public RestfulResponse removeByIds(@RequestBody @ApiParam List<Long> ids) {
		RestfulResponse restfulResponse = new RestfulResponse();
        try {
        	restfulResponse.setData(${table.serviceName?uncap_first}.removeByIds(ids));
        }catch (ServiceException se){
        	restfulResponse.setCode(se.getCode());
        	restfulResponse.setMessage(se.getMsg());

        }  catch (Exception e) {
      log.error(e);
        	restfulResponse.setCode(ResultEnum.SERVER_ERROR.getCode());
        	restfulResponse.setMessage(ResultEnum.SERVER_ERROR.getMsg());

        }
        return restfulResponse;
	}
}
</#if>
