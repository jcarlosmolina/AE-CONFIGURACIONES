package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.CausalConstants;
import com.keralty.aeconfiguraciones.global.ConfiguracionANSConstants;
import com.keralty.aeconfiguraciones.global.DominioConstants;
import com.keralty.aeconfiguraciones.global.GrupoAsignacionNovedadConstants;
import com.keralty.aeconfiguraciones.global.NovedadConstants;
import com.keralty.aeconfiguraciones.global.PlanesConstants;
import com.keralty.aeconfiguraciones.global.SolicitudNovedadConstants;
import com.keralty.aeconfiguraciones.global.UserFunctions;
import com.keralty.aeconfiguraciones.persistence.Causal;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.Novedad;
import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.sqlutils.SQLCount;
import com.keralty.aeconfiguraciones.sqlutils.SQLExists;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLPopulation;
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

public class NovedadJpaRepositoryImpl extends AbstractRepository implements NovedadJpaRepositoryCustom {

    @Inject
    public NovedadJpaRepositoryImpl(Agent agent, UserFunctions userFunctionsSrv) {
        super(agent, userFunctionsSrv);
    }

    @Override
    public List<Novedad> executeQuery(String jpqlQuery) {
        return getQueryBuilder().build(jpqlQuery).execute();
    }
    
    @Override
    public List<Novedad> executeQuery(String jpqlQuery, List<SQLParam> params) {
        return getQueryBuilder().build(jpqlQuery).setParameters(params).execute();
    }
    
    public Collection<Novedad> executeQuery(SQLSelect select) {
        return getQueryBuilder().build(select).execute();
    }

    private TypedQueryBuilder<Novedad> getQueryBuilder() {
        return new TypedQueryBuilder<>(em, Novedad.class);
    }

    @Override	
    public List<Novedad> solveQuery(SQLSelect select) {
        return solveQuery(select, 0, 0);
    }

    @Override
    public List<Novedad> solveQuery(SQLSelect select, int pageSize, int pageNumber) {
        return getQueryBuilder().build(select, pageSize, pageNumber).execute();
    }

