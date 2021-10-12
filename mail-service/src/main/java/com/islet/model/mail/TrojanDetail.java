package com.islet.model.mail;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tangJM.
 * @since 2021-10-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TrojanDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 木马详情ID
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 内容
     */
    private String content;

    /**
     * 附件路径
     */
    private String attachment;


}
