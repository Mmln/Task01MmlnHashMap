package org.stepup.stream6.interfaces;

@FunctionalInterface
public interface NameRuleAble {
    NameRuleAble nameRule = (x) -> x == null || x.isEmpty();
    boolean check(String txt);
}
