package com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.InstitucionMatNeoConstants;
import com.keralty.aeconfiguraciones.persistence.InstitucionMatNeo;
import com.keralty.aeconfiguraciones.persistence.Municipio;
import com.keralty.aeconfiguraciones.persistence.oid.InstitucionMatNeoOid;
import com.keralty.aeconfiguraciones.persistence.oid.MunicipioOid;
import com.keralty.aeconfiguraciones.persistence.oid.TarifaOid;
import com.keralty.aeconfiguraciones.persistence.Tarifa;
import com.keralty.aeconfiguraciones.persistence.TipoIdentificacion;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneDeserializer;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneSerializer;
import java.sql.Date;

public class TMODIFICARRequest {

    @JsonProperty("p_thisinstitucionmatneo_oid")
    private InstitucionMatNeoOid institucionMatNeoTMODIFICARpthisInstitucionMatNeo;
    
    @JsonIgnore
    private InstitucionMatNeo institucionMatNeoTMODIFICARpthisInstitucionMatNeoInstance;

    @JsonProperty("pt_p_evcmunicipio_oid")
    private MunicipioOid institucionMatNeoTMODIFICARptpevcMunicipio;
    
    @JsonIgnore
    private Municipio institucionMatNeoTMODIFICARptpevcMunicipioInstance;

    @JsonProperty("pt_p_evctarifa_oid")
    private TarifaOid institucionMatNeoTMODIFICARptpevcTarifa;
    
    @JsonIgnore
    private Tarifa institucionMatNeoTMODIFICARptpevcTarifaInstance;

    @JsonProperty("pt_p_evctipoidentificacion_json")
    private String institucionMatNeoTMODIFICARptpevcTipoIdentificacion;
    
    @JsonIgnore
    private TipoIdentificacion institucionMatNeoTMODIFICARptpevcTipoIdentificacionInstance;

    @JsonProperty("pt_p_numidentificacion")
    private String institucionMatNeoTMODIFICARptpNumIdentificacion;

    @JsonProperty("pt_p_nombre")
    private String institucionMatNeoTMODIFICARptpNombre;

    @JsonProperty("pt_p_paramaternidad")
    private Boolean institucionMatNeoTMODIFICARptpParaMaternidad;

    @JsonProperty("pt_p_paraneonatal")
    private Boolean institucionMatNeoTMODIFICARptpParaNeonatal;

    @JsonProperty("pt_p_fechainicial")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date institucionMatNeoTMODIFICARptpFechaInicial;

    @JsonProperty("pt_p_fechafinal")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date institucionMatNeoTMODIFICARptpFechaFinal;

    public void checkArguments() throws NotNullArgumentException {
        checkInstitucionMatNeoTMODIFICARpthisInstitucionMatNeo();
        checkInstitucionMatNeoTMODIFICARptpevcMunicipio();
        checkInstitucionMatNeoTMODIFICARptpevcTarifa();
        checkInstitucionMatNeoTMODIFICARptpevcTipoIdentificacion();
        checkInstitucionMatNeoTMODIFICARptpNumIdentificacion();
        checkInstitucionMatNeoTMODIFICARptpNombre();
        checkInstitucionMatNeoTMODIFICARptpParaMaternidad();
        checkInstitucionMatNeoTMODIFICARptpParaNeonatal();
        checkInstitucionMatNeoTMODIFICARptpFechaInicial();
        checkInstitucionMatNeoTMODIFICARptpFechaFinal();
    }

    @JsonIgnore
    public InstitucionMatNeoOid getInstitucionMatNeoTMODIFICARpthisInstitucionMatNeo() {
        return this.institucionMatNeoTMODIFICARpthisInstitucionMatNeo;
    }
    
    public void setInstitucionMatNeoTMODIFICARpthisInstitucionMatNeo(InstitucionMatNeoOid institucionMatNeoTMODIFICARpthisInstitucionMatNeo) {
        this.institucionMatNeoTMODIFICARpthisInstitucionMatNeo = institucionMatNeoTMODIFICARpthisInstitucionMatNeo;
    }

