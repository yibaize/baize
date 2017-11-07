package org.baize.utils.excel;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 作者： 白泽
 * 时间： 2017/11/7.
 * 描述：
 */
public class User {
    private final String name;
    private final int id;
    private final float age;
    public User() {
        this.name = new String();
        this.id = 0;
        this.age = 0F;
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

    @Override
    public String toString() {
        return "User{" +
                "name=" + name +
                ", id=" + id +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) throws Exception {
        User user = new User();
        Field field = user.getClass().getDeclaredField("name");

        Class<?> clazz = field.getType();
        System.out.println(clazz == String.class);

        modify(user,"name","asd");
        modify(user,"id","456");
        modify(user,"age","4867");
        System.out.println(user);
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
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            Class<?> clazz = field.getType();
            Object value = null;
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
