package com.zxx.riskcontrol.demo.engine;

import com.zxx.riskcontrol.demo.Action;
import com.zxx.riskcontrol.demo.Condition;
import com.zxx.riskcontrol.demo.context.CardTradeDO;
import com.zxx.riskcontrol.demo.rules.condition.MatchAllCondition;
import com.zxx.riskcontrol.demo.context.BizContext;
import com.zxx.riskcontrol.demo.context.UserDO;
import lombok.Data;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

@Data
public class RiskControlEngine {
    private final MatchAllCondition trigger;
    private final List<Pair<Condition, Action>> actions;

    public RiskControlEngine(MatchAllCondition trigger, List<Pair<Condition, Action>> actions) {
        this.trigger = trigger;
        this.actions = actions;
    }

    public void run(UserDO user, CardTradeDO trade) {
        final BizContext bizContext = new BizContext(user);
        bizContext.setCardTrade(trade);

        if (trigger.match(bizContext)) {
            for (Pair<Condition, Action> action : actions) {
                if (action.getKey().match(bizContext)) {
                    action.getValue().execute(bizContext);
                }
            }
        } else {
            System.out.println("测试。。。 条件未匹配");
        }
    }
}
