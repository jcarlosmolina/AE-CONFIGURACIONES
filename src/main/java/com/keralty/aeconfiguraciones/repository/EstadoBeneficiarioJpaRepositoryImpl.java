package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.EstadoBeneficiarioConstants;
import com.keralty.aeconfiguraciones.global.UserFunctions;
import com.keralty.aeconfiguraciones.persistence.EstadoBeneficiario;
import com.keralty.aeconfiguraciones.sqlutils.SQLExists;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.TypedQueryBuilder;
import com.keralty.aeconfiguraciones.utils.Agent;
import jakarta.inject.Inject;
import java.util.Collection;
import java.util.List;

public class EstadoBeneficiarioJpaRepositoryImpl extends AbstractRepository implements EstadoBeneficiarioJpaRepositoryCustom {

    @Inject
    public EstadoBeneficiarioJpaRepositoryImpl(Agent agent, UserFunctions userFunctionsSrv) {
        super(agent, userFunctionsSrv);
    }

    @Override
    public List<EstadoBeneficiario> executeQuery(String jpqlQuery) {
        return getQueryBuilder().build(jpqlQuery).execute();
    }
    
    @Override
    public List<EstadoBeneficiario> executeQuery(String jpqlQuery, List<SQLParam> params) {
        return getQueryBuilder().build(jpqlQuery).setParameters(params).execute();
    }
    
    public Collection<EstadoBeneficiario> executeQuery(SQLSelect select) {
        return getQueryBuilder().build(select).execute();
    }

    private TypedQueryBuilder<EstadoBeneficiario> getQueryBuilder() {
        return new TypedQueryBuilder<>(em, EstadoBeneficiario.class);
    }

    @Override	
    public List<EstadoBeneficiario> solveQuery(SQLSelect select) {
        return solveQuery(select, 0, 0);
    }

    @Override
    public List<EstadoBeneficiario> solveQuery(SQLSelect select, int pageSize, int pageNumber) {
        return getQueryBuilder().build(select, pageSize, pageNumber).execute();
    }

    // AssocOperator: AssocOperator000
	@Override
    public Boolean assocOperator000(EstadoBeneficiario instance) throws SystemException {
        try {
            // EXIST (EstadoBeneficiarioAE)
            return assocOperator000Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: EstadoBeneficiario, Assoc.Operator: AssocOperator000, Method: assocOperator000";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator000
	@Override
    public Boolean assocOperator000Range1 (EstadoBeneficiario instance) throws SystemException {
        SQLExists sql = assocOperator000Range1Query(instance);
        return !new TypedQueryBuilder<EstadoBeneficiario>(em, EstadoBeneficiario.class).build(sql).execute().isEmpty();
    }

    // Range Query 1 for AssocOperator AssocOperator000
	@Override
    public SQLExists assocOperator000Range1Query (EstadoBeneficiario instance) throws SystemException {
        try {
            // The path starts with a role
            return new SQLExists(EstadoBeneficiarioConstants.CLASS_NAME, EstadoBeneficiarioConstants.ROLE_NAME_ESTADOBENEFICIARIOAE, instance.getOid());
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: EstadoBeneficiario, Assoc.Operator: AssocOperator000, Method: assocOperator000Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    @Override
    public void refreshEntity(Object object) {
        super.refreshEntity(object);
    }
}
