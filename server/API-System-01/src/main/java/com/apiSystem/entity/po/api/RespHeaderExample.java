package com.apiSystem.entity.po.api;

import java.util.ArrayList;
import java.util.List;

public class RespHeaderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RespHeaderExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andRespIdIsNull() {
            addCriterion("resp_id is null");
            return (Criteria) this;
        }

        public Criteria andRespIdIsNotNull() {
            addCriterion("resp_id is not null");
            return (Criteria) this;
        }

        public Criteria andRespIdEqualTo(Integer value) {
            addCriterion("resp_id =", value, "respId");
            return (Criteria) this;
        }

        public Criteria andRespIdNotEqualTo(Integer value) {
            addCriterion("resp_id <>", value, "respId");
            return (Criteria) this;
        }

        public Criteria andRespIdGreaterThan(Integer value) {
            addCriterion("resp_id >", value, "respId");
            return (Criteria) this;
        }

        public Criteria andRespIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("resp_id >=", value, "respId");
            return (Criteria) this;
        }

        public Criteria andRespIdLessThan(Integer value) {
            addCriterion("resp_id <", value, "respId");
            return (Criteria) this;
        }

        public Criteria andRespIdLessThanOrEqualTo(Integer value) {
            addCriterion("resp_id <=", value, "respId");
            return (Criteria) this;
        }

        public Criteria andRespIdIn(List<Integer> values) {
            addCriterion("resp_id in", values, "respId");
            return (Criteria) this;
        }

        public Criteria andRespIdNotIn(List<Integer> values) {
            addCriterion("resp_id not in", values, "respId");
            return (Criteria) this;
        }

        public Criteria andRespIdBetween(Integer value1, Integer value2) {
            addCriterion("resp_id between", value1, value2, "respId");
            return (Criteria) this;
        }

        public Criteria andRespIdNotBetween(Integer value1, Integer value2) {
            addCriterion("resp_id not between", value1, value2, "respId");
            return (Criteria) this;
        }

        public Criteria andTagNameIsNull() {
            addCriterion("tag_name is null");
            return (Criteria) this;
        }

        public Criteria andTagNameIsNotNull() {
            addCriterion("tag_name is not null");
            return (Criteria) this;
        }

        public Criteria andTagNameEqualTo(String value) {
            addCriterion("tag_name =", value, "tagName");
            return (Criteria) this;
        }

        public Criteria andTagNameNotEqualTo(String value) {
            addCriterion("tag_name <>", value, "tagName");
            return (Criteria) this;
        }

        public Criteria andTagNameGreaterThan(String value) {
            addCriterion("tag_name >", value, "tagName");
            return (Criteria) this;
        }

        public Criteria andTagNameGreaterThanOrEqualTo(String value) {
            addCriterion("tag_name >=", value, "tagName");
            return (Criteria) this;
        }

        public Criteria andTagNameLessThan(String value) {
            addCriterion("tag_name <", value, "tagName");
            return (Criteria) this;
        }

        public Criteria andTagNameLessThanOrEqualTo(String value) {
            addCriterion("tag_name <=", value, "tagName");
            return (Criteria) this;
        }

        public Criteria andTagNameLike(String value) {
            addCriterion("tag_name like", value, "tagName");
            return (Criteria) this;
        }

        public Criteria andTagNameNotLike(String value) {
            addCriterion("tag_name not like", value, "tagName");
            return (Criteria) this;
        }

        public Criteria andTagNameIn(List<String> values) {
            addCriterion("tag_name in", values, "tagName");
            return (Criteria) this;
        }

        public Criteria andTagNameNotIn(List<String> values) {
            addCriterion("tag_name not in", values, "tagName");
            return (Criteria) this;
        }

        public Criteria andTagNameBetween(String value1, String value2) {
            addCriterion("tag_name between", value1, value2, "tagName");
            return (Criteria) this;
        }

        public Criteria andTagNameNotBetween(String value1, String value2) {
            addCriterion("tag_name not between", value1, value2, "tagName");
            return (Criteria) this;
        }

        public Criteria andDataTypeIsNull() {
            addCriterion("data_type is null");
            return (Criteria) this;
        }

        public Criteria andDataTypeIsNotNull() {
            addCriterion("data_type is not null");
            return (Criteria) this;
        }

        public Criteria andDataTypeEqualTo(String value) {
            addCriterion("data_type =", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotEqualTo(String value) {
            addCriterion("data_type <>", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeGreaterThan(String value) {
            addCriterion("data_type >", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeGreaterThanOrEqualTo(String value) {
            addCriterion("data_type >=", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLessThan(String value) {
            addCriterion("data_type <", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLessThanOrEqualTo(String value) {
            addCriterion("data_type <=", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLike(String value) {
            addCriterion("data_type like", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotLike(String value) {
            addCriterion("data_type not like", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeIn(List<String> values) {
            addCriterion("data_type in", values, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotIn(List<String> values) {
            addCriterion("data_type not in", values, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeBetween(String value1, String value2) {
            addCriterion("data_type between", value1, value2, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotBetween(String value1, String value2) {
            addCriterion("data_type not between", value1, value2, "dataType");
            return (Criteria) this;
        }

        public Criteria andRequiredIsNull() {
            addCriterion("required is null");
            return (Criteria) this;
        }

        public Criteria andRequiredIsNotNull() {
            addCriterion("required is not null");
            return (Criteria) this;
        }

        public Criteria andRequiredEqualTo(Integer value) {
            addCriterion("required =", value, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredNotEqualTo(Integer value) {
            addCriterion("required <>", value, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredGreaterThan(Integer value) {
            addCriterion("required >", value, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredGreaterThanOrEqualTo(Integer value) {
            addCriterion("required >=", value, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredLessThan(Integer value) {
            addCriterion("required <", value, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredLessThanOrEqualTo(Integer value) {
            addCriterion("required <=", value, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredIn(List<Integer> values) {
            addCriterion("required in", values, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredNotIn(List<Integer> values) {
            addCriterion("required not in", values, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredBetween(Integer value1, Integer value2) {
            addCriterion("required between", value1, value2, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredNotBetween(Integer value1, Integer value2) {
            addCriterion("required not between", value1, value2, "required");
            return (Criteria) this;
        }

        public Criteria andExampleIsNull() {
            addCriterion("example is null");
            return (Criteria) this;
        }

        public Criteria andExampleIsNotNull() {
            addCriterion("example is not null");
            return (Criteria) this;
        }

        public Criteria andExampleEqualTo(String value) {
            addCriterion("example =", value, "example");
            return (Criteria) this;
        }

        public Criteria andExampleNotEqualTo(String value) {
            addCriterion("example <>", value, "example");
            return (Criteria) this;
        }

        public Criteria andExampleGreaterThan(String value) {
            addCriterion("example >", value, "example");
            return (Criteria) this;
        }

        public Criteria andExampleGreaterThanOrEqualTo(String value) {
            addCriterion("example >=", value, "example");
            return (Criteria) this;
        }

        public Criteria andExampleLessThan(String value) {
            addCriterion("example <", value, "example");
            return (Criteria) this;
        }

        public Criteria andExampleLessThanOrEqualTo(String value) {
            addCriterion("example <=", value, "example");
            return (Criteria) this;
        }

        public Criteria andExampleLike(String value) {
            addCriterion("example like", value, "example");
            return (Criteria) this;
        }

        public Criteria andExampleNotLike(String value) {
            addCriterion("example not like", value, "example");
            return (Criteria) this;
        }

        public Criteria andExampleIn(List<String> values) {
            addCriterion("example in", values, "example");
            return (Criteria) this;
        }

        public Criteria andExampleNotIn(List<String> values) {
            addCriterion("example not in", values, "example");
            return (Criteria) this;
        }

        public Criteria andExampleBetween(String value1, String value2) {
            addCriterion("example between", value1, value2, "example");
            return (Criteria) this;
        }

        public Criteria andExampleNotBetween(String value1, String value2) {
            addCriterion("example not between", value1, value2, "example");
            return (Criteria) this;
        }

        public Criteria andExplanationIsNull() {
            addCriterion("explanation is null");
            return (Criteria) this;
        }

        public Criteria andExplanationIsNotNull() {
            addCriterion("explanation is not null");
            return (Criteria) this;
        }

        public Criteria andExplanationEqualTo(String value) {
            addCriterion("explanation =", value, "explanation");
            return (Criteria) this;
        }

        public Criteria andExplanationNotEqualTo(String value) {
            addCriterion("explanation <>", value, "explanation");
            return (Criteria) this;
        }

        public Criteria andExplanationGreaterThan(String value) {
            addCriterion("explanation >", value, "explanation");
            return (Criteria) this;
        }

        public Criteria andExplanationGreaterThanOrEqualTo(String value) {
            addCriterion("explanation >=", value, "explanation");
            return (Criteria) this;
        }

        public Criteria andExplanationLessThan(String value) {
            addCriterion("explanation <", value, "explanation");
            return (Criteria) this;
        }

        public Criteria andExplanationLessThanOrEqualTo(String value) {
            addCriterion("explanation <=", value, "explanation");
            return (Criteria) this;
        }

        public Criteria andExplanationLike(String value) {
            addCriterion("explanation like", value, "explanation");
            return (Criteria) this;
        }

        public Criteria andExplanationNotLike(String value) {
            addCriterion("explanation not like", value, "explanation");
            return (Criteria) this;
        }

        public Criteria andExplanationIn(List<String> values) {
            addCriterion("explanation in", values, "explanation");
            return (Criteria) this;
        }

        public Criteria andExplanationNotIn(List<String> values) {
            addCriterion("explanation not in", values, "explanation");
            return (Criteria) this;
        }

        public Criteria andExplanationBetween(String value1, String value2) {
            addCriterion("explanation between", value1, value2, "explanation");
            return (Criteria) this;
        }

        public Criteria andExplanationNotBetween(String value1, String value2) {
            addCriterion("explanation not between", value1, value2, "explanation");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("remarks is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("remarks is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("remarks =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("remarks <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("remarks >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("remarks >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("remarks <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("remarks <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("remarks like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("remarks not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("remarks in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("remarks not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("remarks between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("remarks not between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andLenConstraintIsNull() {
            addCriterion("len_constraint is null");
            return (Criteria) this;
        }

        public Criteria andLenConstraintIsNotNull() {
            addCriterion("len_constraint is not null");
            return (Criteria) this;
        }

        public Criteria andLenConstraintEqualTo(Integer value) {
            addCriterion("len_constraint =", value, "lenConstraint");
            return (Criteria) this;
        }

        public Criteria andLenConstraintNotEqualTo(Integer value) {
            addCriterion("len_constraint <>", value, "lenConstraint");
            return (Criteria) this;
        }

        public Criteria andLenConstraintGreaterThan(Integer value) {
            addCriterion("len_constraint >", value, "lenConstraint");
            return (Criteria) this;
        }

        public Criteria andLenConstraintGreaterThanOrEqualTo(Integer value) {
            addCriterion("len_constraint >=", value, "lenConstraint");
            return (Criteria) this;
        }

        public Criteria andLenConstraintLessThan(Integer value) {
            addCriterion("len_constraint <", value, "lenConstraint");
            return (Criteria) this;
        }

        public Criteria andLenConstraintLessThanOrEqualTo(Integer value) {
            addCriterion("len_constraint <=", value, "lenConstraint");
            return (Criteria) this;
        }

        public Criteria andLenConstraintIn(List<Integer> values) {
            addCriterion("len_constraint in", values, "lenConstraint");
            return (Criteria) this;
        }

        public Criteria andLenConstraintNotIn(List<Integer> values) {
            addCriterion("len_constraint not in", values, "lenConstraint");
            return (Criteria) this;
        }

        public Criteria andLenConstraintBetween(Integer value1, Integer value2) {
            addCriterion("len_constraint between", value1, value2, "lenConstraint");
            return (Criteria) this;
        }

        public Criteria andLenConstraintNotBetween(Integer value1, Integer value2) {
            addCriterion("len_constraint not between", value1, value2, "lenConstraint");
            return (Criteria) this;
        }

        public Criteria andOtherConstraintIsNull() {
            addCriterion("other_constraint is null");
            return (Criteria) this;
        }

        public Criteria andOtherConstraintIsNotNull() {
            addCriterion("other_constraint is not null");
            return (Criteria) this;
        }

        public Criteria andOtherConstraintEqualTo(String value) {
            addCriterion("other_constraint =", value, "otherConstraint");
            return (Criteria) this;
        }

        public Criteria andOtherConstraintNotEqualTo(String value) {
            addCriterion("other_constraint <>", value, "otherConstraint");
            return (Criteria) this;
        }

        public Criteria andOtherConstraintGreaterThan(String value) {
            addCriterion("other_constraint >", value, "otherConstraint");
            return (Criteria) this;
        }

        public Criteria andOtherConstraintGreaterThanOrEqualTo(String value) {
            addCriterion("other_constraint >=", value, "otherConstraint");
            return (Criteria) this;
        }

        public Criteria andOtherConstraintLessThan(String value) {
            addCriterion("other_constraint <", value, "otherConstraint");
            return (Criteria) this;
        }

        public Criteria andOtherConstraintLessThanOrEqualTo(String value) {
            addCriterion("other_constraint <=", value, "otherConstraint");
            return (Criteria) this;
        }

        public Criteria andOtherConstraintLike(String value) {
            addCriterion("other_constraint like", value, "otherConstraint");
            return (Criteria) this;
        }

        public Criteria andOtherConstraintNotLike(String value) {
            addCriterion("other_constraint not like", value, "otherConstraint");
            return (Criteria) this;
        }

        public Criteria andOtherConstraintIn(List<String> values) {
            addCriterion("other_constraint in", values, "otherConstraint");
            return (Criteria) this;
        }

        public Criteria andOtherConstraintNotIn(List<String> values) {
            addCriterion("other_constraint not in", values, "otherConstraint");
            return (Criteria) this;
        }

        public Criteria andOtherConstraintBetween(String value1, String value2) {
            addCriterion("other_constraint between", value1, value2, "otherConstraint");
            return (Criteria) this;
        }

        public Criteria andOtherConstraintNotBetween(String value1, String value2) {
            addCriterion("other_constraint not between", value1, value2, "otherConstraint");
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