package com.vn.osp.notarialservices.common.exception;

public class NoSuchException extends ObjectNotFoundException{
    public NoSuchException(final Object identifier) {
        super("Data with [ " + identifier + " ] not found");
    }
}