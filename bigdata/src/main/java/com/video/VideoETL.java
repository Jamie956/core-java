//package com.video;
//
//public class VideoETL {
//
//    public static String lineETL(String org) {
//        String[] items = org.split("\t");
//        if (items.length < 9) {
//            return null;
//        }
//        StringBuilder sb = new StringBuilder();
//        //去除空格 a & b -> a&b
//        items[3] = items[3].replace(" ", "");
//        for (int i = 0; i < items.length; i++) {
//            if (i < 9) {
//                //前面9个以\t分隔
//                if (i == items.length - 1) {
//                    sb.append(items[i]);
//                } else {
//                    sb.append(items[i]).append("\t");
//                }
//            } else {
//                //处理相关id，以&连接
//                if (i == items.length - 1) {
//                    sb.append(items[i]);
//                } else {
//                    sb.append(items[i]).append("&");
//                }
//            }
//        }
//
//        return sb.toString();
//    }
//
//    public static void main(String[] args) {
//        String ret = lineETL("K4u3KNo2bs8\tBrookers\t736\tNews & Politics\t207\t40268\t4.31\t587\t353\ta8Ye3cgVdhs\tN-bIscz79RU\tmrtnBl7GmWM\taYcIG0Kmjxs\tQUleM6nbvIw\tOeEL6BEOWKE\tFN9ZOOImjCg\tVdA1aCLRIXE\t-fTO_SYoFCM\tKNdj3ae2Tlk\tqRgnCnFKmZ4\tpgMV3qEsMdA\tgrpvone_ciY\tZ02gCksjA_o\tLF6j2Q2BQJc\t7Qs4pjPd6To\tySMvGJAT_9M");
//    }
//}
