package com.zxx.riskcontrol.demo.factory;

import com.zxx.riskcontrol.demo.Action;
import com.zxx.riskcontrol.demo.ActionParser;
import com.zxx.riskcontrol.demo.Condition;
import com.zxx.riskcontrol.demo.ConditionParser;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class RuleFactory {
    private static final ConcurrentHashMap<String, ConditionParser<? extends Condition>> CONDITION_REGISTER = new ConcurrentHashMap<>();
    private static final ConcurrentMap<String, ActionParser<? extends Action>> ACTION_REGISTER = new ConcurrentHashMap<>();

    public static <T extends Condition> void registerCondition(Class<T> condition, ConditionParser<T> parser) {
        String name = classLastName(condition);
        if (CONDITION_REGISTER.get(name) != null) {
            throw new RuntimeException("出现同名的条件判断(Condition)组件，请检查命名是否冲突或是否重复注册 name: " + name);
        }
        CONDITION_REGISTER.put(name, parser);
    }

    public static <T extends Action> void registerAction(Class<T> action, ActionParser<T> parser) {
        String name = classLastName(action);
        if (ACTION_REGISTER.get(name) != null) {
            throw new RuntimeException("出现同名的脚本执行动作(Action)组件，请检查命名是否冲突或是否重复注册 name: " + name);
        }
        ACTION_REGISTER.put(name, parser);
    }

    public static ActionParser<? extends Action> getActionParser(String actionName) {
        return ACTION_REGISTER.get(actionName);
    }

    public static ConditionParser<? extends Condition> getConditionParser(String conditionName) {
        return CONDITION_REGISTER.get(conditionName);
    }

    public static Set<String> getActionNames() {
        return ACTION_REGISTER.keySet();
    }

    public static Set<String> getConditionNames() {
        return CONDITION_REGISTER.keySet();
    }

    private static <T> String classLastName(Class<T> clazz) {
        final String name = clazz.getName();
        final int idx = name.lastIndexOf(".");
        return name.substring(idx + 1);
    }
}
