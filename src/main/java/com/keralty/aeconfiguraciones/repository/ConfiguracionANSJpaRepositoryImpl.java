package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.CausalConstants;
import com.keralty.aeconfiguraciones.global.ConfiguracionANSConstants;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.DominioConstants;
import com.keralty.aeconfiguraciones.global.LogConfiguracionANSConstants;
import com.keralty.aeconfiguraciones.global.NovedadConstants;
import com.keralty.aeconfiguraciones.global.UserFunctions;
import com.keralty.aeconfiguraciones.persistence.Causal;
import com.keralty.aeconfiguraciones.persistence.ConfiguracionANS;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.LogConfiguracionANS;
import com.keralty.aeconfiguraciones.persistence.Novedad;
import com.keralty.aeconfiguraciones.sqlutils.SQLCount;
import com.keralty.aeconfiguraciones.sqlutils.SQLExists;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLPopulation;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereConjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandOVParameter;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandOVParameterAttribute;
import com.keralty.aeconfiguraciones.sqlutils.TypedQueryBuilder;
import com.keralty.aeconfiguraciones.utils.Agent;
import jakarta.inject.Inject;
import java.util.Collection;
import java.util.List;

public class ConfiguracionANSJpaRepositoryImpl extends AbstractRepository implements ConfiguracionANSJpaRepositoryCustom {

    @Inject
    public ConfiguracionANSJpaRepositoryImpl(Agent agent, UserFunctions userFunctionsSrv) {
        super(agent, userFunctionsSrv);
    }

    @Override
    public List<ConfiguracionANS> executeQuery(String jpqlQuery) {
        return getQueryBuilder().build(jpqlQuery).execute();
    }
    
    @Override
    public List<ConfiguracionANS> executeQuery(String jpqlQuery, List<SQLParam> params) {
        return getQueryBuilder().build(jpqlQuery).setParameters(params).execute();
    }
    
    public Collection<ConfiguracionANS> executeQuery(SQLSelect select) {
        return getQueryBuilder().build(select).execute();
    }

    private TypedQueryBuilder<ConfiguracionANS> getQueryBuilder() {
        return new TypedQueryBuilder<>(em, ConfiguracionANS.class);
    }

    @Override	
    public List<ConfiguracionANS> solveQuery(SQLSelect select) {
        return solveQuery(select, 0, 0);
    }

    @Override
    public List<ConfiguracionANS> solveQuery(SQLSelect select, int pageSize, int pageNumber) {
        return getQueryBuilder().build(select, pageSize, pageNumber).execute();
    }

