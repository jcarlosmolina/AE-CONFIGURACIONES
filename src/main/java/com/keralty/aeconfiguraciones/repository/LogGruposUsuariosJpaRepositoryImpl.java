package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.global.UserFunctions;
import com.keralty.aeconfiguraciones.persistence.LogGruposUsuarios;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.TypedQueryBuilder;
import com.keralty.aeconfiguraciones.utils.Agent;
import jakarta.inject.Inject;
import java.util.Collection;
import java.util.List;

public class LogGruposUsuariosJpaRepositoryImpl extends AbstractRepository implements LogGruposUsuariosJpaRepositoryCustom {

    @Inject
    public LogGruposUsuariosJpaRepositoryImpl(Agent agent, UserFunctions userFunctionsSrv) {
        super(agent, userFunctionsSrv);
    }

    @Override
    public List<LogGruposUsuarios> executeQuery(String jpqlQuery) {
        return getQueryBuilder().build(jpqlQuery).execute();
    }
    
    @Override
    public List<LogGruposUsuarios> executeQuery(String jpqlQuery, List<SQLParam> params) {
        return getQueryBuilder().build(jpqlQuery).setParameters(params).execute();
    }
    
    public Collection<LogGruposUsuarios> executeQuery(SQLSelect select) {
        return getQueryBuilder().build(select).execute();
    }

    private TypedQueryBuilder<LogGruposUsuarios> getQueryBuilder() {
        return new TypedQueryBuilder<>(em, LogGruposUsuarios.class);
    }

    @Override	
    public List<LogGruposUsuarios> solveQuery(SQLSelect select) {
        return solveQuery(select, 0, 0);
    }

    @Override
    public List<LogGruposUsuarios> solveQuery(SQLSelect select, int pageSize, int pageNumber) {
        return getQueryBuilder().build(select, pageSize, pageNumber).execute();
    }

    @Override
    public void refreshEntity(Object object) {
        super.refreshEntity(object);
    }
}
