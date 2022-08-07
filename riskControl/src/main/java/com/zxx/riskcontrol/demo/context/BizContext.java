package com.zxx.riskcontrol.demo.context;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BizContext {
    UserDO user;
    CardAuthDO cardAuth;
    CardTradeDO cardTrade;


    public BizContext(UserDO user) {
        this.user = user;
    }

    public UserDO getUser() {
        // if user == null, userService.findByUserId(cardTrade.getUserId)
        // else return user
        return user;
    }

    public CardTradeDO getCardTrade() {
        return cardTrade;
    }

    public void setCardTrade(CardTradeDO cardTrade) {
        this.cardTrade = cardTrade;
    }
}
