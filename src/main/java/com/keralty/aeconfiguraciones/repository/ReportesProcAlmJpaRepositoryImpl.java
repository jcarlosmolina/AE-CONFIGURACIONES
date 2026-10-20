package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.FuncionarioConstants;
import com.keralty.aeconfiguraciones.global.ParamReporteConstants;
import com.keralty.aeconfiguraciones.global.ReportesProcAlmConstants;
import com.keralty.aeconfiguraciones.global.UserFunctions;
import com.keralty.aeconfiguraciones.persistence.Funcionario;
import com.keralty.aeconfiguraciones.persistence.ParamReporte;
import com.keralty.aeconfiguraciones.persistence.ReportesProcAlm;
import com.keralty.aeconfiguraciones.sqlutils.SQLCount;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLPopulation;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereConjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandOVParameter;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandPathAttribute;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandPathInstance;
import com.keralty.aeconfiguraciones.sqlutils.TypedQueryBuilder;
import com.keralty.aeconfiguraciones.utils.Agent;
import jakarta.inject.Inject;
import java.util.Collection;
import java.util.List;

public class ReportesProcAlmJpaRepositoryImpl extends AbstractRepository implements ReportesProcAlmJpaRepositoryCustom {

    @Inject
    public ReportesProcAlmJpaRepositoryImpl(Agent agent, UserFunctions userFunctionsSrv) {
        super(agent, userFunctionsSrv);
    }

    @Override
    public List<ReportesProcAlm> executeQuery(String jpqlQuery) {
        return getQueryBuilder().build(jpqlQuery).execute();
    }
    
    @Override
    public List<ReportesProcAlm> executeQuery(String jpqlQuery, List<SQLParam> params) {
        return getQueryBuilder().build(jpqlQuery).setParameters(params).execute();
    }
    
    public Collection<ReportesProcAlm> executeQuery(SQLSelect select) {
        return getQueryBuilder().build(select).execute();
    }

    private TypedQueryBuilder<ReportesProcAlm> getQueryBuilder() {
        return new TypedQueryBuilder<>(em, ReportesProcAlm.class);
    }

    @Override	
    public List<ReportesProcAlm> solveQuery(SQLSelect select) {
        return solveQuery(select, 0, 0);
    }

    @Override
    public List<ReportesProcAlm> solveQuery(SQLSelect select, int pageSize, int pageNumber) {
        return getQueryBuilder().build(select, pageSize, pageNumber).execute();
    }

    // AssocOperator: AssocOperator000
	@Override
    public Long assocOperator000() throws SystemException {
        try {
            // COUNT (Funcionarios) WHERE (Funcionarios = agent)
            return assocOperator000Range1();
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: ReportesProcAlm, Assoc.Operator: AssocOperator000, Method: assocOperator000";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 0 for AssocOperator AssocOperator000
	@Override
    public Long assocOperator000Range1 () throws SystemException {
        SQLCount sql = assocOperator000Range1Query(null);
        return Long.valueOf(solveCountQuery(sql));
    }

    // Range Query 0 for AssocOperator AssocOperator000
	@Override
    public SQLCount assocOperator000Range1Query (SQLSelect superQuery) throws SystemException {
        try {
            // The path starts with a role
            SQLCount sql = new SQLCount(superQuery, ReportesProcAlmConstants.CLASS_NAME, ReportesProcAlmConstants.ROLE_NAME_FUNCIONARIOS);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Funcionarios = agent
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(ReportesProcAlmConstants.ROLE_NAME_FUNCIONARIOS, FuncionarioConstants.CLASS_NAME);
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(Constants.AGENTKEYWORD, FuncionarioConstants.CLASS_NAME, FuncionarioConstants.CLASS_NAME, agent.getAgentInstance().getOid(), Constants.AGENTKEYWORD);
                    comparison1.setSecondOperand(operand223);
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: ReportesProcAlm, Assoc.Operator: AssocOperator000, Method: assocOperator000Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator001
	@Override
    public Long assocOperator001(ReportesProcAlm instance) throws SystemException {
        try {
            // COUNT (Funcionarios) WHERE (Funcionarios = AGENTFuncionario)
            return assocOperator001Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: ReportesProcAlm, Assoc.Operator: AssocOperator001, Method: assocOperator001";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator001
	@Override
    public Long assocOperator001Range1 (ReportesProcAlm instance) throws SystemException {
        SQLCount sql = assocOperator001Range1Query(instance);
        return Long.valueOf(solveCountQuery(sql));
    }

    // Range Query 1 for AssocOperator AssocOperator001
	@Override
    public SQLCount assocOperator001Range1Query (ReportesProcAlm instance) throws SystemException {
        try {
            // The path starts with a role
            SQLCount sql = new SQLCount(ReportesProcAlmConstants.CLASS_NAME, ReportesProcAlmConstants.ROLE_NAME_FUNCIONARIOS, instance.getOid());
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Funcionarios = AGENTFuncionario
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(ReportesProcAlmConstants.ROLE_NAME_FUNCIONARIOS, FuncionarioConstants.CLASS_NAME);
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(Constants.AGENT_FUNCIONARIO, FuncionarioConstants.CLASS_NAME, FuncionarioConstants.CLASS_NAME, (((Funcionario)agent.getAgentInstance(FuncionarioConstants.CLASS_NAME))).getOid(), Constants.AGENT_FUNCIONARIO);
                    comparison1.setSecondOperand(operand223);
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: ReportesProcAlm, Assoc.Operator: AssocOperator001, Method: assocOperator001Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator002
	@Override
    public Collection<ParamReporte> assocOperator002(ReportesProcAlm instance, Long reportesProcAlmTCAMBIARORDENPARAMSptOrdenActual) throws SystemException {
        try {
            // FOR ALL Parametros WHERE (Parametros.Orden = ptOrdenActual) DO Parametros.cambiarOrden(Parametros, ptNuevoOrden)
        return assocOperator002Range1(instance, reportesProcAlmTCAMBIARORDENPARAMSptOrdenActual);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: ReportesProcAlm, Assoc.Operator: AssocOperator002, Method: assocOperator002";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator002
	@Override
    public Collection<ParamReporte> assocOperator002Range1 (ReportesProcAlm instance, Long reportesProcAlmTCAMBIARORDENPARAMSptOrdenActual) throws SystemException {

        SQLSelect sql = assocOperator002Range1Query(instance, reportesProcAlmTCAMBIARORDENPARAMSptOrdenActual);
        return new TypedQueryBuilder<ParamReporte>(em, ParamReporte.class).build(sql).execute();
    }

    // Range Query 1 for AssocOperator AssocOperator002
	@Override
    public SQLPopulation assocOperator002Range1Query (ReportesProcAlm instance, Long reportesProcAlmTCAMBIARORDENPARAMSptOrdenActual) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation(ReportesProcAlmConstants.CLASS_NAME, ParamReporteConstants.CLASS_NAME, instance.getOid(), ReportesProcAlmConstants.ROLE_NAME_PARAMETROS);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Parametros.Orden = ptOrdenActual
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(ReportesProcAlmConstants.ROLE_NAME_PARAMETROS + "." + ParamReporteConstants.ATTR_NAME_PARAMREPORTEORDEN, Constants.Type.NAT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(reportesProcAlmTCAMBIARORDENPARAMSptOrdenActual, Constants.Type.NAT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: ReportesProcAlm, Assoc.Operator: AssocOperator002, Method: assocOperator002Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    @Override
    public void refreshEntity(Object object) {
        super.refreshEntity(object);
    }
}
