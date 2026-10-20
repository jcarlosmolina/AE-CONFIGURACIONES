package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.global.FuncionarioConstants;
import com.keralty.aeconfiguraciones.global.MesaDeApoyoConstants;
import com.keralty.aeconfiguraciones.global.UserFunctions;
import com.keralty.aeconfiguraciones.persistence.Funcionario;
import com.keralty.aeconfiguraciones.persistence.MesaDeApoyo;
import com.keralty.aeconfiguraciones.sqlutils.SQLJoin;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.TypedQueryBuilder;
import com.keralty.aeconfiguraciones.utils.Agent;
import jakarta.inject.Inject;
import java.util.Collection;
import java.util.List;

public class MesaDeApoyoJpaRepositoryImpl extends AbstractRepository implements MesaDeApoyoJpaRepositoryCustom {

    @Inject
    public MesaDeApoyoJpaRepositoryImpl(Agent agent, UserFunctions userFunctionsSrv) {
        super(agent, userFunctionsSrv);
    }

    @Override
    public List<MesaDeApoyo> executeQuery(String jpqlQuery) {
        return getQueryBuilder().build(jpqlQuery).execute();
    }
    
    @Override
    public List<MesaDeApoyo> executeQuery(String jpqlQuery, List<SQLParam> params) {
        return getQueryBuilder().build(jpqlQuery).setParameters(params).execute();
    }
    
    public Collection<MesaDeApoyo> executeQuery(SQLSelect select) {
        return getQueryBuilder().build(select).execute();
    }

    private TypedQueryBuilder<MesaDeApoyo> getQueryBuilder() {
        return new TypedQueryBuilder<>(em, MesaDeApoyo.class);
    }

    @Override	
    public List<MesaDeApoyo> solveQuery(SQLSelect select) {
        return solveQuery(select, 0, 0);
    }

    @Override
    public List<MesaDeApoyo> solveQuery(SQLSelect select, int pageSize, int pageNumber) {
        return getQueryBuilder().build(select, pageSize, pageNumber).execute();
    }

    @Override
    public Funcionario findParent(Long oid) {
        return em.find(Funcionario.class, oid);
    }

    /**
     * Adds facet where
     * @param myAlias Entity/Table alias.
     * @param join Join clause to add the where to.
     * @param parentAlias Alias of the parent entity.
     */
    public void addFacetWhereparentfuncionario (String myAlias, SQLJoin join, String parentAlias) {
        join.getWheres().add(myAlias + "." + MesaDeApoyoConstants.FLD_FUNCIONARIOID + " = " + parentAlias + "." + FuncionarioConstants.ATTR_FIELD_FUNCIONARIOID);
    }

    @Override
    public void refreshEntity(Object object) {
        super.refreshEntity(object);
    }
}
