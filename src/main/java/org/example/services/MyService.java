package org.example.services;

import org.example.entity.Log;
import java.util.List;

public interface MyService {

	Log addLog(Log log);

	Log updateLog(Log log);

	List<Log> getLogs();

	void deleteLog(Log log);
}
