package com.bsl.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class BslSaleInfoDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BslSaleInfoDetailExample() {
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

        public Criteria andSaleSernoIsNull() {
            addCriterion("sale_serno is null");
            return (Criteria) this;
        }

        public Criteria andSaleSernoIsNotNull() {
            addCriterion("sale_serno is not null");
            return (Criteria) this;
        }

        public Criteria andSaleSernoEqualTo(String value) {
            addCriterion("sale_serno =", value, "saleSerno");
            return (Criteria) this;
        }

        public Criteria andSaleSernoNotEqualTo(String value) {
            addCriterion("sale_serno <>", value, "saleSerno");
            return (Criteria) this;
        }

        public Criteria andSaleSernoGreaterThan(String value) {
            addCriterion("sale_serno >", value, "saleSerno");
            return (Criteria) this;
        }

        public Criteria andSaleSernoGreaterThanOrEqualTo(String value) {
            addCriterion("sale_serno >=", value, "saleSerno");
            return (Criteria) this;
        }

        public Criteria andSaleSernoLessThan(String value) {
            addCriterion("sale_serno <", value, "saleSerno");
            return (Criteria) this;
        }

        public Criteria andSaleSernoLessThanOrEqualTo(String value) {
            addCriterion("sale_serno <=", value, "saleSerno");
            return (Criteria) this;
        }

        public Criteria andSaleSernoLike(String value) {
            addCriterion("sale_serno like", value, "saleSerno");
            return (Criteria) this;
        }

        public Criteria andSaleSernoNotLike(String value) {
            addCriterion("sale_serno not like", value, "saleSerno");
            return (Criteria) this;
        }

        public Criteria andSaleSernoIn(List<String> values) {
            addCriterion("sale_serno in", values, "saleSerno");
            return (Criteria) this;
        }

        public Criteria andSaleSernoNotIn(List<String> values) {
            addCriterion("sale_serno not in", values, "saleSerno");
            return (Criteria) this;
        }

        public Criteria andSaleSernoBetween(String value1, String value2) {
            addCriterion("sale_serno between", value1, value2, "saleSerno");
            return (Criteria) this;
        }

        public Criteria andSaleSernoNotBetween(String value1, String value2) {
            addCriterion("sale_serno not between", value1, value2, "saleSerno");
            return (Criteria) this;
        }

        public Criteria andSalePlanIdIsNull() {
            addCriterion("sale_plan_id is null");
            return (Criteria) this;
        }

        public Criteria andSalePlanIdIsNotNull() {
            addCriterion("sale_plan_id is not null");
            return (Criteria) this;
        }

        public Criteria andSalePlanIdEqualTo(String value) {
            addCriterion("sale_plan_id =", value, "salePlanId");
            return (Criteria) this;
        }

        public Criteria andSalePlanIdNotEqualTo(String value) {
            addCriterion("sale_plan_id <>", value, "salePlanId");
            return (Criteria) this;
        }

        public Criteria andSalePlanIdGreaterThan(String value) {
            addCriterion("sale_plan_id >", value, "salePlanId");
            return (Criteria) this;
        }

        public Criteria andSalePlanIdGreaterThanOrEqualTo(String value) {
            addCriterion("sale_plan_id >=", value, "salePlanId");
            return (Criteria) this;
        }

        public Criteria andSalePlanIdLessThan(String value) {
            addCriterion("sale_plan_id <", value, "salePlanId");
            return (Criteria) this;
        }

        public Criteria andSalePlanIdLessThanOrEqualTo(String value) {
            addCriterion("sale_plan_id <=", value, "salePlanId");
            return (Criteria) this;
        }

        public Criteria andSalePlanIdLike(String value) {
            addCriterion("sale_plan_id like", value, "salePlanId");
            return (Criteria) this;
        }

        public Criteria andSalePlanIdNotLike(String value) {
            addCriterion("sale_plan_id not like", value, "salePlanId");
            return (Criteria) this;
        }

        public Criteria andSalePlanIdIn(List<String> values) {
            addCriterion("sale_plan_id in", values, "salePlanId");
            return (Criteria) this;
        }

        public Criteria andSalePlanIdNotIn(List<String> values) {
            addCriterion("sale_plan_id not in", values, "salePlanId");
            return (Criteria) this;
        }

        public Criteria andSalePlanIdBetween(String value1, String value2) {
            addCriterion("sale_plan_id between", value1, value2, "salePlanId");
            return (Criteria) this;
        }

        public Criteria andSalePlanIdNotBetween(String value1, String value2) {
            addCriterion("sale_plan_id not between", value1, value2, "salePlanId");
            return (Criteria) this;
        }

        public Criteria andSaleFlagIsNull() {
            addCriterion("sale_flag is null");
            return (Criteria) this;
        }

        public Criteria andSaleFlagIsNotNull() {
            addCriterion("sale_flag is not null");
            return (Criteria) this;
        }

        public Criteria andSaleFlagEqualTo(String value) {
            addCriterion("sale_flag =", value, "saleFlag");
            return (Criteria) this;
        }

        public Criteria andSaleFlagNotEqualTo(String value) {
            addCriterion("sale_flag <>", value, "saleFlag");
            return (Criteria) this;
        }

        public Criteria andSaleFlagGreaterThan(String value) {
            addCriterion("sale_flag >", value, "saleFlag");
            return (Criteria) this;
        }

        public Criteria andSaleFlagGreaterThanOrEqualTo(String value) {
            addCriterion("sale_flag >=", value, "saleFlag");
            return (Criteria) this;
        }

        public Criteria andSaleFlagLessThan(String value) {
            addCriterion("sale_flag <", value, "saleFlag");
            return (Criteria) this;
        }

        public Criteria andSaleFlagLessThanOrEqualTo(String value) {
            addCriterion("sale_flag <=", value, "saleFlag");
            return (Criteria) this;
        }

        public Criteria andSaleFlagLike(String value) {
            addCriterion("sale_flag like", value, "saleFlag");
            return (Criteria) this;
        }

        public Criteria andSaleFlagNotLike(String value) {
            addCriterion("sale_flag not like", value, "saleFlag");
            return (Criteria) this;
        }

        public Criteria andSaleFlagIn(List<String> values) {
            addCriterion("sale_flag in", values, "saleFlag");
            return (Criteria) this;
        }

        public Criteria andSaleFlagNotIn(List<String> values) {
            addCriterion("sale_flag not in", values, "saleFlag");
            return (Criteria) this;
        }

        public Criteria andSaleFlagBetween(String value1, String value2) {
            addCriterion("sale_flag between", value1, value2, "saleFlag");
            return (Criteria) this;
        }

        public Criteria andSaleFlagNotBetween(String value1, String value2) {
            addCriterion("sale_flag not between", value1, value2, "saleFlag");
            return (Criteria) this;
        }

        public Criteria andSaleNumIsNull() {
            addCriterion("sale_num is null");
            return (Criteria) this;
        }

        public Criteria andSaleNumIsNotNull() {
            addCriterion("sale_num is not null");
            return (Criteria) this;
        }

        public Criteria andSaleNumEqualTo(Integer value) {
            addCriterion("sale_num =", value, "saleNum");
            return (Criteria) this;
        }

        public Criteria andSaleNumNotEqualTo(Integer value) {
            addCriterion("sale_num <>", value, "saleNum");
            return (Criteria) this;
        }

        public Criteria andSaleNumGreaterThan(Integer value) {
            addCriterion("sale_num >", value, "saleNum");
            return (Criteria) this;
        }

        public Criteria andSaleNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("sale_num >=", value, "saleNum");
            return (Criteria) this;
        }

        public Criteria andSaleNumLessThan(Integer value) {
            addCriterion("sale_num <", value, "saleNum");
            return (Criteria) this;
        }

        public Criteria andSaleNumLessThanOrEqualTo(Integer value) {
            addCriterion("sale_num <=", value, "saleNum");
            return (Criteria) this;
        }

        public Criteria andSaleNumIn(List<Integer> values) {
            addCriterion("sale_num in", values, "saleNum");
            return (Criteria) this;
        }

        public Criteria andSaleNumNotIn(List<Integer> values) {
            addCriterion("sale_num not in", values, "saleNum");
            return (Criteria) this;
        }

        public Criteria andSaleNumBetween(Integer value1, Integer value2) {
            addCriterion("sale_num between", value1, value2, "saleNum");
            return (Criteria) this;
        }

        public Criteria andSaleNumNotBetween(Integer value1, Integer value2) {
            addCriterion("sale_num not between", value1, value2, "saleNum");
            return (Criteria) this;
        }

        public Criteria andSaleWeightIsNull() {
            addCriterion("sale_weight is null");
            return (Criteria) this;
        }

        public Criteria andSaleWeightIsNotNull() {
            addCriterion("sale_weight is not null");
            return (Criteria) this;
        }

        public Criteria andSaleWeightEqualTo(Float value) {
            addCriterion("sale_weight =", value, "saleWeight");
            return (Criteria) this;
        }

        public Criteria andSaleWeightNotEqualTo(Float value) {
            addCriterion("sale_weight <>", value, "saleWeight");
            return (Criteria) this;
        }

        public Criteria andSaleWeightGreaterThan(Float value) {
            addCriterion("sale_weight >", value, "saleWeight");
            return (Criteria) this;
        }

        public Criteria andSaleWeightGreaterThanOrEqualTo(Float value) {
            addCriterion("sale_weight >=", value, "saleWeight");
            return (Criteria) this;
        }

        public Criteria andSaleWeightLessThan(Float value) {
            addCriterion("sale_weight <", value, "saleWeight");
            return (Criteria) this;
        }

        public Criteria andSaleWeightLessThanOrEqualTo(Float value) {
            addCriterion("sale_weight <=", value, "saleWeight");
            return (Criteria) this;
        }

        public Criteria andSaleWeightIn(List<Float> values) {
            addCriterion("sale_weight in", values, "saleWeight");
            return (Criteria) this;
        }

        public Criteria andSaleWeightNotIn(List<Float> values) {
            addCriterion("sale_weight not in", values, "saleWeight");
            return (Criteria) this;
        }

        public Criteria andSaleWeightBetween(Float value1, Float value2) {
            addCriterion("sale_weight between", value1, value2, "saleWeight");
            return (Criteria) this;
        }

        public Criteria andSaleWeightNotBetween(Float value1, Float value2) {
            addCriterion("sale_weight not between", value1, value2, "saleWeight");
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

        public Criteria andProdMaterialIsNull() {
            addCriterion("prod_material is null");
            return (Criteria) this;
        }

        public Criteria andProdMaterialIsNotNull() {
            addCriterion("prod_material is not null");
            return (Criteria) this;
        }

        public Criteria andProdMaterialEqualTo(String value) {
            addCriterion("prod_material =", value, "prodMaterial");
            return (Criteria) this;
        }

        public Criteria andProdMaterialNotEqualTo(String value) {
            addCriterion("prod_material <>", value, "prodMaterial");
            return (Criteria) this;
        }

        public Criteria andProdMaterialGreaterThan(String value) {
            addCriterion("prod_material >", value, "prodMaterial");
            return (Criteria) this;
        }

        public Criteria andProdMaterialGreaterThanOrEqualTo(String value) {
            addCriterion("prod_material >=", value, "prodMaterial");
            return (Criteria) this;
        }

        public Criteria andProdMaterialLessThan(String value) {
            addCriterion("prod_material <", value, "prodMaterial");
            return (Criteria) this;
        }

        public Criteria andProdMaterialLessThanOrEqualTo(String value) {
            addCriterion("prod_material <=", value, "prodMaterial");
            return (Criteria) this;
        }

        public Criteria andProdMaterialLike(String value) {
            addCriterion("prod_material like", value, "prodMaterial");
            return (Criteria) this;
        }

        public Criteria andProdMaterialNotLike(String value) {
            addCriterion("prod_material not like", value, "prodMaterial");
            return (Criteria) this;
        }

        public Criteria andProdMaterialIn(List<String> values) {
            addCriterion("prod_material in", values, "prodMaterial");
            return (Criteria) this;
        }

        public Criteria andProdMaterialNotIn(List<String> values) {
            addCriterion("prod_material not in", values, "prodMaterial");
            return (Criteria) this;
        }

        public Criteria andProdMaterialBetween(String value1, String value2) {
            addCriterion("prod_material between", value1, value2, "prodMaterial");
            return (Criteria) this;
        }

        public Criteria andProdMaterialNotBetween(String value1, String value2) {
            addCriterion("prod_material not between", value1, value2, "prodMaterial");
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

        public Criteria andProdSumnumIsNull() {
            addCriterion("prod_sumnum is null");
            return (Criteria) this;
        }

        public Criteria andProdSumnumIsNotNull() {
            addCriterion("prod_sumnum is not null");
            return (Criteria) this;
        }

        public Criteria andProdSumnumEqualTo(Integer value) {
            addCriterion("prod_sumnum =", value, "prodSumnum");
            return (Criteria) this;
        }

        public Criteria andProdSumnumNotEqualTo(Integer value) {
            addCriterion("prod_sumnum <>", value, "prodSumnum");
            return (Criteria) this;
        }

        public Criteria andProdSumnumGreaterThan(Integer value) {
            addCriterion("prod_sumnum >", value, "prodSumnum");
            return (Criteria) this;
        }

        public Criteria andProdSumnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("prod_sumnum >=", value, "prodSumnum");
            return (Criteria) this;
        }

        public Criteria andProdSumnumLessThan(Integer value) {
            addCriterion("prod_sumnum <", value, "prodSumnum");
            return (Criteria) this;
        }

        public Criteria andProdSumnumLessThanOrEqualTo(Integer value) {
            addCriterion("prod_sumnum <=", value, "prodSumnum");
            return (Criteria) this;
        }

        public Criteria andProdSumnumIn(List<Integer> values) {
            addCriterion("prod_sumnum in", values, "prodSumnum");
            return (Criteria) this;
        }

        public Criteria andProdSumnumNotIn(List<Integer> values) {
            addCriterion("prod_sumnum not in", values, "prodSumnum");
            return (Criteria) this;
        }

        public Criteria andProdSumnumBetween(Integer value1, Integer value2) {
            addCriterion("prod_sumnum between", value1, value2, "prodSumnum");
            return (Criteria) this;
        }

        public Criteria andProdSumnumNotBetween(Integer value1, Integer value2) {
            addCriterion("prod_sumnum not between", value1, value2, "prodSumnum");
            return (Criteria) this;
        }

        public Criteria andProdSumweightIsNull() {
            addCriterion("prod_sumweight is null");
            return (Criteria) this;
        }

        public Criteria andProdSumweightIsNotNull() {
            addCriterion("prod_sumweight is not null");
            return (Criteria) this;
        }

        public Criteria andProdSumweightEqualTo(Float value) {
            addCriterion("prod_sumweight =", value, "prodSumweight");
            return (Criteria) this;
        }

        public Criteria andProdSumweightNotEqualTo(Float value) {
            addCriterion("prod_sumweight <>", value, "prodSumweight");
            return (Criteria) this;
        }

        public Criteria andProdSumweightGreaterThan(Float value) {
            addCriterion("prod_sumweight >", value, "prodSumweight");
            return (Criteria) this;
        }

        public Criteria andProdSumweightGreaterThanOrEqualTo(Float value) {
            addCriterion("prod_sumweight >=", value, "prodSumweight");
            return (Criteria) this;
        }

        public Criteria andProdSumweightLessThan(Float value) {
            addCriterion("prod_sumweight <", value, "prodSumweight");
            return (Criteria) this;
        }

        public Criteria andProdSumweightLessThanOrEqualTo(Float value) {
            addCriterion("prod_sumweight <=", value, "prodSumweight");
            return (Criteria) this;
        }

        public Criteria andProdSumweightIn(List<Float> values) {
            addCriterion("prod_sumweight in", values, "prodSumweight");
            return (Criteria) this;
        }

        public Criteria andProdSumweightNotIn(List<Float> values) {
            addCriterion("prod_sumweight not in", values, "prodSumweight");
            return (Criteria) this;
        }

        public Criteria andProdSumweightBetween(Float value1, Float value2) {
            addCriterion("prod_sumweight between", value1, value2, "prodSumweight");
            return (Criteria) this;
        }

        public Criteria andProdSumweightNotBetween(Float value1, Float value2) {
            addCriterion("prod_sumweight not between", value1, value2, "prodSumweight");
            return (Criteria) this;
        }

        public Criteria andSalePriceIsNull() {
            addCriterion("sale_price is null");
            return (Criteria) this;
        }

        public Criteria andSalePriceIsNotNull() {
            addCriterion("sale_price is not null");
            return (Criteria) this;
        }

        public Criteria andSalePriceEqualTo(Float value) {
            addCriterion("sale_price =", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceNotEqualTo(Float value) {
            addCriterion("sale_price <>", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceGreaterThan(Float value) {
            addCriterion("sale_price >", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceGreaterThanOrEqualTo(Float value) {
            addCriterion("sale_price >=", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceLessThan(Float value) {
            addCriterion("sale_price <", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceLessThanOrEqualTo(Float value) {
            addCriterion("sale_price <=", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceIn(List<Float> values) {
            addCriterion("sale_price in", values, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceNotIn(List<Float> values) {
            addCriterion("sale_price not in", values, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceBetween(Float value1, Float value2) {
            addCriterion("sale_price between", value1, value2, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceNotBetween(Float value1, Float value2) {
            addCriterion("sale_price not between", value1, value2, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSaleStatusIsNull() {
            addCriterion("sale_status is null");
            return (Criteria) this;
        }

        public Criteria andSaleStatusIsNotNull() {
            addCriterion("sale_status is not null");
            return (Criteria) this;
        }

        public Criteria andSaleStatusEqualTo(String value) {
            addCriterion("sale_status =", value, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andSaleStatusNotEqualTo(String value) {
            addCriterion("sale_status <>", value, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andSaleStatusGreaterThan(String value) {
            addCriterion("sale_status >", value, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andSaleStatusGreaterThanOrEqualTo(String value) {
            addCriterion("sale_status >=", value, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andSaleStatusLessThan(String value) {
            addCriterion("sale_status <", value, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andSaleStatusLessThanOrEqualTo(String value) {
            addCriterion("sale_status <=", value, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andSaleStatusLike(String value) {
            addCriterion("sale_status like", value, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andSaleStatusNotLike(String value) {
            addCriterion("sale_status not like", value, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andSaleStatusIn(List<String> values) {
            addCriterion("sale_status in", values, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andSaleStatusNotIn(List<String> values) {
            addCriterion("sale_status not in", values, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andSaleStatusBetween(String value1, String value2) {
            addCriterion("sale_status between", value1, value2, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andSaleStatusNotBetween(String value1, String value2) {
            addCriterion("sale_status not between", value1, value2, "saleStatus");
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

        public Criteria andUpdDateIsNull() {
            addCriterion("upd_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdDateIsNotNull() {
            addCriterion("upd_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdDateEqualTo(Date value) {
            addCriterionForJDBCDate("upd_date =", value, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("upd_date <>", value, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateGreaterThan(Date value) {
            addCriterionForJDBCDate("upd_date >", value, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("upd_date >=", value, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateLessThan(Date value) {
            addCriterionForJDBCDate("upd_date <", value, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("upd_date <=", value, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateIn(List<Date> values) {
            addCriterionForJDBCDate("upd_date in", values, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("upd_date not in", values, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("upd_date between", value1, value2, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("upd_date not between", value1, value2, "updDate");
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

        public Criteria andProdJsnumIsNull() {
            addCriterion("prod_jsnum is null");
            return (Criteria) this;
        }

        public Criteria andProdJsnumIsNotNull() {
            addCriterion("prod_jsnum is not null");
            return (Criteria) this;
        }

        public Criteria andProdJsnumEqualTo(Integer value) {
            addCriterion("prod_jsnum =", value, "prodJsnum");
            return (Criteria) this;
        }

        public Criteria andProdJsnumNotEqualTo(Integer value) {
            addCriterion("prod_jsnum <>", value, "prodJsnum");
            return (Criteria) this;
        }

        public Criteria andProdJsnumGreaterThan(Integer value) {
            addCriterion("prod_jsnum >", value, "prodJsnum");
            return (Criteria) this;
        }

        public Criteria andProdJsnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("prod_jsnum >=", value, "prodJsnum");
            return (Criteria) this;
        }

        public Criteria andProdJsnumLessThan(Integer value) {
            addCriterion("prod_jsnum <", value, "prodJsnum");
            return (Criteria) this;
        }

        public Criteria andProdJsnumLessThanOrEqualTo(Integer value) {
            addCriterion("prod_jsnum <=", value, "prodJsnum");
            return (Criteria) this;
        }

        public Criteria andProdJsnumIn(List<Integer> values) {
            addCriterion("prod_jsnum in", values, "prodJsnum");
            return (Criteria) this;
        }

        public Criteria andProdJsnumNotIn(List<Integer> values) {
            addCriterion("prod_jsnum not in", values, "prodJsnum");
            return (Criteria) this;
        }

        public Criteria andProdJsnumBetween(Integer value1, Integer value2) {
            addCriterion("prod_jsnum between", value1, value2, "prodJsnum");
            return (Criteria) this;
        }

        public Criteria andProdJsnumNotBetween(Integer value1, Integer value2) {
            addCriterion("prod_jsnum not between", value1, value2, "prodJsnum");
            return (Criteria) this;
        }

        public Criteria andProdJsweightIsNull() {
            addCriterion("prod_jsweight is null");
            return (Criteria) this;
        }

        public Criteria andProdJsweightIsNotNull() {
            addCriterion("prod_jsweight is not null");
            return (Criteria) this;
        }

        public Criteria andProdJsweightEqualTo(Float value) {
            addCriterion("prod_jsweight =", value, "prodJsweight");
            return (Criteria) this;
        }

        public Criteria andProdJsweightNotEqualTo(Float value) {
            addCriterion("prod_jsweight <>", value, "prodJsweight");
            return (Criteria) this;
        }

        public Criteria andProdJsweightGreaterThan(Float value) {
            addCriterion("prod_jsweight >", value, "prodJsweight");
            return (Criteria) this;
        }

        public Criteria andProdJsweightGreaterThanOrEqualTo(Float value) {
            addCriterion("prod_jsweight >=", value, "prodJsweight");
            return (Criteria) this;
        }

        public Criteria andProdJsweightLessThan(Float value) {
            addCriterion("prod_jsweight <", value, "prodJsweight");
            return (Criteria) this;
        }

        public Criteria andProdJsweightLessThanOrEqualTo(Float value) {
            addCriterion("prod_jsweight <=", value, "prodJsweight");
            return (Criteria) this;
        }

        public Criteria andProdJsweightIn(List<Float> values) {
            addCriterion("prod_jsweight in", values, "prodJsweight");
            return (Criteria) this;
        }

        public Criteria andProdJsweightNotIn(List<Float> values) {
            addCriterion("prod_jsweight not in", values, "prodJsweight");
            return (Criteria) this;
        }

        public Criteria andProdJsweightBetween(Float value1, Float value2) {
            addCriterion("prod_jsweight between", value1, value2, "prodJsweight");
            return (Criteria) this;
        }

        public Criteria andProdJsweightNotBetween(Float value1, Float value2) {
            addCriterion("prod_jsweight not between", value1, value2, "prodJsweight");
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