package com.islet.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

/**
 * @author tangJM.
 * @date 2022/1/4
 * @description
 */
@AllArgsConstructor
@Getter
public enum TrojanEnum {

    SENDING(1), SUCCESS(2), FIAL(3);

    private Integer status;

    public static TrojanEnum getByStatus(Integer status) {
        if (!Optional.ofNullable(status).isPresent()) {
            return null;
        }

        for (TrojanEnum trojanEnum : TrojanEnum.values()) {
            if (trojanEnum.getStatus() == status.intValue()) {
                return trojanEnum;
            }
        }

        return null;
    }

}
