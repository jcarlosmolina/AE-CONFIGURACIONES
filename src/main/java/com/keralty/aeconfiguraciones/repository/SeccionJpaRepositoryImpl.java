package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.CuestionarioMedConstants;
import com.keralty.aeconfiguraciones.global.PreguntaConstants;
import com.keralty.aeconfiguraciones.global.SeccionConstants;
import com.keralty.aeconfiguraciones.global.UserFunctions;
import com.keralty.aeconfiguraciones.persistence.Pregunta;
import com.keralty.aeconfiguraciones.persistence.Seccion;
import com.keralty.aeconfiguraciones.sqlutils.SQLExists;
import com.keralty.aeconfiguraciones.sqlutils.SQLParam;
import com.keralty.aeconfiguraciones.sqlutils.SQLPopulation;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereConjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandPathAttribute;
import com.keralty.aeconfiguraciones.sqlutils.TypedQueryBuilder;
import com.keralty.aeconfiguraciones.utils.Agent;
import jakarta.inject.Inject;
import java.util.Collection;
import java.util.List;

public class SeccionJpaRepositoryImpl extends AbstractRepository implements SeccionJpaRepositoryCustom {

    @Inject
    public SeccionJpaRepositoryImpl(Agent agent, UserFunctions userFunctionsSrv) {
        super(agent, userFunctionsSrv);
    }

    @Override
    public List<Seccion> executeQuery(String jpqlQuery) {
        return getQueryBuilder().build(jpqlQuery).execute();
    }
    
    @Override
    public List<Seccion> executeQuery(String jpqlQuery, List<SQLParam> params) {
        return getQueryBuilder().build(jpqlQuery).setParameters(params).execute();
    }
    
    public Collection<Seccion> executeQuery(SQLSelect select) {
        return getQueryBuilder().build(select).execute();
    }

    private TypedQueryBuilder<Seccion> getQueryBuilder() {
        return new TypedQueryBuilder<>(em, Seccion.class);
    }

    @Override	
    public List<Seccion> solveQuery(SQLSelect select) {
        return solveQuery(select, 0, 0);
    }

    @Override
    public List<Seccion> solveQuery(SQLSelect select, int pageSize, int pageNumber) {
        return getQueryBuilder().build(select, pageSize, pageNumber).execute();
    }

