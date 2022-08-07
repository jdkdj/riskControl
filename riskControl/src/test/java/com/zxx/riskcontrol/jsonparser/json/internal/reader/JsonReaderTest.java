package com.zxx.riskcontrol.jsonparser.json.internal.reader;

import net.minidev.json.JSONUtil;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import springfox.documentation.spring.web.json.Json;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest {
    @Test
    void test() {
        String a = "a\\naaa";
        String b = "hello, world";
        String c = "c\t";
        String d = "a\r\naaa";
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(b);
        System.out.println(d);
        System.out.println(b);
    }


    @Test
    void test2() {
        String a = "{\"accountCode\":\"W-702\",\"customerId\":\"CLF70\",\"idMatchNumber\":\"04C89H6L4V\",\"customerName\":\"CARDPALACE\",\"corporateCode\":\"\",\"transactionDate\":\"2021-05-07\",\"transactionTime\":\"05:07:22\",\"postedDate\":\"\",\"postedTime\":\"\",\"transactionStatus\":\"Authorized\",\"cardNumber\":\"5567660921027871\",\"cardholderName\":\"CAPTE OILCANG\",\"cardExpireDate\":\"0522\",\"declineCode\":\"\",\"declineMessage\":\"\",\"correctiveAction\":\"\",\"promptedId\":\"\",\"mccGroup\":\"30020\",\"mccNumber\":\"5661\",\"mccDescription\":\"SHOE STORES\",\"merchantName\":\"\",\"merchantAddress\":\"\",\"merchantCity\":\"\",\"merchantState\":\"\",\"merchantZip\":\"\",\"acceptLocation\":\"Foot Locker            8009916815    NY\",\"preAuthAmount\":\"23.99\",\"postedAmount\":\"0.00\",\"approvalCode\":\"749453\",\"reversalFlag\":\"Y\",\"latitude\":null,\"longitude\":null,\"employeeNumber\":\"336N29Q6J3\",\"posEMode\":\"081\",\"posEModeDesc\":\"ECOMMERCE                     \",\"acceptorId\":\"527021002239285\",\"cardToken\":\"336N29Q6J3\"}"
                ;
    }

    @Test
    void test3() {
        String a = "aa\\\nbwerwerew";
        System.out.println(a.charAt(2));
        System.out.println(a);
    }

    @Test
    void test4() {
        String s = "\"s\":\"\\\\ Mid State";
        System.out.println(s);
    }

    @Test
    void test5() {
        String s = "{\n" +
                "\n" +
                "\"paymentAuthMode\":\"2D\",\n" +
                "\"mcc\":\"5300\",\n" +
                "\"saveAsset\":false,\n" +
                "\"shipToStreetAddr1\":\"\\\\Mid State Sports , MS 39056 usa\",\n" +
                "\"payMode\":\"SALES\",\n" +
                "\"requestBaseAmount\":{\n" +
                "\"amount\":3209.99,\n" +
                "\"currency\":{\n" +
                "\"symbol\":\"USD\",\n" +
                "\"currencyCode\":\"USD\",\n" +
                "\"defaultFractionDigits\":2,\n" +
                "\"displayName\":\"美元\",\n" +
                "\"numericCode\":840\n" +
                "},\n" +
                "\"centFactor\":100,\n" +
                "\"currencyCode\":\"USD\",\n" +
                "\"cent\":320999,\n" +
                "\"currencyValue\":\"840\"\n" +
                "}\n" +
                "}";

    }

    @Test
    void test6() {
        String x = "\\";
        String a = "\\\\";
        String b = "\\\\\\";
        String c = "\\\\\\\\";
        System.out.println(x);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

        String d = "aaa\n";
        String e = "aaa\\\n";
        String f = "aaa\\\\n";
        System.out.println(d);
        System.out.println(e);
        System.out.println(f);

        String aa = "\\t";
        System.err.println(aa);

        //
        String g = "\\\"";
        String h= "\\\\";
        String i = "\\/";
        String j = "\b";
        String k = "\\f";
        String l = "\\n";
        String m = "\\r";
        String n = "\\t";
    }

}