package com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ReportesProcAlmConstants;
import com.keralty.aeconfiguraciones.persistence.oid.ReportesProcAlmOid;
import com.keralty.aeconfiguraciones.persistence.ReportesProcAlm;

public class EliminarRequest {

    @JsonProperty("p_thisreportesprocalm_oid")
    private ReportesProcAlmOid reportesProcAlmeliminarpthisReportesProcAlm;
    
    @JsonIgnore
    private ReportesProcAlm reportesProcAlmeliminarpthisReportesProcAlmInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkReportesProcAlmeliminarpthisReportesProcAlm();
    }

    @JsonIgnore
    public ReportesProcAlmOid getReportesProcAlmeliminarpthisReportesProcAlm() {
        return this.reportesProcAlmeliminarpthisReportesProcAlm;
    }
    
    public void setReportesProcAlmeliminarpthisReportesProcAlm(ReportesProcAlmOid reportesProcAlmeliminarpthisReportesProcAlm) {
        this.reportesProcAlmeliminarpthisReportesProcAlm = reportesProcAlmeliminarpthisReportesProcAlm;
    }

    @JsonIgnore
    public ReportesProcAlm getReportesProcAlmeliminarpthisReportesProcAlmInstance() {
        return this.reportesProcAlmeliminarpthisReportesProcAlmInstance;
    }
    
    public void setReportesProcAlmeliminarpthisReportesProcAlmInstance(ReportesProcAlm reportesProcAlmeliminarpthisReportesProcAlm) {
        this.reportesProcAlmeliminarpthisReportesProcAlmInstance = reportesProcAlmeliminarpthisReportesProcAlm;
        this.reportesProcAlmeliminarpthisReportesProcAlm = reportesProcAlmeliminarpthisReportesProcAlm != null ? reportesProcAlmeliminarpthisReportesProcAlm.getOid() : null;
    }

    private void checkReportesProcAlmeliminarpthisReportesProcAlm() throws NotNullArgumentException {
        if(this.getReportesProcAlmeliminarpthisReportesProcAlmInstance() == null || this.getReportesProcAlmeliminarpthisReportesProcAlmInstance().isNull()) {
            throw new NotNullArgumentException(ReportesProcAlmConstants.SERV_ID_ELIMINAR, ReportesProcAlmConstants.CLASS_ID, ReportesProcAlmConstants.ARG_ID_ELIMINAR_REPORTESPROCALMELIMINARPTHISREPORTESPROCALM, ReportesProcAlmConstants.SERV_ALIAS_ELIMINAR, ReportesProcAlmConstants.CLASS_ALIAS, ReportesProcAlmConstants.ARG_ALIAS_ELIMINAR_REPORTESPROCALMELIMINARPTHISREPORTESPROCALM);
        }
    }
}
