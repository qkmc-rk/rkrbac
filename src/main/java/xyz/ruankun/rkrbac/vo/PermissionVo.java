package xyz.ruankun.rkrbac.vo;

import lombok.Data;
import lombok.ToString;
import xyz.ruankun.rkrbac.model.Permission;

import java.util.Date;
import java.util.List;

/**
 * @author: mrruan
 * @date: 2019-09-28 12:49
 * @description:
 */
@Data
@ToString
public class PermissionVo extends Permission {

    private Integer id;

    private String name;

    private String url;

    private Integer type;

    private Integer parentId;

    private Date createTime;

    private Date updateTime;

    private List<PermissionVo> children;
}
