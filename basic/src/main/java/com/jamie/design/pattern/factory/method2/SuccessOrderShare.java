package com.jamie.design.pattern.factory.method2;

/**
 * 分享成功订单
 * 实现Share 接口
 */
public class SuccessOrderShare implements Share {
    @Override
    public String getShareFunctionType() {
        return EnumShareType.SUCCESS_ORDER.getName();
    }

    @Override
    public String mainProcess(String shareName) {
        //do something
        return shareName;
    }
}
