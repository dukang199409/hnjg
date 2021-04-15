package com.bsl.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class BslLuStockInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BslLuStockInfoExample() {
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

        public Criteria andTransDateIsNull() {
            addCriterion("trans_date is null");
            return (Criteria) this;
        }

        public Criteria andTransDateIsNotNull() {
            addCriterion("trans_date is not null");
            return (Criteria) this;
        }

        public Criteria andTransDateEqualTo(Date value) {
            addCriterionForJDBCDate("trans_date =", value, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("trans_date <>", value, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransDateGreaterThan(Date value) {
            addCriterionForJDBCDate("trans_date >", value, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("trans_date >=", value, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransDateLessThan(Date value) {
            addCriterionForJDBCDate("trans_date <", value, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("trans_date <=", value, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransDateIn(List<Date> values) {
            addCriterionForJDBCDate("trans_date in", values, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("trans_date not in", values, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("trans_date between", value1, value2, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("trans_date not between", value1, value2, "transDate");
            return (Criteria) this;
        }

        public Criteria andProdLunoIsNull() {
            addCriterion("prod_luno is null");
            return (Criteria) this;
        }

        public Criteria andProdLunoIsNotNull() {
            addCriterion("prod_luno is not null");
            return (Criteria) this;
        }

        public Criteria andProdLunoEqualTo(String value) {
            addCriterion("prod_luno =", value, "prodLuno");
            return (Criteria) this;
        }

        public Criteria andProdLunoNotEqualTo(String value) {
            addCriterion("prod_luno <>", value, "prodLuno");
            return (Criteria) this;
        }

        public Criteria andProdLunoGreaterThan(String value) {
            addCriterion("prod_luno >", value, "prodLuno");
            return (Criteria) this;
        }

        public Criteria andProdLunoGreaterThanOrEqualTo(String value) {
            addCriterion("prod_luno >=", value, "prodLuno");
            return (Criteria) this;
        }

        public Criteria andProdLunoLessThan(String value) {
            addCriterion("prod_luno <", value, "prodLuno");
            return (Criteria) this;
        }

        public Criteria andProdLunoLessThanOrEqualTo(String value) {
            addCriterion("prod_luno <=", value, "prodLuno");
            return (Criteria) this;
        }

        public Criteria andProdLunoLike(String value) {
            addCriterion("prod_luno like", value, "prodLuno");
            return (Criteria) this;
        }

        public Criteria andProdLunoNotLike(String value) {
            addCriterion("prod_luno not like", value, "prodLuno");
            return (Criteria) this;
        }

        public Criteria andProdLunoIn(List<String> values) {
            addCriterion("prod_luno in", values, "prodLuno");
            return (Criteria) this;
        }

        public Criteria andProdLunoNotIn(List<String> values) {
            addCriterion("prod_luno not in", values, "prodLuno");
            return (Criteria) this;
        }

        public Criteria andProdLunoBetween(String value1, String value2) {
            addCriterion("prod_luno between", value1, value2, "prodLuno");
            return (Criteria) this;
        }

        public Criteria andProdLunoNotBetween(String value1, String value2) {
            addCriterion("prod_luno not between", value1, value2, "prodLuno");
            return (Criteria) this;
        }

        public Criteria andRawAllNumIsNull() {
            addCriterion("raw_all_num is null");
            return (Criteria) this;
        }

        public Criteria andRawAllNumIsNotNull() {
            addCriterion("raw_all_num is not null");
            return (Criteria) this;
        }

        public Criteria andRawAllNumEqualTo(Integer value) {
            addCriterion("raw_all_num =", value, "rawAllNum");
            return (Criteria) this;
        }

        public Criteria andRawAllNumNotEqualTo(Integer value) {
            addCriterion("raw_all_num <>", value, "rawAllNum");
            return (Criteria) this;
        }

        public Criteria andRawAllNumGreaterThan(Integer value) {
            addCriterion("raw_all_num >", value, "rawAllNum");
            return (Criteria) this;
        }

        public Criteria andRawAllNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("raw_all_num >=", value, "rawAllNum");
            return (Criteria) this;
        }

        public Criteria andRawAllNumLessThan(Integer value) {
            addCriterion("raw_all_num <", value, "rawAllNum");
            return (Criteria) this;
        }

        public Criteria andRawAllNumLessThanOrEqualTo(Integer value) {
            addCriterion("raw_all_num <=", value, "rawAllNum");
            return (Criteria) this;
        }

        public Criteria andRawAllNumIn(List<Integer> values) {
            addCriterion("raw_all_num in", values, "rawAllNum");
            return (Criteria) this;
        }

        public Criteria andRawAllNumNotIn(List<Integer> values) {
            addCriterion("raw_all_num not in", values, "rawAllNum");
            return (Criteria) this;
        }

        public Criteria andRawAllNumBetween(Integer value1, Integer value2) {
            addCriterion("raw_all_num between", value1, value2, "rawAllNum");
            return (Criteria) this;
        }

        public Criteria andRawAllNumNotBetween(Integer value1, Integer value2) {
            addCriterion("raw_all_num not between", value1, value2, "rawAllNum");
            return (Criteria) this;
        }

        public Criteria andRawAllWeightIsNull() {
            addCriterion("raw_all_weight is null");
            return (Criteria) this;
        }

        public Criteria andRawAllWeightIsNotNull() {
            addCriterion("raw_all_weight is not null");
            return (Criteria) this;
        }

        public Criteria andRawAllWeightEqualTo(Float value) {
            addCriterion("raw_all_weight =", value, "rawAllWeight");
            return (Criteria) this;
        }

        public Criteria andRawAllWeightNotEqualTo(Float value) {
            addCriterion("raw_all_weight <>", value, "rawAllWeight");
            return (Criteria) this;
        }

        public Criteria andRawAllWeightGreaterThan(Float value) {
            addCriterion("raw_all_weight >", value, "rawAllWeight");
            return (Criteria) this;
        }

        public Criteria andRawAllWeightGreaterThanOrEqualTo(Float value) {
            addCriterion("raw_all_weight >=", value, "rawAllWeight");
            return (Criteria) this;
        }

        public Criteria andRawAllWeightLessThan(Float value) {
            addCriterion("raw_all_weight <", value, "rawAllWeight");
            return (Criteria) this;
        }

        public Criteria andRawAllWeightLessThanOrEqualTo(Float value) {
            addCriterion("raw_all_weight <=", value, "rawAllWeight");
            return (Criteria) this;
        }

        public Criteria andRawAllWeightIn(List<Float> values) {
            addCriterion("raw_all_weight in", values, "rawAllWeight");
            return (Criteria) this;
        }

        public Criteria andRawAllWeightNotIn(List<Float> values) {
            addCriterion("raw_all_weight not in", values, "rawAllWeight");
            return (Criteria) this;
        }

        public Criteria andRawAllWeightBetween(Float value1, Float value2) {
            addCriterion("raw_all_weight between", value1, value2, "rawAllWeight");
            return (Criteria) this;
        }

        public Criteria andRawAllWeightNotBetween(Float value1, Float value2) {
            addCriterion("raw_all_weight not between", value1, value2, "rawAllWeight");
            return (Criteria) this;
        }

        public Criteria andRawNumIsNull() {
            addCriterion("raw_num is null");
            return (Criteria) this;
        }

        public Criteria andRawNumIsNotNull() {
            addCriterion("raw_num is not null");
            return (Criteria) this;
        }

        public Criteria andRawNumEqualTo(Integer value) {
            addCriterion("raw_num =", value, "rawNum");
            return (Criteria) this;
        }

        public Criteria andRawNumNotEqualTo(Integer value) {
            addCriterion("raw_num <>", value, "rawNum");
            return (Criteria) this;
        }

        public Criteria andRawNumGreaterThan(Integer value) {
            addCriterion("raw_num >", value, "rawNum");
            return (Criteria) this;
        }

        public Criteria andRawNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("raw_num >=", value, "rawNum");
            return (Criteria) this;
        }

        public Criteria andRawNumLessThan(Integer value) {
            addCriterion("raw_num <", value, "rawNum");
            return (Criteria) this;
        }

        public Criteria andRawNumLessThanOrEqualTo(Integer value) {
            addCriterion("raw_num <=", value, "rawNum");
            return (Criteria) this;
        }

        public Criteria andRawNumIn(List<Integer> values) {
            addCriterion("raw_num in", values, "rawNum");
            return (Criteria) this;
        }

        public Criteria andRawNumNotIn(List<Integer> values) {
            addCriterion("raw_num not in", values, "rawNum");
            return (Criteria) this;
        }

        public Criteria andRawNumBetween(Integer value1, Integer value2) {
            addCriterion("raw_num between", value1, value2, "rawNum");
            return (Criteria) this;
        }

        public Criteria andRawNumNotBetween(Integer value1, Integer value2) {
            addCriterion("raw_num not between", value1, value2, "rawNum");
            return (Criteria) this;
        }

        public Criteria andRawWeightIsNull() {
            addCriterion("raw_weight is null");
            return (Criteria) this;
        }

        public Criteria andRawWeightIsNotNull() {
            addCriterion("raw_weight is not null");
            return (Criteria) this;
        }

        public Criteria andRawWeightEqualTo(Float value) {
            addCriterion("raw_weight =", value, "rawWeight");
            return (Criteria) this;
        }

        public Criteria andRawWeightNotEqualTo(Float value) {
            addCriterion("raw_weight <>", value, "rawWeight");
            return (Criteria) this;
        }

        public Criteria andRawWeightGreaterThan(Float value) {
            addCriterion("raw_weight >", value, "rawWeight");
            return (Criteria) this;
        }

        public Criteria andRawWeightGreaterThanOrEqualTo(Float value) {
            addCriterion("raw_weight >=", value, "rawWeight");
            return (Criteria) this;
        }

        public Criteria andRawWeightLessThan(Float value) {
            addCriterion("raw_weight <", value, "rawWeight");
            return (Criteria) this;
        }

        public Criteria andRawWeightLessThanOrEqualTo(Float value) {
            addCriterion("raw_weight <=", value, "rawWeight");
            return (Criteria) this;
        }

        public Criteria andRawWeightIn(List<Float> values) {
            addCriterion("raw_weight in", values, "rawWeight");
            return (Criteria) this;
        }

        public Criteria andRawWeightNotIn(List<Float> values) {
            addCriterion("raw_weight not in", values, "rawWeight");
            return (Criteria) this;
        }

        public Criteria andRawWeightBetween(Float value1, Float value2) {
            addCriterion("raw_weight between", value1, value2, "rawWeight");
            return (Criteria) this;
        }

        public Criteria andRawWeightNotBetween(Float value1, Float value2) {
            addCriterion("raw_weight not between", value1, value2, "rawWeight");
            return (Criteria) this;
        }

        public Criteria andHalfAllNumIsNull() {
            addCriterion("half_all_num is null");
            return (Criteria) this;
        }

        public Criteria andHalfAllNumIsNotNull() {
            addCriterion("half_all_num is not null");
            return (Criteria) this;
        }

        public Criteria andHalfAllNumEqualTo(Integer value) {
            addCriterion("half_all_num =", value, "halfAllNum");
            return (Criteria) this;
        }

        public Criteria andHalfAllNumNotEqualTo(Integer value) {
            addCriterion("half_all_num <>", value, "halfAllNum");
            return (Criteria) this;
        }

        public Criteria andHalfAllNumGreaterThan(Integer value) {
            addCriterion("half_all_num >", value, "halfAllNum");
            return (Criteria) this;
        }

        public Criteria andHalfAllNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("half_all_num >=", value, "halfAllNum");
            return (Criteria) this;
        }

        public Criteria andHalfAllNumLessThan(Integer value) {
            addCriterion("half_all_num <", value, "halfAllNum");
            return (Criteria) this;
        }

        public Criteria andHalfAllNumLessThanOrEqualTo(Integer value) {
            addCriterion("half_all_num <=", value, "halfAllNum");
            return (Criteria) this;
        }

        public Criteria andHalfAllNumIn(List<Integer> values) {
            addCriterion("half_all_num in", values, "halfAllNum");
            return (Criteria) this;
        }

        public Criteria andHalfAllNumNotIn(List<Integer> values) {
            addCriterion("half_all_num not in", values, "halfAllNum");
            return (Criteria) this;
        }

        public Criteria andHalfAllNumBetween(Integer value1, Integer value2) {
            addCriterion("half_all_num between", value1, value2, "halfAllNum");
            return (Criteria) this;
        }

        public Criteria andHalfAllNumNotBetween(Integer value1, Integer value2) {
            addCriterion("half_all_num not between", value1, value2, "halfAllNum");
            return (Criteria) this;
        }

        public Criteria andHalfAllWeightIsNull() {
            addCriterion("half_all_weight is null");
            return (Criteria) this;
        }

        public Criteria andHalfAllWeightIsNotNull() {
            addCriterion("half_all_weight is not null");
            return (Criteria) this;
        }

        public Criteria andHalfAllWeightEqualTo(Float value) {
            addCriterion("half_all_weight =", value, "halfAllWeight");
            return (Criteria) this;
        }

        public Criteria andHalfAllWeightNotEqualTo(Float value) {
            addCriterion("half_all_weight <>", value, "halfAllWeight");
            return (Criteria) this;
        }

        public Criteria andHalfAllWeightGreaterThan(Float value) {
            addCriterion("half_all_weight >", value, "halfAllWeight");
            return (Criteria) this;
        }

        public Criteria andHalfAllWeightGreaterThanOrEqualTo(Float value) {
            addCriterion("half_all_weight >=", value, "halfAllWeight");
            return (Criteria) this;
        }

        public Criteria andHalfAllWeightLessThan(Float value) {
            addCriterion("half_all_weight <", value, "halfAllWeight");
            return (Criteria) this;
        }

        public Criteria andHalfAllWeightLessThanOrEqualTo(Float value) {
            addCriterion("half_all_weight <=", value, "halfAllWeight");
            return (Criteria) this;
        }

        public Criteria andHalfAllWeightIn(List<Float> values) {
            addCriterion("half_all_weight in", values, "halfAllWeight");
            return (Criteria) this;
        }

        public Criteria andHalfAllWeightNotIn(List<Float> values) {
            addCriterion("half_all_weight not in", values, "halfAllWeight");
            return (Criteria) this;
        }

        public Criteria andHalfAllWeightBetween(Float value1, Float value2) {
            addCriterion("half_all_weight between", value1, value2, "halfAllWeight");
            return (Criteria) this;
        }

        public Criteria andHalfAllWeightNotBetween(Float value1, Float value2) {
            addCriterion("half_all_weight not between", value1, value2, "halfAllWeight");
            return (Criteria) this;
        }

        public Criteria andHalfNumIsNull() {
            addCriterion("half_num is null");
            return (Criteria) this;
        }

        public Criteria andHalfNumIsNotNull() {
            addCriterion("half_num is not null");
            return (Criteria) this;
        }

        public Criteria andHalfNumEqualTo(Integer value) {
            addCriterion("half_num =", value, "halfNum");
            return (Criteria) this;
        }

        public Criteria andHalfNumNotEqualTo(Integer value) {
            addCriterion("half_num <>", value, "halfNum");
            return (Criteria) this;
        }

        public Criteria andHalfNumGreaterThan(Integer value) {
            addCriterion("half_num >", value, "halfNum");
            return (Criteria) this;
        }

        public Criteria andHalfNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("half_num >=", value, "halfNum");
            return (Criteria) this;
        }

        public Criteria andHalfNumLessThan(Integer value) {
            addCriterion("half_num <", value, "halfNum");
            return (Criteria) this;
        }

        public Criteria andHalfNumLessThanOrEqualTo(Integer value) {
            addCriterion("half_num <=", value, "halfNum");
            return (Criteria) this;
        }

        public Criteria andHalfNumIn(List<Integer> values) {
            addCriterion("half_num in", values, "halfNum");
            return (Criteria) this;
        }

        public Criteria andHalfNumNotIn(List<Integer> values) {
            addCriterion("half_num not in", values, "halfNum");
            return (Criteria) this;
        }

        public Criteria andHalfNumBetween(Integer value1, Integer value2) {
            addCriterion("half_num between", value1, value2, "halfNum");
            return (Criteria) this;
        }

        public Criteria andHalfNumNotBetween(Integer value1, Integer value2) {
            addCriterion("half_num not between", value1, value2, "halfNum");
            return (Criteria) this;
        }

        public Criteria andHalfWeightIsNull() {
            addCriterion("half_weight is null");
            return (Criteria) this;
        }

        public Criteria andHalfWeightIsNotNull() {
            addCriterion("half_weight is not null");
            return (Criteria) this;
        }

        public Criteria andHalfWeightEqualTo(Float value) {
            addCriterion("half_weight =", value, "halfWeight");
            return (Criteria) this;
        }

        public Criteria andHalfWeightNotEqualTo(Float value) {
            addCriterion("half_weight <>", value, "halfWeight");
            return (Criteria) this;
        }

        public Criteria andHalfWeightGreaterThan(Float value) {
            addCriterion("half_weight >", value, "halfWeight");
            return (Criteria) this;
        }

        public Criteria andHalfWeightGreaterThanOrEqualTo(Float value) {
            addCriterion("half_weight >=", value, "halfWeight");
            return (Criteria) this;
        }

        public Criteria andHalfWeightLessThan(Float value) {
            addCriterion("half_weight <", value, "halfWeight");
            return (Criteria) this;
        }

        public Criteria andHalfWeightLessThanOrEqualTo(Float value) {
            addCriterion("half_weight <=", value, "halfWeight");
            return (Criteria) this;
        }

        public Criteria andHalfWeightIn(List<Float> values) {
            addCriterion("half_weight in", values, "halfWeight");
            return (Criteria) this;
        }

        public Criteria andHalfWeightNotIn(List<Float> values) {
            addCriterion("half_weight not in", values, "halfWeight");
            return (Criteria) this;
        }

        public Criteria andHalfWeightBetween(Float value1, Float value2) {
            addCriterion("half_weight between", value1, value2, "halfWeight");
            return (Criteria) this;
        }

        public Criteria andHalfWeightNotBetween(Float value1, Float value2) {
            addCriterion("half_weight not between", value1, value2, "halfWeight");
            return (Criteria) this;
        }

        public Criteria andProdAllNumIsNull() {
            addCriterion("prod_all_num is null");
            return (Criteria) this;
        }

        public Criteria andProdAllNumIsNotNull() {
            addCriterion("prod_all_num is not null");
            return (Criteria) this;
        }

        public Criteria andProdAllNumEqualTo(Integer value) {
            addCriterion("prod_all_num =", value, "prodAllNum");
            return (Criteria) this;
        }

        public Criteria andProdAllNumNotEqualTo(Integer value) {
            addCriterion("prod_all_num <>", value, "prodAllNum");
            return (Criteria) this;
        }

        public Criteria andProdAllNumGreaterThan(Integer value) {
            addCriterion("prod_all_num >", value, "prodAllNum");
            return (Criteria) this;
        }

        public Criteria andProdAllNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("prod_all_num >=", value, "prodAllNum");
            return (Criteria) this;
        }

        public Criteria andProdAllNumLessThan(Integer value) {
            addCriterion("prod_all_num <", value, "prodAllNum");
            return (Criteria) this;
        }

        public Criteria andProdAllNumLessThanOrEqualTo(Integer value) {
            addCriterion("prod_all_num <=", value, "prodAllNum");
            return (Criteria) this;
        }

        public Criteria andProdAllNumIn(List<Integer> values) {
            addCriterion("prod_all_num in", values, "prodAllNum");
            return (Criteria) this;
        }

        public Criteria andProdAllNumNotIn(List<Integer> values) {
            addCriterion("prod_all_num not in", values, "prodAllNum");
            return (Criteria) this;
        }

        public Criteria andProdAllNumBetween(Integer value1, Integer value2) {
            addCriterion("prod_all_num between", value1, value2, "prodAllNum");
            return (Criteria) this;
        }

        public Criteria andProdAllNumNotBetween(Integer value1, Integer value2) {
            addCriterion("prod_all_num not between", value1, value2, "prodAllNum");
            return (Criteria) this;
        }

        public Criteria andProdAllWeightIsNull() {
            addCriterion("prod_all_weight is null");
            return (Criteria) this;
        }

        public Criteria andProdAllWeightIsNotNull() {
            addCriterion("prod_all_weight is not null");
            return (Criteria) this;
        }

        public Criteria andProdAllWeightEqualTo(Float value) {
            addCriterion("prod_all_weight =", value, "prodAllWeight");
            return (Criteria) this;
        }

        public Criteria andProdAllWeightNotEqualTo(Float value) {
            addCriterion("prod_all_weight <>", value, "prodAllWeight");
            return (Criteria) this;
        }

        public Criteria andProdAllWeightGreaterThan(Float value) {
            addCriterion("prod_all_weight >", value, "prodAllWeight");
            return (Criteria) this;
        }

        public Criteria andProdAllWeightGreaterThanOrEqualTo(Float value) {
            addCriterion("prod_all_weight >=", value, "prodAllWeight");
            return (Criteria) this;
        }

        public Criteria andProdAllWeightLessThan(Float value) {
            addCriterion("prod_all_weight <", value, "prodAllWeight");
            return (Criteria) this;
        }

        public Criteria andProdAllWeightLessThanOrEqualTo(Float value) {
            addCriterion("prod_all_weight <=", value, "prodAllWeight");
            return (Criteria) this;
        }

        public Criteria andProdAllWeightIn(List<Float> values) {
            addCriterion("prod_all_weight in", values, "prodAllWeight");
            return (Criteria) this;
        }

        public Criteria andProdAllWeightNotIn(List<Float> values) {
            addCriterion("prod_all_weight not in", values, "prodAllWeight");
            return (Criteria) this;
        }

        public Criteria andProdAllWeightBetween(Float value1, Float value2) {
            addCriterion("prod_all_weight between", value1, value2, "prodAllWeight");
            return (Criteria) this;
        }

        public Criteria andProdAllWeightNotBetween(Float value1, Float value2) {
            addCriterion("prod_all_weight not between", value1, value2, "prodAllWeight");
            return (Criteria) this;
        }

        public Criteria andProdNumIsNull() {
            addCriterion("prod_num is null");
            return (Criteria) this;
        }

        public Criteria andProdNumIsNotNull() {
            addCriterion("prod_num is not null");
            return (Criteria) this;
        }

        public Criteria andProdNumEqualTo(Integer value) {
            addCriterion("prod_num =", value, "prodNum");
            return (Criteria) this;
        }

        public Criteria andProdNumNotEqualTo(Integer value) {
            addCriterion("prod_num <>", value, "prodNum");
            return (Criteria) this;
        }

        public Criteria andProdNumGreaterThan(Integer value) {
            addCriterion("prod_num >", value, "prodNum");
            return (Criteria) this;
        }

        public Criteria andProdNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("prod_num >=", value, "prodNum");
            return (Criteria) this;
        }

        public Criteria andProdNumLessThan(Integer value) {
            addCriterion("prod_num <", value, "prodNum");
            return (Criteria) this;
        }

        public Criteria andProdNumLessThanOrEqualTo(Integer value) {
            addCriterion("prod_num <=", value, "prodNum");
            return (Criteria) this;
        }

        public Criteria andProdNumIn(List<Integer> values) {
            addCriterion("prod_num in", values, "prodNum");
            return (Criteria) this;
        }

        public Criteria andProdNumNotIn(List<Integer> values) {
            addCriterion("prod_num not in", values, "prodNum");
            return (Criteria) this;
        }

        public Criteria andProdNumBetween(Integer value1, Integer value2) {
            addCriterion("prod_num between", value1, value2, "prodNum");
            return (Criteria) this;
        }

        public Criteria andProdNumNotBetween(Integer value1, Integer value2) {
            addCriterion("prod_num not between", value1, value2, "prodNum");
            return (Criteria) this;
        }

        public Criteria andProdWeightIsNull() {
            addCriterion("prod_weight is null");
            return (Criteria) this;
        }

        public Criteria andProdWeightIsNotNull() {
            addCriterion("prod_weight is not null");
            return (Criteria) this;
        }

        public Criteria andProdWeightEqualTo(Float value) {
            addCriterion("prod_weight =", value, "prodWeight");
            return (Criteria) this;
        }

        public Criteria andProdWeightNotEqualTo(Float value) {
            addCriterion("prod_weight <>", value, "prodWeight");
            return (Criteria) this;
        }

        public Criteria andProdWeightGreaterThan(Float value) {
            addCriterion("prod_weight >", value, "prodWeight");
            return (Criteria) this;
        }

        public Criteria andProdWeightGreaterThanOrEqualTo(Float value) {
            addCriterion("prod_weight >=", value, "prodWeight");
            return (Criteria) this;
        }

        public Criteria andProdWeightLessThan(Float value) {
            addCriterion("prod_weight <", value, "prodWeight");
            return (Criteria) this;
        }

        public Criteria andProdWeightLessThanOrEqualTo(Float value) {
            addCriterion("prod_weight <=", value, "prodWeight");
            return (Criteria) this;
        }

        public Criteria andProdWeightIn(List<Float> values) {
            addCriterion("prod_weight in", values, "prodWeight");
            return (Criteria) this;
        }

        public Criteria andProdWeightNotIn(List<Float> values) {
            addCriterion("prod_weight not in", values, "prodWeight");
            return (Criteria) this;
        }

        public Criteria andProdWeightBetween(Float value1, Float value2) {
            addCriterion("prod_weight between", value1, value2, "prodWeight");
            return (Criteria) this;
        }

        public Criteria andProdWeightNotBetween(Float value1, Float value2) {
            addCriterion("prod_weight not between", value1, value2, "prodWeight");
            return (Criteria) this;
        }

        public Criteria andSaleRawNumIsNull() {
            addCriterion("sale_raw_num is null");
            return (Criteria) this;
        }

        public Criteria andSaleRawNumIsNotNull() {
            addCriterion("sale_raw_num is not null");
            return (Criteria) this;
        }

        public Criteria andSaleRawNumEqualTo(Integer value) {
            addCriterion("sale_raw_num =", value, "saleRawNum");
            return (Criteria) this;
        }

        public Criteria andSaleRawNumNotEqualTo(Integer value) {
            addCriterion("sale_raw_num <>", value, "saleRawNum");
            return (Criteria) this;
        }

        public Criteria andSaleRawNumGreaterThan(Integer value) {
            addCriterion("sale_raw_num >", value, "saleRawNum");
            return (Criteria) this;
        }

        public Criteria andSaleRawNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("sale_raw_num >=", value, "saleRawNum");
            return (Criteria) this;
        }

        public Criteria andSaleRawNumLessThan(Integer value) {
            addCriterion("sale_raw_num <", value, "saleRawNum");
            return (Criteria) this;
        }

        public Criteria andSaleRawNumLessThanOrEqualTo(Integer value) {
            addCriterion("sale_raw_num <=", value, "saleRawNum");
            return (Criteria) this;
        }

        public Criteria andSaleRawNumIn(List<Integer> values) {
            addCriterion("sale_raw_num in", values, "saleRawNum");
            return (Criteria) this;
        }

        public Criteria andSaleRawNumNotIn(List<Integer> values) {
            addCriterion("sale_raw_num not in", values, "saleRawNum");
            return (Criteria) this;
        }

        public Criteria andSaleRawNumBetween(Integer value1, Integer value2) {
            addCriterion("sale_raw_num between", value1, value2, "saleRawNum");
            return (Criteria) this;
        }

        public Criteria andSaleRawNumNotBetween(Integer value1, Integer value2) {
            addCriterion("sale_raw_num not between", value1, value2, "saleRawNum");
            return (Criteria) this;
        }

        public Criteria andSaleRawWeightIsNull() {
            addCriterion("sale_raw_weight is null");
            return (Criteria) this;
        }

        public Criteria andSaleRawWeightIsNotNull() {
            addCriterion("sale_raw_weight is not null");
            return (Criteria) this;
        }

        public Criteria andSaleRawWeightEqualTo(Float value) {
            addCriterion("sale_raw_weight =", value, "saleRawWeight");
            return (Criteria) this;
        }

        public Criteria andSaleRawWeightNotEqualTo(Float value) {
            addCriterion("sale_raw_weight <>", value, "saleRawWeight");
            return (Criteria) this;
        }

        public Criteria andSaleRawWeightGreaterThan(Float value) {
            addCriterion("sale_raw_weight >", value, "saleRawWeight");
            return (Criteria) this;
        }

        public Criteria andSaleRawWeightGreaterThanOrEqualTo(Float value) {
            addCriterion("sale_raw_weight >=", value, "saleRawWeight");
            return (Criteria) this;
        }

        public Criteria andSaleRawWeightLessThan(Float value) {
            addCriterion("sale_raw_weight <", value, "saleRawWeight");
            return (Criteria) this;
        }

        public Criteria andSaleRawWeightLessThanOrEqualTo(Float value) {
            addCriterion("sale_raw_weight <=", value, "saleRawWeight");
            return (Criteria) this;
        }

        public Criteria andSaleRawWeightIn(List<Float> values) {
            addCriterion("sale_raw_weight in", values, "saleRawWeight");
            return (Criteria) this;
        }

        public Criteria andSaleRawWeightNotIn(List<Float> values) {
            addCriterion("sale_raw_weight not in", values, "saleRawWeight");
            return (Criteria) this;
        }

        public Criteria andSaleRawWeightBetween(Float value1, Float value2) {
            addCriterion("sale_raw_weight between", value1, value2, "saleRawWeight");
            return (Criteria) this;
        }

        public Criteria andSaleRawWeightNotBetween(Float value1, Float value2) {
            addCriterion("sale_raw_weight not between", value1, value2, "saleRawWeight");
            return (Criteria) this;
        }

        public Criteria andSaleHalfNumIsNull() {
            addCriterion("sale_half_num is null");
            return (Criteria) this;
        }

        public Criteria andSaleHalfNumIsNotNull() {
            addCriterion("sale_half_num is not null");
            return (Criteria) this;
        }

        public Criteria andSaleHalfNumEqualTo(Integer value) {
            addCriterion("sale_half_num =", value, "saleHalfNum");
            return (Criteria) this;
        }

        public Criteria andSaleHalfNumNotEqualTo(Integer value) {
            addCriterion("sale_half_num <>", value, "saleHalfNum");
            return (Criteria) this;
        }

        public Criteria andSaleHalfNumGreaterThan(Integer value) {
            addCriterion("sale_half_num >", value, "saleHalfNum");
            return (Criteria) this;
        }

        public Criteria andSaleHalfNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("sale_half_num >=", value, "saleHalfNum");
            return (Criteria) this;
        }

        public Criteria andSaleHalfNumLessThan(Integer value) {
            addCriterion("sale_half_num <", value, "saleHalfNum");
            return (Criteria) this;
        }

        public Criteria andSaleHalfNumLessThanOrEqualTo(Integer value) {
            addCriterion("sale_half_num <=", value, "saleHalfNum");
            return (Criteria) this;
        }

        public Criteria andSaleHalfNumIn(List<Integer> values) {
            addCriterion("sale_half_num in", values, "saleHalfNum");
            return (Criteria) this;
        }

        public Criteria andSaleHalfNumNotIn(List<Integer> values) {
            addCriterion("sale_half_num not in", values, "saleHalfNum");
            return (Criteria) this;
        }

        public Criteria andSaleHalfNumBetween(Integer value1, Integer value2) {
            addCriterion("sale_half_num between", value1, value2, "saleHalfNum");
            return (Criteria) this;
        }

        public Criteria andSaleHalfNumNotBetween(Integer value1, Integer value2) {
            addCriterion("sale_half_num not between", value1, value2, "saleHalfNum");
            return (Criteria) this;
        }

        public Criteria andSaleHalfWeightIsNull() {
            addCriterion("sale_half_weight is null");
            return (Criteria) this;
        }

        public Criteria andSaleHalfWeightIsNotNull() {
            addCriterion("sale_half_weight is not null");
            return (Criteria) this;
        }

        public Criteria andSaleHalfWeightEqualTo(Float value) {
            addCriterion("sale_half_weight =", value, "saleHalfWeight");
            return (Criteria) this;
        }

        public Criteria andSaleHalfWeightNotEqualTo(Float value) {
            addCriterion("sale_half_weight <>", value, "saleHalfWeight");
            return (Criteria) this;
        }

        public Criteria andSaleHalfWeightGreaterThan(Float value) {
            addCriterion("sale_half_weight >", value, "saleHalfWeight");
            return (Criteria) this;
        }

        public Criteria andSaleHalfWeightGreaterThanOrEqualTo(Float value) {
            addCriterion("sale_half_weight >=", value, "saleHalfWeight");
            return (Criteria) this;
        }

        public Criteria andSaleHalfWeightLessThan(Float value) {
            addCriterion("sale_half_weight <", value, "saleHalfWeight");
            return (Criteria) this;
        }

        public Criteria andSaleHalfWeightLessThanOrEqualTo(Float value) {
            addCriterion("sale_half_weight <=", value, "saleHalfWeight");
            return (Criteria) this;
        }

        public Criteria andSaleHalfWeightIn(List<Float> values) {
            addCriterion("sale_half_weight in", values, "saleHalfWeight");
            return (Criteria) this;
        }

        public Criteria andSaleHalfWeightNotIn(List<Float> values) {
            addCriterion("sale_half_weight not in", values, "saleHalfWeight");
            return (Criteria) this;
        }

        public Criteria andSaleHalfWeightBetween(Float value1, Float value2) {
            addCriterion("sale_half_weight between", value1, value2, "saleHalfWeight");
            return (Criteria) this;
        }

        public Criteria andSaleHalfWeightNotBetween(Float value1, Float value2) {
            addCriterion("sale_half_weight not between", value1, value2, "saleHalfWeight");
            return (Criteria) this;
        }

        public Criteria andSaleProdNumIsNull() {
            addCriterion("sale_prod_num is null");
            return (Criteria) this;
        }

        public Criteria andSaleProdNumIsNotNull() {
            addCriterion("sale_prod_num is not null");
            return (Criteria) this;
        }

        public Criteria andSaleProdNumEqualTo(Integer value) {
            addCriterion("sale_prod_num =", value, "saleProdNum");
            return (Criteria) this;
        }

        public Criteria andSaleProdNumNotEqualTo(Integer value) {
            addCriterion("sale_prod_num <>", value, "saleProdNum");
            return (Criteria) this;
        }

        public Criteria andSaleProdNumGreaterThan(Integer value) {
            addCriterion("sale_prod_num >", value, "saleProdNum");
            return (Criteria) this;
        }

        public Criteria andSaleProdNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("sale_prod_num >=", value, "saleProdNum");
            return (Criteria) this;
        }

        public Criteria andSaleProdNumLessThan(Integer value) {
            addCriterion("sale_prod_num <", value, "saleProdNum");
            return (Criteria) this;
        }

        public Criteria andSaleProdNumLessThanOrEqualTo(Integer value) {
            addCriterion("sale_prod_num <=", value, "saleProdNum");
            return (Criteria) this;
        }

        public Criteria andSaleProdNumIn(List<Integer> values) {
            addCriterion("sale_prod_num in", values, "saleProdNum");
            return (Criteria) this;
        }

        public Criteria andSaleProdNumNotIn(List<Integer> values) {
            addCriterion("sale_prod_num not in", values, "saleProdNum");
            return (Criteria) this;
        }

        public Criteria andSaleProdNumBetween(Integer value1, Integer value2) {
            addCriterion("sale_prod_num between", value1, value2, "saleProdNum");
            return (Criteria) this;
        }

        public Criteria andSaleProdNumNotBetween(Integer value1, Integer value2) {
            addCriterion("sale_prod_num not between", value1, value2, "saleProdNum");
            return (Criteria) this;
        }

        public Criteria andSaleProdWeightIsNull() {
            addCriterion("sale_prod_weight is null");
            return (Criteria) this;
        }

        public Criteria andSaleProdWeightIsNotNull() {
            addCriterion("sale_prod_weight is not null");
            return (Criteria) this;
        }

        public Criteria andSaleProdWeightEqualTo(Float value) {
            addCriterion("sale_prod_weight =", value, "saleProdWeight");
            return (Criteria) this;
        }

        public Criteria andSaleProdWeightNotEqualTo(Float value) {
            addCriterion("sale_prod_weight <>", value, "saleProdWeight");
            return (Criteria) this;
        }

        public Criteria andSaleProdWeightGreaterThan(Float value) {
            addCriterion("sale_prod_weight >", value, "saleProdWeight");
            return (Criteria) this;
        }

        public Criteria andSaleProdWeightGreaterThanOrEqualTo(Float value) {
            addCriterion("sale_prod_weight >=", value, "saleProdWeight");
            return (Criteria) this;
        }

        public Criteria andSaleProdWeightLessThan(Float value) {
            addCriterion("sale_prod_weight <", value, "saleProdWeight");
            return (Criteria) this;
        }

        public Criteria andSaleProdWeightLessThanOrEqualTo(Float value) {
            addCriterion("sale_prod_weight <=", value, "saleProdWeight");
            return (Criteria) this;
        }

        public Criteria andSaleProdWeightIn(List<Float> values) {
            addCriterion("sale_prod_weight in", values, "saleProdWeight");
            return (Criteria) this;
        }

        public Criteria andSaleProdWeightNotIn(List<Float> values) {
            addCriterion("sale_prod_weight not in", values, "saleProdWeight");
            return (Criteria) this;
        }

        public Criteria andSaleProdWeightBetween(Float value1, Float value2) {
            addCriterion("sale_prod_weight between", value1, value2, "saleProdWeight");
            return (Criteria) this;
        }

        public Criteria andSaleProdWeightNotBetween(Float value1, Float value2) {
            addCriterion("sale_prod_weight not between", value1, value2, "saleProdWeight");
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