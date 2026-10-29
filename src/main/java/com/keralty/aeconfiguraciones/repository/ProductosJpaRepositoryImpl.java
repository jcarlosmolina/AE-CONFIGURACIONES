package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.AgentexProductoConstants;
import com.keralty.aeconfiguraciones.global.AsesorConstants;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.ContratoCConstants;
import com.keralty.aeconfiguraciones.global.FamiliaCConstants;
import com.keralty.aeconfiguraciones.global.ProductosConstants;
import com.keralty.aeconfiguraciones.global.UserFunctions;
import com.keralty.aeconfiguraciones.persistence.AgentexProducto;
import com.keralty.aeconfiguraciones.persistence.Asesor;
import com.keralty.aeconfiguraciones.persistence.ContratoC;
import com.keralty.aeconfiguraciones.persistence.Productos;
import com.keralty.aeconfiguraciones.sqlutils.SQLExists;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLPopulation;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereConjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandNull;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandOVParameter;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandOVParameterAttribute;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandPathInstance;
import com.keralty.aeconfiguraciones.sqlutils.TypedQueryBuilder;
import com.keralty.aeconfiguraciones.utils.Agent;
import jakarta.inject.Inject;
import java.sql.Date;
import java.util.Collection;
import java.util.List;

public class ProductosJpaRepositoryImpl extends AbstractRepository implements ProductosJpaRepositoryCustom {

    @Inject
    public ProductosJpaRepositoryImpl(Agent agent, UserFunctions userFunctionsSrv) {
        super(agent, userFunctionsSrv);
    }

    @Override
    public List<Productos> executeQuery(String jpqlQuery) {
        return getQueryBuilder().build(jpqlQuery).execute();
    }
    
    @Override
    public List<Productos> executeQuery(String jpqlQuery, List<SQLParam> params) {
        return getQueryBuilder().build(jpqlQuery).setParameters(params).execute();
    }
    
    public Collection<Productos> executeQuery(SQLSelect select) {
        return getQueryBuilder().build(select).execute();
    }

    private TypedQueryBuilder<Productos> getQueryBuilder() {
        return new TypedQueryBuilder<>(em, Productos.class);
    }

    @Override	
    public List<Productos> solveQuery(SQLSelect select) {
        return solveQuery(select, 0, 0);
    }

    @Override
    public List<Productos> solveQuery(SQLSelect select, int pageSize, int pageNumber) {
        return getQueryBuilder().build(select, pageSize, pageNumber).execute();
    }

