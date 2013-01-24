package org.n52.shared.serializable.pojos;

import org.n52.client.view.gui.elements.layouts.SimpleRuleType;

public class RuleBuilder {

    private SimpleRuleType ruleType;
    private String title;
    private String notificationType;
    private String description;
    private boolean publish;
    private boolean enterEqualsExit;
    private int entryOperatorIndex;
    private int exitOperatorIndex;
    private String entryValue;
    private String entryUnit;
    private String exitValue;
    private String exitUnit;
    private int cookieAsInt;
    private String entrycount;
    private String exitCount;
    private String entryTime;
    private String entryTimeUnit;
    private String exitTime;
    private String exitTimeUnit;
    private FeedingMetadata feedingMetadata;
    @Deprecated
    private String procedure;
    @Deprecated
    private String phenomenon;

    public static RuleBuilder aRule() {
        return new RuleBuilder();
    }

    public RuleBuilder setRuleType(SimpleRuleType ruleTyp) {
        this.ruleType = ruleTyp;
        return this;
    }


    public RuleBuilder setTitle(String title) {
        this.title = title;
        return this;
    }
    
    public RuleBuilder setNotificationType(String notificationType) {
        this.notificationType = notificationType;
        return this;
        
    }

    public RuleBuilder setDescription(String description) {
        this.description = description;
        return this;
        
    }

    public RuleBuilder setPublish(boolean publish) {
        this.publish = publish;
        return this;
    }

    public RuleBuilder setEnterIsSameAsExitCondition(boolean condition) {
        this.enterEqualsExit = condition;
        return this;
    }

    public RuleBuilder setEntryOperatorIndex(int entryOperatorIndex) {
        this.entryOperatorIndex = entryOperatorIndex;
        return this;
    }
    
    public RuleBuilder setExitOperatorIndex(int exitOperatorIndex) {
        this.exitOperatorIndex = exitOperatorIndex;
        return this;
    }

    public RuleBuilder setEntryValue(String entryValue) {
        this.entryValue = entryValue;
        return this;
    }

    public RuleBuilder setEntryUnit(String entryUnit) {
        this.entryUnit = entryUnit;
        return this;
    }

    public RuleBuilder setExitValue(String exitValue) {
        this.exitValue = exitValue;
        return this;
    }

    public RuleBuilder setExitUnit(String exitUnit) {
        this.exitUnit = exitUnit;
        return this;
    }
    
    public RuleBuilder setEntryCount(String entryCount) {
        this.entrycount = entryCount;
        return this;
    }
    
    public RuleBuilder setExitCount(String exitCount) {
        this.exitCount = exitCount;
        return this;
    }

    public RuleBuilder setCookie(int cookieAsInt) {
        this.cookieAsInt = cookieAsInt;
        return this;
    }
    
    public RuleBuilder setEntryTime(String entryTime) {
        this.entryTime = entryTime;
        return this;
    }
    
    public RuleBuilder setEntryTimeUnit(String entryTimeUnit) {
        this.entryTimeUnit = entryTimeUnit;
        return this;
    }
    
    public RuleBuilder setExitTime(String exitTime) {
        this.exitTime = exitTime;
        return this;
    }
  
    public RuleBuilder setExitTimeUnit(String exitTimeUnit) {
        this.exitTimeUnit = exitTimeUnit;
        return this;
    }

    public RuleBuilder setFeedingMetadata(FeedingMetadata feedingMetadata) {
        this.feedingMetadata = feedingMetadata;
        return this;
    }

    public Rule build() {
        Rule rule = new Rule();
        rule.setExitCount(exitCount);
        rule.setExitOperatorIndex(exitOperatorIndex);
        rule.setCount(entrycount);
        rule.setExitTime(exitTime);
        rule.setExitTimeUnit(exitTimeUnit);
        rule.setExitUnit(exitUnit);
        rule.setExitValue(exitValue);
        rule.setDescription(description);
        rule.setEnterEqualsExitCondition(enterEqualsExit);
        rule.setNotificationType(notificationType);
        rule.setPublish(publish);
        rule.setFeedingMetadata(feedingMetadata);
        rule.setEntryOperatorIndex(entryOperatorIndex);
        rule.setrTime(entryTime);
        rule.setrTimeUnit(entryTimeUnit);
        rule.setRuleType(ruleType);
        rule.setEntryUnit(entryUnit);
        rule.setEntryValue(entryValue);
        rule.setTitle(title);
        rule.setUserID(cookieAsInt);
        rule.setProcedure(procedure);
        rule.setPhenomenon(phenomenon);
        return rule;
    }

    @Deprecated
    public RuleBuilder setProcedure(String procedure) {
        this.procedure = procedure;
        return this;
    }
    
    @Deprecated
    public RuleBuilder setPhenomenon(String phenomeon) {
        this.phenomenon = phenomeon;
        return this;
    }
}