package com.islet.domain.dto.base;

import com.islet.domain.dto.BaseDTO;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author tangJM.
 * @date 2021/12/15
 * @description
 */
@Data
@ToString
public class UserDeleteDTO extends BaseDTO {

    @NotNull(message = "请选择要删除数据")
    private List<Long> ids;
}