    // AssocOperator: AssocOperator000
	@Override
    public Boolean assocOperator000(Productos instance) throws SystemException {
        try {
            // EXIST (GruposAsociados)
            return assocOperator000Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Productos, Assoc.Operator: AssocOperator000, Method: assocOperator000";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator000
	@Override
    public Boolean assocOperator000Range1 (Productos instance) throws SystemException {
        SQLExists sql = assocOperator000Range1Query(instance);
        return !new TypedQueryBuilder<Productos>(em, Productos.class).build(sql).execute().isEmpty();
    }

    // Range Query 1 for AssocOperator AssocOperator000
	@Override
    public SQLExists assocOperator000Range1Query (Productos instance) throws SystemException {
        try {
            // The path starts with a role
            return new SQLExists(ProductosConstants.CLASS_NAME, ProductosConstants.ROLE_NAME_GRUPOSASOCIADOS, instance.getOid());
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Productos, Assoc.Operator: AssocOperator000, Method: assocOperator000Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator001
	@Override
    public Boolean assocOperator001(Productos instance) throws SystemException {
        try {
            // EXIST (Dominios)
            return assocOperator001Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Productos, Assoc.Operator: AssocOperator001, Method: assocOperator001";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator001
	@Override
    public Boolean assocOperator001Range1 (Productos instance) throws SystemException {
        SQLExists sql = assocOperator001Range1Query(instance);
        return !new TypedQueryBuilder<Productos>(em, Productos.class).build(sql).execute().isEmpty();
    }

    // Range Query 1 for AssocOperator AssocOperator001
	@Override
    public SQLExists assocOperator001Range1Query (Productos instance) throws SystemException {
        try {
            // The path starts with a role
            return new SQLExists(ProductosConstants.CLASS_NAME, ProductosConstants.ROLE_NAME_DOMINIOS, instance.getOid());
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Productos, Assoc.Operator: AssocOperator001, Method: assocOperator001Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator002
	@Override
    public Boolean assocOperator002(Productos instance) throws SystemException {
        try {
            // EXIST (Planes)
            return assocOperator002Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Productos, Assoc.Operator: AssocOperator002, Method: assocOperator002";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator002
	@Override
    public Boolean assocOperator002Range1 (Productos instance) throws SystemException {
        SQLExists sql = assocOperator002Range1Query(instance);
        return !new TypedQueryBuilder<Productos>(em, Productos.class).build(sql).execute().isEmpty();
    }

    // Range Query 1 for AssocOperator AssocOperator002
	@Override
    public SQLExists assocOperator002Range1Query (Productos instance) throws SystemException {
        try {
            // The path starts with a role
            return new SQLExists(ProductosConstants.CLASS_NAME, ProductosConstants.ROLE_NAME_PLANES, instance.getOid());
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Productos, Assoc.Operator: AssocOperator002, Method: assocOperator002Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator003
	@Override
    public Boolean assocOperator003(Productos instance) throws SystemException {
        try {
            // EXIST (Tarifas)
            return assocOperator003Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Productos, Assoc.Operator: AssocOperator003, Method: assocOperator003";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator003
	@Override
    public Boolean assocOperator003Range1 (Productos instance) throws SystemException {
        SQLExists sql = assocOperator003Range1Query(instance);
        return !new TypedQueryBuilder<Productos>(em, Productos.class).build(sql).execute().isEmpty();
    }

    // Range Query 1 for AssocOperator AssocOperator003
	@Override
    public SQLExists assocOperator003Range1Query (Productos instance) throws SystemException {
        try {
            // The path starts with a role
            return new SQLExists(ProductosConstants.CLASS_NAME, ProductosConstants.ROLE_NAME_TARIFAS, instance.getOid());
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Productos, Assoc.Operator: AssocOperator003, Method: assocOperator003Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator004
	@Override
    public Boolean assocOperator004(Productos instance) throws SystemException {
        try {
            // EXIST (TiposIVA)
            return assocOperator004Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Productos, Assoc.Operator: AssocOperator004, Method: assocOperator004";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator004
	@Override
    public Boolean assocOperator004Range1 (Productos instance) throws SystemException {
        SQLExists sql = assocOperator004Range1Query(instance);
        return !new TypedQueryBuilder<Productos>(em, Productos.class).build(sql).execute().isEmpty();
    }

    // Range Query 1 for AssocOperator AssocOperator004
	@Override
    public SQLExists assocOperator004Range1Query (Productos instance) throws SystemException {
        try {
            // The path starts with a role
            return new SQLExists(ProductosConstants.CLASS_NAME, ProductosConstants.ROLE_NAME_TIPOSIVA, instance.getOid());
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Productos, Assoc.Operator: AssocOperator004, Method: assocOperator004Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator005
	@Override
    public Boolean assocOperator005(Productos instance) throws SystemException {
        try {
            // EXIST (ProteccionesDatos)
            return assocOperator005Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Productos, Assoc.Operator: AssocOperator005, Method: assocOperator005";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator005
	@Override
    public Boolean assocOperator005Range1 (Productos instance) throws SystemException {
        SQLExists sql = assocOperator005Range1Query(instance);
        return !new TypedQueryBuilder<Productos>(em, Productos.class).build(sql).execute().isEmpty();
    }

    // Range Query 1 for AssocOperator AssocOperator005
	@Override
    public SQLExists assocOperator005Range1Query (Productos instance) throws SystemException {
        try {
            // The path starts with a role
            return new SQLExists(ProductosConstants.CLASS_NAME, ProductosConstants.ROLE_NAME_PROTECCIONESDATOS, instance.getOid());
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Productos, Assoc.Operator: AssocOperator005, Method: assocOperator005Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator006
	@Override
    public Boolean assocOperator006(ContratoC productosdummyVerificarContratoIncpContratoC) throws SystemException {
        try {
            // EXIST (pContratoC.EstadoContratoC) WHERE (pContratoC.EstadoContratoC.Codigo = 4)
            return assocOperator006Range1(productosdummyVerificarContratoIncpContratoC);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Productos, Assoc.Operator: AssocOperator006, Method: assocOperator006";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator006
	@Override
    public Boolean assocOperator006Range1 (ContratoC productosdummyVerificarContratoIncpContratoC) throws SystemException {
        SQLExists sql = assocOperator006Range1Query(productosdummyVerificarContratoIncpContratoC, null);
        return !new TypedQueryBuilder<Productos>(em, Productos.class).build(sql).execute().isEmpty();
    }

    // Range Query 1 for AssocOperator AssocOperator006
	@Override
    public SQLExists assocOperator006Range1Query (ContratoC productosdummyVerificarContratoIncpContratoC, SQLSelect superQuery) throws SystemException {
        try {
            SQLExists sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLExists(superQuery, ProductosConstants.CLASS_NAME, ProductosConstants.ARG_NAME_DUMMYVERIFICARCONTRATOINC_PRODUCTOSDUMMYVERIFICARCONTRATOINCPCONTRATOC + "." + ContratoCConstants.ROLE_NAME_ESTADOCONTRATOC, ContratoCConstants.CLASS_NAME, productosdummyVerificarContratoIncpContratoC.getOid(), ProductosConstants.ARG_NAME_DUMMYVERIFICARCONTRATOINC_PRODUCTOSDUMMYVERIFICARCONTRATOINCPCONTRATOC);
            } else {
                sql = new SQLExists(ProductosConstants.CLASS_NAME, ProductosConstants.ARG_NAME_DUMMYVERIFICARCONTRATOINC_PRODUCTOSDUMMYVERIFICARCONTRATOINCPCONTRATOC + "." + ContratoCConstants.ROLE_NAME_ESTADOCONTRATOC, ContratoCConstants.CLASS_NAME, productosdummyVerificarContratoIncpContratoC.getOid(), ProductosConstants.ARG_NAME_DUMMYVERIFICARCONTRATOINC_PRODUCTOSDUMMYVERIFICARCONTRATOINCPCONTRATOC);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // pContratoC.EstadoContratoC.Codigo = 4
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    if (productosdummyVerificarContratoIncpContratoC.getEstadoContratoC().getEstadoContratoCodigo()!= null){
                    comparison1.setFirstOperand(new SQLWhereOperandLiteral(productosdummyVerificarContratoIncpContratoC.getEstadoContratoC().getEstadoContratoCodigo(), Constants.Type.INT.getTypeName()));
                    } else {
                    comparison1.setFirstOperand(new SQLWhereOperandNull());
                    }
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(Long.valueOf(4), Constants.Type.NAT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Productos, Assoc.Operator: AssocOperator006, Method: assocOperator006Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator007
	@Override
    public Boolean assocOperator007(ContratoC productosdummyVerificarContratoIncpContratoC, String productosdummyVerificarContratoIncpNumFamilia) throws SystemException {
        try {
            // EXIST (pContratoC.FamiliasC) WHERE (pContratoC.FamiliasC.NumFamilia = pNumFamilia)
            return assocOperator007Range1(productosdummyVerificarContratoIncpContratoC, productosdummyVerificarContratoIncpNumFamilia);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Productos, Assoc.Operator: AssocOperator007, Method: assocOperator007";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator007
	@Override
    public Boolean assocOperator007Range1 (ContratoC productosdummyVerificarContratoIncpContratoC, String productosdummyVerificarContratoIncpNumFamilia) throws SystemException {
        SQLExists sql = assocOperator007Range1Query(productosdummyVerificarContratoIncpContratoC, productosdummyVerificarContratoIncpNumFamilia, null);
        return !new TypedQueryBuilder<Productos>(em, Productos.class).build(sql).execute().isEmpty();
    }

    // Range Query 1 for AssocOperator AssocOperator007
	@Override
    public SQLExists assocOperator007Range1Query (ContratoC productosdummyVerificarContratoIncpContratoC, String productosdummyVerificarContratoIncpNumFamilia, SQLSelect superQuery) throws SystemException {
        try {
            SQLExists sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLExists(superQuery, ProductosConstants.CLASS_NAME, ProductosConstants.ARG_NAME_DUMMYVERIFICARCONTRATOINC_PRODUCTOSDUMMYVERIFICARCONTRATOINCPCONTRATOC + "." + ContratoCConstants.ROLE_NAME_FAMILIASC, ContratoCConstants.CLASS_NAME, productosdummyVerificarContratoIncpContratoC.getOid(), ProductosConstants.ARG_NAME_DUMMYVERIFICARCONTRATOINC_PRODUCTOSDUMMYVERIFICARCONTRATOINCPCONTRATOC);
            } else {
                sql = new SQLExists(ProductosConstants.CLASS_NAME, ProductosConstants.ARG_NAME_DUMMYVERIFICARCONTRATOINC_PRODUCTOSDUMMYVERIFICARCONTRATOINCPCONTRATOC + "." + ContratoCConstants.ROLE_NAME_FAMILIASC, ContratoCConstants.CLASS_NAME, productosdummyVerificarContratoIncpContratoC.getOid(), ProductosConstants.ARG_NAME_DUMMYVERIFICARCONTRATOINC_PRODUCTOSDUMMYVERIFICARCONTRATOINCPCONTRATOC);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // pContratoC.FamiliasC.NumFamilia = pNumFamilia
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandOVParameterAttribute operand112 = new SQLWhereOperandOVParameterAttribute(ProductosConstants.ARG_NAME_DUMMYVERIFICARCONTRATOINC_PRODUCTOSDUMMYVERIFICARCONTRATOINCPCONTRATOC + "." + ContratoCConstants.ROLE_NAME_FAMILIASC + "." + FamiliaCConstants.ATTR_NAME_FAMILIACNUMFAMILIA, ContratoCConstants.CLASS_NAME, productosdummyVerificarContratoIncpContratoC.getOid(), ProductosConstants.ARG_NAME_DUMMYVERIFICARCONTRATOINC_PRODUCTOSDUMMYVERIFICARCONTRATOINCPCONTRATOC, Constants.Type.STRING.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    if (productosdummyVerificarContratoIncpNumFamilia!= null){
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(productosdummyVerificarContratoIncpNumFamilia, Constants.Type.STRING.getTypeName()));
                    } else {
                    comparison1.setSecondOperand(new SQLWhereOperandNull());
                    }
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Productos, Assoc.Operator: AssocOperator007, Method: assocOperator007Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator008
	@Override
    public Boolean assocOperator008(ContratoC productosdummyVerificarContratoIncpContratoC, String productosdummyVerificarContratoIncpNumFamilia) throws SystemException {
        try {
            // EXIST (pContratoC.FamiliasC) WHERE (pContratoC.FamiliasC.NumFamilia = pNumFamilia AND pContratoC.FamiliasC.EstadoTitularFamilia LIKE "%LIQUIDADO%")
            return assocOperator008Range1(productosdummyVerificarContratoIncpContratoC, productosdummyVerificarContratoIncpNumFamilia);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Productos, Assoc.Operator: AssocOperator008, Method: assocOperator008";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator008
	@Override
    public Boolean assocOperator008Range1 (ContratoC productosdummyVerificarContratoIncpContratoC, String productosdummyVerificarContratoIncpNumFamilia) throws SystemException {
        SQLExists sql = assocOperator008Range1Query(productosdummyVerificarContratoIncpContratoC, productosdummyVerificarContratoIncpNumFamilia, null);
        return !new TypedQueryBuilder<Productos>(em, Productos.class).build(sql).execute().isEmpty();
    }

    // Range Query 1 for AssocOperator AssocOperator008
	@Override
    public SQLExists assocOperator008Range1Query (ContratoC productosdummyVerificarContratoIncpContratoC, String productosdummyVerificarContratoIncpNumFamilia, SQLSelect superQuery) throws SystemException {
        try {
            SQLExists sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLExists(superQuery, ProductosConstants.CLASS_NAME, ProductosConstants.ARG_NAME_DUMMYVERIFICARCONTRATOINC_PRODUCTOSDUMMYVERIFICARCONTRATOINCPCONTRATOC + "." + ContratoCConstants.ROLE_NAME_FAMILIASC, ContratoCConstants.CLASS_NAME, productosdummyVerificarContratoIncpContratoC.getOid(), ProductosConstants.ARG_NAME_DUMMYVERIFICARCONTRATOINC_PRODUCTOSDUMMYVERIFICARCONTRATOINCPCONTRATOC);
            } else {
                sql = new SQLExists(ProductosConstants.CLASS_NAME, ProductosConstants.ARG_NAME_DUMMYVERIFICARCONTRATOINC_PRODUCTOSDUMMYVERIFICARCONTRATOINCPCONTRATOC + "." + ContratoCConstants.ROLE_NAME_FAMILIASC, ContratoCConstants.CLASS_NAME, productosdummyVerificarContratoIncpContratoC.getOid(), ProductosConstants.ARG_NAME_DUMMYVERIFICARCONTRATOINC_PRODUCTOSDUMMYVERIFICARCONTRATOINCPCONTRATOC);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // pContratoC.FamiliasC.NumFamilia = pNumFamilia
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandOVParameterAttribute operand112 = new SQLWhereOperandOVParameterAttribute(ProductosConstants.ARG_NAME_DUMMYVERIFICARCONTRATOINC_PRODUCTOSDUMMYVERIFICARCONTRATOINCPCONTRATOC + "." + ContratoCConstants.ROLE_NAME_FAMILIASC + "." + FamiliaCConstants.ATTR_NAME_FAMILIACNUMFAMILIA, ContratoCConstants.CLASS_NAME, productosdummyVerificarContratoIncpContratoC.getOid(), ProductosConstants.ARG_NAME_DUMMYVERIFICARCONTRATOINC_PRODUCTOSDUMMYVERIFICARCONTRATOINCPCONTRATOC, Constants.Type.STRING.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    if (productosdummyVerificarContratoIncpNumFamilia!= null){
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(productosdummyVerificarContratoIncpNumFamilia, Constants.Type.STRING.getTypeName()));
                    } else {
                    comparison1.setSecondOperand(new SQLWhereOperandNull());
                    }
                    conjunction.addOperand(comparison1);


            // pContratoC.FamiliasC.EstadoTitularFamilia LIKE "%LIQUIDADO%"
                    // 'like' Comparison
                    SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
                    comparison2.setOperator("like");
                    
                    // Operand 1
                    SQLWhereOperandOVParameterAttribute operand113 = new SQLWhereOperandOVParameterAttribute(ProductosConstants.ARG_NAME_DUMMYVERIFICARCONTRATOINC_PRODUCTOSDUMMYVERIFICARCONTRATOINCPCONTRATOC + "." + ContratoCConstants.ROLE_NAME_FAMILIASC + "." + FamiliaCConstants.ATTR_NAME_FAMILIACESTADOTITULARFAMILIA, ContratoCConstants.CLASS_NAME, productosdummyVerificarContratoIncpContratoC.getOid(), ProductosConstants.ARG_NAME_DUMMYVERIFICARCONTRATOINC_PRODUCTOSDUMMYVERIFICARCONTRATOINCPCONTRATOC, Constants.Type.STRING.getTypeName());
                    comparison2.setFirstOperand(operand113);
                    
                    // Operand 2
                    comparison2.setSecondOperand(new SQLWhereOperandLiteral("%LIQUIDADO%", Constants.Type.STRING.getTypeName()));
                    conjunction.addOperand(comparison2);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Productos, Assoc.Operator: AssocOperator008, Method: assocOperator008Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator009
	@Override
    public Boolean assocOperator009(ContratoC productosdummyVerificarContratoIncpContratoC, String productosdummyVerificarContratoIncpNumFamilia, Date productosdummyVerificarContratoIncpFechaInicioContrato) throws SystemException {
        try {
            // EXIST (pContratoC.FamiliasC) WHERE (pContratoC.FamiliasC.NumFamilia = pNumFamilia AND pContratoC.FamiliasC.FechaFinVigencia >= pFechaInicioContrato)
            return assocOperator009Range1(productosdummyVerificarContratoIncpContratoC, productosdummyVerificarContratoIncpNumFamilia, productosdummyVerificarContratoIncpFechaInicioContrato);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Productos, Assoc.Operator: AssocOperator009, Method: assocOperator009";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator009
	@Override
    public Boolean assocOperator009Range1 (ContratoC productosdummyVerificarContratoIncpContratoC, String productosdummyVerificarContratoIncpNumFamilia, Date productosdummyVerificarContratoIncpFechaInicioContrato) throws SystemException {
        SQLExists sql = assocOperator009Range1Query(productosdummyVerificarContratoIncpContratoC, productosdummyVerificarContratoIncpNumFamilia, productosdummyVerificarContratoIncpFechaInicioContrato, null);
        return !new TypedQueryBuilder<Productos>(em, Productos.class).build(sql).execute().isEmpty();
    }

    // Range Query 1 for AssocOperator AssocOperator009
	@Override
    public SQLExists assocOperator009Range1Query (ContratoC productosdummyVerificarContratoIncpContratoC, String productosdummyVerificarContratoIncpNumFamilia, Date productosdummyVerificarContratoIncpFechaInicioContrato, SQLSelect superQuery) throws SystemException {
        try {
            SQLExists sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLExists(superQuery, ProductosConstants.CLASS_NAME, ProductosConstants.ARG_NAME_DUMMYVERIFICARCONTRATOINC_PRODUCTOSDUMMYVERIFICARCONTRATOINCPCONTRATOC + "." + ContratoCConstants.ROLE_NAME_FAMILIASC, ContratoCConstants.CLASS_NAME, productosdummyVerificarContratoIncpContratoC.getOid(), ProductosConstants.ARG_NAME_DUMMYVERIFICARCONTRATOINC_PRODUCTOSDUMMYVERIFICARCONTRATOINCPCONTRATOC);
            } else {
                sql = new SQLExists(ProductosConstants.CLASS_NAME, ProductosConstants.ARG_NAME_DUMMYVERIFICARCONTRATOINC_PRODUCTOSDUMMYVERIFICARCONTRATOINCPCONTRATOC + "." + ContratoCConstants.ROLE_NAME_FAMILIASC, ContratoCConstants.CLASS_NAME, productosdummyVerificarContratoIncpContratoC.getOid(), ProductosConstants.ARG_NAME_DUMMYVERIFICARCONTRATOINC_PRODUCTOSDUMMYVERIFICARCONTRATOINCPCONTRATOC);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // pContratoC.FamiliasC.NumFamilia = pNumFamilia
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandOVParameterAttribute operand112 = new SQLWhereOperandOVParameterAttribute(ProductosConstants.ARG_NAME_DUMMYVERIFICARCONTRATOINC_PRODUCTOSDUMMYVERIFICARCONTRATOINCPCONTRATOC + "." + ContratoCConstants.ROLE_NAME_FAMILIASC + "." + FamiliaCConstants.ATTR_NAME_FAMILIACNUMFAMILIA, ContratoCConstants.CLASS_NAME, productosdummyVerificarContratoIncpContratoC.getOid(), ProductosConstants.ARG_NAME_DUMMYVERIFICARCONTRATOINC_PRODUCTOSDUMMYVERIFICARCONTRATOINCPCONTRATOC, Constants.Type.STRING.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    if (productosdummyVerificarContratoIncpNumFamilia!= null){
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(productosdummyVerificarContratoIncpNumFamilia, Constants.Type.STRING.getTypeName()));
                    } else {
                    comparison1.setSecondOperand(new SQLWhereOperandNull());
                    }
                    conjunction.addOperand(comparison1);


            // pContratoC.FamiliasC.FechaFinVigencia >= pFechaInicioContrato
                    // '>=' Comparison
                    SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
                    comparison2.setOperator(">=");
                    
                    // Operand 1
                    SQLWhereOperandOVParameterAttribute operand113 = new SQLWhereOperandOVParameterAttribute(ProductosConstants.ARG_NAME_DUMMYVERIFICARCONTRATOINC_PRODUCTOSDUMMYVERIFICARCONTRATOINCPCONTRATOC + "." + ContratoCConstants.ROLE_NAME_FAMILIASC + "." + FamiliaCConstants.ATTR_NAME_FAMILIACFECHAFINVIGENCIA, ContratoCConstants.CLASS_NAME, productosdummyVerificarContratoIncpContratoC.getOid(), ProductosConstants.ARG_NAME_DUMMYVERIFICARCONTRATOINC_PRODUCTOSDUMMYVERIFICARCONTRATOINCPCONTRATOC, Constants.Type.DATE.getTypeName());
                    comparison2.setFirstOperand(operand113);
                    
                    // Operand 2
                    comparison2.setSecondOperand(new SQLWhereOperandLiteral(productosdummyVerificarContratoIncpFechaInicioContrato, Constants.Type.DATE.getTypeName()));
                    conjunction.addOperand(comparison2);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Productos, Assoc.Operator: AssocOperator009, Method: assocOperator009Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator010
	@Override
    public Boolean assocOperator010(Productos instance) throws SystemException {
        try {
            // EXIST (AgentexProducto) WHERE (AgentexProducto.Asesor = AGENTAsesor AND AgentexProducto.drvVigente = true)
            for(AgentexProducto item : assocOperator010Range1(instance)){
                // AgentexProducto.drvVigente = true
                if(item.getAgentexProductodrvVigente().equals(true))
                    return true;
            }
            return false;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Productos, Assoc.Operator: AssocOperator010, Method: assocOperator010";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator010
	@Override
    public Collection<AgentexProducto> assocOperator010Range1 (Productos instance) throws SystemException {

        SQLSelect sql = assocOperator010Range1Query(instance);
        return new TypedQueryBuilder<AgentexProducto>(em, AgentexProducto.class).build(sql).execute();
    }

    // Range Query 1 for AssocOperator AssocOperator010
	@Override
    public SQLPopulation assocOperator010Range1Query (Productos instance) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation(ProductosConstants.CLASS_NAME, AgentexProductoConstants.CLASS_NAME, instance.getOid(), ProductosConstants.ROLE_NAME_AGENTEXPRODUCTO);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // AgentexProducto.Asesor = AGENTAsesor
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(ProductosConstants.ROLE_NAME_AGENTEXPRODUCTO + "." + AgentexProductoConstants.ROLE_NAME_ASESOR, AsesorConstants.CLASS_NAME);
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(Constants.AGENT_ASESOR, AsesorConstants.CLASS_NAME, AsesorConstants.CLASS_NAME, (((Asesor)agent.getAgentInstance(AsesorConstants.CLASS_NAME))).getOid(), Constants.AGENT_ASESOR);
                    comparison1.setSecondOperand(operand223);
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Productos, Assoc.Operator: AssocOperator010, Method: assocOperator010Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator011
	@Override
    public Boolean assocOperator011(Productos instance) throws SystemException {
        try {
            // EXIST (AgentexProducto) WHERE (AgentexProducto.Asesor = AGENTAsesor AND AgentexProducto.drvVigente = true)
            for(AgentexProducto item : assocOperator011Range1(instance)){
                // AgentexProducto.drvVigente = true
                if(item.getAgentexProductodrvVigente().equals(true))
                    return true;
            }
            return false;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Productos, Assoc.Operator: AssocOperator011, Method: assocOperator011";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator011
	@Override
    public Collection<AgentexProducto> assocOperator011Range1 (Productos instance) throws SystemException {

        SQLSelect sql = assocOperator011Range1Query(instance);
        return new TypedQueryBuilder<AgentexProducto>(em, AgentexProducto.class).build(sql).execute();
    }

    // Range Query 1 for AssocOperator AssocOperator011
	@Override
    public SQLPopulation assocOperator011Range1Query (Productos instance) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation(ProductosConstants.CLASS_NAME, AgentexProductoConstants.CLASS_NAME, instance.getOid(), ProductosConstants.ROLE_NAME_AGENTEXPRODUCTO);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // AgentexProducto.Asesor = AGENTAsesor
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(ProductosConstants.ROLE_NAME_AGENTEXPRODUCTO + "." + AgentexProductoConstants.ROLE_NAME_ASESOR, AsesorConstants.CLASS_NAME);
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(Constants.AGENT_ASESOR, AsesorConstants.CLASS_NAME, AsesorConstants.CLASS_NAME, (((Asesor)agent.getAgentInstance(AsesorConstants.CLASS_NAME))).getOid(), Constants.AGENT_ASESOR);
                    comparison1.setSecondOperand(operand223);
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Productos, Assoc.Operator: AssocOperator011, Method: assocOperator011Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator012
	@Override
    public Boolean assocOperator012(Productos instance) throws SystemException {
        try {
            // EXIST (AgentexProducto) WHERE (AgentexProducto.Asesor = AGENTAsesor AND AgentexProducto.drvVigente = true)
            for(AgentexProducto item : assocOperator012Range1(instance)){
                // AgentexProducto.drvVigente = true
                if(item.getAgentexProductodrvVigente().equals(true))
                    return true;
            }
            return false;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Productos, Assoc.Operator: AssocOperator012, Method: assocOperator012";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator012
	@Override
    public Collection<AgentexProducto> assocOperator012Range1 (Productos instance) throws SystemException {

        SQLSelect sql = assocOperator012Range1Query(instance);
        return new TypedQueryBuilder<AgentexProducto>(em, AgentexProducto.class).build(sql).execute();
    }

    // Range Query 1 for AssocOperator AssocOperator012
	@Override
    public SQLPopulation assocOperator012Range1Query (Productos instance) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation(ProductosConstants.CLASS_NAME, AgentexProductoConstants.CLASS_NAME, instance.getOid(), ProductosConstants.ROLE_NAME_AGENTEXPRODUCTO);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // AgentexProducto.Asesor = AGENTAsesor
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(ProductosConstants.ROLE_NAME_AGENTEXPRODUCTO + "." + AgentexProductoConstants.ROLE_NAME_ASESOR, AsesorConstants.CLASS_NAME);
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(Constants.AGENT_ASESOR, AsesorConstants.CLASS_NAME, AsesorConstants.CLASS_NAME, (((Asesor)agent.getAgentInstance(AsesorConstants.CLASS_NAME))).getOid(), Constants.AGENT_ASESOR);
                    comparison1.setSecondOperand(operand223);
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Productos, Assoc.Operator: AssocOperator012, Method: assocOperator012Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator013
	@Override
    public Boolean assocOperator013(Productos instance) throws SystemException {
        try {
            // EXIST (AgentexProducto) WHERE (AgentexProducto.Asesor = AGENTAsesor AND AgentexProducto.drvVigente = true)
            for(AgentexProducto item : assocOperator013Range1(instance)){
                // AgentexProducto.drvVigente = true
                if(item.getAgentexProductodrvVigente().equals(true))
                    return true;
            }
            return false;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Productos, Assoc.Operator: AssocOperator013, Method: assocOperator013";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator013
	@Override
    public Collection<AgentexProducto> assocOperator013Range1 (Productos instance) throws SystemException {

        SQLSelect sql = assocOperator013Range1Query(instance);
        return new TypedQueryBuilder<AgentexProducto>(em, AgentexProducto.class).build(sql).execute();
    }

    // Range Query 1 for AssocOperator AssocOperator013
	@Override
    public SQLPopulation assocOperator013Range1Query (Productos instance) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation(ProductosConstants.CLASS_NAME, AgentexProductoConstants.CLASS_NAME, instance.getOid(), ProductosConstants.ROLE_NAME_AGENTEXPRODUCTO);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // AgentexProducto.Asesor = AGENTAsesor
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(ProductosConstants.ROLE_NAME_AGENTEXPRODUCTO + "." + AgentexProductoConstants.ROLE_NAME_ASESOR, AsesorConstants.CLASS_NAME);
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(Constants.AGENT_ASESOR, AsesorConstants.CLASS_NAME, AsesorConstants.CLASS_NAME, (((Asesor)agent.getAgentInstance(AsesorConstants.CLASS_NAME))).getOid(), Constants.AGENT_ASESOR);
                    comparison1.setSecondOperand(operand223);
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Productos, Assoc.Operator: AssocOperator013, Method: assocOperator013Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    @Override
    public void refreshEntity(Object object) {
        super.refreshEntity(object);
    }
}