    // AssocOperator: AssocOperator000
	@Override
    public Boolean assocOperator000(ConfiguracionANS instance) throws SystemException {
        try {
            // EXIST (Causal)
            return assocOperator000Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: ConfiguracionANS, Assoc.Operator: AssocOperator000, Method: assocOperator000";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator000
	@Override
    public Boolean assocOperator000Range1 (ConfiguracionANS instance) throws SystemException {
        SQLExists sql = assocOperator000Range1Query(instance);
        return !new TypedQueryBuilder<ConfiguracionANS>(em, ConfiguracionANS.class).build(sql).execute().isEmpty();
    }

    // Range Query 1 for AssocOperator AssocOperator000
	@Override
    public SQLExists assocOperator000Range1Query (ConfiguracionANS instance) throws SystemException {
        try {
            // The path starts with a role
            return new SQLExists(ConfiguracionANSConstants.CLASS_NAME, ConfiguracionANSConstants.ROLE_NAME_CAUSAL, instance.getOid());
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: ConfiguracionANS, Assoc.Operator: AssocOperator000, Method: assocOperator000Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator001
	@Override
    public Long assocOperator001(Dominio configuracionANSTCREARptpagrDominio, Long configuracionANSTCREARptpatrTramite) throws SystemException {
        try {
            // COUNT (ptpagrDominio.ConfiguracionesANS) WHERE (ptpagrDominio.ConfiguracionesANS.Tramite = ptpatrTramite)
            return assocOperator001Range1(configuracionANSTCREARptpagrDominio, configuracionANSTCREARptpatrTramite);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: ConfiguracionANS, Assoc.Operator: AssocOperator001, Method: assocOperator001";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator001
	@Override
    public Long assocOperator001Range1 (Dominio configuracionANSTCREARptpagrDominio, Long configuracionANSTCREARptpatrTramite) throws SystemException {
        SQLCount sql = assocOperator001Range1Query(configuracionANSTCREARptpagrDominio, configuracionANSTCREARptpatrTramite, null);
        return Long.valueOf(solveCountQuery(sql));
    }

    // Range Query 1 for AssocOperator AssocOperator001
	@Override
    public SQLCount assocOperator001Range1Query (Dominio configuracionANSTCREARptpagrDominio, Long configuracionANSTCREARptpatrTramite, SQLSelect superQuery) throws SystemException {
        try {
            SQLCount sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLCount(superQuery, ConfiguracionANSConstants.CLASS_NAME, ConfiguracionANSConstants.ARG_NAME_TCREAR_CONFIGURACIONANSTCREARPTPAGRDOMINIO + "." + DominioConstants.ROLE_NAME_CONFIGURACIONESANS, DominioConstants.CLASS_NAME, configuracionANSTCREARptpagrDominio.getOid(), ConfiguracionANSConstants.ARG_NAME_TCREAR_CONFIGURACIONANSTCREARPTPAGRDOMINIO);
            } else {
                sql = new SQLCount(ConfiguracionANSConstants.CLASS_NAME, ConfiguracionANSConstants.ARG_NAME_TCREAR_CONFIGURACIONANSTCREARPTPAGRDOMINIO + "." + DominioConstants.ROLE_NAME_CONFIGURACIONESANS, DominioConstants.CLASS_NAME, configuracionANSTCREARptpagrDominio.getOid(), ConfiguracionANSConstants.ARG_NAME_TCREAR_CONFIGURACIONANSTCREARPTPAGRDOMINIO);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // ptpagrDominio.ConfiguracionesANS.Tramite = ptpatrTramite
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandOVParameterAttribute operand112 = new SQLWhereOperandOVParameterAttribute(ConfiguracionANSConstants.ARG_NAME_TCREAR_CONFIGURACIONANSTCREARPTPAGRDOMINIO + "." + DominioConstants.ROLE_NAME_CONFIGURACIONESANS + "." + ConfiguracionANSConstants.ATTR_NAME_CONFIGURACIONANSTRAMITE, DominioConstants.CLASS_NAME, configuracionANSTCREARptpagrDominio.getOid(), ConfiguracionANSConstants.ARG_NAME_TCREAR_CONFIGURACIONANSTCREARPTPAGRDOMINIO, Constants.Type.NAT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(configuracionANSTCREARptpatrTramite, Constants.Type.NAT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: ConfiguracionANS, Assoc.Operator: AssocOperator001, Method: assocOperator001Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator002
	@Override
    public Long assocOperator002(Dominio configuracionANSTCREARptpagrDominio, Novedad configuracionANSTCREARptpagrNovedad) throws SystemException {
        try {
            // COUNT (ptpagrDominio.ConfiguracionesANS) WHERE (ptpagrDominio.ConfiguracionesANS.Novedad = ptpagrNovedad AND ptpagrDominio.ConfiguracionesANS.drvTieneCausal = false)
            long count = 0L;
            for(ConfiguracionANS item : assocOperator002Range1(configuracionANSTCREARptpagrDominio, configuracionANSTCREARptpagrNovedad)){
                // ptpagrDominio.ConfiguracionesANS.drvTieneCausal = false
                if(item.getConfiguracionANSdrvTieneCausal().equals(false))
                    count += 1;
            }
            return count;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: ConfiguracionANS, Assoc.Operator: AssocOperator002, Method: assocOperator002";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator002
	@Override
    public Collection<ConfiguracionANS> assocOperator002Range1 (Dominio configuracionANSTCREARptpagrDominio, Novedad configuracionANSTCREARptpagrNovedad) throws SystemException {

        SQLSelect sql = assocOperator002Range1Query(configuracionANSTCREARptpagrDominio, configuracionANSTCREARptpagrNovedad);
        return new TypedQueryBuilder<ConfiguracionANS>(em, ConfiguracionANS.class).build(sql).execute();
    }

    // Range Query 1 for AssocOperator AssocOperator002
	@Override
    public SQLPopulation assocOperator002Range1Query (Dominio configuracionANSTCREARptpagrDominio, Novedad configuracionANSTCREARptpagrNovedad) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation(ConfiguracionANSConstants.CLASS_NAME, ConfiguracionANSConstants.CLASS_NAME, ConfiguracionANSConstants.ARG_NAME_TCREAR_CONFIGURACIONANSTCREARPTPAGRDOMINIO + "." + DominioConstants.ROLE_NAME_CONFIGURACIONESANS, configuracionANSTCREARptpagrDominio.getOid(), ConfiguracionANSConstants.ARG_NAME_TCREAR_CONFIGURACIONANSTCREARPTPAGRDOMINIO);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // ptpagrDominio.ConfiguracionesANS.Novedad = ptpagrNovedad
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandOVParameter operand112 = new SQLWhereOperandOVParameter(ConfiguracionANSConstants.ARG_NAME_TCREAR_CONFIGURACIONANSTCREARPTPAGRDOMINIO + "." + DominioConstants.ROLE_NAME_CONFIGURACIONESANS + "." + ConfiguracionANSConstants.ROLE_NAME_NOVEDAD, DominioConstants.CLASS_NAME, NovedadConstants.CLASS_NAME, configuracionANSTCREARptpagrDominio.getOid(), ConfiguracionANSConstants.ARG_NAME_TCREAR_CONFIGURACIONANSTCREARPTPAGRDOMINIO);
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(ConfiguracionANSConstants.ARG_NAME_TCREAR_CONFIGURACIONANSTCREARPTPAGRNOVEDAD, NovedadConstants.CLASS_NAME, NovedadConstants.CLASS_NAME, configuracionANSTCREARptpagrNovedad.getOid(), ConfiguracionANSConstants.ARG_NAME_TCREAR_CONFIGURACIONANSTCREARPTPAGRNOVEDAD);
                    comparison1.setSecondOperand(operand223);
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: ConfiguracionANS, Assoc.Operator: AssocOperator002, Method: assocOperator002Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator003
	@Override
    public Long assocOperator003(Dominio configuracionANSTCREARptpagrDominio, Causal configuracionANSTCREARptpagrCausal) throws SystemException {
        try {
            // COUNT (ptpagrDominio.ConfiguracionesANS) WHERE (ptpagrDominio.ConfiguracionesANS.Causal = ptpagrCausal)
            return assocOperator003Range1(configuracionANSTCREARptpagrDominio, configuracionANSTCREARptpagrCausal);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: ConfiguracionANS, Assoc.Operator: AssocOperator003, Method: assocOperator003";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator003
	@Override
    public Long assocOperator003Range1 (Dominio configuracionANSTCREARptpagrDominio, Causal configuracionANSTCREARptpagrCausal) throws SystemException {
        SQLCount sql = assocOperator003Range1Query(configuracionANSTCREARptpagrDominio, configuracionANSTCREARptpagrCausal, null);
        return Long.valueOf(solveCountQuery(sql));
    }

    // Range Query 1 for AssocOperator AssocOperator003
	@Override
    public SQLCount assocOperator003Range1Query (Dominio configuracionANSTCREARptpagrDominio, Causal configuracionANSTCREARptpagrCausal, SQLSelect superQuery) throws SystemException {
        try {
            SQLCount sql;
            // The path starts with an object
            if (superQuery != null) {
                sql = new SQLCount(superQuery, ConfiguracionANSConstants.CLASS_NAME, ConfiguracionANSConstants.ARG_NAME_TCREAR_CONFIGURACIONANSTCREARPTPAGRDOMINIO + "." + DominioConstants.ROLE_NAME_CONFIGURACIONESANS, DominioConstants.CLASS_NAME, configuracionANSTCREARptpagrDominio.getOid(), ConfiguracionANSConstants.ARG_NAME_TCREAR_CONFIGURACIONANSTCREARPTPAGRDOMINIO);
            } else {
                sql = new SQLCount(ConfiguracionANSConstants.CLASS_NAME, ConfiguracionANSConstants.ARG_NAME_TCREAR_CONFIGURACIONANSTCREARPTPAGRDOMINIO + "." + DominioConstants.ROLE_NAME_CONFIGURACIONESANS, DominioConstants.CLASS_NAME, configuracionANSTCREARptpagrDominio.getOid(), ConfiguracionANSConstants.ARG_NAME_TCREAR_CONFIGURACIONANSTCREARPTPAGRDOMINIO);
            }
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // ptpagrDominio.ConfiguracionesANS.Causal = ptpagrCausal
                    // '=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("=");
                    
                    // Operand 1
                    SQLWhereOperandOVParameter operand112 = new SQLWhereOperandOVParameter(ConfiguracionANSConstants.ARG_NAME_TCREAR_CONFIGURACIONANSTCREARPTPAGRDOMINIO + "." + DominioConstants.ROLE_NAME_CONFIGURACIONESANS + "." + ConfiguracionANSConstants.ROLE_NAME_CAUSAL, DominioConstants.CLASS_NAME, CausalConstants.CLASS_NAME, configuracionANSTCREARptpagrDominio.getOid(), ConfiguracionANSConstants.ARG_NAME_TCREAR_CONFIGURACIONANSTCREARPTPAGRDOMINIO);
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(ConfiguracionANSConstants.ARG_NAME_TCREAR_CONFIGURACIONANSTCREARPTPAGRCAUSAL, CausalConstants.CLASS_NAME, CausalConstants.CLASS_NAME, configuracionANSTCREARptpagrCausal.getOid(), ConfiguracionANSConstants.ARG_NAME_TCREAR_CONFIGURACIONANSTCREARPTPAGRCAUSAL);
                    comparison1.setSecondOperand(operand223);
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: ConfiguracionANS, Assoc.Operator: AssocOperator003, Method: assocOperator003Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator004
	@Override
    public Collection<LogConfiguracionANS> assocOperator004(ConfiguracionANS instance) throws SystemException {
        try {
            // FOR ALL LogConfiguracionANS DO LogConfiguracionANS.eliminar(LogConfiguracionANS)
        return assocOperator004Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: ConfiguracionANS, Assoc.Operator: AssocOperator004, Method: assocOperator004";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator004
	@Override
    public Collection<LogConfiguracionANS> assocOperator004Range1 (ConfiguracionANS instance) throws SystemException {

        SQLSelect sql = assocOperator004Range1Query(instance);
        return new TypedQueryBuilder<LogConfiguracionANS>(em, LogConfiguracionANS.class).build(sql).execute();
    }

    // Range Query 1 for AssocOperator AssocOperator004
	@Override
    public SQLPopulation assocOperator004Range1Query (ConfiguracionANS instance) throws SystemException {
        try {
            return new SQLPopulation(ConfiguracionANSConstants.CLASS_NAME, LogConfiguracionANSConstants.CLASS_NAME, instance.getOid(), ConfiguracionANSConstants.ROLE_NAME_LOGCONFIGURACIONANS);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: ConfiguracionANS, Assoc.Operator: AssocOperator004, Method: assocOperator004Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    @Override
    public void refreshEntity(Object object) {
        super.refreshEntity(object);
    }
}
