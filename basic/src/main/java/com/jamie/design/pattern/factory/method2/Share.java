package com.jamie.design.pattern.factory.method2;

/**
 * Share 模版
 */
public interface Share {
    String getShareFunctionType();
    String mainProcess(String shareName);
}