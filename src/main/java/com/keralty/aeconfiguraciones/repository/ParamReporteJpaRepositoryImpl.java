package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.ParamReporteConstants;
import com.keralty.aeconfiguraciones.global.ReportesProcAlmConstants;
import com.keralty.aeconfiguraciones.global.UserFunctions;
import com.keralty.aeconfiguraciones.persistence.ParamReporte;
import com.keralty.aeconfiguraciones.persistence.ReportesProcAlm;
import com.keralty.aeconfiguraciones.sqlutils.SQLCount;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLPopulation;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereConjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandNull;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandPathAttribute;
import com.keralty.aeconfiguraciones.sqlutils.TypedQueryBuilder;
import com.keralty.aeconfiguraciones.utils.Agent;
import jakarta.inject.Inject;
import java.util.Collection;
import java.util.List;

public class ParamReporteJpaRepositoryImpl extends AbstractRepository implements ParamReporteJpaRepositoryCustom {

    @Inject
    public ParamReporteJpaRepositoryImpl(Agent agent, UserFunctions userFunctionsSrv) {
        super(agent, userFunctionsSrv);
    }

    @Override
    public List<ParamReporte> executeQuery(String jpqlQuery) {
        return getQueryBuilder().build(jpqlQuery).execute();
    }
    
    @Override
    public List<ParamReporte> executeQuery(String jpqlQuery, List<SQLParam> params) {
        return getQueryBuilder().build(jpqlQuery).setParameters(params).execute();
    }
    
    public Collection<ParamReporte> executeQuery(SQLSelect select) {
        return getQueryBuilder().build(select).execute();
    }

    private TypedQueryBuilder<ParamReporte> getQueryBuilder() {
        return new TypedQueryBuilder<>(em, ParamReporte.class);
    }

    @Override	
    public List<ParamReporte> solveQuery(SQLSelect select) {
        return solveQuery(select, 0, 0);
    }

    @Override
    public List<ParamReporte> solveQuery(SQLSelect select, int pageSize, int pageNumber) {
        return getQueryBuilder().build(select, pageSize, pageNumber).execute();
    }

