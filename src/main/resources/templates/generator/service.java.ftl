package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import com.lianren.doc.pay.vo.req.BaseQueryRequest;
import com.lianren.doc.pay.utils.PageUtils;
/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @date ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

 /**
 * 分页查找数据
 * @param queryRequest
 * @return
 */
 PageUtils queryPage(BaseQueryRequest queryRequest);
}
</#if>
