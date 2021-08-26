package com.bsl.pojo;

import java.util.ArrayList;
import java.util.List;

public class BslCarRetchaInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BslCarRetchaInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andCarNoIsNull() {
            addCriterion("car_no is null");
            return (Criteria) this;
        }

        public Criteria andCarNoIsNotNull() {
            addCriterion("car_no is not null");
            return (Criteria) this;
        }

        public Criteria andCarNoEqualTo(String value) {
            addCriterion("car_no =", value, "carNo");
            return (Criteria) this;
        }

        public Criteria andCarNoNotEqualTo(String value) {
            addCriterion("car_no <>", value, "carNo");
            return (Criteria) this;
        }

        public Criteria andCarNoGreaterThan(String value) {
            addCriterion("car_no >", value, "carNo");
            return (Criteria) this;
        }

        public Criteria andCarNoGreaterThanOrEqualTo(String value) {
            addCriterion("car_no >=", value, "carNo");
            return (Criteria) this;
        }

        public Criteria andCarNoLessThan(String value) {
            addCriterion("car_no <", value, "carNo");
            return (Criteria) this;
        }

        public Criteria andCarNoLessThanOrEqualTo(String value) {
            addCriterion("car_no <=", value, "carNo");
            return (Criteria) this;
        }

        public Criteria andCarNoLike(String value) {
            addCriterion("car_no like", value, "carNo");
            return (Criteria) this;
        }

        public Criteria andCarNoNotLike(String value) {
            addCriterion("car_no not like", value, "carNo");
            return (Criteria) this;
        }

        public Criteria andCarNoIn(List<String> values) {
            addCriterion("car_no in", values, "carNo");
            return (Criteria) this;
        }

        public Criteria andCarNoNotIn(List<String> values) {
            addCriterion("car_no not in", values, "carNo");
            return (Criteria) this;
        }

        public Criteria andCarNoBetween(String value1, String value2) {
            addCriterion("car_no between", value1, value2, "carNo");
            return (Criteria) this;
        }

        public Criteria andCarNoNotBetween(String value1, String value2) {
            addCriterion("car_no not between", value1, value2, "carNo");
            return (Criteria) this;
        }

        public Criteria andRetWeightIsNull() {
            addCriterion("ret_weight is null");
            return (Criteria) this;
        }

        public Criteria andRetWeightIsNotNull() {
            addCriterion("ret_weight is not null");
            return (Criteria) this;
        }

        public Criteria andRetWeightEqualTo(Float value) {
            addCriterion("ret_weight =", value, "retWeight");
            return (Criteria) this;
        }

        public Criteria andRetWeightNotEqualTo(Float value) {
            addCriterion("ret_weight <>", value, "retWeight");
            return (Criteria) this;
        }

        public Criteria andRetWeightGreaterThan(Float value) {
            addCriterion("ret_weight >", value, "retWeight");
            return (Criteria) this;
        }

        public Criteria andRetWeightGreaterThanOrEqualTo(Float value) {
            addCriterion("ret_weight >=", value, "retWeight");
            return (Criteria) this;
        }

        public Criteria andRetWeightLessThan(Float value) {
            addCriterion("ret_weight <", value, "retWeight");
            return (Criteria) this;
        }

        public Criteria andRetWeightLessThanOrEqualTo(Float value) {
            addCriterion("ret_weight <=", value, "retWeight");
            return (Criteria) this;
        }

        public Criteria andRetWeightIn(List<Float> values) {
            addCriterion("ret_weight in", values, "retWeight");
            return (Criteria) this;
        }

        public Criteria andRetWeightNotIn(List<Float> values) {
            addCriterion("ret_weight not in", values, "retWeight");
            return (Criteria) this;
        }

        public Criteria andRetWeightBetween(Float value1, Float value2) {
            addCriterion("ret_weight between", value1, value2, "retWeight");
            return (Criteria) this;
        }

        public Criteria andRetWeightNotBetween(Float value1, Float value2) {
            addCriterion("ret_weight not between", value1, value2, "retWeight");
            return (Criteria) this;
        }

        public Criteria andPrintNumIsNull() {
            addCriterion("print_num is null");
            return (Criteria) this;
        }

        public Criteria andPrintNumIsNotNull() {
            addCriterion("print_num is not null");
            return (Criteria) this;
        }

        public Criteria andPrintNumEqualTo(Integer value) {
            addCriterion("print_num =", value, "printNum");
            return (Criteria) this;
        }

        public Criteria andPrintNumNotEqualTo(Integer value) {
            addCriterion("print_num <>", value, "printNum");
            return (Criteria) this;
        }

        public Criteria andPrintNumGreaterThan(Integer value) {
            addCriterion("print_num >", value, "printNum");
            return (Criteria) this;
        }

        public Criteria andPrintNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("print_num >=", value, "printNum");
            return (Criteria) this;
        }

        public Criteria andPrintNumLessThan(Integer value) {
            addCriterion("print_num <", value, "printNum");
            return (Criteria) this;
        }

        public Criteria andPrintNumLessThanOrEqualTo(Integer value) {
            addCriterion("print_num <=", value, "printNum");
            return (Criteria) this;
        }

        public Criteria andPrintNumIn(List<Integer> values) {
            addCriterion("print_num in", values, "printNum");
            return (Criteria) this;
        }

        public Criteria andPrintNumNotIn(List<Integer> values) {
            addCriterion("print_num not in", values, "printNum");
            return (Criteria) this;
        }

        public Criteria andPrintNumBetween(Integer value1, Integer value2) {
            addCriterion("print_num between", value1, value2, "printNum");
            return (Criteria) this;
        }

        public Criteria andPrintNumNotBetween(Integer value1, Integer value2) {
            addCriterion("print_num not between", value1, value2, "printNum");
            return (Criteria) this;
        }

        public Criteria andChaWeightIsNull() {
            addCriterion("cha_weight is null");
            return (Criteria) this;
        }

        public Criteria andChaWeightIsNotNull() {
            addCriterion("cha_weight is not null");
            return (Criteria) this;
        }

        public Criteria andChaWeightEqualTo(Float value) {
            addCriterion("cha_weight =", value, "chaWeight");
            return (Criteria) this;
        }

        public Criteria andChaWeightNotEqualTo(Float value) {
            addCriterion("cha_weight <>", value, "chaWeight");
            return (Criteria) this;
        }

        public Criteria andChaWeightGreaterThan(Float value) {
            addCriterion("cha_weight >", value, "chaWeight");
            return (Criteria) this;
        }

        public Criteria andChaWeightGreaterThanOrEqualTo(Float value) {
            addCriterion("cha_weight >=", value, "chaWeight");
            return (Criteria) this;
        }

        public Criteria andChaWeightLessThan(Float value) {
            addCriterion("cha_weight <", value, "chaWeight");
            return (Criteria) this;
        }

        public Criteria andChaWeightLessThanOrEqualTo(Float value) {
            addCriterion("cha_weight <=", value, "chaWeight");
            return (Criteria) this;
        }

        public Criteria andChaWeightIn(List<Float> values) {
            addCriterion("cha_weight in", values, "chaWeight");
            return (Criteria) this;
        }

        public Criteria andChaWeightNotIn(List<Float> values) {
            addCriterion("cha_weight not in", values, "chaWeight");
            return (Criteria) this;
        }

        public Criteria andChaWeightBetween(Float value1, Float value2) {
            addCriterion("cha_weight between", value1, value2, "chaWeight");
            return (Criteria) this;
        }

        public Criteria andChaWeightNotBetween(Float value1, Float value2) {
            addCriterion("cha_weight not between", value1, value2, "chaWeight");
            return (Criteria) this;
        }

        public Criteria andBsGettypeIsNull() {
            addCriterion("bs_gettype is null");
            return (Criteria) this;
        }

        public Criteria andBsGettypeIsNotNull() {
            addCriterion("bs_gettype is not null");
            return (Criteria) this;
        }

        public Criteria andBsGettypeEqualTo(String value) {
            addCriterion("bs_gettype =", value, "bsGettype");
            return (Criteria) this;
        }

        public Criteria andBsGettypeNotEqualTo(String value) {
            addCriterion("bs_gettype <>", value, "bsGettype");
            return (Criteria) this;
        }

        public Criteria andBsGettypeGreaterThan(String value) {
            addCriterion("bs_gettype >", value, "bsGettype");
            return (Criteria) this;
        }

        public Criteria andBsGettypeGreaterThanOrEqualTo(String value) {
            addCriterion("bs_gettype >=", value, "bsGettype");
            return (Criteria) this;
        }

        public Criteria andBsGettypeLessThan(String value) {
            addCriterion("bs_gettype <", value, "bsGettype");
            return (Criteria) this;
        }

        public Criteria andBsGettypeLessThanOrEqualTo(String value) {
            addCriterion("bs_gettype <=", value, "bsGettype");
            return (Criteria) this;
        }

        public Criteria andBsGettypeLike(String value) {
            addCriterion("bs_gettype like", value, "bsGettype");
            return (Criteria) this;
        }

        public Criteria andBsGettypeNotLike(String value) {
            addCriterion("bs_gettype not like", value, "bsGettype");
            return (Criteria) this;
        }

        public Criteria andBsGettypeIn(List<String> values) {
            addCriterion("bs_gettype in", values, "bsGettype");
            return (Criteria) this;
        }

        public Criteria andBsGettypeNotIn(List<String> values) {
            addCriterion("bs_gettype not in", values, "bsGettype");
            return (Criteria) this;
        }

        public Criteria andBsGettypeBetween(String value1, String value2) {
            addCriterion("bs_gettype between", value1, value2, "bsGettype");
            return (Criteria) this;
        }

        public Criteria andBsGettypeNotBetween(String value1, String value2) {
            addCriterion("bs_gettype not between", value1, value2, "bsGettype");
            return (Criteria) this;
        }

        public Criteria andWxFlagIsNull() {
            addCriterion("wx_flag is null");
            return (Criteria) this;
        }

        public Criteria andWxFlagIsNotNull() {
            addCriterion("wx_flag is not null");
            return (Criteria) this;
        }

        public Criteria andWxFlagEqualTo(String value) {
            addCriterion("wx_flag =", value, "wxFlag");
            return (Criteria) this;
        }

        public Criteria andWxFlagNotEqualTo(String value) {
            addCriterion("wx_flag <>", value, "wxFlag");
            return (Criteria) this;
        }

        public Criteria andWxFlagGreaterThan(String value) {
            addCriterion("wx_flag >", value, "wxFlag");
            return (Criteria) this;
        }

        public Criteria andWxFlagGreaterThanOrEqualTo(String value) {
            addCriterion("wx_flag >=", value, "wxFlag");
            return (Criteria) this;
        }

        public Criteria andWxFlagLessThan(String value) {
            addCriterion("wx_flag <", value, "wxFlag");
            return (Criteria) this;
        }

        public Criteria andWxFlagLessThanOrEqualTo(String value) {
            addCriterion("wx_flag <=", value, "wxFlag");
            return (Criteria) this;
        }

        public Criteria andWxFlagLike(String value) {
            addCriterion("wx_flag like", value, "wxFlag");
            return (Criteria) this;
        }

        public Criteria andWxFlagNotLike(String value) {
            addCriterion("wx_flag not like", value, "wxFlag");
            return (Criteria) this;
        }

        public Criteria andWxFlagIn(List<String> values) {
            addCriterion("wx_flag in", values, "wxFlag");
            return (Criteria) this;
        }

        public Criteria andWxFlagNotIn(List<String> values) {
            addCriterion("wx_flag not in", values, "wxFlag");
            return (Criteria) this;
        }

        public Criteria andWxFlagBetween(String value1, String value2) {
            addCriterion("wx_flag between", value1, value2, "wxFlag");
            return (Criteria) this;
        }

        public Criteria andWxFlagNotBetween(String value1, String value2) {
            addCriterion("wx_flag not between", value1, value2, "wxFlag");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}