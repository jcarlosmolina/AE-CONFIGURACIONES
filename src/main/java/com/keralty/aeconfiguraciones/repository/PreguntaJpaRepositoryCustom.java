package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.persistence.Pregunta;
import com.keralty.aeconfiguraciones.persistence.Seccion;
import com.keralty.aeconfiguraciones.persistence.Valor;
import com.keralty.aeconfiguraciones.sqlutils.SQLExists;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLPopulation;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.Collection;
import java.util.List;

public interface PreguntaJpaRepositoryCustom {

    List<Pregunta> solveQuery(SQLSelect query);
    
    List<Pregunta> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    Boolean assocOperator000(Seccion preguntaTCREARptpagrSeccion, Long preguntaTCREARptpatrNumero) throws SystemException;

    Boolean assocOperator000Range1(Seccion preguntaTCREARptpagrSeccion, Long preguntaTCREARptpatrNumero) throws SystemException;
    
    SQLExists assocOperator000Range1Query(Seccion preguntaTCREARptpagrSeccion, Long preguntaTCREARptpatrNumero, SQLSelect superQuery) throws SystemException;

    Collection<Valor> assocOperator001(Pregunta instance) throws SystemException;

    Collection<Valor> assocOperator001Range1(Pregunta instance) throws SystemException;
    
    SQLPopulation assocOperator001Range1Query(Pregunta instance) throws SystemException;

    public List<Pregunta> executeQuery(String query);

    public List<Pregunta> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
