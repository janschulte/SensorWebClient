/**
 * Copyright (C) 2012-2014 52°North Initiative for Geospatial Open Source
 * Software GmbH
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 as publishedby the Free
 * Software Foundation.
 *
 * If the program is linked with libraries which are licensed under one of the
 * following licenses, the combination of the program with the linked library is
 * not considered a "derivative work" of the program:
 *
 *     - Apache License, version 2.0
 *     - Apache Software License, version 1.0
 *     - GNU Lesser General Public License, version 3
 *     - Mozilla Public License, versions 1.0, 1.1 and 2.0
 *     - Common Development and Distribution License (CDDL), version 1.0
 *
 * Therefore the distribution of the program linked with libraries licensed under
 * the aforementioned licenses, is permitted by the copyright holders if the
 * distribution is compliant with both the GNU General Public License version 2
 * and the aforementioned licenses.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details.
 */
package org.n52.series.api.proxy.v1.srv;

import static org.n52.series.api.proxy.v1.srv.QueryParameterAdapter.createQueryParameters;
import static org.n52.server.mgmt.ConfigurationContext.getSOSMetadatas;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.n52.series.api.proxy.v1.io.ProcedureConverter;
import org.n52.io.IoParameters;
import org.n52.io.v1.data.ProcedureOutput;
import org.n52.shared.requests.query.QueryParameters;
import org.n52.shared.serializable.pojos.sos.Procedure;
import org.n52.shared.serializable.pojos.sos.SOSMetadata;
import org.n52.shared.serializable.pojos.sos.SosTimeseries;
import org.n52.shared.serializable.pojos.sos.TimeseriesParametersLookup;
import org.n52.sensorweb.v1.spi.ParameterService;

public class ProcedureOutputAdapter implements ParameterService<ProcedureOutput> {

	@Override
	public ProcedureOutput[] getExpandedParameters(IoParameters map) {
        QueryParameters query = createQueryParameters(map);
		List<ProcedureOutput> allProcedures = new ArrayList<ProcedureOutput>();
		for (SOSMetadata metadata : getSOSMetadatas()) {
		    ProcedureConverter converter = new ProcedureConverter(metadata);
			allProcedures.addAll(converter.convertExpanded(filter(metadata, query)));
		}
		return allProcedures.toArray(new ProcedureOutput[0]);
	}

	@Override
    public ProcedureOutput[] getCondensedParameters(IoParameters map) {
        QueryParameters query = createQueryParameters(map);
        List<ProcedureOutput> allProcedures = new ArrayList<ProcedureOutput>();
        for (SOSMetadata metadata : getSOSMetadatas()) {
            ProcedureConverter converter = new ProcedureConverter(metadata);
            allProcedures.addAll(converter.convertCondensed(filter(metadata, query)));
        }
        return allProcedures.toArray(new ProcedureOutput[0]);
    }

    private Procedure[] filter(SOSMetadata metadata, QueryParameters query) {
        Set<Procedure> allProcedures = new HashSet<Procedure>();
        for (SosTimeseries timeseries : metadata.getMatchingTimeseries(query)) {
          allProcedures.add(timeseries.getProcedure());
        }
        return allProcedures.toArray(new Procedure[0]);
    }

	@Override
    public ProcedureOutput[] getParameters(String[] procedureIds) {
	    return getParameters(procedureIds, IoParameters.createDefaults());
    }

    @Override
    public ProcedureOutput[] getParameters(String[] procedureIds,IoParameters query) {
        List<ProcedureOutput> selectedProcedures = new ArrayList<ProcedureOutput>();
        for (String procedureId : procedureIds) {
            ProcedureOutput procedure = getParameter(procedureId);
            if (procedure != null) {
                selectedProcedures.add(procedure);
            }
        }
        return selectedProcedures.toArray(new ProcedureOutput[0]);
    }

    @Override
	public ProcedureOutput getParameter(String procedureId) {
		return getParameter(procedureId, IoParameters.createDefaults());
	}

    @Override
    public ProcedureOutput getParameter(String procedureId, IoParameters query) {
        for (SOSMetadata metadata : getSOSMetadatas()) {
            TimeseriesParametersLookup lookup = metadata.getTimeseriesParametersLookup();
            for (Procedure procedure : lookup.getProcedures()) {
                if (procedure.getGlobalId().equals(procedureId)) {
                    ProcedureConverter converter = new ProcedureConverter(metadata);
                    return converter.convertExpanded(procedure);
                }
            }
        }
        return null;
    }



}
