package com.wellevate.utilities;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StateListAdd extends StateSearch {
	static List<String> allStates = null;

	public List<String> Nexus() throws IOException {

		try {
			Set<String> listStates = searchForMatchesInAllFiles(
					"src\\com\\wellevate\\configuration\\NexusState.properties");
			allStates = listStates.parallelStream().collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allStates;
	}

	public List<String> NonNexus() {
		try {

			Set<String> listStates = searchForMatchesInAllFiles(
					"src\\com\\wellevate\\configuration\\NonNexusState.properties");
			allStates = listStates.parallelStream().collect(Collectors.toList());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("lo p");
		}

		return allStates;
	}

	public List<String> NonTaxable() throws IOException {

		try {

			Set<String> listStates = searchForMatchesInAllFiles(
					"src\\com\\wellevate\\configuration\\NonNexusState.properties");

			allStates = listStates.parallelStream().collect(Collectors.toList());

		} catch (Exception e) {
			// TODO: handle exception
		}

		return allStates;
	}

	public List<String> TaxableProducts() throws IOException {

		try {

			Set<String> listStates = searchForMatchesInAllFiles(
					"src\\com\\wellevate\\configuration\\TaxableProduct.properties");

			allStates = listStates.parallelStream().collect(Collectors.toList());
		} catch (Exception e) {
			// TODO: handle exception
		}

		return allStates;
	}

	public List<String> ApiValue() throws IOException {

		try {

			Set<String> listStates = searchForMatchesInAllFiles(
					"src\\com\\wellevate\\configuration\\NexusState.properties");

			allStates = listStates.parallelStream().collect(Collectors.toList());
		} catch (Exception e) {
			// TODO: handle exception
		}

		return allStates;
		// }
	}
}