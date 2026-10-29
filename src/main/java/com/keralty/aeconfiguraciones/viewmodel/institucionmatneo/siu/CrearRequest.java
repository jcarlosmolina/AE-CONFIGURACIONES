package com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.InstitucionMatNeoConstants;
import com.keralty.aeconfiguraciones.persistence.Municipio;
import com.keralty.aeconfiguraciones.persistence.oid.MunicipioOid;
import com.keralty.aeconfiguraciones.persistence.oid.TarifaOid;
import com.keralty.aeconfiguraciones.persistence.Tarifa;
import com.keralty.aeconfiguraciones.persistence.TipoIdentificacion;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneDeserializer;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneSerializer;
import java.sql.Date;

public class CrearRequest {

    @JsonProperty("p_agrtarifa_oid")
    private TarifaOid institucionMatNeocrearpagrTarifa;
    
    @JsonIgnore
    private Tarifa institucionMatNeocrearpagrTarifaInstance;

    @JsonProperty("p_agrtipoidentificacion_json")
    private String institucionMatNeocrearpagrTipoIdentificacion;
    
    @JsonIgnore
    private TipoIdentificacion institucionMatNeocrearpagrTipoIdentificacionInstance;

    @JsonProperty("p_agrmunicipio_oid")
    private MunicipioOid institucionMatNeocrearpagrMunicipio;
    
    @JsonIgnore
    private Municipio institucionMatNeocrearpagrMunicipioInstance;

    @JsonProperty("p_atrnumidentificacion")
    private String institucionMatNeocrearpatrNumIdentificacion;

    @JsonProperty("p_atrnombre")
    private String institucionMatNeocrearpatrNombre;

    @JsonProperty("p_atrparamaternidad")
    private Boolean institucionMatNeocrearpatrParaMaternidad;

    @JsonProperty("p_atrparaneonatal")
    private Boolean institucionMatNeocrearpatrParaNeonatal;

    @JsonProperty("p_atrfechainicial")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date institucionMatNeocrearpatrFechaInicial;

    @JsonProperty("p_atrfechafinal")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date institucionMatNeocrearpatrFechaFinal;

    @JsonProperty("p_atrcodigo")
    private String institucionMatNeocrearpatrCodigo;

    public void checkArguments() throws NotNullArgumentException {
        checkInstitucionMatNeocrearpagrTarifa();
        checkInstitucionMatNeocrearpagrTipoIdentificacion();
        checkInstitucionMatNeocrearpagrMunicipio();
        checkInstitucionMatNeocrearpatrNumIdentificacion();
        checkInstitucionMatNeocrearpatrNombre();
        checkInstitucionMatNeocrearpatrParaMaternidad();
        checkInstitucionMatNeocrearpatrParaNeonatal();
        checkInstitucionMatNeocrearpatrFechaInicial();
        checkInstitucionMatNeocrearpatrFechaFinal();
        checkInstitucionMatNeocrearpatrCodigo();
    }

    @JsonIgnore
    public TarifaOid getInstitucionMatNeocrearpagrTarifa() {
        return this.institucionMatNeocrearpagrTarifa;
    }
    
    public void setInstitucionMatNeocrearpagrTarifa(TarifaOid institucionMatNeocrearpagrTarifa) {
        this.institucionMatNeocrearpagrTarifa = institucionMatNeocrearpagrTarifa;
    }

    @JsonIgnore
    public Tarifa getInstitucionMatNeocrearpagrTarifaInstance() {
        return this.institucionMatNeocrearpagrTarifaInstance;
    }
    
    public void setInstitucionMatNeocrearpagrTarifaInstance(Tarifa institucionMatNeocrearpagrTarifa) {
        this.institucionMatNeocrearpagrTarifaInstance = institucionMatNeocrearpagrTarifa;
        this.institucionMatNeocrearpagrTarifa = institucionMatNeocrearpagrTarifa != null ? institucionMatNeocrearpagrTarifa.getOid() : null;
    }

