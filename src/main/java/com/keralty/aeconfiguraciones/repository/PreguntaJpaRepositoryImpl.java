package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.CuestionarioMedConstants;
import com.keralty.aeconfiguraciones.global.PreguntaConstants;
import com.keralty.aeconfiguraciones.global.SeccionConstants;
import com.keralty.aeconfiguraciones.global.UserFunctions;
import com.keralty.aeconfiguraciones.global.ValorConstants;
import com.keralty.aeconfiguraciones.persistence.Pregunta;
import com.keralty.aeconfiguraciones.persistence.Seccion;
import com.keralty.aeconfiguraciones.persistence.Valor;
import com.keralty.aeconfiguraciones.sqlutils.SQLExists;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLPopulation;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereConjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandOVParameterAttribute;
import com.keralty.aeconfiguraciones.sqlutils.TypedQueryBuilder;
import com.keralty.aeconfiguraciones.utils.Agent;
import jakarta.inject.Inject;
import java.util.Collection;
import java.util.List;

public class PreguntaJpaRepositoryImpl extends AbstractRepository implements PreguntaJpaRepositoryCustom {

    @Inject
    public PreguntaJpaRepositoryImpl(Agent agent, UserFunctions userFunctionsSrv) {
        super(agent, userFunctionsSrv);
    }

    @Override
    public List<Pregunta> executeQuery(String jpqlQuery) {
        return getQueryBuilder().build(jpqlQuery).execute();
    }
    
    @Override
    public List<Pregunta> executeQuery(String jpqlQuery, List<SQLParam> params) {
        return getQueryBuilder().build(jpqlQuery).setParameters(params).execute();
    }
    
    public Collection<Pregunta> executeQuery(SQLSelect select) {
        return getQueryBuilder().build(select).execute();
    }

    private TypedQueryBuilder<Pregunta> getQueryBuilder() {
        return new TypedQueryBuilder<>(em, Pregunta.class);
    }

    @Override	
    public List<Pregunta> solveQuery(SQLSelect select) {
        return solveQuery(select, 0, 0);
    }

    @Override
    public List<Pregunta> solveQuery(SQLSelect select, int pageSize, int pageNumber) {
        return getQueryBuilder().build(select, pageSize, pageNumber).execute();
    }

    // AssocOperator: AssocOperator000
	@Override
    public Boolean assocOperator000(Seccion preguntaTCREARptpagrSeccion, Long preguntaTCREARptpatrNumero) throws SystemException {
        try {
            // EXIST (ptpagrSeccion.Cuestionario.Secciones.Preguntas) WHERE (ptpagrSeccion.Cuestionario.Secciones.Preguntas.Numero = ptpatrNumero)
            return assocOperator000Range1(preguntaTCREARptpagrSeccion, preguntaTCREARptpatrNumero);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Pregunta, Assoc.Operator: AssocOperator000, Method: assocOperator000";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator000
	@Override
    public Boolean assocOperator000Range1 (Seccion preguntaTCREARptpagrSeccion, Long preguntaTCREARptpatrNumero) throws SystemException {
        SQLExists sql = assocOperator000Range1Query(preguntaTCREARptpagrSeccion, preguntaTCREARptpatrNumero, null);
        return !new TypedQueryBuilder<Pregunta>(em, Pregunta.class).build(sql).execute().isEmpty();
    }

    // Range Query 1 for AssocOperator AssocOperator000
	@Override
    public SQLExists assocOperator000Range1Query (Seccion preguntaTCREARptpagrSeccion, Long preguntaTCREARptpatrNumero, SQLSelect superQuery) throws SystemException {
        try {
            SQLExists sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLExists(superQuery, PreguntaConstants.CLASS_NAME, PreguntaConstants.ARG_NAME_TCREAR_PREGUNTATCREARPTPAGRSECCION + "." + SeccionConstants.ROLE_NAME_CUESTIONARIO + "." + CuestionarioMedConstants.ROLE_NAME_SECCIONES + "." + SeccionConstants.ROLE_NAME_PREGUNTAS, SeccionConstants.CLASS_NAME, preguntaTCREARptpagrSeccion.getOid(), PreguntaConstants.ARG_NAME_TCREAR_PREGUNTATCREARPTPAGRSECCION);
            } else {
                sql = new SQLExists(PreguntaConstants.CLASS_NAME, PreguntaConstants.ARG_NAME_TCREAR_PREGUNTATCREARPTPAGRSECCION + "." + SeccionConstants.ROLE_NAME_CUESTIONARIO + "." + CuestionarioMedConstants.ROLE_NAME_SECCIONES + "." + SeccionConstants.ROLE_NAME_PREGUNTAS, SeccionConstants.CLASS_NAME, preguntaTCREARptpagrSeccion.getOid(), PreguntaConstants.ARG_NAME_TCREAR_PREGUNTATCREARPTPAGRSECCION);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // ptpagrSeccion.Cuestionario.Secciones.Preguntas.Numero = ptpatrNumero
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandOVParameterAttribute operand112 = new SQLWhereOperandOVParameterAttribute(PreguntaConstants.ARG_NAME_TCREAR_PREGUNTATCREARPTPAGRSECCION + "." + SeccionConstants.ROLE_NAME_CUESTIONARIO + "." + CuestionarioMedConstants.ROLE_NAME_SECCIONES + "." + SeccionConstants.ROLE_NAME_PREGUNTAS + "." + PreguntaConstants.ATTR_NAME_PREGUNTANUMERO, SeccionConstants.CLASS_NAME, preguntaTCREARptpagrSeccion.getOid(), PreguntaConstants.ARG_NAME_TCREAR_PREGUNTATCREARPTPAGRSECCION, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(preguntaTCREARptpatrNumero, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Pregunta, Assoc.Operator: AssocOperator000, Method: assocOperator000Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator001
	@Override
    public Collection<Valor> assocOperator001(Pregunta instance) throws SystemException {
        try {
            // FOR ALL Valores DO Valores.DelValorPregunta(Valores, this)
        return assocOperator001Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Pregunta, Assoc.Operator: AssocOperator001, Method: assocOperator001";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator001
	@Override
    public Collection<Valor> assocOperator001Range1 (Pregunta instance) throws SystemException {

        SQLSelect sql = assocOperator001Range1Query(instance);
        return new TypedQueryBuilder<Valor>(em, Valor.class).build(sql).execute();
    }

    // Range Query 1 for AssocOperator AssocOperator001
	@Override
    public SQLPopulation assocOperator001Range1Query (Pregunta instance) throws SystemException {
        try {
            return new SQLPopulation(PreguntaConstants.CLASS_NAME, ValorConstants.CLASS_NAME, instance.getOid(), PreguntaConstants.ROLE_NAME_VALORES);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Pregunta, Assoc.Operator: AssocOperator001, Method: assocOperator001Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    @Override
    public void refreshEntity(Object object) {
        super.refreshEntity(object);
    }
}
