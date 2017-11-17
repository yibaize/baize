package org.baize.utils.excel;
import org.baize.dao.model.PlayerDataTable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 作者： 白泽
 * 时间： 2017/11/7.
 * 描述：
 */
public class User {
    private final String name;
    private final int id;
    private final float age;
    private final List<User> arr;
    public User() {
        this.name = "ewrfaewfdsf";
        this.id = 10;
        this.age = 20F;
        arr = null;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public float getAge() {
        return age;
    }

    public List<User> getArr() {
        return arr;
    }
    private List<User> arrs(String s){
        List<User> a = new ArrayList<>();
        User t = new User();
        a.add(t);
        return a;
    }
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", age=" + age +
                ", arr=" + arr +
                '}';
    }

    public static void main(String[] args) throws Exception {
        PlayerDataTable dataTable = new PlayerDataTable();
        modify(dataTable,"name","asd");
        modify(dataTable,"id","456");
    }

    /**
     * 反射final变量
     * @param o
     * @param fieldName
     * @param newValue
     */
    private static void modify(Object o, String fieldName, String newValue){
        try {
            Field field = o.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            Class<?> clazz = field.getType();
            Object value = null;
            System.out.println(field.getType() == String.class);
            if(field.getType().isArray() || (field.getGenericType() instanceof ParameterizedType)){
                Method method = User.class.getDeclaredMethod("arrs",new Class[]{String.class});
                value = method.invoke(o,newValue);
            }
            if(clazz == int.class) {
                value = Integer.valueOf(newValue);
            }
            else if(clazz == float.class)
                value = Float.valueOf(newValue);
            else if(clazz == String.class)
                value = newValue;
            field.set(o,value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
