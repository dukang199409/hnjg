package com.bsl.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class BslMakePlanInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BslMakePlanInfoExample() {
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

        public Criteria andPlanFlagIsNull() {
            addCriterion("plan_flag is null");
            return (Criteria) this;
        }

        public Criteria andPlanFlagIsNotNull() {
            addCriterion("plan_flag is not null");
            return (Criteria) this;
        }

        public Criteria andPlanFlagEqualTo(String value) {
            addCriterion("plan_flag =", value, "planFlag");
            return (Criteria) this;
        }

        public Criteria andPlanFlagNotEqualTo(String value) {
            addCriterion("plan_flag <>", value, "planFlag");
            return (Criteria) this;
        }

        public Criteria andPlanFlagGreaterThan(String value) {
            addCriterion("plan_flag >", value, "planFlag");
            return (Criteria) this;
        }

        public Criteria andPlanFlagGreaterThanOrEqualTo(String value) {
            addCriterion("plan_flag >=", value, "planFlag");
            return (Criteria) this;
        }

        public Criteria andPlanFlagLessThan(String value) {
            addCriterion("plan_flag <", value, "planFlag");
            return (Criteria) this;
        }

        public Criteria andPlanFlagLessThanOrEqualTo(String value) {
            addCriterion("plan_flag <=", value, "planFlag");
            return (Criteria) this;
        }

        public Criteria andPlanFlagLike(String value) {
            addCriterion("plan_flag like", value, "planFlag");
            return (Criteria) this;
        }

        public Criteria andPlanFlagNotLike(String value) {
            addCriterion("plan_flag not like", value, "planFlag");
            return (Criteria) this;
        }

        public Criteria andPlanFlagIn(List<String> values) {
            addCriterion("plan_flag in", values, "planFlag");
            return (Criteria) this;
        }

        public Criteria andPlanFlagNotIn(List<String> values) {
            addCriterion("plan_flag not in", values, "planFlag");
            return (Criteria) this;
        }

        public Criteria andPlanFlagBetween(String value1, String value2) {
            addCriterion("plan_flag between", value1, value2, "planFlag");
            return (Criteria) this;
        }

        public Criteria andPlanFlagNotBetween(String value1, String value2) {
            addCriterion("plan_flag not between", value1, value2, "planFlag");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNull() {
            addCriterion("company is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNotNull() {
            addCriterion("company is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyEqualTo(String value) {
            addCriterion("company =", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotEqualTo(String value) {
            addCriterion("company <>", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThan(String value) {
            addCriterion("company >", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("company >=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThan(String value) {
            addCriterion("company <", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThanOrEqualTo(String value) {
            addCriterion("company <=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLike(String value) {
            addCriterion("company like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotLike(String value) {
            addCriterion("company not like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyIn(List<String> values) {
            addCriterion("company in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotIn(List<String> values) {
            addCriterion("company not in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyBetween(String value1, String value2) {
            addCriterion("company between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotBetween(String value1, String value2) {
            addCriterion("company not between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andPlanDepartmentIsNull() {
            addCriterion("plan_department is null");
            return (Criteria) this;
        }

        public Criteria andPlanDepartmentIsNotNull() {
            addCriterion("plan_department is not null");
            return (Criteria) this;
        }

        public Criteria andPlanDepartmentEqualTo(String value) {
            addCriterion("plan_department =", value, "planDepartment");
            return (Criteria) this;
        }

        public Criteria andPlanDepartmentNotEqualTo(String value) {
            addCriterion("plan_department <>", value, "planDepartment");
            return (Criteria) this;
        }

        public Criteria andPlanDepartmentGreaterThan(String value) {
            addCriterion("plan_department >", value, "planDepartment");
            return (Criteria) this;
        }

        public Criteria andPlanDepartmentGreaterThanOrEqualTo(String value) {
            addCriterion("plan_department >=", value, "planDepartment");
            return (Criteria) this;
        }

        public Criteria andPlanDepartmentLessThan(String value) {
            addCriterion("plan_department <", value, "planDepartment");
            return (Criteria) this;
        }

        public Criteria andPlanDepartmentLessThanOrEqualTo(String value) {
            addCriterion("plan_department <=", value, "planDepartment");
            return (Criteria) this;
        }

        public Criteria andPlanDepartmentLike(String value) {
            addCriterion("plan_department like", value, "planDepartment");
            return (Criteria) this;
        }

        public Criteria andPlanDepartmentNotLike(String value) {
            addCriterion("plan_department not like", value, "planDepartment");
            return (Criteria) this;
        }

        public Criteria andPlanDepartmentIn(List<String> values) {
            addCriterion("plan_department in", values, "planDepartment");
            return (Criteria) this;
        }

        public Criteria andPlanDepartmentNotIn(List<String> values) {
            addCriterion("plan_department not in", values, "planDepartment");
            return (Criteria) this;
        }

        public Criteria andPlanDepartmentBetween(String value1, String value2) {
            addCriterion("plan_department between", value1, value2, "planDepartment");
            return (Criteria) this;
        }

        public Criteria andPlanDepartmentNotBetween(String value1, String value2) {
            addCriterion("plan_department not between", value1, value2, "planDepartment");
            return (Criteria) this;
        }

        public Criteria andPlanJzIsNull() {
            addCriterion("plan_jz is null");
            return (Criteria) this;
        }

        public Criteria andPlanJzIsNotNull() {
            addCriterion("plan_jz is not null");
            return (Criteria) this;
        }

        public Criteria andPlanJzEqualTo(String value) {
            addCriterion("plan_jz =", value, "planJz");
            return (Criteria) this;
        }

        public Criteria andPlanJzNotEqualTo(String value) {
            addCriterion("plan_jz <>", value, "planJz");
            return (Criteria) this;
        }

        public Criteria andPlanJzGreaterThan(String value) {
            addCriterion("plan_jz >", value, "planJz");
            return (Criteria) this;
        }

        public Criteria andPlanJzGreaterThanOrEqualTo(String value) {
            addCriterion("plan_jz >=", value, "planJz");
            return (Criteria) this;
        }

        public Criteria andPlanJzLessThan(String value) {
            addCriterion("plan_jz <", value, "planJz");
            return (Criteria) this;
        }

        public Criteria andPlanJzLessThanOrEqualTo(String value) {
            addCriterion("plan_jz <=", value, "planJz");
            return (Criteria) this;
        }

        public Criteria andPlanJzLike(String value) {
            addCriterion("plan_jz like", value, "planJz");
            return (Criteria) this;
        }

        public Criteria andPlanJzNotLike(String value) {
            addCriterion("plan_jz not like", value, "planJz");
            return (Criteria) this;
        }

        public Criteria andPlanJzIn(List<String> values) {
            addCriterion("plan_jz in", values, "planJz");
            return (Criteria) this;
        }

        public Criteria andPlanJzNotIn(List<String> values) {
            addCriterion("plan_jz not in", values, "planJz");
            return (Criteria) this;
        }

        public Criteria andPlanJzBetween(String value1, String value2) {
            addCriterion("plan_jz between", value1, value2, "planJz");
            return (Criteria) this;
        }

        public Criteria andPlanJzNotBetween(String value1, String value2) {
            addCriterion("plan_jz not between", value1, value2, "planJz");
            return (Criteria) this;
        }

        public Criteria andPlanLunoIsNull() {
            addCriterion("plan_luno is null");
            return (Criteria) this;
        }

        public Criteria andPlanLunoIsNotNull() {
            addCriterion("plan_luno is not null");
            return (Criteria) this;
        }

        public Criteria andPlanLunoEqualTo(String value) {
            addCriterion("plan_luno =", value, "planLuno");
            return (Criteria) this;
        }

        public Criteria andPlanLunoNotEqualTo(String value) {
            addCriterion("plan_luno <>", value, "planLuno");
            return (Criteria) this;
        }

        public Criteria andPlanLunoGreaterThan(String value) {
            addCriterion("plan_luno >", value, "planLuno");
            return (Criteria) this;
        }

        public Criteria andPlanLunoGreaterThanOrEqualTo(String value) {
            addCriterion("plan_luno >=", value, "planLuno");
            return (Criteria) this;
        }

        public Criteria andPlanLunoLessThan(String value) {
            addCriterion("plan_luno <", value, "planLuno");
            return (Criteria) this;
        }

        public Criteria andPlanLunoLessThanOrEqualTo(String value) {
            addCriterion("plan_luno <=", value, "planLuno");
            return (Criteria) this;
        }

        public Criteria andPlanLunoLike(String value) {
            addCriterion("plan_luno like", value, "planLuno");
            return (Criteria) this;
        }

        public Criteria andPlanLunoNotLike(String value) {
            addCriterion("plan_luno not like", value, "planLuno");
            return (Criteria) this;
        }

        public Criteria andPlanLunoIn(List<String> values) {
            addCriterion("plan_luno in", values, "planLuno");
            return (Criteria) this;
        }

        public Criteria andPlanLunoNotIn(List<String> values) {
            addCriterion("plan_luno not in", values, "planLuno");
            return (Criteria) this;
        }

        public Criteria andPlanLunoBetween(String value1, String value2) {
            addCriterion("plan_luno between", value1, value2, "planLuno");
            return (Criteria) this;
        }

        public Criteria andPlanLunoNotBetween(String value1, String value2) {
            addCriterion("plan_luno not between", value1, value2, "planLuno");
            return (Criteria) this;
        }

        public Criteria andPlanStatusIsNull() {
            addCriterion("plan_status is null");
            return (Criteria) this;
        }

        public Criteria andPlanStatusIsNotNull() {
            addCriterion("plan_status is not null");
            return (Criteria) this;
        }

        public Criteria andPlanStatusEqualTo(String value) {
            addCriterion("plan_status =", value, "planStatus");
            return (Criteria) this;
        }

        public Criteria andPlanStatusNotEqualTo(String value) {
            addCriterion("plan_status <>", value, "planStatus");
            return (Criteria) this;
        }

        public Criteria andPlanStatusGreaterThan(String value) {
            addCriterion("plan_status >", value, "planStatus");
            return (Criteria) this;
        }

        public Criteria andPlanStatusGreaterThanOrEqualTo(String value) {
            addCriterion("plan_status >=", value, "planStatus");
            return (Criteria) this;
        }

        public Criteria andPlanStatusLessThan(String value) {
            addCriterion("plan_status <", value, "planStatus");
            return (Criteria) this;
        }

        public Criteria andPlanStatusLessThanOrEqualTo(String value) {
            addCriterion("plan_status <=", value, "planStatus");
            return (Criteria) this;
        }

        public Criteria andPlanStatusLike(String value) {
            addCriterion("plan_status like", value, "planStatus");
            return (Criteria) this;
        }

        public Criteria andPlanStatusNotLike(String value) {
            addCriterion("plan_status not like", value, "planStatus");
            return (Criteria) this;
        }

        public Criteria andPlanStatusIn(List<String> values) {
            addCriterion("plan_status in", values, "planStatus");
            return (Criteria) this;
        }

        public Criteria andPlanStatusNotIn(List<String> values) {
            addCriterion("plan_status not in", values, "planStatus");
            return (Criteria) this;
        }

        public Criteria andPlanStatusBetween(String value1, String value2) {
            addCriterion("plan_status between", value1, value2, "planStatus");
            return (Criteria) this;
        }

        public Criteria andPlanStatusNotBetween(String value1, String value2) {
            addCriterion("plan_status not between", value1, value2, "planStatus");
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

        public Criteria andMakeTypeIsNull() {
            addCriterion("make_type is null");
            return (Criteria) this;
        }

        public Criteria andMakeTypeIsNotNull() {
            addCriterion("make_type is not null");
            return (Criteria) this;
        }

        public Criteria andMakeTypeEqualTo(String value) {
            addCriterion("make_type =", value, "makeType");
            return (Criteria) this;
        }

        public Criteria andMakeTypeNotEqualTo(String value) {
            addCriterion("make_type <>", value, "makeType");
            return (Criteria) this;
        }

        public Criteria andMakeTypeGreaterThan(String value) {
            addCriterion("make_type >", value, "makeType");
            return (Criteria) this;
        }

        public Criteria andMakeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("make_type >=", value, "makeType");
            return (Criteria) this;
        }

        public Criteria andMakeTypeLessThan(String value) {
            addCriterion("make_type <", value, "makeType");
            return (Criteria) this;
        }

        public Criteria andMakeTypeLessThanOrEqualTo(String value) {
            addCriterion("make_type <=", value, "makeType");
            return (Criteria) this;
        }

        public Criteria andMakeTypeLike(String value) {
            addCriterion("make_type like", value, "makeType");
            return (Criteria) this;
        }

        public Criteria andMakeTypeNotLike(String value) {
            addCriterion("make_type not like", value, "makeType");
            return (Criteria) this;
        }

        public Criteria andMakeTypeIn(List<String> values) {
            addCriterion("make_type in", values, "makeType");
            return (Criteria) this;
        }

        public Criteria andMakeTypeNotIn(List<String> values) {
            addCriterion("make_type not in", values, "makeType");
            return (Criteria) this;
        }

        public Criteria andMakeTypeBetween(String value1, String value2) {
            addCriterion("make_type between", value1, value2, "makeType");
            return (Criteria) this;
        }

        public Criteria andMakeTypeNotBetween(String value1, String value2) {
            addCriterion("make_type not between", value1, value2, "makeType");
            return (Criteria) this;
        }

        public Criteria andProdOrderIsNull() {
            addCriterion("prod_order is null");
            return (Criteria) this;
        }

        public Criteria andProdOrderIsNotNull() {
            addCriterion("prod_order is not null");
            return (Criteria) this;
        }

        public Criteria andProdOrderEqualTo(String value) {
            addCriterion("prod_order =", value, "prodOrder");
            return (Criteria) this;
        }

        public Criteria andProdOrderNotEqualTo(String value) {
            addCriterion("prod_order <>", value, "prodOrder");
            return (Criteria) this;
        }

        public Criteria andProdOrderGreaterThan(String value) {
            addCriterion("prod_order >", value, "prodOrder");
            return (Criteria) this;
        }

        public Criteria andProdOrderGreaterThanOrEqualTo(String value) {
            addCriterion("prod_order >=", value, "prodOrder");
            return (Criteria) this;
        }

        public Criteria andProdOrderLessThan(String value) {
            addCriterion("prod_order <", value, "prodOrder");
            return (Criteria) this;
        }

        public Criteria andProdOrderLessThanOrEqualTo(String value) {
            addCriterion("prod_order <=", value, "prodOrder");
            return (Criteria) this;
        }

        public Criteria andProdOrderLike(String value) {
            addCriterion("prod_order like", value, "prodOrder");
            return (Criteria) this;
        }

        public Criteria andProdOrderNotLike(String value) {
            addCriterion("prod_order not like", value, "prodOrder");
            return (Criteria) this;
        }

        public Criteria andProdOrderIn(List<String> values) {
            addCriterion("prod_order in", values, "prodOrder");
            return (Criteria) this;
        }

        public Criteria andProdOrderNotIn(List<String> values) {
            addCriterion("prod_order not in", values, "prodOrder");
            return (Criteria) this;
        }

        public Criteria andProdOrderBetween(String value1, String value2) {
            addCriterion("prod_order between", value1, value2, "prodOrder");
            return (Criteria) this;
        }

        public Criteria andProdOrderNotBetween(String value1, String value2) {
            addCriterion("prod_order not between", value1, value2, "prodOrder");
            return (Criteria) this;
        }

        public Criteria andMakeNameIsNull() {
            addCriterion("make_name is null");
            return (Criteria) this;
        }

        public Criteria andMakeNameIsNotNull() {
            addCriterion("make_name is not null");
            return (Criteria) this;
        }

        public Criteria andMakeNameEqualTo(String value) {
            addCriterion("make_name =", value, "makeName");
            return (Criteria) this;
        }

        public Criteria andMakeNameNotEqualTo(String value) {
            addCriterion("make_name <>", value, "makeName");
            return (Criteria) this;
        }

        public Criteria andMakeNameGreaterThan(String value) {
            addCriterion("make_name >", value, "makeName");
            return (Criteria) this;
        }

        public Criteria andMakeNameGreaterThanOrEqualTo(String value) {
            addCriterion("make_name >=", value, "makeName");
            return (Criteria) this;
        }

        public Criteria andMakeNameLessThan(String value) {
            addCriterion("make_name <", value, "makeName");
            return (Criteria) this;
        }

        public Criteria andMakeNameLessThanOrEqualTo(String value) {
            addCriterion("make_name <=", value, "makeName");
            return (Criteria) this;
        }

        public Criteria andMakeNameLike(String value) {
            addCriterion("make_name like", value, "makeName");
            return (Criteria) this;
        }

        public Criteria andMakeNameNotLike(String value) {
            addCriterion("make_name not like", value, "makeName");
            return (Criteria) this;
        }

        public Criteria andMakeNameIn(List<String> values) {
            addCriterion("make_name in", values, "makeName");
            return (Criteria) this;
        }

        public Criteria andMakeNameNotIn(List<String> values) {
            addCriterion("make_name not in", values, "makeName");
            return (Criteria) this;
        }

        public Criteria andMakeNameBetween(String value1, String value2) {
            addCriterion("make_name between", value1, value2, "makeName");
            return (Criteria) this;
        }

        public Criteria andMakeNameNotBetween(String value1, String value2) {
            addCriterion("make_name not between", value1, value2, "makeName");
            return (Criteria) this;
        }

        public Criteria andMakeProdNormIsNull() {
            addCriterion("make_prod_norm is null");
            return (Criteria) this;
        }

        public Criteria andMakeProdNormIsNotNull() {
            addCriterion("make_prod_norm is not null");
            return (Criteria) this;
        }

        public Criteria andMakeProdNormEqualTo(String value) {
            addCriterion("make_prod_norm =", value, "makeProdNorm");
            return (Criteria) this;
        }

        public Criteria andMakeProdNormNotEqualTo(String value) {
            addCriterion("make_prod_norm <>", value, "makeProdNorm");
            return (Criteria) this;
        }

        public Criteria andMakeProdNormGreaterThan(String value) {
            addCriterion("make_prod_norm >", value, "makeProdNorm");
            return (Criteria) this;
        }

        public Criteria andMakeProdNormGreaterThanOrEqualTo(String value) {
            addCriterion("make_prod_norm >=", value, "makeProdNorm");
            return (Criteria) this;
        }

        public Criteria andMakeProdNormLessThan(String value) {
            addCriterion("make_prod_norm <", value, "makeProdNorm");
            return (Criteria) this;
        }

        public Criteria andMakeProdNormLessThanOrEqualTo(String value) {
            addCriterion("make_prod_norm <=", value, "makeProdNorm");
            return (Criteria) this;
        }

        public Criteria andMakeProdNormLike(String value) {
            addCriterion("make_prod_norm like", value, "makeProdNorm");
            return (Criteria) this;
        }

        public Criteria andMakeProdNormNotLike(String value) {
            addCriterion("make_prod_norm not like", value, "makeProdNorm");
            return (Criteria) this;
        }

        public Criteria andMakeProdNormIn(List<String> values) {
            addCriterion("make_prod_norm in", values, "makeProdNorm");
            return (Criteria) this;
        }

        public Criteria andMakeProdNormNotIn(List<String> values) {
            addCriterion("make_prod_norm not in", values, "makeProdNorm");
            return (Criteria) this;
        }

        public Criteria andMakeProdNormBetween(String value1, String value2) {
            addCriterion("make_prod_norm between", value1, value2, "makeProdNorm");
            return (Criteria) this;
        }

        public Criteria andMakeProdNormNotBetween(String value1, String value2) {
            addCriterion("make_prod_norm not between", value1, value2, "makeProdNorm");
            return (Criteria) this;
        }

        public Criteria andCustomerIsNull() {
            addCriterion("customer is null");
            return (Criteria) this;
        }

        public Criteria andCustomerIsNotNull() {
            addCriterion("customer is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerEqualTo(String value) {
            addCriterion("customer =", value, "customer");
            return (Criteria) this;
        }

        public Criteria andCustomerNotEqualTo(String value) {
            addCriterion("customer <>", value, "customer");
            return (Criteria) this;
        }

        public Criteria andCustomerGreaterThan(String value) {
            addCriterion("customer >", value, "customer");
            return (Criteria) this;
        }

        public Criteria andCustomerGreaterThanOrEqualTo(String value) {
            addCriterion("customer >=", value, "customer");
            return (Criteria) this;
        }

        public Criteria andCustomerLessThan(String value) {
            addCriterion("customer <", value, "customer");
            return (Criteria) this;
        }

        public Criteria andCustomerLessThanOrEqualTo(String value) {
            addCriterion("customer <=", value, "customer");
            return (Criteria) this;
        }

        public Criteria andCustomerLike(String value) {
            addCriterion("customer like", value, "customer");
            return (Criteria) this;
        }

        public Criteria andCustomerNotLike(String value) {
            addCriterion("customer not like", value, "customer");
            return (Criteria) this;
        }

        public Criteria andCustomerIn(List<String> values) {
            addCriterion("customer in", values, "customer");
            return (Criteria) this;
        }

        public Criteria andCustomerNotIn(List<String> values) {
            addCriterion("customer not in", values, "customer");
            return (Criteria) this;
        }

        public Criteria andCustomerBetween(String value1, String value2) {
            addCriterion("customer between", value1, value2, "customer");
            return (Criteria) this;
        }

        public Criteria andCustomerNotBetween(String value1, String value2) {
            addCriterion("customer not between", value1, value2, "customer");
            return (Criteria) this;
        }

        public Criteria andAlreadyNumIsNull() {
            addCriterion("already_num is null");
            return (Criteria) this;
        }

        public Criteria andAlreadyNumIsNotNull() {
            addCriterion("already_num is not null");
            return (Criteria) this;
        }

        public Criteria andAlreadyNumEqualTo(Integer value) {
            addCriterion("already_num =", value, "alreadyNum");
            return (Criteria) this;
        }

        public Criteria andAlreadyNumNotEqualTo(Integer value) {
            addCriterion("already_num <>", value, "alreadyNum");
            return (Criteria) this;
        }

        public Criteria andAlreadyNumGreaterThan(Integer value) {
            addCriterion("already_num >", value, "alreadyNum");
            return (Criteria) this;
        }

        public Criteria andAlreadyNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("already_num >=", value, "alreadyNum");
            return (Criteria) this;
        }

        public Criteria andAlreadyNumLessThan(Integer value) {
            addCriterion("already_num <", value, "alreadyNum");
            return (Criteria) this;
        }

        public Criteria andAlreadyNumLessThanOrEqualTo(Integer value) {
            addCriterion("already_num <=", value, "alreadyNum");
            return (Criteria) this;
        }

        public Criteria andAlreadyNumIn(List<Integer> values) {
            addCriterion("already_num in", values, "alreadyNum");
            return (Criteria) this;
        }

        public Criteria andAlreadyNumNotIn(List<Integer> values) {
            addCriterion("already_num not in", values, "alreadyNum");
            return (Criteria) this;
        }

        public Criteria andAlreadyNumBetween(Integer value1, Integer value2) {
            addCriterion("already_num between", value1, value2, "alreadyNum");
            return (Criteria) this;
        }

        public Criteria andAlreadyNumNotBetween(Integer value1, Integer value2) {
            addCriterion("already_num not between", value1, value2, "alreadyNum");
            return (Criteria) this;
        }

        public Criteria andAlreadySumIsNull() {
            addCriterion("already_sum is null");
            return (Criteria) this;
        }

        public Criteria andAlreadySumIsNotNull() {
            addCriterion("already_sum is not null");
            return (Criteria) this;
        }

        public Criteria andAlreadySumEqualTo(Float value) {
            addCriterion("already_sum =", value, "alreadySum");
            return (Criteria) this;
        }

        public Criteria andAlreadySumNotEqualTo(Float value) {
            addCriterion("already_sum <>", value, "alreadySum");
            return (Criteria) this;
        }

        public Criteria andAlreadySumGreaterThan(Float value) {
            addCriterion("already_sum >", value, "alreadySum");
            return (Criteria) this;
        }

        public Criteria andAlreadySumGreaterThanOrEqualTo(Float value) {
            addCriterion("already_sum >=", value, "alreadySum");
            return (Criteria) this;
        }

        public Criteria andAlreadySumLessThan(Float value) {
            addCriterion("already_sum <", value, "alreadySum");
            return (Criteria) this;
        }

        public Criteria andAlreadySumLessThanOrEqualTo(Float value) {
            addCriterion("already_sum <=", value, "alreadySum");
            return (Criteria) this;
        }

        public Criteria andAlreadySumIn(List<Float> values) {
            addCriterion("already_sum in", values, "alreadySum");
            return (Criteria) this;
        }

        public Criteria andAlreadySumNotIn(List<Float> values) {
            addCriterion("already_sum not in", values, "alreadySum");
            return (Criteria) this;
        }

        public Criteria andAlreadySumBetween(Float value1, Float value2) {
            addCriterion("already_sum between", value1, value2, "alreadySum");
            return (Criteria) this;
        }

        public Criteria andAlreadySumNotBetween(Float value1, Float value2) {
            addCriterion("already_sum not between", value1, value2, "alreadySum");
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

        public Criteria andCheckuserIsNull() {
            addCriterion("checkuser is null");
            return (Criteria) this;
        }

        public Criteria andCheckuserIsNotNull() {
            addCriterion("checkuser is not null");
            return (Criteria) this;
        }

        public Criteria andCheckuserEqualTo(String value) {
            addCriterion("checkuser =", value, "checkuser");
            return (Criteria) this;
        }

        public Criteria andCheckuserNotEqualTo(String value) {
            addCriterion("checkuser <>", value, "checkuser");
            return (Criteria) this;
        }

        public Criteria andCheckuserGreaterThan(String value) {
            addCriterion("checkuser >", value, "checkuser");
            return (Criteria) this;
        }

        public Criteria andCheckuserGreaterThanOrEqualTo(String value) {
            addCriterion("checkuser >=", value, "checkuser");
            return (Criteria) this;
        }

        public Criteria andCheckuserLessThan(String value) {
            addCriterion("checkuser <", value, "checkuser");
            return (Criteria) this;
        }

        public Criteria andCheckuserLessThanOrEqualTo(String value) {
            addCriterion("checkuser <=", value, "checkuser");
            return (Criteria) this;
        }

        public Criteria andCheckuserLike(String value) {
            addCriterion("checkuser like", value, "checkuser");
            return (Criteria) this;
        }

        public Criteria andCheckuserNotLike(String value) {
            addCriterion("checkuser not like", value, "checkuser");
            return (Criteria) this;
        }

        public Criteria andCheckuserIn(List<String> values) {
            addCriterion("checkuser in", values, "checkuser");
            return (Criteria) this;
        }

        public Criteria andCheckuserNotIn(List<String> values) {
            addCriterion("checkuser not in", values, "checkuser");
            return (Criteria) this;
        }

        public Criteria andCheckuserBetween(String value1, String value2) {
            addCriterion("checkuser between", value1, value2, "checkuser");
            return (Criteria) this;
        }

        public Criteria andCheckuserNotBetween(String value1, String value2) {
            addCriterion("checkuser not between", value1, value2, "checkuser");
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
