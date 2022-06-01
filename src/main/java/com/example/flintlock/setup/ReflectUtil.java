package com.example.flintlock.setup;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ReflectUtil {


    /**
     * 设置field的final修饰符
     * @param field
     * @param isFinal 是否使用final 修饰
     * @auth zongf
     * @date 2019-05-13
     */
    public static void setModifierFinal(Field field, boolean isFinal) {

        int modifiers = field.getModifiers();

        try {
            if (isFinal) {
                // 设置为final

                if (!Modifier.isFinal(modifiers)) {
                    Field aClass = field.getClass().getDeclaredField("modifiers");
                    aClass.setAccessible(true);
                    aClass.setInt(field, field.getModifiers() | Modifier.FINAL);
                    aClass.setAccessible(false);
                }
            }else {
                // 接触final
                if (Modifier.isFinal(modifiers)) {
                    Field aClass = field.getClass().getDeclaredField("modifiers");
                    aClass.setAccessible(true);
                    aClass.setInt(field, field.getModifiers() & ~Modifier.FINAL);
                    aClass.setAccessible(false);
                }
            }

            System.out.println("isFinal:" + Modifier.isFinal(field.getModifiers()));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
