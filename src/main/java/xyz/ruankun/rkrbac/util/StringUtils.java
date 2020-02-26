package xyz.ruankun.rkrbac.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: mrruan
 * @date: 2018/11/18 20:53
 * @description: 自定义StringUtils类
 * // TODO 深度封装
 * org.springframework.util.StringUtils
 * org.apache.commons.lang3.StringUtils
 * org.apache.commons.lang.StringUtils
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * 将str转换为整型集合
     *
     * @param str demo: "1,2,3,4"
     * @return
     */
    public static List<Integer> strToList(String str) {
        String[] idArray = split(str, ",");
        List<Integer> idList = new ArrayList<>();
        for (String id : idArray) {
            idList.add(Integer.valueOf(id));
        }
        return idList;
    }
}
