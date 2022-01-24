package com.cat.design.pattern.factory.method2;

/**
 * 实现Share接口
 */
public class SuccessOrderShare implements Share {
    /**
     * 获取分享类型
     */
    @Override
    public String getShareFunctionType() {
        return EnumShareType.SUCCESS_ORDER.getName();
    }

    @Override
    public String mainProcess(String shareName) {
        System.out.println("成功订单做了处理");
        return shareName;
    }
}
