package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.PreguntaConstants;
import com.keralty.aeconfiguraciones.global.UserFunctions;
import com.keralty.aeconfiguraciones.global.ValorConstants;
import com.keralty.aeconfiguraciones.persistence.Pregunta;
import com.keralty.aeconfiguraciones.persistence.Valor;
import com.keralty.aeconfiguraciones.sqlutils.SQLExists;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereConjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandOVParameter;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandPathInstance;
import com.keralty.aeconfiguraciones.sqlutils.TypedQueryBuilder;
import com.keralty.aeconfiguraciones.utils.Agent;
import jakarta.inject.Inject;
import java.util.Collection;
import java.util.List;

public class ValorJpaRepositoryImpl extends AbstractRepository implements ValorJpaRepositoryCustom {

    @Inject
    public ValorJpaRepositoryImpl(Agent agent, UserFunctions userFunctionsSrv) {
        super(agent, userFunctionsSrv);
    }

    @Override
    public List<Valor> executeQuery(String jpqlQuery) {
        return getQueryBuilder().build(jpqlQuery).execute();
    }
    
    @Override
    public List<Valor> executeQuery(String jpqlQuery, List<SQLParam> params) {
        return getQueryBuilder().build(jpqlQuery).setParameters(params).execute();
    }
    
    public Collection<Valor> executeQuery(SQLSelect select) {
        return getQueryBuilder().build(select).execute();
    }

    private TypedQueryBuilder<Valor> getQueryBuilder() {
        return new TypedQueryBuilder<>(em, Valor.class);
    }

    @Override	
    public List<Valor> solveQuery(SQLSelect select) {
        return solveQuery(select, 0, 0);
    }

    @Override
    public List<Valor> solveQuery(SQLSelect select, int pageSize, int pageNumber) {
        return getQueryBuilder().build(select, pageSize, pageNumber).execute();
    }

