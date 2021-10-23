package com.islet.controller;

/**
 * @author tangJM.
 * @date 2021/10/15
 * @description
 */
public abstract class AbstractController {

    /**
     * 操作人 Id
     *
     * @return
     */
    protected Long getUserId() {
        return null;
    }

    /**
     * 操作人名字
     *
     * @return
     */
    protected String getCreateName() {
        return null;
    }
}

