package com.bsl.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class BslBsPlanInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BslBsPlanInfoExample() {
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

        public Criteria andBsIdIsNull() {
            addCriterion("bs_id is null");
            return (Criteria) this;
        }

        public Criteria andBsIdIsNotNull() {
            addCriterion("bs_id is not null");
            return (Criteria) this;
        }

        public Criteria andBsIdEqualTo(String value) {
            addCriterion("bs_id =", value, "bsId");
            return (Criteria) this;
        }

        public Criteria andBsIdNotEqualTo(String value) {
            addCriterion("bs_id <>", value, "bsId");
            return (Criteria) this;
        }

        public Criteria andBsIdGreaterThan(String value) {
            addCriterion("bs_id >", value, "bsId");
            return (Criteria) this;
        }

        public Criteria andBsIdGreaterThanOrEqualTo(String value) {
            addCriterion("bs_id >=", value, "bsId");
            return (Criteria) this;
        }

        public Criteria andBsIdLessThan(String value) {
            addCriterion("bs_id <", value, "bsId");
            return (Criteria) this;
        }

        public Criteria andBsIdLessThanOrEqualTo(String value) {
            addCriterion("bs_id <=", value, "bsId");
            return (Criteria) this;
        }

        public Criteria andBsIdLike(String value) {
            addCriterion("bs_id like", value, "bsId");
            return (Criteria) this;
        }

        public Criteria andBsIdNotLike(String value) {
            addCriterion("bs_id not like", value, "bsId");
            return (Criteria) this;
        }

        public Criteria andBsIdIn(List<String> values) {
            addCriterion("bs_id in", values, "bsId");
            return (Criteria) this;
        }

        public Criteria andBsIdNotIn(List<String> values) {
            addCriterion("bs_id not in", values, "bsId");
            return (Criteria) this;
        }

        public Criteria andBsIdBetween(String value1, String value2) {
            addCriterion("bs_id between", value1, value2, "bsId");
            return (Criteria) this;
        }

        public Criteria andBsIdNotBetween(String value1, String value2) {
            addCriterion("bs_id not between", value1, value2, "bsId");
            return (Criteria) this;
        }

        public Criteria andBsTypeIsNull() {
            addCriterion("bs_type is null");
            return (Criteria) this;
        }

        public Criteria andBsTypeIsNotNull() {
            addCriterion("bs_type is not null");
            return (Criteria) this;
        }

        public Criteria andBsTypeEqualTo(String value) {
            addCriterion("bs_type =", value, "bsType");
            return (Criteria) this;
        }

        public Criteria andBsTypeNotEqualTo(String value) {
            addCriterion("bs_type <>", value, "bsType");
            return (Criteria) this;
        }

        public Criteria andBsTypeGreaterThan(String value) {
            addCriterion("bs_type >", value, "bsType");
            return (Criteria) this;
        }

        public Criteria andBsTypeGreaterThanOrEqualTo(String value) {
            addCriterion("bs_type >=", value, "bsType");
            return (Criteria) this;
        }

        public Criteria andBsTypeLessThan(String value) {
            addCriterion("bs_type <", value, "bsType");
            return (Criteria) this;
        }

        public Criteria andBsTypeLessThanOrEqualTo(String value) {
            addCriterion("bs_type <=", value, "bsType");
            return (Criteria) this;
        }

        public Criteria andBsTypeLike(String value) {
            addCriterion("bs_type like", value, "bsType");
            return (Criteria) this;
        }

        public Criteria andBsTypeNotLike(String value) {
            addCriterion("bs_type not like", value, "bsType");
            return (Criteria) this;
        }

        public Criteria andBsTypeIn(List<String> values) {
            addCriterion("bs_type in", values, "bsType");
            return (Criteria) this;
        }

        public Criteria andBsTypeNotIn(List<String> values) {
            addCriterion("bs_type not in", values, "bsType");
            return (Criteria) this;
        }

        public Criteria andBsTypeBetween(String value1, String value2) {
            addCriterion("bs_type between", value1, value2, "bsType");
            return (Criteria) this;
        }

        public Criteria andBsTypeNotBetween(String value1, String value2) {
            addCriterion("bs_type not between", value1, value2, "bsType");
            return (Criteria) this;
        }

        public Criteria andBsCompanyIsNull() {
            addCriterion("bs_company is null");
            return (Criteria) this;
        }

        public Criteria andBsCompanyIsNotNull() {
            addCriterion("bs_company is not null");
            return (Criteria) this;
        }

        public Criteria andBsCompanyEqualTo(String value) {
            addCriterion("bs_company =", value, "bsCompany");
            return (Criteria) this;
        }

        public Criteria andBsCompanyNotEqualTo(String value) {
            addCriterion("bs_company <>", value, "bsCompany");
            return (Criteria) this;
        }

        public Criteria andBsCompanyGreaterThan(String value) {
            addCriterion("bs_company >", value, "bsCompany");
            return (Criteria) this;
        }

        public Criteria andBsCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("bs_company >=", value, "bsCompany");
            return (Criteria) this;
        }

        public Criteria andBsCompanyLessThan(String value) {
            addCriterion("bs_company <", value, "bsCompany");
            return (Criteria) this;
        }

        public Criteria andBsCompanyLessThanOrEqualTo(String value) {
            addCriterion("bs_company <=", value, "bsCompany");
            return (Criteria) this;
        }

        public Criteria andBsCompanyLike(String value) {
            addCriterion("bs_company like", value, "bsCompany");
            return (Criteria) this;
        }

        public Criteria andBsCompanyNotLike(String value) {
            addCriterion("bs_company not like", value, "bsCompany");
            return (Criteria) this;
        }

        public Criteria andBsCompanyIn(List<String> values) {
            addCriterion("bs_company in", values, "bsCompany");
            return (Criteria) this;
        }

        public Criteria andBsCompanyNotIn(List<String> values) {
            addCriterion("bs_company not in", values, "bsCompany");
            return (Criteria) this;
        }

        public Criteria andBsCompanyBetween(String value1, String value2) {
            addCriterion("bs_company between", value1, value2, "bsCompany");
            return (Criteria) this;
        }

        public Criteria andBsCompanyNotBetween(String value1, String value2) {
            addCriterion("bs_company not between", value1, value2, "bsCompany");
            return (Criteria) this;
        }

        public Criteria andBsCustomerIsNull() {
            addCriterion("bs_customer is null");
            return (Criteria) this;
        }

        public Criteria andBsCustomerIsNotNull() {
            addCriterion("bs_customer is not null");
            return (Criteria) this;
        }

        public Criteria andBsCustomerEqualTo(String value) {
            addCriterion("bs_customer =", value, "bsCustomer");
            return (Criteria) this;
        }

        public Criteria andBsCustomerNotEqualTo(String value) {
            addCriterion("bs_customer <>", value, "bsCustomer");
            return (Criteria) this;
        }

        public Criteria andBsCustomerGreaterThan(String value) {
            addCriterion("bs_customer >", value, "bsCustomer");
            return (Criteria) this;
        }

        public Criteria andBsCustomerGreaterThanOrEqualTo(String value) {
            addCriterion("bs_customer >=", value, "bsCustomer");
            return (Criteria) this;
        }

        public Criteria andBsCustomerLessThan(String value) {
            addCriterion("bs_customer <", value, "bsCustomer");
            return (Criteria) this;
        }

        public Criteria andBsCustomerLessThanOrEqualTo(String value) {
            addCriterion("bs_customer <=", value, "bsCustomer");
            return (Criteria) this;
        }

        public Criteria andBsCustomerLike(String value) {
            addCriterion("bs_customer like", value, "bsCustomer");
            return (Criteria) this;
        }

        public Criteria andBsCustomerNotLike(String value) {
            addCriterion("bs_customer not like", value, "bsCustomer");
            return (Criteria) this;
        }

        public Criteria andBsCustomerIn(List<String> values) {
            addCriterion("bs_customer in", values, "bsCustomer");
            return (Criteria) this;
        }

        public Criteria andBsCustomerNotIn(List<String> values) {
            addCriterion("bs_customer not in", values, "bsCustomer");
            return (Criteria) this;
        }

        public Criteria andBsCustomerBetween(String value1, String value2) {
            addCriterion("bs_customer between", value1, value2, "bsCustomer");
            return (Criteria) this;
        }

        public Criteria andBsCustomerNotBetween(String value1, String value2) {
            addCriterion("bs_customer not between", value1, value2, "bsCustomer");
            return (Criteria) this;
        }

        public Criteria andBsHasguaranteeIsNull() {
            addCriterion("bs_hasguarantee is null");
            return (Criteria) this;
        }

        public Criteria andBsHasguaranteeIsNotNull() {
            addCriterion("bs_hasguarantee is not null");
            return (Criteria) this;
        }

        public Criteria andBsHasguaranteeEqualTo(String value) {
            addCriterion("bs_hasguarantee =", value, "bsHasguarantee");
            return (Criteria) this;
        }

        public Criteria andBsHasguaranteeNotEqualTo(String value) {
            addCriterion("bs_hasguarantee <>", value, "bsHasguarantee");
            return (Criteria) this;
        }

        public Criteria andBsHasguaranteeGreaterThan(String value) {
            addCriterion("bs_hasguarantee >", value, "bsHasguarantee");
            return (Criteria) this;
        }

        public Criteria andBsHasguaranteeGreaterThanOrEqualTo(String value) {
            addCriterion("bs_hasguarantee >=", value, "bsHasguarantee");
            return (Criteria) this;
        }

        public Criteria andBsHasguaranteeLessThan(String value) {
            addCriterion("bs_hasguarantee <", value, "bsHasguarantee");
            return (Criteria) this;
        }

        public Criteria andBsHasguaranteeLessThanOrEqualTo(String value) {
            addCriterion("bs_hasguarantee <=", value, "bsHasguarantee");
            return (Criteria) this;
        }

        public Criteria andBsHasguaranteeLike(String value) {
            addCriterion("bs_hasguarantee like", value, "bsHasguarantee");
            return (Criteria) this;
        }

        public Criteria andBsHasguaranteeNotLike(String value) {
            addCriterion("bs_hasguarantee not like", value, "bsHasguarantee");
            return (Criteria) this;
        }

        public Criteria andBsHasguaranteeIn(List<String> values) {
            addCriterion("bs_hasguarantee in", values, "bsHasguarantee");
            return (Criteria) this;
        }

        public Criteria andBsHasguaranteeNotIn(List<String> values) {
            addCriterion("bs_hasguarantee not in", values, "bsHasguarantee");
            return (Criteria) this;
        }

        public Criteria andBsHasguaranteeBetween(String value1, String value2) {
            addCriterion("bs_hasguarantee between", value1, value2, "bsHasguarantee");
            return (Criteria) this;
        }

        public Criteria andBsHasguaranteeNotBetween(String value1, String value2) {
            addCriterion("bs_hasguarantee not between", value1, value2, "bsHasguarantee");
            return (Criteria) this;
        }

        public Criteria andBsAmtIsNull() {
            addCriterion("bs_amt is null");
            return (Criteria) this;
        }

        public Criteria andBsAmtIsNotNull() {
            addCriterion("bs_amt is not null");
            return (Criteria) this;
        }

        public Criteria andBsAmtEqualTo(Integer value) {
            addCriterion("bs_amt =", value, "bsAmt");
            return (Criteria) this;
        }

        public Criteria andBsAmtNotEqualTo(Integer value) {
            addCriterion("bs_amt <>", value, "bsAmt");
            return (Criteria) this;
        }

        public Criteria andBsAmtGreaterThan(Integer value) {
            addCriterion("bs_amt >", value, "bsAmt");
            return (Criteria) this;
        }

        public Criteria andBsAmtGreaterThanOrEqualTo(Integer value) {
            addCriterion("bs_amt >=", value, "bsAmt");
            return (Criteria) this;
        }

        public Criteria andBsAmtLessThan(Integer value) {
            addCriterion("bs_amt <", value, "bsAmt");
            return (Criteria) this;
        }

        public Criteria andBsAmtLessThanOrEqualTo(Integer value) {
            addCriterion("bs_amt <=", value, "bsAmt");
            return (Criteria) this;
        }

        public Criteria andBsAmtIn(List<Integer> values) {
            addCriterion("bs_amt in", values, "bsAmt");
            return (Criteria) this;
        }

        public Criteria andBsAmtNotIn(List<Integer> values) {
            addCriterion("bs_amt not in", values, "bsAmt");
            return (Criteria) this;
        }

        public Criteria andBsAmtBetween(Integer value1, Integer value2) {
            addCriterion("bs_amt between", value1, value2, "bsAmt");
            return (Criteria) this;
        }

        public Criteria andBsAmtNotBetween(Integer value1, Integer value2) {
            addCriterion("bs_amt not between", value1, value2, "bsAmt");
            return (Criteria) this;
        }

        public Criteria andBsWeightIsNull() {
            addCriterion("bs_weight is null");
            return (Criteria) this;
        }

        public Criteria andBsWeightIsNotNull() {
            addCriterion("bs_weight is not null");
            return (Criteria) this;
        }

        public Criteria andBsWeightEqualTo(Float value) {
            addCriterion("bs_weight =", value, "bsWeight");
            return (Criteria) this;
        }

        public Criteria andBsWeightNotEqualTo(Float value) {
            addCriterion("bs_weight <>", value, "bsWeight");
            return (Criteria) this;
        }

        public Criteria andBsWeightGreaterThan(Float value) {
            addCriterion("bs_weight >", value, "bsWeight");
            return (Criteria) this;
        }

        public Criteria andBsWeightGreaterThanOrEqualTo(Float value) {
            addCriterion("bs_weight >=", value, "bsWeight");
            return (Criteria) this;
        }

        public Criteria andBsWeightLessThan(Float value) {
            addCriterion("bs_weight <", value, "bsWeight");
            return (Criteria) this;
        }

        public Criteria andBsWeightLessThanOrEqualTo(Float value) {
            addCriterion("bs_weight <=", value, "bsWeight");
            return (Criteria) this;
        }

        public Criteria andBsWeightIn(List<Float> values) {
            addCriterion("bs_weight in", values, "bsWeight");
            return (Criteria) this;
        }

        public Criteria andBsWeightNotIn(List<Float> values) {
            addCriterion("bs_weight not in", values, "bsWeight");
            return (Criteria) this;
        }

        public Criteria andBsWeightBetween(Float value1, Float value2) {
            addCriterion("bs_weight between", value1, value2, "bsWeight");
            return (Criteria) this;
        }

        public Criteria andBsWeightNotBetween(Float value1, Float value2) {
            addCriterion("bs_weight not between", value1, value2, "bsWeight");
            return (Criteria) this;
        }

        public Criteria andBsFlagIsNull() {
            addCriterion("bs_flag is null");
            return (Criteria) this;
        }

        public Criteria andBsFlagIsNotNull() {
            addCriterion("bs_flag is not null");
            return (Criteria) this;
        }

        public Criteria andBsFlagEqualTo(String value) {
            addCriterion("bs_flag =", value, "bsFlag");
            return (Criteria) this;
        }

        public Criteria andBsFlagNotEqualTo(String value) {
            addCriterion("bs_flag <>", value, "bsFlag");
            return (Criteria) this;
        }

        public Criteria andBsFlagGreaterThan(String value) {
            addCriterion("bs_flag >", value, "bsFlag");
            return (Criteria) this;
        }

        public Criteria andBsFlagGreaterThanOrEqualTo(String value) {
            addCriterion("bs_flag >=", value, "bsFlag");
            return (Criteria) this;
        }

        public Criteria andBsFlagLessThan(String value) {
            addCriterion("bs_flag <", value, "bsFlag");
            return (Criteria) this;
        }

        public Criteria andBsFlagLessThanOrEqualTo(String value) {
            addCriterion("bs_flag <=", value, "bsFlag");
            return (Criteria) this;
        }

        public Criteria andBsFlagLike(String value) {
            addCriterion("bs_flag like", value, "bsFlag");
            return (Criteria) this;
        }

        public Criteria andBsFlagNotLike(String value) {
            addCriterion("bs_flag not like", value, "bsFlag");
            return (Criteria) this;
        }

        public Criteria andBsFlagIn(List<String> values) {
            addCriterion("bs_flag in", values, "bsFlag");
            return (Criteria) this;
        }

        public Criteria andBsFlagNotIn(List<String> values) {
            addCriterion("bs_flag not in", values, "bsFlag");
            return (Criteria) this;
        }

        public Criteria andBsFlagBetween(String value1, String value2) {
            addCriterion("bs_flag between", value1, value2, "bsFlag");
            return (Criteria) this;
        }

        public Criteria andBsFlagNotBetween(String value1, String value2) {
            addCriterion("bs_flag not between", value1, value2, "bsFlag");
            return (Criteria) this;
        }

        public Criteria andBsOrderNoIsNull() {
            addCriterion("bs_order_no is null");
            return (Criteria) this;
        }

        public Criteria andBsOrderNoIsNotNull() {
            addCriterion("bs_order_no is not null");
            return (Criteria) this;
        }

        public Criteria andBsOrderNoEqualTo(String value) {
            addCriterion("bs_order_no =", value, "bsOrderNo");
            return (Criteria) this;
        }

        public Criteria andBsOrderNoNotEqualTo(String value) {
            addCriterion("bs_order_no <>", value, "bsOrderNo");
            return (Criteria) this;
        }

        public Criteria andBsOrderNoGreaterThan(String value) {
            addCriterion("bs_order_no >", value, "bsOrderNo");
            return (Criteria) this;
        }

        public Criteria andBsOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("bs_order_no >=", value, "bsOrderNo");
            return (Criteria) this;
        }

        public Criteria andBsOrderNoLessThan(String value) {
            addCriterion("bs_order_no <", value, "bsOrderNo");
            return (Criteria) this;
        }

        public Criteria andBsOrderNoLessThanOrEqualTo(String value) {
            addCriterion("bs_order_no <=", value, "bsOrderNo");
            return (Criteria) this;
        }

        public Criteria andBsOrderNoLike(String value) {
            addCriterion("bs_order_no like", value, "bsOrderNo");
            return (Criteria) this;
        }

        public Criteria andBsOrderNoNotLike(String value) {
            addCriterion("bs_order_no not like", value, "bsOrderNo");
            return (Criteria) this;
        }

        public Criteria andBsOrderNoIn(List<String> values) {
            addCriterion("bs_order_no in", values, "bsOrderNo");
            return (Criteria) this;
        }

        public Criteria andBsOrderNoNotIn(List<String> values) {
            addCriterion("bs_order_no not in", values, "bsOrderNo");
            return (Criteria) this;
        }

        public Criteria andBsOrderNoBetween(String value1, String value2) {
            addCriterion("bs_order_no between", value1, value2, "bsOrderNo");
            return (Criteria) this;
        }

        public Criteria andBsOrderNoNotBetween(String value1, String value2) {
            addCriterion("bs_order_no not between", value1, value2, "bsOrderNo");
            return (Criteria) this;
        }

        public Criteria andBsTransportIsNull() {
            addCriterion("bs_transport is null");
            return (Criteria) this;
        }

        public Criteria andBsTransportIsNotNull() {
            addCriterion("bs_transport is not null");
            return (Criteria) this;
        }

        public Criteria andBsTransportEqualTo(String value) {
            addCriterion("bs_transport =", value, "bsTransport");
            return (Criteria) this;
        }

        public Criteria andBsTransportNotEqualTo(String value) {
            addCriterion("bs_transport <>", value, "bsTransport");
            return (Criteria) this;
        }

        public Criteria andBsTransportGreaterThan(String value) {
            addCriterion("bs_transport >", value, "bsTransport");
            return (Criteria) this;
        }

        public Criteria andBsTransportGreaterThanOrEqualTo(String value) {
            addCriterion("bs_transport >=", value, "bsTransport");
            return (Criteria) this;
        }

        public Criteria andBsTransportLessThan(String value) {
            addCriterion("bs_transport <", value, "bsTransport");
            return (Criteria) this;
        }

        public Criteria andBsTransportLessThanOrEqualTo(String value) {
            addCriterion("bs_transport <=", value, "bsTransport");
            return (Criteria) this;
        }

        public Criteria andBsTransportLike(String value) {
            addCriterion("bs_transport like", value, "bsTransport");
            return (Criteria) this;
        }

        public Criteria andBsTransportNotLike(String value) {
            addCriterion("bs_transport not like", value, "bsTransport");
            return (Criteria) this;
        }

        public Criteria andBsTransportIn(List<String> values) {
            addCriterion("bs_transport in", values, "bsTransport");
            return (Criteria) this;
        }

        public Criteria andBsTransportNotIn(List<String> values) {
            addCriterion("bs_transport not in", values, "bsTransport");
            return (Criteria) this;
        }

        public Criteria andBsTransportBetween(String value1, String value2) {
            addCriterion("bs_transport between", value1, value2, "bsTransport");
            return (Criteria) this;
        }

        public Criteria andBsTransportNotBetween(String value1, String value2) {
            addCriterion("bs_transport not between", value1, value2, "bsTransport");
            return (Criteria) this;
        }

        public Criteria andBsCarnoIsNull() {
            addCriterion("bs_carno is null");
            return (Criteria) this;
        }

        public Criteria andBsCarnoIsNotNull() {
            addCriterion("bs_carno is not null");
            return (Criteria) this;
        }

        public Criteria andBsCarnoEqualTo(String value) {
            addCriterion("bs_carno =", value, "bsCarno");
            return (Criteria) this;
        }

        public Criteria andBsCarnoNotEqualTo(String value) {
            addCriterion("bs_carno <>", value, "bsCarno");
            return (Criteria) this;
        }

        public Criteria andBsCarnoGreaterThan(String value) {
            addCriterion("bs_carno >", value, "bsCarno");
            return (Criteria) this;
        }

        public Criteria andBsCarnoGreaterThanOrEqualTo(String value) {
            addCriterion("bs_carno >=", value, "bsCarno");
            return (Criteria) this;
        }

        public Criteria andBsCarnoLessThan(String value) {
            addCriterion("bs_carno <", value, "bsCarno");
            return (Criteria) this;
        }

        public Criteria andBsCarnoLessThanOrEqualTo(String value) {
            addCriterion("bs_carno <=", value, "bsCarno");
            return (Criteria) this;
        }

        public Criteria andBsCarnoLike(String value) {
            addCriterion("bs_carno like", value, "bsCarno");
            return (Criteria) this;
        }

        public Criteria andBsCarnoNotLike(String value) {
            addCriterion("bs_carno not like", value, "bsCarno");
            return (Criteria) this;
        }

        public Criteria andBsCarnoIn(List<String> values) {
            addCriterion("bs_carno in", values, "bsCarno");
            return (Criteria) this;
        }

        public Criteria andBsCarnoNotIn(List<String> values) {
            addCriterion("bs_carno not in", values, "bsCarno");
            return (Criteria) this;
        }

        public Criteria andBsCarnoBetween(String value1, String value2) {
            addCriterion("bs_carno between", value1, value2, "bsCarno");
            return (Criteria) this;
        }

        public Criteria andBsCarnoNotBetween(String value1, String value2) {
            addCriterion("bs_carno not between", value1, value2, "bsCarno");
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

        public Criteria andBsStatusIsNull() {
            addCriterion("bs_status is null");
            return (Criteria) this;
        }

        public Criteria andBsStatusIsNotNull() {
            addCriterion("bs_status is not null");
            return (Criteria) this;
        }

        public Criteria andBsStatusEqualTo(String value) {
            addCriterion("bs_status =", value, "bsStatus");
            return (Criteria) this;
        }

        public Criteria andBsStatusNotEqualTo(String value) {
            addCriterion("bs_status <>", value, "bsStatus");
            return (Criteria) this;
        }

        public Criteria andBsStatusGreaterThan(String value) {
            addCriterion("bs_status >", value, "bsStatus");
            return (Criteria) this;
        }

        public Criteria andBsStatusGreaterThanOrEqualTo(String value) {
            addCriterion("bs_status >=", value, "bsStatus");
            return (Criteria) this;
        }

        public Criteria andBsStatusLessThan(String value) {
            addCriterion("bs_status <", value, "bsStatus");
            return (Criteria) this;
        }

        public Criteria andBsStatusLessThanOrEqualTo(String value) {
            addCriterion("bs_status <=", value, "bsStatus");
            return (Criteria) this;
        }

        public Criteria andBsStatusLike(String value) {
            addCriterion("bs_status like", value, "bsStatus");
            return (Criteria) this;
        }

        public Criteria andBsStatusNotLike(String value) {
            addCriterion("bs_status not like", value, "bsStatus");
            return (Criteria) this;
        }

        public Criteria andBsStatusIn(List<String> values) {
            addCriterion("bs_status in", values, "bsStatus");
            return (Criteria) this;
        }

        public Criteria andBsStatusNotIn(List<String> values) {
            addCriterion("bs_status not in", values, "bsStatus");
            return (Criteria) this;
        }

        public Criteria andBsStatusBetween(String value1, String value2) {
            addCriterion("bs_status between", value1, value2, "bsStatus");
            return (Criteria) this;
        }

        public Criteria andBsStatusNotBetween(String value1, String value2) {
            addCriterion("bs_status not between", value1, value2, "bsStatus");
            return (Criteria) this;
        }

        public Criteria andBsRelweightIsNull() {
            addCriterion("bs_relweight is null");
            return (Criteria) this;
        }

        public Criteria andBsRelweightIsNotNull() {
            addCriterion("bs_relweight is not null");
            return (Criteria) this;
        }

        public Criteria andBsRelweightEqualTo(Float value) {
            addCriterion("bs_relweight =", value, "bsRelweight");
            return (Criteria) this;
        }

        public Criteria andBsRelweightNotEqualTo(Float value) {
            addCriterion("bs_relweight <>", value, "bsRelweight");
            return (Criteria) this;
        }

        public Criteria andBsRelweightGreaterThan(Float value) {
            addCriterion("bs_relweight >", value, "bsRelweight");
            return (Criteria) this;
        }

        public Criteria andBsRelweightGreaterThanOrEqualTo(Float value) {
            addCriterion("bs_relweight >=", value, "bsRelweight");
            return (Criteria) this;
        }

        public Criteria andBsRelweightLessThan(Float value) {
            addCriterion("bs_relweight <", value, "bsRelweight");
            return (Criteria) this;
        }

        public Criteria andBsRelweightLessThanOrEqualTo(Float value) {
            addCriterion("bs_relweight <=", value, "bsRelweight");
            return (Criteria) this;
        }

        public Criteria andBsRelweightIn(List<Float> values) {
            addCriterion("bs_relweight in", values, "bsRelweight");
            return (Criteria) this;
        }

        public Criteria andBsRelweightNotIn(List<Float> values) {
            addCriterion("bs_relweight not in", values, "bsRelweight");
            return (Criteria) this;
        }

        public Criteria andBsRelweightBetween(Float value1, Float value2) {
            addCriterion("bs_relweight between", value1, value2, "bsRelweight");
            return (Criteria) this;
        }

        public Criteria andBsRelweightNotBetween(Float value1, Float value2) {
            addCriterion("bs_relweight not between", value1, value2, "bsRelweight");
            return (Criteria) this;
        }

        public Criteria andBsPriceIsNull() {
            addCriterion("bs_price is null");
            return (Criteria) this;
        }

        public Criteria andBsPriceIsNotNull() {
            addCriterion("bs_price is not null");
            return (Criteria) this;
        }

        public Criteria andBsPriceEqualTo(Float value) {
            addCriterion("bs_price =", value, "bsPrice");
            return (Criteria) this;
        }

        public Criteria andBsPriceNotEqualTo(Float value) {
            addCriterion("bs_price <>", value, "bsPrice");
            return (Criteria) this;
        }

        public Criteria andBsPriceGreaterThan(Float value) {
            addCriterion("bs_price >", value, "bsPrice");
            return (Criteria) this;
        }

        public Criteria andBsPriceGreaterThanOrEqualTo(Float value) {
            addCriterion("bs_price >=", value, "bsPrice");
            return (Criteria) this;
        }

        public Criteria andBsPriceLessThan(Float value) {
            addCriterion("bs_price <", value, "bsPrice");
            return (Criteria) this;
        }

        public Criteria andBsPriceLessThanOrEqualTo(Float value) {
            addCriterion("bs_price <=", value, "bsPrice");
            return (Criteria) this;
        }

        public Criteria andBsPriceIn(List<Float> values) {
            addCriterion("bs_price in", values, "bsPrice");
            return (Criteria) this;
        }

        public Criteria andBsPriceNotIn(List<Float> values) {
            addCriterion("bs_price not in", values, "bsPrice");
            return (Criteria) this;
        }

        public Criteria andBsPriceBetween(Float value1, Float value2) {
            addCriterion("bs_price between", value1, value2, "bsPrice");
            return (Criteria) this;
        }

        public Criteria andBsPriceNotBetween(Float value1, Float value2) {
            addCriterion("bs_price not between", value1, value2, "bsPrice");
            return (Criteria) this;
        }

        public Criteria andBsFbweightIsNull() {
            addCriterion("bs_fbweight is null");
            return (Criteria) this;
        }

        public Criteria andBsFbweightIsNotNull() {
            addCriterion("bs_fbweight is not null");
            return (Criteria) this;
        }

        public Criteria andBsFbweightEqualTo(Float value) {
            addCriterion("bs_fbweight =", value, "bsFbweight");
            return (Criteria) this;
        }

        public Criteria andBsFbweightNotEqualTo(Float value) {
            addCriterion("bs_fbweight <>", value, "bsFbweight");
            return (Criteria) this;
        }

        public Criteria andBsFbweightGreaterThan(Float value) {
            addCriterion("bs_fbweight >", value, "bsFbweight");
            return (Criteria) this;
        }

        public Criteria andBsFbweightGreaterThanOrEqualTo(Float value) {
            addCriterion("bs_fbweight >=", value, "bsFbweight");
            return (Criteria) this;
        }

        public Criteria andBsFbweightLessThan(Float value) {
            addCriterion("bs_fbweight <", value, "bsFbweight");
            return (Criteria) this;
        }

        public Criteria andBsFbweightLessThanOrEqualTo(Float value) {
            addCriterion("bs_fbweight <=", value, "bsFbweight");
            return (Criteria) this;
        }

        public Criteria andBsFbweightIn(List<Float> values) {
            addCriterion("bs_fbweight in", values, "bsFbweight");
            return (Criteria) this;
        }

        public Criteria andBsFbweightNotIn(List<Float> values) {
            addCriterion("bs_fbweight not in", values, "bsFbweight");
            return (Criteria) this;
        }

        public Criteria andBsFbweightBetween(Float value1, Float value2) {
            addCriterion("bs_fbweight between", value1, value2, "bsFbweight");
            return (Criteria) this;
        }

        public Criteria andBsFbweightNotBetween(Float value1, Float value2) {
            addCriterion("bs_fbweight not between", value1, value2, "bsFbweight");
            return (Criteria) this;
        }

        public Criteria andBsRetweightIsNull() {
            addCriterion("bs_retweight is null");
            return (Criteria) this;
        }

        public Criteria andBsRetweightIsNotNull() {
            addCriterion("bs_retweight is not null");
            return (Criteria) this;
        }

        public Criteria andBsRetweightEqualTo(Float value) {
            addCriterion("bs_retweight =", value, "bsRetweight");
            return (Criteria) this;
        }

        public Criteria andBsRetweightNotEqualTo(Float value) {
            addCriterion("bs_retweight <>", value, "bsRetweight");
            return (Criteria) this;
        }

        public Criteria andBsRetweightGreaterThan(Float value) {
            addCriterion("bs_retweight >", value, "bsRetweight");
            return (Criteria) this;
        }

        public Criteria andBsRetweightGreaterThanOrEqualTo(Float value) {
            addCriterion("bs_retweight >=", value, "bsRetweight");
            return (Criteria) this;
        }

        public Criteria andBsRetweightLessThan(Float value) {
            addCriterion("bs_retweight <", value, "bsRetweight");
            return (Criteria) this;
        }

        public Criteria andBsRetweightLessThanOrEqualTo(Float value) {
            addCriterion("bs_retweight <=", value, "bsRetweight");
            return (Criteria) this;
        }

        public Criteria andBsRetweightIn(List<Float> values) {
            addCriterion("bs_retweight in", values, "bsRetweight");
            return (Criteria) this;
        }

        public Criteria andBsRetweightNotIn(List<Float> values) {
            addCriterion("bs_retweight not in", values, "bsRetweight");
            return (Criteria) this;
        }

        public Criteria andBsRetweightBetween(Float value1, Float value2) {
            addCriterion("bs_retweight between", value1, value2, "bsRetweight");
            return (Criteria) this;
        }

        public Criteria andBsRetweightNotBetween(Float value1, Float value2) {
            addCriterion("bs_retweight not between", value1, value2, "bsRetweight");
            return (Criteria) this;
        }

        public Criteria andBsChaweightIsNull() {
            addCriterion("bs_chaweight is null");
            return (Criteria) this;
        }

        public Criteria andBsChaweightIsNotNull() {
            addCriterion("bs_chaweight is not null");
            return (Criteria) this;
        }

        public Criteria andBsChaweightEqualTo(Float value) {
            addCriterion("bs_chaweight =", value, "bsChaweight");
            return (Criteria) this;
        }

        public Criteria andBsChaweightNotEqualTo(Float value) {
            addCriterion("bs_chaweight <>", value, "bsChaweight");
            return (Criteria) this;
        }

        public Criteria andBsChaweightGreaterThan(Float value) {
            addCriterion("bs_chaweight >", value, "bsChaweight");
            return (Criteria) this;
        }

        public Criteria andBsChaweightGreaterThanOrEqualTo(Float value) {
            addCriterion("bs_chaweight >=", value, "bsChaweight");
            return (Criteria) this;
        }

        public Criteria andBsChaweightLessThan(Float value) {
            addCriterion("bs_chaweight <", value, "bsChaweight");
            return (Criteria) this;
        }

        public Criteria andBsChaweightLessThanOrEqualTo(Float value) {
            addCriterion("bs_chaweight <=", value, "bsChaweight");
            return (Criteria) this;
        }

        public Criteria andBsChaweightIn(List<Float> values) {
            addCriterion("bs_chaweight in", values, "bsChaweight");
            return (Criteria) this;
        }

        public Criteria andBsChaweightNotIn(List<Float> values) {
            addCriterion("bs_chaweight not in", values, "bsChaweight");
            return (Criteria) this;
        }

        public Criteria andBsChaweightBetween(Float value1, Float value2) {
            addCriterion("bs_chaweight between", value1, value2, "bsChaweight");
            return (Criteria) this;
        }

        public Criteria andBsChaweightNotBetween(Float value1, Float value2) {
            addCriterion("bs_chaweight not between", value1, value2, "bsChaweight");
            return (Criteria) this;
        }

        public Criteria andBsNormIsNull() {
            addCriterion("bs_norm is null");
            return (Criteria) this;
        }

        public Criteria andBsNormIsNotNull() {
            addCriterion("bs_norm is not null");
            return (Criteria) this;
        }

        public Criteria andBsNormEqualTo(String value) {
            addCriterion("bs_norm =", value, "bsNorm");
            return (Criteria) this;
        }

        public Criteria andBsNormNotEqualTo(String value) {
            addCriterion("bs_norm <>", value, "bsNorm");
            return (Criteria) this;
        }

        public Criteria andBsNormGreaterThan(String value) {
            addCriterion("bs_norm >", value, "bsNorm");
            return (Criteria) this;
        }

        public Criteria andBsNormGreaterThanOrEqualTo(String value) {
            addCriterion("bs_norm >=", value, "bsNorm");
            return (Criteria) this;
        }

        public Criteria andBsNormLessThan(String value) {
            addCriterion("bs_norm <", value, "bsNorm");
            return (Criteria) this;
        }

        public Criteria andBsNormLessThanOrEqualTo(String value) {
            addCriterion("bs_norm <=", value, "bsNorm");
            return (Criteria) this;
        }

        public Criteria andBsNormLike(String value) {
            addCriterion("bs_norm like", value, "bsNorm");
            return (Criteria) this;
        }

        public Criteria andBsNormNotLike(String value) {
            addCriterion("bs_norm not like", value, "bsNorm");
            return (Criteria) this;
        }

        public Criteria andBsNormIn(List<String> values) {
            addCriterion("bs_norm in", values, "bsNorm");
            return (Criteria) this;
        }

        public Criteria andBsNormNotIn(List<String> values) {
            addCriterion("bs_norm not in", values, "bsNorm");
            return (Criteria) this;
        }

        public Criteria andBsNormBetween(String value1, String value2) {
            addCriterion("bs_norm between", value1, value2, "bsNorm");
            return (Criteria) this;
        }

        public Criteria andBsNormNotBetween(String value1, String value2) {
            addCriterion("bs_norm not between", value1, value2, "bsNorm");
            return (Criteria) this;
        }

        public Criteria andBsArrdateIsNull() {
            addCriterion("bs_arrdate is null");
            return (Criteria) this;
        }

        public Criteria andBsArrdateIsNotNull() {
            addCriterion("bs_arrdate is not null");
            return (Criteria) this;
        }

        public Criteria andBsArrdateEqualTo(Date value) {
            addCriterionForJDBCDate("bs_arrdate =", value, "bsArrdate");
            return (Criteria) this;
        }

        public Criteria andBsArrdateNotEqualTo(Date value) {
            addCriterionForJDBCDate("bs_arrdate <>", value, "bsArrdate");
            return (Criteria) this;
        }

        public Criteria andBsArrdateGreaterThan(Date value) {
            addCriterionForJDBCDate("bs_arrdate >", value, "bsArrdate");
            return (Criteria) this;
        }

        public Criteria andBsArrdateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("bs_arrdate >=", value, "bsArrdate");
            return (Criteria) this;
        }

        public Criteria andBsArrdateLessThan(Date value) {
            addCriterionForJDBCDate("bs_arrdate <", value, "bsArrdate");
            return (Criteria) this;
        }

        public Criteria andBsArrdateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("bs_arrdate <=", value, "bsArrdate");
            return (Criteria) this;
        }

        public Criteria andBsArrdateIn(List<Date> values) {
            addCriterionForJDBCDate("bs_arrdate in", values, "bsArrdate");
            return (Criteria) this;
        }

        public Criteria andBsArrdateNotIn(List<Date> values) {
            addCriterionForJDBCDate("bs_arrdate not in", values, "bsArrdate");
            return (Criteria) this;
        }

        public Criteria andBsArrdateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("bs_arrdate between", value1, value2, "bsArrdate");
            return (Criteria) this;
        }

        public Criteria andBsArrdateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("bs_arrdate not between", value1, value2, "bsArrdate");
            return (Criteria) this;
        }

        public Criteria andBsJsnumIsNull() {
            addCriterion("bs_jsnum is null");
            return (Criteria) this;
        }

        public Criteria andBsJsnumIsNotNull() {
            addCriterion("bs_jsnum is not null");
            return (Criteria) this;
        }

        public Criteria andBsJsnumEqualTo(Integer value) {
            addCriterion("bs_jsnum =", value, "bsJsnum");
            return (Criteria) this;
        }

        public Criteria andBsJsnumNotEqualTo(Integer value) {
            addCriterion("bs_jsnum <>", value, "bsJsnum");
            return (Criteria) this;
        }

        public Criteria andBsJsnumGreaterThan(Integer value) {
            addCriterion("bs_jsnum >", value, "bsJsnum");
            return (Criteria) this;
        }

        public Criteria andBsJsnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("bs_jsnum >=", value, "bsJsnum");
            return (Criteria) this;
        }

        public Criteria andBsJsnumLessThan(Integer value) {
            addCriterion("bs_jsnum <", value, "bsJsnum");
            return (Criteria) this;
        }

        public Criteria andBsJsnumLessThanOrEqualTo(Integer value) {
            addCriterion("bs_jsnum <=", value, "bsJsnum");
            return (Criteria) this;
        }

        public Criteria andBsJsnumIn(List<Integer> values) {
            addCriterion("bs_jsnum in", values, "bsJsnum");
            return (Criteria) this;
        }

        public Criteria andBsJsnumNotIn(List<Integer> values) {
            addCriterion("bs_jsnum not in", values, "bsJsnum");
            return (Criteria) this;
        }

        public Criteria andBsJsnumBetween(Integer value1, Integer value2) {
            addCriterion("bs_jsnum between", value1, value2, "bsJsnum");
            return (Criteria) this;
        }

        public Criteria andBsJsnumNotBetween(Integer value1, Integer value2) {
            addCriterion("bs_jsnum not between", value1, value2, "bsJsnum");
            return (Criteria) this;
        }

        public Criteria andBsJsweightIsNull() {
            addCriterion("bs_jsweight is null");
            return (Criteria) this;
        }

        public Criteria andBsJsweightIsNotNull() {
            addCriterion("bs_jsweight is not null");
            return (Criteria) this;
        }

        public Criteria andBsJsweightEqualTo(Float value) {
            addCriterion("bs_jsweight =", value, "bsJsweight");
            return (Criteria) this;
        }

        public Criteria andBsJsweightNotEqualTo(Float value) {
            addCriterion("bs_jsweight <>", value, "bsJsweight");
            return (Criteria) this;
        }

        public Criteria andBsJsweightGreaterThan(Float value) {
            addCriterion("bs_jsweight >", value, "bsJsweight");
            return (Criteria) this;
        }

        public Criteria andBsJsweightGreaterThanOrEqualTo(Float value) {
            addCriterion("bs_jsweight >=", value, "bsJsweight");
            return (Criteria) this;
        }

        public Criteria andBsJsweightLessThan(Float value) {
            addCriterion("bs_jsweight <", value, "bsJsweight");
            return (Criteria) this;
        }

        public Criteria andBsJsweightLessThanOrEqualTo(Float value) {
            addCriterion("bs_jsweight <=", value, "bsJsweight");
            return (Criteria) this;
        }

        public Criteria andBsJsweightIn(List<Float> values) {
            addCriterion("bs_jsweight in", values, "bsJsweight");
            return (Criteria) this;
        }

        public Criteria andBsJsweightNotIn(List<Float> values) {
            addCriterion("bs_jsweight not in", values, "bsJsweight");
            return (Criteria) this;
        }

        public Criteria andBsJsweightBetween(Float value1, Float value2) {
            addCriterion("bs_jsweight between", value1, value2, "bsJsweight");
            return (Criteria) this;
        }

        public Criteria andBsJsweightNotBetween(Float value1, Float value2) {
            addCriterion("bs_jsweight not between", value1, value2, "bsJsweight");
            return (Criteria) this;
        }

        public Criteria andBsInputuserIsNull() {
            addCriterion("bs_inputuser is null");
            return (Criteria) this;
        }

        public Criteria andBsInputuserIsNotNull() {
            addCriterion("bs_inputuser is not null");
            return (Criteria) this;
        }

        public Criteria andBsInputuserEqualTo(String value) {
            addCriterion("bs_inputuser =", value, "bsInputuser");
            return (Criteria) this;
        }

        public Criteria andBsInputuserNotEqualTo(String value) {
            addCriterion("bs_inputuser <>", value, "bsInputuser");
            return (Criteria) this;
        }

        public Criteria andBsInputuserGreaterThan(String value) {
            addCriterion("bs_inputuser >", value, "bsInputuser");
            return (Criteria) this;
        }

        public Criteria andBsInputuserGreaterThanOrEqualTo(String value) {
            addCriterion("bs_inputuser >=", value, "bsInputuser");
            return (Criteria) this;
        }

        public Criteria andBsInputuserLessThan(String value) {
            addCriterion("bs_inputuser <", value, "bsInputuser");
            return (Criteria) this;
        }

        public Criteria andBsInputuserLessThanOrEqualTo(String value) {
            addCriterion("bs_inputuser <=", value, "bsInputuser");
            return (Criteria) this;
        }

        public Criteria andBsInputuserLike(String value) {
            addCriterion("bs_inputuser like", value, "bsInputuser");
            return (Criteria) this;
        }

        public Criteria andBsInputuserNotLike(String value) {
            addCriterion("bs_inputuser not like", value, "bsInputuser");
            return (Criteria) this;
        }

        public Criteria andBsInputuserIn(List<String> values) {
            addCriterion("bs_inputuser in", values, "bsInputuser");
            return (Criteria) this;
        }

        public Criteria andBsInputuserNotIn(List<String> values) {
            addCriterion("bs_inputuser not in", values, "bsInputuser");
            return (Criteria) this;
        }

        public Criteria andBsInputuserBetween(String value1, String value2) {
            addCriterion("bs_inputuser between", value1, value2, "bsInputuser");
            return (Criteria) this;
        }

        public Criteria andBsInputuserNotBetween(String value1, String value2) {
            addCriterion("bs_inputuser not between", value1, value2, "bsInputuser");
            return (Criteria) this;
        }

        public Criteria andBsCheckuserIsNull() {
            addCriterion("bs_checkuser is null");
            return (Criteria) this;
        }

        public Criteria andBsCheckuserIsNotNull() {
            addCriterion("bs_checkuser is not null");
            return (Criteria) this;
        }

        public Criteria andBsCheckuserEqualTo(String value) {
            addCriterion("bs_checkuser =", value, "bsCheckuser");
            return (Criteria) this;
        }

        public Criteria andBsCheckuserNotEqualTo(String value) {
            addCriterion("bs_checkuser <>", value, "bsCheckuser");
            return (Criteria) this;
        }

        public Criteria andBsCheckuserGreaterThan(String value) {
            addCriterion("bs_checkuser >", value, "bsCheckuser");
            return (Criteria) this;
        }

        public Criteria andBsCheckuserGreaterThanOrEqualTo(String value) {
            addCriterion("bs_checkuser >=", value, "bsCheckuser");
            return (Criteria) this;
        }

        public Criteria andBsCheckuserLessThan(String value) {
            addCriterion("bs_checkuser <", value, "bsCheckuser");
            return (Criteria) this;
        }

        public Criteria andBsCheckuserLessThanOrEqualTo(String value) {
            addCriterion("bs_checkuser <=", value, "bsCheckuser");
            return (Criteria) this;
        }

        public Criteria andBsCheckuserLike(String value) {
            addCriterion("bs_checkuser like", value, "bsCheckuser");
            return (Criteria) this;
        }

        public Criteria andBsCheckuserNotLike(String value) {
            addCriterion("bs_checkuser not like", value, "bsCheckuser");
            return (Criteria) this;
        }

        public Criteria andBsCheckuserIn(List<String> values) {
            addCriterion("bs_checkuser in", values, "bsCheckuser");
            return (Criteria) this;
        }

        public Criteria andBsCheckuserNotIn(List<String> values) {
            addCriterion("bs_checkuser not in", values, "bsCheckuser");
            return (Criteria) this;
        }

        public Criteria andBsCheckuserBetween(String value1, String value2) {
            addCriterion("bs_checkuser between", value1, value2, "bsCheckuser");
            return (Criteria) this;
        }

        public Criteria andBsCheckuserNotBetween(String value1, String value2) {
            addCriterion("bs_checkuser not between", value1, value2, "bsCheckuser");
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