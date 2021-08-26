package com.bsl.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class BslSendCheckInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BslSendCheckInfoExample() {
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

        public Criteria andSendFlagIsNull() {
            addCriterion("send_flag is null");
            return (Criteria) this;
        }

        public Criteria andSendFlagIsNotNull() {
            addCriterion("send_flag is not null");
            return (Criteria) this;
        }

        public Criteria andSendFlagEqualTo(String value) {
            addCriterion("send_flag =", value, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendFlagNotEqualTo(String value) {
            addCriterion("send_flag <>", value, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendFlagGreaterThan(String value) {
            addCriterion("send_flag >", value, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendFlagGreaterThanOrEqualTo(String value) {
            addCriterion("send_flag >=", value, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendFlagLessThan(String value) {
            addCriterion("send_flag <", value, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendFlagLessThanOrEqualTo(String value) {
            addCriterion("send_flag <=", value, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendFlagLike(String value) {
            addCriterion("send_flag like", value, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendFlagNotLike(String value) {
            addCriterion("send_flag not like", value, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendFlagIn(List<String> values) {
            addCriterion("send_flag in", values, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendFlagNotIn(List<String> values) {
            addCriterion("send_flag not in", values, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendFlagBetween(String value1, String value2) {
            addCriterion("send_flag between", value1, value2, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendFlagNotBetween(String value1, String value2) {
            addCriterion("send_flag not between", value1, value2, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendResultIsNull() {
            addCriterion("send_result is null");
            return (Criteria) this;
        }

        public Criteria andSendResultIsNotNull() {
            addCriterion("send_result is not null");
            return (Criteria) this;
        }

        public Criteria andSendResultEqualTo(String value) {
            addCriterion("send_result =", value, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultNotEqualTo(String value) {
            addCriterion("send_result <>", value, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultGreaterThan(String value) {
            addCriterion("send_result >", value, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultGreaterThanOrEqualTo(String value) {
            addCriterion("send_result >=", value, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultLessThan(String value) {
            addCriterion("send_result <", value, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultLessThanOrEqualTo(String value) {
            addCriterion("send_result <=", value, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultLike(String value) {
            addCriterion("send_result like", value, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultNotLike(String value) {
            addCriterion("send_result not like", value, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultIn(List<String> values) {
            addCriterion("send_result in", values, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultNotIn(List<String> values) {
            addCriterion("send_result not in", values, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultBetween(String value1, String value2) {
            addCriterion("send_result between", value1, value2, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultNotBetween(String value1, String value2) {
            addCriterion("send_result not between", value1, value2, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendDateIsNull() {
            addCriterion("send_date is null");
            return (Criteria) this;
        }

        public Criteria andSendDateIsNotNull() {
            addCriterion("send_date is not null");
            return (Criteria) this;
        }

        public Criteria andSendDateEqualTo(Date value) {
            addCriterionForJDBCDate("send_date =", value, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("send_date <>", value, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateGreaterThan(Date value) {
            addCriterionForJDBCDate("send_date >", value, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("send_date >=", value, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateLessThan(Date value) {
            addCriterionForJDBCDate("send_date <", value, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("send_date <=", value, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateIn(List<Date> values) {
            addCriterionForJDBCDate("send_date in", values, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("send_date not in", values, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("send_date between", value1, value2, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("send_date not between", value1, value2, "sendDate");
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