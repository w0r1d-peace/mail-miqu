package com.islet.model.mail;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 链路表
 * </p>
 *
 * @author tangJM.
 * @since 2021-10-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Vpn implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 节点id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 节点名称
     */
    private String name;

    /**
     * 节点组合
     */
    private String nodeGroup;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    @Version
    private Date modified;

    /**
     * 逻辑删除 0-否 1-是
     */
    @TableLogic
    private Boolean removed;

    /**
     * 虫洞json
     */
    private String links;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 创建人
     */
    private String creator;


}
