package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.PlanesConstants;
import com.keralty.aeconfiguraciones.global.ProductosConstants;
import com.keralty.aeconfiguraciones.global.TipoIVAConstants;
import com.keralty.aeconfiguraciones.global.UserFunctions;
import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.persistence.Productos;
import com.keralty.aeconfiguraciones.persistence.TipoIVA;
import com.keralty.aeconfiguraciones.sqlutils.SQLCount;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereConjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereDisjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandOVParameter;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandOVParameterAttribute;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandPathAttribute;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandPathInstance;
import com.keralty.aeconfiguraciones.sqlutils.TypedQueryBuilder;
import com.keralty.aeconfiguraciones.utils.Agent;
import jakarta.inject.Inject;
import java.sql.Date;
import java.util.Collection;
import java.util.List;

public class TipoIVAJpaRepositoryImpl extends AbstractRepository implements TipoIVAJpaRepositoryCustom {

    @Inject
    public TipoIVAJpaRepositoryImpl(Agent agent, UserFunctions userFunctionsSrv) {
        super(agent, userFunctionsSrv);
    }

    @Override
    public List<TipoIVA> executeQuery(String jpqlQuery) {
        return getQueryBuilder().build(jpqlQuery).execute();
    }
    
    @Override
    public List<TipoIVA> executeQuery(String jpqlQuery, List<SQLParam> params) {
        return getQueryBuilder().build(jpqlQuery).setParameters(params).execute();
    }
    
    public Collection<TipoIVA> executeQuery(SQLSelect select) {
        return getQueryBuilder().build(select).execute();
    }

    private TypedQueryBuilder<TipoIVA> getQueryBuilder() {
        return new TypedQueryBuilder<>(em, TipoIVA.class);
    }

    @Override	
    public List<TipoIVA> solveQuery(SQLSelect select) {
        return solveQuery(select, 0, 0);
    }

    @Override
    public List<TipoIVA> solveQuery(SQLSelect select, int pageSize, int pageNumber) {
        return getQueryBuilder().build(select, pageSize, pageNumber).execute();
    }

