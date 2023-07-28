package com.xiao.sweb.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 * 代码生成器
 *
 * @author 谢仕海
 */
//@Log4j2
public class DvMpGenerator {

  /**
   * 若要同时生成多个表的代码，请以逗号分隔表名输入：tableA,tableB
   */
  public static void main(String[] args) {
    Properties properties = getProperties();
    // 代码生成器
    AutoGenerator mpg = new AutoGenerator();

    // 全局配置
    GlobalConfig gc = new GlobalConfig();
    String projectPath = System.getProperty("user.dir") + "";
    gc.setOutputDir(projectPath + "\\src\\main\\java");
    gc.setAuthor(properties.getProperty("mybatis.plus.global.author"));
    gc.setOpen(false);
    gc.setIdType(IdType.AUTO);
    gc.setFileOverride(true);
    gc.setServiceName("%sService");
    gc.setMapperName("%sDao");
    gc.setSwagger2(true);
    gc.setDateType(DateType.ONLY_DATE);
    mpg.setGlobalConfig(gc);

    // 数据源配置
    DataSourceConfig dsc = new DataSourceConfig();
    dsc.setUrl(properties.getProperty("mybatis.plus.dataSource.url"));
    dsc.setDriverName(properties.getProperty("mybatis.plus.dataSource.driverName"));
    dsc.setUsername(properties.getProperty("mybatis.plus.dataSource.username"));
    dsc.setPassword(properties.getProperty("mybatis.plus.dataSource.password"));
    mpg.setDataSource(dsc);

    PackageConfig pc = new PackageConfig();

    // 包配置
    pc.setModuleName(scanner("模块名（用于生成包）"));
    pc.setParent(properties.getProperty("mybatis.plus.basepackage"));
    pc.setEntity("entity." + pc.getModuleName());
    pc.setController("controlleradmin." + pc.getModuleName());
    pc.setMapper("dao." + pc.getModuleName());
    pc.setService("service." + pc.getModuleName());
    pc.setServiceImpl("service." + pc.getModuleName() + ".impl");
    String moduleName = pc.getModuleName();
    pc.setModuleName(null);
    mpg.setPackageInfo(pc);

    // 自定义配置
    InjectionConfig cfg = new InjectionConfig() {
      @Override
      public void initMap() {
        // to do nothing
      }
    };
    List<FileOutConfig> focList = new ArrayList<>();
    //entity文件
//        focList.add(new FileOutConfig("templates/generator2/entity.java.ftl") {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                return projectPath + "/src/main/java/com/sl/modules/" + pc.getModuleName() + "/model/"
//                        + tableInfo.getEntityName() + StringPool.DOT_JAVA;
//            }
//        });
    //mapper.xml文件
    focList.add(new FileOutConfig("templates\\generator\\mapper.xml.ftl") {
      @Override
      public String outputFile(TableInfo tableInfo) {
        // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
        return projectPath + "\\src\\main\\resources\\mybatis\\mapper\\" + moduleName + "\\"
            + tableInfo.getEntityName() + "Dao" + StringPool.DOT_XML;
      }
    });

    cfg.setFileOutConfigList(focList);
    mpg.setCfg(cfg);
    TemplateConfig templateConfig = new TemplateConfig();
    templateConfig.setController("templates\\generator\\controller.java");
    //entity在上面已经自定义了，这里设置为空
    templateConfig.setEntity("templates\\generator\\entity.java");
    templateConfig.setMapper("templates\\generator\\mapper.java");
    templateConfig.setService("templates\\generator\\service.java");
    templateConfig.setServiceImpl("templates\\generator\\serviceImpl.java");
    templateConfig.setXml(null);
    mpg.setTemplate(templateConfig);

    // 策略配置
    StrategyConfig strategy = new StrategyConfig();
    strategy.setNaming(NamingStrategy.underline_to_camel);
    strategy.setColumnNaming(NamingStrategy.underline_to_camel);
    String scanner = scanner("表名");
    String[] split = scanner.toString().split(",");
    strategy.setInclude(split);
    strategy.setControllerMappingHyphenStyle(true);
    strategy.setRestControllerStyle(true);
    strategy.setTablePrefix("t_");
    strategy.setEntityLombokModel(true);

    List<TableFill> tableFillList = new ArrayList<>();
    tableFillList.add(new TableFill("gmt_create", FieldFill.INSERT));
    tableFillList.add(new TableFill("user_create_id", FieldFill.INSERT));
    tableFillList.add(new TableFill("gmt_modified", FieldFill.UPDATE));
    tableFillList.add(new TableFill("user_modified_id", FieldFill.UPDATE));
    strategy.setLogicDeleteFieldName("flag");
    strategy.setVersionFieldName("version");
    strategy.setTableFillList(tableFillList);
    strategy.setEntityBooleanColumnRemoveIsPrefix(true);
    mpg.setStrategy(strategy);
    // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
    mpg.setTemplateEngine(new FreemarkerTemplateEngine());
    mpg.execute();
  }

  /**
   * <p>
   * 读取控制台内容
   * </p>
   */
  @SuppressWarnings("resource")
  public static String scanner(String tip) {
    Scanner scanner = new Scanner(System.in);
    StringBuilder help = new StringBuilder();
    help.append("请输入" + tip + "：");
    System.out.println(help.toString());
    if (scanner.hasNext()) {
      String ipt = scanner.next();
      if (StringUtils.isNotBlank(ipt)) {
        return ipt;
      }
    }
    throw new MybatisPlusException("请输入正确的" + tip + "！");
  }

  private static Properties getProperties() {
    Properties properties = new Properties();
    InputStreamReader in = null;
    InputStream inputStream = null;
    try {
      inputStream = DvMpGenerator.class.getClassLoader()
              .getResourceAsStream("properties/mybatis-plus-generator.properties");
      in = new InputStreamReader(inputStream, "UTF-8");
      properties.load(in);
    } catch (IOException e) {
//      log.error(e);
    } finally {
      if (in != null) {
        try {
          in.close();
        } catch (IOException e) {
//          log.error(e);
        }
      }
      if (inputStream != null) {
        try {
          inputStream.close();
        } catch (IOException e) {
//          log.error(e);
        }
      }
    }
    return properties;
  }
}
