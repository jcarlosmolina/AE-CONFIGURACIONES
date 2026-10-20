package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.TipoSucursalCConstants;
import com.keralty.aeconfiguraciones.global.UserFunctions;
import com.keralty.aeconfiguraciones.persistence.TipoSucursalC;
import com.keralty.aeconfiguraciones.sqlutils.SQLExists;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.TypedQueryBuilder;
import com.keralty.aeconfiguraciones.utils.Agent;
import jakarta.inject.Inject;
import java.util.Collection;
import java.util.List;

public class TipoSucursalCJpaRepositoryImpl extends AbstractRepository implements TipoSucursalCJpaRepositoryCustom {

    @Inject
    public TipoSucursalCJpaRepositoryImpl(Agent agent, UserFunctions userFunctionsSrv) {
        super(agent, userFunctionsSrv);
    }

    @Override
    public List<TipoSucursalC> executeQuery(String jpqlQuery) {
        return getQueryBuilder().build(jpqlQuery).execute();
    }
    
    @Override
    public List<TipoSucursalC> executeQuery(String jpqlQuery, List<SQLParam> params) {
        return getQueryBuilder().build(jpqlQuery).setParameters(params).execute();
    }
    
    public Collection<TipoSucursalC> executeQuery(SQLSelect select) {
        return getQueryBuilder().build(select).execute();
    }

    private TypedQueryBuilder<TipoSucursalC> getQueryBuilder() {
        return new TypedQueryBuilder<>(em, TipoSucursalC.class);
    }

    @Override	
    public List<TipoSucursalC> solveQuery(SQLSelect select) {
        return solveQuery(select, 0, 0);
    }

    @Override
    public List<TipoSucursalC> solveQuery(SQLSelect select, int pageSize, int pageNumber) {
        return getQueryBuilder().build(select, pageSize, pageNumber).execute();
    }

    // AssocOperator: AssocOperator000
	@Override
    public Boolean assocOperator000(TipoSucursalC instance) throws SystemException {
        try {
            // EXIST (DirSucursalesC)
            return assocOperator000Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: TipoSucursalC, Assoc.Operator: AssocOperator000, Method: assocOperator000";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator000
	@Override
    public Boolean assocOperator000Range1 (TipoSucursalC instance) throws SystemException {
        SQLExists sql = assocOperator000Range1Query(instance);
        return !new TypedQueryBuilder<TipoSucursalC>(em, TipoSucursalC.class).build(sql).execute().isEmpty();
    }

    // Range Query 1 for AssocOperator AssocOperator000
	@Override
    public SQLExists assocOperator000Range1Query (TipoSucursalC instance) throws SystemException {
        try {
            // The path starts with a role
            return new SQLExists(TipoSucursalCConstants.CLASS_NAME, TipoSucursalCConstants.ROLE_NAME_DIRSUCURSALESC, instance.getOid());
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: TipoSucursalC, Assoc.Operator: AssocOperator000, Method: assocOperator000Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    @Override
    public void refreshEntity(Object object) {
        super.refreshEntity(object);
    }
}