    // AssocOperator: AssocOperator000
	@Override
    public Long assocOperator000(Productos tipoIVAcrearpagrProductoT, Planes tipoIVAcrearpagrPlanT, Date tipoIVAcrearpatrFechaInicio, Date tipoIVAcrearpatrFechaFin) throws SystemException {
        try {
            // COUNT (pagrProductoT.TiposIVA) WHERE (pagrProductoT.TiposIVA.PlanT = pagrPlanT AND ((pagrProductoT.TiposIVA.FechaInicio >= patrFechaInicio AND pagrProductoT.TiposIVA.FechaInicio <= patrFechaFin) OR (pagrProductoT.TiposIVA.FechaFin >= patrFechaInicio AND pagrProductoT.TiposIVA.FechaFin <= patrFechaFin)))
            return assocOperator000Range1(tipoIVAcrearpagrProductoT, tipoIVAcrearpagrPlanT, tipoIVAcrearpatrFechaInicio, tipoIVAcrearpatrFechaFin);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: TipoIVA, Assoc.Operator: AssocOperator000, Method: assocOperator000";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator000
	@Override
    public Long assocOperator000Range1 (Productos tipoIVAcrearpagrProductoT, Planes tipoIVAcrearpagrPlanT, Date tipoIVAcrearpatrFechaInicio, Date tipoIVAcrearpatrFechaFin) throws SystemException {
        SQLCount sql = assocOperator000Range1Query(tipoIVAcrearpagrProductoT, tipoIVAcrearpagrPlanT, tipoIVAcrearpatrFechaInicio, tipoIVAcrearpatrFechaFin, null);
        return Long.valueOf(solveCountQuery(sql));
    }

    // Range Query 1 for AssocOperator AssocOperator000
	@Override
    public SQLCount assocOperator000Range1Query (Productos tipoIVAcrearpagrProductoT, Planes tipoIVAcrearpagrPlanT, Date tipoIVAcrearpatrFechaInicio, Date tipoIVAcrearpatrFechaFin, SQLSelect superQuery) throws SystemException {
        try {
            SQLCount sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLCount(superQuery, TipoIVAConstants.CLASS_NAME, TipoIVAConstants.ARG_NAME_CREAR_TIPOIVACREARPAGRPRODUCTOT + "." + ProductosConstants.ROLE_NAME_TIPOSIVA, ProductosConstants.CLASS_NAME, tipoIVAcrearpagrProductoT.getOid(), TipoIVAConstants.ARG_NAME_CREAR_TIPOIVACREARPAGRPRODUCTOT);
            } else {
                sql = new SQLCount(TipoIVAConstants.CLASS_NAME, TipoIVAConstants.ARG_NAME_CREAR_TIPOIVACREARPAGRPRODUCTOT + "." + ProductosConstants.ROLE_NAME_TIPOSIVA, ProductosConstants.CLASS_NAME, tipoIVAcrearpagrProductoT.getOid(), TipoIVAConstants.ARG_NAME_CREAR_TIPOIVACREARPAGRPRODUCTOT);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // pagrProductoT.TiposIVA.PlanT = pagrPlanT
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandOVParameter operand112 = new SQLWhereOperandOVParameter(TipoIVAConstants.ARG_NAME_CREAR_TIPOIVACREARPAGRPRODUCTOT + "." + ProductosConstants.ROLE_NAME_TIPOSIVA + "." + TipoIVAConstants.ROLE_NAME_PLANT, ProductosConstants.CLASS_NAME, PlanesConstants.CLASS_NAME, tipoIVAcrearpagrProductoT.getOid(), TipoIVAConstants.ARG_NAME_CREAR_TIPOIVACREARPAGRPRODUCTOT);
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(TipoIVAConstants.ARG_NAME_CREAR_TIPOIVACREARPAGRPLANT, PlanesConstants.CLASS_NAME, PlanesConstants.CLASS_NAME, tipoIVAcrearpagrPlanT.getOid(), TipoIVAConstants.ARG_NAME_CREAR_TIPOIVACREARPAGRPLANT);
                    comparison1.setSecondOperand(operand223);
                    conjunction.addOperand(comparison1);


            // ((pagrProductoT.TiposIVA.FechaInicio >= patrFechaInicio AND pagrProductoT.TiposIVA.FechaInicio <= patrFechaFin) OR (pagrProductoT.TiposIVA.FechaFin >= patrFechaInicio AND pagrProductoT.TiposIVA.FechaFin <= patrFechaFin))
                {
                    // 'or' Logical operator
                    SQLWhereDisjunction comparison2 = new SQLWhereDisjunction();
                
                    // Operand 1
                    {
                        // 'and' Logical operator
                        SQLWhereConjunction comparison3 = new SQLWhereConjunction();
                    
                        // Operand 1
                            // '>=' Comparison
                            SQLWhereComparison comparison4 = new SQLWhereComparison(sql);
                            comparison4.setOperator(">=");
                            
                            // Operand 1
                            SQLWhereOperandOVParameterAttribute operand115 = new SQLWhereOperandOVParameterAttribute(TipoIVAConstants.ARG_NAME_CREAR_TIPOIVACREARPAGRPRODUCTOT + "." + ProductosConstants.ROLE_NAME_TIPOSIVA + "." + TipoIVAConstants.ATTR_NAME_TIPOIVAFECHAINICIO, ProductosConstants.CLASS_NAME, tipoIVAcrearpagrProductoT.getOid(), TipoIVAConstants.ARG_NAME_CREAR_TIPOIVACREARPAGRPRODUCTOT, Constants.Type.DATE.getTypeName());
                            comparison4.setFirstOperand(operand115);
                            
                            // Operand 2
                            comparison4.setSecondOperand(new SQLWhereOperandLiteral(tipoIVAcrearpatrFechaInicio, Constants.Type.DATE.getTypeName()));
                            comparison3.addOperand(comparison4);
                    
                        // Operand 2
                            // '<=' Comparison
                            SQLWhereComparison comparison6 = new SQLWhereComparison(sql);
                            comparison6.setOperator("<=");
                            
                            // Operand 1
                            SQLWhereOperandOVParameterAttribute operand117 = new SQLWhereOperandOVParameterAttribute(TipoIVAConstants.ARG_NAME_CREAR_TIPOIVACREARPAGRPRODUCTOT + "." + ProductosConstants.ROLE_NAME_TIPOSIVA + "." + TipoIVAConstants.ATTR_NAME_TIPOIVAFECHAINICIO, ProductosConstants.CLASS_NAME, tipoIVAcrearpagrProductoT.getOid(), TipoIVAConstants.ARG_NAME_CREAR_TIPOIVACREARPAGRPRODUCTOT, Constants.Type.DATE.getTypeName());
                            comparison6.setFirstOperand(operand117);
                            
                            // Operand 2
                            comparison6.setSecondOperand(new SQLWhereOperandLiteral(tipoIVAcrearpatrFechaFin, Constants.Type.DATE.getTypeName()));
                            comparison3.addOperand(comparison6);
                        
                        comparison2.addOperand(comparison3);
                    }
                
                    // Operand 2
                    {
                        // 'and' Logical operator
                        SQLWhereConjunction comparison7 = new SQLWhereConjunction();
                    
                        // Operand 1
                            // '>=' Comparison
                            SQLWhereComparison comparison8 = new SQLWhereComparison(sql);
                            comparison8.setOperator(">=");
                            
                            // Operand 1
                            SQLWhereOperandOVParameterAttribute operand119 = new SQLWhereOperandOVParameterAttribute(TipoIVAConstants.ARG_NAME_CREAR_TIPOIVACREARPAGRPRODUCTOT + "." + ProductosConstants.ROLE_NAME_TIPOSIVA + "." + TipoIVAConstants.ATTR_NAME_TIPOIVAFECHAFIN, ProductosConstants.CLASS_NAME, tipoIVAcrearpagrProductoT.getOid(), TipoIVAConstants.ARG_NAME_CREAR_TIPOIVACREARPAGRPRODUCTOT, Constants.Type.DATE.getTypeName());
                            comparison8.setFirstOperand(operand119);
                            
                            // Operand 2
                            comparison8.setSecondOperand(new SQLWhereOperandLiteral(tipoIVAcrearpatrFechaInicio, Constants.Type.DATE.getTypeName()));
                            comparison7.addOperand(comparison8);
                    
                        // Operand 2
                            // '<=' Comparison
                            SQLWhereComparison comparison10 = new SQLWhereComparison(sql);
                            comparison10.setOperator("<=");
                            
                            // Operand 1
                            SQLWhereOperandOVParameterAttribute operand1111 = new SQLWhereOperandOVParameterAttribute(TipoIVAConstants.ARG_NAME_CREAR_TIPOIVACREARPAGRPRODUCTOT + "." + ProductosConstants.ROLE_NAME_TIPOSIVA + "." + TipoIVAConstants.ATTR_NAME_TIPOIVAFECHAFIN, ProductosConstants.CLASS_NAME, tipoIVAcrearpagrProductoT.getOid(), TipoIVAConstants.ARG_NAME_CREAR_TIPOIVACREARPAGRPRODUCTOT, Constants.Type.DATE.getTypeName());
                            comparison10.setFirstOperand(operand1111);
                            
                            // Operand 2
                            comparison10.setSecondOperand(new SQLWhereOperandLiteral(tipoIVAcrearpatrFechaFin, Constants.Type.DATE.getTypeName()));
                            comparison7.addOperand(comparison10);
                        
                        comparison2.addOperand(comparison7);
                    }
                    
                    conjunction.addOperand(comparison2);
                }

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: TipoIVA, Assoc.Operator: AssocOperator000, Method: assocOperator000Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator001
	@Override
    public Long assocOperator001(Productos tipoIVAcrearpagrProductoT, Planes tipoIVAcrearpagrPlanT, Date tipoIVAcrearpatrFechaInicio, Date tipoIVAcrearpatrFechaFin) throws SystemException {
        try {
            // COUNT (pagrProductoT.TiposIVA) WHERE (pagrProductoT.TiposIVA.PlanT = pagrPlanT AND pagrProductoT.TiposIVA.FechaInicio < patrFechaInicio AND pagrProductoT.TiposIVA.FechaFin > patrFechaFin)
            return assocOperator001Range1(tipoIVAcrearpagrProductoT, tipoIVAcrearpagrPlanT, tipoIVAcrearpatrFechaInicio, tipoIVAcrearpatrFechaFin);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: TipoIVA, Assoc.Operator: AssocOperator001, Method: assocOperator001";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator001
	@Override
    public Long assocOperator001Range1 (Productos tipoIVAcrearpagrProductoT, Planes tipoIVAcrearpagrPlanT, Date tipoIVAcrearpatrFechaInicio, Date tipoIVAcrearpatrFechaFin) throws SystemException {
        SQLCount sql = assocOperator001Range1Query(tipoIVAcrearpagrProductoT, tipoIVAcrearpagrPlanT, tipoIVAcrearpatrFechaInicio, tipoIVAcrearpatrFechaFin, null);
        return Long.valueOf(solveCountQuery(sql));
    }

    // Range Query 1 for AssocOperator AssocOperator001
	@Override
    public SQLCount assocOperator001Range1Query (Productos tipoIVAcrearpagrProductoT, Planes tipoIVAcrearpagrPlanT, Date tipoIVAcrearpatrFechaInicio, Date tipoIVAcrearpatrFechaFin, SQLSelect superQuery) throws SystemException {
        try {
            SQLCount sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLCount(superQuery, TipoIVAConstants.CLASS_NAME, TipoIVAConstants.ARG_NAME_CREAR_TIPOIVACREARPAGRPRODUCTOT + "." + ProductosConstants.ROLE_NAME_TIPOSIVA, ProductosConstants.CLASS_NAME, tipoIVAcrearpagrProductoT.getOid(), TipoIVAConstants.ARG_NAME_CREAR_TIPOIVACREARPAGRPRODUCTOT);
            } else {
                sql = new SQLCount(TipoIVAConstants.CLASS_NAME, TipoIVAConstants.ARG_NAME_CREAR_TIPOIVACREARPAGRPRODUCTOT + "." + ProductosConstants.ROLE_NAME_TIPOSIVA, ProductosConstants.CLASS_NAME, tipoIVAcrearpagrProductoT.getOid(), TipoIVAConstants.ARG_NAME_CREAR_TIPOIVACREARPAGRPRODUCTOT);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // pagrProductoT.TiposIVA.PlanT = pagrPlanT
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandOVParameter operand112 = new SQLWhereOperandOVParameter(TipoIVAConstants.ARG_NAME_CREAR_TIPOIVACREARPAGRPRODUCTOT + "." + ProductosConstants.ROLE_NAME_TIPOSIVA + "." + TipoIVAConstants.ROLE_NAME_PLANT, ProductosConstants.CLASS_NAME, PlanesConstants.CLASS_NAME, tipoIVAcrearpagrProductoT.getOid(), TipoIVAConstants.ARG_NAME_CREAR_TIPOIVACREARPAGRPRODUCTOT);
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(TipoIVAConstants.ARG_NAME_CREAR_TIPOIVACREARPAGRPLANT, PlanesConstants.CLASS_NAME, PlanesConstants.CLASS_NAME, tipoIVAcrearpagrPlanT.getOid(), TipoIVAConstants.ARG_NAME_CREAR_TIPOIVACREARPAGRPLANT);
                    comparison1.setSecondOperand(operand223);
                    conjunction.addOperand(comparison1);


            // pagrProductoT.TiposIVA.FechaInicio < patrFechaInicio
                    // '<' Comparison
                    SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
                    comparison2.setOperator("<");
                    
                    // Operand 1
                    SQLWhereOperandOVParameterAttribute operand113 = new SQLWhereOperandOVParameterAttribute(TipoIVAConstants.ARG_NAME_CREAR_TIPOIVACREARPAGRPRODUCTOT + "." + ProductosConstants.ROLE_NAME_TIPOSIVA + "." + TipoIVAConstants.ATTR_NAME_TIPOIVAFECHAINICIO, ProductosConstants.CLASS_NAME, tipoIVAcrearpagrProductoT.getOid(), TipoIVAConstants.ARG_NAME_CREAR_TIPOIVACREARPAGRPRODUCTOT, Constants.Type.DATE.getTypeName());
                    comparison2.setFirstOperand(operand113);
                    
                    // Operand 2
                    comparison2.setSecondOperand(new SQLWhereOperandLiteral(tipoIVAcrearpatrFechaInicio, Constants.Type.DATE.getTypeName()));
                    conjunction.addOperand(comparison2);


            // pagrProductoT.TiposIVA.FechaFin > patrFechaFin
                    // '>' Comparison
                    SQLWhereComparison comparison3 = new SQLWhereComparison(sql);
                    comparison3.setOperator(">");
                    
                    // Operand 1
                    SQLWhereOperandOVParameterAttribute operand114 = new SQLWhereOperandOVParameterAttribute(TipoIVAConstants.ARG_NAME_CREAR_TIPOIVACREARPAGRPRODUCTOT + "." + ProductosConstants.ROLE_NAME_TIPOSIVA + "." + TipoIVAConstants.ATTR_NAME_TIPOIVAFECHAFIN, ProductosConstants.CLASS_NAME, tipoIVAcrearpagrProductoT.getOid(), TipoIVAConstants.ARG_NAME_CREAR_TIPOIVACREARPAGRPRODUCTOT, Constants.Type.DATE.getTypeName());
                    comparison3.setFirstOperand(operand114);
                    
                    // Operand 2
                    comparison3.setSecondOperand(new SQLWhereOperandLiteral(tipoIVAcrearpatrFechaFin, Constants.Type.DATE.getTypeName()));
                    conjunction.addOperand(comparison3);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: TipoIVA, Assoc.Operator: AssocOperator001, Method: assocOperator001Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator002
	@Override
    public Long assocOperator002(TipoIVA instance, TipoIVA tipoIVAmodificarpthisTipoIVA, Date tipoIVAmodificarpFechaInicio, Date tipoIVAmodificarpFechaFin) throws SystemException {
        try {
            // COUNT (ProductoT.TiposIVA) WHERE (ProductoT.TiposIVA <> pthisTipoIVA AND ProductoT.TiposIVA.PlanT = PlanT AND ((ProductoT.TiposIVA.FechaInicio >= pFechaInicio AND ProductoT.TiposIVA.FechaInicio <= pFechaFin) OR (ProductoT.TiposIVA.FechaFin >= pFechaInicio AND ProductoT.TiposIVA.FechaFin <= pFechaFin)))
            return assocOperator002Range1(instance, tipoIVAmodificarpthisTipoIVA, tipoIVAmodificarpFechaInicio, tipoIVAmodificarpFechaFin);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: TipoIVA, Assoc.Operator: AssocOperator002, Method: assocOperator002";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator002
	@Override
    public Long assocOperator002Range1 (TipoIVA instance, TipoIVA tipoIVAmodificarpthisTipoIVA, Date tipoIVAmodificarpFechaInicio, Date tipoIVAmodificarpFechaFin) throws SystemException {
        SQLCount sql = assocOperator002Range1Query(instance, tipoIVAmodificarpthisTipoIVA, tipoIVAmodificarpFechaInicio, tipoIVAmodificarpFechaFin);
        return Long.valueOf(solveCountQuery(sql));
    }

    // Range Query 1 for AssocOperator AssocOperator002
	@Override
    public SQLCount assocOperator002Range1Query (TipoIVA instance, TipoIVA tipoIVAmodificarpthisTipoIVA, Date tipoIVAmodificarpFechaInicio, Date tipoIVAmodificarpFechaFin) throws SystemException {
        try {
            // The path starts with a role
            SQLCount sql = new SQLCount(TipoIVAConstants.CLASS_NAME, TipoIVAConstants.ROLE_NAME_PRODUCTOT + "." + ProductosConstants.ROLE_NAME_TIPOSIVA, instance.getOid());
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // ProductoT.TiposIVA <> pthisTipoIVA
                    // '<>' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("<>");
                    
                    // Operand 1
                    SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(TipoIVAConstants.ROLE_NAME_PRODUCTOT + "." + ProductosConstants.ROLE_NAME_TIPOSIVA, TipoIVAConstants.CLASS_NAME);
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(TipoIVAConstants.ARG_NAME_MODIFICAR_TIPOIVAMODIFICARPTHISTIPOIVA, TipoIVAConstants.CLASS_NAME, TipoIVAConstants.CLASS_NAME, tipoIVAmodificarpthisTipoIVA.getOid(), TipoIVAConstants.ARG_NAME_MODIFICAR_TIPOIVAMODIFICARPTHISTIPOIVA);
                    comparison1.setSecondOperand(operand223);
                    conjunction.addOperand(comparison1);


            // ProductoT.TiposIVA.PlanT = PlanT
                    // '=' Comparison
                    SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
                    comparison2.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathInstance operand113 = new SQLWhereOperandPathInstance(TipoIVAConstants.ROLE_NAME_PRODUCTOT + "." + ProductosConstants.ROLE_NAME_TIPOSIVA + "." + TipoIVAConstants.ROLE_NAME_PLANT, PlanesConstants.CLASS_NAME);
                    comparison2.setFirstOperand(operand113);
                    
                    // Operand 2
                    SQLWhereOperandOVParameter operand224 = new SQLWhereOperandOVParameter(TipoIVAConstants.ROLE_NAME_PLANT, PlanesConstants.CLASS_NAME, PlanesConstants.CLASS_NAME, instance.getPlanT().getOid(), TipoIVAConstants.ROLE_NAME_PLANT);
                    comparison2.setSecondOperand(operand224);
                    conjunction.addOperand(comparison2);


            // ((ProductoT.TiposIVA.FechaInicio >= pFechaInicio AND ProductoT.TiposIVA.FechaInicio <= pFechaFin) OR (ProductoT.TiposIVA.FechaFin >= pFechaInicio AND ProductoT.TiposIVA.FechaFin <= pFechaFin))
                {
                    // 'or' Logical operator
                    SQLWhereDisjunction comparison3 = new SQLWhereDisjunction();
                
                    // Operand 1
                    {
                        // 'and' Logical operator
                        SQLWhereConjunction comparison4 = new SQLWhereConjunction();
                    
                        // Operand 1
                            // '>=' Comparison
                            SQLWhereComparison comparison5 = new SQLWhereComparison(sql);
                            comparison5.setOperator(">=");
                            
                            // Operand 1
                            SQLWhereOperandPathAttribute operand116 = new SQLWhereOperandPathAttribute(TipoIVAConstants.ROLE_NAME_PRODUCTOT + "." + ProductosConstants.ROLE_NAME_TIPOSIVA + "." + TipoIVAConstants.ATTR_NAME_TIPOIVAFECHAINICIO, Constants.Type.DATE.getTypeName());
                            comparison5.setFirstOperand(operand116);
                            
                            // Operand 2
                            comparison5.setSecondOperand(new SQLWhereOperandLiteral(tipoIVAmodificarpFechaInicio, Constants.Type.DATE.getTypeName()));
                            comparison4.addOperand(comparison5);
                    
                        // Operand 2
                            // '<=' Comparison
                            SQLWhereComparison comparison7 = new SQLWhereComparison(sql);
                            comparison7.setOperator("<=");
                            
                            // Operand 1
                            SQLWhereOperandPathAttribute operand118 = new SQLWhereOperandPathAttribute(TipoIVAConstants.ROLE_NAME_PRODUCTOT + "." + ProductosConstants.ROLE_NAME_TIPOSIVA + "." + TipoIVAConstants.ATTR_NAME_TIPOIVAFECHAINICIO, Constants.Type.DATE.getTypeName());
                            comparison7.setFirstOperand(operand118);
                            
                            // Operand 2
                            comparison7.setSecondOperand(new SQLWhereOperandLiteral(tipoIVAmodificarpFechaFin, Constants.Type.DATE.getTypeName()));
                            comparison4.addOperand(comparison7);
                        
                        comparison3.addOperand(comparison4);
                    }
                
                    // Operand 2
                    {
                        // 'and' Logical operator
                        SQLWhereConjunction comparison8 = new SQLWhereConjunction();
                    
                        // Operand 1
                            // '>=' Comparison
                            SQLWhereComparison comparison9 = new SQLWhereComparison(sql);
                            comparison9.setOperator(">=");
                            
                            // Operand 1
                            SQLWhereOperandPathAttribute operand1110 = new SQLWhereOperandPathAttribute(TipoIVAConstants.ROLE_NAME_PRODUCTOT + "." + ProductosConstants.ROLE_NAME_TIPOSIVA + "." + TipoIVAConstants.ATTR_NAME_TIPOIVAFECHAFIN, Constants.Type.DATE.getTypeName());
                            comparison9.setFirstOperand(operand1110);
                            
                            // Operand 2
                            comparison9.setSecondOperand(new SQLWhereOperandLiteral(tipoIVAmodificarpFechaInicio, Constants.Type.DATE.getTypeName()));
                            comparison8.addOperand(comparison9);
                    
                        // Operand 2
                            // '<=' Comparison
                            SQLWhereComparison comparison11 = new SQLWhereComparison(sql);
                            comparison11.setOperator("<=");
                            
                            // Operand 1
                            SQLWhereOperandPathAttribute operand1112 = new SQLWhereOperandPathAttribute(TipoIVAConstants.ROLE_NAME_PRODUCTOT + "." + ProductosConstants.ROLE_NAME_TIPOSIVA + "." + TipoIVAConstants.ATTR_NAME_TIPOIVAFECHAFIN, Constants.Type.DATE.getTypeName());
                            comparison11.setFirstOperand(operand1112);
                            
                            // Operand 2
                            comparison11.setSecondOperand(new SQLWhereOperandLiteral(tipoIVAmodificarpFechaFin, Constants.Type.DATE.getTypeName()));
                            comparison8.addOperand(comparison11);
                        
                        comparison3.addOperand(comparison8);
                    }
                    
                    conjunction.addOperand(comparison3);
                }

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: TipoIVA, Assoc.Operator: AssocOperator002, Method: assocOperator002Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator003
	@Override
    public Long assocOperator003(TipoIVA instance, TipoIVA tipoIVAmodificarpthisTipoIVA, Date tipoIVAmodificarpFechaInicio, Date tipoIVAmodificarpFechaFin) throws SystemException {
        try {
            // COUNT (ProductoT.TiposIVA) WHERE (ProductoT.TiposIVA <> pthisTipoIVA AND ProductoT.TiposIVA.PlanT = PlanT AND ProductoT.TiposIVA.FechaInicio < pFechaInicio AND ProductoT.TiposIVA.FechaFin > pFechaFin)
            return assocOperator003Range1(instance, tipoIVAmodificarpthisTipoIVA, tipoIVAmodificarpFechaInicio, tipoIVAmodificarpFechaFin);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: TipoIVA, Assoc.Operator: AssocOperator003, Method: assocOperator003";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator003
	@Override
    public Long assocOperator003Range1 (TipoIVA instance, TipoIVA tipoIVAmodificarpthisTipoIVA, Date tipoIVAmodificarpFechaInicio, Date tipoIVAmodificarpFechaFin) throws SystemException {
        SQLCount sql = assocOperator003Range1Query(instance, tipoIVAmodificarpthisTipoIVA, tipoIVAmodificarpFechaInicio, tipoIVAmodificarpFechaFin);
        return Long.valueOf(solveCountQuery(sql));
    }

    // Range Query 1 for AssocOperator AssocOperator003
	@Override
    public SQLCount assocOperator003Range1Query (TipoIVA instance, TipoIVA tipoIVAmodificarpthisTipoIVA, Date tipoIVAmodificarpFechaInicio, Date tipoIVAmodificarpFechaFin) throws SystemException {
        try {
            // The path starts with a role
            SQLCount sql = new SQLCount(TipoIVAConstants.CLASS_NAME, TipoIVAConstants.ROLE_NAME_PRODUCTOT + "." + ProductosConstants.ROLE_NAME_TIPOSIVA, instance.getOid());
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // ProductoT.TiposIVA <> pthisTipoIVA
                    // '<>' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("<>");
                    
                    // Operand 1
                    SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(TipoIVAConstants.ROLE_NAME_PRODUCTOT + "." + ProductosConstants.ROLE_NAME_TIPOSIVA, TipoIVAConstants.CLASS_NAME);
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(TipoIVAConstants.ARG_NAME_MODIFICAR_TIPOIVAMODIFICARPTHISTIPOIVA, TipoIVAConstants.CLASS_NAME, TipoIVAConstants.CLASS_NAME, tipoIVAmodificarpthisTipoIVA.getOid(), TipoIVAConstants.ARG_NAME_MODIFICAR_TIPOIVAMODIFICARPTHISTIPOIVA);
                    comparison1.setSecondOperand(operand223);
                    conjunction.addOperand(comparison1);


            // ProductoT.TiposIVA.PlanT = PlanT
                    // '=' Comparison
                    SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
                    comparison2.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathInstance operand113 = new SQLWhereOperandPathInstance(TipoIVAConstants.ROLE_NAME_PRODUCTOT + "." + ProductosConstants.ROLE_NAME_TIPOSIVA + "." + TipoIVAConstants.ROLE_NAME_PLANT, PlanesConstants.CLASS_NAME);
                    comparison2.setFirstOperand(operand113);
                    
                    // Operand 2
                    SQLWhereOperandOVParameter operand224 = new SQLWhereOperandOVParameter(TipoIVAConstants.ROLE_NAME_PLANT, PlanesConstants.CLASS_NAME, PlanesConstants.CLASS_NAME, instance.getPlanT().getOid(), TipoIVAConstants.ROLE_NAME_PLANT);
                    comparison2.setSecondOperand(operand224);
                    conjunction.addOperand(comparison2);


            // ProductoT.TiposIVA.FechaInicio < pFechaInicio
                    // '<' Comparison
                    SQLWhereComparison comparison3 = new SQLWhereComparison(sql);
                    comparison3.setOperator("<");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand114 = new SQLWhereOperandPathAttribute(TipoIVAConstants.ROLE_NAME_PRODUCTOT + "." + ProductosConstants.ROLE_NAME_TIPOSIVA + "." + TipoIVAConstants.ATTR_NAME_TIPOIVAFECHAINICIO, Constants.Type.DATE.getTypeName());
                    comparison3.setFirstOperand(operand114);
                    
                    // Operand 2
                    comparison3.setSecondOperand(new SQLWhereOperandLiteral(tipoIVAmodificarpFechaInicio, Constants.Type.DATE.getTypeName()));
                    conjunction.addOperand(comparison3);


            // ProductoT.TiposIVA.FechaFin > pFechaFin
                    // '>' Comparison
                    SQLWhereComparison comparison4 = new SQLWhereComparison(sql);
                    comparison4.setOperator(">");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand115 = new SQLWhereOperandPathAttribute(TipoIVAConstants.ROLE_NAME_PRODUCTOT + "." + ProductosConstants.ROLE_NAME_TIPOSIVA + "." + TipoIVAConstants.ATTR_NAME_TIPOIVAFECHAFIN, Constants.Type.DATE.getTypeName());
                    comparison4.setFirstOperand(operand115);
                    
                    // Operand 2
                    comparison4.setSecondOperand(new SQLWhereOperandLiteral(tipoIVAmodificarpFechaFin, Constants.Type.DATE.getTypeName()));
                    conjunction.addOperand(comparison4);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: TipoIVA, Assoc.Operator: AssocOperator003, Method: assocOperator003Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    @Override
    public void refreshEntity(Object object) {
        super.refreshEntity(object);
    }
}
