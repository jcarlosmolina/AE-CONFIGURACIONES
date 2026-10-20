package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.LogPriorizacionSolConstants;
import com.keralty.aeconfiguraciones.global.PriorizacionSolicitudesConstants;
import com.keralty.aeconfiguraciones.global.UserFunctions;
import com.keralty.aeconfiguraciones.persistence.LogPriorizacionSol;
import com.keralty.aeconfiguraciones.persistence.PriorizacionSolicitudes;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLPopulation;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.TypedQueryBuilder;
import com.keralty.aeconfiguraciones.utils.Agent;
import jakarta.inject.Inject;
import java.util.Collection;
import java.util.List;

public class PriorizacionSolicitudesJpaRepositoryImpl extends AbstractRepository implements PriorizacionSolicitudesJpaRepositoryCustom {

    @Inject
    public PriorizacionSolicitudesJpaRepositoryImpl(Agent agent, UserFunctions userFunctionsSrv) {
        super(agent, userFunctionsSrv);
    }

    @Override
    public List<PriorizacionSolicitudes> executeQuery(String jpqlQuery) {
        return getQueryBuilder().build(jpqlQuery).execute();
    }
    
    @Override
    public List<PriorizacionSolicitudes> executeQuery(String jpqlQuery, List<SQLParam> params) {
        return getQueryBuilder().build(jpqlQuery).setParameters(params).execute();
    }
    
    public Collection<PriorizacionSolicitudes> executeQuery(SQLSelect select) {
        return getQueryBuilder().build(select).execute();
    }

    private TypedQueryBuilder<PriorizacionSolicitudes> getQueryBuilder() {
        return new TypedQueryBuilder<>(em, PriorizacionSolicitudes.class);
    }

    @Override	
    public List<PriorizacionSolicitudes> solveQuery(SQLSelect select) {
        return solveQuery(select, 0, 0);
    }

    @Override
    public List<PriorizacionSolicitudes> solveQuery(SQLSelect select, int pageSize, int pageNumber) {
        return getQueryBuilder().build(select, pageSize, pageNumber).execute();
    }

    // AssocOperator: AssocOperator000
	@Override
    public Collection<LogPriorizacionSol> assocOperator000(PriorizacionSolicitudes instance) throws SystemException {
        try {
            // FOR ALL LogPriorizacionSol DO LogPriorizacionSol.eliminar(LogPriorizacionSol)
        return assocOperator000Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: PriorizacionSolicitudes, Assoc.Operator: AssocOperator000, Method: assocOperator000";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator000
	@Override
    public Collection<LogPriorizacionSol> assocOperator000Range1 (PriorizacionSolicitudes instance) throws SystemException {

        SQLSelect sql = assocOperator000Range1Query(instance);
        return new TypedQueryBuilder<LogPriorizacionSol>(em, LogPriorizacionSol.class).build(sql).execute();
    }

    // Range Query 1 for AssocOperator AssocOperator000
	@Override
    public SQLPopulation assocOperator000Range1Query (PriorizacionSolicitudes instance) throws SystemException {
        try {
            return new SQLPopulation(PriorizacionSolicitudesConstants.CLASS_NAME, LogPriorizacionSolConstants.CLASS_NAME, instance.getOid(), PriorizacionSolicitudesConstants.ROLE_NAME_LOGPRIORIZACIONSOL);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: PriorizacionSolicitudes, Assoc.Operator: AssocOperator000, Method: assocOperator000Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    @Override
    public void refreshEntity(Object object) {
        super.refreshEntity(object);
    }
}
