package com.jamie.design.pattern.factory.method2;

/**
 * 工厂方法模式 -- 缓存实例
 */
public class Client {
    public static void main(String[] args) {
        //创建分享模版工厂
        ShareFactory shareFactory = new ShareFactory();
        //添加分享模版
        shareFactory.addShareList(new SuccessOrderShare());
        //获取模版
        Share shareFunction = shareFactory.getShareFunction(EnumShareType.SUCCESS_ORDER.getName());
        String successOrder = shareFunction.mainProcess("Success Order");
        System.out.println(successOrder);
    }
}
