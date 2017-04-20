package com.wellevate.utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StateSearch {
	Set<File> files = new HashSet();

	// public void addState(File file) {
	// files.add(file);
	// }

	public Set<String> searchForMatchesInAllFiles(String filepath) throws IOException {

		Map<String, List<String>> linesPerPath = new HashMap<>();
		// for (File file : files) {
		// Path path = file.toPath();
		linesPerPath.put(filepath, Files.readAllLines(new File(filepath).toPath()));
		// }
		Set<String> state = new HashSet();
		Iterator<String> it = linesPerPath.keySet().iterator();
		List<String> linesA = null;
		while (it.hasNext()) {
			// if (null == linesA) {
			linesA = linesPerPath.get(it.next());
			// }
		}

		state.addAll(linesA);

		return state;
	}

}
