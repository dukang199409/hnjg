package com.bsl.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class BslLunoQualityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BslLunoQualityExample() {
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

        public Criteria andLuIdIsNull() {
            addCriterion("lu_id is null");
            return (Criteria) this;
        }

        public Criteria andLuIdIsNotNull() {
            addCriterion("lu_id is not null");
            return (Criteria) this;
        }

        public Criteria andLuIdEqualTo(String value) {
            addCriterion("lu_id =", value, "luId");
            return (Criteria) this;
        }

        public Criteria andLuIdNotEqualTo(String value) {
            addCriterion("lu_id <>", value, "luId");
            return (Criteria) this;
        }

        public Criteria andLuIdGreaterThan(String value) {
            addCriterion("lu_id >", value, "luId");
            return (Criteria) this;
        }

        public Criteria andLuIdGreaterThanOrEqualTo(String value) {
            addCriterion("lu_id >=", value, "luId");
            return (Criteria) this;
        }

        public Criteria andLuIdLessThan(String value) {
            addCriterion("lu_id <", value, "luId");
            return (Criteria) this;
        }

        public Criteria andLuIdLessThanOrEqualTo(String value) {
            addCriterion("lu_id <=", value, "luId");
            return (Criteria) this;
        }

        public Criteria andLuIdLike(String value) {
            addCriterion("lu_id like", value, "luId");
            return (Criteria) this;
        }

        public Criteria andLuIdNotLike(String value) {
            addCriterion("lu_id not like", value, "luId");
            return (Criteria) this;
        }

        public Criteria andLuIdIn(List<String> values) {
            addCriterion("lu_id in", values, "luId");
            return (Criteria) this;
        }

        public Criteria andLuIdNotIn(List<String> values) {
            addCriterion("lu_id not in", values, "luId");
            return (Criteria) this;
        }

        public Criteria andLuIdBetween(String value1, String value2) {
            addCriterion("lu_id between", value1, value2, "luId");
            return (Criteria) this;
        }

        public Criteria andLuIdNotBetween(String value1, String value2) {
            addCriterion("lu_id not between", value1, value2, "luId");
            return (Criteria) this;
        }

        public Criteria andLuCompanyIsNull() {
            addCriterion("lu_company is null");
            return (Criteria) this;
        }

        public Criteria andLuCompanyIsNotNull() {
            addCriterion("lu_company is not null");
            return (Criteria) this;
        }

        public Criteria andLuCompanyEqualTo(String value) {
            addCriterion("lu_company =", value, "luCompany");
            return (Criteria) this;
        }

        public Criteria andLuCompanyNotEqualTo(String value) {
            addCriterion("lu_company <>", value, "luCompany");
            return (Criteria) this;
        }

        public Criteria andLuCompanyGreaterThan(String value) {
            addCriterion("lu_company >", value, "luCompany");
            return (Criteria) this;
        }

        public Criteria andLuCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("lu_company >=", value, "luCompany");
            return (Criteria) this;
        }

        public Criteria andLuCompanyLessThan(String value) {
            addCriterion("lu_company <", value, "luCompany");
            return (Criteria) this;
        }

        public Criteria andLuCompanyLessThanOrEqualTo(String value) {
            addCriterion("lu_company <=", value, "luCompany");
            return (Criteria) this;
        }

        public Criteria andLuCompanyLike(String value) {
            addCriterion("lu_company like", value, "luCompany");
            return (Criteria) this;
        }

        public Criteria andLuCompanyNotLike(String value) {
            addCriterion("lu_company not like", value, "luCompany");
            return (Criteria) this;
        }

        public Criteria andLuCompanyIn(List<String> values) {
            addCriterion("lu_company in", values, "luCompany");
            return (Criteria) this;
        }

        public Criteria andLuCompanyNotIn(List<String> values) {
            addCriterion("lu_company not in", values, "luCompany");
            return (Criteria) this;
        }

        public Criteria andLuCompanyBetween(String value1, String value2) {
            addCriterion("lu_company between", value1, value2, "luCompany");
            return (Criteria) this;
        }

        public Criteria andLuCompanyNotBetween(String value1, String value2) {
            addCriterion("lu_company not between", value1, value2, "luCompany");
            return (Criteria) this;
        }

        public Criteria andChemicalCIsNull() {
            addCriterion("chemical_C is null");
            return (Criteria) this;
        }

        public Criteria andChemicalCIsNotNull() {
            addCriterion("chemical_C is not null");
            return (Criteria) this;
        }

        public Criteria andChemicalCEqualTo(Integer value) {
            addCriterion("chemical_C =", value, "chemicalC");
            return (Criteria) this;
        }

        public Criteria andChemicalCNotEqualTo(Integer value) {
            addCriterion("chemical_C <>", value, "chemicalC");
            return (Criteria) this;
        }

        public Criteria andChemicalCGreaterThan(Integer value) {
            addCriterion("chemical_C >", value, "chemicalC");
            return (Criteria) this;
        }

        public Criteria andChemicalCGreaterThanOrEqualTo(Integer value) {
            addCriterion("chemical_C >=", value, "chemicalC");
            return (Criteria) this;
        }

        public Criteria andChemicalCLessThan(Integer value) {
            addCriterion("chemical_C <", value, "chemicalC");
            return (Criteria) this;
        }

        public Criteria andChemicalCLessThanOrEqualTo(Integer value) {
            addCriterion("chemical_C <=", value, "chemicalC");
            return (Criteria) this;
        }

        public Criteria andChemicalCIn(List<Integer> values) {
            addCriterion("chemical_C in", values, "chemicalC");
            return (Criteria) this;
        }

        public Criteria andChemicalCNotIn(List<Integer> values) {
            addCriterion("chemical_C not in", values, "chemicalC");
            return (Criteria) this;
        }

        public Criteria andChemicalCBetween(Integer value1, Integer value2) {
            addCriterion("chemical_C between", value1, value2, "chemicalC");
            return (Criteria) this;
        }

        public Criteria andChemicalCNotBetween(Integer value1, Integer value2) {
            addCriterion("chemical_C not between", value1, value2, "chemicalC");
            return (Criteria) this;
        }

        public Criteria andChemicalMnIsNull() {
            addCriterion("chemical_Mn is null");
            return (Criteria) this;
        }

        public Criteria andChemicalMnIsNotNull() {
            addCriterion("chemical_Mn is not null");
            return (Criteria) this;
        }

        public Criteria andChemicalMnEqualTo(Integer value) {
            addCriterion("chemical_Mn =", value, "chemicalMn");
            return (Criteria) this;
        }

        public Criteria andChemicalMnNotEqualTo(Integer value) {
            addCriterion("chemical_Mn <>", value, "chemicalMn");
            return (Criteria) this;
        }

        public Criteria andChemicalMnGreaterThan(Integer value) {
            addCriterion("chemical_Mn >", value, "chemicalMn");
            return (Criteria) this;
        }

        public Criteria andChemicalMnGreaterThanOrEqualTo(Integer value) {
            addCriterion("chemical_Mn >=", value, "chemicalMn");
            return (Criteria) this;
        }

        public Criteria andChemicalMnLessThan(Integer value) {
            addCriterion("chemical_Mn <", value, "chemicalMn");
            return (Criteria) this;
        }

        public Criteria andChemicalMnLessThanOrEqualTo(Integer value) {
            addCriterion("chemical_Mn <=", value, "chemicalMn");
            return (Criteria) this;
        }

        public Criteria andChemicalMnIn(List<Integer> values) {
            addCriterion("chemical_Mn in", values, "chemicalMn");
            return (Criteria) this;
        }

        public Criteria andChemicalMnNotIn(List<Integer> values) {
            addCriterion("chemical_Mn not in", values, "chemicalMn");
            return (Criteria) this;
        }

        public Criteria andChemicalMnBetween(Integer value1, Integer value2) {
            addCriterion("chemical_Mn between", value1, value2, "chemicalMn");
            return (Criteria) this;
        }

        public Criteria andChemicalMnNotBetween(Integer value1, Integer value2) {
            addCriterion("chemical_Mn not between", value1, value2, "chemicalMn");
            return (Criteria) this;
        }

        public Criteria andChemicalSiIsNull() {
            addCriterion("chemical_Si is null");
            return (Criteria) this;
        }

        public Criteria andChemicalSiIsNotNull() {
            addCriterion("chemical_Si is not null");
            return (Criteria) this;
        }

        public Criteria andChemicalSiEqualTo(Integer value) {
            addCriterion("chemical_Si =", value, "chemicalSi");
            return (Criteria) this;
        }

        public Criteria andChemicalSiNotEqualTo(Integer value) {
            addCriterion("chemical_Si <>", value, "chemicalSi");
            return (Criteria) this;
        }

        public Criteria andChemicalSiGreaterThan(Integer value) {
            addCriterion("chemical_Si >", value, "chemicalSi");
            return (Criteria) this;
        }

        public Criteria andChemicalSiGreaterThanOrEqualTo(Integer value) {
            addCriterion("chemical_Si >=", value, "chemicalSi");
            return (Criteria) this;
        }

        public Criteria andChemicalSiLessThan(Integer value) {
            addCriterion("chemical_Si <", value, "chemicalSi");
            return (Criteria) this;
        }

        public Criteria andChemicalSiLessThanOrEqualTo(Integer value) {
            addCriterion("chemical_Si <=", value, "chemicalSi");
            return (Criteria) this;
        }

        public Criteria andChemicalSiIn(List<Integer> values) {
            addCriterion("chemical_Si in", values, "chemicalSi");
            return (Criteria) this;
        }

        public Criteria andChemicalSiNotIn(List<Integer> values) {
            addCriterion("chemical_Si not in", values, "chemicalSi");
            return (Criteria) this;
        }

        public Criteria andChemicalSiBetween(Integer value1, Integer value2) {
            addCriterion("chemical_Si between", value1, value2, "chemicalSi");
            return (Criteria) this;
        }

        public Criteria andChemicalSiNotBetween(Integer value1, Integer value2) {
            addCriterion("chemical_Si not between", value1, value2, "chemicalSi");
            return (Criteria) this;
        }

        public Criteria andChemicalSIsNull() {
            addCriterion("chemical_S is null");
            return (Criteria) this;
        }

        public Criteria andChemicalSIsNotNull() {
            addCriterion("chemical_S is not null");
            return (Criteria) this;
        }

        public Criteria andChemicalSEqualTo(Integer value) {
            addCriterion("chemical_S =", value, "chemicalS");
            return (Criteria) this;
        }

        public Criteria andChemicalSNotEqualTo(Integer value) {
            addCriterion("chemical_S <>", value, "chemicalS");
            return (Criteria) this;
        }

        public Criteria andChemicalSGreaterThan(Integer value) {
            addCriterion("chemical_S >", value, "chemicalS");
            return (Criteria) this;
        }

        public Criteria andChemicalSGreaterThanOrEqualTo(Integer value) {
            addCriterion("chemical_S >=", value, "chemicalS");
            return (Criteria) this;
        }

        public Criteria andChemicalSLessThan(Integer value) {
            addCriterion("chemical_S <", value, "chemicalS");
            return (Criteria) this;
        }

        public Criteria andChemicalSLessThanOrEqualTo(Integer value) {
            addCriterion("chemical_S <=", value, "chemicalS");
            return (Criteria) this;
        }

        public Criteria andChemicalSIn(List<Integer> values) {
            addCriterion("chemical_S in", values, "chemicalS");
            return (Criteria) this;
        }

        public Criteria andChemicalSNotIn(List<Integer> values) {
            addCriterion("chemical_S not in", values, "chemicalS");
            return (Criteria) this;
        }

        public Criteria andChemicalSBetween(Integer value1, Integer value2) {
            addCriterion("chemical_S between", value1, value2, "chemicalS");
            return (Criteria) this;
        }

        public Criteria andChemicalSNotBetween(Integer value1, Integer value2) {
            addCriterion("chemical_S not between", value1, value2, "chemicalS");
            return (Criteria) this;
        }

        public Criteria andChemicalPIsNull() {
            addCriterion("chemical_P is null");
            return (Criteria) this;
        }

        public Criteria andChemicalPIsNotNull() {
            addCriterion("chemical_P is not null");
            return (Criteria) this;
        }

        public Criteria andChemicalPEqualTo(Integer value) {
            addCriterion("chemical_P =", value, "chemicalP");
            return (Criteria) this;
        }

        public Criteria andChemicalPNotEqualTo(Integer value) {
            addCriterion("chemical_P <>", value, "chemicalP");
            return (Criteria) this;
        }

        public Criteria andChemicalPGreaterThan(Integer value) {
            addCriterion("chemical_P >", value, "chemicalP");
            return (Criteria) this;
        }

        public Criteria andChemicalPGreaterThanOrEqualTo(Integer value) {
            addCriterion("chemical_P >=", value, "chemicalP");
            return (Criteria) this;
        }

        public Criteria andChemicalPLessThan(Integer value) {
            addCriterion("chemical_P <", value, "chemicalP");
            return (Criteria) this;
        }

        public Criteria andChemicalPLessThanOrEqualTo(Integer value) {
            addCriterion("chemical_P <=", value, "chemicalP");
            return (Criteria) this;
        }

        public Criteria andChemicalPIn(List<Integer> values) {
            addCriterion("chemical_P in", values, "chemicalP");
            return (Criteria) this;
        }

        public Criteria andChemicalPNotIn(List<Integer> values) {
            addCriterion("chemical_P not in", values, "chemicalP");
            return (Criteria) this;
        }

        public Criteria andChemicalPBetween(Integer value1, Integer value2) {
            addCriterion("chemical_P between", value1, value2, "chemicalP");
            return (Criteria) this;
        }

        public Criteria andChemicalPNotBetween(Integer value1, Integer value2) {
            addCriterion("chemical_P not between", value1, value2, "chemicalP");
            return (Criteria) this;
        }

        public Criteria andChemicalTiIsNull() {
            addCriterion("chemical_Ti is null");
            return (Criteria) this;
        }

        public Criteria andChemicalTiIsNotNull() {
            addCriterion("chemical_Ti is not null");
            return (Criteria) this;
        }

        public Criteria andChemicalTiEqualTo(Integer value) {
            addCriterion("chemical_Ti =", value, "chemicalTi");
            return (Criteria) this;
        }

        public Criteria andChemicalTiNotEqualTo(Integer value) {
            addCriterion("chemical_Ti <>", value, "chemicalTi");
            return (Criteria) this;
        }

        public Criteria andChemicalTiGreaterThan(Integer value) {
            addCriterion("chemical_Ti >", value, "chemicalTi");
            return (Criteria) this;
        }

        public Criteria andChemicalTiGreaterThanOrEqualTo(Integer value) {
            addCriterion("chemical_Ti >=", value, "chemicalTi");
            return (Criteria) this;
        }

        public Criteria andChemicalTiLessThan(Integer value) {
            addCriterion("chemical_Ti <", value, "chemicalTi");
            return (Criteria) this;
        }

        public Criteria andChemicalTiLessThanOrEqualTo(Integer value) {
            addCriterion("chemical_Ti <=", value, "chemicalTi");
            return (Criteria) this;
        }

        public Criteria andChemicalTiIn(List<Integer> values) {
            addCriterion("chemical_Ti in", values, "chemicalTi");
            return (Criteria) this;
        }

        public Criteria andChemicalTiNotIn(List<Integer> values) {
            addCriterion("chemical_Ti not in", values, "chemicalTi");
            return (Criteria) this;
        }

        public Criteria andChemicalTiBetween(Integer value1, Integer value2) {
            addCriterion("chemical_Ti between", value1, value2, "chemicalTi");
            return (Criteria) this;
        }

        public Criteria andChemicalTiNotBetween(Integer value1, Integer value2) {
            addCriterion("chemical_Ti not between", value1, value2, "chemicalTi");
            return (Criteria) this;
        }

        public Criteria andChemicalNiIsNull() {
            addCriterion("chemical_Ni is null");
            return (Criteria) this;
        }

        public Criteria andChemicalNiIsNotNull() {
            addCriterion("chemical_Ni is not null");
            return (Criteria) this;
        }

        public Criteria andChemicalNiEqualTo(Integer value) {
            addCriterion("chemical_Ni =", value, "chemicalNi");
            return (Criteria) this;
        }

        public Criteria andChemicalNiNotEqualTo(Integer value) {
            addCriterion("chemical_Ni <>", value, "chemicalNi");
            return (Criteria) this;
        }

        public Criteria andChemicalNiGreaterThan(Integer value) {
            addCriterion("chemical_Ni >", value, "chemicalNi");
            return (Criteria) this;
        }

        public Criteria andChemicalNiGreaterThanOrEqualTo(Integer value) {
            addCriterion("chemical_Ni >=", value, "chemicalNi");
            return (Criteria) this;
        }

        public Criteria andChemicalNiLessThan(Integer value) {
            addCriterion("chemical_Ni <", value, "chemicalNi");
            return (Criteria) this;
        }

        public Criteria andChemicalNiLessThanOrEqualTo(Integer value) {
            addCriterion("chemical_Ni <=", value, "chemicalNi");
            return (Criteria) this;
        }

        public Criteria andChemicalNiIn(List<Integer> values) {
            addCriterion("chemical_Ni in", values, "chemicalNi");
            return (Criteria) this;
        }

        public Criteria andChemicalNiNotIn(List<Integer> values) {
            addCriterion("chemical_Ni not in", values, "chemicalNi");
            return (Criteria) this;
        }

        public Criteria andChemicalNiBetween(Integer value1, Integer value2) {
            addCriterion("chemical_Ni between", value1, value2, "chemicalNi");
            return (Criteria) this;
        }

        public Criteria andChemicalNiNotBetween(Integer value1, Integer value2) {
            addCriterion("chemical_Ni not between", value1, value2, "chemicalNi");
            return (Criteria) this;
        }

        public Criteria andChemicalCrIsNull() {
            addCriterion("chemical_Cr is null");
            return (Criteria) this;
        }

        public Criteria andChemicalCrIsNotNull() {
            addCriterion("chemical_Cr is not null");
            return (Criteria) this;
        }

        public Criteria andChemicalCrEqualTo(Integer value) {
            addCriterion("chemical_Cr =", value, "chemicalCr");
            return (Criteria) this;
        }

        public Criteria andChemicalCrNotEqualTo(Integer value) {
            addCriterion("chemical_Cr <>", value, "chemicalCr");
            return (Criteria) this;
        }

        public Criteria andChemicalCrGreaterThan(Integer value) {
            addCriterion("chemical_Cr >", value, "chemicalCr");
            return (Criteria) this;
        }

        public Criteria andChemicalCrGreaterThanOrEqualTo(Integer value) {
            addCriterion("chemical_Cr >=", value, "chemicalCr");
            return (Criteria) this;
        }

        public Criteria andChemicalCrLessThan(Integer value) {
            addCriterion("chemical_Cr <", value, "chemicalCr");
            return (Criteria) this;
        }

        public Criteria andChemicalCrLessThanOrEqualTo(Integer value) {
            addCriterion("chemical_Cr <=", value, "chemicalCr");
            return (Criteria) this;
        }

        public Criteria andChemicalCrIn(List<Integer> values) {
            addCriterion("chemical_Cr in", values, "chemicalCr");
            return (Criteria) this;
        }

        public Criteria andChemicalCrNotIn(List<Integer> values) {
            addCriterion("chemical_Cr not in", values, "chemicalCr");
            return (Criteria) this;
        }

        public Criteria andChemicalCrBetween(Integer value1, Integer value2) {
            addCriterion("chemical_Cr between", value1, value2, "chemicalCr");
            return (Criteria) this;
        }

        public Criteria andChemicalCrNotBetween(Integer value1, Integer value2) {
            addCriterion("chemical_Cr not between", value1, value2, "chemicalCr");
            return (Criteria) this;
        }

        public Criteria andChemicalCuIsNull() {
            addCriterion("chemical_Cu is null");
            return (Criteria) this;
        }

        public Criteria andChemicalCuIsNotNull() {
            addCriterion("chemical_Cu is not null");
            return (Criteria) this;
        }

        public Criteria andChemicalCuEqualTo(Integer value) {
            addCriterion("chemical_Cu =", value, "chemicalCu");
            return (Criteria) this;
        }

        public Criteria andChemicalCuNotEqualTo(Integer value) {
            addCriterion("chemical_Cu <>", value, "chemicalCu");
            return (Criteria) this;
        }

        public Criteria andChemicalCuGreaterThan(Integer value) {
            addCriterion("chemical_Cu >", value, "chemicalCu");
            return (Criteria) this;
        }

        public Criteria andChemicalCuGreaterThanOrEqualTo(Integer value) {
            addCriterion("chemical_Cu >=", value, "chemicalCu");
            return (Criteria) this;
        }

        public Criteria andChemicalCuLessThan(Integer value) {
            addCriterion("chemical_Cu <", value, "chemicalCu");
            return (Criteria) this;
        }

        public Criteria andChemicalCuLessThanOrEqualTo(Integer value) {
            addCriterion("chemical_Cu <=", value, "chemicalCu");
            return (Criteria) this;
        }

        public Criteria andChemicalCuIn(List<Integer> values) {
            addCriterion("chemical_Cu in", values, "chemicalCu");
            return (Criteria) this;
        }

        public Criteria andChemicalCuNotIn(List<Integer> values) {
            addCriterion("chemical_Cu not in", values, "chemicalCu");
            return (Criteria) this;
        }

        public Criteria andChemicalCuBetween(Integer value1, Integer value2) {
            addCriterion("chemical_Cu between", value1, value2, "chemicalCu");
            return (Criteria) this;
        }

        public Criteria andChemicalCuNotBetween(Integer value1, Integer value2) {
            addCriterion("chemical_Cu not between", value1, value2, "chemicalCu");
            return (Criteria) this;
        }

        public Criteria andChemicalNbIsNull() {
            addCriterion("chemical_Nb is null");
            return (Criteria) this;
        }

        public Criteria andChemicalNbIsNotNull() {
            addCriterion("chemical_Nb is not null");
            return (Criteria) this;
        }

        public Criteria andChemicalNbEqualTo(Integer value) {
            addCriterion("chemical_Nb =", value, "chemicalNb");
            return (Criteria) this;
        }

        public Criteria andChemicalNbNotEqualTo(Integer value) {
            addCriterion("chemical_Nb <>", value, "chemicalNb");
            return (Criteria) this;
        }

        public Criteria andChemicalNbGreaterThan(Integer value) {
            addCriterion("chemical_Nb >", value, "chemicalNb");
            return (Criteria) this;
        }

        public Criteria andChemicalNbGreaterThanOrEqualTo(Integer value) {
            addCriterion("chemical_Nb >=", value, "chemicalNb");
            return (Criteria) this;
        }

        public Criteria andChemicalNbLessThan(Integer value) {
            addCriterion("chemical_Nb <", value, "chemicalNb");
            return (Criteria) this;
        }

        public Criteria andChemicalNbLessThanOrEqualTo(Integer value) {
            addCriterion("chemical_Nb <=", value, "chemicalNb");
            return (Criteria) this;
        }

        public Criteria andChemicalNbIn(List<Integer> values) {
            addCriterion("chemical_Nb in", values, "chemicalNb");
            return (Criteria) this;
        }

        public Criteria andChemicalNbNotIn(List<Integer> values) {
            addCriterion("chemical_Nb not in", values, "chemicalNb");
            return (Criteria) this;
        }

        public Criteria andChemicalNbBetween(Integer value1, Integer value2) {
            addCriterion("chemical_Nb between", value1, value2, "chemicalNb");
            return (Criteria) this;
        }

        public Criteria andChemicalNbNotBetween(Integer value1, Integer value2) {
            addCriterion("chemical_Nb not between", value1, value2, "chemicalNb");
            return (Criteria) this;
        }

        public Criteria andMechanicalSIsNull() {
            addCriterion("mechanical_s is null");
            return (Criteria) this;
        }

        public Criteria andMechanicalSIsNotNull() {
            addCriterion("mechanical_s is not null");
            return (Criteria) this;
        }

        public Criteria andMechanicalSEqualTo(Float value) {
            addCriterion("mechanical_s =", value, "mechanicalS");
            return (Criteria) this;
        }

        public Criteria andMechanicalSNotEqualTo(Float value) {
            addCriterion("mechanical_s <>", value, "mechanicalS");
            return (Criteria) this;
        }

        public Criteria andMechanicalSGreaterThan(Float value) {
            addCriterion("mechanical_s >", value, "mechanicalS");
            return (Criteria) this;
        }

        public Criteria andMechanicalSGreaterThanOrEqualTo(Float value) {
            addCriterion("mechanical_s >=", value, "mechanicalS");
            return (Criteria) this;
        }

        public Criteria andMechanicalSLessThan(Float value) {
            addCriterion("mechanical_s <", value, "mechanicalS");
            return (Criteria) this;
        }

        public Criteria andMechanicalSLessThanOrEqualTo(Float value) {
            addCriterion("mechanical_s <=", value, "mechanicalS");
            return (Criteria) this;
        }

        public Criteria andMechanicalSIn(List<Float> values) {
            addCriterion("mechanical_s in", values, "mechanicalS");
            return (Criteria) this;
        }

        public Criteria andMechanicalSNotIn(List<Float> values) {
            addCriterion("mechanical_s not in", values, "mechanicalS");
            return (Criteria) this;
        }

        public Criteria andMechanicalSBetween(Float value1, Float value2) {
            addCriterion("mechanical_s between", value1, value2, "mechanicalS");
            return (Criteria) this;
        }

        public Criteria andMechanicalSNotBetween(Float value1, Float value2) {
            addCriterion("mechanical_s not between", value1, value2, "mechanicalS");
            return (Criteria) this;
        }

        public Criteria andMechanicalBIsNull() {
            addCriterion("mechanical_b is null");
            return (Criteria) this;
        }

        public Criteria andMechanicalBIsNotNull() {
            addCriterion("mechanical_b is not null");
            return (Criteria) this;
        }

        public Criteria andMechanicalBEqualTo(Float value) {
            addCriterion("mechanical_b =", value, "mechanicalB");
            return (Criteria) this;
        }

        public Criteria andMechanicalBNotEqualTo(Float value) {
            addCriterion("mechanical_b <>", value, "mechanicalB");
            return (Criteria) this;
        }

        public Criteria andMechanicalBGreaterThan(Float value) {
            addCriterion("mechanical_b >", value, "mechanicalB");
            return (Criteria) this;
        }

        public Criteria andMechanicalBGreaterThanOrEqualTo(Float value) {
            addCriterion("mechanical_b >=", value, "mechanicalB");
            return (Criteria) this;
        }

        public Criteria andMechanicalBLessThan(Float value) {
            addCriterion("mechanical_b <", value, "mechanicalB");
            return (Criteria) this;
        }

        public Criteria andMechanicalBLessThanOrEqualTo(Float value) {
            addCriterion("mechanical_b <=", value, "mechanicalB");
            return (Criteria) this;
        }

        public Criteria andMechanicalBIn(List<Float> values) {
            addCriterion("mechanical_b in", values, "mechanicalB");
            return (Criteria) this;
        }

        public Criteria andMechanicalBNotIn(List<Float> values) {
            addCriterion("mechanical_b not in", values, "mechanicalB");
            return (Criteria) this;
        }

        public Criteria andMechanicalBBetween(Float value1, Float value2) {
            addCriterion("mechanical_b between", value1, value2, "mechanicalB");
            return (Criteria) this;
        }

        public Criteria andMechanicalBNotBetween(Float value1, Float value2) {
            addCriterion("mechanical_b not between", value1, value2, "mechanicalB");
            return (Criteria) this;
        }

        public Criteria andMechanicalLIsNull() {
            addCriterion("mechanical_l is null");
            return (Criteria) this;
        }

        public Criteria andMechanicalLIsNotNull() {
            addCriterion("mechanical_l is not null");
            return (Criteria) this;
        }

        public Criteria andMechanicalLEqualTo(Float value) {
            addCriterion("mechanical_l =", value, "mechanicalL");
            return (Criteria) this;
        }

        public Criteria andMechanicalLNotEqualTo(Float value) {
            addCriterion("mechanical_l <>", value, "mechanicalL");
            return (Criteria) this;
        }

        public Criteria andMechanicalLGreaterThan(Float value) {
            addCriterion("mechanical_l >", value, "mechanicalL");
            return (Criteria) this;
        }

        public Criteria andMechanicalLGreaterThanOrEqualTo(Float value) {
            addCriterion("mechanical_l >=", value, "mechanicalL");
            return (Criteria) this;
        }

        public Criteria andMechanicalLLessThan(Float value) {
            addCriterion("mechanical_l <", value, "mechanicalL");
            return (Criteria) this;
        }

        public Criteria andMechanicalLLessThanOrEqualTo(Float value) {
            addCriterion("mechanical_l <=", value, "mechanicalL");
            return (Criteria) this;
        }

        public Criteria andMechanicalLIn(List<Float> values) {
            addCriterion("mechanical_l in", values, "mechanicalL");
            return (Criteria) this;
        }

        public Criteria andMechanicalLNotIn(List<Float> values) {
            addCriterion("mechanical_l not in", values, "mechanicalL");
            return (Criteria) this;
        }

        public Criteria andMechanicalLBetween(Float value1, Float value2) {
            addCriterion("mechanical_l between", value1, value2, "mechanicalL");
            return (Criteria) this;
        }

        public Criteria andMechanicalLNotBetween(Float value1, Float value2) {
            addCriterion("mechanical_l not between", value1, value2, "mechanicalL");
            return (Criteria) this;
        }

        public Criteria andImpactTIsNull() {
            addCriterion("impact_T is null");
            return (Criteria) this;
        }

        public Criteria andImpactTIsNotNull() {
            addCriterion("impact_T is not null");
            return (Criteria) this;
        }

        public Criteria andImpactTEqualTo(Float value) {
            addCriterion("impact_T =", value, "impactT");
            return (Criteria) this;
        }

        public Criteria andImpactTNotEqualTo(Float value) {
            addCriterion("impact_T <>", value, "impactT");
            return (Criteria) this;
        }

        public Criteria andImpactTGreaterThan(Float value) {
            addCriterion("impact_T >", value, "impactT");
            return (Criteria) this;
        }

        public Criteria andImpactTGreaterThanOrEqualTo(Float value) {
            addCriterion("impact_T >=", value, "impactT");
            return (Criteria) this;
        }

        public Criteria andImpactTLessThan(Float value) {
            addCriterion("impact_T <", value, "impactT");
            return (Criteria) this;
        }

        public Criteria andImpactTLessThanOrEqualTo(Float value) {
            addCriterion("impact_T <=", value, "impactT");
            return (Criteria) this;
        }

        public Criteria andImpactTIn(List<Float> values) {
            addCriterion("impact_T in", values, "impactT");
            return (Criteria) this;
        }

        public Criteria andImpactTNotIn(List<Float> values) {
            addCriterion("impact_T not in", values, "impactT");
            return (Criteria) this;
        }

        public Criteria andImpactTBetween(Float value1, Float value2) {
            addCriterion("impact_T between", value1, value2, "impactT");
            return (Criteria) this;
        }

        public Criteria andImpactTNotBetween(Float value1, Float value2) {
            addCriterion("impact_T not between", value1, value2, "impactT");
            return (Criteria) this;
        }

        public Criteria andImpactN1IsNull() {
            addCriterion("impact_N1 is null");
            return (Criteria) this;
        }

        public Criteria andImpactN1IsNotNull() {
            addCriterion("impact_N1 is not null");
            return (Criteria) this;
        }

        public Criteria andImpactN1EqualTo(String value) {
            addCriterion("impact_N1 =", value, "impactN1");
            return (Criteria) this;
        }

        public Criteria andImpactN1NotEqualTo(String value) {
            addCriterion("impact_N1 <>", value, "impactN1");
            return (Criteria) this;
        }

        public Criteria andImpactN1GreaterThan(String value) {
            addCriterion("impact_N1 >", value, "impactN1");
            return (Criteria) this;
        }

        public Criteria andImpactN1GreaterThanOrEqualTo(String value) {
            addCriterion("impact_N1 >=", value, "impactN1");
            return (Criteria) this;
        }

        public Criteria andImpactN1LessThan(String value) {
            addCriterion("impact_N1 <", value, "impactN1");
            return (Criteria) this;
        }

        public Criteria andImpactN1LessThanOrEqualTo(String value) {
            addCriterion("impact_N1 <=", value, "impactN1");
            return (Criteria) this;
        }

        public Criteria andImpactN1Like(String value) {
            addCriterion("impact_N1 like", value, "impactN1");
            return (Criteria) this;
        }

        public Criteria andImpactN1NotLike(String value) {
            addCriterion("impact_N1 not like", value, "impactN1");
            return (Criteria) this;
        }

        public Criteria andImpactN1In(List<String> values) {
            addCriterion("impact_N1 in", values, "impactN1");
            return (Criteria) this;
        }

        public Criteria andImpactN1NotIn(List<String> values) {
            addCriterion("impact_N1 not in", values, "impactN1");
            return (Criteria) this;
        }

        public Criteria andImpactN1Between(String value1, String value2) {
            addCriterion("impact_N1 between", value1, value2, "impactN1");
            return (Criteria) this;
        }

        public Criteria andImpactN1NotBetween(String value1, String value2) {
            addCriterion("impact_N1 not between", value1, value2, "impactN1");
            return (Criteria) this;
        }

        public Criteria andImpactN2IsNull() {
            addCriterion("impact_N2 is null");
            return (Criteria) this;
        }

        public Criteria andImpactN2IsNotNull() {
            addCriterion("impact_N2 is not null");
            return (Criteria) this;
        }

        public Criteria andImpactN2EqualTo(String value) {
            addCriterion("impact_N2 =", value, "impactN2");
            return (Criteria) this;
        }

        public Criteria andImpactN2NotEqualTo(String value) {
            addCriterion("impact_N2 <>", value, "impactN2");
            return (Criteria) this;
        }

        public Criteria andImpactN2GreaterThan(String value) {
            addCriterion("impact_N2 >", value, "impactN2");
            return (Criteria) this;
        }

        public Criteria andImpactN2GreaterThanOrEqualTo(String value) {
            addCriterion("impact_N2 >=", value, "impactN2");
            return (Criteria) this;
        }

        public Criteria andImpactN2LessThan(String value) {
            addCriterion("impact_N2 <", value, "impactN2");
            return (Criteria) this;
        }

        public Criteria andImpactN2LessThanOrEqualTo(String value) {
            addCriterion("impact_N2 <=", value, "impactN2");
            return (Criteria) this;
        }

        public Criteria andImpactN2Like(String value) {
            addCriterion("impact_N2 like", value, "impactN2");
            return (Criteria) this;
        }

        public Criteria andImpactN2NotLike(String value) {
            addCriterion("impact_N2 not like", value, "impactN2");
            return (Criteria) this;
        }

        public Criteria andImpactN2In(List<String> values) {
            addCriterion("impact_N2 in", values, "impactN2");
            return (Criteria) this;
        }

        public Criteria andImpactN2NotIn(List<String> values) {
            addCriterion("impact_N2 not in", values, "impactN2");
            return (Criteria) this;
        }

        public Criteria andImpactN2Between(String value1, String value2) {
            addCriterion("impact_N2 between", value1, value2, "impactN2");
            return (Criteria) this;
        }

        public Criteria andImpactN2NotBetween(String value1, String value2) {
            addCriterion("impact_N2 not between", value1, value2, "impactN2");
            return (Criteria) this;
        }

        public Criteria andImpactN3IsNull() {
            addCriterion("impact_N3 is null");
            return (Criteria) this;
        }

        public Criteria andImpactN3IsNotNull() {
            addCriterion("impact_N3 is not null");
            return (Criteria) this;
        }

        public Criteria andImpactN3EqualTo(String value) {
            addCriterion("impact_N3 =", value, "impactN3");
            return (Criteria) this;
        }

        public Criteria andImpactN3NotEqualTo(String value) {
            addCriterion("impact_N3 <>", value, "impactN3");
            return (Criteria) this;
        }

        public Criteria andImpactN3GreaterThan(String value) {
            addCriterion("impact_N3 >", value, "impactN3");
            return (Criteria) this;
        }

        public Criteria andImpactN3GreaterThanOrEqualTo(String value) {
            addCriterion("impact_N3 >=", value, "impactN3");
            return (Criteria) this;
        }

        public Criteria andImpactN3LessThan(String value) {
            addCriterion("impact_N3 <", value, "impactN3");
            return (Criteria) this;
        }

        public Criteria andImpactN3LessThanOrEqualTo(String value) {
            addCriterion("impact_N3 <=", value, "impactN3");
            return (Criteria) this;
        }

        public Criteria andImpactN3Like(String value) {
            addCriterion("impact_N3 like", value, "impactN3");
            return (Criteria) this;
        }

        public Criteria andImpactN3NotLike(String value) {
            addCriterion("impact_N3 not like", value, "impactN3");
            return (Criteria) this;
        }

        public Criteria andImpactN3In(List<String> values) {
            addCriterion("impact_N3 in", values, "impactN3");
            return (Criteria) this;
        }

        public Criteria andImpactN3NotIn(List<String> values) {
            addCriterion("impact_N3 not in", values, "impactN3");
            return (Criteria) this;
        }

        public Criteria andImpactN3Between(String value1, String value2) {
            addCriterion("impact_N3 between", value1, value2, "impactN3");
            return (Criteria) this;
        }

        public Criteria andImpactN3NotBetween(String value1, String value2) {
            addCriterion("impact_N3 not between", value1, value2, "impactN3");
            return (Criteria) this;
        }

        public Criteria andBendwcIsNull() {
            addCriterion("bendWC is null");
            return (Criteria) this;
        }

        public Criteria andBendwcIsNotNull() {
            addCriterion("bendWC is not null");
            return (Criteria) this;
        }

        public Criteria andBendwcEqualTo(String value) {
            addCriterion("bendWC =", value, "bendwc");
            return (Criteria) this;
        }

        public Criteria andBendwcNotEqualTo(String value) {
            addCriterion("bendWC <>", value, "bendwc");
            return (Criteria) this;
        }

        public Criteria andBendwcGreaterThan(String value) {
            addCriterion("bendWC >", value, "bendwc");
            return (Criteria) this;
        }

        public Criteria andBendwcGreaterThanOrEqualTo(String value) {
            addCriterion("bendWC >=", value, "bendwc");
            return (Criteria) this;
        }

        public Criteria andBendwcLessThan(String value) {
            addCriterion("bendWC <", value, "bendwc");
            return (Criteria) this;
        }

        public Criteria andBendwcLessThanOrEqualTo(String value) {
            addCriterion("bendWC <=", value, "bendwc");
            return (Criteria) this;
        }

        public Criteria andBendwcLike(String value) {
            addCriterion("bendWC like", value, "bendwc");
            return (Criteria) this;
        }

        public Criteria andBendwcNotLike(String value) {
            addCriterion("bendWC not like", value, "bendwc");
            return (Criteria) this;
        }

        public Criteria andBendwcIn(List<String> values) {
            addCriterion("bendWC in", values, "bendwc");
            return (Criteria) this;
        }

        public Criteria andBendwcNotIn(List<String> values) {
            addCriterion("bendWC not in", values, "bendwc");
            return (Criteria) this;
        }

        public Criteria andBendwcBetween(String value1, String value2) {
            addCriterion("bendWC between", value1, value2, "bendwc");
            return (Criteria) this;
        }

        public Criteria andBendwcNotBetween(String value1, String value2) {
            addCriterion("bendWC not between", value1, value2, "bendwc");
            return (Criteria) this;
        }

        public Criteria andBendybIsNull() {
            addCriterion("bendYB is null");
            return (Criteria) this;
        }

        public Criteria andBendybIsNotNull() {
            addCriterion("bendYB is not null");
            return (Criteria) this;
        }

        public Criteria andBendybEqualTo(String value) {
            addCriterion("bendYB =", value, "bendyb");
            return (Criteria) this;
        }

        public Criteria andBendybNotEqualTo(String value) {
            addCriterion("bendYB <>", value, "bendyb");
            return (Criteria) this;
        }

        public Criteria andBendybGreaterThan(String value) {
            addCriterion("bendYB >", value, "bendyb");
            return (Criteria) this;
        }

        public Criteria andBendybGreaterThanOrEqualTo(String value) {
            addCriterion("bendYB >=", value, "bendyb");
            return (Criteria) this;
        }

        public Criteria andBendybLessThan(String value) {
            addCriterion("bendYB <", value, "bendyb");
            return (Criteria) this;
        }

        public Criteria andBendybLessThanOrEqualTo(String value) {
            addCriterion("bendYB <=", value, "bendyb");
            return (Criteria) this;
        }

        public Criteria andBendybLike(String value) {
            addCriterion("bendYB like", value, "bendyb");
            return (Criteria) this;
        }

        public Criteria andBendybNotLike(String value) {
            addCriterion("bendYB not like", value, "bendyb");
            return (Criteria) this;
        }

        public Criteria andBendybIn(List<String> values) {
            addCriterion("bendYB in", values, "bendyb");
            return (Criteria) this;
        }

        public Criteria andBendybNotIn(List<String> values) {
            addCriterion("bendYB not in", values, "bendyb");
            return (Criteria) this;
        }

        public Criteria andBendybBetween(String value1, String value2) {
            addCriterion("bendYB between", value1, value2, "bendyb");
            return (Criteria) this;
        }

        public Criteria andBendybNotBetween(String value1, String value2) {
            addCriterion("bendYB not between", value1, value2, "bendyb");
            return (Criteria) this;
        }

        public Criteria andInputuserIsNull() {
            addCriterion("inputUser is null");
            return (Criteria) this;
        }

        public Criteria andInputuserIsNotNull() {
            addCriterion("inputUser is not null");
            return (Criteria) this;
        }

        public Criteria andInputuserEqualTo(String value) {
            addCriterion("inputUser =", value, "inputuser");
            return (Criteria) this;
        }

        public Criteria andInputuserNotEqualTo(String value) {
            addCriterion("inputUser <>", value, "inputuser");
            return (Criteria) this;
        }

        public Criteria andInputuserGreaterThan(String value) {
            addCriterion("inputUser >", value, "inputuser");
            return (Criteria) this;
        }

        public Criteria andInputuserGreaterThanOrEqualTo(String value) {
            addCriterion("inputUser >=", value, "inputuser");
            return (Criteria) this;
        }

        public Criteria andInputuserLessThan(String value) {
            addCriterion("inputUser <", value, "inputuser");
            return (Criteria) this;
        }

        public Criteria andInputuserLessThanOrEqualTo(String value) {
            addCriterion("inputUser <=", value, "inputuser");
            return (Criteria) this;
        }

        public Criteria andInputuserLike(String value) {
            addCriterion("inputUser like", value, "inputuser");
            return (Criteria) this;
        }

        public Criteria andInputuserNotLike(String value) {
            addCriterion("inputUser not like", value, "inputuser");
            return (Criteria) this;
        }

        public Criteria andInputuserIn(List<String> values) {
            addCriterion("inputUser in", values, "inputuser");
            return (Criteria) this;
        }

        public Criteria andInputuserNotIn(List<String> values) {
            addCriterion("inputUser not in", values, "inputuser");
            return (Criteria) this;
        }

        public Criteria andInputuserBetween(String value1, String value2) {
            addCriterion("inputUser between", value1, value2, "inputuser");
            return (Criteria) this;
        }

        public Criteria andInputuserNotBetween(String value1, String value2) {
            addCriterion("inputUser not between", value1, value2, "inputuser");
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