    @JsonIgnore
    public InstitucionMatNeo getInstitucionMatNeoTMODIFICARpthisInstitucionMatNeoInstance() {
        return this.institucionMatNeoTMODIFICARpthisInstitucionMatNeoInstance;
    }
    
    public void setInstitucionMatNeoTMODIFICARpthisInstitucionMatNeoInstance(InstitucionMatNeo institucionMatNeoTMODIFICARpthisInstitucionMatNeo) {
        this.institucionMatNeoTMODIFICARpthisInstitucionMatNeoInstance = institucionMatNeoTMODIFICARpthisInstitucionMatNeo;
        this.institucionMatNeoTMODIFICARpthisInstitucionMatNeo = institucionMatNeoTMODIFICARpthisInstitucionMatNeo != null ? institucionMatNeoTMODIFICARpthisInstitucionMatNeo.getOid() : null;
    }

    private void checkInstitucionMatNeoTMODIFICARpthisInstitucionMatNeo() throws NotNullArgumentException {
        if(this.getInstitucionMatNeoTMODIFICARpthisInstitucionMatNeoInstance() == null || this.getInstitucionMatNeoTMODIFICARpthisInstitucionMatNeoInstance().isNull()) {
            throw new NotNullArgumentException(InstitucionMatNeoConstants.SERV_ID_TMODIFICAR, InstitucionMatNeoConstants.CLASS_ID, InstitucionMatNeoConstants.ARG_ID_TMODIFICAR_INSTITUCIONMATNEOTMODIFICARPTHISINSTITUCIONMATNEO, InstitucionMatNeoConstants.SERV_ALIAS_TMODIFICAR, InstitucionMatNeoConstants.CLASS_ALIAS, InstitucionMatNeoConstants.ARG_ALIAS_TMODIFICAR_INSTITUCIONMATNEOTMODIFICARPTHISINSTITUCIONMATNEO);
        }
    }

    @JsonIgnore
    public MunicipioOid getInstitucionMatNeoTMODIFICARptpevcMunicipio() {
        return this.institucionMatNeoTMODIFICARptpevcMunicipio;
    }
    
    public void setInstitucionMatNeoTMODIFICARptpevcMunicipio(MunicipioOid institucionMatNeoTMODIFICARptpevcMunicipio) {
        this.institucionMatNeoTMODIFICARptpevcMunicipio = institucionMatNeoTMODIFICARptpevcMunicipio;
    }

    @JsonIgnore
    public Municipio getInstitucionMatNeoTMODIFICARptpevcMunicipioInstance() {
        return this.institucionMatNeoTMODIFICARptpevcMunicipioInstance;
    }
    
    public void setInstitucionMatNeoTMODIFICARptpevcMunicipioInstance(Municipio institucionMatNeoTMODIFICARptpevcMunicipio) {
        this.institucionMatNeoTMODIFICARptpevcMunicipioInstance = institucionMatNeoTMODIFICARptpevcMunicipio;
        this.institucionMatNeoTMODIFICARptpevcMunicipio = institucionMatNeoTMODIFICARptpevcMunicipio != null ? institucionMatNeoTMODIFICARptpevcMunicipio.getOid() : null;
    }

    private void checkInstitucionMatNeoTMODIFICARptpevcMunicipio() throws NotNullArgumentException {
        if(this.getInstitucionMatNeoTMODIFICARptpevcMunicipioInstance() == null || this.getInstitucionMatNeoTMODIFICARptpevcMunicipioInstance().isNull()) {
            throw new NotNullArgumentException(InstitucionMatNeoConstants.SERV_ID_TMODIFICAR, InstitucionMatNeoConstants.CLASS_ID, InstitucionMatNeoConstants.ARG_ID_TMODIFICAR_INSTITUCIONMATNEOTMODIFICARPTPEVCMUNICIPIO, InstitucionMatNeoConstants.SERV_ALIAS_TMODIFICAR, InstitucionMatNeoConstants.CLASS_ALIAS, InstitucionMatNeoConstants.ARG_ALIAS_TMODIFICAR_INSTITUCIONMATNEOTMODIFICARPTPEVCMUNICIPIO);
        }
    }

    @JsonIgnore
    public TarifaOid getInstitucionMatNeoTMODIFICARptpevcTarifa() {
        return this.institucionMatNeoTMODIFICARptpevcTarifa;
    }
    
