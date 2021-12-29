package com.islet.domain.dto.mail;

import com.islet.domain.dto.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author tangJM.
 * @date 2021/12/28
 * @description
 */
@Data
public class ExportDTO extends BaseDTO {

    /**
     * 邮箱ID
     */
    @NotNull(message = "请选择邮箱")
    private List<Long> ids;

    @NotBlank(message = "起始时间不能为空")
    private String startTime;

    @NotBlank(message = "终止时间不能为空")
    private String endTime;

}