    // AssocOperator: AssocOperator000
	@Override
    public Boolean assocOperator000(Pregunta preguntaInsValorPreguntapthisPregunta) throws SystemException {
        try {
            // EXIST (Preguntas) WHERE (Preguntas = pthisPregunta)
            return assocOperator000Range1(preguntaInsValorPreguntapthisPregunta);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Valor, Assoc.Operator: AssocOperator000, Method: assocOperator000";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 0 for AssocOperator AssocOperator000
	@Override
    public Boolean assocOperator000Range1 (Pregunta preguntaInsValorPreguntapthisPregunta) throws SystemException {
        SQLExists sql = assocOperator000Range1Query(preguntaInsValorPreguntapthisPregunta, null);
        return !new TypedQueryBuilder<Valor>(em, Valor.class).build(sql).execute().isEmpty();
    }

    // Range Query 0 for AssocOperator AssocOperator000
	@Override
    public SQLExists assocOperator000Range1Query (Pregunta preguntaInsValorPreguntapthisPregunta, SQLSelect superQuery) throws SystemException {
        try {
            // The path starts with a role
            SQLExists sql = new SQLExists(superQuery, ValorConstants.CLASS_NAME, ValorConstants.ROLE_NAME_PREGUNTAS);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Preguntas = pthisPregunta
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(ValorConstants.ROLE_NAME_PREGUNTAS, PreguntaConstants.CLASS_NAME);
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(PreguntaConstants.ARG_NAME_INSVALORPREGUNTA_PREGUNTAINSVALORPREGUNTAPTHISPREGUNTA, PreguntaConstants.CLASS_NAME, PreguntaConstants.CLASS_NAME, preguntaInsValorPreguntapthisPregunta.getOid(), PreguntaConstants.ARG_NAME_INSVALORPREGUNTA_PREGUNTAINSVALORPREGUNTAPTHISPREGUNTA);
                    comparison1.setSecondOperand(operand223);
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Valor, Assoc.Operator: AssocOperator000, Method: assocOperator000Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator001
	@Override
    public Boolean assocOperator001(Pregunta preguntaDelValorPreguntapthisPregunta) throws SystemException {
        try {
            // EXIST (Preguntas) WHERE (Preguntas = pthisPregunta)
            return assocOperator001Range1(preguntaDelValorPreguntapthisPregunta);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Valor, Assoc.Operator: AssocOperator001, Method: assocOperator001";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 0 for AssocOperator AssocOperator001
	@Override
    public Boolean assocOperator001Range1 (Pregunta preguntaDelValorPreguntapthisPregunta) throws SystemException {
        SQLExists sql = assocOperator001Range1Query(preguntaDelValorPreguntapthisPregunta, null);
        return !new TypedQueryBuilder<Valor>(em, Valor.class).build(sql).execute().isEmpty();
    }

    // Range Query 0 for AssocOperator AssocOperator001
	@Override
    public SQLExists assocOperator001Range1Query (Pregunta preguntaDelValorPreguntapthisPregunta, SQLSelect superQuery) throws SystemException {
        try {
            // The path starts with a role
            SQLExists sql = new SQLExists(superQuery, ValorConstants.CLASS_NAME, ValorConstants.ROLE_NAME_PREGUNTAS);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Preguntas = pthisPregunta
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(ValorConstants.ROLE_NAME_PREGUNTAS, PreguntaConstants.CLASS_NAME);
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(PreguntaConstants.ARG_NAME_DELVALORPREGUNTA_PREGUNTADELVALORPREGUNTAPTHISPREGUNTA, PreguntaConstants.CLASS_NAME, PreguntaConstants.CLASS_NAME, preguntaDelValorPreguntapthisPregunta.getOid(), PreguntaConstants.ARG_NAME_DELVALORPREGUNTA_PREGUNTADELVALORPREGUNTAPTHISPREGUNTA);
                    comparison1.setSecondOperand(operand223);
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Valor, Assoc.Operator: AssocOperator001, Method: assocOperator001Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator002
	@Override
    public Boolean assocOperator002(Pregunta voPregunta) throws SystemException {
        try {
            // EXIST (Preguntas) WHERE (Preguntas = voPregunta)
            return assocOperator002Range1(voPregunta);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Valor, Assoc.Operator: AssocOperator002, Method: assocOperator002";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 0 for AssocOperator AssocOperator002
	@Override
    public Boolean assocOperator002Range1 (Pregunta voPregunta) throws SystemException {
        SQLExists sql = assocOperator002Range1Query(voPregunta, null);
        return !new TypedQueryBuilder<Valor>(em, Valor.class).build(sql).execute().isEmpty();
    }

    // Range Query 0 for AssocOperator AssocOperator002
	@Override
    public SQLExists assocOperator002Range1Query (Pregunta voPregunta, SQLSelect superQuery) throws SystemException {
        try {
            // The path starts with a role
            SQLExists sql = new SQLExists(superQuery, ValorConstants.CLASS_NAME, ValorConstants.ROLE_NAME_PREGUNTAS);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Preguntas = voPregunta
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(ValorConstants.ROLE_NAME_PREGUNTAS, PreguntaConstants.CLASS_NAME);
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(ValorConstants.VAR_NAME_IFVALORESPREGUNTA_VOPREGUNTA, PreguntaConstants.CLASS_NAME, PreguntaConstants.CLASS_NAME, voPregunta.getOid(), ValorConstants.VAR_NAME_IFVALORESPREGUNTA_VOPREGUNTA);
                    comparison1.setSecondOperand(operand223);
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Valor, Assoc.Operator: AssocOperator002, Method: assocOperator002Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator003
	@Override
    public Boolean assocOperator003(Valor instance) throws SystemException {
        try {
            // EXIST (Preguntas)
            return assocOperator003Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Valor, Assoc.Operator: AssocOperator003, Method: assocOperator003";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator003
	@Override
    public Boolean assocOperator003Range1 (Valor instance) throws SystemException {
        SQLExists sql = assocOperator003Range1Query(instance);
        return !new TypedQueryBuilder<Valor>(em, Valor.class).build(sql).execute().isEmpty();
    }

    // Range Query 1 for AssocOperator AssocOperator003
	@Override
    public SQLExists assocOperator003Range1Query (Valor instance) throws SystemException {
        try {
            // The path starts with a role
            return new SQLExists(ValorConstants.CLASS_NAME, ValorConstants.ROLE_NAME_PREGUNTAS, instance.getOid());
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Valor, Assoc.Operator: AssocOperator003, Method: assocOperator003Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    @Override
    public void refreshEntity(Object object) {
        super.refreshEntity(object);
    }
}