    private void checkInstitucionMatNeocrearpagrTarifa() throws NotNullArgumentException {
        if(this.getInstitucionMatNeocrearpagrTarifaInstance() == null || this.getInstitucionMatNeocrearpagrTarifaInstance().isNull()) {
            throw new NotNullArgumentException(InstitucionMatNeoConstants.SERV_ID_CREAR, InstitucionMatNeoConstants.CLASS_ID, InstitucionMatNeoConstants.ARG_ID_CREAR_INSTITUCIONMATNEOCREARPAGRTARIFA, InstitucionMatNeoConstants.SERV_ALIAS_CREAR, InstitucionMatNeoConstants.CLASS_ALIAS, InstitucionMatNeoConstants.ARG_ALIAS_CREAR_INSTITUCIONMATNEOCREARPAGRTARIFA);
        }
    }

    @JsonIgnore
    public String getInstitucionMatNeocrearpagrTipoIdentificacion() {
        return this.institucionMatNeocrearpagrTipoIdentificacion;
    }
    
    public void setInstitucionMatNeocrearpagrTipoIdentificacion(String institucionMatNeocrearpagrTipoIdentificacion) {
        this.institucionMatNeocrearpagrTipoIdentificacion = institucionMatNeocrearpagrTipoIdentificacion;
    }

    @JsonIgnore
    public TipoIdentificacion getInstitucionMatNeocrearpagrTipoIdentificacionInstance() {
        return this.institucionMatNeocrearpagrTipoIdentificacionInstance;
    }
    
    public void setInstitucionMatNeocrearpagrTipoIdentificacionInstance(TipoIdentificacion institucionMatNeocrearpagrTipoIdentificacion) {
        this.institucionMatNeocrearpagrTipoIdentificacionInstance = institucionMatNeocrearpagrTipoIdentificacion;
        this.institucionMatNeocrearpagrTipoIdentificacion = institucionMatNeocrearpagrTipoIdentificacion != null ? institucionMatNeocrearpagrTipoIdentificacion.getOid().toJson() : "";
    }

    private void checkInstitucionMatNeocrearpagrTipoIdentificacion() throws NotNullArgumentException {
        if(this.getInstitucionMatNeocrearpagrTipoIdentificacionInstance() == null || this.getInstitucionMatNeocrearpagrTipoIdentificacionInstance().isNull()) {
            throw new NotNullArgumentException(InstitucionMatNeoConstants.SERV_ID_CREAR, InstitucionMatNeoConstants.CLASS_ID, InstitucionMatNeoConstants.ARG_ID_CREAR_INSTITUCIONMATNEOCREARPAGRTIPOIDENTIFICACION, InstitucionMatNeoConstants.SERV_ALIAS_CREAR, InstitucionMatNeoConstants.CLASS_ALIAS, InstitucionMatNeoConstants.ARG_ALIAS_CREAR_INSTITUCIONMATNEOCREARPAGRTIPOIDENTIFICACION);
        }
    }

    @JsonIgnore
    public MunicipioOid getInstitucionMatNeocrearpagrMunicipio() {
        return this.institucionMatNeocrearpagrMunicipio;
    }
    
    public void setInstitucionMatNeocrearpagrMunicipio(MunicipioOid institucionMatNeocrearpagrMunicipio) {
        this.institucionMatNeocrearpagrMunicipio = institucionMatNeocrearpagrMunicipio;
    }

    @JsonIgnore
    public Municipio getInstitucionMatNeocrearpagrMunicipioInstance() {
        return this.institucionMatNeocrearpagrMunicipioInstance;
    }
    
    public void setInstitucionMatNeocrearpagrMunicipioInstance(Municipio institucionMatNeocrearpagrMunicipio) {
        this.institucionMatNeocrearpagrMunicipioInstance = institucionMatNeocrearpagrMunicipio;
        this.institucionMatNeocrearpagrMunicipio = institucionMatNeocrearpagrMunicipio != null ? institucionMatNeocrearpagrMunicipio.getOid() : null;
    }

    private void checkInstitucionMatNeocrearpagrMunicipio() throws NotNullArgumentException {
        if(this.getInstitucionMatNeocrearpagrMunicipioInstance() == null || this.getInstitucionMatNeocrearpagrMunicipioInstance().isNull()) {
            throw new NotNullArgumentException(InstitucionMatNeoConstants.SERV_ID_CREAR, InstitucionMatNeoConstants.CLASS_ID, InstitucionMatNeoConstants.ARG_ID_CREAR_INSTITUCIONMATNEOCREARPAGRMUNICIPIO, InstitucionMatNeoConstants.SERV_ALIAS_CREAR, InstitucionMatNeoConstants.CLASS_ALIAS, InstitucionMatNeoConstants.ARG_ALIAS_CREAR_INSTITUCIONMATNEOCREARPAGRMUNICIPIO);
        }
    }