    public void setInstitucionMatNeoTMODIFICARptpevcTarifa(TarifaOid institucionMatNeoTMODIFICARptpevcTarifa) {
        this.institucionMatNeoTMODIFICARptpevcTarifa = institucionMatNeoTMODIFICARptpevcTarifa;
    }

    @JsonIgnore
    public Tarifa getInstitucionMatNeoTMODIFICARptpevcTarifaInstance() {
        return this.institucionMatNeoTMODIFICARptpevcTarifaInstance;
    }
    
    public void setInstitucionMatNeoTMODIFICARptpevcTarifaInstance(Tarifa institucionMatNeoTMODIFICARptpevcTarifa) {
        this.institucionMatNeoTMODIFICARptpevcTarifaInstance = institucionMatNeoTMODIFICARptpevcTarifa;
        this.institucionMatNeoTMODIFICARptpevcTarifa = institucionMatNeoTMODIFICARptpevcTarifa != null ? institucionMatNeoTMODIFICARptpevcTarifa.getOid() : null;
    }

    private void checkInstitucionMatNeoTMODIFICARptpevcTarifa() throws NotNullArgumentException {
        if(this.getInstitucionMatNeoTMODIFICARptpevcTarifaInstance() == null || this.getInstitucionMatNeoTMODIFICARptpevcTarifaInstance().isNull()) {
            throw new NotNullArgumentException(InstitucionMatNeoConstants.SERV_ID_TMODIFICAR, InstitucionMatNeoConstants.CLASS_ID, InstitucionMatNeoConstants.ARG_ID_TMODIFICAR_INSTITUCIONMATNEOTMODIFICARPTPEVCTARIFA, InstitucionMatNeoConstants.SERV_ALIAS_TMODIFICAR, InstitucionMatNeoConstants.CLASS_ALIAS, InstitucionMatNeoConstants.ARG_ALIAS_TMODIFICAR_INSTITUCIONMATNEOTMODIFICARPTPEVCTARIFA);
        }
    }

    @JsonIgnore
    public String getInstitucionMatNeoTMODIFICARptpevcTipoIdentificacion() {
        return this.institucionMatNeoTMODIFICARptpevcTipoIdentificacion;
    }
    
    public void setInstitucionMatNeoTMODIFICARptpevcTipoIdentificacion(String institucionMatNeoTMODIFICARptpevcTipoIdentificacion) {
        this.institucionMatNeoTMODIFICARptpevcTipoIdentificacion = institucionMatNeoTMODIFICARptpevcTipoIdentificacion;
    }

    @JsonIgnore
    public TipoIdentificacion getInstitucionMatNeoTMODIFICARptpevcTipoIdentificacionInstance() {
        return this.institucionMatNeoTMODIFICARptpevcTipoIdentificacionInstance;
    }
    
    public void setInstitucionMatNeoTMODIFICARptpevcTipoIdentificacionInstance(TipoIdentificacion institucionMatNeoTMODIFICARptpevcTipoIdentificacion) {
        this.institucionMatNeoTMODIFICARptpevcTipoIdentificacionInstance = institucionMatNeoTMODIFICARptpevcTipoIdentificacion;
        this.institucionMatNeoTMODIFICARptpevcTipoIdentificacion = institucionMatNeoTMODIFICARptpevcTipoIdentificacion != null ? institucionMatNeoTMODIFICARptpevcTipoIdentificacion.getOid().toJson() : "";
    }

    private void checkInstitucionMatNeoTMODIFICARptpevcTipoIdentificacion() throws NotNullArgumentException {
        if(this.getInstitucionMatNeoTMODIFICARptpevcTipoIdentificacionInstance() == null || this.getInstitucionMatNeoTMODIFICARptpevcTipoIdentificacionInstance().isNull()) {
            throw new NotNullArgumentException(InstitucionMatNeoConstants.SERV_ID_TMODIFICAR, InstitucionMatNeoConstants.CLASS_ID, InstitucionMatNeoConstants.ARG_ID_TMODIFICAR_INSTITUCIONMATNEOTMODIFICARPTPEVCTIPOIDENTIFICACION, InstitucionMatNeoConstants.SERV_ALIAS_TMODIFICAR, InstitucionMatNeoConstants.CLASS_ALIAS, InstitucionMatNeoConstants.ARG_ALIAS_TMODIFICAR_INSTITUCIONMATNEOTMODIFICARPTPEVCTIPOIDENTIFICACION);
        }
    }

