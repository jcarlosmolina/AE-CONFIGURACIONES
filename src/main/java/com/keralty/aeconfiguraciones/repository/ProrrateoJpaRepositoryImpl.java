package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.DominioConstants;
import com.keralty.aeconfiguraciones.global.ProrrateoConstants;
import com.keralty.aeconfiguraciones.global.UserFunctions;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.Prorrateo;
import com.keralty.aeconfiguraciones.sqlutils.SQLExists;
import com.keralty.aeconfiguraciones.sqlutils.SQLMax;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereConjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandOVParameterAttribute;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandPathAttribute;
import com.keralty.aeconfiguraciones.sqlutils.TypedQueryBuilder;
import com.keralty.aeconfiguraciones.utils.Agent;
import jakarta.inject.Inject;
import java.util.Collection;
import java.util.List;
import org.slf4j.LoggerFactory;

public class ProrrateoJpaRepositoryImpl extends AbstractRepository implements ProrrateoJpaRepositoryCustom {

    @Inject
    public ProrrateoJpaRepositoryImpl(Agent agent, UserFunctions userFunctionsSrv) {
        super(agent, userFunctionsSrv);
    }

    @Override
    public List<Prorrateo> executeQuery(String jpqlQuery) {
        return getQueryBuilder().build(jpqlQuery).execute();
    }
    
    @Override
    public List<Prorrateo> executeQuery(String jpqlQuery, List<SQLParam> params) {
        return getQueryBuilder().build(jpqlQuery).setParameters(params).execute();
    }
    
    public Collection<Prorrateo> executeQuery(SQLSelect select) {
        return getQueryBuilder().build(select).execute();
    }

    private TypedQueryBuilder<Prorrateo> getQueryBuilder() {
        return new TypedQueryBuilder<>(em, Prorrateo.class);
    }

    @Override	
    public List<Prorrateo> solveQuery(SQLSelect select) {
        return solveQuery(select, 0, 0);
    }

    @Override
    public List<Prorrateo> solveQuery(SQLSelect select, int pageSize, int pageNumber) {
        return getQueryBuilder().build(select, pageSize, pageNumber).execute();
    }

    @Override
    public Long nextProrrateoidProrrateo(Long dominioNumero) {
        try {
            SQLMax sql = new SQLMax(null, ProrrateoConstants.CLASS_NAME, ProrrateoConstants.CLASS_NAME + "." + ProrrateoConstants.ATTR_FIELD_PRORRATEOIDPRORRATEO);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(ProrrateoConstants.ROLE_NAME_DOMINIO + "." + DominioConstants.ATTR_FIELD_DOMINIONUMERO, "Autonumerico"));
            comparison1.setOperator("=");
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(dominioNumero, "Autonumerico"));
            conjunction.addComparison(comparison1);

            sql.getWherePart().addConjunction(conjunction);

