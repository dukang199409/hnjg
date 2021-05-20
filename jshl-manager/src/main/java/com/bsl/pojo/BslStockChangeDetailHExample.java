package com.bsl.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class BslStockChangeDetailHExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BslStockChangeDetailHExample() {
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

        public Criteria andTransSernoIsNull() {
            addCriterion("trans_serno is null");
            return (Criteria) this;
        }

        public Criteria andTransSernoIsNotNull() {
            addCriterion("trans_serno is not null");
            return (Criteria) this;
        }

        public Criteria andTransSernoEqualTo(String value) {
            addCriterion("trans_serno =", value, "transSerno");
            return (Criteria) this;
        }

        public Criteria andTransSernoNotEqualTo(String value) {
            addCriterion("trans_serno <>", value, "transSerno");
            return (Criteria) this;
        }

        public Criteria andTransSernoGreaterThan(String value) {
            addCriterion("trans_serno >", value, "transSerno");
            return (Criteria) this;
        }

        public Criteria andTransSernoGreaterThanOrEqualTo(String value) {
            addCriterion("trans_serno >=", value, "transSerno");
            return (Criteria) this;
        }

        public Criteria andTransSernoLessThan(String value) {
            addCriterion("trans_serno <", value, "transSerno");
            return (Criteria) this;
        }

        public Criteria andTransSernoLessThanOrEqualTo(String value) {
            addCriterion("trans_serno <=", value, "transSerno");
            return (Criteria) this;
        }

        public Criteria andTransSernoLike(String value) {
            addCriterion("trans_serno like", value, "transSerno");
            return (Criteria) this;
        }

        public Criteria andTransSernoNotLike(String value) {
            addCriterion("trans_serno not like", value, "transSerno");
            return (Criteria) this;
        }

        public Criteria andTransSernoIn(List<String> values) {
            addCriterion("trans_serno in", values, "transSerno");
            return (Criteria) this;
        }

        public Criteria andTransSernoNotIn(List<String> values) {
            addCriterion("trans_serno not in", values, "transSerno");
            return (Criteria) this;
        }

        public Criteria andTransSernoBetween(String value1, String value2) {
            addCriterion("trans_serno between", value1, value2, "transSerno");
            return (Criteria) this;
        }

        public Criteria andTransSernoNotBetween(String value1, String value2) {
            addCriterion("trans_serno not between", value1, value2, "transSerno");
            return (Criteria) this;
        }

        public Criteria andProdIdIsNull() {
            addCriterion("prod_id is null");
            return (Criteria) this;
        }

        public Criteria andProdIdIsNotNull() {
            addCriterion("prod_id is not null");
            return (Criteria) this;
        }

        public Criteria andProdIdEqualTo(String value) {
            addCriterion("prod_id =", value, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdNotEqualTo(String value) {
            addCriterion("prod_id <>", value, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdGreaterThan(String value) {
            addCriterion("prod_id >", value, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdGreaterThanOrEqualTo(String value) {
            addCriterion("prod_id >=", value, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdLessThan(String value) {
            addCriterion("prod_id <", value, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdLessThanOrEqualTo(String value) {
            addCriterion("prod_id <=", value, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdLike(String value) {
            addCriterion("prod_id like", value, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdNotLike(String value) {
            addCriterion("prod_id not like", value, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdIn(List<String> values) {
            addCriterion("prod_id in", values, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdNotIn(List<String> values) {
            addCriterion("prod_id not in", values, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdBetween(String value1, String value2) {
            addCriterion("prod_id between", value1, value2, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdNotBetween(String value1, String value2) {
            addCriterion("prod_id not between", value1, value2, "prodId");
            return (Criteria) this;
        }

        public Criteria andPlanSernoIsNull() {
            addCriterion("plan_serno is null");
            return (Criteria) this;
        }

        public Criteria andPlanSernoIsNotNull() {
            addCriterion("plan_serno is not null");
            return (Criteria) this;
        }

        public Criteria andPlanSernoEqualTo(String value) {
            addCriterion("plan_serno =", value, "planSerno");
            return (Criteria) this;
        }

        public Criteria andPlanSernoNotEqualTo(String value) {
            addCriterion("plan_serno <>", value, "planSerno");
            return (Criteria) this;
        }

        public Criteria andPlanSernoGreaterThan(String value) {
            addCriterion("plan_serno >", value, "planSerno");
            return (Criteria) this;
        }

        public Criteria andPlanSernoGreaterThanOrEqualTo(String value) {
            addCriterion("plan_serno >=", value, "planSerno");
            return (Criteria) this;
        }

        public Criteria andPlanSernoLessThan(String value) {
            addCriterion("plan_serno <", value, "planSerno");
            return (Criteria) this;
        }

        public Criteria andPlanSernoLessThanOrEqualTo(String value) {
            addCriterion("plan_serno <=", value, "planSerno");
            return (Criteria) this;
        }

        public Criteria andPlanSernoLike(String value) {
            addCriterion("plan_serno like", value, "planSerno");
            return (Criteria) this;
        }

        public Criteria andPlanSernoNotLike(String value) {
            addCriterion("plan_serno not like", value, "planSerno");
            return (Criteria) this;
        }

        public Criteria andPlanSernoIn(List<String> values) {
            addCriterion("plan_serno in", values, "planSerno");
            return (Criteria) this;
        }

        public Criteria andPlanSernoNotIn(List<String> values) {
            addCriterion("plan_serno not in", values, "planSerno");
            return (Criteria) this;
        }

        public Criteria andPlanSernoBetween(String value1, String value2) {
            addCriterion("plan_serno between", value1, value2, "planSerno");
            return (Criteria) this;
        }

        public Criteria andPlanSernoNotBetween(String value1, String value2) {
            addCriterion("plan_serno not between", value1, value2, "planSerno");
            return (Criteria) this;
        }

        public Criteria andTransCodeIsNull() {
            addCriterion("trans_code is null");
            return (Criteria) this;
        }

        public Criteria andTransCodeIsNotNull() {
            addCriterion("trans_code is not null");
            return (Criteria) this;
        }

        public Criteria andTransCodeEqualTo(String value) {
            addCriterion("trans_code =", value, "transCode");
            return (Criteria) this;
        }

        public Criteria andTransCodeNotEqualTo(String value) {
            addCriterion("trans_code <>", value, "transCode");
            return (Criteria) this;
        }

        public Criteria andTransCodeGreaterThan(String value) {
            addCriterion("trans_code >", value, "transCode");
            return (Criteria) this;
        }

        public Criteria andTransCodeGreaterThanOrEqualTo(String value) {
            addCriterion("trans_code >=", value, "transCode");
            return (Criteria) this;
        }

        public Criteria andTransCodeLessThan(String value) {
            addCriterion("trans_code <", value, "transCode");
            return (Criteria) this;
        }

        public Criteria andTransCodeLessThanOrEqualTo(String value) {
            addCriterion("trans_code <=", value, "transCode");
            return (Criteria) this;
        }

        public Criteria andTransCodeLike(String value) {
            addCriterion("trans_code like", value, "transCode");
            return (Criteria) this;
        }

        public Criteria andTransCodeNotLike(String value) {
            addCriterion("trans_code not like", value, "transCode");
            return (Criteria) this;
        }

        public Criteria andTransCodeIn(List<String> values) {
            addCriterion("trans_code in", values, "transCode");
            return (Criteria) this;
        }

        public Criteria andTransCodeNotIn(List<String> values) {
            addCriterion("trans_code not in", values, "transCode");
            return (Criteria) this;
        }

        public Criteria andTransCodeBetween(String value1, String value2) {
            addCriterion("trans_code between", value1, value2, "transCode");
            return (Criteria) this;
        }

        public Criteria andTransCodeNotBetween(String value1, String value2) {
            addCriterion("trans_code not between", value1, value2, "transCode");
            return (Criteria) this;
        }

        public Criteria andProdTypeIsNull() {
            addCriterion("prod_type is null");
            return (Criteria) this;
        }

        public Criteria andProdTypeIsNotNull() {
            addCriterion("prod_type is not null");
            return (Criteria) this;
        }

        public Criteria andProdTypeEqualTo(String value) {
            addCriterion("prod_type =", value, "prodType");
            return (Criteria) this;
        }

        public Criteria andProdTypeNotEqualTo(String value) {
            addCriterion("prod_type <>", value, "prodType");
            return (Criteria) this;
        }

        public Criteria andProdTypeGreaterThan(String value) {
            addCriterion("prod_type >", value, "prodType");
            return (Criteria) this;
        }

        public Criteria andProdTypeGreaterThanOrEqualTo(String value) {
            addCriterion("prod_type >=", value, "prodType");
            return (Criteria) this;
        }

        public Criteria andProdTypeLessThan(String value) {
            addCriterion("prod_type <", value, "prodType");
            return (Criteria) this;
        }

        public Criteria andProdTypeLessThanOrEqualTo(String value) {
            addCriterion("prod_type <=", value, "prodType");
            return (Criteria) this;
        }

        public Criteria andProdTypeLike(String value) {
            addCriterion("prod_type like", value, "prodType");
            return (Criteria) this;
        }

        public Criteria andProdTypeNotLike(String value) {
            addCriterion("prod_type not like", value, "prodType");
            return (Criteria) this;
        }

        public Criteria andProdTypeIn(List<String> values) {
            addCriterion("prod_type in", values, "prodType");
            return (Criteria) this;
        }

        public Criteria andProdTypeNotIn(List<String> values) {
            addCriterion("prod_type not in", values, "prodType");
            return (Criteria) this;
        }

        public Criteria andProdTypeBetween(String value1, String value2) {
            addCriterion("prod_type between", value1, value2, "prodType");
            return (Criteria) this;
        }

        public Criteria andProdTypeNotBetween(String value1, String value2) {
            addCriterion("prod_type not between", value1, value2, "prodType");
            return (Criteria) this;
        }

        public Criteria andRubbishTypeIsNull() {
            addCriterion("rubbish_type is null");
            return (Criteria) this;
        }

        public Criteria andRubbishTypeIsNotNull() {
            addCriterion("rubbish_type is not null");
            return (Criteria) this;
        }

        public Criteria andRubbishTypeEqualTo(String value) {
            addCriterion("rubbish_type =", value, "rubbishType");
            return (Criteria) this;
        }

        public Criteria andRubbishTypeNotEqualTo(String value) {
            addCriterion("rubbish_type <>", value, "rubbishType");
            return (Criteria) this;
        }

        public Criteria andRubbishTypeGreaterThan(String value) {
            addCriterion("rubbish_type >", value, "rubbishType");
            return (Criteria) this;
        }

        public Criteria andRubbishTypeGreaterThanOrEqualTo(String value) {
            addCriterion("rubbish_type >=", value, "rubbishType");
            return (Criteria) this;
        }

        public Criteria andRubbishTypeLessThan(String value) {
            addCriterion("rubbish_type <", value, "rubbishType");
            return (Criteria) this;
        }

        public Criteria andRubbishTypeLessThanOrEqualTo(String value) {
            addCriterion("rubbish_type <=", value, "rubbishType");
            return (Criteria) this;
        }

        public Criteria andRubbishTypeLike(String value) {
            addCriterion("rubbish_type like", value, "rubbishType");
            return (Criteria) this;
        }

        public Criteria andRubbishTypeNotLike(String value) {
            addCriterion("rubbish_type not like", value, "rubbishType");
            return (Criteria) this;
        }

        public Criteria andRubbishTypeIn(List<String> values) {
            addCriterion("rubbish_type in", values, "rubbishType");
            return (Criteria) this;
        }

        public Criteria andRubbishTypeNotIn(List<String> values) {
            addCriterion("rubbish_type not in", values, "rubbishType");
            return (Criteria) this;
        }

        public Criteria andRubbishTypeBetween(String value1, String value2) {
            addCriterion("rubbish_type between", value1, value2, "rubbishType");
            return (Criteria) this;
        }

        public Criteria andRubbishTypeNotBetween(String value1, String value2) {
            addCriterion("rubbish_type not between", value1, value2, "rubbishType");
            return (Criteria) this;
        }

        public Criteria andProdOriIdIsNull() {
            addCriterion("prod_ori_id is null");
            return (Criteria) this;
        }

        public Criteria andProdOriIdIsNotNull() {
            addCriterion("prod_ori_id is not null");
            return (Criteria) this;
        }

        public Criteria andProdOriIdEqualTo(String value) {
            addCriterion("prod_ori_id =", value, "prodOriId");
            return (Criteria) this;
        }

        public Criteria andProdOriIdNotEqualTo(String value) {
            addCriterion("prod_ori_id <>", value, "prodOriId");
            return (Criteria) this;
        }

        public Criteria andProdOriIdGreaterThan(String value) {
            addCriterion("prod_ori_id >", value, "prodOriId");
            return (Criteria) this;
        }

        public Criteria andProdOriIdGreaterThanOrEqualTo(String value) {
            addCriterion("prod_ori_id >=", value, "prodOriId");
            return (Criteria) this;
        }

        public Criteria andProdOriIdLessThan(String value) {
            addCriterion("prod_ori_id <", value, "prodOriId");
            return (Criteria) this;
        }

        public Criteria andProdOriIdLessThanOrEqualTo(String value) {
            addCriterion("prod_ori_id <=", value, "prodOriId");
            return (Criteria) this;
        }

        public Criteria andProdOriIdLike(String value) {
            addCriterion("prod_ori_id like", value, "prodOriId");
            return (Criteria) this;
        }

        public Criteria andProdOriIdNotLike(String value) {
            addCriterion("prod_ori_id not like", value, "prodOriId");
            return (Criteria) this;
        }

        public Criteria andProdOriIdIn(List<String> values) {
            addCriterion("prod_ori_id in", values, "prodOriId");
            return (Criteria) this;
        }

        public Criteria andProdOriIdNotIn(List<String> values) {
            addCriterion("prod_ori_id not in", values, "prodOriId");
            return (Criteria) this;
        }

        public Criteria andProdOriIdBetween(String value1, String value2) {
            addCriterion("prod_ori_id between", value1, value2, "prodOriId");
            return (Criteria) this;
        }

        public Criteria andProdOriIdNotBetween(String value1, String value2) {
            addCriterion("prod_ori_id not between", value1, value2, "prodOriId");
            return (Criteria) this;
        }

        public Criteria andRubbishWeightIsNull() {
            addCriterion("rubbish_weight is null");
            return (Criteria) this;
        }

        public Criteria andRubbishWeightIsNotNull() {
            addCriterion("rubbish_weight is not null");
            return (Criteria) this;
        }

        public Criteria andRubbishWeightEqualTo(Float value) {
            addCriterion("rubbish_weight =", value, "rubbishWeight");
            return (Criteria) this;
        }

        public Criteria andRubbishWeightNotEqualTo(Float value) {
            addCriterion("rubbish_weight <>", value, "rubbishWeight");
            return (Criteria) this;
        }

        public Criteria andRubbishWeightGreaterThan(Float value) {
            addCriterion("rubbish_weight >", value, "rubbishWeight");
            return (Criteria) this;
        }

        public Criteria andRubbishWeightGreaterThanOrEqualTo(Float value) {
            addCriterion("rubbish_weight >=", value, "rubbishWeight");
            return (Criteria) this;
        }

        public Criteria andRubbishWeightLessThan(Float value) {
            addCriterion("rubbish_weight <", value, "rubbishWeight");
            return (Criteria) this;
        }

        public Criteria andRubbishWeightLessThanOrEqualTo(Float value) {
            addCriterion("rubbish_weight <=", value, "rubbishWeight");
            return (Criteria) this;
        }

        public Criteria andRubbishWeightIn(List<Float> values) {
            addCriterion("rubbish_weight in", values, "rubbishWeight");
            return (Criteria) this;
        }

        public Criteria andRubbishWeightNotIn(List<Float> values) {
            addCriterion("rubbish_weight not in", values, "rubbishWeight");
            return (Criteria) this;
        }

        public Criteria andRubbishWeightBetween(Float value1, Float value2) {
            addCriterion("rubbish_weight between", value1, value2, "rubbishWeight");
            return (Criteria) this;
        }

        public Criteria andRubbishWeightNotBetween(Float value1, Float value2) {
            addCriterion("rubbish_weight not between", value1, value2, "rubbishWeight");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(Float value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(Float value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(Float value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(Float value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(Float value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(Float value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<Float> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<Float> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(Float value1, Float value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(Float value1, Float value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andTargetPlaceIsNull() {
            addCriterion("target_place is null");
            return (Criteria) this;
        }

        public Criteria andTargetPlaceIsNotNull() {
            addCriterion("target_place is not null");
            return (Criteria) this;
        }

        public Criteria andTargetPlaceEqualTo(String value) {
            addCriterion("target_place =", value, "targetPlace");
            return (Criteria) this;
        }

        public Criteria andTargetPlaceNotEqualTo(String value) {
            addCriterion("target_place <>", value, "targetPlace");
            return (Criteria) this;
        }

        public Criteria andTargetPlaceGreaterThan(String value) {
            addCriterion("target_place >", value, "targetPlace");
            return (Criteria) this;
        }

        public Criteria andTargetPlaceGreaterThanOrEqualTo(String value) {
            addCriterion("target_place >=", value, "targetPlace");
            return (Criteria) this;
        }

        public Criteria andTargetPlaceLessThan(String value) {
            addCriterion("target_place <", value, "targetPlace");
            return (Criteria) this;
        }

        public Criteria andTargetPlaceLessThanOrEqualTo(String value) {
            addCriterion("target_place <=", value, "targetPlace");
            return (Criteria) this;
        }

        public Criteria andTargetPlaceLike(String value) {
            addCriterion("target_place like", value, "targetPlace");
            return (Criteria) this;
        }

        public Criteria andTargetPlaceNotLike(String value) {
            addCriterion("target_place not like", value, "targetPlace");
            return (Criteria) this;
        }

        public Criteria andTargetPlaceIn(List<String> values) {
            addCriterion("target_place in", values, "targetPlace");
            return (Criteria) this;
        }

        public Criteria andTargetPlaceNotIn(List<String> values) {
            addCriterion("target_place not in", values, "targetPlace");
            return (Criteria) this;
        }

        public Criteria andTargetPlaceBetween(String value1, String value2) {
            addCriterion("target_place between", value1, value2, "targetPlace");
            return (Criteria) this;
        }

        public Criteria andTargetPlaceNotBetween(String value1, String value2) {
            addCriterion("target_place not between", value1, value2, "targetPlace");
            return (Criteria) this;
        }

        public Criteria andInputuserIsNull() {
            addCriterion("inputuser is null");
            return (Criteria) this;
        }

        public Criteria andInputuserIsNotNull() {
            addCriterion("inputuser is not null");
            return (Criteria) this;
        }

        public Criteria andInputuserEqualTo(String value) {
            addCriterion("inputuser =", value, "inputuser");
            return (Criteria) this;
        }

        public Criteria andInputuserNotEqualTo(String value) {
            addCriterion("inputuser <>", value, "inputuser");
            return (Criteria) this;
        }

        public Criteria andInputuserGreaterThan(String value) {
            addCriterion("inputuser >", value, "inputuser");
            return (Criteria) this;
        }

        public Criteria andInputuserGreaterThanOrEqualTo(String value) {
            addCriterion("inputuser >=", value, "inputuser");
            return (Criteria) this;
        }

        public Criteria andInputuserLessThan(String value) {
            addCriterion("inputuser <", value, "inputuser");
            return (Criteria) this;
        }

        public Criteria andInputuserLessThanOrEqualTo(String value) {
            addCriterion("inputuser <=", value, "inputuser");
            return (Criteria) this;
        }

        public Criteria andInputuserLike(String value) {
            addCriterion("inputuser like", value, "inputuser");
            return (Criteria) this;
        }

        public Criteria andInputuserNotLike(String value) {
            addCriterion("inputuser not like", value, "inputuser");
            return (Criteria) this;
        }

        public Criteria andInputuserIn(List<String> values) {
            addCriterion("inputuser in", values, "inputuser");
            return (Criteria) this;
        }

        public Criteria andInputuserNotIn(List<String> values) {
            addCriterion("inputuser not in", values, "inputuser");
            return (Criteria) this;
        }

        public Criteria andInputuserBetween(String value1, String value2) {
            addCriterion("inputuser between", value1, value2, "inputuser");
            return (Criteria) this;
        }

        public Criteria andInputuserNotBetween(String value1, String value2) {
            addCriterion("inputuser not between", value1, value2, "inputuser");
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