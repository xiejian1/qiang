package com.qiang.common.db;

/**
 * Created by xieqiang_daye on 2018/1/14.
 */
/**
 * 多数据源枚举类型
 */
public enum DataBaseEnum {
    /**
     * 主库
     * */
    MASTER("masterDataSource",true),
    /**
     * 从裤
     */
    SLVAE("slaveDataSource",false),;
    //数据源名称
    private  String name;
    //是否默认数据源
    private boolean master;

    DataBaseEnum(String name,boolean master){
        this.name = name;
        this.master = master;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMaster() {
        return master;
    }

    public void setMaster(boolean master) {
        this.master = master;
    }

    public  String getDefault(){
        String defaultDataSource="";
        for (DataBaseEnum dataBaseEnum:DataBaseEnum.values()){
            if(!"".equals(defaultDataSource)){
                break;
            }
            if (dataBaseEnum.master){
                defaultDataSource = dataBaseEnum.getName();
            }
        }
        return  defaultDataSource;
    }
}
