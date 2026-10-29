package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.CausalConstants;
import com.keralty.aeconfiguraciones.global.DominioConstants;
import com.keralty.aeconfiguraciones.global.GrupoAsignacionNovedadConstants;
import com.keralty.aeconfiguraciones.global.UserFunctions;
import com.keralty.aeconfiguraciones.persistence.Causal;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacionNovedad;
import com.keralty.aeconfiguraciones.sqlutils.SQLCount;
import com.keralty.aeconfiguraciones.sqlutils.SQLExists;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereConjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandOVParameter;
import com.keralty.aeconfiguraciones.sqlutils.TypedQueryBuilder;
import com.keralty.aeconfiguraciones.utils.Agent;
import jakarta.inject.Inject;
import java.util.Collection;
import java.util.List;

public class GrupoAsignacionNovedadJpaRepositoryImpl extends AbstractRepository implements GrupoAsignacionNovedadJpaRepositoryCustom {

    @Inject
    public GrupoAsignacionNovedadJpaRepositoryImpl(Agent agent, UserFunctions userFunctionsSrv) {
        super(agent, userFunctionsSrv);
    }

    @Override
    public List<GrupoAsignacionNovedad> executeQuery(String jpqlQuery) {
        return getQueryBuilder().build(jpqlQuery).execute();
    }
    
    @Override
    public List<GrupoAsignacionNovedad> executeQuery(String jpqlQuery, List<SQLParam> params) {
        return getQueryBuilder().build(jpqlQuery).setParameters(params).execute();
    }
    
    public Collection<GrupoAsignacionNovedad> executeQuery(SQLSelect select) {
        return getQueryBuilder().build(select).execute();
    }

    private TypedQueryBuilder<GrupoAsignacionNovedad> getQueryBuilder() {
        return new TypedQueryBuilder<>(em, GrupoAsignacionNovedad.class);
    }

    @Override	
    public List<GrupoAsignacionNovedad> solveQuery(SQLSelect select) {
        return solveQuery(select, 0, 0);
    }

    @Override
    public List<GrupoAsignacionNovedad> solveQuery(SQLSelect select, int pageSize, int pageNumber) {
        return getQueryBuilder().build(select, pageSize, pageNumber).execute();
    }