    // AssocOperator: AssocOperator000
	@Override
    public Boolean assocOperator000(Seccion instance) throws SystemException {
        try {
            // EXIST (Preguntas)
            return assocOperator000Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Seccion, Assoc.Operator: AssocOperator000, Method: assocOperator000";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator000
	@Override
    public Boolean assocOperator000Range1 (Seccion instance) throws SystemException {
        SQLExists sql = assocOperator000Range1Query(instance);
        return !new TypedQueryBuilder<Seccion>(em, Seccion.class).build(sql).execute().isEmpty();
    }

    // Range Query 1 for AssocOperator AssocOperator000
	@Override
    public SQLExists assocOperator000Range1Query (Seccion instance) throws SystemException {
        try {
            // The path starts with a role
            return new SQLExists(SeccionConstants.CLASS_NAME, SeccionConstants.ROLE_NAME_PREGUNTAS, instance.getOid());
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Seccion, Assoc.Operator: AssocOperator000, Method: assocOperator000Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator001
	@Override
    public Collection<Seccion> assocOperator001(Seccion instance, Long seccionTCREARptpatrNumOrden) throws SystemException {
        try {
            // FOR ALL Cuestionario.Secciones WHERE (Cuestionario.Secciones.NumOrden >= ptpatrNumOrden) DO Cuestionario.Secciones.cambiarOrden(Cuestionario.Secciones, Cuestionario.Secciones.NumOrden + 1)
        return assocOperator001Range1(instance, seccionTCREARptpatrNumOrden);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Seccion, Assoc.Operator: AssocOperator001, Method: assocOperator001";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator001
	@Override
    public Collection<Seccion> assocOperator001Range1 (Seccion instance, Long seccionTCREARptpatrNumOrden) throws SystemException {

        SQLSelect sql = assocOperator001Range1Query(instance, seccionTCREARptpatrNumOrden);
        return new TypedQueryBuilder<Seccion>(em, Seccion.class).build(sql).execute();
    }

    // Range Query 1 for AssocOperator AssocOperator001
	@Override
    public SQLPopulation assocOperator001Range1Query (Seccion instance, Long seccionTCREARptpatrNumOrden) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation(SeccionConstants.CLASS_NAME, SeccionConstants.CLASS_NAME, instance.getOid(), SeccionConstants.ROLE_NAME_CUESTIONARIO + "." + CuestionarioMedConstants.ROLE_NAME_SECCIONES);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Cuestionario.Secciones.NumOrden >= ptpatrNumOrden
                    // '>=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator(">=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(SeccionConstants.ROLE_NAME_CUESTIONARIO + "." + CuestionarioMedConstants.ROLE_NAME_SECCIONES + "." + SeccionConstants.ATTR_NAME_SECCIONNUMORDEN, Constants.Type.NAT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(seccionTCREARptpatrNumOrden, Constants.Type.NAT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Seccion, Assoc.Operator: AssocOperator001, Method: assocOperator001Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator002
	@Override
    public Collection<Seccion> assocOperator002(Seccion instance, Long seccionTMODIFICARptpNumOrden) throws SystemException {
        try {
            // FOR ALL Cuestionario.Secciones WHERE (Cuestionario.Secciones.NumOrden >= ptpNumOrden AND Cuestionario.Secciones.NumOrden < NumOrden) DO Cuestionario.Secciones.cambiarOrden(Cuestionario.Secciones, Cuestionario.Secciones.NumOrden + 1)
        return assocOperator002Range1(instance, seccionTMODIFICARptpNumOrden);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Seccion, Assoc.Operator: AssocOperator002, Method: assocOperator002";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator002
	@Override
    public Collection<Seccion> assocOperator002Range1 (Seccion instance, Long seccionTMODIFICARptpNumOrden) throws SystemException {

        SQLSelect sql = assocOperator002Range1Query(instance, seccionTMODIFICARptpNumOrden);
        return new TypedQueryBuilder<Seccion>(em, Seccion.class).build(sql).execute();
    }

    // Range Query 1 for AssocOperator AssocOperator002
	@Override
    public SQLPopulation assocOperator002Range1Query (Seccion instance, Long seccionTMODIFICARptpNumOrden) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation(SeccionConstants.CLASS_NAME, SeccionConstants.CLASS_NAME, instance.getOid(), SeccionConstants.ROLE_NAME_CUESTIONARIO + "." + CuestionarioMedConstants.ROLE_NAME_SECCIONES);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Cuestionario.Secciones.NumOrden >= ptpNumOrden
                    // '>=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator(">=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(SeccionConstants.ROLE_NAME_CUESTIONARIO + "." + CuestionarioMedConstants.ROLE_NAME_SECCIONES + "." + SeccionConstants.ATTR_NAME_SECCIONNUMORDEN, Constants.Type.NAT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(seccionTMODIFICARptpNumOrden, Constants.Type.NAT.getTypeName()));
                    conjunction.addOperand(comparison1);


            // Cuestionario.Secciones.NumOrden < NumOrden
                    // '<' Comparison
                    SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
                    comparison2.setOperator("<");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand113 = new SQLWhereOperandPathAttribute(SeccionConstants.ROLE_NAME_CUESTIONARIO + "." + CuestionarioMedConstants.ROLE_NAME_SECCIONES + "." + SeccionConstants.ATTR_NAME_SECCIONNUMORDEN, Constants.Type.NAT.getTypeName());
                    comparison2.setFirstOperand(operand113);
                    
                    // Operand 2
                    comparison2.setSecondOperand(new SQLWhereOperandLiteral(instance.getSeccionNumOrden(), Constants.Type.NAT.getTypeName()));
                    conjunction.addOperand(comparison2);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Seccion, Assoc.Operator: AssocOperator002, Method: assocOperator002Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator003
	@Override
    public Collection<Seccion> assocOperator003(Seccion instance, Long seccionTMODIFICARptpNumOrden) throws SystemException {
        try {
            // FOR ALL Cuestionario.Secciones WHERE (Cuestionario.Secciones.NumOrden <= ptpNumOrden AND Cuestionario.Secciones.NumOrden > NumOrden) DO Cuestionario.Secciones.cambiarOrden(Cuestionario.Secciones, Cuestionario.Secciones.NumOrden - 1)
        return assocOperator003Range1(instance, seccionTMODIFICARptpNumOrden);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Seccion, Assoc.Operator: AssocOperator003, Method: assocOperator003";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator003
	@Override
    public Collection<Seccion> assocOperator003Range1 (Seccion instance, Long seccionTMODIFICARptpNumOrden) throws SystemException {

        SQLSelect sql = assocOperator003Range1Query(instance, seccionTMODIFICARptpNumOrden);
        return new TypedQueryBuilder<Seccion>(em, Seccion.class).build(sql).execute();
    }

    // Range Query 1 for AssocOperator AssocOperator003
	@Override
    public SQLPopulation assocOperator003Range1Query (Seccion instance, Long seccionTMODIFICARptpNumOrden) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation(SeccionConstants.CLASS_NAME, SeccionConstants.CLASS_NAME, instance.getOid(), SeccionConstants.ROLE_NAME_CUESTIONARIO + "." + CuestionarioMedConstants.ROLE_NAME_SECCIONES);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Cuestionario.Secciones.NumOrden <= ptpNumOrden
                    // '<=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator("<=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(SeccionConstants.ROLE_NAME_CUESTIONARIO + "." + CuestionarioMedConstants.ROLE_NAME_SECCIONES + "." + SeccionConstants.ATTR_NAME_SECCIONNUMORDEN, Constants.Type.NAT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(seccionTMODIFICARptpNumOrden, Constants.Type.NAT.getTypeName()));
                    conjunction.addOperand(comparison1);


            // Cuestionario.Secciones.NumOrden > NumOrden
                    // '>' Comparison
                    SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
                    comparison2.setOperator(">");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand113 = new SQLWhereOperandPathAttribute(SeccionConstants.ROLE_NAME_CUESTIONARIO + "." + CuestionarioMedConstants.ROLE_NAME_SECCIONES + "." + SeccionConstants.ATTR_NAME_SECCIONNUMORDEN, Constants.Type.NAT.getTypeName());
                    comparison2.setFirstOperand(operand113);
                    
                    // Operand 2
                    comparison2.setSecondOperand(new SQLWhereOperandLiteral(instance.getSeccionNumOrden(), Constants.Type.NAT.getTypeName()));
                    conjunction.addOperand(comparison2);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Seccion, Assoc.Operator: AssocOperator003, Method: assocOperator003Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator004
	@Override
    public Collection<Seccion> assocOperator004(Seccion instance) throws SystemException {
        try {
            // FOR ALL Cuestionario.Secciones WHERE (Cuestionario.Secciones.NumOrden > NumOrden) DO Cuestionario.Secciones.cambiarOrden(Cuestionario.Secciones, Cuestionario.Secciones.NumOrden - 1)
        return assocOperator004Range1(instance);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Seccion, Assoc.Operator: AssocOperator004, Method: assocOperator004";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator004
	@Override
    public Collection<Seccion> assocOperator004Range1 (Seccion instance) throws SystemException {

        SQLSelect sql = assocOperator004Range1Query(instance);
        return new TypedQueryBuilder<Seccion>(em, Seccion.class).build(sql).execute();
    }

    // Range Query 1 for AssocOperator AssocOperator004
	@Override
    public SQLPopulation assocOperator004Range1Query (Seccion instance) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation(SeccionConstants.CLASS_NAME, SeccionConstants.CLASS_NAME, instance.getOid(), SeccionConstants.ROLE_NAME_CUESTIONARIO + "." + CuestionarioMedConstants.ROLE_NAME_SECCIONES);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Cuestionario.Secciones.NumOrden > NumOrden
                    // '>' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator(">");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(SeccionConstants.ROLE_NAME_CUESTIONARIO + "." + CuestionarioMedConstants.ROLE_NAME_SECCIONES + "." + SeccionConstants.ATTR_NAME_SECCIONNUMORDEN, Constants.Type.NAT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(instance.getSeccionNumOrden(), Constants.Type.NAT.getTypeName()));
                    conjunction.addOperand(comparison1);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Seccion, Assoc.Operator: AssocOperator004, Method: assocOperator004Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator005
	@Override
    public Collection<Pregunta> assocOperator005(Seccion instance, Long seccionTCAMBIARORDENPREGSptOrdenDesde, Long seccionTCAMBIARORDENPREGSptOrdenHasta) throws SystemException {
        try {
            // FOR ALL Preguntas WHERE (Preguntas.NumOrden >= ptOrdenDesde AND Preguntas.NumOrden <= ptOrdenHasta) DO Preguntas.cambiarOrden(Preguntas, Preguntas.NumOrden + 1)
        return assocOperator005Range1(instance, seccionTCAMBIARORDENPREGSptOrdenDesde, seccionTCAMBIARORDENPREGSptOrdenHasta);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Seccion, Assoc.Operator: AssocOperator005, Method: assocOperator005";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator005
	@Override
    public Collection<Pregunta> assocOperator005Range1 (Seccion instance, Long seccionTCAMBIARORDENPREGSptOrdenDesde, Long seccionTCAMBIARORDENPREGSptOrdenHasta) throws SystemException {

        SQLSelect sql = assocOperator005Range1Query(instance, seccionTCAMBIARORDENPREGSptOrdenDesde, seccionTCAMBIARORDENPREGSptOrdenHasta);
        return new TypedQueryBuilder<Pregunta>(em, Pregunta.class).build(sql).execute();
    }

    // Range Query 1 for AssocOperator AssocOperator005
	@Override
    public SQLPopulation assocOperator005Range1Query (Seccion instance, Long seccionTCAMBIARORDENPREGSptOrdenDesde, Long seccionTCAMBIARORDENPREGSptOrdenHasta) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation(SeccionConstants.CLASS_NAME, PreguntaConstants.CLASS_NAME, instance.getOid(), SeccionConstants.ROLE_NAME_PREGUNTAS);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Preguntas.NumOrden >= ptOrdenDesde
                    // '>=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator(">=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(SeccionConstants.ROLE_NAME_PREGUNTAS + "." + PreguntaConstants.ATTR_NAME_PREGUNTANUMORDEN, Constants.Type.NAT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(seccionTCAMBIARORDENPREGSptOrdenDesde, Constants.Type.NAT.getTypeName()));
                    conjunction.addOperand(comparison1);


            // Preguntas.NumOrden <= ptOrdenHasta
                    // '<=' Comparison
                    SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
                    comparison2.setOperator("<=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand113 = new SQLWhereOperandPathAttribute(SeccionConstants.ROLE_NAME_PREGUNTAS + "." + PreguntaConstants.ATTR_NAME_PREGUNTANUMORDEN, Constants.Type.NAT.getTypeName());
                    comparison2.setFirstOperand(operand113);
                    
                    // Operand 2
                    comparison2.setSecondOperand(new SQLWhereOperandLiteral(seccionTCAMBIARORDENPREGSptOrdenHasta, Constants.Type.NAT.getTypeName()));
                    conjunction.addOperand(comparison2);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Seccion, Assoc.Operator: AssocOperator005, Method: assocOperator005Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // AssocOperator: AssocOperator006
	@Override
    public Collection<Pregunta> assocOperator006(Seccion instance, Long seccionTCAMBIARORDENPREGSptOrdenDesde, Long seccionTCAMBIARORDENPREGSptOrdenHasta) throws SystemException {
        try {
            // FOR ALL Preguntas WHERE (Preguntas.NumOrden >= ptOrdenDesde AND Preguntas.NumOrden <= ptOrdenHasta) DO Preguntas.cambiarOrden(Preguntas, Preguntas.NumOrden - 1)
        return assocOperator006Range1(instance, seccionTCAMBIARORDENPREGSptOrdenDesde, seccionTCAMBIARORDENPREGSptOrdenHasta);
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Seccion, Assoc.Operator: AssocOperator006, Method: assocOperator006";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    // Range 1 for AssocOperator AssocOperator006
	@Override
    public Collection<Pregunta> assocOperator006Range1 (Seccion instance, Long seccionTCAMBIARORDENPREGSptOrdenDesde, Long seccionTCAMBIARORDENPREGSptOrdenHasta) throws SystemException {

        SQLSelect sql = assocOperator006Range1Query(instance, seccionTCAMBIARORDENPREGSptOrdenDesde, seccionTCAMBIARORDENPREGSptOrdenHasta);
        return new TypedQueryBuilder<Pregunta>(em, Pregunta.class).build(sql).execute();
    }

    // Range Query 1 for AssocOperator AssocOperator006
	@Override
    public SQLPopulation assocOperator006Range1Query (Seccion instance, Long seccionTCAMBIARORDENPREGSptOrdenDesde, Long seccionTCAMBIARORDENPREGSptOrdenHasta) throws SystemException {
        try {
            SQLPopulation sql = new SQLPopulation(SeccionConstants.CLASS_NAME, PreguntaConstants.CLASS_NAME, instance.getOid(), SeccionConstants.ROLE_NAME_PREGUNTAS);
            SQLWhereConjunction conjunction = new SQLWhereConjunction();

            // Preguntas.NumOrden >= ptOrdenDesde
                    // '>=' Comparison
                    SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
                    comparison1.setOperator(">=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(SeccionConstants.ROLE_NAME_PREGUNTAS + "." + PreguntaConstants.ATTR_NAME_PREGUNTANUMORDEN, Constants.Type.NAT.getTypeName());
                    comparison1.setFirstOperand(operand112);
                    
                    // Operand 2
                    comparison1.setSecondOperand(new SQLWhereOperandLiteral(seccionTCAMBIARORDENPREGSptOrdenDesde, Constants.Type.NAT.getTypeName()));
                    conjunction.addOperand(comparison1);


            // Preguntas.NumOrden <= ptOrdenHasta
                    // '<=' Comparison
                    SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
                    comparison2.setOperator("<=");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand113 = new SQLWhereOperandPathAttribute(SeccionConstants.ROLE_NAME_PREGUNTAS + "." + PreguntaConstants.ATTR_NAME_PREGUNTANUMORDEN, Constants.Type.NAT.getTypeName());
                    comparison2.setFirstOperand(operand113);
                    
                    // Operand 2
                    comparison2.setSecondOperand(new SQLWhereOperandLiteral(seccionTCAMBIARORDENPREGSptOrdenHasta, Constants.Type.NAT.getTypeName()));
                    conjunction.addOperand(comparison2);

            sql.addOperand(conjunction);
            return sql;
        } catch (Exception e) {
            String exceptionMessage = "Definition Class: Seccion, Assoc.Operator: AssocOperator006, Method: assocOperator006Range1Query";
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(exceptionMessage);
            throw new SystemException(e, exceptionMessage);
        }
    }

    @Override
    public void refreshEntity(Object object) {
        super.refreshEntity(object);
    }
}
