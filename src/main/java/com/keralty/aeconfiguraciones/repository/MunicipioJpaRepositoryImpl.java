package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.MunicipioConstants;
import com.keralty.aeconfiguraciones.global.UserFunctions;
import com.keralty.aeconfiguraciones.global.UsuarioConstants;
import com.keralty.aeconfiguraciones.persistence.Municipio;
import com.keralty.aeconfiguraciones.persistence.Usuario;
import com.keralty.aeconfiguraciones.sqlutils.SQLExists;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.TypedQueryBuilder;
import com.keralty.aeconfiguraciones.utils.Agent;
import jakarta.inject.Inject;
import java.util.Collection;
import java.util.List;

public class MunicipioJpaRepositoryImpl extends AbstractRepository implements MunicipioJpaRepositoryCustom {

    @Inject
    public MunicipioJpaRepositoryImpl(Agent agent, UserFunctions userFunctionsSrv) {
        super(agent, userFunctionsSrv);
    }

    @Override
    public List<Municipio> executeQuery(String jpqlQuery) {
        return getQueryBuilder().build(jpqlQuery).execute();
    }
    
    @Override
    public List<Municipio> executeQuery(String jpqlQuery, List<SQLParam> params) {
        return getQueryBuilder().build(jpqlQuery).setParameters(params).execute();
    }
    
    public Collection<Municipio> executeQuery(SQLSelect select) {
        return getQueryBuilder().build(select).execute();
    }

    private TypedQueryBuilder<Municipio> getQueryBuilder() {
        return new TypedQueryBuilder<>(em, Municipio.class);
    }

    @Override	
    public List<Municipio> solveQuery(SQLSelect select) {
        return solveQuery(select, 0, 0);
    }

    @Override
    public List<Municipio> solveQuery(SQLSelect select, int pageSize, int pageNumber) {
        return getQueryBuilder().build(select, pageSize, pageNumber).execute();
    }

