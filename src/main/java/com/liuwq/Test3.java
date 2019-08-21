package com.liuwq;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description:
 * @author: liuwq
 * @date: 2019/7/17 0017 下午 12:39
 * @version: V1.0
 */
public class Test3 {

    public static void main(String[] args) {
        String str = underlineToCamel("PAY_FEE_MODE_CODE");
        System.out.println(str);
        String str2 = camel2Underline(str);
        System.out.println(str2);
    }

    /** 字符串 驼峰-->下划线大写 **/
    public static String camel2Underline(String line) {
        if (line == null || "".equals(line)) {
            return "";
        }
        line = String.valueOf(line.charAt(0)).toUpperCase().concat(line.substring(1));
        StringBuffer sb = new StringBuffer();
        Pattern pattern = Pattern.compile("[A-Z]([a-z\\d]+)?");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            String word = matcher.group();
            sb.append(word.toUpperCase());
            sb.append(matcher.end() == line.length() ? "" : "_");
        }
        return sb.toString();
    }

    /** 字符串 下划线大写-->驼峰 **/
    public static String underlineToCamel(String name) {

        StringBuilder result = new StringBuilder();

        if (name == null || name.isEmpty()) {
            return "";
        }
        else if (!name.contains("_")) {
            return name.toLowerCase();
        }

        String camels[] = name.split("_");

        for (String camel : camels) {
            if (camel.isEmpty()) {
                continue;
            }

            if (result.length() == 0) {
                result.append(camel.toLowerCase());
            }
            else {
                result.append(camel.substring(0, 1).toUpperCase());

                result.append(camel.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    /** 对象 驼峰-->下划线大写(非空字段) */
//    public static String toUnderlineJSONString(Object object) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        String reqJson = mapper.writeValueAsString(object);
//        JSONObject json = transToUpperCaseObject(reqJson);
//        return json.toString();
//        /*
//         * JSONObject hostObject = JSONObject.fromObject(reqJson);
//         * @SuppressWarnings("unchecked") Iterator<String> sIterator = hostObject.keys(); JSONObject rsp = new JSONObject(); while (sIterator.hasNext()) { //
//         * 获得key String key = sIterator.next(); // 根据key获得value, value也可以是JSONObject,JSONArray,使用对应的参数接收即可 String value = hostObject.getString(key);
//         * rsp.put(key.toUpperCase(), value); } return rsp.toString();
//         */
//    }
//
//    /** json下划线小写 --> json下划线大写 **/
//    public static JSONObject transToUpperCaseObject(String json) {
//        JSONObject jSONArray2 = new JSONObject();
//        JSONObject jSONArray1 = JSONObject.fromObject(json);
//        @SuppressWarnings("rawtypes")
//        Iterator it = jSONArray1.keys();
//        while (it.hasNext()) {
//            String key = (String) it.next();
//            Object object = jSONArray1.get(key);
//            if (object.getClass().toString().endsWith("JSONObject")) {
//                jSONArray2.accumulate(key.toUpperCase(), transToUpperCaseObject(object.toString()));
//            }
//            else if (object.getClass().toString().endsWith("JSONArray")) {
//                jSONArray2.accumulate(key.toUpperCase(), transToArray(jSONArray1.getJSONArray(key).toString()));
//            }
//            else {
//                jSONArray2.accumulate(key.toUpperCase(), object);
//            }
//        }
//        return jSONArray2;
//    }
//
//    /** json 驼峰转下划线(入参)，下划线转驼峰（出参） **/
//    public static JSONObject transToUpperCaseUndeline(String json, boolean isReq) {
//        JSONObject jSONArray2 = new JSONObject();
//        JSONObject jSONArray1 = JSONObject.fromObject(json);
//        if (null == jSONArray1 || jSONArray1.size() <= 0) {
//            return null;
//        }
//
//        @SuppressWarnings("rawtypes")
//        Iterator it = jSONArray1.keys();
//        while (it.hasNext()) {
//            String key = (String) it.next();
//            Object object = jSONArray1.get(key);
//            boolean isLower = false;
//            char c = key.charAt(0);
//            if (Character.isLowerCase(c)) {
//                isLower = true;
//            }
//
//            if (object.getClass().toString().endsWith("JSONObject")) {
//                if (isReq) {
//                    jSONArray2.accumulate(camel2Underline(key), transToUpperCaseUndeline(object.toString(), true));
//                }
//                else {
//                    if (isLower) {
//                        jSONArray2.accumulate(key, transToUpperCaseUndeline(object.toString(), false));
//                    }
//                    else {
//                        jSONArray2.accumulate(underlineToCamel(key), transToUpperCaseUndeline(object.toString(), false));
//                    }
//                }
//
//            }
//            else if (object.getClass().toString().endsWith("JSONArray")) {
//                if (isReq) {
//                    jSONArray2.accumulate(camel2Underline(key), transToUpperCaseUndelineArray(jSONArray1.getJSONArray(key).toString(), isReq));
//                }
//                else {
//                    if (isLower) {
//                        jSONArray2.accumulate(key, transToUpperCaseUndelineArray(jSONArray1.getJSONArray(key).toString(), isReq));
//                    }
//                    else {
//                        jSONArray2.accumulate(underlineToCamel(key), transToUpperCaseUndelineArray(jSONArray1.getJSONArray(key).toString(), isReq));
//                    }
//                }
//            }
//            else {
//                if (isReq) {
//                    jSONArray2.accumulate(camel2Underline(key), object);
//                }
//                else {
//                    if (isLower) {
//                        jSONArray2.accumulate(key, object);
//                    }
//                    else {
//                        jSONArray2.accumulate(underlineToCamel(key), object);
//                    }
//                }
//            }
//        }
//        return jSONArray2;
//    }
//
//    // json数组 驼峰转下划线
//    public static JSONArray transToUpperCaseUndelineArray(String jsonArray, boolean isReq) {
//        JSONArray jSONArray2 = new JSONArray();
//        JSONArray jSONArray1 = JSONArray.fromObject(jsonArray);
//        for (int i = 0; i < jSONArray1.size(); i++) {
//            Object jArray = jSONArray1.getJSONObject(i);
//            if (jArray.getClass().toString().endsWith("JSONObject")) {
//                JSONObject json = transToUpperCaseUndeline(jArray.toString(), isReq);
//                if (null != json && json.size() > 0) {
//                    jSONArray2.add(json);
//                }
//            }
//            else if (jArray.getClass().toString().endsWith("JSONArray")) {
//                jSONArray2.add(transToUpperCaseUndelineArray(jArray.toString(), isReq));
//            }
//        }
//        return jSONArray2;
//    }

}
