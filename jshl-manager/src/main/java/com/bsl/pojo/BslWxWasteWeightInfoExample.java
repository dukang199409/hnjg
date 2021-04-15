package com.bsl.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class BslWxWasteWeightInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BslWxWasteWeightInfoExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andWasteTypeIsNull() {
            addCriterion("waste_type is null");
            return (Criteria) this;
        }

        public Criteria andWasteTypeIsNotNull() {
            addCriterion("waste_type is not null");
            return (Criteria) this;
        }

        public Criteria andWasteTypeEqualTo(String value) {
            addCriterion("waste_type =", value, "wasteType");
            return (Criteria) this;
        }

        public Criteria andWasteTypeNotEqualTo(String value) {
            addCriterion("waste_type <>", value, "wasteType");
            return (Criteria) this;
        }

        public Criteria andWasteTypeGreaterThan(String value) {
            addCriterion("waste_type >", value, "wasteType");
            return (Criteria) this;
        }

        public Criteria andWasteTypeGreaterThanOrEqualTo(String value) {
            addCriterion("waste_type >=", value, "wasteType");
            return (Criteria) this;
        }

        public Criteria andWasteTypeLessThan(String value) {
            addCriterion("waste_type <", value, "wasteType");
            return (Criteria) this;
        }

        public Criteria andWasteTypeLessThanOrEqualTo(String value) {
            addCriterion("waste_type <=", value, "wasteType");
            return (Criteria) this;
        }

        public Criteria andWasteTypeLike(String value) {
            addCriterion("waste_type like", value, "wasteType");
            return (Criteria) this;
        }

        public Criteria andWasteTypeNotLike(String value) {
            addCriterion("waste_type not like", value, "wasteType");
            return (Criteria) this;
        }

        public Criteria andWasteTypeIn(List<String> values) {
            addCriterion("waste_type in", values, "wasteType");
            return (Criteria) this;
        }

        public Criteria andWasteTypeNotIn(List<String> values) {
            addCriterion("waste_type not in", values, "wasteType");
            return (Criteria) this;
        }

        public Criteria andWasteTypeBetween(String value1, String value2) {
            addCriterion("waste_type between", value1, value2, "wasteType");
            return (Criteria) this;
        }

        public Criteria andWasteTypeNotBetween(String value1, String value2) {
            addCriterion("waste_type not between", value1, value2, "wasteType");
            return (Criteria) this;
        }

        public Criteria andWasteWeightIsNull() {
            addCriterion("waste_weight is null");
            return (Criteria) this;
        }

        public Criteria andWasteWeightIsNotNull() {
            addCriterion("waste_weight is not null");
            return (Criteria) this;
        }

        public Criteria andWasteWeightEqualTo(Float value) {
            addCriterion("waste_weight =", value, "wasteWeight");
            return (Criteria) this;
        }

        public Criteria andWasteWeightNotEqualTo(Float value) {
            addCriterion("waste_weight <>", value, "wasteWeight");
            return (Criteria) this;
        }

        public Criteria andWasteWeightGreaterThan(Float value) {
            addCriterion("waste_weight >", value, "wasteWeight");
            return (Criteria) this;
        }

        public Criteria andWasteWeightGreaterThanOrEqualTo(Float value) {
            addCriterion("waste_weight >=", value, "wasteWeight");
            return (Criteria) this;
        }

        public Criteria andWasteWeightLessThan(Float value) {
            addCriterion("waste_weight <", value, "wasteWeight");
            return (Criteria) this;
        }

        public Criteria andWasteWeightLessThanOrEqualTo(Float value) {
            addCriterion("waste_weight <=", value, "wasteWeight");
            return (Criteria) this;
        }

        public Criteria andWasteWeightIn(List<Float> values) {
            addCriterion("waste_weight in", values, "wasteWeight");
            return (Criteria) this;
        }

        public Criteria andWasteWeightNotIn(List<Float> values) {
            addCriterion("waste_weight not in", values, "wasteWeight");
            return (Criteria) this;
        }

        public Criteria andWasteWeightBetween(Float value1, Float value2) {
            addCriterion("waste_weight between", value1, value2, "wasteWeight");
            return (Criteria) this;
        }

        public Criteria andWasteWeightNotBetween(Float value1, Float value2) {
            addCriterion("waste_weight not between", value1, value2, "wasteWeight");
            return (Criteria) this;
        }

        public Criteria andWasteInWeightIsNull() {
            addCriterion("waste_in_weight is null");
            return (Criteria) this;
        }

        public Criteria andWasteInWeightIsNotNull() {
            addCriterion("waste_in_weight is not null");
            return (Criteria) this;
        }

        public Criteria andWasteInWeightEqualTo(Float value) {
            addCriterion("waste_in_weight =", value, "wasteInWeight");
            return (Criteria) this;
        }

        public Criteria andWasteInWeightNotEqualTo(Float value) {
            addCriterion("waste_in_weight <>", value, "wasteInWeight");
            return (Criteria) this;
        }

        public Criteria andWasteInWeightGreaterThan(Float value) {
            addCriterion("waste_in_weight >", value, "wasteInWeight");
            return (Criteria) this;
        }

        public Criteria andWasteInWeightGreaterThanOrEqualTo(Float value) {
            addCriterion("waste_in_weight >=", value, "wasteInWeight");
            return (Criteria) this;
        }

        public Criteria andWasteInWeightLessThan(Float value) {
            addCriterion("waste_in_weight <", value, "wasteInWeight");
            return (Criteria) this;
        }

        public Criteria andWasteInWeightLessThanOrEqualTo(Float value) {
            addCriterion("waste_in_weight <=", value, "wasteInWeight");
            return (Criteria) this;
        }

        public Criteria andWasteInWeightIn(List<Float> values) {
            addCriterion("waste_in_weight in", values, "wasteInWeight");
            return (Criteria) this;
        }

        public Criteria andWasteInWeightNotIn(List<Float> values) {
            addCriterion("waste_in_weight not in", values, "wasteInWeight");
            return (Criteria) this;
        }

        public Criteria andWasteInWeightBetween(Float value1, Float value2) {
            addCriterion("waste_in_weight between", value1, value2, "wasteInWeight");
            return (Criteria) this;
        }

        public Criteria andWasteInWeightNotBetween(Float value1, Float value2) {
            addCriterion("waste_in_weight not between", value1, value2, "wasteInWeight");
            return (Criteria) this;
        }

        public Criteria andWasteOutWeightIsNull() {
            addCriterion("waste_out_weight is null");
            return (Criteria) this;
        }

        public Criteria andWasteOutWeightIsNotNull() {
            addCriterion("waste_out_weight is not null");
            return (Criteria) this;
        }

        public Criteria andWasteOutWeightEqualTo(Float value) {
            addCriterion("waste_out_weight =", value, "wasteOutWeight");
            return (Criteria) this;
        }

        public Criteria andWasteOutWeightNotEqualTo(Float value) {
            addCriterion("waste_out_weight <>", value, "wasteOutWeight");
            return (Criteria) this;
        }

        public Criteria andWasteOutWeightGreaterThan(Float value) {
            addCriterion("waste_out_weight >", value, "wasteOutWeight");
            return (Criteria) this;
        }

        public Criteria andWasteOutWeightGreaterThanOrEqualTo(Float value) {
            addCriterion("waste_out_weight >=", value, "wasteOutWeight");
            return (Criteria) this;
        }

        public Criteria andWasteOutWeightLessThan(Float value) {
            addCriterion("waste_out_weight <", value, "wasteOutWeight");
            return (Criteria) this;
        }

        public Criteria andWasteOutWeightLessThanOrEqualTo(Float value) {
            addCriterion("waste_out_weight <=", value, "wasteOutWeight");
            return (Criteria) this;
        }

        public Criteria andWasteOutWeightIn(List<Float> values) {
            addCriterion("waste_out_weight in", values, "wasteOutWeight");
            return (Criteria) this;
        }

        public Criteria andWasteOutWeightNotIn(List<Float> values) {
            addCriterion("waste_out_weight not in", values, "wasteOutWeight");
            return (Criteria) this;
        }

        public Criteria andWasteOutWeightBetween(Float value1, Float value2) {
            addCriterion("waste_out_weight between", value1, value2, "wasteOutWeight");
            return (Criteria) this;
        }

        public Criteria andWasteOutWeightNotBetween(Float value1, Float value2) {
            addCriterion("waste_out_weight not between", value1, value2, "wasteOutWeight");
            return (Criteria) this;
        }

        public Criteria andCrtDateIsNull() {
            addCriterion("crt_date is null");
            return (Criteria) this;
        }

        public Criteria andCrtDateIsNotNull() {
            addCriterion("crt_date is not null");
            return (Criteria) this;
        }

        public Criteria andCrtDateEqualTo(Date value) {
            addCriterionForJDBCDate("crt_date =", value, "crtDate");
            return (Criteria) this;
        }

        public Criteria andCrtDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("crt_date <>", value, "crtDate");
            return (Criteria) this;
        }

        public Criteria andCrtDateGreaterThan(Date value) {
            addCriterionForJDBCDate("crt_date >", value, "crtDate");
            return (Criteria) this;
        }

        public Criteria andCrtDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("crt_date >=", value, "crtDate");
            return (Criteria) this;
        }

        public Criteria andCrtDateLessThan(Date value) {
            addCriterionForJDBCDate("crt_date <", value, "crtDate");
            return (Criteria) this;
        }

        public Criteria andCrtDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("crt_date <=", value, "crtDate");
            return (Criteria) this;
        }

        public Criteria andCrtDateIn(List<Date> values) {
            addCriterionForJDBCDate("crt_date in", values, "crtDate");
            return (Criteria) this;
        }

        public Criteria andCrtDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("crt_date not in", values, "crtDate");
            return (Criteria) this;
        }

        public Criteria andCrtDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("crt_date between", value1, value2, "crtDate");
            return (Criteria) this;
        }

        public Criteria andCrtDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("crt_date not between", value1, value2, "crtDate");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
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