package com.qiang.common.validator;


import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;

/**
 * Created by xieqiang_daye on 2018/2/4.
 */
public class NotNullValidator extends ValidatorHandler<String> implements Validator<String> {
    private String filedName;
    public NotNullValidator(String filedName){
        this.filedName = filedName;
    }
    public boolean validator(ValidatorContext context,String s){
        if(null==s){
            context.addError(ValidationError.create(String.format("%s不能为空!",filedName))
            .setErrorCode(-1)
            .setField(filedName)
            .setInvalidValue(s));
            return false;
        }
        return true;
    }
}