    // AssocOperator: AssocOperator000
	@Override
    public Long assocOperator000(ParamReporte instance) throws SystemException {
        try {
            // COUNT (Reporte.Parametros)
            return assocOperator000Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: ParamReporte, Assoc.Operator: AssocOperator000, Method: assocOperator000";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator000
	@Override
    public Long assocOperator000Range1 (ParamReporte instance) throws SystemException {
        SQLCount sql = assocOperator000Range1Query(instance);
        return Long.valueOf(solveCountQuery(sql));
    }

    // Range Query 1 for AssocOperator AssocOperator000
	@Override
    public SQLCount assocOperator000Range1Query (ParamReporte instance) throws SystemException {
        try {
            // The path starts with a role
            return new SQLCount(ParamReporteConstants.CLASS_NAME, ParamReporteConstants.ROLE_NAME_REPORTE + "." + ReportesProcAlmConstants.ROLE_NAME_PARAMETROS, instance.getOid());
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: ParamReporte, Assoc.Operator: AssocOperator000, Method: assocOperator000Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator001
	@Override
    public Long assocOperator001(ParamReporte instance) throws SystemException {
        try {
            // COUNT (Reporte.Parametros)
            return assocOperator001Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: ParamReporte, Assoc.Operator: AssocOperator001, Method: assocOperator001";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator001
	@Override
    public Long assocOperator001Range1 (ParamReporte instance) throws SystemException {
        SQLCount sql = assocOperator001Range1Query(instance);
        return Long.valueOf(solveCountQuery(sql));
    }

    // Range Query 1 for AssocOperator AssocOperator001
	@Override
    public SQLCount assocOperator001Range1Query (ParamReporte instance) throws SystemException {
        try {
            // The path starts with a role
            return new SQLCount(ParamReporteConstants.CLASS_NAME, ParamReporteConstants.ROLE_NAME_REPORTE + "." + ReportesProcAlmConstants.ROLE_NAME_PARAMETROS, instance.getOid());
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: ParamReporte, Assoc.Operator: AssocOperator001, Method: assocOperator001Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator003
	@Override
    public Long assocOperator003(ReportesProcAlm paramReporteTNEWptpagrReporte) throws SystemException {
        try {
            // COUNT (ptpagrReporte.Parametros)
            return assocOperator003Range1(paramReporteTNEWptpagrReporte);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: ParamReporte, Assoc.Operator: AssocOperator003, Method: assocOperator003";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator003
	@Override
    public Long assocOperator003Range1 (ReportesProcAlm paramReporteTNEWptpagrReporte) throws SystemException {
        SQLCount sql = assocOperator003Range1Query(paramReporteTNEWptpagrReporte, null);
        return Long.valueOf(solveCountQuery(sql));
    }

    // Range Query 1 for AssocOperator AssocOperator003
	@Override
    public SQLCount assocOperator003Range1Query (ReportesProcAlm paramReporteTNEWptpagrReporte, SQLSelect superQuery) throws SystemException {
        try {
            SQLCount sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLCount(superQuery, ParamReporteConstants.CLASS_NAME, ParamReporteConstants.ARG_NAME_TNEW_PARAMREPORTETNEWPTPAGRREPORTE + "." + ReportesProcAlmConstants.ROLE_NAME_PARAMETROS, ReportesProcAlmConstants.CLASS_NAME, paramReporteTNEWptpagrReporte.getOid(), ParamReporteConstants.ARG_NAME_TNEW_PARAMREPORTETNEWPTPAGRREPORTE);
            } else {
                sql = new SQLCount(ParamReporteConstants.CLASS_NAME, ParamReporteConstants.ARG_NAME_TNEW_PARAMREPORTETNEWPTPAGRREPORTE + "." + ReportesProcAlmConstants.ROLE_NAME_PARAMETROS, ReportesProcAlmConstants.CLASS_NAME, paramReporteTNEWptpagrReporte.getOid(), ParamReporteConstants.ARG_NAME_TNEW_PARAMREPORTETNEWPTPAGRREPORTE);
            }
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: ParamReporte, Assoc.Operator: AssocOperator003, Method: assocOperator003Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator004
	@Override
    public Collection<ParamReporte> assocOperator004(ParamReporte instance) throws SystemException {
        try {
            // FOR ALL Reporte.Parametros WHERE (Reporte.Parametros.Orden > Orden) DO Reporte.Parametros.cambiarOrden(Reporte.Parametros, Reporte.Parametros.Orden - 1)
        return assocOperator004Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: ParamReporte, Assoc.Operator: AssocOperator004, Method: assocOperator004";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator004
	@Override
    public Collection<ParamReporte> assocOperator004Range1 (ParamReporte instance) throws SystemException {

        SQLSelect sql = assocOperator004Range1Query(instance);
        return new TypedQueryBuilder<ParamReporte>(em, ParamReporte.class).build(sql).execute();
    }

    // Range Query 1 for AssocOperator AssocOperator004
	@Override
    public SQLPopulation assocOperator004Range1Query (ParamReporte instance) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation(ParamReporteConstants.CLASS_NAME, ParamReporteConstants.CLASS_NAME, instance.getOid(), ParamReporteConstants.ROLE_NAME_REPORTE + "." + ReportesProcAlmConstants.ROLE_NAME_PARAMETROS);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Reporte.Parametros.Orden > Orden
                    // '>' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator(">");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(ParamReporteConstants.ROLE_NAME_REPORTE + "." + ReportesProcAlmConstants.ROLE_NAME_PARAMETROS + "." + ParamReporteConstants.ATTR_NAME_PARAMREPORTEORDEN, Constants.Type.NAT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    if (instance.getParamReporteOrden()!= null){
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(instance.getParamReporteOrden(), Constants.Type.NAT.getTypeName()));
                    } else {
                    comparison1.setSecondOperand(new SQLWhereOperandNull());
                    }
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: ParamReporte, Assoc.Operator: AssocOperator004, Method: assocOperator004Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    @Override
    public void refreshEntity(Object object) {
        super.refreshEntity(object);
    }
}
