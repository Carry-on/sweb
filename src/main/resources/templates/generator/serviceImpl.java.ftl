package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;
import com.lianren.doc.pay.vo.req.BaseQueryRequest;
import com.lianren.doc.pay.utils.PageUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @date ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    @Override
    public PageUtils queryPage(BaseQueryRequest queryRequest) {
        IPage<${entity}> ipage = new Page<>(queryRequest.getPage(), queryRequest.getRows());
        List<${entity}> list = baseMapper.query(ipage);
        ipage.setRecords(list);
        return new PageUtils(ipage);
     }

}
</#if>
