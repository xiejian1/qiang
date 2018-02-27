package com.qiang.common.base;

/**
 * Created by xieqiang_daye on 2018/2/4.
 */

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * BaseService接口
 * */
public interface BaseService<Record,Example> {
    /**
     * 根据条件查询记录条数
     * */
    int countByExample(Example example);

    /**
     * 根据条件删除记录
     * */
    int deleteByExample(Example example);

    /**
     * 根据主键删除记录
     * */
    int deleteByPrimaryKey(Integer id);
    /**
     * 插入记录
     * */
    int insert(Record record);
    /**
     * 插入记录，有效字段
     * */
    int insertSelective(Record record);
    /**
     * 根据条件查询记录附带Blob字段
     * */
    List<Record> selectByExampleWithBolbs(Example example);
    /**
     * 根据条件查询记录
     * */
    List<Record> selectByExample(Example example);
    /**
     * 根据条件查询记录并按页码分页，附带Blobs字段
     * @param example
     * @param pageNum 页数
     * @param pageSize 每页记录数
     * */
    List<Record> selectByExampleWithBlobsForStartPage(Example example,Integer pageNum,Integer pageSize);
    /**
     * 根据条件查询记录，并按页码分页
     * @param  example 条件
     * @param  pageNum 页数
     * @param  pageSize
     * @return
     * */
    List<Record> selectByExampleForStartPage(Example example,Integer pageNum,Integer pageSize );

    /**
     * 根据条件查询记录并按最后记录数分页,附带BLOB
     * @param example 条件
     * @param offset 跳过条数
     * @param  limit 查询数量
     *@return
     *
     * * */
    List<Record> selectByExampleWithBLOBsForOffsetPage(Example example,Integer offset,Integer limit);
    /**
     * 根据条件查询记录,并按最后记录数分页
     * @param example 条件
     *@param limit
     * @param offset
     * @return
     * */
    List<Record> selectByExampleForOffsetPage(Example example,Integer offset,Integer limit);

    /**
     * 根据条件查询第一条记录
     * @param example
     * @return
     * */
    Record selectFirstByExample(Example example);

    /**
     * 根据条件查询第一条记录，附带BLOBS字段
     * @param example
     * @return
     * */
    Record selectFistByExampleWithBolbs(Example example);

    /**
     * 根据主键查询记录
     * @param id
     * @return
     * */
    Record selectByPrimaryKey(Integer id);

    /**
     * 根据条件更新有效字段
     * @param record
     * @param example
     * @return
     * */
    int updateByExampleSelective(@Param("record")Record record,@Param("example") Example example);

    /**
     * 更具条件更新记录有效字段，附带BLOB字段
     * @param example
     * @param record
     * @return
     * */
    int updateByExampleWithBlobs(@Param("record") Record record,@Param("example")Example example);
    /**
     * 更具条件更新记录
     * @param record
     * @param example
     * @return
     * */
    int updateByExample(@Param("record") Record record,@Param("example") Example example);
    /**
     *  根据主键更新记录有效字段
     *  @param record
     *  @return
     *  * */
    int updateByPrimaryKeySelective(Record record);
    /**
     * 根据主键更新记录，附带BLOB字段
     * @param record
     * @return
     * */
    int updateByPrimaryKeyWithBlobs(Record record);

    /**
     * 根据主键更新记录
     * @param record
     * @return
     * */
    int updateByPrimaryKey(Record record);
    /**
     * 根据主键批量删除记录
     * @param ids
     * @return
     * */
    int deleteByPrimaryKeys(String ids);
    /**
     * 初始化mapper
     * */
    void  intiMapper();
}
