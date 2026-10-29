package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.CompanyiaConstants;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.ContratosExentosFIngresoConstants;
import com.keralty.aeconfiguraciones.global.DepartamentoConstants;
import com.keralty.aeconfiguraciones.global.EntidadFinancieraCConstants;
import com.keralty.aeconfiguraciones.global.EpsConstants;
import com.keralty.aeconfiguraciones.global.EstadoBeneficiarioConstants;
import com.keralty.aeconfiguraciones.global.EstadoCivilConstants;
import com.keralty.aeconfiguraciones.global.EstadoContratoConstants;
import com.keralty.aeconfiguraciones.global.EstadoPersonaConstants;
import com.keralty.aeconfiguraciones.global.FechaIniEPSConstants;
import com.keralty.aeconfiguraciones.global.FormaPagoConstants;
import com.keralty.aeconfiguraciones.global.FrecuenciaActConstants;
import com.keralty.aeconfiguraciones.global.GrupoAsociadoConstants;
import com.keralty.aeconfiguraciones.global.LogContratoComboConstants;
import com.keralty.aeconfiguraciones.global.MunicipioConstants;
import com.keralty.aeconfiguraciones.global.PaisConstants;
import com.keralty.aeconfiguraciones.global.ParentescoConstants;
import com.keralty.aeconfiguraciones.global.PeriodicidadConstants;
import com.keralty.aeconfiguraciones.global.PlanesConstants;
import com.keralty.aeconfiguraciones.global.PlantillaReporteConstants;
import com.keralty.aeconfiguraciones.global.ProductosConstants;
import com.keralty.aeconfiguraciones.global.RegionalConstants;
import com.keralty.aeconfiguraciones.global.SucursalConstants;
import com.keralty.aeconfiguraciones.global.TipoDireccionConstants;
import com.keralty.aeconfiguraciones.global.TipoEntidadCConstants;
import com.keralty.aeconfiguraciones.global.TipoIdentificacionConstants;
import com.keralty.aeconfiguraciones.global.TipoInstitucionCConstants;
import com.keralty.aeconfiguraciones.global.TipoSucursalCConstants;
import com.keralty.aeconfiguraciones.global.UserFunctions;
import com.keralty.aeconfiguraciones.persistence.Companyia;
import com.keralty.aeconfiguraciones.persistence.ContratoCombo;
import com.keralty.aeconfiguraciones.persistence.ContratosExentosFIngreso;
import com.keralty.aeconfiguraciones.persistence.Departamento;
import com.keralty.aeconfiguraciones.persistence.EntidadFinancieraC;
import com.keralty.aeconfiguraciones.persistence.Eps;
import com.keralty.aeconfiguraciones.persistence.EstadoBeneficiario;
import com.keralty.aeconfiguraciones.persistence.EstadoCivil;
import com.keralty.aeconfiguraciones.persistence.EstadoContrato;
import com.keralty.aeconfiguraciones.persistence.EstadoPersona;
import com.keralty.aeconfiguraciones.persistence.FechaIniEPS;
import com.keralty.aeconfiguraciones.persistence.FormaPago;
import com.keralty.aeconfiguraciones.persistence.FrecuenciaAct;
import com.keralty.aeconfiguraciones.persistence.GrupoAsociado;
import com.keralty.aeconfiguraciones.persistence.LogContratoCombo;
import com.keralty.aeconfiguraciones.persistence.Municipio;
import com.keralty.aeconfiguraciones.persistence.Pais;
import com.keralty.aeconfiguraciones.persistence.Parentesco;
import com.keralty.aeconfiguraciones.persistence.Periodicidad;
import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.persistence.PlantillaReporte;
import com.keralty.aeconfiguraciones.persistence.Productos;
import com.keralty.aeconfiguraciones.persistence.Regional;
import com.keralty.aeconfiguraciones.persistence.Sucursal;
import com.keralty.aeconfiguraciones.persistence.TipoDireccion;
import com.keralty.aeconfiguraciones.persistence.TipoEntidadC;
import com.keralty.aeconfiguraciones.persistence.TipoIdentificacion;
import com.keralty.aeconfiguraciones.persistence.TipoInstitucionC;
import com.keralty.aeconfiguraciones.persistence.TipoSucursalC;
import com.keralty.aeconfiguraciones.sqlutils.SQLCount;
import com.keralty.aeconfiguraciones.sqlutils.SQLExists;
import com.keralty.aeconfiguraciones.sqlutils.SQLPopulation;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereConjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandPathAttribute;
import com.keralty.aeconfiguraciones.sqlutils.TypedQueryBuilder;
import com.keralty.aeconfiguraciones.utils.Agent;
import jakarta.inject.Inject;
import java.sql.Date;
import java.util.Collection;
import org.springframework.stereotype.Service;

@Service
public class GlobalServicesJpaRepositoryImpl extends AbstractRepository implements GlobalServicesJpaRepository {

    @Inject
    public GlobalServicesJpaRepositoryImpl(Agent agent, UserFunctions userFunctionsSrv) {
        super(agent, userFunctionsSrv);
    }