            Long tempResult = new TypedQueryBuilder<>(em, Long.class).build(sql).getSingleResult();
            return (tempResult != null ? tempResult + 1L : 1L);
        } catch (Exception e) {
            LoggerFactory.getLogger(Prorrateo.class).error(e.getMessage());
            return 1L;
        }
    }

    // AssocOperator: AssocOperator000
	@Override
    public Boolean assocOperator000(Dominio prorrateocrearpagrDominio, Long prorrateocrearpatrDia) throws SystemException {
        try {
            // EXIST (pagrDominio.Prorrateos) WHERE (pagrDominio.Prorrateos.Dia = patrDia)
            return assocOperator000Range1(prorrateocrearpagrDominio, prorrateocrearpatrDia);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Prorrateo, Assoc.Operator: AssocOperator000, Method: assocOperator000";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator000
	@Override
    public Boolean assocOperator000Range1 (Dominio prorrateocrearpagrDominio, Long prorrateocrearpatrDia) throws SystemException {
        SQLExists sql = assocOperator000Range1Query(prorrateocrearpagrDominio, prorrateocrearpatrDia, null);
        return !new TypedQueryBuilder<Prorrateo>(em, Prorrateo.class).build(sql).execute().isEmpty();
    }

    // Range Query 1 for AssocOperator AssocOperator000
	@Override
    public SQLExists assocOperator000Range1Query (Dominio prorrateocrearpagrDominio, Long prorrateocrearpatrDia, SQLSelect superQuery) throws SystemException {
        try {
            SQLExists sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLExists(superQuery, ProrrateoConstants.CLASS_NAME, ProrrateoConstants.ARG_NAME_CREAR_PRORRATEOCREARPAGRDOMINIO + "." + DominioConstants.ROLE_NAME_PRORRATEOS, DominioConstants.CLASS_NAME, prorrateocrearpagrDominio.getOid(), ProrrateoConstants.ARG_NAME_CREAR_PRORRATEOCREARPAGRDOMINIO);
            } else {
                sql = new SQLExists(ProrrateoConstants.CLASS_NAME, ProrrateoConstants.ARG_NAME_CREAR_PRORRATEOCREARPAGRDOMINIO + "." + DominioConstants.ROLE_NAME_PRORRATEOS, DominioConstants.CLASS_NAME, prorrateocrearpagrDominio.getOid(), ProrrateoConstants.ARG_NAME_CREAR_PRORRATEOCREARPAGRDOMINIO);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // pagrDominio.Prorrateos.Dia = patrDia
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandOVParameterAttribute operand112 = new SQLWhereOperandOVParameterAttribute(ProrrateoConstants.ARG_NAME_CREAR_PRORRATEOCREARPAGRDOMINIO + "." + DominioConstants.ROLE_NAME_PRORRATEOS + "." + ProrrateoConstants.ATTR_NAME_PRORRATEODIA, DominioConstants.CLASS_NAME, prorrateocrearpagrDominio.getOid(), ProrrateoConstants.ARG_NAME_CREAR_PRORRATEOCREARPAGRDOMINIO, Constants.Type.NAT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(prorrateocrearpatrDia, Constants.Type.NAT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Prorrateo, Assoc.Operator: AssocOperator000, Method: assocOperator000Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator001
	@Override
    public Boolean assocOperator001(Prorrateo instance, Long prorrateomodificarpDia) throws SystemException {
        try {
            // EXIST (Dominio.Prorrateos) WHERE (Dominio.Prorrateos.Dia = pDia)
            return assocOperator001Range1(instance, prorrateomodificarpDia);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Prorrateo, Assoc.Operator: AssocOperator001, Method: assocOperator001";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator001
	@Override
    public Boolean assocOperator001Range1 (Prorrateo instance, Long prorrateomodificarpDia) throws SystemException {
        SQLExists sql = assocOperator001Range1Query(instance, prorrateomodificarpDia);
        return !new TypedQueryBuilder<Prorrateo>(em, Prorrateo.class).build(sql).execute().isEmpty();
    }

    // Range Query 1 for AssocOperator AssocOperator001
	@Override
    public SQLExists assocOperator001Range1Query (Prorrateo instance, Long prorrateomodificarpDia) throws SystemException {
        try {
            // The path starts with a role
            SQLExists sql = new SQLExists(ProrrateoConstants.CLASS_NAME, ProrrateoConstants.ROLE_NAME_DOMINIO + "." + DominioConstants.ROLE_NAME_PRORRATEOS, instance.getOid());
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Dominio.Prorrateos.Dia = pDia
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(ProrrateoConstants.ROLE_NAME_DOMINIO + "." + DominioConstants.ROLE_NAME_PRORRATEOS + "." + ProrrateoConstants.ATTR_NAME_PRORRATEODIA, Constants.Type.NAT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(prorrateomodificarpDia, Constants.Type.NAT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Prorrateo, Assoc.Operator: AssocOperator001, Method: assocOperator001Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    @Override
    public void refreshEntity(Object object) {
        super.refreshEntity(object);
    }
}
