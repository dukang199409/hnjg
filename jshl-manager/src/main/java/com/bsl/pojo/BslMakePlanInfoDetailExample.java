package com.bsl.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class BslMakePlanInfoDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BslMakePlanInfoDetailExample() {
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

        public Criteria andPlanInfoDetailIdIsNull() {
            addCriterion("plan_info_detail_id is null");
            return (Criteria) this;
        }

        public Criteria andPlanInfoDetailIdIsNotNull() {
            addCriterion("plan_info_detail_id is not null");
            return (Criteria) this;
        }

        public Criteria andPlanInfoDetailIdEqualTo(String value) {
            addCriterion("plan_info_detail_id =", value, "planInfoDetailId");
            return (Criteria) this;
        }

        public Criteria andPlanInfoDetailIdNotEqualTo(String value) {
            addCriterion("plan_info_detail_id <>", value, "planInfoDetailId");
            return (Criteria) this;
        }

        public Criteria andPlanInfoDetailIdGreaterThan(String value) {
            addCriterion("plan_info_detail_id >", value, "planInfoDetailId");
            return (Criteria) this;
        }

        public Criteria andPlanInfoDetailIdGreaterThanOrEqualTo(String value) {
            addCriterion("plan_info_detail_id >=", value, "planInfoDetailId");
            return (Criteria) this;
        }

        public Criteria andPlanInfoDetailIdLessThan(String value) {
            addCriterion("plan_info_detail_id <", value, "planInfoDetailId");
            return (Criteria) this;
        }

        public Criteria andPlanInfoDetailIdLessThanOrEqualTo(String value) {
            addCriterion("plan_info_detail_id <=", value, "planInfoDetailId");
            return (Criteria) this;
        }

        public Criteria andPlanInfoDetailIdLike(String value) {
            addCriterion("plan_info_detail_id like", value, "planInfoDetailId");
            return (Criteria) this;
        }

        public Criteria andPlanInfoDetailIdNotLike(String value) {
            addCriterion("plan_info_detail_id not like", value, "planInfoDetailId");
            return (Criteria) this;
        }

        public Criteria andPlanInfoDetailIdIn(List<String> values) {
            addCriterion("plan_info_detail_id in", values, "planInfoDetailId");
            return (Criteria) this;
        }

        public Criteria andPlanInfoDetailIdNotIn(List<String> values) {
            addCriterion("plan_info_detail_id not in", values, "planInfoDetailId");
            return (Criteria) this;
        }

        public Criteria andPlanInfoDetailIdBetween(String value1, String value2) {
            addCriterion("plan_info_detail_id between", value1, value2, "planInfoDetailId");
            return (Criteria) this;
        }

        public Criteria andPlanInfoDetailIdNotBetween(String value1, String value2) {
            addCriterion("plan_info_detail_id not between", value1, value2, "planInfoDetailId");
            return (Criteria) this;
        }

        public Criteria andPlanIdIsNull() {
            addCriterion("plan_id is null");
            return (Criteria) this;
        }

        public Criteria andPlanIdIsNotNull() {
            addCriterion("plan_id is not null");
            return (Criteria) this;
        }

        public Criteria andPlanIdEqualTo(String value) {
            addCriterion("plan_id =", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdNotEqualTo(String value) {
            addCriterion("plan_id <>", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdGreaterThan(String value) {
            addCriterion("plan_id >", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdGreaterThanOrEqualTo(String value) {
            addCriterion("plan_id >=", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdLessThan(String value) {
            addCriterion("plan_id <", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdLessThanOrEqualTo(String value) {
            addCriterion("plan_id <=", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdLike(String value) {
            addCriterion("plan_id like", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdNotLike(String value) {
            addCriterion("plan_id not like", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdIn(List<String> values) {
            addCriterion("plan_id in", values, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdNotIn(List<String> values) {
            addCriterion("plan_id not in", values, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdBetween(String value1, String value2) {
            addCriterion("plan_id between", value1, value2, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdNotBetween(String value1, String value2) {
            addCriterion("plan_id not between", value1, value2, "planId");
            return (Criteria) this;
        }

        public Criteria andProdNormIsNull() {
            addCriterion("prod_norm is null");
            return (Criteria) this;
        }

        public Criteria andProdNormIsNotNull() {
            addCriterion("prod_norm is not null");
            return (Criteria) this;
        }

        public Criteria andProdNormEqualTo(String value) {
            addCriterion("prod_norm =", value, "prodNorm");
            return (Criteria) this;
        }

        public Criteria andProdNormNotEqualTo(String value) {
            addCriterion("prod_norm <>", value, "prodNorm");
            return (Criteria) this;
        }

        public Criteria andProdNormGreaterThan(String value) {
            addCriterion("prod_norm >", value, "prodNorm");
            return (Criteria) this;
        }

        public Criteria andProdNormGreaterThanOrEqualTo(String value) {
            addCriterion("prod_norm >=", value, "prodNorm");
            return (Criteria) this;
        }

        public Criteria andProdNormLessThan(String value) {
            addCriterion("prod_norm <", value, "prodNorm");
            return (Criteria) this;
        }

        public Criteria andProdNormLessThanOrEqualTo(String value) {
            addCriterion("prod_norm <=", value, "prodNorm");
            return (Criteria) this;
        }

        public Criteria andProdNormLike(String value) {
            addCriterion("prod_norm like", value, "prodNorm");
            return (Criteria) this;
        }

        public Criteria andProdNormNotLike(String value) {
            addCriterion("prod_norm not like", value, "prodNorm");
            return (Criteria) this;
        }

        public Criteria andProdNormIn(List<String> values) {
            addCriterion("prod_norm in", values, "prodNorm");
            return (Criteria) this;
        }

        public Criteria andProdNormNotIn(List<String> values) {
            addCriterion("prod_norm not in", values, "prodNorm");
            return (Criteria) this;
        }

        public Criteria andProdNormBetween(String value1, String value2) {
            addCriterion("prod_norm between", value1, value2, "prodNorm");
            return (Criteria) this;
        }

        public Criteria andProdNormNotBetween(String value1, String value2) {
            addCriterion("prod_norm not between", value1, value2, "prodNorm");
            return (Criteria) this;
        }

        public Criteria andProdLevelIsNull() {
            addCriterion("prod_level is null");
            return (Criteria) this;
        }

        public Criteria andProdLevelIsNotNull() {
            addCriterion("prod_level is not null");
            return (Criteria) this;
        }

        public Criteria andProdLevelEqualTo(String value) {
            addCriterion("prod_level =", value, "prodLevel");
            return (Criteria) this;
        }

        public Criteria andProdLevelNotEqualTo(String value) {
            addCriterion("prod_level <>", value, "prodLevel");
            return (Criteria) this;
        }

        public Criteria andProdLevelGreaterThan(String value) {
            addCriterion("prod_level >", value, "prodLevel");
            return (Criteria) this;
        }

        public Criteria andProdLevelGreaterThanOrEqualTo(String value) {
            addCriterion("prod_level >=", value, "prodLevel");
            return (Criteria) this;
        }

        public Criteria andProdLevelLessThan(String value) {
            addCriterion("prod_level <", value, "prodLevel");
            return (Criteria) this;
        }

        public Criteria andProdLevelLessThanOrEqualTo(String value) {
            addCriterion("prod_level <=", value, "prodLevel");
            return (Criteria) this;
        }

        public Criteria andProdLevelLike(String value) {
            addCriterion("prod_level like", value, "prodLevel");
            return (Criteria) this;
        }

        public Criteria andProdLevelNotLike(String value) {
            addCriterion("prod_level not like", value, "prodLevel");
            return (Criteria) this;
        }

        public Criteria andProdLevelIn(List<String> values) {
            addCriterion("prod_level in", values, "prodLevel");
            return (Criteria) this;
        }

        public Criteria andProdLevelNotIn(List<String> values) {
            addCriterion("prod_level not in", values, "prodLevel");
            return (Criteria) this;
        }

        public Criteria andProdLevelBetween(String value1, String value2) {
            addCriterion("prod_level between", value1, value2, "prodLevel");
            return (Criteria) this;
        }

        public Criteria andProdLevelNotBetween(String value1, String value2) {
            addCriterion("prod_level not between", value1, value2, "prodLevel");
            return (Criteria) this;
        }

        public Criteria andProdLengthIsNull() {
            addCriterion("prod_length is null");
            return (Criteria) this;
        }

        public Criteria andProdLengthIsNotNull() {
            addCriterion("prod_length is not null");
            return (Criteria) this;
        }

        public Criteria andProdLengthEqualTo(Float value) {
            addCriterion("prod_length =", value, "prodLength");
            return (Criteria) this;
        }

        public Criteria andProdLengthNotEqualTo(Float value) {
            addCriterion("prod_length <>", value, "prodLength");
            return (Criteria) this;
        }

        public Criteria andProdLengthGreaterThan(Float value) {
            addCriterion("prod_length >", value, "prodLength");
            return (Criteria) this;
        }

        public Criteria andProdLengthGreaterThanOrEqualTo(Float value) {
            addCriterion("prod_length >=", value, "prodLength");
            return (Criteria) this;
        }

        public Criteria andProdLengthLessThan(Float value) {
            addCriterion("prod_length <", value, "prodLength");
            return (Criteria) this;
        }

        public Criteria andProdLengthLessThanOrEqualTo(Float value) {
            addCriterion("prod_length <=", value, "prodLength");
            return (Criteria) this;
        }

        public Criteria andProdLengthIn(List<Float> values) {
            addCriterion("prod_length in", values, "prodLength");
            return (Criteria) this;
        }

        public Criteria andProdLengthNotIn(List<Float> values) {
            addCriterion("prod_length not in", values, "prodLength");
            return (Criteria) this;
        }

        public Criteria andProdLengthBetween(Float value1, Float value2) {
            addCriterion("prod_length between", value1, value2, "prodLength");
            return (Criteria) this;
        }

        public Criteria andProdLengthNotBetween(Float value1, Float value2) {
            addCriterion("prod_length not between", value1, value2, "prodLength");
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

        public Criteria andPlanOutputVolumeIsNull() {
            addCriterion("plan_output_volume is null");
            return (Criteria) this;
        }

        public Criteria andPlanOutputVolumeIsNotNull() {
            addCriterion("plan_output_volume is not null");
            return (Criteria) this;
        }

        public Criteria andPlanOutputVolumeEqualTo(String value) {
            addCriterion("plan_output_volume =", value, "planOutputVolume");
            return (Criteria) this;
        }

        public Criteria andPlanOutputVolumeNotEqualTo(String value) {
            addCriterion("plan_output_volume <>", value, "planOutputVolume");
            return (Criteria) this;
        }

        public Criteria andPlanOutputVolumeGreaterThan(String value) {
            addCriterion("plan_output_volume >", value, "planOutputVolume");
            return (Criteria) this;
        }

        public Criteria andPlanOutputVolumeGreaterThanOrEqualTo(String value) {
            addCriterion("plan_output_volume >=", value, "planOutputVolume");
            return (Criteria) this;
        }

        public Criteria andPlanOutputVolumeLessThan(String value) {
            addCriterion("plan_output_volume <", value, "planOutputVolume");
            return (Criteria) this;
        }

        public Criteria andPlanOutputVolumeLessThanOrEqualTo(String value) {
            addCriterion("plan_output_volume <=", value, "planOutputVolume");
            return (Criteria) this;
        }

        public Criteria andPlanOutputVolumeLike(String value) {
            addCriterion("plan_output_volume like", value, "planOutputVolume");
            return (Criteria) this;
        }

        public Criteria andPlanOutputVolumeNotLike(String value) {
            addCriterion("plan_output_volume not like", value, "planOutputVolume");
            return (Criteria) this;
        }

        public Criteria andPlanOutputVolumeIn(List<String> values) {
            addCriterion("plan_output_volume in", values, "planOutputVolume");
            return (Criteria) this;
        }

        public Criteria andPlanOutputVolumeNotIn(List<String> values) {
            addCriterion("plan_output_volume not in", values, "planOutputVolume");
            return (Criteria) this;
        }

        public Criteria andPlanOutputVolumeBetween(String value1, String value2) {
            addCriterion("plan_output_volume between", value1, value2, "planOutputVolume");
            return (Criteria) this;
        }

        public Criteria andPlanOutputVolumeNotBetween(String value1, String value2) {
            addCriterion("plan_output_volume not between", value1, value2, "planOutputVolume");
            return (Criteria) this;
        }

        public Criteria andPlanFinistDateIsNull() {
            addCriterion("plan_finist_date is null");
            return (Criteria) this;
        }

        public Criteria andPlanFinistDateIsNotNull() {
            addCriterion("plan_finist_date is not null");
            return (Criteria) this;
        }

        public Criteria andPlanFinistDateEqualTo(Date value) {
            addCriterionForJDBCDate("plan_finist_date =", value, "planFinistDate");
            return (Criteria) this;
        }

        public Criteria andPlanFinistDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("plan_finist_date <>", value, "planFinistDate");
            return (Criteria) this;
        }

        public Criteria andPlanFinistDateGreaterThan(Date value) {
            addCriterionForJDBCDate("plan_finist_date >", value, "planFinistDate");
            return (Criteria) this;
        }

        public Criteria andPlanFinistDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("plan_finist_date >=", value, "planFinistDate");
            return (Criteria) this;
        }

        public Criteria andPlanFinistDateLessThan(Date value) {
            addCriterionForJDBCDate("plan_finist_date <", value, "planFinistDate");
            return (Criteria) this;
        }

        public Criteria andPlanFinistDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("plan_finist_date <=", value, "planFinistDate");
            return (Criteria) this;
        }

        public Criteria andPlanFinistDateIn(List<Date> values) {
            addCriterionForJDBCDate("plan_finist_date in", values, "planFinistDate");
            return (Criteria) this;
        }

        public Criteria andPlanFinistDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("plan_finist_date not in", values, "planFinistDate");
            return (Criteria) this;
        }

        public Criteria andPlanFinistDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("plan_finist_date between", value1, value2, "planFinistDate");
            return (Criteria) this;
        }

        public Criteria andPlanFinistDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("plan_finist_date not between", value1, value2, "planFinistDate");
            return (Criteria) this;
        }

        public Criteria andCollectedUnitsIsNull() {
            addCriterion("collected_units is null");
            return (Criteria) this;
        }

        public Criteria andCollectedUnitsIsNotNull() {
            addCriterion("collected_units is not null");
            return (Criteria) this;
        }

        public Criteria andCollectedUnitsEqualTo(String value) {
            addCriterion("collected_units =", value, "collectedUnits");
            return (Criteria) this;
        }

        public Criteria andCollectedUnitsNotEqualTo(String value) {
            addCriterion("collected_units <>", value, "collectedUnits");
            return (Criteria) this;
        }

        public Criteria andCollectedUnitsGreaterThan(String value) {
            addCriterion("collected_units >", value, "collectedUnits");
            return (Criteria) this;
        }

        public Criteria andCollectedUnitsGreaterThanOrEqualTo(String value) {
            addCriterion("collected_units >=", value, "collectedUnits");
            return (Criteria) this;
        }

        public Criteria andCollectedUnitsLessThan(String value) {
            addCriterion("collected_units <", value, "collectedUnits");
            return (Criteria) this;
        }

        public Criteria andCollectedUnitsLessThanOrEqualTo(String value) {
            addCriterion("collected_units <=", value, "collectedUnits");
            return (Criteria) this;
        }

        public Criteria andCollectedUnitsLike(String value) {
            addCriterion("collected_units like", value, "collectedUnits");
            return (Criteria) this;
        }

        public Criteria andCollectedUnitsNotLike(String value) {
            addCriterion("collected_units not like", value, "collectedUnits");
            return (Criteria) this;
        }

        public Criteria andCollectedUnitsIn(List<String> values) {
            addCriterion("collected_units in", values, "collectedUnits");
            return (Criteria) this;
        }

        public Criteria andCollectedUnitsNotIn(List<String> values) {
            addCriterion("collected_units not in", values, "collectedUnits");
            return (Criteria) this;
        }

        public Criteria andCollectedUnitsBetween(String value1, String value2) {
            addCriterion("collected_units between", value1, value2, "collectedUnits");
            return (Criteria) this;
        }

        public Criteria andCollectedUnitsNotBetween(String value1, String value2) {
            addCriterion("collected_units not between", value1, value2, "collectedUnits");
            return (Criteria) this;
        }

        public Criteria andPlanDyzIsNull() {
            addCriterion("plan_dyz is null");
            return (Criteria) this;
        }

        public Criteria andPlanDyzIsNotNull() {
            addCriterion("plan_dyz is not null");
            return (Criteria) this;
        }

        public Criteria andPlanDyzEqualTo(String value) {
            addCriterion("plan_dyz =", value, "planDyz");
            return (Criteria) this;
        }

        public Criteria andPlanDyzNotEqualTo(String value) {
            addCriterion("plan_dyz <>", value, "planDyz");
            return (Criteria) this;
        }

        public Criteria andPlanDyzGreaterThan(String value) {
            addCriterion("plan_dyz >", value, "planDyz");
            return (Criteria) this;
        }

        public Criteria andPlanDyzGreaterThanOrEqualTo(String value) {
            addCriterion("plan_dyz >=", value, "planDyz");
            return (Criteria) this;
        }

        public Criteria andPlanDyzLessThan(String value) {
            addCriterion("plan_dyz <", value, "planDyz");
            return (Criteria) this;
        }

        public Criteria andPlanDyzLessThanOrEqualTo(String value) {
            addCriterion("plan_dyz <=", value, "planDyz");
            return (Criteria) this;
        }

        public Criteria andPlanDyzLike(String value) {
            addCriterion("plan_dyz like", value, "planDyz");
            return (Criteria) this;
        }

        public Criteria andPlanDyzNotLike(String value) {
            addCriterion("plan_dyz not like", value, "planDyz");
            return (Criteria) this;
        }

        public Criteria andPlanDyzIn(List<String> values) {
            addCriterion("plan_dyz in", values, "planDyz");
            return (Criteria) this;
        }

        public Criteria andPlanDyzNotIn(List<String> values) {
            addCriterion("plan_dyz not in", values, "planDyz");
            return (Criteria) this;
        }

        public Criteria andPlanDyzBetween(String value1, String value2) {
            addCriterion("plan_dyz between", value1, value2, "planDyz");
            return (Criteria) this;
        }

        public Criteria andPlanDyzNotBetween(String value1, String value2) {
            addCriterion("plan_dyz not between", value1, value2, "planDyz");
            return (Criteria) this;
        }

        public Criteria andProdInputuserIsNull() {
            addCriterion("prod_inputuser is null");
            return (Criteria) this;
        }

        public Criteria andProdInputuserIsNotNull() {
            addCriterion("prod_inputuser is not null");
            return (Criteria) this;
        }

        public Criteria andProdInputuserEqualTo(String value) {
            addCriterion("prod_inputuser =", value, "prodInputuser");
            return (Criteria) this;
        }

        public Criteria andProdInputuserNotEqualTo(String value) {
            addCriterion("prod_inputuser <>", value, "prodInputuser");
            return (Criteria) this;
        }

        public Criteria andProdInputuserGreaterThan(String value) {
            addCriterion("prod_inputuser >", value, "prodInputuser");
            return (Criteria) this;
        }

        public Criteria andProdInputuserGreaterThanOrEqualTo(String value) {
            addCriterion("prod_inputuser >=", value, "prodInputuser");
            return (Criteria) this;
        }

        public Criteria andProdInputuserLessThan(String value) {
            addCriterion("prod_inputuser <", value, "prodInputuser");
            return (Criteria) this;
        }

        public Criteria andProdInputuserLessThanOrEqualTo(String value) {
            addCriterion("prod_inputuser <=", value, "prodInputuser");
            return (Criteria) this;
        }

        public Criteria andProdInputuserLike(String value) {
            addCriterion("prod_inputuser like", value, "prodInputuser");
            return (Criteria) this;
        }

        public Criteria andProdInputuserNotLike(String value) {
            addCriterion("prod_inputuser not like", value, "prodInputuser");
            return (Criteria) this;
        }

        public Criteria andProdInputuserIn(List<String> values) {
            addCriterion("prod_inputuser in", values, "prodInputuser");
            return (Criteria) this;
        }

        public Criteria andProdInputuserNotIn(List<String> values) {
            addCriterion("prod_inputuser not in", values, "prodInputuser");
            return (Criteria) this;
        }

        public Criteria andProdInputuserBetween(String value1, String value2) {
            addCriterion("prod_inputuser between", value1, value2, "prodInputuser");
            return (Criteria) this;
        }

        public Criteria andProdInputuserNotBetween(String value1, String value2) {
            addCriterion("prod_inputuser not between", value1, value2, "prodInputuser");
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