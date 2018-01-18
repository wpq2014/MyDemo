package com.demo.wpq.mydemo.json.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Json工具类
 */
public final class JsonUtil {

    private JsonUtil() {
        /* cannot be instantiated */
    }

    public static <T> T parseObject(String json, Class<T> c) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return parseObject(jsonObject, c);
    }

    public static <T> T parseObject(JSONObject jsonObject, Class<T> c) {
        if (null == jsonObject) {
            return null;
        }

        T t = null;
        try {
            t = c.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Class<?> type = field.getType();
            String name = field.getName();
            if (!jsonObject.has(name)) continue;
            String typeName = type.getName();
            switch (typeName) {
                case "java.lang.String": {
                    try {
                        field.set(t, jsonObject.optString(name));
                    } catch (Exception e) {
                        e.printStackTrace();
                        try {
                            field.set(t, "");
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                    break;
                }
                case "boolean":
                case "java.lang.Boolean": {
                    try {
                        field.set(t, jsonObject.optBoolean(name));
                    } catch (Exception e) {
                        e.printStackTrace();
                        try {
                            field.set(t, false);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                    break;
                }
                case "int":
                case "java.lang.Integer": {
                    try {
                        field.set(t, jsonObject.optInt(name));
                    } catch (Exception e) {
                        e.printStackTrace();
                        try {
                            field.set(t, 0);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                    break;
                }
                case "long":
                case "java.lang.Long": {
                    try {
                        field.set(t, jsonObject.optLong(name));
                    } catch (Exception e) {
                        e.printStackTrace();
                        try {
                            field.set(t, 0);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                    break;
                }
                case "float":
                case "java.lang.Float": {
                    try {
                        field.set(t, Float.valueOf(jsonObject.optString(name)));
                    } catch (Exception e) {
                        e.printStackTrace();
                        try {
                            field.set(t, 0);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                    break;
                }
                case "double":
                case "java.lang.Double": {
                    try {
                        field.set(t, jsonObject.optDouble(name));
                    } catch (Exception e) {
                        e.printStackTrace();
                        try {
                            field.set(t, 0);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                    break;
                }
                case "java.util.List":
                case "java.util.ArrayList": {
                    try {
                        Object obj = jsonObject.opt(name);
                        Type genericType = field.getGenericType();
                        String className = genericType.toString().replace("<", "")
                                .replace(type.getName(), "").replace(">", "");
                        Class<?> clazz = Class.forName(className);
                        if (obj instanceof JSONArray) {
                            List<?> objList = parseArray((JSONArray) obj, clazz);
                            field.set(t, objList);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                default: {
                    // JSONObject
                    try {
                        Object obj = jsonObject.opt(name);
                        Class<?> clazz = Class.forName(typeName);
                        if (obj instanceof JSONObject) {
                            Object parseJson = parseObject((JSONObject) obj, clazz);
                            field.set(t, parseJson);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }

            }
        }
        return t;
    }

    public static <T> List<T> parseArray(String json, Class<T> c) {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return parseArray(jsonArray, c);
    }

    public static <T> List<T> parseArray(JSONArray jsonArray, Class<T> c) {
        if (null == jsonArray) {
            return null;
        }

        ArrayList<T> list = new ArrayList<>(jsonArray.length());
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                if (jsonArray.opt(i) instanceof JSONObject) {
                    T t = parseObject(jsonArray.getJSONObject(i), c);
                    list.add(t);
                } else {
                    @SuppressWarnings("unchecked")
                    T t = (T) jsonArray.opt(i); // String或基本数据类型
                    list.add(t);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static <T> String objectToJson(T t) {
        Field[] fields = t.getClass().getDeclaredFields();
        final StringBuilder sb = new StringBuilder(fields.length << 4);
        sb.append("{");

        for (Field field : fields) {
            field.setAccessible(true);
            Class<?> type = field.getType();
            String name = field.getName();

            // 'this$Number' 是内部类的外部类引用(指针)字段
            if (name.contains("this$")) continue;

            String typeName = type.getName();
            switch (typeName) {
                case "java.lang.String": {
                    try {
                        sb.append("\"").append(name).append("\":");
                        sb.append(stringToJson((String) field.get(t)));
                        sb.append(",");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "boolean":
                case "java.lang.Boolean":
                case "int":
                case "java.lang.Integer":
                case "long":
                case "java.lang.Long":
                case "float":
                case "java.lang.Float":
                case "double":
                case "java.lang.Double": {
                    try {
                        sb.append("\"").append(name).append("\":");
                        sb.append(field.get(t));
                        sb.append(",");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "java.util.List":
                case "java.util.ArrayList": {
                    try {
                        List<?> objList = (List<?>) field.get(t);
                        if (null != objList && objList.size() > 0) {
                            sb.append("\"").append(name).append("\":");
                            sb.append("[");
                            String toJson = listToJson((List<?>) field.get(t));
                            sb.append(toJson);
                            sb.setCharAt(sb.length() - 1, ']');
                            sb.append(",");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                default: {
                    try {
                        sb.append("\"").append(name).append("\":");
//                        sb.append("{");
                        sb.append(objectToJson(field.get(t)));
                        sb.setCharAt(sb.length() - 1, '}');
                        sb.append(",");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }

        if (sb.length() == 1) {
            sb.append("}");
        }
        sb.setCharAt(sb.length() - 1, '}');
        return sb.toString();
    }

    public static <T> String listToJson(List<T> objList) {
        final StringBuilder sb = new StringBuilder();
        for (T t : objList) {
            if (t instanceof String) {
                sb.append(stringToJson((String) t));
                sb.append(",");
            } else if (t instanceof Boolean ||
                    t instanceof Integer ||
                    t instanceof Float ||
                    t instanceof Double) {
                sb.append(t);
                sb.append(",");
            } else {
                sb.append(objectToJson(t));
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public static String stringToJson(String str) {
        if (str == null || str.length() == 0) {
            return "\"\"";
        }

        final StringBuilder sb = new StringBuilder(str.length() + 2 << 4);
        sb.append('\"');
        for (int i = 0; i < str.length(); i++) {
            final char c = str.charAt(i);
            switch (c) {
                case '\"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '/':
                    sb.append("\\/");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        sb.append('\"');
        return sb.toString();
    }

}
