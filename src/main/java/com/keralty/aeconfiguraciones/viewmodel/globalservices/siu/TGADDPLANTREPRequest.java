package com.keralty.aeconfiguraciones.viewmodel.globalservices.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GlobalServiceConstants;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneDeserializer;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneSerializer;
import java.sql.Date;

public class TGADDPLANTREPRequest {

    @JsonProperty("pt_p_atrtipo")
    private Long tGADDPLANTREPptpatrTipo;

    @JsonProperty("pt_p_atrtitulo")
    private String tGADDPLANTREPptpatrTitulo;

    @JsonProperty("pt_p_atrfechavigencia")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date tGADDPLANTREPptpatrFechaVigencia;

    @JsonProperty("pt_p_atrrutaarchivos")
    private String tGADDPLANTREPptpatrRutaArchivos;

    @JsonProperty("pt_p_atrnombrearchivopdf")
    private String tGADDPLANTREPptpatrNombreArchivoPDF;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkTGADDPLANTREPptpatrTipo();
        checkTGADDPLANTREPptpatrTitulo();
        checkTGADDPLANTREPptpatrFechaVigencia();
        checkTGADDPLANTREPptpatrRutaArchivos();
        checkTGADDPLANTREPptpatrNombreArchivoPDF();
    }

    @JsonIgnore
    public Long getTGADDPLANTREPptpatrTipo() {
        return this.tGADDPLANTREPptpatrTipo;
    }
    
    public void setTGADDPLANTREPptpatrTipo(Long tGADDPLANTREPptpatrTipo) {
        this.tGADDPLANTREPptpatrTipo = tGADDPLANTREPptpatrTipo;
    }

    private void checkTGADDPLANTREPptpatrTipo() throws NotNullArgumentException {
        if(this.getTGADDPLANTREPptpatrTipo() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGADDPLANTREP, GlobalServiceConstants.GS_ARG_ID_TGADDPLANTREP_TGADDPLANTREPPTPATRTIPO, GlobalServiceConstants.G_SERV_ALIAS_TGADDPLANTREP, GlobalServiceConstants.GS_ARG_ALIAS_TGADDPLANTREP_TGADDPLANTREPPTPATRTIPO);
        }
    }

    @JsonIgnore
    public String getTGADDPLANTREPptpatrTitulo() {
        return this.tGADDPLANTREPptpatrTitulo;
    }
    
    public void setTGADDPLANTREPptpatrTitulo(String tGADDPLANTREPptpatrTitulo) {
        this.tGADDPLANTREPptpatrTitulo = tGADDPLANTREPptpatrTitulo;
    }

    private void checkTGADDPLANTREPptpatrTitulo() throws NotNullArgumentException {
        if(this.getTGADDPLANTREPptpatrTitulo() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGADDPLANTREP, GlobalServiceConstants.GS_ARG_ID_TGADDPLANTREP_TGADDPLANTREPPTPATRTITULO, GlobalServiceConstants.G_SERV_ALIAS_TGADDPLANTREP, GlobalServiceConstants.GS_ARG_ALIAS_TGADDPLANTREP_TGADDPLANTREPPTPATRTITULO);
        }
    }

    @JsonIgnore
    public Date getTGADDPLANTREPptpatrFechaVigencia() {
        return this.tGADDPLANTREPptpatrFechaVigencia;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setTGADDPLANTREPptpatrFechaVigencia(Date tGADDPLANTREPptpatrFechaVigencia) {
        this.tGADDPLANTREPptpatrFechaVigencia = tGADDPLANTREPptpatrFechaVigencia;
    }

    private void checkTGADDPLANTREPptpatrFechaVigencia() throws NotNullArgumentException {
        if(this.getTGADDPLANTREPptpatrFechaVigencia() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGADDPLANTREP, GlobalServiceConstants.GS_ARG_ID_TGADDPLANTREP_TGADDPLANTREPPTPATRFECHAVIGENCIA, GlobalServiceConstants.G_SERV_ALIAS_TGADDPLANTREP, GlobalServiceConstants.GS_ARG_ALIAS_TGADDPLANTREP_TGADDPLANTREPPTPATRFECHAVIGENCIA);
        }
    }

    @JsonIgnore
    public String getTGADDPLANTREPptpatrRutaArchivos() {
        return this.tGADDPLANTREPptpatrRutaArchivos;
    }
    
    public void setTGADDPLANTREPptpatrRutaArchivos(String tGADDPLANTREPptpatrRutaArchivos) {
        this.tGADDPLANTREPptpatrRutaArchivos = tGADDPLANTREPptpatrRutaArchivos;
    }

    private void checkTGADDPLANTREPptpatrRutaArchivos() throws NotNullArgumentException {
        if(this.getTGADDPLANTREPptpatrRutaArchivos() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGADDPLANTREP, GlobalServiceConstants.GS_ARG_ID_TGADDPLANTREP_TGADDPLANTREPPTPATRRUTAARCHIVOS, GlobalServiceConstants.G_SERV_ALIAS_TGADDPLANTREP, GlobalServiceConstants.GS_ARG_ALIAS_TGADDPLANTREP_TGADDPLANTREPPTPATRRUTAARCHIVOS);
        }
    }

    @JsonIgnore
    public String getTGADDPLANTREPptpatrNombreArchivoPDF() {
        return this.tGADDPLANTREPptpatrNombreArchivoPDF;
    }
    
    public void setTGADDPLANTREPptpatrNombreArchivoPDF(String tGADDPLANTREPptpatrNombreArchivoPDF) {
        this.tGADDPLANTREPptpatrNombreArchivoPDF = tGADDPLANTREPptpatrNombreArchivoPDF;
    }

    private void checkTGADDPLANTREPptpatrNombreArchivoPDF() throws NotNullArgumentException {
        if(this.getTGADDPLANTREPptpatrNombreArchivoPDF() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGADDPLANTREP, GlobalServiceConstants.GS_ARG_ID_TGADDPLANTREP_TGADDPLANTREPPTPATRNOMBREARCHIVOPDF, GlobalServiceConstants.G_SERV_ALIAS_TGADDPLANTREP, GlobalServiceConstants.GS_ARG_ALIAS_TGADDPLANTREP_TGADDPLANTREPPTPATRNOMBREARCHIVOPDF);
        }
    }

    @JsonIgnore
    public String getPreconditionId() {
        return this.preconditionId;
    }
    
    public void setPreconditionId(String preconditionId) {
        this.preconditionId = preconditionId;
    }
}
