package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.Parentesco;
import com.keralty.aeconfiguraciones.persistence.ParentescoDom;
import com.keralty.aeconfiguraciones.sqlutils.SQLExists;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.List;

public interface ParentescoDomJpaRepositoryCustom {

    List<ParentescoDom> solveQuery(SQLSelect query);
    
    List<ParentescoDom> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);
    
    Long nextParentescoDomIdentificador(Long dominioNumero);

    Boolean assocOperator000(Dominio parentescoDomcrearpagrDominio, Parentesco parentescoDomcrearpagrParentesco) throws SystemException;

    Boolean assocOperator000Range1(Dominio parentescoDomcrearpagrDominio, Parentesco parentescoDomcrearpagrParentesco) throws SystemException;
    
    SQLExists assocOperator000Range1Query(Dominio parentescoDomcrearpagrDominio, Parentesco parentescoDomcrearpagrParentesco, SQLSelect superQuery) throws SystemException;

    public List<ParentescoDom> executeQuery(String query);

    public List<ParentescoDom> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
