package com.zzm.sqlite.utils;

import com.zzm.sqlite.core.BindEntity;
import com.zzm.sqlite.core.BindField;
import com.zzm.sqlite.core.BindFieldIgnore;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: zhuzhaoman
 * @date: 2020-12-28
 * @description:
 **/
@Component
public class ValueObjectTransfer {

    public Object cast(Object pojo, Class classVO) {

        try {
            Object resultVO = classVO.newInstance();

            if (!classVO.isAnnotationPresent(BindEntity.class)) {
                //需要转换的PO与VO所绑定的PO不一致
                return null;
            }

            for (Field field : classVO.getDeclaredFields()) {
                field.setAccessible(true);
                Class type = field.getType();
                String name = field.getName();
                // 判断是否包含声明为BindFieldName注解的属性
                if (field.isAnnotationPresent(BindField.class)) {
                    BindField bindField = field.getAnnotation(BindField.class);
                    // 反射调用绑定的PO属性的get方法进行取值
                    Object value = invokeGetBindField(pojo, bindField);
                    // 反射调用执行该VO属性的set方法设置值
                    if (type.isInstance(value)) {
                        invokeSetBindField(resultVO, name, value);
                    }
                } else {
                    BindFieldIgnore bindFieldIgnore = field.getAnnotation(BindFieldIgnore.class);

                    if (bindFieldIgnore != null) {
                        continue;
                    } else {
                        Object value = invokeGet(pojo, name);
                        invokeSet(resultVO, name, value);
                    }
                }
            }

            return resultVO;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @SneakyThrows
    private Object invokeGet(Object pojo, String fileName) {
        Method method = getMethod(pojo.getClass(), fileName);
        Object result = method.invoke(pojo, new Object[0]);

        return result;
    }

    private void invokeSet(Object vo, String fieldName, Object value) {
        Method method = setMethod(vo.getClass(), fieldName);
        try {
            method.invoke(vo, new Object[]{value});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    private Object invokeGetBindField(Object pojo, BindField bindField) {

        String[] fieldList = bindField.fieldName().split(",");

        if (fieldList.length == 1) {
            Method method = getMethod(pojo.getClass(), bindField.fieldName());
            Object value = method.invoke(pojo, new Object[0]);

            Class<?> aClass = bindField.value();
            Method method1 = aClass.getMethod(bindField.methodName(), value.getClass());
            Object result = method1.invoke(aClass.newInstance(), value);

            return result;
        } else {

            List<Object> values = Arrays.stream(fieldList).map(field -> {
                Method method = getMethod(pojo.getClass(), field);
                Object value = null;
                try {
                    value = method.invoke(pojo, new Object[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return value;
            }).collect(Collectors.toList());

            Class<?> aClass = bindField.value();
            Method method1 = aClass.getMethod(bindField.methodName(), values.getClass());
            Object result = method1.invoke(aClass.newInstance(), values);

            return result;
        }
    }

    private void invokeSetBindField(Object vo, String fieldName, Object value) {
        Method method = setMethod(vo.getClass(), fieldName);
        try {
            method.invoke(vo, new Object[]{value});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Method getMethod(Class objectClass, String fieldName) {
        StringBuffer sb = new StringBuffer();
        sb.append("get");
        sb.append(fieldName.substring(0, 1).toUpperCase());
        sb.append(fieldName.substring(1));
        try {
            return objectClass.getMethod(sb.toString());
        } catch (Exception e) {
        }
        return null;
    }

    private Method setMethod(Class objectClass, String fieldName) {

        try {
            Class[] parameterTypes = new Class[1];
            Field field = objectClass.getDeclaredField(fieldName);
            parameterTypes[0] = field.getType();
            StringBuffer sb = new StringBuffer();
            sb.append("set");
            sb.append(fieldName.substring(0, 1).toUpperCase());
            sb.append(fieldName.substring(1));
            Method method = objectClass.getMethod(sb.toString(), parameterTypes);
            return method;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

}
