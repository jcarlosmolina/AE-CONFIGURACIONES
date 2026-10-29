package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.EstadoBeneficiarioAE;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.List;

public interface EstadoBeneficiarioAEJpaRepositoryCustom {

    List<EstadoBeneficiarioAE> solveQuery(SQLSelect query);
    
    List<EstadoBeneficiarioAE> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<EstadoBeneficiarioAE> executeQuery(String query);

    public List<EstadoBeneficiarioAE> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
