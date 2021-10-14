package com.islet.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

/**
 * @author tangJM.
 * @date 2021/10/13
 * @description
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum TaskProtocolTypeEnum {
    IMAP(1), POP3(2), EXCHANGE(3);

    private int protocolType;


    public static TaskProtocolTypeEnum getByProtocolType(Integer protocolType) {
        if (!Optional.ofNullable(protocolType).isPresent()) {
            return null;
        }
        for (TaskProtocolTypeEnum taskProtocolTypeEnum : TaskProtocolTypeEnum.values()) {
            if (taskProtocolTypeEnum.getProtocolType() == protocolType.intValue()) {
                return taskProtocolTypeEnum;
            }
        }
        return null;
    }
}
