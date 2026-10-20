package com.keralty.aeconfiguraciones.viewmodel.seccion.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.SeccionConstants;
import com.keralty.aeconfiguraciones.persistence.oid.SeccionOid;
import com.keralty.aeconfiguraciones.persistence.Seccion;

public class TCAMBIARORDENPREGSRequest {

    @JsonProperty("p_thisseccion_oid")
    private SeccionOid seccionTCAMBIARORDENPREGSpthisSeccion;
    
    @JsonIgnore
    private Seccion seccionTCAMBIARORDENPREGSpthisSeccionInstance;

    @JsonProperty("ptordendesde")
    private Long seccionTCAMBIARORDENPREGSptOrdenDesde;

    @JsonProperty("ptsubir")
    private Boolean seccionTCAMBIARORDENPREGSptSubir;

    @JsonProperty("ptordenhasta")
    private Long seccionTCAMBIARORDENPREGSptOrdenHasta;

    public void checkArguments() throws NotNullArgumentException {
        checkSeccionTCAMBIARORDENPREGSpthisSeccion();
        checkSeccionTCAMBIARORDENPREGSptOrdenDesde();
        checkSeccionTCAMBIARORDENPREGSptSubir();
        checkSeccionTCAMBIARORDENPREGSptOrdenHasta();
    }

    @JsonIgnore
    public SeccionOid getSeccionTCAMBIARORDENPREGSpthisSeccion() {
        return this.seccionTCAMBIARORDENPREGSpthisSeccion;
    }
    
    public void setSeccionTCAMBIARORDENPREGSpthisSeccion(SeccionOid seccionTCAMBIARORDENPREGSpthisSeccion) {
        this.seccionTCAMBIARORDENPREGSpthisSeccion = seccionTCAMBIARORDENPREGSpthisSeccion;
    }

    @JsonIgnore
    public Seccion getSeccionTCAMBIARORDENPREGSpthisSeccionInstance() {
        return this.seccionTCAMBIARORDENPREGSpthisSeccionInstance;
    }
    
    public void setSeccionTCAMBIARORDENPREGSpthisSeccionInstance(Seccion seccionTCAMBIARORDENPREGSpthisSeccion) {
        this.seccionTCAMBIARORDENPREGSpthisSeccionInstance = seccionTCAMBIARORDENPREGSpthisSeccion;
        this.seccionTCAMBIARORDENPREGSpthisSeccion = seccionTCAMBIARORDENPREGSpthisSeccion != null ? seccionTCAMBIARORDENPREGSpthisSeccion.getOid() : null;
    }

    private void checkSeccionTCAMBIARORDENPREGSpthisSeccion() throws NotNullArgumentException {
        if(this.getSeccionTCAMBIARORDENPREGSpthisSeccionInstance() == null || this.getSeccionTCAMBIARORDENPREGSpthisSeccionInstance().isNull()) {
            throw new NotNullArgumentException(SeccionConstants.SERV_ID_TCAMBIARORDENPREGS, SeccionConstants.CLASS_ID, SeccionConstants.ARG_ID_TCAMBIARORDENPREGS_SECCIONTCAMBIARORDENPREGSPTHISSECCION, SeccionConstants.SERV_ALIAS_TCAMBIARORDENPREGS, SeccionConstants.CLASS_ALIAS, SeccionConstants.ARG_ALIAS_TCAMBIARORDENPREGS_SECCIONTCAMBIARORDENPREGSPTHISSECCION);
        }
    }

    @JsonIgnore
    public Long getSeccionTCAMBIARORDENPREGSptOrdenDesde() {
        return this.seccionTCAMBIARORDENPREGSptOrdenDesde;
    }
    
    public void setSeccionTCAMBIARORDENPREGSptOrdenDesde(Long seccionTCAMBIARORDENPREGSptOrdenDesde) {
        this.seccionTCAMBIARORDENPREGSptOrdenDesde = seccionTCAMBIARORDENPREGSptOrdenDesde;
    }

    private void checkSeccionTCAMBIARORDENPREGSptOrdenDesde() throws NotNullArgumentException {
        if(this.getSeccionTCAMBIARORDENPREGSptOrdenDesde() == null) {
            throw new NotNullArgumentException(SeccionConstants.SERV_ID_TCAMBIARORDENPREGS, SeccionConstants.CLASS_ID, SeccionConstants.ARG_ID_TCAMBIARORDENPREGS_SECCIONTCAMBIARORDENPREGSPTORDENDESDE, SeccionConstants.SERV_ALIAS_TCAMBIARORDENPREGS, SeccionConstants.CLASS_ALIAS, SeccionConstants.ARG_ALIAS_TCAMBIARORDENPREGS_SECCIONTCAMBIARORDENPREGSPTORDENDESDE);
        }
    }

    @JsonIgnore
    public Boolean getSeccionTCAMBIARORDENPREGSptSubir() {
        return this.seccionTCAMBIARORDENPREGSptSubir;
    }
    
    public void setSeccionTCAMBIARORDENPREGSptSubir(Boolean seccionTCAMBIARORDENPREGSptSubir) {
        this.seccionTCAMBIARORDENPREGSptSubir = seccionTCAMBIARORDENPREGSptSubir;
    }

    private void checkSeccionTCAMBIARORDENPREGSptSubir() throws NotNullArgumentException {
        if(this.getSeccionTCAMBIARORDENPREGSptSubir() == null) {
            throw new NotNullArgumentException(SeccionConstants.SERV_ID_TCAMBIARORDENPREGS, SeccionConstants.CLASS_ID, SeccionConstants.ARG_ID_TCAMBIARORDENPREGS_SECCIONTCAMBIARORDENPREGSPTSUBIR, SeccionConstants.SERV_ALIAS_TCAMBIARORDENPREGS, SeccionConstants.CLASS_ALIAS, SeccionConstants.ARG_ALIAS_TCAMBIARORDENPREGS_SECCIONTCAMBIARORDENPREGSPTSUBIR);
        }
    }

    @JsonIgnore
    public Long getSeccionTCAMBIARORDENPREGSptOrdenHasta() {
        return this.seccionTCAMBIARORDENPREGSptOrdenHasta;
    }
    
    public void setSeccionTCAMBIARORDENPREGSptOrdenHasta(Long seccionTCAMBIARORDENPREGSptOrdenHasta) {
        this.seccionTCAMBIARORDENPREGSptOrdenHasta = seccionTCAMBIARORDENPREGSptOrdenHasta;
    }

    private void checkSeccionTCAMBIARORDENPREGSptOrdenHasta() throws NotNullArgumentException {
        if(this.getSeccionTCAMBIARORDENPREGSptOrdenHasta() == null) {
            throw new NotNullArgumentException(SeccionConstants.SERV_ID_TCAMBIARORDENPREGS, SeccionConstants.CLASS_ID, SeccionConstants.ARG_ID_TCAMBIARORDENPREGS_SECCIONTCAMBIARORDENPREGSPTORDENHASTA, SeccionConstants.SERV_ALIAS_TCAMBIARORDENPREGS, SeccionConstants.CLASS_ALIAS, SeccionConstants.ARG_ALIAS_TCAMBIARORDENPREGS_SECCIONTCAMBIARORDENPREGSPTORDENHASTA);
        }
    }
}
