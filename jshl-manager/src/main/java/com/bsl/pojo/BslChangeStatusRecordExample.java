package com.bsl.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class BslChangeStatusRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BslChangeStatusRecordExample() {
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

        public Criteria andChangeSernoIsNull() {
            addCriterion("change_serno is null");
            return (Criteria) this;
        }

        public Criteria andChangeSernoIsNotNull() {
            addCriterion("change_serno is not null");
            return (Criteria) this;
        }

        public Criteria andChangeSernoEqualTo(String value) {
            addCriterion("change_serno =", value, "changeSerno");
            return (Criteria) this;
        }

        public Criteria andChangeSernoNotEqualTo(String value) {
            addCriterion("change_serno <>", value, "changeSerno");
            return (Criteria) this;
        }

        public Criteria andChangeSernoGreaterThan(String value) {
            addCriterion("change_serno >", value, "changeSerno");
            return (Criteria) this;
        }

        public Criteria andChangeSernoGreaterThanOrEqualTo(String value) {
            addCriterion("change_serno >=", value, "changeSerno");
            return (Criteria) this;
        }

        public Criteria andChangeSernoLessThan(String value) {
            addCriterion("change_serno <", value, "changeSerno");
            return (Criteria) this;
        }

        public Criteria andChangeSernoLessThanOrEqualTo(String value) {
            addCriterion("change_serno <=", value, "changeSerno");
            return (Criteria) this;
        }

        public Criteria andChangeSernoLike(String value) {
            addCriterion("change_serno like", value, "changeSerno");
            return (Criteria) this;
        }

        public Criteria andChangeSernoNotLike(String value) {
            addCriterion("change_serno not like", value, "changeSerno");
            return (Criteria) this;
        }

        public Criteria andChangeSernoIn(List<String> values) {
            addCriterion("change_serno in", values, "changeSerno");
            return (Criteria) this;
        }

        public Criteria andChangeSernoNotIn(List<String> values) {
            addCriterion("change_serno not in", values, "changeSerno");
            return (Criteria) this;
        }

        public Criteria andChangeSernoBetween(String value1, String value2) {
            addCriterion("change_serno between", value1, value2, "changeSerno");
            return (Criteria) this;
        }

        public Criteria andChangeSernoNotBetween(String value1, String value2) {
            addCriterion("change_serno not between", value1, value2, "changeSerno");
            return (Criteria) this;
        }

        public Criteria andChangeTypeIsNull() {
            addCriterion("change_type is null");
            return (Criteria) this;
        }

        public Criteria andChangeTypeIsNotNull() {
            addCriterion("change_type is not null");
            return (Criteria) this;
        }

        public Criteria andChangeTypeEqualTo(String value) {
            addCriterion("change_type =", value, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeNotEqualTo(String value) {
            addCriterion("change_type <>", value, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeGreaterThan(String value) {
            addCriterion("change_type >", value, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("change_type >=", value, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeLessThan(String value) {
            addCriterion("change_type <", value, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeLessThanOrEqualTo(String value) {
            addCriterion("change_type <=", value, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeLike(String value) {
            addCriterion("change_type like", value, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeNotLike(String value) {
            addCriterion("change_type not like", value, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeIn(List<String> values) {
            addCriterion("change_type in", values, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeNotIn(List<String> values) {
            addCriterion("change_type not in", values, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeBetween(String value1, String value2) {
            addCriterion("change_type between", value1, value2, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeNotBetween(String value1, String value2) {
            addCriterion("change_type not between", value1, value2, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeProdIdIsNull() {
            addCriterion("change_prod_id is null");
            return (Criteria) this;
        }

        public Criteria andChangeProdIdIsNotNull() {
            addCriterion("change_prod_id is not null");
            return (Criteria) this;
        }

        public Criteria andChangeProdIdEqualTo(String value) {
            addCriterion("change_prod_id =", value, "changeProdId");
            return (Criteria) this;
        }

        public Criteria andChangeProdIdNotEqualTo(String value) {
            addCriterion("change_prod_id <>", value, "changeProdId");
            return (Criteria) this;
        }

        public Criteria andChangeProdIdGreaterThan(String value) {
            addCriterion("change_prod_id >", value, "changeProdId");
            return (Criteria) this;
        }

        public Criteria andChangeProdIdGreaterThanOrEqualTo(String value) {
            addCriterion("change_prod_id >=", value, "changeProdId");
            return (Criteria) this;
        }

        public Criteria andChangeProdIdLessThan(String value) {
            addCriterion("change_prod_id <", value, "changeProdId");
            return (Criteria) this;
        }

        public Criteria andChangeProdIdLessThanOrEqualTo(String value) {
            addCriterion("change_prod_id <=", value, "changeProdId");
            return (Criteria) this;
        }

        public Criteria andChangeProdIdLike(String value) {
            addCriterion("change_prod_id like", value, "changeProdId");
            return (Criteria) this;
        }

        public Criteria andChangeProdIdNotLike(String value) {
            addCriterion("change_prod_id not like", value, "changeProdId");
            return (Criteria) this;
        }

        public Criteria andChangeProdIdIn(List<String> values) {
            addCriterion("change_prod_id in", values, "changeProdId");
            return (Criteria) this;
        }

        public Criteria andChangeProdIdNotIn(List<String> values) {
            addCriterion("change_prod_id not in", values, "changeProdId");
            return (Criteria) this;
        }

        public Criteria andChangeProdIdBetween(String value1, String value2) {
            addCriterion("change_prod_id between", value1, value2, "changeProdId");
            return (Criteria) this;
        }

        public Criteria andChangeProdIdNotBetween(String value1, String value2) {
            addCriterion("change_prod_id not between", value1, value2, "changeProdId");
            return (Criteria) this;
        }

        public Criteria andBeforeStatusIsNull() {
            addCriterion("before_status is null");
            return (Criteria) this;
        }

        public Criteria andBeforeStatusIsNotNull() {
            addCriterion("before_status is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeStatusEqualTo(String value) {
            addCriterion("before_status =", value, "beforeStatus");
            return (Criteria) this;
        }

        public Criteria andBeforeStatusNotEqualTo(String value) {
            addCriterion("before_status <>", value, "beforeStatus");
            return (Criteria) this;
        }

        public Criteria andBeforeStatusGreaterThan(String value) {
            addCriterion("before_status >", value, "beforeStatus");
            return (Criteria) this;
        }

        public Criteria andBeforeStatusGreaterThanOrEqualTo(String value) {
            addCriterion("before_status >=", value, "beforeStatus");
            return (Criteria) this;
        }

        public Criteria andBeforeStatusLessThan(String value) {
            addCriterion("before_status <", value, "beforeStatus");
            return (Criteria) this;
        }

        public Criteria andBeforeStatusLessThanOrEqualTo(String value) {
            addCriterion("before_status <=", value, "beforeStatus");
            return (Criteria) this;
        }

        public Criteria andBeforeStatusLike(String value) {
            addCriterion("before_status like", value, "beforeStatus");
            return (Criteria) this;
        }

        public Criteria andBeforeStatusNotLike(String value) {
            addCriterion("before_status not like", value, "beforeStatus");
            return (Criteria) this;
        }

        public Criteria andBeforeStatusIn(List<String> values) {
            addCriterion("before_status in", values, "beforeStatus");
            return (Criteria) this;
        }

        public Criteria andBeforeStatusNotIn(List<String> values) {
            addCriterion("before_status not in", values, "beforeStatus");
            return (Criteria) this;
        }

        public Criteria andBeforeStatusBetween(String value1, String value2) {
            addCriterion("before_status between", value1, value2, "beforeStatus");
            return (Criteria) this;
        }

        public Criteria andBeforeStatusNotBetween(String value1, String value2) {
            addCriterion("before_status not between", value1, value2, "beforeStatus");
            return (Criteria) this;
        }

        public Criteria andAfterStatusIsNull() {
            addCriterion("after_status is null");
            return (Criteria) this;
        }

        public Criteria andAfterStatusIsNotNull() {
            addCriterion("after_status is not null");
            return (Criteria) this;
        }

        public Criteria andAfterStatusEqualTo(String value) {
            addCriterion("after_status =", value, "afterStatus");
            return (Criteria) this;
        }

        public Criteria andAfterStatusNotEqualTo(String value) {
            addCriterion("after_status <>", value, "afterStatus");
            return (Criteria) this;
        }

        public Criteria andAfterStatusGreaterThan(String value) {
            addCriterion("after_status >", value, "afterStatus");
            return (Criteria) this;
        }

        public Criteria andAfterStatusGreaterThanOrEqualTo(String value) {
            addCriterion("after_status >=", value, "afterStatus");
            return (Criteria) this;
        }

        public Criteria andAfterStatusLessThan(String value) {
            addCriterion("after_status <", value, "afterStatus");
            return (Criteria) this;
        }

        public Criteria andAfterStatusLessThanOrEqualTo(String value) {
            addCriterion("after_status <=", value, "afterStatus");
            return (Criteria) this;
        }

        public Criteria andAfterStatusLike(String value) {
            addCriterion("after_status like", value, "afterStatus");
            return (Criteria) this;
        }

        public Criteria andAfterStatusNotLike(String value) {
            addCriterion("after_status not like", value, "afterStatus");
            return (Criteria) this;
        }

        public Criteria andAfterStatusIn(List<String> values) {
            addCriterion("after_status in", values, "afterStatus");
            return (Criteria) this;
        }

        public Criteria andAfterStatusNotIn(List<String> values) {
            addCriterion("after_status not in", values, "afterStatus");
            return (Criteria) this;
        }

        public Criteria andAfterStatusBetween(String value1, String value2) {
            addCriterion("after_status between", value1, value2, "afterStatus");
            return (Criteria) this;
        }

        public Criteria andAfterStatusNotBetween(String value1, String value2) {
            addCriterion("after_status not between", value1, value2, "afterStatus");
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