    // AssocOperator: AssocOperator000
	@Override
    public Boolean assocOperator000(Municipio instance) throws SystemException {
        try {
            // EXIST (Direcciones)
            return assocOperator000Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Municipio, Assoc.Operator: AssocOperator000, Method: assocOperator000";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator000
	@Override
    public Boolean assocOperator000Range1 (Municipio instance) throws SystemException {
        SQLExists sql = assocOperator000Range1Query(instance);
        return !new TypedQueryBuilder<Municipio>(em, Municipio.class).build(sql).execute().isEmpty();
    }

    // Range Query 1 for AssocOperator AssocOperator000
	@Override
    public SQLExists assocOperator000Range1Query (Municipio instance) throws SystemException {
        try {
            // The path starts with a role
            return new SQLExists(MunicipioConstants.CLASS_NAME, MunicipioConstants.ROLE_NAME_DIRECCIONES, instance.getOid());
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Municipio, Assoc.Operator: AssocOperator000, Method: assocOperator000Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator001
	@Override
    public Boolean assocOperator001(Municipio instance) throws SystemException {
        try {
            // EXIST (Departamento)
            return assocOperator001Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Municipio, Assoc.Operator: AssocOperator001, Method: assocOperator001";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator001
	@Override
    public Boolean assocOperator001Range1 (Municipio instance) throws SystemException {
        SQLExists sql = assocOperator001Range1Query(instance);
        return !new TypedQueryBuilder<Municipio>(em, Municipio.class).build(sql).execute().isEmpty();
    }

    // Range Query 1 for AssocOperator AssocOperator001
	@Override
    public SQLExists assocOperator001Range1Query (Municipio instance) throws SystemException {
        try {
            // The path starts with a role
            return new SQLExists(MunicipioConstants.CLASS_NAME, MunicipioConstants.ROLE_NAME_DEPARTAMENTO, instance.getOid());
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Municipio, Assoc.Operator: AssocOperator001, Method: assocOperator001Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator002
	@Override
    public Boolean assocOperator002(Municipio instance) throws SystemException {
        try {
            // EXIST (Departamento)
            return assocOperator002Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Municipio, Assoc.Operator: AssocOperator002, Method: assocOperator002";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator002
	@Override
    public Boolean assocOperator002Range1 (Municipio instance) throws SystemException {
        SQLExists sql = assocOperator002Range1Query(instance);
        return !new TypedQueryBuilder<Municipio>(em, Municipio.class).build(sql).execute().isEmpty();
    }

    // Range Query 1 for AssocOperator AssocOperator002
	@Override
    public SQLExists assocOperator002Range1Query (Municipio instance) throws SystemException {
        try {
            // The path starts with a role
            return new SQLExists(MunicipioConstants.CLASS_NAME, MunicipioConstants.ROLE_NAME_DEPARTAMENTO, instance.getOid());
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Municipio, Assoc.Operator: AssocOperator002, Method: assocOperator002Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator004
	@Override
    public Boolean assocOperator004(Municipio instance) throws SystemException {
        try {
            // EXIST (Departamento)
            return assocOperator004Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Municipio, Assoc.Operator: AssocOperator004, Method: assocOperator004";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator004
	@Override
    public Boolean assocOperator004Range1 (Municipio instance) throws SystemException {
        SQLExists sql = assocOperator004Range1Query(instance);
        return !new TypedQueryBuilder<Municipio>(em, Municipio.class).build(sql).execute().isEmpty();
    }

    // Range Query 1 for AssocOperator AssocOperator004
	@Override
    public SQLExists assocOperator004Range1Query (Municipio instance) throws SystemException {
        try {
            // The path starts with a role
            return new SQLExists(MunicipioConstants.CLASS_NAME, MunicipioConstants.ROLE_NAME_DEPARTAMENTO, instance.getOid());
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Municipio, Assoc.Operator: AssocOperator004, Method: assocOperator004Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator005
	@Override
    public Boolean assocOperator005(Usuario usuarioTMODUSUARIOpthisUsuario) throws SystemException {
        try {
            // EXIST (pthisUsuario.UsuarioPersona)
            return assocOperator005Range1(usuarioTMODUSUARIOpthisUsuario);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Municipio, Assoc.Operator: AssocOperator005, Method: assocOperator005";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator005
	@Override
    public Boolean assocOperator005Range1 (Usuario usuarioTMODUSUARIOpthisUsuario) throws SystemException {
        SQLExists sql = assocOperator005Range1Query(usuarioTMODUSUARIOpthisUsuario, null);
        return !new TypedQueryBuilder<Municipio>(em, Municipio.class).build(sql).execute().isEmpty();
    }

    // Range Query 1 for AssocOperator AssocOperator005
	@Override
    public SQLExists assocOperator005Range1Query (Usuario usuarioTMODUSUARIOpthisUsuario, SQLSelect superQuery) throws SystemException {
        try {
            SQLExists sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLExists(superQuery, MunicipioConstants.CLASS_NAME, UsuarioConstants.ARG_NAME_TMODUSUARIO_USUARIOTMODUSUARIOPTHISUSUARIO + "." + UsuarioConstants.ROLE_NAME_USUARIOPERSONA, UsuarioConstants.CLASS_NAME, usuarioTMODUSUARIOpthisUsuario.getOid(), UsuarioConstants.ARG_NAME_TMODUSUARIO_USUARIOTMODUSUARIOPTHISUSUARIO);
            } else {
                sql = new SQLExists(MunicipioConstants.CLASS_NAME, UsuarioConstants.ARG_NAME_TMODUSUARIO_USUARIOTMODUSUARIOPTHISUSUARIO + "." + UsuarioConstants.ROLE_NAME_USUARIOPERSONA, UsuarioConstants.CLASS_NAME, usuarioTMODUSUARIOpthisUsuario.getOid(), UsuarioConstants.ARG_NAME_TMODUSUARIO_USUARIOTMODUSUARIOPTHISUSUARIO);
            }
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Municipio, Assoc.Operator: AssocOperator005, Method: assocOperator005Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    @Override
    public void refreshEntity(Object object) {
        super.refreshEntity(object);
    }
}
