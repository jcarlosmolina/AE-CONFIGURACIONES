package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.DominioConstants;
import com.keralty.aeconfiguraciones.global.LogDatosDominioConstants;
import com.keralty.aeconfiguraciones.global.LogGruposUsuariosConstants;
import com.keralty.aeconfiguraciones.global.LogIncompatibilidadesConstants;
import com.keralty.aeconfiguraciones.global.ParentescoDomConstants;
import com.keralty.aeconfiguraciones.global.PeriodicidadConstants;
import com.keralty.aeconfiguraciones.global.PlanesConstants;
import com.keralty.aeconfiguraciones.global.PlantillaEmailConstants;
import com.keralty.aeconfiguraciones.global.PlantillaReporteConstants;
import com.keralty.aeconfiguraciones.global.ProductosConstants;
import com.keralty.aeconfiguraciones.global.ProrrateoConstants;
import com.keralty.aeconfiguraciones.global.ReportesDominioConstants;
import com.keralty.aeconfiguraciones.global.RolConstants;
import com.keralty.aeconfiguraciones.global.UserFunctions;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.LogDatosDominio;
import com.keralty.aeconfiguraciones.persistence.LogGruposUsuarios;
import com.keralty.aeconfiguraciones.persistence.LogIncompatibilidades;
import com.keralty.aeconfiguraciones.persistence.ParentescoDom;
import com.keralty.aeconfiguraciones.persistence.Periodicidad;
import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.persistence.PlantillaEmail;
import com.keralty.aeconfiguraciones.persistence.PlantillaReporte;
import com.keralty.aeconfiguraciones.persistence.Productos;
import com.keralty.aeconfiguraciones.persistence.Prorrateo;
import com.keralty.aeconfiguraciones.persistence.Rol;
import com.keralty.aeconfiguraciones.sqlutils.SQLCount;
import com.keralty.aeconfiguraciones.sqlutils.SQLExists;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLPopulation;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereConjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandOVParameter;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandPathAttribute;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandPathInstance;
import com.keralty.aeconfiguraciones.sqlutils.TypedQueryBuilder;
import com.keralty.aeconfiguraciones.utils.Agent;
import jakarta.inject.Inject;
import java.util.Collection;
import java.util.List;

public class DominioJpaRepositoryImpl extends AbstractRepository implements DominioJpaRepositoryCustom {

    @Inject
    public DominioJpaRepositoryImpl(Agent agent, UserFunctions userFunctionsSrv) {
        super(agent, userFunctionsSrv);
    }

    @Override
    public List<Dominio> executeQuery(String jpqlQuery) {
        return getQueryBuilder().build(jpqlQuery).execute();
    }
    
    @Override
    public List<Dominio> executeQuery(String jpqlQuery, List<SQLParam> params) {
        return getQueryBuilder().build(jpqlQuery).setParameters(params).execute();
    }
    
    public Collection<Dominio> executeQuery(SQLSelect select) {
        return getQueryBuilder().build(select).execute();
    }

    private TypedQueryBuilder<Dominio> getQueryBuilder() {
        return new TypedQueryBuilder<>(em, Dominio.class);
    }

    @Override	
    public List<Dominio> solveQuery(SQLSelect select) {
        return solveQuery(select, 0, 0);
    }

    @Override
    public List<Dominio> solveQuery(SQLSelect select, int pageSize, int pageNumber) {
        return getQueryBuilder().build(select, pageSize, pageNumber).execute();
    }

