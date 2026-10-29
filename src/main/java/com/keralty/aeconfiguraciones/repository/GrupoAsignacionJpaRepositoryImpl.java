package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.GrupoAsignacionConstants;
import com.keralty.aeconfiguraciones.global.LogGrupoAsignacionConstants;
import com.keralty.aeconfiguraciones.global.UserFunctions;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacion;
import com.keralty.aeconfiguraciones.persistence.LogGrupoAsignacion;
import com.keralty.aeconfiguraciones.sqlutils.SQLCount;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLPopulation;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.TypedQueryBuilder;
import com.keralty.aeconfiguraciones.utils.Agent;
import jakarta.inject.Inject;
import java.util.Collection;
import java.util.List;

public class GrupoAsignacionJpaRepositoryImpl extends AbstractRepository implements GrupoAsignacionJpaRepositoryCustom {

    @Inject
    public GrupoAsignacionJpaRepositoryImpl(Agent agent, UserFunctions userFunctionsSrv) {
        super(agent, userFunctionsSrv);
    }

    @Override
    public List<GrupoAsignacion> executeQuery(String jpqlQuery) {
        return getQueryBuilder().build(jpqlQuery).execute();
    }
    
    @Override
    public List<GrupoAsignacion> executeQuery(String jpqlQuery, List<SQLParam> params) {
        return getQueryBuilder().build(jpqlQuery).setParameters(params).execute();
    }
    
    public Collection<GrupoAsignacion> executeQuery(SQLSelect select) {
        return getQueryBuilder().build(select).execute();
    }

    private TypedQueryBuilder<GrupoAsignacion> getQueryBuilder() {
        return new TypedQueryBuilder<>(em, GrupoAsignacion.class);
    }

    @Override	
    public List<GrupoAsignacion> solveQuery(SQLSelect select) {
        return solveQuery(select, 0, 0);
    }

    @Override
    public List<GrupoAsignacion> solveQuery(SQLSelect select, int pageSize, int pageNumber) {
        return getQueryBuilder().build(select, pageSize, pageNumber).execute();
    }

    // AssocOperator: AssocOperator000
	@Override
    public Long assocOperator000(GrupoAsignacion instance) throws SystemException {
        try {
            // COUNT (DominioAreaMedica)
            return assocOperator000Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GrupoAsignacion, Assoc.Operator: AssocOperator000, Method: assocOperator000";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator000
	@Override
    public Long assocOperator000Range1 (GrupoAsignacion instance) throws SystemException {
        SQLCount sql = assocOperator000Range1Query(instance);
        return Long.valueOf(solveCountQuery(sql));
    }

    // Range Query 1 for AssocOperator AssocOperator000
	@Override
    public SQLCount assocOperator000Range1Query (GrupoAsignacion instance) throws SystemException {
        try {
            // The path starts with a role
            return new SQLCount(GrupoAsignacionConstants.CLASS_NAME, GrupoAsignacionConstants.ROLE_NAME_DOMINIOAREAMEDICA, instance.getOid());
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GrupoAsignacion, Assoc.Operator: AssocOperator000, Method: assocOperator000Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator001
	@Override
    public Long assocOperator001(GrupoAsignacion instance) throws SystemException {
        try {
            // COUNT (DominioAsesores)
            return assocOperator001Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GrupoAsignacion, Assoc.Operator: AssocOperator001, Method: assocOperator001";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator001
	@Override
    public Long assocOperator001Range1 (GrupoAsignacion instance) throws SystemException {
        SQLCount sql = assocOperator001Range1Query(instance);
        return Long.valueOf(solveCountQuery(sql));
    }

    // Range Query 1 for AssocOperator AssocOperator001
	@Override
    public SQLCount assocOperator001Range1Query (GrupoAsignacion instance) throws SystemException {
        try {
            // The path starts with a role
            return new SQLCount(GrupoAsignacionConstants.CLASS_NAME, GrupoAsignacionConstants.ROLE_NAME_DOMINIOASESORES, instance.getOid());
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GrupoAsignacion, Assoc.Operator: AssocOperator001, Method: assocOperator001Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator002
	@Override
    public Collection<LogGrupoAsignacion> assocOperator002(GrupoAsignacion instance) throws SystemException {
        try {
            // FOR ALL LogGrupoAsignacion DO LogGrupoAsignacion.eliminar(LogGrupoAsignacion)
        return assocOperator002Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GrupoAsignacion, Assoc.Operator: AssocOperator002, Method: assocOperator002";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator002
	@Override
    public Collection<LogGrupoAsignacion> assocOperator002Range1 (GrupoAsignacion instance) throws SystemException {

        SQLSelect sql = assocOperator002Range1Query(instance);
        return new TypedQueryBuilder<LogGrupoAsignacion>(em, LogGrupoAsignacion.class).build(sql).execute();
    }

    // Range Query 1 for AssocOperator AssocOperator002
	@Override
    public SQLPopulation assocOperator002Range1Query (GrupoAsignacion instance) throws SystemException {
        try {
            return new SQLPopulation(GrupoAsignacionConstants.CLASS_NAME, LogGrupoAsignacionConstants.CLASS_NAME, instance.getOid(), GrupoAsignacionConstants.ROLE_NAME_LOGGRUPOASIGNACION);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GrupoAsignacion, Assoc.Operator: AssocOperator002, Method: assocOperator002Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    @Override
    public void refreshEntity(Object object) {
        super.refreshEntity(object);
    }
}
