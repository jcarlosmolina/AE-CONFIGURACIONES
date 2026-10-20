package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.persistence.Pregunta;
import com.keralty.aeconfiguraciones.persistence.Valor;
import com.keralty.aeconfiguraciones.sqlutils.SQLExists;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import java.util.List;

public interface ValorJpaRepositoryCustom {

    List<Valor> solveQuery(SQLSelect query);
    
    List<Valor> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    Boolean assocOperator000(Pregunta preguntaInsValorPreguntapthisPregunta) throws SystemException;

    Boolean assocOperator000Range1(Pregunta preguntaInsValorPreguntapthisPregunta) throws SystemException;
    
    SQLExists assocOperator000Range1Query(Pregunta preguntaInsValorPreguntapthisPregunta, SQLSelect superQuery) throws SystemException;

    Boolean assocOperator001(Pregunta preguntaDelValorPreguntapthisPregunta) throws SystemException;

    Boolean assocOperator001Range1(Pregunta preguntaDelValorPreguntapthisPregunta) throws SystemException;
    
    SQLExists assocOperator001Range1Query(Pregunta preguntaDelValorPreguntapthisPregunta, SQLSelect superQuery) throws SystemException;

    Boolean assocOperator002(Pregunta voPregunta) throws SystemException;

    Boolean assocOperator002Range1(Pregunta voPregunta) throws SystemException;
    
    SQLExists assocOperator002Range1Query(Pregunta voPregunta, SQLSelect superQuery) throws SystemException;

    Boolean assocOperator003(Valor instance) throws SystemException;

    Boolean assocOperator003Range1(Valor instance) throws SystemException;
    
    SQLExists assocOperator003Range1Query(Valor instance) throws SystemException;

    public List<Valor> executeQuery(String query);

    public List<Valor> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
