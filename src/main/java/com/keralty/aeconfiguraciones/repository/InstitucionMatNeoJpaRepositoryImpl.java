package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.InstitucionMatNeoConstants;
import com.keralty.aeconfiguraciones.global.UserFunctions;
import com.keralty.aeconfiguraciones.persistence.InstitucionMatNeo;
import com.keralty.aeconfiguraciones.sqlutils.SQLCount;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.TypedQueryBuilder;
import com.keralty.aeconfiguraciones.utils.Agent;
import jakarta.inject.Inject;
import java.util.Collection;
import java.util.List;

public class InstitucionMatNeoJpaRepositoryImpl extends AbstractRepository implements InstitucionMatNeoJpaRepositoryCustom {

    @Inject
    public InstitucionMatNeoJpaRepositoryImpl(Agent agent, UserFunctions userFunctionsSrv) {
        super(agent, userFunctionsSrv);
    }

    @Override
    public List<InstitucionMatNeo> executeQuery(String jpqlQuery) {
        return getQueryBuilder().build(jpqlQuery).execute();
    }
    
    @Override
    public List<InstitucionMatNeo> executeQuery(String jpqlQuery, List<SQLParam> params) {
        return getQueryBuilder().build(jpqlQuery).setParameters(params).execute();
    }
    
    public Collection<InstitucionMatNeo> executeQuery(SQLSelect select) {
        return getQueryBuilder().build(select).execute();
    }

    private TypedQueryBuilder<InstitucionMatNeo> getQueryBuilder() {
        return new TypedQueryBuilder<>(em, InstitucionMatNeo.class);
    }

    @Override	
    public List<InstitucionMatNeo> solveQuery(SQLSelect select) {
        return solveQuery(select, 0, 0);
    }

    @Override
    public List<InstitucionMatNeo> solveQuery(SQLSelect select, int pageSize, int pageNumber) {
        return getQueryBuilder().build(select, pageSize, pageNumber).execute();
    }

    // AssocOperator: AssocOperator000
	@Override
    public Long assocOperator000(InstitucionMatNeo instance) throws SystemException {
        try {
            // COUNT (AnexoMat)
            return assocOperator000Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: InstitucionMatNeo, Assoc.Operator: AssocOperator000, Method: assocOperator000";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator000
	@Override
    public Long assocOperator000Range1 (InstitucionMatNeo instance) throws SystemException {
        SQLCount sql = assocOperator000Range1Query(instance);
        return Long.valueOf(solveCountQuery(sql));
    }

    // Range Query 1 for AssocOperator AssocOperator000
	@Override
    public SQLCount assocOperator000Range1Query (InstitucionMatNeo instance) throws SystemException {
        try {
            // The path starts with a role
            return new SQLCount(InstitucionMatNeoConstants.CLASS_NAME, InstitucionMatNeoConstants.ROLE_NAME_ANEXOMAT, instance.getOid());
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: InstitucionMatNeo, Assoc.Operator: AssocOperator000, Method: assocOperator000Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator001
	@Override
    public Long assocOperator001(InstitucionMatNeo instance) throws SystemException {
        try {
            // COUNT (AnexoNeo)
            return assocOperator001Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: InstitucionMatNeo, Assoc.Operator: AssocOperator001, Method: assocOperator001";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator001
	@Override
    public Long assocOperator001Range1 (InstitucionMatNeo instance) throws SystemException {
        SQLCount sql = assocOperator001Range1Query(instance);
        return Long.valueOf(solveCountQuery(sql));
    }

    // Range Query 1 for AssocOperator AssocOperator001
	@Override
    public SQLCount assocOperator001Range1Query (InstitucionMatNeo instance) throws SystemException {
        try {
            // The path starts with a role
            return new SQLCount(InstitucionMatNeoConstants.CLASS_NAME, InstitucionMatNeoConstants.ROLE_NAME_ANEXONEO, instance.getOid());
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: InstitucionMatNeo, Assoc.Operator: AssocOperator001, Method: assocOperator001Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    @Override
    public void refreshEntity(Object object) {
        super.refreshEntity(object);
    }
}
