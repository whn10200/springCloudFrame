package com.tuodao.bp.db.model.basic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SystemBasicDataExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public SystemBasicDataExample() {
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

    protected abstract static class GeneratedCriteria implements Serializable {
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

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andModuleIsNull() {
            addCriterion("MODULE is null");
            return (Criteria) this;
        }

        public Criteria andModuleIsNotNull() {
            addCriterion("MODULE is not null");
            return (Criteria) this;
        }

        public Criteria andModuleEqualTo(String value) {
            addCriterion("MODULE =", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleNotEqualTo(String value) {
            addCriterion("MODULE <>", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleGreaterThan(String value) {
            addCriterion("MODULE >", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleGreaterThanOrEqualTo(String value) {
            addCriterion("MODULE >=", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleLessThan(String value) {
            addCriterion("MODULE <", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleLessThanOrEqualTo(String value) {
            addCriterion("MODULE <=", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleLike(String value) {
            addCriterion("MODULE like", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleNotLike(String value) {
            addCriterion("MODULE not like", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleIn(List<String> values) {
            addCriterion("MODULE in", values, "module");
            return (Criteria) this;
        }

        public Criteria andModuleNotIn(List<String> values) {
            addCriterion("MODULE not in", values, "module");
            return (Criteria) this;
        }

        public Criteria andModuleBetween(String value1, String value2) {
            addCriterion("MODULE between", value1, value2, "module");
            return (Criteria) this;
        }

        public Criteria andModuleNotBetween(String value1, String value2) {
            addCriterion("MODULE not between", value1, value2, "module");
            return (Criteria) this;
        }

        public Criteria andConfKeyIsNull() {
            addCriterion("CONF_KEY is null");
            return (Criteria) this;
        }

        public Criteria andConfKeyIsNotNull() {
            addCriterion("CONF_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andConfKeyEqualTo(String value) {
            addCriterion("CONF_KEY =", value, "confKey");
            return (Criteria) this;
        }

        public Criteria andConfKeyNotEqualTo(String value) {
            addCriterion("CONF_KEY <>", value, "confKey");
            return (Criteria) this;
        }

        public Criteria andConfKeyGreaterThan(String value) {
            addCriterion("CONF_KEY >", value, "confKey");
            return (Criteria) this;
        }

        public Criteria andConfKeyGreaterThanOrEqualTo(String value) {
            addCriterion("CONF_KEY >=", value, "confKey");
            return (Criteria) this;
        }

        public Criteria andConfKeyLessThan(String value) {
            addCriterion("CONF_KEY <", value, "confKey");
            return (Criteria) this;
        }

        public Criteria andConfKeyLessThanOrEqualTo(String value) {
            addCriterion("CONF_KEY <=", value, "confKey");
            return (Criteria) this;
        }

        public Criteria andConfKeyLike(String value) {
            addCriterion("CONF_KEY like", value, "confKey");
            return (Criteria) this;
        }

        public Criteria andConfKeyNotLike(String value) {
            addCriterion("CONF_KEY not like", value, "confKey");
            return (Criteria) this;
        }

        public Criteria andConfKeyIn(List<String> values) {
            addCriterion("CONF_KEY in", values, "confKey");
            return (Criteria) this;
        }

        public Criteria andConfKeyNotIn(List<String> values) {
            addCriterion("CONF_KEY not in", values, "confKey");
            return (Criteria) this;
        }

        public Criteria andConfKeyBetween(String value1, String value2) {
            addCriterion("CONF_KEY between", value1, value2, "confKey");
            return (Criteria) this;
        }

        public Criteria andConfKeyNotBetween(String value1, String value2) {
            addCriterion("CONF_KEY not between", value1, value2, "confKey");
            return (Criteria) this;
        }

        public Criteria andConfValueIsNull() {
            addCriterion("CONF_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andConfValueIsNotNull() {
            addCriterion("CONF_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andConfValueEqualTo(String value) {
            addCriterion("CONF_VALUE =", value, "confValue");
            return (Criteria) this;
        }

        public Criteria andConfValueNotEqualTo(String value) {
            addCriterion("CONF_VALUE <>", value, "confValue");
            return (Criteria) this;
        }

        public Criteria andConfValueGreaterThan(String value) {
            addCriterion("CONF_VALUE >", value, "confValue");
            return (Criteria) this;
        }

        public Criteria andConfValueGreaterThanOrEqualTo(String value) {
            addCriterion("CONF_VALUE >=", value, "confValue");
            return (Criteria) this;
        }

        public Criteria andConfValueLessThan(String value) {
            addCriterion("CONF_VALUE <", value, "confValue");
            return (Criteria) this;
        }

        public Criteria andConfValueLessThanOrEqualTo(String value) {
            addCriterion("CONF_VALUE <=", value, "confValue");
            return (Criteria) this;
        }

        public Criteria andConfValueLike(String value) {
            addCriterion("CONF_VALUE like", value, "confValue");
            return (Criteria) this;
        }

        public Criteria andConfValueNotLike(String value) {
            addCriterion("CONF_VALUE not like", value, "confValue");
            return (Criteria) this;
        }

        public Criteria andConfValueIn(List<String> values) {
            addCriterion("CONF_VALUE in", values, "confValue");
            return (Criteria) this;
        }

        public Criteria andConfValueNotIn(List<String> values) {
            addCriterion("CONF_VALUE not in", values, "confValue");
            return (Criteria) this;
        }

        public Criteria andConfValueBetween(String value1, String value2) {
            addCriterion("CONF_VALUE between", value1, value2, "confValue");
            return (Criteria) this;
        }

        public Criteria andConfValueNotBetween(String value1, String value2) {
            addCriterion("CONF_VALUE not between", value1, value2, "confValue");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("DESCRIPTION is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("DESCRIPTION is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("DESCRIPTION =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("DESCRIPTION <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("DESCRIPTION >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("DESCRIPTION >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("DESCRIPTION <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("DESCRIPTION <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("DESCRIPTION like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("DESCRIPTION not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("DESCRIPTION in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("DESCRIPTION not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("DESCRIPTION between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("DESCRIPTION not between", value1, value2, "description");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion implements Serializable {
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