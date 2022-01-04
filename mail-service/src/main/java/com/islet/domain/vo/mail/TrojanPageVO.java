package com.islet.domain.vo.mail;

import com.islet.enums.TrojanEnum;
import lombok.Data;

@Data
public class TrojanPageVO {
    private Long id;
    private String source;
    private String target;
    private String title;
    private Integer status;

    public String getStatusName() {
        TrojanEnum trojanEnum = TrojanEnum.getByStatus(this.status);
        switch (trojanEnum) {
            case SENDING:
                return "发送中";
            case SUCCESS:
                return "发送成功";
            case FIAL:
                return "发送失败";
            default:
                return "--";
        }
    }
}
