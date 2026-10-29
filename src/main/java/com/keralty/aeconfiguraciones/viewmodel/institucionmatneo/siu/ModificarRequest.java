package com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.InstitucionMatNeoConstants;
import com.keralty.aeconfiguraciones.persistence.InstitucionMatNeo;
import com.keralty.aeconfiguraciones.persistence.oid.InstitucionMatNeoOid;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneDeserializer;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneSerializer;
import java.sql.Date;

public class ModificarRequest {

    @JsonProperty("p_thisinstitucionmatneo_oid")
    private InstitucionMatNeoOid institucionMatNeomodificarpthisInstitucionMatNeo;
    
    @JsonIgnore
    private InstitucionMatNeo institucionMatNeomodificarpthisInstitucionMatNeoInstance;

    @JsonProperty("p_numidentificacion")
    private String institucionMatNeomodificarpNumIdentificacion;

    @JsonProperty("p_nombre")
    private String institucionMatNeomodificarpNombre;

    @JsonProperty("p_paramaternidad")
    private Boolean institucionMatNeomodificarpParaMaternidad;

    @JsonProperty("p_paraneonatal")
    private Boolean institucionMatNeomodificarpParaNeonatal;

    @JsonProperty("p_fechainicial")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date institucionMatNeomodificarpFechaInicial;

    @JsonProperty("p_fechafinal")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date institucionMatNeomodificarpFechaFinal;

    public void checkArguments() throws NotNullArgumentException {
        checkInstitucionMatNeomodificarpthisInstitucionMatNeo();
        checkInstitucionMatNeomodificarpNumIdentificacion();
        checkInstitucionMatNeomodificarpNombre();
        checkInstitucionMatNeomodificarpParaMaternidad();
        checkInstitucionMatNeomodificarpParaNeonatal();
        checkInstitucionMatNeomodificarpFechaInicial();
        checkInstitucionMatNeomodificarpFechaFinal();
    }

    @JsonIgnore
    public InstitucionMatNeoOid getInstitucionMatNeomodificarpthisInstitucionMatNeo() {
        return this.institucionMatNeomodificarpthisInstitucionMatNeo;
    }
    
    public void setInstitucionMatNeomodificarpthisInstitucionMatNeo(InstitucionMatNeoOid institucionMatNeomodificarpthisInstitucionMatNeo) {
        this.institucionMatNeomodificarpthisInstitucionMatNeo = institucionMatNeomodificarpthisInstitucionMatNeo;
    }

    @JsonIgnore
    public InstitucionMatNeo getInstitucionMatNeomodificarpthisInstitucionMatNeoInstance() {
        return this.institucionMatNeomodificarpthisInstitucionMatNeoInstance;
    }
    
    public void setInstitucionMatNeomodificarpthisInstitucionMatNeoInstance(InstitucionMatNeo institucionMatNeomodificarpthisInstitucionMatNeo) {
        this.institucionMatNeomodificarpthisInstitucionMatNeoInstance = institucionMatNeomodificarpthisInstitucionMatNeo;
        this.institucionMatNeomodificarpthisInstitucionMatNeo = institucionMatNeomodificarpthisInstitucionMatNeo != null ? institucionMatNeomodificarpthisInstitucionMatNeo.getOid() : null;
    }

    private void checkInstitucionMatNeomodificarpthisInstitucionMatNeo() throws NotNullArgumentException {
        if(this.getInstitucionMatNeomodificarpthisInstitucionMatNeoInstance() == null || this.getInstitucionMatNeomodificarpthisInstitucionMatNeoInstance().isNull()) {
            throw new NotNullArgumentException(InstitucionMatNeoConstants.SERV_ID_MODIFICAR, InstitucionMatNeoConstants.CLASS_ID, InstitucionMatNeoConstants.ARG_ID_MODIFICAR_INSTITUCIONMATNEOMODIFICARPTHISINSTITUCIONMATNEO, InstitucionMatNeoConstants.SERV_ALIAS_MODIFICAR, InstitucionMatNeoConstants.CLASS_ALIAS, InstitucionMatNeoConstants.ARG_ALIAS_MODIFICAR_INSTITUCIONMATNEOMODIFICARPTHISINSTITUCIONMATNEO);
        }
    }

    @JsonIgnore
    public String getInstitucionMatNeomodificarpNumIdentificacion() {
        return this.institucionMatNeomodificarpNumIdentificacion;
    }
    
    public void setInstitucionMatNeomodificarpNumIdentificacion(String institucionMatNeomodificarpNumIdentificacion) {
        this.institucionMatNeomodificarpNumIdentificacion = institucionMatNeomodificarpNumIdentificacion;
    }

    private void checkInstitucionMatNeomodificarpNumIdentificacion() throws NotNullArgumentException {
        if(this.getInstitucionMatNeomodificarpNumIdentificacion() == null) {
            throw new NotNullArgumentException(InstitucionMatNeoConstants.SERV_ID_MODIFICAR, InstitucionMatNeoConstants.CLASS_ID, InstitucionMatNeoConstants.ARG_ID_MODIFICAR_INSTITUCIONMATNEOMODIFICARPNUMIDENTIFICACION, InstitucionMatNeoConstants.SERV_ALIAS_MODIFICAR, InstitucionMatNeoConstants.CLASS_ALIAS, InstitucionMatNeoConstants.ARG_ALIAS_MODIFICAR_INSTITUCIONMATNEOMODIFICARPNUMIDENTIFICACION);
        }
    }

    @JsonIgnore
    public String getInstitucionMatNeomodificarpNombre() {
        return this.institucionMatNeomodificarpNombre;
    }
    
    public void setInstitucionMatNeomodificarpNombre(String institucionMatNeomodificarpNombre) {
        this.institucionMatNeomodificarpNombre = institucionMatNeomodificarpNombre;
    }

