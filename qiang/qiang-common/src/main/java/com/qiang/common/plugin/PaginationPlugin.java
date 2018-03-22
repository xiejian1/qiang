package com.qiang.common.plugin;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;

import java.util.List;

/**MYSQL分页插件
 * Created by xieqiang-daye on 2018/3/22.
 */
public class PaginationPlugin extends PluginAdapter{

    @Override
    public boolean validate(List<String> list) {
        return true;
    }
    /**
     * 为每个example类添加limit和offset属性和setter，getter方法
     * */
    public boolean modeExampleClassGenerator(TopLevelClass topLevelClass, IntrospectedTable introspectedTable){
        PrimitiveTypeWrapper integerWrapper = FullyQualifiedJavaType.getIntInstance().getPrimitiveTypeWrapper();
        Field limie = new Field();
        limie.setName("limit");
        limie.setVisibility(JavaVisibility.PRIVATE);
        limie.setType(integerWrapper);
        topLevelClass.addField(limie);

        Method setLimit = new Method();
        setLimit.setVisibility(JavaVisibility.PUBLIC);
        setLimit.setName("setLimit");
        setLimit.addParameter(new Parameter(integerWrapper,"limit"));
        setLimit.addBodyLine("this.limit = limit");
        topLevelClass.addMethod(setLimit);


        Method getLimit = new Method();
        getLimit.setVisibility(JavaVisibility.PUBLIC);
        getLimit.setReturnType(integerWrapper);
        getLimit.setName("getLimit");
        getLimit.addBodyLine("return limit;");
        topLevelClass.addMethod(getLimit);

        Field offset = new Field();
        offset.setName("offset");
        offset.setVisibility(JavaVisibility.PRIVATE);
        offset.setType(integerWrapper);
        topLevelClass.addField(offset);

        Method setOffset = new Method();
        setOffset.setVisibility(JavaVisibility.PUBLIC);
        setOffset.setName("setOffset");
        setOffset.addParameter(new Parameter(integerWrapper, "offset"));
        setOffset.addBodyLine("this.offset = offset;");
        topLevelClass.addMethod(setOffset);

        Method getOffset = new Method();
        getOffset.setVisibility(JavaVisibility.PUBLIC);
        getOffset.setReturnType(integerWrapper);
        getOffset.setName("getOffset");
        getOffset.addBodyLine("return offset;");
        topLevelClass.addMethod(getOffset);

        return true;
    }
}