    // AssocOperator: AssocOperator000
	@Override
    public Long assocOperator000(Novedad instance) throws SystemException {
        try {
            // COUNT (Causales)
            return assocOperator000Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Novedad, Assoc.Operator: AssocOperator000, Method: assocOperator000";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator000
	@Override
    public Long assocOperator000Range1 (Novedad instance) throws SystemException {
        SQLCount sql = assocOperator000Range1Query(instance);
        return Long.valueOf(solveCountQuery(sql));
    }

    // Range Query 1 for AssocOperator AssocOperator000
	@Override
    public SQLCount assocOperator000Range1Query (Novedad instance) throws SystemException {
        try {
            // The path starts with a role
            return new SQLCount(NovedadConstants.CLASS_NAME, NovedadConstants.ROLE_NAME_CAUSALES, instance.getOid());
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Novedad, Assoc.Operator: AssocOperator000, Method: assocOperator000Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator001
	@Override
    public Long assocOperator001(Novedad instance) throws SystemException {
        try {
            // COUNT (CausasExclusion)
            return assocOperator001Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Novedad, Assoc.Operator: AssocOperator001, Method: assocOperator001";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator001
	@Override
    public Long assocOperator001Range1 (Novedad instance) throws SystemException {
        SQLCount sql = assocOperator001Range1Query(instance);
        return Long.valueOf(solveCountQuery(sql));
    }

    // Range Query 1 for AssocOperator AssocOperator001
	@Override
    public SQLCount assocOperator001Range1Query (Novedad instance) throws SystemException {
        try {
            // The path starts with a role
            return new SQLCount(NovedadConstants.CLASS_NAME, NovedadConstants.ROLE_NAME_CAUSASEXCLUSION, instance.getOid());
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Novedad, Assoc.Operator: AssocOperator001, Method: assocOperator001Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator002
	@Override
    public Collection<Causal> assocOperator002(Novedad instance) throws SystemException {
        try {
            // FOR ALL Causales DO Causales.eliminar(Causales)
        return assocOperator002Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Novedad, Assoc.Operator: AssocOperator002, Method: assocOperator002";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator002
	@Override
    public Collection<Causal> assocOperator002Range1 (Novedad instance) throws SystemException {

        SQLSelect sql = assocOperator002Range1Query(instance);
        return new TypedQueryBuilder<Causal>(em, Causal.class).build(sql).execute();
    }

    // Range Query 1 for AssocOperator AssocOperator002
	@Override
    public SQLPopulation assocOperator002Range1Query (Novedad instance) throws SystemException {
        try {
            return new SQLPopulation(NovedadConstants.CLASS_NAME, CausalConstants.CLASS_NAME, instance.getOid(), NovedadConstants.ROLE_NAME_CAUSALES);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Novedad, Assoc.Operator: AssocOperator002, Method: assocOperator002Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator003
	@Override
    public Boolean assocOperator003(Planes solicitudNovedadTCREARptpagrPlan) throws SystemException {
        try {
            // EXIST (Dominios) WHERE (Dominios.PlanT = ptpagrPlan)
            return assocOperator003Range1(solicitudNovedadTCREARptpagrPlan);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Novedad, Assoc.Operator: AssocOperator003, Method: assocOperator003";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 0 for AssocOperator AssocOperator003
	@Override
    public Boolean assocOperator003Range1 (Planes solicitudNovedadTCREARptpagrPlan) throws SystemException {
        SQLExists sql = assocOperator003Range1Query(solicitudNovedadTCREARptpagrPlan, null);
        return !new TypedQueryBuilder<Novedad>(em, Novedad.class).build(sql).execute().isEmpty();
    }

    // Range Query 0 for AssocOperator AssocOperator003
	@Override
    public SQLExists assocOperator003Range1Query (Planes solicitudNovedadTCREARptpagrPlan, SQLSelect superQuery) throws SystemException {
        try {
            // The path starts with a role
            SQLExists sql = new SQLExists(superQuery, NovedadConstants.CLASS_NAME, NovedadConstants.ROLE_NAME_DOMINIOS);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Dominios.PlanT = ptpagrPlan
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(NovedadConstants.ROLE_NAME_DOMINIOS + "." + DominioConstants.ROLE_NAME_PLANT, PlanesConstants.CLASS_NAME);
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(SolicitudNovedadConstants.ARG_NAME_TCREAR_SOLICITUDNOVEDADTCREARPTPAGRPLAN, PlanesConstants.CLASS_NAME, PlanesConstants.CLASS_NAME, solicitudNovedadTCREARptpagrPlan.getOid(), SolicitudNovedadConstants.ARG_NAME_TCREAR_SOLICITUDNOVEDADTCREARPTPAGRPLAN);
                    comparison1.setSecondOperand(operand223);
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Novedad, Assoc.Operator: AssocOperator003, Method: assocOperator003Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator004
	@Override
    public Boolean assocOperator004(Planes solicitudNovedadTCREAREXCEPCIONptpagrPlan) throws SystemException {
        try {
            // EXIST (Dominios) WHERE (Dominios.PlanT = ptpagrPlan)
            return assocOperator004Range1(solicitudNovedadTCREAREXCEPCIONptpagrPlan);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Novedad, Assoc.Operator: AssocOperator004, Method: assocOperator004";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 0 for AssocOperator AssocOperator004
	@Override
    public Boolean assocOperator004Range1 (Planes solicitudNovedadTCREAREXCEPCIONptpagrPlan) throws SystemException {
        SQLExists sql = assocOperator004Range1Query(solicitudNovedadTCREAREXCEPCIONptpagrPlan, null);
        return !new TypedQueryBuilder<Novedad>(em, Novedad.class).build(sql).execute().isEmpty();
    }

    // Range Query 0 for AssocOperator AssocOperator004
	@Override
    public SQLExists assocOperator004Range1Query (Planes solicitudNovedadTCREAREXCEPCIONptpagrPlan, SQLSelect superQuery) throws SystemException {
        try {
            // The path starts with a role
            SQLExists sql = new SQLExists(superQuery, NovedadConstants.CLASS_NAME, NovedadConstants.ROLE_NAME_DOMINIOS);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Dominios.PlanT = ptpagrPlan
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(NovedadConstants.ROLE_NAME_DOMINIOS + "." + DominioConstants.ROLE_NAME_PLANT, PlanesConstants.CLASS_NAME);
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(SolicitudNovedadConstants.ARG_NAME_TCREAREXCEPCION_SOLICITUDNOVEDADTCREAREXCEPCIONPTPAGRPLAN, PlanesConstants.CLASS_NAME, PlanesConstants.CLASS_NAME, solicitudNovedadTCREAREXCEPCIONptpagrPlan.getOid(), SolicitudNovedadConstants.ARG_NAME_TCREAREXCEPCION_SOLICITUDNOVEDADTCREAREXCEPCIONPTPAGRPLAN);
                    comparison1.setSecondOperand(operand223);
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Novedad, Assoc.Operator: AssocOperator004, Method: assocOperator004Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator005
	@Override
    public Boolean assocOperator005(Dominio grupoAsignacionNovedadTCREARptpagrDominio) throws SystemException {
        try {
            // EXIST (Dominios) WHERE (Dominios = ptpagrDominio)
            return assocOperator005Range1(grupoAsignacionNovedadTCREARptpagrDominio);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Novedad, Assoc.Operator: AssocOperator005, Method: assocOperator005";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 0 for AssocOperator AssocOperator005
	@Override
    public Boolean assocOperator005Range1 (Dominio grupoAsignacionNovedadTCREARptpagrDominio) throws SystemException {
        SQLExists sql = assocOperator005Range1Query(grupoAsignacionNovedadTCREARptpagrDominio, null);
        return !new TypedQueryBuilder<Novedad>(em, Novedad.class).build(sql).execute().isEmpty();
    }

    // Range Query 0 for AssocOperator AssocOperator005
	@Override
    public SQLExists assocOperator005Range1Query (Dominio grupoAsignacionNovedadTCREARptpagrDominio, SQLSelect superQuery) throws SystemException {
        try {
            // The path starts with a role
            SQLExists sql = new SQLExists(superQuery, NovedadConstants.CLASS_NAME, NovedadConstants.ROLE_NAME_DOMINIOS);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Dominios = ptpagrDominio
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(NovedadConstants.ROLE_NAME_DOMINIOS, DominioConstants.CLASS_NAME);
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(GrupoAsignacionNovedadConstants.ARG_NAME_TCREAR_GRUPOASIGNACIONNOVEDADTCREARPTPAGRDOMINIO, DominioConstants.CLASS_NAME, DominioConstants.CLASS_NAME, grupoAsignacionNovedadTCREARptpagrDominio.getOid(), GrupoAsignacionNovedadConstants.ARG_NAME_TCREAR_GRUPOASIGNACIONNOVEDADTCREARPTPAGRDOMINIO);
                    comparison1.setSecondOperand(operand223);
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Novedad, Assoc.Operator: AssocOperator005, Method: assocOperator005Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator006
	@Override
    public Boolean assocOperator006(Dominio configuracionANSTCREARptpagrDominio) throws SystemException {
        try {
            // EXIST (Dominios) WHERE (Dominios = ptpagrDominio)
            return assocOperator006Range1(configuracionANSTCREARptpagrDominio);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Novedad, Assoc.Operator: AssocOperator006, Method: assocOperator006";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 0 for AssocOperator AssocOperator006
	@Override
    public Boolean assocOperator006Range1 (Dominio configuracionANSTCREARptpagrDominio) throws SystemException {
        SQLExists sql = assocOperator006Range1Query(configuracionANSTCREARptpagrDominio, null);
        return !new TypedQueryBuilder<Novedad>(em, Novedad.class).build(sql).execute().isEmpty();
    }

    // Range Query 0 for AssocOperator AssocOperator006
	@Override
    public SQLExists assocOperator006Range1Query (Dominio configuracionANSTCREARptpagrDominio, SQLSelect superQuery) throws SystemException {
        try {
            // The path starts with a role
            SQLExists sql = new SQLExists(superQuery, NovedadConstants.CLASS_NAME, NovedadConstants.ROLE_NAME_DOMINIOS);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Dominios = ptpagrDominio
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(NovedadConstants.ROLE_NAME_DOMINIOS, DominioConstants.CLASS_NAME);
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(ConfiguracionANSConstants.ARG_NAME_TCREAR_CONFIGURACIONANSTCREARPTPAGRDOMINIO, DominioConstants.CLASS_NAME, DominioConstants.CLASS_NAME, configuracionANSTCREARptpagrDominio.getOid(), ConfiguracionANSConstants.ARG_NAME_TCREAR_CONFIGURACIONANSTCREARPTPAGRDOMINIO);
                    comparison1.setSecondOperand(operand223);
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Novedad, Assoc.Operator: AssocOperator006, Method: assocOperator006Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    @Override
    public void refreshEntity(Object object) {
        super.refreshEntity(object);
    }
}