    // AssocOperator: AssocOperator001
	@Override
    public Collection<Productos> assocOperator001(Long tGSINC1PRODUCTOptpatrNumero) throws SystemException {
        try {
            // FOR ALL Productos WHERE (Productos.Producto = ptpatrNumero) DO Productos.sincronizar(Productos, ptpatrDescripcion, ptpatrCodigoLegal)
            return assocOperator001Range1(tGSINC1PRODUCTOptpatrNumero);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator001, Method: assocOperator001";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Collection<Productos> assocOperator001Range1 (Long tGSINC1PRODUCTOptpatrNumero) throws SystemException {

        SQLSelect sql = assocOperator001Range1Query(tGSINC1PRODUCTOptpatrNumero);
        return new TypedQueryBuilder<Productos>(em, Productos.class).build(sql).execute();
    }


    public SQLPopulation assocOperator001Range1Query (Long tGSINC1PRODUCTOptpatrNumero) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation("", ProductosConstants.CLASS_NAME);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Productos.Producto = ptpatrNumero
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(ProductosConstants.CLASS_NAME + "." + ProductosConstants.ATTR_NAME_PRODUCTOSPRODUCTO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1PRODUCTOptpatrNumero, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator001, Method: assocOperator001Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }
    // AssocOperator: AssocOperator002
	@Override
    public Boolean assocOperator002(Long tGSINC1PRODUCTOptpatrNumero) throws SystemException {
        try {
            // EXIST (Productos) WHERE (Productos.Producto = ptpatrNumero)
            return assocOperator002Range1(tGSINC1PRODUCTOptpatrNumero);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator002, Method: assocOperator002";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Boolean assocOperator002Range1 (Long tGSINC1PRODUCTOptpatrNumero) throws SystemException {
        SQLExists sql = assocOperator002Range1Query(tGSINC1PRODUCTOptpatrNumero, null);
        return !new TypedQueryBuilder<Productos>(em, Productos.class).build(sql).execute().isEmpty();
    }


    public SQLExists assocOperator002Range1Query (Long tGSINC1PRODUCTOptpatrNumero, SQLSelect superQuery) throws SystemException {
        try {
            SQLExists sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLExists(superQuery, "", ProductosConstants.CLASS_NAME, ProductosConstants.CLASS_NAME, null, ProductosConstants.CLASS_NAME);
            } else {
                sql = new SQLExists("", ProductosConstants.CLASS_NAME, ProductosConstants.CLASS_NAME, null, ProductosConstants.CLASS_NAME);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Productos.Producto = ptpatrNumero
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(ProductosConstants.CLASS_NAME + "." + ProductosConstants.ATTR_NAME_PRODUCTOSPRODUCTO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1PRODUCTOptpatrNumero, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator002, Method: assocOperator002Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator004
	@Override
    public Collection<Productos> assocOperator004(Long tGSINC1PLANptpatrNumero) throws SystemException {
        try {
            // FOR ALL Productos WHERE (Productos.Producto = ptpatrNumero) DO TGSINC1PLANAUX(Productos, ptpatrCodigo, ptpatrNombre, ptpatrCodigoLegal)
            return assocOperator004Range1(tGSINC1PLANptpatrNumero);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator004, Method: assocOperator004";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Collection<Productos> assocOperator004Range1 (Long tGSINC1PLANptpatrNumero) throws SystemException {

        SQLSelect sql = assocOperator004Range1Query(tGSINC1PLANptpatrNumero);
        return new TypedQueryBuilder<Productos>(em, Productos.class).build(sql).execute();
    }


    public SQLPopulation assocOperator004Range1Query (Long tGSINC1PLANptpatrNumero) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation("", ProductosConstants.CLASS_NAME);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Productos.Producto = ptpatrNumero
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(ProductosConstants.CLASS_NAME + "." + ProductosConstants.ATTR_NAME_PRODUCTOSPRODUCTO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1PLANptpatrNumero, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator004, Method: assocOperator004Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }
    // AssocOperator: AssocOperator005
	@Override
    public Boolean assocOperator005(Long tGSINC1PLANptpatrNumero) throws SystemException {
        try {
            // EXIST (Productos) WHERE (Productos.Producto = ptpatrNumero)
            return assocOperator005Range1(tGSINC1PLANptpatrNumero);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator005, Method: assocOperator005";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Boolean assocOperator005Range1 (Long tGSINC1PLANptpatrNumero) throws SystemException {
        SQLExists sql = assocOperator005Range1Query(tGSINC1PLANptpatrNumero, null);
        return !new TypedQueryBuilder<Productos>(em, Productos.class).build(sql).execute().isEmpty();
    }


    public SQLExists assocOperator005Range1Query (Long tGSINC1PLANptpatrNumero, SQLSelect superQuery) throws SystemException {
        try {
            SQLExists sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLExists(superQuery, "", ProductosConstants.CLASS_NAME, ProductosConstants.CLASS_NAME, null, ProductosConstants.CLASS_NAME);
            } else {
                sql = new SQLExists("", ProductosConstants.CLASS_NAME, ProductosConstants.CLASS_NAME, null, ProductosConstants.CLASS_NAME);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Productos.Producto = ptpatrNumero
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(ProductosConstants.CLASS_NAME + "." + ProductosConstants.ATTR_NAME_PRODUCTOSPRODUCTO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1PLANptpatrNumero, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator005, Method: assocOperator005Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator007
	@Override
    public Collection<Planes> assocOperator007(Long tGSINC1PLANAUXptpatrCodigo) throws SystemException {
        try {
            // FOR ALL Planes WHERE (Planes.Plan = ptpatrCodigo) DO Planes.TSINCRONIZAR(Planes, ptpatrNombre, ptpagrProducto, ptpatrCodigoLegal)
            return assocOperator007Range1(tGSINC1PLANAUXptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator007, Method: assocOperator007";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Collection<Planes> assocOperator007Range1 (Long tGSINC1PLANAUXptpatrCodigo) throws SystemException {

        SQLSelect sql = assocOperator007Range1Query(tGSINC1PLANAUXptpatrCodigo);
        return new TypedQueryBuilder<Planes>(em, Planes.class).build(sql).execute();
    }


    public SQLPopulation assocOperator007Range1Query (Long tGSINC1PLANAUXptpatrCodigo) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation("", PlanesConstants.CLASS_NAME);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Planes.Plan = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(PlanesConstants.CLASS_NAME + "." + PlanesConstants.ATTR_NAME_PLANESPLAN, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1PLANAUXptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator007, Method: assocOperator007Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }
    // AssocOperator: AssocOperator008
	@Override
    public Boolean assocOperator008(Long tGSINC1PLANAUXptpatrCodigo) throws SystemException {
        try {
            // EXIST (Planes) WHERE (Planes.Plan = ptpatrCodigo)
            return assocOperator008Range1(tGSINC1PLANAUXptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator008, Method: assocOperator008";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Boolean assocOperator008Range1 (Long tGSINC1PLANAUXptpatrCodigo) throws SystemException {
        SQLExists sql = assocOperator008Range1Query(tGSINC1PLANAUXptpatrCodigo, null);
        return !new TypedQueryBuilder<Planes>(em, Planes.class).build(sql).execute().isEmpty();
    }


    public SQLExists assocOperator008Range1Query (Long tGSINC1PLANAUXptpatrCodigo, SQLSelect superQuery) throws SystemException {
        try {
            SQLExists sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLExists(superQuery, "", PlanesConstants.CLASS_NAME, PlanesConstants.CLASS_NAME, null, PlanesConstants.CLASS_NAME);
            } else {
                sql = new SQLExists("", PlanesConstants.CLASS_NAME, PlanesConstants.CLASS_NAME, null, PlanesConstants.CLASS_NAME);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Planes.Plan = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(PlanesConstants.CLASS_NAME + "." + PlanesConstants.ATTR_NAME_PLANESPLAN, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1PLANAUXptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator008, Method: assocOperator008Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator010
	@Override
    public Collection<Parentesco> assocOperator010(Long tGSINC1PARENTESCOptpatrCodigo) throws SystemException {
        try {
            // FOR ALL Parentesco WHERE (Parentesco.Codigo = ptpatrCodigo) DO Parentesco.sincronizar(Parentesco, ptpatrDescripcion, ptpatrNombreCorto)
            return assocOperator010Range1(tGSINC1PARENTESCOptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator010, Method: assocOperator010";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Collection<Parentesco> assocOperator010Range1 (Long tGSINC1PARENTESCOptpatrCodigo) throws SystemException {

        SQLSelect sql = assocOperator010Range1Query(tGSINC1PARENTESCOptpatrCodigo);
        return new TypedQueryBuilder<Parentesco>(em, Parentesco.class).build(sql).execute();
    }


    public SQLPopulation assocOperator010Range1Query (Long tGSINC1PARENTESCOptpatrCodigo) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation("", ParentescoConstants.CLASS_NAME);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Parentesco.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(ParentescoConstants.CLASS_NAME + "." + ParentescoConstants.ATTR_NAME_PARENTESCOCODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1PARENTESCOptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator010, Method: assocOperator010Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }
    // AssocOperator: AssocOperator011
	@Override
    public Boolean assocOperator011(Long tGSINC1PARENTESCOptpatrCodigo) throws SystemException {
        try {
            // EXIST (Parentesco) WHERE (Parentesco.Codigo = ptpatrCodigo)
            return assocOperator011Range1(tGSINC1PARENTESCOptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator011, Method: assocOperator011";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Boolean assocOperator011Range1 (Long tGSINC1PARENTESCOptpatrCodigo) throws SystemException {
        SQLExists sql = assocOperator011Range1Query(tGSINC1PARENTESCOptpatrCodigo, null);
        return !new TypedQueryBuilder<Parentesco>(em, Parentesco.class).build(sql).execute().isEmpty();
    }


    public SQLExists assocOperator011Range1Query (Long tGSINC1PARENTESCOptpatrCodigo, SQLSelect superQuery) throws SystemException {
        try {
            SQLExists sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLExists(superQuery, "", ParentescoConstants.CLASS_NAME, ParentescoConstants.CLASS_NAME, null, ParentescoConstants.CLASS_NAME);
            } else {
                sql = new SQLExists("", ParentescoConstants.CLASS_NAME, ParentescoConstants.CLASS_NAME, null, ParentescoConstants.CLASS_NAME);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Parentesco.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(ParentescoConstants.CLASS_NAME + "." + ParentescoConstants.ATTR_NAME_PARENTESCOCODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1PARENTESCOptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator011, Method: assocOperator011Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator013
	@Override
    public Collection<Periodicidad> assocOperator013(Long tGSINC1PERIODICIDADptpatrCodigo) throws SystemException {
        try {
            // FOR ALL Periodicidad WHERE (Periodicidad.Codigo = ptpatrCodigo) DO Periodicidad.sincronizar(Periodicidad, ptpatrDescripcion)
            return assocOperator013Range1(tGSINC1PERIODICIDADptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator013, Method: assocOperator013";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Collection<Periodicidad> assocOperator013Range1 (Long tGSINC1PERIODICIDADptpatrCodigo) throws SystemException {

        SQLSelect sql = assocOperator013Range1Query(tGSINC1PERIODICIDADptpatrCodigo);
        return new TypedQueryBuilder<Periodicidad>(em, Periodicidad.class).build(sql).execute();
    }


    public SQLPopulation assocOperator013Range1Query (Long tGSINC1PERIODICIDADptpatrCodigo) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation("", PeriodicidadConstants.CLASS_NAME);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Periodicidad.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(PeriodicidadConstants.CLASS_NAME + "." + PeriodicidadConstants.ATTR_NAME_PERIODICIDADCODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1PERIODICIDADptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator013, Method: assocOperator013Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }
    // AssocOperator: AssocOperator014
	@Override
    public Boolean assocOperator014(Long tGSINC1PERIODICIDADptpatrCodigo) throws SystemException {
        try {
            // EXIST (Periodicidad) WHERE (Periodicidad.Codigo = ptpatrCodigo)
            return assocOperator014Range1(tGSINC1PERIODICIDADptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator014, Method: assocOperator014";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Boolean assocOperator014Range1 (Long tGSINC1PERIODICIDADptpatrCodigo) throws SystemException {
        SQLExists sql = assocOperator014Range1Query(tGSINC1PERIODICIDADptpatrCodigo, null);
        return !new TypedQueryBuilder<Periodicidad>(em, Periodicidad.class).build(sql).execute().isEmpty();
    }


    public SQLExists assocOperator014Range1Query (Long tGSINC1PERIODICIDADptpatrCodigo, SQLSelect superQuery) throws SystemException {
        try {
            SQLExists sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLExists(superQuery, "", PeriodicidadConstants.CLASS_NAME, PeriodicidadConstants.CLASS_NAME, null, PeriodicidadConstants.CLASS_NAME);
            } else {
                sql = new SQLExists("", PeriodicidadConstants.CLASS_NAME, PeriodicidadConstants.CLASS_NAME, null, PeriodicidadConstants.CLASS_NAME);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Periodicidad.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(PeriodicidadConstants.CLASS_NAME + "." + PeriodicidadConstants.ATTR_NAME_PERIODICIDADCODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1PERIODICIDADptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator014, Method: assocOperator014Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator016
	@Override
    public Collection<EstadoContrato> assocOperator016(Long tGSINC1ESTADOCONTRATOptpatrCodigo) throws SystemException {
        try {
            // FOR ALL EstadoContrato WHERE (EstadoContrato.Codigo = ptpatrCodigo) DO EstadoContrato.sincronizar(EstadoContrato, ptpatrDescripcion)
            return assocOperator016Range1(tGSINC1ESTADOCONTRATOptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator016, Method: assocOperator016";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Collection<EstadoContrato> assocOperator016Range1 (Long tGSINC1ESTADOCONTRATOptpatrCodigo) throws SystemException {

        SQLSelect sql = assocOperator016Range1Query(tGSINC1ESTADOCONTRATOptpatrCodigo);
        return new TypedQueryBuilder<EstadoContrato>(em, EstadoContrato.class).build(sql).execute();
    }


    public SQLPopulation assocOperator016Range1Query (Long tGSINC1ESTADOCONTRATOptpatrCodigo) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation("", EstadoContratoConstants.CLASS_NAME);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // EstadoContrato.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(EstadoContratoConstants.CLASS_NAME + "." + EstadoContratoConstants.ATTR_NAME_ESTADOCONTRATOCODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1ESTADOCONTRATOptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator016, Method: assocOperator016Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }
    // AssocOperator: AssocOperator017
	@Override
    public Boolean assocOperator017(Long tGSINC1ESTADOCONTRATOptpatrCodigo) throws SystemException {
        try {
            // EXIST (EstadoContrato) WHERE (EstadoContrato.Codigo = ptpatrCodigo)
            return assocOperator017Range1(tGSINC1ESTADOCONTRATOptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator017, Method: assocOperator017";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Boolean assocOperator017Range1 (Long tGSINC1ESTADOCONTRATOptpatrCodigo) throws SystemException {
        SQLExists sql = assocOperator017Range1Query(tGSINC1ESTADOCONTRATOptpatrCodigo, null);
        return !new TypedQueryBuilder<EstadoContrato>(em, EstadoContrato.class).build(sql).execute().isEmpty();
    }


    public SQLExists assocOperator017Range1Query (Long tGSINC1ESTADOCONTRATOptpatrCodigo, SQLSelect superQuery) throws SystemException {
        try {
            SQLExists sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLExists(superQuery, "", EstadoContratoConstants.CLASS_NAME, EstadoContratoConstants.CLASS_NAME, null, EstadoContratoConstants.CLASS_NAME);
            } else {
                sql = new SQLExists("", EstadoContratoConstants.CLASS_NAME, EstadoContratoConstants.CLASS_NAME, null, EstadoContratoConstants.CLASS_NAME);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // EstadoContrato.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(EstadoContratoConstants.CLASS_NAME + "." + EstadoContratoConstants.ATTR_NAME_ESTADOCONTRATOCODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1ESTADOCONTRATOptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator017, Method: assocOperator017Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator019
	@Override
    public Collection<EstadoBeneficiario> assocOperator019(Long tGSINC1ESTADOBENEFICIARIOptpatrCodigo) throws SystemException {
        try {
            // FOR ALL EstadoBeneficiario WHERE (EstadoBeneficiario.Codigo = ptpatrCodigo) DO EstadoBeneficiario.sincronizar(EstadoBeneficiario, ptpatrDescripcion)
            return assocOperator019Range1(tGSINC1ESTADOBENEFICIARIOptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator019, Method: assocOperator019";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Collection<EstadoBeneficiario> assocOperator019Range1 (Long tGSINC1ESTADOBENEFICIARIOptpatrCodigo) throws SystemException {

        SQLSelect sql = assocOperator019Range1Query(tGSINC1ESTADOBENEFICIARIOptpatrCodigo);
        return new TypedQueryBuilder<EstadoBeneficiario>(em, EstadoBeneficiario.class).build(sql).execute();
    }


    public SQLPopulation assocOperator019Range1Query (Long tGSINC1ESTADOBENEFICIARIOptpatrCodigo) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation("", EstadoBeneficiarioConstants.CLASS_NAME);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // EstadoBeneficiario.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(EstadoBeneficiarioConstants.CLASS_NAME + "." + EstadoBeneficiarioConstants.ATTR_NAME_ESTADOBENEFICIARIOCODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1ESTADOBENEFICIARIOptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator019, Method: assocOperator019Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }
    // AssocOperator: AssocOperator020
	@Override
    public Boolean assocOperator020(Long tGSINC1ESTADOBENEFICIARIOptpatrCodigo) throws SystemException {
        try {
            // EXIST (EstadoBeneficiario) WHERE (EstadoBeneficiario.Codigo = ptpatrCodigo)
            return assocOperator020Range1(tGSINC1ESTADOBENEFICIARIOptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator020, Method: assocOperator020";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Boolean assocOperator020Range1 (Long tGSINC1ESTADOBENEFICIARIOptpatrCodigo) throws SystemException {
        SQLExists sql = assocOperator020Range1Query(tGSINC1ESTADOBENEFICIARIOptpatrCodigo, null);
        return !new TypedQueryBuilder<EstadoBeneficiario>(em, EstadoBeneficiario.class).build(sql).execute().isEmpty();
    }


    public SQLExists assocOperator020Range1Query (Long tGSINC1ESTADOBENEFICIARIOptpatrCodigo, SQLSelect superQuery) throws SystemException {
        try {
            SQLExists sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLExists(superQuery, "", EstadoBeneficiarioConstants.CLASS_NAME, EstadoBeneficiarioConstants.CLASS_NAME, null, EstadoBeneficiarioConstants.CLASS_NAME);
            } else {
                sql = new SQLExists("", EstadoBeneficiarioConstants.CLASS_NAME, EstadoBeneficiarioConstants.CLASS_NAME, null, EstadoBeneficiarioConstants.CLASS_NAME);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // EstadoBeneficiario.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(EstadoBeneficiarioConstants.CLASS_NAME + "." + EstadoBeneficiarioConstants.ATTR_NAME_ESTADOBENEFICIARIOCODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1ESTADOBENEFICIARIOptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator020, Method: assocOperator020Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator022
	@Override
    public Collection<Regional> assocOperator022(Long tGSINC1REGIONALptpatrCodigo) throws SystemException {
        try {
            // FOR ALL Regional WHERE (Regional.Codigo = ptpatrCodigo) DO Regional.sincronizar(Regional, ptpatrDescripcion)
            return assocOperator022Range1(tGSINC1REGIONALptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator022, Method: assocOperator022";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Collection<Regional> assocOperator022Range1 (Long tGSINC1REGIONALptpatrCodigo) throws SystemException {

        SQLSelect sql = assocOperator022Range1Query(tGSINC1REGIONALptpatrCodigo);
        return new TypedQueryBuilder<Regional>(em, Regional.class).build(sql).execute();
    }


    public SQLPopulation assocOperator022Range1Query (Long tGSINC1REGIONALptpatrCodigo) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation("", RegionalConstants.CLASS_NAME);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Regional.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(RegionalConstants.CLASS_NAME + "." + RegionalConstants.ATTR_NAME_REGIONALCODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1REGIONALptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator022, Method: assocOperator022Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }
    // AssocOperator: AssocOperator023
	@Override
    public Boolean assocOperator023(Long tGSINC1REGIONALptpatrCodigo) throws SystemException {
        try {
            // EXIST (Regional) WHERE (Regional.Codigo = ptpatrCodigo)
            return assocOperator023Range1(tGSINC1REGIONALptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator023, Method: assocOperator023";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Boolean assocOperator023Range1 (Long tGSINC1REGIONALptpatrCodigo) throws SystemException {
        SQLExists sql = assocOperator023Range1Query(tGSINC1REGIONALptpatrCodigo, null);
        return !new TypedQueryBuilder<Regional>(em, Regional.class).build(sql).execute().isEmpty();
    }


    public SQLExists assocOperator023Range1Query (Long tGSINC1REGIONALptpatrCodigo, SQLSelect superQuery) throws SystemException {
        try {
            SQLExists sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLExists(superQuery, "", RegionalConstants.CLASS_NAME, RegionalConstants.CLASS_NAME, null, RegionalConstants.CLASS_NAME);
            } else {
                sql = new SQLExists("", RegionalConstants.CLASS_NAME, RegionalConstants.CLASS_NAME, null, RegionalConstants.CLASS_NAME);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Regional.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(RegionalConstants.CLASS_NAME + "." + RegionalConstants.ATTR_NAME_REGIONALCODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1REGIONALptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator023, Method: assocOperator023Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator025
	@Override
    public Collection<Regional> assocOperator025(Long tGSINC1SUCURSALptpatrCodigo) throws SystemException {
        try {
            // FOR ALL Regional WHERE (Regional.Codigo = ptpatrCodigo) DO TGSINC1SUCURSALAUX(Regional, ptpatrCodigoSucursal, ptpatrDescripcion)
            return assocOperator025Range1(tGSINC1SUCURSALptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator025, Method: assocOperator025";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Collection<Regional> assocOperator025Range1 (Long tGSINC1SUCURSALptpatrCodigo) throws SystemException {

        SQLSelect sql = assocOperator025Range1Query(tGSINC1SUCURSALptpatrCodigo);
        return new TypedQueryBuilder<Regional>(em, Regional.class).build(sql).execute();
    }


    public SQLPopulation assocOperator025Range1Query (Long tGSINC1SUCURSALptpatrCodigo) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation("", RegionalConstants.CLASS_NAME);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Regional.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(RegionalConstants.CLASS_NAME + "." + RegionalConstants.ATTR_NAME_REGIONALCODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1SUCURSALptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator025, Method: assocOperator025Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }
    // AssocOperator: AssocOperator026
	@Override
    public Boolean assocOperator026(Long tGSINC1SUCURSALptpatrCodigo) throws SystemException {
        try {
            // EXIST (Regional) WHERE (Regional.Codigo = ptpatrCodigo)
            return assocOperator026Range1(tGSINC1SUCURSALptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator026, Method: assocOperator026";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Boolean assocOperator026Range1 (Long tGSINC1SUCURSALptpatrCodigo) throws SystemException {
        SQLExists sql = assocOperator026Range1Query(tGSINC1SUCURSALptpatrCodigo, null);
        return !new TypedQueryBuilder<Regional>(em, Regional.class).build(sql).execute().isEmpty();
    }


    public SQLExists assocOperator026Range1Query (Long tGSINC1SUCURSALptpatrCodigo, SQLSelect superQuery) throws SystemException {
        try {
            SQLExists sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLExists(superQuery, "", RegionalConstants.CLASS_NAME, RegionalConstants.CLASS_NAME, null, RegionalConstants.CLASS_NAME);
            } else {
                sql = new SQLExists("", RegionalConstants.CLASS_NAME, RegionalConstants.CLASS_NAME, null, RegionalConstants.CLASS_NAME);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Regional.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(RegionalConstants.CLASS_NAME + "." + RegionalConstants.ATTR_NAME_REGIONALCODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1SUCURSALptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator026, Method: assocOperator026Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator028
	@Override
    public Collection<Sucursal> assocOperator028(Long tGSINC1SUCURSALAUXptpatrCodigo) throws SystemException {
        try {
            // FOR ALL Sucursal WHERE (Sucursal.Codigo = ptpatrCodigo) DO Sucursal.TSINCRONIZAR(Sucursal, ptpatrDescripcion, ptpagrRegional)
            return assocOperator028Range1(tGSINC1SUCURSALAUXptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator028, Method: assocOperator028";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Collection<Sucursal> assocOperator028Range1 (Long tGSINC1SUCURSALAUXptpatrCodigo) throws SystemException {

        SQLSelect sql = assocOperator028Range1Query(tGSINC1SUCURSALAUXptpatrCodigo);
        return new TypedQueryBuilder<Sucursal>(em, Sucursal.class).build(sql).execute();
    }


    public SQLPopulation assocOperator028Range1Query (Long tGSINC1SUCURSALAUXptpatrCodigo) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation("", SucursalConstants.CLASS_NAME);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Sucursal.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(SucursalConstants.CLASS_NAME + "." + SucursalConstants.ATTR_NAME_SUCURSALCODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1SUCURSALAUXptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator028, Method: assocOperator028Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }
    // AssocOperator: AssocOperator029
	@Override
    public Boolean assocOperator029(Long tGSINC1SUCURSALAUXptpatrCodigo) throws SystemException {
        try {
            // EXIST (Sucursal) WHERE (Sucursal.Codigo = ptpatrCodigo)
            return assocOperator029Range1(tGSINC1SUCURSALAUXptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator029, Method: assocOperator029";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Boolean assocOperator029Range1 (Long tGSINC1SUCURSALAUXptpatrCodigo) throws SystemException {
        SQLExists sql = assocOperator029Range1Query(tGSINC1SUCURSALAUXptpatrCodigo, null);
        return !new TypedQueryBuilder<Sucursal>(em, Sucursal.class).build(sql).execute().isEmpty();
    }


    public SQLExists assocOperator029Range1Query (Long tGSINC1SUCURSALAUXptpatrCodigo, SQLSelect superQuery) throws SystemException {
        try {
            SQLExists sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLExists(superQuery, "", SucursalConstants.CLASS_NAME, SucursalConstants.CLASS_NAME, null, SucursalConstants.CLASS_NAME);
            } else {
                sql = new SQLExists("", SucursalConstants.CLASS_NAME, SucursalConstants.CLASS_NAME, null, SucursalConstants.CLASS_NAME);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Sucursal.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(SucursalConstants.CLASS_NAME + "." + SucursalConstants.ATTR_NAME_SUCURSALCODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1SUCURSALAUXptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator029, Method: assocOperator029Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator031
	@Override
    public Collection<GrupoAsociado> assocOperator031(Long tGSINC1GRUPOASOCAUX2ptpatrCodigo) throws SystemException {
        try {
            // FOR ALL GrupoAsociado WHERE (GrupoAsociado.Codigo = ptpatrCodigo) DO GrupoAsociado.TSINCRONIZAR(GrupoAsociado, ptpatrDescripcion, ptpatrFIniVigencia, ptpatrFFinVigencia, ptpatrEstado, ptpagrPlan, ptpagrProducto, ptCodTarifaCORE, ptpatrCodigoLegal)
            return assocOperator031Range1(tGSINC1GRUPOASOCAUX2ptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator031, Method: assocOperator031";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Collection<GrupoAsociado> assocOperator031Range1 (Long tGSINC1GRUPOASOCAUX2ptpatrCodigo) throws SystemException {

        SQLSelect sql = assocOperator031Range1Query(tGSINC1GRUPOASOCAUX2ptpatrCodigo);
        return new TypedQueryBuilder<GrupoAsociado>(em, GrupoAsociado.class).build(sql).execute();
    }


    public SQLPopulation assocOperator031Range1Query (Long tGSINC1GRUPOASOCAUX2ptpatrCodigo) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation("", GrupoAsociadoConstants.CLASS_NAME);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // GrupoAsociado.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(GrupoAsociadoConstants.CLASS_NAME + "." + GrupoAsociadoConstants.ATTR_NAME_GRUPOASOCIADOCODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1GRUPOASOCAUX2ptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator031, Method: assocOperator031Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }
    // AssocOperator: AssocOperator032
	@Override
    public Boolean assocOperator032(Long tGSINC1GRUPOASOCAUX2ptpatrCodigo) throws SystemException {
        try {
            // EXIST (GrupoAsociado) WHERE (GrupoAsociado.Codigo = ptpatrCodigo)
            return assocOperator032Range1(tGSINC1GRUPOASOCAUX2ptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator032, Method: assocOperator032";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Boolean assocOperator032Range1 (Long tGSINC1GRUPOASOCAUX2ptpatrCodigo) throws SystemException {
        SQLExists sql = assocOperator032Range1Query(tGSINC1GRUPOASOCAUX2ptpatrCodigo, null);
        return !new TypedQueryBuilder<GrupoAsociado>(em, GrupoAsociado.class).build(sql).execute().isEmpty();
    }


    public SQLExists assocOperator032Range1Query (Long tGSINC1GRUPOASOCAUX2ptpatrCodigo, SQLSelect superQuery) throws SystemException {
        try {
            SQLExists sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLExists(superQuery, "", GrupoAsociadoConstants.CLASS_NAME, GrupoAsociadoConstants.CLASS_NAME, null, GrupoAsociadoConstants.CLASS_NAME);
            } else {
                sql = new SQLExists("", GrupoAsociadoConstants.CLASS_NAME, GrupoAsociadoConstants.CLASS_NAME, null, GrupoAsociadoConstants.CLASS_NAME);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // GrupoAsociado.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(GrupoAsociadoConstants.CLASS_NAME + "." + GrupoAsociadoConstants.ATTR_NAME_GRUPOASOCIADOCODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1GRUPOASOCAUX2ptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator032, Method: assocOperator032Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator033
	@Override
    public Collection<Planes> assocOperator033(String tGSINC1GRUPOASOCAUXptpatrCodigo, Productos tGSINC1GRUPOASOCAUXptpagrProducto) throws SystemException {
        try {
            // FOR ALL Planes WHERE (Planes.CodigoLegal = ptpatrCodigo AND Planes.ProductoT.CodigoLegal = ptpagrProducto.CodigoLegal) DO TGSINC1GRUPOASOCAUX2(ptpagrProducto, Planes, ptpatrCodigoLegal, ptpatrDescripcion, ptpatrFIniVigencia, ptpatrFFinVigencia, ptpatrEstado, ptCodTarifaCORE, ptpatrCodigoGA)
            return assocOperator033Range1(tGSINC1GRUPOASOCAUXptpatrCodigo, tGSINC1GRUPOASOCAUXptpagrProducto);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator033, Method: assocOperator033";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Collection<Planes> assocOperator033Range1 (String tGSINC1GRUPOASOCAUXptpatrCodigo, Productos tGSINC1GRUPOASOCAUXptpagrProducto) throws SystemException {

        SQLSelect sql = assocOperator033Range1Query(tGSINC1GRUPOASOCAUXptpatrCodigo, tGSINC1GRUPOASOCAUXptpagrProducto);
        return new TypedQueryBuilder<Planes>(em, Planes.class).build(sql).execute();
    }


    public SQLPopulation assocOperator033Range1Query (String tGSINC1GRUPOASOCAUXptpatrCodigo, Productos tGSINC1GRUPOASOCAUXptpagrProducto) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation("", PlanesConstants.CLASS_NAME);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Planes.CodigoLegal = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(PlanesConstants.CLASS_NAME + "." + PlanesConstants.ATTR_NAME_PLANESCODIGOLEGAL, Constants.Type.STRING.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1GRUPOASOCAUXptpatrCodigo, Constants.Type.STRING.getTypeName()));
                    conjunction.addOperand(comparison1);


            // Planes.ProductoT.CodigoLegal = ptpagrProducto.CodigoLegal
                    // '=' Comparison
                    SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
                    comparison2.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand113 = new SQLWhereOperandPathAttribute(PlanesConstants.CLASS_NAME + "." + PlanesConstants.ROLE_NAME_PRODUCTOT + "." + ProductosConstants.ATTR_NAME_PRODUCTOSCODIGOLEGAL, Constants.Type.STRING.getTypeName());
                    comparison2.setFirstOperand(operand113);
                    
                    // Operand 2
                    comparison2.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1GRUPOASOCAUXptpagrProducto.getProductosCodigoLegal(), Constants.Type.STRING.getTypeName()));
                    conjunction.addOperand(comparison2);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator033, Method: assocOperator033Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator034
	@Override
    public Collection<Productos> assocOperator034(String tGSINC1GRUPOASOCptpatrNumero) throws SystemException {
        try {
            // FOR ALL Productos WHERE (Productos.CodigoLegal = ptpatrNumero) DO TGSINC1GRUPOASOCAUX(Productos, ptpatrCodigo, ptpatrCodigoLegal, ptpatrDescripcion, ptpatrFIniVigencia, ptpatrFFinVigencia, ptpatrEstado, ptCodTarifaCORE, ptpatrCodigoGA)
            return assocOperator034Range1(tGSINC1GRUPOASOCptpatrNumero);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator034, Method: assocOperator034";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Collection<Productos> assocOperator034Range1 (String tGSINC1GRUPOASOCptpatrNumero) throws SystemException {

        SQLSelect sql = assocOperator034Range1Query(tGSINC1GRUPOASOCptpatrNumero);
        return new TypedQueryBuilder<Productos>(em, Productos.class).build(sql).execute();
    }


    public SQLPopulation assocOperator034Range1Query (String tGSINC1GRUPOASOCptpatrNumero) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation("", ProductosConstants.CLASS_NAME);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Productos.CodigoLegal = ptpatrNumero
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(ProductosConstants.CLASS_NAME + "." + ProductosConstants.ATTR_NAME_PRODUCTOSCODIGOLEGAL, Constants.Type.STRING.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1GRUPOASOCptpatrNumero, Constants.Type.STRING.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator034, Method: assocOperator034Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator036
	@Override
    public Collection<EstadoCivil> assocOperator036(Long tGSINC1ESTADOCIVILptpatrCodigo) throws SystemException {
        try {
            // FOR ALL EstadoCivil WHERE (EstadoCivil.Codigo = ptpatrCodigo) DO EstadoCivil.sincronizar(EstadoCivil, ptpatrDescripcion, ptpatrNombreCorto)
            return assocOperator036Range1(tGSINC1ESTADOCIVILptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator036, Method: assocOperator036";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Collection<EstadoCivil> assocOperator036Range1 (Long tGSINC1ESTADOCIVILptpatrCodigo) throws SystemException {

        SQLSelect sql = assocOperator036Range1Query(tGSINC1ESTADOCIVILptpatrCodigo);
        return new TypedQueryBuilder<EstadoCivil>(em, EstadoCivil.class).build(sql).execute();
    }


    public SQLPopulation assocOperator036Range1Query (Long tGSINC1ESTADOCIVILptpatrCodigo) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation("", EstadoCivilConstants.CLASS_NAME);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // EstadoCivil.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(EstadoCivilConstants.CLASS_NAME + "." + EstadoCivilConstants.ATTR_NAME_ESTADOCIVILCODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1ESTADOCIVILptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator036, Method: assocOperator036Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }
    // AssocOperator: AssocOperator037
	@Override
    public Boolean assocOperator037(Long tGSINC1ESTADOCIVILptpatrCodigo) throws SystemException {
        try {
            // EXIST (EstadoCivil) WHERE (EstadoCivil.Codigo = ptpatrCodigo)
            return assocOperator037Range1(tGSINC1ESTADOCIVILptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator037, Method: assocOperator037";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Boolean assocOperator037Range1 (Long tGSINC1ESTADOCIVILptpatrCodigo) throws SystemException {
        SQLExists sql = assocOperator037Range1Query(tGSINC1ESTADOCIVILptpatrCodigo, null);
        return !new TypedQueryBuilder<EstadoCivil>(em, EstadoCivil.class).build(sql).execute().isEmpty();
    }


    public SQLExists assocOperator037Range1Query (Long tGSINC1ESTADOCIVILptpatrCodigo, SQLSelect superQuery) throws SystemException {
        try {
            SQLExists sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLExists(superQuery, "", EstadoCivilConstants.CLASS_NAME, EstadoCivilConstants.CLASS_NAME, null, EstadoCivilConstants.CLASS_NAME);
            } else {
                sql = new SQLExists("", EstadoCivilConstants.CLASS_NAME, EstadoCivilConstants.CLASS_NAME, null, EstadoCivilConstants.CLASS_NAME);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // EstadoCivil.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(EstadoCivilConstants.CLASS_NAME + "." + EstadoCivilConstants.ATTR_NAME_ESTADOCIVILCODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1ESTADOCIVILptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator037, Method: assocOperator037Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator039
	@Override
    public Collection<Departamento> assocOperator039(Long tGSINC1DEPARTAMENTOptpatrCodigo) throws SystemException {
        try {
            // FOR ALL Departamento WHERE (Departamento.Codigo = ptpatrCodigo) DO Departamento.sincronizar(Departamento, ptpatrDescripcion)
            return assocOperator039Range1(tGSINC1DEPARTAMENTOptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator039, Method: assocOperator039";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Collection<Departamento> assocOperator039Range1 (Long tGSINC1DEPARTAMENTOptpatrCodigo) throws SystemException {

        SQLSelect sql = assocOperator039Range1Query(tGSINC1DEPARTAMENTOptpatrCodigo);
        return new TypedQueryBuilder<Departamento>(em, Departamento.class).build(sql).execute();
    }


    public SQLPopulation assocOperator039Range1Query (Long tGSINC1DEPARTAMENTOptpatrCodigo) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation("", DepartamentoConstants.CLASS_NAME);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Departamento.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(DepartamentoConstants.CLASS_NAME + "." + DepartamentoConstants.ATTR_NAME_DEPARTAMENTOCODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1DEPARTAMENTOptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator039, Method: assocOperator039Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }
    // AssocOperator: AssocOperator040
	@Override
    public Boolean assocOperator040(Long tGSINC1DEPARTAMENTOptpatrCodigo) throws SystemException {
        try {
            // EXIST (Departamento) WHERE (Departamento.Codigo = ptpatrCodigo)
            return assocOperator040Range1(tGSINC1DEPARTAMENTOptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator040, Method: assocOperator040";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Boolean assocOperator040Range1 (Long tGSINC1DEPARTAMENTOptpatrCodigo) throws SystemException {
        SQLExists sql = assocOperator040Range1Query(tGSINC1DEPARTAMENTOptpatrCodigo, null);
        return !new TypedQueryBuilder<Departamento>(em, Departamento.class).build(sql).execute().isEmpty();
    }


    public SQLExists assocOperator040Range1Query (Long tGSINC1DEPARTAMENTOptpatrCodigo, SQLSelect superQuery) throws SystemException {
        try {
            SQLExists sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLExists(superQuery, "", DepartamentoConstants.CLASS_NAME, DepartamentoConstants.CLASS_NAME, null, DepartamentoConstants.CLASS_NAME);
            } else {
                sql = new SQLExists("", DepartamentoConstants.CLASS_NAME, DepartamentoConstants.CLASS_NAME, null, DepartamentoConstants.CLASS_NAME);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Departamento.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(DepartamentoConstants.CLASS_NAME + "." + DepartamentoConstants.ATTR_NAME_DEPARTAMENTOCODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1DEPARTAMENTOptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator040, Method: assocOperator040Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator047
	@Override
    public Collection<Companyia> assocOperator047(Long tGSINC1COMPANIAptpatrCodigo) throws SystemException {
        try {
            // FOR ALL Companyia WHERE (Companyia.Codigo = ptpatrCodigo) DO Companyia.sincronizar(Companyia, ptpatrNombre)
            return assocOperator047Range1(tGSINC1COMPANIAptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator047, Method: assocOperator047";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Collection<Companyia> assocOperator047Range1 (Long tGSINC1COMPANIAptpatrCodigo) throws SystemException {

        SQLSelect sql = assocOperator047Range1Query(tGSINC1COMPANIAptpatrCodigo);
        return new TypedQueryBuilder<Companyia>(em, Companyia.class).build(sql).execute();
    }


    public SQLPopulation assocOperator047Range1Query (Long tGSINC1COMPANIAptpatrCodigo) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation("", CompanyiaConstants.CLASS_NAME);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Companyia.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(CompanyiaConstants.CLASS_NAME + "." + CompanyiaConstants.ATTR_NAME_COMPANYIACODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1COMPANIAptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator047, Method: assocOperator047Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }
    // AssocOperator: AssocOperator048
	@Override
    public Boolean assocOperator048(Long tGSINC1COMPANIAptpatrCodigo) throws SystemException {
        try {
            // EXIST (Companyia) WHERE (Companyia.Codigo = ptpatrCodigo)
            return assocOperator048Range1(tGSINC1COMPANIAptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator048, Method: assocOperator048";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Boolean assocOperator048Range1 (Long tGSINC1COMPANIAptpatrCodigo) throws SystemException {
        SQLExists sql = assocOperator048Range1Query(tGSINC1COMPANIAptpatrCodigo, null);
        return !new TypedQueryBuilder<Companyia>(em, Companyia.class).build(sql).execute().isEmpty();
    }


    public SQLExists assocOperator048Range1Query (Long tGSINC1COMPANIAptpatrCodigo, SQLSelect superQuery) throws SystemException {
        try {
            SQLExists sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLExists(superQuery, "", CompanyiaConstants.CLASS_NAME, CompanyiaConstants.CLASS_NAME, null, CompanyiaConstants.CLASS_NAME);
            } else {
                sql = new SQLExists("", CompanyiaConstants.CLASS_NAME, CompanyiaConstants.CLASS_NAME, null, CompanyiaConstants.CLASS_NAME);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Companyia.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(CompanyiaConstants.CLASS_NAME + "." + CompanyiaConstants.ATTR_NAME_COMPANYIACODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1COMPANIAptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator048, Method: assocOperator048Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator050
	@Override
    public Collection<Municipio> assocOperator050(Long tGSINC1MUNICIPIOptpatrCodigo) throws SystemException {
        try {
            // FOR ALL Municipio WHERE (Municipio.Codigo = ptpatrCodigo) DO Municipio.tsincronizar(Municipio, ptpatrDescripcion, ptpatrCodigoLegal, ptpatrEsValidoEPS)
            return assocOperator050Range1(tGSINC1MUNICIPIOptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator050, Method: assocOperator050";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Collection<Municipio> assocOperator050Range1 (Long tGSINC1MUNICIPIOptpatrCodigo) throws SystemException {

        SQLSelect sql = assocOperator050Range1Query(tGSINC1MUNICIPIOptpatrCodigo);
        return new TypedQueryBuilder<Municipio>(em, Municipio.class).build(sql).execute();
    }


    public SQLPopulation assocOperator050Range1Query (Long tGSINC1MUNICIPIOptpatrCodigo) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation("", MunicipioConstants.CLASS_NAME);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Municipio.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(MunicipioConstants.CLASS_NAME + "." + MunicipioConstants.ATTR_NAME_MUNICIPIOCODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1MUNICIPIOptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator050, Method: assocOperator050Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }
    // AssocOperator: AssocOperator051
	@Override
    public Boolean assocOperator051(Long tGSINC1MUNICIPIOptpatrCodigo) throws SystemException {
        try {
            // EXIST (Municipio) WHERE (Municipio.Codigo = ptpatrCodigo)
            return assocOperator051Range1(tGSINC1MUNICIPIOptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator051, Method: assocOperator051";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Boolean assocOperator051Range1 (Long tGSINC1MUNICIPIOptpatrCodigo) throws SystemException {
        SQLExists sql = assocOperator051Range1Query(tGSINC1MUNICIPIOptpatrCodigo, null);
        return !new TypedQueryBuilder<Municipio>(em, Municipio.class).build(sql).execute().isEmpty();
    }


    public SQLExists assocOperator051Range1Query (Long tGSINC1MUNICIPIOptpatrCodigo, SQLSelect superQuery) throws SystemException {
        try {
            SQLExists sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLExists(superQuery, "", MunicipioConstants.CLASS_NAME, MunicipioConstants.CLASS_NAME, null, MunicipioConstants.CLASS_NAME);
            } else {
                sql = new SQLExists("", MunicipioConstants.CLASS_NAME, MunicipioConstants.CLASS_NAME, null, MunicipioConstants.CLASS_NAME);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Municipio.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(MunicipioConstants.CLASS_NAME + "." + MunicipioConstants.ATTR_NAME_MUNICIPIOCODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1MUNICIPIOptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator051, Method: assocOperator051Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator053
	@Override
    public Collection<TipoDireccion> assocOperator053(Long tGSINC1TIPODIRECCIONptpatrCodigo) throws SystemException {
        try {
            // FOR ALL TipoDireccion WHERE (TipoDireccion.Codigo = ptpatrCodigo) DO TipoDireccion.sincronizar(TipoDireccion, ptpatrDescripcion)
            return assocOperator053Range1(tGSINC1TIPODIRECCIONptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator053, Method: assocOperator053";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Collection<TipoDireccion> assocOperator053Range1 (Long tGSINC1TIPODIRECCIONptpatrCodigo) throws SystemException {

        SQLSelect sql = assocOperator053Range1Query(tGSINC1TIPODIRECCIONptpatrCodigo);
        return new TypedQueryBuilder<TipoDireccion>(em, TipoDireccion.class).build(sql).execute();
    }


    public SQLPopulation assocOperator053Range1Query (Long tGSINC1TIPODIRECCIONptpatrCodigo) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation("", TipoDireccionConstants.CLASS_NAME);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // TipoDireccion.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(TipoDireccionConstants.CLASS_NAME + "." + TipoDireccionConstants.ATTR_NAME_TIPODIRECCIONCODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1TIPODIRECCIONptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator053, Method: assocOperator053Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }
    // AssocOperator: AssocOperator054
	@Override
    public Boolean assocOperator054(Long tGSINC1TIPODIRECCIONptpatrCodigo) throws SystemException {
        try {
            // EXIST (TipoDireccion) WHERE (TipoDireccion.Codigo = ptpatrCodigo)
            return assocOperator054Range1(tGSINC1TIPODIRECCIONptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator054, Method: assocOperator054";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Boolean assocOperator054Range1 (Long tGSINC1TIPODIRECCIONptpatrCodigo) throws SystemException {
        SQLExists sql = assocOperator054Range1Query(tGSINC1TIPODIRECCIONptpatrCodigo, null);
        return !new TypedQueryBuilder<TipoDireccion>(em, TipoDireccion.class).build(sql).execute().isEmpty();
    }


    public SQLExists assocOperator054Range1Query (Long tGSINC1TIPODIRECCIONptpatrCodigo, SQLSelect superQuery) throws SystemException {
        try {
            SQLExists sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLExists(superQuery, "", TipoDireccionConstants.CLASS_NAME, TipoDireccionConstants.CLASS_NAME, null, TipoDireccionConstants.CLASS_NAME);
            } else {
                sql = new SQLExists("", TipoDireccionConstants.CLASS_NAME, TipoDireccionConstants.CLASS_NAME, null, TipoDireccionConstants.CLASS_NAME);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // TipoDireccion.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(TipoDireccionConstants.CLASS_NAME + "." + TipoDireccionConstants.ATTR_NAME_TIPODIRECCIONCODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1TIPODIRECCIONptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator054, Method: assocOperator054Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator056
	@Override
    public Collection<EstadoPersona> assocOperator056(Long tGSINC1ESTADOPERSONAptpatrCodigo) throws SystemException {
        try {
            // FOR ALL EstadoPersona WHERE (EstadoPersona.Codigo = ptpatrCodigo) DO EstadoPersona.sincronizar(EstadoPersona, ptpatrDescripcion)
            return assocOperator056Range1(tGSINC1ESTADOPERSONAptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator056, Method: assocOperator056";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Collection<EstadoPersona> assocOperator056Range1 (Long tGSINC1ESTADOPERSONAptpatrCodigo) throws SystemException {

        SQLSelect sql = assocOperator056Range1Query(tGSINC1ESTADOPERSONAptpatrCodigo);
        return new TypedQueryBuilder<EstadoPersona>(em, EstadoPersona.class).build(sql).execute();
    }


    public SQLPopulation assocOperator056Range1Query (Long tGSINC1ESTADOPERSONAptpatrCodigo) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation("", EstadoPersonaConstants.CLASS_NAME);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // EstadoPersona.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(EstadoPersonaConstants.CLASS_NAME + "." + EstadoPersonaConstants.ATTR_NAME_ESTADOPERSONACODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1ESTADOPERSONAptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator056, Method: assocOperator056Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }
    // AssocOperator: AssocOperator057
	@Override
    public Boolean assocOperator057(Long tGSINC1ESTADOPERSONAptpatrCodigo) throws SystemException {
        try {
            // EXIST (EstadoPersona) WHERE (EstadoPersona.Codigo = ptpatrCodigo)
            return assocOperator057Range1(tGSINC1ESTADOPERSONAptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator057, Method: assocOperator057";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Boolean assocOperator057Range1 (Long tGSINC1ESTADOPERSONAptpatrCodigo) throws SystemException {
        SQLExists sql = assocOperator057Range1Query(tGSINC1ESTADOPERSONAptpatrCodigo, null);
        return !new TypedQueryBuilder<EstadoPersona>(em, EstadoPersona.class).build(sql).execute().isEmpty();
    }


    public SQLExists assocOperator057Range1Query (Long tGSINC1ESTADOPERSONAptpatrCodigo, SQLSelect superQuery) throws SystemException {
        try {
            SQLExists sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLExists(superQuery, "", EstadoPersonaConstants.CLASS_NAME, EstadoPersonaConstants.CLASS_NAME, null, EstadoPersonaConstants.CLASS_NAME);
            } else {
                sql = new SQLExists("", EstadoPersonaConstants.CLASS_NAME, EstadoPersonaConstants.CLASS_NAME, null, EstadoPersonaConstants.CLASS_NAME);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // EstadoPersona.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(EstadoPersonaConstants.CLASS_NAME + "." + EstadoPersonaConstants.ATTR_NAME_ESTADOPERSONACODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1ESTADOPERSONAptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator057, Method: assocOperator057Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator059
	@Override
    public Collection<FormaPago> assocOperator059(Long tGSINC1FORMAPAGOptpatrCodigo) throws SystemException {
        try {
            // FOR ALL FormaPago WHERE (FormaPago.Codigo = ptpatrCodigo) DO FormaPago.sincronizar(FormaPago, ptpatrDescripcion)
            return assocOperator059Range1(tGSINC1FORMAPAGOptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator059, Method: assocOperator059";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Collection<FormaPago> assocOperator059Range1 (Long tGSINC1FORMAPAGOptpatrCodigo) throws SystemException {

        SQLSelect sql = assocOperator059Range1Query(tGSINC1FORMAPAGOptpatrCodigo);
        return new TypedQueryBuilder<FormaPago>(em, FormaPago.class).build(sql).execute();
    }


    public SQLPopulation assocOperator059Range1Query (Long tGSINC1FORMAPAGOptpatrCodigo) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation("", FormaPagoConstants.CLASS_NAME);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // FormaPago.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(FormaPagoConstants.CLASS_NAME + "." + FormaPagoConstants.ATTR_NAME_FORMAPAGOCODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1FORMAPAGOptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator059, Method: assocOperator059Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }
    // AssocOperator: AssocOperator060
	@Override
    public Boolean assocOperator060(Long tGSINC1FORMAPAGOptpatrCodigo) throws SystemException {
        try {
            // EXIST (FormaPago) WHERE (FormaPago.Codigo = ptpatrCodigo)
            return assocOperator060Range1(tGSINC1FORMAPAGOptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator060, Method: assocOperator060";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Boolean assocOperator060Range1 (Long tGSINC1FORMAPAGOptpatrCodigo) throws SystemException {
        SQLExists sql = assocOperator060Range1Query(tGSINC1FORMAPAGOptpatrCodigo, null);
        return !new TypedQueryBuilder<FormaPago>(em, FormaPago.class).build(sql).execute().isEmpty();
    }


    public SQLExists assocOperator060Range1Query (Long tGSINC1FORMAPAGOptpatrCodigo, SQLSelect superQuery) throws SystemException {
        try {
            SQLExists sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLExists(superQuery, "", FormaPagoConstants.CLASS_NAME, FormaPagoConstants.CLASS_NAME, null, FormaPagoConstants.CLASS_NAME);
            } else {
                sql = new SQLExists("", FormaPagoConstants.CLASS_NAME, FormaPagoConstants.CLASS_NAME, null, FormaPagoConstants.CLASS_NAME);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // FormaPago.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(FormaPagoConstants.CLASS_NAME + "." + FormaPagoConstants.ATTR_NAME_FORMAPAGOCODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1FORMAPAGOptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator060, Method: assocOperator060Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator062
	@Override
    public Collection<TipoIdentificacion> assocOperator062(Long tGSINC1TIPOIDENTIFICACIONptpatrCodigo) throws SystemException {
        try {
            // FOR ALL TipoIdentificacion WHERE (TipoIdentificacion.Codigo = ptpatrCodigo) DO TipoIdentificacion.sincronizar(TipoIdentificacion, ptpatrDescripcion, ptpatrNombreCorto)
            return assocOperator062Range1(tGSINC1TIPOIDENTIFICACIONptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator062, Method: assocOperator062";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Collection<TipoIdentificacion> assocOperator062Range1 (Long tGSINC1TIPOIDENTIFICACIONptpatrCodigo) throws SystemException {

        SQLSelect sql = assocOperator062Range1Query(tGSINC1TIPOIDENTIFICACIONptpatrCodigo);
        return new TypedQueryBuilder<TipoIdentificacion>(em, TipoIdentificacion.class).build(sql).execute();
    }


    public SQLPopulation assocOperator062Range1Query (Long tGSINC1TIPOIDENTIFICACIONptpatrCodigo) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation("", TipoIdentificacionConstants.CLASS_NAME);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // TipoIdentificacion.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(TipoIdentificacionConstants.CLASS_NAME + "." + TipoIdentificacionConstants.ATTR_NAME_TIPOIDENTIFICACIONCODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1TIPOIDENTIFICACIONptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator062, Method: assocOperator062Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }
    // AssocOperator: AssocOperator063
	@Override
    public Boolean assocOperator063(Long tGSINC1TIPOIDENTIFICACIONptpatrCodigo) throws SystemException {
        try {
            // EXIST (TipoIdentificacion) WHERE (TipoIdentificacion.Codigo = ptpatrCodigo)
            return assocOperator063Range1(tGSINC1TIPOIDENTIFICACIONptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator063, Method: assocOperator063";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Boolean assocOperator063Range1 (Long tGSINC1TIPOIDENTIFICACIONptpatrCodigo) throws SystemException {
        SQLExists sql = assocOperator063Range1Query(tGSINC1TIPOIDENTIFICACIONptpatrCodigo, null);
        return !new TypedQueryBuilder<TipoIdentificacion>(em, TipoIdentificacion.class).build(sql).execute().isEmpty();
    }


    public SQLExists assocOperator063Range1Query (Long tGSINC1TIPOIDENTIFICACIONptpatrCodigo, SQLSelect superQuery) throws SystemException {
        try {
            SQLExists sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLExists(superQuery, "", TipoIdentificacionConstants.CLASS_NAME, TipoIdentificacionConstants.CLASS_NAME, null, TipoIdentificacionConstants.CLASS_NAME);
            } else {
                sql = new SQLExists("", TipoIdentificacionConstants.CLASS_NAME, TipoIdentificacionConstants.CLASS_NAME, null, TipoIdentificacionConstants.CLASS_NAME);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // TipoIdentificacion.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(TipoIdentificacionConstants.CLASS_NAME + "." + TipoIdentificacionConstants.ATTR_NAME_TIPOIDENTIFICACIONCODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1TIPOIDENTIFICACIONptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator063, Method: assocOperator063Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator065
	@Override
    public Collection<Eps> assocOperator065(Long tGSINC1EPSptpatrCodigo) throws SystemException {
        try {
            // FOR ALL Eps WHERE (Eps.Codigo = ptpatrCodigo) DO Eps.sincronizar(Eps, ptpatrDescripcion)
            return assocOperator065Range1(tGSINC1EPSptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator065, Method: assocOperator065";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Collection<Eps> assocOperator065Range1 (Long tGSINC1EPSptpatrCodigo) throws SystemException {

        SQLSelect sql = assocOperator065Range1Query(tGSINC1EPSptpatrCodigo);
        return new TypedQueryBuilder<Eps>(em, Eps.class).build(sql).execute();
    }


    public SQLPopulation assocOperator065Range1Query (Long tGSINC1EPSptpatrCodigo) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation("", EpsConstants.CLASS_NAME);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Eps.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(EpsConstants.CLASS_NAME + "." + EpsConstants.ATTR_NAME_EPSCODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1EPSptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator065, Method: assocOperator065Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }
    // AssocOperator: AssocOperator066
	@Override
    public Boolean assocOperator066(Long tGSINC1EPSptpatrCodigo) throws SystemException {
        try {
            // EXIST (Eps) WHERE (Eps.Codigo = ptpatrCodigo)
            return assocOperator066Range1(tGSINC1EPSptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator066, Method: assocOperator066";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Boolean assocOperator066Range1 (Long tGSINC1EPSptpatrCodigo) throws SystemException {
        SQLExists sql = assocOperator066Range1Query(tGSINC1EPSptpatrCodigo, null);
        return !new TypedQueryBuilder<Eps>(em, Eps.class).build(sql).execute().isEmpty();
    }


    public SQLExists assocOperator066Range1Query (Long tGSINC1EPSptpatrCodigo, SQLSelect superQuery) throws SystemException {
        try {
            SQLExists sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLExists(superQuery, "", EpsConstants.CLASS_NAME, EpsConstants.CLASS_NAME, null, EpsConstants.CLASS_NAME);
            } else {
                sql = new SQLExists("", EpsConstants.CLASS_NAME, EpsConstants.CLASS_NAME, null, EpsConstants.CLASS_NAME);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Eps.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(EpsConstants.CLASS_NAME + "." + EpsConstants.ATTR_NAME_EPSCODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1EPSptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator066, Method: assocOperator066Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator068
	@Override
    public Collection<Pais> assocOperator068(String tGSINC1PAISptpatrCodigo) throws SystemException {
        try {
            // FOR ALL Pais WHERE (Pais.Codigo = ptpatrCodigo) DO Pais.sincronizar(Pais, ptpatrDescripcion)
            return assocOperator068Range1(tGSINC1PAISptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator068, Method: assocOperator068";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Collection<Pais> assocOperator068Range1 (String tGSINC1PAISptpatrCodigo) throws SystemException {

        SQLSelect sql = assocOperator068Range1Query(tGSINC1PAISptpatrCodigo);
        return new TypedQueryBuilder<Pais>(em, Pais.class).build(sql).execute();
    }


    public SQLPopulation assocOperator068Range1Query (String tGSINC1PAISptpatrCodigo) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation("", PaisConstants.CLASS_NAME);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Pais.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(PaisConstants.CLASS_NAME + "." + PaisConstants.ATTR_NAME_PAISCODIGO, Constants.Type.STRING.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1PAISptpatrCodigo, Constants.Type.STRING.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator068, Method: assocOperator068Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }
    // AssocOperator: AssocOperator069
	@Override
    public Boolean assocOperator069(String tGSINC1PAISptpatrCodigo) throws SystemException {
        try {
            // EXIST (Pais) WHERE (Pais.Codigo = ptpatrCodigo)
            return assocOperator069Range1(tGSINC1PAISptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator069, Method: assocOperator069";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Boolean assocOperator069Range1 (String tGSINC1PAISptpatrCodigo) throws SystemException {
        SQLExists sql = assocOperator069Range1Query(tGSINC1PAISptpatrCodigo, null);
        return !new TypedQueryBuilder<Pais>(em, Pais.class).build(sql).execute().isEmpty();
    }


    public SQLExists assocOperator069Range1Query (String tGSINC1PAISptpatrCodigo, SQLSelect superQuery) throws SystemException {
        try {
            SQLExists sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLExists(superQuery, "", PaisConstants.CLASS_NAME, PaisConstants.CLASS_NAME, null, PaisConstants.CLASS_NAME);
            } else {
                sql = new SQLExists("", PaisConstants.CLASS_NAME, PaisConstants.CLASS_NAME, null, PaisConstants.CLASS_NAME);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Pais.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(PaisConstants.CLASS_NAME + "." + PaisConstants.ATTR_NAME_PAISCODIGO, Constants.Type.STRING.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1PAISptpatrCodigo, Constants.Type.STRING.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator069, Method: assocOperator069Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator079
	@Override
    public Collection<EntidadFinancieraC> assocOperator079(Long tGSINC1ENTIDADFINptpatrCodigo) throws SystemException {
        try {
            // FOR ALL EntidadFinancieraC WHERE (EntidadFinancieraC.Codigo = ptpatrCodigo) DO EntidadFinancieraC.modificar(EntidadFinancieraC, ptpatrDescripcion)
            return assocOperator079Range1(tGSINC1ENTIDADFINptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator079, Method: assocOperator079";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Collection<EntidadFinancieraC> assocOperator079Range1 (Long tGSINC1ENTIDADFINptpatrCodigo) throws SystemException {

        SQLSelect sql = assocOperator079Range1Query(tGSINC1ENTIDADFINptpatrCodigo);
        return new TypedQueryBuilder<EntidadFinancieraC>(em, EntidadFinancieraC.class).build(sql).execute();
    }


    public SQLPopulation assocOperator079Range1Query (Long tGSINC1ENTIDADFINptpatrCodigo) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation("", EntidadFinancieraCConstants.CLASS_NAME);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // EntidadFinancieraC.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(EntidadFinancieraCConstants.CLASS_NAME + "." + EntidadFinancieraCConstants.ATTR_NAME_ENTIDADFINANCIERACCODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1ENTIDADFINptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator079, Method: assocOperator079Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }
    // AssocOperator: AssocOperator080
	@Override
    public Boolean assocOperator080(Long tGSINC1ENTIDADFINptpatrCodigo) throws SystemException {
        try {
            // EXIST (EntidadFinancieraC) WHERE (EntidadFinancieraC.Codigo = ptpatrCodigo)
            return assocOperator080Range1(tGSINC1ENTIDADFINptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator080, Method: assocOperator080";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Boolean assocOperator080Range1 (Long tGSINC1ENTIDADFINptpatrCodigo) throws SystemException {
        SQLExists sql = assocOperator080Range1Query(tGSINC1ENTIDADFINptpatrCodigo, null);
        return !new TypedQueryBuilder<EntidadFinancieraC>(em, EntidadFinancieraC.class).build(sql).execute().isEmpty();
    }


    public SQLExists assocOperator080Range1Query (Long tGSINC1ENTIDADFINptpatrCodigo, SQLSelect superQuery) throws SystemException {
        try {
            SQLExists sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLExists(superQuery, "", EntidadFinancieraCConstants.CLASS_NAME, EntidadFinancieraCConstants.CLASS_NAME, null, EntidadFinancieraCConstants.CLASS_NAME);
            } else {
                sql = new SQLExists("", EntidadFinancieraCConstants.CLASS_NAME, EntidadFinancieraCConstants.CLASS_NAME, null, EntidadFinancieraCConstants.CLASS_NAME);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // EntidadFinancieraC.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(EntidadFinancieraCConstants.CLASS_NAME + "." + EntidadFinancieraCConstants.ATTR_NAME_ENTIDADFINANCIERACCODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1ENTIDADFINptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator080, Method: assocOperator080Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator082
	@Override
    public Collection<TipoEntidadC> assocOperator082(Long tGSINC1TIPOENTIDADptpatrCodigo) throws SystemException {
        try {
            // FOR ALL TipoEntidadC WHERE (TipoEntidadC.Codigo = ptpatrCodigo) DO TipoEntidadC.modificar(TipoEntidadC, ptpatrDescripcion)
            return assocOperator082Range1(tGSINC1TIPOENTIDADptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator082, Method: assocOperator082";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Collection<TipoEntidadC> assocOperator082Range1 (Long tGSINC1TIPOENTIDADptpatrCodigo) throws SystemException {

        SQLSelect sql = assocOperator082Range1Query(tGSINC1TIPOENTIDADptpatrCodigo);
        return new TypedQueryBuilder<TipoEntidadC>(em, TipoEntidadC.class).build(sql).execute();
    }


    public SQLPopulation assocOperator082Range1Query (Long tGSINC1TIPOENTIDADptpatrCodigo) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation("", TipoEntidadCConstants.CLASS_NAME);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // TipoEntidadC.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(TipoEntidadCConstants.CLASS_NAME + "." + TipoEntidadCConstants.ATTR_NAME_TIPOENTIDADCCODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1TIPOENTIDADptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator082, Method: assocOperator082Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }
    // AssocOperator: AssocOperator083
	@Override
    public Boolean assocOperator083(Long tGSINC1TIPOENTIDADptpatrCodigo) throws SystemException {
        try {
            // EXIST (TipoEntidadC) WHERE (TipoEntidadC.Codigo = ptpatrCodigo)
            return assocOperator083Range1(tGSINC1TIPOENTIDADptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator083, Method: assocOperator083";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Boolean assocOperator083Range1 (Long tGSINC1TIPOENTIDADptpatrCodigo) throws SystemException {
        SQLExists sql = assocOperator083Range1Query(tGSINC1TIPOENTIDADptpatrCodigo, null);
        return !new TypedQueryBuilder<TipoEntidadC>(em, TipoEntidadC.class).build(sql).execute().isEmpty();
    }


    public SQLExists assocOperator083Range1Query (Long tGSINC1TIPOENTIDADptpatrCodigo, SQLSelect superQuery) throws SystemException {
        try {
            SQLExists sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLExists(superQuery, "", TipoEntidadCConstants.CLASS_NAME, TipoEntidadCConstants.CLASS_NAME, null, TipoEntidadCConstants.CLASS_NAME);
            } else {
                sql = new SQLExists("", TipoEntidadCConstants.CLASS_NAME, TipoEntidadCConstants.CLASS_NAME, null, TipoEntidadCConstants.CLASS_NAME);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // TipoEntidadC.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(TipoEntidadCConstants.CLASS_NAME + "." + TipoEntidadCConstants.ATTR_NAME_TIPOENTIDADCCODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1TIPOENTIDADptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator083, Method: assocOperator083Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator085
	@Override
    public Collection<TipoInstitucionC> assocOperator085(Long tGSINC1TIPOINSTITUCIONptpatrCodigo) throws SystemException {
        try {
            // FOR ALL TipoInstitucionC WHERE (TipoInstitucionC.Codigo = ptpatrCodigo) DO TipoInstitucionC.modificar(TipoInstitucionC, ptpatrNombre)
            return assocOperator085Range1(tGSINC1TIPOINSTITUCIONptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator085, Method: assocOperator085";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Collection<TipoInstitucionC> assocOperator085Range1 (Long tGSINC1TIPOINSTITUCIONptpatrCodigo) throws SystemException {

        SQLSelect sql = assocOperator085Range1Query(tGSINC1TIPOINSTITUCIONptpatrCodigo);
        return new TypedQueryBuilder<TipoInstitucionC>(em, TipoInstitucionC.class).build(sql).execute();
    }


    public SQLPopulation assocOperator085Range1Query (Long tGSINC1TIPOINSTITUCIONptpatrCodigo) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation("", TipoInstitucionCConstants.CLASS_NAME);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // TipoInstitucionC.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(TipoInstitucionCConstants.CLASS_NAME + "." + TipoInstitucionCConstants.ATTR_NAME_TIPOINSTITUCIONCCODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1TIPOINSTITUCIONptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator085, Method: assocOperator085Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }
    // AssocOperator: AssocOperator086
	@Override
    public Boolean assocOperator086(Long tGSINC1TIPOINSTITUCIONptpatrCodigo) throws SystemException {
        try {
            // EXIST (TipoInstitucionC) WHERE (TipoInstitucionC.Codigo = ptpatrCodigo)
            return assocOperator086Range1(tGSINC1TIPOINSTITUCIONptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator086, Method: assocOperator086";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Boolean assocOperator086Range1 (Long tGSINC1TIPOINSTITUCIONptpatrCodigo) throws SystemException {
        SQLExists sql = assocOperator086Range1Query(tGSINC1TIPOINSTITUCIONptpatrCodigo, null);
        return !new TypedQueryBuilder<TipoInstitucionC>(em, TipoInstitucionC.class).build(sql).execute().isEmpty();
    }


    public SQLExists assocOperator086Range1Query (Long tGSINC1TIPOINSTITUCIONptpatrCodigo, SQLSelect superQuery) throws SystemException {
        try {
            SQLExists sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLExists(superQuery, "", TipoInstitucionCConstants.CLASS_NAME, TipoInstitucionCConstants.CLASS_NAME, null, TipoInstitucionCConstants.CLASS_NAME);
            } else {
                sql = new SQLExists("", TipoInstitucionCConstants.CLASS_NAME, TipoInstitucionCConstants.CLASS_NAME, null, TipoInstitucionCConstants.CLASS_NAME);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // TipoInstitucionC.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(TipoInstitucionCConstants.CLASS_NAME + "." + TipoInstitucionCConstants.ATTR_NAME_TIPOINSTITUCIONCCODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1TIPOINSTITUCIONptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator086, Method: assocOperator086Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator088
	@Override
    public Collection<TipoSucursalC> assocOperator088(Long tGSINC1TIPOSUCURSALptpatrCodigo) throws SystemException {
        try {
            // FOR ALL TipoSucursalC WHERE (TipoSucursalC.Codigo = ptpatrCodigo) DO TipoSucursalC.modificar(TipoSucursalC, ptpatrDescripcion)
            return assocOperator088Range1(tGSINC1TIPOSUCURSALptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator088, Method: assocOperator088";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Collection<TipoSucursalC> assocOperator088Range1 (Long tGSINC1TIPOSUCURSALptpatrCodigo) throws SystemException {

        SQLSelect sql = assocOperator088Range1Query(tGSINC1TIPOSUCURSALptpatrCodigo);
        return new TypedQueryBuilder<TipoSucursalC>(em, TipoSucursalC.class).build(sql).execute();
    }


    public SQLPopulation assocOperator088Range1Query (Long tGSINC1TIPOSUCURSALptpatrCodigo) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation("", TipoSucursalCConstants.CLASS_NAME);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // TipoSucursalC.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(TipoSucursalCConstants.CLASS_NAME + "." + TipoSucursalCConstants.ATTR_NAME_TIPOSUCURSALCCODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1TIPOSUCURSALptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator088, Method: assocOperator088Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }
    // AssocOperator: AssocOperator089
	@Override
    public Boolean assocOperator089(Long tGSINC1TIPOSUCURSALptpatrCodigo) throws SystemException {
        try {
            // EXIST (TipoSucursalC) WHERE (TipoSucursalC.Codigo = ptpatrCodigo)
            return assocOperator089Range1(tGSINC1TIPOSUCURSALptpatrCodigo);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator089, Method: assocOperator089";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Boolean assocOperator089Range1 (Long tGSINC1TIPOSUCURSALptpatrCodigo) throws SystemException {
        SQLExists sql = assocOperator089Range1Query(tGSINC1TIPOSUCURSALptpatrCodigo, null);
        return !new TypedQueryBuilder<TipoSucursalC>(em, TipoSucursalC.class).build(sql).execute().isEmpty();
    }


    public SQLExists assocOperator089Range1Query (Long tGSINC1TIPOSUCURSALptpatrCodigo, SQLSelect superQuery) throws SystemException {
        try {
            SQLExists sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLExists(superQuery, "", TipoSucursalCConstants.CLASS_NAME, TipoSucursalCConstants.CLASS_NAME, null, TipoSucursalCConstants.CLASS_NAME);
            } else {
                sql = new SQLExists("", TipoSucursalCConstants.CLASS_NAME, TipoSucursalCConstants.CLASS_NAME, null, TipoSucursalCConstants.CLASS_NAME);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // TipoSucursalC.Codigo = ptpatrCodigo
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(TipoSucursalCConstants.CLASS_NAME + "." + TipoSucursalCConstants.ATTR_NAME_TIPOSUCURSALCCODIGO, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGSINC1TIPOSUCURSALptpatrCodigo, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator089, Method: assocOperator089Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator139
	@Override
    public Long assocOperator139(String tGCREARCOMBOptpatrContratoIntegral, String tGCREARCOMBOptpatrContratoOdontologico) throws SystemException {
        try {
            // COUNT (LogContratoCombo) WHERE (LogContratoCombo.ContratoIntegral = pt_p_atrContratoIntegral AND LogContratoCombo.ContratoOdontologico = pt_p_atrContratoOdontologico)
            return assocOperator139Range1(tGCREARCOMBOptpatrContratoIntegral, tGCREARCOMBOptpatrContratoOdontologico);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator139, Method: assocOperator139";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Long assocOperator139Range1 (String tGCREARCOMBOptpatrContratoIntegral, String tGCREARCOMBOptpatrContratoOdontologico) throws SystemException {
        SQLCount sql = assocOperator139Range1Query(tGCREARCOMBOptpatrContratoIntegral, tGCREARCOMBOptpatrContratoOdontologico, null);
        return Long.valueOf(new TypedQueryBuilder<LogContratoCombo>(em, LogContratoCombo.class).build(sql).execute().size());
    }


    public SQLCount assocOperator139Range1Query (String tGCREARCOMBOptpatrContratoIntegral, String tGCREARCOMBOptpatrContratoOdontologico, SQLSelect superQuery) throws SystemException {
        try {
            SQLCount sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLCount(superQuery, "", LogContratoComboConstants.CLASS_NAME, LogContratoComboConstants.CLASS_NAME, null, LogContratoComboConstants.CLASS_NAME);
            } else {
                sql = new SQLCount("", LogContratoComboConstants.CLASS_NAME, LogContratoComboConstants.CLASS_NAME, null, LogContratoComboConstants.CLASS_NAME);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // LogContratoCombo.ContratoIntegral = pt_p_atrContratoIntegral
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(LogContratoComboConstants.CLASS_NAME + "." + LogContratoComboConstants.ATTR_NAME_LOGCONTRATOCOMBOCONTRATOINTEGRAL, Constants.Type.STRING.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGCREARCOMBOptpatrContratoIntegral, Constants.Type.STRING.getTypeName()));
                    conjunction.addOperand(comparison1);


            // LogContratoCombo.ContratoOdontologico = pt_p_atrContratoOdontologico
                    // '=' Comparison
                    SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
                    comparison2.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand113 = new SQLWhereOperandPathAttribute(LogContratoComboConstants.CLASS_NAME + "." + LogContratoComboConstants.ATTR_NAME_LOGCONTRATOCOMBOCONTRATOODONTOLOGICO, Constants.Type.STRING.getTypeName());
                    comparison2.setFirstOperand(operand113);
                    
                    // Operand 2
                    comparison2.setSecondOperand(new SQLWhereOperandLiteral(tGCREARCOMBOptpatrContratoOdontologico, Constants.Type.STRING.getTypeName()));
                    conjunction.addOperand(comparison2);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator139, Method: assocOperator139Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator140
	@Override
    public Long assocOperator140(ContratoCombo tGEDITARCOMBOptpthisContratoCombo, String tGEDITARCOMBOptpContratoIntegral, String tGEDITARCOMBOptpContratoOdontologico) throws SystemException {
        try {
            // COUNT (LogContratoCombo) WHERE (LogContratoCombo.Id <> pt_p_thisContratoCombo.Id AND LogContratoCombo.ContratoIntegral = pt_p_ContratoIntegral AND LogContratoCombo.ContratoOdontologico = pt_p_ContratoOdontologico)
            return assocOperator140Range1(tGEDITARCOMBOptpthisContratoCombo, tGEDITARCOMBOptpContratoIntegral, tGEDITARCOMBOptpContratoOdontologico);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator140, Method: assocOperator140";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Long assocOperator140Range1 (ContratoCombo tGEDITARCOMBOptpthisContratoCombo, String tGEDITARCOMBOptpContratoIntegral, String tGEDITARCOMBOptpContratoOdontologico) throws SystemException {
        SQLCount sql = assocOperator140Range1Query(tGEDITARCOMBOptpthisContratoCombo, tGEDITARCOMBOptpContratoIntegral, tGEDITARCOMBOptpContratoOdontologico, null);
        return Long.valueOf(new TypedQueryBuilder<LogContratoCombo>(em, LogContratoCombo.class).build(sql).execute().size());
    }


    public SQLCount assocOperator140Range1Query (ContratoCombo tGEDITARCOMBOptpthisContratoCombo, String tGEDITARCOMBOptpContratoIntegral, String tGEDITARCOMBOptpContratoOdontologico, SQLSelect superQuery) throws SystemException {
        try {
            SQLCount sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLCount(superQuery, "", LogContratoComboConstants.CLASS_NAME, LogContratoComboConstants.CLASS_NAME, null, LogContratoComboConstants.CLASS_NAME);
            } else {
                sql = new SQLCount("", LogContratoComboConstants.CLASS_NAME, LogContratoComboConstants.CLASS_NAME, null, LogContratoComboConstants.CLASS_NAME);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // LogContratoCombo.Id <> pt_p_thisContratoCombo.Id
                    // '<>' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("<>");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(LogContratoComboConstants.CLASS_NAME + "." + LogContratoComboConstants.ATTR_NAME_LOGCONTRATOCOMBOID, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGEDITARCOMBOptpthisContratoCombo.getContratoComboId(), Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);


            // LogContratoCombo.ContratoIntegral = pt_p_ContratoIntegral
                    // '=' Comparison
                    SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
                    comparison2.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand113 = new SQLWhereOperandPathAttribute(LogContratoComboConstants.CLASS_NAME + "." + LogContratoComboConstants.ATTR_NAME_LOGCONTRATOCOMBOCONTRATOINTEGRAL, Constants.Type.STRING.getTypeName());
                    comparison2.setFirstOperand(operand113);
                    
                    // Operand 2
                    comparison2.setSecondOperand(new SQLWhereOperandLiteral(tGEDITARCOMBOptpContratoIntegral, Constants.Type.STRING.getTypeName()));
                    conjunction.addOperand(comparison2);


            // LogContratoCombo.ContratoOdontologico = pt_p_ContratoOdontologico
                    // '=' Comparison
                    SQLWhereComparison comparison3 = new SQLWhereComparison(sql);
                    comparison3.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand114 = new SQLWhereOperandPathAttribute(LogContratoComboConstants.CLASS_NAME + "." + LogContratoComboConstants.ATTR_NAME_LOGCONTRATOCOMBOCONTRATOODONTOLOGICO, Constants.Type.STRING.getTypeName());
                    comparison3.setFirstOperand(operand114);
                    
                    // Operand 2
                    comparison3.setSecondOperand(new SQLWhereOperandLiteral(tGEDITARCOMBOptpContratoOdontologico, Constants.Type.STRING.getTypeName()));
                    conjunction.addOperand(comparison3);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator140, Method: assocOperator140Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator143
	@Override
    public Long assocOperator143(Long tGADDPLANTREPptpatrTipo, String tGADDPLANTREPptpatrNombreArchivoPDF) throws SystemException {
        try {
            // COUNT (PlantillaReporte) WHERE (PlantillaReporte.Tipo <> pt_p_atrTipo AND PlantillaReporte.NombreArchivoPDF = pt_p_atrNombreArchivoPDF)
            return assocOperator143Range1(tGADDPLANTREPptpatrTipo, tGADDPLANTREPptpatrNombreArchivoPDF);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator143, Method: assocOperator143";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Long assocOperator143Range1 (Long tGADDPLANTREPptpatrTipo, String tGADDPLANTREPptpatrNombreArchivoPDF) throws SystemException {
        SQLCount sql = assocOperator143Range1Query(tGADDPLANTREPptpatrTipo, tGADDPLANTREPptpatrNombreArchivoPDF, null);
        return Long.valueOf(new TypedQueryBuilder<PlantillaReporte>(em, PlantillaReporte.class).build(sql).execute().size());
    }


    public SQLCount assocOperator143Range1Query (Long tGADDPLANTREPptpatrTipo, String tGADDPLANTREPptpatrNombreArchivoPDF, SQLSelect superQuery) throws SystemException {
        try {
            SQLCount sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLCount(superQuery, "", PlantillaReporteConstants.CLASS_NAME, PlantillaReporteConstants.CLASS_NAME, null, PlantillaReporteConstants.CLASS_NAME);
            } else {
                sql = new SQLCount("", PlantillaReporteConstants.CLASS_NAME, PlantillaReporteConstants.CLASS_NAME, null, PlantillaReporteConstants.CLASS_NAME);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // PlantillaReporte.Tipo <> pt_p_atrTipo
                    // '<>' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("<>");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(PlantillaReporteConstants.CLASS_NAME + "." + PlantillaReporteConstants.ATTR_NAME_PLANTILLAREPORTETIPO, Constants.Type.NAT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGADDPLANTREPptpatrTipo, Constants.Type.NAT.getTypeName()));
                    conjunction.addOperand(comparison1);


            // PlantillaReporte.NombreArchivoPDF = pt_p_atrNombreArchivoPDF
                    // '=' Comparison
                    SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
                    comparison2.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand113 = new SQLWhereOperandPathAttribute(PlantillaReporteConstants.CLASS_NAME + "." + PlantillaReporteConstants.ATTR_NAME_PLANTILLAREPORTENOMBREARCHIVOPDF, Constants.Type.STRING.getTypeName());
                    comparison2.setFirstOperand(operand113);
                    
                    // Operand 2
                    comparison2.setSecondOperand(new SQLWhereOperandLiteral(tGADDPLANTREPptpatrNombreArchivoPDF, Constants.Type.STRING.getTypeName()));
                    conjunction.addOperand(comparison2);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator143, Method: assocOperator143Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator144
	@Override
    public Long assocOperator144(PlantillaReporte tGEDITPLANTREPptpthisPlantillaReporte, String tGEDITPLANTREPptpNombreArchivoPDF) throws SystemException {
        try {
            // COUNT (PlantillaReporte) WHERE (PlantillaReporte.Tipo <> pt_p_thisPlantillaReporte.Tipo AND PlantillaReporte.NombreArchivoPDF = pt_p_NombreArchivoPDF)
            return assocOperator144Range1(tGEDITPLANTREPptpthisPlantillaReporte, tGEDITPLANTREPptpNombreArchivoPDF);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator144, Method: assocOperator144";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Long assocOperator144Range1 (PlantillaReporte tGEDITPLANTREPptpthisPlantillaReporte, String tGEDITPLANTREPptpNombreArchivoPDF) throws SystemException {
        SQLCount sql = assocOperator144Range1Query(tGEDITPLANTREPptpthisPlantillaReporte, tGEDITPLANTREPptpNombreArchivoPDF, null);
        return Long.valueOf(new TypedQueryBuilder<PlantillaReporte>(em, PlantillaReporte.class).build(sql).execute().size());
    }


    public SQLCount assocOperator144Range1Query (PlantillaReporte tGEDITPLANTREPptpthisPlantillaReporte, String tGEDITPLANTREPptpNombreArchivoPDF, SQLSelect superQuery) throws SystemException {
        try {
            SQLCount sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLCount(superQuery, "", PlantillaReporteConstants.CLASS_NAME, PlantillaReporteConstants.CLASS_NAME, null, PlantillaReporteConstants.CLASS_NAME);
            } else {
                sql = new SQLCount("", PlantillaReporteConstants.CLASS_NAME, PlantillaReporteConstants.CLASS_NAME, null, PlantillaReporteConstants.CLASS_NAME);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // PlantillaReporte.Tipo <> pt_p_thisPlantillaReporte.Tipo
                    // '<>' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("<>");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(PlantillaReporteConstants.CLASS_NAME + "." + PlantillaReporteConstants.ATTR_NAME_PLANTILLAREPORTETIPO, Constants.Type.NAT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGEDITPLANTREPptpthisPlantillaReporte.getPlantillaReporteTipo(), Constants.Type.NAT.getTypeName()));
                    conjunction.addOperand(comparison1);


            // PlantillaReporte.NombreArchivoPDF = pt_p_NombreArchivoPDF
                    // '=' Comparison
                    SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
                    comparison2.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand113 = new SQLWhereOperandPathAttribute(PlantillaReporteConstants.CLASS_NAME + "." + PlantillaReporteConstants.ATTR_NAME_PLANTILLAREPORTENOMBREARCHIVOPDF, Constants.Type.STRING.getTypeName());
                    comparison2.setFirstOperand(operand113);
                    
                    // Operand 2
                    comparison2.setSecondOperand(new SQLWhereOperandLiteral(tGEDITPLANTREPptpNombreArchivoPDF, Constants.Type.STRING.getTypeName()));
                    conjunction.addOperand(comparison2);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator144, Method: assocOperator144Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator145
	@Override
    public Long assocOperator145(Date tGCREARFINIEPSptpatrDesde, Date tGCREARFINIEPSptpatrHasta, Long tGCREARFINIEPSptpatrTramite, Long tGCREARFINIEPSptpatrTipoContrato) throws SystemException {
        try {
            // COUNT (FechaIniEPS) WHERE (FechaIniEPS.Desde >= pt_p_atrDesde AND FechaIniEPS.Desde <= pt_p_atrHasta AND FechaIniEPS.Tramite = pt_p_atrTramite AND FechaIniEPS.TipoContrato = pt_p_atrTipoContrato)
            return assocOperator145Range1(tGCREARFINIEPSptpatrDesde, tGCREARFINIEPSptpatrHasta, tGCREARFINIEPSptpatrTramite, tGCREARFINIEPSptpatrTipoContrato);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator145, Method: assocOperator145";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Long assocOperator145Range1 (Date tGCREARFINIEPSptpatrDesde, Date tGCREARFINIEPSptpatrHasta, Long tGCREARFINIEPSptpatrTramite, Long tGCREARFINIEPSptpatrTipoContrato) throws SystemException {
        SQLCount sql = assocOperator145Range1Query(tGCREARFINIEPSptpatrDesde, tGCREARFINIEPSptpatrHasta, tGCREARFINIEPSptpatrTramite, tGCREARFINIEPSptpatrTipoContrato, null);
        return Long.valueOf(new TypedQueryBuilder<FechaIniEPS>(em, FechaIniEPS.class).build(sql).execute().size());
    }


    public SQLCount assocOperator145Range1Query (Date tGCREARFINIEPSptpatrDesde, Date tGCREARFINIEPSptpatrHasta, Long tGCREARFINIEPSptpatrTramite, Long tGCREARFINIEPSptpatrTipoContrato, SQLSelect superQuery) throws SystemException {
        try {
            SQLCount sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLCount(superQuery, "", FechaIniEPSConstants.CLASS_NAME, FechaIniEPSConstants.CLASS_NAME, null, FechaIniEPSConstants.CLASS_NAME);
            } else {
                sql = new SQLCount("", FechaIniEPSConstants.CLASS_NAME, FechaIniEPSConstants.CLASS_NAME, null, FechaIniEPSConstants.CLASS_NAME);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // FechaIniEPS.Desde >= pt_p_atrDesde
                    // '>=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator(">=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(FechaIniEPSConstants.CLASS_NAME + "." + FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSDESDE, Constants.Type.DATE.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGCREARFINIEPSptpatrDesde, Constants.Type.DATE.getTypeName()));
                    conjunction.addOperand(comparison1);


            // FechaIniEPS.Desde <= pt_p_atrHasta
                    // '<=' Comparison
                    SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
                    comparison2.setOperator("<=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand113 = new SQLWhereOperandPathAttribute(FechaIniEPSConstants.CLASS_NAME + "." + FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSDESDE, Constants.Type.DATE.getTypeName());
                    comparison2.setFirstOperand(operand113);
                    
                    // Operand 2
                    comparison2.setSecondOperand(new SQLWhereOperandLiteral(tGCREARFINIEPSptpatrHasta, Constants.Type.DATE.getTypeName()));
                    conjunction.addOperand(comparison2);


            // FechaIniEPS.Tramite = pt_p_atrTramite
                    // '=' Comparison
                    SQLWhereComparison comparison3 = new SQLWhereComparison(sql);
                    comparison3.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand114 = new SQLWhereOperandPathAttribute(FechaIniEPSConstants.CLASS_NAME + "." + FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSTRAMITE, Constants.Type.NAT.getTypeName());
                    comparison3.setFirstOperand(operand114);
                    
                    // Operand 2
                    comparison3.setSecondOperand(new SQLWhereOperandLiteral(tGCREARFINIEPSptpatrTramite, Constants.Type.NAT.getTypeName()));
                    conjunction.addOperand(comparison3);


            // FechaIniEPS.TipoContrato = pt_p_atrTipoContrato
                    // '=' Comparison
                    SQLWhereComparison comparison4 = new SQLWhereComparison(sql);
                    comparison4.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand115 = new SQLWhereOperandPathAttribute(FechaIniEPSConstants.CLASS_NAME + "." + FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSTIPOCONTRATO, Constants.Type.NAT.getTypeName());
                    comparison4.setFirstOperand(operand115);
                    
                    // Operand 2
                    comparison4.setSecondOperand(new SQLWhereOperandLiteral(tGCREARFINIEPSptpatrTipoContrato, Constants.Type.NAT.getTypeName()));
                    conjunction.addOperand(comparison4);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator145, Method: assocOperator145Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }
    // AssocOperator: AssocOperator146
	@Override
    public Long assocOperator146(Date tGCREARFINIEPSptpatrDesde, Date tGCREARFINIEPSptpatrHasta, Long tGCREARFINIEPSptpatrTramite, Long tGCREARFINIEPSptpatrTipoContrato) throws SystemException {
        try {
            // COUNT (FechaIniEPS) WHERE (FechaIniEPS.Hasta >= pt_p_atrDesde AND FechaIniEPS.Hasta <= pt_p_atrHasta AND FechaIniEPS.Tramite = pt_p_atrTramite AND FechaIniEPS.TipoContrato = pt_p_atrTipoContrato)
            return assocOperator146Range1(tGCREARFINIEPSptpatrDesde, tGCREARFINIEPSptpatrHasta, tGCREARFINIEPSptpatrTramite, tGCREARFINIEPSptpatrTipoContrato);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator146, Method: assocOperator146";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Long assocOperator146Range1 (Date tGCREARFINIEPSptpatrDesde, Date tGCREARFINIEPSptpatrHasta, Long tGCREARFINIEPSptpatrTramite, Long tGCREARFINIEPSptpatrTipoContrato) throws SystemException {
        SQLCount sql = assocOperator146Range1Query(tGCREARFINIEPSptpatrDesde, tGCREARFINIEPSptpatrHasta, tGCREARFINIEPSptpatrTramite, tGCREARFINIEPSptpatrTipoContrato, null);
        return Long.valueOf(new TypedQueryBuilder<FechaIniEPS>(em, FechaIniEPS.class).build(sql).execute().size());
    }


    public SQLCount assocOperator146Range1Query (Date tGCREARFINIEPSptpatrDesde, Date tGCREARFINIEPSptpatrHasta, Long tGCREARFINIEPSptpatrTramite, Long tGCREARFINIEPSptpatrTipoContrato, SQLSelect superQuery) throws SystemException {
        try {
            SQLCount sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLCount(superQuery, "", FechaIniEPSConstants.CLASS_NAME, FechaIniEPSConstants.CLASS_NAME, null, FechaIniEPSConstants.CLASS_NAME);
            } else {
                sql = new SQLCount("", FechaIniEPSConstants.CLASS_NAME, FechaIniEPSConstants.CLASS_NAME, null, FechaIniEPSConstants.CLASS_NAME);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // FechaIniEPS.Hasta >= pt_p_atrDesde
                    // '>=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator(">=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(FechaIniEPSConstants.CLASS_NAME + "." + FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSHASTA, Constants.Type.DATE.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGCREARFINIEPSptpatrDesde, Constants.Type.DATE.getTypeName()));
                    conjunction.addOperand(comparison1);


            // FechaIniEPS.Hasta <= pt_p_atrHasta
                    // '<=' Comparison
                    SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
                    comparison2.setOperator("<=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand113 = new SQLWhereOperandPathAttribute(FechaIniEPSConstants.CLASS_NAME + "." + FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSHASTA, Constants.Type.DATE.getTypeName());
                    comparison2.setFirstOperand(operand113);
                    
                    // Operand 2
                    comparison2.setSecondOperand(new SQLWhereOperandLiteral(tGCREARFINIEPSptpatrHasta, Constants.Type.DATE.getTypeName()));
                    conjunction.addOperand(comparison2);


            // FechaIniEPS.Tramite = pt_p_atrTramite
                    // '=' Comparison
                    SQLWhereComparison comparison3 = new SQLWhereComparison(sql);
                    comparison3.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand114 = new SQLWhereOperandPathAttribute(FechaIniEPSConstants.CLASS_NAME + "." + FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSTRAMITE, Constants.Type.NAT.getTypeName());
                    comparison3.setFirstOperand(operand114);
                    
                    // Operand 2
                    comparison3.setSecondOperand(new SQLWhereOperandLiteral(tGCREARFINIEPSptpatrTramite, Constants.Type.NAT.getTypeName()));
                    conjunction.addOperand(comparison3);


            // FechaIniEPS.TipoContrato = pt_p_atrTipoContrato
                    // '=' Comparison
                    SQLWhereComparison comparison4 = new SQLWhereComparison(sql);
                    comparison4.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand115 = new SQLWhereOperandPathAttribute(FechaIniEPSConstants.CLASS_NAME + "." + FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSTIPOCONTRATO, Constants.Type.NAT.getTypeName());
                    comparison4.setFirstOperand(operand115);
                    
                    // Operand 2
                    comparison4.setSecondOperand(new SQLWhereOperandLiteral(tGCREARFINIEPSptpatrTipoContrato, Constants.Type.NAT.getTypeName()));
                    conjunction.addOperand(comparison4);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator146, Method: assocOperator146Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }
    // AssocOperator: AssocOperator147
	@Override
    public Long assocOperator147(Date tGCREARFINIEPSptpatrDesde, Date tGCREARFINIEPSptpatrHasta, Long tGCREARFINIEPSptpatrTramite, Long tGCREARFINIEPSptpatrTipoContrato) throws SystemException {
        try {
            // COUNT (FechaIniEPS) WHERE (FechaIniEPS.Desde <= pt_p_atrDesde AND FechaIniEPS.Hasta >= pt_p_atrHasta AND FechaIniEPS.Tramite = pt_p_atrTramite AND FechaIniEPS.TipoContrato = pt_p_atrTipoContrato)
            return assocOperator147Range1(tGCREARFINIEPSptpatrDesde, tGCREARFINIEPSptpatrHasta, tGCREARFINIEPSptpatrTramite, tGCREARFINIEPSptpatrTipoContrato);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator147, Method: assocOperator147";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Long assocOperator147Range1 (Date tGCREARFINIEPSptpatrDesde, Date tGCREARFINIEPSptpatrHasta, Long tGCREARFINIEPSptpatrTramite, Long tGCREARFINIEPSptpatrTipoContrato) throws SystemException {
        SQLCount sql = assocOperator147Range1Query(tGCREARFINIEPSptpatrDesde, tGCREARFINIEPSptpatrHasta, tGCREARFINIEPSptpatrTramite, tGCREARFINIEPSptpatrTipoContrato, null);
        return Long.valueOf(new TypedQueryBuilder<FechaIniEPS>(em, FechaIniEPS.class).build(sql).execute().size());
    }


    public SQLCount assocOperator147Range1Query (Date tGCREARFINIEPSptpatrDesde, Date tGCREARFINIEPSptpatrHasta, Long tGCREARFINIEPSptpatrTramite, Long tGCREARFINIEPSptpatrTipoContrato, SQLSelect superQuery) throws SystemException {
        try {
            SQLCount sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLCount(superQuery, "", FechaIniEPSConstants.CLASS_NAME, FechaIniEPSConstants.CLASS_NAME, null, FechaIniEPSConstants.CLASS_NAME);
            } else {
                sql = new SQLCount("", FechaIniEPSConstants.CLASS_NAME, FechaIniEPSConstants.CLASS_NAME, null, FechaIniEPSConstants.CLASS_NAME);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // FechaIniEPS.Desde <= pt_p_atrDesde
                    // '<=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("<=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(FechaIniEPSConstants.CLASS_NAME + "." + FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSDESDE, Constants.Type.DATE.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGCREARFINIEPSptpatrDesde, Constants.Type.DATE.getTypeName()));
                    conjunction.addOperand(comparison1);


            // FechaIniEPS.Hasta >= pt_p_atrHasta
                    // '>=' Comparison
                    SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
                    comparison2.setOperator(">=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand113 = new SQLWhereOperandPathAttribute(FechaIniEPSConstants.CLASS_NAME + "." + FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSHASTA, Constants.Type.DATE.getTypeName());
                    comparison2.setFirstOperand(operand113);
                    
                    // Operand 2
                    comparison2.setSecondOperand(new SQLWhereOperandLiteral(tGCREARFINIEPSptpatrHasta, Constants.Type.DATE.getTypeName()));
                    conjunction.addOperand(comparison2);


            // FechaIniEPS.Tramite = pt_p_atrTramite
                    // '=' Comparison
                    SQLWhereComparison comparison3 = new SQLWhereComparison(sql);
                    comparison3.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand114 = new SQLWhereOperandPathAttribute(FechaIniEPSConstants.CLASS_NAME + "." + FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSTRAMITE, Constants.Type.NAT.getTypeName());
                    comparison3.setFirstOperand(operand114);
                    
                    // Operand 2
                    comparison3.setSecondOperand(new SQLWhereOperandLiteral(tGCREARFINIEPSptpatrTramite, Constants.Type.NAT.getTypeName()));
                    conjunction.addOperand(comparison3);


            // FechaIniEPS.TipoContrato = pt_p_atrTipoContrato
                    // '=' Comparison
                    SQLWhereComparison comparison4 = new SQLWhereComparison(sql);
                    comparison4.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand115 = new SQLWhereOperandPathAttribute(FechaIniEPSConstants.CLASS_NAME + "." + FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSTIPOCONTRATO, Constants.Type.NAT.getTypeName());
                    comparison4.setFirstOperand(operand115);
                    
                    // Operand 2
                    comparison4.setSecondOperand(new SQLWhereOperandLiteral(tGCREARFINIEPSptpatrTipoContrato, Constants.Type.NAT.getTypeName()));
                    conjunction.addOperand(comparison4);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator147, Method: assocOperator147Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }
    // AssocOperator: AssocOperator148
	@Override
    public Long assocOperator148(Date tGCREARFINIEPSptpatrDesde, Date tGCREARFINIEPSptpatrHasta, Long tGCREARFINIEPSptpatrTramite, Long tGCREARFINIEPSptpatrTipoContrato) throws SystemException {
        try {
            // COUNT (FechaIniEPS) WHERE (FechaIniEPS.Desde >= pt_p_atrDesde AND FechaIniEPS.Hasta <= pt_p_atrHasta AND FechaIniEPS.Tramite = pt_p_atrTramite AND FechaIniEPS.TipoContrato = pt_p_atrTipoContrato)
            return assocOperator148Range1(tGCREARFINIEPSptpatrDesde, tGCREARFINIEPSptpatrHasta, tGCREARFINIEPSptpatrTramite, tGCREARFINIEPSptpatrTipoContrato);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator148, Method: assocOperator148";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Long assocOperator148Range1 (Date tGCREARFINIEPSptpatrDesde, Date tGCREARFINIEPSptpatrHasta, Long tGCREARFINIEPSptpatrTramite, Long tGCREARFINIEPSptpatrTipoContrato) throws SystemException {
        SQLCount sql = assocOperator148Range1Query(tGCREARFINIEPSptpatrDesde, tGCREARFINIEPSptpatrHasta, tGCREARFINIEPSptpatrTramite, tGCREARFINIEPSptpatrTipoContrato, null);
        return Long.valueOf(new TypedQueryBuilder<FechaIniEPS>(em, FechaIniEPS.class).build(sql).execute().size());
    }


    public SQLCount assocOperator148Range1Query (Date tGCREARFINIEPSptpatrDesde, Date tGCREARFINIEPSptpatrHasta, Long tGCREARFINIEPSptpatrTramite, Long tGCREARFINIEPSptpatrTipoContrato, SQLSelect superQuery) throws SystemException {
        try {
            SQLCount sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLCount(superQuery, "", FechaIniEPSConstants.CLASS_NAME, FechaIniEPSConstants.CLASS_NAME, null, FechaIniEPSConstants.CLASS_NAME);
            } else {
                sql = new SQLCount("", FechaIniEPSConstants.CLASS_NAME, FechaIniEPSConstants.CLASS_NAME, null, FechaIniEPSConstants.CLASS_NAME);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // FechaIniEPS.Desde >= pt_p_atrDesde
                    // '>=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator(">=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(FechaIniEPSConstants.CLASS_NAME + "." + FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSDESDE, Constants.Type.DATE.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGCREARFINIEPSptpatrDesde, Constants.Type.DATE.getTypeName()));
                    conjunction.addOperand(comparison1);


            // FechaIniEPS.Hasta <= pt_p_atrHasta
                    // '<=' Comparison
                    SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
                    comparison2.setOperator("<=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand113 = new SQLWhereOperandPathAttribute(FechaIniEPSConstants.CLASS_NAME + "." + FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSHASTA, Constants.Type.DATE.getTypeName());
                    comparison2.setFirstOperand(operand113);
                    
                    // Operand 2
                    comparison2.setSecondOperand(new SQLWhereOperandLiteral(tGCREARFINIEPSptpatrHasta, Constants.Type.DATE.getTypeName()));
                    conjunction.addOperand(comparison2);


            // FechaIniEPS.Tramite = pt_p_atrTramite
                    // '=' Comparison
                    SQLWhereComparison comparison3 = new SQLWhereComparison(sql);
                    comparison3.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand114 = new SQLWhereOperandPathAttribute(FechaIniEPSConstants.CLASS_NAME + "." + FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSTRAMITE, Constants.Type.NAT.getTypeName());
                    comparison3.setFirstOperand(operand114);
                    
                    // Operand 2
                    comparison3.setSecondOperand(new SQLWhereOperandLiteral(tGCREARFINIEPSptpatrTramite, Constants.Type.NAT.getTypeName()));
                    conjunction.addOperand(comparison3);


            // FechaIniEPS.TipoContrato = pt_p_atrTipoContrato
                    // '=' Comparison
                    SQLWhereComparison comparison4 = new SQLWhereComparison(sql);
                    comparison4.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand115 = new SQLWhereOperandPathAttribute(FechaIniEPSConstants.CLASS_NAME + "." + FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSTIPOCONTRATO, Constants.Type.NAT.getTypeName());
                    comparison4.setFirstOperand(operand115);
                    
                    // Operand 2
                    comparison4.setSecondOperand(new SQLWhereOperandLiteral(tGCREARFINIEPSptpatrTipoContrato, Constants.Type.NAT.getTypeName()));
                    conjunction.addOperand(comparison4);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator148, Method: assocOperator148Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator149
	@Override
    public Long assocOperator149(FechaIniEPS tGMODFINIEPSptpthisFechaIniEPS, Date tGMODFINIEPSptpDesde, Date tGMODFINIEPSptpHasta) throws SystemException {
        try {
            // COUNT (FechaIniEPS) WHERE (FechaIniEPS.Id <> pt_p_thisFechaIniEPS.Id AND FechaIniEPS.Desde >= pt_p_Desde AND FechaIniEPS.Desde <= pt_p_Hasta AND FechaIniEPS.Tramite = pt_p_thisFechaIniEPS.Tramite AND FechaIniEPS.TipoContrato = pt_p_thisFechaIniEPS.TipoContrato)
            return assocOperator149Range1(tGMODFINIEPSptpthisFechaIniEPS, tGMODFINIEPSptpDesde, tGMODFINIEPSptpHasta);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator149, Method: assocOperator149";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Long assocOperator149Range1 (FechaIniEPS tGMODFINIEPSptpthisFechaIniEPS, Date tGMODFINIEPSptpDesde, Date tGMODFINIEPSptpHasta) throws SystemException {
        SQLCount sql = assocOperator149Range1Query(tGMODFINIEPSptpthisFechaIniEPS, tGMODFINIEPSptpDesde, tGMODFINIEPSptpHasta, null);
        return Long.valueOf(new TypedQueryBuilder<FechaIniEPS>(em, FechaIniEPS.class).build(sql).execute().size());
    }


    public SQLCount assocOperator149Range1Query (FechaIniEPS tGMODFINIEPSptpthisFechaIniEPS, Date tGMODFINIEPSptpDesde, Date tGMODFINIEPSptpHasta, SQLSelect superQuery) throws SystemException {
        try {
            SQLCount sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLCount(superQuery, "", FechaIniEPSConstants.CLASS_NAME, FechaIniEPSConstants.CLASS_NAME, null, FechaIniEPSConstants.CLASS_NAME);
            } else {
                sql = new SQLCount("", FechaIniEPSConstants.CLASS_NAME, FechaIniEPSConstants.CLASS_NAME, null, FechaIniEPSConstants.CLASS_NAME);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // FechaIniEPS.Id <> pt_p_thisFechaIniEPS.Id
                    // '<>' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("<>");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(FechaIniEPSConstants.CLASS_NAME + "." + FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSID, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGMODFINIEPSptpthisFechaIniEPS.getFechaIniEPSId(), Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);


            // FechaIniEPS.Desde >= pt_p_Desde
                    // '>=' Comparison
                    SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
                    comparison2.setOperator(">=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand113 = new SQLWhereOperandPathAttribute(FechaIniEPSConstants.CLASS_NAME + "." + FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSDESDE, Constants.Type.DATE.getTypeName());
                    comparison2.setFirstOperand(operand113);
                    
                    // Operand 2
                    comparison2.setSecondOperand(new SQLWhereOperandLiteral(tGMODFINIEPSptpDesde, Constants.Type.DATE.getTypeName()));
                    conjunction.addOperand(comparison2);


            // FechaIniEPS.Desde <= pt_p_Hasta
                    // '<=' Comparison
                    SQLWhereComparison comparison3 = new SQLWhereComparison(sql);
                    comparison3.setOperator("<=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand114 = new SQLWhereOperandPathAttribute(FechaIniEPSConstants.CLASS_NAME + "." + FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSDESDE, Constants.Type.DATE.getTypeName());
                    comparison3.setFirstOperand(operand114);
                    
                    // Operand 2
                    comparison3.setSecondOperand(new SQLWhereOperandLiteral(tGMODFINIEPSptpHasta, Constants.Type.DATE.getTypeName()));
                    conjunction.addOperand(comparison3);


            // FechaIniEPS.Tramite = pt_p_thisFechaIniEPS.Tramite
                    // '=' Comparison
                    SQLWhereComparison comparison4 = new SQLWhereComparison(sql);
                    comparison4.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand115 = new SQLWhereOperandPathAttribute(FechaIniEPSConstants.CLASS_NAME + "." + FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSTRAMITE, Constants.Type.NAT.getTypeName());
                    comparison4.setFirstOperand(operand115);
                    
                    // Operand 2
                    comparison4.setSecondOperand(new SQLWhereOperandLiteral(tGMODFINIEPSptpthisFechaIniEPS.getFechaIniEPSTramite(), Constants.Type.NAT.getTypeName()));
                    conjunction.addOperand(comparison4);


            // FechaIniEPS.TipoContrato = pt_p_thisFechaIniEPS.TipoContrato
                    // '=' Comparison
                    SQLWhereComparison comparison5 = new SQLWhereComparison(sql);
                    comparison5.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand116 = new SQLWhereOperandPathAttribute(FechaIniEPSConstants.CLASS_NAME + "." + FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSTIPOCONTRATO, Constants.Type.NAT.getTypeName());
                    comparison5.setFirstOperand(operand116);
                    
                    // Operand 2
                    comparison5.setSecondOperand(new SQLWhereOperandLiteral(tGMODFINIEPSptpthisFechaIniEPS.getFechaIniEPSTipoContrato(), Constants.Type.NAT.getTypeName()));
                    conjunction.addOperand(comparison5);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator149, Method: assocOperator149Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }
    // AssocOperator: AssocOperator150
	@Override
    public Long assocOperator150(FechaIniEPS tGMODFINIEPSptpthisFechaIniEPS, Date tGMODFINIEPSptpDesde, Date tGMODFINIEPSptpHasta) throws SystemException {
        try {
            // COUNT (FechaIniEPS) WHERE (FechaIniEPS.Id <> pt_p_thisFechaIniEPS.Id AND FechaIniEPS.Hasta >= pt_p_Desde AND FechaIniEPS.Hasta <= pt_p_Hasta AND FechaIniEPS.Tramite = pt_p_thisFechaIniEPS.Tramite AND FechaIniEPS.TipoContrato = pt_p_thisFechaIniEPS.TipoContrato)
            return assocOperator150Range1(tGMODFINIEPSptpthisFechaIniEPS, tGMODFINIEPSptpDesde, tGMODFINIEPSptpHasta);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator150, Method: assocOperator150";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Long assocOperator150Range1 (FechaIniEPS tGMODFINIEPSptpthisFechaIniEPS, Date tGMODFINIEPSptpDesde, Date tGMODFINIEPSptpHasta) throws SystemException {
        SQLCount sql = assocOperator150Range1Query(tGMODFINIEPSptpthisFechaIniEPS, tGMODFINIEPSptpDesde, tGMODFINIEPSptpHasta, null);
        return Long.valueOf(new TypedQueryBuilder<FechaIniEPS>(em, FechaIniEPS.class).build(sql).execute().size());
    }


    public SQLCount assocOperator150Range1Query (FechaIniEPS tGMODFINIEPSptpthisFechaIniEPS, Date tGMODFINIEPSptpDesde, Date tGMODFINIEPSptpHasta, SQLSelect superQuery) throws SystemException {
        try {
            SQLCount sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLCount(superQuery, "", FechaIniEPSConstants.CLASS_NAME, FechaIniEPSConstants.CLASS_NAME, null, FechaIniEPSConstants.CLASS_NAME);
            } else {
                sql = new SQLCount("", FechaIniEPSConstants.CLASS_NAME, FechaIniEPSConstants.CLASS_NAME, null, FechaIniEPSConstants.CLASS_NAME);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // FechaIniEPS.Id <> pt_p_thisFechaIniEPS.Id
                    // '<>' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("<>");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(FechaIniEPSConstants.CLASS_NAME + "." + FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSID, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGMODFINIEPSptpthisFechaIniEPS.getFechaIniEPSId(), Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);


            // FechaIniEPS.Hasta >= pt_p_Desde
                    // '>=' Comparison
                    SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
                    comparison2.setOperator(">=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand113 = new SQLWhereOperandPathAttribute(FechaIniEPSConstants.CLASS_NAME + "." + FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSHASTA, Constants.Type.DATE.getTypeName());
                    comparison2.setFirstOperand(operand113);
                    
                    // Operand 2
                    comparison2.setSecondOperand(new SQLWhereOperandLiteral(tGMODFINIEPSptpDesde, Constants.Type.DATE.getTypeName()));
                    conjunction.addOperand(comparison2);


            // FechaIniEPS.Hasta <= pt_p_Hasta
                    // '<=' Comparison
                    SQLWhereComparison comparison3 = new SQLWhereComparison(sql);
                    comparison3.setOperator("<=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand114 = new SQLWhereOperandPathAttribute(FechaIniEPSConstants.CLASS_NAME + "." + FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSHASTA, Constants.Type.DATE.getTypeName());
                    comparison3.setFirstOperand(operand114);
                    
                    // Operand 2
                    comparison3.setSecondOperand(new SQLWhereOperandLiteral(tGMODFINIEPSptpHasta, Constants.Type.DATE.getTypeName()));
                    conjunction.addOperand(comparison3);


            // FechaIniEPS.Tramite = pt_p_thisFechaIniEPS.Tramite
                    // '=' Comparison
                    SQLWhereComparison comparison4 = new SQLWhereComparison(sql);
                    comparison4.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand115 = new SQLWhereOperandPathAttribute(FechaIniEPSConstants.CLASS_NAME + "." + FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSTRAMITE, Constants.Type.NAT.getTypeName());
                    comparison4.setFirstOperand(operand115);
                    
                    // Operand 2
                    comparison4.setSecondOperand(new SQLWhereOperandLiteral(tGMODFINIEPSptpthisFechaIniEPS.getFechaIniEPSTramite(), Constants.Type.NAT.getTypeName()));
                    conjunction.addOperand(comparison4);


            // FechaIniEPS.TipoContrato = pt_p_thisFechaIniEPS.TipoContrato
                    // '=' Comparison
                    SQLWhereComparison comparison5 = new SQLWhereComparison(sql);
                    comparison5.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand116 = new SQLWhereOperandPathAttribute(FechaIniEPSConstants.CLASS_NAME + "." + FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSTIPOCONTRATO, Constants.Type.NAT.getTypeName());
                    comparison5.setFirstOperand(operand116);
                    
                    // Operand 2
                    comparison5.setSecondOperand(new SQLWhereOperandLiteral(tGMODFINIEPSptpthisFechaIniEPS.getFechaIniEPSTipoContrato(), Constants.Type.NAT.getTypeName()));
                    conjunction.addOperand(comparison5);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator150, Method: assocOperator150Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }
    // AssocOperator: AssocOperator151
	@Override
    public Long assocOperator151(FechaIniEPS tGMODFINIEPSptpthisFechaIniEPS, Date tGMODFINIEPSptpDesde, Date tGMODFINIEPSptpHasta) throws SystemException {
        try {
            // COUNT (FechaIniEPS) WHERE (FechaIniEPS.Id <> pt_p_thisFechaIniEPS.Id AND FechaIniEPS.Desde <= pt_p_Desde AND FechaIniEPS.Hasta >= pt_p_Hasta AND FechaIniEPS.Tramite = pt_p_thisFechaIniEPS.Tramite AND FechaIniEPS.TipoContrato = pt_p_thisFechaIniEPS.TipoContrato)
            return assocOperator151Range1(tGMODFINIEPSptpthisFechaIniEPS, tGMODFINIEPSptpDesde, tGMODFINIEPSptpHasta);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator151, Method: assocOperator151";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Long assocOperator151Range1 (FechaIniEPS tGMODFINIEPSptpthisFechaIniEPS, Date tGMODFINIEPSptpDesde, Date tGMODFINIEPSptpHasta) throws SystemException {
        SQLCount sql = assocOperator151Range1Query(tGMODFINIEPSptpthisFechaIniEPS, tGMODFINIEPSptpDesde, tGMODFINIEPSptpHasta, null);
        return Long.valueOf(new TypedQueryBuilder<FechaIniEPS>(em, FechaIniEPS.class).build(sql).execute().size());
    }


    public SQLCount assocOperator151Range1Query (FechaIniEPS tGMODFINIEPSptpthisFechaIniEPS, Date tGMODFINIEPSptpDesde, Date tGMODFINIEPSptpHasta, SQLSelect superQuery) throws SystemException {
        try {
            SQLCount sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLCount(superQuery, "", FechaIniEPSConstants.CLASS_NAME, FechaIniEPSConstants.CLASS_NAME, null, FechaIniEPSConstants.CLASS_NAME);
            } else {
                sql = new SQLCount("", FechaIniEPSConstants.CLASS_NAME, FechaIniEPSConstants.CLASS_NAME, null, FechaIniEPSConstants.CLASS_NAME);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // FechaIniEPS.Id <> pt_p_thisFechaIniEPS.Id
                    // '<>' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("<>");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(FechaIniEPSConstants.CLASS_NAME + "." + FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSID, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGMODFINIEPSptpthisFechaIniEPS.getFechaIniEPSId(), Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);


            // FechaIniEPS.Desde <= pt_p_Desde
                    // '<=' Comparison
                    SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
                    comparison2.setOperator("<=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand113 = new SQLWhereOperandPathAttribute(FechaIniEPSConstants.CLASS_NAME + "." + FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSDESDE, Constants.Type.DATE.getTypeName());
                    comparison2.setFirstOperand(operand113);
                    
                    // Operand 2
                    comparison2.setSecondOperand(new SQLWhereOperandLiteral(tGMODFINIEPSptpDesde, Constants.Type.DATE.getTypeName()));
                    conjunction.addOperand(comparison2);


            // FechaIniEPS.Hasta >= pt_p_Hasta
                    // '>=' Comparison
                    SQLWhereComparison comparison3 = new SQLWhereComparison(sql);
                    comparison3.setOperator(">=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand114 = new SQLWhereOperandPathAttribute(FechaIniEPSConstants.CLASS_NAME + "." + FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSHASTA, Constants.Type.DATE.getTypeName());
                    comparison3.setFirstOperand(operand114);
                    
                    // Operand 2
                    comparison3.setSecondOperand(new SQLWhereOperandLiteral(tGMODFINIEPSptpHasta, Constants.Type.DATE.getTypeName()));
                    conjunction.addOperand(comparison3);


            // FechaIniEPS.Tramite = pt_p_thisFechaIniEPS.Tramite
                    // '=' Comparison
                    SQLWhereComparison comparison4 = new SQLWhereComparison(sql);
                    comparison4.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand115 = new SQLWhereOperandPathAttribute(FechaIniEPSConstants.CLASS_NAME + "." + FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSTRAMITE, Constants.Type.NAT.getTypeName());
                    comparison4.setFirstOperand(operand115);
                    
                    // Operand 2
                    comparison4.setSecondOperand(new SQLWhereOperandLiteral(tGMODFINIEPSptpthisFechaIniEPS.getFechaIniEPSTramite(), Constants.Type.NAT.getTypeName()));
                    conjunction.addOperand(comparison4);


            // FechaIniEPS.TipoContrato = pt_p_thisFechaIniEPS.TipoContrato
                    // '=' Comparison
                    SQLWhereComparison comparison5 = new SQLWhereComparison(sql);
                    comparison5.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand116 = new SQLWhereOperandPathAttribute(FechaIniEPSConstants.CLASS_NAME + "." + FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSTIPOCONTRATO, Constants.Type.NAT.getTypeName());
                    comparison5.setFirstOperand(operand116);
                    
                    // Operand 2
                    comparison5.setSecondOperand(new SQLWhereOperandLiteral(tGMODFINIEPSptpthisFechaIniEPS.getFechaIniEPSTipoContrato(), Constants.Type.NAT.getTypeName()));
                    conjunction.addOperand(comparison5);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator151, Method: assocOperator151Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }
    // AssocOperator: AssocOperator152
	@Override
    public Long assocOperator152(FechaIniEPS tGMODFINIEPSptpthisFechaIniEPS, Date tGMODFINIEPSptpDesde, Date tGMODFINIEPSptpHasta) throws SystemException {
        try {
            // COUNT (FechaIniEPS) WHERE (FechaIniEPS.Id <> pt_p_thisFechaIniEPS.Id AND FechaIniEPS.Desde >= pt_p_Desde AND FechaIniEPS.Hasta <= pt_p_Hasta AND FechaIniEPS.Tramite = pt_p_thisFechaIniEPS.Tramite AND FechaIniEPS.TipoContrato = pt_p_thisFechaIniEPS.TipoContrato)
            return assocOperator152Range1(tGMODFINIEPSptpthisFechaIniEPS, tGMODFINIEPSptpDesde, tGMODFINIEPSptpHasta);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator152, Method: assocOperator152";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Long assocOperator152Range1 (FechaIniEPS tGMODFINIEPSptpthisFechaIniEPS, Date tGMODFINIEPSptpDesde, Date tGMODFINIEPSptpHasta) throws SystemException {
        SQLCount sql = assocOperator152Range1Query(tGMODFINIEPSptpthisFechaIniEPS, tGMODFINIEPSptpDesde, tGMODFINIEPSptpHasta, null);
        return Long.valueOf(new TypedQueryBuilder<FechaIniEPS>(em, FechaIniEPS.class).build(sql).execute().size());
    }


    public SQLCount assocOperator152Range1Query (FechaIniEPS tGMODFINIEPSptpthisFechaIniEPS, Date tGMODFINIEPSptpDesde, Date tGMODFINIEPSptpHasta, SQLSelect superQuery) throws SystemException {
        try {
            SQLCount sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLCount(superQuery, "", FechaIniEPSConstants.CLASS_NAME, FechaIniEPSConstants.CLASS_NAME, null, FechaIniEPSConstants.CLASS_NAME);
            } else {
                sql = new SQLCount("", FechaIniEPSConstants.CLASS_NAME, FechaIniEPSConstants.CLASS_NAME, null, FechaIniEPSConstants.CLASS_NAME);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // FechaIniEPS.Id <> pt_p_thisFechaIniEPS.Id
                    // '<>' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("<>");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(FechaIniEPSConstants.CLASS_NAME + "." + FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSID, Constants.Type.INT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGMODFINIEPSptpthisFechaIniEPS.getFechaIniEPSId(), Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);


            // FechaIniEPS.Desde >= pt_p_Desde
                    // '>=' Comparison
                    SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
                    comparison2.setOperator(">=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand113 = new SQLWhereOperandPathAttribute(FechaIniEPSConstants.CLASS_NAME + "." + FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSDESDE, Constants.Type.DATE.getTypeName());
                    comparison2.setFirstOperand(operand113);
                    
                    // Operand 2
                    comparison2.setSecondOperand(new SQLWhereOperandLiteral(tGMODFINIEPSptpDesde, Constants.Type.DATE.getTypeName()));
                    conjunction.addOperand(comparison2);


            // FechaIniEPS.Hasta <= pt_p_Hasta
                    // '<=' Comparison
                    SQLWhereComparison comparison3 = new SQLWhereComparison(sql);
                    comparison3.setOperator("<=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand114 = new SQLWhereOperandPathAttribute(FechaIniEPSConstants.CLASS_NAME + "." + FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSHASTA, Constants.Type.DATE.getTypeName());
                    comparison3.setFirstOperand(operand114);
                    
                    // Operand 2
                    comparison3.setSecondOperand(new SQLWhereOperandLiteral(tGMODFINIEPSptpHasta, Constants.Type.DATE.getTypeName()));
                    conjunction.addOperand(comparison3);


            // FechaIniEPS.Tramite = pt_p_thisFechaIniEPS.Tramite
                    // '=' Comparison
                    SQLWhereComparison comparison4 = new SQLWhereComparison(sql);
                    comparison4.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand115 = new SQLWhereOperandPathAttribute(FechaIniEPSConstants.CLASS_NAME + "." + FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSTRAMITE, Constants.Type.NAT.getTypeName());
                    comparison4.setFirstOperand(operand115);
                    
                    // Operand 2
                    comparison4.setSecondOperand(new SQLWhereOperandLiteral(tGMODFINIEPSptpthisFechaIniEPS.getFechaIniEPSTramite(), Constants.Type.NAT.getTypeName()));
                    conjunction.addOperand(comparison4);


            // FechaIniEPS.TipoContrato = pt_p_thisFechaIniEPS.TipoContrato
                    // '=' Comparison
                    SQLWhereComparison comparison5 = new SQLWhereComparison(sql);
                    comparison5.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand116 = new SQLWhereOperandPathAttribute(FechaIniEPSConstants.CLASS_NAME + "." + FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSTIPOCONTRATO, Constants.Type.NAT.getTypeName());
                    comparison5.setFirstOperand(operand116);
                    
                    // Operand 2
                    comparison5.setSecondOperand(new SQLWhereOperandLiteral(tGMODFINIEPSptpthisFechaIniEPS.getFechaIniEPSTipoContrato(), Constants.Type.NAT.getTypeName()));
                    conjunction.addOperand(comparison5);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator152, Method: assocOperator152Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator153
	@Override
    public Long assocOperator153(String tGCREARCONTEXENTOptpatrNumContrato) throws SystemException {
        try {
            // COUNT (ContratosExentosFIngreso) WHERE (ContratosExentosFIngreso.NumContrato = pt_p_atrNumContrato)
            return assocOperator153Range1(tGCREARCONTEXENTOptpatrNumContrato);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator153, Method: assocOperator153";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Long assocOperator153Range1 (String tGCREARCONTEXENTOptpatrNumContrato) throws SystemException {
        SQLCount sql = assocOperator153Range1Query(tGCREARCONTEXENTOptpatrNumContrato, null);
        return Long.valueOf(new TypedQueryBuilder<ContratosExentosFIngreso>(em, ContratosExentosFIngreso.class).build(sql).execute().size());
    }


    public SQLCount assocOperator153Range1Query (String tGCREARCONTEXENTOptpatrNumContrato, SQLSelect superQuery) throws SystemException {
        try {
            SQLCount sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLCount(superQuery, "", ContratosExentosFIngresoConstants.CLASS_NAME, ContratosExentosFIngresoConstants.CLASS_NAME, null, ContratosExentosFIngresoConstants.CLASS_NAME);
            } else {
                sql = new SQLCount("", ContratosExentosFIngresoConstants.CLASS_NAME, ContratosExentosFIngresoConstants.CLASS_NAME, null, ContratosExentosFIngresoConstants.CLASS_NAME);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // ContratosExentosFIngreso.NumContrato = pt_p_atrNumContrato
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(ContratosExentosFIngresoConstants.CLASS_NAME + "." + ContratosExentosFIngresoConstants.ATTR_NAME_CONTRATOSEXENTOSFINGRESONUMCONTRATO, Constants.Type.STRING.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGCREARCONTEXENTOptpatrNumContrato, Constants.Type.STRING.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator153, Method: assocOperator153Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator158
	@Override
    public Collection<FrecuenciaAct> assocOperator158() throws SystemException {
        try {
            // FOR ALL FrecuenciaAct DO FrecuenciaAct.modificar(FrecuenciaAct, 0)
            return assocOperator158Range1();
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator158, Method: assocOperator158";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Collection<FrecuenciaAct> assocOperator158Range1 () throws SystemException {

        SQLSelect sql = assocOperator158Range1Query();
        return new TypedQueryBuilder<FrecuenciaAct>(em, FrecuenciaAct.class).build(sql).execute();
    }


    public SQLPopulation assocOperator158Range1Query () throws SystemException {
        try {
            return new SQLPopulation("", FrecuenciaActConstants.CLASS_NAME);
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator158, Method: assocOperator158Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator159
	@Override
    public Collection<FrecuenciaAct> assocOperator159(Long tGFIJARDATOSSINCptNumParametrica) throws SystemException {
        try {
            // FOR ALL FrecuenciaAct WHERE (FrecuenciaAct.Parametrica = ptNumParametrica) DO FrecuenciaAct.fijarSincronizacion(FrecuenciaAct, systemDate(), ptResultado, AGENTFuncionario.Usuario, AGENTFuncionario.drvNombreCompleto)
            return assocOperator159Range1(tGFIJARDATOSSINCptNumParametrica);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator159, Method: assocOperator159";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }



    public Collection<FrecuenciaAct> assocOperator159Range1 (Long tGFIJARDATOSSINCptNumParametrica) throws SystemException {

        SQLSelect sql = assocOperator159Range1Query(tGFIJARDATOSSINCptNumParametrica);
        return new TypedQueryBuilder<FrecuenciaAct>(em, FrecuenciaAct.class).build(sql).execute();
    }


    public SQLPopulation assocOperator159Range1Query (Long tGFIJARDATOSSINCptNumParametrica) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation("", FrecuenciaActConstants.CLASS_NAME);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // FrecuenciaAct.Parametrica = ptNumParametrica
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(FrecuenciaActConstants.CLASS_NAME + "." + FrecuenciaActConstants.ATTR_NAME_FRECUENCIAACTPARAMETRICA, Constants.Type.NAT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(tGFIJARDATOSSINCptNumParametrica, Constants.Type.INT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Assoc.Operator: AssocOperator159, Method: assocOperator159Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }
    // AssocOperator: AssocOperator160
	@Override
    public Collection<FrecuenciaAct> assocOperator160(Long tGFIJARDATOSSINCptNumParametrica) throws SystemException {
        try {
            // FOR ALL FrecuenciaAct WHERE (FrecuenciaAct.Parametrica = ptNumParametrica) DO FrecuenciaAct.fijarSincronizacion(FrecuenciaAct, systemDate(), ptResultado, AGENTUsuarioSistemaExterno.Login, "")
            return assocOperator159(tGFIJARDATOSSINCptNumParametrica);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator160, Method: assocOperator160";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }
    // AssocOperator: AssocOperator161
	@Override
    public Collection<FrecuenciaAct> assocOperator161(Long tGFIJARDATOSSINCptNumParametrica) throws SystemException {
        try {
            // FOR ALL FrecuenciaAct WHERE (FrecuenciaAct.Parametrica = ptNumParametrica) DO FrecuenciaAct.fijarSincronizacion(FrecuenciaAct, systemDate(), ptResultado, "Auto", "")
            return assocOperator159(tGFIJARDATOSSINCptNumParametrica);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: GLOBAL, Assoc.Operator: AssocOperator161, Method: assocOperator161";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    @Override
    public void refreshEntity(Object object) {
        super.refreshEntity(object);
    }
}
