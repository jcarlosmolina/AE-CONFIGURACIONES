// Utility para el MS aeconfiguraciones
package com.keralty.integraciones.parametricas;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.keralty.aeconfiguraciones.global.STDFunctions;
import com.keralty.integraciones.CallBHUtils;
import com.keralty.integraciones.IntegracionesUtils;
import com.keralty.integraciones.token.TokenUtils;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ParametricasUtils {

	TokenUtils tokenUtils;
	
	CallBHUtils callBHUtils;

	@RestClient
	ParametricasProxy parametricas;
	
	@ConfigProperty(name="com.keralty.integraciones.token.usr")
	String username;
	
	@ConfigProperty(name="com.keralty.integraciones.token.pwd")
	String password;

	@ConfigProperty(name="com.keralty.integraciones.parametricas.metodopago.cod_table")
	Optional<String> codTableMetodoPago;
	
	@ConfigProperty(name="com.keralty.integraciones.parametricas.metodopago.nom_table")
	String nomTableMetodoPago;

	@ConfigProperty(name="com.keralty.integraciones.parametricas.estadocivil.cod_table")
	String codTableEstadoCivil;

	@ConfigProperty(name="com.keralty.integraciones.parametricas.departamento.cod_table")
	String codTableDepartamento;

	@ConfigProperty(name="com.keralty.integraciones.parametricas.estadocontrato.cod_table")
	String codTableEstadoContrato;

	@ConfigProperty(name="com.keralty.integraciones.parametricas.regionales.cod_table")
	String codTableRegionales;

	@ConfigProperty(name="com.keralty.integraciones.parametricas.pais.cod_table")
	String codTablePais;
	
	@ConfigProperty(name="com.keralty.integraciones.parametricas.entidadfinancier.cod_table")
	String codTableEntidadFinanciera;
	
	@ConfigProperty(name="com.keralty.integraciones.parametricas.tipoentidad.cod_table")
	String codTableTipoEntidad;
	
	@ConfigProperty(name="com.keralty.integraciones.parametricas.tipoinstitucion.cod_table")
	String codTableTipoInstitucion;

	@ConfigProperty(name="com.keralty.integraciones.parametricas.producto.cod_table")
	String codTableProducto;

	@ConfigProperty(name="com.keralty.integraciones.parametricas.plan.cod_table")
	String codTablePlan;

	@ConfigProperty(name="com.keralty.integraciones.parametricas.municipio.cod_table")
	String codTableMunicipio;

	@ConfigProperty(name="com.keralty.integraciones.parametricas.tipodireccion.cod_table")
	String codTableTipoDireccion;

	@ConfigProperty(name="com.keralty.integraciones.parametricas.estadopersona.cod_table")
	String codTableEstadoPersona;
	
	@ConfigProperty(name="com.keralty.integraciones.parametricas.estadobeneficiario.cod_table")
	String codTableEstadoBeneficiario;
	
	@ConfigProperty(name="com.keralty.integraciones.parametricas.tipoidentificacion.cod_table")
	String codTableTipoIdentificacion;
	
	@ConfigProperty(name="com.keralty.integraciones.parametricas.periodicidad.cod_table")
	String codTablePeriodicidad;
	
	@ConfigProperty(name="com.keralty.integraciones.parametricas.grupoasociado.cod_table")
	String codTableGrupoAsociado;
	
	@ConfigProperty(name="com.keralty.integraciones.parametricas.tiposucursal.cod_table")
	String codTableTipoSucursal;
	
	@ConfigProperty(name="com.keralty.integraciones.parametricas.parentesco.cod_table")
	String codTableParentesco;
	
	
	public ParametricasUtils(CallBHUtils callBHUtils, TokenUtils tokenUtils) {
		this.tokenUtils = tokenUtils;
		this.callBHUtils = callBHUtils;
	}


	private List<DatoParametrica> consultarPorCodigo(String codigo) {
		GetParametricRequest request = new GetParametricRequest();
		request.setCodTable(codigo);
		String textoError = null;
		long startTime = System.currentTimeMillis();
		long endTime = startTime;
		Double tiempoEjecucion = 0.0;
		Timestamp fechaHora = STDFunctions.systemDateTimeFun();	
		List<DatoParametrica> respuesta = new ArrayList<DatoParametrica>();
		try {
			respuesta = parametricas.getParametric(tokenUtils.getTokenValue(), "ID", IntegracionesUtils.getRequestDate(), request);
			endTime = System.currentTimeMillis();
		} catch (Exception e) {
			endTime = System.currentTimeMillis();
			org.slf4j.LoggerFactory.getLogger(this.getClass().getName()).error("INT-0020. " + e.getMessage());
			textoError = e.getMessage();
		}
		tiempoEjecucion = (double) ((endTime - startTime)/1000.0f);
		
		callBHUtils.guardarLlamadaBH(fechaHora, callBHUtils.getUsuario(), "getParametric", "Parametric", null, request, respuesta, tiempoEjecucion, textoError);
		
		return respuesta;
	}

	private List<DatoParametrica> consultarPorNombre(String nombre) {
		GetParametricRequest request = new GetParametricRequest();
		request.setNomTable(nombre);
		String textoError = null;
		long startTime = System.currentTimeMillis();
		long endTime = startTime;
		Double tiempoEjecucion = 0.0;
		Timestamp fechaHora = STDFunctions.systemDateTimeFun();	
		List<DatoParametrica> respuesta = new ArrayList<DatoParametrica>();
		try {
			respuesta = parametricas.getParametric(tokenUtils.getTokenValue(), "ID", IntegracionesUtils.getRequestDate(), request);
			endTime = System.currentTimeMillis();
		} catch (Exception e) {
			endTime = System.currentTimeMillis();
			org.slf4j.LoggerFactory.getLogger(this.getClass().getName()).error("INT-0030. " + e.getMessage());
			textoError = e.getMessage();
		}
		tiempoEjecucion = (double) ((endTime - startTime)/1000.0f);
		
		callBHUtils.guardarLlamadaBH(fechaHora, callBHUtils.getUsuario(), "getParametric", "Parametric", null, request, respuesta, tiempoEjecucion, textoError);
		
		return respuesta;
	}
	
	public List<DatoParametrica> getFormasPagoCORE() {
		return consultarPorNombre(nomTableMetodoPago);
	}
	public List<DatoParametrica> getEstadosCivilesCORE() {
		return consultarPorCodigo(codTableEstadoCivil);
	}
	public List<DatoParametrica> getDepartamentosCORE() {
		return consultarPorCodigo(codTableDepartamento);
	}
	public List<DatoParametrica> getEstadosContratoCORE() {
		return consultarPorCodigo(codTableEstadoContrato);
	}
	public List<DatoParametrica> getRegionalesCORE() {
		return consultarPorCodigo(codTableRegionales);
	}
	public List<DatoParametrica> getPaisesCORE() {
		return consultarPorCodigo(codTablePais);
	}
	public List<DatoParametrica> getEntidadesFinancierasCORE() {
		return consultarPorCodigo(codTableEntidadFinanciera);
	}
	public List<DatoParametrica> getTiposEntidadCORE() {
		return consultarPorCodigo(codTableTipoEntidad);
	}
	public List<DatoParametrica> getTiposInstitucionCORE() {
		return consultarPorCodigo(codTableTipoInstitucion);
	}
	public List<DatoParametrica> getProductosCORE() {
		return consultarPorCodigo(codTableProducto);
	}
	public List<DatoParametrica> getPlanesCORE() {
		return consultarPorCodigo(codTablePlan);
	}
	public List<DatoParametrica> getMunicipiosCORE() {
		return consultarPorCodigo(codTableMunicipio);
	}
	public List<DatoParametrica> getTiposDireccionCORE() {
		return consultarPorCodigo(codTableTipoDireccion);
	}
	public List<DatoParametrica> getEstadosPersonaCORE() {
		return consultarPorCodigo(codTableEstadoPersona);
	}
	public List<DatoParametrica> getEstadosBeneficiarioCORE() {
		return consultarPorCodigo(codTableEstadoBeneficiario);
	}
	public List<DatoParametrica> getTiposIdentificacionCORE() {
		return consultarPorCodigo(codTableTipoIdentificacion);
	}
	public List<DatoParametrica> getPeriodicidadesCORE() {
		return consultarPorCodigo(codTablePeriodicidad);
	}
	public List<DatoParametrica> getGruposAsociadosCORE() {
		return consultarPorCodigo(codTableGrupoAsociado);
	}
	public List<DatoParametrica> getTiposSucursalCORE() {
		return consultarPorCodigo(codTableTipoSucursal);
	}
	public List<DatoParametrica> getParentescosCORE() {
		return consultarPorCodigo(codTableParentesco);
	}	
	
	
	public static java.sql.Date parseDate(String formattedDate, String datePattern) throws ParseException {
		java.sql.Date date = null;
		formattedDate = formattedDate.replace("a.m.", "AM");
		formattedDate = formattedDate.replace("a. m.", "AM");
		formattedDate = formattedDate.replace("A.M.", "AM");
		formattedDate = formattedDate.replace("A. M.", "AM");
		formattedDate = formattedDate.replace("p.m.", "PM");
		formattedDate = formattedDate.replace("p. m.", "PM");
		formattedDate = formattedDate.replace("P.M.", "PM");
		formattedDate = formattedDate.replace("P. M.", "PM");
		if (formattedDate != null && !formattedDate.isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
			java.util.Date parsedDate = sdf.parse(formattedDate);
			date = new java.sql.Date(parsedDate.getTime());
		}
		return date;
	}
	public static String[] descomponerNombreCompleto(String nombreCompleto, boolean reverseOrder) {
		String nombre1 = "";
		String nombre2 = "";
		String apellido1 = "";
		String apellido2 = "";


		String[] partesNombre = nombreCompleto.split("\\s+", 4);

		if (reverseOrder) {
			nombre1 = partesNombre[0];
			if (partesNombre.length == 2) {
				nombre1 = partesNombre[1];
				apellido1 = partesNombre[0];
			}
			if (partesNombre.length == 3) {
				nombre1 = partesNombre[1];
				nombre2 = partesNombre[2];
				apellido1 = partesNombre[0];
			}
			if (partesNombre.length > 3) {
				nombre1 = partesNombre[2];
				nombre2 = partesNombre[3];
				apellido1 = partesNombre[0];
				apellido2 = partesNombre[1];
			}
		} else {
			nombre1 = partesNombre[0];
			if (partesNombre.length == 2) {
				apellido1 = partesNombre[1];
			}
			if (partesNombre.length > 2) {
				nombre2 = partesNombre[1];
				apellido1 = partesNombre[2];
			}
			if (partesNombre.length > 3) {
				apellido2 = partesNombre[3];
			}
		}

		String[] resultado = new String[4];
		resultado[0] = nombre1;
		resultado[1] = nombre2;
		resultado[2] = apellido1;
		resultado[3] = apellido2;
		return resultado;
	}

	public static String getDescripcionPorCodigo(List<DatoParametrica> parametricas, String codigo) {

		for(DatoParametrica dato  : parametricas) {
			if (dato.getCode().equalsIgnoreCase(codigo)) {
				return dato.getDescription();
			}
		}
		return null;
	}

}
