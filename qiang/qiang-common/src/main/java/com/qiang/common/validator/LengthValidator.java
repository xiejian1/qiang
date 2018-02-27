package com.qiang.common.validator;

/**
 * Created by xieqiang_daye on 2018/1/30.
 */


import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;

import java.util.Set;

/***
 * 长度检验
 * */
public class LengthValidator extends ValidatorHandler<String> implements Validator<String> {

    private int min;
    private int max;
    private String filedName;

    public LengthValidator(int min,int max,String filedName){
        this.min =min;
        this.max =max;
        this.filedName = filedName;
    }
    @Override
    public boolean validate(ValidatorContext context, String s) {
        if(null==s||s.length()>max||s.length()<min){
            context.addError(ValidationError.create(String.format("%s长度必须介于%s~%s之间!",filedName,min,max))
                    .setErrorCode(-1)
                    .setField(filedName)
                    .setInvalidValue(s));
            return  false;
        }
        return  true;
    }
}
