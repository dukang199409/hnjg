package com.bsl.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BslProductInfoHExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BslProductInfoHExample() {
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

        public Criteria andProdNameIsNull() {
            addCriterion("prod_name is null");
            return (Criteria) this;
        }

        public Criteria andProdNameIsNotNull() {
            addCriterion("prod_name is not null");
            return (Criteria) this;
        }

        public Criteria andProdNameEqualTo(String value) {
            addCriterion("prod_name =", value, "prodName");
            return (Criteria) this;
        }

        public Criteria andProdNameNotEqualTo(String value) {
            addCriterion("prod_name <>", value, "prodName");
            return (Criteria) this;
        }

        public Criteria andProdNameGreaterThan(String value) {
            addCriterion("prod_name >", value, "prodName");
            return (Criteria) this;
        }

        public Criteria andProdNameGreaterThanOrEqualTo(String value) {
            addCriterion("prod_name >=", value, "prodName");
            return (Criteria) this;
        }

        public Criteria andProdNameLessThan(String value) {
            addCriterion("prod_name <", value, "prodName");
            return (Criteria) this;
        }

        public Criteria andProdNameLessThanOrEqualTo(String value) {
            addCriterion("prod_name <=", value, "prodName");
            return (Criteria) this;
        }

        public Criteria andProdNameLike(String value) {
            addCriterion("prod_name like", value, "prodName");
            return (Criteria) this;
        }

        public Criteria andProdNameNotLike(String value) {
            addCriterion("prod_name not like", value, "prodName");
            return (Criteria) this;
        }

        public Criteria andProdNameIn(List<String> values) {
            addCriterion("prod_name in", values, "prodName");
            return (Criteria) this;
        }

        public Criteria andProdNameNotIn(List<String> values) {
            addCriterion("prod_name not in", values, "prodName");
            return (Criteria) this;
        }

        public Criteria andProdNameBetween(String value1, String value2) {
            addCriterion("prod_name between", value1, value2, "prodName");
            return (Criteria) this;
        }

        public Criteria andProdNameNotBetween(String value1, String value2) {
            addCriterion("prod_name not between", value1, value2, "prodName");
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

        public Criteria andProdRecordWeightIsNull() {
            addCriterion("prod_record_weight is null");
            return (Criteria) this;
        }

        public Criteria andProdRecordWeightIsNotNull() {
            addCriterion("prod_record_weight is not null");
            return (Criteria) this;
        }

        public Criteria andProdRecordWeightEqualTo(Float value) {
            addCriterion("prod_record_weight =", value, "prodRecordWeight");
            return (Criteria) this;
        }

        public Criteria andProdRecordWeightNotEqualTo(Float value) {
            addCriterion("prod_record_weight <>", value, "prodRecordWeight");
            return (Criteria) this;
        }

        public Criteria andProdRecordWeightGreaterThan(Float value) {
            addCriterion("prod_record_weight >", value, "prodRecordWeight");
            return (Criteria) this;
        }

        public Criteria andProdRecordWeightGreaterThanOrEqualTo(Float value) {
            addCriterion("prod_record_weight >=", value, "prodRecordWeight");
            return (Criteria) this;
        }

        public Criteria andProdRecordWeightLessThan(Float value) {
            addCriterion("prod_record_weight <", value, "prodRecordWeight");
            return (Criteria) this;
        }

        public Criteria andProdRecordWeightLessThanOrEqualTo(Float value) {
            addCriterion("prod_record_weight <=", value, "prodRecordWeight");
            return (Criteria) this;
        }

        public Criteria andProdRecordWeightIn(List<Float> values) {
            addCriterion("prod_record_weight in", values, "prodRecordWeight");
            return (Criteria) this;
        }

        public Criteria andProdRecordWeightNotIn(List<Float> values) {
            addCriterion("prod_record_weight not in", values, "prodRecordWeight");
            return (Criteria) this;
        }

        public Criteria andProdRecordWeightBetween(Float value1, Float value2) {
            addCriterion("prod_record_weight between", value1, value2, "prodRecordWeight");
            return (Criteria) this;
        }

        public Criteria andProdRecordWeightNotBetween(Float value1, Float value2) {
            addCriterion("prod_record_weight not between", value1, value2, "prodRecordWeight");
            return (Criteria) this;
        }

        public Criteria andProdRelWeightIsNull() {
            addCriterion("prod_rel_weight is null");
            return (Criteria) this;
        }

        public Criteria andProdRelWeightIsNotNull() {
            addCriterion("prod_rel_weight is not null");
            return (Criteria) this;
        }

        public Criteria andProdRelWeightEqualTo(Float value) {
            addCriterion("prod_rel_weight =", value, "prodRelWeight");
            return (Criteria) this;
        }

        public Criteria andProdRelWeightNotEqualTo(Float value) {
            addCriterion("prod_rel_weight <>", value, "prodRelWeight");
            return (Criteria) this;
        }

        public Criteria andProdRelWeightGreaterThan(Float value) {
            addCriterion("prod_rel_weight >", value, "prodRelWeight");
            return (Criteria) this;
        }

        public Criteria andProdRelWeightGreaterThanOrEqualTo(Float value) {
            addCriterion("prod_rel_weight >=", value, "prodRelWeight");
            return (Criteria) this;
        }

        public Criteria andProdRelWeightLessThan(Float value) {
            addCriterion("prod_rel_weight <", value, "prodRelWeight");
            return (Criteria) this;
        }

        public Criteria andProdRelWeightLessThanOrEqualTo(Float value) {
            addCriterion("prod_rel_weight <=", value, "prodRelWeight");
            return (Criteria) this;
        }

        public Criteria andProdRelWeightIn(List<Float> values) {
            addCriterion("prod_rel_weight in", values, "prodRelWeight");
            return (Criteria) this;
        }

        public Criteria andProdRelWeightNotIn(List<Float> values) {
            addCriterion("prod_rel_weight not in", values, "prodRelWeight");
            return (Criteria) this;
        }

        public Criteria andProdRelWeightBetween(Float value1, Float value2) {
            addCriterion("prod_rel_weight between", value1, value2, "prodRelWeight");
            return (Criteria) this;
        }

        public Criteria andProdRelWeightNotBetween(Float value1, Float value2) {
            addCriterion("prod_rel_weight not between", value1, value2, "prodRelWeight");
            return (Criteria) this;
        }

        public Criteria andProdPrintWeightIsNull() {
            addCriterion("prod_print_weight is null");
            return (Criteria) this;
        }

        public Criteria andProdPrintWeightIsNotNull() {
            addCriterion("prod_print_weight is not null");
            return (Criteria) this;
        }

        public Criteria andProdPrintWeightEqualTo(Float value) {
            addCriterion("prod_print_weight =", value, "prodPrintWeight");
            return (Criteria) this;
        }

        public Criteria andProdPrintWeightNotEqualTo(Float value) {
            addCriterion("prod_print_weight <>", value, "prodPrintWeight");
            return (Criteria) this;
        }

        public Criteria andProdPrintWeightGreaterThan(Float value) {
            addCriterion("prod_print_weight >", value, "prodPrintWeight");
            return (Criteria) this;
        }

        public Criteria andProdPrintWeightGreaterThanOrEqualTo(Float value) {
            addCriterion("prod_print_weight >=", value, "prodPrintWeight");
            return (Criteria) this;
        }

        public Criteria andProdPrintWeightLessThan(Float value) {
            addCriterion("prod_print_weight <", value, "prodPrintWeight");
            return (Criteria) this;
        }

        public Criteria andProdPrintWeightLessThanOrEqualTo(Float value) {
            addCriterion("prod_print_weight <=", value, "prodPrintWeight");
            return (Criteria) this;
        }

        public Criteria andProdPrintWeightIn(List<Float> values) {
            addCriterion("prod_print_weight in", values, "prodPrintWeight");
            return (Criteria) this;
        }

        public Criteria andProdPrintWeightNotIn(List<Float> values) {
            addCriterion("prod_print_weight not in", values, "prodPrintWeight");
            return (Criteria) this;
        }

        public Criteria andProdPrintWeightBetween(Float value1, Float value2) {
            addCriterion("prod_print_weight between", value1, value2, "prodPrintWeight");
            return (Criteria) this;
        }

        public Criteria andProdPrintWeightNotBetween(Float value1, Float value2) {
            addCriterion("prod_print_weight not between", value1, value2, "prodPrintWeight");
            return (Criteria) this;
        }

        public Criteria andProdOutWeightIsNull() {
            addCriterion("prod_out_weight is null");
            return (Criteria) this;
        }

        public Criteria andProdOutWeightIsNotNull() {
            addCriterion("prod_out_weight is not null");
            return (Criteria) this;
        }

        public Criteria andProdOutWeightEqualTo(Float value) {
            addCriterion("prod_out_weight =", value, "prodOutWeight");
            return (Criteria) this;
        }

        public Criteria andProdOutWeightNotEqualTo(Float value) {
            addCriterion("prod_out_weight <>", value, "prodOutWeight");
            return (Criteria) this;
        }

        public Criteria andProdOutWeightGreaterThan(Float value) {
            addCriterion("prod_out_weight >", value, "prodOutWeight");
            return (Criteria) this;
        }

        public Criteria andProdOutWeightGreaterThanOrEqualTo(Float value) {
            addCriterion("prod_out_weight >=", value, "prodOutWeight");
            return (Criteria) this;
        }

        public Criteria andProdOutWeightLessThan(Float value) {
            addCriterion("prod_out_weight <", value, "prodOutWeight");
            return (Criteria) this;
        }

        public Criteria andProdOutWeightLessThanOrEqualTo(Float value) {
            addCriterion("prod_out_weight <=", value, "prodOutWeight");
            return (Criteria) this;
        }

        public Criteria andProdOutWeightIn(List<Float> values) {
            addCriterion("prod_out_weight in", values, "prodOutWeight");
            return (Criteria) this;
        }

        public Criteria andProdOutWeightNotIn(List<Float> values) {
            addCriterion("prod_out_weight not in", values, "prodOutWeight");
            return (Criteria) this;
        }

        public Criteria andProdOutWeightBetween(Float value1, Float value2) {
            addCriterion("prod_out_weight between", value1, value2, "prodOutWeight");
            return (Criteria) this;
        }

        public Criteria andProdOutWeightNotBetween(Float value1, Float value2) {
            addCriterion("prod_out_weight not between", value1, value2, "prodOutWeight");
            return (Criteria) this;
        }

        public Criteria andProdSourceCompanyIsNull() {
            addCriterion("prod_source_company is null");
            return (Criteria) this;
        }

        public Criteria andProdSourceCompanyIsNotNull() {
            addCriterion("prod_source_company is not null");
            return (Criteria) this;
        }

        public Criteria andProdSourceCompanyEqualTo(String value) {
            addCriterion("prod_source_company =", value, "prodSourceCompany");
            return (Criteria) this;
        }

        public Criteria andProdSourceCompanyNotEqualTo(String value) {
            addCriterion("prod_source_company <>", value, "prodSourceCompany");
            return (Criteria) this;
        }

        public Criteria andProdSourceCompanyGreaterThan(String value) {
            addCriterion("prod_source_company >", value, "prodSourceCompany");
            return (Criteria) this;
        }

        public Criteria andProdSourceCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("prod_source_company >=", value, "prodSourceCompany");
            return (Criteria) this;
        }

        public Criteria andProdSourceCompanyLessThan(String value) {
            addCriterion("prod_source_company <", value, "prodSourceCompany");
            return (Criteria) this;
        }

        public Criteria andProdSourceCompanyLessThanOrEqualTo(String value) {
            addCriterion("prod_source_company <=", value, "prodSourceCompany");
            return (Criteria) this;
        }

        public Criteria andProdSourceCompanyLike(String value) {
            addCriterion("prod_source_company like", value, "prodSourceCompany");
            return (Criteria) this;
        }

        public Criteria andProdSourceCompanyNotLike(String value) {
            addCriterion("prod_source_company not like", value, "prodSourceCompany");
            return (Criteria) this;
        }

        public Criteria andProdSourceCompanyIn(List<String> values) {
            addCriterion("prod_source_company in", values, "prodSourceCompany");
            return (Criteria) this;
        }

        public Criteria andProdSourceCompanyNotIn(List<String> values) {
            addCriterion("prod_source_company not in", values, "prodSourceCompany");
            return (Criteria) this;
        }

        public Criteria andProdSourceCompanyBetween(String value1, String value2) {
            addCriterion("prod_source_company between", value1, value2, "prodSourceCompany");
            return (Criteria) this;
        }

        public Criteria andProdSourceCompanyNotBetween(String value1, String value2) {
            addCriterion("prod_source_company not between", value1, value2, "prodSourceCompany");
            return (Criteria) this;
        }

        public Criteria andProdStatusIsNull() {
            addCriterion("prod_status is null");
            return (Criteria) this;
        }

        public Criteria andProdStatusIsNotNull() {
            addCriterion("prod_status is not null");
            return (Criteria) this;
        }

        public Criteria andProdStatusEqualTo(String value) {
            addCriterion("prod_status =", value, "prodStatus");
            return (Criteria) this;
        }

        public Criteria andProdStatusNotEqualTo(String value) {
            addCriterion("prod_status <>", value, "prodStatus");
            return (Criteria) this;
        }

        public Criteria andProdStatusGreaterThan(String value) {
            addCriterion("prod_status >", value, "prodStatus");
            return (Criteria) this;
        }

        public Criteria andProdStatusGreaterThanOrEqualTo(String value) {
            addCriterion("prod_status >=", value, "prodStatus");
            return (Criteria) this;
        }

        public Criteria andProdStatusLessThan(String value) {
            addCriterion("prod_status <", value, "prodStatus");
            return (Criteria) this;
        }

        public Criteria andProdStatusLessThanOrEqualTo(String value) {
            addCriterion("prod_status <=", value, "prodStatus");
            return (Criteria) this;
        }

        public Criteria andProdStatusLike(String value) {
            addCriterion("prod_status like", value, "prodStatus");
            return (Criteria) this;
        }

        public Criteria andProdStatusNotLike(String value) {
            addCriterion("prod_status not like", value, "prodStatus");
            return (Criteria) this;
        }

        public Criteria andProdStatusIn(List<String> values) {
            addCriterion("prod_status in", values, "prodStatus");
            return (Criteria) this;
        }

        public Criteria andProdStatusNotIn(List<String> values) {
            addCriterion("prod_status not in", values, "prodStatus");
            return (Criteria) this;
        }

        public Criteria andProdStatusBetween(String value1, String value2) {
            addCriterion("prod_status between", value1, value2, "prodStatus");
            return (Criteria) this;
        }

        public Criteria andProdStatusNotBetween(String value1, String value2) {
            addCriterion("prod_status not between", value1, value2, "prodStatus");
            return (Criteria) this;
        }

        public Criteria andProdUserTypeIsNull() {
            addCriterion("prod_user_type is null");
            return (Criteria) this;
        }

        public Criteria andProdUserTypeIsNotNull() {
            addCriterion("prod_user_type is not null");
            return (Criteria) this;
        }

        public Criteria andProdUserTypeEqualTo(String value) {
            addCriterion("prod_user_type =", value, "prodUserType");
            return (Criteria) this;
        }

        public Criteria andProdUserTypeNotEqualTo(String value) {
            addCriterion("prod_user_type <>", value, "prodUserType");
            return (Criteria) this;
        }

        public Criteria andProdUserTypeGreaterThan(String value) {
            addCriterion("prod_user_type >", value, "prodUserType");
            return (Criteria) this;
        }

        public Criteria andProdUserTypeGreaterThanOrEqualTo(String value) {
            addCriterion("prod_user_type >=", value, "prodUserType");
            return (Criteria) this;
        }

        public Criteria andProdUserTypeLessThan(String value) {
            addCriterion("prod_user_type <", value, "prodUserType");
            return (Criteria) this;
        }

        public Criteria andProdUserTypeLessThanOrEqualTo(String value) {
            addCriterion("prod_user_type <=", value, "prodUserType");
            return (Criteria) this;
        }

        public Criteria andProdUserTypeLike(String value) {
            addCriterion("prod_user_type like", value, "prodUserType");
            return (Criteria) this;
        }

        public Criteria andProdUserTypeNotLike(String value) {
            addCriterion("prod_user_type not like", value, "prodUserType");
            return (Criteria) this;
        }

        public Criteria andProdUserTypeIn(List<String> values) {
            addCriterion("prod_user_type in", values, "prodUserType");
            return (Criteria) this;
        }

        public Criteria andProdUserTypeNotIn(List<String> values) {
            addCriterion("prod_user_type not in", values, "prodUserType");
            return (Criteria) this;
        }

        public Criteria andProdUserTypeBetween(String value1, String value2) {
            addCriterion("prod_user_type between", value1, value2, "prodUserType");
            return (Criteria) this;
        }

        public Criteria andProdUserTypeNotBetween(String value1, String value2) {
            addCriterion("prod_user_type not between", value1, value2, "prodUserType");
            return (Criteria) this;
        }

        public Criteria andProdSourceIsNull() {
            addCriterion("prod_source is null");
            return (Criteria) this;
        }

        public Criteria andProdSourceIsNotNull() {
            addCriterion("prod_source is not null");
            return (Criteria) this;
        }

        public Criteria andProdSourceEqualTo(String value) {
            addCriterion("prod_source =", value, "prodSource");
            return (Criteria) this;
        }

        public Criteria andProdSourceNotEqualTo(String value) {
            addCriterion("prod_source <>", value, "prodSource");
            return (Criteria) this;
        }

        public Criteria andProdSourceGreaterThan(String value) {
            addCriterion("prod_source >", value, "prodSource");
            return (Criteria) this;
        }

        public Criteria andProdSourceGreaterThanOrEqualTo(String value) {
            addCriterion("prod_source >=", value, "prodSource");
            return (Criteria) this;
        }

        public Criteria andProdSourceLessThan(String value) {
            addCriterion("prod_source <", value, "prodSource");
            return (Criteria) this;
        }

        public Criteria andProdSourceLessThanOrEqualTo(String value) {
            addCriterion("prod_source <=", value, "prodSource");
            return (Criteria) this;
        }

        public Criteria andProdSourceLike(String value) {
            addCriterion("prod_source like", value, "prodSource");
            return (Criteria) this;
        }

        public Criteria andProdSourceNotLike(String value) {
            addCriterion("prod_source not like", value, "prodSource");
            return (Criteria) this;
        }

        public Criteria andProdSourceIn(List<String> values) {
            addCriterion("prod_source in", values, "prodSource");
            return (Criteria) this;
        }

        public Criteria andProdSourceNotIn(List<String> values) {
            addCriterion("prod_source not in", values, "prodSource");
            return (Criteria) this;
        }

        public Criteria andProdSourceBetween(String value1, String value2) {
            addCriterion("prod_source between", value1, value2, "prodSource");
            return (Criteria) this;
        }

        public Criteria andProdSourceNotBetween(String value1, String value2) {
            addCriterion("prod_source not between", value1, value2, "prodSource");
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

        public Criteria andProdOrirawidIsNull() {
            addCriterion("prod_orirawid is null");
            return (Criteria) this;
        }

        public Criteria andProdOrirawidIsNotNull() {
            addCriterion("prod_orirawid is not null");
            return (Criteria) this;
        }

        public Criteria andProdOrirawidEqualTo(String value) {
            addCriterion("prod_orirawid =", value, "prodOrirawid");
            return (Criteria) this;
        }

        public Criteria andProdOrirawidNotEqualTo(String value) {
            addCriterion("prod_orirawid <>", value, "prodOrirawid");
            return (Criteria) this;
        }

        public Criteria andProdOrirawidGreaterThan(String value) {
            addCriterion("prod_orirawid >", value, "prodOrirawid");
            return (Criteria) this;
        }

        public Criteria andProdOrirawidGreaterThanOrEqualTo(String value) {
            addCriterion("prod_orirawid >=", value, "prodOrirawid");
            return (Criteria) this;
        }

        public Criteria andProdOrirawidLessThan(String value) {
            addCriterion("prod_orirawid <", value, "prodOrirawid");
            return (Criteria) this;
        }

        public Criteria andProdOrirawidLessThanOrEqualTo(String value) {
            addCriterion("prod_orirawid <=", value, "prodOrirawid");
            return (Criteria) this;
        }

        public Criteria andProdOrirawidLike(String value) {
            addCriterion("prod_orirawid like", value, "prodOrirawid");
            return (Criteria) this;
        }

        public Criteria andProdOrirawidNotLike(String value) {
            addCriterion("prod_orirawid not like", value, "prodOrirawid");
            return (Criteria) this;
        }

        public Criteria andProdOrirawidIn(List<String> values) {
            addCriterion("prod_orirawid in", values, "prodOrirawid");
            return (Criteria) this;
        }

        public Criteria andProdOrirawidNotIn(List<String> values) {
            addCriterion("prod_orirawid not in", values, "prodOrirawid");
            return (Criteria) this;
        }

        public Criteria andProdOrirawidBetween(String value1, String value2) {
            addCriterion("prod_orirawid between", value1, value2, "prodOrirawid");
            return (Criteria) this;
        }

        public Criteria andProdOrirawidNotBetween(String value1, String value2) {
            addCriterion("prod_orirawid not between", value1, value2, "prodOrirawid");
            return (Criteria) this;
        }

        public Criteria andProdDclFlagIsNull() {
            addCriterion("prod_dcl_flag is null");
            return (Criteria) this;
        }

        public Criteria andProdDclFlagIsNotNull() {
            addCriterion("prod_dcl_flag is not null");
            return (Criteria) this;
        }

        public Criteria andProdDclFlagEqualTo(String value) {
            addCriterion("prod_dcl_flag =", value, "prodDclFlag");
            return (Criteria) this;
        }

        public Criteria andProdDclFlagNotEqualTo(String value) {
            addCriterion("prod_dcl_flag <>", value, "prodDclFlag");
            return (Criteria) this;
        }

        public Criteria andProdDclFlagGreaterThan(String value) {
            addCriterion("prod_dcl_flag >", value, "prodDclFlag");
            return (Criteria) this;
        }

        public Criteria andProdDclFlagGreaterThanOrEqualTo(String value) {
            addCriterion("prod_dcl_flag >=", value, "prodDclFlag");
            return (Criteria) this;
        }

        public Criteria andProdDclFlagLessThan(String value) {
            addCriterion("prod_dcl_flag <", value, "prodDclFlag");
            return (Criteria) this;
        }

        public Criteria andProdDclFlagLessThanOrEqualTo(String value) {
            addCriterion("prod_dcl_flag <=", value, "prodDclFlag");
            return (Criteria) this;
        }

        public Criteria andProdDclFlagLike(String value) {
            addCriterion("prod_dcl_flag like", value, "prodDclFlag");
            return (Criteria) this;
        }

        public Criteria andProdDclFlagNotLike(String value) {
            addCriterion("prod_dcl_flag not like", value, "prodDclFlag");
            return (Criteria) this;
        }

        public Criteria andProdDclFlagIn(List<String> values) {
            addCriterion("prod_dcl_flag in", values, "prodDclFlag");
            return (Criteria) this;
        }

        public Criteria andProdDclFlagNotIn(List<String> values) {
            addCriterion("prod_dcl_flag not in", values, "prodDclFlag");
            return (Criteria) this;
        }

        public Criteria andProdDclFlagBetween(String value1, String value2) {
            addCriterion("prod_dcl_flag between", value1, value2, "prodDclFlag");
            return (Criteria) this;
        }

        public Criteria andProdDclFlagNotBetween(String value1, String value2) {
            addCriterion("prod_dcl_flag not between", value1, value2, "prodDclFlag");
            return (Criteria) this;
        }

        public Criteria andProdPlanNoIsNull() {
            addCriterion("prod_plan_no is null");
            return (Criteria) this;
        }

        public Criteria andProdPlanNoIsNotNull() {
            addCriterion("prod_plan_no is not null");
            return (Criteria) this;
        }

        public Criteria andProdPlanNoEqualTo(String value) {
            addCriterion("prod_plan_no =", value, "prodPlanNo");
            return (Criteria) this;
        }

        public Criteria andProdPlanNoNotEqualTo(String value) {
            addCriterion("prod_plan_no <>", value, "prodPlanNo");
            return (Criteria) this;
        }

        public Criteria andProdPlanNoGreaterThan(String value) {
            addCriterion("prod_plan_no >", value, "prodPlanNo");
            return (Criteria) this;
        }

        public Criteria andProdPlanNoGreaterThanOrEqualTo(String value) {
            addCriterion("prod_plan_no >=", value, "prodPlanNo");
            return (Criteria) this;
        }

        public Criteria andProdPlanNoLessThan(String value) {
            addCriterion("prod_plan_no <", value, "prodPlanNo");
            return (Criteria) this;
        }

        public Criteria andProdPlanNoLessThanOrEqualTo(String value) {
            addCriterion("prod_plan_no <=", value, "prodPlanNo");
            return (Criteria) this;
        }

        public Criteria andProdPlanNoLike(String value) {
            addCriterion("prod_plan_no like", value, "prodPlanNo");
            return (Criteria) this;
        }

        public Criteria andProdPlanNoNotLike(String value) {
            addCriterion("prod_plan_no not like", value, "prodPlanNo");
            return (Criteria) this;
        }

        public Criteria andProdPlanNoIn(List<String> values) {
            addCriterion("prod_plan_no in", values, "prodPlanNo");
            return (Criteria) this;
        }

        public Criteria andProdPlanNoNotIn(List<String> values) {
            addCriterion("prod_plan_no not in", values, "prodPlanNo");
            return (Criteria) this;
        }

        public Criteria andProdPlanNoBetween(String value1, String value2) {
            addCriterion("prod_plan_no between", value1, value2, "prodPlanNo");
            return (Criteria) this;
        }

        public Criteria andProdPlanNoNotBetween(String value1, String value2) {
            addCriterion("prod_plan_no not between", value1, value2, "prodPlanNo");
            return (Criteria) this;
        }

        public Criteria andProdMakeJzIsNull() {
            addCriterion("prod_make_jz is null");
            return (Criteria) this;
        }

        public Criteria andProdMakeJzIsNotNull() {
            addCriterion("prod_make_jz is not null");
            return (Criteria) this;
        }

        public Criteria andProdMakeJzEqualTo(String value) {
            addCriterion("prod_make_jz =", value, "prodMakeJz");
            return (Criteria) this;
        }

        public Criteria andProdMakeJzNotEqualTo(String value) {
            addCriterion("prod_make_jz <>", value, "prodMakeJz");
            return (Criteria) this;
        }

        public Criteria andProdMakeJzGreaterThan(String value) {
            addCriterion("prod_make_jz >", value, "prodMakeJz");
            return (Criteria) this;
        }

        public Criteria andProdMakeJzGreaterThanOrEqualTo(String value) {
            addCriterion("prod_make_jz >=", value, "prodMakeJz");
            return (Criteria) this;
        }

        public Criteria andProdMakeJzLessThan(String value) {
            addCriterion("prod_make_jz <", value, "prodMakeJz");
            return (Criteria) this;
        }

        public Criteria andProdMakeJzLessThanOrEqualTo(String value) {
            addCriterion("prod_make_jz <=", value, "prodMakeJz");
            return (Criteria) this;
        }

        public Criteria andProdMakeJzLike(String value) {
            addCriterion("prod_make_jz like", value, "prodMakeJz");
            return (Criteria) this;
        }

        public Criteria andProdMakeJzNotLike(String value) {
            addCriterion("prod_make_jz not like", value, "prodMakeJz");
            return (Criteria) this;
        }

        public Criteria andProdMakeJzIn(List<String> values) {
            addCriterion("prod_make_jz in", values, "prodMakeJz");
            return (Criteria) this;
        }

        public Criteria andProdMakeJzNotIn(List<String> values) {
            addCriterion("prod_make_jz not in", values, "prodMakeJz");
            return (Criteria) this;
        }

        public Criteria andProdMakeJzBetween(String value1, String value2) {
            addCriterion("prod_make_jz between", value1, value2, "prodMakeJz");
            return (Criteria) this;
        }

        public Criteria andProdMakeJzNotBetween(String value1, String value2) {
            addCriterion("prod_make_jz not between", value1, value2, "prodMakeJz");
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

        public Criteria andProdParentNoIsNull() {
            addCriterion("prod_parent_no is null");
            return (Criteria) this;
        }

        public Criteria andProdParentNoIsNotNull() {
            addCriterion("prod_parent_no is not null");
            return (Criteria) this;
        }

        public Criteria andProdParentNoEqualTo(String value) {
            addCriterion("prod_parent_no =", value, "prodParentNo");
            return (Criteria) this;
        }

        public Criteria andProdParentNoNotEqualTo(String value) {
            addCriterion("prod_parent_no <>", value, "prodParentNo");
            return (Criteria) this;
        }

        public Criteria andProdParentNoGreaterThan(String value) {
            addCriterion("prod_parent_no >", value, "prodParentNo");
            return (Criteria) this;
        }

        public Criteria andProdParentNoGreaterThanOrEqualTo(String value) {
            addCriterion("prod_parent_no >=", value, "prodParentNo");
            return (Criteria) this;
        }

        public Criteria andProdParentNoLessThan(String value) {
            addCriterion("prod_parent_no <", value, "prodParentNo");
            return (Criteria) this;
        }

        public Criteria andProdParentNoLessThanOrEqualTo(String value) {
            addCriterion("prod_parent_no <=", value, "prodParentNo");
            return (Criteria) this;
        }

        public Criteria andProdParentNoLike(String value) {
            addCriterion("prod_parent_no like", value, "prodParentNo");
            return (Criteria) this;
        }

        public Criteria andProdParentNoNotLike(String value) {
            addCriterion("prod_parent_no not like", value, "prodParentNo");
            return (Criteria) this;
        }

        public Criteria andProdParentNoIn(List<String> values) {
            addCriterion("prod_parent_no in", values, "prodParentNo");
            return (Criteria) this;
        }

        public Criteria andProdParentNoNotIn(List<String> values) {
            addCriterion("prod_parent_no not in", values, "prodParentNo");
            return (Criteria) this;
        }

        public Criteria andProdParentNoBetween(String value1, String value2) {
            addCriterion("prod_parent_no between", value1, value2, "prodParentNo");
            return (Criteria) this;
        }

        public Criteria andProdParentNoNotBetween(String value1, String value2) {
            addCriterion("prod_parent_no not between", value1, value2, "prodParentNo");
            return (Criteria) this;
        }

        public Criteria andProdOutPlanIsNull() {
            addCriterion("prod_out_plan is null");
            return (Criteria) this;
        }

        public Criteria andProdOutPlanIsNotNull() {
            addCriterion("prod_out_plan is not null");
            return (Criteria) this;
        }

        public Criteria andProdOutPlanEqualTo(String value) {
            addCriterion("prod_out_plan =", value, "prodOutPlan");
            return (Criteria) this;
        }

        public Criteria andProdOutPlanNotEqualTo(String value) {
            addCriterion("prod_out_plan <>", value, "prodOutPlan");
            return (Criteria) this;
        }

        public Criteria andProdOutPlanGreaterThan(String value) {
            addCriterion("prod_out_plan >", value, "prodOutPlan");
            return (Criteria) this;
        }

        public Criteria andProdOutPlanGreaterThanOrEqualTo(String value) {
            addCriterion("prod_out_plan >=", value, "prodOutPlan");
            return (Criteria) this;
        }

        public Criteria andProdOutPlanLessThan(String value) {
            addCriterion("prod_out_plan <", value, "prodOutPlan");
            return (Criteria) this;
        }

        public Criteria andProdOutPlanLessThanOrEqualTo(String value) {
            addCriterion("prod_out_plan <=", value, "prodOutPlan");
            return (Criteria) this;
        }

        public Criteria andProdOutPlanLike(String value) {
            addCriterion("prod_out_plan like", value, "prodOutPlan");
            return (Criteria) this;
        }

        public Criteria andProdOutPlanNotLike(String value) {
            addCriterion("prod_out_plan not like", value, "prodOutPlan");
            return (Criteria) this;
        }

        public Criteria andProdOutPlanIn(List<String> values) {
            addCriterion("prod_out_plan in", values, "prodOutPlan");
            return (Criteria) this;
        }

        public Criteria andProdOutPlanNotIn(List<String> values) {
            addCriterion("prod_out_plan not in", values, "prodOutPlan");
            return (Criteria) this;
        }

        public Criteria andProdOutPlanBetween(String value1, String value2) {
            addCriterion("prod_out_plan between", value1, value2, "prodOutPlan");
            return (Criteria) this;
        }

        public Criteria andProdOutPlanNotBetween(String value1, String value2) {
            addCriterion("prod_out_plan not between", value1, value2, "prodOutPlan");
            return (Criteria) this;
        }

        public Criteria andProdSaleSernoIsNull() {
            addCriterion("prod_sale_serno is null");
            return (Criteria) this;
        }

        public Criteria andProdSaleSernoIsNotNull() {
            addCriterion("prod_sale_serno is not null");
            return (Criteria) this;
        }

        public Criteria andProdSaleSernoEqualTo(String value) {
            addCriterion("prod_sale_serno =", value, "prodSaleSerno");
            return (Criteria) this;
        }

        public Criteria andProdSaleSernoNotEqualTo(String value) {
            addCriterion("prod_sale_serno <>", value, "prodSaleSerno");
            return (Criteria) this;
        }

        public Criteria andProdSaleSernoGreaterThan(String value) {
            addCriterion("prod_sale_serno >", value, "prodSaleSerno");
            return (Criteria) this;
        }

        public Criteria andProdSaleSernoGreaterThanOrEqualTo(String value) {
            addCriterion("prod_sale_serno >=", value, "prodSaleSerno");
            return (Criteria) this;
        }

        public Criteria andProdSaleSernoLessThan(String value) {
            addCriterion("prod_sale_serno <", value, "prodSaleSerno");
            return (Criteria) this;
        }

        public Criteria andProdSaleSernoLessThanOrEqualTo(String value) {
            addCriterion("prod_sale_serno <=", value, "prodSaleSerno");
            return (Criteria) this;
        }

        public Criteria andProdSaleSernoLike(String value) {
            addCriterion("prod_sale_serno like", value, "prodSaleSerno");
            return (Criteria) this;
        }

        public Criteria andProdSaleSernoNotLike(String value) {
            addCriterion("prod_sale_serno not like", value, "prodSaleSerno");
            return (Criteria) this;
        }

        public Criteria andProdSaleSernoIn(List<String> values) {
            addCriterion("prod_sale_serno in", values, "prodSaleSerno");
            return (Criteria) this;
        }

        public Criteria andProdSaleSernoNotIn(List<String> values) {
            addCriterion("prod_sale_serno not in", values, "prodSaleSerno");
            return (Criteria) this;
        }

        public Criteria andProdSaleSernoBetween(String value1, String value2) {
            addCriterion("prod_sale_serno between", value1, value2, "prodSaleSerno");
            return (Criteria) this;
        }

        public Criteria andProdSaleSernoNotBetween(String value1, String value2) {
            addCriterion("prod_sale_serno not between", value1, value2, "prodSaleSerno");
            return (Criteria) this;
        }

        public Criteria andProdOutCarnoIsNull() {
            addCriterion("prod_out_carno is null");
            return (Criteria) this;
        }

        public Criteria andProdOutCarnoIsNotNull() {
            addCriterion("prod_out_carno is not null");
            return (Criteria) this;
        }

        public Criteria andProdOutCarnoEqualTo(String value) {
            addCriterion("prod_out_carno =", value, "prodOutCarno");
            return (Criteria) this;
        }

        public Criteria andProdOutCarnoNotEqualTo(String value) {
            addCriterion("prod_out_carno <>", value, "prodOutCarno");
            return (Criteria) this;
        }

        public Criteria andProdOutCarnoGreaterThan(String value) {
            addCriterion("prod_out_carno >", value, "prodOutCarno");
            return (Criteria) this;
        }

        public Criteria andProdOutCarnoGreaterThanOrEqualTo(String value) {
            addCriterion("prod_out_carno >=", value, "prodOutCarno");
            return (Criteria) this;
        }

        public Criteria andProdOutCarnoLessThan(String value) {
            addCriterion("prod_out_carno <", value, "prodOutCarno");
            return (Criteria) this;
        }

        public Criteria andProdOutCarnoLessThanOrEqualTo(String value) {
            addCriterion("prod_out_carno <=", value, "prodOutCarno");
            return (Criteria) this;
        }

        public Criteria andProdOutCarnoLike(String value) {
            addCriterion("prod_out_carno like", value, "prodOutCarno");
            return (Criteria) this;
        }

        public Criteria andProdOutCarnoNotLike(String value) {
            addCriterion("prod_out_carno not like", value, "prodOutCarno");
            return (Criteria) this;
        }

        public Criteria andProdOutCarnoIn(List<String> values) {
            addCriterion("prod_out_carno in", values, "prodOutCarno");
            return (Criteria) this;
        }

        public Criteria andProdOutCarnoNotIn(List<String> values) {
            addCriterion("prod_out_carno not in", values, "prodOutCarno");
            return (Criteria) this;
        }

        public Criteria andProdOutCarnoBetween(String value1, String value2) {
            addCriterion("prod_out_carno between", value1, value2, "prodOutCarno");
            return (Criteria) this;
        }

        public Criteria andProdOutCarnoNotBetween(String value1, String value2) {
            addCriterion("prod_out_carno not between", value1, value2, "prodOutCarno");
            return (Criteria) this;
        }

        public Criteria andProdOutDateIsNull() {
            addCriterion("prod_out_date is null");
            return (Criteria) this;
        }

        public Criteria andProdOutDateIsNotNull() {
            addCriterion("prod_out_date is not null");
            return (Criteria) this;
        }

        public Criteria andProdOutDateEqualTo(Date value) {
            addCriterion("prod_out_date =", value, "prodOutDate");
            return (Criteria) this;
        }

        public Criteria andProdOutDateNotEqualTo(Date value) {
            addCriterion("prod_out_date <>", value, "prodOutDate");
            return (Criteria) this;
        }

        public Criteria andProdOutDateGreaterThan(Date value) {
            addCriterion("prod_out_date >", value, "prodOutDate");
            return (Criteria) this;
        }

        public Criteria andProdOutDateGreaterThanOrEqualTo(Date value) {
            addCriterion("prod_out_date >=", value, "prodOutDate");
            return (Criteria) this;
        }

        public Criteria andProdOutDateLessThan(Date value) {
            addCriterion("prod_out_date <", value, "prodOutDate");
            return (Criteria) this;
        }

        public Criteria andProdOutDateLessThanOrEqualTo(Date value) {
            addCriterion("prod_out_date <=", value, "prodOutDate");
            return (Criteria) this;
        }

        public Criteria andProdOutDateIn(List<Date> values) {
            addCriterion("prod_out_date in", values, "prodOutDate");
            return (Criteria) this;
        }

        public Criteria andProdOutDateNotIn(List<Date> values) {
            addCriterion("prod_out_date not in", values, "prodOutDate");
            return (Criteria) this;
        }

        public Criteria andProdOutDateBetween(Date value1, Date value2) {
            addCriterion("prod_out_date between", value1, value2, "prodOutDate");
            return (Criteria) this;
        }

        public Criteria andProdOutDateNotBetween(Date value1, Date value2) {
            addCriterion("prod_out_date not between", value1, value2, "prodOutDate");
            return (Criteria) this;
        }

        public Criteria andProdCompanyIsNull() {
            addCriterion("prod_company is null");
            return (Criteria) this;
        }

        public Criteria andProdCompanyIsNotNull() {
            addCriterion("prod_company is not null");
            return (Criteria) this;
        }

        public Criteria andProdCompanyEqualTo(String value) {
            addCriterion("prod_company =", value, "prodCompany");
            return (Criteria) this;
        }

        public Criteria andProdCompanyNotEqualTo(String value) {
            addCriterion("prod_company <>", value, "prodCompany");
            return (Criteria) this;
        }

        public Criteria andProdCompanyGreaterThan(String value) {
            addCriterion("prod_company >", value, "prodCompany");
            return (Criteria) this;
        }

        public Criteria andProdCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("prod_company >=", value, "prodCompany");
            return (Criteria) this;
        }

        public Criteria andProdCompanyLessThan(String value) {
            addCriterion("prod_company <", value, "prodCompany");
            return (Criteria) this;
        }

        public Criteria andProdCompanyLessThanOrEqualTo(String value) {
            addCriterion("prod_company <=", value, "prodCompany");
            return (Criteria) this;
        }

        public Criteria andProdCompanyLike(String value) {
            addCriterion("prod_company like", value, "prodCompany");
            return (Criteria) this;
        }

        public Criteria andProdCompanyNotLike(String value) {
            addCriterion("prod_company not like", value, "prodCompany");
            return (Criteria) this;
        }

        public Criteria andProdCompanyIn(List<String> values) {
            addCriterion("prod_company in", values, "prodCompany");
            return (Criteria) this;
        }

        public Criteria andProdCompanyNotIn(List<String> values) {
            addCriterion("prod_company not in", values, "prodCompany");
            return (Criteria) this;
        }

        public Criteria andProdCompanyBetween(String value1, String value2) {
            addCriterion("prod_company between", value1, value2, "prodCompany");
            return (Criteria) this;
        }

        public Criteria andProdCompanyNotBetween(String value1, String value2) {
            addCriterion("prod_company not between", value1, value2, "prodCompany");
            return (Criteria) this;
        }

        public Criteria andProdCustomerIsNull() {
            addCriterion("prod_customer is null");
            return (Criteria) this;
        }

        public Criteria andProdCustomerIsNotNull() {
            addCriterion("prod_customer is not null");
            return (Criteria) this;
        }

        public Criteria andProdCustomerEqualTo(String value) {
            addCriterion("prod_customer =", value, "prodCustomer");
            return (Criteria) this;
        }

        public Criteria andProdCustomerNotEqualTo(String value) {
            addCriterion("prod_customer <>", value, "prodCustomer");
            return (Criteria) this;
        }

        public Criteria andProdCustomerGreaterThan(String value) {
            addCriterion("prod_customer >", value, "prodCustomer");
            return (Criteria) this;
        }

        public Criteria andProdCustomerGreaterThanOrEqualTo(String value) {
            addCriterion("prod_customer >=", value, "prodCustomer");
            return (Criteria) this;
        }

        public Criteria andProdCustomerLessThan(String value) {
            addCriterion("prod_customer <", value, "prodCustomer");
            return (Criteria) this;
        }

        public Criteria andProdCustomerLessThanOrEqualTo(String value) {
            addCriterion("prod_customer <=", value, "prodCustomer");
            return (Criteria) this;
        }

        public Criteria andProdCustomerLike(String value) {
            addCriterion("prod_customer like", value, "prodCustomer");
            return (Criteria) this;
        }

        public Criteria andProdCustomerNotLike(String value) {
            addCriterion("prod_customer not like", value, "prodCustomer");
            return (Criteria) this;
        }

        public Criteria andProdCustomerIn(List<String> values) {
            addCriterion("prod_customer in", values, "prodCustomer");
            return (Criteria) this;
        }

        public Criteria andProdCustomerNotIn(List<String> values) {
            addCriterion("prod_customer not in", values, "prodCustomer");
            return (Criteria) this;
        }

        public Criteria andProdCustomerBetween(String value1, String value2) {
            addCriterion("prod_customer between", value1, value2, "prodCustomer");
            return (Criteria) this;
        }

        public Criteria andProdCustomerNotBetween(String value1, String value2) {
            addCriterion("prod_customer not between", value1, value2, "prodCustomer");
            return (Criteria) this;
        }

        public Criteria andProdBcIsNull() {
            addCriterion("prod_bc is null");
            return (Criteria) this;
        }

        public Criteria andProdBcIsNotNull() {
            addCriterion("prod_bc is not null");
            return (Criteria) this;
        }

        public Criteria andProdBcEqualTo(String value) {
            addCriterion("prod_bc =", value, "prodBc");
            return (Criteria) this;
        }

        public Criteria andProdBcNotEqualTo(String value) {
            addCriterion("prod_bc <>", value, "prodBc");
            return (Criteria) this;
        }

        public Criteria andProdBcGreaterThan(String value) {
            addCriterion("prod_bc >", value, "prodBc");
            return (Criteria) this;
        }

        public Criteria andProdBcGreaterThanOrEqualTo(String value) {
            addCriterion("prod_bc >=", value, "prodBc");
            return (Criteria) this;
        }

        public Criteria andProdBcLessThan(String value) {
            addCriterion("prod_bc <", value, "prodBc");
            return (Criteria) this;
        }

        public Criteria andProdBcLessThanOrEqualTo(String value) {
            addCriterion("prod_bc <=", value, "prodBc");
            return (Criteria) this;
        }

        public Criteria andProdBcLike(String value) {
            addCriterion("prod_bc like", value, "prodBc");
            return (Criteria) this;
        }

        public Criteria andProdBcNotLike(String value) {
            addCriterion("prod_bc not like", value, "prodBc");
            return (Criteria) this;
        }

        public Criteria andProdBcIn(List<String> values) {
            addCriterion("prod_bc in", values, "prodBc");
            return (Criteria) this;
        }

        public Criteria andProdBcNotIn(List<String> values) {
            addCriterion("prod_bc not in", values, "prodBc");
            return (Criteria) this;
        }

        public Criteria andProdBcBetween(String value1, String value2) {
            addCriterion("prod_bc between", value1, value2, "prodBc");
            return (Criteria) this;
        }

        public Criteria andProdBcNotBetween(String value1, String value2) {
            addCriterion("prod_bc not between", value1, value2, "prodBc");
            return (Criteria) this;
        }

        public Criteria andProdFhckIsNull() {
            addCriterion("prod_fhck is null");
            return (Criteria) this;
        }

        public Criteria andProdFhckIsNotNull() {
            addCriterion("prod_fhck is not null");
            return (Criteria) this;
        }

        public Criteria andProdFhckEqualTo(String value) {
            addCriterion("prod_fhck =", value, "prodFhck");
            return (Criteria) this;
        }

        public Criteria andProdFhckNotEqualTo(String value) {
            addCriterion("prod_fhck <>", value, "prodFhck");
            return (Criteria) this;
        }

        public Criteria andProdFhckGreaterThan(String value) {
            addCriterion("prod_fhck >", value, "prodFhck");
            return (Criteria) this;
        }

        public Criteria andProdFhckGreaterThanOrEqualTo(String value) {
            addCriterion("prod_fhck >=", value, "prodFhck");
            return (Criteria) this;
        }

        public Criteria andProdFhckLessThan(String value) {
            addCriterion("prod_fhck <", value, "prodFhck");
            return (Criteria) this;
        }

        public Criteria andProdFhckLessThanOrEqualTo(String value) {
            addCriterion("prod_fhck <=", value, "prodFhck");
            return (Criteria) this;
        }

        public Criteria andProdFhckLike(String value) {
            addCriterion("prod_fhck like", value, "prodFhck");
            return (Criteria) this;
        }

        public Criteria andProdFhckNotLike(String value) {
            addCriterion("prod_fhck not like", value, "prodFhck");
            return (Criteria) this;
        }

        public Criteria andProdFhckIn(List<String> values) {
            addCriterion("prod_fhck in", values, "prodFhck");
            return (Criteria) this;
        }

        public Criteria andProdFhckNotIn(List<String> values) {
            addCriterion("prod_fhck not in", values, "prodFhck");
            return (Criteria) this;
        }

        public Criteria andProdFhckBetween(String value1, String value2) {
            addCriterion("prod_fhck between", value1, value2, "prodFhck");
            return (Criteria) this;
        }

        public Criteria andProdFhckNotBetween(String value1, String value2) {
            addCriterion("prod_fhck not between", value1, value2, "prodFhck");
            return (Criteria) this;
        }

        public Criteria andProdRucIsNull() {
            addCriterion("prod_ruc is null");
            return (Criteria) this;
        }

        public Criteria andProdRucIsNotNull() {
            addCriterion("prod_ruc is not null");
            return (Criteria) this;
        }

        public Criteria andProdRucEqualTo(String value) {
            addCriterion("prod_ruc =", value, "prodRuc");
            return (Criteria) this;
        }

        public Criteria andProdRucNotEqualTo(String value) {
            addCriterion("prod_ruc <>", value, "prodRuc");
            return (Criteria) this;
        }

        public Criteria andProdRucGreaterThan(String value) {
            addCriterion("prod_ruc >", value, "prodRuc");
            return (Criteria) this;
        }

        public Criteria andProdRucGreaterThanOrEqualTo(String value) {
            addCriterion("prod_ruc >=", value, "prodRuc");
            return (Criteria) this;
        }

        public Criteria andProdRucLessThan(String value) {
            addCriterion("prod_ruc <", value, "prodRuc");
            return (Criteria) this;
        }

        public Criteria andProdRucLessThanOrEqualTo(String value) {
            addCriterion("prod_ruc <=", value, "prodRuc");
            return (Criteria) this;
        }

        public Criteria andProdRucLike(String value) {
            addCriterion("prod_ruc like", value, "prodRuc");
            return (Criteria) this;
        }

        public Criteria andProdRucNotLike(String value) {
            addCriterion("prod_ruc not like", value, "prodRuc");
            return (Criteria) this;
        }

        public Criteria andProdRucIn(List<String> values) {
            addCriterion("prod_ruc in", values, "prodRuc");
            return (Criteria) this;
        }

        public Criteria andProdRucNotIn(List<String> values) {
            addCriterion("prod_ruc not in", values, "prodRuc");
            return (Criteria) this;
        }

        public Criteria andProdRucBetween(String value1, String value2) {
            addCriterion("prod_ruc between", value1, value2, "prodRuc");
            return (Criteria) this;
        }

        public Criteria andProdRucNotBetween(String value1, String value2) {
            addCriterion("prod_ruc not between", value1, value2, "prodRuc");
            return (Criteria) this;
        }

        public Criteria andProdWccplyIsNull() {
            addCriterion("prod_wccply is null");
            return (Criteria) this;
        }

        public Criteria andProdWccplyIsNotNull() {
            addCriterion("prod_wccply is not null");
            return (Criteria) this;
        }

        public Criteria andProdWccplyEqualTo(String value) {
            addCriterion("prod_wccply =", value, "prodWccply");
            return (Criteria) this;
        }

        public Criteria andProdWccplyNotEqualTo(String value) {
            addCriterion("prod_wccply <>", value, "prodWccply");
            return (Criteria) this;
        }

        public Criteria andProdWccplyGreaterThan(String value) {
            addCriterion("prod_wccply >", value, "prodWccply");
            return (Criteria) this;
        }

        public Criteria andProdWccplyGreaterThanOrEqualTo(String value) {
            addCriterion("prod_wccply >=", value, "prodWccply");
            return (Criteria) this;
        }

        public Criteria andProdWccplyLessThan(String value) {
            addCriterion("prod_wccply <", value, "prodWccply");
            return (Criteria) this;
        }

        public Criteria andProdWccplyLessThanOrEqualTo(String value) {
            addCriterion("prod_wccply <=", value, "prodWccply");
            return (Criteria) this;
        }

        public Criteria andProdWccplyLike(String value) {
            addCriterion("prod_wccply like", value, "prodWccply");
            return (Criteria) this;
        }

        public Criteria andProdWccplyNotLike(String value) {
            addCriterion("prod_wccply not like", value, "prodWccply");
            return (Criteria) this;
        }

        public Criteria andProdWccplyIn(List<String> values) {
            addCriterion("prod_wccply in", values, "prodWccply");
            return (Criteria) this;
        }

        public Criteria andProdWccplyNotIn(List<String> values) {
            addCriterion("prod_wccply not in", values, "prodWccply");
            return (Criteria) this;
        }

        public Criteria andProdWccplyBetween(String value1, String value2) {
            addCriterion("prod_wccply between", value1, value2, "prodWccply");
            return (Criteria) this;
        }

        public Criteria andProdWccplyNotBetween(String value1, String value2) {
            addCriterion("prod_wccply not between", value1, value2, "prodWccply");
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

        public Criteria andProdCheckuserIsNull() {
            addCriterion("prod_checkuser is null");
            return (Criteria) this;
        }

        public Criteria andProdCheckuserIsNotNull() {
            addCriterion("prod_checkuser is not null");
            return (Criteria) this;
        }

        public Criteria andProdCheckuserEqualTo(String value) {
            addCriterion("prod_checkuser =", value, "prodCheckuser");
            return (Criteria) this;
        }

        public Criteria andProdCheckuserNotEqualTo(String value) {
            addCriterion("prod_checkuser <>", value, "prodCheckuser");
            return (Criteria) this;
        }

        public Criteria andProdCheckuserGreaterThan(String value) {
            addCriterion("prod_checkuser >", value, "prodCheckuser");
            return (Criteria) this;
        }

        public Criteria andProdCheckuserGreaterThanOrEqualTo(String value) {
            addCriterion("prod_checkuser >=", value, "prodCheckuser");
            return (Criteria) this;
        }

        public Criteria andProdCheckuserLessThan(String value) {
            addCriterion("prod_checkuser <", value, "prodCheckuser");
            return (Criteria) this;
        }

        public Criteria andProdCheckuserLessThanOrEqualTo(String value) {
            addCriterion("prod_checkuser <=", value, "prodCheckuser");
            return (Criteria) this;
        }

        public Criteria andProdCheckuserLike(String value) {
            addCriterion("prod_checkuser like", value, "prodCheckuser");
            return (Criteria) this;
        }

        public Criteria andProdCheckuserNotLike(String value) {
            addCriterion("prod_checkuser not like", value, "prodCheckuser");
            return (Criteria) this;
        }

        public Criteria andProdCheckuserIn(List<String> values) {
            addCriterion("prod_checkuser in", values, "prodCheckuser");
            return (Criteria) this;
        }

        public Criteria andProdCheckuserNotIn(List<String> values) {
            addCriterion("prod_checkuser not in", values, "prodCheckuser");
            return (Criteria) this;
        }

        public Criteria andProdCheckuserBetween(String value1, String value2) {
            addCriterion("prod_checkuser between", value1, value2, "prodCheckuser");
            return (Criteria) this;
        }

        public Criteria andProdCheckuserNotBetween(String value1, String value2) {
            addCriterion("prod_checkuser not between", value1, value2, "prodCheckuser");
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
            addCriterion("crt_date =", value, "crtDate");
            return (Criteria) this;
        }

        public Criteria andCrtDateNotEqualTo(Date value) {
            addCriterion("crt_date <>", value, "crtDate");
            return (Criteria) this;
        }

        public Criteria andCrtDateGreaterThan(Date value) {
            addCriterion("crt_date >", value, "crtDate");
            return (Criteria) this;
        }

        public Criteria andCrtDateGreaterThanOrEqualTo(Date value) {
            addCriterion("crt_date >=", value, "crtDate");
            return (Criteria) this;
        }

        public Criteria andCrtDateLessThan(Date value) {
            addCriterion("crt_date <", value, "crtDate");
            return (Criteria) this;
        }

        public Criteria andCrtDateLessThanOrEqualTo(Date value) {
            addCriterion("crt_date <=", value, "crtDate");
            return (Criteria) this;
        }

        public Criteria andCrtDateIn(List<Date> values) {
            addCriterion("crt_date in", values, "crtDate");
            return (Criteria) this;
        }

        public Criteria andCrtDateNotIn(List<Date> values) {
            addCriterion("crt_date not in", values, "crtDate");
            return (Criteria) this;
        }

        public Criteria andCrtDateBetween(Date value1, Date value2) {
            addCriterion("crt_date between", value1, value2, "crtDate");
            return (Criteria) this;
        }

        public Criteria andCrtDateNotBetween(Date value1, Date value2) {
            addCriterion("crt_date not between", value1, value2, "crtDate");
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
            addCriterion("upd_date =", value, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateNotEqualTo(Date value) {
            addCriterion("upd_date <>", value, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateGreaterThan(Date value) {
            addCriterion("upd_date >", value, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateGreaterThanOrEqualTo(Date value) {
            addCriterion("upd_date >=", value, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateLessThan(Date value) {
            addCriterion("upd_date <", value, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateLessThanOrEqualTo(Date value) {
            addCriterion("upd_date <=", value, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateIn(List<Date> values) {
            addCriterion("upd_date in", values, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateNotIn(List<Date> values) {
            addCriterion("upd_date not in", values, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateBetween(Date value1, Date value2) {
            addCriterion("upd_date between", value1, value2, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateNotBetween(Date value1, Date value2) {
            addCriterion("upd_date not between", value1, value2, "updDate");
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