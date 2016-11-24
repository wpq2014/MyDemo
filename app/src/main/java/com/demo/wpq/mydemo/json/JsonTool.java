package com.demo.wpq.mydemo.json;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Json工具类
 *
 * @author 武普泉
 * @version 1.0.0
 */

public class JsonTool {

    /**
     * 将JSON字符串封装到对象
     *
     * @param jsonStr 待封装的JSON字符串
     * @param clazz   待封装的实例字节码
     * @return T: 封装JSON数据的对象
     */
    public static <T> T toBean(String jsonStr, Class<T> clazz) {
        try {
            JSONObject job = new JSONObject(jsonStr);
            return parseObject(job, clazz, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * JSONObject 封装到 对象实例
     *
     * @param job 待封装的JSONObject
     * @param c   待封装的实例对象class
     * @param v   待封装实例的外部类实例对象</br>只有内部类存在,外部类时传递null
     * @return T:封装数据的实例对象
     */
    @SuppressWarnings("unchecked")
    private static <T, V> T parseObject(JSONObject job, Class<T> c, V v) {
        T t = null;
        try {
            if (null == v) {
                t = c.newInstance();
            } else {
                Constructor<?> constructor = c.getDeclaredConstructors()[0];
                constructor.setAccessible(true);
                t = (T) constructor.newInstance(v);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            Log.e(JsonTool.class.getSimpleName(),
                    c.toString() + " should provide a default constructor " +
                            "(a public constructor with no arguments)");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Class<?> type = field.getType();
            String name = field.getName();

            // if the object don`t has a mapping for name, then continue
            if (!job.has(name)) continue;

            String typeName = type.getName();
            if (typeName.equals("java.lang.String")) {
                try {
                    String value = job.getString(name);
                    if (value != null && value.equals("null")) {
                        value = "";
                    }
                    field.set(t, value);
                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        field.set(t, "");
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            } else if (typeName.equals("int") ||
                    typeName.equals("java.lang.Integer")) {
                try {
                    field.set(t, job.getInt(name));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (typeName.equals("boolean") ||
                    typeName.equals("java.lang.Boolean")) {
                try {
                    field.set(t, job.getBoolean(name));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (typeName.equals("float") ||
                    typeName.equals("java.lang.Float")) {
                try {
                    field.set(t, Float.valueOf(job.getString(name)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (typeName.equals("double") ||
                    typeName.equals("java.lang.Double")) {
                try {
                    field.set(t, job.getDouble(name));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (typeName.equals("long") ||
                    typeName.equals("java.lang.Long")) {
                try {
                    field.set(t, job.getLong(name));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (typeName.equals("java.util.List") ||
                    typeName.equals("java.util.ArrayList")) {
                try {
                    Object obj = job.get(name);
                    Type genericType = field.getGenericType();
                    String className = genericType.toString().replace("<", "")
                            .replace(type.getName(), "").replace(">", "");
                    Class<?> clazz = Class.forName(className);
                    if (obj instanceof JSONArray) {
                        ArrayList<?> objList = parseArray((JSONArray) obj, clazz, t);
                        field.set(t, objList);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Object obj = job.get(name);
                    Class<?> clazz = Class.forName(typeName);
                    if (obj instanceof JSONObject) {
                        Object parseJson = parseObject((JSONObject) obj, clazz, t);
                        field.set(t, parseJson);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return t;
    }

    /**
     * 将 JSONArray 封装到 ArrayList 对象
     *
     * @param array 待封装的JSONArray
     * @param c     待封装实体字节码
     * @param v     待封装实例的外部类实例对象</br>只有内部类存在,外部类时传递null
     * @return ArrayList<T>: 封装后的实体集合
     */
    @SuppressWarnings("unchecked")
    private static <T, V> ArrayList<T> parseArray(JSONArray array, Class<T> c, V v) {
        ArrayList<T> list = new ArrayList<T>(array.length());
        try {
            for (int i = 0; i < array.length(); i++) {
                if (array.get(i) instanceof JSONObject) {
                    T t = parseObject(array.getJSONObject(i), c, v);
                    list.add(t);
                } else {
                    list.add((T) array.get(i));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
