package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.DominioConstants;
import com.keralty.aeconfiguraciones.global.ParentescoConstants;
import com.keralty.aeconfiguraciones.global.ParentescoDomConstants;
import com.keralty.aeconfiguraciones.global.UserFunctions;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.Parentesco;
import com.keralty.aeconfiguraciones.persistence.ParentescoDom;
import com.keralty.aeconfiguraciones.sqlutils.SQLExists;
import com.keralty.aeconfiguraciones.sqlutils.SQLMax;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereConjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandOVParameter;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandPathAttribute;
import com.keralty.aeconfiguraciones.sqlutils.TypedQueryBuilder;
import com.keralty.aeconfiguraciones.utils.Agent;
import jakarta.inject.Inject;
import java.util.Collection;
import java.util.List;
import org.slf4j.LoggerFactory;

public class ParentescoDomJpaRepositoryImpl extends AbstractRepository implements ParentescoDomJpaRepositoryCustom {

    @Inject
    public ParentescoDomJpaRepositoryImpl(Agent agent, UserFunctions userFunctionsSrv) {
        super(agent, userFunctionsSrv);
    }

    @Override
    public List<ParentescoDom> executeQuery(String jpqlQuery) {
        return getQueryBuilder().build(jpqlQuery).execute();
    }
    
    @Override
    public List<ParentescoDom> executeQuery(String jpqlQuery, List<SQLParam> params) {
        return getQueryBuilder().build(jpqlQuery).setParameters(params).execute();
    }
    
    public Collection<ParentescoDom> executeQuery(SQLSelect select) {
        return getQueryBuilder().build(select).execute();
    }

    private TypedQueryBuilder<ParentescoDom> getQueryBuilder() {
        return new TypedQueryBuilder<>(em, ParentescoDom.class);
    }

    @Override	
    public List<ParentescoDom> solveQuery(SQLSelect select) {
        return solveQuery(select, 0, 0);
    }

    @Override
    public List<ParentescoDom> solveQuery(SQLSelect select, int pageSize, int pageNumber) {
        return getQueryBuilder().build(select, pageSize, pageNumber).execute();
    }

    @Override
    public Long nextParentescoDomIdentificador(Long dominioNumero) {
        try {
            SQLMax sql = new SQLMax(null, ParentescoDomConstants.CLASS_NAME, ParentescoDomConstants.CLASS_NAME + "." + ParentescoDomConstants.ATTR_FIELD_PARENTESCODOMIDENTIFICADOR);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(ParentescoDomConstants.ROLE_NAME_DOMINIO + "." + DominioConstants.ATTR_FIELD_DOMINIONUMERO, "Autonumerico"));
            comparison1.setOperator("=");
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(dominioNumero, "Autonumerico"));
            conjunction.addComparison(comparison1);

            sql.getWherePart().addConjunction(conjunction);

            Long tempResult = new TypedQueryBuilder<>(em, Long.class).build(sql).getSingleResult();
            return (tempResult != null ? tempResult + 1L : 1L);
        } catch (Exception e) {
            LoggerFactory.getLogger(ParentescoDom.class).error(e.getMessage());
            return 1L;
        }
    }

    // AssocOperator: AssocOperator000
	@Override
    public Boolean assocOperator000(Dominio parentescoDomcrearpagrDominio, Parentesco parentescoDomcrearpagrParentesco) throws SystemException {
        try {
            // EXIST (pagrDominio.ParentescosDom) WHERE (pagrDominio.ParentescosDom.Parentesco = pagrParentesco)
            return assocOperator000Range1(parentescoDomcrearpagrDominio, parentescoDomcrearpagrParentesco);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: ParentescoDom, Assoc.Operator: AssocOperator000, Method: assocOperator000";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator000
	@Override
    public Boolean assocOperator000Range1 (Dominio parentescoDomcrearpagrDominio, Parentesco parentescoDomcrearpagrParentesco) throws SystemException {
        SQLExists sql = assocOperator000Range1Query(parentescoDomcrearpagrDominio, parentescoDomcrearpagrParentesco, null);
        return !new TypedQueryBuilder<ParentescoDom>(em, ParentescoDom.class).build(sql).execute().isEmpty();
    }

    // Range Query 1 for AssocOperator AssocOperator000
	@Override
    public SQLExists assocOperator000Range1Query (Dominio parentescoDomcrearpagrDominio, Parentesco parentescoDomcrearpagrParentesco, SQLSelect superQuery) throws SystemException {
        try {
            SQLExists sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLExists(superQuery, ParentescoDomConstants.CLASS_NAME, ParentescoDomConstants.ARG_NAME_CREAR_PARENTESCODOMCREARPAGRDOMINIO + "." + DominioConstants.ROLE_NAME_PARENTESCOSDOM, DominioConstants.CLASS_NAME, parentescoDomcrearpagrDominio.getOid(), ParentescoDomConstants.ARG_NAME_CREAR_PARENTESCODOMCREARPAGRDOMINIO);
            } else {
                sql = new SQLExists(ParentescoDomConstants.CLASS_NAME, ParentescoDomConstants.ARG_NAME_CREAR_PARENTESCODOMCREARPAGRDOMINIO + "." + DominioConstants.ROLE_NAME_PARENTESCOSDOM, DominioConstants.CLASS_NAME, parentescoDomcrearpagrDominio.getOid(), ParentescoDomConstants.ARG_NAME_CREAR_PARENTESCODOMCREARPAGRDOMINIO);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // pagrDominio.ParentescosDom.Parentesco = pagrParentesco
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandOVParameter operand112 = new SQLWhereOperandOVParameter(ParentescoDomConstants.ARG_NAME_CREAR_PARENTESCODOMCREARPAGRDOMINIO + "." + DominioConstants.ROLE_NAME_PARENTESCOSDOM + "." + ParentescoDomConstants.ROLE_NAME_PARENTESCO, DominioConstants.CLASS_NAME, ParentescoConstants.CLASS_NAME, parentescoDomcrearpagrDominio.getOid(), ParentescoDomConstants.ARG_NAME_CREAR_PARENTESCODOMCREARPAGRDOMINIO);
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(ParentescoDomConstants.ARG_NAME_CREAR_PARENTESCODOMCREARPAGRPARENTESCO, ParentescoConstants.CLASS_NAME, ParentescoConstants.CLASS_NAME, parentescoDomcrearpagrParentesco.getOid(), ParentescoDomConstants.ARG_NAME_CREAR_PARENTESCODOMCREARPAGRPARENTESCO);
                    comparison1.setSecondOperand(operand223);
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: ParentescoDom, Assoc.Operator: AssocOperator000, Method: assocOperator000Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    @Override
    public void refreshEntity(Object object) {
        super.refreshEntity(object);
    }
}
