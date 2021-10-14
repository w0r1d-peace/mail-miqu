package com.islet.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author tangJM.
 * @date 2021/10/14
 * @description
 */
@Getter
@AllArgsConstructor
public enum ConnStatusEnum {
    CONN_NORMAL(1), CONN_EXCEPTION(2);

    private int connStatus;
}