    // AssocOperator: AssocOperator000
	@Override
    public Boolean assocOperator000(Rol voRol) throws SystemException {
        try {
            // EXIST (Roles) WHERE (Roles = voRol)
            return assocOperator000Range1(voRol);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Dominio, Assoc.Operator: AssocOperator000, Method: assocOperator000";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 0 for AssocOperator AssocOperator000
	@Override
    public Boolean assocOperator000Range1 (Rol voRol) throws SystemException {
        SQLExists sql = assocOperator000Range1Query(voRol, null);
        return !new TypedQueryBuilder<Dominio>(em, Dominio.class).build(sql).execute().isEmpty();
    }

    // Range Query 0 for AssocOperator AssocOperator000
	@Override
    public SQLExists assocOperator000Range1Query (Rol voRol, SQLSelect superQuery) throws SystemException {
        try {
            // The path starts with a role
            SQLExists sql = new SQLExists(superQuery, DominioConstants.CLASS_NAME, DominioConstants.ROLE_NAME_ROLES);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Roles = voRol
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(DominioConstants.ROLE_NAME_ROLES, RolConstants.CLASS_NAME);
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(DominioConstants.VAR_NAME_FDOMINIOVARIOS_VOROL, RolConstants.CLASS_NAME, RolConstants.CLASS_NAME, voRol.getOid(), DominioConstants.VAR_NAME_FDOMINIOVARIOS_VOROL);
                    comparison1.setSecondOperand(operand223);
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Dominio, Assoc.Operator: AssocOperator000, Method: assocOperator000Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator001
	@Override
    public Boolean assocOperator001(Productos dominiocrearpagrProductoT, Planes dominiocrearpagrPlanT) throws SystemException {
        try {
            // EXIST (pagrProductoT.Dominios) WHERE (pagrProductoT.Dominios.PlanT = pagrPlanT)
            return assocOperator001Range1(dominiocrearpagrProductoT, dominiocrearpagrPlanT);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Dominio, Assoc.Operator: AssocOperator001, Method: assocOperator001";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator001
	@Override
    public Boolean assocOperator001Range1 (Productos dominiocrearpagrProductoT, Planes dominiocrearpagrPlanT) throws SystemException {
        SQLExists sql = assocOperator001Range1Query(dominiocrearpagrProductoT, dominiocrearpagrPlanT, null);
        return !new TypedQueryBuilder<Dominio>(em, Dominio.class).build(sql).execute().isEmpty();
    }

    // Range Query 1 for AssocOperator AssocOperator001
	@Override
    public SQLExists assocOperator001Range1Query (Productos dominiocrearpagrProductoT, Planes dominiocrearpagrPlanT, SQLSelect superQuery) throws SystemException {
        try {
            SQLExists sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLExists(superQuery, DominioConstants.CLASS_NAME, DominioConstants.ARG_NAME_CREAR_DOMINIOCREARPAGRPRODUCTOT + "." + ProductosConstants.ROLE_NAME_DOMINIOS, ProductosConstants.CLASS_NAME, dominiocrearpagrProductoT.getOid(), DominioConstants.ARG_NAME_CREAR_DOMINIOCREARPAGRPRODUCTOT);
            } else {
                sql = new SQLExists(DominioConstants.CLASS_NAME, DominioConstants.ARG_NAME_CREAR_DOMINIOCREARPAGRPRODUCTOT + "." + ProductosConstants.ROLE_NAME_DOMINIOS, ProductosConstants.CLASS_NAME, dominiocrearpagrProductoT.getOid(), DominioConstants.ARG_NAME_CREAR_DOMINIOCREARPAGRPRODUCTOT);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // pagrProductoT.Dominios.PlanT = pagrPlanT
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandOVParameter operand112 = new SQLWhereOperandOVParameter(DominioConstants.ARG_NAME_CREAR_DOMINIOCREARPAGRPRODUCTOT + "." + ProductosConstants.ROLE_NAME_DOMINIOS + "." + DominioConstants.ROLE_NAME_PLANT, ProductosConstants.CLASS_NAME, PlanesConstants.CLASS_NAME, dominiocrearpagrProductoT.getOid(), DominioConstants.ARG_NAME_CREAR_DOMINIOCREARPAGRPRODUCTOT);
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(DominioConstants.ARG_NAME_CREAR_DOMINIOCREARPAGRPLANT, PlanesConstants.CLASS_NAME, PlanesConstants.CLASS_NAME, dominiocrearpagrPlanT.getOid(), DominioConstants.ARG_NAME_CREAR_DOMINIOCREARPAGRPLANT);
                    comparison1.setSecondOperand(operand223);
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Dominio, Assoc.Operator: AssocOperator001, Method: assocOperator001Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator002
	@Override
    public Boolean assocOperator002(Planes dominiocrearpagrPlanT, Productos dominiocrearpagrProductoT) throws SystemException {
        try {
            // EXIST (pagrPlanT.Dominios) WHERE (pagrPlanT.Dominios.ProductoT = pagrProductoT)
            return assocOperator002Range1(dominiocrearpagrPlanT, dominiocrearpagrProductoT);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Dominio, Assoc.Operator: AssocOperator002, Method: assocOperator002";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator002
	@Override
    public Boolean assocOperator002Range1 (Planes dominiocrearpagrPlanT, Productos dominiocrearpagrProductoT) throws SystemException {
        SQLExists sql = assocOperator002Range1Query(dominiocrearpagrPlanT, dominiocrearpagrProductoT, null);
        return !new TypedQueryBuilder<Dominio>(em, Dominio.class).build(sql).execute().isEmpty();
    }

    // Range Query 1 for AssocOperator AssocOperator002
	@Override
    public SQLExists assocOperator002Range1Query (Planes dominiocrearpagrPlanT, Productos dominiocrearpagrProductoT, SQLSelect superQuery) throws SystemException {
        try {
            SQLExists sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLExists(superQuery, DominioConstants.CLASS_NAME, DominioConstants.ARG_NAME_CREAR_DOMINIOCREARPAGRPLANT + "." + PlanesConstants.ROLE_NAME_DOMINIOS, PlanesConstants.CLASS_NAME, dominiocrearpagrPlanT.getOid(), DominioConstants.ARG_NAME_CREAR_DOMINIOCREARPAGRPLANT);
            } else {
                sql = new SQLExists(DominioConstants.CLASS_NAME, DominioConstants.ARG_NAME_CREAR_DOMINIOCREARPAGRPLANT + "." + PlanesConstants.ROLE_NAME_DOMINIOS, PlanesConstants.CLASS_NAME, dominiocrearpagrPlanT.getOid(), DominioConstants.ARG_NAME_CREAR_DOMINIOCREARPAGRPLANT);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // pagrPlanT.Dominios.ProductoT = pagrProductoT
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandOVParameter operand112 = new SQLWhereOperandOVParameter(DominioConstants.ARG_NAME_CREAR_DOMINIOCREARPAGRPLANT + "." + PlanesConstants.ROLE_NAME_DOMINIOS + "." + DominioConstants.ROLE_NAME_PRODUCTOT, PlanesConstants.CLASS_NAME, ProductosConstants.CLASS_NAME, dominiocrearpagrPlanT.getOid(), DominioConstants.ARG_NAME_CREAR_DOMINIOCREARPAGRPLANT);
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(DominioConstants.ARG_NAME_CREAR_DOMINIOCREARPAGRPRODUCTOT, ProductosConstants.CLASS_NAME, ProductosConstants.CLASS_NAME, dominiocrearpagrProductoT.getOid(), DominioConstants.ARG_NAME_CREAR_DOMINIOCREARPAGRPRODUCTOT);
                    comparison1.setSecondOperand(operand223);
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Dominio, Assoc.Operator: AssocOperator002, Method: assocOperator002Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator003
	@Override
    public Long assocOperator003(Dominio instance, PlantillaReporte dominioTADDREPORTEptpagrPlantillaReporte) throws SystemException {
        try {
            // COUNT (ReportesDominio) WHERE (ReportesDominio.PlantillaReporte = ptpagrPlantillaReporte)
            return assocOperator003Range1(instance, dominioTADDREPORTEptpagrPlantillaReporte);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Dominio, Assoc.Operator: AssocOperator003, Method: assocOperator003";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator003
	@Override
    public Long assocOperator003Range1 (Dominio instance, PlantillaReporte dominioTADDREPORTEptpagrPlantillaReporte) throws SystemException {
        SQLCount sql = assocOperator003Range1Query(instance, dominioTADDREPORTEptpagrPlantillaReporte);
        return Long.valueOf(solveCountQuery(sql));
    }

    // Range Query 1 for AssocOperator AssocOperator003
	@Override
    public SQLCount assocOperator003Range1Query (Dominio instance, PlantillaReporte dominioTADDREPORTEptpagrPlantillaReporte) throws SystemException {
        try {
            // The path starts with a role
            SQLCount sql = new SQLCount(DominioConstants.CLASS_NAME, DominioConstants.ROLE_NAME_REPORTESDOMINIO, instance.getOid());
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // ReportesDominio.PlantillaReporte = ptpagrPlantillaReporte
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(DominioConstants.ROLE_NAME_REPORTESDOMINIO + "." + ReportesDominioConstants.ROLE_NAME_PLANTILLAREPORTE, PlantillaReporteConstants.CLASS_NAME);
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(DominioConstants.ARG_NAME_TADDREPORTE_DOMINIOTADDREPORTEPTPAGRPLANTILLAREPORTE, PlantillaReporteConstants.CLASS_NAME, PlantillaReporteConstants.CLASS_NAME, dominioTADDREPORTEptpagrPlantillaReporte.getOid(), DominioConstants.ARG_NAME_TADDREPORTE_DOMINIOTADDREPORTEPTPAGRPLANTILLAREPORTE);
                    comparison1.setSecondOperand(operand223);
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Dominio, Assoc.Operator: AssocOperator003, Method: assocOperator003Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator004
	@Override
    public Long assocOperator004(Dominio instance, PlantillaReporte dominioTADDREPORTEptpagrPlantillaReporte) throws SystemException {
        try {
            // COUNT (ReportesDominio) WHERE (ReportesDominio.PlantillaReporte.Tipo = ptpagrPlantillaReporte.Tipo AND ReportesDominio.PlantillaReporte.FechaVigencia = ptpagrPlantillaReporte.FechaVigencia)
            return assocOperator004Range1(instance, dominioTADDREPORTEptpagrPlantillaReporte);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Dominio, Assoc.Operator: AssocOperator004, Method: assocOperator004";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator004
	@Override
    public Long assocOperator004Range1 (Dominio instance, PlantillaReporte dominioTADDREPORTEptpagrPlantillaReporte) throws SystemException {
        SQLCount sql = assocOperator004Range1Query(instance, dominioTADDREPORTEptpagrPlantillaReporte);
        return Long.valueOf(solveCountQuery(sql));
    }

    // Range Query 1 for AssocOperator AssocOperator004
	@Override
    public SQLCount assocOperator004Range1Query (Dominio instance, PlantillaReporte dominioTADDREPORTEptpagrPlantillaReporte) throws SystemException {
        try {
            // The path starts with a role
            SQLCount sql = new SQLCount(DominioConstants.CLASS_NAME, DominioConstants.ROLE_NAME_REPORTESDOMINIO, instance.getOid());
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // ReportesDominio.PlantillaReporte.Tipo = ptpagrPlantillaReporte.Tipo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(DominioConstants.ROLE_NAME_REPORTESDOMINIO + "." + ReportesDominioConstants.ROLE_NAME_PLANTILLAREPORTE + "." + PlantillaReporteConstants.ATTR_NAME_PLANTILLAREPORTETIPO, Constants.Type.NAT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(dominioTADDREPORTEptpagrPlantillaReporte.getPlantillaReporteTipo(), Constants.Type.NAT.getTypeName()));
                    conjunction.addOperand(comparison1);


            // ReportesDominio.PlantillaReporte.FechaVigencia = ptpagrPlantillaReporte.FechaVigencia
                    // '=' Comparison
                    SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
                    comparison2.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand113 = new SQLWhereOperandPathAttribute(DominioConstants.ROLE_NAME_REPORTESDOMINIO + "." + ReportesDominioConstants.ROLE_NAME_PLANTILLAREPORTE + "." + PlantillaReporteConstants.ATTR_NAME_PLANTILLAREPORTEFECHAVIGENCIA, Constants.Type.DATE.getTypeName());
                    comparison2.setFirstOperand(operand113);
                    
                    // Operand 2
                    comparison2.setSecondOperand(new SQLWhereOperandLiteral(dominioTADDREPORTEptpagrPlantillaReporte.getPlantillaReporteFechaVigencia(), Constants.Type.DATE.getTypeName()));
                    conjunction.addOperand(comparison2);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Dominio, Assoc.Operator: AssocOperator004, Method: assocOperator004Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator005
	@Override
    public Long assocOperator005(Dominio instance, Dominio dominioInsIncompatiblepevcDominio) throws SystemException {
        try {
            // COUNT (DominiosIncompatibles) WHERE (DominiosIncompatibles = pevcDominio)
            return assocOperator005Range1(instance, dominioInsIncompatiblepevcDominio);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Dominio, Assoc.Operator: AssocOperator005, Method: assocOperator005";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator005
	@Override
    public Long assocOperator005Range1 (Dominio instance, Dominio dominioInsIncompatiblepevcDominio) throws SystemException {
        SQLCount sql = assocOperator005Range1Query(instance, dominioInsIncompatiblepevcDominio);
        return Long.valueOf(solveCountQuery(sql));
    }

    // Range Query 1 for AssocOperator AssocOperator005
	@Override
    public SQLCount assocOperator005Range1Query (Dominio instance, Dominio dominioInsIncompatiblepevcDominio) throws SystemException {
        try {
            // The path starts with a role
            SQLCount sql = new SQLCount(DominioConstants.CLASS_NAME, DominioConstants.ROLE_NAME_DOMINIOSINCOMPATIBLES, instance.getOid());
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // DominiosIncompatibles = pevcDominio
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(DominioConstants.ROLE_NAME_DOMINIOSINCOMPATIBLES, DominioConstants.CLASS_NAME);
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(DominioConstants.ARG_NAME_INSINCOMPATIBLE_DOMINIOINSINCOMPATIBLEPEVCDOMINIO, DominioConstants.CLASS_NAME, DominioConstants.CLASS_NAME, dominioInsIncompatiblepevcDominio.getOid(), DominioConstants.ARG_NAME_INSINCOMPATIBLE_DOMINIOINSINCOMPATIBLEPEVCDOMINIO);
                    comparison1.setSecondOperand(operand223);
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Dominio, Assoc.Operator: AssocOperator005, Method: assocOperator005Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator006
	@Override
    public Collection<ParentescoDom> assocOperator006(Dominio instance) throws SystemException {
        try {
            // FOR ALL ParentescosDom DO ParentescosDom.eliminar(ParentescosDom)
        return assocOperator006Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Dominio, Assoc.Operator: AssocOperator006, Method: assocOperator006";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator006
	@Override
    public Collection<ParentescoDom> assocOperator006Range1 (Dominio instance) throws SystemException {

        SQLSelect sql = assocOperator006Range1Query(instance);
        return new TypedQueryBuilder<ParentescoDom>(em, ParentescoDom.class).build(sql).execute();
    }

    // Range Query 1 for AssocOperator AssocOperator006
	@Override
    public SQLPopulation assocOperator006Range1Query (Dominio instance) throws SystemException {
        try {
            return new SQLPopulation(DominioConstants.CLASS_NAME, ParentescoDomConstants.CLASS_NAME, instance.getOid(), DominioConstants.ROLE_NAME_PARENTESCOSDOM);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Dominio, Assoc.Operator: AssocOperator006, Method: assocOperator006Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator007
	@Override
    public Collection<Periodicidad> assocOperator007(Dominio instance) throws SystemException {
        try {
            // FOR ALL Periodicidades DO DelPeriodicidadDominio(this, Periodicidades)
        return assocOperator007Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Dominio, Assoc.Operator: AssocOperator007, Method: assocOperator007";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator007
	@Override
    public Collection<Periodicidad> assocOperator007Range1 (Dominio instance) throws SystemException {

        SQLSelect sql = assocOperator007Range1Query(instance);
        return new TypedQueryBuilder<Periodicidad>(em, Periodicidad.class).build(sql).execute();
    }

    // Range Query 1 for AssocOperator AssocOperator007
	@Override
    public SQLPopulation assocOperator007Range1Query (Dominio instance) throws SystemException {
        try {
            return new SQLPopulation(DominioConstants.CLASS_NAME, PeriodicidadConstants.CLASS_NAME, instance.getOid(), DominioConstants.ROLE_NAME_PERIODICIDADES);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Dominio, Assoc.Operator: AssocOperator007, Method: assocOperator007Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator008
	@Override
    public Collection<Rol> assocOperator008(Dominio instance) throws SystemException {
        try {
            // FOR ALL Roles DO DelRollDominio(this, Roles)
        return assocOperator008Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Dominio, Assoc.Operator: AssocOperator008, Method: assocOperator008";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator008
	@Override
    public Collection<Rol> assocOperator008Range1 (Dominio instance) throws SystemException {

        SQLSelect sql = assocOperator008Range1Query(instance);
        return new TypedQueryBuilder<Rol>(em, Rol.class).build(sql).execute();
    }

    // Range Query 1 for AssocOperator AssocOperator008
	@Override
    public SQLPopulation assocOperator008Range1Query (Dominio instance) throws SystemException {
        try {
            return new SQLPopulation(DominioConstants.CLASS_NAME, RolConstants.CLASS_NAME, instance.getOid(), DominioConstants.ROLE_NAME_ROLES);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Dominio, Assoc.Operator: AssocOperator008, Method: assocOperator008Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator009
	@Override
    public Collection<Prorrateo> assocOperator009(Dominio instance) throws SystemException {
        try {
            // FOR ALL Prorrateos DO Prorrateos.eliminar(Prorrateos)
        return assocOperator009Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Dominio, Assoc.Operator: AssocOperator009, Method: assocOperator009";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator009
	@Override
    public Collection<Prorrateo> assocOperator009Range1 (Dominio instance) throws SystemException {

        SQLSelect sql = assocOperator009Range1Query(instance);
        return new TypedQueryBuilder<Prorrateo>(em, Prorrateo.class).build(sql).execute();
    }

    // Range Query 1 for AssocOperator AssocOperator009
	@Override
    public SQLPopulation assocOperator009Range1Query (Dominio instance) throws SystemException {
        try {
            return new SQLPopulation(DominioConstants.CLASS_NAME, ProrrateoConstants.CLASS_NAME, instance.getOid(), DominioConstants.ROLE_NAME_PRORRATEOS);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Dominio, Assoc.Operator: AssocOperator009, Method: assocOperator009Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator010
	@Override
    public Collection<LogDatosDominio> assocOperator010(Dominio instance) throws SystemException {
        try {
            // FOR ALL LogDatosDominio DO LogDatosDominio.eliminar(LogDatosDominio)
        return assocOperator010Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Dominio, Assoc.Operator: AssocOperator010, Method: assocOperator010";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator010
	@Override
    public Collection<LogDatosDominio> assocOperator010Range1 (Dominio instance) throws SystemException {

        SQLSelect sql = assocOperator010Range1Query(instance);
        return new TypedQueryBuilder<LogDatosDominio>(em, LogDatosDominio.class).build(sql).execute();
    }

    // Range Query 1 for AssocOperator AssocOperator010
	@Override
    public SQLPopulation assocOperator010Range1Query (Dominio instance) throws SystemException {
        try {
            return new SQLPopulation(DominioConstants.CLASS_NAME, LogDatosDominioConstants.CLASS_NAME, instance.getOid(), DominioConstants.ROLE_NAME_LOGDATOSDOMINIO);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Dominio, Assoc.Operator: AssocOperator010, Method: assocOperator010Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator011
	@Override
    public Collection<LogGruposUsuarios> assocOperator011(Dominio instance) throws SystemException {
        try {
            // FOR ALL LogGruposUsuarios DO LogGruposUsuarios.eliminar(LogGruposUsuarios)
        return assocOperator011Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Dominio, Assoc.Operator: AssocOperator011, Method: assocOperator011";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator011
	@Override
    public Collection<LogGruposUsuarios> assocOperator011Range1 (Dominio instance) throws SystemException {

        SQLSelect sql = assocOperator011Range1Query(instance);
        return new TypedQueryBuilder<LogGruposUsuarios>(em, LogGruposUsuarios.class).build(sql).execute();
    }

    // Range Query 1 for AssocOperator AssocOperator011
	@Override
    public SQLPopulation assocOperator011Range1Query (Dominio instance) throws SystemException {
        try {
            return new SQLPopulation(DominioConstants.CLASS_NAME, LogGruposUsuariosConstants.CLASS_NAME, instance.getOid(), DominioConstants.ROLE_NAME_LOGGRUPOSUSUARIOS);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Dominio, Assoc.Operator: AssocOperator011, Method: assocOperator011Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator012
	@Override
    public Collection<LogIncompatibilidades> assocOperator012(Dominio instance) throws SystemException {
        try {
            // FOR ALL LogIncompatibilidades DO LogIncompatibilidades.eliminar(LogIncompatibilidades)
        return assocOperator012Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Dominio, Assoc.Operator: AssocOperator012, Method: assocOperator012";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator012
	@Override
    public Collection<LogIncompatibilidades> assocOperator012Range1 (Dominio instance) throws SystemException {

        SQLSelect sql = assocOperator012Range1Query(instance);
        return new TypedQueryBuilder<LogIncompatibilidades>(em, LogIncompatibilidades.class).build(sql).execute();
    }

    // Range Query 1 for AssocOperator AssocOperator012
	@Override
    public SQLPopulation assocOperator012Range1Query (Dominio instance) throws SystemException {
        try {
            return new SQLPopulation(DominioConstants.CLASS_NAME, LogIncompatibilidadesConstants.CLASS_NAME, instance.getOid(), DominioConstants.ROLE_NAME_LOGINCOMPATIBILIDADES);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Dominio, Assoc.Operator: AssocOperator012, Method: assocOperator012Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator013
	@Override
    public Collection<PlantillaEmail> assocOperator013(Dominio instance, PlantillaEmail dominioTINSPLANTILLAptPlantilla) throws SystemException {
        try {
            // FOR ALL Plantillas WHERE (Plantillas.Codigo = ptPlantilla.Codigo) DO DelPlantilla(this, Plantillas)
        return assocOperator013Range1(instance, dominioTINSPLANTILLAptPlantilla);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Dominio, Assoc.Operator: AssocOperator013, Method: assocOperator013";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator013
	@Override
    public Collection<PlantillaEmail> assocOperator013Range1 (Dominio instance, PlantillaEmail dominioTINSPLANTILLAptPlantilla) throws SystemException {

        SQLSelect sql = assocOperator013Range1Query(instance, dominioTINSPLANTILLAptPlantilla);
        return new TypedQueryBuilder<PlantillaEmail>(em, PlantillaEmail.class).build(sql).execute();
    }

    // Range Query 1 for AssocOperator AssocOperator013
	@Override
    public SQLPopulation assocOperator013Range1Query (Dominio instance, PlantillaEmail dominioTINSPLANTILLAptPlantilla) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation(DominioConstants.CLASS_NAME, PlantillaEmailConstants.CLASS_NAME, instance.getOid(), DominioConstants.ROLE_NAME_PLANTILLAS);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Plantillas.Codigo = ptPlantilla.Codigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(DominioConstants.ROLE_NAME_PLANTILLAS + "." + PlantillaEmailConstants.ATTR_NAME_PLANTILLAEMAILCODIGO, Constants.Type.NAT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(dominioTINSPLANTILLAptPlantilla.getPlantillaEmailCodigo(), Constants.Type.NAT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Dominio, Assoc.Operator: AssocOperator013, Method: assocOperator013Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator015
	@Override
    public Boolean assocOperator015(Dominio instance) throws SystemException {
        try {
            // EXIST (GrupoAsesores)
            return assocOperator015Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Dominio, Assoc.Operator: AssocOperator015, Method: assocOperator015";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator015
	@Override
    public Boolean assocOperator015Range1 (Dominio instance) throws SystemException {
        SQLExists sql = assocOperator015Range1Query(instance);
        return !new TypedQueryBuilder<Dominio>(em, Dominio.class).build(sql).execute().isEmpty();
    }

    // Range Query 1 for AssocOperator AssocOperator015
	@Override
    public SQLExists assocOperator015Range1Query (Dominio instance) throws SystemException {
        try {
            // The path starts with a role
            return new SQLExists(DominioConstants.CLASS_NAME, DominioConstants.ROLE_NAME_GRUPOASESORES, instance.getOid());
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Dominio, Assoc.Operator: AssocOperator015, Method: assocOperator015Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator017
	@Override
    public Boolean assocOperator017(Dominio instance) throws SystemException {
        try {
            // EXIST (GrupoAreaMedica)
            return assocOperator017Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Dominio, Assoc.Operator: AssocOperator017, Method: assocOperator017";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator017
	@Override
    public Boolean assocOperator017Range1 (Dominio instance) throws SystemException {
        SQLExists sql = assocOperator017Range1Query(instance);
        return !new TypedQueryBuilder<Dominio>(em, Dominio.class).build(sql).execute().isEmpty();
    }

    // Range Query 1 for AssocOperator AssocOperator017
	@Override
    public SQLExists assocOperator017Range1Query (Dominio instance) throws SystemException {
        try {
            // The path starts with a role
            return new SQLExists(DominioConstants.CLASS_NAME, DominioConstants.ROLE_NAME_GRUPOAREAMEDICA, instance.getOid());
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Dominio, Assoc.Operator: AssocOperator017, Method: assocOperator017Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    @Override
    public void refreshEntity(Object object) {
        super.refreshEntity(object);
    }
}
