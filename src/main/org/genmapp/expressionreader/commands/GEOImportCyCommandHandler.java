/*******************************************************************************
 * Copyright 2010 Alexander Pico
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.genmapp.expressionreader.commands;

import java.util.Collection;
import java.util.Map;

import cytoscape.command.AbstractCommandHandler;
import cytoscape.command.CyCommandException;
import cytoscape.command.CyCommandManager;
import cytoscape.command.CyCommandResult;
import cytoscape.layout.Tunable;

/**
 * CyCommandHandler registration/execution
 * 
 */
public class GEOImportCyCommandHandler extends AbstractCommandHandler {

	// implements CyCommandHandler, SOFTViewer {

	public final static String NAMESPACE = "expression data reader";

	public final static String CREATE_NETWORK = "create network";
	public final static String ARG_CREATE_NETWORK = "toggle";
	public static boolean CREATE_NETWORK_TOGGLE = false;

	public final static String IMPORT_SOURCE = "get source";
	public static String IMPORT_SOURCE_URL = null;

	public GEOImportCyCommandHandler() {
		super(CyCommandManager.reserveNamespace(NAMESPACE));

		addDescription(CREATE_NETWORK,
				"Set toggle to create network and view from imported GEO data");
		addArgument(CREATE_NETWORK, ARG_CREATE_NETWORK);

		addDescription(IMPORT_SOURCE, "get URL for imported GEO table");
		addArgument(IMPORT_SOURCE);
	}

	public CyCommandResult execute(String command, Collection<Tunable> args)
			throws CyCommandException {
		return execute(command, createKVMap(args));
	}

	public CyCommandResult execute(String command, Map<String, Object> args)
			throws CyCommandException {
		CyCommandResult result = new CyCommandResult();

		for (String t : args.keySet()) {
			result.addMessage("Arg: " + t + " = " + args.get(t));
		}
		if (CREATE_NETWORK.equals(command)) {
			boolean val = Boolean.parseBoolean((String) args
					.get(ARG_CREATE_NETWORK));
			CREATE_NETWORK_TOGGLE = val;
		} else if (IMPORT_SOURCE.equals(command)) {
			result.addResult(IMPORT_SOURCE_URL);
			result.addMessage("returning URL: " + IMPORT_SOURCE_URL);
		} else {
			result.addError("Command not supported: " + command);
		}
		return (result);
	}
}

