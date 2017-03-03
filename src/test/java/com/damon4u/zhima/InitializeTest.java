package com.damon4u.zhima;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.damon4u.zhima.domain.*;
import com.damon4u.zhima.util.JSONUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/**
 * Description:
 *
 * @author damon4u
 * @version 2017-03-01 10:44
 */
public class InitializeTest {

    private static final Logger logger = LoggerFactory.getLogger(InitializeTest.class);

    private static final String ALIPAY_URL = "https://openapi.alipaydev.com/gateway.do";
    private static final String APP_ID = "2016080400166082";
    private static final String FORMAT = "json";
    private static final String CHATSET = "utf-8";
    private static final String APPLICATION_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCyxjzsW38hVanzqimdxqPNbpy4WYPOBOwuyjef7rNoRnPYjnhPnyAFajPaTB1pK4yvNYm5lSV96uNAlj/OPrRll8RTIskKw26k/TlU08aY4SlXNzQzDO5/kUaLXzFsF/1fYhxMnqE4THv7dVk7zIIXTx/dUE5ZB6iCdGSKXPitdmh3VuDR2VB/enk/xx4hvk3HKpCEfrr1OkchCueq3zXXmX2oAQLMxmieXUq7BzPXjP+PgslNVl3PQOBTMMIznpqdziTZpYTjrUkizAca3cG/9R0f3xE+y+l4Q0uWkpmA3Lh2ROiRZr6pmEP6U8BT/Kh5Y/4Cf5Qj7snj/Wcw4xClAgMBAAECggEAbM11mqv87X7DVobDmUChxI+DVjc0npgkXj24KOAP1HC8ZJog4uSHioQeVsdoRu+4SZId5hK9pKrl8BMBdjZ3eDUrySUskh5rIoLy94uEHNs8W9Y/MKq4JG94La6b15uqGvkNHByXJjp2Yo4C5wAKVMFVNU6AwHKGvg60efknZ/nZWRNyLXhvf/IIvcEaQbY46nPUORpfZ+Lk0fHG98jDJOlIvJBI5rltEDOV0clqewen8vd8aH8Q33x4MM5hR3Ro7Meq3wDhHhAhQEsB0cwpkreKsT59naP/2WNYPnmcgOBKATqTdrhHOEDHzeFU1Ft/k2ANCLU7uO/m6LGBvdrCgQKBgQDnc6owSbzSXezCZi6kVEy43UjQ/Zz4TeONle537XEDALGX2hJP9/LmYQa7/gceztB//TUTLgoWuqcUli7FtI4k89+dhYkPJhyAml36pal9LAtRFU2utTHRvijXIYMH2+QFsyImHkb07gznU1IsvCTYoFOPKC95/MMDXgop2gxq8QKBgQDFvEkErrT8LxLP+PgfAQ53ZeDiPm2pvHdCz/0E/mXtBfzZZ78kd5eM6MDyca4ebXoZ6iKVogq89I1SHcdzcPIwQvc4otBZ3cBv6AxFjL8gEJ0QUGQuiSEjBu96+rqmDSx8/i4qtoSUzc+TPfuXEue+FtylydrMdwRXrO6kLVw49QKBgQCqP7NtR6AkIcC5Ek62w+eXh/Y6/IKNR0Xd7O53uumJKuZ3a4pS1nEOs0ov2GDGcZx+uPNkg1Yad/vUsc//z5tLZTQUe5hg00jd9C6y406z60T7xK7alx/pR3ZlIJpwmne1FZpF7bmGCuDk+64Th+WWjwBH6mhZ0+pivLa/KExAEQKBgA5K0rhM7HYB7QktRLnw+Ju2uG+B9ak8Hx6sK+yteq6MLT+FmTJIlqKvvCqJ/vziCt5OpxtLsB0CTCiwUFdOxaKpZHcqhYbJWXx9OE3ao4Hs0X15YhRfAw8mFC8+ajlQYHPUZ3/c225qBePtqf68dhWuT1qIYZE/1QeFT2Z8hNr1AoGBALKU9f9c+2wj3sasEOFBmlH0zEB/nEcLFRIokrZrO0qg7tz9nXBElsuQ9WZVQxGvp977/6UGMowDFyncScybYTGsc50FWzUHLw+uSBV6IPN4GIpmMV0bmSKgXFffewAoxdSuI94JLN0ep9X9WNJOr4k9x1zNKbAMAa8ta3Nd9lkw";
    private static final String ALIPAY_PRODUCT_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAw+MqvBp9/3yfdNuxVYLBDEF+V5577aXNV5iZeZH0SAqoGC4nWCCpYxAnKnCN0C2nTs6rF8hV5c+h2aNJKQVjo3avYW8yASzH0bhnMtqkoaoUZW9hsfjCJHlzq8lB0Eake8urH8/gZttmkMpJNn9wP7WP4deODabjEYbG9mw83TRPp/RXl8PsZ7fMnHk0QpGItRaqfo07wFaCgwlZjEp4c5HxJ8AQU5ILPRt/Vix9oMedmt9SpTXS6iRxQEHaN8JyNzIxCYF6B8NTJV3uMgfPk4ufghsEyNLee0T29vbel7gX0EHP2BtgY6GH5iTI2cb3tRO0y/IfYYXOPvZPViUCGwIDAQAB";
    private static final String SIGN_TYPE = "RSA2";
    private static final String BIZ_CODE = "FACE";
    private static final String IDENTITY_TYPE = "CERT_INFO";
    private static final String CERT_TYPE = "IDENTITY_CARD";

