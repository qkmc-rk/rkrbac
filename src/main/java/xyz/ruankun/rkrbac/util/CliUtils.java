package xyz.ruankun.rkrbac.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: mrruan
 * @date: 2018/11/18 20:53
 * @description: 系统工具类
 */
public class CliUtils {

    /**
     * 复制非空属性
     *
     * @param source
     * @param target
     */
    public static void copyProperties(Object source, Object target) {
        BeanUtil.copyProperties(source, target, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
    }

    /**
     * List转换为Set
     *
     * @param list
     * @return
     */
    public static Set<String> listToSet(List<String> list) {
        Set<String> set = new HashSet<>();
        for (String str : list) {
            if (StringUtils.isBlank(str)) {
                continue;
            }
            set.addAll(Arrays.asList(str.trim().split(",")));
        }
        return set;
    }
}