    @JsonIgnore
    public String getInstitucionMatNeocrearpatrNumIdentificacion() {
        return this.institucionMatNeocrearpatrNumIdentificacion;
    }
    
    public void setInstitucionMatNeocrearpatrNumIdentificacion(String institucionMatNeocrearpatrNumIdentificacion) {
        this.institucionMatNeocrearpatrNumIdentificacion = institucionMatNeocrearpatrNumIdentificacion;
    }

    private void checkInstitucionMatNeocrearpatrNumIdentificacion() throws NotNullArgumentException {
        if(this.getInstitucionMatNeocrearpatrNumIdentificacion() == null) {
            throw new NotNullArgumentException(InstitucionMatNeoConstants.SERV_ID_CREAR, InstitucionMatNeoConstants.CLASS_ID, InstitucionMatNeoConstants.ARG_ID_CREAR_INSTITUCIONMATNEOCREARPATRNUMIDENTIFICACION, InstitucionMatNeoConstants.SERV_ALIAS_CREAR, InstitucionMatNeoConstants.CLASS_ALIAS, InstitucionMatNeoConstants.ARG_ALIAS_CREAR_INSTITUCIONMATNEOCREARPATRNUMIDENTIFICACION);
        }
    }

    @JsonIgnore
    public String getInstitucionMatNeocrearpatrNombre() {
        return this.institucionMatNeocrearpatrNombre;
    }
    
    public void setInstitucionMatNeocrearpatrNombre(String institucionMatNeocrearpatrNombre) {
        this.institucionMatNeocrearpatrNombre = institucionMatNeocrearpatrNombre;
    }

    private void checkInstitucionMatNeocrearpatrNombre() throws NotNullArgumentException {
        if(this.getInstitucionMatNeocrearpatrNombre() == null) {
            throw new NotNullArgumentException(InstitucionMatNeoConstants.SERV_ID_CREAR, InstitucionMatNeoConstants.CLASS_ID, InstitucionMatNeoConstants.ARG_ID_CREAR_INSTITUCIONMATNEOCREARPATRNOMBRE, InstitucionMatNeoConstants.SERV_ALIAS_CREAR, InstitucionMatNeoConstants.CLASS_ALIAS, InstitucionMatNeoConstants.ARG_ALIAS_CREAR_INSTITUCIONMATNEOCREARPATRNOMBRE);
        }
    }

    @JsonIgnore
    public Boolean getInstitucionMatNeocrearpatrParaMaternidad() {
        return this.institucionMatNeocrearpatrParaMaternidad;
    }
    
    public void setInstitucionMatNeocrearpatrParaMaternidad(Boolean institucionMatNeocrearpatrParaMaternidad) {
        this.institucionMatNeocrearpatrParaMaternidad = institucionMatNeocrearpatrParaMaternidad;
    }

    private void checkInstitucionMatNeocrearpatrParaMaternidad() throws NotNullArgumentException {
        if(this.getInstitucionMatNeocrearpatrParaMaternidad() == null) {
            throw new NotNullArgumentException(InstitucionMatNeoConstants.SERV_ID_CREAR, InstitucionMatNeoConstants.CLASS_ID, InstitucionMatNeoConstants.ARG_ID_CREAR_INSTITUCIONMATNEOCREARPATRPARAMATERNIDAD, InstitucionMatNeoConstants.SERV_ALIAS_CREAR, InstitucionMatNeoConstants.CLASS_ALIAS, InstitucionMatNeoConstants.ARG_ALIAS_CREAR_INSTITUCIONMATNEOCREARPATRPARAMATERNIDAD);
        }
    }

    @JsonIgnore
    public Boolean getInstitucionMatNeocrearpatrParaNeonatal() {
        return this.institucionMatNeocrearpatrParaNeonatal;
    }
    
    public void setInstitucionMatNeocrearpatrParaNeonatal(Boolean institucionMatNeocrearpatrParaNeonatal) {
        this.institucionMatNeocrearpatrParaNeonatal = institucionMatNeocrearpatrParaNeonatal;
    }