    @Test
    public void testInitialize() throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(
                ALIPAY_URL,
                APP_ID,
                APPLICATION_PRIVATE_KEY,
                FORMAT,
                CHATSET,
                ALIPAY_PRODUCT_KEY,
                SIGN_TYPE);
        ZhimaCustomerCertificationInitializeRequest request = new ZhimaCustomerCertificationInitializeRequest();
        ZhimaCustomerCertificationInitializeBizContent bizContent = new ZhimaCustomerCertificationInitializeBizContent();
        bizContent.setTransactionId(generateOrderId());
        bizContent.setProductCode("w1010100000000002978");
        bizContent.setBizCode(BIZ_CODE);
        IdentityParam identityParam = new IdentityParam();
        identityParam.setIdentityType(IDENTITY_TYPE);
        identityParam.setCertType(CERT_TYPE);
        identityParam.setCertName("沙箱环境");
        identityParam.setCertNo("712347191804061323");
        bizContent.setIdentityParam(identityParam);
        request.setBizContent(JSONUtil.toJson(bizContent));
        ZhimaCustomerCertificationInitializeResponse response = alipayClient.execute(request);
        if (response.isSuccess()) {
            logger.info("调用成功");
            String bizNo = response.getBizNo();
            logger.info("bizNo = " + bizNo);

            certify(alipayClient, bizNo);

        } else {
            logger.error("调用失败! " + response.getCode() + "," + response.getMsg() + "," + response.getSubCode() + "," + response.getSubMsg());
        }
    }

    private void certify(AlipayClient alipayClient, String bizNo) throws AlipayApiException {
        ZhimaCustomerCertificationCertifyRequest request = new ZhimaCustomerCertificationCertifyRequest();
        ZhimaCustomerCertificationCertifyBizContent bizContent = new ZhimaCustomerCertificationCertifyBizContent();
        bizContent.setBizNo(bizNo);
        // 设置业务参数,必须要biz_no
        request.setBizContent(JSONUtil.toJson(bizContent));
        // 设置回调地址,必填. 如果需要直接在支付宝APP里面打开回调地址使用alipay协议
        // alipay://www.taobao.com 或者 alipays://www.taobao.com,分别对应http和https请求
        // 设置业务参数,必须要biz_no
        request.setReturnUrl("alipays://www.taobao.com");
        // 这里一定要使用GET模式
        ZhimaCustomerCertificationCertifyResponse response = alipayClient.pageExecute(request, "GET");
        // 从body中获取URL
        String url = response.getBody();
        logger.info("generateCertifyUrl url:" + url);
    }


    /**
     * 随机生成20位订单号，订单格式为年月日时分秒毫秒+三位随机字符串，随机字符串由0-9
     */
    private static String generateOrderId() {
        int i;
        int count = 0;
        char[] str = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        StringBuilder buf = new StringBuilder("NM");
        long currentTime = System.currentTimeMillis();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(currentTime);
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        buf.append(df.format(cal.getTime()));
        Random rand = new Random(currentTime);
        while (count < 2) {
            i = Math.abs(rand.nextInt(str.length));
            if (i >= 0 && i < str.length) {
                buf.append(str[i]);
                count++;
            }
        }
        return buf.toString();
    }
}
