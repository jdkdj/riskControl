package com.zxx.riskcontrol.demo.engine;

import com.zxx.riskcontrol.demo.context.CardTradeDO;
import com.zxx.riskcontrol.demo.context.UserDO;
import com.zxx.riskcontrol.demo.factory.RuleFactoryInitialize;
import com.zxx.riskcontrol.jsonparser.SyntaxException;
import org.junit.jupiter.api.Test;

import java.io.SyncFailedException;

import static org.junit.jupiter.api.Assertions.*;

class RiskControlEngineParserTest {
    @Test
    void testAll() throws SyntaxException, SyntaxException, SyncFailedException {
        String trigger = "[\n" +
                "    {\n" +
                "        \"condition\": \"UserWhiteListCondition\",\n" +
                "        \"params\": {\n" +
                "            \"whitelist\": [\"skipThisUser\"]\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"condition\": \"TradeDescriptionCondition\",\n" +
                "        \"params\": {\n" +
                "            \"containAny\": [\"FaceBk\", \"FACEBOOK\"]\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"condition\": \"TradeDescriptionCondition\",\n" +
                "        \"params\": {\n" +
                "            \"containAny\": [\"FaceBk ADS\", \"ADS\"]\n" +
                "        }\n" +
                "    }\n" +
                "]\n";

        String actions = "[\n" +
                "    {\n" +
                "        \"if\": [\n" +
                "            {\n" +
                "                \"condition\": \"MatchUserCondition\",\n" +
                "                \"params\": {\n" +
                "                    \"matchAny\": [ \"disableThisUser\"]\n" +
                "                }\n" +
                "            }\n" +
                "        ],\n" +
                "        \"then\": [\n" +
                "            {\n" +
                "                \"action\": \"DisableUserAction\",\n" +
                "                \"params\": {\n" +
                "                    \"reason\": \"测试关闭用户\"\n" +
                "                }\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"if\": [\n" +
                "            {\n" +
                "                \"condition\": \"TrueCondition\",\n" +
                "                \"params\": [\n" +
                "                ]\n" +
                "            }\n" +
                "        ],\n" +
                "        \"then\": [\n" +
                "            {\n" +
                "                \"action\": \"PushRiskAction\",\n" +
                "                \"params\": {\n" +
                "                    \"message\": \"测试控消息到工单\"\n" +
                "                }\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "]\n";
        RuleFactoryInitialize.init();
        //描述 币种 白名单 组合规则 and or 场景

        final RiskControlEngine engine = RiskControlEngineParser.parse(trigger, actions);

        final CardTradeDO cardTradeDO = new CardTradeDO();
        cardTradeDO.setDesp("FaceBk ADS");
        //cardTradeDO.setDesp("FaceBk");

        final UserDO userDO = new UserDO();
//        userDO.setUserId("skipThisUser");
        //userDO.setUserId("disableThisUser");
        engine.run(userDO, cardTradeDO);
    }

}