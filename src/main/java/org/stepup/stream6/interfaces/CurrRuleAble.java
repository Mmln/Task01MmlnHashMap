package org.stepup.stream6.interfaces;

@FunctionalInterface
public interface CurrRuleAble {
    CurrRuleAble currRule = (y) -> (y < 0);
    boolean check(Integer bgnum);
}
