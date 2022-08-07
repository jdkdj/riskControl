package com.zxx.riskcontrol.demo.factory;

import com.zxx.riskcontrol.demo.rules.action.*;
import com.zxx.riskcontrol.demo.rules.condition.*;

public class RuleFactoryInitialize {
    static public void init() {
        RuleFactory.registerAction(BatchAction.class, new BatchActionParser());
        RuleFactory.registerAction(DisableUserAction.class, new DisableUserActionParser());
        RuleFactory.registerAction(PushRiskAction.class, new PushRiskActionParser());

        RuleFactory.registerCondition(MatchAllCondition.class, new MatchAllConditionParser());
        RuleFactory.registerCondition(TradeDescriptionCondition.class, new TradeDescriptionConditionParser());
        RuleFactory.registerCondition(UserWhiteListCondition.class, new UserWhiteListConditionParser());
        RuleFactory.registerCondition(MatchUserCondition.class, new MatchUserConditionParser());
        RuleFactory.registerCondition(TrueCondition.class, new TrueConditionParser());
    }
}
