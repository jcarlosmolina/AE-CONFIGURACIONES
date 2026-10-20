package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.global.UserFunctions;
import com.keralty.aeconfiguraciones.persistence.Titular;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.TypedQueryBuilder;
import com.keralty.aeconfiguraciones.utils.Agent;
import jakarta.inject.Inject;
import java.util.Collection;
import java.util.List;

public class TitularJpaRepositoryImpl extends AbstractRepository implements TitularJpaRepositoryCustom {

    @Inject
    public TitularJpaRepositoryImpl(Agent agent, UserFunctions userFunctionsSrv) {
        super(agent, userFunctionsSrv);
    }

    @Override
    public List<Titular> executeQuery(String jpqlQuery) {
        return getQueryBuilder().build(jpqlQuery).execute();
    }
    
    @Override
    public List<Titular> executeQuery(String jpqlQuery, List<SQLParam> params) {
        return getQueryBuilder().build(jpqlQuery).setParameters(params).execute();
    }
    
    public Collection<Titular> executeQuery(SQLSelect select) {
        return getQueryBuilder().build(select).execute();
    }

    private TypedQueryBuilder<Titular> getQueryBuilder() {
        return new TypedQueryBuilder<>(em, Titular.class);
    }

    @Override	
    public List<Titular> solveQuery(SQLSelect select) {
        return solveQuery(select, 0, 0);
    }

    @Override
    public List<Titular> solveQuery(SQLSelect select, int pageSize, int pageNumber) {
        return getQueryBuilder().build(select, pageSize, pageNumber).execute();
    }

    @Override
    public void refreshEntity(Object object) {
        super.refreshEntity(object);
    }
}
