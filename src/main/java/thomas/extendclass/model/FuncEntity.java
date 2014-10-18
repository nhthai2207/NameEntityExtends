package thomas.extendclass.model;

@CollectionName(name = "function")
public class FuncEntity extends NameEntity {

	private long funcId;

	private String clazz;
	private String module;
	private String type;
	private String src_file;
	private String description_url;
	private String description;

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSrc_file() {
		return src_file;
	}

	public void setSrc_file(String src_file) {
		this.src_file = src_file;
	}

	public String getDescription_url() {
		return description_url;
	}

	public void setDescription_url(String description_url) {
		this.description_url = description_url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getFuncId() {
		return funcId;
	}

	public void setFuncId(long funcId) {
		this.funcId = funcId;
	}
}