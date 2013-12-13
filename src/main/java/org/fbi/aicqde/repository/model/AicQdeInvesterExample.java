package org.fbi.aicqde.repository.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AicQdeInvesterExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table FIS.AIC_QDE_INVESTER
     *
     * @mbggenerated Wed Dec 11 11:35:40 CST 2013
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table FIS.AIC_QDE_INVESTER
     *
     * @mbggenerated Wed Dec 11 11:35:40 CST 2013
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table FIS.AIC_QDE_INVESTER
     *
     * @mbggenerated Wed Dec 11 11:35:40 CST 2013
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIS.AIC_QDE_INVESTER
     *
     * @mbggenerated Wed Dec 11 11:35:40 CST 2013
     */
    public AicQdeInvesterExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIS.AIC_QDE_INVESTER
     *
     * @mbggenerated Wed Dec 11 11:35:40 CST 2013
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIS.AIC_QDE_INVESTER
     *
     * @mbggenerated Wed Dec 11 11:35:40 CST 2013
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIS.AIC_QDE_INVESTER
     *
     * @mbggenerated Wed Dec 11 11:35:40 CST 2013
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIS.AIC_QDE_INVESTER
     *
     * @mbggenerated Wed Dec 11 11:35:40 CST 2013
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIS.AIC_QDE_INVESTER
     *
     * @mbggenerated Wed Dec 11 11:35:40 CST 2013
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIS.AIC_QDE_INVESTER
     *
     * @mbggenerated Wed Dec 11 11:35:40 CST 2013
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIS.AIC_QDE_INVESTER
     *
     * @mbggenerated Wed Dec 11 11:35:40 CST 2013
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIS.AIC_QDE_INVESTER
     *
     * @mbggenerated Wed Dec 11 11:35:40 CST 2013
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIS.AIC_QDE_INVESTER
     *
     * @mbggenerated Wed Dec 11 11:35:40 CST 2013
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIS.AIC_QDE_INVESTER
     *
     * @mbggenerated Wed Dec 11 11:35:40 CST 2013
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table FIS.AIC_QDE_INVESTER
     *
     * @mbggenerated Wed Dec 11 11:35:40 CST 2013
     */
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

        public Criteria andPkidIsNull() {
            addCriterion("PKID is null");
            return (Criteria) this;
        }

        public Criteria andPkidIsNotNull() {
            addCriterion("PKID is not null");
            return (Criteria) this;
        }

        public Criteria andPkidEqualTo(String value) {
            addCriterion("PKID =", value, "pkid");
            return (Criteria) this;
        }

        public Criteria andPkidNotEqualTo(String value) {
            addCriterion("PKID <>", value, "pkid");
            return (Criteria) this;
        }

        public Criteria andPkidGreaterThan(String value) {
            addCriterion("PKID >", value, "pkid");
            return (Criteria) this;
        }

        public Criteria andPkidGreaterThanOrEqualTo(String value) {
            addCriterion("PKID >=", value, "pkid");
            return (Criteria) this;
        }

        public Criteria andPkidLessThan(String value) {
            addCriterion("PKID <", value, "pkid");
            return (Criteria) this;
        }

        public Criteria andPkidLessThanOrEqualTo(String value) {
            addCriterion("PKID <=", value, "pkid");
            return (Criteria) this;
        }

        public Criteria andPkidLike(String value) {
            addCriterion("PKID like", value, "pkid");
            return (Criteria) this;
        }

        public Criteria andPkidNotLike(String value) {
            addCriterion("PKID not like", value, "pkid");
            return (Criteria) this;
        }

        public Criteria andPkidIn(List<String> values) {
            addCriterion("PKID in", values, "pkid");
            return (Criteria) this;
        }

        public Criteria andPkidNotIn(List<String> values) {
            addCriterion("PKID not in", values, "pkid");
            return (Criteria) this;
        }

        public Criteria andPkidBetween(String value1, String value2) {
            addCriterion("PKID between", value1, value2, "pkid");
            return (Criteria) this;
        }

        public Criteria andPkidNotBetween(String value1, String value2) {
            addCriterion("PKID not between", value1, value2, "pkid");
            return (Criteria) this;
        }

        public Criteria andPregNoIsNull() {
            addCriterion("PREG_NO is null");
            return (Criteria) this;
        }

        public Criteria andPregNoIsNotNull() {
            addCriterion("PREG_NO is not null");
            return (Criteria) this;
        }

        public Criteria andPregNoEqualTo(String value) {
            addCriterion("PREG_NO =", value, "pregNo");
            return (Criteria) this;
        }

        public Criteria andPregNoNotEqualTo(String value) {
            addCriterion("PREG_NO <>", value, "pregNo");
            return (Criteria) this;
        }

        public Criteria andPregNoGreaterThan(String value) {
            addCriterion("PREG_NO >", value, "pregNo");
            return (Criteria) this;
        }

        public Criteria andPregNoGreaterThanOrEqualTo(String value) {
            addCriterion("PREG_NO >=", value, "pregNo");
            return (Criteria) this;
        }

        public Criteria andPregNoLessThan(String value) {
            addCriterion("PREG_NO <", value, "pregNo");
            return (Criteria) this;
        }

        public Criteria andPregNoLessThanOrEqualTo(String value) {
            addCriterion("PREG_NO <=", value, "pregNo");
            return (Criteria) this;
        }

        public Criteria andPregNoLike(String value) {
            addCriterion("PREG_NO like", value, "pregNo");
            return (Criteria) this;
        }

        public Criteria andPregNoNotLike(String value) {
            addCriterion("PREG_NO not like", value, "pregNo");
            return (Criteria) this;
        }

        public Criteria andPregNoIn(List<String> values) {
            addCriterion("PREG_NO in", values, "pregNo");
            return (Criteria) this;
        }

        public Criteria andPregNoNotIn(List<String> values) {
            addCriterion("PREG_NO not in", values, "pregNo");
            return (Criteria) this;
        }

        public Criteria andPregNoBetween(String value1, String value2) {
            addCriterion("PREG_NO between", value1, value2, "pregNo");
            return (Criteria) this;
        }

        public Criteria andPregNoNotBetween(String value1, String value2) {
            addCriterion("PREG_NO not between", value1, value2, "pregNo");
            return (Criteria) this;
        }

        public Criteria andRegNoIsNull() {
            addCriterion("REG_NO is null");
            return (Criteria) this;
        }

        public Criteria andRegNoIsNotNull() {
            addCriterion("REG_NO is not null");
            return (Criteria) this;
        }

        public Criteria andRegNoEqualTo(String value) {
            addCriterion("REG_NO =", value, "regNo");
            return (Criteria) this;
        }

        public Criteria andRegNoNotEqualTo(String value) {
            addCriterion("REG_NO <>", value, "regNo");
            return (Criteria) this;
        }

        public Criteria andRegNoGreaterThan(String value) {
            addCriterion("REG_NO >", value, "regNo");
            return (Criteria) this;
        }

        public Criteria andRegNoGreaterThanOrEqualTo(String value) {
            addCriterion("REG_NO >=", value, "regNo");
            return (Criteria) this;
        }

        public Criteria andRegNoLessThan(String value) {
            addCriterion("REG_NO <", value, "regNo");
            return (Criteria) this;
        }

        public Criteria andRegNoLessThanOrEqualTo(String value) {
            addCriterion("REG_NO <=", value, "regNo");
            return (Criteria) this;
        }

        public Criteria andRegNoLike(String value) {
            addCriterion("REG_NO like", value, "regNo");
            return (Criteria) this;
        }

        public Criteria andRegNoNotLike(String value) {
            addCriterion("REG_NO not like", value, "regNo");
            return (Criteria) this;
        }

        public Criteria andRegNoIn(List<String> values) {
            addCriterion("REG_NO in", values, "regNo");
            return (Criteria) this;
        }

        public Criteria andRegNoNotIn(List<String> values) {
            addCriterion("REG_NO not in", values, "regNo");
            return (Criteria) this;
        }

        public Criteria andRegNoBetween(String value1, String value2) {
            addCriterion("REG_NO between", value1, value2, "regNo");
            return (Criteria) this;
        }

        public Criteria andRegNoNotBetween(String value1, String value2) {
            addCriterion("REG_NO not between", value1, value2, "regNo");
            return (Criteria) this;
        }

        public Criteria andVchSnIsNull() {
            addCriterion("VCH_SN is null");
            return (Criteria) this;
        }

        public Criteria andVchSnIsNotNull() {
            addCriterion("VCH_SN is not null");
            return (Criteria) this;
        }

        public Criteria andVchSnEqualTo(String value) {
            addCriterion("VCH_SN =", value, "vchSn");
            return (Criteria) this;
        }

        public Criteria andVchSnNotEqualTo(String value) {
            addCriterion("VCH_SN <>", value, "vchSn");
            return (Criteria) this;
        }

        public Criteria andVchSnGreaterThan(String value) {
            addCriterion("VCH_SN >", value, "vchSn");
            return (Criteria) this;
        }

        public Criteria andVchSnGreaterThanOrEqualTo(String value) {
            addCriterion("VCH_SN >=", value, "vchSn");
            return (Criteria) this;
        }

        public Criteria andVchSnLessThan(String value) {
            addCriterion("VCH_SN <", value, "vchSn");
            return (Criteria) this;
        }

        public Criteria andVchSnLessThanOrEqualTo(String value) {
            addCriterion("VCH_SN <=", value, "vchSn");
            return (Criteria) this;
        }

        public Criteria andVchSnLike(String value) {
            addCriterion("VCH_SN like", value, "vchSn");
            return (Criteria) this;
        }

        public Criteria andVchSnNotLike(String value) {
            addCriterion("VCH_SN not like", value, "vchSn");
            return (Criteria) this;
        }

        public Criteria andVchSnIn(List<String> values) {
            addCriterion("VCH_SN in", values, "vchSn");
            return (Criteria) this;
        }

        public Criteria andVchSnNotIn(List<String> values) {
            addCriterion("VCH_SN not in", values, "vchSn");
            return (Criteria) this;
        }

        public Criteria andVchSnBetween(String value1, String value2) {
            addCriterion("VCH_SN between", value1, value2, "vchSn");
            return (Criteria) this;
        }

        public Criteria andVchSnNotBetween(String value1, String value2) {
            addCriterion("VCH_SN not between", value1, value2, "vchSn");
            return (Criteria) this;
        }

        public Criteria andInvesterNameIsNull() {
            addCriterion("INVESTER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andInvesterNameIsNotNull() {
            addCriterion("INVESTER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andInvesterNameEqualTo(String value) {
            addCriterion("INVESTER_NAME =", value, "investerName");
            return (Criteria) this;
        }

        public Criteria andInvesterNameNotEqualTo(String value) {
            addCriterion("INVESTER_NAME <>", value, "investerName");
            return (Criteria) this;
        }

        public Criteria andInvesterNameGreaterThan(String value) {
            addCriterion("INVESTER_NAME >", value, "investerName");
            return (Criteria) this;
        }

        public Criteria andInvesterNameGreaterThanOrEqualTo(String value) {
            addCriterion("INVESTER_NAME >=", value, "investerName");
            return (Criteria) this;
        }

        public Criteria andInvesterNameLessThan(String value) {
            addCriterion("INVESTER_NAME <", value, "investerName");
            return (Criteria) this;
        }

        public Criteria andInvesterNameLessThanOrEqualTo(String value) {
            addCriterion("INVESTER_NAME <=", value, "investerName");
            return (Criteria) this;
        }

        public Criteria andInvesterNameLike(String value) {
            addCriterion("INVESTER_NAME like", value, "investerName");
            return (Criteria) this;
        }

        public Criteria andInvesterNameNotLike(String value) {
            addCriterion("INVESTER_NAME not like", value, "investerName");
            return (Criteria) this;
        }

        public Criteria andInvesterNameIn(List<String> values) {
            addCriterion("INVESTER_NAME in", values, "investerName");
            return (Criteria) this;
        }

        public Criteria andInvesterNameNotIn(List<String> values) {
            addCriterion("INVESTER_NAME not in", values, "investerName");
            return (Criteria) this;
        }

        public Criteria andInvesterNameBetween(String value1, String value2) {
            addCriterion("INVESTER_NAME between", value1, value2, "investerName");
            return (Criteria) this;
        }

        public Criteria andInvesterNameNotBetween(String value1, String value2) {
            addCriterion("INVESTER_NAME not between", value1, value2, "investerName");
            return (Criteria) this;
        }

        public Criteria andActNoIsNull() {
            addCriterion("ACT_NO is null");
            return (Criteria) this;
        }

        public Criteria andActNoIsNotNull() {
            addCriterion("ACT_NO is not null");
            return (Criteria) this;
        }

        public Criteria andActNoEqualTo(String value) {
            addCriterion("ACT_NO =", value, "actNo");
            return (Criteria) this;
        }

        public Criteria andActNoNotEqualTo(String value) {
            addCriterion("ACT_NO <>", value, "actNo");
            return (Criteria) this;
        }

        public Criteria andActNoGreaterThan(String value) {
            addCriterion("ACT_NO >", value, "actNo");
            return (Criteria) this;
        }

        public Criteria andActNoGreaterThanOrEqualTo(String value) {
            addCriterion("ACT_NO >=", value, "actNo");
            return (Criteria) this;
        }

        public Criteria andActNoLessThan(String value) {
            addCriterion("ACT_NO <", value, "actNo");
            return (Criteria) this;
        }

        public Criteria andActNoLessThanOrEqualTo(String value) {
            addCriterion("ACT_NO <=", value, "actNo");
            return (Criteria) this;
        }

        public Criteria andActNoLike(String value) {
            addCriterion("ACT_NO like", value, "actNo");
            return (Criteria) this;
        }

        public Criteria andActNoNotLike(String value) {
            addCriterion("ACT_NO not like", value, "actNo");
            return (Criteria) this;
        }

        public Criteria andActNoIn(List<String> values) {
            addCriterion("ACT_NO in", values, "actNo");
            return (Criteria) this;
        }

        public Criteria andActNoNotIn(List<String> values) {
            addCriterion("ACT_NO not in", values, "actNo");
            return (Criteria) this;
        }

        public Criteria andActNoBetween(String value1, String value2) {
            addCriterion("ACT_NO between", value1, value2, "actNo");
            return (Criteria) this;
        }

        public Criteria andActNoNotBetween(String value1, String value2) {
            addCriterion("ACT_NO not between", value1, value2, "actNo");
            return (Criteria) this;
        }

        public Criteria andInvAmtIsNull() {
            addCriterion("INV_AMT is null");
            return (Criteria) this;
        }

        public Criteria andInvAmtIsNotNull() {
            addCriterion("INV_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andInvAmtEqualTo(BigDecimal value) {
            addCriterion("INV_AMT =", value, "invAmt");
            return (Criteria) this;
        }

        public Criteria andInvAmtNotEqualTo(BigDecimal value) {
            addCriterion("INV_AMT <>", value, "invAmt");
            return (Criteria) this;
        }

        public Criteria andInvAmtGreaterThan(BigDecimal value) {
            addCriterion("INV_AMT >", value, "invAmt");
            return (Criteria) this;
        }

        public Criteria andInvAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("INV_AMT >=", value, "invAmt");
            return (Criteria) this;
        }

        public Criteria andInvAmtLessThan(BigDecimal value) {
            addCriterion("INV_AMT <", value, "invAmt");
            return (Criteria) this;
        }

        public Criteria andInvAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("INV_AMT <=", value, "invAmt");
            return (Criteria) this;
        }

        public Criteria andInvAmtIn(List<BigDecimal> values) {
            addCriterion("INV_AMT in", values, "invAmt");
            return (Criteria) this;
        }

        public Criteria andInvAmtNotIn(List<BigDecimal> values) {
            addCriterion("INV_AMT not in", values, "invAmt");
            return (Criteria) this;
        }

        public Criteria andInvAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("INV_AMT between", value1, value2, "invAmt");
            return (Criteria) this;
        }

        public Criteria andInvAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("INV_AMT not between", value1, value2, "invAmt");
            return (Criteria) this;
        }

        public Criteria andInvDateIsNull() {
            addCriterion("INV_DATE is null");
            return (Criteria) this;
        }

        public Criteria andInvDateIsNotNull() {
            addCriterion("INV_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andInvDateEqualTo(String value) {
            addCriterion("INV_DATE =", value, "invDate");
            return (Criteria) this;
        }

        public Criteria andInvDateNotEqualTo(String value) {
            addCriterion("INV_DATE <>", value, "invDate");
            return (Criteria) this;
        }

        public Criteria andInvDateGreaterThan(String value) {
            addCriterion("INV_DATE >", value, "invDate");
            return (Criteria) this;
        }

        public Criteria andInvDateGreaterThanOrEqualTo(String value) {
            addCriterion("INV_DATE >=", value, "invDate");
            return (Criteria) this;
        }

        public Criteria andInvDateLessThan(String value) {
            addCriterion("INV_DATE <", value, "invDate");
            return (Criteria) this;
        }

        public Criteria andInvDateLessThanOrEqualTo(String value) {
            addCriterion("INV_DATE <=", value, "invDate");
            return (Criteria) this;
        }

        public Criteria andInvDateLike(String value) {
            addCriterion("INV_DATE like", value, "invDate");
            return (Criteria) this;
        }

        public Criteria andInvDateNotLike(String value) {
            addCriterion("INV_DATE not like", value, "invDate");
            return (Criteria) this;
        }

        public Criteria andInvDateIn(List<String> values) {
            addCriterion("INV_DATE in", values, "invDate");
            return (Criteria) this;
        }

        public Criteria andInvDateNotIn(List<String> values) {
            addCriterion("INV_DATE not in", values, "invDate");
            return (Criteria) this;
        }

        public Criteria andInvDateBetween(String value1, String value2) {
            addCriterion("INV_DATE between", value1, value2, "invDate");
            return (Criteria) this;
        }

        public Criteria andInvDateNotBetween(String value1, String value2) {
            addCriterion("INV_DATE not between", value1, value2, "invDate");
            return (Criteria) this;
        }

        public Criteria andActBankNameIsNull() {
            addCriterion("ACT_BANK_NAME is null");
            return (Criteria) this;
        }

        public Criteria andActBankNameIsNotNull() {
            addCriterion("ACT_BANK_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andActBankNameEqualTo(String value) {
            addCriterion("ACT_BANK_NAME =", value, "actBankName");
            return (Criteria) this;
        }

        public Criteria andActBankNameNotEqualTo(String value) {
            addCriterion("ACT_BANK_NAME <>", value, "actBankName");
            return (Criteria) this;
        }

        public Criteria andActBankNameGreaterThan(String value) {
            addCriterion("ACT_BANK_NAME >", value, "actBankName");
            return (Criteria) this;
        }

        public Criteria andActBankNameGreaterThanOrEqualTo(String value) {
            addCriterion("ACT_BANK_NAME >=", value, "actBankName");
            return (Criteria) this;
        }

        public Criteria andActBankNameLessThan(String value) {
            addCriterion("ACT_BANK_NAME <", value, "actBankName");
            return (Criteria) this;
        }

        public Criteria andActBankNameLessThanOrEqualTo(String value) {
            addCriterion("ACT_BANK_NAME <=", value, "actBankName");
            return (Criteria) this;
        }

        public Criteria andActBankNameLike(String value) {
            addCriterion("ACT_BANK_NAME like", value, "actBankName");
            return (Criteria) this;
        }

        public Criteria andActBankNameNotLike(String value) {
            addCriterion("ACT_BANK_NAME not like", value, "actBankName");
            return (Criteria) this;
        }

        public Criteria andActBankNameIn(List<String> values) {
            addCriterion("ACT_BANK_NAME in", values, "actBankName");
            return (Criteria) this;
        }

        public Criteria andActBankNameNotIn(List<String> values) {
            addCriterion("ACT_BANK_NAME not in", values, "actBankName");
            return (Criteria) this;
        }

        public Criteria andActBankNameBetween(String value1, String value2) {
            addCriterion("ACT_BANK_NAME between", value1, value2, "actBankName");
            return (Criteria) this;
        }

        public Criteria andActBankNameNotBetween(String value1, String value2) {
            addCriterion("ACT_BANK_NAME not between", value1, value2, "actBankName");
            return (Criteria) this;
        }

        public Criteria andCertIdIsNull() {
            addCriterion("CERT_ID is null");
            return (Criteria) this;
        }

        public Criteria andCertIdIsNotNull() {
            addCriterion("CERT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCertIdEqualTo(String value) {
            addCriterion("CERT_ID =", value, "certId");
            return (Criteria) this;
        }

        public Criteria andCertIdNotEqualTo(String value) {
            addCriterion("CERT_ID <>", value, "certId");
            return (Criteria) this;
        }

        public Criteria andCertIdGreaterThan(String value) {
            addCriterion("CERT_ID >", value, "certId");
            return (Criteria) this;
        }

        public Criteria andCertIdGreaterThanOrEqualTo(String value) {
            addCriterion("CERT_ID >=", value, "certId");
            return (Criteria) this;
        }

        public Criteria andCertIdLessThan(String value) {
            addCriterion("CERT_ID <", value, "certId");
            return (Criteria) this;
        }

        public Criteria andCertIdLessThanOrEqualTo(String value) {
            addCriterion("CERT_ID <=", value, "certId");
            return (Criteria) this;
        }

        public Criteria andCertIdLike(String value) {
            addCriterion("CERT_ID like", value, "certId");
            return (Criteria) this;
        }

        public Criteria andCertIdNotLike(String value) {
            addCriterion("CERT_ID not like", value, "certId");
            return (Criteria) this;
        }

        public Criteria andCertIdIn(List<String> values) {
            addCriterion("CERT_ID in", values, "certId");
            return (Criteria) this;
        }

        public Criteria andCertIdNotIn(List<String> values) {
            addCriterion("CERT_ID not in", values, "certId");
            return (Criteria) this;
        }

        public Criteria andCertIdBetween(String value1, String value2) {
            addCriterion("CERT_ID between", value1, value2, "certId");
            return (Criteria) this;
        }

        public Criteria andCertIdNotBetween(String value1, String value2) {
            addCriterion("CERT_ID not between", value1, value2, "certId");
            return (Criteria) this;
        }

        public Criteria andInvestTypeIsNull() {
            addCriterion("INVEST_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andInvestTypeIsNotNull() {
            addCriterion("INVEST_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andInvestTypeEqualTo(String value) {
            addCriterion("INVEST_TYPE =", value, "investType");
            return (Criteria) this;
        }

        public Criteria andInvestTypeNotEqualTo(String value) {
            addCriterion("INVEST_TYPE <>", value, "investType");
            return (Criteria) this;
        }

        public Criteria andInvestTypeGreaterThan(String value) {
            addCriterion("INVEST_TYPE >", value, "investType");
            return (Criteria) this;
        }

        public Criteria andInvestTypeGreaterThanOrEqualTo(String value) {
            addCriterion("INVEST_TYPE >=", value, "investType");
            return (Criteria) this;
        }

        public Criteria andInvestTypeLessThan(String value) {
            addCriterion("INVEST_TYPE <", value, "investType");
            return (Criteria) this;
        }

        public Criteria andInvestTypeLessThanOrEqualTo(String value) {
            addCriterion("INVEST_TYPE <=", value, "investType");
            return (Criteria) this;
        }

        public Criteria andInvestTypeLike(String value) {
            addCriterion("INVEST_TYPE like", value, "investType");
            return (Criteria) this;
        }

        public Criteria andInvestTypeNotLike(String value) {
            addCriterion("INVEST_TYPE not like", value, "investType");
            return (Criteria) this;
        }

        public Criteria andInvestTypeIn(List<String> values) {
            addCriterion("INVEST_TYPE in", values, "investType");
            return (Criteria) this;
        }

        public Criteria andInvestTypeNotIn(List<String> values) {
            addCriterion("INVEST_TYPE not in", values, "investType");
            return (Criteria) this;
        }

        public Criteria andInvestTypeBetween(String value1, String value2) {
            addCriterion("INVEST_TYPE between", value1, value2, "investType");
            return (Criteria) this;
        }

        public Criteria andInvestTypeNotBetween(String value1, String value2) {
            addCriterion("INVEST_TYPE not between", value1, value2, "investType");
            return (Criteria) this;
        }

        public Criteria andBankHostSnIsNull() {
            addCriterion("BANK_HOST_SN is null");
            return (Criteria) this;
        }

        public Criteria andBankHostSnIsNotNull() {
            addCriterion("BANK_HOST_SN is not null");
            return (Criteria) this;
        }

        public Criteria andBankHostSnEqualTo(String value) {
            addCriterion("BANK_HOST_SN =", value, "bankHostSn");
            return (Criteria) this;
        }

        public Criteria andBankHostSnNotEqualTo(String value) {
            addCriterion("BANK_HOST_SN <>", value, "bankHostSn");
            return (Criteria) this;
        }

        public Criteria andBankHostSnGreaterThan(String value) {
            addCriterion("BANK_HOST_SN >", value, "bankHostSn");
            return (Criteria) this;
        }

        public Criteria andBankHostSnGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_HOST_SN >=", value, "bankHostSn");
            return (Criteria) this;
        }

        public Criteria andBankHostSnLessThan(String value) {
            addCriterion("BANK_HOST_SN <", value, "bankHostSn");
            return (Criteria) this;
        }

        public Criteria andBankHostSnLessThanOrEqualTo(String value) {
            addCriterion("BANK_HOST_SN <=", value, "bankHostSn");
            return (Criteria) this;
        }

        public Criteria andBankHostSnLike(String value) {
            addCriterion("BANK_HOST_SN like", value, "bankHostSn");
            return (Criteria) this;
        }

        public Criteria andBankHostSnNotLike(String value) {
            addCriterion("BANK_HOST_SN not like", value, "bankHostSn");
            return (Criteria) this;
        }

        public Criteria andBankHostSnIn(List<String> values) {
            addCriterion("BANK_HOST_SN in", values, "bankHostSn");
            return (Criteria) this;
        }

        public Criteria andBankHostSnNotIn(List<String> values) {
            addCriterion("BANK_HOST_SN not in", values, "bankHostSn");
            return (Criteria) this;
        }

        public Criteria andBankHostSnBetween(String value1, String value2) {
            addCriterion("BANK_HOST_SN between", value1, value2, "bankHostSn");
            return (Criteria) this;
        }

        public Criteria andBankHostSnNotBetween(String value1, String value2) {
            addCriterion("BANK_HOST_SN not between", value1, value2, "bankHostSn");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table FIS.AIC_QDE_INVESTER
     *
     * @mbggenerated do_not_delete_during_merge Wed Dec 11 11:35:40 CST 2013
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table FIS.AIC_QDE_INVESTER
     *
     * @mbggenerated Wed Dec 11 11:35:40 CST 2013
     */
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