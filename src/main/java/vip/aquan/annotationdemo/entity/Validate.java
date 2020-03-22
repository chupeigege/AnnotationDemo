package vip.aquan.annotationdemo.entity;

import vip.aquan.annotationdemo.annotation.Length;

import java.lang.reflect.Field;

/**
 * @author: wcp
 * @date: 2020/3/22 15:43
 * @Description:
 */
public class Validate {

    /**
     * 校验参数规范，需配合@Length使用
     * @param object
     * @return
     */
    public static String validate(Object object) throws IllegalAccessException {
        //获取对象的所有属性
        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            //校验属性有没有标注@Length
            if(field.isAnnotationPresent(Length.class)){
                //获取属性上的注解信息
                Length length = field.getAnnotation(Length.class);
                //添加该设置，在反射时能访问私有变量
                field.setAccessible(true);
                //获得属性值的长度
                int valueLength = ((String) field.get(object)).length();
                //长度检验
                if(valueLength<length.min() || valueLength>length.max()){
                    return length.errorMsg();
                }
            }
        }
        return null;
    }
}
