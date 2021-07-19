package cursojava;

import cursojava.entity.Log;
import cursojava.services.MyService;

import java.util.List;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.lang.Strings;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class MyViewModel {

	@WireVariable
	private MyService myService;
	private ListModelList<Log> logListModel;
	private String message;
	private boolean editable=false;

	@Init
	public void init() {
		Log logDePrueba= new Log("Este es un log que generé desde mi clase java y no tiene persistencia");
		myService.addLog(logDePrueba);

		List<Log> logList = myService.getLogs();

		logListModel = new ListModelList<Log>(logList);
	}

	public ListModel<Log> getLogListModel() {
		return logListModel;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	@Command
	public void addLog() {
		if(Strings.isBlank(message)) {
			return;
		}
		Log log = new Log(message);
		log = myService.addLog(log);
		logListModel.add(log);
	}

	@Command
	public void deleteLog(@BindingParam("log") Log log) {
		myService.deleteLog(log);
		logListModel.remove(log);
	}

	@Command
	public void updateLog(@BindingParam("log") Log log) {
		myService.updateLog(log);

	}

	@Command
	public void edit() {
		this.setEditable(!this.isEditable());
	}

}