    @JsonIgnore
    public String getInstitucionMatNeoTMODIFICARptpNumIdentificacion() {
        return this.institucionMatNeoTMODIFICARptpNumIdentificacion;
    }
    
    public void setInstitucionMatNeoTMODIFICARptpNumIdentificacion(String institucionMatNeoTMODIFICARptpNumIdentificacion) {
        this.institucionMatNeoTMODIFICARptpNumIdentificacion = institucionMatNeoTMODIFICARptpNumIdentificacion;
    }

    private void checkInstitucionMatNeoTMODIFICARptpNumIdentificacion() throws NotNullArgumentException {
        if(this.getInstitucionMatNeoTMODIFICARptpNumIdentificacion() == null) {
            throw new NotNullArgumentException(InstitucionMatNeoConstants.SERV_ID_TMODIFICAR, InstitucionMatNeoConstants.CLASS_ID, InstitucionMatNeoConstants.ARG_ID_TMODIFICAR_INSTITUCIONMATNEOTMODIFICARPTPNUMIDENTIFICACION, InstitucionMatNeoConstants.SERV_ALIAS_TMODIFICAR, InstitucionMatNeoConstants.CLASS_ALIAS, InstitucionMatNeoConstants.ARG_ALIAS_TMODIFICAR_INSTITUCIONMATNEOTMODIFICARPTPNUMIDENTIFICACION);
        }
    }

    @JsonIgnore
    public String getInstitucionMatNeoTMODIFICARptpNombre() {
        return this.institucionMatNeoTMODIFICARptpNombre;
    }
    
    public void setInstitucionMatNeoTMODIFICARptpNombre(String institucionMatNeoTMODIFICARptpNombre) {
        this.institucionMatNeoTMODIFICARptpNombre = institucionMatNeoTMODIFICARptpNombre;
    }

    private void checkInstitucionMatNeoTMODIFICARptpNombre() throws NotNullArgumentException {
        if(this.getInstitucionMatNeoTMODIFICARptpNombre() == null) {
            throw new NotNullArgumentException(InstitucionMatNeoConstants.SERV_ID_TMODIFICAR, InstitucionMatNeoConstants.CLASS_ID, InstitucionMatNeoConstants.ARG_ID_TMODIFICAR_INSTITUCIONMATNEOTMODIFICARPTPNOMBRE, InstitucionMatNeoConstants.SERV_ALIAS_TMODIFICAR, InstitucionMatNeoConstants.CLASS_ALIAS, InstitucionMatNeoConstants.ARG_ALIAS_TMODIFICAR_INSTITUCIONMATNEOTMODIFICARPTPNOMBRE);
        }
    }

    @JsonIgnore
    public Boolean getInstitucionMatNeoTMODIFICARptpParaMaternidad() {
        return this.institucionMatNeoTMODIFICARptpParaMaternidad;
    }
    
    public void setInstitucionMatNeoTMODIFICARptpParaMaternidad(Boolean institucionMatNeoTMODIFICARptpParaMaternidad) {
        this.institucionMatNeoTMODIFICARptpParaMaternidad = institucionMatNeoTMODIFICARptpParaMaternidad;
    }

    private void checkInstitucionMatNeoTMODIFICARptpParaMaternidad() throws NotNullArgumentException {
        if(this.getInstitucionMatNeoTMODIFICARptpParaMaternidad() == null) {
            throw new NotNullArgumentException(InstitucionMatNeoConstants.SERV_ID_TMODIFICAR, InstitucionMatNeoConstants.CLASS_ID, InstitucionMatNeoConstants.ARG_ID_TMODIFICAR_INSTITUCIONMATNEOTMODIFICARPTPPARAMATERNIDAD, InstitucionMatNeoConstants.SERV_ALIAS_TMODIFICAR, InstitucionMatNeoConstants.CLASS_ALIAS, InstitucionMatNeoConstants.ARG_ALIAS_TMODIFICAR_INSTITUCIONMATNEOTMODIFICARPTPPARAMATERNIDAD);
        }
    }