    // AssocOperator: AssocOperator000
	@Override
    public Long assocOperator000(Dominio grupoAsignacionNovedadTCREARptpagrDominio, Causal grupoAsignacionNovedadTCREARptpagrCausal) throws SystemException {
        try {
            // COUNT (ptpagrDominio.GrupoAsignacionNovedad) WHERE (ptpagrDominio.GrupoAsignacionNovedad.Causal = ptpagrCausal)
            return assocOperator000Range1(grupoAsignacionNovedadTCREARptpagrDominio, grupoAsignacionNovedadTCREARptpagrCausal);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GrupoAsignacionNovedad, Assoc.Operator: AssocOperator000, Method: assocOperator000";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator000
	@Override
    public Long assocOperator000Range1 (Dominio grupoAsignacionNovedadTCREARptpagrDominio, Causal grupoAsignacionNovedadTCREARptpagrCausal) throws SystemException {
        SQLCount sql = assocOperator000Range1Query(grupoAsignacionNovedadTCREARptpagrDominio, grupoAsignacionNovedadTCREARptpagrCausal, null);
        return Long.valueOf(solveCountQuery(sql));
    }

    // Range Query 1 for AssocOperator AssocOperator000
	@Override
    public SQLCount assocOperator000Range1Query (Dominio grupoAsignacionNovedadTCREARptpagrDominio, Causal grupoAsignacionNovedadTCREARptpagrCausal, SQLSelect superQuery) throws SystemException {
        try {
            SQLCount sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLCount(superQuery, GrupoAsignacionNovedadConstants.CLASS_NAME, GrupoAsignacionNovedadConstants.ARG_NAME_TCREAR_GRUPOASIGNACIONNOVEDADTCREARPTPAGRDOMINIO + "." + DominioConstants.ROLE_NAME_GRUPOASIGNACIONNOVEDAD, DominioConstants.CLASS_NAME, grupoAsignacionNovedadTCREARptpagrDominio.getOid(), GrupoAsignacionNovedadConstants.ARG_NAME_TCREAR_GRUPOASIGNACIONNOVEDADTCREARPTPAGRDOMINIO);
            } else {
                sql = new SQLCount(GrupoAsignacionNovedadConstants.CLASS_NAME, GrupoAsignacionNovedadConstants.ARG_NAME_TCREAR_GRUPOASIGNACIONNOVEDADTCREARPTPAGRDOMINIO + "." + DominioConstants.ROLE_NAME_GRUPOASIGNACIONNOVEDAD, DominioConstants.CLASS_NAME, grupoAsignacionNovedadTCREARptpagrDominio.getOid(), GrupoAsignacionNovedadConstants.ARG_NAME_TCREAR_GRUPOASIGNACIONNOVEDADTCREARPTPAGRDOMINIO);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // ptpagrDominio.GrupoAsignacionNovedad.Causal = ptpagrCausal
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandOVParameter operand112 = new SQLWhereOperandOVParameter(GrupoAsignacionNovedadConstants.ARG_NAME_TCREAR_GRUPOASIGNACIONNOVEDADTCREARPTPAGRDOMINIO + "." + DominioConstants.ROLE_NAME_GRUPOASIGNACIONNOVEDAD + "." + GrupoAsignacionNovedadConstants.ROLE_NAME_CAUSAL, DominioConstants.CLASS_NAME, CausalConstants.CLASS_NAME, grupoAsignacionNovedadTCREARptpagrDominio.getOid(), GrupoAsignacionNovedadConstants.ARG_NAME_TCREAR_GRUPOASIGNACIONNOVEDADTCREARPTPAGRDOMINIO);
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(GrupoAsignacionNovedadConstants.ARG_NAME_TCREAR_GRUPOASIGNACIONNOVEDADTCREARPTPAGRCAUSAL, CausalConstants.CLASS_NAME, CausalConstants.CLASS_NAME, grupoAsignacionNovedadTCREARptpagrCausal.getOid(), GrupoAsignacionNovedadConstants.ARG_NAME_TCREAR_GRUPOASIGNACIONNOVEDADTCREARPTPAGRCAUSAL);
                    comparison1.setSecondOperand(operand223);
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GrupoAsignacionNovedad, Assoc.Operator: AssocOperator000, Method: assocOperator000Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator002
	@Override
    public Boolean assocOperator002(GrupoAsignacionNovedad instance) throws SystemException {
        try {
            // EXIST (GrupoUsuarios)
            return assocOperator002Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GrupoAsignacionNovedad, Assoc.Operator: AssocOperator002, Method: assocOperator002";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator002
	@Override
    public Boolean assocOperator002Range1 (GrupoAsignacionNovedad instance) throws SystemException {
        SQLExists sql = assocOperator002Range1Query(instance);
        return !new TypedQueryBuilder<GrupoAsignacionNovedad>(em, GrupoAsignacionNovedad.class).build(sql).execute().isEmpty();
    }

    // Range Query 1 for AssocOperator AssocOperator002
	@Override
    public SQLExists assocOperator002Range1Query (GrupoAsignacionNovedad instance) throws SystemException {
        try {
            // The path starts with a role
            return new SQLExists(GrupoAsignacionNovedadConstants.CLASS_NAME, GrupoAsignacionNovedadConstants.ROLE_NAME_GRUPOUSUARIOS, instance.getOid());
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GrupoAsignacionNovedad, Assoc.Operator: AssocOperator002, Method: assocOperator002Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    @Override
    public void refreshEntity(Object object) {
        super.refreshEntity(object);
    }
}
