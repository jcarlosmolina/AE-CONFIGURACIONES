package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.DominioConstants;
import com.keralty.aeconfiguraciones.global.PlantillaReporteConstants;
import com.keralty.aeconfiguraciones.global.ReportesDominioConstants;
import com.keralty.aeconfiguraciones.global.UserFunctions;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.PlantillaReporte;
import com.keralty.aeconfiguraciones.sqlutils.SQLCount;
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

public class PlantillaReporteJpaRepositoryImpl extends AbstractRepository implements PlantillaReporteJpaRepositoryCustom {

    @Inject
    public PlantillaReporteJpaRepositoryImpl(Agent agent, UserFunctions userFunctionsSrv) {
        super(agent, userFunctionsSrv);
    }

    @Override
    public List<PlantillaReporte> executeQuery(String jpqlQuery) {
        return getQueryBuilder().build(jpqlQuery).execute();
    }
    
    @Override
    public List<PlantillaReporte> executeQuery(String jpqlQuery, List<SQLParam> params) {
        return getQueryBuilder().build(jpqlQuery).setParameters(params).execute();
    }
    
    public Collection<PlantillaReporte> executeQuery(SQLSelect select) {
        return getQueryBuilder().build(select).execute();
    }

    private TypedQueryBuilder<PlantillaReporte> getQueryBuilder() {
        return new TypedQueryBuilder<>(em, PlantillaReporte.class);
    }

    @Override	
    public List<PlantillaReporte> solveQuery(SQLSelect select) {
        return solveQuery(select, 0, 0);
    }

    @Override
    public List<PlantillaReporte> solveQuery(SQLSelect select, int pageSize, int pageNumber) {
        return getQueryBuilder().build(select, pageSize, pageNumber).execute();
    }

    // AssocOperator: AssocOperator000
	@Override
    public Long assocOperator000(Dominio dominioTADDREPORTEpthisDominio) throws SystemException {
        try {
            // COUNT (ReportesDominio.Dominio) WHERE (ReportesDominio.Dominio = pthisDominio)
            return assocOperator000Range1(dominioTADDREPORTEpthisDominio);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: PlantillaReporte, Assoc.Operator: AssocOperator000, Method: assocOperator000";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 0 for AssocOperator AssocOperator000
	@Override
    public Long assocOperator000Range1 (Dominio dominioTADDREPORTEpthisDominio) throws SystemException {
        SQLCount sql = assocOperator000Range1Query(dominioTADDREPORTEpthisDominio, null);
        return Long.valueOf(solveCountQuery(sql));
    }

    // Range Query 0 for AssocOperator AssocOperator000
	@Override
    public SQLCount assocOperator000Range1Query (Dominio dominioTADDREPORTEpthisDominio, SQLSelect superQuery) throws SystemException {
        try {
            // The path starts with a role
            SQLCount sql = new SQLCount(superQuery, PlantillaReporteConstants.CLASS_NAME, PlantillaReporteConstants.ROLE_NAME_REPORTESDOMINIO + "." + ReportesDominioConstants.ROLE_NAME_DOMINIO);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // ReportesDominio.Dominio = pthisDominio
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(PlantillaReporteConstants.ROLE_NAME_REPORTESDOMINIO + "." + ReportesDominioConstants.ROLE_NAME_DOMINIO, DominioConstants.CLASS_NAME);
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(DominioConstants.ARG_NAME_TADDREPORTE_DOMINIOTADDREPORTEPTHISDOMINIO, DominioConstants.CLASS_NAME, DominioConstants.CLASS_NAME, dominioTADDREPORTEpthisDominio.getOid(), DominioConstants.ARG_NAME_TADDREPORTE_DOMINIOTADDREPORTEPTHISDOMINIO);
                    comparison1.setSecondOperand(operand223);
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: PlantillaReporte, Assoc.Operator: AssocOperator000, Method: assocOperator000Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator001
	@Override
    public Long assocOperator001(PlantillaReporte instance) throws SystemException {
        try {
            // COUNT (ReportesDominio)
            return assocOperator001Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: PlantillaReporte, Assoc.Operator: AssocOperator001, Method: assocOperator001";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator001
	@Override
    public Long assocOperator001Range1 (PlantillaReporte instance) throws SystemException {
        SQLCount sql = assocOperator001Range1Query(instance);
        return Long.valueOf(solveCountQuery(sql));
    }

    // Range Query 1 for AssocOperator AssocOperator001
	@Override
    public SQLCount assocOperator001Range1Query (PlantillaReporte instance) throws SystemException {
        try {
            // The path starts with a role
            return new SQLCount(PlantillaReporteConstants.CLASS_NAME, PlantillaReporteConstants.ROLE_NAME_REPORTESDOMINIO, instance.getOid());
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: PlantillaReporte, Assoc.Operator: AssocOperator001, Method: assocOperator001Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator002
	@Override
    public Long assocOperator002(PlantillaReporte instance) throws SystemException {
        try {
            // COUNT (ReportesDominio)
            return assocOperator002Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: PlantillaReporte, Assoc.Operator: AssocOperator002, Method: assocOperator002";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator002
	@Override
    public Long assocOperator002Range1 (PlantillaReporte instance) throws SystemException {
        SQLCount sql = assocOperator002Range1Query(instance);
        return Long.valueOf(solveCountQuery(sql));
    }

    // Range Query 1 for AssocOperator AssocOperator002
	@Override
    public SQLCount assocOperator002Range1Query (PlantillaReporte instance) throws SystemException {
        try {
            // The path starts with a role
            return new SQLCount(PlantillaReporteConstants.CLASS_NAME, PlantillaReporteConstants.ROLE_NAME_REPORTESDOMINIO, instance.getOid());
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: PlantillaReporte, Assoc.Operator: AssocOperator002, Method: assocOperator002Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    @Override
    public void refreshEntity(Object object) {
        super.refreshEntity(object);
    }
}
