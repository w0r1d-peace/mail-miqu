package com.islet.domain.dto.mail;

import com.islet.domain.dto.PageDTO;
import lombok.Data;

/**
 * @author tangJM.
 * @date 2022/1/4
 * @description
 */
@Data
public class TrojanPageDTO extends PageDTO {

    private Integer status;
    private String target;
    private String startTime;
    private String endTime;
}
