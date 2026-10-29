package com.keralty.aeconfiguraciones.viewmodel.globalservices.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GlobalServiceConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PlantillaReporteOid;
import com.keralty.aeconfiguraciones.persistence.PlantillaReporte;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneDeserializer;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneSerializer;
import java.sql.Date;

public class TGEDITPLANTREPRequest {

    @JsonProperty("pt_p_thisplantillareporte_oid")
    private PlantillaReporteOid tGEDITPLANTREPptpthisPlantillaReporte;
    
    @JsonIgnore
    private PlantillaReporte tGEDITPLANTREPptpthisPlantillaReporteInstance;

    @JsonProperty("pt_p_titulo")
    private String tGEDITPLANTREPptpTitulo;

    @JsonProperty("pt_p_fechavigencia")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date tGEDITPLANTREPptpFechaVigencia;

    @JsonProperty("pt_p_rutaarchivos")
    private String tGEDITPLANTREPptpRutaArchivos;

    @JsonProperty("pt_p_nombrearchivopdf")
    private String tGEDITPLANTREPptpNombreArchivoPDF;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkTGEDITPLANTREPptpthisPlantillaReporte();
        checkTGEDITPLANTREPptpTitulo();
        checkTGEDITPLANTREPptpFechaVigencia();
        checkTGEDITPLANTREPptpRutaArchivos();
        checkTGEDITPLANTREPptpNombreArchivoPDF();
    }

    @JsonIgnore
    public PlantillaReporteOid getTGEDITPLANTREPptpthisPlantillaReporte() {
        return this.tGEDITPLANTREPptpthisPlantillaReporte;
    }
    
    public void setTGEDITPLANTREPptpthisPlantillaReporte(PlantillaReporteOid tGEDITPLANTREPptpthisPlantillaReporte) {
        this.tGEDITPLANTREPptpthisPlantillaReporte = tGEDITPLANTREPptpthisPlantillaReporte;
    }

    @JsonIgnore
    public PlantillaReporte getTGEDITPLANTREPptpthisPlantillaReporteInstance() {
        return this.tGEDITPLANTREPptpthisPlantillaReporteInstance;
    }
    
    public void setTGEDITPLANTREPptpthisPlantillaReporteInstance(PlantillaReporte tGEDITPLANTREPptpthisPlantillaReporte) {
        this.tGEDITPLANTREPptpthisPlantillaReporteInstance = tGEDITPLANTREPptpthisPlantillaReporte;
        this.tGEDITPLANTREPptpthisPlantillaReporte = tGEDITPLANTREPptpthisPlantillaReporte != null ? tGEDITPLANTREPptpthisPlantillaReporte.getOid() : null;
    }

    private void checkTGEDITPLANTREPptpthisPlantillaReporte() throws NotNullArgumentException {
        if(this.getTGEDITPLANTREPptpthisPlantillaReporteInstance() == null || this.getTGEDITPLANTREPptpthisPlantillaReporteInstance().isNull()) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGEDITPLANTREP, GlobalServiceConstants.GS_ARG_ID_TGEDITPLANTREP_TGEDITPLANTREPPTPTHISPLANTILLAREPORTE, GlobalServiceConstants.G_SERV_ALIAS_TGEDITPLANTREP, GlobalServiceConstants.GS_ARG_ALIAS_TGEDITPLANTREP_TGEDITPLANTREPPTPTHISPLANTILLAREPORTE);
        }
    }

    @JsonIgnore
    public String getTGEDITPLANTREPptpTitulo() {
        return this.tGEDITPLANTREPptpTitulo;
    }
    
    public void setTGEDITPLANTREPptpTitulo(String tGEDITPLANTREPptpTitulo) {
        this.tGEDITPLANTREPptpTitulo = tGEDITPLANTREPptpTitulo;
    }

    private void checkTGEDITPLANTREPptpTitulo() throws NotNullArgumentException {
        if(this.getTGEDITPLANTREPptpTitulo() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGEDITPLANTREP, GlobalServiceConstants.GS_ARG_ID_TGEDITPLANTREP_TGEDITPLANTREPPTPTITULO, GlobalServiceConstants.G_SERV_ALIAS_TGEDITPLANTREP, GlobalServiceConstants.GS_ARG_ALIAS_TGEDITPLANTREP_TGEDITPLANTREPPTPTITULO);
        }
    }

    @JsonIgnore
    public Date getTGEDITPLANTREPptpFechaVigencia() {
        return this.tGEDITPLANTREPptpFechaVigencia;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setTGEDITPLANTREPptpFechaVigencia(Date tGEDITPLANTREPptpFechaVigencia) {
        this.tGEDITPLANTREPptpFechaVigencia = tGEDITPLANTREPptpFechaVigencia;
    }

    private void checkTGEDITPLANTREPptpFechaVigencia() throws NotNullArgumentException {
        if(this.getTGEDITPLANTREPptpFechaVigencia() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGEDITPLANTREP, GlobalServiceConstants.GS_ARG_ID_TGEDITPLANTREP_TGEDITPLANTREPPTPFECHAVIGENCIA, GlobalServiceConstants.G_SERV_ALIAS_TGEDITPLANTREP, GlobalServiceConstants.GS_ARG_ALIAS_TGEDITPLANTREP_TGEDITPLANTREPPTPFECHAVIGENCIA);
        }
    }

    @JsonIgnore
    public String getTGEDITPLANTREPptpRutaArchivos() {
        return this.tGEDITPLANTREPptpRutaArchivos;
    }
    
    public void setTGEDITPLANTREPptpRutaArchivos(String tGEDITPLANTREPptpRutaArchivos) {
        this.tGEDITPLANTREPptpRutaArchivos = tGEDITPLANTREPptpRutaArchivos;
    }

    private void checkTGEDITPLANTREPptpRutaArchivos() throws NotNullArgumentException {
        if(this.getTGEDITPLANTREPptpRutaArchivos() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGEDITPLANTREP, GlobalServiceConstants.GS_ARG_ID_TGEDITPLANTREP_TGEDITPLANTREPPTPRUTAARCHIVOS, GlobalServiceConstants.G_SERV_ALIAS_TGEDITPLANTREP, GlobalServiceConstants.GS_ARG_ALIAS_TGEDITPLANTREP_TGEDITPLANTREPPTPRUTAARCHIVOS);
        }
    }

    @JsonIgnore
    public String getTGEDITPLANTREPptpNombreArchivoPDF() {
        return this.tGEDITPLANTREPptpNombreArchivoPDF;
    }
    
    public void setTGEDITPLANTREPptpNombreArchivoPDF(String tGEDITPLANTREPptpNombreArchivoPDF) {
        this.tGEDITPLANTREPptpNombreArchivoPDF = tGEDITPLANTREPptpNombreArchivoPDF;
    }

    private void checkTGEDITPLANTREPptpNombreArchivoPDF() throws NotNullArgumentException {
        if(this.getTGEDITPLANTREPptpNombreArchivoPDF() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGEDITPLANTREP, GlobalServiceConstants.GS_ARG_ID_TGEDITPLANTREP_TGEDITPLANTREPPTPNOMBREARCHIVOPDF, GlobalServiceConstants.G_SERV_ALIAS_TGEDITPLANTREP, GlobalServiceConstants.GS_ARG_ALIAS_TGEDITPLANTREP_TGEDITPLANTREPPTPNOMBREARCHIVOPDF);
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
