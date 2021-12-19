package com.jamie.design.pattern.factory.method2;

/**
 * Share模版接口
 */
public interface Share {
    String getShareFunctionType();
    String mainProcess(String shareName);
}