    @JsonIgnore
    public Boolean getInstitucionMatNeoTMODIFICARptpParaNeonatal() {
        return this.institucionMatNeoTMODIFICARptpParaNeonatal;
    }
    
    public void setInstitucionMatNeoTMODIFICARptpParaNeonatal(Boolean institucionMatNeoTMODIFICARptpParaNeonatal) {
        this.institucionMatNeoTMODIFICARptpParaNeonatal = institucionMatNeoTMODIFICARptpParaNeonatal;
    }

    private void checkInstitucionMatNeoTMODIFICARptpParaNeonatal() throws NotNullArgumentException {
        if(this.getInstitucionMatNeoTMODIFICARptpParaNeonatal() == null) {
            throw new NotNullArgumentException(InstitucionMatNeoConstants.SERV_ID_TMODIFICAR, InstitucionMatNeoConstants.CLASS_ID, InstitucionMatNeoConstants.ARG_ID_TMODIFICAR_INSTITUCIONMATNEOTMODIFICARPTPPARANEONATAL, InstitucionMatNeoConstants.SERV_ALIAS_TMODIFICAR, InstitucionMatNeoConstants.CLASS_ALIAS, InstitucionMatNeoConstants.ARG_ALIAS_TMODIFICAR_INSTITUCIONMATNEOTMODIFICARPTPPARANEONATAL);
        }
    }

    @JsonIgnore
    public Date getInstitucionMatNeoTMODIFICARptpFechaInicial() {
        return this.institucionMatNeoTMODIFICARptpFechaInicial;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setInstitucionMatNeoTMODIFICARptpFechaInicial(Date institucionMatNeoTMODIFICARptpFechaInicial) {
        this.institucionMatNeoTMODIFICARptpFechaInicial = institucionMatNeoTMODIFICARptpFechaInicial;
    }

    private void checkInstitucionMatNeoTMODIFICARptpFechaInicial() throws NotNullArgumentException {
        if(this.getInstitucionMatNeoTMODIFICARptpFechaInicial() == null) {
            throw new NotNullArgumentException(InstitucionMatNeoConstants.SERV_ID_TMODIFICAR, InstitucionMatNeoConstants.CLASS_ID, InstitucionMatNeoConstants.ARG_ID_TMODIFICAR_INSTITUCIONMATNEOTMODIFICARPTPFECHAINICIAL, InstitucionMatNeoConstants.SERV_ALIAS_TMODIFICAR, InstitucionMatNeoConstants.CLASS_ALIAS, InstitucionMatNeoConstants.ARG_ALIAS_TMODIFICAR_INSTITUCIONMATNEOTMODIFICARPTPFECHAINICIAL);
        }
    }

    @JsonIgnore
    public Date getInstitucionMatNeoTMODIFICARptpFechaFinal() {
        return this.institucionMatNeoTMODIFICARptpFechaFinal;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setInstitucionMatNeoTMODIFICARptpFechaFinal(Date institucionMatNeoTMODIFICARptpFechaFinal) {
        this.institucionMatNeoTMODIFICARptpFechaFinal = institucionMatNeoTMODIFICARptpFechaFinal;
    }

    private void checkInstitucionMatNeoTMODIFICARptpFechaFinal() throws NotNullArgumentException {
        if(this.getInstitucionMatNeoTMODIFICARptpFechaFinal() == null) {
            throw new NotNullArgumentException(InstitucionMatNeoConstants.SERV_ID_TMODIFICAR, InstitucionMatNeoConstants.CLASS_ID, InstitucionMatNeoConstants.ARG_ID_TMODIFICAR_INSTITUCIONMATNEOTMODIFICARPTPFECHAFINAL, InstitucionMatNeoConstants.SERV_ALIAS_TMODIFICAR, InstitucionMatNeoConstants.CLASS_ALIAS, InstitucionMatNeoConstants.ARG_ALIAS_TMODIFICAR_INSTITUCIONMATNEOTMODIFICARPTPFECHAFINAL);
        }
    }
}
