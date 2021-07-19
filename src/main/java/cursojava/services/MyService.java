package cursojava.services;

import cursojava.entity.Log;

import java.util.List;

public interface MyService {

	Log addLog(Log log);

	Log updateLog(Log log);

	List<Log> getLogs();

	void deleteLog(Log log);
}
