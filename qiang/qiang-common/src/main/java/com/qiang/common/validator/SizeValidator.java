package com.qiang.common.validator;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;

/**
 * Created by xieqiang_daye on 2018/2/4.
 */
public class SizeValidator extends ValidatorHandler<String> implements Validator<String>{
    private String filedName;

    private  int min;

    private  int max;

    public SizeValidator(String filedName,int min,int max){
        this.filedName = filedName;
        this.min = min;
        this.max = max;
    }
    public boolean validator(ValidatorContext context,Integer integer){
        if(null==integer||integer.intValue()>max||integer.intValue()<min){
            context.addError(ValidationError.create(String.format("%s必须大于%s,小于%s",filedName,max,min))
            .setErrorCode(-1)
            .setField(filedName)
            .setInvalidValue(integer));
            return false;
        }
        return true;
    }
}
