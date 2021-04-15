package com.bsl.pojo;

import java.util.ArrayList;
import java.util.List;

public class BslEnumInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BslEnumInfoExample() {
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

        public Criteria andEnumEnglishNameIsNull() {
            addCriterion("enum_english_name is null");
            return (Criteria) this;
        }

        public Criteria andEnumEnglishNameIsNotNull() {
            addCriterion("enum_english_name is not null");
            return (Criteria) this;
        }

        public Criteria andEnumEnglishNameEqualTo(String value) {
            addCriterion("enum_english_name =", value, "enumEnglishName");
            return (Criteria) this;
        }

        public Criteria andEnumEnglishNameNotEqualTo(String value) {
            addCriterion("enum_english_name <>", value, "enumEnglishName");
            return (Criteria) this;
        }

        public Criteria andEnumEnglishNameGreaterThan(String value) {
            addCriterion("enum_english_name >", value, "enumEnglishName");
            return (Criteria) this;
        }

        public Criteria andEnumEnglishNameGreaterThanOrEqualTo(String value) {
            addCriterion("enum_english_name >=", value, "enumEnglishName");
            return (Criteria) this;
        }

        public Criteria andEnumEnglishNameLessThan(String value) {
            addCriterion("enum_english_name <", value, "enumEnglishName");
            return (Criteria) this;
        }

        public Criteria andEnumEnglishNameLessThanOrEqualTo(String value) {
            addCriterion("enum_english_name <=", value, "enumEnglishName");
            return (Criteria) this;
        }

        public Criteria andEnumEnglishNameLike(String value) {
            addCriterion("enum_english_name like", value, "enumEnglishName");
            return (Criteria) this;
        }

        public Criteria andEnumEnglishNameNotLike(String value) {
            addCriterion("enum_english_name not like", value, "enumEnglishName");
            return (Criteria) this;
        }

        public Criteria andEnumEnglishNameIn(List<String> values) {
            addCriterion("enum_english_name in", values, "enumEnglishName");
            return (Criteria) this;
        }

        public Criteria andEnumEnglishNameNotIn(List<String> values) {
            addCriterion("enum_english_name not in", values, "enumEnglishName");
            return (Criteria) this;
        }

        public Criteria andEnumEnglishNameBetween(String value1, String value2) {
            addCriterion("enum_english_name between", value1, value2, "enumEnglishName");
            return (Criteria) this;
        }

        public Criteria andEnumEnglishNameNotBetween(String value1, String value2) {
            addCriterion("enum_english_name not between", value1, value2, "enumEnglishName");
            return (Criteria) this;
        }

        public Criteria andEnumKeyIsNull() {
            addCriterion("enum_key is null");
            return (Criteria) this;
        }

        public Criteria andEnumKeyIsNotNull() {
            addCriterion("enum_key is not null");
            return (Criteria) this;
        }

        public Criteria andEnumKeyEqualTo(String value) {
            addCriterion("enum_key =", value, "enumKey");
            return (Criteria) this;
        }

        public Criteria andEnumKeyNotEqualTo(String value) {
            addCriterion("enum_key <>", value, "enumKey");
            return (Criteria) this;
        }

        public Criteria andEnumKeyGreaterThan(String value) {
            addCriterion("enum_key >", value, "enumKey");
            return (Criteria) this;
        }

        public Criteria andEnumKeyGreaterThanOrEqualTo(String value) {
            addCriterion("enum_key >=", value, "enumKey");
            return (Criteria) this;
        }

        public Criteria andEnumKeyLessThan(String value) {
            addCriterion("enum_key <", value, "enumKey");
            return (Criteria) this;
        }

        public Criteria andEnumKeyLessThanOrEqualTo(String value) {
            addCriterion("enum_key <=", value, "enumKey");
            return (Criteria) this;
        }

        public Criteria andEnumKeyLike(String value) {
            addCriterion("enum_key like", value, "enumKey");
            return (Criteria) this;
        }

        public Criteria andEnumKeyNotLike(String value) {
            addCriterion("enum_key not like", value, "enumKey");
            return (Criteria) this;
        }

        public Criteria andEnumKeyIn(List<String> values) {
            addCriterion("enum_key in", values, "enumKey");
            return (Criteria) this;
        }

        public Criteria andEnumKeyNotIn(List<String> values) {
            addCriterion("enum_key not in", values, "enumKey");
            return (Criteria) this;
        }

        public Criteria andEnumKeyBetween(String value1, String value2) {
            addCriterion("enum_key between", value1, value2, "enumKey");
            return (Criteria) this;
        }

        public Criteria andEnumKeyNotBetween(String value1, String value2) {
            addCriterion("enum_key not between", value1, value2, "enumKey");
            return (Criteria) this;
        }

        public Criteria andEnumChineseNameIsNull() {
            addCriterion("enum_chinese_name is null");
            return (Criteria) this;
        }

        public Criteria andEnumChineseNameIsNotNull() {
            addCriterion("enum_chinese_name is not null");
            return (Criteria) this;
        }

        public Criteria andEnumChineseNameEqualTo(String value) {
            addCriterion("enum_chinese_name =", value, "enumChineseName");
            return (Criteria) this;
        }

        public Criteria andEnumChineseNameNotEqualTo(String value) {
            addCriterion("enum_chinese_name <>", value, "enumChineseName");
            return (Criteria) this;
        }

        public Criteria andEnumChineseNameGreaterThan(String value) {
            addCriterion("enum_chinese_name >", value, "enumChineseName");
            return (Criteria) this;
        }

