package com.q8888.springboot.mybatis.server.base;

/**
 * Created by huangr on 2018/10/10.
 */
public final class Constants {

    private Constants() {
    }

    public static final String ENV_DEVICE_TYPE = "环保设备";
    public static final String MANAGE_DEVICE_TYPE = "经营设备";
    public static final String PLAT_PATTERN = "([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼]{1}(([A-HJ-Z]{1}[A-HJ-NP-Z0-9]{5})|([A-HJ-Z]{1}(([DF]{1}[A-HJ-NP-Z0-9]{1}[0-9]{4})|([0-9]{5}[DF]{1})))|([A-HJ-Z]{1}[A-D0-9]{1}[0-9]{3}警)))|([0-9]{6}使)|((([沪粤川云桂鄂陕蒙藏黑辽渝]{1}A)|鲁B|闽D|蒙E|蒙H)[0-9]{4}领)|(WJ[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼·•]{1}[0-9]{4}[TDSHBXJ0-9]{1})|([VKHBSLJNGCE]{1}[A-DJ-PR-TVY]{1}[0-9]{5})";

    public static final String WASTE_PATTERN = "\\d{3}-\\d{3}-\\d{2}";
    public static final String CREDIT_CODE = "[0-9A-HJ-NPQRTUWXY]{2}\\d{6}[0-9A-HJ-NPQRTUWXY]{10}";
    public static final String DISPOSAL_CODE = "\\d{10}";

    public static final String CODE = "DZLDS";

    public static final String DATE_FORMAT_01 = "yyyy-MM-dd";
    public static final String DATE_FORMAT_02 = "yyyyMMddHHmmss";
    public static final String DATE_FORMAT_03 = "yyyy-MM-ddHHmmss";
    public static final String DATE_FORMAT_04 = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE1 = "2018-11-10 00:00:00";
    public static final String DATE2 = "2018-11-11 00:00:00";

    public static final String FORMAT1 = " 00:00:00";
    public static final String FORMAT2 = " 23:59:59";
    public static final String FORMAT3 = "-01 00:00:00";
    public static final String FORMAT4 = "-31 23:59:59";
    public static final String FORMAT5 = "-01-01 00:00:00";
    public static final String FORMAT6 = "-12-31 23:59:59";
    public static final String FORMAT7 = "-01";
    public static final String FORMAT8 = "-31";

    public static final Integer STATUS = 1;
    public static final Integer FEEDBACK = 1;
    public static final Integer RECEIPT = 1;


    public static final String SUCCESS = "success";
    public static final String FAILED = "failed";


    public static final String ENTERPRISE_ID = "请输入企业ID";
    public static final String DEVICE_ID = "请输入设备ID";
    public static final String ENTERPRISE_ID_02 = "enterprise_id";

    public static final String data1 = "0.0000";

}
