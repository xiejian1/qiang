package com.qiang.common.base;

import com.github.pagehelper.PageHelper;
import com.qiang.common.db.DataBaseEnum;
import com.qiang.common.db.DynamicDataSource;
import com.qiang.common.util.SpringContextUtil;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.omg.DynamicAny.DynAny;
import org.springframework.http.converter.ObjectToStringHttpMessageConverter;
import org.springframework.security.access.method.P;


import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by xieqiang_daye on 2018/2/6.
 */
public  abstract class BaseServiceImpl<Mapper,Record,Example> implements BaseService<Record,Example> {

    public  Mapper mapper;

    @Override
    public int countByExample(Example example) {
        try{
            DynamicDataSource.setDataSource(DataBaseEnum.SLVAE.getName());
            Method countByExample = mapper.getClass().getDeclaredMethod("countByExample",example.getClass());
            Object result = countByExample.invoke(mapper,example);
            return Integer.parseInt(String.valueOf(result));
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
        return 0;
    }

    @Override
    public int deleteByExample(Example example) {
        try{
            DynamicDataSource.setDataSource(DataBaseEnum.MASTER.getName());
            Method deleteByExample = mapper.getClass().getDeclaredMethod("deleteByExample",example.getClass());
            Object result = deleteByExample.invoke(mapper,example);
            return  Integer.parseInt(String.valueOf(result));
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        try{
            DynamicDataSource.setDataSource(DataBaseEnum.MASTER.getName());
            Method deleteByPrimaryKey  = mapper.getClass().getDeclaredMethod("deleteByPrimaryKey",id.getClass());
            Object result = deleteByPrimaryKey.invoke(mapper,id);
            return  Integer.parseInt(String.valueOf(result));
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
        return 0;
    }

    @Override
    public int insert(Record record) {
        try{
            DynamicDataSource.setDataSource(DataBaseEnum.MASTER.getName());
            Method insert = mapper.getClass().getDeclaredMethod("insert",record.getClass());
            Object result = insert.invoke(mapper,record);
            return  Integer.parseInt(String.valueOf(result));
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
        return 0;
    }

    @Override
    public int insertSelective(Record record) {
        try{
            DynamicDataSource.setDataSource(DataBaseEnum.MASTER.getName());
            Method insertSelective = mapper.getClass().getDeclaredMethod("insertSelective",record.getClass());
            Object result = insertSelective.invoke(mapper,record);
            return  Integer.parseInt(String.valueOf(result));
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
        return 0;
    }

    @Override
    public List<Record> selectByExampleWithBolbs(Example example) {
        try{
            DynamicDataSource.setDataSource(DataBaseEnum.SLVAE.getName());
            Method selectByExampleWithBlobs = mapper.getClass().getDeclaredMethod("selectByExampleWithBlobs",example.getClass());
            Object result = selectByExampleWithBlobs.invoke(mapper,example);
            return (List<Record>) result;
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
        return null;
    }

    @Override
    public List<Record> selectByExample(Example example) {
        try{
            DynamicDataSource.setDataSource(DataBaseEnum.SLVAE.getName());
            Method selectByExample = mapper.getClass().getDeclaredMethod("selectByExample",example.getClass());
            Object result = selectByExample.invoke(mapper,example);
            return (List<Record>) result;
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
        return null;
    }

    @Override
    public List<Record> selectByExampleWithBlobsForStartPage(Example example, Integer pageNum, Integer pageSize) {
        try{
            DynamicDataSource.setDataSource(DataBaseEnum.SLVAE.getName());
            Method selectByExampleWithBLOBs = mapper.getClass().getDeclaredMethod("selectByExampleWithBLOBs",example.getClass());
            PageHelper.startPage(pageNum,pageSize,false);
            Object result = selectByExampleWithBLOBs.invoke(mapper,example);
            return (List<Record>) result;
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
        return null;
    }

    @Override
    public List<Record> selectByExampleForStartPage(Example example, Integer pageNum, Integer pageSize) {
        try {
            DynamicDataSource.setDataSource(DataBaseEnum.SLVAE.getName());
            Method selectByExample = mapper.getClass().getDeclaredMethod("selectByExample",example.getClass());
            PageHelper.startPage(pageNum,pageSize,false);
            Object result = selectByExample.invoke(mapper,example);
            return (List<Record>) result;
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
        return null;
    }

    @Override
    public List<Record> selectByExampleWithBLOBsForOffsetPage(Example example, Integer offset, Integer limit) {
        try{
            DynamicDataSource.setDataSource(DataBaseEnum.SLVAE.getName());
            Method selectByExampleWithBLOBs = mapper.getClass().getDeclaredMethod("selectByExampleWithBLOBs",example.getClass());
            PageHelper.offsetPage(offset,limit,false);
            Object result = selectByExampleWithBLOBs.invoke(mapper,example);
            return (List<Record>) result;
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
        return null;
    }

    @Override
    public List<Record> selectByExampleForOffsetPage(Example example, Integer offset, Integer limit) {
      try{
          DynamicDataSource.setDataSource(DataBaseEnum.SLVAE.getName());
          Method selectByExample = mapper.getClass().getDeclaredMethod("selectByExample",example.getClass());
          PageHelper.offsetPage(offset,limit,false);
          Object result = selectByExample.invoke(mapper,example);
          return (List<Record>) result;
      }catch (IllegalAccessException e){
          e.printStackTrace();
      }catch (InvocationTargetException e){
          e.printStackTrace();
      }catch (NoSuchMethodException e){
          e.printStackTrace();
      }
        DynamicDataSource.clearDataSource();
        return null;
    }

    @Override
    public Record selectFirstByExample(Example example) {
        try{
            DynamicDataSource.setDataSource(DataBaseEnum.SLVAE.getName());
            Method selectByexample = mapper.getClass().getDeclaredMethod("selectByExample",example.getClass());
            List<Record> result = (List<Record>) selectByexample.invoke(mapper,example);
            if(null!=result&&result.size()>0){
                return result.get(0);
            }
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
        return null;
    }

    @Override
    public Record selectFistByExampleWithBolbs(Example example) {
        try{
            DynamicDataSource.setDataSource(DataBaseEnum.SLVAE.getName());
            Method selectByExampleWithBLOBs = mapper.getClass().getDeclaredMethod("selectByExampleWithBLOBs",example.getClass());
            List<Record> result = (List<Record>) selectByExampleWithBLOBs.invoke(mapper,example);
            if(null!=result&&result.size() > 0){
                return result.get(0);
            }
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
        return null;
    }

    @Override
    public Record selectByPrimaryKey(Integer id) {
        try{
            DynamicDataSource.setDataSource(DataBaseEnum.SLVAE.getName());
            Method selectByPrimaryKey = mapper.getClass().getDeclaredMethod("selectByPrimaryKey",id.getClass());
            List<Record> result = (List<Record>) selectByPrimaryKey.invoke(mapper,id);
            if(null!=result&&result.size() > 0 ){
                return result.get(0);
            }
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
        return null;
    }

    @Override
    public int updateByExampleSelective(@Param("record") Record record, @Param("example") Example example) {
        try{
            DynamicDataSource.setDataSource(DataBaseEnum.MASTER.getName());
            Method updateByExampleSelective = mapper.getClass().getDeclaredMethod("updateByExampleSelective",example.getClass());
            Object result = updateByExampleSelective.invoke(mapper,record,example);
            return  Integer.parseInt(String.valueOf(record));
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
        return 0;
    }

    @Override
    public int updateByExampleWithBlobs(@Param("record") Record record, @Param("example") Example example) {
        try{
            DynamicDataSource.setDataSource(DataBaseEnum.MASTER.getName());
            Method updateByExampleWithBLOBs = mapper.getClass().getDeclaredMethod("updateByExampleWithBLOBs",example.getClass());
            Object result = updateByExampleWithBLOBs.invoke(mapper,record,example);
            return Integer.parseInt(String.valueOf(result));
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
        return 0;
    }

    @Override
    public int updateByExample(@Param("record") Record record, @Param("example") Example example) {
        try{
            DynamicDataSource.setDataSource(DataBaseEnum.MASTER.getName());
            Method updateByExample = mapper.getClass().getDeclaredMethod("updateByExample",example.getClass());
            Object result = updateByExample.invoke(mapper,record,example);
            return  Integer.parseInt(String.valueOf(result));
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Record record) {
        try{
            DynamicDataSource.setDataSource(DataBaseEnum.MASTER.getName());
            Method updateByPrimaryKeySelective = mapper.getClass().getDeclaredMethod("updateByPrimaryKeySelective",record.getClass());
            Object result = updateByPrimaryKeySelective.invoke(mapper,record);
            return  Integer.parseInt(String.valueOf(result));
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
        return 0;
    }

    @Override
    public int updateByPrimaryKeyWithBlobs(Record record) {
        try{
            DynamicDataSource.setDataSource(DataBaseEnum.MASTER.getName());
            Method updateByPrimaryKeyWithBLOBs = mapper.getClass().getDeclaredMethod("updateByPrimaryKeyWithBLOBs",record.getClass());
            Object result  = updateByPrimaryKeyWithBLOBs.invoke(mapper,record);
            return  Integer.parseInt(String.valueOf(result));
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Record record) {
        try{
            DynamicDataSource.setDataSource(DataBaseEnum.MASTER.getName());
            Method updateByPrimaryKey = mapper.getClass().getDeclaredMethod("updateByPrimaryKey",record.getClass());
            Object result = updateByPrimaryKey.invoke(mapper,record);
            return Integer.parseInt(String.valueOf(result));
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
        return 0;
    }

    @Override
    public int deleteByPrimaryKeys(String ids) {
        try{
            if (StringUtils.isBlank(ids)){
                return 0;
            }
            DynamicDataSource.setDataSource(DataBaseEnum.MASTER.getName());
            String[] idArray = ids.split("-");
            int count = 0;
            for(String idStr:idArray){
                if(StringUtils.isBlank(idStr)){
                    continue;
                }
                Integer id = Integer.parseInt(idStr);
                Method deleteByPrimaryKey = mapper.getClass().getDeclaredMethod("updateByPrimaryKey",id.getClass());
                Object result = deleteByPrimaryKey.invoke(mapper,id);
                count+=Integer.parseInt(String.valueOf(result));
            }
            return count;
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void intiMapper() {
       this.mapper = SpringContextUtil.getBean(getMapperClass());
    }
    /**
     * 获取泛型class
     * @return
     * */
    public  Class<Mapper> getMapperClass(){
        return (Class<Mapper>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