    private void checkInstitucionMatNeocrearpatrParaNeonatal() throws NotNullArgumentException {
        if(this.getInstitucionMatNeocrearpatrParaNeonatal() == null) {
            throw new NotNullArgumentException(InstitucionMatNeoConstants.SERV_ID_CREAR, InstitucionMatNeoConstants.CLASS_ID, InstitucionMatNeoConstants.ARG_ID_CREAR_INSTITUCIONMATNEOCREARPATRPARANEONATAL, InstitucionMatNeoConstants.SERV_ALIAS_CREAR, InstitucionMatNeoConstants.CLASS_ALIAS, InstitucionMatNeoConstants.ARG_ALIAS_CREAR_INSTITUCIONMATNEOCREARPATRPARANEONATAL);
        }
    }

    @JsonIgnore
    public Date getInstitucionMatNeocrearpatrFechaInicial() {
        return this.institucionMatNeocrearpatrFechaInicial;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setInstitucionMatNeocrearpatrFechaInicial(Date institucionMatNeocrearpatrFechaInicial) {
        this.institucionMatNeocrearpatrFechaInicial = institucionMatNeocrearpatrFechaInicial;
    }

    private void checkInstitucionMatNeocrearpatrFechaInicial() throws NotNullArgumentException {
        if(this.getInstitucionMatNeocrearpatrFechaInicial() == null) {
            throw new NotNullArgumentException(InstitucionMatNeoConstants.SERV_ID_CREAR, InstitucionMatNeoConstants.CLASS_ID, InstitucionMatNeoConstants.ARG_ID_CREAR_INSTITUCIONMATNEOCREARPATRFECHAINICIAL, InstitucionMatNeoConstants.SERV_ALIAS_CREAR, InstitucionMatNeoConstants.CLASS_ALIAS, InstitucionMatNeoConstants.ARG_ALIAS_CREAR_INSTITUCIONMATNEOCREARPATRFECHAINICIAL);
        }
    }

    @JsonIgnore
    public Date getInstitucionMatNeocrearpatrFechaFinal() {
        return this.institucionMatNeocrearpatrFechaFinal;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setInstitucionMatNeocrearpatrFechaFinal(Date institucionMatNeocrearpatrFechaFinal) {
        this.institucionMatNeocrearpatrFechaFinal = institucionMatNeocrearpatrFechaFinal;
    }

    private void checkInstitucionMatNeocrearpatrFechaFinal() throws NotNullArgumentException {
        if(this.getInstitucionMatNeocrearpatrFechaFinal() == null) {
            throw new NotNullArgumentException(InstitucionMatNeoConstants.SERV_ID_CREAR, InstitucionMatNeoConstants.CLASS_ID, InstitucionMatNeoConstants.ARG_ID_CREAR_INSTITUCIONMATNEOCREARPATRFECHAFINAL, InstitucionMatNeoConstants.SERV_ALIAS_CREAR, InstitucionMatNeoConstants.CLASS_ALIAS, InstitucionMatNeoConstants.ARG_ALIAS_CREAR_INSTITUCIONMATNEOCREARPATRFECHAFINAL);
        }
    }

    @JsonIgnore
    public String getInstitucionMatNeocrearpatrCodigo() {
        return this.institucionMatNeocrearpatrCodigo;
    }
    
    public void setInstitucionMatNeocrearpatrCodigo(String institucionMatNeocrearpatrCodigo) {
        this.institucionMatNeocrearpatrCodigo = institucionMatNeocrearpatrCodigo;
    }

    private void checkInstitucionMatNeocrearpatrCodigo() throws NotNullArgumentException {
        if(this.getInstitucionMatNeocrearpatrCodigo() == null) {
            throw new NotNullArgumentException(InstitucionMatNeoConstants.SERV_ID_CREAR, InstitucionMatNeoConstants.CLASS_ID, InstitucionMatNeoConstants.ARG_ID_CREAR_INSTITUCIONMATNEOCREARPATRCODIGO, InstitucionMatNeoConstants.SERV_ALIAS_CREAR, InstitucionMatNeoConstants.CLASS_ALIAS, InstitucionMatNeoConstants.ARG_ALIAS_CREAR_INSTITUCIONMATNEOCREARPATRCODIGO);
        }
    }
}