        public Criteria andEnumChineseNameGreaterThanOrEqualTo(String value) {
            addCriterion("enum_chinese_name >=", value, "enumChineseName");
            return (Criteria) this;
        }

        public Criteria andEnumChineseNameLessThan(String value) {
            addCriterion("enum_chinese_name <", value, "enumChineseName");
            return (Criteria) this;
        }

        public Criteria andEnumChineseNameLessThanOrEqualTo(String value) {
            addCriterion("enum_chinese_name <=", value, "enumChineseName");
            return (Criteria) this;
        }

        public Criteria andEnumChineseNameLike(String value) {
            addCriterion("enum_chinese_name like", value, "enumChineseName");
            return (Criteria) this;
        }

        public Criteria andEnumChineseNameNotLike(String value) {
            addCriterion("enum_chinese_name not like", value, "enumChineseName");
            return (Criteria) this;
        }

        public Criteria andEnumChineseNameIn(List<String> values) {
            addCriterion("enum_chinese_name in", values, "enumChineseName");
            return (Criteria) this;
        }

        public Criteria andEnumChineseNameNotIn(List<String> values) {
            addCriterion("enum_chinese_name not in", values, "enumChineseName");
            return (Criteria) this;
        }

        public Criteria andEnumChineseNameBetween(String value1, String value2) {
            addCriterion("enum_chinese_name between", value1, value2, "enumChineseName");
            return (Criteria) this;
        }

        public Criteria andEnumChineseNameNotBetween(String value1, String value2) {
            addCriterion("enum_chinese_name not between", value1, value2, "enumChineseName");
            return (Criteria) this;
        }

        public Criteria andEnumValueIsNull() {
            addCriterion("enum_value is null");
            return (Criteria) this;
        }

        public Criteria andEnumValueIsNotNull() {
            addCriterion("enum_value is not null");
            return (Criteria) this;
        }

        public Criteria andEnumValueEqualTo(String value) {
            addCriterion("enum_value =", value, "enumValue");
            return (Criteria) this;
        }

        public Criteria andEnumValueNotEqualTo(String value) {
            addCriterion("enum_value <>", value, "enumValue");
            return (Criteria) this;
        }

        public Criteria andEnumValueGreaterThan(String value) {
            addCriterion("enum_value >", value, "enumValue");
            return (Criteria) this;
        }

        public Criteria andEnumValueGreaterThanOrEqualTo(String value) {
            addCriterion("enum_value >=", value, "enumValue");
            return (Criteria) this;
        }

        public Criteria andEnumValueLessThan(String value) {
            addCriterion("enum_value <", value, "enumValue");
            return (Criteria) this;
        }

        public Criteria andEnumValueLessThanOrEqualTo(String value) {
            addCriterion("enum_value <=", value, "enumValue");
            return (Criteria) this;
        }

        public Criteria andEnumValueLike(String value) {
            addCriterion("enum_value like", value, "enumValue");
            return (Criteria) this;
        }

        public Criteria andEnumValueNotLike(String value) {
            addCriterion("enum_value not like", value, "enumValue");
            return (Criteria) this;
        }

        public Criteria andEnumValueIn(List<String> values) {
            addCriterion("enum_value in", values, "enumValue");
            return (Criteria) this;
        }

        public Criteria andEnumValueNotIn(List<String> values) {
            addCriterion("enum_value not in", values, "enumValue");
            return (Criteria) this;
        }

        public Criteria andEnumValueBetween(String value1, String value2) {
            addCriterion("enum_value between", value1, value2, "enumValue");
            return (Criteria) this;
        }

        public Criteria andEnumValueNotBetween(String value1, String value2) {
            addCriterion("enum_value not between", value1, value2, "enumValue");
            return (Criteria) this;
        }

        public Criteria andEnumOrderIsNull() {
            addCriterion("enum_order is null");
            return (Criteria) this;
        }

        public Criteria andEnumOrderIsNotNull() {
            addCriterion("enum_order is not null");
            return (Criteria) this;
        }

        public Criteria andEnumOrderEqualTo(Integer value) {
            addCriterion("enum_order =", value, "enumOrder");
            return (Criteria) this;
        }

        public Criteria andEnumOrderNotEqualTo(Integer value) {
            addCriterion("enum_order <>", value, "enumOrder");
            return (Criteria) this;
        }

        public Criteria andEnumOrderGreaterThan(Integer value) {
            addCriterion("enum_order >", value, "enumOrder");
            return (Criteria) this;
        }

        public Criteria andEnumOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("enum_order >=", value, "enumOrder");
            return (Criteria) this;
        }

        public Criteria andEnumOrderLessThan(Integer value) {
            addCriterion("enum_order <", value, "enumOrder");
            return (Criteria) this;
        }

        public Criteria andEnumOrderLessThanOrEqualTo(Integer value) {
            addCriterion("enum_order <=", value, "enumOrder");
            return (Criteria) this;
        }

        public Criteria andEnumOrderIn(List<Integer> values) {
            addCriterion("enum_order in", values, "enumOrder");
            return (Criteria) this;
        }

        public Criteria andEnumOrderNotIn(List<Integer> values) {
            addCriterion("enum_order not in", values, "enumOrder");
            return (Criteria) this;
        }

        public Criteria andEnumOrderBetween(Integer value1, Integer value2) {
            addCriterion("enum_order between", value1, value2, "enumOrder");
            return (Criteria) this;
        }

        public Criteria andEnumOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("enum_order not between", value1, value2, "enumOrder");
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