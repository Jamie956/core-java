package com.cat.pattern.factory.method2;

import java.util.ArrayList;
import java.util.List;

/**
 * 分享工厂
 */
public class ShareFactory {
    /**
     * 缓存
     */
    private List<Share> shareFunctionList;

    /**
     * 遍历缓存获取实例
     */
    public Share getShareFunction(String type) {
        for (Share share : shareFunctionList) {
            if (share.getShareFunctionType().equalsIgnoreCase(type)) {
                return share;
            }
        }
        return null;
    }

    /**
     * 缓存实例
     */
    public void addShareList(Share share){
        if (shareFunctionList == null) {
            shareFunctionList = new ArrayList<>();
        }
        shareFunctionList.add(share);
    }
}