    private void checkInstitucionMatNeomodificarpNombre() throws NotNullArgumentException {
        if(this.getInstitucionMatNeomodificarpNombre() == null) {
            throw new NotNullArgumentException(InstitucionMatNeoConstants.SERV_ID_MODIFICAR, InstitucionMatNeoConstants.CLASS_ID, InstitucionMatNeoConstants.ARG_ID_MODIFICAR_INSTITUCIONMATNEOMODIFICARPNOMBRE, InstitucionMatNeoConstants.SERV_ALIAS_MODIFICAR, InstitucionMatNeoConstants.CLASS_ALIAS, InstitucionMatNeoConstants.ARG_ALIAS_MODIFICAR_INSTITUCIONMATNEOMODIFICARPNOMBRE);
        }
    }

    @JsonIgnore
    public Boolean getInstitucionMatNeomodificarpParaMaternidad() {
        return this.institucionMatNeomodificarpParaMaternidad;
    }
    
    public void setInstitucionMatNeomodificarpParaMaternidad(Boolean institucionMatNeomodificarpParaMaternidad) {
        this.institucionMatNeomodificarpParaMaternidad = institucionMatNeomodificarpParaMaternidad;
    }

    private void checkInstitucionMatNeomodificarpParaMaternidad() throws NotNullArgumentException {
        if(this.getInstitucionMatNeomodificarpParaMaternidad() == null) {
            throw new NotNullArgumentException(InstitucionMatNeoConstants.SERV_ID_MODIFICAR, InstitucionMatNeoConstants.CLASS_ID, InstitucionMatNeoConstants.ARG_ID_MODIFICAR_INSTITUCIONMATNEOMODIFICARPPARAMATERNIDAD, InstitucionMatNeoConstants.SERV_ALIAS_MODIFICAR, InstitucionMatNeoConstants.CLASS_ALIAS, InstitucionMatNeoConstants.ARG_ALIAS_MODIFICAR_INSTITUCIONMATNEOMODIFICARPPARAMATERNIDAD);
        }
    }

    @JsonIgnore
    public Boolean getInstitucionMatNeomodificarpParaNeonatal() {
        return this.institucionMatNeomodificarpParaNeonatal;
    }
    
    public void setInstitucionMatNeomodificarpParaNeonatal(Boolean institucionMatNeomodificarpParaNeonatal) {
        this.institucionMatNeomodificarpParaNeonatal = institucionMatNeomodificarpParaNeonatal;
    }

    private void checkInstitucionMatNeomodificarpParaNeonatal() throws NotNullArgumentException {
        if(this.getInstitucionMatNeomodificarpParaNeonatal() == null) {
            throw new NotNullArgumentException(InstitucionMatNeoConstants.SERV_ID_MODIFICAR, InstitucionMatNeoConstants.CLASS_ID, InstitucionMatNeoConstants.ARG_ID_MODIFICAR_INSTITUCIONMATNEOMODIFICARPPARANEONATAL, InstitucionMatNeoConstants.SERV_ALIAS_MODIFICAR, InstitucionMatNeoConstants.CLASS_ALIAS, InstitucionMatNeoConstants.ARG_ALIAS_MODIFICAR_INSTITUCIONMATNEOMODIFICARPPARANEONATAL);
        }
    }

    @JsonIgnore
    public Date getInstitucionMatNeomodificarpFechaInicial() {
        return this.institucionMatNeomodificarpFechaInicial;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setInstitucionMatNeomodificarpFechaInicial(Date institucionMatNeomodificarpFechaInicial) {
        this.institucionMatNeomodificarpFechaInicial = institucionMatNeomodificarpFechaInicial;
    }

    private void checkInstitucionMatNeomodificarpFechaInicial() throws NotNullArgumentException {
        if(this.getInstitucionMatNeomodificarpFechaInicial() == null) {
            throw new NotNullArgumentException(InstitucionMatNeoConstants.SERV_ID_MODIFICAR, InstitucionMatNeoConstants.CLASS_ID, InstitucionMatNeoConstants.ARG_ID_MODIFICAR_INSTITUCIONMATNEOMODIFICARPFECHAINICIAL, InstitucionMatNeoConstants.SERV_ALIAS_MODIFICAR, InstitucionMatNeoConstants.CLASS_ALIAS, InstitucionMatNeoConstants.ARG_ALIAS_MODIFICAR_INSTITUCIONMATNEOMODIFICARPFECHAINICIAL);
        }
    }

    @JsonIgnore
    public Date getInstitucionMatNeomodificarpFechaFinal() {
        return this.institucionMatNeomodificarpFechaFinal;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setInstitucionMatNeomodificarpFechaFinal(Date institucionMatNeomodificarpFechaFinal) {
        this.institucionMatNeomodificarpFechaFinal = institucionMatNeomodificarpFechaFinal;
    }

    private void checkInstitucionMatNeomodificarpFechaFinal() throws NotNullArgumentException {
        if(this.getInstitucionMatNeomodificarpFechaFinal() == null) {
            throw new NotNullArgumentException(InstitucionMatNeoConstants.SERV_ID_MODIFICAR, InstitucionMatNeoConstants.CLASS_ID, InstitucionMatNeoConstants.ARG_ID_MODIFICAR_INSTITUCIONMATNEOMODIFICARPFECHAFINAL, InstitucionMatNeoConstants.SERV_ALIAS_MODIFICAR, InstitucionMatNeoConstants.CLASS_ALIAS, InstitucionMatNeoConstants.ARG_ALIAS_MODIFICAR_INSTITUCIONMATNEOMODIFICARPFECHAFINAL);
        }
    }
}
