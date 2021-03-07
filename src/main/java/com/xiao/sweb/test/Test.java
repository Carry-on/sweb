package com.xiao.sweb.test;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberToCarrierMapper;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.google.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

/**
 * @author xiaoyu
 * @version 1.0
 * @date 2020/11/23 21:58
 */
public class Test {

    private static PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

    private static PhoneNumberToCarrierMapper carrierMapper = PhoneNumberToCarrierMapper.getInstance();

    private static PhoneNumberOfflineGeocoder geocoder = PhoneNumberOfflineGeocoder.getInstance();

    /**
     * 根据国家代码和手机号  判断手机号是否有效
     * @param phoneNumber
     * @param countryCode
     * @return
     */
    public static boolean checkPhoneNumber(String phoneNumber, String countryCode){
//        int ccode = StringUtils.obj2Int(countryCode);
//        long phone = StringUtils.toLong(phoneNumber);
        int ccode = Integer.valueOf(countryCode);
        long phone = Long.valueOf(phoneNumber);
        PhoneNumber pn = new PhoneNumber();
        pn.setCountryCode(ccode);
        pn.setNationalNumber(phone);
        return phoneNumberUtil.isValidNumber(pn);

    }



    /**
     * 根据国家代码和手机号  判断手机运营商
     * @param phoneNumber
     * @param countryCode
     * @return
     */
    public static String getCarrier(String phoneNumber, String countryCode){
        int ccode = Integer.valueOf(countryCode);
        long phone = Long.valueOf(phoneNumber);
        PhoneNumber pn = new PhoneNumber();
        pn.setCountryCode(ccode);
        pn.setNationalNumber(phone);
        //返回结果只有英文，自己转成成中文
        String carrierEn = carrierMapper.getNameForNumber(pn, Locale.ENGLISH);
        String carrierZh = "";
        carrierZh += geocoder.getDescriptionForNumber(pn, Locale.CHINESE);
        switch (carrierEn) {
            case "China Mobile":
                carrierZh += "移动";
                break;
            case "China Unicom":
                carrierZh += "联通";
                break;
            case "China Telecom":
                carrierZh += "电信";
                break;
            default:
                break;
        }
        return carrierZh;
    }



    /**
     *
     * @Description: 根据国家代码和手机号  手机归属地
     * @param @param phoneNumber
     * @param @param countryCode
     * @param @return    参数
     * @throws
     */
    public static String getGeo(String phoneNumber, String countryCode){
        int ccode = Integer.valueOf(countryCode);
        long phone = Long.valueOf(phoneNumber);
        PhoneNumber pn = new PhoneNumber();
        pn.setCountryCode(ccode);
        pn.setNationalNumber(phone);
        return geocoder.getDescriptionForNumber(pn, Locale.CHINESE);
    }



    /**
     *
     * @Title: getPhoneRegionCode
     * @Description: 得到手机的归宿地编码
     * @return String    返回类型
     * @throws
     */
    public static String getPhoneRegionCode(String phoneNumber, String countryCode){
        String areaName=getGeo(phoneNumber,countryCode);
        if(StringUtils.isEmpty(areaName)){
            return "";
        }
        if(areaName.length()<3){
            return "";
        }
        return areaName;
    }






    public static void main(String[] args) {
        System.out.println(getPhoneRegionCode("18931234567","86"));
        System.out.println(getCarrier("18931234567","86"));
        System.out.println(checkPhoneNumber("18931234567","86"));
    }
}
